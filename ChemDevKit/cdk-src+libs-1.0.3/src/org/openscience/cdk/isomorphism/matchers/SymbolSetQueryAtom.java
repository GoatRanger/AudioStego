/*
 *  $RCSfile$
 *  $Author: sushil_ronghe $
 *  $Date: 2007-04-17 20:49:16 +0200 (Tue, 17 Apr 2007) $
 *  $Revision: 8208 $
 *
 *  Copyright (C) 2004-2007  The Chemistry Development Kit (CDK) project
 *
 *  Contact: cdk-devel@lists.sourceforge.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.isomorphism.matchers;

import java.util.HashSet;

import org.openscience.cdk.interfaces.IAtom;

/**
 *  A QueryAtom that matches all symbols in this container. You may add symbols
 *  to this container. This QueryAtom will only give a match if it contains the
 *  symbol of the Atom to match (example: add "F", "Cl", "Br", "I" to get a
 *  match for the most common halogens).
 *
 *@author        kha
 *@cdk.created   2004-09-16
 *@see           InverseSymbolSetQueryAtom
 *@cdk.module    extra
 */
public class SymbolSetQueryAtom extends org.openscience.cdk.PseudoAtom implements IQueryAtom {

    private static final long serialVersionUID = 7539577277779603551L;
    
    private HashSet symbols = new HashSet();

    /**
     *  Constructor for the SymbolSetQueryAtom object
     */
    public SymbolSetQueryAtom() { }
    public void setOperator(String str){}

    /**
     *  The matches implementation of the QueryAtom interface.
     *
     *@param  atom  The atom to be matched by this QueryAtom
     *@return       true if Atom matched
     */
    public boolean matches(IAtom atom) {
        return symbols.contains(atom.getSymbol());
    }


    /**
     *  Add a symbol to this QueryAtom
     *
     *@param  symbol  The symbol to add
     */
    public void addSymbol(String symbol) {
        symbols.add(symbol);
    }


    /**
     *  Remove a symbol from this QueryAtom
     *
     *@param  symbol  The symbol to remove
     */
    public void removeSymbol(String symbol) {
        symbols.remove(symbol);
    }


    /**
     *  Check whether a symbol is already registered
     *
     *@param  symbol  The symbol to check for
     *@return         true if symbol already registered
     */
    public boolean hasSymbol(String symbol) {
        return symbols.contains(symbol);
    }


    /**
     *  Retrieve the Set of symbols
     *
     *@return    The symbol Set
     */
    public HashSet getSymbolSet() {
        return symbols;
    }


    /**
     *  The toString method
     *
     *@return    The String representation of this object.
     */
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("SymbolSetQueryAtom(");
        s.append(this.hashCode() + ", ");
        s.append(symbols.toString());
        s.append(")");
        return s.toString();
    }
}

