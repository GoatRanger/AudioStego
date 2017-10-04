/* $Revision: 7740 $ $Author: egonw $ $Date: 2007-01-20 14:51:41 +0100 (Sat, 20 Jan 2007) $
 *
 * Copyright (C) 2003-2007  Miguel Howard <miguel@jmol.org>
 *
 * Contact: cdk-devel@lists.sf.net
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA. 
 */
package org.openscience.cdk.graph.rebond;


/**
 * @author      Miguel Howard
 * @cdk.created 2003-05
 * @cdk.module  standard
 */
public class Point implements Bspt.Tuple {
  double x;
  double y;
  double z;

  public Point(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double getDimValue(int dim) {
    if (dim == 0)
      return x;
    if (dim == 1)
      return y;
    return z;
  }

  public String toString() {
    return "<" + x + "," + y + "," + z + ">";
  }
}
