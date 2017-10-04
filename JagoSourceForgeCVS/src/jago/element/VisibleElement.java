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
 * VisibleElement.java
 *
 * Created on May 9, 2002, 10:10 AM
 */
package jago.element;

/**
 * A abstraction of visible simulation elements.  Since this class implements
 * the <CODE>Visible</CODE> interface, it will be drawn during simulation
 * updates.
 *
 * @version 2.4
 * @author Karl A. Gossett
 */
public abstract class VisibleElement
    extends SimElement
    implements Visible {

    /** A visual representation. */
        protected java.awt.Image appearance = null;

    /**
     * Creates new VisibleElement
     */
    public VisibleElement() {
        super();
    }

    /**
     * Sets the picture of any object using this interface to the image
     * identified by the URL provided.
     *
     * @param filename     The picture to use.
     */
    public void setAppearance(String filename) {
        appearance = jago.ImageLoader.getImage(this, filename);
        setAppearance(appearance);
    }

    /**
     * Sets the appearance of this element to the previously defined  Image.
     *
     * @param image     The <CODE>Image</CODE> of the new appearance
     */
    public void setAppearance(java.awt.Image image) {

        if (image != null) {
            appearance = image;
            size.setSize(appearance.getWidth(null),
                         appearance.getHeight(null));
        } else {
            appearance = null;
            size = new java.awt.Dimension();
        }
    }

    /**
     * Draws the image of the object that implements this interface into a
     * Java2D (Graphics2D) context.
     *
     * @param g2    The Graphics2D context to draw in.
     */
    public void draw(java.awt.Graphics2D g2) {

        if (appearance != null) {
            g2.drawImage(appearance, (int)location.getX(),
                         (int)location.getY(), null);
        } else {
            g2.draw(myShape);
        }
    }
}