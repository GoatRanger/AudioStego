/* $RCSfile$
 * $Author: djiao $
 * $Date: 2007-05-11 16:02:02 +0200 (Fri, 11 May 2007) $
 * $Revision: 8317 $
 * 
 * Copyright (C) 2004-2007  The Chemistry Development Kit (CDK) project
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
package org.openscience.cdk.isomorphism.matchers.smarts;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.isomorphism.matchers.IQueryAtom;

/**
 * This is just a simple proof of concept, and far from a functional SMARTSAtom.
 * 
 * @cdk.module extra
 */
public abstract class SMARTSAtom extends org.openscience.cdk.PseudoAtom implements
        IQueryAtom {
    public String ID;

    public SMARTSAtom() {
    }

    public void setOperator(String str) {
        ID = str;
    }
    public boolean matches(IAtom atom) {
        return false;
    }


}
