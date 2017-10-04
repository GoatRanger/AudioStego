package eecs.robot.simulation;

import eecs.robot.jago.SimBox;
import eecs.robot.jago.element.ColoredCircle;
import eecs.robot.jago.element.PolarPoint;
import eecs.robot.jago.element.ReflectiveColors;
import eecs.robot.jago.element.StandardRobot;
import eecs.robot.jago.element.WidePolygon;

public class TwoPolygonSim extends SimBox {
  protected int[] xPoints;
  protected int[] xPoints2;
  protected int[] xPoints3;
  protected int[] yPoints;
  protected int[] yPoints2;
  protected int[] yPoints3;
  protected boolean hasPolygon = false;
  protected boolean isConvex = true;
  protected boolean isRandom = false;
  protected boolean isIntersecting = false;
  protected int[] intersectionXArray;
  protected int[] intersectionYArray;
  protected int intersectionArrayCounter = 0;
  protected boolean isNotValid = false;
  protected int lineWidth = 11;
  protected int numPoints = 0;
  protected int numPoints2 = 0;
  protected int redIndex = 3;
  protected final int SERVER_XSTART = 110;
  protected final int X_MIN = 300;
  protected final int X_MAX = 900;
  protected final int Y_MIN = 25;
  protected final int Y_MAX = 600;
  protected final int POLYGON1_START_X = 488;
  protected final int POLYGON1_START_Y = 200;
  protected final int POLYGON2_START_X = 600;
  protected final int POLYGON2_START_Y = 225;
  protected int shapeWidth = 30;
  protected boolean tooClose = false;
  StandardRobot server;
  WidePolygon polygon = null;
  WidePolygon polygon2 = null;
  WidePolygon polygon3 = null;
  ColoredCircle[] circle = { new ColoredCircle(20, ReflectiveColors.RED), new ColoredCircle(20, ReflectiveColors.RED),
      new ColoredCircle(20, ReflectiveColors.RED) };
  int serverYLocation = 0;

  public TwoPolygonSim(boolean isRandom, boolean isConvex, boolean isIntersecting) {
    this();
    this.isRandom = isRandom;
    this.isConvex = isConvex;
    this.isIntersecting = isIntersecting;
  }

  public TwoPolygonSim() {
    super();
    setBackgroundImage("/eecs/images/background3.gif");
    intersectionXArray = new int[2];
    intersectionYArray = new int[2];
    intersectionXArray[0] = 0;
    intersectionYArray[0] = 0;
  }

  public void addSquare(int xCoord, int yCoord, int length) {
    int[] squareX;
    int[] squareY;
    squareX = new int[4];
    squareY = new int[4];
    squareX[0] = xCoord - length;
    squareY[0] = yCoord - length;
    squareX[1] = xCoord - length;
    squareY[1] = yCoord + length;
    squareX[2] = xCoord + length;
    squareY[2] = yCoord + length;
    squareX[3] = xCoord + length;
    squareY[3] = yCoord - length;
  }

  public int[] getXPoints() {
    int[] rtn = new int[xPoints.length];
    System.arraycopy(xPoints, 0, rtn, 0, xPoints.length);
    return rtn;
  }

  public int[] getXPoints2() {
    int[] rtn = new int[xPoints2.length];
    System.arraycopy(xPoints2, 0, rtn, 0, xPoints2.length);
    return rtn;
  }

  public int[] getYPoints() {
    int[] rtn = new int[yPoints.length];
    System.arraycopy(yPoints, 0, rtn, 0, yPoints.length);
    return rtn;
  }

  public int[] getYPoints2() {
    int[] rtn = new int[yPoints2.length];
    System.arraycopy(yPoints2, 0, rtn, 0, yPoints2.length);
    return rtn;
  }

