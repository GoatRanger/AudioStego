/*  $RCSfile: $
 *  $Author: egonw $
 *  $Date: 2007-04-21 02:22:30 +0200 (Sat, 21 Apr 2007) $
 *  $Revision: 8224 $
 *
 *  Copyright (C) 2002-2007  The Chemistry Development Kit (CDK) project
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
 */
package org.openscience.cdk.aromaticity;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IRing;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.ringsearch.AllRingsFinder;
import org.openscience.cdk.tools.LoggingTool;

/**
 * The HueckelAromaticityDetector detects the aromaticity based on the Hueckel
 * 4n+2 pi-electrons Rule. This is done by one of the detectAromaticity
 * methods. They set the aromaticity flags of appropriate Atoms, Bonds and
 * Rings. After the detection, you can use getFlag(CDKConstants.ISAROMATIC) on
 * these ChemObjects.
 *
 * @author         steinbeck
 * @author         kaihartmann
 * @cdk.module     standard
 * @cdk.created    2001-09-04
 * 
 * @see org.openscience.cdk.CDKConstants
 */
public class HueckelAromaticityDetector
{

	static LoggingTool logger = new LoggingTool(HueckelAromaticityDetector.class);
	AllRingsFinder ringFinder = null;
	static IRingSet ringSet=null;
	static long timeout=5000;


	public static IRingSet getRingSet() {
		return ringSet;
	}

	/**
	 * Retrieves the set of all rings and performs an aromaticity detection based
	 * on Hueckels 4n+2 rule.
	 *
	 * @param  atomContainer		AtomContainer to detect rings in
	 * @return                      True if the molecule has aromatic features
	 * @exception  CDKException 	Thrown if something goes wrong or in
	 *                              case of a AllRingsFinder timeout 
	 */
	public static boolean detectAromaticity(IAtomContainer atomContainer) throws CDKException
	{
		return (detectAromaticity(atomContainer, true));
	}


	/**
	 * Uses precomputed set of ALL rings and performs an aromaticity detection
	 * based on Hueckels 4n+2 rule.
	 *
	 * @param  ringSet		  set of ALL rings
	 * @param  atomContainer  The AtomContainer to detect rings in
	 * @return                True if molecule has aromatic features
	 * @exception             org.openscience.cdk.exception.CDKException  
	 */
	public static boolean detectAromaticity(IAtomContainer atomContainer, IRingSet ringSet) throws org.openscience.cdk.exception.CDKException
	{
		return (detectAromaticity(atomContainer, ringSet, true));
	}


	/**
	 *  Retrieves the set of all rings and performs an aromaticity detection based
	 *  on Hueckels 4n + 2 rule.
	 *
	 *@param  removeAromatictyFlags  When true, we leaves ChemObjects that 
	 *				 are already marked as aromatic as they are
	 *@param  atomContainer          AtomContainer to be searched for
	 * 				rings
	 *@return			True, if molecule has aromatic features                               	 
	 *@exception CDKException  	Thrown in case of errors or an 
	 *				AllRingsFinder timeout
	 */
	public static boolean detectAromaticity(IAtomContainer atomContainer, boolean removeAromatictyFlags) throws org.openscience.cdk.exception.CDKException
	{
		return detectAromaticity(atomContainer, removeAromatictyFlags, null);
	}


	/**
	 *  Retrieves the set of all rings and performs an aromaticity detection based
	 *  on Hueckels 4n + 2 rule. An AllRingsFinder with customized timeout may be
	 *  assigned to this method.
	 *@param  removeAromatictyFlags  When true, we leaves ChemObjects that 
	 *				 are already marked as aromatic as they are
	 *@param  atomContainer          AtomContainer to be searched for
	 *@param  arf                    AllRingsFinder to be employed for the
	 *				ringsearch. Use this to customize the 
	 *				AllRingsFinder timeout feature
	 * 				rings
	 *@return			True, if molecule has aromatic features                               	 
	 *@exception CDKException  	Thrown in case of errors or an 
	 *				AllRingsFinder timeout
	 */
	public static boolean detectAromaticity(IAtomContainer atomContainer, boolean removeAromatictyFlags, AllRingsFinder arf) throws org.openscience.cdk.exception.CDKException
	{
		logger.debug("Entered Aromaticity Detection");
		logger.debug("Starting AllRingsFinder");
		long before = System.currentTimeMillis();
		if (arf == null)
		{
			arf = new AllRingsFinder();
			arf.setTimeout(timeout);
		}
		ringSet = arf.findAllRings(atomContainer);
		long after = System.currentTimeMillis();
		logger.debug("time for finding all rings: " + (after - before) + " milliseconds");
		logger.debug("Finished AllRingsFinder");
		if (ringSet.getAtomContainerCount() > 0)
		{
			return detectAromaticity(atomContainer, ringSet, removeAromatictyFlags);
		}
		return false;
	}


