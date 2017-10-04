package eecs;

import eecs.robot.simulation.RobotSim;

public class RobotGui extends RobotGuiBase {
  static {
    sim = new RobotSim();
    setSimulationTitle(DEFAULT_TITLE);
    sim.setBackgroundTile(DEFAULT_IMAGE);
    frame = sim;
    sim.setVisible(true);
    robot = null;
  }
}