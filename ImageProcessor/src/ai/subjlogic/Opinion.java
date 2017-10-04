/*
 * Created on Sep 24, 2004
 *
 * This class implements the Opinion based subjective logic.
 */
/** 
 * This logic was first described by Audun Josang.<br>
 * A. J�sang. <i>A Logic for Uncertain Probabilities.</i> 
 * International Journal of Uncertainty, Fuzziness and Knowledge-Based Systems. 9(3), pp.279-311, June 2001.
 * 
 * A brief description of this technique, extracted from the above resource:
 * <blockquote>In this paper we describe subjective logic (see J�sang 1997 for an earlier version) as
 * a logic which operates on subjective beliefs about the world, and use the term opinion
 * to denote the representation of a subjective belief. Subjective logic operates on opinions
 * and contains standard logical operators in addition to some non-standard operators which
 * specifically depend on belief ownership. An opinion can be interpreted as a probability
 * measure containing secondary uncertainty, and as such subjective logic can be seen as an
 * extension of both probability calculus and binary logic.</blockquote>
 * 
 * Functions compared with, and special cases added, based on Subjective Logic-API from 
 * <a href="http://security.dstc.edu.au/spectrum/sl-api/">DSTC - http://security.dstc.edu.au/spectrum/sl-api/</a>.
 *
 */
package ai.subjlogic;

/** Subjective Logic manager */
public class Opinion implements Comparable<Object> {
	double belief;
	double disbelief;
	double uncertainty;
	double atomicity;

	/*
	 * Create an Opinion. An opinion created through the empty constructor will
	 * have complete uncertainty.
	 */
	public Opinion() {
		belief = 0.0;
		disbelief = 0.0;
		uncertainty = 1.0;
		atomicity = 0.5;
	}

	public Opinion(double belief, double disbelief, double ignorance) throws IllegalArgumentException {
		this(belief, disbelief, ignorance, 0.0);
	}

	public Opinion(double belief, double disbelief, double ignorance, double atomicity)
			throws IllegalArgumentException {
		if (Math.round(belief + disbelief + ignorance) != 1) {
			System.out.println("Inconsistent Opinion!");
			throw new IllegalArgumentException("Inconsistent Opinion");
		}
		this.belief = belief;
		this.disbelief = disbelief;
		this.uncertainty = ignorance;
		this.atomicity = atomicity;
	}

	/*
	 * Duplicates an Opinion.
	 * 
	 * @param b The Opinion to use as a source for this Opinion
	 */
	public Opinion(Opinion b) {
		this.belief = b.belief;
		this.disbelief = b.disbelief;
		this.uncertainty = b.uncertainty;
		this.atomicity = b.atomicity;
	}

	public double getBelief() {
		return belief;
	}

	public double getUncertainty() {
		return uncertainty;
	}
	
	public double getAtomicity() {
		return atomicity;
	}

	public double getDisbelief() {
		return 1 - this.belief - this.uncertainty;
	}
	
	public double getExpectation() {
		return this.belief + this.uncertainty * this.atomicity;
	}

	/*
	 * Changes this Opinion by the amount of belief, disbelief, and ignorance
	 * specified.
	 * 
	 * @param belief The amount to adjust belief
	 * @param disbelief The amount to adjust disbelief
	 * @param ignorance The amount to adjust ignorance
	 */
	public void change(double belief, double disbelief, double ignorance) {
		this.belief += belief;
		this.disbelief += disbelief;
		this.uncertainty += ignorance;
	}

