/* $RCSfile$
 * $Author: egonw $ 
 * $Date: 2007-05-01 22:13:10 +0200 (Tue, 01 May 2007) $
 * $Revision: 8295 $
 * 
 * Copyright (C) 2003-2007  The Chemistry Development Kit (CDK) project
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
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
package org.openscience.cdk.tools.manipulator;

import org.openscience.cdk.interfaces.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class with convenience methods that provide methods from
 * methods from ChemObjects within the ChemFile.
 *
 * @see org.openscience.cdk.AtomContainer#removeAtomAndConnectedElectronContainers(IAtom)
 *
 * @cdk.module standard
 */
public class ChemFileManipulator {

	/**
	 * Get the total number of atoms inside an IChemFile.
	 * 
	 * @param file       The IChemFile object.
	 * @return           The number of Atom object inside.
	 */
    public static int getAtomCount(IChemFile file) {
    	int count = 0;
        for (int i=0; i<file.getChemSequenceCount(); i++) {
        	count += ChemSequenceManipulator.getAtomCount(file.getChemSequence(i));
        }
        return count;
    }

    /**
	 * Get the total number of bonds inside an IChemFile.
	 * 
	 * @param file       The IChemFile object.
	 * @return           The number of Bond object inside.
	 */
    public static int getBondCount(IChemFile file) {
    	int count = 0;
        for (int i=0; i<file.getChemSequenceCount(); i++) {
        	count += ChemSequenceManipulator.getBondCount(file.getChemSequence(i));
        }
        return count;
    }

    /**
     * Returns a List of all IChemObject inside a ChemFile.
     *
     * @return  A list of all ChemObjects
     */
    public static List getAllChemObjects(IChemFile file) {
    	ArrayList list = new ArrayList();
    	//list.add(file); // should not add the original file
        for (int i=0; i<file.getChemSequenceCount(); i++) {
        	list.add(file.getChemSequence(i));
            list.addAll(ChemSequenceManipulator.getAllChemObjects(
                file.getChemSequence(i)
            ));
        }
        return list;
    }

    public static List getAllIDs(IChemFile file) {
    	ArrayList list = new ArrayList();
    	if (file.getID() != null) list.add(file.getID());
        for (int i=0; i<file.getChemSequenceCount(); i++) {
            list.addAll(ChemSequenceManipulator.getAllIDs(
                file.getChemSequence(i)
            ));
        }
        return list;
    }

    /**
     * Returns all the AtomContainer's of a ChemFile.
     */
    public static List getAllAtomContainers(IChemFile file) {
        Iterator sequences = file.chemSequences();
        List acList = new ArrayList();
        while (sequences.hasNext()) {
        	IChemSequence chemseq  = (IChemSequence)sequences.next();
            acList.addAll(ChemSequenceManipulator.getAllAtomContainers(chemseq));
        }
        return acList;
    }
    
    /**
     * Get a list of all ChemModels inside an IChemFile.
     * 
     * @param file  The IChemFile object.
     * @return      The List of IChemModel objects inside.
     */
    public static List getAllChemModels(IChemFile file)
    {
        List modelsList = new ArrayList();

	    for (int f = 0; f < file.getChemSequenceCount(); f++){
		    java.util.Iterator iter = file.getChemSequence(f).chemModels();
		    while (iter.hasNext())
		    {
			    modelsList.add((IChemModel)iter.next());
		    }
	    }
	    return modelsList;
    }
}

