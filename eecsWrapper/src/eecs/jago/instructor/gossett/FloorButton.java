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
 * FloorButton.java
 *
 * Created on March 1, 2002, 5:03 PM
 */
package eecs.jago.instructor.gossett;

import jago.element.*;


/**
 * @author Karl A. Gossett
 */
public class FloorButton
    extends MobileElement
    implements FlatObject {

    protected final int TIME_TO_RISE = 3;
    protected boolean hitThisUpdate = false;
    protected boolean isPushed = false;
    protected int leftToRise = 0;

    /**
     * Creates a new instance of FloorButton
     */
    public FloorButton() {
        super();
        size = new java.awt.Dimension(20, 20);
    }

    /**
     * Overriden to prevent loading of an image, since this is automatically
     * drawn.
     * 
     * @param url     not used
     */
    public void setAppearance(String url) {}

    /**
     * Draws this FloorButton in a Graphics2D context.
     * 
     * @param g2 DOCUMENT ME!
     */
    public void draw(java.awt.Graphics2D g2) {

        if (added) {
            g2.setColor(java.awt.Color.gray);
            g2.fill3DRect((int)location.getX(), (int)location.getY(), 
                          (int)size.getWidth(), (int)size.getHeight(), true);
            g2.setColor(java.awt.Color.blue);
            g2.fill3DRect((int)location.getX() + 3, (int)location.getY() + 3, 
                          (int)size.getWidth() - 6, (int)size.getHeight() - 6, 
                          !isPushed);
        }
    }

    /**
     * React to a collision with another Collidable object
     * 
     * @param collisionElement DOCUMENT ME!
     */
    public void handleCollision(Collidable collisionElement) {
        hitThisUpdate = true;
        leftToRise = TIME_TO_RISE;
    }

    /**
     * Undocumented
     */
    protected void runTaskStep() {

        if (hitThisUpdate) {
            isPushed = true;
        } else {
            isPushed = false;
        }

        leftToRise--;

        if (leftToRise <= 0) {
            hitThisUpdate = false;
        }
    }
}