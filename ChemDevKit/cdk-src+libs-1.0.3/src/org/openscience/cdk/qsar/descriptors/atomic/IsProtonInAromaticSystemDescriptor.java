/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-10-25 11:54:10 +0200 (Thu, 25 Oct 2007) $
 *  $Revision: 9202 $
 *
 *  Copyright (C) 2004-2007  The Chemistry Development Kit (CDK) project
 *
 *  Contact: cdk-devel@lists.sourceforge.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
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
package org.openscience.cdk.qsar.descriptors.atomic;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.aromaticity.HueckelAromaticityDetector;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.nonotify.NNMolecule;
import org.openscience.cdk.qsar.DescriptorSpecification;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IAtomicDescriptor;
import org.openscience.cdk.qsar.result.IntegerResult;
import org.openscience.cdk.ringsearch.AllRingsFinder;

/**
 *  This descriptor returns 1 if the protons is directly bonded to an aromatic system,
 *  it returns 2 if the distance between aromatic system and proton is 2 bonds, 
 *  and it return 0 for other positions. It is needed to use addExplicitHydrogensToSatisfyValency method.
 *
 * <p>This descriptor uses these parameters:
 * <table>
 *   <tr>
 *     <td>Name</td>
 *     <td>Default</td>
 *     <td>Description</td>
 *   </tr>
 *   <tr>
 *     <td>checkAromaticity</td>
 *     <td>false</td>
 *     <td>True is the aromaticity has to be checked</td>
 *   </tr>
 *   
 * </table>
 *
 * @author      mfe4
 * @cdk.created 2004-11-03
 * @cdk.module  qsar
 * @cdk.set     qsar-descriptors
 * @cdk.dictref qsar-descriptors:isProtonInAromaticSystem
 */
public class IsProtonInAromaticSystemDescriptor implements IAtomicDescriptor {

	private boolean checkAromaticity = false;


	/**
	 *  Constructor for the IsProtonInAromaticSystemDescriptor object
	 */
	public IsProtonInAromaticSystemDescriptor() { }


	/**
	 *  Gets the specification attribute of the IsProtonInAromaticSystemDescriptor
	 *  object
	 *
	 *@return    The specification value
	 */
	public DescriptorSpecification getSpecification() {
        return new DescriptorSpecification(
            "http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#isProtonInAromaticSystem",
		    this.getClass().getName(),
		    "$Id: IsProtonInAromaticSystemDescriptor.java 9202 2007-10-25 09:54:10Z egonw $",
            "The Chemistry Development Kit");
	}


	/**
	 *  Sets the parameters attribute of the IsProtonInAromaticSystemDescriptor
	 *  object
	 *
	 *@param  params            The new parameters value
	 *@exception  CDKException  Possible Exceptions
	 */
	public void setParameters(Object[] params) throws CDKException {
		if (params.length > 1) {
			throw new CDKException("IsProtonInAromaticSystemDescriptor only expects two parameters");
		}
		if (!(params[0] instanceof Boolean)) {
			throw new CDKException("The second parameter must be of type Boolean");
		}
		checkAromaticity = ((Boolean) params[0]).booleanValue();
	}


	/**
	 *  Gets the parameters attribute of the IsProtonInAromaticSystemDescriptor
	 *  object
	 *
	 *@return    The parameters value
	 */
	public Object[] getParameters() {
		// return the parameters as used for the descriptor calculation
		Object[] params = new Object[1];
		params[0] = new Boolean(checkAromaticity);
		return params;
	}


	/**
	 *  The method is a proton descriptor that evaluate if a proton is bonded to an aromatic system or if there is distance of 2 bonds.
	 *  It is needed to call the addExplicitHydrogensToSatisfyValency method from the class tools.HydrogenAdder.
	 *
	 *@param  atom              The IAtom for which the DescriptorValue is requested
     *@param  atomContainer               AtomContainer
	 *@return                   true if the proton is bonded to an aromatic atom.
	 *@exception  CDKException  Possible Exceptions
	 */
	public DescriptorValue calculate(IAtom atom, IAtomContainer atomContainer) throws CDKException {
        IAtomContainer clonedAtomContainer;
        try {
            clonedAtomContainer = (IAtomContainer) atomContainer.clone();
        } catch (CloneNotSupportedException e) {
            throw new CDKException("Error during clone");
        }
        IAtom clonedAtom = clonedAtomContainer.getAtom(atomContainer.getAtomNumber(atom));

        int isProtonInAromaticSystem = 0;
		IMolecule mol = new NNMolecule(clonedAtomContainer);
		if (checkAromaticity) {
			IRingSet rs = (new AllRingsFinder()).findAllRings(mol);
			HueckelAromaticityDetector.detectAromaticity(mol, rs, true);
		}
		java.util.List neighboor = mol.getConnectedAtomsList(clonedAtom);
		IAtom target = atom;
		IAtom neighbour0 = (IAtom)neighboor.get(0);
		if(target.getSymbol().equals("H")) {
			//logger.debug("aromatic proton");
			if(neighbour0.getFlag(CDKConstants.ISAROMATIC)) {
				isProtonInAromaticSystem = 1;
			}
			else {
				java.util.List betaAtoms = clonedAtomContainer.getConnectedAtomsList(neighbour0);
				for (int i = 0; i < betaAtoms.size(); i++) {
					if(((IAtom)betaAtoms.get(i)).getFlag(CDKConstants.ISAROMATIC)) {
						isProtonInAromaticSystem = 2;
						break;
					}
				}
			}
		}
		else {
			isProtonInAromaticSystem = 0;
		}
		return new DescriptorValue(getSpecification(), getParameterNames(), getParameters(), new IntegerResult(isProtonInAromaticSystem));	
	}


	/**
	 *  Gets the parameterNames attribute of the IsProtonInAromaticSystemDescriptor
	 *  object
	 *
	 *@return    The parameterNames value
	 */
	public String[] getParameterNames() {
		String[] params = new String[1];
		params[0] = "checkAromaticity";
		return params;
	}


	/**
	 *  Gets the parameterType attribute of the IsProtonInAromaticSystemDescriptor
	 *  object
	 *
	 *@param  name  Description of the Parameter
	 *@return       The parameterType value
	 */
	public Object getParameterType(String name) {
		return new Boolean(true);
	}
}

