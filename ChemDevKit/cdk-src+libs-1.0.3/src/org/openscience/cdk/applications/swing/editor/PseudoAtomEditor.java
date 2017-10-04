/* $RCSfile$   
 * $Author: egonw $   
 * $Date: 2007-01-04 18:26:00 +0100 (Thu, 04 Jan 2007) $    
 * $Revision: 7634 $
 * 
 * Copyright (C) 2001-2007  The Chemistry Development Kit (CDK) project
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

import javax.swing.JTextField;

import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.interfaces.IPseudoAtom;

/**
 * @cdk.module applications
 * @cdk.require swing
 */
public class PseudoAtomEditor extends ChemObjectEditor {
    
    private static final long serialVersionUID = 7785262423262705152L;
    
    JTextField labelField;
    
	public PseudoAtomEditor() {
        super();
        constructPanel();
	}
    
    private void constructPanel() {
        labelField = new JTextField(20);
        addField("Label", labelField);
    }
    
    public void setChemObject(IChemObject object) {
        if (object instanceof IPseudoAtom) {
            source = object;
            // update table contents
            labelField.setText(((IPseudoAtom)object).getLabel());
        } else {
            throw new IllegalArgumentException("Argument must be an PseudoAtom");
        }
    }
	
    public void applyChanges() {
        IPseudoAtom atom = (IPseudoAtom)source;
        atom.setLabel(labelField.getText());
    }
}


