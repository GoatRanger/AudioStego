/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-02-16 13:56:34 +0100 (Fri, 16 Feb 2007) $
 * $Revision: 7957 $
 *
 * Copyright (C) 2006-2007  Egon Willighagen <egonw@users.sf.net>
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
package org.openscience.cdk.interfaces;

/**
 * A Object containing a number of ChemSequences. This is supposed to be the
 * top level container, which can contain all the concepts stored in a chemical
 * document
 *
 * @author     egonw
 * @cdk.module interfaces
 */
public interface IChemFile extends IChemObject {

	/**
	 * Adds an ChemSequence to this container.
	 *
	 * @param  chemSequence  The chemSequence to be added to this container
	 * @see                  #chemSequences
	 */
	public void addChemSequence(IChemSequence chemSequence);

	/**
	 *  Removes a ChemSequence from this container.
	 *
	 *@param  chemSequence  The chemSequence to be added to this container
	 *@see                  #chemSequences
	 */
	public void removeChemSequence(int pos);
	
	/**
	 *  Returns the Iterator to ChemSequences of this container.
	 *
	 *@return    The Iterator to ChemSequences of this container
	 *@see       #addChemSequence
	 */
	public java.util.Iterator chemSequences();

	/**
	 * Returns the ChemSequence at position <code>number</code> in the container.
	 *
	 * @param  number  The position of the ChemSequence to be returned.
	 * @return         The ChemSequence at position <code>number</code>.
	 * @see            #addChemSequence
	 */
	public IChemSequence getChemSequence(int number);

	/**
	 * Returns the number of ChemSequences in this Container.
	 *
	 * @return    The number of ChemSequences in this Container
	 */
	public int getChemSequenceCount();

}

