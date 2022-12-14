/*  $Revision: 8197 $ $Author: egonw $ $Date: 2007-04-15 11:06:53 +0200 (Sun, 15 Apr 2007) $
 *
 *  Copyright (C) 2005-2007  Christian Hoppe <chhoppe@users.sf.net>
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
package org.openscience.cdk.modeling.builder3d;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

import javax.vecmath.Point3d;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.fingerprint.Fingerprinter;
import org.openscience.cdk.fingerprint.FingerprinterTool;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.interfaces.IMoleculeSet;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.io.iterator.IteratingMDLReader;
import org.openscience.cdk.isomorphism.UniversalIsomorphismTester;
import org.openscience.cdk.isomorphism.matchers.QueryAtomContainer;
import org.openscience.cdk.isomorphism.matchers.QueryAtomContainerCreator;
import org.openscience.cdk.isomorphism.mcss.RMap;
import org.openscience.cdk.nonotify.NoNotificationChemObjectBuilder;
import org.openscience.cdk.tools.LoggingTool;

/**
 * Helper class for ModelBuilder3D. Handles templates. This is
 * our layout solution for 3D ring systems
 *
 * @author      cho
 * @author      steinbeck
 * @cdk.created 2004-09-21
 * @cdk.module  builder3d
 */
public class TemplateHandler3D {
	
	private static final IChemObjectBuilder builder = NoNotificationChemObjectBuilder.getInstance();
	private static final LoggingTool logger = new LoggingTool(TemplateHandler3D.class);
	
    IMolecule molecule;
    IRingSet sssr;
    IMoleculeSet templates = null;
    List fingerprintData = null;
    List ringTemplates = null;
    private boolean templatesLoaded = false;

    private static TemplateHandler3D self = null;
    
    private TemplateHandler3D() {
        templates = builder.newMoleculeSet();
        fingerprintData = new ArrayList();
        ringTemplates = new ArrayList(75);
    }

    public static TemplateHandler3D getInstance() throws CDKException {
    	if (self == null) {
    		self = new TemplateHandler3D();
    	}
    	return self;
    }

    /**
     * Loads all existing templates into memory. 
     * Template file is a mdl file. Creates a Object Set of Molecules
     */
    private void loadTemplates() throws CDKException {
        logger.debug("Loading templates...");
        IteratingMDLReader imdl;
        InputStream ins;
        BufferedReader fin;

        try {
            ins = this.getClass().getClassLoader().getResourceAsStream("org/openscience/cdk/modeling/builder3d/data/ringTemplateStructures.sdf.gz");
            fin = new BufferedReader(new InputStreamReader(new GZIPInputStream(ins)));
            imdl = new IteratingMDLReader(fin, builder);
        } catch (Exception exc1) {
            throw new CDKException("Problems loading file ringTemplateStructures.sdf.gz", exc1);
        }
        IMolecule molecule;
        while (imdl.hasNext()) {
            molecule = (IMolecule) imdl.next();
            templates.addMolecule(molecule);
        }
        molecule = null;
        try {
            imdl.close();
        } catch (Exception exc2) {
            System.out.println("Could not close Reader due to: " + exc2.getMessage());
        }
        //logger.debug("TEMPLATE Finger");
        try {

            ins = this.getClass().getClassLoader().getResourceAsStream("org/openscience/cdk/modeling/builder3d/data/ringTemplateFingerprints.txt.gz");
            fin = new BufferedReader(new InputStreamReader(new GZIPInputStream(ins)));
        } catch (Exception exc3) {
            throw new CDKException("Could not read Fingerprints from FingerprintFile due to: " + exc3.getMessage(), exc3);
        }
        String s = null;
        while (true) {
            try {
                s = fin.readLine();
            } catch (Exception exc4) {
                throw new CDKException("Error while reading the fingerprints: " + exc4.getMessage(), exc4);
            }

            if (s == null) {
                break;
            }
            fingerprintData.add((BitSet) getBitSetFromFile(new StringTokenizer(s, "\t ;{, }")));
        }
        //logger.debug("Fingerprints are read in:"+fingerprintData.size());
        templatesLoaded = true;
    }

    private BitSet getBitSetFromFile(StringTokenizer st) {
        BitSet bitSet = new BitSet(1024);
        for (int i = 0; i < st.countTokens(); i++) {

            try {
                bitSet.set(Integer.parseInt(st.nextToken()));
            } catch (NumberFormatException nfe) {
                // do nothing
            }
        }
        return bitSet;
    }

    /**
     * Checks if one of the loaded templates is a substructure in the given
     * Molecule. If so, it assigns the coordinates from the template to the
     * respective atoms in the Molecule.
     *
     * @param ringSystems       AtomContainer from the ring systems
     * @param NumberOfRingAtoms double
     */
    public void mapTemplates(IAtomContainer ringSystems, double NumberOfRingAtoms) throws Exception {
		if (!templatesLoaded) self.loadTemplates();

        //logger.debug("Map Template...START---Number of Ring Atoms:"+NumberOfRingAtoms);
        IAtomContainer template;
        QueryAtomContainer queryRingSystem = QueryAtomContainerCreator.createAnyAtomContainer(ringSystems, false);
        QueryAtomContainer query;
        BitSet ringSystemFingerprint = new Fingerprinter().getFingerprint(queryRingSystem);
        RMap map;
        org.openscience.cdk.interfaces.IAtom atom1;
        org.openscience.cdk.interfaces.IAtom atom2;
        boolean flagMaxSubstructure = false;
        for (int i = 0; i < fingerprintData.size(); i++) {
            template = templates.getMolecule(i);
            if (template.getAtomCount() != ringSystems.getAtomCount()) {
                continue;
            }
            if (FingerprinterTool.isSubset(ringSystemFingerprint, (BitSet) fingerprintData.get(i))) {
                query = QueryAtomContainerCreator.createAnyAtomContainer(template, true);
                if (UniversalIsomorphismTester.isSubgraph(ringSystems, query)) {
                    List list = UniversalIsomorphismTester.getSubgraphAtomsMap(ringSystems, query);
                    if ((NumberOfRingAtoms) / list.size() == 1) {
                        flagMaxSubstructure = true;
                    }

                    for (int j = 0; j < list.size(); j++) {
                        map = (RMap) list.get(j);
                        atom1 = ringSystems.getAtom(map.getId1());
                        atom2 = template.getAtom(map.getId2());
                        if (atom1.getFlag(CDKConstants.ISINRING)) {
                        	atom1.setPoint3d(new Point3d(atom2.getPoint3d()));
                        }
                    }//for j

                    if (flagMaxSubstructure) {
                        break;
                    }

                }//if subgraph
            }//if fingerprint
        }//for i
        if (!flagMaxSubstructure) {
            System.out.println("WARNING: Maybe RingTemplateError!");
        }
    }

    /**
     * Gets the templateCount attribute of the TemplateHandler object
     *
     * @return The templateCount value
     */
    public int getTemplateCount() {
        return templates.getMoleculeCount();
    }


    /**
     *  Gets the templateAt attribute of the TemplateHandler object
     *
     *@param  position  Description of the Parameter
     *@return The templateAt value
     */
    public IAtomContainer getTemplateAt(int position) {
        return templates.getMolecule(position);
	}
}

