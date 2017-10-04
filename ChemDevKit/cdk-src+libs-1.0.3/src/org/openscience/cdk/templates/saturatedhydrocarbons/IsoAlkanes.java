/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-01-04 18:46:10 +0100 (Thu, 04 Jan 2007) $
 * $Revision: 7636 $
 *
 * Copyright (C) 2002-2007  The Chemistry Development Kit (CDK) project
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
package org.openscience.cdk.templates.saturatedhydrocarbons;

import org.openscience.cdk.Atom;
import org.openscience.cdk.Molecule;

/**
 * This class contains methods for generating simple organic alkanes.
 *
 * @cdk.keyword templates
 */
public class IsoAlkanes {

    public static Molecule getIsobutane() {
        Molecule mol = new Molecule();
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));

        mol.addBond(0, 1, 1);
        mol.addBond(0, 2, 1);
        mol.addBond(0, 3, 1);
        return mol;
    }

    public static Molecule getIsopentane() {
        Molecule mol = new Molecule();
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));

        mol.addBond(0, 1, 1);
        mol.addBond(0, 2, 1);
        mol.addBond(0, 3, 1);
        mol.addBond(3, 4, 1);
        return mol;
    }

    public static Molecule getIsohexane() {
        Molecule mol = new Molecule();
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));
        mol.addAtom(new Atom("C"));

        mol.addBond(0, 1, 1);
        mol.addBond(0, 2, 1);
        mol.addBond(0, 3, 1);
        mol.addBond(3, 4, 1);
        mol.addBond(4, 5, 1);
        return mol;
    }

}
