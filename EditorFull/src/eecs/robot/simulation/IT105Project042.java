package eecs.robot.simulation;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import eecs.RobotGuiBase;
import eecs.robot.instructor.cobb.ObstacleCrossingBot;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.StandardSensor;
import eecs.robot.jago.element.StandardSensorFactory;

public class IT105Project042 extends RobotGuiBase {
  public static boolean areIntersectingPointsClose = false;
  static int divisionPublicKey, divisionPrivateKey, cadetsPublicKey, cadetsPrivateKey;
  static long startTime;
  static double totalTime;
  static boolean areCrossingPointsValid;
  static String assemblyArea;
  static boolean hasAssemblyArea = false;
  static boolean testing;
  static int robotStartX, robotStartY;
  static int angle = 0;
  static boolean scenarioLoaded;
  private static String option = "";
  static {
    getProperties();
    sim = new ObstacleSim105();
    setSimulationTitle("IT105/155 Final Project");
    frame = sim;
    if (!runningTests) {
      sim.setVisible(true);
    }

    robot = null;
    runRealisticSim();

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
  }

  public static void createRobot(int x, int y, int angle) {
    createRobot();
  }

  private static void createRobot() {
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
    sim.add(robot, robotStartX, robotStartY);
    // Pause to allow updates prior to attempting to use light sensor
    // Must ensure sensor is initialized and that all adds to the simulation
    // have
    // completed so that the graphics buffer is fully populated.
    pause(500);
  }

  public static boolean getBridgingStatus() {
    return (((ObstacleCrossingBot) robot).getBridgeBreached());
  }

  public static void checkForOldFormat(String file) {
    openReadFile(file);
    readLine();
    try {
      Double.parseDouble(readLine());
      // Wrong format, so read it and move on.
      closeReadFile();
      openReadFile(file);
      HighScore[] hs = new HighScore[25];
      for (int i = 0; i < hs.length; i++) {
        hs[i] = new HighScore(1000.0, "none", "Joe Cadet");
      }
      int counter = 0;
      while (moreToRead()) {
        double score = Double.parseDouble(readLine());
        hs[counter++] = new HighScore(score, "unknown", System.getProperty("user.name", "n/a"));
      }
      closeReadFile();
      openWriteFile(file);
      for (int i = 0; i < hs.length; i++) {
        write(hs[i].toMultiLineString());
      }
      closeWriteFile();
    }
    catch (NumberFormatException nfe) {
      // If error, then file is new file format (second entry is a String)
      closeReadFile();
    }
  }

