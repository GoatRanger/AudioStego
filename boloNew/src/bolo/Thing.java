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


// the root class for all bolo objects
abstract class Thing
    extends Action {

    Color color;
    boolean obsolete = false;
    Shape shape;

    /**
     * Undocumented
     * 
     * @param other Empty
     */
    void collideWith(Thing other) {}

    /**
     * Undocumented
     * 
     * @param dmg Empty
     */
    void damage(double dmg) {}

    /**
     * Undocumented
     * 
     * @param g Empty
     */
    void draw(Graphics2D g) {
        g.setPaint(color);
        g.fill(shape);
    }

    /**
     * Undocumented
     * 
     * @param other Empty
     * @return Empty 
     */
    boolean overlaps(Thing other) {

        return Util.overlaps(this.shape, other.shape);
    }

    // the following methods should only be called by the animation thread

    /**
     * Undocumented
     * 
     * @param deltaT Empty
     */
    void tick(double deltaT) {}
} // end class Thing