package ai.sl;

////////////////////////////////////////////////////////////////////////////
//
// Copyright (C) DSTC Pty Ltd (ACN 052 372 577) 2002.
// Unpublished work.  All Rights Reserved.
//
// The software contained on this media is the property of the
// DSTC Pty Ltd.  Use of this software is strictly in accordance
// with the license agreement in the accompanying LICENSE.DOC
// file. If your distribution of this software does not contain
// a LICENSE.DOC file then you have no rights to use this
// software in any manner and should contact DSTC at the address
// below to determine an appropriate licensing arrangement.
//
//      DSTC Pty Ltd
//      Level 7, GP South
//      University of Queensland
//      St Lucia, 4072
//      Australia
//      Tel: +61 7 3365 4310
//      Fax: +61 7 3365 4311
//      Email: enquiries@dstc.edu.au
//
// This software is being provided "AS IS" without warranty of
// any kind.  In no event shall DSTC Pty Ltd be liable for
// damage of any kind arising out of or in connection with
// the use or performance of this software.
//
////////////////////////////////////////////////////////////////////////////

import java.text.DecimalFormat;

/**
 * This class defines an opinion with corresponding operators.
 * <p>Copyright (c) DSTC Pty Ltd 2002</p>
 * @author Audun Josang
 * @author Robert Peime
 * @version 2.0 - 15/10/2002
 */
public class Opinion {
	private static DecimalFormat fmt = new DecimalFormat("#.###");

	public  double b = 0;
	public  double d = 0;
	public  double u = 1;
	public  double a = 0.5;
	// Constructor:
	public Opinion(double b,
			double d,
			double u,
			double a) {
		this.b  = b;
		this.d  = d;
		this.u  = u;
		this.a  = a;
	}
	public Opinion() {
	}
	public String toString() {
		return ""
				+ fmt.format(b) + ", "
				+ fmt.format(d) + ", "
				+ fmt.format(u) + ", "
				+ fmt.format(a);
	}
	/**
	 * Multiplication of opinions about states in disjoint frames of
	 * discernment produces the product of the two opinions, i.e. the 
	 * opinion about the logical AND of those states. 
	 * I.c.o. dogmatic argument opinions (i.e. u=0), the product opinion
	 * is equivalent to the product of probabilities. 
	 * I.c.o. absolute argument opinions (i.e. u=0 and b=1 or d=1) the product
	 * opinion is equivalent to logical AND of binary propositions.
	 * The general case handles uncertainty and relative atomicity.
	 * NOTE: Zero divisor case not fully studied.
	 */
	public static Opinion Multiplication(Opinion x, Opinion y) {
		Opinion o = new Opinion();
		double divisor = 1;
		o.b = x.b*y.b;
		o.d = x.d + y.d - x.d*y.d;
		o.u = x.b*y.u + y.b*x.u + x.u*y.u;
		divisor = x.b*y.u + y.b*x.u + x.u*y.u;
		if (divisor != 0) {
			/*
			 * Normal case: No zero divisor for relative atomicity.
			 */
			o.a = (y.a*x.b*y.u + x.a*y.b*x.u + x.a*y.a*x.u*y.u)/
					(x.b*y.u + y.b*x.u + x.u*y.u);
		} else if ((y.u == 0) && (x.u == 0)) {
			/*
			 * Zero divisor. The expression below produces limit value
			 * of the relative atomicity o.a when (x.u, y.u) --> 0.
			 */
			o.a = (y.a*x.b + x.a*y.b)/(x.b + y.b);
		} else if (x.d == 1) {
			/*
			 * Zero divisor. The expression below produces limit value
			 * of the relative atomicity o.a when (x.b, x.u) --> 0.
			 */
			o.a = x.a;
		} else if (y.d == 1) {
			/*
			 * Zero divisor. The expression below produces limit value
			 * of the relative atomicity o.a when (y.b, y.u) --> 0.
			 */
			o.a = y.a;
		} else {
			/*
			 * Undefined zero divisor case.
			 */
			o.a = 0.5;
		}
		OpinionConsistency.Check(o);
		return o;
	}

