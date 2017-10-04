/* $RCSfile: $
 * $Author: egonw $
 * $Date: 2006-05-04 19:29:58 +0000 (Thu, 04 May 2006) $
 * $Revision: 6171 $
 *
 * Copyright (C) 2006-2007  Todd Martin (Environmental Protection Agency)
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.aromaticity.HueckelAromaticityDetector;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.interfaces.IRing;
import org.openscience.cdk.ringsearch.AllRingsFinder;

/**
 * Utility class written by Todd Martin, for help in his QSAR descriptors and SMILES
 * parser. Seems to have overlap with, at least, cdk.tools.Normalizer.
 * 
 * <p>TODO: merge with Normalizer.
 * 
 * @author     Todd Martin
 * @cdk.module extra
 * 
 * @see        org.openscience.cdk.tools.Normalizer
 */
public class CDKUtilities {
		
	public static String fixSmiles(String Smiles) {
		Smiles=Smiles.replaceAll("CL","Cl");
		Smiles=Smiles.replaceAll("(H)","([H])");
//		Smiles=Smiles.replace("N=N#N","N=[N+]=[N-]");
//		Smiles=Smiles.replace("#N=O","#[N+][O-]");
		Smiles=Smiles.trim();
		
		return Smiles;
		
	}
	
	
	private static boolean fixNitroGroups(IMolecule m) {
		// changes nitros given by N(=O)(=O) to [N+](=O)[O-]
		boolean changed=false;
		try {
		for (int i=0;i<=m.getAtomCount()-1;i++) {
			IAtom a=m.getAtom(i);
			boolean nitro=false;
			
			if (a.getSymbol().equals("N")) {
				List ca=m.getConnectedAtomsList(a);
				
				if (ca.size()==3) {					
					
					IAtom [] cao= new IAtom[2];
					
					int count=0;
					
					for (int j=0;j<=2;j++) {					
						if (((IAtom)ca.get(j)).getSymbol().equals("O")) {
							count++;
						}						
					}
					
					if (count>1) {
					
						count=0;
						for (int j=0;j<=2;j++) {
							IAtom caj = (IAtom)ca.get(j);
							if (caj.getSymbol().equals("O")) {
								if (m.getConnectedAtomsCount(caj)==1) {// account for possibility of ONO2
									cao[count]=caj;
									count++;									
								}
							}						
						}
						
						
						double order1=m.getBond(a,cao[0]).getOrder();
						double order2=m.getBond(a,cao[1]).getOrder();
						
						
						//if (totalobonds==4) { // need to fix (FIXME)
						if (order1==2 && order2==2) {
							a.setFormalCharge(1);
							cao[0].setFormalCharge(-1); // pick first O arbitrarily
							m.getBond(a,cao[0]).setOrder(1);
							changed=true;
						}
					} //else if (count==1) {// end if count>1
					
				}// end ca==3 if
				
			} // end symbol == N
			
			
		}
		
		return changed;
		
		
		} catch (Exception e) {
			return changed;
		}
		
	}
	
	public static boolean fixNitroGroups2(IMolecule m) {
		// changes nitros given by [N+](=O)[O-] to N(=O)(=O) 
		boolean changed=false;
		try {
		for (int i=0;i<=m.getAtomCount()-1;i++) {
			IAtom a=m.getAtom(i);
			boolean nitro=false;
			
			if (a.getSymbol().equals("N")) {
				List ca=m.getConnectedAtomsList(a);
				
				if (ca.size()==3) {					
					
					IAtom [] cao=new IAtom[2];
					
					int count=0;
					
					for (int j=0;j<=2;j++) {
						IAtom caj = (IAtom)ca.get(j);
						if (caj.getSymbol().equals("O")) {
							count++;
						}						
					}
					
					if (count>1) {
					
						count=0;
						for (int j=0;j<=2;j++) {
							IAtom caj = (IAtom)ca.get(j);
							if (caj.getSymbol().equals("O")) {
								if (m.getConnectedAtomsCount(caj) == 1) {// account for possibility of ONO2
									cao[count]=caj;
									count++;									
								}
							}						
						}
						
						
						double order1=m.getBond(a,cao[0]).getOrder();
						double order2=m.getBond(a,cao[1]).getOrder();
						
						//int totalobonds=0;						
						//totalobonds+=m.getBond(a,cao[0]).getOrder();
//						totalobonds+=m.getBond(a,cao[1]).getOrder();
						
						//if (totalobonds==4) { // need to fix
						if ((order1==1 && order2==2) || (order1==2 && order2==1) ) {
							a.setFormalCharge(0);
							cao[0].setFormalCharge(0); // pick first O arbitrarily
							cao[1].setFormalCharge(0); // pick first O arbitrarily
							m.getBond(a,cao[0]).setOrder(2);
							m.getBond(a,cao[1]).setOrder(2);
							changed=true;
						}
					} // end if count>1
					
					
				}// end ca==3 if
				
			} // end symbol == N
			
			
		}
		
		return changed;
		} catch (Exception e) {
			return changed;
		}
	}
	
	
	
	
	
