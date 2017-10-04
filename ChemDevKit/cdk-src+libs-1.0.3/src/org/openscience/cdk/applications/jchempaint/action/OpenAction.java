/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-01-05 15:18:42 +0100 (Fri, 05 Jan 2007) $
 *  $Revision: 7640 $
 *
 *  Copyright (C) 1997-2007  The JChemPaint project
 *
 *  Contact: jchempaint-devel@lists.sf.net
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
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.openscience.cdk.ChemModel;
import org.openscience.cdk.applications.jchempaint.io.JCPFileFilter;
import org.openscience.cdk.applications.jchempaint.io.JCPFileView;
import org.openscience.cdk.interfaces.IChemFile;
import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.io.CMLReader;
import org.openscience.cdk.io.IChemObjectReader;
import org.openscience.cdk.io.INChIReader;
import org.openscience.cdk.io.MDLV2000Reader;
import org.openscience.cdk.tools.HydrogenAdder;

/**
 * Shows the open dialog
 *
 * @author        steinbeck
 * @cdk.module    jchempaint
 */
public class OpenAction extends JCPAction {

	private static final long serialVersionUID = 1030940425527065876L;

	private FileFilter currentFilter = null;

	/**
	 *  Opens an empty JChemPaint frame.
	 *
	 * @param  e  Description of the Parameter
	 */
	public void actionPerformed(ActionEvent e) {

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(jcpPanel.getCurrentWorkDirectory());
		JCPFileFilter.addChoosableFileFilters(chooser);
		if (jcpPanel.getCurrentOpenFileFilter() != null) {
			chooser.setFileFilter(jcpPanel.getCurrentOpenFileFilter());
		}
		if (jcpPanel.getLastOpenedFile() != null) {
			chooser.setSelectedFile(jcpPanel.getLastOpenedFile());
		}
		if (currentFilter != null) {
			chooser.setFileFilter(currentFilter);
		}
		chooser.setFileView(new JCPFileView());

		int returnVal = chooser.showOpenDialog(jcpPanel);
		String type = null;
		IChemObjectReader cor = null;

		currentFilter = chooser.getFileFilter();

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			jcpPanel.setCurrentWorkDirectory(chooser.getCurrentDirectory());
			jcpPanel.setCurrentOpenFileFilter(chooser.getFileFilter());

			File inFile = chooser.getSelectedFile();
			jcpPanel.setLastOpenedFile(inFile);

			/*
			 *  Have the ReaderFactory determine the file format
			 */
			try {
				cor = jcpPanel.getChemObjectReader(new FileReader(inFile));
			} catch (IOException ioExc) {
				logger.warn("IOException while determining file format.");
				logger.debug(ioExc);
			} catch (Exception exc) {
				logger.warn("Exception while determining file format.");
				logger.debug(exc);
			}

			if (cor == null) {
				// try to determine from user's guess
				try {
					FileInputStream reader = new FileInputStream(inFile);
					javax.swing.filechooser.FileFilter ff = chooser.getFileFilter();
					if (ff instanceof JCPFileFilter) {
						type = ((JCPFileFilter) ff).getType();
					}
					else {
						type = "unknown";
					}

					if (type.equals(JCPFileFilter.cml) || type.equals(JCPFileFilter.xml)) {
						cor = new CMLReader(reader);
					}
					else if (type.equals(JCPFileFilter.sdf)) {
						cor = new MDLV2000Reader(reader);
					}
					else if (type.equals(JCPFileFilter.mol)) {
						cor = new MDLV2000Reader(reader);
					}
					else if (type.equals(JCPFileFilter.inchi)) {
						cor = new INChIReader(reader);
					}
				} catch (FileNotFoundException exception) {
					logger.warn("File cannot be found.");
					logger.debug(exception);
				}
			}

			if (cor == null) {
				JOptionPane.showMessageDialog(jcpPanel, "Could not determine file format.");
				return;
			}

			//this takes care of files called .mol, but having several, sdf-stylish entried
			if(cor instanceof MDLV2000Reader){
				try{
					FileInputStream reader = new FileInputStream(inFile);
					DataInputStream in = new DataInputStream(reader);
	                while (in.available() !=0)
					{
	                	if(in.readLine().equals("$$$$")){
	                		JOptionPane.showMessageDialog(jcpPanel, "It seems you opened a mol or sdf file containing several molecules. Only the first one will be shown","sdf-like file", JOptionPane.INFORMATION_MESSAGE);
	                		break;
	                	}
					}
				}catch(IOException ex){
					//we do nothing - firstly if IO does not work, we should not get here, secondly, if only this does not work, don't worry
				}
			}
			
			String error = null;
			ChemModel chemModel = null;
			IChemFile chemFile=null;
			if (cor.accepts(IChemFile.class)) {
				// try to read a ChemFile
				try {
					chemFile = (IChemFile) cor.read((IChemObject) new org.openscience.cdk.ChemFile());
					if (chemFile != null) {
												
						jcpPanel.processChemFile(chemFile);
						if(jcpPanel.getJChemPaintModel().getControllerModel().getAutoUpdateImplicitHydrogens()){
							HydrogenAdder hydrogenAdder = new HydrogenAdder("org.openscience.cdk.tools.ValencyChecker");
				        	java.util.Iterator mols = chemFile.getChemSequence(0).getChemModel(0).getMoleculeSet().molecules();
							while (mols.hasNext())
							{
								org.openscience.cdk.interfaces.IMolecule molecule = (IMolecule)mols.next();
							    if (molecule != null)
								{
									try{
											hydrogenAdder.addImplicitHydrogensToSatisfyValency(molecule);
									}catch(Exception ex){
										//do nothing
									}
								}
							}
						}
						//The following do apply either to the existing or the new frame
						jcpPanel.lastUsedJCPP.getJChemPaintModel().setTitle(inFile.getName());
						jcpPanel.lastUsedJCPP.setIsAlreadyAFile(inFile);
						if (jcpPanel.isEmbedded() == false) {
							((JFrame)
							jcpPanel.lastUsedJCPP.getParent().getParent().getParent().getParent()).setTitle(inFile.getName());
						}
						return;
					}
					else {
						logger.warn("The object chemFile was empty unexpectedly!");
					}
				} catch (Exception exception) {
					error = "Error while reading file: " + exception.getMessage();
					exception.printStackTrace();
					logger.warn(error);
					logger.debug(exception);
				}
			}
			if (error != null) {
				JOptionPane.showMessageDialog(jcpPanel, error);
				return;
			}
			if (cor.accepts(ChemModel.class)) {
				// try to read a ChemModel
				try {
					chemModel = (ChemModel) cor.read((IChemObject) new ChemModel());
					if (chemModel != null) {
						jcpPanel.processChemModel(chemModel);
						if(jcpPanel.getJChemPaintModel().getControllerModel().getAutoUpdateImplicitHydrogens()){
							HydrogenAdder hydrogenAdder = new HydrogenAdder("org.openscience.cdk.tools.ValencyChecker");
				        	java.util.Iterator mols = chemModel.getMoleculeSet().molecules();
							while (mols.hasNext())
							{
								IMolecule molecule = (IMolecule)mols.next();
							    if (molecule != null)
								{
									try{
											hydrogenAdder.addImplicitHydrogensToSatisfyValency(molecule);
									}catch(Exception ex){
										//do nothing
									}
								}
							}
						}
						//The following do apply either to the existing or the new frame
						jcpPanel.lastUsedJCPP.getJChemPaintModel().setTitle(inFile.getName());
						jcpPanel.lastUsedJCPP.setIsAlreadyAFile(inFile);
						((JFrame) jcpPanel.lastUsedJCPP.getParent().getParent().getParent().getParent()).setTitle(inFile.getName());
						return;
					}
					else {
						logger.warn("The object chemModel was empty unexpectedly!");
					}
					error = null;
					// overwrite previous problems, it worked now
				} catch (Exception exception) {
					error = "Error while reading file: " + exception.getMessage();
					exception.printStackTrace();
					logger.error(error);
					logger.debug(exception);
				}
			}
			if (error != null) {
				JOptionPane.showMessageDialog(jcpPanel, error);
			}
		}
	}
}

