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
 * PusherRobot.java
 *
 * Created on November 17, 2001, 8:53 PM
 */
package eecs.instructor.cobb;

import jago.element.*;




/**
 * A robot that is designed to push <CODE>Pushable</CODE> objects.  The
 * differernce in implementation between this robot and a
 * <CODE>StandardRobot</CODE> is that the standard robot assumes it is
 * blocked when it makes contact with any object, while this robot will
 * attempt to continue moving when it contacts a <CODE>Pushable</CODE>
 * object.  This robot is also implemented with a bumper
 * (<CODE>Sensor.TOUCH</CODE>) connected to RCX Input 2 and a light sensor
 * (<CODE>Sensor.LIGHT</CODE>) connected to RCX Input 1, both assumed to
 * react to any contact across the entire front of the robot.
 * 
 * @version 1.0
 * @author Karl A. Gossett
 */
public class FootballRobot
    extends LegoRobot {

    /**
     * Set to <CODE>true</CODE> if the robot has collided with something
     * Pushable.
     */

    public FootballRobot() {
        super();
        
    }

    /**
     * Performs actions when a collision occurs. The current implementation
     * assumes that any TOUCH sensor will react to any collision on the front
     * of the robot, and that any light sensor will react to reflections
     * across most of the front of the robot.
     * 
     * @param collider   A collidable actor to react to
     */
    
}