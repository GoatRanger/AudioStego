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
 * MovingDoor.java
 *
 * Created on November 10, 2001, 10:31 AM
 */
package eecs.robot.jago.element;

/**
 * This class provides a large door that opens/closes when pushed.  The
 * current implementation only opens to the right, hinged from the top.
 * Future versions will allow specification of the direction to open the
 * door, and will also provide lockable doors that require a key.
 *
 * @version 0.1
 * @author Karl A. Gossett
 */
public class MovingDoor
    extends SolidElement {

        protected java.awt.Image closedDoor;
    protected boolean lockable;
    protected java.awt.Image openDoor;
    protected java.awt.Image partialOpenDoor;
    protected int state;
    final int CLOSED = 0;
    final int OPEN = 2;
    final int PARTIALLY_OPEN = 1;

    /**
     * Creates new MovingDoor
     */
    public MovingDoor() {
        closedDoor = eecs.robot.jago.ImageLoader.getImage(this, "/eecs/images/door_vert.gif");
        openDoor = eecs.robot.jago.ImageLoader.getImage(this, "/eecs/images/door_right.gif");
        partialOpenDoor = eecs.robot.jago.ImageLoader.getImage(this, "/eecs/images/door_right45.gif");
        setAppearance(closedDoor);
        state = CLOSED;
        lockable = false;
    }

    /**
     * Collidable elements must be able to draw themselves.
     *
     * @param g2 DOCUMENT ME!
     */
    public void draw(java.awt.Graphics2D g2) {
        super.draw(g2);
    }

    /**
     * React to a collision with another Collidable object
     *
     * @param collisionElement DOCUMENT ME!
     */
    public void handleCollision(Collidable collisionElement) {

       
        if (!(collisionElement instanceof MobileElement)) {

            return;
        }

        MobileElement mover = (MobileElement)collisionElement;

        //if (lockable && !(mover.previousLocation instanceof Hauler)) return;
        if (state == CLOSED) {

            if (mover.getPreviousPosition()
                     .getX() < location.getX()) {
                state = PARTIALLY_OPEN;
                setAppearance(partialOpenDoor);
            }
        } else if (state == PARTIALLY_OPEN) {

            if (mover.getPreviousPosition()
                     .getX() < location.getX()
                || mover.getPreviousPosition()
                        .getY() > location.getY()) {
                state = OPEN;
                setAppearance(openDoor);
            } else {
                state = CLOSED;
                setAppearance(closedDoor);
            }
        } else {

            if (mover.getPreviousPosition()
                     .getY() < location.getY()) {
                state = PARTIALLY_OPEN;
                setAppearance(partialOpenDoor);
            }
        }
    }
}