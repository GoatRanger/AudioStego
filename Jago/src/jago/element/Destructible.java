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
 * This interface describes any object that can be destroyed during the course
 * of the simulation.  In this context, the object will change state, perhaps
 * react to its surroundings, and the visual representation will change when
 * the element is destroyed.
 *
 * @version 1.0
 * @author Karl A. Gossett
 * @since 4.1
 */
public interface Destructible {

    /** Indicates that the element was destroyed. */
    public static final int DESTROYED = 99;

    /**
     * Sets the flag indicating if this object was destroyed.
     */
    public void setDestroyed();

    /**
     * Returns the current state of this object.
     *
     * @return <CODE>true</CODE> if this element was destroyed.
     */
    public boolean isDestroyed();
}