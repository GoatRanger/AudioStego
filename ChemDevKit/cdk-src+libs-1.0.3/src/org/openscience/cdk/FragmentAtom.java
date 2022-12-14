/* $Revision: 6963 $ $Author: egonw $$Date: 2006-09-20 12:48:23 +0200 (Wed, 20 Sep 2006) $
 *
 * Copyright (C) 2006-2007  Egon Willighagen <ewilligh@uni-koeln.de>
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
package org.openscience.cdk;

import org.openscience.cdk.interfaces.IAtomContainer;

/**
 * @cdk.module data
 * 
 * @author egonw
 */
public class FragmentAtom extends PseudoAtom {

	private static final long serialVersionUID = -6144605920605752463L;

	private IAtomContainer fragment;
	private boolean isExpanded;
	
	public FragmentAtom() {
		fragment = this.getBuilder().newAtomContainer();
		isExpanded = false;
	}
	
	public boolean isExpanded() {
		return isExpanded;
	}
	
	public void setExpanded(boolean bool) {
		this.isExpanded = bool;
	}

	public IAtomContainer getFragment() {
		return fragment;
	}

	public void setFragment(IAtomContainer fragment) {
		this.fragment = fragment;
	}
	
}
