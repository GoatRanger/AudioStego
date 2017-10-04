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

 // DiagonalColoredLine.java
package eecs.robot.jago.element;

import java.awt.Color;
import java.awt.Polygon;

public class DiagonalColoredLine extends SolidElement
    implements Reflective, ReflectiveColors, FlatObject {

    protected Color color;
    protected int reflectedLevel = -1;
    protected double x1 = -1;
    protected double x2 = -1;
    protected double y1 = -1;
    protected double y2 = -1;
    protected double width = -1;

    public DiagonalColoredLine(double x1, double y1, double x2, double y2,
                               double width, int color) {
        super();
        if (((x1 < x2) || (y1<y2)) &&
            !((x1 < x2) && (y2<y1))) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        else {
            this.x1 = x2;
            this.y1 = y2;
            this.x2 = x1;
            this.y2 = y1;
        }
        this.width = width;
        setColor(color);
    }

    public void draw(java.awt.Graphics2D g2) {
        g2.setColor(color);

        double[] xRot = new double[4];
        double[] yRot = new double[4];
        double[] xTrans = new double[4];
        double[] yTrans = new double[4];
        double[] x = new double[4];
        double[] y = new double[4];
        int[] xInt = new int[4];
        int[] yInt = new int[4];
        int numPoints = 4;

        double distance = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));

        if (x1 == x2) {
          xInt[0] = (int) (x1 - (0.5 * width));
          yInt[0] = (int) y1;
          xInt[1] = (int) (x1 + (0.5 * width));
          yInt[1] = (int) y1;
          xInt[2] = (int) (x1 + (0.5 * width));
          yInt[2] = (int) y2;
          xInt[3] = (int) (x1 - (0.5 * width));
          yInt[3] = (int) y2;
          Polygon polygon = new Polygon(xInt, yInt, numPoints);
          g2.fillPolygon(polygon);
          return;
        }

        if (y1 == y2) {
          xInt[0] = (int) x1;
          yInt[0] = (int) (y1 + (0.5 * width));
          xInt[1] = (int) x1;
          yInt[1] = (int) (y1 - (0.5 * width));
          xInt[2] = (int) x2;
          yInt[2] = (int) (y2 - (0.5 * width));
          xInt[3] = (int) x2;
          yInt[3] = (int) (y2 + (0.5 * width));
          Polygon polygon = new Polygon(xInt, yInt, numPoints);
          g2.fillPolygon(polygon);
          return;
        }

        xRot[0] = -0.5 * width;
        yRot[0] = 0;
        xRot[1] = 0.5 * width;
        yRot[1] = 0;
        xRot[2] = 0.5 * width;
        yRot[2] = distance;
        xRot[3] = -0.5 * width;
        yRot[3] = distance;

        double angleOfRotation = 2*Math.PI - Math.atan((x2-x1)/(y2-y1));

        xTrans[0] = xRot[0] * Math.cos(angleOfRotation) + yRot[0] * (-Math.sin(angleOfRotation));
        yTrans[0] = xRot[0] * Math.sin(angleOfRotation) + yRot[0] * Math.cos(angleOfRotation);
        xTrans[1] = xRot[1] * Math.cos(angleOfRotation) + yRot[1] * (-Math.sin(angleOfRotation));
        yTrans[1] = xRot[1] * Math.sin(angleOfRotation) + yRot[1] * Math.cos(angleOfRotation);
        xTrans[2] = xRot[2] * Math.cos(angleOfRotation) + yRot[2] * (-Math.sin(angleOfRotation));
        yTrans[2] = xRot[2] * Math.sin(angleOfRotation) + yRot[2] * Math.cos(angleOfRotation);
        xTrans[3] = xRot[3] * Math.cos(angleOfRotation) + yRot[3] * (-Math.sin(angleOfRotation));
        yTrans[3] = xRot[3] * Math.sin(angleOfRotation) + yRot[3] * Math.cos(angleOfRotation);

        x[0] = xTrans[0] + x1;
        y[0] = yTrans[0] + y1;
        x[1] = xTrans[1] + x1;
        y[1] = yTrans[1] + y1;
        x[2] = xTrans[2] + x1;
        y[2] = yTrans[2] + y1;
        x[3] = xTrans[3] + x1;
        y[3] = yTrans[3] + y1;

        xInt[0] = (int) x[0];
        yInt[0] = (int) y[0];
        xInt[1] = (int) x[1];
        yInt[1] = (int) y[1];
        xInt[2] = (int) x[2];
        yInt[2] = (int) y[2];
        xInt[3] = (int) x[3];
        yInt[3] = (int) y[3];

        Polygon polygon = new Polygon(xInt, yInt, numPoints);
        g2.fillPolygon(polygon);
    }

    public void setReflection(int level) {
        setColor(level);
    }

    public int getReflection() {
        return reflectedLevel;
    }

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