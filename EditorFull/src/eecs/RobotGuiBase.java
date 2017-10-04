package eecs;

import java.awt.Image;
import java.io.File;
import javax.swing.JOptionPane;
import eecs.robot.jago.Realism;
import eecs.robot.jago.SimBox;
import eecs.robot.jago.element.LegoRobot;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.ReflectiveColors;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.SensorState;
import eecs.robot.jago.element.StandardRobot;
import eecs.robot.jago.element.StandardSensor;
import eecs.robot.jago.element.StandardSensorFactory;
import eecs.robot.wrapper.ImageLoader;

public class RobotGuiBase extends GuiBase implements SensorState, ReflectiveColors, Realism {
  protected static final String DEFAULT_IMAGE = "/eecs/images/white.gif";
  protected static final String DEFAULT_TITLE = "IT105/155 Lego Robot Simulation";
  protected static int realism = IDEALISTIC;
  protected static StandardRobot robot;
  protected static SimBox sim;
  protected static String title = DEFAULT_TITLE;

  public static void createRobot(int x, int y, int angle) {
    if (robot != null) {
      showError("Robot already exists.  Cannot create a new one.");
      System.exit(0);
    }
    robot = new LegoRobot();
    StandardSensor lightSensor = StandardSensorFactory.createSensor(RobotSensors.LIGHT, robot);
    StandardSensor hitSensor = StandardSensorFactory.createSensor(RobotSensors.HIT, robot);
    StandardSensor bumperSensor = StandardSensorFactory.createSensor(RobotSensors.INVERTED_FRONT_BUMPER, robot);
    robot.addSensor(Monitor.RCX_2, bumperSensor);
    robot.addSensor(Monitor.RCX_3, hitSensor);
    robot.addSensor(Monitor.RCX_1, lightSensor);
    robot.setPower(5);
    robot.setInitialHeading(angle);
    robot.setRealismLevel(realism);
    sim.add(robot, x, y);
    // Pause to allow updates prior to attempting to use light sensor
    // Trying to use light sensor prior to it being ready may result in
    // an infinite loop
    pause(500);
  }

  public static void destroyRobot() {
    if (robot != null) {
      sim.remove(robot);
      robot = null;
    }
  }

  public static void drawTrails(boolean on) {
    checkRobot("drawTrails");
    robot.drawTrails(on);
  }

  public static void setBackgroundImage(String filename) {
    File f = new File(filename);
    if (!f.exists()) {
      Image img = ImageLoader.getImage(new RobotGuiBase(), filename);
      if (img != null) {
        sim.setBackgroundImage(img);
      }
      else {
        checkFile(filename);
      }
    }
    else {
      sim.setBackgroundImage(filename);
    }
  }

  public static void setBackgroundTile(String filename) {
    checkFile(filename);
    sim.setBackgroundTile(filename);
  }

  public static int getHITSensor() {
    return getDualLightSensor();
  }

  public static int getDualLightSensor() {
    checkRobot("getDualLightSensor");
    int value = robot.getSensorInput(Monitor.RCX_3);
    if (value == RobotSensors.NOT_CONNECTED) {
      showError("Tried to get the value of a differential light sensor, but this " + "robot does not have a differential light sensor.");
      System.exit(0);
    }
    return value;
  }

  public static void setPower(int power) {
    checkRobot("setPower");
    if ((power < 0) || (power > 10)) {
      showError("Tried to setPower with power level " + power + ".\n" + "Must be between 1 and 10.");
      System.exit(0);
    }
    robot.setPower(power);
  }

  public static int getLightSensor() {
    return getReflectanceSensor();
  }

  public static int getColorSensor() {
    return getReflectanceSensor();
  }

  public static int getReflectanceSensor() {
    checkRobot("getReflectanceSensor");
    int value = robot.getSensorInput(Monitor.RCX_1);
    if (value == RobotSensors.NOT_CONNECTED) {
      showError("Tried to get the value of the reflectance sensor, but this " + "robot does not have a blue reflectance sensor.");
      System.exit(0);
    }
    if (value == SensorState.NOT_READY) {
      showError("The sensor did not have time to properly initialize.");
    }
    return value;
  }

  public static void setSimulationTitle(String name) {
    getProperties();
    if (!runningTests) {
      title = name;
      if (realism == REALISTIC) {
        sim.setSimTitle(title + " - REALISTIC Mode");
      }
      else {
        sim.setSimTitle(title + " - IDEALISTIC Mode");
      }
    }
  }

  public static int getTouchSensor() {
    checkRobot("getTouchSensor");
    int value = robot.getSensorInput(Monitor.RCX_2);
    if (value == RobotSensors.NOT_CONNECTED) {
      showError("Tried to get the value of a touch sensor, but this " + "robot does not have a touch sensor.");
      System.exit(0);
    }
    return value;
  }

