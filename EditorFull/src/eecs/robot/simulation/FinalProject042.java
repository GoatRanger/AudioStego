package eecs.robot.simulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import eecs.RobotGuiBase;
import eecs.robot.instructor.cobb.ObstacleCrossingBot;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.StandardSensor;
import eecs.robot.jago.element.StandardSensorFactory;

public class FinalProject042 extends RobotGuiBase {
  public static boolean areIntersectingPointsClose = false;
  static int divisionPublicKey, divisionPrivateKey, cadetsPublicKey, cadetsPrivateKey;
  static long startTime;
  static double totalTime;
  static boolean areCrossingPointsValid;
  static String assemblyArea;
  static boolean testing;
  static {
    getProperties();
    sim = new ObstacleSim();
    setSimulationTitle("IT105/155 Final Project");
    frame = sim;
    if (!runningTests) {
      sim.setVisible(true);
    }
    //sim.setDebugging(true);
    robot = null;
    runRealisticSim();
    createRobot();
    createKeys();
    startTime = System.currentTimeMillis();
    double randomAA = Math.random();
    if (randomAA < .33) {
      assemblyArea = "AA Eagle";
    }
    else
      if (randomAA < .66) {
        assemblyArea = "AA River";
      }
      else {
        assemblyArea = "AA Cobra";
      }
    if (runningTests) {
      try {
        assemblyArea = testConsoleReader.readLine();
        // This allows == to work (in most cases). Since == would work
        // when not testing, need it to work the same here.
        assemblyArea = assemblyArea.intern();
      }
      catch (IOException e) {
        System.err.println("Error reading a String from test file (console)." + "  Format in input file was incorrect.");
      }
    }
  }

  public static void createKeys() {
    divisionPublicKey = (int) Math.round(Math.random() * 1000);
    divisionPrivateKey = (int) Math.round(Math.random() * 1000);
    cadetsPublicKey = (int) Math.round(Math.random() * 1000);
    cadetsPrivateKey = (int) Math.round(Math.random() * 1000);
  }

  public static void createRobot(int x, int y, int angle) {
    createRobot();
  }

  private static void createRobot() {
    int angle = 0;
    int robotStartY = 500;
    int robotStartX = 140;
    if (robot != null) {
      showError("Robot already exists.  Cannot create a new one.");
      System.exit(0);
    }
    robot = new ObstacleCrossingBot();
    StandardSensor lightSensor = StandardSensorFactory.createSensor(RobotSensors.LIGHT, robot);
    StandardSensor hitSensor = StandardSensorFactory.createSensor(RobotSensors.HIT, robot);
    StandardSensor bumperSensor = StandardSensorFactory.createSensor(RobotSensors.INVERTED_FRONT_BUMPER, robot);
    robot.addSensor(Monitor.RCX_2, bumperSensor);
    robot.addSensor(Monitor.RCX_3, hitSensor);
    robot.addSensor(Monitor.RCX_1, lightSensor);
    robot.setPower(50);
    robot.setRealismLevel(realism);
    robot.setInitialHeading(angle);
    getProperties();
    int startPosition;
    if (runningTests) {
      startPosition = BUTTON1;
    }
    else {
      startPosition = chooseButton("Where do you want to start?", "beginning", "road", "Tower");
    }
    if (startPosition == BUTTON1) {
      robotStartX = 140;
      robotStartY = 550;
      testing = false;
    }
    else
      if (startPosition == BUTTON2) {
        robotStartX = 340;
        robotStartY = 100;
        testing = true;
      }
      else {
        robotStartX = 600;
        robotStartY = 400;
        testing = true;
        robot.setInitialHeading(180);
      }
    sim.add(robot, robotStartX, robotStartY);
    pause(500);
  }

  public static boolean getBridgingStatus() {
    return (((ObstacleCrossingBot) robot).getBridgeBreached());
  }

