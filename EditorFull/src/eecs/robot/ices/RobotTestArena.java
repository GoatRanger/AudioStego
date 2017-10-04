package eecs.robot.ices;

import eecs.RobotGui;
import eecs.robot.instructor.cobb.ObstacleCrossingBot2;
import eecs.robot.jago.SimBox;
import eecs.robot.jago.element.BrightLight;
import eecs.robot.jago.element.Message;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.StandardSensor;
import eecs.robot.jago.element.StandardSensorFactory;
import eecs.robot.jago.element.Wall;

public class RobotTestArena extends RobotGui {
  static Message[] displays = new Message[4];
  static {
    sim = new SimBox();
    setSimulationTitle("Proving Grounds");
    frame = sim;
    robot = new ObstacleCrossingBot2();
    runRealisticSim();
    sim.setBackgroundImage("/eecs/images/calibration_test.gif");
    System.out.println("Welcome to the Robot Proving Grounds");
    loadWall();
    loadLight();
    displays[0] = new Message("Task 1 - The wall!");
    displays[1] = new Message("Task 2 Find the White Line");
    displays[2] = new Message("Task 3 Find the light!");
    displays[3] = new Message("Task 4 Cross two Black lines");
    loadRobots();
  }

  public static void loadWall() {
    Wall touchSensorWall = new Wall(150, 10);
    sim.add(touchSensorWall, 20, 20);
  }

  public static void loadLight() {
    BrightLight light = new BrightLight();
    sim.add(light, 700, 50);
  }

  public static void loadRobots() {
    StandardSensor lightSensor1 = StandardSensorFactory.createSensor(RobotSensors.LIGHT, robot);
    StandardSensor hitSensor1 = StandardSensorFactory.createSensor(RobotSensors.HIT, robot);
    StandardSensor bumperSensor1 = StandardSensorFactory.createSensor(RobotSensors.INVERTED_FRONT_BUMPER, robot);
    robot.addSensor(Monitor.RCX_2, bumperSensor1);
    robot.addSensor(Monitor.RCX_3, hitSensor1);
    robot.addSensor(Monitor.RCX_1, lightSensor1);
    robot.setPower(50);
    robot.setRealismLevel(realism);
    robot.setInitialHeading(0);
    robot.setPower(100);
    sim.add(robot, 50, 100);
    sim.add(displays[0], 50, 150);
    sim.add(displays[1], 445, 15);
    sim.add(displays[2], 50, 300);
    sim.add(displays[3], 50, 500);
  }

  public static void moveToTask1() {
    destroyRobot();
    createRobot(50, 100, 0);
  }

  public static void moveToTask2() {
    destroyRobot();
    createRobot(450, 40, 180);
  }

  public static void moveToTask3() {
    destroyRobot();
    createRobot(50, 250, 90);//Bright Light
  }

  public static void moveToTask4() {
    destroyRobot();
    createRobot(50, 430, 90);//count black lines
  }

  public static int getColorSensor() {
    return getReflectanceSensor();
  }

  public static int getMyXPosition() {
    return (int) robot.getLocation().getX();
  }

  public static int getMyYPosition() {
    return (int) robot.getLocation().getY();
  }
}