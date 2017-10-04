/*
 * Copyright (C) 2002 USMA This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU General Public License along with this program; if not,
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package eecs.robot.jago.element;

/**
 * A polar coordinate pair (magnitude, direction), where the 0 degree heading relates to a vertical vector (x
 * component=0). Since this is different from a standard mathematical polar coordinate system, users of this class
 * should take care to implement the correct math based on this transformation.
 * 
 * @author Karl A. Gossett
 */
public class PolarPoint {

  private double direction;

  private double magnitude;

  /** Creates a new instance of PolarPoint */
  public PolarPoint() {
    this(0.0, 0.0);
  }

  public PolarPoint(double magnitude, double direction) {
    this.direction = direction;
    this.magnitude = magnitude;
  }

  public PolarPoint(PolarPoint vector) {
    direction = vector.getDirection();
    magnitude = vector.getMagnitude();
  }

  /*
   * 
   */
  public PolarPoint(double xbase, double ybase, double x2, double y2) {
    magnitude = Math.sqrt((ybase - y2) * (ybase - y2) + (xbase - x2) * (xbase - x2));
    double xDiff = x2 - xbase;
    double yDiff = y2 - ybase;
    direction = (Math.toDegrees(Math.atan2(yDiff, xDiff)) + 450) % 360;
  }

  public int hashCode() {
    return (int) (direction * 31 + magnitude);
  }

  public String toString() {
    return "PolarPoint [" + direction + ", " + magnitude + "]";
  }

  public boolean equals(Object obj) {
    PolarPoint pt = (PolarPoint) obj;
    return (direction == pt.getDirection() && magnitude == pt.getMagnitude());
  }

  public Object clone() {
    PolarPoint pt = new PolarPoint(this);
    return pt;
  }

  public void setVector(double magnitude, double direction) {
    this.direction = direction;
    this.magnitude = magnitude;
  }

  public PolarPoint getVector() {
    return new PolarPoint(magnitude, direction);
  }

  public void addToDirection(double value) {
    direction = (direction + value + 360) % 360;
  }

  public PolarPoint add(PolarPoint vector) {
    double xComp = this.getX() + vector.getX();
    double yComp = this.getY() + vector.getY();
    direction = (Math.toDegrees(Math.atan2(yComp, xComp)) + 450) % 360;
    magnitude = Math.sqrt(xComp * xComp + yComp * yComp);
    return this;
  }

  public PolarPoint subtract(PolarPoint vector) {
    double xComp = this.getX() - vector.getX();
    double yComp = this.getY() - vector.getY();
    direction = (Math.toDegrees(Math.atan2(yComp, xComp)) + 450) % 360;
    magnitude = Math.sqrt(xComp * xComp + yComp * yComp);
    return this;
  }

  public double getX() {
    return magnitude * Math.cos(Math.toRadians(direction - 90));
  }

  /**
   * Returns the y component of the vector, with a coordinate system where the y axis is inverted (positive down).
   */
  public double getY() {
    return magnitude * Math.sin(Math.toRadians(direction - 90));
  }

  public double getDirection() {
    return direction;
  }

  public double getMagnitude() {
    return magnitude;
  }

  public void setDirection(double direction) {
    this.direction = direction;
  }

  public void setMagnitude(double magnitude) {
    this.magnitude = magnitude;
  }
}