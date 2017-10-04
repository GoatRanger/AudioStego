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


class Chaff
    extends Ammunition {

    static final int BASIC_LOAD = 1000;
    static final Color COLOR = Color.black;
    static final double DAMAGE = 1;
    static final Color EX_COLOR = new Color(0.3f, 0.3f, 0.3f, 0.4f);
    static final double EX_DURATION = 2.0;
    static final Shape EX_SHAPE = Util.circle(45);
    static final double MAX_DISTANCE = 50;
    static final double MIN_DISTANCE = 1;
    static final double RADIUS = 2;
    static final double RELOAD_DELAY = 3.0;
    static final double VELOCITY = 100;
    static final Shape SHAPE = Util.circle(RADIUS);
    static Ammunition.Properties properties = new Ammunition.Properties(
                                                      MIN_DISTANCE, 
                                                      MAX_DISTANCE, VELOCITY, 
                                                      RELOAD_DELAY);

    private Chaff(double x, double y, double heading, double distance) {
        super(x, y, heading, distance, VELOCITY, RADIUS, COLOR, SHAPE);
        Arena.actionQueue.add(this);
    }

    /**
     * Undocumented
     */
    void action() {
        super.action();
        Sound.play(Sound.FIRE_MISSILE);
    }

    /**
     * Undocumented
     */
    void explode() {
        new Explosion(x, y, EX_COLOR, EX_SHAPE, DAMAGE, EX_DURATION, 
                      Sound.MISSILE_EXPLOSION) {
            void action() {
                super.action();
                Arena.scannableThings.add(this);
            }
        };

        obsolete = true;
    }

    static Weapon newWeapon() {

        return new Weapon(BASIC_LOAD, 
                          properties, 
                          new Chaff.Factory());
    }

    static class Factory
        implements Ammunition.Factory {
        public void makeInstance(double x, double y, double heading, 
                                 double distance) {
            new Chaff(x, y, heading, distance);
        }
    }
}