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
 * Attachment.java
 *
 * Created on January 21, 2002, 9:25 PM
 */
package jago.element;


/**
 * This interface describes elements that are attached to other elements.
 *
 * @version 0.1
 * @author Karl A. Gossett
 */
public interface Attachment {

    /**
     * Determines if this element is attached to the specified element.
     *
     * @param s The element that is a potential host.
     * @return <CODE>true</CODE> if this element is attached to the specified
     *         one.
     */
    public boolean isAttachedTo(SimElement s);

    /**
     * Gets the <CODE>Monitor</CODE> that this <CODE>Attachment</CODE> is
     * attached to.
     *
     * @return DOCUMENT ME!
     */
    public Monitor attachedTo();

    /**
     * Sets the relative position of this element.  Specified as a vector, the
     * parameters should describe the position from the center of the element
     * (attached to) to the inside center of this element (so the radius is
     * in pixels, to the point nearest the host's center).
     *
     * @param radius   The radius of the vector, from the center of the
     *        element attached to (must be > 0).
     * @param theta    The angle between the center of the two elements, in
     *        degrees.  Assumes 0 is from the initial orientation of the
     *        host.
     */
    void setReference(double radius, double theta);
}