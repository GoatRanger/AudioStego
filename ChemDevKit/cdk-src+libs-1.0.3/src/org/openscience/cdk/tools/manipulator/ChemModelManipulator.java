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
import java.util.Iterator;
import java.util.List;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IChemModel;
import org.openscience.cdk.interfaces.ICrystal;
import org.openscience.cdk.interfaces.IElectronContainer;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.interfaces.IMoleculeSet;
import org.openscience.cdk.interfaces.IReaction;
import org.openscience.cdk.interfaces.IReactionSet;

/**
 * Class with convenience methods that provide methods from
 * methods from ChemObjects within the ChemModel. For example:
 * <pre>
 * ChemModelManipulator.removeAtomAndConnectedElectronContainers(chemModel, atom);
 * </pre>
 * will find the Atom in the model by traversing the ChemModel's
 * MoleculeSet, Crystal and ReactionSet fields and remove
 * it with the removeAtomAndConnectedElectronContainers(Atom) method.
 *
 * @cdk.module standard
 *
 * @see org.openscience.cdk.AtomContainer#removeAtomAndConnectedElectronContainers(IAtom)
 */
public class ChemModelManipulator {
    
	/**
	 * Get the total number of atoms inside an IChemModel.
	 * 
	 * @param chemModel  The IChemModel object.
	 * @return           The number of Atom object inside.
	 */
    public static int getAtomCount(IChemModel chemModel) {
    	int count = 0;
        ICrystal crystal = chemModel.getCrystal();
        if (crystal != null) {
            count += crystal.getAtomCount();
        }
        IMoleculeSet moleculeSet = chemModel.getMoleculeSet();
        if (moleculeSet != null) {
            count += MoleculeSetManipulator.getAtomCount(moleculeSet);
        }
        IReactionSet reactionSet = chemModel.getReactionSet();
        if (reactionSet != null) {
            count += ReactionSetManipulator.getAtomCount(reactionSet);
        }
        return count;
    }
    
    /**
	 * Get the total number of bonds inside an IChemModel.
	 * 
	 * @param chemModel  The IChemModel object.
	 * @return           The number of Bond object inside.
	 */
    public static int getBondCount(IChemModel chemModel) {
    	int count = 0;
        ICrystal crystal = chemModel.getCrystal();
        if (crystal != null) {
            count += crystal.getBondCount();
        }
        IMoleculeSet moleculeSet = chemModel.getMoleculeSet();
        if (moleculeSet != null) {
            count += MoleculeSetManipulator.getBondCount(moleculeSet);
        }
        IReactionSet reactionSet = chemModel.getReactionSet();
        if (reactionSet != null) {
            count += ReactionSetManipulator.getBondCount(reactionSet);
        }
        return count;
    }
    
    /**
     * Remove an Atom and the connected ElectronContainers from all AtomContainers
     * inside an IChemModel.
     * 
     * @param chemModel  The IChemModel object.
     * @param atom       The Atom object to remove.
     */
    public static void removeAtomAndConnectedElectronContainers(IChemModel chemModel, IAtom atom) {
        ICrystal crystal = chemModel.getCrystal();
        if (crystal != null) {
            if (crystal.contains(atom)) {
                crystal.removeAtomAndConnectedElectronContainers(atom);
            }
            return;
        }
        IMoleculeSet moleculeSet = chemModel.getMoleculeSet();
        if (moleculeSet != null) {
            MoleculeSetManipulator.removeAtomAndConnectedElectronContainers(moleculeSet, atom);
        }
        IReactionSet reactionSet = chemModel.getReactionSet();
        if (reactionSet != null) {
            ReactionSetManipulator.removeAtomAndConnectedElectronContainers(reactionSet, atom);
        }
    }
    
