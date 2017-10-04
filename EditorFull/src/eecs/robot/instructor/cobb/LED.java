package eecs.robot.instructor.cobb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import eecs.robot.jago.element.BrightLight;
import eecs.robot.jago.element.Mobile;
import eecs.robot.jago.element.ReflectiveColors;

public class LED extends BrightLight implements Mobile, ReflectiveColors {
  int color;

  public LED() {
    this(10);
  }

  public LED(int size) {
    super();
    color = LIGHT_GRAY;
    currentSize = size;
    this.size = new Dimension(currentSize, currentSize);
    createArea();
    intensity = 100;
  }

  protected void createArea() {
    myShape = new java.awt.geom.Area(new RoundRectangle2D.Double(location.getX() - (size.getWidth() / 2), location.getY()
        - (size.getHeight() / 2), size.getWidth(), size.getHeight(), 5, 5));
  }

  public void setColor(int color) {
    if (color != RED && color != GREEN && color != LIGHT_GRAY) {
      color = RED;
    }
    this.color = color;
  }

  public void setOff() {
    color = LIGHT_GRAY;
  }

  public void draw(Graphics2D g2) {
    Color c;
    if (color == GREEN) {
      c = new Color(0, 255, 0);
    }
    else
      if (color == RED) {
        c = new Color(255, 0, 0);
      }
      else {
        c = new Color(64, 64, 64);
      }
    Color oldColor = g2.getColor();
    g2.setColor(c);
    g2.fill(myShape);
    g2.setColor(oldColor);
  }

  public void setIntensity(int intensity) {
    if (intensity >= 0 && intensity <= 100) {
      this.intensity = intensity;
    }
    else {
      this.intensity = 100;
    }
  }

  public double getAngularVelocity() {
    return 0;
  }

  public double getHeading() {
    return 0;
  }

  public java.awt.geom.Point2D getPreviousPosition() {
    return location;
  }

  public double getVelocity() {
    return 0;
  }

  public void setPower(double speed) {
    // Determines the new power of the mobile element
  }

  public void updatePosition() {
    // Updates the current location based on velocity and heading.
  }
}