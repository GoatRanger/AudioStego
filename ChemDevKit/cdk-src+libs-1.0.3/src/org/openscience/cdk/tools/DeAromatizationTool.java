/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-02-16 16:11:33 +0100 (Fri, 16 Feb 2007) $
 * $Revision: 7964 $
 * 
 * Copyright (C) 2005-2007  The Chemistry Development Kit (CDK) project
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA. 
 * 
 */
package org.openscience.cdk.tools;

import java.util.Iterator;
import java.util.Map;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IRing;

/**
 * Methods that takes a ring of which all bonds are aromatic, and assigns single
 * and double bonds. It does this in a non-general way by looking at the ring
 * size and take everything as a special case.
 *
 *
 * @author         seb
 * @cdk.created    13. April 2005
 * @cdk.module     standard
 * @cdk.keyword    aromatic ring, bond order adjustment
 */
public class DeAromatizationTool {

	/**
	 * Methods that takes a ring of which all bonds are aromatic, and assigns single
	 * and double bonds. It does this in a non-general way by looking at the ring
	 * size and take everything as a special case.
	 *
	 * @param ring Ring to dearomatize
	 * @return  False if it could not convert the aromatic ring bond into single and double bonds
	 */
	public static boolean deAromatize(IRing ring) {
		boolean allaromatic=true;
		for(int i=0;i<ring.getBondCount();i++){
			if(!ring.getBond(i).getFlag(CDKConstants.ISAROMATIC))
				allaromatic=false;
		}
		if(!allaromatic)
			return false;
		for(int i=0;i<ring.getBondCount();i++){
			if(ring.getBond(i).getOrder()==1.5)
				ring.getBond(i).setOrder(1);
		}
		boolean result = false;
		Map elementCounts = new MFAnalyser(ring).getFormulaHashtable();
		if (ring.getRingSize() == 6) {
			if (((Integer)elementCounts.get("C")).intValue() == 6) {
				result = DeAromatizationTool.deAromatizeBenzene(ring);
			} else if (((Integer)elementCounts.get("C")).intValue() == 5 &&
			           ((Integer)elementCounts.get("N")).intValue() == 1) {
				result = DeAromatizationTool.deAromatizePyridine(ring);
			}
		}
		if (ring.getRingSize() == 5) {
            if (((Integer)elementCounts.get("C")).intValue() == 4 &&
			           ((Integer)elementCounts.get("N")).intValue() == 1) {
				result= deAromatizePyrolle(ring);
			}
		}
		return result;
	}
	
	private static boolean deAromatizePyridine(IRing ring) {
		return deAromatizeBenzene(ring); // same task to do
	}
	
	private static boolean deAromatizePyrolle(IRing ring) {
		if (ring.getBondCount() != 5) return false;
		for (int i = 0; i<ring.getAtomCount(); i++) {
			IAtom atom=ring.getAtom(i);
			if(atom.getSymbol().equals("N")){
				int done=0;
				IBond bond=null;
				int count=0;
				while(done!=2){
					bond=getNextBond(atom,bond,ring);
					if(bond.getAtom(0)==atom)
						atom=bond.getAtom(1);
					else
						atom=bond.getAtom(0);
					count++;
					if(count%2==0){
						bond.setOrder(2);
						done++;
					}
				}
				break;
			}
		}
		return true;
	}
	
	private static IBond getNextBond(IAtom atom, IBond bond, IRing ring){
		java.util.List bonds=ring.getConnectedBondsList(atom);
		for(int i=0;i<bonds.size();i++)
			if((IBond)bonds.get(i)!=bond)
				return (IBond)bonds.get(i);
		return null;
	}

    private static boolean deAromatizeBenzene(IRing ring) {
        if (ring.getBondCount() != 6) return false;
        Iterator bonds = ring.bonds();
        int counter = 0;
        while (bonds.hasNext()) {
            IBond bond = (IBond) bonds.next();
            if (counter % 2 == 0) {
                bond.setOrder(CDKConstants.BONDORDER_SINGLE);
            } else {
                bond.setOrder(CDKConstants.BONDORDER_DOUBLE);
            }
            counter++;
        }
        return true;
    }

}
