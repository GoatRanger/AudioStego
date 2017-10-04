/* $RCSfile$
 * $Author: djiao $
 * $Date: 2007-05-11 16:02:02 +0200 (Fri, 11 May 2007) $
 * $Revision: 8317 $
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
 * This matcher checks the number of connections of the checked Atom
 * with other Atom's. This cannot be matched with a unpreprocessed Atom!
 *
 * @cdk.module extra
 */
public class DegreeAtom extends SMARTSAtom {
    
    private static final long serialVersionUID = 2623272739045200480L;
    
    private int degree;
    
    public DegreeAtom(int degree) {
        this.degree = degree;
    }
    public DegreeAtom(){
        this.degree = Default;
    }
    
    public int getOperator(){
        if(ID!=null && this.degree==Default)
            return 1;
        else if(ID!=null && this.degree!=Default)
            return 2;
        else if(this.degree==Default)
            return 3;
        else if(this.degree!=Default)
            return 4;
        return 5;
    }
    private int getDD(IAtom atom){
    	return atom.getFormalCharge();
    }
    
 public boolean matches(IAtom atom) {
        switch(getOperator()){
            case 1:return defaultOperatorCheck(atom);
            case 2:return nonDefaultOperatorCheck(atom);
            case 3:return defaultCheck(atom);
            case 4:return nonDefaultCheck(atom);
            default:return false;
        }
    };
    
    private boolean defaultCheck(IAtom atom){
        if(getDD(atom)!=0)return true;
        return false;
    }
    private boolean nonDefaultCheck(IAtom atom){
        if(getDD(atom)==this.degree) return true;
        return false;
    }
    private boolean defaultOperatorCheck(IAtom atom){
        if(getDD(atom)==0)return true;
        return false;
    }
    private boolean nonDefaultOperatorCheck(IAtom atom){
        if(getDD(atom)!=0 && getDD(atom)!=this.degree) return false;
        return false;
    }
    public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("DegreeAtom(");
        s.append(this.hashCode() + ", ");
		s.append("D:" + degree);
		s.append(")");
		return s.toString();
    }
}

