package eecs.robot.simulation;

import eecs.robot.jago.SimBox;
import eecs.robot.jago.element.BrightLight;
import eecs.robot.jago.element.ReflectiveColors;
import eecs.robot.jago.element.StandardRobot;
import eecs.robot.jago.element.WidePolygon;

public class PolygonSim extends SimBox {
  protected int[] xPoints;
  protected int[] yPoints;
  protected boolean hasPolygon = false;
  protected boolean isConvex = true;
  protected boolean isRandom = false;
  protected int lineWidth = 16;
  protected int numPoints = 0;
  protected int redIndex = 3;
  protected int shapeWidth = 30;
  StandardRobot server;
  WidePolygon polygon = null;

  public PolygonSim(boolean isRandom, boolean isConvex) {
    this();
    createPolygon(isRandom, isConvex);
  }

  public PolygonSim() {
    super();
    setBackgroundImage("/eecs/images/calibration.gif");
    server = new StandardRobot();
    server.setInitialHeading(115);
    add(server, 90, 60);
    add(new BrightLight(100), 110, 75);
  }

  public int getConcaveIndex() {
    return redIndex;
  }

  public int[] getXPoints() {
    int[] rtn = new int[xPoints.length];
    System.arraycopy(xPoints, 0, rtn, 0, xPoints.length);
    return rtn;
  }

  public int[] getYPoints() {
    int[] rtn = new int[yPoints.length];
    System.arraycopy(yPoints, 0, rtn, 0, yPoints.length);
    return rtn;
  }

  public void createPolygon(boolean isRandom, boolean isConvex) {
    if (hasPolygon) {
      remove(polygon);
      try {
        Thread.sleep(200);
      }
      catch (InterruptedException e) {
        //
      }
    }
    this.isRandom = isRandom;
    this.isConvex = isConvex;
    generateRandomPoints();
    polygon = new WidePolygon(xPoints, yPoints, numPoints, lineWidth, ReflectiveColors.WHITE, true, true, shapeWidth,
        ReflectiveColors.BLACK, redIndex);
    add(polygon, 0, 0);
    hasPolygon = true;
  }

  public void dataComplete() {
    server.goBackward(1000);
  }

  protected void displayPoints() {
    for (int i = 0; i < numPoints; i++) {
      printLine("( " + xPoints[i] + ", " + yPoints[i] + ")");
    }
  }

  protected void generateRandomConcavePolygon(int lengthOfSide, double angleInDegrees) {
    generateRandomConvexPolygon(lengthOfSide, angleInDegrees);
    redIndex = (int) (Math.random() * (numPoints));
    int counterclockwiseOfRedIndex = redIndex - 1;
    if (counterclockwiseOfRedIndex == -1) {
      counterclockwiseOfRedIndex = numPoints - 1;
    }
    int clockwiseOfRedIndex = redIndex + 1;
    if (clockwiseOfRedIndex == numPoints) {
      clockwiseOfRedIndex = 0;
    }
    int oldX = xPoints[redIndex];
    int oldY = yPoints[redIndex];
    xPoints[redIndex] = (xPoints[counterclockwiseOfRedIndex] + xPoints[clockwiseOfRedIndex]) / 2;
    yPoints[redIndex] = (yPoints[counterclockwiseOfRedIndex] + yPoints[clockwiseOfRedIndex]) / 2;
    if ((xPoints[counterclockwiseOfRedIndex] <= oldX) && (xPoints[clockwiseOfRedIndex] <= oldX)) {
      xPoints[counterclockwiseOfRedIndex] = (xPoints[counterclockwiseOfRedIndex] + oldX) / 2;
      xPoints[clockwiseOfRedIndex] = (xPoints[clockwiseOfRedIndex] + oldX) / 2;
    }
    else {
      yPoints[counterclockwiseOfRedIndex] = (yPoints[counterclockwiseOfRedIndex] + oldY) / 2;
      yPoints[clockwiseOfRedIndex] = (yPoints[clockwiseOfRedIndex] + oldY) / 2;
    }
  }

