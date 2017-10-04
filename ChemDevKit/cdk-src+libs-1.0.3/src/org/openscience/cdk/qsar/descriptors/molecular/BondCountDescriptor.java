/*  $Revision: 7894 $ $Author: egonw $ $Date: 2007-02-07 23:15:58 +0100 (Wed, 07 Feb 2007) $
 *
 *  Copyright (C) 2004-2007  Matteo Floris <mfe4@users.sf.net>
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

import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.qsar.DescriptorSpecification;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IMolecularDescriptor;
import org.openscience.cdk.qsar.result.IDescriptorResult;
import org.openscience.cdk.qsar.result.IntegerResult;

import java.util.Iterator;

/**
 *  IDescriptor based on the number of bonds of a certain bond order.
 *
 * <p>This descriptor uses these parameters:
 * <table border="1">
 *   <tr>
 *     <td>Name</td>
 *     <td>Default</td>
 *     <td>Description</td>
 *   </tr>
 *   <tr>
 *     <td>order</td>
 *     <td>any</td>
 *     <td>The bond order</td>
 *   </tr>
 * </table>
 *
 * Returns a single value with name <i>nBX</i> where <i>X</i> can be
 * <ul>
 * <li>s for single bonds
 * <li>d for double bonds
 * <li>t for triple bonds
 * <li>a for aromatic bonds
 * <li>"" for all bonds
 * </ul>
 *
 * @author      mfe4
 * @cdk.created 2004-11-13
 * @cdk.module  qsar
 * @cdk.set     qsar-descriptors
 * @cdk.dictref qsar-descriptors:bondCount
 */
public class BondCountDescriptor implements IMolecularDescriptor {

	/** defaults to -1.0, which means: count all bonds **/
    private double order = -1.0;

    /**
     *  Constructor for the BondCountDescriptor object
     */
    public BondCountDescriptor() { }


    /**
     *  Gets the specification attribute of the BondCountDescriptor object
     *
     *@return    The specification value
     */
    public DescriptorSpecification getSpecification() {
        return new DescriptorSpecification(
            "http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#bondCount",
            this.getClass().getName(),
            "$Id: BondCountDescriptor.java 7894 2007-02-07 22:15:58Z egonw $",
            "The Chemistry Development Kit");
    }


    /**
     *  Sets the parameters attribute of the BondCountDescriptor object
     *
     *@param  params            The new parameters value
     *@exception  CDKException  Description of the Exception
     */
    public void setParameters(Object[] params) throws CDKException {
        if (params.length > 1) {
            throw new CDKException("BondCount only expects one parameter");
        }
        if (!(params[0] instanceof Double)) {
            throw new CDKException("The parameter must be of type Double");
        }
        // ok, all should be fine
        order = ((Double) params[0]).doubleValue();
    }


    /**
     *  Gets the parameters attribute of the BondCountDescriptor object
     *
     *@return    The parameters value
     */
    public Object[] getParameters() {
        // return the parameters as used for the descriptor calculation
        Object[] params = new Object[1];
        params[0] = new Double(order);
        return params;
    }


    /**
     *  This method calculate the number of bonds of a given type in an atomContainer
     *
     *@param  container  AtomContainer
     *@return            The number of bonds of a certain type.
     */
    public DescriptorValue calculate(IAtomContainer container) {
    	if (order < 0) {
    		// the special case: just count them all
    		return new DescriptorValue(getSpecification(), getParameterNames(), getParameters(),
                    new IntegerResult(container.getBondCount()), new String[]{"nB"});
    	}
    	
        int bondCount = 0;
        Iterator bonds = container.bonds();
        while (bonds.hasNext()) {
            IBond bond = (IBond) bonds.next();
            if (bond.getOrder() == order) {
                bondCount += 1;
            }
        }

        String name = "nB";
        if (order == 1) name += "s";
        else if (order == 2) name += "d";
        else if (order == 3) name += "t";
        else if (order == 1.5) name += "a";

        return new DescriptorValue(getSpecification(), getParameterNames(), getParameters(),
                new IntegerResult(bondCount), new String[]{name});
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
     *  Gets the parameterNames attribute of the BondCountDescriptor object
     *
     *@return    The parameterNames value
     */
    public String[] getParameterNames() {
        String[] params = new String[1];
        params[0] = "order";
        return params;
    }


    /**
     *  Gets the parameterType attribute of the BondCountDescriptor object
     *
     *@param  name  Description of the Parameter
     *@return       The parameterType value
     */
    public Object getParameterType(String name) {
        return new Double(0.0);
    }
}

