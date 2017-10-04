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
 * A mine that will be removed only by a DozerBot.  It will destroy all other
 * <CODE>Destructible</CODE> elements that collide with it.  When hit by a
 * DozerBot, it is destroyed, and removed from the simulation.
 */
public class Mine
    extends SolidElement
    implements Destructible {

    String defaultImage = "mine.gif";
    boolean destroyed = false;
    int delay = 5;

    /**
     * Creates a new Mine with the default image
     */
    public Mine() {
        this("mine.gif");
    }

    /**
     * Creates a new Mine with the specified image
     * 
     * @param imgFile DOCUMENT ME!
     */
    public Mine(String imgFile) {
        super();
        setAppearance(imgFile);

        if (appearance == null) {
            setAppearance(defaultImage);
        }
    }

    /**
     * Destroys this Mine.
     */
    public void setDestroyed() {
        setAppearance("images/bang.gif");
        destroyed = true;
    }

    /**
     * Returns the current state of this object.
     * 
     * @return DOCUMENT ME!
     */
    public boolean isDestroyed() {

        return destroyed;
    }

    /**
     * Reacts to a collision.  If colliding with a DozerBot, the mine will
     * explode and then be removed from the simulation; otherwise, it will
     * destroy any <CODE>Destructible</CODE> element that collides with it,
     * then it will destroy itself.
     * 
     * @param element DOCUMENT ME!
     */
    public void handleCollision(Collidable element) {

        if (element instanceof DozerBot) {
            setDestroyed();
        } else if (element instanceof Destructible) {
            ((Destructible)element).setDestroyed();
            setDestroyed();
        }
    }

    /**
     * Undocumented
     */
    public void runTaskStep() {

        if (destroyed) {
            if (delay-- < 1) {
                removeFromSim();
            }
        }
    }

    /**
     * Removes the mine.
     */
    protected void removeFromSim() {
        host.remove(this);
    }
}