	public static void makeHydrogensExplicit(IMolecule molecule)
	{		
		
		HydrogenAdder hydrogenAdder = null;
		hydrogenAdder = new HydrogenAdder("org.openscience.cdk.tools.ValencyChecker");

		//AtomContainer changedAtomsAndBonds = null;
			
		try
		{
			
			if (molecule != null) {
				// remove explicit hydrogen if necessary
				Iterator atoms = molecule.atoms();
				
				while (atoms.hasNext())
				{
					IAtom atom = (IAtom)atoms.next();
					if (atom.getSymbol().equals("H"))
					{
						molecule.removeAtomAndConnectedElectronContainers(atom);
					}
				}
				// add implicit hydrogen
				HashMap hydrogenAtomMap = hydrogenAdder.addImplicitHydrogensToSatisfyValency(molecule);
				
			}
		} catch (Exception e) {
			
		}
	}

	public static void addHydrogens(IMolecule m) {
		HydrogenAdder hydrogenAdder = null;
		hydrogenAdder = new HydrogenAdder("org.openscience.cdk.tools.ValencyChecker");

		try {
			hydrogenAdder.addExplicitHydrogensToSatisfyValency(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fixAromaticityForXLogP(IMolecule m) {
		// need to find rings and aromaticity again since added H's
		
		IRingSet rs=null;
		try {
			AllRingsFinder arf = new AllRingsFinder();
			rs = arf.findAllRings(m);

			// SSSRFinder s = new SSSRFinder(m);
			// srs = s.findEssentialRings();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// figure out which atoms are in aromatic rings:
			HueckelAromaticityDetector.detectAromaticity(m);
			// figure out which rings are aromatic:
			HueckelAromaticityDetector.detectAromaticity(m, rs);
			// figure out which simple (non cycles) rings are aromatic:
			// HueckelAromaticityDetector.detectAromaticity(m, srs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// only atoms in 6 membered rings are aromatic
		// determine largest ring that each atom is a part of
		
		for (int i=0;i<=m.getAtomCount()-1;i++) {
			
			m.getAtom(i).setFlag(CDKConstants.ISAROMATIC,false);
			
			jloop:
			for (int j=0;j<=rs.getAtomContainerCount()-1;j++) {
				//logger.debug(i+"\t"+j);
				IRing r=(IRing)rs.getAtomContainer(j);
				if (!r.getFlag(CDKConstants.ISAROMATIC)) {
					continue jloop;
				}
				
				boolean haveatom=r.contains(m.getAtom(i));

				//logger.debug("haveatom="+haveatom);
				
				if (haveatom && r.getAtomCount()==6) {
					m.getAtom(i).setFlag(CDKConstants.ISAROMATIC,true);
				}
				
			}
			
		}

		
	}
	
	
	public static void fixSulphurH(IMolecule m) {
		// removes extra H's attached to sulphurs
		//logger.debug("EnterFixSulphur");
		
		for (int i = 0; i <= m.getAtomCount()-1; i++)
		{
			IAtom a=m.getAtom(i);
			
			if (a.getSymbol().equals("S")) {
				List connectedAtoms=m.getConnectedAtomsList(a);
				
				
				int bondOrderSum=0;
				
				double oldBondOrderSum = m.getBondOrderSum(a); // includes H's
												
				for (int j=0;j<connectedAtoms.size();j++) {
					IAtom conAtom = (IAtom)connectedAtoms.get(j);
					if (!conAtom.getSymbol().equals("H")) {
						IBond b=m.getBond(a,conAtom);
						bondOrderSum+=b.getOrder();						
					}
				}
								
				if (bondOrderSum>1) {
					for (int j=0;j<connectedAtoms.size();j++) {
						IAtom conAtom = (IAtom)connectedAtoms.get(j);
						if (conAtom.getSymbol().equals("H")) {
							m.removeAtomAndConnectedElectronContainers(conAtom);
						}
					}
				}
																	
			}
			
		}
	}
	
}
