/* $RCSfile$
 * $Author: egonw $ 
 * $Date: 2007-05-01 22:13:10 +0200 (Tue, 01 May 2007) $
 * $Revision: 8295 $
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
import java.util.List;
import java.util.Vector;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.interfaces.IElectronContainer;
import org.openscience.cdk.interfaces.IMapping;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.interfaces.IMoleculeSet;
import org.openscience.cdk.interfaces.IReaction;

/**
 * @cdk.module standard
 *
 * @see ChemModelManipulator
 */
public class ReactionManipulator {
    
    public static int getAtomCount(IReaction reaction) {
    	int count = 0;
        IMoleculeSet reactants = reaction.getReactants();
        for (int i=0; i<reactants.getAtomContainerCount(); i++) {
        	count += reactants.getMolecule(i).getAtomCount();
        }
        IMoleculeSet products = reaction.getProducts();
        for (int i=0; i<products.getAtomContainerCount(); i++) {
        	count += products.getMolecule(i).getAtomCount();
        }
        return count;
    }
    
    public static int getBondCount(IReaction reaction) {
    	int count = 0;
    	IMoleculeSet reactants = reaction.getReactants();
        for (int i=0; i<reactants.getAtomContainerCount(); i++) {
        	count += reactants.getMolecule(i).getBondCount();
        }
        IMoleculeSet products = reaction.getProducts();
        for (int i=0; i<products.getAtomContainerCount(); i++) {
        	count += products.getMolecule(i).getBondCount();
        }
        return count;
    }
    
    public static void removeAtomAndConnectedElectronContainers(IReaction reaction, IAtom atom) {
    	IMoleculeSet reactants = reaction.getReactants();
        for (int i=0; i<reactants.getAtomContainerCount(); i++) {
            IMolecule mol = reactants.getMolecule(i);
            if (mol.contains(atom)) {
                mol.removeAtomAndConnectedElectronContainers(atom);
            }
        }
        IMoleculeSet products = reaction.getProducts();
        for (int i=0; i<products.getAtomContainerCount(); i++) {
            IMolecule mol = products.getMolecule(i);
            if (mol.contains(atom)) {
                mol.removeAtomAndConnectedElectronContainers(atom);
            }
        }
    }
    
    public static void removeElectronContainer(IReaction reaction, IElectronContainer electrons) {
    	IMoleculeSet reactants = reaction.getReactants();
        for (int i=0; i<reactants.getAtomContainerCount(); i++) {
            IMolecule mol = reactants.getMolecule(i);
            if (mol.contains(electrons)) {
                mol.removeElectronContainer(electrons);
            }
        }
        IMoleculeSet products = reaction.getProducts();
        for (int i=0; i<products.getAtomContainerCount(); i++) {
            IMolecule mol = products.getMolecule(i);
            if (mol.contains(electrons)) {
                mol.removeElectronContainer(electrons);
            }
        }
    }
    
    /**
     * get all molecule of a IReaction. Reactants + Products
     * 
     * @param reaction  The IReaction
     * @return The IMoleculeSet
     */
    public static IMoleculeSet getAllMolecules(IReaction reaction) {
        IMoleculeSet moleculeSet = reaction.getBuilder().newMoleculeSet();

        moleculeSet.add(getAllReactants(reaction));
        moleculeSet.add(getAllProducts(reaction));
        
        return moleculeSet;
    }
    /**
     * get all products of a IReaction
     * 
     * @param reaction  The IReaction
     * @return The IMoleculeSet
     */
    public static IMoleculeSet getAllProducts(IReaction reaction) {
        IMoleculeSet moleculeSet = reaction.getBuilder().newMoleculeSet();
        IMoleculeSet products = reaction.getProducts();
        for (int i=0; i<products.getAtomContainerCount(); i++) {
            moleculeSet.addMolecule(products.getMolecule(i));
        }
        return moleculeSet;
    }

    /**
     * get all reactants of a IReaction
     * 
     * @param reaction  The IReaction
     * @return The IMoleculeSet
     */
    public static IMoleculeSet getAllReactants(IReaction reaction) {
        IMoleculeSet moleculeSet = reaction.getBuilder().newMoleculeSet();
        IMoleculeSet reactants = reaction.getReactants();
        for (int i=0; i<reactants.getAtomContainerCount(); i++) {
            moleculeSet.addMolecule(reactants.getMolecule(i));
        }
        return moleculeSet;
    }
    