  public void createPolygon(boolean isRandom, boolean isConvex, boolean isIntersecting) {
    if (hasPolygon) {
      remove(polygon);
      remove(polygon2);
      remove(polygon3);
      remove(circle[0]);
      remove(circle[1]);
      intersectionXArray[0] = 0;
      intersectionXArray[1] = 0;
      intersectionYArray[0] = 0;
      intersectionYArray[1] = 0;
      try {
        Thread.sleep(600);
      }
      catch (InterruptedException e) {
        //
      }
    }
    this.isRandom = isRandom;
    this.isConvex = isConvex;
    this.isIntersecting = isIntersecting;
    do {
      isNotValid = false;
      intersectionArrayCounter = 0;
      tooClose = false;
      generateRandomPoints();
      setUpIntersectionCheck();
      checkPolygonLocation();
    } while (isNotValid);
    polygon = new WidePolygon(xPoints, yPoints, numPoints, lineWidth, ReflectiveColors.WHITE, false, false, shapeWidth,
        ReflectiveColors.BLACK, redIndex);
    add(polygon, 0, 0);
    polygon2 = new WidePolygon(xPoints2, yPoints2, numPoints2, lineWidth, ReflectiveColors.WHITE, false, false, shapeWidth,
        ReflectiveColors.BLACK, redIndex);
    add(polygon2, 0, 0);
    polygon3 = new WidePolygon(xPoints3, yPoints3, 4, lineWidth, ReflectiveColors.GREEN, false, false, shapeWidth, ReflectiveColors.BLACK,
        redIndex);
    add(polygon3, 0, 0);
    hasPolygon = true;
    markIntersectingPoints();
  }

  private boolean arePointsClose(int xbase, int ybase, int xLoc, int yLoc, int width) {
    return ((xLoc >= (xbase - width)) && (xLoc <= (xbase + width)) && (yLoc >= (ybase - width)) && (yLoc <= (ybase + width)));
  }

  public void checkPolygonLocation() {
    if (!isIntersecting) {
      int maxX = 0;
      int minX2 = 900;
      for (int i = 0; i < xPoints.length; i++) {
        if (xPoints[i] > maxX) {
          maxX = xPoints[i];
        }
        if (xPoints2[i] < minX2) {
          minX2 = xPoints2[i];
        }
      }
      if (maxX >= minX2 - 25)
        isNotValid = true;
    }
  }

  public boolean checkSolution(int x1, int y1, int x2, int y2, int xLoc, int yLoc) {
    boolean correct = false;
    if (arePointsClose(SERVER_XSTART, serverYLocation, xLoc, yLoc, 70)) {
      if (intersectionXArray[0] == 0) {
        if ((x1 == 0) && (x2 == 0) && (y1 == 0) && (y2 == 0)) {
          printLine("User correctly identified no intersection");
          server.goBackward(3000);
          correct = true;
        }
        else {
          printLine("User's solution is incorrect");
          server.goForward(3000);
          correct = false;
        }
      }
      else
      {
        if (((arePointsClose(intersectionXArray[0], intersectionYArray[0], x1, y1, 50)) && (arePointsClose(intersectionXArray[1],
            intersectionYArray[1], x2, y2, 50)))
            || ((arePointsClose(intersectionXArray[0], intersectionYArray[0], x2, y2, 50)) && (arePointsClose(intersectionXArray[1],
                intersectionYArray[1], x1, y1, 50)))) {
          correct = true;
          server.goBackward(3000);
        }
        else {
          server.pivotRight(90);
          printLine("Not accurate enough.");
        }
      }
    }
    else {
      printLine("User not within transmit distance");
      server.pivotLeft(90);
      correct = false;
    }
    return correct;
  }

