/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-02-07 23:20:44 +0100 (Wed, 07 Feb 2007) $
 *  $Revision: 7895 $
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

import java.io.IOException;

import org.openscience.cdk.config.AtomTypeFactory;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomType;
import org.openscience.cdk.qsar.DescriptorSpecification;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IAtomicDescriptor;
import org.openscience.cdk.qsar.result.DoubleResult;
import org.openscience.cdk.tools.LoggingTool;

/**
 *  This class return the VdW radius of a given atom.
 *
 * <p>This descriptor uses these parameters:
 * <table border="1">
 *   <tr>
 *     <td>Name</td>
 *     <td>Default</td>
 *     <td>Description</td>
 *   </tr>
 *   <tr>
 *     <td></td>
 *     <td></td>
 *     <td></td>
 *   </tr>
 * </table>
 *
 * @author         mfe4
 * @cdk.created    2004-11-13
 * @cdk.module     qsar
 * @cdk.set        qsar-descriptors
 * @cdk.dictref qsar-descriptors:vdwradius
 */
public class VdWRadiusDescriptor implements IAtomicDescriptor {

    private AtomTypeFactory factory = null;
    private LoggingTool logger;

    /**
     *  Constructor for the VdWRadiusDescriptor object.
     *
     *  @throws IOException if an error ocurrs when reading atom type information
     *  @throws ClassNotFoundException if an error occurs during tom typing
     */
    public VdWRadiusDescriptor() throws IOException, ClassNotFoundException {
        logger = new LoggingTool(this);
    }


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
                "http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#vdwradius",
                this.getClass().getName(),
                "$Id: VdWRadiusDescriptor.java 7895 2007-02-07 22:20:44Z egonw $",
                "The Chemistry Development Kit");
    }


    /**
     * This descriptor does have any parameter.
     */
    public void setParameters(Object[] params) throws CDKException {
    }


    /**
     *  Gets the parameters attribute of the VdWRadiusDescriptor object.
     *
     *@return    The parameters value
     * @see #setParameters
     */
    public Object[] getParameters() {
        return null;
    }


    /**
     *  This method calculate the Van der Waals radius of an atom.
     *
     *@param  container         The {@link IAtomContainer} for which the descriptor is to be calculated
     *@return                   The Van der Waals radius of the atom
     *@exception  CDKException  if an error occurs during atom typing
     */

    public DescriptorValue calculate(IAtom atom, IAtomContainer container) throws CDKException {
        if (factory == null) 
            try {
                factory = AtomTypeFactory.getInstance(
                    "org/openscience/cdk/config/data/jmol_atomtypes.txt", 
                    container.getBuilder()
                );
            } catch (Exception exception) {
                throw new CDKException("Could not instantiate AtomTypeFactory!", exception);
            }

        double vdwradius = 0;
        try {
            String symbol = atom.getSymbol();
            IAtomType type = factory.getAtomType(symbol);
            vdwradius = type.getVanderwaalsRadius();
            return new DescriptorValue(getSpecification(), getParameterNames(), getParameters(), new DoubleResult(vdwradius));
        } catch (Exception ex1) {
            logger.debug(ex1);
            throw new CDKException("Problems with AtomTypeFactory due to " + ex1.toString(), ex1);
        }
    }


    /**
     *  Gets the parameterNames attribute of the VdWRadiusDescriptor object.
     *
     *@return    The parameterNames value
     */
    public String[] getParameterNames() {
        return new String[0];
    }

    /**
     * Gets the parameterType attribute of the VdWRadiusDescriptor object.
     *
     * @param  name  Description of the Parameter
     * @return       An Object of class equal to that of the parameter being requested
     */
    public Object getParameterType(String name) {
        return null;
    }
}

