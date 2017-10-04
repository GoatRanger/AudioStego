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


abstract class Mobile
    extends Thing {

    double heading; // in radians
    double radius; // all mobile things are circular
    double rebound = 0.0;
    double velocity;
    double x;
    double y;
    Shape zeroShape; // maintain the shape at the origin

    /**
     * Undocumented
     * 
     * @param other Empty
     */
    void collideWith(Thing other) {}

    /**
     * Undocumented
     */
    void collideWithWall() {}

    /**
     * Undocumented
     * 
     * @param dx Empty
     * @param dy Empty
     */
    void move(double dx, double dy) {
        x += dx;
        y += dy;
        shape = Util.translate(zeroShape, x, y);
    }

    /**
     * Undocumented
     * 
     * @param deltaT Empty
     */
    void tick(double deltaT) {

        double distance = velocity * deltaT;
        move(distance * Math.cos(heading), distance * Math.sin(heading));
    }
}