  public void setUpIntersectionCheck() {
    int[] x1 = new int[xPoints.length];
    int[] x2 = new int[xPoints2.length];
    int[] y1 = new int[yPoints.length];
    int[] y2 = new int[yPoints2.length];
    x1 = getXPoints();
    x2 = getXPoints2();
    y1 = getYPoints();
    y2 = getYPoints2();
    for (int i = 0, size1 = x1.length - 1; i < size1; ++i) {
      // check against the second polygon's segments
      for (int j = 0, size2 = x2.length - 1; j < size2; ++j) {
        // check to see if lines are parallel, if not , check for intersection
        // I would assume that the two polygons cannot share the same edges
        //  if(((y2[j+1]-y2[j]) * (x1[i+1]-x1[i]) - (x2[j+1]-x2[j]) * (y1[i+1]-y1[i])) != 0)
        //check to see if intersect, pass end points of both segments
        intersectionCheck(x1[i], y1[i], x1[i + 1], y1[i + 1], x2[j], y2[j], x2[j + 1], y2[j + 1]);
      }
      intersectionCheck(x1[i], y1[i], x1[i + 1], y1[i + 1], x2[0], y2[0], x2[x2.length - 1], y2[x2.length - 1]);
    }
    for (int j = 0, size2 = x2.length - 1; j < size2; ++j) {
      intersectionCheck(x1[0], y1[0], x1[x1.length - 1], y1[y1.length - 1], x2[j], y2[j], x2[j + 1], y2[j + 1]);
    }
    intersectionCheck(x1[0], y1[0], x1[x1.length - 1], y1[y1.length - 1], x2[0], y2[0], x2[x2.length - 1], y2[x2.length - 1]);
    if (intersectionXArray[0] == 0) {
      //      System.err.println("NO INTERSECTION");
      if (xPoints[0] > X_MIN) {
        translatePoints(X_MIN - xPoints[0] + 25, 0);
        isIntersecting = false;
        //System.err.println("Moving"+(X_MIN-xPoints[0]+5));
        int maxX = getMaxX();
        translatePoints2(875 - maxX, 0);
      }
    }
    else {
      //      System.err.println("INTERSECTION");
      isIntersecting = true;
      if ((arePointsClose(intersectionXArray[0], intersectionYArray[0], intersectionXArray[1], intersectionYArray[1], 50))) {
        printLine("The two intersecting points are very close and may cause problems.  Proceed with caution. This specific case is considered a challenge");
        tooClose = true;
      }
    }
  }

  public double[] getEquation(double x1, double y1, double x2, double y2) {
    double[] result = { 0, 0 };
    if (x1 != x2) // if not vertical line
    {
      if (y1 != y2) // if not horizontal line
      {
        result[0] = ((y2 - y1) / (x2 - x1)); //slope of line
      }
      else {
        result[0] = 0; // if horizontal line set slope to 0
      }
    }
    else {
      result[0] = 1000000000; // if vertical line set slope to 1000000000 (no slope)
      //	 printLine("VERTICAL LINE!!!!!!!!!!!!!!");
    }
    //do I need tofix this for the case result[0] = 1000000000?
    result[1] = y1 - (result[0] * x1); // set intercept point
    //printLine("Equation: slope = "+result[0]+ " intercept = "+result[1]);
    return result;
  }

  public void intersectionCheck(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
    double[] intersectPoint = { 0, 0 };
    double[] equation1;
    double[] equation2;
    double x = 0;
    double y = 0;
    // get the slope and intercept of the two segments
    equation1 = getEquation(x1, y1, x2, y2);
    equation2 = getEquation(x3, y3, x4, y4);
    //printLine("Slope1 = "+equation1[0]+" Intercept1 = "+ equation1[1] + " Slope2 = "+equation2[0]+" Intercept2 =
    // "+equation2[1]);
    if (equation1 != equation2) // if not the same lines
    {
      if (equation1[0] == 1000000000) // if line segment 1 is vertical
      {
        x = x1;
        y = ((equation2[0] * x) + equation2[1]);
      }
      else
        if (equation2[0] == 1000000000) // if line segment 2 is vertical
        {
          x = x3;
          y = ((equation1[0] * x) + equation1[1]);
        }
        else // if neither line is vertical
        {
          x = ((equation2[1] - equation1[1]) / (equation1[0] - equation2[0])); // x coordinate
          y = ((equation1[0] * x) + equation1[1]); // y coordinate
        }
      // test to see if the intersecting point falls on a segment
      if (((x >= Math.min(x1, x2)) && (x <= Math.max(x1, x2)) && (x >= Math.min(x3, x4)) && (x <= Math.max(x3, x4)))
          && ((y >= Math.min(y1, y2)) && (y <= Math.max(y1, y2)) && (y >= Math.min(y3, y4)) && (y <= Math.max(y3, y4)))) {
        intersectPoint[0] = x; //x coordinate
        intersectPoint[1] = y; //y coordinate
        try {
          intersectionXArray[intersectionArrayCounter] = (int) x;
          intersectionYArray[intersectionArrayCounter] = (int) y;
          intersectionArrayCounter++;
        }
        catch (ArrayIndexOutOfBoundsException ae) {
          isNotValid = true;
        }
      }
    }
    //printLine("-----------------");
    //System.err.println("Intersecting point: x="+intersectPoint[0]+" y="+intersectPoint[1]);
    //printLine("-----------------");
  }