	/**
	 * Returns a new opinion from the same analyst/perspective about x and y.
	 * Given this opinion about x, and  the provided opinion about y, form an opinion
	 * about both x and y.
	 *
	 * @param b
	 *            The second proposition
	 *
	 * @return A new opinion on the truth of this proposition and proposition b
	 */
	public Opinion conjunction(Opinion b) {
		Opinion conjunct = new Opinion();
		
		conjunct.disbelief = this.disbelief + b.disbelief - (this.disbelief * b.disbelief);
		conjunct.atomicity = this.atomicity * b.atomicity;
		
		if (this.atomicity == 1.0 && b.atomicity == 1.0) {
			// Base rate / atomicity of both opinions is 1 - not opinions about independent propositions
			throw new IllegalArgumentException("Cannot form conjunction where opinions are not independent propositions. " + b.toString());
		} else {
			conjunct.belief = this.belief * b.belief 
					+ ((1-this.atomicity) * b.atomicity * this.belief * b.uncertainty 
							+ (1-b.atomicity) * this.atomicity * b.belief * this.uncertainty)
					/(1 - this.atomicity * b.atomicity);
		    conjunct.uncertainty = this.uncertainty * b.uncertainty 
		    		+ ((1-b.atomicity) * this.belief * b.uncertainty 
		    				+ (1-this.atomicity) * b.belief * this.uncertainty)/(1 - this.atomicity * b.atomicity);
		}
		if (isConsistent(conjunct))
			return conjunct;
		else
			return new Opinion();
		}
	
	/**
	 * Returns a new opinion from the same analyst/perspective about x and y.
	 * Given this opinion about x, and  the provided opinion about y, form an opinion
	 * about both x and y.
	 *
	 * @param b
	 *            The second proposition
	 *
	 * @return A new opinion on the truth of this proposition and proposition b
	 */
	public Opinion conjunction_old(Opinion b) {
		Opinion conjunct = new Opinion();
		
		conjunct.belief = this.belief * b.belief;
		conjunct.disbelief = this.disbelief + b.disbelief - (this.disbelief * b.disbelief);
		conjunct.uncertainty = this.belief * b.uncertainty + this.uncertainty * b.belief
				+ this.uncertainty * b.uncertainty;
		
		if (conjunct.uncertainty != 0.0) {
			// since uncertainty is the divisor of atomicity calc
			conjunct.atomicity = (this.belief * b.uncertainty * b.atomicity
					+ this.uncertainty * this.atomicity * b.belief
					+ this.uncertainty * this.atomicity * b.uncertainty * b.atomicity) / (conjunct.uncertainty);
		} else if (this.uncertainty == 0.0 && b.uncertainty == 0.0) {
			// if both opinions have no uncertainty, then atomicity is a function of belief
			conjunct.atomicity = (b.atomicity * this.belief + this.atomicity * b.belief) / (this.belief + b.belief);
		
		} else if (b.disbelief == 1.0) {
			// since the conjunction uncertainty is 0, atomicity of the conjunction is that of
			// any opinion that has disbelief of 1.
			conjunct.atomicity = b.atomicity;
		} else if (this.disbelief == 1.0) {
			conjunct.atomicity = this.atomicity;
		} else {
			conjunct.atomicity = 0.5;
		}
		
		if (isConsistent(conjunct))
			return conjunct;
		else
			return new Opinion();
	}

	/**
	 * Returns a new opinion about whether either of the two opinions are true.
	 *
	 * @param b
	 *            The second proposition
	 *
	 * @return A new opinion on the truth of this proposition or proposition b
	 */
	public Opinion disjunction(Opinion b) {
		Opinion disjunct = new Opinion();
		disjunct.belief = this.belief + b.belief - (this.belief * this.belief);
		disjunct.disbelief = this.disbelief * b.disbelief;
		disjunct.uncertainty = this.disbelief * b.uncertainty + this.uncertainty * b.disbelief
				+ this.uncertainty * b.uncertainty;
		return disjunct;
	}

	/**
	 * Returns the negation of an opinion. Represents the opinion about this
	 * opinion being false.
	 *
	 * @return The complement (negation) of this opinion
	 */
	public Opinion complement() {
		Opinion negate = new Opinion();
		negate.belief = this.disbelief;
		negate.disbelief = this.belief;
		negate.uncertainty = this.uncertainty;
		negate.atomicity = 1 - this.atomicity;

		isConsistent(negate);
		return negate;
	}

	/*
	 * Returns the consensus between this opinion and second opinion. This
	 * method assumes relative dogmatism of 1.
	 * 
	 * @param b The second opinion
	 */
	public Opinion consensus(Opinion b) {
		return consensus(b, 1.0);
	}

