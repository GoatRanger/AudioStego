package eecs.robot.wrapper;

import eecs.RobotGuiBase;
import eecs.robot.jago.SimBox;
import eecs.robot.jago.element.ColoredLine;
import eecs.robot.jago.element.ReflectiveColors;
import eecs.robot.jago.element.Wall;

public class Robot extends RobotGuiBase {
  static {
    sim = new SimBox();
    setSimulationTitle(DEFAULT_TITLE);
    sim.add(new Wall(5, 400), 35, 50);
    sim.add(new ColoredLine(5, 400, ReflectiveColors.BLACK), 400, 50);

    frame = sim;

    sim.setVisible(true);

    robot = null;
  }

  public int getLight() {
    return super.getReflectanceSensor();
  }

  public int getTouch() {
    return super.getTouchSensor();
  }

  public void backward() {
    super.goBackward();
  }

  public void backward(int val) {
    super.goBackward(val);
  }

  public void forward() {
    super.goForward();
  }

  public void forward(int val) {
    super.goForward(val);
  }

  public void initRobot(int x, int y, int head) {
    super.createRobot(x, y, head);
    robot.setPower(8);
  }

  public void left(int val) {
    super.pivotLeft(val);
  }

  public void right(int val) {
    super.pivotRight(val);
  }

  public void stop() {
    super.halt();
  }
}