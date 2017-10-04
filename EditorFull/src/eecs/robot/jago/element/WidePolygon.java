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

public class WidePolygon extends SolidElement implements Reflective, ReflectiveColors, FlatObject {

  protected double[] x;

  protected double[] y;

  protected int numPoints = 0;

  protected double width = -1;

  protected int color;

  protected boolean isVerticesOn = false;

  protected boolean isShapeACircle = false;

  protected int widthOfShape = -1;

  protected int reflectedLevel = -1;

  protected int colorOfShape;

  protected int indexRedShape = -1;

  public WidePolygon(int[] x, int[] y, int numPoints, double width, int color, boolean isVerticesOn,
      boolean isShapeACircle, int widthOfShape, int colorOfShape, int indexRedShape) {

    super();

    this.x = new double[numPoints];
    this.y = new double[numPoints];
    for (int i = 0; i < numPoints; i++) {
      this.x[i] = x[i];
      this.y[i] = y[i];
    }

    this.numPoints = numPoints;
    this.width = width;
    this.color = color;
    this.isVerticesOn = isVerticesOn;
    this.isShapeACircle = isShapeACircle;
    this.widthOfShape = widthOfShape;
    this.colorOfShape = colorOfShape;
    this.indexRedShape = indexRedShape;
  }

  protected void createArea() {
    int[] xint = new int[x.length];
    int[] yint = new int[y.length];
    for (int i = 0; i < x.length; i++) {
      xint[i] = (int) x[i];
      yint[i] = (int) y[i];
    }
    myShape = new java.awt.geom.Area(new java.awt.Polygon(xint, yint, xint.length));
  }

  public void draw(java.awt.Graphics2D g2) {
    g2.setColor(setColor(color));
    g2.setStroke(new java.awt.BasicStroke((float) width, java.awt.BasicStroke.CAP_ROUND,
        java.awt.BasicStroke.JOIN_ROUND));
    g2.draw(myShape);

    if (isVerticesOn) {
      for (int i = 0; i < numPoints; i++) {
        if (i == indexRedShape)
          g2.setColor(new Color(128, 0, 0));
        else
          g2.setColor(setColor(colorOfShape));

        int xShape = (int) (x[i] - 0.5 * widthOfShape);
        int yShape = (int) (y[i] - 0.5 * widthOfShape);

        if (isShapeACircle)
          g2.fillOval(xShape, yShape, widthOfShape, widthOfShape);
        else
          g2.fillRect(xShape, yShape, widthOfShape, widthOfShape);
      }
    }

  }

  public void setReflection(int level) {
    setColor(level);
  }

  public int getReflection() {
    return reflectedLevel;
  }

  protected Color setColor(int value) {
    if (value < 0 || value > 100)
      value = 0;
    reflectedLevel = value;
    switch (value) {
      case ReflectiveColors.BLACK:
        return Color.black;
      case ReflectiveColors.RED:
        return new Color(128, 0, 0);
      case ReflectiveColors.GREEN:
        return new Color(0, 255, 0);
      case ReflectiveColors.BLUE:
        return new Color(0, 0, 255);
      case ReflectiveColors.WHITE:
        return Color.white;
      case ReflectiveColors.DARK_GRAY:
        return Color.darkGray;
      case ReflectiveColors.MED_GRAY:
        return Color.gray;
      case ReflectiveColors.LIGHT_GRAY:
        return Color.lightGray;
      default:
        int newColor = (int) (value * 2.55);
        return new Color(newColor, newColor, newColor);
    }
  }
}