    /**
     * Returns a new Reaction object which is the reverse of the given
     * Reaction.
     */
    public static IReaction reverse(IReaction reaction) {
        IReaction reversedReaction = reaction.getBuilder().newReaction();
        if (reaction.getDirection() == IReaction.BIDIRECTIONAL) {
            reversedReaction.setDirection(IReaction.BIDIRECTIONAL);
        } else if (reaction.getDirection() == IReaction.FORWARD) {
            reversedReaction.setDirection(IReaction.BACKWARD);
        } else if (reaction.getDirection() == IReaction.BACKWARD) {
            reversedReaction.setDirection(IReaction.FORWARD);
        }
        IMoleculeSet reactants = reaction.getReactants();
        for (int i=0; i<reactants.getAtomContainerCount(); i++) {
            double coefficient = reaction.getReactantCoefficient(reactants.getMolecule(i));
            reversedReaction.addProduct(reactants.getMolecule(i), coefficient);
        }
        IMoleculeSet products = reaction.getProducts();
        for (int i=0; i<products.getAtomContainerCount(); i++) {
            double coefficient = reaction.getProductCoefficient(products.getMolecule(i));
            reversedReaction.addReactant(products.getMolecule(i), coefficient);
        }
        return reversedReaction;
    }
    
    /**
     * Returns all the AtomContainer's of a Reaction.
     */
    public static List getAllAtomContainers(IReaction reaction) {
		return MoleculeSetManipulator.getAllAtomContainers(
            getAllMolecules(reaction)
        );
    }
    
    public static Vector getAllIDs(IReaction reaction) {
        Vector idList = new Vector();
        if (reaction.getID() != null) idList.addElement(reaction.getID());
        IMoleculeSet reactants = reaction.getReactants();
        for (int i=0; i<reactants.getAtomContainerCount(); i++) {
            IMolecule mol = reactants.getMolecule(i);;
            idList.addAll(AtomContainerManipulator.getAllIDs(mol));
        }
        IMoleculeSet products = reaction.getProducts();
        for (int i=0; i<products.getAtomContainerCount(); i++) {
            IMolecule mol = products.getMolecule(i);;
            idList.addAll(AtomContainerManipulator.getAllIDs(mol));
        }
        return idList;
    }

    public static IAtomContainer getRelevantAtomContainer(IReaction reaction, IAtom atom) {
        IAtomContainer result = MoleculeSetManipulator.getRelevantAtomContainer(reaction.getReactants(), atom);
        if (result != null) {
            return result;
        }
        return MoleculeSetManipulator.getRelevantAtomContainer(reaction.getProducts(), atom);
    }

    public static IAtomContainer getRelevantAtomContainer(IReaction reaction, IBond bond) {
        IAtomContainer result = MoleculeSetManipulator.getRelevantAtomContainer(reaction.getReactants(), bond);
        if (result != null) {
            return result;
        }
        return MoleculeSetManipulator.getRelevantAtomContainer(reaction.getProducts(), bond);
    }
    
    public static void setAtomProperties(IReaction reaction, Object propKey, Object propVal) {
    	IMoleculeSet reactants = reaction.getReactants();
        for (int j=0; j<reactants.getAtomContainerCount(); j++) {
            AtomContainerManipulator.setAtomProperties(
                reactants.getMolecule(j), propKey, propVal
            );
        }
        IMoleculeSet products = reaction.getProducts();
        for (int j=0; j<products.getAtomContainerCount(); j++) {
            AtomContainerManipulator.setAtomProperties(
                products.getMolecule(j), propKey, propVal
            );
        }
    }
    
    public static List getAllChemObjects(IReaction reaction) {
        ArrayList list = new ArrayList();
        list.add(reaction);
        IMoleculeSet reactants = reaction.getReactants();
        for (int i=0; i<reactants.getAtomContainerCount(); i++) {
            list.add(reactants.getMolecule(i));
        }
        IMoleculeSet products = reaction.getProducts();
        for (int i=0; i<products.getAtomContainerCount(); i++) {
            list.add(products.getMolecule(i));
        }
        return list;
    }
    /**
     * get the IAtom which is mapped
     * 
     * @param reaction   The IReaction which contains the mapping 
     * @param chemObject The IChemObject which will be searched its mapped IChemObject
     * @return           The mapped IChemObject
     */
    public static IChemObject getMappedChemObject(IReaction reaction, IChemObject chemObject){
    	java.util.Iterator mappings = reaction.mappings();
    	while (mappings.hasNext()){
    		IMapping mapping = (IMapping)mappings.next();
			if (mapping.getChemObject(0).equals(chemObject)){
				return mapping.getChemObject(1);
			} else if (mapping.getChemObject(1).equals(chemObject))
				return mapping.getChemObject(0);
    	}
    	return null;
    }
    
}
