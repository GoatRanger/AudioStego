/* $Revision: 8245 $ $Author: djiao $ $Date: 2007-04-22 17:20:57 -0400 (Sun, 22 Apr 2007) $
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
package org.openscience.cdk.isomorphism.matchers.smarts;

import java.util.List;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;

/**
 * This matches Hydrogen atoms
 *
 * @cdk.module extra
 */
public class HydrogenAtom extends SMARTSAtom {
	private IAtomContainer atomContainer;

	public HydrogenAtom() {
	}

	public boolean matches(IAtom atom) {
		if (!atom.getSymbol().equals("H")) {
			return false;
		}
		
		if (atom.getFormalCharge() == 1) { // proton matches
			return true;
		}
		
		// hydrogens connected to other hydrogens, e.g., molecular hydrogen
		List list = atomContainer.getConnectedAtomsList(atom);
                for (int i=0; i<list.size(); i++) {
		        IAtom connAtom = (IAtom)list.get(i);
			if (connAtom.getSymbol().equals("H")) {
				return true;
			}
		}
		
		// hydrogens connected to other than one other atom, e.g., bridging hydrogens
		if (atom.getFormalNeighbourCount() > 1) {
			return true;
		}
		
		//isotopic hydrogen specifications, e.g. deuterium [2H] 
		if (atom.getMassNumber() > 1) { 
			return true;
		}
		
		return false;
	}

	public IAtomContainer getAtomContainer() {
		return atomContainer;
	}

	public void setAtomContainer(IAtomContainer atomContainer) {
		this.atomContainer = atomContainer;
	}	
}
