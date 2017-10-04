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

/*
 * RobotState.java
 *
 * Created on December 16, 2001, 12:40 PM
 */
package jago.element;


/**
 * This interface contains no functionality; it is used only to maintain the
 * robot state constants.
 *
 * @version 0.1
 * @author Karl A. Gossett
 * @since 5.1
 */
public interface RobotState {

    /** The robot has hit a solid object in its current movement direction */
    static final int BLOCKED = 5;

    /** State representing that the robot is set to go backwards */
    static final int GOING_BACKWARD = 2;

    /** State representing that the robot is set to go forward */
    static final int GOING_FORWARD = 1;

    /** State representing no action */
    static final int NONE = -1;

    /** State representing that the robot is pivoting to the left */
    static final int PIVOTING_LEFT = 4;

    /** State representing that the robot is pivoting to the right */
    static final int PIVOTING_RIGHT = 3;

    /** State representing that the robot is stopped */
    static final int STOPPED = 0;
}