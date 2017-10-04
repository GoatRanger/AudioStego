/* $RCSfile$    
 * $Author: egonw $    
 * $Date: 2007-05-01 21:15:34 +0200 (Tue, 01 May 2007) $    
 * $Revision: 8292 $
 *
 * Copyright (C) 2003-2007  The JChemPaint project
 *
 * Contact: jchempaint-devel@lists.sourceforge.net
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
package org.openscience.cdk.applications.jchempaint.action;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.applications.jchempaint.JChemPaintModel;
import org.openscience.cdk.dict.CDKDictionaryReferences;
import org.openscience.cdk.tools.manipulator.ChemModelManipulator;


/**
 * Creates a dictionary entry for a selected entity
 *
 * @cdk.module jchempaint
 * @author  Egon Willighagen
 * @cdk.created 2003-08-08
 */
public class DictionaryAction extends JCPAction {

	private static final long serialVersionUID = -4542062694817405630L;

	public void actionPerformed(ActionEvent event) {
        logger.debug("Dictionary Action triggered");
        JChemPaintModel jcpModel = jcpPanel.getJChemPaintModel();
        org.openscience.cdk.interfaces.IChemModel chemModel = jcpModel.getChemModel();
        
        // make explicit references
        CDKDictionaryReferences.makeReferencesExplicit(chemModel);
        Iterator containers = ChemModelManipulator.getAllAtomContainers(chemModel).iterator();
        while (containers.hasNext()) {
        	IAtomContainer container = (IAtomContainer)containers.next();
        	for (int i=0; i<container.getAtomCount(); i++) {
        		logger.debug("Making references for atom...");
        		CDKDictionaryReferences.makeReferencesExplicit(container.getAtom(i));
        	}
        	for (int i=0; i<container.getBondCount(); i++) {
        		logger.debug("Making references for bond...");
        		CDKDictionaryReferences.makeReferencesExplicit(container.getBond(i));
        	}
        }
        
        jcpModel.fireChange();
    }
    
}
