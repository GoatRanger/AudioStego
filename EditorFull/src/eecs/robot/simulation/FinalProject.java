package eecs.robot.simulation;

import eecs.RobotGuiBase;
import eecs.robot.jago.SimBox;
import eecs.robot.jago.element.Barrel;
import eecs.robot.jago.element.BrightLight;
import eecs.robot.jago.element.ColoredLine;
import eecs.robot.jago.element.LegoRobot;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.ReflectiveColors;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.StandardSensor;
import eecs.robot.jago.element.StandardSensorFactory;

// The base final project class used for the Fall Semester, 2002. Provides a black path with 3 forks and a pushable barrel at the end of
// each fork. Also has a gray border, and a small gray box with a lightsource in it. Designed to allow demonstration of skills using
// iteration, selection, and the use of a touch, light, and differential-light sensor.
public class FinalProject extends RobotGuiBase {
  static {
    sim = new SimBox();
    sim.setBackgroundImage("/eecs/images/ArcPath.gif");
    setSimulationTitle("IT105/155 Final Project");
    sim.add(new ColoredLine(80, 80, ReflectiveColors.MED_GRAY), 260, 35);
    sim.add(new BrightLight(100), 300, 50);
    sim.add(new Barrel(12), 77, 425);
    sim.add(new Barrel(12), 650, 425);
    sim.add(new Barrel(12), 358, 420);
    frame = sim;
    sim.setVisible(true);
    robot = null;
    runRealisticSim();
  }

  public static int getDifferentialSensor() {
    return robot.getSensorInput(Monitor.RCX_3);
  }
  
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
    pause(500);
  }
}