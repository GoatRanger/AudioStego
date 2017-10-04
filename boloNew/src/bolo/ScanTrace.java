/* Copyright (C) 2002  USMA
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.
 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package bolo;

import java.awt.*;
import java.awt.geom.*;


class ScanTrace
    extends Transient {
    ScanTrace(double x, double y, double radius, double heading, 
              double distance) {
        super(Const.SCAN_DURATION);
        color = Const.SCAN_COLOR;

        Shape line = new Rectangle2D.Double(radius, -0.5, distance - radius, 1);
        line = Util.rotate(line, heading);
        shape = Util.translate(line, x, y);
    }

    /**
     * Undocumented
     */
    void action() {}
}