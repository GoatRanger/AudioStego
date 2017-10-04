package eecs.robot.simulation;

import eecs.RobotGuiBase;
import eecs.robot.jago.element.LegoRobot;
import eecs.robot.jago.element.Monitor;
import eecs.robot.jago.element.RobotSensors;
import eecs.robot.jago.element.StandardSensor;
import eecs.robot.jago.element.StandardSensorFactory;

public class FinalProject032 extends RobotGuiBase {
  static {
    sim = new PolygonSim(true, false);
    setSimulationTitle("IT105/155 Final Project");
    frame = sim;
    sim.setVisible(true);
    robot = null;
    runRealisticSim();
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