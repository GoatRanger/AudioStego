package eecs.robot.finalProject.finalProject051;

import eecs.robot.jago.element.Collidable;
import eecs.robot.jago.element.SolidElement;

public class TeleportationCenter extends SolidElement {
  int xLocation;
  int yLocation;
  boolean pendingCollision = false;

  public TeleportationCenter() {
    super();
    setAppearance("/eecs/images/teleporter.gif");
  }

  public TeleportationCenter(int x, int y) {
    xLocation = x;
    yLocation = y;
  }

  public void pendingCollision(boolean pending) {
    pendingCollision = pending;
  }

  public void bounceOff(Collidable collisionElement) {
    // does nothing
  }

  public void handleCollision(Collidable collisionElement) {
    //
  }

  public int getXLocation() {
    return xLocation;
  }

  public void setXLocation(int location) {
    xLocation = location;
  }

  public int getYLocation() {
    return yLocation;
  }

  public void setYLocation(int location) {
    yLocation = location;
  }
}