/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-01-04 18:46:10 +0100 (Thu, 04 Jan 2007) $
 *  $Revision: 7636 $
 *
 *  Copyright (C) 2002-2003  The Jmol Project
 *  Copyright (C) 2003-2007  The Chemistry Development Kit (CDK) project
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
import org.openscience.cdk.graph.invariant.MorganNumbersTools;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;

import javax.vecmath.Point2d;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

/**
 * A set of static utility classes for geometric calculations on Bonds.
 * The methods for detecting stereo configurations are described in CDK news, vol 2, p. 64 - 66.
 *
 * @author      shk3
 * @cdk.created 2005-08-04
 * @cdk.module  standard
 */
public class BondTools {
  /**
   *  Tells if a certain bond is center of a valid double bond configuration.
   *
   * @param  container  The atomcontainer.
   * @param  bond       The bond.
   * @return            true=is a potential configuration, false=is not.
   */
  public static boolean isValidDoubleBondConfiguration(IAtomContainer container, IBond bond) {
    //org.openscience.cdk.interfaces.IAtom[] atoms = bond.getAtoms();
    java.util.List connectedAtoms = container.getConnectedAtomsList(bond.getAtom(0));
    IAtom from = null;
    for (int i = 0; i < connectedAtoms.size(); i++) {
    	IAtom conAtom = (IAtom)connectedAtoms.get(i);
    	if (conAtom != bond.getAtom(1)) {
    		from = conAtom;
    	}
    }
    boolean[] array = new boolean[container.getBondCount()];
    for (int i = 0; i < array.length; i++) {
      array[i] = true;
    }
    if (isStartOfDoubleBond(container, bond.getAtom(0), from, array) && isEndOfDoubleBond(container, bond.getAtom(1), bond.getAtom(0), array) && !bond.getFlag(CDKConstants.ISAROMATIC)) {
      return (true);
    } else {
      return (false);
    }
  }


  /**
   *  Says if two atoms are in cis or trans position around a double bond.
   *  The atoms have to be given to the method like this:  firstOuterAtom - firstInnerAtom = secondInnterAtom - secondOuterAtom
   *
   * @param  firstOuterAtom    See above.
   * @param  firstInnerAtom    See above.
   * @param  secondInnerAtom   See above.
   * @param  secondOuterAtom   See above.
   * @param  ac                The atom container the atoms are in.
   * @return                   true=trans, false=cis.
   * @exception  CDKException  The atoms are not in a double bond configuration (no double bond in the middle, same atoms on one side)
   */
  public static boolean isCisTrans(IAtom firstOuterAtom, IAtom firstInnerAtom, IAtom secondInnerAtom, IAtom secondOuterAtom, IAtomContainer ac) throws CDKException {
    if (!isValidDoubleBondConfiguration(ac, ac.getBond(firstInnerAtom, secondInnerAtom))) {
      throw new CDKException("There is no valid double bond configuration between your inner atoms!");
    }
    boolean firstDirection = isLeft(firstOuterAtom, firstInnerAtom, secondInnerAtom);
    boolean secondDirection = isLeft(secondOuterAtom, secondInnerAtom, firstInnerAtom);
    if (firstDirection == secondDirection) {
      return true;
    } else {
      return false;
    }
  }


  /**
   *  Says if an atom is on the left side of a another atom seen from a certain
   *  atom or not
   *
   * @param  whereIs   The atom the position of which is returned
   * @param  viewFrom  The atom from which to look
   * @param  viewTo    The atom to which to look
   * @return           true=is left, false = is not
   */
  public static boolean isLeft(IAtom whereIs, IAtom viewFrom, IAtom viewTo) {
    double angle = giveAngleBothMethods(viewFrom, viewTo, whereIs, false);
    if (angle < 0) {
      return (false);
    } else {
      return (true);
    }
  }


  /**
   * Returns true if the two atoms are within the distance fudge
   * factor of each other.
   *
   * @param  atom1                Description of Parameter
   * @param  atom2                Description of Parameter
   * @param  distanceFudgeFactor  Description of Parameter
   * @return                      Description of the Returned Value
   * @cdk.keyword                 join-the-dots
   * @cdk.keyword                 bond creation
   */
  public static boolean closeEnoughToBond(IAtom atom1, IAtom atom2, double distanceFudgeFactor) {

    if (atom1 != atom2) {
      double distanceBetweenAtoms = atom1.getPoint3d().distance(atom2.getPoint3d());
      double bondingDistance = atom1.getCovalentRadius() + atom2.getCovalentRadius();
      if (distanceBetweenAtoms <= (distanceFudgeFactor * bondingDistance)) {
        return true;
      }
    }
    return false;
  }


