/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-08-28 09:51:32 +0200 (Tue, 28 Aug 2007) $
 *  $Revision: 8710 $
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

import org.openscience.cdk.config.IsotopeFactory;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.qsar.DescriptorSpecification;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IMolecularDescriptor;
import org.openscience.cdk.qsar.result.DoubleArrayResult;
import org.openscience.cdk.qsar.result.IDescriptorResult;
import org.openscience.cdk.tools.LoggingTool;

import javax.vecmath.Point3d;
import java.util.Vector;


/**
 * IDescriptor characterizing the mass distribution of the molecule.
 * Described by Katritzky et al. {@cdk.cite KAT96}.
 * For modelling purposes the value of the descriptor is calculated
 * both with and without H atoms. Furthermore the square and cube roots
 * of the descriptor are also generated as described by Wessel et al. {@cdk.cite WES98}.
 * <p/>
 * The descriptor routine generates 9 descriptors:
 * <ul>
 * <li>GRAV-1 -  gravitational index of heavy atoms
 * <li>GRAV-2 -  square root of gravitational index of heavy atoms
 * <li>GRAV-3 -  cube root of gravitational index of heavy atoms
 * <li>GRAVH-1 -  gravitational index - hydrogens included
 * <li>GRAVH-2 -  square root of hydrogen-included gravitational index
 * <li>GRAVH-3 -  cube root of hydrogen-included gravitational index
 * <li>GRAV-4 -  grav1 for all pairs of atoms (not just bonded pairs)
 * <li>GRAV-5 -  grav2 for all pairs of atoms (not just bonded pairs)
 * <li>GRAV-6 -  grav3 for all pairs of atoms (not just bonded pairs)
 * </ul>
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
 * @author Rajarshi Guha
 * @cdk.created 2004-11-23
 * @cdk.module qsar
 * @cdk.set qsar-descriptors
 * @cdk.dictref qsar-descriptors:gravitationalIndex
 * @cdk.keyword gravitational index
 * @cdk.keyword descriptor
 */
public class GravitationalIndexDescriptor implements IMolecularDescriptor {

    private LoggingTool logger;

    private class pair {
        int x, y;

        public pair() {
            x = 0;
            y = 0;
        }
    }

    public GravitationalIndexDescriptor() {
        logger = new LoggingTool(this);
    }

    public DescriptorSpecification getSpecification() {
        return new DescriptorSpecification(
                "http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#gravitationalIndex",
                this.getClass().getName(),
                "$Id: GravitationalIndexDescriptor.java 8710 2007-08-28 07:51:32Z egonw $",
                "The Chemistry Development Kit");
    }

    ;

    /**
     * Sets the parameters attribute of the GravitationalIndexDescriptor object.
     *
     * @param params The new parameters value
     * @throws CDKException Description of the Exception
     * @see #getParameters
     */
    public void setParameters(Object[] params) throws CDKException {
        // no parameters for this descriptor
    }

    /**
     * Gets the parameters attribute of the GravitationalIndexDescriptor object.
     *
     * @return The parameters value
     * @see #setParameters
     */
    public Object[] getParameters() {
        // no parameters to return
        return (null);
    }

    /**
     * Gets the parameterNames attribute of the GravitationalIndexDescriptor object.
     *
     * @return The parameterNames value
     */
    public String[] getParameterNames() {
        // no param names to return
        return (null);
    }


    /**
     * Gets the parameterType attribute of the GravitationalIndexDescriptor object.
     *
     * @param name Description of the Parameter
     * @return The parameterType value
     */
    public Object getParameterType(String name) {
        return (null);
    }

    /**
     * Calculates the 9 gravitational indices.
     *
     * @param container Parameter is the atom container.
     * @return An ArrayList containing 9 elements in the order described above
     */

    public DescriptorValue calculate(IAtomContainer container) throws CDKException {
        IsotopeFactory factory = null;
        double mass1 = 0;
        double mass2 = 0;
        try {
            factory = IsotopeFactory.getInstance(container.getBuilder());
        } catch (Exception e) {
            logger.debug(e);
        }

        double sum = 0;
        for (int i = 0; i < container.getBondCount(); i++) {
            org.openscience.cdk.interfaces.IBond bond = container.getBond(i);

            if (bond.getAtomCount() != 2) {
                throw new CDKException("GravitationalIndex: Only handles 2 center bonds");
            }

            mass1 = factory.getMajorIsotope(bond.getAtom(0).getSymbol()).getMassNumber();
            mass2 = factory.getMajorIsotope(bond.getAtom(1).getSymbol()).getMassNumber();

            Point3d p1 = bond.getAtom(0).getPoint3d();
            Point3d p2 = bond.getAtom(1).getPoint3d();

            if (p1 == null || p2 == null) {
                throw new CDKException("Molecule must have 3D coordinates");
            }
            double x1 = p1.x;
            double y1 = p1.y;
            double z1 = p1.z;
            double x2 = p2.x;
            double y2 = p2.y;
            double z2 = p2.z;

            double dist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2);
            sum += (mass1 * mass2) / dist;
        }

