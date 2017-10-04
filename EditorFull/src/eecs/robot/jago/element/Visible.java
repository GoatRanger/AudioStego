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
 * A <code>Visible</code> object has the ability to display itself if given a
 * context to draw in.
 *
 * @version 1.0
 * @author Karl A. Gossett
 * @since 4.1
 */
public interface Visible {

    /**
     * Sets the picture of any object using this interface to the image
     * identified by the URL provided.
     *
     * @param url     The URL of the picture to use.
     */
    void setAppearance(String url);

    /**
     * Returns the bounding rectangle of any visible object.
     *
     * @return A <code>Rectangle2D</code> that completely encloses this object.
     */
    java.awt.geom.Rectangle2D getBounds2D();

    /**
     * Draws the image of the object that implements this interface into a
     * Java2D (Graphics2D) context.
     *
     * @param g2    The Graphics2D context to draw in.
     */
    void draw(java.awt.Graphics2D g2);
}