	/**
	 * Co-multiplication of opinions about states in disjoint frames of
	 * discernment produces the product of the two opinions, i.e. the 
	 * opinion about the logical OR of those states. 
	 * I.c.o. dogmatic argument opinions (i.e. u=0), the co-product opinion
	 * is equivalent to the co-product of probabilities. 
	 * I.c.o. absolute argument opinions (i.e. u=0 and b=1 or d=1) the
	 * co-product opinion is equivalent to logical OR of binary propositions.
	 * The general case handles uncertainty and relative atomicity.
	 * NOTE: Zero divisor case not fully studied.
	 */
	public static Opinion CoMultiplication(Opinion x, Opinion y) {
		Opinion o = new Opinion();
		double divisor = 1;
		o.b = x.b + y.b - x.b*y.b;
		o.d = x.d*y.d;
		o.u = x.d*y.u + y.d*x.u + x.u*y.u;
		divisor = x.u + y.u - x.b*y.u - y.b*x.u - x.u*y.u;
		if (divisor != 0) {
			/*
			 * Normal case: No zero divisor for relative atomicity.
			 */
			o.a = (  x.u*x.a + y.u*y.a
					- y.a*x.b*y.u - x.a*y.b*x.u - x.a*y.a*x.u*y.u)/
					(x.u + y.u - x.b*y.u - y.b*x.u - x.u*y.u);
		} else if ((y.u == 0) && (x.u == 0)){
			/*
			 * Zero divisor. The expression below produces limit value
			 * of the relative atomicity o.a when (x.u, y.u) --> 0.
			 */
			o.a = (x.a*(1-y.b) + y.a*(1-x.b))/((1-y.b) + (1-x.b));
		} else {
			/*
			 * Undefined zero divisor case.
			 */
			o.a = 0.5;
		}
		OpinionConsistency.Check(o);
		return o;
	}

	/**
	 * Complement of an opinion produces an opinion about the 
	 * complementary state in the frame of discernment.
	 * I.c.o. dogmatic argument opinion (i.e. u=0), the complement opinion is 
	 * equivalent to the complement probability.
	 * I.c.o. absolute argument opinion (i.e. u=0 and b=1 or d=1) the
	 * complement opinion is equivalent to the negation of a binary proposition.
	 * The general case handles uncertainty and relative atomicity.
	 */
	public static Opinion Complement(Opinion x) {
		Opinion o = new Opinion();
		o.b = x.d;
		o.d = x.b;
		o.u = x.u;
		o.a = 1 - x.a;
		OpinionConsistency.Check(o);
		return o;
	}

	/**
	 * The addition of two opinions about states in the same frame of
	 * discernment produces the sum of the opinions, i.e. the opinion
	 * about the union of the two states. 
	 * I.c.o. dogmatic arguments opinions (i.e. u=0), the sum opinion
	 * is equivalent to the sum of probabilities. 
	 * I.c.o. absolute argument opinions (i.e. u=0 and b=1 or d=1) the sum
	 * opinion is equivalent to logical union of binary propositions.
	 * NOTE: Not defined to handle uncertainty in the general case.
	 */
	public static Opinion Addition(Opinion x, Opinion y) {
		Opinion o = new Opinion();
		o.b = x.b + y.b;
		o.d = (x.a*( x.d - y.b) + y.a*(y.d - x.b))/(x.a + y.a);
		o.u = (x.a*x.u + y.a*y.u)/(x.a + y.a);
		o.a = x.a + y.a;
		OpinionConsistency.Check(o);
		return o;
	}


	/**
	 * The conditional inference opinion about state y by x is the opinion
	 * about y as a function of the 3 argument opinions 1) the opinion
	 * about x 2) the  opinion about y given x is true, 3) the opinion
	 * about y given x is false.
	 * I.c.o. dogmatic argument opinions (i.e. u=0), the conditional opinion 
	 * is equivalent to conditional probability.
	 * I.c.o. absolute argument opinions (i.e. u=1 and b=1 or d=1) the
	 * conditional opinion is equivalent to logical Modus Ponens of
	 * binary propositions.  The general case handles uncertainty and
	 * relative atomicity.
	 */

