/*
 * Created on Apr 14, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.jago.instructor.gossett;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import jago.SimEngine;
import jago.element.MobileElement;
import jago.element.PolarPoint;

/**
 * @author DK8685
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TurtleIcon extends MobileElement {
  /* (non-Javadoc)
   * @see jago.element.MobileElement#createArea()
   */
  private Color color = Color.black;
  
  public TurtleIcon() {
    setAppearance("images/turtle.gif");
    createArea();
  }
  
  /* (non-Javadoc)
   * @see jago.element.MobileElement#addedTo(jago.SimEngine, java.awt.geom.Point2D)
   */
  public void addedTo(SimEngine sim, Point2D loc) {
    // TODO Auto-generated method stub
    super.addedTo(sim, loc);
    location.setLocation(location.getX()-size.getWidth()/2,location.getY()-size.getHeight()/2);
  }
  
  protected void createArea() {
    myShape = new java.awt.geom.Area(new java.awt.geom.Rectangle2D.Double(
                               location.getX(),
                               location.getY(),
                               size.getWidth(),
                               size.getHeight()));

    AffineTransform at = new AffineTransform();
    at.rotate(Math.toRadians(velVector.getDirection()),
              location.getX() + size.getWidth() / 2.0,
              location.getY() + size.getHeight() / 2.0);
    myShape.transform(at);
    at.setToIdentity();
    previousShape = (java.awt.geom.Area)myShape.clone();
    previousVel = (PolarPoint)velVector.clone();
    previousLocation.setLocation(location);
  }
  
  public void moveTo(double x, double y) {
  	location.setLocation(x-size.getWidth()/2,y-size.getHeight()/2);
  	createArea();
  }
  
  public void turnTo(double heading) {
  	velVector.setDirection(heading);
  	createArea();
  }
  
  public void turn(double heading) {
    velVector.addToDirection(heading);
    createArea();
  }
}
