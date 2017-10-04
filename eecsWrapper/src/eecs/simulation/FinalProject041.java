/*
 * Created on Oct 31, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.simulation;

import jago.element.LegoRobot;
import jago.element.RobotSensors;
import jago.element.StandardSensor;
import jago.element.StandardSensorFactory;
import jago.element.TimingNavigatorRobot;


/**
 * @author dm0266
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FinalProject041 extends eecs.RobotGuiBase
{
  public static boolean areIntersectingPointsClose = false;

  static
  {
    sim = new TwoPolygonSim(true, false, false);
    setSimulationTitle("IT105/155 Final Project");
    frame = sim;
    sim.setVisible(true);
    robot = null;
    runRealisticSim();

//    int choice = chooseButton("Which case do you want to run",
//        "Fixed Intersecting", "Fixed Non-Intersecting", "Random");
//
//    if (choice == BUTTON1)
//    {
//      loadFixedIntersectingPolygons();
//    }
//    else if (choice == BUTTON2)
//    {
//      loadFixedNonIntersectingPolygons();
//    }
//    else
//    {
//      loadRandomPolygons();
//    }
  }

  public static void createRobot(int x, int y, int angle)
  {
  	createRobot();
  }

  private static void createRobot()
  {
    int angle = 0;
    int robotStartY = 0;

    if (robot != null)
    {
      showError("Robot already exists.  Cannot create a new one.");
      System.exit(0);
    }

    robot = new TimingNavigatorRobot();

    StandardSensor lightSensor = StandardSensorFactory.createSensor(RobotSensors.LIGHT,
        robot);
    StandardSensor hitSensor = StandardSensorFactory.createSensor(RobotSensors.HIT,
        robot);
    StandardSensor bumperSensor = StandardSensorFactory.createSensor(RobotSensors.INVERTED_FRONT_BUMPER,
        robot);
    robot.addSensor(LegoRobot.RCX_2, bumperSensor);
    robot.addSensor(LegoRobot.RCX_3, hitSensor);
    robot.addSensor(LegoRobot.RCX_1, lightSensor);
    robot.setPower(10);

    robot.setRealismLevel(realism);
    angle = 0;
    robotStartY = 570;
    robot.setInitialHeading(angle);
    sim.add(robot, ((TwoPolygonSim) sim).getMaxX() - 50, robotStartY);

    //sim.add(robot, 50, 50);
    ((TimingNavigatorRobot) robot).resetLocation();
    ((TimingNavigatorRobot) robot).setMovementScale(40.0);
    robot.drawTrails(true);
    //	resetLocation((((TwoPolygonSim)sim).getMaxX()-50)*1.0, robotStartY*1.0);
    //sim.add(robot,x,y);
    // Pause to allow updates prior to attempting to use light sensor
    // Trying to use light sensor prior to it being ready may result in
    // an infinite loop
    pause(500);
  }

  /**
   * overrides the TimingNavigatorRobots's reset method?
   *
   */

  /*public static void resetLocation(double x, double y)
  {
    ((TimingNavigatorRobot)robot).distanceTraveled.setLocation(x, y);
  }
  */
  public static void setPower(int power)
  {
	  checkRobot("setPower");

	  if ((power < 0) || (power > 100))
	  {
		  showError("Tried to setPower with power level " + power +
			  ".\n" + "Must be between 1 and 100.");
		  System.exit(0);
	  }

	  robot.setPower(power);
  }
  
  public static void setRobotAppearance(String filename) {
  	checkFile(filename);
  	robot.setAppearance(filename);
  }
  // This method will move the robot forward slightly
  // to determine the robot's heading (0-360 degrees, with 
  // 0 pointing to the top of the screen)
  public static double getHeading()
  {
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

    //System.err.println("Going relative ("+x1 + ", " + y1 +")");
    // Calculate angle to go to:
    angle = Math.atan2(y3, x3);

    if (Math.toDegrees(angle) <= 90)
    {
      angle = Math.toDegrees(angle) + 270;
    }
    else
    {
      angle = Math.abs(90 - Math.toDegrees(angle));
    }

    return angle;
  }

  /**
   *
   * @return an integer array of the X coordinates of the vertices of the first polygon
   */
  public static int[] getPolygon1XCoordinates()
  {
    return ((TwoPolygonSim) sim).getXPoints();
  }

  /**
     *
     * @return an integer array of the Y coordinates of the vertices of the first polygon
     */
  public static int[] getPolygon1YCoordinates()
  {
    return ((TwoPolygonSim) sim).getYPoints();
  }

  /**
   *
   * @return an integer array of the X coordinates of the vertices of the Second polygon
   */
  public static int[] getPolygon2XCoordinates()
  {
    return ((TwoPolygonSim) sim).getXPoints2();
  }

  /**
   *
   * @return an integer array of the y coordinates of the vertices of the Second polygon
   */
  public static int[] getPolygon2YCoordinates()
  {
    return ((TwoPolygonSim) sim).getYPoints2();
  }

  /**
   *
   * @return an integer of the x-coordinate of the current location of the robot.
   */
  public static int getMyXPosition()
  {
    return (int) robot.getLocation().getX();
  }

  /**
     *
     * @return an integer of the y-coordinate of the current location of the robot.
     */
  public static int getMyYPosition()
  {
    return (int) robot.getLocation().getY();
  }

  /**
   * This method will move your robot to the x,y coordinate.
   *
   * @param x X coordinate
   * @param y Y coordinate
   */
  public static void gotoPoint(double x, double y)
  {
    // Determine relative points
    double x1 = 0;
    double y1 = 0;

    //double distance=Math.sqrt(x1*x1+y1*y1);
    double inchDistance = 10;
    double angle = 0;

    // Note: since vert axis is reverse of standard, negate y1
    // y1 = -y1;
    while (inchDistance > 5)
    {
      x1 = x - robot.getLocation().getX();
      y1 = y - robot.getLocation().getY();

      //System.err.println("Going relative ("+x1 + ", " + y1 +")");
      // Calculate angle to go to:
      angle = Math.atan2(y1, x1);

      // Calculate distance to travel:
      //System.err.println("  and angle of " + Math.toDegrees(angle));
      if (y1 != 0)
      {
        inchDistance = y1 / Math.sin(angle);
      }
      else
      {
        inchDistance = x1 / Math.cos(angle);
      }

      //printLine("inch dist "+inchDistance+"distance "+distance);
      //System.err.println("   and I am " + inchDistance + " away.");
      angle = Math.toDegrees(angle) + 90;

      //	printLine("Angle = "+angle+"; getHeading= "+getHeading());
      // Now convert theory into action:
      //angle=330;
      //System.err.println(angle);
      ((TimingNavigatorRobot) robot).gotoAngle((float) angle);
      goForward(75);

      //((TimingNavigatorRobot) robot).travelForward(Math.round(inchDistance));
    }
  }

  /**
     *
     * @author dm0266
     * @todo ensure they intersect
     * Loads the Fixed Intersecting Polygon Case
     *
     */
  public static void loadFixedIntersectingPolygons()
  {
    ((TwoPolygonSim) sim).createPolygon(false, true, true);
    printLine("Generating Fixed Intersecting Polygons");

    //((TwoPolygonSim)sim).displayPoints();
    //((TwoPolygonSim) sim).setUpIntersectionCheck();
    createRobot();
  }

  /**
   *
   * @author dm0266
   *
   * Loads the Fixed Non-Intersecting Polygon Case
   *
   */
  public static void loadFixedNonIntersectingPolygons()
  {
    ((TwoPolygonSim) sim).createPolygon(false, true, false);
    printLine("Generating Fixed Non-Intersecting Polygons");

    //((TwoPolygonSim)sim).displayPoints();
    //((TwoPolygonSim) sim).setUpIntersectionCheck();
    createRobot();
  }

  /**
   *
   * @author dm0266
   *
   * Loads Random Polygon Case
   *
   */
  public static void loadRandomPolygons()
  {
    ((TwoPolygonSim) sim).createPolygon(true, true, false);
    printLine("Generating Random Polygons");

    //((TwoPolygonSim)sim).displayPoints();
    //((TwoPolygonSim) sim).setUpIntersectionCheck();

    if (((TwoPolygonSim) sim).getTooClose())
    {
      showMessage(
        "The random case may be challenging due to the closeness of points. See if your robot " +
        "is able to solve this problem and let your instructor know if you are successful. Good Luck");
    }

    createRobot();
  }

  // public static int getMyX()
  // public static int getMyY()
  // public static void transmitData(int x1, int y2,int x2, y2);)

  /**
   *
   * @author dm0266
   *
   * This method is used to transmit the intersecting coordinates from the robot
   * to the server.
   *  @param x1 the x-coordinate of the first intersecting point
   *  @param y1 the y-coordiante of the first intersecting point
   *   @param x2 the x-coordinate of the second intersecting point
   *  @param y2 the y-coordiante of the second itnersecting point
   */
  public static void transmitPoints(int x1, int y1, int x2, int y2)
  {
    //check distance from server to transmitting robot.
    printLine("Check solution");

    int currentX = (int) robot.getLocation().getX();
    int currentY = (int) robot.getLocation().getY();
    printLine("current Location is x " + currentX + " y " + currentY);

    boolean isCorrect = ((TwoPolygonSim) sim).checkSolution(x1, y1, x2, y2,
        currentX, currentY);

    if (isCorrect)
    {
      showMessage("GREAT JOB! YOU ARE CORRECT");
    }

    else
    {
      showMessage(
        "Oops, Not so fast my friend, My records show an incorrect solution");
    }
  }
}
