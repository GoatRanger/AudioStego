/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-01-04 18:46:10 +0100 (Thu, 04 Jan 2007) $
 * $Revision: 7636 $
 *
 * Copyright (C) 2003-2007  Egon Willighagen <egonw@users.sf.net>
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
 * 
 */
package org.openscience.cdk;

import org.openscience.cdk.interfaces.IChemObject;

/**
 * A Mapping is an relation between two ChemObjects in a non-chemical
 * entity. It is not a Bond, nor a Association, merely a relation.
 * An example of such a mapping, is the mapping between corresponding atoms
 * in a Reaction.
 *
 * @cdk.module data
 *
 * @cdk.keyword reaction, atom mapping
 *
 * @author  Egon Willighagen
 * @cdk.created 2003-08-16
 */
public class Mapping extends ChemObject implements java.io.Serializable, Cloneable, org.openscience.cdk.interfaces.IMapping {

    /**
     * Determines if a de-serialized object is compatible with this class.
     *
     * This value must only be changed if and only if the new version
     * of this class is imcompatible with the old version. See Sun docs
     * for <a href=http://java.sun.com/products/jdk/1.1/docs/guide
     * /serialization/spec/version.doc.html>details</a>.
	 */
	private static final long serialVersionUID = -6541914644492043503L;

	private IChemObject[] relation;
    
    /**
     * Constructs an unconnected lone pair.
     *
     * @param objectOne The first IChemObject of the mapping
     * @param objectTwo The second IChemObject of the mapping
     */
    public Mapping(IChemObject objectOne, IChemObject objectTwo) {
        relation = new IChemObject[2];
        relation[0] = objectOne;
        relation[1] = objectTwo;
    }

    /**
     * Returns an Iterator to the two IChemObjects.
     * Iterator.remove() is not implemented.
     *
     * @return An Iterator to two IChemObjects that define the mapping
     */
    public java.util.Iterator relatedChemObjects() {
    	return new ChemObjectIterator();
    }
    
    /**
     * The inner Iterator class.
     *
     */
    private class ChemObjectIterator implements java.util.Iterator {

        private int pointer = 0;
    	
        public boolean hasNext() {
            if (pointer < 2) return true;
            return false;
        }

        public Object next() {
            return relation[pointer++];
        }

        public void remove() {}
    	
    }
    
    /**
     * Retrieve the first or second of the related IChemObjects.
     * 
     * @param   pos  The position of the IChemObject.
     * @return  The IChemObject to retrieve.
     */
    public IChemObject getChemObject(int pos) {
    	return relation[pos];
    }
    
	/**
	 * Clones this <code>Mapping</code> and the mapped <code>IChemObject</code>s.
	 *
	 * @return  The cloned object
	 */
	public Object clone() throws CloneNotSupportedException {
		Mapping clone = (Mapping)super.clone();
        // clone the related IChemObject's
        if (relation != null) {
		    ((Mapping)clone).relation = new IChemObject[relation.length];
            for (int f = 0; f < relation.length; f++) {
                if (relation[f] != null) {
                    ((Mapping)clone).relation[f] = (IChemObject)relation[f].clone();
                }
            }
        }
		return clone;
	}
}