  public int getMaxX() {
    int maxX = 0;
    for (int i = 0; i < xPoints.length; i++) {
      if (xPoints[i] > maxX) {
        maxX = xPoints[i];
      }
      if (xPoints2[i] > maxX) {
        maxX = xPoints2[i];
      }
    }
    return maxX;
  }

  /**
   * Validate Polygons to ensure they are not side by side
   */
  public void validatePolygons() {
    //    System.err.println("validating polygon positions");
    for (int i = 0; i < xPoints.length; i++) {
      // System.err.println(xPoints2.length);
      for (int j = 0; j < xPoints2.length; j++) {
        //	System.err.println("prior to check "+(xPoints[i]-xPoints2[j]));
        if (Math.abs(xPoints[i] - xPoints2[j]) < 20) {
          if (Math.abs(yPoints[i] - yPoints2[j]) < 20) {
            //printLine("problems*******"+(xPoints[i]-xPoints2[j]));
          }
        }
      }
    }
  }

  public void dataComplete() {
    server.goBackward(1000);
  }

  protected void generateRandomConcavePolygon(int lengthOfSide, double angleInDegrees) {
    //
  }

  protected void generateRandomConvexPolygon(int lengthOfSide, double angleInDegrees) {
    xPoints[0] = POLYGON1_START_X;
    yPoints[0] = POLYGON1_START_Y;
    PolarPoint polar;
    for (int i = 1; i < numPoints; i++) {
      polar = new PolarPoint(lengthOfSide, angleInDegrees * i);
      xPoints[i] = (int) polar.getX() + xPoints[i - 1];
      yPoints[i] = (int) polar.getY() + yPoints[i - 1];
    }
  }

  protected void generateRandomConvexPolygon2(int lengthOfSide, double angleInDegrees) {
    xPoints2[0] = POLYGON2_START_X;
    yPoints2[0] = POLYGON2_START_Y;
    PolarPoint polar;
    for (int i = 1; i < numPoints2; i++) {
      polar = new PolarPoint(lengthOfSide, angleInDegrees * i);
      xPoints2[i] = (int) polar.getX() + xPoints2[i - 1];
      yPoints2[i] = (int) polar.getY() + yPoints2[i - 1];
    }
  }

