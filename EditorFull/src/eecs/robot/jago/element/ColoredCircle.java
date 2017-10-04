/*
 * Copyright (C) 2002 USMA This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU General Public License along with this program; if not,
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package eecs.robot.jago.element;

import java.awt.Color;
import java.awt.geom.Area;

/**
 * Creates colored lines.
 * 
 * @version 2.0
 * @author Karl A. Gossett
 * @since 4.1
 */
public class ColoredCircle extends SolidElement implements Reflective, ReflectiveColors, FlatObject {

  /** The color of this line */
  protected Color color;

  protected int[] xpoints;

  protected int[] ypoints;

  /**
   * Returns the amount of reflected light from this line, as an integer 0-100
   */
  protected int reflectedLevel = -1;

  /**
   * Creates a new <CODE>ColoredLine</CODE> with the specified color (or amount of reflection).
   * 
   * @param color
   *          The color of this line.
   */
  public ColoredCircle(double radius, int color) {
    super();
    double width = 2 * radius;
    double height = 2 * radius;
    if (width > 0 && height > 0) {
      size = new java.awt.Dimension((int) width, (int) height);
    }

    setColor(color);
  }

  /**
   * overriding SimElements Create Area method.
   */
  protected void createArea() {
    myShape = new Area(new java.awt.geom.Ellipse2D.Double(location.getX() - (size.getWidth() / 2), location.getY()
        - (size.getHeight() / 2), size.getWidth(), size.getHeight()));
  }

  /**
   * Returns the color of the line. Newer versions should use getReflection() instead.
   * 
   * @return The color of this line.
   * @deprecated use <CODE>getReflection</CODE>
   */
  public int getColor() {

    return getReflection();
  }

  /**
   * Sets the color. This can be based on the colors defined in the {@linkReflective} interface, or on the actual
   * amount of reflected light.
   * 
   * @param level
   *          The color of line.
   */
  public void setReflection(int level) {
    setColor(level);
  }

  /**
   * Returns the level of reflection of light.
   * 
   * @return The level of reflected light, either as a continuous level, or as one of the {@link Reflective}constants.
   */
  public int getReflection() {

    return reflectedLevel;
  }

  /**
   * Draws the colored line in the specified context
   * 
   * @param g2
   *          The <CODE>Graphics2D</CODE> context.
   */
  public void draw(java.awt.Graphics2D g2) {

    if (size.getWidth() == 0 || size.getHeight() == 0) {

      return;
    }

    g2.setColor(color);
    g2.fill(myShape);
  }

  /**
   * Sets the color of this line. If the value specified is not one of the standard constants, a grayscale line will be
   * created using the parameter as the level of gray.
   * 
   * @param value
   *          The color of the line, from the <CODE>ReflectiveColors</CODE> constants; otherwise, level of gray
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

        int newColor = (int) (value * 2.55);
        color = new Color(newColor, newColor, newColor);

        break;
    }

    reflectedLevel = value;
  }
}