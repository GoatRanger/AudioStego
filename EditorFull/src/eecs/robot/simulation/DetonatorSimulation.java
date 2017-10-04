package eecs.robot.simulation;

import java.util.Random;
import eecs.robot.jago.SimBox;
import eecs.robot.jago.element.Detonator;
import eecs.robot.jago.element.Wall;

public class DetonatorSimulation extends SimBox {
  private static final long serialVersionUID = 8202137619578793110L;
  protected final int DET_SIZE = 30;
  protected Detonator[] detonator;
  protected boolean running = false;
  protected int detCount = 3;

  public DetonatorSimulation(boolean randomLayout) {
    this("Detonator Disarm Array", randomLayout);
  }

  public DetonatorSimulation(String title, boolean randomLayout) {
    super(780, 580);
    setTitle(title);
    setBackgroundTile("/eecs/images/gray_checkered.gif");
    setupSimulation(randomLayout);
  }

  /**
   * Starts the detonators in the simulation.
   */
  public void startDetonators() {
    if ((detonator[0] != null) && !running) {
      for (int i = 0; i < detCount; i++) {
        detonator[i].start(4, 0);
      }
      running = true;
    }
  }

  protected void setupSimulation(boolean randomLayout) {
    setBoundingBox();
    detonator = new Detonator[detCount];
    layDetonatorArray(randomLayout);
  }

  private final void setBoundingBox() {
    // Left Side wall
    add(new Wall(7, getSize().height - 42), 0, 8);
    // Top Wall
    add(new Wall(getSize().width - 7, 7), 0, 0);
    // Bottom Wall
    add(new Wall(getSize().width - 7, 7), 0, getSize().height - 33);
    // Right side wall
    add(new Wall(7, getSize().height - 42), getSize().width - 14, 8);
  }

  private final void layDetonatorArray(boolean isLayoutRandom) {
    final int NUM_COLUMNS = detCount;
    int startingX = 130;
    int offset = ((this.getSize().width) - 300) / (detCount - 1);
    int upperBound = 130;
    int lowerBound = (this.getSize().height) - 190;
    int[] detType = { Detonator.LEFT, Detonator.RIGHT, Detonator.BOTTOM, Detonator.TOP };
    int x = startingX;
    int y = 0;
    int type = Detonator.LEFT;
    Random r = new Random();
    int[] yCoords = { this.getSize().height / 2, lowerBound, upperBound, this.getSize().height / 3 * 2 };
    for (int i = 0; i < NUM_COLUMNS; i++) {
      if (isLayoutRandom) {
        y = (r.nextInt(lowerBound - upperBound)) + upperBound;
        type = detType[r.nextInt(detType.length)];
      }
      else {
        y = yCoords[i];
        type = detType[i];
      }
      detonator[i] = new Detonator(type, DET_SIZE);
      add(detonator[i], x, y);
      x = x + offset;
    }
  }
}