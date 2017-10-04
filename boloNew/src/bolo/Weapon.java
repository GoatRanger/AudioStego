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

class Weapon {

    final Ammunition.Factory factory;
    final Ammunition.Properties properties;
    int rounds;

    Weapon(int rounds, Ammunition.Properties properties, 
           Ammunition.Factory factory) {
        this.rounds = rounds;
        this.properties = properties;
        this.factory = factory;
    }

    /**
     * Undocumented
     * 
     * @param x Empty
     * @param y Empty
     * @param heading Empty
     * @param distance Empty
     * @return Empty 
     */
    boolean fire(double x, double y, double heading, double distance) {

        if (rounds == 0) {

            return false;
        }
         else {
            rounds--;
            factory.makeInstance(x, y, heading, distance);

            return true;
        }
    }
}