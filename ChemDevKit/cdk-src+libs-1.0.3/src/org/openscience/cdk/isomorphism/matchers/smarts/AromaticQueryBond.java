/* $RCSfile$
 * $Author: sushil_ronghe $
 * $Date: 2007-04-17 20:49:16 +0200 (Tue, 17 Apr 2007) $
 * $Revision: 8208 $
 * 
 * Copyright (C) 2004-2007  The Chemistry Development Kit (CDK) project
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
package org.openscience.cdk.isomorphism.matchers.smarts;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.isomorphism.matchers.IQueryAtom;

/**
 * @cdk.module extra
 */
public class AromaticQueryBond extends SMARTSBond {

    private static final long serialVersionUID = 6941220923564432716L;
    private IAtom atom1;
    private IAtom atom2;
    public AromaticQueryBond() {
    }

    public AromaticQueryBond(IQueryAtom m_atom1, IQueryAtom m_atom2, double order) {
        super(m_atom1, m_atom2, order);
       
    }
    
	public boolean matches(IBond bond) {      
         if ( bond.getFlag(CDKConstants.ISAROMATIC)) {
            return true;
        }
        
        return false;
    };

    public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("AromaticQueryBond(");
        s.append(this.hashCode() + ", ");
		s.append("#A:" + atoms.length);
		for (int i = 0; i < atoms.length; i++) {
			if (atoms[i] == null) {
				s.append(", null");
			} else {
				s.append(", " + atoms[i].toString());
			}
		}
		s.append(")");
		return s.toString();
    }
}

