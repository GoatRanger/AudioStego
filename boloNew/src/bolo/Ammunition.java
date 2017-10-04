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


abstract class Ammunition
    extends Mobile {

    double remainingTime;

    Ammunition(double x, double y, double heading, double distance, 
               double velocity, double radius, Color color, Shape zeroShape) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.velocity = velocity;
        this.radius = radius;
        this.color = color;
        this.zeroShape = zeroShape;
        shape = Util.translate(zeroShape, x, y);
        remainingTime = distance / velocity;
    }

    abstract void explode();

    /**
     * Undocumented
     */
    void action() {
        Arena.timedThings.add(this);
        Arena.visibleThings.add(this);
        Arena.mobileThings.add(this);
    }

    /**
     * Undocumented
     */
    void collideWithWall() {
        explode();
    }

    /**
     * Undocumented
     * 
     * @param deltaT Empty
     */
    void tick(double deltaT) {
        super.tick(deltaT);

        // could explode during super.tick, so check if obsolete
        // IS THAT STILL TRUE?
        if (!obsolete) {
            remainingTime -= deltaT;

            if (remainingTime <= 0.0) {
                explode();
            }
        }
    }

    interface Factory {
        void makeInstance(double x, double y, double theta, double distance);
    }

    static class Properties {

        final double maxRange;
        final double minRange;
        final double reloadDelay;
        final double velocity;

        Properties(double minRange, double maxRange, double velocity, 
                   double delay) {
            this.minRange = minRange;
            this.maxRange = maxRange;
            this.velocity = velocity;
            this.reloadDelay = delay;
        }
    }
}