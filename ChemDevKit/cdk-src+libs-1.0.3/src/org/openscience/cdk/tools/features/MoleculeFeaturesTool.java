/* $RCSfile: $
 * $Author: egonw $
 * $Date: 2006-05-09 21:32:32 +0200 (Tue, 09 May 2006) $  
 * $Revision: 6204 $
 *
 * Copyright (C) 2006-2007  The Chemistry Development Kit (CDK) project
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
package org.openscience.cdk.tools.features;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.tools.DataFeatures;

import java.util.Iterator;

/**
 * Utility that helps determine which data features are present.
 * 
 * @author egonw
 * 
 * @see    DataFeatures
 */
public class MoleculeFeaturesTool {

    public static boolean hasPartialCharges(IMolecule molecule) {
        java.util.Iterator atoms = molecule.atoms();
        while (atoms.hasNext()) {
            if (((IAtom)atoms.next()).getCharge() != 0.0000) return true;
        }
        return false;
    }

    public static boolean hasFormalCharges(IMolecule molecule) {
        java.util.Iterator atoms = molecule.atoms();
        while (atoms.hasNext()) {
            if (((IAtom)atoms.next()).getFormalCharge() != 0) return true;
        }
        return false;
    }

    public static boolean hasElementSymbols(IMolecule molecule) {
        java.util.Iterator atoms = molecule.atoms();
        while (atoms.hasNext()) {
            IAtom atom = (IAtom)atoms.next();
            if (atom.getSymbol() != null &&
                atom.getSymbol().length() > 0) return true;
        }
        return false;
    }

    /**
     * Checks wether all bonds have exactly two atoms.
     */
    public static boolean hasGraphRepresentation(IMolecule molecule) {

        Iterator bonds = molecule.bonds();
        while (bonds.hasNext()) {
            IBond bond = (IBond) bonds.next();       
            if (bond.getAtomCount() != 2) return false;
        }
        return true;
    }

}