  public static void checkHighScores() {
    int maxScores = 10;
    String fileName = "\\\\usmasvddspider\\internal web\\courses\\it105instructors\\scores\\";
    String file = "highScores155.txt";
    File myf = new File(fileName + file);
    if (myf.exists()) {
      printLine("Exists");
    }
    else {
      try {
        openWriteFile(fileName + file);
        for (int i = 0; i < maxScores; i++) {
          writeLine("1000.0");
          writeLine("Joe Cadet");
        }
        closeWriteFile();
      }
      catch (Exception e) {
        //
      }
    }
    try {
      fileReader = new BufferedReader(new FileReader("highScores.txt"));
    }
    catch (Exception e) {
      showError("Could not open file " + myf);
      System.exit(0);
    }
    double[] scores = new double[6];
    //    try {
    //      // fileReader = new BufferedReader(new FileReader(fileName + file));
    //    	fileReader = new BufferedReader(new FileReader(file));
    //    } catch (Exception e) {
    //      showError("Could not open file " + myf);
    //      System.exit(0);
    //    }
    String highScores = "Your Time: " + totalTime + "\n";
    HighScore155[] hs = new HighScore155[maxScores + 1];
    //    double[] scores = new double[6];
    //    String[] scenario = new String[6];
    //    String[] person = new String[6];
    int counter = 0;
    openReadFile(fileName + file);
    // openReadFile(file);
    while (moreToRead()) {
      String line = readLine();
      scores[counter] = Double.parseDouble(line);
      //scores[counter] = Double.parseDouble(line);
      double score = Double.parseDouble(line);
      //scenario[counter] = readLine();
      //      person[counter] = readLine();
      String p = readLine();
      hs[counter] = new HighScore155(score, p);
      counter++;
    }
    for (int fill = counter; fill < maxScores + 1; fill++) {
      hs[fill] = new HighScore155(1000.0, "Jane Cadet");
    }
    hs[maxScores] = new HighScore155(totalTime, System.getProperty("user.name", "anonymous"));
    //    scores[5] = totalTime;
    closeReadFile();
    Arrays.sort(hs);
    if (hs[maxScores].getScore() == totalTime) {
      highScores += "\n" + "No High Score this time" + "\n";
    }
    else {
      highScores += "\n" + "Great Job, A new high Score" + "\n";
    }
    highScores += "  Best Times" + "\n";
    openWriteFile(fileName + file);
    //openWriteFile(file);
    for (int i = 0; i < maxScores; i++) {
      //writeLine(scores[i]);
      write(hs[i].toMultiLineString());
      highScores += (i + 1) + " " + hs[i].toString() + "\n";
    }
    closeWriteFile();
    showMessage(highScores);
    //sort Array
    //find out if current score is lower than any
  }

  public static boolean foundValidCrossingPoints() {
    if (((ObstacleCrossingBot) robot).hasCorrectlyCrossedConcertina() && ((ObstacleCrossingBot) robot).hasCorrectlyCrossedFire()) {
      return true;
    }
    return false;
  }

  public static boolean foundCorrectAssemblyArea() {
    int currentX = getMyXPosition();
    int currentY = getMyYPosition();
    if ((assemblyArea.equals("AA Eagle")) && ((ObstacleSim) sim).arePointsClose(600, 550, currentX, currentY, 25)) {
      return true;
    }
    else
      if ((assemblyArea.equals("AA River")) && ((ObstacleSim) sim).arePointsClose(700, 550, currentX, currentY, 25)) {
        return true;
      }
      else
        if ((assemblyArea.equals("AA Cobra")) && ((ObstacleSim) sim).arePointsClose(800, 550, currentX, currentY, 25)) {
          return true;
        }
        else
          return false;
  }

