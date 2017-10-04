/* $RCSfile$
 * $Author: egonw $
 * $Date: 2006-03-29 10:27:08 +0200 (Wed, 29 Mar 2006) $
 * $Revision: 5855 $
 * 
 * Copyright (C) 2006-2007  The Chemistry Development Kit (CDK) project
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
package org.openscience.cdk.qsar;

import java.util.HashMap;
import java.util.Map;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;

/**
 * Abstract bond descriptor class with helper functions for descriptors
 * that require the whole molecule to calculate the descriptor values,
 * which in turn need to be cached for all bonds, so that they can be
 * retrieved one by one.
 *
 * @cdk.module qsar
 */
public abstract class AbstractBondDescriptor implements IBondDescriptor {
    
	private static final String PREVIOUS_ATOMCONTAINER = "previousAtomContainer";
	
	private Map cachedDescriptorValues = null;
	
	/**
	 * Returns the cached DescriptorValue for the given IBond.
	 * 
	 * @param bond the IAtom for which the DescriptorValue is requested
	 * @return     null, if no DescriptorValue was cached for the given IBond
	 */
	public DescriptorValue getCachedDescriptorValue(IBond bond) {
		if (cachedDescriptorValues == null) return null;
		return (DescriptorValue)cachedDescriptorValues.get(bond);
	}
	
	/**
	 * Caches a DescriptorValue for a given IBond. This method may only
	 * be called after setNewContainer() is called.
	 * 
	 * @param bond  IBond to cache the value for
	 * @param value DescriptorValue for the given IBond
	 */
	public void cacheDescriptorValue(IBond bond, IAtomContainer container, DescriptorValue value) {
		if (cachedDescriptorValues == null) {
			cachedDescriptorValues = new HashMap();
			cachedDescriptorValues.put(PREVIOUS_ATOMCONTAINER, container);
		} else if (cachedDescriptorValues.get(PREVIOUS_ATOMCONTAINER) != container) {
			cachedDescriptorValues.clear();
			cachedDescriptorValues.put(PREVIOUS_ATOMCONTAINER, container);
		}
		cachedDescriptorValues.put(bond, value);
	}
}

