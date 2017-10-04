/* $RCSfile$   
 * $Author: egonw $   
 * $Date: 2007-01-04 18:26:00 +0100 (Thu, 04 Jan 2007) $    
 * $Revision: 7634 $
 * 
 * Copyright (C) 2004-2007  The Chemistry Development Kit (CDK) project
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
package org.openscience.cdk.applications.swing.editor;

import org.openscience.cdk.applications.swing.FieldTablePanel;
import org.openscience.cdk.controller.Controller2DModel;

/**
 * @cdk.module applications
 * @cdk.require swing
 */
public class Controller2DModelEditor extends FieldTablePanel {
    
    private static final long serialVersionUID = 7978080354056029617L;
    
    private Controller2DModel model;
    
	public Controller2DModelEditor() {
        super();
        constructPanel();
	}
    
    private void constructPanel() {
    }
    
    public void setModel(Controller2DModel model) {
        this.model = model;
    }
	
    public void applyChanges() {
    }

	public Controller2DModel getModel() {
		return model;
	}
}