  protected void generateRandomPoints() {
    if (isRandom) {
      numPoints = 4 + ((int) (Math.round(Math.random() * 3)));
      numPoints2 = numPoints;
      isConvex = true;
    }
    else {
      numPoints = 6;
      numPoints2 = 6;
    }
    xPoints = new int[numPoints];
    yPoints = new int[numPoints];
    xPoints2 = new int[numPoints2];
    yPoints2 = new int[numPoints2];
    xPoints3 = new int[4];
    yPoints3 = new int[4];
    xPoints3[0] = X_MIN;
    xPoints3[1] = X_MIN;
    xPoints3[2] = X_MAX;
    xPoints3[3] = X_MAX;
    yPoints3[0] = Y_MIN;
    yPoints3[1] = Y_MAX;
    yPoints3[2] = Y_MAX;
    yPoints3[3] = Y_MIN;
    if (!isRandom) { //Fixed intersecting Polygon 1
      int lengthOfSide = 123;
      double angleInDegrees = 60.0;
      xPoints[0] = POLYGON1_START_X;
      yPoints[0] = POLYGON1_START_Y;
      //lengthOfSide=100;
      PolarPoint polar; //  angleInDegrees=30;
      for (int i = 1; i < numPoints; i++) {
        polar = new PolarPoint(lengthOfSide, angleInDegrees * i);
        xPoints[i] = (int) polar.getX() + xPoints[i - 1];
        yPoints[i] = (int) polar.getY() + yPoints[i - 1];
      }
      //Polygon 2
      lengthOfSide = 105;
      xPoints2[0] = POLYGON2_START_X;
      yPoints2[0] = POLYGON2_START_Y;
      for (int i = 1; i < numPoints; i++) {
        polar = new PolarPoint(lengthOfSide, angleInDegrees * i);
        xPoints2[i] = (int) polar.getX() + xPoints2[i - 1];
        yPoints2[i] = (int) polar.getY() + yPoints2[i - 1];
      }
      if (!isIntersecting) {
        rotatePointsBy(30);
        translatePoints(-150, 0);
        translatePoints2(900 - getMaxX(), 0);
      }
    }
    else {
      int lengthOfSide = (int) Math.round(Math.random() * 75) + 75;
      int lengthOfSide2 = (int) Math.round(Math.random() * 75) + 75;
      double angleInDegrees = 360.0 / numPoints;
      double angleInDegrees2 = 360.0 / (numPoints);
      printLine("Length:" + lengthOfSide);
      printLine("Length 2:" + lengthOfSide2);
      printLine("Angle:" + angleInDegrees + " degrees.");
      printLine("Angle 2:" + angleInDegrees2 + " degrees.");
      initializePoints();
      generateRandomConvexPolygon(lengthOfSide, angleInDegrees);
      generateRandomConvexPolygon2(lengthOfSide2, angleInDegrees2);
      printLine("Generated Random Convex Polygons 1 and 2.");
      if (Math.random() < .30) {
        rotatePointsBy(30);
        isIntersecting = false;
        translatePoints(-170, 0);
        translatePoints2(900 - getMaxX() - 25, 0);
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
    checkIfOverBounds();
  }

  protected void checkIfOverBounds() {
    for (int i = 0; i < xPoints2.length; i++) {
      if (xPoints2[i] >= X_MAX) {
        translatePoints2(X_MAX - xPoints2[i] - 25, 0);
      }
    }
  }

  protected void initializePoints() {
    for (int i = 0; i < numPoints; i++) {
      xPoints[i] = 0;
      yPoints[i] = 0;
      xPoints2[i] = 0;
      yPoints2[i] = 0;
    }
  }

  private void markIntersectingPoints() {
    if (intersectionXArray[0] != 0) {
      int x1 = intersectionXArray[0];
      int y1 = intersectionYArray[0];
      int x2 = intersectionXArray[1];
      int y2 = intersectionYArray[1];
      add(circle[0], x1, y1);
      add(circle[1], x2, y2);
    }
  }

  protected void rotatePointsBy(double angleInDegrees) {
    double angleInRadians = (angleInDegrees * Math.PI) / 180;
    double angleOfRotation = -((2 * Math.PI) - angleInRadians);
    for (int i = 0; i < numPoints; i++) {
      int tempX = (int) ((xPoints2[i] * Math.cos(angleOfRotation)) + (yPoints2[i] * (-Math.sin(angleOfRotation))));
      xPoints2[i] = tempX;
    }
  }

  protected void translatePoints(int x, int y) {
    for (int i = 0; i < numPoints; i++) {
      xPoints[i] = xPoints[i] + x;
      yPoints[i] = yPoints[i] + y;
    }
  }

  protected void translatePoints2(int x, int y) {
    for (int i = 0; i < numPoints; i++) {
      xPoints2[i] = xPoints2[i] + x;
      yPoints2[i] = yPoints2[i] + y;
    }
  }

  public boolean getTooClose() {
    return tooClose;
  }
}