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
 * Propulsion.java
 *
 * Created on May 25, 2002, 10:48 PM
 */
package eecs.robot.jago.element;


/**
 * An interface for elements that have the ability to move.  Provides the
 * translation from a power setting to physical movement.
 *
 * @author Karl A. Gossett
 */
public interface Propulsion {

    /**
     * Returns the distance that this propulsion device has moved.
     *
     * @param deltaTime The amount of time that has passed (in ms)
     * @return The distance traveled; forward is positive, reverse is negative
     */
    double getDistanceMoved(double deltaTime);

    /**
     * Returns the amount of drag that this system has
     *
     * @return DOCUMENT ME!
     */
    double getDrag();

    /**
     * Sets the number of teeth on the input gear to this propulsion device.
     *
     * @param teeth  The effective number of teeth on the gear.
     */
    void setInputGear(int teeth);

    /**
     * Set the RPM of the motor that is driving this device.
     *
     * @param setting   The power setting
     */
    void setInputRPM(double setting);
}