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

import java.awt.Color;
import java.awt.Font;



/**
 * A detonator that counts down, and explodes when the timer reaches 0:00. The
 * detonator has a disarming button on one side that halts the countdown.
 * Each detonator is surrounded by reflective tape; the side with the
 * disarming  button has red ({@link jago.element.ReflectiveColors.RED})
 * reflective  tape, all other sides have black.
 *
 * @version 5.1   November 2001
 * @author Rusl Flowers
 * @author Karl A. Gossett
 * @since 4.0
 */
public class Detonator
    extends SolidElement
    implements Destructible {

    /** A button on the left */
    public static final int BOTTOM = 2;

    /** A button on the left */
    public static final int LEFT = 3;

    /** A button on the Right */
    public static final int RIGHT = 1;

    /** A button on top */
    public static final int TOP = 0;

    /** The image that is shown when a detonator explodes */
    private static java.awt.Image explodeImage = null;

    /** The lines used around the outside of the detonator */
    private ColoredLine[] boundingLine;

    /** The font used for the counter */
    private Font counterFont = new Font("Serif", Font.BOLD,
                                                 18);

    /** The amount of time until this detonator explodes */
    private int expires = -1;

    /** Flag indicating that this detonator has exploded. */
    private boolean exploded = false;
    private boolean imageLocked = false;

    /** If this detonator is ticking */
    private boolean ticking = true;

    /** The style of this detonator, one of TOP, BOTTOM, LEFT, RIGHT */
    private int type;

    /**
     * Creates a new Detonator (35x35 pixels + 5 pixel button) facing the
     * specified direction, one of:
     *
     * <ul>
     * <li>
     * Detonator.TOP
     * </li>
     * <li>
     * Detonator.BOTTOM
     * </li>
     * <li>
     * Detonator.LEFT
     * </li>
     * <li>
     * Detonator.RIGHT
     * </li>
     * </ul>
     *
     * The facing determines the location of the disarming button.
     *
     * @param type   The direction of the disarming button
     */
    public Detonator(int type) {
        this(type, 35);
    }

    /**
     * Creates a new detonator with a user specified width and height (square).
     *
     * <P>
     * Note: The default explosion is designed to work with a 35x35 detonator.
     * Significant changes in size should be accompanied by a resized
     * explosion.
     * </p>
     *
     * @param type The type of detonator
     * @param width The width/height of the Detonator (Detonators are square)
     */
    public Detonator(int type, int width) {
        super();
        this.type = type;

        switch (type) {

            case LEFT: // fall through to RIGHT
            case RIGHT:
                size = new java.awt.Dimension(width + 5, width);

                break;

            case TOP: // fall through to BOTTOM
            case BOTTOM:
                size = new java.awt.Dimension(width, width + 5);

                break;

            default:
                size = new java.awt.Dimension(width, width);

                break;
        }

        ticking = false;
        setExplosion("images/detonator_exploded.gif");
        boundingLine = new ColoredLine[4];
        counterFont = new Font("Serif", Font.BOLD,
                                        (int)(width / 2.2));
    }

    /**
     * Sets the appearance of the detonator to the specified image.  If the
     * Detonator has already been added to a simulation, the appearance
     * cannot be changed, so this method must be called prior to adding the
     * Detonator to a simulation.
     *
     * @param filename   The file name of the image to use
     */
    public void setAppearance(String filename) {

        if (!imageLocked) {
            super.setAppearance(filename);
        }
    }

    /**
     * This detonator was destroyed.
     */
    public void setDestroyed() {
        setAppearance(explodeImage);
        ticking = false;
        exploded = true;

        for (int i = 0; i < 4; i++) {
            host.remove(boundingLine[i]);
        }
    }

    /**
     * Returns the current state of this Detonator.
     *
     * @return Empty
     */
    public boolean isDestroyed() {

        return (exploded);
    }

    /**
     * Sets the appearance of the detonator when it explodes to the specified
     * image.
     *
     * @param filename Empty
     */
    public void setExplosion(String filename) {
        explodeImage = jago.ImageLoader.getImage(this, filename);
    }

    /**
     * Notifies this Detonator where it was added, both by giving the
     * SimEngine that is hosting it and the location in the SimEngine's
     * space. Overrides <CODE>addedTo</CODE> in <CODE>SimElement</CODE>
     *
     * @param sim    The SimEngine that is hosting this SimElement
     * @param loc    The location where this SimElement was added
     */
    public void addedTo(jago.SimEngine sim, java.awt.geom.Point2D loc) {
        imageLocked = true;
        super.addedTo(sim, loc);

        // Now add the bounding lines.
        if (type == Detonator.TOP) {
            boundingLine[0] = new ColoredLine(size.getWidth() - 4, 3,
                                              ReflectiveColors.RED);
            boundingLine[1] = new ColoredLine(size.getWidth() + 9, 3,
                                              ReflectiveColors.BLACK);
            boundingLine[2] = new ColoredLine(3, size.getHeight() + 6,
                                              ReflectiveColors.BLACK);
            boundingLine[3] = new ColoredLine(3, size.getHeight() + 6,
                                              ReflectiveColors.BLACK);
            host.add(boundingLine[0], location.getX() + 2, location.getY() - 8);
            host.add(boundingLine[1], location.getX() - 5,
                     location.getY() + size.getHeight() + 3);
            host.add(boundingLine[2],
                     location.getX() - 8,
                     location.getY());
            host.add(boundingLine[3],
                     location.getX() + size.getWidth() + 4,
                     location.getY());
        } else if (type == Detonator.RIGHT) {
            boundingLine[0] = new ColoredLine(size.getWidth() + 6, 3,
                                              ReflectiveColors.BLACK);
            boundingLine[1] = new ColoredLine(size.getWidth() + 6, 3,
                                              ReflectiveColors.BLACK);
            boundingLine[2] = new ColoredLine(3, size.getHeight() + 8,
                                              ReflectiveColors.BLACK);
            boundingLine[3] = new ColoredLine(3, size.getHeight() - 4,
                                              ReflectiveColors.RED);
            host.add(boundingLine[0], location.getX() - 6, location.getY() - 7);
            host.add(boundingLine[1], location.getX() - 6,
                     location.getY() + size.getHeight() + 4);
            host.add(boundingLine[2], location.getX() - 6, location.getY() - 4);
            host.add(boundingLine[3], location.getX() + size.getWidth() + 4,
                     location.getY() + 2);
        } else if (type == Detonator.BOTTOM) {
            boundingLine[0] = new ColoredLine(size.getWidth() + 9, 3,
                                              ReflectiveColors.BLACK);
            boundingLine[1] = new ColoredLine(size.getWidth() - 4, 3,
                                              ReflectiveColors.RED);
            boundingLine[2] = new ColoredLine(3, size.getHeight() + 6,
                                              ReflectiveColors.BLACK);
            boundingLine[3] = new ColoredLine(3, size.getHeight() + 6,
                                              ReflectiveColors.BLACK);
            host.add(boundingLine[0], location.getX() - 5, location.getY() - 6);
            host.add(boundingLine[1], location.getX() + 2,
                     location.getY() + size.getHeight() + 4);
            host.add(boundingLine[2], location.getX() - 8, location.getY() - 6);
            host.add(boundingLine[3], location.getX() + size.getWidth() + 4,
                     location.getY() - 6);
        } else if (type == Detonator.LEFT) {
            boundingLine[0] = new ColoredLine(size.getWidth() + 6, 3,
                                              ReflectiveColors.BLACK);
            boundingLine[1] = new ColoredLine(size.getWidth() + 6, 3,
                                              ReflectiveColors.BLACK);
            boundingLine[2] = new ColoredLine(3, size.getHeight() - 4,
                                              ReflectiveColors.RED);
            boundingLine[3] = new ColoredLine(3, size.getHeight() + 8,
                                              ReflectiveColors.BLACK);
            host.add(boundingLine[0],
                     location.getX(),
                     location.getY() - 7);
            host.add(boundingLine[1],
                     location.getX(),
                     location.getY() + size.getHeight() + 4);
            host.add(boundingLine[2], location.getX() - 5, location.getY() + 2);
            host.add(boundingLine[3], location.getX() + size.getWidth() + 3,
                     location.getY() - 4);
        }
    }

    /**
     * Draws this Detonator.  Normally draws it as a rectangle with a button,
     * but will draw an explosion once the timer reaches 0:00.
     *
     * @param g2 Empty
     */
    public void draw(java.awt.Graphics2D g2) {

        if (exploded) {
            super.draw(g2);

            return;
        }

        g2.setColor(Color.lightGray);

        // if this detonator has an image for a background
        boolean usingImage = (appearance != null);

        if (usingImage) {
            super.draw(g2);
        }

        switch (type) {

            case LEFT:

                if (!usingImage) {
                    g2.fill3DRect((int)location.getX() + 5,
                                  (int)location.getY(),
                                  (int)size.getWidth() - 5,
                                  (int)size.getHeight(), true);
                }

                g2.setColor(Color.white);
                g2.fillRoundRect((int)location.getX(),
                                 (int)location.getY() + 3, 4,
                                 (int)size.getHeight() - 6, 2, 2);

                break;

            case RIGHT:

                if (!usingImage) {
                    g2.fill3DRect((int)location.getX(), (int)location.getY(),
                                  (int)size.getWidth() - 5,
                                  (int)size.getHeight(), true);
                }

                g2.setColor(Color.white);
                g2.fillRoundRect(
                        (int)((int)location.getX() + size.getWidth() - 4),
                        (int)location.getY() + 3, 4, (int)size.getHeight() - 6,
                        2, 2);

                break;

            case TOP:

                if (!usingImage) {
                    g2.fill3DRect((int)location.getX(),
                                  (int)location.getY() + 5,
                                  (int)size.getWidth(),
                                  (int)size.getHeight() - 5, true);
                }

                g2.setColor(Color.white);
                g2.fillRoundRect((int)location.getX() + 3,
                                 (int)(int)location.getY(),
                                 (int)size.getWidth() - 6, 4, 2, 2);

                break;

            case BOTTOM:

                if (!usingImage) {
                    g2.fill3DRect((int)location.getX(), (int)location.getY(),
                                  (int)size.getWidth(),
                                  (int)size.getHeight() - 5, true);
                }

                g2.setColor(Color.white);
                g2.fillRoundRect((int)location.getX() + 3,
                                 (int)(location.getY() + size.getHeight() - 4),
                                 (int)size.getWidth() - 6, 4, 2, 2);

                break;
        }

        g2.setFont(counterFont);
        g2.setColor(Color.black);

        int minutes = expires / 60000;
        int seconds = (expires - (minutes * 60000)) / 1000;
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
     * During a collision, checks to see if hit on the "button" side.  If so,
     * turn off the timer.  Otherwise, do nothing.
     *
     * @param mover   The <CODE>Collidable</CODE> object that hit this
     *        detonator.
     */
    public void handleCollision(Collidable mover) {

        if (!ticking) {

            return;
        }

        int sideOfCollision = -1;
        Mobile collider;

        if (mover instanceof Mobile) {
            collider = (Mobile)mover;
        } else {

            // For debugging, should NEVER occur
            System.out.println("Two statics collided...");

            return;
        }

        java.awt.geom.Rectangle2D me = getBounds2D();
        java.awt.geom.Rectangle2D other = collider.getBounds2D();
        java.awt.geom.Rectangle2D intersection = me.createIntersection(other);
        double myCenterX = me.getCenterX();
        double myCenterY = me.getCenterY();

        if (intersection.getWidth() > intersection.getHeight()) {

            // Top or Bottom hit
            if (intersection.getY() < myCenterY) {
                sideOfCollision = TOP;
            } else {
                sideOfCollision = BOTTOM;
            }
        } else {

            // Left or Right side hit
            if (intersection.getX() < myCenterX) {
                sideOfCollision = LEFT;
            } else {
                sideOfCollision = RIGHT;
            }
        }

        // Halt the clock
        if (sideOfCollision == type) {
            ticking = false;
        }
    }

    /**
     * Undocumented
     */
    public void runTaskStep() {
        calculateCurrentFrame();
    }

    /**
     * Starts this detonator's countdown timer
     *
     * @param minutes The number of minutes in the timer
     * @param seconds The number of seconds before this timer detonates
     *        (between 0-60).
     */
    public void start(int minutes, int seconds) {
        expires = minutes * 60000 + seconds * 1000;
        ticking = true;
    }

    /**
     * Determines the current time of the detonator.
     */
    protected void calculateCurrentFrame() {

        if (!ticking) {

            return;
        }

        expires -= host.deltaTickTimeMillis();

        if (expires <= 0) {
            expires = 0;
            setDestroyed();
        }
    }
}