        // heavy atoms only
        double heavysum = 0;
        for (int i = 0; i < container.getBondCount(); i++) {
            org.openscience.cdk.interfaces.IBond b = container.getBond(i);

            if (b.getAtomCount() != 2) {
                throw new CDKException("GravitationalIndex: Only handles 2 center bonds");
            }

            if (b.getAtom(0).getSymbol().equals("H") ||
                    b.getAtom(1).getSymbol().equals("H")) continue;


            mass1 = factory.getMajorIsotope(b.getAtom(0).getSymbol()).getMassNumber();
            mass2 = factory.getMajorIsotope(b.getAtom(1).getSymbol()).getMassNumber();

            Point3d point0 = b.getAtom(0).getPoint3d();
            Point3d point1 = b.getAtom(1).getPoint3d();
            if (point0 == null || point1 == null) {
                throw new CDKException("The molecule must have 3D coordinates");
            }
            
            double x1 = point0.x;
            double y1 = point0.y;
            double z1 = point0.z;
            double x2 = point1.x;
            double y2 = point1.y;
            double z2 = point1.z;

            double dist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2);
            heavysum += (mass1 * mass2) / dist;
        }

        // all pairs
        Vector x = new Vector();
        for (int i = 0; i < container.getAtomCount(); i++) {
            if (container.getAtom(i).getSymbol().equals("H")) continue;
            else x.add(new Integer(i));
        }
        int npair = x.size() * (x.size() - 1) / 2;
        pair[] p = new pair[npair];
        for (int i = 0; i < npair; i++) p[i] = new pair();
        int pcount = 0;
        for (int i = 0; i < x.size() - 1; i++) {
            for (int j = i + 1; j < x.size(); j++) {
                int present = 0;
                int a = ((Integer) x.get(i)).intValue();
                int b = ((Integer) x.get(j)).intValue();
                for (int k = 0; k < pcount; k++) {
                    if ((p[k].x == a && p[k].y == b) ||
                            (p[k].y == a && p[k].x == b)) present = 1;
                }
                if (present == 1) continue;
                p[pcount].x = a;
                p[pcount].y = b;
                pcount += 1;
            }
        }
        double allheavysum = 0;
        for (int i = 0; i < p.length; i++) {
            int atomNumber1 = p[i].x;
            int atomNumber2 = p[i].y;

            mass1 = factory.getMajorIsotope(container.getAtom(atomNumber1).getSymbol()).getMassNumber();
            mass2 = factory.getMajorIsotope(container.getAtom(atomNumber2).getSymbol()).getMassNumber();

            double x1 = container.getAtom(atomNumber1).getPoint3d().x;
            double y1 = container.getAtom(atomNumber1).getPoint3d().y;
            double z1 = container.getAtom(atomNumber1).getPoint3d().z;
            double x2 = container.getAtom(atomNumber2).getPoint3d().x;
            double y2 = container.getAtom(atomNumber2).getPoint3d().y;
            double z2 = container.getAtom(atomNumber2).getPoint3d().z;

            double dist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2);
            allheavysum += (mass1 * mass2) / dist;
        }


        DoubleArrayResult retval = new DoubleArrayResult(9);
        retval.add(heavysum);
        retval.add(Math.sqrt(heavysum));
        retval.add(Math.pow(heavysum, 1.0 / 3.0));

        retval.add(sum);
        retval.add(Math.sqrt(sum));
        retval.add(Math.pow(sum, 1.0 / 3.0));

        retval.add(allheavysum);
        retval.add(Math.sqrt(allheavysum));
        retval.add(Math.pow(allheavysum, 1.0 / 3.0));

        String[] names = {
                "GRAV-1", "GRAV-2", "GRAV-3",
                "GRAVH-1", "GRAVH-2", "GRAVH-3",
                "GRAV-4", "GRAV-5", "GRAV-6"
        };
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
    