	/**
	 * Returns the consensus between two opinions.  
	 * Assume two observers A and B who have independent opinions over the frame X. This can
	 * for example result from having observed the outcomes of a process over two separate 
	 * time periods. (Josang http://folk.uio.no/josang/papers/Jos2009-FUSION.pdf) 
	 * In the case of two dogmatic argument opinions (i.e. uncertainty of 0),
	 * the consensus opinion is equivalent to the weighted average of the 
	 * belief and disbelief based on the relative dogmatism (with 0 uncertainty).
	 *
	 * @param b
	 *            The second opinion
	 * @param rd
	 *            Relative dogmatism
	 *
	 * @return The consensus opinion
	 */
	public Opinion consensus(Opinion b, double rd) {
		Opinion consent = new Opinion();

		double kappa = this.uncertainty + b.uncertainty - (this.uncertainty * b.uncertainty);
		if (kappa == 0) {
			// The case of two certain opinions (kappa is zero when no
			// uncertainty in either opinion)
			consent.belief = (rd * this.belief + b.belief) / (rd + 1);
			consent.disbelief = (rd * this.disbelief + b.disbelief) / (rd + 1);
			consent.uncertainty = 0;
			consent.atomicity = (rd * b.atomicity + this.atomicity) / (rd + 1);
		} else {
			if (kappa - this.uncertainty * b.uncertainty != 0) {
				// then there is at least some uncertainty in one of the
				// opinions
				consent.belief = (this.belief * b.uncertainty + b.belief * this.uncertainty) / kappa;
				consent.disbelief = (this.disbelief * b.uncertainty + b.disbelief * this.uncertainty) / kappa;
				consent.uncertainty = (this.uncertainty * b.uncertainty) / kappa;
				consent.atomicity = (b.atomicity * this.uncertainty + this.atomicity * b.uncertainty
						- (this.atomicity + b.atomicity) * this.uncertainty * b.uncertainty)
						/ (kappa - this.uncertainty * b.uncertainty);
			} else {
				// Both opinions are 100% uncertain
				if (this.atomicity == b.atomicity) {
					// If atomicity is equal, then trivial to say consensus is
					// also uncertain
					consent.belief = 0;
					consent.disbelief = 0;
					consent.uncertainty = 1;
					consent.atomicity = this.atomicity;
				} else {
					// Something is wrong - shouldn't have two opinions at 100%
					// uncertain, but different atomicity
					// TODO: Handle this as an error
					consent.belief = 0;
					consent.disbelief = 0;
					consent.uncertainty = 1;
					consent.atomicity = 0.5;
				}
			}
		}
		if (isConsistent(consent))
			return consent;
		else
			//Based on new isConsistent function, will throw exception
			return new Opinion();
	}
	
	/**
	 * Returns a new opinion based on a recommendation about this opinion.
	 * Assumes this is an opinion about some proposition, and the passed opinion
	 * is an opinion about the performance of this recommender.
	 * Also identified as discounting. This opinion is A's opinion about X, and the passed
	 * opinion is B's opinion about A, and the returned opinion is A's opinion about X discounted
	 * based on B's opinion about A.
	 *
	 * @param b  An opinion about this agent's recommendations
	 *
	 * @return The recommendation
	 */
	public Opinion recommendation(Opinion b) {
		Opinion recommend = new Opinion();

		recommend.belief = this.belief * b.belief;
		recommend.disbelief = b.belief * this.disbelief;
		recommend.uncertainty = b.disbelief + b.uncertainty + b.belief * this.uncertainty;

		if (isConsistent(recommend)) {
			return recommend;
		} else
			return new Opinion();
	}
	
	public Opinion adjustExpectation(double expectation) {
		Opinion e=new Opinion(0.0, 0.0, 1.0, expectation);
		
		return conjunction(e);
		
	}
	
