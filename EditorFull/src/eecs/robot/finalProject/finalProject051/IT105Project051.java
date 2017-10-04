package eecs.robot.finalProject.finalProject051;

import java.io.File;
import java.util.*;
import javax.swing.JOptionPane;
import eecs.robot.instructor.cobb.ObstacleCrossingBot2;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.StandardSensor;
import eecs.robot.jago.element.StandardSensorFactory;

public class IT105Project051 extends eecs.RobotGuiBase {
  public static boolean areIntersectingPointsClose = false;
  static int teleportations = 0;
  static long startTime;
  static double totalTime;
  static boolean areCrossingPointsValid;
  static String assemblyArea;
  static boolean hasAssemblyArea = false;
  static boolean testing;
  private static int robotStartX, robotStartY;
  private static int angle = 0;
  private static boolean scenarioLoaded;
  private static String endOfMissionMessage = "";
  private static boolean isNewYorkSectorWMD = false;
  private static boolean isVirigniaSectorWMD = false;
  private static boolean isFloridaSectorWMD = false;
  private static int scenario = 0;
  private static boolean isRobotCloseToWeapon = false;
  private static int numberOfSectors = 0;
  private static String option = "";
  static {
    getProperties(); // Why?
    sim = new WMDSim051();
    setSimulationTitle("IT105/155 Final Project");
    frame = sim;
    if (!runningTests) {
      sim.setVisible(true);
    }
    //sim.setDebugging(true);
    robot = null;
    runRealisticSim();
    //createRobot();
    startTime = System.currentTimeMillis();
  }

  public static void createRobot(int x, int y, int angle) {
    createRobot();
  }

  public static int getColorSensor() {
    return getReflectanceSensor();
  }

