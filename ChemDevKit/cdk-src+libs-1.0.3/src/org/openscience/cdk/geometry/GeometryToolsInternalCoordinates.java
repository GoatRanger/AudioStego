/*
 *  $RCSfile$
 *  $Author: miguelrojasch $
 *  $Date: 2006-09-27 13:32:31 +0000 (Mi, 27 Sep 2006) $
 *  $Revision: 7069 $
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
package org.openscience.cdk.geometry;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.isomorphism.UniversalIsomorphismTester;
import org.openscience.cdk.isomorphism.mcss.RMap;
import org.openscience.cdk.tools.LoggingTool;

import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * A set of static utility classes for geometric calculations and operations.
 * This class is extensively used, for example, by JChemPaint to edit molecule.
 * All methods in this class change the coordinates of the atoms. Use GeometryTools if you use an external set of coordinates (e. g. rendeirngCoordinates from RendererModel)
 *
 * @author        seb
 * @author        Stefan Kuhn
 * @author        Egon Willighagen
 * @author        Ludovic Petain
 * @author        Christian Hoppe
 * 
 * @cdk.module    standard
 */
public class GeometryToolsInternalCoordinates {

	private static LoggingTool logger = new LoggingTool(GeometryToolsInternalCoordinates.class);


	/**
	 *  Adds an automatically calculated offset to the coordinates of all atoms
	 *  such that all coordinates are positive and the smallest x or y coordinate
	 *  is exactly zero.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atomCon  AtomContainer for which all the atoms are translated to
	 *      positive coordinates
	 */
	public static void translateAllPositive(IAtomContainer atomCon) {
		double minX = Double.MAX_VALUE;
		double
				minY = Double.MAX_VALUE;
		java.util.Iterator atoms = atomCon.atoms();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom.getPoint2d() != null) {
				if (atom.getPoint2d().x < minX) {
					minX = atom.getPoint2d().x;
				}
				if (atom.getPoint2d().y < minY) {
					minY = atom.getPoint2d().y;
				}
			}
		}
		logger.debug("Translating: minx=" + minX + ", minY=" + minY);
		translate2D(atomCon, minX * -1, minY * -1);
	}


	/**
	 *  Translates the given molecule by the given Vector.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atomCon  The molecule to be translated
	 *@param  transX   translation in x direction
	 *@param  transY   translation in y direction
	 */
	public static void translate2D(IAtomContainer atomCon, double transX, double transY) {
		translate2D(atomCon, new Vector2d(transX, transY));
	}


	/**
	 *  Scales a molecule such that it fills a given percentage of a given
	 *  dimension
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atomCon     The molecule to be scaled
	 *@param  areaDim     The dimension to be filled
	 *@param  fillFactor  The percentage of the dimension to be filled
	 */
	public static void scaleMolecule(IAtomContainer atomCon, Dimension areaDim, double fillFactor) {
		Dimension molDim = get2DDimension(atomCon);
		double widthFactor = (double) areaDim.width / (double) molDim.width;
		double heightFactor = (double) areaDim.height / (double) molDim.height;
		double scaleFactor = Math.min(widthFactor, heightFactor) * fillFactor;
		scaleMolecule(atomCon, scaleFactor);
	}


	/**
	 *  Multiplies all the coordinates of the atoms of the given molecule with the
	 *  scalefactor.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atomCon      The molecule to be scaled
	 *@param  scaleFactor  Description of the Parameter
	 */
	public static void scaleMolecule(IAtomContainer atomCon, double scaleFactor) {
		for (int i = 0; i < atomCon.getAtomCount(); i++) {
			if (atomCon.getAtom(i).getPoint2d() != null) {
				atomCon.getAtom(i).getPoint2d().x *= scaleFactor;
				atomCon.getAtom(i).getPoint2d().y *= scaleFactor;
			}
		}
	}


	/**
	 *  Centers the molecule in the given area
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atomCon  molecule to be centered
	 *@param  areaDim  dimension in which the molecule is to be centered
	 */
	public static void center(IAtomContainer atomCon, Dimension areaDim) {
		Dimension molDim = get2DDimension(atomCon);
		int transX = (areaDim.width - molDim.width) / 2;
		int transY = (areaDim.height - molDim.height) / 2;
		translateAllPositive(atomCon);
		translate2D(atomCon, new Vector2d(transX, transY));
	}


	/**
	 *  Translates a molecule from the origin to a new point denoted by a vector.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atomCon  molecule to be translated
	 *@param  vector   dimension that represents the translation vector
	 */
	public static void translate2D(IAtomContainer atomCon, Vector2d vector) {
		java.util.Iterator atoms = atomCon.atoms();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom.getPoint2d() != null) {
				atom.getPoint2d().add(vector);
			} else {
				logger.warn("Could not translate atom in 2D space");
			}
		}
	}


	/**
	 *  Rotates a molecule around a given center by a given angle
	 *
	 *@param  atomCon  The molecule to be rotated
	 *@param  center   A point giving the rotation center
	 *@param  angle    The angle by which to rotate the molecule, in radians
	 */
	public static void rotate(IAtomContainer atomCon, Point2d center, double angle) {
		Point2d p;
		double costheta = Math.cos(angle);
		double sintheta = Math.sin(angle);
		IAtom atom;
		for (int i = 0; i < atomCon.getAtomCount(); i++) {
			atom = atomCon.getAtom(i);
			p = atom.getPoint2d();
			double x = p.x - center.x;
			double y = p.y - center.y;
			p.x = x * costheta - y * sintheta + center.x;
			p.y = x * sintheta + y * costheta + center.y;
		}
	}

    /**
     * Rotates a 3D point about a specified line segment by a specified angle.
     *
     * The code is based on code available <a href="http://astronomy.swin.edu.au/~pbourke/geometry/rotate/source.c">here</a>.
     * Positive angles are anticlockwise looking down the axis towards the origin.
     * Assume right hand coordinate system.
     *
     * @param atom The atom to rotate
     * @param p1  The  first point of the line segment
     * @param p2  The second point of the line segment
     * @param angle  The angle to rotate by (in degrees)
     */
    public static void rotate(IAtom atom, Point3d p1, Point3d p2, double angle) {
        double costheta, sintheta;

        Point3d r = new Point3d();

        r.x = p2.x - p1.x;
        r.y = p2.y - p1.y;
        r.z = p2.z - p1.z;
        normalize(r);


        angle = angle * Math.PI / 180.0;
        costheta = Math.cos(angle);
        sintheta = Math.sin(angle);

        Point3d p = atom.getPoint3d();
        p.x -= p1.x;
        p.y -= p1.y;
        p.z -= p1.z;

        Point3d q = new Point3d(0, 0, 0);
        q.x += (costheta + (1 - costheta) * r.x * r.x) * p.x;
        q.x += ((1 - costheta) * r.x * r.y - r.z * sintheta) * p.y;
        q.x += ((1 - costheta) * r.x * r.z + r.y * sintheta) * p.z;

        q.y += ((1 - costheta) * r.x * r.y + r.z * sintheta) * p.x;
        q.y += (costheta + (1 - costheta) * r.y * r.y) * p.y;
        q.y += ((1 - costheta) * r.y * r.z - r.x * sintheta) * p.z;

        q.z += ((1 - costheta) * r.x * r.z - r.y * sintheta) * p.x;
        q.z += ((1 - costheta) * r.y * r.z + r.x * sintheta) * p.y;
        q.z += (costheta + (1 - costheta) * r.z * r.z) * p.z;

        q.x += p1.x;
        q.y += p1.y;
        q.z += p1.z;

        atom.setPoint3d(q);
    }

    /**
     * Normalizes a point.
     *
     * @param point The point to normalize
     */
    public static void normalize(Point3d point) {
        double sum = Math.sqrt(point.x * point.x + point.y * point.y + point.z * point.z);
        point.x = point.x / sum;
        point.y = point.y / sum;
        point.z = point.z / sum;
    }


	/**
	 *  Returns the java.awt.Dimension of a molecule
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atomCon  of which the dimension should be returned
	 *@return          The java.awt.Dimension of this molecule
	 */
	public static Dimension get2DDimension(IAtomContainer atomCon) {
		double[] minmax = getMinMax(atomCon);
		double maxX = minmax[2];
		double
				maxY = minmax[3];
		double
				minX = minmax[0];
		double
				minY = minmax[1];
		return new Dimension((int) (maxX - minX + 1), (int) (maxY - minY + 1));
	}


	/**
	 *  Returns the minimum and maximum X and Y coordinates of the atoms in the
	 *  AtomContainer. The output is returned as: <pre>
	 *   minmax[0] = minX;
	 *   minmax[1] = minY;
	 *   minmax[2] = maxX;
	 *   minmax[3] = maxY;
	 * </pre>
	 * See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  container  Description of the Parameter
	 *@return            An four int array as defined above.
	 */
	public static double[] getMinMax(IAtomContainer container) {
		double maxX = Double.MIN_VALUE;
		double
				maxY = Double.MIN_VALUE;
		double
				minX = Double.MAX_VALUE;
		double
				minY = Double.MAX_VALUE;
		for (int i = 0; i < container.getAtomCount(); i++) {
			IAtom atom = container.getAtom(i);
			if (atom.getPoint2d() != null) {
				if (atom.getPoint2d().x > maxX) {
					maxX = atom.getPoint2d().x;
				}
				if (atom.getPoint2d().x < minX) {
					minX = atom.getPoint2d().x;
				}
				if (atom.getPoint2d().y > maxY) {
					maxY = atom.getPoint2d().y;
				}
				if (atom.getPoint2d().y < minY) {
					minY = atom.getPoint2d().y;
				}
			}
		}
		double[] minmax = new double[4];
		minmax[0] = minX;
		minmax[1] = minY;
		minmax[2] = maxX;
		minmax[3] = maxY;
		return minmax;
	}


	/**
	 *  Translates a molecule from the origin to a new point denoted by a vector.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atomCon  molecule to be translated
	 *@param  p        Description of the Parameter
	 */
	public static void translate2DCentreOfMassTo(IAtomContainer atomCon, Point2d p) {
		Point2d com = get2DCentreOfMass(atomCon);
		Vector2d translation = new Vector2d(p.x - com.x, p.y - com.y);
		java.util.Iterator atoms = atomCon.atoms();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom.getPoint2d() != null) {
				atom.getPoint2d().add(translation);
			}
		}
	}


	/**
	 *  Calculates the center of the given atoms and returns it as a Point2d
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atoms  The vector of the given atoms
	 *@return        The center of the given atoms as Point2d
	 */
	public static Point2d get2DCenter(IAtom[] atoms) {
		IAtom atom;
		double x = 0;
		double y = 0;
		for (int f = 0; f < atoms.length; f++) {
			atom = atoms[f];
			if (atom.getPoint2d() != null) {
				x += atom.getPoint2d().x;
				y += atom.getPoint2d().y;
			}
		}
		return new Point2d(x / (double) atoms.length, y / (double) atoms.length);
	}

	
	/**
	 *  Calculates the center of the given atoms and returns it as a Point2d
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  atoms  The Iterator of the given atoms
	 *@return        The center of the given atoms as Point2d
	 */
	public static Point2d get2DCenter(java.util.Iterator atoms) {
		IAtom atom;
		double x = 0;
		double y = 0;
		int length = 0;
		while (atoms.hasNext()) {
			atom = (IAtom)atoms.next();
			if (atom.getPoint2d() != null) {
				x += atom.getPoint2d().x;
				y += atom.getPoint2d().y;
			}
			++length;
		}
		return new Point2d(x / (double) length, y / (double) length);
	}
	

	/**
	 *  Returns the geometric center of all the rings in this ringset.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  ringSet  Description of the Parameter
	 *@return          the geometric center of the rings in this ringset
	 */
	public static Point2d get2DCenter(IRingSet ringSet) {
		double centerX = 0;
		double centerY = 0;
		for (int i = 0; i < ringSet.getAtomContainerCount(); i++) {
			Point2d centerPoint = GeometryToolsInternalCoordinates.get2DCenter((org.openscience.cdk.interfaces.IRing)ringSet.getAtomContainer(i));
			centerX += centerPoint.x;
			centerY += centerPoint.y;
		}
        return new Point2d(centerX / ((double) ringSet.getAtomContainerCount()), centerY / ((double) ringSet.getAtomContainerCount()));
	}


	/**
	 *  Calculates the center of mass for the <code>Atom</code>s in the
	 *  AtomContainer for the 2D coordinates.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  ac      AtomContainer for which the center of mass is calculated
	 *@return         Description of the Return Value
	 *@cdk.keyword    center of mass
	 */
	public static Point2d get2DCentreOfMass(IAtomContainer ac) {
		double x = 0.0;
		double y = 0.0;

		double totalmass = 0.0;

		java.util.Iterator atoms = ac.atoms();
		while (atoms.hasNext()) {
			IAtom a = (IAtom) atoms.next();
			double mass = a.getExactMass();
			totalmass += mass;
			x += mass * a.getPoint2d().x;
			y += mass * a.getPoint2d().y;
		}

		return new Point2d(x / totalmass, y / totalmass);
	}


	/**
	 *  Returns the geometric center of all the atoms in the atomContainer.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  container  Description of the Parameter
	 *@return            the geometric center of the atoms in this atomContainer
	 */
	public static Point2d get2DCenter(IAtomContainer container) {
		double centerX = 0;
		double centerY = 0;
		double counter = 0;
		java.util.Iterator atoms = container.atoms();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom.getPoint2d() != null) {
				centerX += atom.getPoint2d().x;
				centerY += atom.getPoint2d().y;
				counter++;
			}
		}
        return new Point2d(centerX / (counter), centerY / (counter));
	}

	
	/**
	 *  Translates the geometric 2DCenter of the given
	 *  AtomContainer container to the specified Point2d p.
	 *
	 *@param  container  AtomContainer which should be translated.
	 *@param  p          New Location of the geometric 2D Center.
	 *@see #get2DCenter
	 *@see #translate2DCentreOfMassTo
	 */
	public static void translate2DCenterTo(IAtomContainer container, Point2d p) {
		Point2d com = get2DCenter(container);
		Vector2d translation = new Vector2d(p.x - com.x, p.y - com.y);
		java.util.Iterator atoms = container.atoms();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom.getPoint2d() != null) {
				atom.getPoint2d().add(translation);
			}
		}
	}


	/**
	 *  Calculates the center of mass for the <code>Atom</code>s in the
	 *  AtomContainer for the 2D coordinates.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  ac      AtomContainer for which the center of mass is calculated
	 *@return         Description of the Return Value
	 *@cdk.keyword    center of mass
     * @cdk.dictref   blue-obelisk:calculate3DCenterOfMass
	 */
	public static Point3d get3DCentreOfMass(IAtomContainer ac) {
		double x = 0.0;
		double y = 0.0;
		double z = 0.0;

		double totalmass = 0.0;

		java.util.Iterator atoms = ac.atoms();
		while (atoms.hasNext()) {
			IAtom a = (IAtom)atoms.next();
			double mass = a.getExactMass();
			totalmass += mass;
			x += mass * a.getPoint3d().x;
			y += mass * a.getPoint3d().y;
			z += mass * a.getPoint3d().z;
		}

		return new Point3d(x / totalmass, y / totalmass, z / totalmass);
	}


	/**
	 *  Returns the geometric center of all the atoms in this atomContainer.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  ac  Description of the Parameter
	 *@return     the geometric center of the atoms in this atomContainer
	 */
	public static Point3d get3DCenter(IAtomContainer ac) {
		double centerX = 0;
		double centerY = 0;
		double centerZ = 0;
		double counter = 0;
		java.util.Iterator atoms = ac.atoms();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom.getPoint3d() != null) {
				centerX += atom.getPoint3d().x;
				centerY += atom.getPoint3d().y;
				centerZ += atom.getPoint3d().z;
				counter++;
			}
		}
        return new Point3d(centerX / (counter), centerY / (counter), centerZ / (counter));
	}


	/**
	 *  Gets the angle attribute of the GeometryTools class
	 *
	 *@param  xDiff  Description of the Parameter
	 *@param  yDiff  Description of the Parameter
	 *@return        The angle value
	 */
	public static double getAngle(double xDiff, double yDiff) {
		double angle = 0;
//		logger.debug("getAngle->xDiff: " + xDiff);
//		logger.debug("getAngle->yDiff: " + yDiff);
		if (xDiff >= 0 && yDiff >= 0) {
			angle = Math.atan(yDiff / xDiff);
		} else if (xDiff < 0 && yDiff >= 0) {
			angle = Math.PI + Math.atan(yDiff / xDiff);
		} else if (xDiff < 0 && yDiff < 0) {
			angle = Math.PI + Math.atan(yDiff / xDiff);
		} else if (xDiff >= 0 && yDiff < 0) {
			angle = 2 * Math.PI + Math.atan(yDiff / xDiff);
		}
		return angle;
	}


	/**
	 *  Gets the coordinates of two points (that represent a bond) and calculates
	 *  for each the coordinates of two new points that have the given distance
	 *  vertical to the bond.
	 *
	 *@param  coords  The coordinates of the two given points of the bond like this
	 *      [point1x, point1y, point2x, point2y]
	 *@param  dist    The vertical distance between the given points and those to
	 *      be calculated
	 *@return         The coordinates of the calculated four points
	 */
	public static int[] distanceCalculator(int[] coords, double dist) {
		double angle;
		if ((coords[2] - coords[0]) == 0) {
			angle = Math.PI / 2;
		} else {
			angle = Math.atan(((double) coords[3] - (double) coords[1]) / ((double) coords[2] - (double) coords[0]));
		}
		int begin1X = (int) (Math.cos(angle + Math.PI / 2) * dist + coords[0]);
		int begin1Y = (int) (Math.sin(angle + Math.PI / 2) * dist + coords[1]);
		int begin2X = (int) (Math.cos(angle - Math.PI / 2) * dist + coords[0]);
		int begin2Y = (int) (Math.sin(angle - Math.PI / 2) * dist + coords[1]);
		int end1X = (int) (Math.cos(angle - Math.PI / 2) * dist + coords[2]);
		int end1Y = (int) (Math.sin(angle - Math.PI / 2) * dist + coords[3]);
		int end2X = (int) (Math.cos(angle + Math.PI / 2) * dist + coords[2]);
		int end2Y = (int) (Math.sin(angle + Math.PI / 2) * dist + coords[3]);

        return new int[]{begin1X, begin1Y, begin2X, begin2Y, end1X, end1Y, end2X, end2Y};
	}


	/**
	 *  Writes the coordinates of the atoms participating the given bond into an
	 *  array.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  bond  The given bond
	 *@return       The array with the coordinates
	 */
	public static int[] getBondCoordinates(IBond bond) {
		if (bond.getAtom(0).getPoint2d() == null ||
				bond.getAtom(1).getPoint2d() == null) {
			logger.error("getBondCoordinates() called on Bond without 2D coordinates!");
			return new int[0];
		}
		int beginX = (int) bond.getAtom(0).getPoint2d().x;
		int endX = (int) bond.getAtom(1).getPoint2d().x;
		int beginY = (int) bond.getAtom(0).getPoint2d().y;
		int endY = (int) bond.getAtom(1).getPoint2d().y;
        return new int[]{beginX, beginY, endX, endY};
	}


	/**
	 *  Returns the atom of the given molecule that is closest to the given
	 *  coordinates.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  xPosition  The x coordinate
	 *@param  yPosition  The y coordinate
	 *@param  atomCon    The molecule that is searched for the closest atom
	 *@return            The atom that is closest to the given coordinates
	 */
	public static IAtom getClosestAtom(int xPosition, int yPosition, IAtomContainer atomCon) {
		IAtom closestAtom = null;
		IAtom currentAtom;
		double smallestMouseDistance = -1;
		double mouseDistance;
		double atomX;
		double atomY;
		for (int i = 0; i < atomCon.getAtomCount(); i++) {
			currentAtom = atomCon.getAtom(i);
			atomX = currentAtom.getPoint2d().x;
			atomY = currentAtom.getPoint2d().y;
			mouseDistance = Math.sqrt(Math.pow(atomX - xPosition, 2) + Math.pow(atomY - yPosition, 2));
			if (mouseDistance < smallestMouseDistance || smallestMouseDistance == -1) {
				smallestMouseDistance = mouseDistance;
				closestAtom = currentAtom;
			}
		}
		return closestAtom;
	}


	/**
	 *  Returns the bond of the given molecule that is closest to the given
	 *  coordinates.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  xPosition  The x coordinate
	 *@param  yPosition  The y coordinate
	 *@param  atomCon    The molecule that is searched for the closest bond
	 *@return            The bond that is closest to the given coordinates
	 */
	public static IBond getClosestBond(int xPosition, int yPosition, IAtomContainer atomCon) {
		Point2d bondCenter;
		IBond closestBond = null;

		double smallestMouseDistance = -1;
		double mouseDistance;
        Iterator bonds = atomCon.bonds();
        while (bonds.hasNext()) {
            IBond currentBond =  (IBond) bonds.next();
			bondCenter = get2DCenter(currentBond.atoms());
			mouseDistance = Math.sqrt(Math.pow(bondCenter.x - xPosition, 2) + Math.pow(bondCenter.y - yPosition, 2));
			if (mouseDistance < smallestMouseDistance || smallestMouseDistance == -1) {
				smallestMouseDistance = mouseDistance;
				closestBond = currentBond;
			}
		}
		return closestBond;
	}


	/**
	 *  Sorts a Vector of atoms such that the 2D distances of the atom locations
	 *  from a given point are smallest for the first atoms in the vector
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  point  The point from which the distances to the atoms are measured
	 *@param  atoms  The atoms for which the distances to point are measured
	 */
	public static void sortBy2DDistance(IAtom[] atoms, Point2d point) {
		double distance1;
		double distance2;
		IAtom atom1;
		IAtom atom2;
		boolean doneSomething;
		do {
			doneSomething = false;
			for (int f = 0; f < atoms.length - 1; f++) {
				atom1 = atoms[f];
				atom2 = atoms[f + 1];
				distance1 = point.distance(atom1.getPoint2d());
				distance2 = point.distance(atom2.getPoint2d());
				if (distance2 < distance1) {
					atoms[f] = atom2;
					atoms[f + 1] = atom1;
					doneSomething = true;
				}
			}
		} while (doneSomething);
	}


	/**
	 *  Determines the scale factor for displaying a structure loaded from disk in
	 *  a frame. An average of all bond length values is produced and a scale
	 *  factor is determined which would scale the given molecule such that its
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  ac          The AtomContainer for which the ScaleFactor is to be
	 *      calculated
	 *@param  bondLength  The target bond length
	 *@return             The ScaleFactor with which the AtomContainer must be
	 *      scaled to have the target bond length
	 */

	public static double getScaleFactor(IAtomContainer ac, double bondLength) {
		double currentAverageBondLength = getBondLengthAverage(ac);
    if(currentAverageBondLength==0 || Double.isNaN(currentAverageBondLength))
      return 1;
		return bondLength / currentAverageBondLength;
	}


	/**
	 *  An average of all 2D bond length values is produced. Bonds which have
	 *  Atom's with no coordinates are disregarded.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  ac  The AtomContainer for which the average bond length is to be
	 *      calculated
	 *@return     the average bond length
	 */
	public static double getBondLengthAverage(IAtomContainer ac) {
		double bondLengthSum = 0;
        Iterator bonds = ac.bonds();
        int bondCounter = 0;
        while (bonds.hasNext()) {
            IBond bond = (IBond) bonds.next();
			org.openscience.cdk.interfaces.IAtom atom1 = bond.getAtom(0);
			org.openscience.cdk.interfaces.IAtom atom2 = bond.getAtom(1);
			if (atom1.getPoint2d() != null &&
					atom2.getPoint2d() != null) {
				bondCounter++;
				bondLengthSum += getLength2D(bond);
			}
		}
		return bondLengthSum / bondCounter;
	}


	/**
	 *  Returns the geometric length of this bond in 2D space.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  bond  Description of the Parameter
	 *@return       The geometric length of this bond
	 */
	public static double getLength2D(IBond bond) {
		if (bond.getAtom(0) == null ||
				bond.getAtom(1) == null) {
			return 0.0;
		}
		Point2d p1 = bond.getAtom(0).getPoint2d();
		Point2d p2 = bond.getAtom(1).getPoint2d();
		if (p1 == null || p2 == null) {
			return 0.0;
		}
		return p1.distance(p2);
	}


	/**
	 *  Determines if this AtomContainer contains 2D coordinates.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  m  Description of the Parameter
	 *@return    boolean indication that 2D coordinates are available
	 */
	public static boolean has2DCoordinates(IAtomContainer m) {
		return has2DCoordinatesNew(m)>0;
	}


	/**
	 *  Determines if this AtomContainer contains 2D coordinates for some or all molecules.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  m  Description of the Parameter
	 *@return    0 no 2d, 1=some, 2= for each atom
	 */
	public static int has2DCoordinatesNew(IAtomContainer ac) {
		boolean no2d=false;
		boolean with2d=false;
		java.util.Iterator atoms = ac.atoms();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom.getPoint2d() == null) {
				no2d=true;
			}else{
				with2d=true;
			}
		}
		if(!no2d && with2d){
			return 2;
		} else if(no2d && with2d){
			return 1;
		} else{
			return 0;
		}
	}


	/**
	 *  Determines if this Atom contains 2D coordinates.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  a  Description of the Parameter
	 *@return    boolean indication that 2D coordinates are available
	 */
	public static boolean has2DCoordinates(IAtom a) {
		return (a.getPoint2d() != null);
	}


	/**
	 *  Determines if this Bond contains 2D coordinates.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  b  Description of the Parameter
	 *@return    boolean indication that 2D coordinates are available
	 */
	public static boolean has2DCoordinates(IBond b) {
		java.util.Iterator atoms = b.atoms();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom.getPoint2d() == null) {
				return false;
			}
		}
		return true;
	}


	/**
	 *  Determines if this model contains 3D coordinates
	 *
	 *@param  m  Description of the Parameter
	 *@return    boolean indication that 3D coordinates are available
	 */
	public static boolean has3DCoordinates(IAtomContainer ac) {
		boolean hasinfo = true;
		java.util.Iterator atoms = ac.atoms();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom.getPoint3d() == null) {
				return false;
			}
		}
		return hasinfo;
	}


	/**
	 *  Determines the normalized vector orthogonal on the vector p1->p2.
	 *
	 *@param  p1  Description of the Parameter
	 *@param  p2  Description of the Parameter
	 *@return     Description of the Return Value
	 */
	public static Vector2d calculatePerpendicularUnitVector(Point2d p1, Point2d p2) {
		Vector2d v = new Vector2d();
		v.sub(p2, p1);
		v.normalize();

		// Return the perpendicular vector
		return new Vector2d(-1.0 * v.y, v.x);
	}


	/**
	 *  Calculates the normalization factor in order to get an average bond length
	 *  of 1.5. It takes only into account Bond's with two atoms.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  container  Description of the Parameter
	 *@return            The normalizationFactor value
	 */
	public static double getNormalizationFactor(IAtomContainer container) {
		double bondlength = 0.0;
		double ratio;
		/*
		 *  Desired bond length for storing structures in MDL mol files
		 *  This should probably be set externally (from system wide settings)
		 */
		double desiredBondLength = 1.5;
		// loop over all bonds and determine the mean bond distance
		int counter = 0;
        Iterator bonds = container.bonds();
        while (bonds.hasNext()) {
            IBond bond = (IBond) bonds.next();
			// only consider two atom bonds into account
			if (bond.getAtomCount() == 2) {
				counter++;
				org.openscience.cdk.interfaces.IAtom atom1 = bond.getAtom(0);
				org.openscience.cdk.interfaces.IAtom atom2 = bond.getAtom(1);
				bondlength += Math.sqrt(Math.pow(atom1.getPoint2d().x - atom2.getPoint2d().x, 2) +
						Math.pow(atom1.getPoint2d().y - atom2.getPoint2d().y, 2));
			}
		}
		bondlength = bondlength / counter;
		ratio = desiredBondLength / bondlength;
		return ratio;
	}


	/**
	 *  Determines the best alignment for the label of an atom in 2D space. It
	 *  returns 1 if left aligned, and -1 if right aligned.
	 *  See comment for center(IAtomContainer atomCon, Dimension areaDim, HashMap renderingCoordinates) for details on coordinate sets
	 *
	 *@param  container  Description of the Parameter
	 *@param  atom       Description of the Parameter
	 *@return            The bestAlignmentForLabel value
	 */
	public static int getBestAlignmentForLabel(IAtomContainer container, IAtom atom) {
		java.util.Iterator connectedAtoms = container.getConnectedAtomsList(atom).iterator();
		int overallDiffX = 0;
		while (connectedAtoms.hasNext()) {
			IAtom connectedAtom = (IAtom)connectedAtoms.next();
			overallDiffX = overallDiffX + (int) (connectedAtom.getPoint2d().x - atom.getPoint2d().x);
		}
		if (overallDiffX <= 0) {
			return 1;
		} else {
			return -1;
		}
	}


	/**
	 *  Returns the atoms which are closes to an atom in an AtomContainer by
	 *  distance in 3d.
	 *
	 *@param  ac                The AtomContainer to examine
	 *@param  a                 the atom to start from
	 *@param  max               the number of neighbours to return
	 *@return                   the average bond length
	 *@exception  CDKException  Description of the Exception
	 */
	public static Vector findClosestInSpace(IAtomContainer ac, IAtom a, int max) throws CDKException {
		java.util.Iterator atoms = ac.atoms();
		Point3d originalPoint = a.getPoint3d();
		if (originalPoint == null) {
			throw new CDKException("No point3d, but findClosestInSpace is working on point3ds");
		}
		Map hm = new TreeMap();
		while (atoms.hasNext()) {
			IAtom atom = (IAtom)atoms.next();
			if (atom != a) {
				if (atom.getPoint3d() == null) {
					throw new CDKException("No point3d, but findClosestInSpace is working on point3ds");
				}
				double distance = atom.getPoint3d().distance(originalPoint);
				hm.put(new Double(distance), atom);
			}
		}
		Set ks = hm.keySet();
		Iterator it = ks.iterator();
		Vector returnValue = new Vector();
		int i = 0;
		while (it.hasNext() && i < max) {
			returnValue.add(hm.get(it.next()));
			i++;
		}
		return (returnValue);
	}
	/**
	 *  Returns a Map with the AtomNumbers, the first number corresponds to the first (or the largest
	 *  AtomContainer) atomcontainer. It is recommend to sort the atomContainer due to their number of atoms before
	 *  calling this function.
	 *  
	 *  The molecules needs to be aligned before! (coordinates are needed)
	 *
	 *@param  firstAtomContainer                the (largest) first aligned AtomContainer which is the reference
	 *@param  secondAtomContainer               the second aligned AtomContainer
	 *@param  searchRadius               		the radius of space search from each atom
	 *@return                   				a Map of the mapped atoms
	 *@exception  CDKException  Description of the Exception
	 */
	public static Map mapAtomsOfAlignedStructures(IAtomContainer firstAtomContainer, IAtomContainer secondAtomContainer, double searchRadius, Map mappedAtoms)throws CDKException {
		//to return the mapping setProperty("MappedAtom",AtomNumber)
		//logger.debug("**** MAP ATOMS ****");
		getLargestAtomContainer(firstAtomContainer,secondAtomContainer);
		double[][] distanceMatrix=new double[firstAtomContainer.getAtomCount()][secondAtomContainer.getAtomCount()];
		for (int i=0;i<firstAtomContainer.getAtomCount();i++){
			Point3d firstAtomPoint=firstAtomContainer.getAtom(i).getPoint3d();
			//logger.debug("Closest atoms of "+firstAtomContainer.getAtoms()[i].getSymbol()+" :");
			for (int j=0;j<secondAtomContainer.getAtomCount();j++){
				distanceMatrix[i][j]=firstAtomPoint.distance(secondAtomContainer.getAtom(j).getPoint3d());
				//logger.debug("Distance "+i+" "+j+":"+distanceMatrix[i][j]);
			}
			//logger.debug(" Atoms from the secondAtomContainer");
		}
		
		//logger.debug();
		//logger.debug("\t");
		//for (int j=0;j<secondAtomContainer.getAtomCount();j++){
			//logger.debug(j+" "+secondAtomContainer.getAtomAt(j).getSymbol()+"\t");
		//}
		//DEBUGG
		/*double tmp=0;
		for(int i=0;i<firstAtomContainer.getAtomCount();i++){
			//logger.debug(i+" "+firstAtomContainer.getAtomAt(i).getSymbol()+"\t");
			for (int j=0;j<secondAtomContainer.getAtomCount();j++){
				tmp=Math.floor(distanceMatrix[i][j]*10);
				//logger.debug(tmp/10+"\t");
			}			
		}*/
		
		double minimumDistance;
        for(int i=0;i<firstAtomContainer.getAtomCount();i++){
            minimumDistance=searchRadius;
            for (int j=0;j<secondAtomContainer.getAtomCount();j++){
                if(distanceMatrix[i][j]< searchRadius && distanceMatrix[i][j]< minimumDistance){
                    //logger.debug("Distance OK "+i+" "+j+":"+distanceMatrix[i][j]+" AtomCheck:"+checkAtomMapping(firstAtomContainer,secondAtomContainer, i, j));
                    //check atom properties
                    if (checkAtomMapping(firstAtomContainer,secondAtomContainer, i, j)){
                        minimumDistance=distanceMatrix[i][j];
                        mappedAtoms.put(new Integer(firstAtomContainer.getAtomNumber(firstAtomContainer.getAtom(i))),new Integer(secondAtomContainer.getAtomNumber(secondAtomContainer.getAtom(j))));
                        //firstAtomContainer.getAtomAt(i).setProperty("MappedAtom",new Integer(secondAtomContainer.getAtomNumber(secondAtomContainer.getAtomAt(j))));
                        //logger.debug("#:"+countMappedAtoms+" Atom:"+i+" is mapped to Atom"+j);
                        //logger.debug(firstAtomContainer.getConnectedAtoms(firstAtomContainer.getAtomAt(i)).length);
                    }
                }
            }
        }
		return mappedAtoms;
	}

    /**
     * Returns a Map with the AtomNumbers, the first number corresponds to the first (or the largest
     * AtomContainer) atomContainer.
     * <p/>
     * Only for similar and aligned molecules with coordinates!
     *
     * @param firstAtomContainer  the (largest) first aligned AtomContainer which is the reference
     * @param secondAtomContainer the second aligned AtomContainer
     * @return a Map of the mapped atoms
     * @throws CDKException if there is an error in the UniversalIsomorphismTester
     */
    public static Map mapAtomsOfAlignedStructures(IAtomContainer firstAtomContainer, IAtomContainer secondAtomContainer, Map mappedAtoms) throws CDKException {
        //logger.debug("**** GT MAP ATOMS ****");
        //Map atoms onto each other
        if (firstAtomContainer.getAtomCount() < 1 & secondAtomContainer.getAtomCount() < 1) {
            return mappedAtoms;
        }
        RMap map;
        org.openscience.cdk.interfaces.IAtom atom1;
        org.openscience.cdk.interfaces.IAtom atom2;
        List list;
        try {
            list = UniversalIsomorphismTester.getSubgraphAtomsMap(firstAtomContainer, secondAtomContainer);
            //logger.debug("ListSize:"+list.size());
            for (int i = 0; i < list.size(); i++) {
                map = (RMap) list.get(i);
                atom1 = firstAtomContainer.getAtom(map.getId1());
                atom2 = secondAtomContainer.getAtom(map.getId2());
                if (checkAtomMapping(firstAtomContainer, secondAtomContainer, firstAtomContainer.getAtomNumber(atom1), secondAtomContainer.getAtomNumber(atom2)))
                {
                    mappedAtoms.put(new Integer(firstAtomContainer.getAtomNumber(atom1)), new Integer(secondAtomContainer.getAtomNumber(atom2)));
                    //logger.debug("#:"+countMappedAtoms+" Atom:"+firstAtomContainer.getAtomNumber(atom1)+" is mapped to Atom:"+secondAtomContainer.getAtomNumber(atom2));
                } else {
                    logger.error("Error: Atoms are not similar !!");
                }
            }
        } catch (CDKException e) {
            throw new CDKException("Error in UniversalIsomorphismTester due to:" + e.toString());
        }
        return mappedAtoms;
    }


    private static void getLargestAtomContainer(IAtomContainer firstAC, IAtomContainer secondAC) {
        if (firstAC.getAtomCount() < secondAC.getAtomCount()){
            IAtomContainer tmp;
            try {
                tmp = (IAtomContainer) firstAC.clone();
                firstAC=(IAtomContainer)secondAC.clone();
                secondAC=(IAtomContainer)tmp.clone();
            } catch (CloneNotSupportedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
	
	private static boolean checkAtomMapping(IAtomContainer firstAC, IAtomContainer secondAC, int posFirstAtom, int posSecondAtom){
		IAtom firstAtom=firstAC.getAtom(posFirstAtom);
		IAtom secondAtom=secondAC.getAtom(posSecondAtom);
		if (firstAtom.getSymbol().equals(secondAtom.getSymbol()) && firstAC.getConnectedAtomsList(firstAtom).size() == secondAC.getConnectedAtomsList(secondAtom).size() &&
				firstAtom.getBondOrderSum() == secondAtom.getBondOrderSum() &&
				firstAtom.getMaxBondOrder() == secondAtom.getMaxBondOrder() 
		        ){
			return true;
		}else {
			return false;
		}
	}
	
	private static IAtomContainer setVisitedFlagsToFalse(IAtomContainer atomContainer) {
        for (int i=0;i<atomContainer.getAtomCount();i++){
            atomContainer.getAtom(i).setFlag(CDKConstants.VISITED, false);
        }
        return atomContainer;
    }
	
	/**
	 *  Return the RMSD of bonds length between the 2 aligned molecules.
	 *
	 *@param  firstAtomContainer                the (largest) first aligned AtomContainer which is the reference
	 *@param  secondAtomContainer               the second aligned AtomContainer
	 *@param  mappedAtoms             			Map: a Map of the mapped atoms
	 *@param  Coords3d            			    boolean: true if moecules has 3D coords, false if molecules has 2D coords
	 *@return                   				double: all the RMSD of bonds length
	 *
	 **/
	public static double getBondLengthRMSD(IAtomContainer firstAtomContainer, IAtomContainer secondAtomContainer,Map mappedAtoms, boolean Coords3d) {
        //logger.debug("**** GT getBondLengthRMSD ****");
        Iterator firstAtoms=mappedAtoms.keySet().iterator();
        IAtom centerAtomFirstMolecule;
        IAtom centerAtomSecondMolecule;
        java.util.List connectedAtoms;
        double sum=0;
        double n=0;
        double distance1=0;
        double distance2=0;
        setVisitedFlagsToFalse(firstAtomContainer);
        setVisitedFlagsToFalse(secondAtomContainer);
        while(firstAtoms.hasNext()){
            centerAtomFirstMolecule=firstAtomContainer.getAtom(((Integer)firstAtoms.next()).intValue());
            centerAtomFirstMolecule.setFlag(CDKConstants.VISITED, true);
            centerAtomSecondMolecule=secondAtomContainer.getAtom(((Integer)mappedAtoms.get(new Integer(firstAtomContainer.getAtomNumber(centerAtomFirstMolecule)))).intValue());
            connectedAtoms=firstAtomContainer.getConnectedAtomsList(centerAtomFirstMolecule);
            for (int i=0;i<connectedAtoms.size();i++){
            	IAtom conAtom = (IAtom)connectedAtoms.get(i);
                //this step is built to know if the program has already calculate a bond length (so as not to have duplicate values)
                if(!conAtom.getFlag(CDKConstants.VISITED)){
                    if (Coords3d){
                        distance1=((Point3d)centerAtomFirstMolecule.getPoint3d()).distance(conAtom.getPoint3d());
                        distance2=((Point3d)centerAtomSecondMolecule.getPoint3d()).distance(secondAtomContainer.getAtom(((Integer)mappedAtoms.get(new Integer(firstAtomContainer.getAtomNumber(conAtom)))).intValue()).getPoint3d());
                        sum=sum+Math.pow((distance1-distance2),2);
                        n++;
                    }else{
                        distance1=((Point2d)centerAtomFirstMolecule.getPoint2d()).distance(conAtom.getPoint2d());
                        distance2=((Point2d)centerAtomSecondMolecule.getPoint2d()).distance(secondAtomContainer.getAtom(((Integer)mappedAtoms.get(new Integer(firstAtomContainer.getAtomNumber(conAtom)))).intValue()).getPoint2d());
                        sum=sum+Math.pow((distance1-distance2),2);
                        n++;
                    }
                }
            }
        }
        setVisitedFlagsToFalse(firstAtomContainer);
        setVisitedFlagsToFalse(secondAtomContainer);
        return Math.sqrt(sum/n);
    }
	/**
	 *  Return the variation of each angle value between the 2 aligned molecules.
	 *
	 *@param  firstAtomContainer                the (largest) first aligned AtomContainer which is the reference
	 *@param  secondAtomContainer               the second aligned AtomContainer
	 *@param  mappedAtoms             			Map: a Map of the mapped atoms
	 *@return                   				double: the value of the RMSD
	 *
	 **/
	public static double getAngleRMSD(IAtomContainer firstAtomContainer, IAtomContainer secondAtomContainer, Map mappedAtoms) {
        //logger.debug("**** GT getAngleRMSD ****");
        Iterator firstAtoms=mappedAtoms.keySet().iterator();
        //logger.debug("mappedAtoms:"+mappedAtoms.toString());
        IAtom firstAtomfirstAC;
        IAtom centerAtomfirstAC;
        IAtom firstAtomsecondAC;
        IAtom secondAtomsecondAC;
        IAtom centerAtomsecondAC;
        double angleFirstMolecule;
        double angleSecondMolecule;
        double sum=0;
        double n=0;
        while(firstAtoms.hasNext()){
            int firstAtomNumber=((Integer)firstAtoms.next()).intValue();
            centerAtomfirstAC=firstAtomContainer.getAtom(firstAtomNumber);
            java.util.List connectedAtoms=firstAtomContainer.getConnectedAtomsList(centerAtomfirstAC);
            if (connectedAtoms.size() >1){
                //logger.debug("If "+centerAtomfirstAC.getSymbol()+" is the center atom :");
                for (int i=0; i < connectedAtoms.size()-1;i++){
                    firstAtomfirstAC=(IAtom)connectedAtoms.get(i);
                    for (int j=i+1; j < connectedAtoms.size();j++){
                        angleFirstMolecule=getAngle(centerAtomfirstAC,firstAtomfirstAC,(IAtom)connectedAtoms.get(j));
                        centerAtomsecondAC=secondAtomContainer.getAtom(((Integer)mappedAtoms.get(new Integer(firstAtomContainer.getAtomNumber(centerAtomfirstAC)))).intValue());
                        firstAtomsecondAC=secondAtomContainer.getAtom(((Integer)mappedAtoms.get(new Integer(firstAtomContainer.getAtomNumber(firstAtomfirstAC)))).intValue());
                        secondAtomsecondAC=secondAtomContainer.getAtom(((Integer)mappedAtoms.get(new Integer(firstAtomContainer.getAtomNumber((IAtom)connectedAtoms.get(j))))).intValue());
                        angleSecondMolecule=getAngle(centerAtomsecondAC,firstAtomsecondAC,secondAtomsecondAC);
                        sum=sum+Math.pow(angleFirstMolecule-angleSecondMolecule,2);
                        n++;
                        //logger.debug("Error for the "+firstAtomfirstAC.getSymbol().toLowerCase()+"-"+centerAtomfirstAC.getSymbol()+"-"+connectedAtoms[j].getSymbol().toLowerCase()+" Angle :"+deltaAngle+" degrees");
                    }
                }
            }//if
        }
        return Math.sqrt(sum/n);
    }
	
	private static double getAngle(IAtom atom1, IAtom atom2, IAtom atom3){
		
		Vector3d centerAtom = new Vector3d();
		centerAtom.x=atom1.getPoint3d().x;
		centerAtom.y=atom1.getPoint3d().y;
		centerAtom.z=atom1.getPoint3d().z;
		Vector3d firstAtom = new Vector3d();
		Vector3d secondAtom = new Vector3d();
			
		firstAtom.x=atom2.getPoint3d().x;
		firstAtom.y=atom2.getPoint3d().y;
		firstAtom.z=atom2.getPoint3d().z;
				
		secondAtom.x=atom3.getPoint3d().x;
		secondAtom.y=atom3.getPoint3d().y;
		secondAtom.z=atom3.getPoint3d().z;
				
		firstAtom.sub(centerAtom);
		secondAtom.sub(centerAtom);
				
		return firstAtom.angle(secondAtom);
	}
	
	/**
	 *  Return the RMSD between the 2 aligned molecules.
	 *
	 *@param  firstAtomContainer                the (largest) first aligned AtomContainer which is the reference
	 *@param  secondAtomContainer               the second aligned AtomContainer
	 *@param  mappedAtoms             			Map: a Map of the mapped atoms
	 *@param  Coords3d            			    boolean: true if moecules has 3D coords, false if molecules has 2D coords
	 *@return                   				double: the value of the RMSD 
	 *@exception  CDKException
	 *
	 **/
	public static double getAllAtomRMSD(IAtomContainer firstAtomContainer, IAtomContainer secondAtomContainer, Map mappedAtoms, boolean Coords3d)throws CDKException {
		//logger.debug("**** GT getAllAtomRMSD ****");
		double sum=0;
		double RMSD;
		Iterator firstAtoms=mappedAtoms.keySet().iterator();
		int firstAtomNumber;
		int secondAtomNumber;
		int n=0;
		while(firstAtoms.hasNext()){
			firstAtomNumber=((Integer)firstAtoms.next()).intValue();
			try{
				secondAtomNumber=((Integer)mappedAtoms.get(new Integer(firstAtomNumber))).intValue();
				IAtom firstAtom=firstAtomContainer.getAtom(firstAtomNumber);
				if (Coords3d){
					sum=sum+Math.pow(firstAtom.getPoint3d().distance(secondAtomContainer.getAtom(secondAtomNumber).getPoint3d()),2);
					n++;
				}else{
					sum=sum+Math.pow(firstAtom.getPoint2d().distance(secondAtomContainer.getAtom(secondAtomNumber).getPoint2d()),2);
					n++;
				}
			}catch (Exception ex){
                throw new CDKException(ex.toString());
            }
		}
		RMSD=Math.sqrt(sum/n);
		return RMSD;
	}
	
	/**
	 *  Return the RMSD of the heavy atoms between the 2 aligned molecules.
	 *
	 *@param  firstAtomContainer                the (largest) first aligned AtomContainer which is the reference
	 *@param  secondAtomContainer               the second aligned AtomContainer
	 *@param  mappedAtoms             			Map: a Map of the mapped atoms
	 *@param hetAtomOnly                        boolean: true if only hetero atoms should be considered
	 *@param  Coords3d            			    boolean: true if moecules has 3D coords, false if molecules has 2D coords
	 *@return                   				double: the value of the RMSD 
	 *@exception  CDK
	 *
	 **/
	public static double getHeavyAtomRMSD(IAtomContainer firstAtomContainer, IAtomContainer secondAtomContainer, Map mappedAtoms, boolean hetAtomOnly ,boolean Coords3d)throws CDKException {
		//logger.debug("**** GT getAllAtomRMSD ****");
		double sum=0;
		double RMSD=0;
		Iterator firstAtoms=mappedAtoms.keySet().iterator();
		int firstAtomNumber=0;
		int secondAtomNumber=0;
		int n=0;
		while(firstAtoms.hasNext()){
			firstAtomNumber=((Integer)firstAtoms.next()).intValue();
			try{
				secondAtomNumber=((Integer)mappedAtoms.get(new Integer(firstAtomNumber))).intValue();
				IAtom firstAtom=firstAtomContainer.getAtom(firstAtomNumber);
				if (hetAtomOnly){
					if (!firstAtom.getSymbol().equals("H") && !firstAtom.getSymbol().equals("C")){
						if (Coords3d){
							sum=sum+Math.pow(((Point3d)firstAtom.getPoint3d()).distance(secondAtomContainer.getAtom(secondAtomNumber).getPoint3d()),2);
							n++;
						}else{
							sum=sum+Math.pow(((Point2d)firstAtom.getPoint2d()).distance(secondAtomContainer.getAtom(secondAtomNumber).getPoint2d()),2);
							n++;
						}
					}
				}else{
					if (!firstAtom.getSymbol().equals("H")){
						if (Coords3d){
							sum=sum+Math.pow(((Point3d)firstAtom.getPoint3d()).distance(secondAtomContainer.getAtom(secondAtomNumber).getPoint3d()),2);
							n++;
						}else{
							sum=sum+Math.pow(((Point2d)firstAtom.getPoint2d()).distance(secondAtomContainer.getAtom(secondAtomNumber).getPoint2d()),2);
							n++;
						}
					}
				}
			}catch (Exception ex){
			}
		}
		RMSD=Math.sqrt(sum/n);
		return RMSD;
	}



	/**
	 *  An average of all 3D bond length values is produced, using point3ds in atoms.
	 *  Atom's with no coordinates are disregarded.
	 *
	 *@param  ac  The AtomContainer for which the average bond length is to be
	 *      calculated
	 *@return     the average bond length
	 */
	public static double getBondLengthAverage3D(IAtomContainer ac) {
		double bondLengthSum = 0;
        Iterator bonds = ac.bonds();
        int bondCounter = 0;
        while (bonds.hasNext()) {
            IBond bond = (IBond) bonds.next();
			org.openscience.cdk.interfaces.IAtom atom1 = bond.getAtom(0);
			org.openscience.cdk.interfaces.IAtom atom2 = bond.getAtom(1);
			if (atom1.getPoint3d() != null &&
					atom2.getPoint3d() != null) {
				bondCounter++;
				bondLengthSum += atom1.getPoint3d().distance(atom2.getPoint3d());
			}
		}
		return bondLengthSum / bondCounter;
	}
}


