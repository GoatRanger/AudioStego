/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-01-04 18:26:00 +0100 (Thu, 04 Jan 2007) $
 *  $Revision: 7634 $
 *
 *  Copyright (C) 1997-2007  The JChemPaint project
 *
 *  Contact: jchempaint-devel@lists.sourceforge.net
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
package org.openscience.cdk.applications.jchempaint.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.undo.UndoableEdit;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.Reaction;
import org.openscience.cdk.MoleculeSet;
import org.openscience.cdk.ReactionSet;
import org.openscience.cdk.applications.jchempaint.DrawingPanel;
import org.openscience.cdk.applications.jchempaint.JChemPaintModel;
import org.openscience.cdk.applications.undoredo.CleanUpEdit;
import org.openscience.cdk.geometry.GeometryToolsInternalCoordinates;
import org.openscience.cdk.geometry.GeometryTools;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.layout.StructureDiagramGenerator;
import org.openscience.cdk.layout.TemplateHandler;
import org.openscience.cdk.nonotify.NoNotificationChemObjectBuilder;
import org.openscience.cdk.renderer.Renderer2DModel;


/**
 * Triggers the invocation of the structure diagram generator
 *
 * @cdk.module jchempaint
 * @author     steinbeck
 */
public class CleanupAction extends JCPAction
{

	private static final long serialVersionUID = -1048878006430754582L;
	private StructureDiagramGenerator diagramGenerator;
    

	/**
	 *  Constructor for the CleanupAction object
	 */
	public CleanupAction()
	{
		super();
	}


	/**
	 *  Relayouts a molecule
	 *
	 *@param  e  Description of the Parameter
	 */
	public void actionPerformed(ActionEvent e)
	{	
        HashMap atomCoordsMap = new HashMap();
        logger.info("Going to performe a clean up...");
		if (jcpPanel.getJChemPaintModel() != null)
		{
			JChemPaintModel jcpmodel = jcpPanel.getJChemPaintModel();
			if (diagramGenerator == null) {
                diagramGenerator = new StructureDiagramGenerator();
                diagramGenerator.setTemplateHandler(
                    new TemplateHandler(jcpmodel.getChemModel().getBuilder())
                );
            }
			Renderer2DModel renderModel = jcpmodel.getRendererModel();
			double bondLength = renderModel.getBondLength() / renderModel.getScaleFactor();
			diagramGenerator.setBondLength(bondLength * 2.0);
			// FIXME this extra factor should not be necessary
			logger.debug("getting ChemModel");
			org.openscience.cdk.interfaces.IChemModel model = jcpmodel.getChemModel();
			logger.debug("got ChemModel");
			org.openscience.cdk.interfaces.IMoleculeSet som = model.getMoleculeSet();
			if (som != null)
			{
                
				logger.debug("no mols in som: ", som.getMoleculeCount());
				MoleculeSet newsom = new MoleculeSet();
				java.util.Iterator mols = som.molecules();
				while (mols.hasNext())
				{
                    IMolecule molecule = (IMolecule)mols.next();
                    if (molecule != null && molecule.getAtomCount() > 0) {
                    	IMolecule cleanedMol = relayoutMolecule(molecule);
                    	newsom.addMolecule(cleanedMol);
                    	for (int j=0; j<molecule.getAtomCount(); j++) {
                    		IAtom atom = molecule.getAtom(j);
                    		IAtom newAtom = cleanedMol.getAtom(j);
                    		Point2d oldCoord =  (Point2d)jcpmodel.getRendererModel().getRenderingCoordinate(atom);
                    		Point2d newCoord = newAtom.getPoint2d();
                    		if (!oldCoord.equals(newCoord)) {
                    			Point2d[] coords = new Point2d[2];
                    			coords[0] = newCoord;
                    			coords[1] = oldCoord;
                    			atomCoordsMap.put(newAtom, coords);
                    		}
                    	}
                    }
				}
				model.setMoleculeSet(newsom);
                
                UndoableEdit  edit = new CleanUpEdit(atomCoordsMap);
                jcpPanel.getUndoSupport().postEdit(edit);
    			GeometryTools.makeRenderingCoordinates(newsom, jcpmodel.getRendererModel().getRenderingCoordinates());

			}
			org.openscience.cdk.interfaces.IReactionSet reactionSet = model.getReactionSet();
			if (reactionSet != null)
			{
				ReactionSet newSet = new ReactionSet();
				// FIXME, this does not preserve reactionset properties!
				java.util.Iterator reactionsIter = reactionSet.reactions();
				while (reactionsIter.hasNext())
				{
					org.openscience.cdk.interfaces.IReaction reaction = (org.openscience.cdk.interfaces.IReaction)reactionsIter.next();
					Reaction newReaction = new Reaction();
					// FIXME, this does not preserve reaction properties!
					java.util.Iterator reactants = reaction.getReactants().molecules();
					while (reactants.hasNext())
					{
						newReaction.addReactant(relayoutMolecule((IMolecule)reactants.next()));
					}
					java.util.Iterator products = reaction.getProducts().molecules();
					while (products.hasNext())
					{
						newReaction.addProduct(relayoutMolecule((IMolecule)products.next()));
					}
					newSet.addReaction(newReaction);
				}
				model.setReactionSet(newSet);
			}

			jcpmodel.getRendererModel().setSelectedPart(new AtomContainer());
			jcpmodel.fireChange();
			jcpPanel.scaleAndCenterMolecule(jcpmodel.getChemModel());
			((DrawingPanel)jcpPanel.getDrawingPanel()).updateRingSetInRenderer();
			jcpPanel.repaint();
		}
	}

	private IMolecule relayoutMolecule(IMolecule molecule)
	{
		JChemPaintModel jcpmodel = jcpPanel.getJChemPaintModel();
		IMolecule cleanedMol = molecule;
		if (molecule != null) {
			if (molecule.getAtomCount() > 2) {
				try {
			    	Point2d centre = GeometryTools.get2DCentreOfMass(molecule,jcpmodel.getRendererModel().getRenderingCoordinates());
					// since we will copy the coordinates later anyway, let's use
					// a NonNotifying data class
					diagramGenerator.setMolecule(
						NoNotificationChemObjectBuilder.getInstance().
							newMolecule(molecule)
					);
					diagramGenerator.generateExperimentalCoordinates(new Vector2d(0, 1));
					cleanedMol = diagramGenerator.getMolecule();
                    /*
					 *  make the molecule end up somewhere reasonable
					 *  See constructor of JCPPanel
					 */
					// Thread.sleep(5000);
					GeometryToolsInternalCoordinates.translateAllPositive(cleanedMol);
					double scaleFactor = GeometryToolsInternalCoordinates.getScaleFactor(cleanedMol, jcpmodel.getRendererModel().getBondLength());
					GeometryToolsInternalCoordinates.scaleMolecule(cleanedMol, scaleFactor);
					GeometryToolsInternalCoordinates.translate2DCentreOfMassTo(cleanedMol, centre);
				} catch (Exception exc)
				{
					logger.error("Could not generate coordinates for molecule");
					logger.debug(exc);
				}
			} else
			{
				logger.info("Molecule with less than 2 atoms are not cleaned up");
			}
		} else
		{
			logger.error("Molecule is null! Cannot do layout!");
		}
		return cleanedMol;
	}
}