	/**
	 *  Uses precomputed set of ALL rings and performs an aromaticity detection
	 *  based on Hueckels 4n + 2 rule.
	 *
	 *@param  ringSet                 set of ALL rings
	 *@param  removeAromaticityFlags  Leaves ChemObjects that are already marked as
	 *      aromatic as they are
	 *@param  atomContainer           AtomContainer to be searched for rings
	 *@return                         True, if molecules contains an
	 *				  aromatic feature
	 */
	public static boolean detectAromaticity(IAtomContainer atomContainer, IRingSet ringSet, boolean removeAromaticityFlags)
	{
		boolean foundSomething = false;
		if (removeAromaticityFlags)
		{
			for (int f = 0; f < atomContainer.getAtomCount(); f++)
			{
				atomContainer.getAtom(f).setFlag(CDKConstants.ISAROMATIC, false);
			}
			for (int f = 0; f < atomContainer.getBondCount(); f++)
			{
				atomContainer.getBond(f).setFlag(CDKConstants.ISAROMATIC, false);
			}
			for (int f = 0; f < ringSet.getAtomContainerCount(); f++)
			{
				((IRing) ringSet.getAtomContainer(f)).setFlag(CDKConstants.ISAROMATIC, false);
			}
		}

		IRing ring = null;
		//RingSetManipulator.sort(ringSet);
		for (int f = 0; f < ringSet.getAtomContainerCount(); f++)
		{
			ring = (IRing) ringSet.getAtomContainer(f);
			logger.debug("Testing for aromaticity in ring no ", f);
			if (AromaticityCalculator.isAromatic(ring, atomContainer))
			{
				ring.setFlag(CDKConstants.ISAROMATIC, true);

				for (int g = 0; g < ring.getAtomCount(); g++)
				{
					ring.getAtom(g).setFlag(CDKConstants.ISAROMATIC, true);
				}

				for (int g = 0; g < ring.getBondCount(); g++)
				{
					ring.getBond(g).setFlag(CDKConstants.ISAROMATIC, true);
				}

				foundSomething = true;
				logger.debug("This ring is aromatic: ", f);
			} else
			{
				logger.debug("This ring is *not* aromatic: ", f);
			}
		}
		return foundSomething;
	}


	/**
	 *  This method sets the aromaticity flags for a RingSet from the Atom flags.
	 *  It can be used after the aromaticity detection to set the appropriate flags
	 *  for a RingSet from the SSSR search.
	 *
	 *@param  ringset  the RingSet to set the flags for
	 */
	public static void setRingFlags(IRingSet ringset)
	{
		for (int i = 0; i < ringset.getAtomContainerCount(); i++)
		{
			boolean aromatic = true;
			IRing ring = (IRing) ringset.getAtomContainer(i);
			for (int j = 0; j < ring.getAtomCount(); j++)
			{
				if (ring.getAtom(j).getFlag(CDKConstants.ISAROMATIC) != true)
				{
					aromatic = false;
					break;
				}
			}
			if (aromatic)
			{
				ring.setFlag(CDKConstants.ISAROMATIC, true);
			} else
			{
				ring.setFlag(CDKConstants.ISAROMATIC, false);
			}
		}
	}


	/*
	 *  Sets the current AllRingsFinder instance Use this if you want to customize
	 *  the timeout for the AllRingsFinder. AllRingsFinder is stopping its quest to
	 *  find all rings after a default of 5 seconds.
	 *
	 *@param  ringFinder  The value to assign ringFinder.
	 *@see                org.openscience.cdk.ringsearch.AllRingsFinder
	 */
//	public void setRingFinder(AllRingsFinder ringFinder)
//	{
//		this.ringFinder = ringFinder;
//	}

	/**
	 * Sets the timeout for the AllRingsFinder. The default is 5000ms.
	 * 
	 * @param timeout  The timeout for the AllRingsFinder.
	 */
	public static void setTimeout(long timeout) {
		HueckelAromaticityDetector.timeout = timeout;
	}

	/*
	 *  public static boolean isAromatic(AtomContainer ac, Ring ring)
	 *  {
	 *  return AromaticityCalculator.isAromatic(ring, ac);
	 *  }
	 *  *	public static boolean isAromatic(AtomContainer ac, Ring ring)
	 *  {
	 *  int piElectronCount = 0;
	 *  int freeElectronPairCount = 0;
	 *  Atom atom = null;
	 *  Bond bond = null;
	 *  int aromaCounter = 0;
	 *  logger.debug("isAromatic() -> ring.size(): " + ring.getAtomCount());
	 *  for (int g = 0; g < ring.getAtomCount(); g++)
	 *  {
	 *  atom = ring.getAtomAt(g);
	 *  if ("O-N-S-P".indexOf(atom.getSymbol()) > -1)
	 *  {
	 *  freeElectronPairCount += 1;
	 *  }
	 *  if (atom.getFlag(CDKConstants.ISAROMATIC))
	 *  {
	 *  aromaCounter ++;
	 *  }
	 *  }
	 *  for (int g = 0; g < ring.getElectronContainerCount(); g++) {
	 *  ElectronContainer ec = ring.getElectronContainerAt(g);
	 *  if (ec instanceof org.openscience.cdk.interfaces.Bond) {
	 *  bond = (Bond)ec;
	 *  if (bond.getOrder() > 1) {
	 *  piElectronCount += 2*(bond.getOrder()-1);
	 *  }
	 *  }
	 *  }
	 *  for (int f = 0; f < ((ring.getAtomCount() - 2)/4) + 2; f ++)
	 *  {
	 *  logger.debug("isAromatic() -> freeElectronPairCount: " + freeElectronPairCount);
	 *  logger.debug("isAromatic() -> piElectronCount: " + piElectronCount);
	 *  logger.debug("isAromatic() -> f: " + f);
	 *  logger.debug("isAromatic() -> (4 * f) + 2: " + ((4 * f) + 2));
	 *  logger.debug("isAromatic() -> ring.size(): " + ring.getAtomCount());
	 *  logger.debug("isAromatic() -> aromaCounter: " + aromaCounter);
	 *  if (aromaCounter == ring.getAtomCount()) return true;
	 *  else if ((piElectronCount == ring.getAtomCount())&&((4 * f) + 2 == piElectronCount)) return true;
	 *  else if ((4 * f) + 2 == piElectronCount + (freeElectronPairCount * 2) && ring.getAtomCount() < piElectronCount + (freeElectronPairCount * 2)) return true;
	 *  }
	 *  return false;
	 *  }
	 */
}

