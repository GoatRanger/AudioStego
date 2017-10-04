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


// ASSUMES YOU WILL NEVER TRY TO ADD MORE THAN 100 OBJECTS
// ALSO DOES NO SYCHRONIZATION SO POSSIBLE PROBLEMS WITH CONCURRENT ACCESS
package bolo;

import java.awt.*;


class Pool {

    private static final int MAX_SIZE = 1000;
    private int size = 0;
    private Thing[] a = new Thing[MAX_SIZE];

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    Iterator getIterator() {

        return new Iterator();
    }

    /**
     * Undocumented
     * 
     * @param thing Empty
     */
    void add(Thing thing) {

        // ignore overflow!!!!!!
        a[size++] = thing;
        
    }

    /**
     * Undocumented
     * 
     * @param g Empty
     */
    void draw(Graphics2D g) {

        int i = 0;

        while (i < size) {

            if (a[i].obsolete) {
                a[i] = a[--size];
                a[size] = null;
            } else {
                a[i++].draw(g);
            }
        }
    }

    /**
     * Undocumented
     * 
     * @param deltaT Empty
     */
    void tick(double deltaT) {

        int i = 0;

        while (i < size) {

            if (a[i].obsolete) {
                a[i] = a[--size];
                a[size] = null;
            } else {
                a[i++].tick(deltaT);
            }
        }
    }

    class Iterator {

        private int i;

        private Iterator() {
            i = 0;
        }

        private Iterator(int start) {
            i = start;
        }

        Iterator copy() {

            return new Iterator(i);
        }

        Thing next() {

            while (i < size) {

                if (!a[i].obsolete) {

                    return a[i++];
                }
                 else {
                    a[i] = a[--size];
                    a[size] = null;
                }
            }

            return null;
        }
    }
}