	public static Opinion ConditionalInference(Opinion x, Opinion yTx, 
			Opinion yFx) throws Exception {
		Opinion o  = new Opinion();
		Opinion Eo = new Opinion();
		double  Expec = 0;

		if (yTx.a == yFx.a)
			o.a = yTx.a;
		else {
			throw new Exception("The atomicity of both sub-conditionals must be equal"); 	
		}

		/* First compute the b,d,u values for opinions with the required
		 * expectation value that map to a line on the y image domain.
		 * In case the image domain is a triangle, uncertainty must be 
		 * increased proportional to the uncertainty on the x-domain.
		 */ 
		Eo.b = (x.b + x.a*x.u)*yTx.b +(x.d + (1-x.a)*x.u)*yFx.b;
		Eo.d = (x.b + x.a*x.u)*yTx.d +(x.d + (1-x.a)*x.u)*yFx.d;
		Eo.u = (x.b + x.a*x.u)*yTx.u +(x.d + (1-x.a)*x.u)*yFx.u;
		Eo.a = o.a;
		Expec = Eo.b + Eo.a*Eo.u;

		if (((yTx.b >= yFx.b) & (yTx.d >= yFx.d)) | 
				((yTx.b <= yFx.b) & (yTx.d <= yFx.d))) {
			/* Case I.
			 * The image domain is a line.
			 */
			o.b = Eo.b;
			o.d = Eo.d;
			o.u = Eo.u;
		}

		else if (((yTx.b >= yFx.b) & (yTx.d <= yFx.d)) &
				(Expec <= yFx.b + o.a*(1 - yFx.b - yTx.d)) &
				(x.b + x.a*x.u <= x.a)) {
			/* Case II.A.1  Tested OK 
			 * The y image domain is a triangle. 
			 * Sub-conditionals in the order yFx - yTx.
			 * Uncertainty adjustment by projection towards the yFx.b line.
			 * Uncertainty adjustment measured relative to the x.b line
			 */
			if (x.b > 0) {
				// No zero divisor.
				o.b = Eo.b - (x.u*x.a*(Eo.b - yFx.b))/(x.b + x.a*x.u);
				o.d = Eo.d - (x.u*x.a*(1 - o.a)*(Eo.b - yFx.b))/((x.b + x.a*x.u)*o.a);
				o.u = Eo.u + (x.u*x.a*(Eo.b - yFx.b))/((x.b + x.a*x.u)*o.a);
			}
			else {
				// Eliminate zero divisor.
				o.b = Eo.b - (Eo.b - yFx.b);
				o.d = Eo.d - ((1 - o.a)*(Eo.b - yFx.b))/(o.a);
				o.u = Eo.u + (Eo.b - yFx.b)/(o.a);
			}
		}

		else if (((yTx.b >= yFx.b) & (yTx.d <= yFx.d)) &
				(Expec <= yFx.b + o.a*(1 - yFx.b - yTx.d)) &
				(x.b + x.a*x.u > x.a)) {
			/* Case II.A.2  Tested OK
			 * The y image domain is a triangle. 
			 * Sub-conditionals in the order from left to right: yFx - yTx.
			 * Uncertainty adjustment by projection towards the yFx.b line.
			 * Uncertainty adjustment measured relative to the x.d line
			 */
			if (x.d > 0) {
				// No zero divisor.
				o.b = Eo.b - (x.u*(1-x.a)*(Eo.b - yFx.b))/((x.d + (1-x.a)*x.u));
				o.d = Eo.d - (x.u*(1-x.a)*(1-o.a)*(Eo.b - yFx.b))/((x.d + (1-x.a)*x.u)*o.a);
				o.u = Eo.u + (x.u*(1-x.a)*(Eo.b - yFx.b))/((x.d + (1-x.a)*x.u)*o.a);
			}
			else {
				// Avoid zero divisor.
				o.b = Eo.b - (Eo.b - yFx.b);
				o.d = Eo.d - ((1-o.a)*(Eo.b - yFx.b))/(o.a);
				o.u = Eo.u + (Eo.b - yFx.b)/(o.a);
			}
		}

		else if (((yTx.b >= yFx.b) & (yTx.d <= yFx.d)) &
				(Expec > yFx.b + o.a*(1 - yFx.b - yTx.d)) &
				(x.b + x.a*x.u <= x.a)) {
			/* Case II.B.1  Tested OK
			 * The y image domain is a triangle. 
			 * Sub-conditionals in the order from left to right: yFx - yTx.
			 * Uncertainty adjustment by projection towards the yTx.d line.
			 * Uncertainty adjustment measured relative to the x.b line
			 */
			if (x.b > 0) {
				// No zero divisor.
				o.b = Eo.b - (x.u*x.a*o.a*(Eo.d - yTx.d))/((x.b + x.a*x.u)*(1 - o.a));
				o.d = Eo.d - (x.u*x.a*(1 - o.a)*(Eo.d - yTx.d))/((x.b + x.a*x.u)*(1 - o.a));
				o.u = Eo.u + (x.u*x.a*(Eo.d - yTx.d))/((x.b + x.a*x.u)*(1 - o.a));
			}
			else {
				// Avoid zero divisor.
				o.b = Eo.b - (o.a*(Eo.d - yTx.d))/(1 - o.a);
				o.d = Eo.d - ((1 - o.a)*(Eo.d - yTx.d))/(1 - o.a);
				o.u = Eo.u + (Eo.d - yTx.d)/(1 - o.a);
			}
		}

		else if (((yTx.b >= yFx.b) & (yTx.d <= yFx.d)) &
				(Expec > yFx.b + o.a*(1 - yFx.b - yTx.d)) &
				(x.b + x.a*x.u > x.a)) {
			/* Case II.B.2 Tested OK
			 * The y image domain is a triangle. 
			 * Sub-conditionals in the order from left to right: yFx - yTx.
			 * Uncertainty adjustment by projection towards the yTx.d line.
			 * Uncertainty adjustment measured relative to the x.d line
			 */
			if (x.d > 0) {
				// No zero divisor.
				o.b = Eo.b - (x.u*(1-x.a)*o.a*(Eo.d-yTx.d))/((x.d+(1-x.a)*x.u)*(1 - o.a));
				o.d = Eo.d - (x.u*(1-x.a)*(1-o.a)*(Eo.d-yTx.d))/((x.d+(1-x.a)*x.u)*(1-o.a));
				o.u = Eo.u + (x.u*(1-x.a)*(Eo.d-yTx.d))/((x.d+(1-x.a)*x.u)*(1-o.a));
			}
			else {
				// Avoid zero divisor.
				o.b = Eo.b - (o.a*(Eo.d-yTx.d))/(1 - o.a);
				o.d = Eo.d - ((1-o.a)*(Eo.d-yTx.d))/(1-o.a);
				o.u = Eo.u + (Eo.d-yTx.d)/(1-o.a);
			}
		}

		else if (((yTx.b <= yFx.b) & (yTx.d >= yFx.d)) &
				(Expec <= yTx.b + o.a*(1 - yTx.b - yFx.d)) &
				(x.b + x.a*x.u <= x.a)) {
			/* Case III.A.1  Tested OK
			 * The y image domain is a triangle. 
			 * Sub-conditionals in the order from left to right: yTx - yFx.
			 * Uncertainty adjustment by projection towards the yTx.b line.
			 * Uncertainty adjustment measured relative to the x.b line
			 */
			if (x.b > 0) {
				// No zero divisor.
				o.b = Eo.b - (x.u*x.a*(Eo.b - yTx.b))/(x.b + x.a*x.u);
				o.d = Eo.d - (x.u*x.a*(1 - o.a)*(Eo.b - yTx.b))/((x.b + x.a*x.u)*o.a);
				o.u = Eo.u + (x.u*x.a*(Eo.b - yTx.b))/((x.b + x.a*x.u)*o.a);
			}
			else {
				// Avoid zero divisor.
				o.b = Eo.b - (Eo.b - yTx.b);
				o.d = Eo.d - ((1 - o.a)*(Eo.b - yTx.b))/(o.a);
				o.u = Eo.u + (Eo.b - yTx.b)/(o.a);
			}
		}

		else if (((yTx.b <= yFx.b) & (yTx.d >= yFx.d)) &
				(Expec <= yTx.b + o.a*(1 - yTx.b - yFx.d)) &
				(x.b + x.a*x.u > x.a)) {
			/* Case III.A.2  Tested OK
			 * The y image domain is a triangle. 
			 * Sub-conditionals in the order from left to right: yTx - yFx.
			 * Uncertainty adjustment by projection towards the yTx.b line.
			 * Uncertainty adjustment measured relative to the x.d line
			 */
			if (x.d > 0) {
				// No zero divisor.
				o.b = Eo.b - (x.u*(1-x.a)*(Eo.b - yTx.b))/((x.d + (1-x.a)*x.u));
				o.d = Eo.d - (x.u*(1-x.a)*(1-o.a)*(Eo.b - yTx.b))/((x.d+(1-x.a)*x.u)*o.a);
				o.u = Eo.u + (x.u*(1-x.a)*(Eo.b - yTx.b))/((x.d + (1-x.a)*x.u)*o.a);
			}
			else {
				// Avoid zero divisor.
				o.b = Eo.b - (Eo.b - yTx.b);
				o.d = Eo.d - ((1-o.a)*(Eo.b - yTx.b))/(o.a);
				o.u = Eo.u + (Eo.b - yTx.b)/(o.a);
			}
		}

		else if (((yTx.b <= yFx.b) & (yTx.d >= yFx.d)) &
				(Expec > yTx.b + o.a*(1 - yTx.b - yFx.d)) &
				(x.b + x.a*x.u <= x.a)) {
			/* Case III.B.1  Tested OK
			 * The y image domain is a triangle. 
			 * Sub-conditionals in the order from left to right: yTx - yFx.
			 * Uncertainty adjustment by projection towards the yFx.d line.
			 * Uncertainty adjustment measured relative to the x.b line
			 */
			if (x.b > 0) {
				// No zero divisor.
				o.b = Eo.b - (x.u*x.a*o.a*(Eo.d - yFx.d))/((x.b + x.a*x.u)*(1 - o.a));
				o.d = Eo.d - (x.u*x.a*(1 - o.a)*(Eo.d - yFx.d))/((x.b + x.a*x.u)*(1 - o.a));
				o.u = Eo.u + (x.u*x.a*(Eo.d - yFx.d))/((x.b + x.a*x.u)*(1 - o.a));
			}
			else {
				// Avoid zero divisor.
				o.b = Eo.b - (o.a*(Eo.d - yFx.d))/(1 - o.a);
				o.d = Eo.d - ((1 - o.a)*(Eo.d - yFx.d))/(1 - o.a);
				o.u = Eo.u + (Eo.d - yFx.d)/(1 - o.a);
			}
		}

		else if (((yTx.b <= yFx.b) & (yTx.d >= yFx.d)) &
				(Expec > yTx.b + o.a*(1 - yTx.b - yFx.d)) &
				(x.b + x.a*x.u > x.a)) {
			/* Case III.B.2  Tested OK
			 * The y image domain is a triangle. 
			 * Sub-conditionals in the order from left to right: yTx - yFx.
			 * Uncertainty adjustment by projection towards the yFx.d line.
			 * Uncertainty adjustment measured relative to the x.d line
			 */
			if (x.d > 0) {
				// No zero divisor.
				o.b = Eo.b - (x.u*(1-x.a)*o.a*(Eo.d-yFx.d))/((x.d + (1-x.a)*x.u)*(1-o.a));
				o.d = Eo.d - (x.u*(1-x.a)*(1-o.a)*(Eo.d-yFx.d))/((x.d+(1-x.a)*x.u)*(1-o.a));
				o.u = Eo.u + (x.u*(1-x.a)*(Eo.d - yFx.d))/((x.d + (1-x.a)*x.u)*(1-o.a));
			}
			else {
				// Avoid zero divisor.
				o.b = Eo.b - (o.a*(Eo.d-yFx.d))/(1-o.a);
				o.d = Eo.d - ((1-o.a)*(Eo.d-yFx.d))/(1-o.a);
				o.u = Eo.u + (Eo.d - yFx.d)/(1-o.a);
			}
		}

		else {
			/* Catch phantom case. */
			System.err.println("Conditional inference phantom case.");
			throw new Exception("Undefined Conditional Inference");
		}
		OpinionConsistency.Check(o);
		return o;
	}


