/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-01-04 18:26:00 +0100 (Thu, 04 Jan 2007) $
 * $Revision: 7634 $
 *
 * Copyright (C) 1997-2007  The JChemPaint project
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

import org.openscience.cdk.applications.jchempaint.JChemPaintEditorPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Opens a new empty JChemPaintFrame.
 *
 * @cdk.module jchempaint
 * @author     steinbeck
 * @cdk.created    1997
 */
public class NewAction extends JCPAction {

    private static final long serialVersionUID = -6710948755122145479L;

    /**
     *  Opens an empty JChemPaint frame.
     *
     *@param  e  Description of the Parameter
     */
    public void actionPerformed(ActionEvent e) {
      if(jcpPanel.isEmbedded()){
        int clear=jcpPanel.showWarning();
        if(clear==JOptionPane.YES_OPTION){
        	if(jcpPanel.getJChemPaintModel().getChemModel().getMoleculeSet()!=null)
        		jcpPanel.getJChemPaintModel().getChemModel().getMoleculeSet().removeAllAtomContainers();
        	if(jcpPanel.getJChemPaintModel().getChemModel().getReactionSet()!=null)
        		jcpPanel.getJChemPaintModel().getChemModel().getReactionSet().removeAllReactions();
        	jcpPanel.repaint();
        }
      }else{
        JFrame frame = JChemPaintEditorPanel.getEmptyFrameWithModel();
        frame.setVisible(true);
        frame.pack();
      }
    }
}

