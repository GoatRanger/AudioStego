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
 * PushableBomb.java
 *
 * Created on November 13, 2001, 12:35 PM
 */
package eecs.jago.instructor.gossett;

import jago.element.*;

import java.awt.*;
import java.awt.geom.*;


/**
 * @version 0.1
 * @author Karl A. Gossett
 */
public class PushableBomb
    extends PushableBox
    implements Destructible {

    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 1;
    public static final int TOP = 0;
    boolean hasMoved = false;
    int timer = -1;
    private ColoredLine[] boundingLine;
    private Font counterFont;
    private Image explodeImage = null;
    private boolean exploded = false;
    private boolean hasTape = true;
    private boolean ticking = true;
    private int type;

    /**
     * Creates new PushableBomb with a timer that starts with 1 minute
     */
    public PushableBomb() {
        this(TOP);
        start(4, 0);
    }

    /**
     * Creates a new PushableBomb facing the specified direction, one of:
     * 
     * <ul>
     * <li>
     * PushableBomb.TOP
     * </li>
     * <li>
     * PushableBomb.BOTTOM
     * </li>
     * <li>
     * PushableBomb.LEFT
     * </li>
     * <li>
     * PushableBomb.RIGHT
     * </li>
     * </ul>
     * 
     * The facing determines the location of the disarming button.
     * 
     * @param type   The direction of the disarming button
     */
    public PushableBomb(int type) {
        this(type, 35);
    }

    public PushableBomb(int type, int width) {
        super();
        this.type = type;

        switch (type) {

            case LEFT: // fall through to RIGHT
            case RIGHT:
                size = new Dimension(width + 5, width);

                break;

            case TOP: // fall through to BOTTOM
            case BOTTOM:
                size = new Dimension(width, width + 5);

                break;

            default:
                size = new Dimension(width, width);

                break;
        }

        ticking = false;
        loadExplosion();
        boundingLine = new ColoredLine[4];
        counterFont = new java.awt.Font("Serif", java.awt.Font.BOLD, 
                                        (int)(width / 2.2));
    }

    /**
     * This PushableBomb was destroyed.
     */
    public void setDestroyed() {

        //       setAppearance(explodeImage);
        appearance = explodeImage;
        ticking = false;
        exploded = true;
    }

    /**
     * Returns the current state of this PushableBomb.
     * 
     * @return DOCUMENT ME!
     */
    public boolean isDestroyed() {

        return (exploded);
    }

    /**
     * Notifies this PushableBomb where it was added, both by giving the
     * SimEngine that is hosting it and the location in the SimEngine's
     * space. Overrides <CODE>addedTo</CODE> in <CODE>SimElement</CODE>
     * 
     * @param sim    The SimEngine that is hosting this SimElement
     * @param loc    The location where this SimElement was added
     */
    public void addedTo(jago.SimEngine sim, Point2D loc) {
        super.addedTo(sim, loc);

        // Now add the bounding lines.
        if (type == TOP) {
            boundingLine[0] = new ColoredLine(size.getWidth() + 4, 3, 
                                              ReflectiveColors.RED);
            boundingLine[1] = new ColoredLine(size.getWidth() + 9, 3, 
                                              ReflectiveColors.BLACK);
            boundingLine[2] = new ColoredLine(3, size.getHeight() + 7, 
                                              ReflectiveColors.BLACK);
            boundingLine[3] = new ColoredLine(3, size.getHeight() + 7, 
                                              ReflectiveColors.BLACK);
            host.add(boundingLine[0], location.getX() - 2, location.getY() - 6);
            host.add(boundingLine[1], location.getX() - 5, 
                     location.getY() + size.getHeight() + 3);
            host.add(boundingLine[2], location.getX() - 8, location.getY() - 1);
            host.add(boundingLine[3], location.getX() + size.getWidth() + 4, 
                     location.getY() - 1);
        } else if (type == RIGHT) {
            boundingLine[0] = new ColoredLine(size.getWidth() + 7, 3, 
                                              ReflectiveColors.BLACK);
            boundingLine[1] = new ColoredLine(size.getWidth() + 7, 3, 
                                              ReflectiveColors.BLACK);
            boundingLine[2] = new ColoredLine(3, size.getHeight() + 8, 
                                              ReflectiveColors.BLACK);
            boundingLine[3] = new ColoredLine(3, size.getHeight() + 4, 
                                              ReflectiveColors.RED);
            host.add(boundingLine[0], location.getX() - 6, location.getY() - 7);
            host.add(boundingLine[1], location.getX() - 6, 
                     location.getY() + size.getHeight() + 4);
            host.add(boundingLine[2], location.getX() - 6, location.getY() - 4);
            host.add(boundingLine[3], location.getX() + size.getWidth() + 3, 
                     location.getY() - 2);
        } else if (type == BOTTOM) {
            boundingLine[0] = new ColoredLine(size.getWidth() + 9, 3, 
                                              ReflectiveColors.BLACK);
            boundingLine[1] = new ColoredLine(size.getWidth() + 4, 3, 
                                              ReflectiveColors.RED);
            boundingLine[2] = new ColoredLine(3, size.getHeight() + 7, 
                                              ReflectiveColors.BLACK);
            boundingLine[3] = new ColoredLine(3, size.getHeight() + 7, 
                                              ReflectiveColors.BLACK);
            host.add(boundingLine[0], location.getX() - 5, location.getY() - 6);
            host.add(boundingLine[1], location.getX() - 2, 
                     location.getY() + size.getHeight() + 3);
            host.add(boundingLine[2], location.getX() - 8, location.getY() - 6);
            host.add(boundingLine[3], location.getX() + size.getWidth() + 4, 
                     location.getY() - 6);
        } else if (type == LEFT) {
            boundingLine[0] = new ColoredLine(size.getWidth() + 7, 3, 
                                              ReflectiveColors.BLACK);
            boundingLine[1] = new ColoredLine(size.getWidth() + 7, 3, 
                                              ReflectiveColors.BLACK);
            boundingLine[2] = new ColoredLine(3, size.getHeight() + 4, 
                                              ReflectiveColors.RED);
            boundingLine[3] = new ColoredLine(3, size.getHeight() + 8, 
                                              ReflectiveColors.BLACK);
            host.add(boundingLine[0], location.getX() - 1, location.getY() - 7);
            host.add(boundingLine[1], location.getX() - 1, 
                     location.getY() + size.getHeight() + 4);
            host.add(boundingLine[2], location.getX() - 4, location.getY() - 2);
            host.add(boundingLine[3], location.getX() + size.getWidth() + 3, 
                     location.getY() - 4);
        }
    }

    /**
     * Draws this MobileElement in a Graphics2D context.  Overrides draw in
     * MobileElement to prevent rotation of the box when moving.
     * 
     * @param g2 DOCUMENT ME!
     */
    public void draw(Graphics2D g2) {

        if (exploded) {
            super.draw(g2);

            return;
        }

        g2.setColor(java.awt.Color.lightGray);

        switch (type) {

            case LEFT:
                g2.fill3DRect((int)location.getX() + 5, (int)location.getY(), 
                              (int)size.getWidth() - 5, (int)size.getHeight(), 
                              true);
                g2.setColor(java.awt.Color.blue);
                g2.fillRoundRect((int)location.getX(), 
                                 (int)location.getY() + 3, 4, 
                                 (int)size.getHeight() - 6, 2, 2);

                break;

            case RIGHT:
                g2.fill3DRect((int)location.getX(), (int)location.getY(), 
                              (int)size.getWidth() - 5, (int)size.getHeight(), 
                              true);
                g2.setColor(java.awt.Color.blue);
                g2.fillRoundRect((int)(location.getX() + size.getWidth() - 4), 
                                 (int)location.getY() + 3, 4, 
                                 (int)size.getHeight() - 6, 2, 2);

                break;

            case TOP:
                g2.fill3DRect((int)location.getX(), (int)location.getY() + 5, 
                              (int)size.getWidth(), (int)size.getHeight() - 5, 
                              true);
                g2.setColor(java.awt.Color.blue);
                g2.fillRoundRect((int)location.getX() + 3, 
                                 (int)location.getY(), 
                                 (int)size.getWidth() - 6, 4, 2, 2);

                break;

            case BOTTOM:
                g2.fill3DRect((int)location.getX(), (int)location.getY(), 
                              (int)size.getWidth(), (int)size.getHeight() - 5, 
                              true);
                g2.setColor(java.awt.Color.blue);
                g2.fillRoundRect((int)location.getX() + 3, 
                                 (int)(location.getY() + size.getHeight() - 4), 
                                 (int)size.getWidth() - 6, 4, 2, 2);

                break;
        }

        g2.setFont(counterFont);
        g2.setColor(java.awt.Color.black);

        int minutes = timer / 60000;
        int seconds = (timer - (minutes * 60000)) / 1000;
        String secondsString = Integer.toString(seconds);

        if (secondsString.length() < 2) {
            secondsString = "0" + secondsString;
        }

        String time = Integer.toString(minutes) + ":" + secondsString;
        int xOffset = (int)(size.getWidth() / 8);

        if (type == LEFT) {
            xOffset += 4;
        }

        int yOffset = (int)(size.getHeight() / 1.5);

        if (type == BOTTOM) {
            yOffset -= 4;
        }

        g2.drawString(time, (int)(location.getX() + xOffset), 
                      (int)(location.getY() + yOffset));
    }

    /**
     * React to a collision with another Collidable object
     * 
     * @param collisionElement DOCUMENT ME!
     */
    public void handleCollision(Collidable collisionElement) {

        // If hitting something reflective, don't bother with collisions
        if (collisionElement instanceof Reflective) {

            return;
        }

        int sideOfCollision = -1;
        MobileElement collider;

        if (collisionElement instanceof MobileElement) {
            collider = (MobileElement)collisionElement;
        } else {

            // Hit something solid, so stop moving and explode
            location.setLocation(previousLocation);
            velVector = new PolarPoint();

            //            velocity = 0;
            setDestroyed();

            return;
        }

        Rectangle2D me = getBounds2D();
        Rectangle2D other = collider.getBounds2D();
        double myCenterX = location.getX() + size.getWidth() / 2.0;
        double myCenterY = location.getY() + size.getHeight() / 2.0;

        // Get the center point of the thing that collided with me
        double otherCenterX = collider.getPreviousPosition()
                                      .getX() + other.getWidth() / 2.0
                              - myCenterX;
        double otherCenterY = collider.getPreviousPosition()
                                      .getY() + other.getHeight() / 2.0
                              - myCenterY;
        Point2D otherCenter = new Point2D.Double(otherCenterX, otherCenterY);

        if (Math.abs(otherCenter.getX()) > Math.abs(otherCenter.getY())) {

            if (otherCenter.getX() > 0) {
                sideOfCollision = RIGHT;
            } else if (otherCenter.getX() < 0) {
                sideOfCollision = LEFT;
            } else {

                // Side of collision unknown
            }
        } else {

            if (otherCenter.getY() > 0) {
                sideOfCollision = BOTTOM;
            } else if (otherCenter.getY() < 0) {
                sideOfCollision = TOP;
            } else {

                // Side unknown
            }
        }

        if (sideOfCollision == type) {
            super.handleCollision(collisionElement);
            hasMoved = true;
        } else {
            timer -= 10000;
        }
    }

    /**
     * Undocumented
     * 
     * @return Empty
     */
    public boolean hasMoved() {

        return hasMoved;
    }

    /**
     * Undocumented
     * 
     * @return Empty
     */
    public boolean hasTape() {

        return hasTape;
    }

    /**
     * Undocumented
     */
    public void removeTape() {

        for (int i = 0; i < 4; i++) {
            host.remove(boundingLine[i]);
        }

        hasTape = false;
    }

    /**
     * Undocumented
     * 
     * @param minutes Empty
     * @param seconds Empty
     */
    public void start(int minutes, int seconds) {
        timer = minutes * 60000 + seconds * 1000;
        ticking = true;
    }

    /**
     * Undocumented
     */
    public void updatePosition() {
        calculateCurrentFrame();
        super.updatePosition();
    }

    /**
     * Keep track of the velocity of the bomb after it was pushed
     */
    public void updateVelocity() {

        if (velVector.getMagnitude() > 0) { /*velocity*/
            velVector.setMagnitude(velVector.getMagnitude() * 0.8);

            //            velocity = velocity * 0.1;
        }

        if (velVector.getMagnitude() < 0.01) { /*velocity*/
            velVector.setMagnitude(0);

            //            velocity = 0;
        }
    }

    /**
     * Overridden to enable countdown effect.
     */
    protected void calculateCurrentFrame() {

        if (!ticking) {

            return;
        }

        timer -= host.deltaTickTimeMillis();

        if (timer <= 0) {
            timer = 0;
            setDestroyed();
        }
    }

    /**
     * Disable this bomb once it is in the correct location
     */
    protected void disable() {
        ticking = false;
    }

    /**
     * Undocumented
     */
    private void loadExplosion() {
        explodeImage = jago.ImageLoader.getImage(this, 
                                                      "detonator_exploded.gif");
    }
}