	/**
	 * The consensus opinion of two opinions is an opinion that
	 * reflects both argument opinions in a fair and equal way.
	 * I.c.o. dogmatic argument opinions (i.e. u=0), the consensus opinion
	 * is equivalent to the weighted average of probabilities as a function
	 * of the relative dogmatism.
	 * I.c.o. absolute argument opinions (i.e. u=0 and b=1 or d=1)
	 * the consensus opinion is equivalent to the weighted average of
	 * the truth value of two
	 * binary propositions as a function  of the relative dogmatism.
	 * NOTE: in case both argument opinions are vacuous (i.e. u=1) the 
	 * relative atomicities must be equal. 
	 */
	public static Opinion Consensus(Opinion A, Opinion B, Double rdd) {
		Opinion o = new Opinion();
		double k = 1;
		double l = 1;
		double rd = rdd.doubleValue();
		k = A.u + B.u - A.u*B.u;
		l = A.u + B.u - 2*A.u*B.u;
		if (k != 0) {
			/*
			 * Normal case: No zero divisor.
			 */
			if (l != 0) {
				/*
				 * Normal case: Argument opinions are not both vacuous.
				 */
				o.b = (A.b*B.u + B.b*A.u)/k;
				o.d = (A.d*B.u + B.d*A.u)/k;
				o.u = (A.u*B.u)/k;
				o.a = (B.a*A.u + A.a*B.u - (A.a + B.a)*A.u*B.u)/l;
			} else {
				/*
				 * Argument opinions are both vacuous.
				 */
				if (A.a == B.a) {
					/*
					 * OK: Argument relative atomicities are equal. Trivial case.
					 */
					o.b = 0;
					o.d = 0;
					o.u = 1;
					o.a = A.a;
				} else {
					/*
					 * NOK: Argument relative atomicities are NOT equal. Error case.
					 */
					o.b = 0;
					o.d = 0;
					o.u = 1;
					o.a = -1; /* Flags error to OpinionConsistency class. */
				}
			}
		} else {
			/*
			 * Zero divisor. The expressions below produces limit values
			 * when (A.u, B.u) --> 0.
			 */
			o.b = ((rd * A.b) + B.b)/(rd + 1);
			o.d = ((rd * A.d) + B.d)/(rd + 1);
			o.u = 0;
			o.a = ((rd * B.a) + A.a)/(rd + 1);
		}
		OpinionConsistency.Check(o);
		return o;
	}

	/**
	 * Discounting of opinions is to let A receive B's opinion about x
	 * and discount it by A's opinion about B, so that A can derive his
	 * own opinion about x. 
	 * There exists no corresponding operator in probability calculus or
	 * binary logic.
	 */
	public static Opinion Discount(Opinion AB, Opinion Bx) {
		Opinion o = new Opinion();
		o.b = AB.b*Bx.b;
		o.d = AB.b*Bx.d;
		o.u = AB.d + AB.u + AB.b*Bx.u;
		o.a = Bx.a;
		OpinionConsistency.Check(o);
		return o;
	}


	/***
	 * The probability expectation value of an opinion is the
	 * projection from the 3-dimensional opinion space onto
	 * the 1-dimensional probability space. Probability expectation
	 * is also called the pignistic probability value.
	 */
	public static double Expectation(Opinion x) {
		return x.b + x.a*x.u;
	}

}



