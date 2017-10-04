/*
 * Created on Oct 31, 2003 To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.simulation;

import java.io.File;
import java.util.*;

import javax.swing.JOptionPane;

import jago.element.LegoRobot;
import jago.element.RobotSensors;
import jago.element.StandardSensor;
import jago.element.StandardSensorFactory;
import eecs.instructor.cobb.ObstacleCrossingBot;
//import eecs.jago.instructor.flowers.DozerBot;
import jago.element.Wall;


/**
 * @author dm0266 To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IT105Project042 extends eecs.RobotGuiBase {
  public static boolean areIntersectingPointsClose = false;
  static int divisionPublicKey, divisionPrivateKey, cadetsPublicKey,
      cadetsPrivateKey;
  static String option = "";
  static long startTime;
  static double totalTime;
  static boolean areCrossingPointsValid;
  static String assemblyArea;
  static boolean testing;
  static int robotStartX, robotStartY;
  static int angle = 0;
  static boolean scenarioLoaded;
  static {
    sim = new ObstacleSim105();
    setSimulationTitle("IT105/155 Final Project");
    frame = sim;
    sim.setVisible(true);
    //sim.setDebugging(true);
    robot = null;
    runRealisticSim();
    Wall leftObstacle1, rightObstacle1, leftObstacle2, rightObstacle2, leftObstacle3, rightObstacle3;
    //createRobot();
    startTime = System.currentTimeMillis();
    double randomAA = Math.random();
    if (randomAA < .33) {
      assemblyArea = "AA Eagle";
    } else if (randomAA < .66) {
      assemblyArea = "AA River";
    } else {
      assemblyArea = "AA Cobra";
    }
    scenarioLoaded = false;
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
    StandardSensor lightSensor = StandardSensorFactory.createSensor(
        RobotSensors.LIGHT, robot);
    StandardSensor hitSensor = StandardSensorFactory.createSensor(
        RobotSensors.HIT, robot);
    StandardSensor bumperSensor = StandardSensorFactory.createSensor(
        RobotSensors.INVERTED_FRONT_BUMPER, robot);
    robot.addSensor(LegoRobot.RCX_2, bumperSensor);
    robot.addSensor(LegoRobot.RCX_3, hitSensor);
    robot.addSensor(LegoRobot.RCX_1, lightSensor);
    robot.setPower(50);
    robot.setRealismLevel(realism);
    robot.setInitialHeading(angle);
    sim.add(robot, robotStartX, robotStartY);
    // Pause to allow updates prior to attempting to use light sensor
    // Trying to use light sensor prior to it being ready may result in
    // an infinite loop
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
  		for (int i=0; i<hs.length; i++) {
  			hs[i] = new HighScore(1000.0,"none","Joe Cadet");
  		}
  		int counter = 0;
  		while (moreToRead()) {
  			double score = Double.parseDouble(readLine());
  			hs[counter++] = new HighScore(score,"unknown",System.getProperty("user.name","n/a"));
  		}
  		closeReadFile();
  		openWriteFile(file);
  		for (int i=0; i<hs.length; i++) {
  			write(hs[i].toMultiLineString());
  		}
  		closeWriteFile();
    } catch (NumberFormatException nfe) {
      // If error, then file is new file format (second entry is a String)
    	closeReadFile();
  	}
  }
  public static void checkHighScores() {
  	int maxScores = 25;
    //  	openReadFile("highScores.txt");
  	String fileName = "\\\\usmasvddspider\\internal web\\courses\\it105instructors\\scores\\";
    String file = "highScores.txt";
    //File myf = new File(fileName + file);
    File myf = new File(file);
    if (myf.exists()) {
      printLine("Exists");
      checkForOldFormat(file);
    } else {
      try {
      	//File dir = new File(fileName);
      	//dir.mkdir();
        //printLine("File created");
        //openWriteFile(fileName + file);
      	openWriteFile(file);
        for (int i = 0; i < maxScores; i++) {
          writeLine("1000.0");
          writeLine("none");
          writeLine("Joe Cadet");
        }
        closeWriteFile();
      } catch (Exception e) {
      }
    }
//    try {
//      // fileReader = new BufferedReader(new FileReader(fileName + file));
//    	fileReader = new BufferedReader(new FileReader(file));
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
    //openReadFile(fileName + file);
    openReadFile(file);
    while (moreToRead()) {
    	
      String line = readLine();
      //scores[counter] = Double.parseDouble(line);
      double score = Double.parseDouble(line);
      //scenario[counter] = readLine();
      String scen = readLine();
//      person[counter] = readLine();
      String p = readLine();
      hs[counter] = new HighScore(score,scen,p);
      counter++;
    }
    for (int fill = counter; fill < maxScores+1; fill++) {
    	hs[fill] = new HighScore(1000.0,"none","Jane Cadet");
    }
    hs[maxScores] = new HighScore(totalTime, option, System.getProperty("user.name","anonymous"));
//    scores[5] = totalTime;
    closeReadFile();
    Arrays.sort(hs);
    if (hs[maxScores].getScore() == totalTime) {
      highScores += "\n" + "No High Score this time"+"\n";
    } else {
      highScores += "\n" + "Great Job, A new high Score" + "\n";
    }
		highScores += "  Best Times" + "\n";
    //openWriteFile(fileName + file);
		openWriteFile(file);
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
    if (((ObstacleCrossingBot) robot).hasCorrectlyCrossedConcertina()
        && ((ObstacleCrossingBot) robot).hasCorrectlyCrossedFire()) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean foundCorrectAssemblyArea() {
    int currentX = getMyXPosition();
    int currentY = getMyYPosition();
    if ((assemblyArea.equals("AA Eagle"))
        && ((ObstacleSim105) sim).arePointsClose(600, 550, currentX, currentY,
            25)) {
      return true;
    } else if ((assemblyArea.equals("AA River"))
        && ((ObstacleSim105) sim).arePointsClose(700, 550, currentX, currentY,
            25)) {
      return true;
    } else if ((assemblyArea.equals("AA Cobra"))
        && ((ObstacleSim105) sim).arePointsClose(800, 550, currentX, currentY,
            25)) {
      return true;
    } else
      return false;
  }

  public static void transmitMissionComplete() {
    //System.out.println("Still need to finish this method");
    totalTime = (System.currentTimeMillis() - startTime) / 1000.0;
    System.out.println("TotalTime=" + totalTime + " seconds");
    /*
     * @TODO add the opening of file read file and check for high score...
     */
    ;
    if (foundValidCrossingPoints() && foundCorrectAssemblyArea() && (!testing)) {
      checkHighScores();
    } else if (testing) {
      showMessage("Testing Complete");
    } else {
      showError("Failed to correctly identify crossing points or you are in the wrong AA");
    }
  }

  public static void setPower(int power) {
    checkRobot("setPower");
    if ((power < 0) || (power > 100)) {
      showError("Tried to setPower with power level " + power + ".\n"
          + "Must be between 1 and 100.");
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
    return (int)robot.getHeading();
  }

  public static double getHeadingDouble() {
    return robot.getHeading();
  }

  /**
   * @return 2 integer pairs. each pair represents the direction and distance
   *         from the Satellit potition at 500,10 in the simulation.The first
   *         number of each pair represents the distance while the second number
   *         is the direction from 500,10.
   */
  public int[] getBypassInfo() {
    int[] array = new int[3];
    /*
     * @TODO 042:getBypassInfo
     */
    return array;
  }

  /**
   * @return an integer of the x-coordinate of the current location of the
   *         robot.
   */
  public static int getMyXPosition() {
    return (int) robot.getLocation().getX();
  }

  /**
   * @return an integer of the y-coordinate of the current location of the
   *         robot.
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
      } else {
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
    double randomAA = Math.random();
    if (key == divisionPublicKey) {
      if (randomAA < .33) {
        assemblyArea = "AA Eagle";
      } else if (randomAA < .66) {
        assemblyArea = "AA River";
      } else {
        assemblyArea = "AA Cobra";
      }
    } else {
      assemblyArea = "asl;dkfj-32";
    }
    return assemblyArea;
  }

  public static String getAssemblyAreaName() {
    //return "JKJFDSI*&)(*K:";
    return assemblyArea;
  }
  
  public static String getEncryptedAssemblyArea() {
    return "JKJFDSI*&)(*K:";
  }
  
  /*
   * @TODO Need to access the robots stored crossing locations to provide to
   * user.
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
  public static int getTemperatureSensor(){
  	return getTemperature();
  }
  /*
   * @TODO Finish GetTemperature. perhaps if you know the location of fire just
   * test for it?
   */
  public static int getTemperature() {
    int currentX;
    int currentY;
    int currentFireY;
    int deltaY;
    int bypassXMin, bypassXMax;
    currentX = getMyXPosition();
    currentY = getMyYPosition();
    currentFireY = ((ObstacleSim105) sim).getFireYLocation();
    deltaY = currentY - currentFireY;
    bypassXMin = ((ObstacleSim105) sim).getFireXByPassLocation() - 40;
    bypassXMax = ((ObstacleSim105) sim).getFireXByPassLocation() + 0;
    //System.out.println("Bypass min and max: "+bypassXMin+ " "+bypassXMax);
    if (currentX > bypassXMin && currentX < bypassXMax) {
      return 10;
    } else {
      double error = 0;
      if (currentX<bypassXMin) {
        error = Math.round((bypassXMin-currentX)/3.0);
      } else {
        error = Math.round((currentX-bypassXMax)/3.0);
      }
      return (int)(90+error);
    }
  }

  public static void loadStart() {
    createRobot();
  }

  public static void loadSingleFireObstacle() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null,"Cannot load Single Fire Obstacle Scenario.\nAnother scenario was already loaded.","Cannot Load Two Scenarios",JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    option = "Fire";
    ((ObstacleSim105) sim).createScenario(1);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadSingleConcertinaObstacle() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null,"Cannot load Single Concertina Obstacle Scenario.\nAnother scenario was already loaded.","Cannot Load Two Scenarios",JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    option = "Concertina";
    ((ObstacleSim105) sim).createScenario(2);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadSingleRandomObstacle() {
  	if (scenarioLoaded) {
    JOptionPane.showMessageDialog(null,"Cannot load Single Random Obstacle Scenario.\nAnother scenario was already loaded.","Cannot Load Two Scenarios",JOptionPane.ERROR_MESSAGE);
    System.exit(0);
  }
  	scenarioLoaded = true;
    option = "SingleRandom";
    ((ObstacleSim105) sim).createScenario(3);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadDoubleFixedObstacle() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null,"Cannot load Double Fixed Obstacle Scenario.\nAnother scenario was already loaded.","Cannot Load Two Scenarios",JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    option = "DoubleFixed";
    ((ObstacleSim105) sim).createScenario(4);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadDoubleRandomObstacle() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null,"Cannot load Double Random Obstacle Scenario.\nAnother scenario was already loaded.","Cannot Load Two Scenarios",JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    scenarioLoaded = true;
    option = "DoubleRandom";
    ((ObstacleSim105) sim).createScenario(5);
    robotStartX = 140;
    robotStartY = 550;
    testing = false;
    loadStart();
  }

  public static void loadRoadTest() {
    if (scenarioLoaded) {
      JOptionPane.showMessageDialog(null,"Cannot load Road Test Scenario.\nAnother scenario was already loaded.","Cannot Load Two Scenarios",JOptionPane.ERROR_MESSAGE);
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
    JOptionPane.showMessageDialog(null,"Cannot load Water Test Scenario.\nAnother scenario was already loaded.","Cannot Load Two Scenarios",JOptionPane.ERROR_MESSAGE);
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
      JOptionPane.showMessageDialog(null,"Cannot load Tower Test Scenario.\nAnother scenario was already loaded.","Cannot Load Two Scenarios",JOptionPane.ERROR_MESSAGE);
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
    } else {
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
		return "" + score + "\r\n" +
			scenario + "\r\n" + person + "\r\n";
	}
	
	public String toString() {
		return "" + score + " - " + person + " ("+scenario+")";
	}

	public int compareTo(Object arg0) {
		HighScore other = (HighScore)arg0;
		return new Double(this.score).compareTo(new Double(other.score));
	}
}