    /**
     * Remove an ElectronContainer from all AtomContainers
     * inside an IChemModel.
     * 
     * @param chemModel  The IChemModel object.
     * @param electrons  The ElectronContainer to remove.
     */
    public static void removeElectronContainer(IChemModel chemModel, IElectronContainer electrons) {
        ICrystal crystal = chemModel.getCrystal();
        if (crystal != null) {
            if (crystal.contains(electrons)) {
                crystal.removeElectronContainer(electrons);
            }
            return;
        }
        IMoleculeSet moleculeSet = chemModel.getMoleculeSet();
        if (moleculeSet != null) {
            MoleculeSetManipulator.removeElectronContainer(moleculeSet, electrons);
        }
        IReactionSet reactionSet = chemModel.getReactionSet();
        if (reactionSet != null) {
            ReactionSetManipulator.removeElectronContainer(reactionSet, electrons);
        }
    }
    
    /**
     * Adds a new Molecule to the MoleculeSet inside a given ChemModel.
     * Creates a MoleculeSet if none is contained.
     * 
     * @param chemModel  The ChemModel object.
     * @return           The created Molecule object.
     */
    public static IAtomContainer createNewMolecule(IChemModel chemModel) {
        // Add a new molecule either the set of molecules
        IMolecule molecule = chemModel.getBuilder().newMolecule();
        if (chemModel.getMoleculeSet() != null) {
            IMoleculeSet moleculeSet = chemModel.getMoleculeSet();
            moleculeSet.addMolecule(molecule);
        } else {
            IMoleculeSet moleculeSet = chemModel.getBuilder().newMoleculeSet();
            moleculeSet.addMolecule(molecule);
            chemModel.setMoleculeSet(moleculeSet);
        }
        return molecule;
    }

    /**
     * Create a new ChemModel containing an IAtomContainer.
     * 
     * @param  atomContainer  The AtomContainer to have inside the ChemModel.
     * @return                The new IChemModel object.
     */
    public static IChemModel newChemModel(IAtomContainer atomContainer) {
        IChemModel model = atomContainer.getBuilder().newChemModel();
        IMoleculeSet moleculeSet = model.getBuilder().newMoleculeSet();
        moleculeSet.addAtomContainer(atomContainer);
        model.setMoleculeSet(moleculeSet);
        return model;
    }

    /**
     * This badly named methods tries to determine which AtomContainer in the
     * ChemModel is best suited to contain added Atom's and Bond's.
     */
    public static IAtomContainer getRelevantAtomContainer(IChemModel chemModel, IAtom atom) {
        IAtomContainer result = null;
        if (chemModel.getMoleculeSet() != null) {
            IMoleculeSet moleculeSet = chemModel.getMoleculeSet();
            result = MoleculeSetManipulator.getRelevantAtomContainer(moleculeSet, atom);
            if (result != null) {
                return result;
            }
        }
        if (chemModel.getReactionSet() != null) {
            IReactionSet reactionSet = chemModel.getReactionSet();
            return ReactionSetManipulator.getRelevantAtomContainer(reactionSet, atom);
        }
        // This should never happen.
        return null;
    }

    /**
     * Retrieves the first IAtomContainer containing a given IBond from an
     * IChemModel.
     * 
     * @param chemModel  The IChemModel object.
     * @param bond       The IBond object to search.
     * @return           The IAtomContainer object found, null if none is found.
     */
    public static IAtomContainer getRelevantAtomContainer(IChemModel chemModel, IBond bond) {
        IAtomContainer result = null;
        if (chemModel.getMoleculeSet() != null) {
            IMoleculeSet moleculeSet = chemModel.getMoleculeSet();
            result = MoleculeSetManipulator.getRelevantAtomContainer(moleculeSet, bond);
            if (result != null) {
                return result;
            }
        }
        if (chemModel.getReactionSet() != null) {
            IReactionSet reactionSet = chemModel.getReactionSet();
            return ReactionSetManipulator.getRelevantAtomContainer(reactionSet, bond);
        }
        // This should never happen.
        return null;
    }
    