  public static void transmitMissionComplete() {
    getProperties();
    //System.out.println("Still need to finish this method");
    totalTime = (System.currentTimeMillis() - startTime) / 1000.0;
    System.out.println("TotalTime=" + totalTime + " seconds");
    System.err.println("Robot Location: " + getMyXPosition() + ", " + getMyYPosition());
    if (foundValidCrossingPoints() && foundCorrectAssemblyArea() && (!testing)) {
      if (runningTests) {
        System.err.println("Correct solution found");
      }
      else {
        checkHighScores();
      }
    }
    else
      if (testing) {
        showMessage("Testing Complete");
        if (runningTests) {
          System.err.println("Not running a complete simulation scenario.");
        }
      }
      else {
        if (runningTests) {
          System.err.println("Failed to find solution");
          System.err.println("Found bypasses: " + foundValidCrossingPoints() + ", Found AA: " + foundCorrectAssemblyArea());
        }
        showError("Failed to correctly identify crossing points or you are in the wrong AA");
      }
    // Running tests? This should be the last command
    if (runningTests)
      System.exit(0);
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

  public static int getHeading() {
    return (int) getHeadingDouble();
  }

  public static double getHeadingDouble() {
    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;
    double angle;
    if (robot == null)
      return Double.NaN;
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
    if (Math.toDegrees(angle) <= 90) {
      angle = Math.toDegrees(angle) + 270;
    }
    else {
      angle = Math.abs(90 - Math.toDegrees(angle));
    }
    return angle;
  }

  /**
   * @return 3 integer pairs. each pair represents the direction and distance from the Satellit potition at 500,10 in the simulation.The
   *         first number of each pair represents the distance while the second number is the direction from 500,10.
   */
  public int[] getBypassInfo() {
    int[] array = new int[3];
    /*
     * @TODO 042:getBypassInfo
     */
    return array;
  }

  /**
   * @return an integer of the x-coordinate of the current location of the robot.
   */
  public static int getMyXPosition() {
    return (int) robot.getLocation().getX();
  }

  /**
   * @return an integer of the y-coordinate of the current location of the robot.
   */
  public static int getMyYPosition() {
    return (int) robot.getLocation().getY();
  }

  /**
   * This method will move your robot to the x,y coordinate.
   * 
   * @param x
   *          X coordinate
   * @param y
   *          Y coordinate
   */
  public static void gotoPoint(double x, double y) {
    // Determine relative points
    double x1 = 0;
    double y1 = 0;
    //double distance=Math.sqrt(x1*x1+y1*y1);
    double inchDistance = 10;
    double angle = 0;
    // Note: since vert axis is reverse of standard, negate y1
    // y1 = -y1;
    while (inchDistance > 5) {
      x1 = x - robot.getLocation().getX();
      y1 = y - robot.getLocation().getY();
      //System.err.println("Going relative ("+x1 + ", " + y1 +")");
      // Calculate angle to go to:
      angle = Math.atan2(y1, x1);
      // Calculate distance to travel:
      //System.err.println(" and angle of " + Math.toDegrees(angle));
      if (y1 != 0) {
        inchDistance = y1 / Math.sin(angle);
      }
      else {
        inchDistance = x1 / Math.cos(angle);
      }
      //printLine("inch dist "+inchDistance+"distance "+distance);
      //System.err.println(" and I am " + inchDistance + " away.");
      angle = Math.toDegrees(angle) + 90;
      //	printLine("Angle = "+angle+"; getHeading= "+getHeading());
      // Now convert theory into action:
      //angle=330;
      //System.err.println(angle);
      ((ObstacleCrossingBot) robot).gotoAngle((float) angle);
      goForward(75);
    }
  }

  public static void pivotTo(float angle) {
    ((ObstacleCrossingBot) robot).gotoAngle(angle);
  }

  public static int getDivisionPublicKey() {
    return divisionPublicKey;
  }

  public static int getDivisionPrivateKey() {
    return divisionPrivateKey;
  }

  public static int getMyPublicKey() {
    return cadetsPublicKey;
  }

  public static int getMyPrivateKey() {
    return cadetsPrivateKey;
  }

  public static String decryptMessage(int key, String msg) {
    if (key != cadetsPrivateKey) {
      assemblyArea = "asl;dkfj-32";
    }
    System.err.println("Moving to " + assemblyArea);
    return assemblyArea;
  }

  public static String getAssemblyAreaInfo() {
    return "JKJFDSI*&)(*K:";
  }

  /*
   * @TODO Need to access the robots stored crossing locations to provide to user.
   */
  public static int getObstacle1XLocation() {
    return ((ObstacleCrossingBot) robot).getX1();
  }

  public static int getObstacle1YLocation() {
    return ((ObstacleCrossingBot) robot).getY1();
  }

  public static int getObstacle2XLocation() {
    return ((ObstacleCrossingBot) robot).getX2();
  }

  public static int getObstacle2YLocation() {
    return ((ObstacleCrossingBot) robot).getY2();
  }

  public static int[] getPolarCoordinates() {
    int[] pcArray = new int[4];
    pcArray = ((ObstacleSim) sim).getPolarObstacleArray();
    return pcArray;
  }

  /*
   * @TODO Finish GetTemperature. perhaps if you know the location of fire just test for it?
   */
  public static int getTemperature() {
    int currentX;
    int currentY;
    int currentFireY;
    int deltaY;
    int bypassXMin, bypassXMax;
    currentX = getMyXPosition();
    currentY = getMyYPosition();
    currentFireY = ((ObstacleSim) sim).getFireYLocation();
    deltaY = currentY - currentFireY;
    bypassXMin = ((ObstacleSim) sim).getFireXByPassLocation() - 20;
    bypassXMax = ((ObstacleSim) sim).getFireXByPassLocation() + 20;
    //System.out.println("Bypass min and max: "+bypassXMin+ " "+bypassXMax);
    if (currentX > bypassXMin && currentX < bypassXMax && deltaY <= 150) { // &&
      // currentX>bypassXMin
      // &&
      // currentX<bypassXMax){
      //System.out.println(currentX+" currentX");
      return 10;
    }
    return 100;
  }
}

class HighScore155 implements Comparable {
  private double score = 0;
  private String person = "";

  public HighScore155(double score, String person) {
    this.score = score;
    this.person = person;
  }

  public double getScore() {
    return score;
  }

  public String toMultiLineString() {
    return "" + score + "\r\n" + person + "\r\n";
  }

  public String toString() {
    return "" + score + " - " + person;
  }

  public int compareTo(Object arg0) {
    HighScore155 other = (HighScore155) arg0;
    return new Double(this.score).compareTo(new Double(other.score));
  }
}