  /**
   *  Gives the angle between two lines starting at atom from and going to to1
   *  and to2. If bool=false the angle starts from the middle line and goes from
   *  0 to PI or 0 to -PI if the to2 is on the left or right side of the line. If
   *  bool=true the angle goes from 0 to 2PI.
   *
   * @param  from  the atom to view from.
   * @param  to1   first direction to look in.
   * @param  to2   second direction to look in.
   * @param  bool  true=angle is 0 to 2PI, false=angel is -PI to PI.
   * @return       The angle in rad.
   */
  public static double giveAngleBothMethods(IAtom from, IAtom to1, IAtom to2, boolean bool) {
	  return giveAngleBothMethods(from.getPoint2d(), to1.getPoint2d(), to2.getPoint2d(),bool);
  }


  public static double giveAngleBothMethods(Point2d from, Point2d to1, Point2d to2, boolean bool) {
	    double[] A = new double[2];
	    from.get(A);
	    double[] B = new double[2];
	    to1.get(B);
	    double[] C = new double[2];
	    to2.get(C);
	    double angle1 = Math.atan2((B[1] - A[1]), (B[0] - A[0]));
	    double angle2 = Math.atan2((C[1] - A[1]), (C[0] - A[0]));
	    double angle = angle2 - angle1;
	    if (angle2 < 0 && angle1 > 0 && angle2 < -(Math.PI / 2)) {
	      angle = Math.PI + angle2 + Math.PI - angle1;
	    }
	    if (angle2 > 0 && angle1 < 0 && angle1 < -(Math.PI / 2)) {
	      angle = -Math.PI + angle2 - Math.PI - angle1;
	    }
	    if (bool && angle < 0) {
	      return (2 * Math.PI + angle);
	    } else {
	      return (angle);
	    }
  }
	  
  /**
   *  Says if an atom is the end of a double bond configuration
   *
   * @param  atom                     The atom which is the end of configuration
   * @param  container                The atomContainer the atom is in
   * @param  parent                   The atom we came from
   * @param  doubleBondConfiguration  The array indicating where double bond
   *      configurations are specified (this method ensures that there is
   *      actually the possibility of a double bond configuration)
   * @return                          false=is not end of configuration, true=is
   */
  private static boolean isEndOfDoubleBond(IAtomContainer container, org.openscience.cdk.interfaces.IAtom atom, org.openscience.cdk.interfaces.IAtom parent, boolean[] doubleBondConfiguration) {
    if (container.getBondNumber(atom, parent) == -1 || doubleBondConfiguration.length <= container.getBondNumber(atom, parent) || !doubleBondConfiguration[container.getBondNumber(atom, parent)]) {
      return false;
    }
    int lengthAtom = container.getConnectedAtomsList(atom).size() + atom.getHydrogenCount();
    int lengthParent = container.getConnectedAtomsList(parent).size() + parent.getHydrogenCount();
    if (container.getBond(atom, parent) != null) {
      if (container.getBond(atom, parent).getOrder() == CDKConstants.BONDORDER_DOUBLE && (lengthAtom == 3 || (lengthAtom == 2 && atom.getSymbol().equals("N"))) && (lengthParent == 3 || (lengthParent == 2 && parent.getSymbol().equals("N")))) {
        java.util.List atoms = container.getConnectedAtomsList(atom);
        IAtom one = null;
        IAtom two = null;
        for (int i = 0; i < atoms.size(); i++) {
        	IAtom conAtom = (IAtom)atoms.get(i);
          if (conAtom != parent && one == null) {
            one = conAtom;
          } else if (conAtom != parent && one != null) {
            two = conAtom;
          }
        }
        String[] morgannumbers = MorganNumbersTools.getMorganNumbersWithElementSymbol(container);
        if ((one != null && two == null && atom.getSymbol().equals("N") && Math.abs(giveAngleBothMethods(parent, atom, one, true)) > Math.PI / 10) || (!atom.getSymbol().equals("N") && one != null && two != null && !morgannumbers[container.getAtomNumber(one)].equals(morgannumbers[container.getAtomNumber(two)]))) {
          return (true);
        } else {
          return (false);
        }
      }
    }
    return (false);
  }


