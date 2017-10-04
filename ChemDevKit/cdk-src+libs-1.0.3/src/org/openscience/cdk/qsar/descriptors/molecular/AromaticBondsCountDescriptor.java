/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-10-14 20:35:55 +0200 (Sun, 14 Oct 2007) $
 *  $Revision: 9057 $
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
package org.openscience.cdk.qsar.descriptors.molecular;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.aromaticity.HueckelAromaticityDetector;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.qsar.DescriptorSpecification;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IMolecularDescriptor;
import org.openscience.cdk.qsar.result.IDescriptorResult;
import org.openscience.cdk.qsar.result.IntegerResult;
import org.openscience.cdk.ringsearch.AllRingsFinder;

import java.util.Iterator;

/**
 * This Class contains a method that returns the number of aromatic atoms in an AtomContainer.
 *
 * <p>This descriptor uses these parameters:
 * <table border="1">
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
 * </table>
 *
 * Returns a single value with name <i>nAromBond</i>
 *
 * @author      mfe4
 * @cdk.created 2004-11-03
 * @cdk.module  qsar
 * @cdk.set     qsar-descriptors
 * @cdk.dictref qsar-descriptors:aromaticBondsCount
 */
public class AromaticBondsCountDescriptor implements IMolecularDescriptor {
    private boolean checkAromaticity = false;


    /**
     *  Constructor for the AromaticBondsCountDescriptor object.
     */
    public AromaticBondsCountDescriptor() { }

    /**
     * Returns a <code>Map</code> which specifies which descriptor
     * is implemented by this class. 
     *
     * These fields are used in the map:
     * <ul>
     * <li>Specification-Reference: refers to an entry in a unique dictionary
     * <li>Implementation-Title: anything
     * <li>Implementation-Identifier: a unique identifier for this version of
     *  this class
     * <li>Implementation-Vendor: CDK, JOELib, or anything else
     * </ul>
     *
     * @return An object containing the descriptor specification
     */

    public DescriptorSpecification getSpecification() {
        return new DescriptorSpecification(
                "http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#aromaticBondsCount",
                this.getClass().getName(),
                "$Id: AromaticBondsCountDescriptor.java 9057 2007-10-14 18:35:55Z egonw $",
                "The Chemistry Development Kit");
    }


    /**
     *  Sets the parameters attribute of the AromaticBondsCountDescriptor object.
     *
     * This descriptor takes one parameter, which should be Boolean to indicate whether
     * aromaticity has been checked (TRUE) or not (FALSE).
     * 
     * @param  params            The new parameters value
     * @exception  CDKException if more than one parameter or a non-Boolean parameter is specified
     *@see #getParameters
     */
    public void setParameters(Object[] params) throws CDKException {
        if (params.length != 1) {
            throw new CDKException("AromaticBondsCountDescriptor expects one parameter");
        }
        if (!(params[0] instanceof Boolean)) {
            throw new CDKException("The first parameter must be of type Boolean");
        }
        // ok, all should be fine
        checkAromaticity = ((Boolean) params[0]).booleanValue();
    }


    /**
     *  Gets the parameters attribute of the AromaticBondsCountDescriptor object.
     *
     *@return    The parameters value
     *@see #setParameters
     */
    public Object[] getParameters() {
        // return the parameters as used for the descriptor calculation
        Object[] params = new Object[1];
        params[0] = new Boolean(checkAromaticity);
        return params;
    }


    /**
     * Calculate the count of aromatic atoms in the supplied {@link IAtomContainer}.
     *
     *  The method take a boolean checkAromaticity: if the boolean is true, it means that
     *  aromaticity has to be checked.
     *
     *@param  atomContainer  The {@link IAtomContainer} for which this descriptor is to be calculated
     *@return the number of aromatic atoms of this AtomContainer
     *@throws CDKException if there is a problem in atomaticity detection
     *@see #setParameters
     */
    public DescriptorValue calculate(IAtomContainer atomContainer) throws CDKException {
        IAtomContainer ac;
        try {
            ac = (IAtomContainer) atomContainer.clone();
        } catch (CloneNotSupportedException e) {
            throw new CDKException("Error during clone");
        }


        int aromaticBondsCount = 0;
        if (checkAromaticity) {
            IRingSet rs = (new AllRingsFinder()).findAllRings(ac);
            HueckelAromaticityDetector.detectAromaticity(ac, rs, true);
        }
        Iterator bonds = ac.bonds();
        while (bonds.hasNext()) {
            IBond bond = (IBond) bonds.next();
            if (bond.getFlag(CDKConstants.ISAROMATIC)) {
                aromaticBondsCount += 1;
            }
        }
        return new DescriptorValue(getSpecification(), getParameterNames(), getParameters(),
                new IntegerResult(aromaticBondsCount), new String[] {"nAromBond"});
    }

    /**
     * Returns the specific type of the DescriptorResult object.
     * <p/>
     * The return value from this method really indicates what type of result will
     * be obtained from the {@link org.openscience.cdk.qsar.DescriptorValue} object. Note that the same result
     * can be achieved by interrogating the {@link org.openscience.cdk.qsar.DescriptorValue} object; this method
     * allows you to do the same thing, without actually calculating the descriptor.
     *
     * @return an object that implements the {@link org.openscience.cdk.qsar.result.IDescriptorResult} interface indicating
     *         the actual type of values returned by the descriptor in the {@link org.openscience.cdk.qsar.DescriptorValue} object
     */
    public IDescriptorResult getDescriptorResultType() {
        return new IntegerResult(1);
    }


    /**
     *  Gets the parameterNames attribute of the AromaticBondsCountDescriptor object.
     *
     *@return    The parameterNames value
     */
    public String[] getParameterNames() {
        String[] params = new String[1];
        params[0] = "checkAromaticity";
        return params;
    }



    /**
     *  Gets the parameterType attribute of the AromaticBondsCountDescriptor object.
     *
     *@param  name  Description of the Parameter
     *@return       An Object of class equal to that of the parameter being requested
     */
    public Object getParameterType(String name) {
        return new Boolean(true);
    }
}

