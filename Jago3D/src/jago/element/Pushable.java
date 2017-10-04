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
 * Pushable.java
 *
 * Created on November 12, 2001, 9:15 PM
 */
package jago.element;


/**
 * An empty interface that can be used to determine if objects can be pushed.
 *
 * <p>
 * This interface should be used sparingly, because it is only a temporary
 * interface to allow the detection of movable objects.  Once object weight
 * and physics are implemented, there should be no need for this interface,
 * and it will probably be removed.
 * </p>
 *
 * @version 0.1
 * @author Karl A. Gossett
 */
public interface Pushable {

    /**
     * Undocumented
     */
    public void updatePosition();
}