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
 * A <code>SolidElement</CODE> is a visible element in the simulation that can
 * be collided with, but that does not move.
 *
 * @version 0.1
 * @author Karl A. Gossett
 * @since 4.1
 */
public abstract class SolidElement
    extends VisibleElement
    implements Collidable {

    protected boolean pendingCollision = false;

    /**
     * Determines the way this object bounces off another Collidable object
     *
     * @param collisionElement DOCUMENT ME!
     */
    public void bounceOff(Collidable collisionElement) {

        // does nothing
    }

    /**
     * React to a collision with another Collidable object
     *
     * @param collisionElement DOCUMENT ME!
     */
    public void handleCollision(Collidable collisionElement) {

        // does nothing
    }

    /**
     * Undocumented
     *
     * @param pending Empty
     */
    public void pendingCollision(boolean pending) {
        pendingCollision = pending;
    }

    public void setAppearance(java.awt.Image image) {
        if (host != null) host.setStaticChanged();
        super.setAppearance(image);
    }
}