/*
 * Copyright (C) 2002 USMA This program is free software; you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the License,
 * or any later version. This program is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU
 * General Public License along with this program; if not, write to the Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
/*
 * RouletteHW.java Created on July 8, 2002, 9:29 PM
 */
package eecs;

import java.awt.Image;
import java.io.IOException;
import eecs.sim.*;
import jago.*;


/**
 * A roulette simulation. This class uses the simulation interface to create a
 * roulette table. The functionality provided is only for visuals and generating
 * a random result from spinning the wheel; no scoring capability is included.
 * 
 * @author Karl A. Gossett
 * @version 0.1
 */
public class RouletteGui extends eecs.RobotGuiBase
    implements
      eecs.sim.RouletteData {
  
  protected static int[] colors =
  {
      RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE,
      BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE,
      BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE,
      RED_SPACE, BLACK_SPACE, RED_SPACE, RED_SPACE, BLACK_SPACE,
      RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE,
      RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE, 
      BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE,
      RED_SPACE
  };
  /** The chips that have been placed on the roulette table. */
  protected static java.util.List chips;
  /** Not used. */
  protected static boolean hasRun;
  
  protected static int testingSpace = -1;
  
  /** The wheel object. */
  protected static eecs.sim.RouletteWheel wheel;
  static {
    getProperties();
    if (!runningTests) {
      sim = new SimBox();
      Image bg = ImageLoader.getImage(String.class,
          "/eecs/images/roulette_layout.gif");
      sim.setBackgroundImage(bg);
      sim.setSimTitle("IT105 Roulette!");
      // The following should be in every GUI
      //  (unless a known, specific reason to omit)
      frame = sim; // so that all the windows from eecs.GuiBase will
      // appear in the sim box
      wheel = new eecs.sim.RouletteWheel();
      sim.add(wheel, 5, 55);
      sim.setVisible(true);
      robot = null;
      hasRun = false;
      chips = new java.util.ArrayList();
      testingSpace = -1;
    }
  }

  // end static initializer
  /**
   * Determines what space (number) that the ball stopped in.
   * 
   * @return Empty
   */
  public static int getBallSpace() {
    getProperties();
    if (runningTests) {
      int value = 0;
      try {
        if (testingSpace == -1) {
          String result = "0";
          result = testConsoleReader.readLine();
          value = Integer.parseInt(result);
          testingSpace = value;
        } else {
          value = testingSpace;
        }
      } catch (IOException e) {
        System.err
            .println("Error reading a BallSpace int from test file (console)."
                + "  Format in input file was incorrect.");
      }
      return value;
    } else {
      return wheel.getNumber();
    }
  }

  /**
   * Determines the color of the space that the ball stopped in. Values returned
   * will be one of RED_SPACE, BLACK_SPACE, or GREEN_SPACE.
   * 
   * @return The space color, from {@link eecs.sim.RouletteData}constants.
   */
  public static int getSpaceColor() {
    getProperties();
    if (runningTests) {
      if (testingSpace == -1) {
        getBallSpace();
      }
      if (testingSpace <= 0 || testingSpace > 36) {
        return GREEN_SPACE;
      }
      
      return colors[testingSpace-1];
    } else {
      return wheel.getColor();
    }
  }

  /**
   * Adds a bet on the board on the outside black or red bet.
   * 
   * @param color
   *          a <code>String</code> of either "red" or "black"
   */
  public static void addChipColor(String color) {
    getProperties();
    if (!runningTests) {
      RouletteChip chip = new RouletteChip();
      int yPos = 0;
      if (color.equalsIgnoreCase("red")) {
        yPos = 214 + (int) (Math.random() * 8);
      } else if (color.equalsIgnoreCase("black")) {
        yPos = 282 + (int) (Math.random() * 8);
      } else {
        System.out.println("You attempted to bet on " + color
            + ".  You must bet on either red or black.");
        return;
      }
      int xPos = 218;
      sim.add(chip, xPos, yPos);
      chips.add(chip);
    }
  }

  /**
   * Adds a bet marker for the column (2to1) bets.
   * 
   * @param choice
   *          <code>"left"</code>,<code>"middle"</code>, or
   *          <code>"right"</code>
   */
  public static void addChipColumn(String choice) {
    getProperties();
    if (!runningTests) {
      RouletteChip chip = new RouletteChip();
      int xPos = 0;
      if (choice.equalsIgnoreCase("left")) {
        xPos = 298 + (int) (Math.random() * 8);
      } else if (choice.equalsIgnoreCase("middle")) {
        xPos = 344 + (int) (Math.random() * 8);
      } else if (choice.equalsIgnoreCase("right")) {
        xPos = 392 + (int) (Math.random() * 8);
      } else {
        System.out.println("You attempted to bet on " + choice
            + ".  You must bet on either low or high.");
        return;
      }
      int yPos = 481;
      sim.add(chip, xPos, yPos);
      chips.add(chip);
    }
  }

  /**
   * Adds a chip to either the 1to18 (low) or 19to36 (high) board space.
   * 
   * @param choice
   *          Either <code>"LOW"</code> or <code>"HIGH"</code>
   */
  public static void addChipHighLow(String choice) {
    getProperties();
    if (!runningTests) {
      RouletteChip chip = new RouletteChip();
      int yPos = 0;
      if (choice.equalsIgnoreCase("low")) {
        yPos = 76 + (int) (Math.random() * 8);
      } else if (choice.equalsIgnoreCase("high")) {
        yPos = 427 + (int) (Math.random() * 8);
      } else {
        System.out.println("You attempted to bet on " + choice
            + ".  You must bet on either low or high.");
        return;
      }
      int xPos = 224;
      sim.add(chip, xPos, yPos);
      chips.add(chip);
    }
  }

  /**
   * Places a bet on the board over the specified number.
   * 
   * @param value
   *          The number to bet on.
   */
  public static void addChipNumber(int value) {
    getProperties();
    if (!runningTests) {
      RouletteChip chip = new RouletteChip();
      int xPos = ((value - 1) % 3 * 44) + 290 + (int) (Math.random() * 7);
      int yPos = (((value - 1) / 3) * 35) + 53 + (int) (Math.random() * 5);
      sim.add(chip, xPos, yPos);
      chips.add(chip);
    }
  }

  /**
   * Adds a chip to either the ODD or EVEN board space.
   * 
   * @param choice
   *          Either <code>"ODD"</code> or <code>"EVEN"</code>
   */
  public static void addChipOddEven(String choice) {
    getProperties();
    if (!runningTests) {
      RouletteChip chip = new RouletteChip();
      int yPos = 0;
      if (choice.equalsIgnoreCase("odd")) {
        yPos = 359 + (int) (Math.random() * 8);
      } else if (choice.equalsIgnoreCase("even")) {
        yPos = 147 + (int) (Math.random() * 8);
      } else {
        System.out.println("You attempted to bet on " + choice
            + ".  You must bet on either odd or even.");
        return;
      }
      int xPos = 224;
      sim.add(chip, xPos, yPos);
      chips.add(chip);
    }
  }

  /**
   * Adds a bet marker for the section bets (1st 12, 2nd 12, 3rd 12).
   * 
   * @param choice
   *          <code>"1st"</code>,<code>"2nd"</code>,<code>"3rd"</code>
   */
  public static void addChipSection(String choice) {
    getProperties();
    if (!runningTests) {
      RouletteChip chip = new RouletteChip();
      int yPos = 0;
      if (choice.equalsIgnoreCase("1st")) {
        yPos = 106 + (int) (Math.random() * 8);
      } else if (choice.equalsIgnoreCase("2nd")) {
        yPos = 248 + (int) (Math.random() * 8);
      } else if (choice.equalsIgnoreCase("3rd")) {
        yPos = 390 + (int) (Math.random() * 8);
      } else {
        System.out.println("You attempted to bet on " + choice
            + ".  You must bet on either low or high.");
        return;
      }
      int xPos = 262;
      sim.add(chip, xPos, yPos);
      chips.add(chip);
    }
  }

  /**
   * Removes all chips from the table
   */
  public static void clearTable() {
    getProperties();
    if (!runningTests) {
      java.util.Iterator iter = chips.iterator();
      while (iter.hasNext()) {
        RouletteChip chip = (RouletteChip) iter.next();
        sim.remove(chip);
      }
      chips.removeAll(chips);
    }
  }

  /**
   * Causes the simulation to wait for the specified amount of time.
   * 
   * @param ms
   *          The number of milliseconds to wait.
   */
  public static void pauseFor(int ms) {
    getProperties();
    if (!runningTests) {
      checkTime("pause", ms);
      /*
       * Previous iteration used the robot wait, but there really isn't any
       * reason to use it, and waiting here will ensure that the time waited is
       * more accurate. If returning to the robot's waitFor instead, check it
       * for accuracy--sometimes didn't wait the appropriate amount of time if
       * called publicly.
       */
      try {
        Thread.sleep(ms);
      } catch (InterruptedException e) {
      }
    }
  }

  /**
   * Perform one spin of the roulette wheel.
   */
  public static void spin() {
    getProperties();
    if (runningTests) {
      testingSpace = -1;
    } else {
      wheel.spin();
    }
  }
}