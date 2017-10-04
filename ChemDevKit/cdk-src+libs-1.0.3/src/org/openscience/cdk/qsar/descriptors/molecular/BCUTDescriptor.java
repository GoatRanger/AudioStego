/*  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-10-14 20:29:40 +0200 (Sun, 14 Oct 2007) $
 *  $Revision: 9054 $
 *
 *  Copyright (C) 2004-2007  Rajarshi Guha <rajarshi@users.sourceforge.net> 
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

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.Molecule;
import org.openscience.cdk.aromaticity.HueckelAromaticityDetector;
import org.openscience.cdk.charges.GasteigerMarsiliPartialCharges;
import org.openscience.cdk.charges.Polarizability;
import org.openscience.cdk.config.IsotopeFactory;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.qsar.DescriptorSpecification;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IMolecularDescriptor;
import org.openscience.cdk.qsar.result.DoubleArrayResult;
import org.openscience.cdk.qsar.result.IDescriptorResult;
import org.openscience.cdk.tools.HydrogenAdder;
import org.openscience.cdk.tools.LoggingTool;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

/**
 * Eigenvalue based descriptor noted for its utility in chemical diversity.
 * Described by Pearlman et al. {@cdk.cite PEA99}.
 * <p/>
 * <p>The descriptor is based on a weighted version of the Burden matrix {@cdk.cite BUR89, BUR97}
 * which takes into account both the connectivity as well as atomic
 * properties of a molecule. The weights are a variety of atom properties placed along the
 * diagonal of the Burden matrix. Currently three weighting schemes are employed
 * <ul>
 * <li>atomic weight
 * <li>partial charge (Gasteiger Marsilli)
 * <li>polarizability {@cdk.cite KJ81}
 * </ul>
 * <p>By default, the descriptor will return the highest and lowest eigenvalues for the three
 * classes of descriptor in a single ArrayList (in the order shown above). However it is also
 * possible to supply a parameter list indicating how many of the highest and lowest eigenvalues
 * (for each class of descriptor) are required.
 * <p/>
 * <p>The descriptor works with the hydrogen depleted molecule and thus the maximum number
 * of eigenvalues calculated for any class of BCUT descriptor is equal to the number
 * of heavy atoms present.
 * <p>This descriptor uses these parameters:
 * <table border="1">
 * <tr>
 * <td>Name</td>
 * <td>Default</td>
 * <td>Description</td>
 * </tr>
 * <tr>
 * <td>nhigh</td>
 * <td>1</td>
 * <td>The number of highest eigenvalue</td>
 * </tr>
 * <tr>
 * <td>nlow</td>
 * <td>1</td>
 * <td>The number of lowest eigenvalue</td>
 * </tr>
 * </table>
 * <p/>
 * Returns an array of values in the following order
 * <ol>
 * <p/>
 * <li>BCUTw-1l, BCUTw-2l ... - <i>nhigh</i> lowest atom weighted BCUTS
 * <li>BCUTw-1h, BCUTw-2h ... - <i>nlow</i> highest atom weighted BCUTS
 * <li>BCUTc-1l, BCUTc-2l ... - <i>nhigh</i> lowest partial charge weighted BCUTS
 * <li>BCUTc-1h, BCUTc-2h ... - <i>nlow</i> highest partial charge weighted BCUTS
 * <li>BCUTp-1l, BCUTp-2l ... - <i>nhigh</i> lowest polarizability weighted BCUTS
 * <li>BCUTp-1h, BCUTp-2h ... - <i>nlow</i> highest polarizability weighted BCUTS
 *
 * @author Rajarshi Guha
 * @cdk.created 2004-11-30
 * @cdk.builddepends Jama-1.0.1.jar
 * @cdk.depends Jama-1.0.1.jar
 * @cdk.module qsar
 * @cdk.set qsar-descriptors
 * @cdk.dictref qsar-descriptors:BCUT
 * @cdk.keyword BCUT
 * @cdk.keyword descriptor
 */
public class BCUTDescriptor implements IMolecularDescriptor {
    private LoggingTool logger;

    // the number of negative & positive eigenvalues
    // to return for each class of BCUT descriptor
    private int nhigh;
    private int nlow;
    private boolean checkAromaticity = true;

    public BCUTDescriptor() {
        logger = new LoggingTool(this);

        // set the default number of BCUT's
        this.nhigh = 1;
        this.nlow = 1;
    }

    public DescriptorSpecification getSpecification() {
        return new DescriptorSpecification(
                "http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#BCUT",
                this.getClass().getName(),
                "$Id: BCUTDescriptor.java 9054 2007-10-14 18:29:40Z egonw $",
                "The Chemistry Development Kit");
    }

