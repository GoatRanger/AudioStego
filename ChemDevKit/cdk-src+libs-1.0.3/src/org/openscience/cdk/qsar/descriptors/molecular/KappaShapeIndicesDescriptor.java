/*
 *  $RCSfile$
 *  $Author: rajarshi $
 *  $Date: 2007-02-01 20:46:29 +0100 (Thu, 01 Feb 2007) $
 *  $Revision: 7844 $
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

import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.qsar.DescriptorSpecification;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IMolecularDescriptor;
import org.openscience.cdk.qsar.result.DoubleArrayResult;
import org.openscience.cdk.qsar.result.IDescriptorResult;

import java.util.ArrayList;

/**
 * Kier and Hall kappa molecular shape indices compare the molecular graph with minimal and maximal molecular graphs;
 * a description is given at: http://www.chemcomp.com/Journal_of_CCG/Features/descr.htm#KH :
 * "they are intended to capture different aspects of molecular shape.
 * In the following description, n denotes the number of atoms in the hydrogen suppressed graph,
 * m is the number of bonds in the hydrogen suppressed graph. Also, let p2 denote the number of paths of length 2
 * and let p3 denote the number of paths of length 3".
 * <p/>
 * Returns three values in the order
 * <ol>
 * <li>Kier1 -  First kappa shape index
 * <li>Kier2 - Second kappa shape index
 * <li>Kier3 -  Third kappa (&kappa;) shape index
 * </ol>
 * <p/>
 * <p>This descriptor uses these parameters:
 * <table border="1">
 * <tr>
 * <td>Name</td>
 * <td>Default</td>
 * <td>Description</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td>no parameters</td>
 * </tr>
 * </table>
 *
 * @author mfe4
 * @cdk.created 2004-11-03
 * @cdk.module qsar
 * @cdk.set qsar-descriptors
 * @cdk.dictref qsar-descriptors:kierValues
 * @cdk.keyword Kappe shape index
 * @cdk.keyword descriptor
 */
public class KappaShapeIndicesDescriptor implements IMolecularDescriptor {

    private ArrayList singlePaths = null;
    private ArrayList doublePaths = null;
    private ArrayList triplePaths = null;

    /**
     * Constructor for the KappaShapeIndicesDescriptor object
     */
    public KappaShapeIndicesDescriptor() {
    }


    /**
     * Gets the specification attribute of the
     * KappaShapeIndicesDescriptor object
     *
     * @return The specification value
     */
    public DescriptorSpecification getSpecification() {
        return new DescriptorSpecification(
                "http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#kierValues",
                this.getClass().getName(),
                "$Id: KappaShapeIndicesDescriptor.java 7844 2007-02-01 19:46:29Z rajarshi $",
                "The Chemistry Development Kit");
    }


    /**
     * Sets the parameters attribute of the
     * KappaShapeIndicesDescriptor object
     *
     * @param params The new parameters value
     * @throws CDKException Description of the Exception
     */
    public void setParameters(Object[] params) throws CDKException {
        // no parameters for this descriptor
    }


    /**
     * Gets the parameters attribute of the
     * KappaShapeIndicesDescriptor object
     *
     * @return The parameters value
     */
    public Object[] getParameters() {
        // no parameters to return
        return (null);
    }


