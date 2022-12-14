/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-10-14 22:04:57 +0200 (Sun, 14 Oct 2007) $
 *  $Revision: 9065 $
 *
 *  Copyright (C) 1997-2007  The Chemistry Development Kit (CDK) project
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
 *
 */
package org.openscience.cdk.layout;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.io.CMLReader;
import org.openscience.cdk.isomorphism.UniversalIsomorphismTester;
import org.openscience.cdk.isomorphism.mcss.RMap;
import org.openscience.cdk.tools.LoggingTool;
import org.openscience.cdk.tools.manipulator.ChemFileManipulator;

import javax.vecmath.Point2d;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Helper class for Structure Diagram Generation. Handles templates. This is
 * our layout solution for ring systems which are notoriously difficult to
 * layout, like cubane, adamantane, porphyrin, etc.
 *
 * @author       steinbeck
 * @cdk.created  2003-09-04
 * @cdk.keyword  layout
 * @cdk.keyword  2D-coordinates
 * @cdk.require  java1.4+
 * @cdk.module   sdg
 */
public class TemplateHandler
{

	private LoggingTool logger;

	private IAtomContainer molecule;
	private IRingSet sssr;
	private double bondLength = 1.5;

	private Vector templates = null;


	/**
	 * Creates a new TemplateHandler.
	 */
	public TemplateHandler(IChemObjectBuilder builder)
	{
		logger = new LoggingTool(this);
		templates = new Vector();
		loadTemplates(builder);
	}


	/**
	 * Loads all existing templates into memory. To add templates to be used in
	 * SDG, place a drawing with the new template in data/templates and add the
	 * template filename to data/templates/template.list
	 */
	public void loadTemplates(IChemObjectBuilder builder)
	{
		String line = null;
		try
		{
			InputStream ins = this.getClass().getClassLoader().getResourceAsStream("data/templates/templates.list");
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
			while (reader.ready()) {
					line = reader.readLine();
					line = "data/templates/" + line;
					logger.debug("Attempting to read template ", line);
					CMLReader structureReader = new CMLReader(
									this.getClass().getClassLoader().getResourceAsStream(line)
					);
					IChemFile file = (IChemFile) structureReader.read(builder.newChemFile());
					List files = ChemFileManipulator.getAllAtomContainers(file);
					for (int i = 0; i < files.size(); i++)
							templates.addElement((IAtomContainer) files.get(i));
					logger.debug("Successfully read template ", line);
			}
		} catch (Exception exc)
		{
			logger.debug("Could not read templates");
			logger.debug("Reason: " + exc.getMessage());
		}

	}

	/**
	 * Adds a Molecule to the list of templates use by this TemplateHandler
	 *
	 * @param  molecule  The molecule to be added to the TemplateHandler
	 */
	public void addMolecule(IAtomContainer molecule)
	{
		templates.addElement(molecule);
	}
	
