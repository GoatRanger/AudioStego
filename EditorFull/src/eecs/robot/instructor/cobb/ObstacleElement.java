package eecs.robot.instructor.cobb;

import eecs.robot.jago.element.SolidElement;

public class ObstacleElement {
  static SolidElement leftObstacle;
  static SolidElement rightObstacle;

  public ObstacleElement() {
    //
  }

  public void setLeftObstacle(SolidElement obstacle) {
    leftObstacle = obstacle;
    System.out.println();
  }

  public void setRightObstacle(SolidElement obstacle) {
    rightObstacle = obstacle;
  }

  public SolidElement getLeftObstacle() {
    return leftObstacle;
  }

  public SolidElement getRightObstacle() {
    return rightObstacle;
  }
}