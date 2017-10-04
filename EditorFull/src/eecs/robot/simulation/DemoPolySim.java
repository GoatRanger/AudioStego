package eecs.robot.simulation;

import eecs.robot.jago.SimBox;
import eecs.robot.jago.element.BrightLight;
import eecs.robot.jago.element.StandardRobot;

public class DemoPolySim extends SimBox {
  /**
   * The x coordinates for the polygon. Element 0 is the west (left-most) vertex, and the vertices are identified counterclockwise around
   * the polygon.
   */
  protected int[] xPoints;
  /**
   * The y coordinates for the polygon. Element 0 is the west (left-most) vertex, and the vertices are identified counterclockwise around
   * the polygon.
   */
  protected int[] yPoints;
  /** True of a polygon has already been added to the simulation */
  protected boolean hasPolygon = false;
  /** DOCUMENTATION INCOMPLETE */
  protected boolean isConvex = true;
  /** DOCUMENTATION INCOMPLETE */
  protected boolean isRandom = false;
  /** DOCUMENTATION INCOMPLETE */
  protected int lineWidth = 16;
  /** DOCUMENTATION INCOMPLETE */
  protected int numPoints = 0;
  /** DOCUMENTATION INCOMPLETE */
  protected int redIndex = 3;
  /** DOCUMENTATION INCOMPLETE */
  protected int shapeWidth = 30;
  StandardRobot server;

  /**
   * Creates a new instance of PolygonSim
   * 
   * @param isRandom
   *          DOCUMENTATION INCOMPLETE
   * @param isConvex
   *          DOCUMENTATION INCOMPLETE
   */
  public DemoPolySim(boolean isRandom, boolean isConvex) {
    this();
    createPolygon(isRandom, isConvex);
  }

  /**
   * Creates a new PolygonSim object.
   */
  public DemoPolySim() {
    super();
    setBackgroundImage("/eecs/images/calibration_test.gif");
    server = new StandardRobot();
    server.setInitialHeading(115);
    add(server, 230, 200);
    add(new BrightLight(100), 244, 213);
  }

  /**
   * Returns the array of x coordinates of the polygon.
   * 
   * @return An array of integers containing the x coordinates. The array will be exactly the same size as the number of vertices.
   */
  public int[] getXPoints() {
    return xPoints;
  }

  /**
   * Returns the array of y coordinates of the polygon.
   * 
   * @return An array of integers containing the x coordinates. The array will be exactly the same size as the number of vertices.
   */
  public int[] getYPoints() {
    return yPoints;
  }

  public void createPolygon(boolean isRandom, boolean isConvex) {
    if (hasPolygon) {
      return;
    }
    this.isRandom = isRandom;
    this.isConvex = isConvex;
    generateRandomPoints();
    hasPolygon = true;
  }

  /**
   * Once the data has been received and verified, this causes the receiving robot to drive backward a small amount, as a visual indicator
   * that it is complete.
   */
  public void dataComplete() {
    server.goBackward(1000);
  }

  protected void displayPoints() {
    for (int i = 0; i < numPoints; i++) {
      printLine("( " + xPoints[i] + ", " + yPoints[i] + ")");
    }
  }

  protected void generateRandomPoints() {
    numPoints = 3;
    xPoints = new int[numPoints];
    yPoints = new int[numPoints];
    xPoints[0] = 445;
    yPoints[0] = 185;
    xPoints[1] = 580;
    yPoints[1] = 195;
    xPoints[2] = 475;
    yPoints[2] = 370;
    redIndex = 1;
  }
}