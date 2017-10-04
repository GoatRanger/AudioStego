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

import org.openscience.cdk.config.IsotopeFactory;
import org.openscience.cdk.interfaces.IAtom;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.io.IOException;
import java.io.OptionalDataException;

/**
 * Undo/Redo Edit class for the ChangeAtomSymbolAction, containing the methods
 * for undoing and redoing the regarding changes
 * 
 * @author tohel
 * @cdk.module control
 */
public class ChangeAtomSymbolEdit extends AbstractUndoableEdit {

    private static final long serialVersionUID = 779682083223003185L;

    private IAtom atom;

	private String formerSymbol;

	private String symbol;

	/**
	 * @param atomInRange
	 *            The atom been changed
	 * @param formerSymbol
	 *            The atom symbol before change
	 * @param symbol
	 *            The atom symbol past change
	 */
	public ChangeAtomSymbolEdit(IAtom atomInRange, String formerSymbol,
			String symbol) {
		this.atom = atomInRange;
		this.formerSymbol = formerSymbol;
		this.symbol = symbol;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.undo.UndoableEdit#redo()
	 */
	public void redo() throws CannotRedoException {
		this.atom.setSymbol(symbol);
		try {
			IsotopeFactory.getInstance(atom.getBuilder()).configure(atom);
		} catch (OptionalDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.undo.UndoableEdit#undo()
	 */
	public void undo() throws CannotUndoException {
		this.atom.setSymbol(formerSymbol);
		try {
			IsotopeFactory.getInstance(atom.getBuilder()).configure(atom);
		} catch (OptionalDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.undo.UndoableEdit#canRedo()
	 */
	public boolean canRedo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.undo.UndoableEdit#canUndo()
	 */
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.undo.UndoableEdit#getPresentationName()
	 */
	public String getPresentationName() {
		return "ChangeAtomSymbol";
	}

}