    /**
     * Retrieves the first IReaction containing a given IAtom from an
     * IChemModel.
     * 
     * @param chemModel  The IChemModel object.
     * @param atom       The IAtom object to search.
     * @return           The IAtomContainer object found, null if none is found.
     */
    public static IReaction getRelevantReaction(IChemModel chemModel, IAtom atom) {
        IReaction reaction = null;
        if (chemModel.getReactionSet() != null) {
            IReactionSet reactionSet = chemModel.getReactionSet();
            reaction = ReactionSetManipulator.getRelevantReaction(reactionSet, atom);
        }
        return reaction;
    }

    /**
     * Returns all the AtomContainer's of a ChemModel.
     */
    public static List getAllAtomContainers(IChemModel chemModel) {
        IMoleculeSet moleculeSet = chemModel.getBuilder().newMoleculeSet();
        if (chemModel.getMoleculeSet() != null) {
            moleculeSet.add(chemModel.getMoleculeSet());
        }
        if (chemModel.getReactionSet() != null) {
            moleculeSet.add(
                ReactionSetManipulator.getAllMolecules(
                    chemModel.getReactionSet()
                )
            );
        }
        return MoleculeSetManipulator.getAllAtomContainers(moleculeSet);
    }

    /**
     * Sets the AtomProperties of all Atoms inside an IChemModel.
     * 
     * @param chemModel  The IChemModel object.
     * @param propKey    The key of the property.
     * @param propVal    The value of the property.
     */
    public static void setAtomProperties(IChemModel chemModel, Object propKey, Object propVal) {
        if (chemModel.getMoleculeSet() != null) {
            MoleculeSetManipulator.setAtomProperties(
                chemModel.getMoleculeSet(), propKey, propVal
            );
        }
        if (chemModel.getReactionSet() != null) {
            ReactionSetManipulator.setAtomProperties(
                chemModel.getReactionSet(), propKey, propVal
            );
        }
        if (chemModel.getCrystal() != null) {
            AtomContainerManipulator.setAtomProperties(
                chemModel.getCrystal(), propKey, propVal
            );
        }
    }
    
    /**
     * Retrieve a List of all ChemObject objects within an IChemModel.
     * 
     * @param chemModel  The IChemModel object.
     * @return           A List of all ChemObjects inside.
     */
	public static List getAllChemObjects(IChemModel chemModel) {
		ArrayList list = new ArrayList();
        // list.add(chemModel); // only add ChemObjects contained within
        ICrystal crystal = chemModel.getCrystal();
        if (crystal != null) {
            list.add(crystal);
        }
        IMoleculeSet moleculeSet = chemModel.getMoleculeSet();
        if (moleculeSet != null) {
        	list.add(moleculeSet);
        	List current = MoleculeSetManipulator.getAllChemObjects(moleculeSet);
        	for (Iterator iter = current.iterator(); iter.hasNext();) {
        		Object o = iter.next();
        		if (!list.contains(o)) list.add(o);
        	}
        }
        IReactionSet reactionSet = chemModel.getReactionSet();
        if (reactionSet != null) {
        	list.add(reactionSet);
            List current = ReactionSetManipulator.getAllChemObjects(reactionSet);
        	for (Iterator iter = current.iterator(); iter.hasNext();) {
        		Object o = iter.next();
        		if (!list.contains(o)) list.add(o);
        	}
        }
		return list;
	}

	public static List getAllIDs(IChemModel chemModel) {
		ArrayList list = new ArrayList();
		if (chemModel.getID() != null) list.add(chemModel.getID());
        ICrystal crystal = chemModel.getCrystal();
        if (crystal != null) {
            list.addAll(AtomContainerManipulator.getAllIDs(crystal));
        }
        IMoleculeSet moleculeSet = chemModel.getMoleculeSet();
        if (moleculeSet != null) {
        	list.addAll(MoleculeSetManipulator.getAllIDs(moleculeSet));
        }
        IReactionSet reactionSet = chemModel.getReactionSet();
        if (reactionSet != null) {
        	list.addAll(ReactionSetManipulator.getAllIDs(reactionSet));
        }
		return list;
	}
}