    /**
     * Sets the parameters attribute of the BCUTDescriptor object.
     *
     * @param params The new parameter values. This descriptor takes 3 parameters: number of highest
     *               eigenvalues and number of lowest eigenvalues. If 0 is specified for either (the default)
     *               then all calculated eigenvalues are returned. The third parameter checkAromaticity is a boolean.
     *               If checkAromaticity is true, the method check the aromaticity, if false, means that the aromaticity has
     *               already been checked.
     * @throws CDKException if the parameters are of the wrong type
     * @see #getParameters
     */
    public void setParameters(Object[] params) throws CDKException {
        // we expect 3 parameters
        if (params.length != 3) {
            throw new CDKException("BCUTDescriptor requires 3 parameters");
        }
        if (!(params[0] instanceof Integer) || !(params[1] instanceof Integer)) {
            throw new CDKException("Parameters must be of type Integer");
        } else if (!(params[2] instanceof Boolean)) {
            throw new CDKException("The third parameter must be of type Boolean");
        }
        // ok, all should be fine

        this.nhigh = ((Integer) params[0]).intValue();
        this.nlow = ((Integer) params[1]).intValue();
        this.checkAromaticity = ((Boolean) params[2]).booleanValue();

        if (this.nhigh < 0 || this.nlow < 0) {
            throw new CDKException("Number of eigenvalues to return must be positive or 0");
        }
    }

    /**
     * Gets the parameters attribute of the BCUTDescriptor object.
     *
     * @return Three element array of Integer and one boolean representing number of highest and lowest eigenvalues and the checkAromaticity flag
     *         to return respectively
     * @see #setParameters
     */
    public Object[] getParameters() {
        Object params[] = new Object[3];
        params[0] = new Integer(this.nhigh);
        params[1] = new Integer(this.nlow);
        params[2] = Boolean.valueOf(this.checkAromaticity);
        return (params);
    }

    /**
     * Gets the parameterNames attribute of the BCUTDescriptor object.
     *
     * @return The parameterNames value
     */
    public String[] getParameterNames() {
        String[] params = new String[3];
        params[0] = "nhigh";
        params[1] = "nlow";
        params[2] = "checkAromaticity";
        return (params);
    }


    /**
     * Gets the parameterType attribute of the BCUTDescriptor object.
     *
     * @param name Description of the Parameter (can be either 'nhigh' or 'nlow' or checkAromaticity)
     * @return The parameterType value
     */
    public Object getParameterType(String name) {
        Object object = null;
        if (name.equals("nhigh")) object = new Integer(1);
        if (name.equals("nlow")) object = new Integer(1);
        if (name.equals("checkAromaticity")) object = new Integer(1);
        return (object);
    }


    static private class BurdenMatrix {

        static double[][] evalMatrix(IAtomContainer atomContainer, double[] vsd) {
            IAtomContainer local = AtomContainerManipulator.removeHydrogens(atomContainer);

            int natom = local.getAtomCount();
            double[][] matrix = new double[natom][natom];

            /* set the off diagonal entries */
            for (int i = 0; i < natom - 1; i++) {
                for (int j = i + 1; j < natom; j++) {
                    for (int k = 0; k < local.getBondCount(); k++) {
                        org.openscience.cdk.interfaces.IBond bond = local.getBond(k);
                        if (bond.contains(local.getAtom(i)) && bond.contains(local.getAtom(j))) {
                            if (bond.getOrder() == CDKConstants.BONDORDER_SINGLE) matrix[i][j] = 0.1;
                            else if (bond.getOrder() == CDKConstants.BONDORDER_DOUBLE) matrix[i][j] = 0.2;
                            else if (bond.getOrder() == CDKConstants.BONDORDER_TRIPLE) matrix[i][j] = 0.3;
                            else if (bond.getOrder() == CDKConstants.BONDORDER_AROMATIC) matrix[i][j] = 0.15;

                            if (local.getConnectedBondsCount(i) == 1 || local.getConnectedBondsCount(j) == 1) {
                                matrix[i][j] += 0.01;
                            }
                            matrix[j][i] = matrix[i][j];
                        } else {
                            matrix[i][j] = 0.001;
                            matrix[j][i] = 0.001;
                        }
                    }
                }
            }

            /* set the diagonal entries */
            for (int i = 0; i < natom; i++) {
                if (vsd != null) matrix[i][i] = vsd[i];
                else matrix[i][i] = 0.0;
            }
            return (matrix);
        }
    }

