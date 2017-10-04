package eecs.robot.simulation;

import eecs.RobotGuiBase;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.StandardSensor;
import eecs.robot.jago.element.StandardSensorFactory;
import eecs.robot.jago.element.TimingNavigatorRobot;

public class FinalProject041 extends RobotGuiBase {
  public static boolean areIntersectingPointsClose = false;
  static {
    sim = new TwoPolygonSim(true, false, false);
    setSimulationTitle("IT105/155 Final Project");
    frame = sim;
    sim.setVisible(true);
    robot = null;
    runRealisticSim();
  }

  public static void createRobot(int x, int y, int angle) {
    createRobot();
  }

  private static void createRobot() {
    int angle = 0;
    int robotStartY = 0;
    if (robot != null) {
      showError("Robot already exists.  Cannot create a new one.");
      System.exit(0);
    }
    robot = new TimingNavigatorRobot();
    StandardSensor lightSensor = StandardSensorFactory.createSensor(RobotSensors.LIGHT, robot);
    StandardSensor hitSensor = StandardSensorFactory.createSensor(RobotSensors.HIT, robot);
    StandardSensor bumperSensor = StandardSensorFactory.createSensor(RobotSensors.INVERTED_FRONT_BUMPER, robot);
    robot.addSensor(Monitor.RCX_2, bumperSensor);
    robot.addSensor(Monitor.RCX_3, hitSensor);
    robot.addSensor(Monitor.RCX_1, lightSensor);
    robot.setPower(10);
    robot.setRealismLevel(realism);
    angle = 0;
    robotStartY = 570;
    robot.setInitialHeading(angle);
    sim.add(robot, ((TwoPolygonSim) sim).getMaxX() - 50, robotStartY);
    ((TimingNavigatorRobot) robot).resetLocation();
    ((TimingNavigatorRobot) robot).setMovementScale(40.0);
    robot.drawTrails(true);
    pause(500);
  }

  public static void setPower(int power) {
    checkRobot("setPower");
    if ((power < 0) || (power > 100)) {
      showError("Tried to setPower with power level " + power + ".\n" + "Must be between 1 and 100.");
      System.exit(0);
    }
    robot.setPower(power);
  }

  public static void setRobotAppearance(String filename) {
    checkFile(filename);
    robot.setAppearance(filename);
  }

  public static double getHeading() {
    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;
    double angle;
    x1 = robot.getLocation().getX();
    y1 = robot.getLocation().getY();
    goForward(75);
    x2 = robot.getLocation().getX();
    y2 = robot.getLocation().getY();
    x3 = x1 - x2;
    y3 = y1 - y2;
    angle = Math.atan2(y3, x3);
    if (Math.toDegrees(angle) <= 90) {
      angle = Math.toDegrees(angle) + 270;
    }
    else {
      angle = Math.abs(90 - Math.toDegrees(angle));
    }
    return angle;
  }

  public static int[] getPolygon1XCoordinates() {
    return ((TwoPolygonSim) sim).getXPoints();
  }

  public static int[] getPolygon1YCoordinates() {
    return ((TwoPolygonSim) sim).getYPoints();
  }

  public static int[] getPolygon2XCoordinates() {
    return ((TwoPolygonSim) sim).getXPoints2();
  }

  public static int[] getPolygon2YCoordinates() {
    return ((TwoPolygonSim) sim).getYPoints2();
  }

  public static int getMyXPosition() {
    return (int) robot.getLocation().getX();
  }

  public static int getMyYPosition() {
    return (int) robot.getLocation().getY();
  }

  public static void gotoPoint(double x, double y) {
    double x1 = 0;
    double y1 = 0;
    double inchDistance = 10;
    double angle = 0;
    while (inchDistance > 5) {
      x1 = x - robot.getLocation().getX();
      y1 = y - robot.getLocation().getY();
      angle = Math.atan2(y1, x1);
      if (y1 != 0) {
        inchDistance = y1 / Math.sin(angle);
      }
      else {
        inchDistance = x1 / Math.cos(angle);
      }
      angle = Math.toDegrees(angle) + 90;
      ((TimingNavigatorRobot) robot).gotoAngle((float) angle);
      goForward(75);
    }
  }

  public static void loadFixedIntersectingPolygons() {
    ((TwoPolygonSim) sim).createPolygon(false, true, true);
    printLine("Generating Fixed Intersecting Polygons");
    createRobot();
  }

  public static void loadFixedNonIntersectingPolygons() {
    ((TwoPolygonSim) sim).createPolygon(false, true, false);
    printLine("Generating Fixed Non-Intersecting Polygons");
    createRobot();
  }

  public static void loadRandomPolygons() {
    ((TwoPolygonSim) sim).createPolygon(true, true, false);
    printLine("Generating Random Polygons");
    if (((TwoPolygonSim) sim).getTooClose()) {
      showMessage("The random case may be challenging due to the closeness of points. See if your robot "
          + "is able to solve this problem and let your instructor know if you are successful. Good Luck");
    }
    createRobot();
  }

  public static void transmitPoints(int x1, int y1, int x2, int y2) {
    printLine("Check solution");
    int currentX = (int) robot.getLocation().getX();
    int currentY = (int) robot.getLocation().getY();
    printLine("current Location is x " + currentX + " y " + currentY);
    boolean isCorrect = ((TwoPolygonSim) sim).checkSolution(x1, y1, x2, y2, currentX, currentY);
    if (isCorrect) {
      showMessage("GREAT JOB! YOU ARE CORRECT");
    }
    else {
      showMessage("Oops, Not so fast my friend, My records show an incorrect solution");
    }
  }
}