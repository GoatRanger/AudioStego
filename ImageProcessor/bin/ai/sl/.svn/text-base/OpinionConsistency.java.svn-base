////////////////////////////////////////////////////////////////////////////
//
// Copyright (C) DSTC Pty Ltd (ACN 052 372 577) 2001, 2002.
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
package ai.sl;

/**
 * This checks an opinion for consistency.
 * @author Audun Josang
 * @version 2.0 - 15/10/2002
 */
public class OpinionConsistency {
    public static boolean Check(Opinion x) {
	double toleranceValue   = 0.0000000001;
        /*                                   11111111112
         *                          12345678901234567890
         * The smallest practicle tolerance value for simple
         * computations is 10E-15. For more complex computations it
         * is believed that 10E-10 offers strong consistency check.
         */
	double deltaBelief      = Math.abs(x.b + x.d + x.u - 1);
	if (deltaBelief > toleranceValue) {
System.err.println("Opinion is : "+x.toString());
	    throw new IllegalArgumentException(
                "belief, disbelif and uncertainty do not add up to 1");
	}
	if (x.a == -1) {
System.err.println("Opinion is : "+x.toString());
	    throw new IllegalArgumentException(
                "Both argument opinions are vacuous, but the relative atomicities are different.");
	}
	return true;
    }
}



