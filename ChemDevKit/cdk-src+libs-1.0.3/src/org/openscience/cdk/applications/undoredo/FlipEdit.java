/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-01-04 18:26:00 +0100 (Thu, 04 Jan 2007) $
 * $Revision: 7634 $
 *
 * Copyright (C) 2005-2007  The Chemistry Development Kit (CDK) project
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
package org.openscience.cdk.applications.undoredo;

import java.util.HashMap;

/**
 * Undo/Redo Edit class for the FlipAction using the ChangeCoordsEdit superclass
 * for providing undo/redo functionality
 * 
 * @author tohel
 * @cdk.module control
 */
public class FlipEdit extends ChangeCoordsEdit {

    private static final long serialVersionUID = -8590216354736212213L;

    /**
	 * @param atomCoordsMap
	 *            A HashMap containing the changed atoms as key and an Array
	 *            with the former and the changed coordinates as Point2ds
	 */
	public FlipEdit(HashMap atomCoordsMap) {
		super(atomCoordsMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.undo.UndoableEdit#getPresentationName()
	 */
	public String getPresentationName() {
		return "Flip";
	}
}
