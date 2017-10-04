/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-10-14 20:07:41 +0200 (Sun, 14 Oct 2007) $
 *  $Revision: 9044 $
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

import java.util.Iterator;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.PseudoAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.isomorphism.matchers.smarts.AnyAtom;
import org.openscience.cdk.isomorphism.matchers.smarts.AnyOrderQueryBond;
import org.openscience.cdk.isomorphism.matchers.smarts.AromaticAtom;
import org.openscience.cdk.isomorphism.matchers.smarts.AromaticQueryBond;

/**
 *@cdk.module    extra
 */
public class QueryAtomContainerCreator {

    /**
     *  Creates a QueryAtomContainer with SymbolQueryAtom's and
     *  OrderQueryBond's.
     *
     *@param  container  The AtomContainer that stands as model
     *@return            The new QueryAtomContainer created from container.
     */
    public static QueryAtomContainer createBasicQueryContainer(IAtomContainer container) {
        QueryAtomContainer queryContainer = new QueryAtomContainer();
        for (int i = 0; i < container.getAtomCount(); i++) {
            queryContainer.addAtom(new SymbolQueryAtom(container.getAtom(i)));
        }
        Iterator bonds = container.bonds();
        while (bonds.hasNext()) {
        	IBond bond = (IBond)bonds.next();
            int index1 = container.getAtomNumber(bond.getAtom(0));
            int index2 = container.getAtomNumber(bond.getAtom(1));
            if (bond.getFlag(CDKConstants.ISAROMATIC)) {
                queryContainer.addBond(new AromaticQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                                      (IQueryAtom) queryContainer.getAtom(index2),
                                      1.5));
            } else {
                queryContainer.addBond(new OrderQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                                      (IQueryAtom) queryContainer.getAtom(index2),
                                      bond.getOrder()));
            }
        }
        return queryContainer;
    }

    
    /**
     *  Creates a QueryAtomContainer with SymbolAncChargeQueryAtom's and
     *  OrderQueryBond's.
     *
     *@param  container  The AtomContainer that stands as model
     *@return            The new QueryAtomContainer created from container.
     */
    public static QueryAtomContainer createSymbolAndChargeQueryContainer(IAtomContainer container) {
        QueryAtomContainer queryContainer = new QueryAtomContainer();
        for (int i = 0; i < container.getAtomCount(); i++) {
            queryContainer.addAtom(new SymbolAndChargeQueryAtom(container.getAtom(i)));
        }
        Iterator bonds = container.bonds();
        while (bonds.hasNext()) {
        	IBond bond = (IBond)bonds.next();
            int index1 = container.getAtomNumber(bond.getAtom(0));
            int index2 = container.getAtomNumber(bond.getAtom(1));
            if (bond.getFlag(CDKConstants.ISAROMATIC)) {
                queryContainer.addBond(new AromaticQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                                      (IQueryAtom) queryContainer.getAtom(index2),
                                      1.5));
            } else {
                queryContainer.addBond(new OrderQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                                      (IQueryAtom) queryContainer.getAtom(index2),
                                      bond.getOrder()));
            }
        }
        return queryContainer;
    }    
    
    public static QueryAtomContainer createSymbolChargeIDQueryContainer(IAtomContainer container) {
        QueryAtomContainer queryContainer = new QueryAtomContainer();
        for (int i = 0; i < container.getAtomCount(); i++) {
            queryContainer.addAtom(new SymbolChargeIDQueryAtom(container.getAtom(i)));
        }
        Iterator bonds = container.bonds();
        while (bonds.hasNext()) {
        	IBond bond = (IBond)bonds.next();
            int index1 = container.getAtomNumber(bond.getAtom(0));
            int index2 = container.getAtomNumber(bond.getAtom(1));
            if (bond.getFlag(CDKConstants.ISAROMATIC)) {
                queryContainer.addBond(new AromaticQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                                      (IQueryAtom) queryContainer.getAtom(index2),
                                      1.5));
            } else {
                queryContainer.addBond(new OrderQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                                      (IQueryAtom) queryContainer.getAtom(index2),
                                      bond.getOrder()));
            }
        }
        return queryContainer;
    }    

    /**
     *  Creates a QueryAtomContainer with AnyAtoms / Aromatic Atoms and OrderQueryBonds / AromaticQueryBonds.
     *  It uses the CDKConstants.ISAROMATIC flag to determine the aromaticity of container.
     *
     *@param  container    The AtomContainer that stands as model
     *@param  aromaticity  True = use aromaticity flags to create AtomaticAtoms and AromaticQueryBonds
     *@return              The new QueryAtomContainer created from container
     */
    public static QueryAtomContainer createAnyAtomContainer(IAtomContainer container, boolean aromaticity) {
        QueryAtomContainer queryContainer = new QueryAtomContainer();

        for (int i = 0; i < container.getAtomCount(); i++) {
        	if (aromaticity && container.getAtom(i).getFlag(CDKConstants.ISAROMATIC)) {
                queryContainer.addAtom(new AromaticAtom());
            } else {
                queryContainer.addAtom(new AnyAtom());
            }
        }

        Iterator bonds = container.bonds();
        while (bonds.hasNext()) {
        	IBond bond = (IBond)bonds.next();
            int index1 = container.getAtomNumber(bond.getAtom(0));
            int index2 = container.getAtomNumber(bond.getAtom(1));
            if (aromaticity && bond.getFlag(CDKConstants.ISAROMATIC)) {
                queryContainer.addBond(new AromaticQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                        (IQueryAtom) queryContainer.getAtom(index2),
                        1.5));
            } else {
                queryContainer.addBond(new OrderQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                        (IQueryAtom) queryContainer.getAtom(index2),
                        bond.getOrder()));
            }
        }
        return queryContainer;
    }

    /**
     * Creates a QueryAtomContainer with wildcard atoms and wildcard bonds.
     * <p/>
     * This method thus allows the user to search based only on connectivity.
     *
     * @param container   The AtomContainer that stands as the model
     * @param aromaticity If True, aromaticity flags are checked to create AromaticAtoms
     *                    and AromaticQueryBonds
     * @return The new QueryAtomContainer
     */
    public static QueryAtomContainer createAnyAtomAnyBondContainer(IAtomContainer container, boolean aromaticity) {
        QueryAtomContainer queryContainer = new QueryAtomContainer();

        for (int i = 0; i < container.getAtomCount(); i++) {
            if (aromaticity && container.getAtom(i).getFlag(CDKConstants.ISAROMATIC)) {
                queryContainer.addAtom(new AromaticAtom());
            } else {
                queryContainer.addAtom(new AnyAtom());
            }
        }

        Iterator bonds = container.bonds();
        while (bonds.hasNext()) {
        	IBond bond = (IBond)bonds.next();
            int index1 = container.getAtomNumber(bond.getAtom(0));
            int index2 = container.getAtomNumber(bond.getAtom(1));
            queryContainer.addBond(new AnyOrderQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                    (IQueryAtom) queryContainer.getAtom(index2),
                    bond.getOrder()));
        }
        return queryContainer;
    }

    /**
     *  Creates a QueryAtomContainer with SymbolQueryAtom's and
     *  OrderQueryBond's. Each PseudoAtom will be replaced by a 
     *  AnyAtom
     *
     *@param  container  The AtomContainer that stands as model
     *@return            The new QueryAtomContainer created from container.
     */
    public static QueryAtomContainer createAnyAtomForPseudoAtomQueryContainer(IAtomContainer container) {
        QueryAtomContainer queryContainer = new QueryAtomContainer();
        for (int i = 0; i < container.getAtomCount(); i++) {
        	if (container.getAtom(i) instanceof PseudoAtom) {
				queryContainer.addAtom(new AnyAtom());
			}
        	else {
        		queryContainer.addAtom(new SymbolQueryAtom(container.getAtom(i)));
			}
            
        }
        Iterator bonds = container.bonds();
        while (bonds.hasNext()) {
        	IBond bond = (IBond)bonds.next();
            int index1 = container.getAtomNumber(bond.getAtom(0));
            int index2 = container.getAtomNumber(bond.getAtom(1));
            if (bond.getFlag(CDKConstants.ISAROMATIC)) {
                queryContainer.addBond(new AromaticQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                                      (IQueryAtom) queryContainer.getAtom(index2),
                                      1.5));
            } else {
                queryContainer.addBond(new OrderQueryBond((IQueryAtom) queryContainer.getAtom(index1),
                                      (IQueryAtom) queryContainer.getAtom(index2),
                                      bond.getOrder()));
            }
        }
        return queryContainer;
    }
}