  private static void createRobot() {
    if (robot != null) {
      showError("Robot already exists.  Cannot create a new one.");
      System.exit(0);
    }
    robot = new ObstacleCrossingBot2();
    if (scenario == 5 || scenario == 7 || scenario == 9) {
      angle = (int) Math.round(Math.random() * 360);
    }
    else {
      angle = 0;
    }
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

  public static String getCurrentSectorsObstacleType() {
    Sector currentSector = ((WMDSim051) sim).getCurrentSector();
    return currentSector.getMyObstacleType();
  }

  public static int getCurrentWeaponsXLocation() {
    return ((WMDSim051) sim).getCurrentSector().getMyWeapon().getXLocation();
  }

  public static int getCurrentWeaponsYLocation() {
    return ((WMDSim051) sim).getCurrentSector().getMyWeapon().getYLocation();
  }

  public static int getCurrentTeleportersXLocation() {
    return ((WMDSim051) sim).getCurrentSector().getMyCenter().getXLocation();
  }

  public static int getCurrentTeleportersYLocation() {
    return ((WMDSim051) sim).getCurrentSector().getMyCenter().getYLocation();
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
    String file;
    String fileName = "\\\\usmasvddcsweb\\it105\\scores\\";
    if (numberOfSectors == 1) {
      file = "highScores.txt";
    }
    else
      if (numberOfSectors == 2) {
        file = "highScores2.txt";
      }
      else {
        file = "highScores3.txt";
      }
    File myf = new File(fileName + file);
    if (myf.exists()) {
      //printLine("Exists");
    }
    else {
      try {
        openWriteFile(fileName + file);
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
    String highScores = "Your Time: " + totalTime + "\n";
    HighScore[] hs = new HighScore[maxScores + 1];
    int counter = 0;
    openReadFile(fileName + file);
    while (moreToRead()) {
      String line = readLine();
      double score = Double.parseDouble(line);
      String scen = readLine();
      String p = readLine();
      hs[counter] = new HighScore(score, scen, p);
      counter++;
    }
    for (int fill = counter; fill < maxScores + 1; fill++) {
      hs[fill] = new HighScore(1000.0, "none", "Jane Cadet");
    }
    hs[maxScores] = new HighScore(totalTime, option, System.getProperty("user.name", "anonymous"));
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
  }

  public static void teleportRobotToNextSector() {
    if (isRobotCloseToTeleporter()) {
      teleportations++;
      int currentSectorID = ((WMDSim051) sim).getSectorID();
      if (currentSectorID == 0 && numberOfSectors == 1) {
        printLine("No sector available to teleport to...");
      }
      else
        if (currentSectorID == 0 && numberOfSectors > 1) {
          teleportRobotToVirginiaSector();
        }
        else
          if (currentSectorID == 1 && numberOfSectors == 2) {
            teleportRobotToNewYorkSector();
          }
          else
            if (currentSectorID == 1 && numberOfSectors == 3) {
              teleportRobotToFloridaSector();
            }
            else
              if (currentSectorID == 2 && numberOfSectors == 3) {
                teleportRobotToNewYorkSector();
              }
    }
    else {
      showMessage("I need more POWER! You are too far from the teleporter.");
    }
  }

  private static void teleportRobotToVirginiaSector() {
    ((WMDSim051) sim).remove(robot);
    robot = null;
    robotStartX = 650;
    robotStartY = 550;
    createRobot();
    ((WMDSim051) sim).setCurrentSectorID(1);
  }

  private static void teleportRobotToNewYorkSector() {
    ((WMDSim051) sim).remove(robot);
    robot = null;
    robotStartX = 250;
    robotStartY = 550;
    createRobot();
    ((WMDSim051) sim).setCurrentSectorID(0);
  }

  private static void teleportRobotToFloridaSector() {
    ((WMDSim051) sim).remove(robot);
    robot = null;
    robotStartX = 250;
    robotStartY = 230;
    createRobot();
    ((WMDSim051) sim).setCurrentSectorID(2);
  }

  /*
   * public static void teleportRobotToGold(){ ((WMDSim051)sim).remove(((ObstacleCrossingBot)robot)); robot=null; robotStartX=250;
   * robotStartY=250; createRobot(); }
   */
  private static void checkRobotPosition() {
    //check to see if the robot is within 75 pixels of the Weapon
    Sector currentSector = ((WMDSim051) sim).getCurrentSector();
    int weaponX = currentSector.getMyWeapon().getXLocation();
    int weaponY = currentSector.getMyWeapon().getYLocation();
    if (((WMDSim051) sim).arePointsClose(weaponX, weaponY, getMyXPosition(), getMyYPosition(), 75)) {
      isRobotCloseToWeapon = true;
      // printLine("True");
    }
    else {
      isRobotCloseToWeapon = false;
    }
  }

  public static int getWeightSensor() {
    checkRobotPosition();
    Sector currentSector = ((WMDSim051) sim).getCurrentSector();
    if (((currentSector.getMyWeapon().isWeightWMD))) {
      if (isRobotCloseToWeapon) {
        return (int) (5000 + Math.round(Math.random() * 3000));
      }
      printLine("Robot Weight Sensor SubSystem Error.....");
      //					invalidWeight=true; //note: this is a global variable that will tell the grader if the weight they got was
      // bogus due to distance
      return (int) Math.round(Math.random() * 3000);
    }
    return (int) Math.round(Math.random() * 4000);
  }

  public static String getMetalSensor() {
    checkRobotPosition();
    Sector currentSector = ((WMDSim051) sim).getCurrentSector();
    if (currentSector.getMyWeapon().isMetalWMD) {
      //totalDistance=getDistance();
      if (isRobotCloseToWeapon) {
        return "lead"; //metalType will be a string that can be "lead" or "none" based on if it is metal or not
      }
      printLine("Robot Metal Sensor SubSystem Error.....");
      return "fsdgor";
    }
    return "none";
  }

  public static double getRadiationSensor() {
    double radiationLevel = 0;
    checkRobotPosition();
    Sector currentSector = ((WMDSim051) sim).getCurrentSector();
    if (currentSector.getMyWeapon().isRadiationWMD) {
      //distance = getDistance();
      if (isRobotCloseToWeapon) {
        radiationLevel = generateRandomNumber(0.0002, 0.0003);
        return radiationLevel;
      }
      radiationLevel = generateRandomNumber(0.00001, 0.000025);
      printLine("Robot Radiation Sensor SubSystem Error.....");
      return radiationLevel;
    }
    radiationLevel = generateRandomNumber(0.00001, 0.000025);
    endOfMissionMessage += " Too far from Weapon for radiation measurement";
    return radiationLevel;
  }

  public static double generateRandomNumber(double min, double max) {
    double range = max - min;
    double number = Math.random() * range + min;
    return number;
  }

  private static boolean areWeaponsCorrectlyIdentified() {
    boolean isCorrect = true;
    boolean[] solutionArray = ((WMDSim051) sim).getSolution();
    if (!(isNewYorkSectorWMD == solutionArray[0])) {
      System.out.println("New York Sector Identification is incorrect");
      isCorrect = false;
    }
    if (!(isVirigniaSectorWMD == solutionArray[1])) {
      System.out.println("Virginia Sector Identification is incorrect");
      isCorrect = false;
    }
    if (!isFloridaSectorWMD == solutionArray[2]) {
      System.out.println("Florida Sector Identification is incorrect");
      isCorrect = false;
    }
    return isCorrect;
  }

  private static boolean isRobotCloseToTeleporter() {
    Sector currentSector = ((WMDSim051) sim).getCurrentSector();
    int teleporterX = currentSector.getMyCenter().getXLocation();
    int teleporterY = currentSector.getMyCenter().getYLocation();
    if (((WMDSim051) sim).arePointsClose(teleporterX, teleporterY, getMyXPosition(), getMyYPosition(), 75)) {
      return true;
    }
    return false;
  }

  private static boolean didTeleportProperly() {
    return (teleportations >= (numberOfSectors - 1));
  }

  private static boolean didRobotPassConcertina() {
    boolean b = ((ObstacleCrossingBot2) robot).hasCorrectlyCrossedConcertina();
    return b;
  }

  public static void submitUNReport() {
    getProperties();
    totalTime = (System.currentTimeMillis() - startTime) / 1000.0;
    System.out.println("TotalTime=" + totalTime + " seconds");
    if (isRobotCloseToTeleporter()) {
      if (areWeaponsCorrectlyIdentified() && didRobotPassConcertina() && didTeleportProperly()) {
        System.out.println("All weapons identified Correctly...Good Job");
        //showMessage("Great Job. We are working on the High Score List");
        checkHighScores();
      }
      else
        if (!didRobotPassConcertina()) {
          showMessage("You did not correctly bypass the Concerinta obstacle");
        }
        else
          if (!didTeleportProperly()) {
            showMessage("You did not teleport properly");
          }
          else {
            showMessage("All weapons not identified Correctly ");
          }
    }
    else {
      showMessage("There has been a technical failure in your communications with UN HQs. Check your location");
    }
  }

  public static void destroyRobot() {
    showMessage("Nice Try....");
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

  public static int getMyXPosition() {
    return (int) robot.getLocation().getX();
  }

  public static int getMyYPosition() {
    return (int) robot.getLocation().getY();
  }

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
      ((ObstacleCrossingBot2) robot).gotoAngle((float) angle);
      goForward(75);
    }
  }

  public static void pivotTo(float angle) {
    ((ObstacleCrossingBot2) robot).gotoAngle(angle);
  }

  public static void setCurrentSectorsWeaponStatus(boolean isWMD) {
    int currentSectorID = ((WMDSim051) sim).getSectorID();
    if (currentSectorID == 0) {
      storeNewYorkSectorWeaponStatus(isWMD);
    }
    else
      if (currentSectorID == 1) {
        storeVirginiaSectorWeaponStatus(isWMD);
      }
      else {
        storeFloridaSectorWeaponStatus(isWMD);
      }
  }

  public static void storeNewYorkSectorWeaponStatus(boolean isWMD) {
    ((WMDSim051) sim).getCurrentSector();
    isNewYorkSectorWMD = isWMD;
  }

  public static void storeVirginiaSectorWeaponStatus(boolean isWMD) {
    isVirigniaSectorWMD = isWMD;
  }

  public static void storeFloridaSectorWeaponStatus(boolean isWMD) {
    isFloridaSectorWMD = isWMD;
  }

  public static int getTemperatureSensor() {
    return getTemperature();
  }

  public static int getTemperature() {
    int currentX;
    int bypassXMin, bypassXMax;
    ((WMDSim051) sim).getCurrentSector();
    currentX = getMyXPosition();
    bypassXMin = ((WMDSim051) sim).getFireXByPassLocation() - 40;
    bypassXMax = ((WMDSim051) sim).getFireXByPassLocation() + 0;
    //printLine("gettemp bypass min (40 off of center) information"+bypassXMin);
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

  public static void loadSingleFixedFire() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Tower Test Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Fire Fixed";
    scenarioLoaded = true;
    scenario = 1;
    numberOfSectors = 1;
    ((WMDSim051) sim).createScenario(scenario, numberOfSectors, false);// Single Fixed Fire
    //WMDSim051 sim.loadObstacle(Fire);
    robotStartX = 100 + (int) Math.round(Math.random() * 700);
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadSingleFixedWire() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Single Wire Obstacle Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Wire Fixed";
    scenarioLoaded = true;
    scenario = 2;
    numberOfSectors = 1;
    ((WMDSim051) sim).createScenario(scenario, numberOfSectors, false);
    robotStartX = 100 + (int) Math.round(Math.random() * 700);
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadSingleFixedWater() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Water Scenario.\nAnother scenario was already loaded.", "Cannot Load Two Scenarios",
          JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    scenario = 3;
    option = "Single Water";
    numberOfSectors = 1;
    ((WMDSim051) sim).createScenario(scenario, numberOfSectors, false);
    //WMDSim051 sim.loadObstacle(Fire);
    robotStartX = 100 + (int) Math.round(Math.random() * 700);
    robotStartY = 550;
    testing = false;
    angle = 180;
    loadStart();
  }

  public static void loadSingleFixedWall() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Fixed Wall Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    scenario = 4;
    numberOfSectors = 1;
    ((WMDSim051) sim).createScenario(scenario, numberOfSectors, false);
    option = "Single Wall";
    robotStartX = 100 + (int) Math.round(Math.random() * 700);
    robotStartY = 550;
    testing = false;
    angle = (int) Math.round(Math.random() * 360);
    loadStart();
  }

  public static void loadSingleRandom() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Single Random Obstacle Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Single Random";
    scenarioLoaded = true;
    numberOfSectors = 1;
    scenario = 5;
    ((WMDSim051) sim).createScenario(scenario, numberOfSectors, true);
    robotStartX = 100 + (int) Math.round(Math.random() * 700);
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadTwoFixed() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Double Fixed Obstacle Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Double Fixed";
    scenarioLoaded = true;
    scenario = 6;
    numberOfSectors = 2;
    ((WMDSim051) sim).createScenario(scenario, numberOfSectors, false);
    robotStartX = 50 + (int) Math.round(Math.random() * 300);
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadTwoRandom() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Double Random Obstacle Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    option = "Double Random";
    scenarioLoaded = true;
    numberOfSectors = 2;
    scenario = 7;
    ((WMDSim051) sim).createScenario(scenario, numberOfSectors, true);
    robotStartX = 100 + (int) Math.round(Math.random() * 300);
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadThreeFixed() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Three Fixed Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    option = "Three Fixed";
    numberOfSectors = 3;
    scenario = 8;
    ((WMDSim051) sim).createScenario(scenario, numberOfSectors, false);
    robotStartX = 100 + (int) Math.round(Math.random() * 300);
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadThreeRandom() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null, "Cannot load Water Test Scenario.\nAnother scenario was already loaded.",
          "Cannot Load Two Scenarios", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    scenario = 9;
    numberOfSectors = 3;
    option = "Random Three";
    ((WMDSim051) sim).createScenario(scenario, numberOfSectors, true);
    robotStartX = 100 + (int) Math.round(Math.random() * 300);
    robotStartY = 550;
    testing = false;
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
}// end of IT105Project 051 below is Karl Gossett/Mike Cobb's High score program

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