    /**
     * Calculates the three classes of BCUT descriptors.
     *
     * @param container Parameter is the atom container.
     * @return An ArrayList containing the descriptors. The default is to return
     *         all calculated eigenvalues of the Burden matrices in the order described
     *         above. If a parameter list was supplied, then only the specified number
     *         of highest and lowest eigenvalues (for each class of BCUT) will be returned.
     * @throws CDKException if the wrong number of eigenvalues are requested (negative or more than the number
     *                      of heavy atoms)
     */
    public DescriptorValue calculate(IAtomContainer container) throws CDKException {
        int counter;
        Molecule molecule;
        try {
            molecule = (Molecule) container.clone();
        } catch (CloneNotSupportedException e) {
            logger.debug("Error during clone");
            throw new CDKException("Error occured during clone "+e);
        }

        // add H's in case they're not present
        HydrogenAdder hydrogenAdder = new HydrogenAdder();
        try {
            hydrogenAdder.addExplicitHydrogensToSatisfyValency(molecule);
        } catch (Exception e) {
            throw new CDKException("Could not add hydrogens: " + e.getMessage(), e);
        }

        // do aromaticity detecttion for calculating polarizability later on
        if (this.checkAromaticity) {
            HueckelAromaticityDetector.detectAromaticity(molecule);
        }

        // find number of heavy atoms
        int nheavy = 0;
        for (int i = 0; i < molecule.getAtomCount(); i++) {
            if (!molecule.getAtom(i).getSymbol().equals("H")) nheavy++;
        }

        if (this.nhigh > nheavy || this.nlow > nheavy) {
            throw new CDKException("Number of negative or positive eigenvalues cannot be more than number of heavy atoms");
        }

        double[] diagvalue = new double[nheavy];

        // get atomic mass weighted BCUT
        counter = 0;
        try {
            for (int i = 0; i < molecule.getAtomCount(); i++) {
                if (molecule.getAtom(i).getSymbol().equals("H")) continue;
                diagvalue[counter] = IsotopeFactory.getInstance(molecule.getBuilder()).
                        getMajorIsotope(molecule.getAtom(i).getSymbol()).getExactMass();
                counter++;
            }
        } catch (Exception e) {
            throw new CDKException("Could not calculate weight: " + e.getMessage(), e);
        }
        double[][] burdenMatrix = BurdenMatrix.evalMatrix(molecule, diagvalue);
        Matrix matrix = new Matrix(burdenMatrix);
        EigenvalueDecomposition eigenDecomposition = new EigenvalueDecomposition(matrix);
        double[] eval1 = eigenDecomposition.getRealEigenvalues();

        // get charge weighted BCUT
        GasteigerMarsiliPartialCharges peoe;
        try {
            peoe = new GasteigerMarsiliPartialCharges();
            peoe.assignGasteigerMarsiliSigmaPartialCharges(molecule, true);
        } catch (Exception e) {
            throw new CDKException("Could not calculate partial charges: " + e.getMessage(), e);
        }
        counter = 0;
        for (int i = 0; i < molecule.getAtomCount(); i++) {
            if (molecule.getAtom(i).getSymbol().equals("H")) continue;
            diagvalue[counter] = molecule.getAtom(i).getCharge();
            counter++;
        }
        burdenMatrix = BurdenMatrix.evalMatrix(molecule, diagvalue);
        matrix = new Matrix(burdenMatrix);
        eigenDecomposition = new EigenvalueDecomposition(matrix);
        double[] eval2 = eigenDecomposition.getRealEigenvalues();

        // get polarizability weighted BCUT
        Polarizability pol = new Polarizability();
        counter = 0;
        for (int i = 0; i < molecule.getAtomCount(); i++) {
            if (molecule.getAtom(i).getSymbol().equals("H")) continue;
            diagvalue[counter] = pol.calculateGHEffectiveAtomPolarizability(molecule, molecule.getAtom(i), 1000);
            counter++;
        }
        burdenMatrix = BurdenMatrix.evalMatrix(molecule, diagvalue);
        matrix = new Matrix(burdenMatrix);
        eigenDecomposition = new EigenvalueDecomposition(matrix);
        double[] eval3 = eigenDecomposition.getRealEigenvalues();

        DoubleArrayResult retval = new DoubleArrayResult(eval1.length + eval2.length + eval3.length);

        String[] names;
        String[] suffix = {"w", "c", "p"};

        if (nhigh == 0 || nlow == 0) {
            // return all calculated eigenvalues
            for (int i = 0; i < eval1.length; i++) retval.add(eval1[i]);
            for (int i = 0; i < eval2.length; i++) retval.add(eval2[i]);
            for (int i = 0; i < eval3.length; i++) retval.add(eval3[i]);

            names = new String[retval.length()];
            for (int j = 0; j < suffix.length; j++) {
                for (int i = 0; i < eval1.length; i++) {
                    names[i] = "BCUT" + suffix[j] + "-" + (i + 1) + "l";
                }
                for (int i = 0; i < eval1.length; i++) {
                    names[i] = "BCUT" + suffix[j] + "-" + (i + 1) + "h";
                }
            }
        } else {
            // return only the n highest & lowest eigenvalues
            for (int i = 0; i < nlow; i++) retval.add(eval1[i]);
            for (int i = 0; i < nhigh; i++) retval.add(eval1[eval1.length - i - 1]);

            for (int i = 0; i < nlow; i++) retval.add(eval2[i]);
            for (int i = 0; i < nhigh; i++) retval.add(eval2[eval2.length - i - 1]);

            for (int i = 0; i < nlow; i++) retval.add(eval3[i]);
            for (int i = 0; i < nhigh; i++) retval.add(eval3[eval3.length - i - 1]);

            names = new String[3 * nhigh + 3 * nlow];
            counter = 0;
            for (int j = 0; j < suffix.length; j++) {
                for (int i = 0; i < nhigh; i++) {
                    names[counter++] = "BCUT" + suffix[j] + "-" + (i + 1) + "l";
                }
                for (int i = 0; i < nlow; i++) {
                    names[counter++] = "BCUT" + suffix[j] + "-" + (i + 1) + "h";
                }
            }
        }


        return new DescriptorValue(getSpecification(), getParameterNames(), getParameters(), retval, names);
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
}