  /**
   *  Says if an atom is the start of a double bond configuration
   *
   * @param  a                        The atom which is the start of configuration
   * @param  container                The atomContainer the atom is in
   * @param  parent                   The atom we came from
   * @param  doubleBondConfiguration  The array indicating where double bond
   *      configurations are specified (this method ensures that there is
   *      actually the possibility of a double bond configuration)
   * @return                          false=is not start of configuration, true=is
   */
  private static boolean isStartOfDoubleBond(IAtomContainer container, org.openscience.cdk.interfaces.IAtom a, org.openscience.cdk.interfaces.IAtom parent, boolean[] doubleBondConfiguration) {
    int lengthAtom = container.getConnectedAtomsList(a).size() + a.getHydrogenCount();
    if (lengthAtom != 3 && (lengthAtom != 2 && a.getSymbol() != ("N"))) {
      return (false);
    }
    java.util.List atoms = container.getConnectedAtomsList(a);
    IAtom one = null;
    IAtom two = null;
    boolean doubleBond = false;
    IAtom nextAtom = null;
    for (int i = 0; i < atoms.size(); i++) {
    	IAtom atom = (IAtom)atoms.get(i);
    	if (atom != parent && container.getBond(atom, a).getOrder() == CDKConstants.BONDORDER_DOUBLE && isEndOfDoubleBond(container, atom, a, doubleBondConfiguration)) {
    		doubleBond = true;
    		nextAtom = atom;
    	}
    	if (atom != nextAtom && one == null) {
    		one = atom;
    	} else if (atom != nextAtom && one != null) {
    		two = atom;
    	}
    }
    String[] morgannumbers = MorganNumbersTools.getMorganNumbersWithElementSymbol(container);
    if (one != null && ((!a.getSymbol().equals("N") && two != null && !morgannumbers[container.getAtomNumber(one)].equals(morgannumbers[container.getAtomNumber(two)]) && doubleBond && doubleBondConfiguration[container.getBondNumber(a, nextAtom)]) || (doubleBond && a.getSymbol().equals("N") && Math.abs(giveAngleBothMethods(nextAtom, a, parent, true)) > Math.PI / 10))) {
    	return (true);
    } else {
    	return (false);
    }
  }
  
  
	/**
	 *  Says if an atom as a center of a tetrahedral chirality
	 *
	 *@param  a          The atom which is the center
	 *@param  container  The atomContainer the atom is in
	 *@return            0=is not tetrahedral;>1 is a certain depiction of
	 *      tetrahedrality (evaluated in parse chain)
	 */
	public static int isTetrahedral(IAtomContainer container, IAtom a, boolean strict)
	{
		java.util.List atoms = container.getConnectedAtomsList(a);
		if (atoms.size() != 4)
		{
			return (0);
		}
		java.util.List bonds = container.getConnectedBondsList(a);
        int up = 0;
		int down = 0;
		for (int i = 0; i < bonds.size(); i++)
		{
			IBond bond = (IBond)bonds.get(i);
			if (bond.getStereo() == CDKConstants.STEREO_BOND_NONE || bond.getStereo() == CDKConstants.STEREO_BOND_UNDEFINED)
			{
            }
			if (bond.getStereo() == CDKConstants.STEREO_BOND_UP)
			{
				up++;
			}
			if (bond.getStereo() == CDKConstants.STEREO_BOND_DOWN)
			{
				down++;
			}
		}
		if (up == 1 && down == 1)
		{
			return 1;
		}
		if (up == 2 && down == 2)
		{
			if (stereosAreOpposite(container, a))
			{
				return 2;
			}
			return 0;
		}
		if (up == 1 && down == 0 && !strict)
		{
			return 3;
		}
		if (down == 1 && up == 0 && !strict)
		{
			return 4;
		}
		if (down == 2 && up == 1 && !strict)
		{
			return 5;
		}
		if (down == 1 && up == 2 && !strict)
		{
			return 6;
		}
		return 0;
	}


	/**
	 *  Says if an atom as a center of a trigonal-bipyramidal or actahedral
	 *  chirality
	 *
	 *@param  a          The atom which is the center
	 *@param  container  The atomContainer the atom is in
	 *@return            true=is square planar, false=is not
	 */
	public static int isTrigonalBipyramidalOrOctahedral(IAtomContainer container, IAtom a)
	{
		java.util.List atoms = container.getConnectedAtomsList(a);
		if (atoms.size() < 5 || atoms.size() > 6)
		{
			return (0);
		}
		java.util.List bonds = container.getConnectedBondsList(a);
        int up = 0;
		int down = 0;
		for (int i = 0; i < bonds.size(); i++)
		{
			IBond bond = (IBond)bonds.get(i);
			if (bond.getStereo() == CDKConstants.STEREO_BOND_UNDEFINED || bond.getStereo() == CDKConstants.STEREO_BOND_NONE)
			{
            }
			if (bond.getStereo() == CDKConstants.STEREO_BOND_UP)
			{
				up++;
			}
			if (bond.getStereo() == CDKConstants.STEREO_BOND_DOWN)
			{
				down++;
			}
		}
		if (up == 1 && down == 1)
		{
			if(atoms.size()==5)
				return 1;
			else
				return 2;
		}
		return 0;
	}


