/* $RCSfile$
 * $Author: sushil_ronghe $
 * $Date: 2007-04-17 20:49:16 +0200 (Tue, 17 Apr 2007) $
 * $Revision: 8208 $
 * 
 * Copyright (C) 2004-2007  Egon Willighagen <egonw@users.sf.net>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.isomorphism.matchers;

import org.openscience.cdk.interfaces.IAtom;

/**
 * Defines the abililty to be matched against IAtom's. Most prominent application
 * is in isomorphism and substructure matching in the UniversalIsomorphismTester.
 * 
 * @cdk.module interfaces
 * @see        org.openscience.cdk.isomorphism.UniversalIsomorphismTester
 */
public interface IQueryAtom extends IAtom {
         public static final int Default=56562;
	/**
	 * Returns true of the given <code>atom</code> matches this IQueryAtom.
	 * 
	 * @param atom IAtom to match against
	 * @return     true, if this IQueryAtom matches the given IAtom
	 */
	public boolean matches(IAtom atom);
        public void setOperator(String ID);
        
    
}