  protected void generateRandomConvexPolygon(int lengthOfSide, double angleInDegrees) {
    redIndex = 100;
    xPoints[0] = 300;
    yPoints[0] = 150;
    for (int i = 1; i < numPoints; i++) {
      xPoints[i] = 300;
      yPoints[i] = 150 + lengthOfSide;
      translatePoints(-xPoints[i], -yPoints[i]);
      rotatePointsBy(angleInDegrees);
      translatePoints(300, 150 + lengthOfSide);
      translatePoints(0, -lengthOfSide);
    }
    int randomRotationAngleInDegrees = (int) (Math.round(Math.random() * 40) + 5);
    int tempX = xPoints[numPoints - 1];
    int tempY = yPoints[numPoints - 1];
    translatePoints(-xPoints[numPoints - 1], -yPoints[numPoints - 1]);
    rotatePointsBy(randomRotationAngleInDegrees);
    translatePoints(tempX, tempY);
  }

  protected void generateRandomPoints() {
    if (isRandom) {
      numPoints = 5 + ((int) (Math.round(Math.random())));
      isConvex = (((int) (Math.round(Math.random()))) == 0);
    }
    else {
      numPoints = 6;
    }
    xPoints = new int[numPoints];
    yPoints = new int[numPoints];
    if (!isRandom) {
      if (isConvex) {
        printLine("Generating Fixed Convex Polygon.");
        xPoints[0] = 300;
        yPoints[0] = 150;
        xPoints[1] = 320;
        yPoints[1] = 320;
        xPoints[2] = 400;
        yPoints[2] = 350;
        xPoints[3] = 500;
        yPoints[3] = 290;
        xPoints[4] = 500;
        yPoints[4] = 150;
        xPoints[5] = 400;
        yPoints[5] = 50;
        redIndex = 100;
      }
      else {
        printLine("Generating Fixed Concave Polygon.");
        xPoints[0] = 300;
        yPoints[0] = 150;
        xPoints[1] = 325;
        yPoints[1] = 350;
        xPoints[2] = 400;
        yPoints[2] = 340;
        xPoints[3] = 400;
        yPoints[3] = 250;
        xPoints[4] = 500;
        yPoints[4] = 150;
        xPoints[5] = 400;
        yPoints[5] = 50;
      }
    }
    else {
      int lengthOfSide = (int) Math.round(Math.random() * 75) + 100;
      double angleInDegrees = 360.0 / numPoints;
      initializePoints();
      if (isConvex) {
        generateRandomConvexPolygon(lengthOfSide, angleInDegrees);
        printLine("Generated Random Convex Polygon.");
      }
      else {
        generateRandomConcavePolygon(lengthOfSide, angleInDegrees);
        printLine("Generated Random Concave Polygon.");
      }
    }
    int minX = 2000;
    int maxX = 0;
    int minY = 2000;
    int maxY = 0;
    for (int i = 0; i < xPoints.length; i++) {
      if (xPoints[i] < minX) {
        minX = xPoints[i];
      }
      if (xPoints[i] > maxX) {
        maxX = xPoints[i];
      }
      if (yPoints[i] < minY) {
        minY = yPoints[i];
      }
      if (yPoints[i] > maxY) {
        maxY = yPoints[i];
      }
    }
    translatePoints((540 - ((minX + maxX) / 2)), (220 - ((minY + maxY) / 2)));
  }

  protected void initializePoints() {
    for (int i = 0; i < numPoints; i++) {
      xPoints[i] = 0;
      yPoints[i] = 0;
    }
  }

  protected void rotatePointsBy(double angleInDegrees) {
    double angleInRadians = (angleInDegrees * Math.PI) / 180;
    double angleOfRotation = -((2 * Math.PI) - angleInRadians);
    for (int i = 0; i < numPoints; i++) {
      int tempX = (int) ((xPoints[i] * Math.cos(angleOfRotation)) + (yPoints[i] * (-Math.sin(angleOfRotation))));
      int tempY = (int) ((xPoints[i] * Math.sin(angleOfRotation)) + (yPoints[i] * Math.cos(angleOfRotation)));
      xPoints[i] = tempX;
      yPoints[i] = tempY;
    }
  }

  protected void translatePoints(int x, int y) {
    for (int i = 0; i < numPoints; i++) {
      xPoints[i] = xPoints[i] + x;
      yPoints[i] = yPoints[i] + y;
    }
  }
}