	public IMolecule removeMolecule(IAtomContainer molecule)  throws CDKException
	{
		IAtomContainer ac1 = molecule.getBuilder().newAtomContainer(molecule);
		IAtomContainer ac2 = null;
		IMolecule mol2 = null;
		for (int f = 0; f < templates.size(); f++)
		{
			mol2 = (IMolecule)templates.elementAt(f);
			ac2 = molecule.getBuilder().newAtomContainer(mol2);
			if (UniversalIsomorphismTester.isIsomorph(ac1, ac2)) {
				templates.removeElementAt(f);
				return mol2;
			}
		}
		return null;
	}

	
	/**
	 * Checks if one of the loaded templates is isomorph to the given
	 * Molecule. If so, it assigns the coordinates from the template to the
	 * respective atoms in the Molecule, and marks the atoms as ISPLACED.
	 *
	 * @param  molecule  The molecule to be check for potential templates
	 * @return           True if there was a possible mapping
	 */
	public boolean mapTemplateExact(IAtomContainer molecule) throws CDKException {
				logger.debug("Trying to map a molecule...");
		boolean mapped = false;
		IMolecule template = null;
		RMap map = null;
		org.openscience.cdk.interfaces.IAtom atom1 = null;
		org.openscience.cdk.interfaces.IAtom atom2 = null;
		for (int f = 0; f < templates.size(); f++)
		{
			template = (IMolecule) templates.elementAt(f);
			if (UniversalIsomorphismTester.isIsomorph(molecule, template))
			{
				List list = UniversalIsomorphismTester.getIsomorphAtomsMap(
						molecule.getBuilder().newAtomContainer(molecule), 
						molecule.getBuilder().newAtomContainer(template)
				);
				logger.debug("Found a subgraph mapping of size " + list.size() + ", template: " + template.getID());
				for (int i = 0; i < list.size(); i++)
				{
					map = (RMap) list.get(i);
					atom1 = molecule.getAtom(map.getId1());
					atom2 = template.getAtom(map.getId2());
					atom1.setPoint2d(new Point2d(atom2.getPoint2d()));
					atom1.setFlag(CDKConstants.ISPLACED, true);
				}
				mapped = true;
			} else {
				logger.debug("Structure does not match template: ", template.getID());
			}
		}
		return mapped;
	}


	/**
	 * Checks if one of the loaded templates is a substructure in the given
	 * Molecule. If so, it assigns the coordinates from the template to the
	 * respective atoms in the Molecule, and marks the atoms as ISPLACED.
	 *
	 * @param  molecule  The molecule to be check for potential templates
	 * @return           True if there was a possible mapping
	 */
	public boolean mapTemplates(IAtomContainer molecule) throws CDKException {
				logger.debug("Trying to map a molecule...");
		boolean mapped = false;
		IMolecule template = null;
		RMap map = null;
		org.openscience.cdk.interfaces.IAtom atom1 = null;
		org.openscience.cdk.interfaces.IAtom atom2 = null;
		for (int f = 0; f < templates.size(); f++)
		{
			template = (IMolecule) templates.elementAt(f);
			if (UniversalIsomorphismTester.isSubgraph(molecule, template))
			{
				List listOfLists = UniversalIsomorphismTester.getSubgraphAtomsMaps(
						molecule.getBuilder().newAtomContainer(molecule), 
						molecule.getBuilder().newAtomContainer(template)
				);
				logger.debug("Found " + listOfLists.size() + " subgraphs matching template: " + template.getID());
				for (Iterator listOfListsIterator = listOfLists.iterator(); listOfListsIterator.hasNext(); ) {
					List list = (List) listOfListsIterator.next();
					logger.debug("Found a subgraph mapping of size " + list.size() + ", template: " + template.getID());
					for (int i = 0; i < list.size(); i++)
					{
						map = (RMap) list.get(i);
						atom1 = molecule.getAtom(map.getId1());
						atom2 = template.getAtom(map.getId2());
						atom1.setPoint2d(new Point2d(atom2.getPoint2d()));
						atom1.setFlag(CDKConstants.ISPLACED, true);
					}
					mapped = true;
				}
			} else {
				logger.debug("Structure does not match template: ", template.getID());
			}
		}
		return mapped;
	}


	/**
	 *  Gets the templateCount attribute of the TemplateHandler object
	 *
	 *@return    The templateCount value
	 */
	public int getTemplateCount()
	{
		return templates.size();
	}


	/**
	 *  Gets the templateAt attribute of the TemplateHandler object
	 *
	 *@param  position  Description of the Parameter
	 *@return           The templateAt value
	 */
	public IAtomContainer getTemplateAt(int position)
	{
		return (IAtomContainer) templates.elementAt(position);
	}
	

	/**
	 *  Set the bond length used for laying out the molecule
	 *
	 *@param  bondLength  The new bondLength value
	 */
	public void setBondLength(double bondLength)
	{
		this.bondLength = bondLength;
	}

