package bolo;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Util {
  public static void showError(String msg) {
	JOptionPane.showMessageDialog(
	  null, 
	  msg,
      "ERROR",
      JOptionPane.ERROR_MESSAGE); 
  }

  public static void showFatalError(String msg) {
    showError(msg);
	System.exit(0);
  }

  public static Shape circle(double radius) {
    return new Ellipse2D.Double(-radius,-radius,2*radius,2*radius);
  }

  public static Shape circle(double x,double y,double radius) {
    return new Ellipse2D.Double(x-radius,y-radius,2*radius,2*radius);
  }

  public static Shape rectangle(double width,double height) {
    return new Rectangle2D.Double(-width/2,-height/2,width,height);
  }

  public static Shape rectangle(double x,double y,double width,double height) {
    return new Rectangle2D.Double(x-width/2,y-height/2,width,height);
  }

  static Shape rotate(Shape shape,double radians) {
    return AffineTransform.getRotateInstance(radians)
	                      .createTransformedShape(shape);
  }

  static Shape translate(Shape shape,double dx,double dy) {
    return AffineTransform.getTranslateInstance(dx,dy)
	                      .createTransformedShape(shape);
  }

  static Shape scale(Shape shape,double sx,double sy) {
    return AffineTransform.getScaleInstance(sx,sy)
	                      .createTransformedShape(shape);
  }

  static boolean overlaps(Shape shape,Area area) {
    Area myArea = new Area(shape);
	myArea.intersect(area);
	return !myArea.isEmpty();
  }

  static boolean overlaps(Shape shape1,Shape shape2) {
    Area area = new Area(shape1);
	area.intersect(new Area(shape2));
	return !area.isEmpty();
  }

  static double distance(double dx,double dy) {
    return Math.sqrt(dx*dx + dy*dy);
  }

  static double bound(double lo,double x,double hi) {
    if (x >= hi) return hi;
	else if (x <= lo) return lo;
	else return x;
  }

}