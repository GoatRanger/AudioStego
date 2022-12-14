/*
 *  $RCSfile$
 *  $Author: rajarshi $
 *  $Date: 2007-02-01 20:46:29 +0100 (Thu, 01 Feb 2007) $
 *  $Revision: 7844 $
 *
 *  Copyright (C) 2005-2007  Christian Hoppe <chhoppe@users.sf.net>
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

import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.graph.PathTools;
import org.openscience.cdk.graph.matrix.ConnectionMatrix;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.qsar.DescriptorSpecification;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IMolecularDescriptor;
import org.openscience.cdk.qsar.result.IDescriptorResult;
import org.openscience.cdk.qsar.result.IntegerResult;
import org.openscience.cdk.ringsearch.SSSRFinder;

import java.util.Vector;

/**
 *  Class that returns the number of atoms in the longest aliphatic chain.
 *
 * <p>This descriptor uses these parameters:
 * <table border="1">
 *   <tr>
 *     <td>Name</td>
 *     <td>Default</td>
 *     <td>Description</td>
 *   </tr>
 *   <tr>
 *     <td>checkRingSystem</td>
 *     <td>false</td>
 *     <td>True is the CDKConstant.ISINRING has to be set</td>
 *   </tr>
 * </table>
 *
 * Returns a single value named <i>nAtomLAC</i>
 * @author      chhoppe from EUROSCREEN
 * @cdk.created 2006-1-03
 * @cdk.module  qsar
 * @cdk.set     qsar-descriptors
 * @cdk.dictref qsar-descriptors:largestAliphaticChain
 */

public class LongestAliphaticChainDescriptor implements IMolecularDescriptor {
	private boolean checkRingSystem = false;