	/**
	 *  Says if an atom as a center of any valid stereo configuration or not
	 *
	 *@param  a          The atom which is the center
	 *@param  container  The atomContainer the atom is in
	 *@return            true=is a stereo atom, false=is not
	 */
	public static boolean isStereo(IAtomContainer container, IAtom a)
	{
		java.util.List atoms = container.getConnectedAtomsList(a);
		if (atoms.size() < 4 || atoms.size() > 6)
		{
			return (false);
		}
		java.util.List bonds = container.getConnectedBondsList(a);
		int stereo = 0;
		for (int i = 0; i < bonds.size(); i++)
		{
			if (((IBond)bonds.get(i)).getStereo() != 0)
			{
				stereo++;
			}
		}
		if (stereo == 0)
		{
			return false;
		}
		int differentAtoms = 0;
		for (int i = 0; i < atoms.size(); i++)
		{
			boolean isDifferent = true;
			for (int k = 0; k < i; k++)
			{
				if (((IAtom)atoms.get(i)).getSymbol().equals(((IAtom)atoms.get(i)).getSymbol()))
				{
					isDifferent = false;
					break;
				}
			}
			if (isDifferent)
			{
				differentAtoms++;
			}
		}
		if (differentAtoms != atoms.size())
		{
			int[] morgannumbers = MorganNumbersTools.getMorganNumbers(container);
			Vector differentSymbols = new Vector();
			for (int i = 0; i < atoms.size(); i++)
			{
				IAtom atom = (IAtom)atoms.get(i);
				if (!differentSymbols.contains(atom.getSymbol()))
				{
					differentSymbols.add(atom.getSymbol());
				}
			}
			int[] onlyRelevantIfTwo = new int[2];
			if (differentSymbols.size() == 2)
			{
				for (int i = 0; i < atoms.size(); i++)
				{
					IAtom atom = (IAtom)atoms.get(i);
					if (differentSymbols.indexOf(atom.getSymbol()) == 0)
					{
						onlyRelevantIfTwo[0]++;
					} else
					{
						onlyRelevantIfTwo[1]++;
					}
				}
			}
			boolean[] symbolsWithDifferentMorganNumbers = new boolean[differentSymbols.size()];
			Vector[] symbolsMorganNumbers = new Vector[differentSymbols.size()];
			for (int i = 0; i < symbolsWithDifferentMorganNumbers.length; i++)
			{
				symbolsWithDifferentMorganNumbers[i] = true;
				symbolsMorganNumbers[i] = new Vector();
			}
			for (int k = 0; k < atoms.size(); k++)
			{
				IAtom atom = (IAtom)atoms.get(k);
				int elementNumber = differentSymbols.indexOf(atom.getSymbol());
				if (symbolsMorganNumbers[elementNumber].contains(new Integer(morgannumbers[container.getAtomNumber(atom)])))
				{
					symbolsWithDifferentMorganNumbers[elementNumber] = false;
				} else
				{
					symbolsMorganNumbers[elementNumber].add(new Integer(morgannumbers[container.getAtomNumber(atom)]));
				}
			}
			int numberOfSymbolsWithDifferentMorganNumbers = 0;
			for (int i = 0; i < symbolsWithDifferentMorganNumbers.length; i++)
			{
				if (symbolsWithDifferentMorganNumbers[i] == true)
				{
					numberOfSymbolsWithDifferentMorganNumbers++;
				}
			}
			if (numberOfSymbolsWithDifferentMorganNumbers != differentSymbols.size())
			{
				if ((atoms.size() == 5 || atoms.size() == 6) && (numberOfSymbolsWithDifferentMorganNumbers + differentAtoms > 2 || (differentAtoms == 2 && onlyRelevantIfTwo[0] > 1 && onlyRelevantIfTwo[1] > 1)))
				{
					return (true);
				}
				if (isSquarePlanar(container, a) && (numberOfSymbolsWithDifferentMorganNumbers + differentAtoms > 2 || (differentAtoms == 2 && onlyRelevantIfTwo[0] > 1 && onlyRelevantIfTwo[1] > 1)))
				{
					return (true);
				}
				return false;
			}
		}
		return (true);
	}