  public static void clearOutput() {
    getProperties();
    if (!runningTests) {
      sim.clearOutput();
    }
  }

  public static void goBackward() {
    checkRobot("goBackward");
    robot.goBackward();
  }

  public static void goBackward(int ms) {
    checkRobot("goBackward");
    checkTime("goBackward", ms);
    robot.goBackward(ms);
  }

  public static void goForward() {
    checkRobot("goForward");
    robot.goForward();
  }

  public static void goForward(int ms) {
    checkRobot("goForward");
    checkTime("goForward", ms);
    robot.goForward(ms);
  }

  public static void halt() {
    checkRobot("halt");
    robot.halt();
  }

  public static void pause(int ms) {
    checkRobot("pause");
    checkTime("pause", ms);
    try {
      Thread.sleep(ms);
    }
    catch (InterruptedException e) {
      //
    }
  }

  public static void pivotLeft(int degrees) {
    checkRobot("pivotLeft");
    checkAngle("pivotLeft", degrees);
    robot.pivotLeft(degrees);
  }

  public static void pivotRight(int degrees) {
    checkRobot("pivotRight");
    checkAngle("pivotRight", degrees);
    robot.pivotRight(degrees);
  }

  public static void runFixedRealisticSim() {
    realism = FIXED_REALISTIC;
    sim.setRealismLevel(realism);
    sim.setSimTitle(title + " - Fixed-REALISTIC Mode");
  }

  public static void runIdealisticSim() {
    realism = IDEALISTIC;
    sim.setRealismLevel(realism);
    sim.setSimTitle(title + " - IDEALISTIC Mode");
  }

  public static void runRealisticSim() {
    realism = REALISTIC;
    sim.setRealismLevel(realism);
    sim.setSimTitle(title + " - REALISTIC Mode");
  }

  public void setRealismLevel(int level) {
    if (level == REALISTIC) {
      runRealisticSim();
    }
    else
      if (level == IDEALISTIC) {
        runIdealisticSim();
      }
      else {
        showMessage("Realism must be set to IDEALISTIC or REALISTIC");
      }
  }

  public int getRealismLevel() {
    return realism;
  }

  public static void print(String msg) {
    getProperties();
    if (runningTests) {
      System.out.print(msg);
    }
    else {
      sim.print(msg);
    }
  }

  public static void print(int number) {
    print("" + number);
  }

  public static void print(double number) {
    print("" + number);
  }

  public static void print(boolean b) {
    print("" + b);
  }

  public static void print(char ch) {
    print("" + ch);
  }

  public static void printCode(String code) {
    getProperties();
    if (runningTests) {
      System.out.println(code);
    }
    else {
      sim.printCode(code);
    }
  }

  public static void printError(String msg) {
    getProperties();
    if (runningTests) {
      System.out.println(msg);
    }
    else {
      sim.printError(msg);
    }
  }

  public static void printError(int number) {
    printError("" + number);
  }

  public static void printError(double number) {
    printError("" + number);
  }

  public static void printError(boolean b) {
    printError("" + b);
  }

  public static void printError(char ch) {
    printError("" + ch);
  }

  public static void printError() {
    printError("");
  }

  public static void printLine(String msg) {
    getProperties();
    if (runningTests) {
      System.out.println(msg);
    }
    else {
      sim.printLine(msg);
    }
  }

  public static void printLine(int number) {
    printLine("" + number);
  }

  public static void printLine(double number) {
    printLine("" + number);
  }

  public static void printLine(boolean b) {
    printLine("" + b);
  }

  public static void printLine(char ch) {
    printLine("" + ch);
  }

  public static void printLine() {
    printLine("");
  }

  public static void showError(String msg) {
    getProperties();
    if (runningTests) {
      System.out.println(msg);
    }
    else {
      sim.stop();
      JOptionPane.showMessageDialog(frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
      sim.start();
    }
  }

  public static void showMessage(String msg) {
    getProperties();
    if (runningTests) {
      System.out.println(msg);
    }
    else {
      JOptionPane.showMessageDialog(frame, msg);
    }
  }

  protected static void checkAngle(String command, int degrees) {
    if ((degrees < 0) || (degrees > 360)) {
      showError("Tried to call " + command + " with " + degrees + " degrees.\n" + "Must be between 0 and 360.");
      System.exit(0);
    }
  }

  protected static void checkFile(String filename) {
    java.io.File file = new java.io.File(filename);
    if (!file.exists()) {
      showError("File " + filename + " does not exist.");
      System.exit(0);
    }
  }
  protected static void checkRobot(String command) {
    if (robot == null) {
      showError("Must create robot before issuing " + command + " command.");
      System.exit(0);
    }
  }

  protected static void checkTime(String command, int ms) {
    if (ms <= 0) {
      showError("Tried to call " + command + " with " + ms + " milliseconds.\n" + "Must be positive.");
      System.exit(0);
    }
  }
}