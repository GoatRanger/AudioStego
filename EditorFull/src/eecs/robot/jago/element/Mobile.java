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

package eecs.robot.jago.element;

/**
 * An interface that can be used to provide SimElements the ability to move
 * around the simulation.
 *
 * @version 1.0
 * @author Karl A. Gossett
 * @since 4.0b
 */
public interface Mobile {

    /**
     * Gets the angular velocity of this element. Convention is positive
     * values are clockwise.
     *
     * @return DOCUMENT ME!
     */
    double getAngularVelocity();

    /**
     * Gets the bounding rectangle of this mobile element
     *
     * @return DOCUMENT ME!
     */
    java.awt.geom.Rectangle2D getBounds2D();

    /**
     * Gets the current heading
     *
     * @return DOCUMENT ME!
     */
    double getHeading();

    // public static final int STOPPED = 0;

    /**
     * Gets the location and returns it in a Point object
     *
     * @return DOCUMENT ME!
     */
    java.awt.geom.Point2D getPosition();

    /**
     * Determines the new power of the mobile element
     *
     * @param speed DOCUMENT ME!
     */
    void setPower(double speed);

    /**
     * Gets the last valid location of this object
     *
     * @return DOCUMENT ME!
     */
    java.awt.geom.Point2D getPreviousPosition();

    /**
     * Gets the current velocity.
     *
     * @return DOCUMENT ME!
     */
    double getVelocity();

    /**
     * Updates the current location based on velocity and heading.
     */
    void updatePosition();
}