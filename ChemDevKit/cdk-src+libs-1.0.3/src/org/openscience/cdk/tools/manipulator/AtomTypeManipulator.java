/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-02-02 01:03:41 +0100 (Fri, 02 Feb 2007) $
 *  $Revision: 7856 $
 *
 *  Copyright (C) 2003-2007  The Chemistry Development Kit (CDK) project
 *
 *  Contact: cdk-devel@lists.sourceforge.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *  All we ask is that proper credit is given for our work, which includes
 *  - but is not limited to - adding the above copyright notice to the beginning
 *  of your source code files, and to any copyright notice that you may distribute
 *  with programs based on this work.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.tools.manipulator;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomType;


/**
 * Class with utilities for the <code>AtomType</code> class.
 * - changed 21/7/05 by cho: add properties for mmff94 atom type 
 *
 * @author     mfe4
 * @author     egonw
 * @cdk.module standard
 */
public class AtomTypeManipulator {
	
	/**
	 * Method that assign properties to an atom given a particular atomType.
	 *
	 * @param  atom  Atom to configure
	 * @param  atomType    AtomType
	 */
	public static void configure(IAtom atom, IAtomType atomType) {
		atom.setSymbol(atomType.getSymbol());
		atom.setAtomTypeName(atomType.getAtomTypeName());
        atom.setMaxBondOrder(atomType.getMaxBondOrder());
        atom.setBondOrderSum(atomType.getBondOrderSum());
        atom.setVanderwaalsRadius(atomType.getVanderwaalsRadius());
        atom.setCovalentRadius(atomType.getCovalentRadius());
        atom.setValency(atomType.getValency());
        atom.setFormalCharge(atomType.getFormalCharge());
        atom.setHybridization(atomType.getHybridization());
        atom.setFormalNeighbourCount(atomType.getFormalNeighbourCount());
        atom.setFlag(CDKConstants.IS_HYDROGENBOND_ACCEPTOR, atomType.getFlag(CDKConstants.IS_HYDROGENBOND_ACCEPTOR));
        atom.setFlag(CDKConstants.IS_HYDROGENBOND_DONOR, atomType.getFlag(CDKConstants.IS_HYDROGENBOND_DONOR));
        Object constant = atomType.getProperty(CDKConstants.CHEMICAL_GROUP_CONSTANT);
        if (constant != null) {
        	atom.setProperty(CDKConstants.CHEMICAL_GROUP_CONSTANT, constant);
        }
        atom.setFlag(CDKConstants.ISAROMATIC, atomType.getFlag(CDKConstants.ISAROMATIC));
            
        Object color = atomType.getProperty("org.openscience.cdk.renderer.color");
        if (color != null) {
            atom.setProperty("org.openscience.cdk.renderer.color", color);
        }
        if (atomType.getAtomicNumber() != 0) {
            atom.setAtomicNumber(atomType.getAtomicNumber());
        }
        if (atomType.getExactMass() > 0.0) {
            atom.setExactMass(atomType.getExactMass());
       }
        
        
	}
}