	/**
	 * Adjusts the expectation of this opinion to the target expectation provided.
	 * If provided expectation is outside the range of allowable values (0-1), returns the
	 * original unchanged opinion.
	 * 
	 * @param expectation
	 * @return The Opinion with the target expectation.
	 */
	public Opinion adjustExpectation2(double expectation) {
		Opinion adjusted = new Opinion();
		// TODO: Fix this. Calculations are definitely wrong for case where atomicity > expectation
		if (expectation >= 0.0 && expectation <= 1.0) {
			if (this.getExpectation() == 0) {
				// Original expectation is 0, implying complete disbelief
				adjusted.belief = expectation;
				adjusted.uncertainty = 0; // was originally a dogmatic opinion
				adjusted.disbelief = 1 - adjusted.belief - adjusted.uncertainty;
				adjusted.atomicity = this.atomicity;
			} else {
				// Shifts the Projector based on the ratio of expectations
				adjusted.belief = expectation / this.getExpectation() * this.belief;
				adjusted.uncertainty = expectation / this.getExpectation() * this.uncertainty;
				adjusted.disbelief = 1 - adjusted.belief - adjusted.uncertainty;
				adjusted.atomicity = this.atomicity;
			}
		} else {
			// Invalid expectation. should probably throw an exception, but for now, just return original
			adjusted.belief = this.belief;
			adjusted.disbelief = this.disbelief;
			adjusted.uncertainty = this.uncertainty;
			adjusted.atomicity = this.atomicity;
		}
		
		return adjusted;
	}

	/**
	 * Determine if this opinion is equivalent to the provided opinion.
	 * Two opinions are considered equal if they are within 1/100 for each attribute
	 * of belief, disbelief, and uncertainty.
	 * TODO: Should this also include atomicity?
	 * 
	 * @param o  An Object, as another Opinion (will return false if not an Opinion)
	 * @return true if the opinions are equal
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Opinion))
			return false;
		Opinion b = (Opinion) o;
		double v1 = roundTo(belief - b.belief, 2);
		double v2 = roundTo(disbelief - b.disbelief, 2);
		double v3 = roundTo(uncertainty - b.uncertainty, 2);
		return (v1 == 0 && v2 == 0 && v3 == 0);
	}

	/** Returns the maximum of this opinion and the supplied opinion */
	public Opinion max(Opinion b) {
		double first = (this.belief + this.uncertainty) / (this.belief + this.disbelief + 2 * this.uncertainty);
		double second = (b.belief + b.uncertainty) / (b.belief + b.disbelief + 2 * b.uncertainty);
		if (first == second) {
			if (this.uncertainty < b.uncertainty)
				return new Opinion(this);
			else
				return new Opinion(b);
		}
		if (second < first)
			return new Opinion(this);
		return new Opinion(b);
	}

	/**
	 * Compares two opinions
	 *
	 * @param o   Compare this opinion to opinion o
	 * @return -1 if this opinion is less, 1 if greater, 0 if equal
	 */
	public int compareTo(Object o) {
		Opinion b = (Opinion) o;

		Double first = new Double(
				(this.belief + this.uncertainty) / (this.belief + this.disbelief + 2 * this.uncertainty));
		Double second = new Double((b.belief + b.uncertainty) / (b.belief + b.disbelief + 2 * b.uncertainty));
		Double fi = new Double(this.uncertainty);
		Double si = new Double(b.uncertainty);
		if (first.equals(second))
			return (fi.compareTo(si));
		return second.compareTo(first);
	}

	/**
	 * Utility function to round a number.
	 * 
	 * @param val       The number, with arbitrary precision
	 * @param digits    The number of digits of desired precision
	 * @return          A value rounded to the specified precision
	 */
	private double roundTo(double val, int digits) {
		double temp;
		temp = (double) (((int) (val * Math.pow(10, digits))) / (double) Math.pow(10, digits));
		return temp;
	}
	
	/**
	 * Determines if an opinion is consistent, that is, if belief+disbelief+uncertainty add up to 1.
	 * 
	 * @param o The opinion to check for consistency
	 * @return true if consistent
	 * @throws IllegalArgmentException if the opinion is inconsistent (since no way to know why)
	 */
	private boolean isConsistent(Opinion o) {
		// Some issues with precision, so rather than comparing to 1, compare to some tolerable error
		double errorAcceptable = 0.000001;
		
		double error = Math.abs(1 - (o.belief + o.disbelief + o.uncertainty));
		if (error > errorAcceptable) {
			// Could do this as a returned boolean, but it's really an error - hard to handle in code
			throw new IllegalArgumentException("Inconsistency in opinion. " + o.toString());
		}

		return true;
	}

	public String toString() {
		return ("Belief: " + roundTo(belief, 2) + ", Disbelief: " + roundTo(disbelief, 2) + ", Uncertainty: "
				+ roundTo(uncertainty, 2) + ", Atomicity: " + roundTo(atomicity, 2));
	}

}