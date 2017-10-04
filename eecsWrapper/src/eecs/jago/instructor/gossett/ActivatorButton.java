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
 * ActivatorButton.java
 *
 * Created on March 1, 2002, 10:36 PM
 */
package eecs.jago.instructor.gossett;

import java.awt.Color;


/**
 * @author Karl Gossett
 */
public class ActivatorButton
    extends FloorButton {

    public final int ALWAYS_INACTIVE = -1;
    protected boolean activated = false;
    protected boolean countSet = false;
    protected int currentPressCount = 0;
    protected boolean on = false;
    protected int type = 0;
    private int activationCount = 0;
    private Color[] colors = { Color.blue, Color.red, Color.green };

    /**
     * Creates a new instance of ActivatorButton that cannot be activated.
     * Must set the activation count to override.
     */
    public ActivatorButton() {
        super();
        activationCount = ALWAYS_INACTIVE;
    }

    public ActivatorButton(int activationCount) {
        this();
        this.activationCount = activationCount;
        countSet = true;
        on = true;
    }

    /**
     * Undocumented
     * 
     * @return Empty
     */
    public boolean isActivated() {

        return activated;
    }

    /**
     * Undocumented
     * 
     * @param count Empty
     */
    public void setActivationCount(int count) {

        if (countSet) {

            return;
        }

        if (count < 1) {
            count = 1;
        }

        activationCount = count;
        countSet = true;
    }

    /**
     * Undocumented
     * 
     * @param state Empty
     */
    public void setOn(boolean state) {
        on = state;

        if (!on) {
            activated = false;
        } else {
            reset();
        }
    }

    /**
     * Undocumented
     * 
     * @return Empty
     */
    public boolean isOn() {

        return on;
    }

    /**
     * Undocumented
     * 
     * @param type Empty
     */
    public void setType(int type) {

        if (type > colors.length || type < 0) {
            type = 0;
        }

        this.type = type;
    }

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

            if (on) {

                if (activated) {
                    g2.setColor(java.awt.Color.red);
                } else {
                    g2.setColor(colors[type]);
                }

                g2.fill3DRect((int)location.getX() + 3, 
                              (int)location.getY() + 3, 
                              (int)size.getWidth() - 6, 
                              (int)size.getHeight() - 6, !isPushed);
            } else {
                g2.setColor(java.awt.Color.darkGray);
                g2.fill3DRect((int)location.getX() + 3, 
                              (int)location.getY() + 3, 
                              (int)size.getWidth() - 6, 
                              (int)size.getHeight() - 6, false);
            }
        }
    }

    /**
     * Undocumented
     */
    public void reset() {
        currentPressCount = 0;
        on = true;
        activated = false;
        isPushed = false;
    }

    /**
     * Undocumented
     */
    protected void runTaskStep() {

        if (on) {

            if (hitThisUpdate) {
                isPushed = true;
            } else {
                isPushed = false;
            }

            if (leftToRise == 1) {
                currentPressCount++;

                // Just in case, wrap to 0
                if (currentPressCount == Integer.MAX_VALUE) {
                    currentPressCount = 0;
                }
            }

            leftToRise--;

            if (leftToRise <= 0) {
                hitThisUpdate = false;
                leftToRise = 0;
            }

            if (currentPressCount == activationCount) {
                activated = true;
            } else {
                activated = false;
            }
        }
    }
}