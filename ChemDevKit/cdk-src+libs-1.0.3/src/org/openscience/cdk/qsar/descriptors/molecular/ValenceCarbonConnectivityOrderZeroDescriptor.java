/*  $RCSfile$
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

import org.openscience.cdk.config.IsotopeFactory;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IElement;
import org.openscience.cdk.qsar.DescriptorSpecification;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IMolecularDescriptor;
import org.openscience.cdk.qsar.descriptors.atomic.AtomValenceDescriptor;
import org.openscience.cdk.qsar.result.DoubleResult;
import org.openscience.cdk.qsar.result.IDescriptorResult;
import org.openscience.cdk.tools.LoggingTool;

import java.util.Map;


/**
 *  Atomic valence connectivity index (order 0). See
 *  <a href="http://www.edusoft-lc.com/molconn/manuals/400/chaptwo.html">http://www.edusoft-lc.com/molconn/manuals/400/chaptwo.html</a> and
 *  <a href="http://www.chemcomp.com/Journal_of_CCG/Features/descr.htm#KH">http://www.chemcomp.com/Journal_of_CCG/Features/descr.htm#KH</a>.
 *
 *  <p>Returned value is:
 *  <ul>
 *    <li>chi0vC is the Carbon valence connectivity index (order 0);
 *  </ul>
 *  where the valence is the number of s and p valence electrons of atom.
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
 *     <td>no parameters</td>
 *   </tr>
 * </table>
 * 
 * @author      mfe4
 * @cdk.created 2004-11-03
 * @cdk.module	qsar
 * @cdk.set     qsar-descriptors
 * @cdk.dictref qsar-descriptors:chi0vC
 */
public class ValenceCarbonConnectivityOrderZeroDescriptor implements IMolecularDescriptor {

    private LoggingTool logger;
    private static Map valences;
    private AtomValenceDescriptor avd = null;
	/**
	 *  Constructor for the ValenceCarbonConnectivityOrderZeroDescriptor object
	 */
	public ValenceCarbonConnectivityOrderZeroDescriptor() { 
            logger = new LoggingTool(this);
	    if (valences == null) { 
		avd = new AtomValenceDescriptor();
		valences = avd.valencesTable;
	    }
        }


	/**
	 *  Gets the specification attribute of the
	 *  ValenceCarbonConnectivityOrderZeroDescriptor object
	 *
	 *@return    The specification value
	 */
	public DescriptorSpecification getSpecification() {
        return new DescriptorSpecification(
            "http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#chi0vC",
		    this.getClass().getName(),
		    "$Id: ValenceCarbonConnectivityOrderZeroDescriptor.java 7844 2007-02-01 19:46:29Z rajarshi $",
            "The Chemistry Development Kit");
	}


	/**
	 *  Sets the parameters attribute of the
	 *  ValenceCarbonConnectivityOrderZeroDescriptor object
	 *
	 *@param  params            The new parameters value
	 *@exception  CDKException  Description of the Exception
	 */
	public void setParameters(Object[] params) throws CDKException {
		// no parameters for this descriptor
	}


	/**
	 *  Gets the parameters attribute of the
	 *  ValenceCarbonConnectivityOrderZeroDescriptor object
	 *
	 *@return    The parameters value
	 */
	public Object[] getParameters() {
		// no parameters to return
		return (null);
	}


	/**
	 *  calculates the Carbon valence connectivity index (order 0) descriptor for an atom container
	 *
	 *@param  atomContainer                AtomContainer
	 *@return                   Carbon valence connectivity index (order 0)
	 *@exception  CDKException  Possible Exceptions
	 */
	public DescriptorValue calculate(IAtomContainer atomContainer) throws CDKException {
		int valence = 0;
		int atomicNumber = 0;
		int hcount = 0;
		int atomValue = 0;
		double chi0vC = 0;
		java.util.List neighatoms = null;
		IAtom atom = null;
		IElement element = null;
		IsotopeFactory elfac = null;
		String symbol = null;
                for (int i = 0; i < atomContainer.getAtomCount(); i++) {
                	atom = atomContainer.getAtom(i);
                    symbol = atom.getSymbol();
                    if(!symbol.equals("H")) {
                        try {
                            elfac = IsotopeFactory.getInstance(atomContainer.getBuilder());
                        } catch (Exception exc) {
                            logger.debug(exc);
                            throw new CDKException("Problem instantiating IsotopeFactory: " + exc.toString(), exc);
                        }
                        try {
                            element = elfac.getElement(symbol);
                        } catch (Exception exc) {
                            logger.debug(exc);
                            throw new CDKException("Problem getting isotope " + symbol + " from ElementFactory: " + exc.toString(), exc);
                        }
                        atomicNumber = element.getAtomicNumber();
                        valence = ((Integer)valences.get(symbol)).intValue();
                        hcount = 0;
                        atomValue = 0;
                        neighatoms = atomContainer.getConnectedAtomsList(atom);
                        for (int a = 0; a < neighatoms.size(); a++) {
                            if (((IAtom)neighatoms.get(a)).getSymbol().equals("H")) {
                                hcount += 1;
                            }
                        }
                        hcount += atomContainer.getAtom(i).getHydrogenCount();
                        atomValue = (valence - hcount) / (atomicNumber - valence - 1);
                        if (atomValue > 0) {
                            if(symbol.equals("C")) {
                                chi0vC  += (1/(Math.sqrt(atomValue))); // chi0vC
                            }
                        }
                    }
                }
                return new DescriptorValue(getSpecification(), getParameterNames(), getParameters(),
                        new DoubleResult(chi0vC), new String[]{"chi0vC"});
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
        return new DoubleResult(0.0);
    }


    /**
     *  Gets the parameterNames attribute of the
     *  ValenceCarbonConnectivityOrderZeroDescriptor object
     *
     *@return    The parameterNames value
     */
    public String[] getParameterNames() {
        // no param names to return
        return (null);
    }



	/**
	 *  Gets the parameterType attribute of the
	 *  ValenceCarbonConnectivityOrderZeroDescriptor object
	 *
	 *@param  name  Description of the Parameter
	 *@return       The parameterType value
	 */
	public Object getParameterType(String name) {
		return (null);
	}
}