	/**
	 *  Says if an atom as a center of a square planar chirality
	 *
	 *@param  a          The atom which is the center
	 *@param  container  The atomContainer the atom is in
	 *@return            true=is square planar, false=is not
	 */
	public static boolean isSquarePlanar(IAtomContainer container, IAtom a)
	{
		java.util.List atoms = container.getConnectedAtomsList(a);
		if (atoms.size() != 4)
		{
			return (false);
		}
		java.util.List bonds = container.getConnectedBondsList(a);
        int up = 0;
		int down = 0;
		for (int i = 0; i < bonds.size(); i++)
		{
			IBond bond = (IBond)bonds.get(i);
			if (bond.getStereo() == CDKConstants.STEREO_BOND_UNDEFINED || bond.getStereo() == CDKConstants.STEREO_BOND_NONE)
			{
            }
			if (bond.getStereo() == CDKConstants.STEREO_BOND_UP)
			{
				up++;
			}
			if (bond.getStereo() == CDKConstants.STEREO_BOND_DOWN)
			{
				down++;
			}
		}
		if (up == 2 && down == 2 && !stereosAreOpposite(container, a))
		{
			return true;
		}
		return false;
	}


	/**
	 *  Says if of four atoms connected two one atom the up and down bonds are
	 *  opposite or not, i. e.if it's tetrehedral or square planar. The method
	 *  doesnot check if there are four atoms and if two or up and two are down
	 *
	 *@param  a          The atom which is the center
	 *@param  container  The atomContainer the atom is in
	 *@return            true=are opposite, false=are not
	 */
	public static boolean stereosAreOpposite(IAtomContainer container, IAtom a)
	{
		List atoms = container.getConnectedAtomsList(a);
		TreeMap hm = new TreeMap();
		for (int i = 1; i < atoms.size(); i++)
		{
			hm.put(new Double(giveAngle(a, (IAtom) atoms.get(0), ((IAtom) atoms.get(i)))), new Integer(i));
		}
		Object[] ohere = hm.values().toArray();
		int stereoOne = container.getBond(a, (IAtom) atoms.get(0)).getStereo();
		int stereoOpposite = container.getBond(a, (IAtom) atoms.get((((Integer) ohere[1])).intValue())).getStereo();
		if (stereoOpposite == stereoOne)
		{
			return true;
		} else
		{
			return false;
		}
	}


	/**
	 *  Calls giveAngleBothMethods with bool = true
	 *
	 *@param  from  the atom to view from
	 *@param  to1   first direction to look in
	 *@param  to2   second direction to look in
	 *@return       The angle in rad from 0 to 2*PI
	 */
	public static double giveAngle(IAtom from, IAtom to1, IAtom to2)
	{
		return (giveAngleBothMethods(from, to1, to2, true));
	}


	/**
	 *  Calls giveAngleBothMethods with bool = false
	 *
	 *@param  from  the atom to view from
	 *@param  to1   first direction to look in
	 *@param  to2   second direction to look in
	 *@return       The angle in rad from -PI to PI
	 */
	public static double giveAngleFromMiddle(IAtom from, IAtom to1, IAtom to2)
	{
		return (giveAngleBothMethods(from, to1, to2, false));
	}
	
	public static void  makeUpDownBonds(IAtomContainer container){
	    for (int i = 0; i < container.getAtomCount(); i++) {
	        IAtom a = container.getAtom(i);
	        if (container.getConnectedAtomsList(a).size() == 4) {
	          int up = 0;
	          int down = 0;
	          int hs = 0;
	          IAtom h = null;
	          for (int k = 0; k < 4; k++) {
	        	  IAtom conAtom = (IAtom)container.getConnectedAtomsList(a).get(k);
	        	  int stereo = container.getBond(a,conAtom).getStereo();
	        	  if (stereo  == CDKConstants.STEREO_BOND_UP) {
	        		  up++;
	        	  }
	        	  else if (stereo == CDKConstants.STEREO_BOND_DOWN) {
	        		  down++;
	        	  }
	        	  else if (stereo == CDKConstants.STEREO_BOND_NONE && conAtom.getSymbol().equals("H")) {
	        		  h = conAtom;
	        		  hs++;
	        	  } else {
	        		  h = null;
	        	  }
	          }
	          if (up == 0 && down == 1 && h != null && hs == 1) {
	            container.getBond(a, h).setStereo(CDKConstants.STEREO_BOND_UP);
	          }
	          if (up == 1 && down == 0 && h != null && hs == 1) {
	            container.getBond(a, h).setStereo(CDKConstants.STEREO_BOND_DOWN);
	          }
	        }
	      }
	}
}


