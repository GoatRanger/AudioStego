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

package jago.element;

/**
 * A <CODE>Collidable</CODE> element will interact with other
 * <CODE>Collidable</CODE> elements during a collision.
 *
 * @version 0.1
 * @author Karl A. Gossett
 * @since 4.1
 */
public interface Collidable {

    /**
     * Gets the shape of any element, as an <CODE>Area</CODE>, implementing
     * this interface.
     *
     * @return DOCUMENT ME!
     */
    java.awt.geom.Area getArea();

    /**
     * Gets the bounding rectangle of a Collidable element.  For an element to
     * be Collidable, it must have dimensions greater than zero.
     *
     * @return The Rectangle that bounds this element.
     */
    java.awt.geom.Rectangle2D getBounds2D();

    /**
     * Determines the way this object bounces off another Collidable object.
     * Provided as part of the interface to ensure elements bounce off as
     * required
     *
     * @param collisionElement The {@link jago.element.Collidable}
     *        element to bounce off of.
     */
    void bounceOff(Collidable collisionElement);

    /**
     * Draws this element.
     *
     * @param g2 DOCUMENT ME!
     */
    void draw(java.awt.Graphics2D g2);

    /**
     * React to a collision with another Collidable object
     *
     * @param collisionElement The <CODE>Collidable</CODE> element that this
     *        element collided with
     */
    void handleCollision(Collidable collisionElement);

    /**
     * Notifies this element whether or not it is involved in a potential
     * collision.  <CODE>pending</CODE> will be false when any pending
     * collision has been resolved.
     *
     * @param pending DOCUMENT ME!
     */
    void pendingCollision(boolean pending);
}