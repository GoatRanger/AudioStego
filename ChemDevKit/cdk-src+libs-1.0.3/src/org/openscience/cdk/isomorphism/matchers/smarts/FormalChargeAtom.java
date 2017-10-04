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

/**
 * This matcher checks the formal charge of the Atom.
 *
 * @cdk.module extra
 */
public class FormalChargeAtom extends SMARTSAtom {
    
    private static final long serialVersionUID = 1203658471548492071L;
    
    private int charge;
    
    public FormalChargeAtom(int charge) {
        this.charge = charge;
    }
    
    public int getOperator(){
        if(ID!=null )
            return 1;
        else if(this.charge!=Default)
            return 2;
        return 3;
    }
    public boolean matches(IAtom atom) {
     switch(getOperator()){
                case 1: { if(atom.getFormalCharge()!=this.charge) return true;}
                case 2: { if(atom.getFormalCharge()==this.charge) return true;}
                default: return false;
            }   
    };
    

    public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("FormalChargeAtom(");
        s.append(this.hashCode() + ", ");
		s.append("FC:" + charge);
		s.append(")");
		return s.toString();
    }
}

