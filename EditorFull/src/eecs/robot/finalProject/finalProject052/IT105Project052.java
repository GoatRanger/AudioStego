package eecs.robot.finalProject.finalProject052;

import eecs.RobotGuiBase;
import eecs.robot.instructor.cobb.ObstacleCrossingBot2;
import eecs.robot.jago.element.Message;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.StandardSensor;
import eecs.robot.jago.element.StandardSensorFactory;

public class IT105Project052 extends RobotGuiBase {
  private static Message clockMSG = new Message("0:00");
  private static int speedRatio = 1;
  private static long startTime = 0;
  private static long totalTime = 0;
  static {
    getProperties();
    sim = new WMDSim051();
    setSimulationTitle("IT105/155 Final Project");
    frame = sim;
    sim.setVisible(!runningTests);
    robot = null;
    runRealisticSim();
    String[] speeds = { "normal", "2x", "4x", "8x" };
    int speed = chooseButton("What speed do you want to run this simulation?", speeds);
    speedRatio = setSimSpeed(speed);
  }

  public static int setSimSpeed(int speed) {
    return (int) Math.pow(2, speed);
  }

  public static void startCountDown(int minutes) {
    totalTime = minutes;
    printLine("Countdown Started... T-" + totalTime + ":00");
    createRobot();
    startTime = System.currentTimeMillis();
  }

  public static void createRobot(int x, int y, int angle) {
    createRobot();
  }

  public static int checkTemperatureSensor() {
    long elapsedTimeInMS = ((System.currentTimeMillis() - startTime));
    elapsedTimeInMS += (1000 * speedRatio);
    String countDownTime = calculateCurrentCountDownTime(elapsedTimeInMS);
    printLine("Temperature sensor checked at T-" + countDownTime);
    return 5;
  }

  private static String calculateCurrentCountDownTime(long elapsedSecs) {
    printLine(elapsedSecs);
    int min = 1;
    while (elapsedSecs >= 60) {
      min++;
      elapsedSecs = elapsedSecs - 60;
    }
    long minTime = totalTime - min;
    long secTime = 60000 - elapsedSecs;
    String countDownTime = "" + minTime + ":" + secTime;
    return countDownTime;
  }

  public static void displayTime(int min, int sec) {
    String zeroFiller = "";
    if (clockMSG.inASimulation()) {
      sim.remove(clockMSG);
    }
    if (sec < 10) {
      zeroFiller = "0";
    }
    clockMSG = new Message(min + ":" + zeroFiller + sec);
    sim.add(clockMSG, 200, 100);
  }

  public static void launchShuttle() {
    goForward(1000);
  }

  public static void waitASecond() {
    try {
      Thread.sleep(1000 / speedRatio);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void createRobot() {
    if (robot != null) {
      showError("Robot already exists.  Cannot create a new one.");
      System.exit(0);
    }
    int angle = 0;
    int robotStartX = 350;
    int robotStartY = 350;
    robot = new ObstacleCrossingBot2();
    robot.setAppearance("/eecs/images/shuttle.jpg");
    StandardSensor lightSensor = StandardSensorFactory.createSensor(RobotSensors.LIGHT, robot);
    StandardSensor hitSensor = StandardSensorFactory.createSensor(RobotSensors.HIT, robot);
    StandardSensor bumperSensor = StandardSensorFactory.createSensor(RobotSensors.HIT, robot);
    robot.addSensor(Monitor.RCX_2, lightSensor);
    robot.addSensor(Monitor.RCX_3, hitSensor);
    robot.addSensor(Monitor.RCX_1, bumperSensor);
    robot.setPower(50);
    robot.setRealismLevel(realism);
    robot.setInitialHeading(angle);
    sim.add(robot, robotStartX, robotStartY);
  }

  public static void checkForOldFormat(String file) {
    openReadFile(file);
    readLine();
    try {
      Double.parseDouble(readLine());
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
      closeReadFile();
    }
  }

  public static double generateRandomNumber(double min, double max) {
    double range = max - min;
    double number = Math.random() * range + min;
    return number;
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

  public static void goForward(int time) {
    if (robot.inASimulation()) {
      robot.goForward(time);
    }
    else {
      showError("Robot is destroyed");
      System.exit(0);
    }
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