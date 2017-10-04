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
 * Message.java
 *
 * Created on November 9, 2001, 11:53 PM
 */
package jago.element;

import java.awt.Color;


/**
 * This class creates a message that appears in the simulation at the desired
 * location.
 *
 * @version 0.1
 * @author Karl A. Gossett
 * @since 5.0
 */
public class Message
    extends VisibleElement {

    /** A black message */
    public static final int COLOR_BLACK = 0;

    /** A blue message */
    public static final int COLOR_BLUE = 2;

    /** An orange message */
    public static final int COLOR_ORANGE = 4;

    /** A red message */
    public static final int COLOR_RED = 1;

    /** A white message */
    public static final int COLOR_WHITE = 3;

    /** The duration for a message that should always be visible */
    public static final int INFINITE = -1;

    /** The Font for this message */
    java.awt.Font messageFont;
    long startTime;
    String text;
    Color textColor = Color.black;

    /** The number of seconds to make this message appear */
    double timeToLive = 0;

    /**
     * Creates a new Message to display the specified text.  Uses a font size
     * of 18, and a color of blue.
     *
     * @param text   The String to display as a message
     */
    public Message(String text) {
        this(text, 18, COLOR_BLUE);
    }

    /**
     * Creates a new Message with the specified text size and color. If the
     * color specified does not match one of the constants, cyan will be
     * used.
     *
     * @param text DOCUMENT ME!
     * @param fontSize DOCUMENT ME!
     * @param color DOCUMENT ME!
     */
    public Message(String text, int fontSize, int color) {
        super();
        this.text = text;

        if (fontSize < 0 || fontSize > 100) {
            fontSize = 18;
        }

        switch (color) {

            case COLOR_BLACK:
                textColor = Color.black;

                break;

            case COLOR_RED:
                textColor = Color.red;

                break;

            case COLOR_BLUE:
                textColor = Color.blue;

                break;

            case COLOR_WHITE:
                textColor = Color.white;

                break;

            case COLOR_ORANGE:
                textColor = Color.orange;

                break;

            default:
                textColor = Color.cyan;
        }

        messageFont = new java.awt.Font("Serif", java.awt.Font.BOLD, fontSize);
        timeToLive = INFINITE;
        determineBounds();
    }

    /**
     * Sets the text that this message will display.  Using this method can
     * change the message displayed.
     *
     * @param url     The String to display
     */
    public void setAppearance(String url) {
        text = url;
        if (host != null) {
          host.setStaticChanged();
        }
    }

    /**
     * The amount of time that the message should be visible, in seconds.
     *
     * @param seconds
     */
    public void setDuration(int seconds) {

        if (seconds >= 0 || seconds == INFINITE) {
            timeToLive = seconds;
        }
    }

    /**
     * Notifies this message that it was added to a simulation, and starts the
     * expiration timer, if one is set.
     * @param sim Empty
     * @param loc Empty
     */
    public void addedTo(jago.SimEngine sim, java.awt.geom.Point2D loc) {
        super.addedTo(sim, loc);
        startTime = System.currentTimeMillis();
    }

    /**
     * Draws the image of the object that implements this interface into a
     * Java2D (Graphics2D) context.
     *
     * @param g2    The Graphics2D context to draw in.
     */
    public void draw(java.awt.Graphics2D g2) {

        Color oldColor = g2.getColor();
        java.awt.Font oldFont = g2.getFont();

        if (timeToLive > 0 || (timeToLive == INFINITE)) {
            g2.setColor(textColor);
            g2.setFont(messageFont);
            g2.drawString(text, (int)location.getX(), (int)location.getY());
        }

        g2.setColor(oldColor);
        g2.setFont(oldFont);
    }

    /**
     * Returns the contents of the message
     *
     * @return DOCUMENT ME!
     */
    public java.lang.String toString() {

        return ("Message: " + text);
    }

    /**
     * Undocumented
     */
    protected void runTaskStep() {

        if (timeToLive > 0) {

            if ((System.currentTimeMillis() - startTime) / 1000 > timeToLive) {
                timeToLive = 0;
            }
        }

        // Since a static element, need to remove to make it disappear
        if (timeToLive == 0 && added) {
            host.remove(this);
        }
    }

    /**
     * Undocumented
     */
    private void determineBounds() {

        java.awt.image.BufferedImage buffer = new java.awt.image.BufferedImage(50, 50,
                                                 java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g2 = buffer.createGraphics();
        g2.setFont(messageFont);

        java.awt.geom.Rectangle2D bounds = messageFont.getStringBounds(
                                     text,
                                     g2.getFontRenderContext());
        size = bounds.getBounds()
                     .getSize();
        createArea();
    }
}