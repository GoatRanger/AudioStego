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


class Explosion
    extends Transient {

    Shape blastShape;
    double blastStrength;
    double duration;
    int sound;
    double x;
    double y;

    Explosion(double x, double y, Color color, Shape blastShape, 
              double strength, double duration, int sound) {
        super(duration);
        this.x = x;
        this.y = y;
        this.duration = duration;
        this.color = color;
        this.blastShape = blastShape;
        this.sound = sound;
        shape = Util.translate(blastShape, x, y);
        blastStrength = strength;
        Arena.actionQueue.add(this);
    }

    /**
     * Undocumented
     */
    void action() {
        Sound.play(sound);
        Arena.visibleThings.add(this);
        Arena.timedThings.add(this);
        new DelayedAction(0.3, 
                          new Action() {
            public void action() {
                checkBlastArea();
            }
        });
    }

    // damage those things caught in the blast

    /**
     * Undocumented
     */
    void checkBlastArea() {

        Pool.Iterator it = Arena.vulnerableThings.getIterator();

        for (Thing thing = it.next(); thing != null; thing = it.next()) {

            if (overlaps(thing)) {
                thing.damage(blastStrength);
            }
        }
    }

    /**
     * Undocumented
     * 
     * @param deltaT Empty
     */
    void tick(double deltaT) {
        super.tick(deltaT);

        if (!obsolete) {

            // blast expands over time
            double scaleFactor = 1.0
                                 - 0.75 * Math.pow(remainingTime / duration, 
                                                   5.0);
            Shape scaledShape = Util.scale(blastShape, scaleFactor, 
                                           scaleFactor);
            shape = Util.translate(scaledShape, x, y);
        }
    }
} // end class Explosion