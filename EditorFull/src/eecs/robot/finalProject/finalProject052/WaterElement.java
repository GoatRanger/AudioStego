package eecs.robot.finalProject.finalProject052;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import eecs.robot.jago.element.Collidable;
import eecs.robot.jago.element.FlatObject;
import eecs.robot.jago.element.VisibleElement;

public class WaterElement extends VisibleElement implements Collidable, FlatObject {
  int width;
  Color color;
  GeneralPath path;
  boolean fillPath;
  boolean pendingCollision = false;

  public WaterElement(GeneralPath path, Color color, boolean fillPath, int width) {
    super();
    this.width = width;
    this.path = path;
    this.color = color;
    this.fillPath = fillPath;
  }

  public void pendingCollision(boolean pending) {
    pendingCollision = pending;
  }

  public void bounceOff(Collidable collisionElement) {
    //
  }

  public void handleCollision(Collidable collisionElement) {
    //
  }

  public void createArea() {
    this.myShape = new Area(path);
  }

  public void draw(Graphics2D g2) {
    g2.setColor(color);
    g2.setStroke(new java.awt.BasicStroke(width, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
    if (fillPath)
      g2.fill(path);
    g2.draw(path);
  }
}