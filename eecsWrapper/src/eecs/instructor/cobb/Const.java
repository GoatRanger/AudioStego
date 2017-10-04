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

package eecs.instructor.cobb;

import java.awt.*;


class Const {

    // colors
    static final Color BACKGROUND_COLOR = Color.white;
    static final int BOTTOM = 560;
    static final int BOTTOM_BORDER = 36;
    static final Color CLOCK_COLOR = Color.black;
    static final Font CLOCK_FONT = new Font("Sans-serif", Font.BOLD, 12);
    static final double COLLISION_DAMAGE = 10;
    static final double EAST = 0.0;
    static final double EXPLOSION_DURATION = 1.0; // seconds

    // health
    static final double HEALING_RATE = 0.2;
    static final Color HEALTHY_COLOR = Color.green;

    // thread priorities
    static final int HIGH_PRIORITY = Thread.MAX_PRIORITY; // main animation loop
    static final Font INITIALS_FONT = new Font("Sans-serif", Font.PLAIN, 8);
    static final int INITIAL_DELAY = 500; // in ms

    // dimensions of playing field (the coordinate of the INSIDE edge)
    static final int LEFT = 4;
    static final int LOW_PRIORITY = Thread.MIN_PRIORITY; // robots
    static final Color MISSILE_EXPLOSION_COLOR = new Color(1.0f, 0.2f, 0.0f, 
                                                           0.7f);

    // damage
    static final double MISSILE_EXPLOSION_DAMAGE = 30;

    // size
    static final double MISSILE_EXPLOSION_RADIUS = 20.0;

    // fonts
    static final Font NAME_FONT = new Font("Sans-serif", Font.PLAIN, 10);

    // angles
    static final double NORTH = 1.5 * Math.PI;

    // starting positions of robots
    static final double OFFSET = 28;

    // file names for robots
    static final String PREFIX = "TEAM_";
    static final double RELOAD_DELAY = 3.0; // in seconds
    static final int RIGHT = 700;
    static final int RIGHT_BORDER = 10;
    static final Color ROBOT_COLOR = Color.black;
    static final Color ROBOT_EXPLOSION_COLOR = new Color(1.0f, 0.8f, 0.0f, 
                                                         0.7f);
    static final double ROBOT_EXPLOSION_DAMAGE = 35;
    static final double ROBOT_EXPLOSION_RADIUS = 40.0;
    static final double SCAN_ACCURACY = 0.5;
    static final Color SCAN_COLOR = new Color(0.0f, 0.0f, 1.0f, 0.4f);
    static final double SCAN_DURATION = 0.15; // seconds

    // distance
    static final double SCAN_RANGE = 1000.0;
    static final Color SICK_COLOR = Color.red;
    static final double SOUTH = 0.5 * Math.PI;

    // width of health lines
    static final double STATUS_WIDTH = 3.0;
    static final String SUFFIX = ".class";
    static final int TIME_BETWEEN_TICKS = 50; // in ms (if using ticker thread)

    // time
    static final double TIME_LIMIT = 120.25; // in seconds
    static final int TIME_PER_TICK = 100; // in ms (if using timer)

    // title of main window
    static final String TITLE = "Bolo";
    static final int TOP = 16;
    static final Color WALL_COLOR = Color.black;

    // borders around playing field
    static final int WALL_WIDTH = 2;
    static final double WEST = Math.PI;
    static final double WIN_DELAY = 3.0; // in seconds

    // clock position
    static final float CLOCK_X = (RIGHT + RIGHT_BORDER - 24) / 2.0f;
    static final float CLOCK_Y = TOP - 3;
    static final double[] START_X = new double[] {
        LEFT + OFFSET, RIGHT - OFFSET, (LEFT + RIGHT) / 2, (LEFT + RIGHT) / 2
    };
    static final double[] START_Y = new double[] {
        (TOP + BOTTOM) / 2, (TOP + BOTTOM) / 2, TOP + OFFSET, BOTTOM - OFFSET
    };
    static final double[] START_HEADING = new double[] {
        EAST, WEST, SOUTH, NORTH
    };

    // shapes
   /* static final Shape MISSILE_EXPLOSION_SHAPE = Util.circle(
                                                         MISSILE_EXPLOSION_RADIUS);
    static final Shape ROBOT_EXPLOSION_SHAPE = Util.circle(
                                                       ROBOT_EXPLOSION_RADIUS);
    static final Shape[] WALL_SHAPES = new Shape[] {

        Util.rectangle(LEFT - WALL_WIDTH / 2, (TOP + BOTTOM) / 2.0, WALL_WIDTH, 
                       BOTTOM - TOP + 2 * WALL_WIDTH), 
        Util.rectangle(RIGHT + WALL_WIDTH / 2, (TOP + BOTTOM) / 2.0, 
                       WALL_WIDTH, BOTTOM - TOP + 2 * WALL_WIDTH), 
        Util.rectangle((LEFT + RIGHT) / 2.0, TOP - WALL_WIDTH / 2, 
                       RIGHT - LEFT + WALL_WIDTH, WALL_WIDTH), 
        Util.rectangle((LEFT + RIGHT) / 2.0, BOTTOM + WALL_WIDTH / 2, 
                       RIGHT - LEFT + WALL_WIDTH, WALL_WIDTH)
    };*/
}