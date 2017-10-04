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

class Clock {

    private static double elapsedTime; // seconds since startTime was set
    private static long startTime; // ms since 1970

    static {
        start();
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public static double read() { // returns time since last start

        return elapsedTime;
    }

    /**
     * Undocumented
     */
    public static void start() {
        startTime = now();
        elapsedTime = 0.0;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public static double update() { // returns time since last update

        double now = (now() - startTime) / 1000.0;
        double deltaT = now - elapsedTime;
        elapsedTime = now;

        return deltaT;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    private static long now() {

        return System.currentTimeMillis();
    }
}