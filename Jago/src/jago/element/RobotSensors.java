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
 * SensorTypes.java
 *
 * Created on December 16, 2001, 12:44 PM
 */
package jago.element;


/**
 * The types of sensors that can be attached to a <I>StandardRobot</I>.
 *
 * @version 0.1
 * @author Karl A. Gossett
 * @since 5.1
 */
public interface RobotSensors {

    /** Identifies the sensor as a compass sensor */
    public static final int COMPASS_SENSOR = 3;

    /** A differential light sensor (to detect direction of light) */
    public static final int DUAL_LIGHT_SENSOR = 6;

    /** A generic HIT (High Intensity Tracking) Sensor (a dual light sensor) */
    public static final int HIT = 6;

    /**
     * Identifies the sensors as an inverted (ends point forward instead  of
     * back) front-mounted bumper.
     */
    public static final int INVERTED_FRONT_BUMPER = 11;

    /**
     * Identifies the sensor as a bumper connected to the left side (Not
     * implemented)
     */
    public static final int LEFT_FRONT_BUMPER = 12;

    /** A generic light (contrast) sensor */
    public static final int LIGHT = 2;

    /** Identifies the sensor as the light sensor */
    public static final int LIGHT_SENSOR = 2;

    /** Value returned when polling an input that does not have a sensor */
    public static final int NOT_CONNECTED = Integer.MIN_VALUE;

    /**
     * Indicates no sensor is attached. Used to identify when an input doesn't
     * have anything connected to it.
     */
    public static final int NO_SENSOR = -1;

    /**
     * Identifies the sensor as a bumper (touch) sensor connected to the right
     * side (Not implemented)
     */
    public static final int RIGHT_FRONT_BUMPER = 13;

    /** A generic rotation sensor */
    public static final int ROTATION = 9;

    /** Identifies the sensor as the front bumper */
    public static final int STANDARD_FRONT_BUMPER = 1;

    /** A generic temperature sensor */
    public static final int TEMP = 8;

    /** A generic touch sensor */
    public static final int TOUCH = 1;

    /** This value reflects the highest number for a sensor type */
    static final int LAST_SENSOR_TYPE = 13;
}