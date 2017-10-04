/* $RCSfile$
 * $Author: shk3 $    
 * $Date: 2007-09-01 21:39:38 +0200 (Sat, 01 Sep 2007) $    
 * $Revision: 8815 $
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

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.openscience.cdk.ChemModel;
import org.openscience.cdk.applications.jchempaint.JChemPaintModel;
import org.openscience.cdk.applications.jchempaint.io.JCPExportFileFilter;
import org.openscience.cdk.applications.jchempaint.io.JCPFileView;

import com.sun.media.jai.codec.JPEGEncodeParam;

/**
 * Exporting the current model various formats
 *
 * @cdk.module  jchempaint
 * @author      Egon Willighagen
 * @cdk.require jai
 * @cdk.bug     1586156
 */
public class ExportAction extends SaveAsAction {

	private static final long serialVersionUID = -3287152749914283054L;
	
	private FileFilter currentFilter = null;
    
    /**
    * Opens a dialog frame and manages the saving of a file.
    */
    public void actionPerformed(ActionEvent e) {
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(jcpPanel.getCurrentWorkDirectory());
        JCPExportFileFilter.addChoosableFileFilters(chooser);
        if (currentFilter != null) {
            chooser.setFileFilter(currentFilter);
        }
        chooser.setFileView(new JCPFileView());
        while(true){
	        int returnVal = chooser.showSaveDialog(jcpPanel);
	        String type = null;
	        currentFilter = chooser.getFileFilter();
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	            type = ((JCPExportFileFilter)currentFilter).getType();
	            File outFile = new File(chooser.getSelectedFile().getAbsolutePath()+"."+type);
	        	boolean dowrite=true;
	        	if(outFile.exists()){
	        		int value=JOptionPane.showConfirmDialog(jcpPanel,"File already exists. Do you want to overwrite it?", "File already exists", JOptionPane.YES_NO_OPTION);
	    			if(value==JOptionPane.NO_OPTION){
	    				dowrite=false;
	    			}
	        	}
	        	if(dowrite){
		            if (type.equals(JCPExportFileFilter.svg)) {
		                try {
		                    JChemPaintModel jcpm = jcpPanel.getJChemPaintModel();
		                    ChemModel model = (ChemModel)jcpm.getChemModel();
		                    saveAsSVG(model, outFile);
		                } catch (Exception exc) {
		                    String error = "Error while writing file: " + exc.getMessage();
		                    logger.error(error);
		                    logger.debug(exc);
		                    JOptionPane.showMessageDialog(jcpPanel, error);
		                }
		            } else {
		                // A binary image
		                Image awtImage = jcpPanel.takeSnapshot();
		                String filename = outFile.toString();
		                logger.debug("Creating binary image: ", filename);
		                RenderedOp image = JAI.create("AWTImage", awtImage);
		                if (type.equals(JCPExportFileFilter.bmp)) {
		                    JAI.create("filestore", image, filename, "BMP", null);
		                } else if (type.equals(JCPExportFileFilter.tiff)) {
		                    JAI.create("filestore", image, filename, "TIFF", null);
		                } else if (type.equals(JCPExportFileFilter.jpg)) {
		                    JAI.create("filestore", image, filename, "JPEG", new JPEGEncodeParam());
		                } else if (type.equals(JCPExportFileFilter.png)) {
		                    JAI.create("filestore", image, filename, "PNG", null);
		                } else { // default to a PNG binary image
		                    JAI.create("filestore", image, filename, "PNG", null);
		                }
		                logger.debug("Binary image saved to: ", filename);
		            }
		            break;
	        	}
	        }else{
	        	break;
	        }
	
	        jcpPanel.setCurrentWorkDirectory(chooser.getCurrentDirectory());
        }
    }
}
    