    /**
     * calculates the kier shape indices for an atom container
     *
     * @param atomContainer AtomContainer
     * @return kier1, kier2 and kier3 are returned as arrayList of doubles
     * @throws CDKException Possible Exceptions
     */
    public DescriptorValue calculate(IAtomContainer atomContainer) throws CDKException {

        //org.openscience.cdk.interfaces.IAtom[] atoms = atomContainer.getAtoms();
        java.util.List firstAtomNeighboors;
        java.util.List secondAtomNeighboors;
        java.util.List thirdAtomNeighboors;
        DoubleArrayResult kierValues = new DoubleArrayResult(3);
        double bond1;
        double bond2;
        double bond3;
        double kier1;
        double kier2;
        double kier3;
        double atomsCount = atomContainer.getAtomCount();
        singlePaths = new ArrayList();
        doublePaths = new ArrayList();
        triplePaths = new ArrayList();
        double[] sorterFirst = new double[2];
        double[] sorterSecond = new double[3];
        String tmpbond2;
        String tmpbond3;

        for (int a1 = 0; a1 < atomsCount; a1++) {
            bond1 = 0;
            firstAtomNeighboors = atomContainer.getConnectedAtomsList(atomContainer.getAtom(a1));
            for (int a2 = 0; a2 < firstAtomNeighboors.size(); a2 ++) {
                bond1 = atomContainer.getBondNumber(atomContainer.getAtom(a1), (IAtom) firstAtomNeighboors.get(a2));
                if (!singlePaths.contains(new Double(bond1))) {
                    singlePaths.add(new Double(bond1));
                    java.util.Collections.sort(singlePaths);
                }
                secondAtomNeighboors = atomContainer.getConnectedAtomsList((IAtom) firstAtomNeighboors.get(a2));
                for (int a3 = 0; a3 < secondAtomNeighboors.size(); a3 ++) {
                    bond2 = atomContainer.getBondNumber((IAtom) firstAtomNeighboors.get(a2), (IAtom) secondAtomNeighboors.get(a3));
                    if (!singlePaths.contains(new Double(bond2))) {
                        singlePaths.add(new Double(bond2));
                    }
                    sorterFirst[0] = bond1;
                    sorterFirst[1] = bond2;
                    java.util.Arrays.sort(sorterFirst);

                    tmpbond2 = sorterFirst[0] + "+" + sorterFirst[1];

                    if (!doublePaths.contains(tmpbond2) && (bond1 != bond2)) {
                        doublePaths.add(tmpbond2);
                    }
                    thirdAtomNeighboors = atomContainer.getConnectedAtomsList((IAtom) secondAtomNeighboors.get(a3));
                    for (int a4 = 0; a4 < thirdAtomNeighboors.size(); a4 ++) {
                        bond3 = atomContainer.getBondNumber((IAtom) secondAtomNeighboors.get(a3), (IAtom) thirdAtomNeighboors.get(a4));
                        if (!singlePaths.contains(new Double(bond3))) {
                            singlePaths.add(new Double(bond3));
                        }
                        sorterSecond[0] = bond1;
                        sorterSecond[1] = bond2;
                        sorterSecond[2] = bond3;
                        java.util.Arrays.sort(sorterSecond);

                        tmpbond3 = sorterSecond[0] + "+" + sorterSecond[1] + "+" + sorterSecond[2];
                        if (!triplePaths.contains(tmpbond3)) {
                            if ((bond1 != bond2) && (bond1 != bond3) && (bond2 != bond3)) {
                                triplePaths.add(tmpbond3);
                            }
                        }
                    }
                }
            }
        }

        if (atomsCount == 1) {
            kier1 = 0;
            kier2 = 0;
            kier3 = 0;
        } else {
            kier1 = (((atomsCount) * ((atomsCount - 1) * (atomsCount - 1))) / (singlePaths.size() * singlePaths.size()));
            if (atomsCount == 2) {
                kier2 = 0;
                kier3 = 0;
            } else {
                if (doublePaths.size() == 0) kier2 = Double.NaN;
                else
                    kier2 = (((atomsCount - 1) * ((atomsCount - 2) * (atomsCount - 2))) / (doublePaths.size() * doublePaths.size()));
                if (atomsCount == 3) {
                    kier3 = 0;
                } else {
                    if (atomsCount % 2 != 0) {
                        if (triplePaths.size() == 0) kier3 = Double.NaN;
                        else
                            kier3 = (((atomsCount - 1) * ((atomsCount - 3) * (atomsCount - 3))) / (triplePaths.size() * triplePaths.size()));
                    } else {
                        if (triplePaths.size() == 0) kier3 = Double.NaN;
                        else
                            kier3 = (((atomsCount - 3) * ((atomsCount - 2) * (atomsCount - 2))) / (triplePaths.size() * triplePaths.size()));
                    }
                }
            }
        }

        kierValues.add(kier1);
        kierValues.add(kier2);
        kierValues.add(kier3);
        return new DescriptorValue(getSpecification(), getParameterNames(), getParameters(), kierValues,
                new String[]{"Kier1", "Kier2", "Kier3"});
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
        return new DoubleArrayResult();
    }


    /**
     * Gets the parameterNames attribute of the
     * KappaShapeIndicesDescriptor object
     *
     * @return The parameterNames value
     */
    public String[] getParameterNames() {
        // no param names to return
        return (null);
    }


    /**
     * Gets the parameterType attribute of the
     * KappaShapeIndicesDescriptor object
     *
     * @param name Description of the Parameter
     * @return The parameterType value
     */
    public Object getParameterType(String name) {
        return (null);
    }
}

