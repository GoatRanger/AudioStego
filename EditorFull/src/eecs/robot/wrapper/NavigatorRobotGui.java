package eecs.robot.wrapper;

import java.awt.geom.Point2D;
import eecs.RobotGuiBase;
import eecs.robot.jago.element.ColoredLine;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.TimingNavigatorRobot;
import eecs.robot.simulation.PolygonSim;

public class NavigatorRobotGui extends RobotGuiBase {
  public static final int CONVEX = 1;
  public static final int CONCAVE = -1;
  static double pixelsPerInch = 7.08;
  static int originX = 330;
  static int originY = 440;
  static {
    sim = new eecs.robot.simulation.PolygonSim();
    setSimulationTitle("IT105 Final Project");
    frame = sim;
    sim.setVisible(true);
    sim.add(new ColoredLine(5, 5, BLACK), originX, originY);
    robot = null;
  }

  public static void setConcavePolygon() {
    ((PolygonSim) sim).createPolygon(false, false);
  }

  public static void setConvexPolygon() {
    ((PolygonSim) sim).createPolygon(false, true);
  }

  public static int getMyX() {
    if (robot != null) {
      return (int) (Math.round(robot.getDistanceMoved().getX() * 10.0) / 10.0);
    }
    return 0;
  }

  public static int getMyY() {
    if (robot != null) {
      return (int) (Math.round(robot.getDistanceMoved().getY() * 10.0) / 10.0);
    }
    return 0;
  }

  public static void setRandomPolygon() {
    ((PolygonSim) sim).createPolygon(true, true);
  }

  public static int[] getXArray() {
    int[] points = ((PolygonSim) sim).getXPoints();
    for (int i = 0; i < points.length; i++) {
      points[i] = (int) ((points[i] - originX) / pixelsPerInch);
    }
    return points;
  }

  public static int getXPoint(int index) {
    int[] points = getXArray();
    if (index > points.length) {
      String indexExt = "th";
      switch (index) {
        case 1:
          indexExt = "st";
          break;
        case 2:
          indexExt = "nd";
          break;
        case 3:
          indexExt = "rd";
          break;
        default:
          indexExt = "th";
          break;
      }
      showError("Tried to get the x coordinate for the " + index + indexExt + "vertex, but the polygon does not have "
          + "that many vertices (remember the vertices are numbered " + "starting at 0).");
      System.exit(0);
    }
    return points[index];
  }

  public static int[] getYArray() {
    int[] points = ((PolygonSim) sim).getYPoints();
    for (int i = 0; i < points.length; i++) {
      points[i] = (int) ((originY - points[i]) / pixelsPerInch);
    }
    return points;
  }

  public static int getYPoint(int index) {
    int[] points = getYArray();
    if (index > points.length) {
      String indexExt = "th";
      switch (index) {
        case 1:
          indexExt = "st";
          break;
        case 2:
          indexExt = "nd";
          break;
        case 3:
          indexExt = "rd";
          break;
        default:
          indexExt = "th";
          break;
      }
      showError("Tried to get the y coordinate for the " + index + indexExt + "vertex, but the polygon does not have "
          + "that many vertices (remember the vertices are numbered " + "starting at 0).");
      System.exit(0);
    }
    return points[index];
  }

  public static void calibrate() {
    ((TimingNavigatorRobot) robot).calibrateRotation();
    goForward();
    while (getReflectanceSensor() <= 36) {
      // Make sure it's not still on the rotation calibration line
    }
    halt();
    pause(3000);
    ((TimingNavigatorRobot) robot).calibrateDistance(24);
    pause(1000);
    java.awt.geom.Point2D loc = robot.getLocation();
    double error = (originX - loc.getX()) + (originY - loc.getY());
    while (error > 4) {
      pivotTowardOrigin();
      goForward(100);
      loc = robot.getLocation();
      error = Math.abs(originX - loc.getX()) + Math.abs(loc.getY() - originY);
    }
    while (robot.getHeading() > 45) {
      pivotLeft(1);
    }
    while (robot.getHeading() < 45) {
      pivotRight(1);
    }
    ((TimingNavigatorRobot) robot).resetLocation();
  }

  public static void createRobot(int x, int y, int angle) {
    checkAngle("createRobot", angle);
    runRealisticSim();
    if (robot != null) {
      showError("Robot already exists.  Cannot create a new one.");
      System.exit(0);
    }
    robot = new TimingNavigatorRobot();
    robot.addSensor(Monitor.RCX_3, RobotSensors.HIT);
    robot.addSensor(Monitor.RCX_2, RobotSensors.TOUCH);
    robot.setPower(10);
    robot.setInitialHeading(angle);
    if (realism != IDEALISTIC) {
      robot.setRealismLevel(realism);
    }
    sim.add(robot, x, y);
  }

  public static void gotoPoint(double x, double y) {
    ((TimingNavigatorRobot) robot).gotoPoint((float) x, (float) y);
  }

  public static void transmitResult(int type, int x, int y) {
    printLine((type == CONVEX) ? "Convex" : "Concave");
    printLine("Rcv " + x);
    printLine("Rcv " + y);
    ((PolygonSim) sim).dataComplete();
    int index = ((PolygonSim) sim).getConcaveIndex();
    int xdiff = 0;
    int ydiff = 0;
    int tempIndex = index;
    if (type == CONCAVE) {
      if (index > 10) {
        tempIndex = 0;
      }
      xdiff = Math.abs(x - getXPoint(tempIndex));
      ydiff = Math.abs(y - getYPoint(tempIndex));
    }
    if (((xdiff < 6) && (ydiff < 6) && (type == CONCAVE)) || ((index > 10) && (type == CONVEX))) {
      printLine("Vertex identification correct");
    }
    else {
      if (index > 6) {
        printLine("Polygon is Convex, you transmitted concave");
      }
      else {
        printLine("Appears to be incorrect (outside tolerance):");
        printLine("Your point: (" + x + ", " + y + ")");
        printLine(" Actual values: (" + getXPoint(index) + ", " + getYPoint(index) + ")");
      }
    }
  }

  public static void travelBackward(int inches) {
    ((TimingNavigatorRobot) robot).travelBackward(inches);
  }

  public static void travelForward(int inches) {
    ((TimingNavigatorRobot) robot).travelForward(inches);
  }

  protected static void pivotTowardOrigin() {
    Point2D loc = robot.getLocation();
    double x1 = originX - loc.getX();
    double y1 = originY - loc.getY();
    double angle = Math.atan2(y1, x1);
    int heading = (int) robot.getHeading();
    angle = ((Math.toDegrees(angle) + 90) - heading) % 360;
    if (angle > 0) {
      pivotRight((int) angle);
    }
    else {
      pivotLeft(-(int) angle);
    }
  }
}