    /**
     *  Constructor for the LongestAliphaticChainDescriptor object.
     */
    public LongestAliphaticChainDescriptor() { }

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
                "http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#longestAliphaticChain",
                this.getClass().getName(),
                "$Id: LongestAliphaticChainDescriptor.java 7844 2007-02-01 19:46:29Z rajarshi $",
                "The Chemistry Development Kit");
    }


    /**
     *  Sets the parameters attribute of the LongestAliphaticChainDescriptor object.
     *
     * This descriptor takes one parameter, which should be Boolean to indicate whether
     * aromaticity has been checked (TRUE) or not (FALSE).
     * 
     * @param  params            The new parameters value
     * @exception  CDKException if more than one parameter or a non-Boolean parameter is specified
     * @see #getParameters
     */
    public void setParameters(Object[] params) throws CDKException {
        if (params.length > 1) {
            throw new CDKException("LongestAliphaticChainDescriptor only expects one parameter");
        }
        if (!(params[0] instanceof Boolean)) {
            throw new CDKException("Both parameters must be of type Boolean");
        }
        // ok, all should be fine
        checkRingSystem = ((Boolean) params[0]).booleanValue();
    }


    /**
     *  Gets the parameters attribute of the LongestAliphaticChainDescriptor object.
     *
     * @return    The parameters value
     * @see #setParameters
     */
    public Object[] getParameters() {
        // return the parameters as used for the descriptor calculation
        Object[] params = new Object[1];
        params[0] = new Boolean(checkRingSystem);
        return params;
    }


    /**
     * Calculate the count of atoms of the longest aliphatic chain in the supplied {@link AtomContainer}.
     * 
     *  The method require one parameter:
     *  if checkRingSyste is true the CDKConstant.ISINRING will be set
     *
     *@param  container  The {@link AtomContainer} for which this descriptor is to be calculated
     *@return                   the number of atoms in the longest aliphatic chain of this AtomContainer
     *@throws CDKException if there is a problem in aromaticity detection
     *@see #setParameters
     */
    public DescriptorValue calculate(IAtomContainer container) throws CDKException {
    	
    	//logger.debug("LongestAliphaticChainDescriptor");
    	IRingSet rs = null;
    	if (checkRingSystem) {
        	rs = new SSSRFinder(container).findSSSR();
        	for (int i=0;i<container.getAtomCount();i++){
            	if (rs.contains(container.getAtom(i))){
            		container.getAtom(i).setFlag(CDKConstants.ISINRING,true);
            	}
            }
        }
        	
    	int longestChainAtomsCount=0;
        int tmpLongestChainAtomCount=0;
    	//IAtom[] atoms = container.getAtoms();
    	Vector startSphere = null;
    	Vector path = null;
    	//Set all VisitedFlags to False
    	for (int i =0;i<container.getAtomCount();i++){
    		container.getAtom(i).setFlag(CDKConstants.VISITED, false);
		}
    	//logger.debug("Set all atoms to Visited False");
    	for (int i =0;i<container.getAtomCount();i++){
    		IAtom atomi = container.getAtom(i);
    		//logger.debug("atom:"+i+" maxBondOrder:"+container.getMaximumBondOrder(atoms[i])+" Aromatic:"+atoms[i].getFlag(CDKConstants.ISAROMATIC)+" Ring:"+atoms[i].getFlag(CDKConstants.ISINRING)+" FormalCharge:"+atoms[i].getFormalCharge()+" Charge:"+atoms[i].getCharge()+" Flag:"+atoms[i].getFlag(CDKConstants.VISITED));
    		if ((!atomi.getFlag(CDKConstants.ISAROMATIC) && !atomi.getFlag(CDKConstants.ISINRING) & atomi.getSymbol().equals("C")) & !atomi.getFlag(CDKConstants.VISITED)){
    			//logger.debug("...... -> Accepted");
    			startSphere = new Vector();
    			path = new Vector();
    			startSphere.addElement(atomi);
     			breadthFirstSearch(container, startSphere, path);
     			//create Atomcontainer
     			//logger.debug("Create new Atom Container");
     			AtomContainer aliphaticChain =createAtomContainerFromPath(container,path);
     			if (aliphaticChain.getAtomCount()>1){
     				double[][] conMat = ConnectionMatrix.getMatrix(aliphaticChain);
     				//logger.debug("Computing all-pairs-shortest-pathes ");
     				int[][] apsp = PathTools.computeFloydAPSP(conMat);
     				tmpLongestChainAtomCount=getLongestChainPath(apsp);
     				//logger.debug(" lengthPath:"+tmpLongestChainAtomCount+" allLength:"+longestChainAtomsCount);
     				if (tmpLongestChainAtomCount>longestChainAtomsCount){
     					longestChainAtomsCount=tmpLongestChainAtomCount;
     				}
     			}
     		}    		
    	}
  
        return new DescriptorValue(getSpecification(), getParameterNames(), getParameters(),
                new IntegerResult(longestChainAtomsCount), new String[] {"nAtomLAC"});
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

    private int getLongestChainPath(int[][] apsp){
        int longestPath=0;
        for (int i = 0; i < apsp.length; i++){
            for (int j = 0; j < apsp.length; j++){
                    if (apsp[i][j]+1 > longestPath){
                        longestPath = apsp[i][j]+1;
                    }
            }
        }
        return longestPath;
    }
    
    
    
    private AtomContainer createAtomContainerFromPath(IAtomContainer container, Vector path){
    	AtomContainer aliphaticChain = new org.openscience.cdk.AtomContainer();
    	for (int i=0;i<path.size()-1;i++){
    		if (!aliphaticChain.contains((IAtom)path.get(i))){
    			aliphaticChain.addAtom((IAtom)path.get(i));
    		}
    		for (int j=1;j<path.size();j++){
    			if (container.getBond((IAtom)path.get(i),(IAtom)path.get(j))!=null){
    				if (!aliphaticChain.contains((IAtom)path.get(j))){
    	    			aliphaticChain.addAtom((IAtom)path.get(j));
    	    		}
    				aliphaticChain.addBond(container.getBond((IAtom)path.get(i),(IAtom)path.get(j)));
    			}
    		}
    	}
    	
    	//for (int i=0;i<aliphaticChain.getAtomCount();i++){
    	//	logger.debug("container-->atom:"+i+" Nr: "+container.getAtomNumber(aliphaticChain.getAtomAt(i))+" maxBondOrder:"+aliphaticChain.getMaximumBondOrder(aliphaticChain.getAtomAt(i))+" Aromatic:"+aliphaticChain.getAtomAt(i).getFlag(CDKConstants.ISAROMATIC)+" Ring:"+aliphaticChain.getAtomAt(i).getFlag(CDKConstants.ISINRING)+" FormalCharge:"+aliphaticChain.getAtomAt(i).getFormalCharge()+" Charge:"+aliphaticChain.getAtomAt(i).getCharge()+" Flag:"+aliphaticChain.getAtomAt(i).getFlag(CDKConstants.VISITED));
    	//}
    	//logger.debug("BondCount:"+aliphaticChain.getBondCount());
    	if (aliphaticChain.getBondCount()==0){
    		aliphaticChain.removeAllElements();
    	}
    	return aliphaticChain;
    }
    
	/**
	 *  Performs a breadthFirstSearch in an AtomContainer starting with a
	 *  particular sphere, which usually consists of one start atom, and searches
	 *  for a pi system. 
	 *
	 *@param  container                                              The AtomContainer to
	 *      be searched
	 *@param  sphere                                          A sphere of atoms to
	 *      start the search with
	 *@param  path                                          A vector which stores the atoms belonging to the pi system
	 *@exception  org.openscience.cdk.exception.CDKException  Description of the
	 *      Exception
	 */
	public  void breadthFirstSearch(IAtomContainer container, Vector sphere, Vector path) throws org.openscience.cdk.exception.CDKException{
		IAtom atom = null;
		IAtom nextAtom = null;
		Vector newSphere = new Vector();
		for (int i = 0; i < sphere.size(); i++){
			atom = (IAtom) sphere.elementAt(i);
			java.util.List bonds = container.getConnectedBondsList(atom);
			for (int j = 0; j < bonds.size(); j++){
				nextAtom = ((IBond)bonds.get(j)).getConnectedAtom(atom);
				if ((!nextAtom.getFlag(CDKConstants.ISAROMATIC)&& !nextAtom.getFlag(CDKConstants.ISINRING)& nextAtom.getSymbol().equals("C")) & !nextAtom.getFlag(CDKConstants.VISITED)){
	    			path.addElement(nextAtom);
		    		nextAtom.setFlag(CDKConstants.VISITED, true);
		    		if (container.getConnectedBondsCount(nextAtom) > 1){
						newSphere.addElement(nextAtom);
					}
				}else{
					nextAtom.setFlag(CDKConstants.VISITED, true);
				}
			}
		}
		if (newSphere.size() > 0){
			breadthFirstSearch(container, newSphere, path);
		}
	}

    
    /**
     *  Gets the parameterNames attribute of the LongestAliphaticChainDescriptor object.
     *
     *@return    The parameterNames value
     */
    public String[] getParameterNames() {
        String[] params = new String[1];
        params[0] = "checkRingSystem";
        return params;
    }



    /**
     *  Gets the parameterType attribute of the LongestAliphaticChainDescriptor object.
     *
     *@param  name  Description of the Parameter
     *@return       An Object of class equal to that of the parameter being requested
     */
    public Object getParameterType(String name) {
        Object[] paramTypes = new Object[1];
       paramTypes[0] = new Boolean(true);
        return paramTypes;
    }
}
