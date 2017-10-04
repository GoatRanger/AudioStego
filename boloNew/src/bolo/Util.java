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

package bolo;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;


public class Util {

    /**
     * Undocumented
     * 
     * @param radius Empty
     * @return Empty 
     */
    public static Shape circle(double radius) {

        return new Ellipse2D.Double(-radius, -radius, 2 * radius, 2 * radius);
    }

    /**
     * Undocumented
     * 
     * @param x Empty
     * @param y Empty
     * @param radius Empty
     * @return Empty 
     */
    public static Shape circle(double x, double y, double radius) {

        return new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 
                                    2 * radius);
    }

    /**
     * Undocumented
     * 
     * @param width Empty
     * @param height Empty
     * @return Empty 
     */
    public static Shape rectangle(double width, double height) {

        return new Rectangle2D.Double(-width / 2, -height / 2, width, height);
    }

    /**
     * Undocumented
     * 
     * @param x Empty
     * @param y Empty
     * @param width Empty
     * @param height Empty
     * @return Empty 
     */
    public static Shape rectangle(double x, double y, double width, 
                                  double height) {

        return new Rectangle2D.Double(x - width / 2, y - height / 2, width, 
                                      height);
    }

    /**
     * Undocumented
     * 
     * @param msg Empty
     */
    public static void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ERROR", 
                                      JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Undocumented
     * 
     * @param msg Empty
     */
    public static void showFatalError(String msg) {
        showError(msg);
        System.exit(0);
    }

    static double bound(double lo, double x, double hi) {

        if (x >= hi) {

            return hi;
        }
         else if (x <= lo) {

            return lo;
        }
         else {

            return x;
        }
    }

    static double distance(double dx, double dy) {

        return Math.sqrt(dx * dx + dy * dy);
    }

    static boolean overlaps(Shape shape, Area area) {

        Area myArea = new Area(shape);
        myArea.intersect(area);

        return !myArea.isEmpty();
    }

    static boolean overlaps(Shape shape1, Shape shape2) {

        Area area = new Area(shape1);
        area.intersect(new Area(shape2));

        return !area.isEmpty();
    }

    static Shape rotate(Shape shape, double radians) {

        return AffineTransform.getRotateInstance(radians)
                              .createTransformedShape(shape);
    }

    static Shape scale(Shape shape, double sx, double sy) {

        return AffineTransform.getScaleInstance(sx, sy)
                              .createTransformedShape(shape);
    }

    static Shape translate(Shape shape, double dx, double dy) {

        return AffineTransform.getTranslateInstance(dx, dy)
                              .createTransformedShape(shape);
    }
}