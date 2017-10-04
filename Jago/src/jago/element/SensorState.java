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
 * SensorState.java
 *
 * Created on December 16, 2001, 12:47 PM
 */
package jago.element;


/**
 * The SensorState interface maintains the standard states of robot sensors.
 *
 * @version 1.0
 * @author Karl A. Gossett
 * @since 5.1
 */
public interface SensorState {

    /**
     * Indicates that this sensor has not been properly initialized yet.
     * Usually relates to active sensors (such as light sensors), but may
     * also apply to other sensors that require extra time to initialize.
     *
     * @since 6.1
     */
    public static final int NOT_READY = Integer.MAX_VALUE;

    /* OFF has a value of 1030 because the sensor can actually return 0-1023 */

    /** Indicates that the sensor is not active (not necessarily turned off) */
    public static final int OFF = 1030;

    /* ON has a value of 1031 because the sensor can actually return 0-1023 */

    /** Indicates that the sensor is ON.  Normally used for TOUCH sensors */
    public static final int ON = 1031;
}