	/**
	 * Checks if one of the loaded templates is a substructure in the given
	 * Molecule and returns all matched substructures in a IAtomContainerSet.
	 * This method does not assign any coordinates.
	 *
	 * @param  molecule  The molecule to be check for potential templates
	 * @return           an IAtomContainerSet of all matched substructures of
	 *                   the molecule
	 * @throws CDKException if an error occurs
	 */
	public IAtomContainerSet getMappedSubstructures(IAtomContainer molecule) throws CDKException {
		logger.debug("Trying get mapped substructures...");
		IAtomContainerSet matchedSubstructures = molecule.getBuilder().newAtomContainerSet();
		for (int f = 0; f < templates.size(); f++)
		{
			IMolecule template = (IMolecule) templates.elementAt(f);
			if (UniversalIsomorphismTester.isSubgraph(molecule, template))
			{
				List listOfLists = UniversalIsomorphismTester.getSubgraphAtomsMaps(
						molecule.getBuilder().newAtomContainer(molecule), 
						molecule.getBuilder().newAtomContainer(template)
				);
				logger.debug("Found " + listOfLists.size() + " subgraphs matching template: " + template.getID());
				for (Iterator listOfListsIterator = listOfLists.iterator(); listOfListsIterator.hasNext(); ) {
					List list = (List) listOfListsIterator.next();
					logger.debug("Found a subgraph mapping of size " + list.size() + ", template: " + template.getID());
					IAtomContainer matchedSubstructure = molecule.getBuilder().newAtomContainer();
					for (Iterator listIterator = list.iterator(); listIterator.hasNext(); )
					{
						RMap map = (RMap) listIterator.next();
						IAtom atom = molecule.getAtom(map.getId1());
						matchedSubstructure.addAtom(atom);
					}
					for (Iterator atomIterator = matchedSubstructure.atoms(); atomIterator.hasNext(); ) {
						IAtom atom = (IAtom) atomIterator.next();
						for (Iterator connectedBondsIterator = molecule.getConnectedBondsList(atom).iterator(); connectedBondsIterator.hasNext(); ) {
							IBond bond = (IBond) connectedBondsIterator.next();
							boolean addBond = true;
							for (Iterator bondIterator = bond.atoms(); bondIterator.hasNext(); ) {
								IAtom connectedAtom = (IAtom) bondIterator.next();
								if (!matchedSubstructure.contains(connectedAtom) || matchedSubstructure.contains(bond))
									addBond = false;
							}
							if (addBond)
								matchedSubstructure.addBond(bond);
						}
					}
					matchedSubstructures.addAtomContainer(matchedSubstructure);
				}
				
			} else {
				logger.debug("Structure does not match template: ", template.getID());
			}
		}
		
		/*
		 * Uniquify matchedSubstructures
		 */
		for (int i = 0; i < matchedSubstructures.getAtomContainerCount(); i++) {
			for (int j = i + 1; j < matchedSubstructures.getAtomContainerCount(); j++) {
				if (haveSameAtoms(matchedSubstructures.getAtomContainer(i), matchedSubstructures.getAtomContainer(j)))
					matchedSubstructures.removeAtomContainer(j--);
			}
		}
		
		logger.debug("Found " + matchedSubstructures.getAtomContainerCount() + " unique matched subgraphs");
		
		return matchedSubstructures;
	}

	/**
	 * Returns true if both IAtomContainers have the same number of atoms and all atoms
	 * are equal, false otherwise.
	 * @param atomContainer1 an IAtomContainer
	 * @param atomContainer2 another IAtomContainer
	 * @return true if both IAtomContainers have the same number of atoms and all atoms
	 *         are equal, false otherwise.
	 */
	private boolean haveSameAtoms(IAtomContainer atomContainer1, IAtomContainer atomContainer2) {
		if (atomContainer1.getAtomCount() != atomContainer2.getAtomCount())
			return false;
		for (Iterator iterator = atomContainer1.atoms(); iterator.hasNext(); )
			if (!atomContainer2.contains((IAtom) iterator.next()))
				return false;
		return true;
	}
	
}

