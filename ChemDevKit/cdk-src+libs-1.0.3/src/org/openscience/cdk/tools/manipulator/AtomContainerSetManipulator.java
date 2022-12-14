/* $RCSfile$
 * $Author: egonw $ 
 * $Date: 2006-07-30 22:10:12 +0200 (Sun, 30 Jul 2006) $
 * $Revision: 6706 $
 * 
 * Copyright (C) 2003-2007  The Chemistry Development Kit (CDK) project
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
 *  */
package org.openscience.cdk.tools.manipulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openscience.cdk.graph.ConnectivityChecker;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomContainerSet;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IElectronContainer;
import org.openscience.cdk.interfaces.IMoleculeSet;

/**
 * @cdk.module standard
 *
 * @see ChemModelManipulator
 */
public class AtomContainerSetManipulator {
    
	public static int getAtomCount(IAtomContainerSet set) {
		int count = 0;
		java.util.Iterator acs = set.atomContainers();
        while (acs.hasNext()) {
        	count += ((IAtomContainer)acs.next()).getAtomCount();
        }
        return count;
	}
	
	public static int getBondCount(IAtomContainerSet set) {
		int count = 0;
		java.util.Iterator acs = set.atomContainers();
        while (acs.hasNext()) {
        	count += ((IAtomContainer)acs.next()).getBondCount();
        }
        return count;
	}
	
    public static void removeAtomAndConnectedElectronContainers(IAtomContainerSet set, IAtom atom) {
    	java.util.Iterator acs = set.atomContainers();
        while (acs.hasNext()) {
            IAtomContainer container = (IAtomContainer)acs.next();
            if (container.contains(atom)) {
                container.removeAtomAndConnectedElectronContainers(atom);
                IMoleculeSet molecules = ConnectivityChecker.partitionIntoMolecules(container);
                if(molecules.getAtomContainerCount()>1){
                	set.removeAtomContainer(container);
                	for(int k=0;k<molecules.getAtomContainerCount();k++){
                		set.addAtomContainer(molecules.getAtomContainer(k));
                	}
                }
                return;
            }
        }
    }
    
    public static void removeElectronContainer(IAtomContainerSet set, IElectronContainer electrons) {
    	java.util.Iterator acs = set.atomContainers();
        while (acs.hasNext()) {
            IAtomContainer container = (IAtomContainer)acs.next();
            if (container.contains(electrons)) {
                container.removeElectronContainer(electrons);
                IMoleculeSet molecules = ConnectivityChecker.partitionIntoMolecules(container);
                if(molecules.getAtomContainerCount()>1){
                	set.removeAtomContainer(container);
                	for(int k=0;k<molecules.getAtomContainerCount();k++){
                		set.addAtomContainer(molecules.getMolecule(k));
                	}
                }
                return;
            }
        }
    }
    
	/**
     * Returns all the AtomContainer's of a MoleculeSet.
     */
    public static List getAllAtomContainers(IAtomContainerSet set) {
    	List atomContainerList = new ArrayList();
    	Iterator acs = set.atomContainers();
    	while(acs.hasNext()){
    		atomContainerList.add((IAtomContainer)acs.next());
    	}
    	return atomContainerList;
    }
	
	/**
	 * @return The summed charges of all atoms in this set.
	 */
	public static double getTotalCharge(IAtomContainerSet set) {
		double charge = 0;
		for (int i = 0; i < set.getAtomContainerCount(); i++) {
			int thisCharge = AtomContainerManipulator.getTotalFormalCharge(set.getAtomContainer(i));
			double stoich = set.getMultiplier(i);
			charge += stoich * thisCharge;
		}
		return charge;
	}
	
	/**
	 * @return The summed formal charges of all atoms in this set.
	 */
	public static double getTotalFormalCharge(IAtomContainerSet set) {
		int charge = 0;
		for (int i = 0; i < set.getAtomContainerCount(); i++) {
			int thisCharge = AtomContainerManipulator.getTotalFormalCharge(set.getAtomContainer(i));
			double stoich = set.getMultiplier(i);
			charge += stoich * thisCharge;
		}
		return charge;
	}
	
	/**
	 * @return The summed implicit hydrogens of all atoms in this set.
	 */
	public static int getTotalHydrogenCount(IAtomContainerSet set) {
		int hCount = 0;
		for (int i = 0; i < set.getAtomContainerCount(); i++) {
			hCount += AtomContainerManipulator.getTotalHydrogenCount(set.getAtomContainer(i));
		}
		return hCount;
	}
	
    public static List getAllIDs(IAtomContainerSet set) {
        List idList = new ArrayList();
        if (set != null) {
            if (set.getID() != null) idList.add(set.getID());
            for (int i = 0; i < set.getAtomContainerCount(); i++) {
                idList.addAll(AtomContainerManipulator.getAllIDs(set.getAtomContainer(i)));
            }
        }
        return idList;
    }
    
    public static void setAtomProperties(IAtomContainerSet set, Object propKey, Object propVal) {
        if (set != null) {
            for (int i = 0; i < set.getAtomContainerCount(); i++) {
                AtomContainerManipulator.setAtomProperties(set.getAtomContainer(i), propKey, propVal);
            }
        }
    }

    public static IAtomContainer getRelevantAtomContainer(IAtomContainerSet containerSet, IAtom atom) {
    	java.util.Iterator acs = containerSet.atomContainers();
        while (acs.hasNext()) {
        	IAtomContainer ac = (IAtomContainer)acs.next();
            if (ac.contains(atom)) {
                return ac;
            }
        }
        return null;
    }

    public static IAtomContainer getRelevantAtomContainer(IAtomContainerSet containerSet, IBond bond) {
    	java.util.Iterator acs = containerSet.atomContainers();
        while (acs.hasNext()) {
        	IAtomContainer ac = (IAtomContainer)acs.next();
            if (ac.contains(bond)) {
                return ac;
            }
        }
        return null;
    }
    
    /**
     * Does not recursively return the contents of the AtomContainer.
     * 
     * @param set
     * @return
     */
    public static List getAllChemObjects(IAtomContainerSet set) {
        ArrayList list = new ArrayList();
        list.add(set);
        java.util.Iterator acs = set.atomContainers();
        while (acs.hasNext()) {
            list.add((IAtomContainer)acs.next());
        }
        return list;
    }
        
    /**
     * <p>Sorts the IAtomContainers in the given IAtomContainerSet by the following
     * criteria with decreasing priority:</p>
     * <ul>
     *   <li>Compare atom count
     *   <li>Compare molecular weight (heavy atoms only)
     *   <li>Compare bond count
     *   <li>Compare sum of bond orders (heavy atoms only)
     * </ul>
     * <p>If no difference can be found with the above criteria, the IAtomContainers are
     * considered equal.</p>
     */
    public static void sort(IAtomContainerSet atomContainerSet) {
      List atomContainerList = AtomContainerSetManipulator.getAllAtomContainers(atomContainerSet);
      Collections.sort(atomContainerList, new AtomContainerComparator());
      atomContainerSet.removeAllAtomContainers();
      Iterator iterator = atomContainerList.iterator();
      while (iterator.hasNext())
        atomContainerSet.addAtomContainer((IAtomContainer) iterator.next());
    }
    
}

