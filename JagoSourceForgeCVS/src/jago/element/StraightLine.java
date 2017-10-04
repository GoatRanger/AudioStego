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


/**
 * Creates colored lines.
 *
 * @version 2.0
 * @author Karl A. Gossett
 * @since 4.1
 */
public class StraightLine extends SolidElement implements Reflective, ReflectiveColors, FlatObject {

    /** The color of this line */
    protected Color color;

    //protected int[] xpoints;
    //protected int[] ypoints;
    protected java.awt.Point p1;
    protected java.awt.Point p2;
    protected int width;

    /**
     * Returns the amount of reflected light from this line, as an integer
     * 0-100
     */
    protected int reflectedLevel = -1;

    /**
     * Creates a new <CODE>StraightLine</CODE> with the specified color (or
     * amount of reflection).
     *
     */
    public StraightLine(int x1, int y1, int x2, int y2, int color) {
        super();
        p1 = new java.awt.Point(x1,y1);
        p2 = new java.awt.Point(x2,y2);

        size = new java.awt.Dimension(Math.abs(x1-x2), Math.abs(y1-y2));

        setColor(color);
        width = 1;
    }
    
    public StraightLine(int x1, int y1, int x2, int y2, int color, int width) {
      this(x1,y1,x2,y2,color);
      this.width = width;
    }

    /**
     * Returns the color of the line.  Newer versions should use
     * getReflection() instead.
     *
     * @return The color of this line.
     * @deprecated use <CODE>getReflection</CODE>
     */
    public int getColor() {

        return getReflection();
    }

    /**
     * Sets the color.  This can be based on the colors defined in the {@link
     * Reflective} interface, or on the actual amount of reflected light.
     *
     * @param level   The color of line.
     */
    public void setReflection(int level) {
        setColor(level);
    }

    /**
     * Returns the level of reflection of light.
     *
     * @return The level of reflected light, either as a continuous level,  or
     *         as one of the {@link Reflective} constants.
     */
    public int getReflection() {

        return reflectedLevel;
    }

    /**
     * Draws the colored line in the specified context
     *
     * @param g2 The <CODE>Graphics2D</CODE> context.
     */
    public void draw(java.awt.Graphics2D g2) {

        g2.setColor(color);
        g2.setStroke(new java.awt.BasicStroke((float)width, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
        g2.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
    }

    /**
     * Sets the color of this line.  If the value specified is not one of the
     * standard constants, a grayscale line will be created using the
     * parameter as the level of gray.
     *
     * @param value  The color of the line, from the
     *        <CODE>ReflectiveColors</CODE> constants; otherwise, level  of
     *        gray
     */
    protected void setColor(int value) {

        if (value < 0 || value > 100) {
            value = 0;
        }

        switch (value) {

            case ReflectiveColors.BLACK:
                color = Color.black;

                break;

            case ReflectiveColors.RED:
                color = new Color(128, 0, 0);

                break;

            case ReflectiveColors.GREEN:
                color = new Color(0, 255, 0);

                break;

            case ReflectiveColors.BLUE:
                color = new Color(0, 0, 255);

                break;

            case ReflectiveColors.WHITE:
                color = Color.white;

                break;

            case ReflectiveColors.DARK_GRAY:
                color = Color.darkGray;

                break;

            case ReflectiveColors.MED_GRAY:
                color = Color.gray;

                break;

            case ReflectiveColors.LIGHT_GRAY:
                color = Color.lightGray;

                break;

            default:

                int newColor = (int)(value * 2.55);
                color = new Color(newColor, newColor, newColor);

                break;
        }

        reflectedLevel = value;
    }
}