  public static void checkHighScores() {
    int maxScores = 25;
    //  	openReadFile("highScores.txt");
    String fileName = "\\\\usmasvddspider\\internal web\\courses\\it105instructors\\scores\\";
    String file = "highScores.txt";
    File myf = new File(fileName + file);
    //File myf = new File(file);
    if (myf.exists()) {
      printLine("Exists");
    }
    else {
      try {
        //File dir = new File(fileName);
        //dir.mkdir();
        //printLine("File created");
        openWriteFile(fileName + file);
        //openWriteFile(file);
        for (int i = 0; i < maxScores; i++) {
          writeLine("1000.0");
          writeLine("none");
          writeLine("Joe Cadet");
        }
        closeWriteFile();
      }
      catch (Exception e) {
        //
      }
    }
    //    try {
    //      // fileReader = new BufferedReader(new FileReader(fileName + file));
    //      fileReader = new BufferedReader(new FileReader(file));
    //    } catch (Exception e) {
    //      showError("Could not open file " + myf);
    //      System.exit(0);
    //    }
    String highScores = "Your Time: " + totalTime + "\n";
    HighScore[] hs = new HighScore[maxScores + 1];
    //    double[] scores = new double[6];
    //    String[] scenario = new String[6];
    //    String[] person = new String[6];
    int counter = 0;
    openReadFile(fileName + file);
    // openReadFile(file);
    while (moreToRead()) {
      String line = readLine();
      //scores[counter] = Double.parseDouble(line);
      double score = Double.parseDouble(line);
      //scenario[counter] = readLine();
      String scen = readLine();
      //      person[counter] = readLine();
      String p = readLine();
      hs[counter] = new HighScore(score, scen, p);
      counter++;
    }
    for (int fill = counter; fill < maxScores + 1; fill++) {
      hs[fill] = new HighScore(1000.0, "none", "Jane Cadet");
    }
    hs[maxScores] = new HighScore(totalTime, option, System.getProperty("user.name", "anonymous"));
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

    for (int i = 0; i < maxScores; i++) {

      write(hs[i].toMultiLineString());
      highScores += (i + 1) + " " + hs[i].toString() + "\n";
    }
    closeWriteFile();
    showMessage(highScores);
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
    if ((assemblyArea.equals("AA Eagle")) && ((ObstacleSim105) sim).arePointsClose(600, 550, currentX, currentY, 25)) {
      return true;
    }
    else
      if ((assemblyArea.equals("AA River")) && ((ObstacleSim105) sim).arePointsClose(700, 550, currentX, currentY, 25)) {
        return true;
      }
      else
        if ((assemblyArea.equals("AA Cobra")) && ((ObstacleSim105) sim).arePointsClose(800, 550, currentX, currentY, 25)) {
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
    if (foundValidCrossingPoints() && foundCorrectAssemblyArea() && (!testing)) {
      if (runningTests) {
        System.err.println("Correct solution found in " + totalTime + " seconds.");
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
          if (!foundCorrectAssemblyArea()) {
            System.err.println("Robot moved to " + getMyXPosition() + ", " + getMyYPosition());
            System.err.println("Which is not close enough to " + assemblyArea);
          }
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

  // This method will move the robot forward slightly
  // to determine the robot's heading (0-360 degrees, with
  // 0 pointing to the top of the screen)
  public static int getHeading() {
    return (int) robot.getHeading();
  }

  public static double getHeadingDouble() {
    return robot.getHeading();
  }

  /**
   * @return 2 integer pairs. each pair represents the direction and distance from the Satellit potition at 500,10 in the simulation.The
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
    double randomAA = Math.random();
    if (key == divisionPublicKey) {
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
    }
    else {
      assemblyArea = "asl;dkfj-32";
    }
    return assemblyArea;
  }

  public static String getAssemblyAreaName() {
    //return "JKJFDSI*&)(*K:";
    getProperties();
    if (runningTests && !hasAssemblyArea) {
      try {
        assemblyArea = testConsoleReader.readLine();
        assemblyArea = assemblyArea.intern();
        return assemblyArea;
      }
      catch (IOException e) {
        System.err.println("Error reading a String from test file (console)." + "  Format in input file was incorrect.");
      }
    }
    hasAssemblyArea = true;
    return assemblyArea;
  }

  public static String getEncryptedAssemblyArea() {
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
    pcArray = ((ObstacleSim105) sim).getPolarObstacleArray();
    return pcArray;
  }

  public static int getTemperatureSensor() {
    return getTemperature();
  }

  public static int getTemperature() {
    int currentX;
    int bypassXMin, bypassXMax;
    currentX = getMyXPosition();
    bypassXMin = ((ObstacleSim105) sim).getFireXByPassLocation() - 40;
    bypassXMax = ((ObstacleSim105) sim).getFireXByPassLocation() + 0;
    if (currentX > bypassXMin && currentX < bypassXMax) {
      return 10;
    }
    double error = 0;
    if (currentX < bypassXMin) {
      error = Math.round((bypassXMin - currentX) / 3.0);
    }
    else {
      error = Math.round((currentX - bypassXMax) / 3.0);
    }
    return (int) (90 + error);
  }

  public static void loadStart() {
    createRobot();
  }

  public static void loadSingleFireObstacle() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Single Fire Obstacle Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Fire Only";
    scenarioLoaded = true;
    ((ObstacleSim105) sim).createScenario(1);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadSingleConcertinaObstacle() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Single Concertina Obstacle Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Concertina Only";
    scenarioLoaded = true;
    ((ObstacleSim105) sim).createScenario(2);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadSingleRandomObstacle() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Single Random Obstacle Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Single Random";
    scenarioLoaded = true;
    ((ObstacleSim105) sim).createScenario(3);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadDoubleFixedObstacle() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Double Fixed Obstacle Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Double Fixed";
    scenarioLoaded = true;
    ((ObstacleSim105) sim).createScenario(4);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadDoubleRandomObstacle() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Double Random Obstacle Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Double Random";
    scenarioLoaded = true;
    ((ObstacleSim105) sim).createScenario(5);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadRoadTest() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Road Test Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    ((ObstacleSim105) sim).createScenario(6);
    robotStartX = 340;
    robotStartY = 100;
    testing = true;
    loadStart();
  }

  public static void loadWaterTest() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Water Test Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    ((ObstacleSim105) sim).createScenario(7);
    robotStartX = 665;
    robotStartY = 75;
    testing = true;
    angle = 180;
    loadStart();
  }

  public static void loadTowerTest() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Tower Test Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    ((ObstacleSim105) sim).createScenario(8);
    robotStartX = 600;
    robotStartY = 400;
    testing = true;
    angle = 180;
    loadStart();
  }

  public static void goForward(int time) {
    if (robot.inASimulation()) {
      robot.goForward(time);
    }
    else {
      showError("Robot is destroyed");
      System.exit(0);
    }
  }

  public static int getSatelliteXPosition() {
    return ((ObstacleSim105) sim).getSatelliteX();
  }

  public static int getSatelliteYPosition() {
    return ((ObstacleSim105) sim).getSatelliteY();
  }
}

class HighScore implements Comparable {
  private double score = 0;
  private String scenario = "";
  private String person = "";

  public HighScore(double score, String scenario, String person) {
    this.score = score;
    this.scenario = scenario;
    this.person = person;
  }

  public double getScore() {
    return score;
  }

  public String toMultiLineString() {
    return "" + score + "\r\n" + scenario + "\r\n" + person + "\r\n";
  }

  public String toString() {
    return "" + score + " - " + person + " (" + scenario + ")";
  }

  public int compareTo(Object arg0) {
    HighScore other = (HighScore) arg0;
    return new Double(this.score).compareTo(new Double(other.score));
  }
}