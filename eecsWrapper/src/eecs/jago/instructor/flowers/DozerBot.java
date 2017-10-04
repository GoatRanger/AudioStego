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

package eecs.jago.instructor.flowers;

import jago.element.*;


/**
 * This robot has a blade and a light sensor on RCX-1, it suffers if runs into
 * concertina or dead tanks, and it can remove mines.
 */
public class DozerBot
    extends StandardRobot
    implements Destructible {

    /**
     * Creates a new DozerBot
     */
    public DozerBot() {
        super();
        setAppearance("images/dozer.gif");
        addSensor(Monitor.RCX_1, Sensor.LIGHT);
    }

    /**
     * Makes this DozerBot destroy itself.
     */
    public void setDestroyed() {
        state = Destructible.DESTROYED;
        setAppearance("images/bang.gif");
    }

    /**
     * Returns the current state of this object.
     * 
     * @return DOCUMENT ME!
     */
    public boolean isDestroyed() {

        return (state == Destructible.DESTROYED);
    }

    /**
     * Overridden to handle concertina wire, mines, and dead tanks.
     * 
     * @param element DOCUMENT ME!
     */
    public void handleCollision(Collidable element) {

        if (element instanceof Concertina) {
            setPower(2);
        } else if (element instanceof DeadTank) {
            setDestroyed();
        } else if (!(element instanceof Mine)) {

            // None of the above, so use the standard collision handler
            super.handleCollision(element);
        }
        // if a mine, just ignore the collision
    }

    /**
     * Overridden so that the dozer can die.
     */
    public void updatePosition() {

        if (state == DESTROYED) {
            host.remove(this);
        } else {
            super.updatePosition();
        }
    }
}