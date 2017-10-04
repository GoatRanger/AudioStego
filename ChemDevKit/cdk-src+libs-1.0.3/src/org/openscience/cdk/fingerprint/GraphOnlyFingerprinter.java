/* $Revision: 7673 $ $Author: egonw $ $Date: 2007-01-09 10:39:35 +0100 (Tue, 09 Jan 2007) $
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
package org.openscience.cdk.fingerprint;

import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.openscience.cdk.interfaces.IAtomContainer;

/**
 * Specialized version of the Fingerprinter which does not take bond orders
 * into account.
 *
 * @author         egonw
 * @cdk.created    2007-01-11
 * @cdk.keyword    fingerprint
 * @cdk.keyword    similarity
 * @cdk.module     standard
 * 
 * @see            org.openscience.cdk.fingerprint.Fingerprinter
 */
public class GraphOnlyFingerprinter extends Fingerprinter {
	
	/**
	 * Creates a fingerprint generator of length <code>defaultSize</code>
	 * and with a search depth of <code>defaultSearchDepth</code>.
	 */
	public GraphOnlyFingerprinter() {
		super(defaultSize, defaultSearchDepth);
	}
	
	public GraphOnlyFingerprinter(int size) {
		super(size, defaultSearchDepth);
	}
	
	public GraphOnlyFingerprinter(int size, int searchDepth) {
		super(size, searchDepth);
	}

	/**
	 * Gets the bondSymbol attribute of the Fingerprinter class. Because we do
	 * not consider bond orders to be important, we just return "";
	 *
	 * @param  bond  Description of the Parameter
	 * @return       The bondSymbol value
	 */
	protected String getBondSymbol(org.openscience.cdk.interfaces.IBond bond) {
		return "";
	}

	protected BitSet getFingerprint(IAtomContainer ac, int size) throws Exception {
		Map paths = findPathes(ac, super.getSearchDepth());
		BitSet bs = new BitSet(size);
		for (Iterator e = paths.values().iterator(); e.hasNext(); ) {
			bs.set(new Random(((String)e.next()).hashCode()).nextInt(size));
		}
		return bs;
	}
}

