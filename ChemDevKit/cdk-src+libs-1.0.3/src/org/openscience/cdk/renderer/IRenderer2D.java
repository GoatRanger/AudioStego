/* $Revision: 7074 $ $Author: egonw $ $Date: 2006-09-28 19:00:34 +0200 (Thu, 28 Sep 2006) $
 *  
 * Copyright (C) 2002-2007  Egon Willighagen <egonw@users.sf.net>
 *
 * Contact: cdk-devel@lists.sourceforge.net
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
package org.openscience.cdk.renderer;

import java.awt.Graphics2D;

import org.openscience.cdk.interfaces.IChemModel;
import org.openscience.cdk.interfaces.IMoleculeSet;
import org.openscience.cdk.interfaces.IReaction;
import org.openscience.cdk.interfaces.IReactionSet;

/**
 * @author      egonw
 * @cdk.created 2006-10-25
 * @cdk.module  render
 */
public interface IRenderer2D extends ISimpleRenderer2D {

	/**
	 * Triggers a paint of an IChemModel.
	 *
	 * @param  model     IChemModel to draw
	 * @param  graphics  Graphics2D object to draw too
	 */
	public abstract void paintChemModel(IChemModel model, Graphics2D graphics);

	/**
	 * Triggers a paint of an IReactionSet.
	 *
	 * @param  reactionSet  IReactionSet to draw
	 * @param  graphics     Graphics2D object to draw too
	 */
	public abstract void paintReactionSet(IReactionSet reactionSet, Graphics2D graphics);

	/**
	 * Triggers a paint of an IMoleculeSet.
	 *
	 * @param  moleculeSet  IMoleculeSet to draw
	 * @param  graphics     Graphics2D object to draw too
	 */
	public abstract void paintMoleculeSet(IMoleculeSet moleculeSet, Graphics2D graphics);

	/**
	 * Triggers a paint of an IReaction.
	 *
	 * @param  reaction  IReaction to draw
	 * @param  graphics  Graphics2D object to draw too
	 */
	public abstract void paintReaction(IReaction reaction, Graphics2D graphics);

}