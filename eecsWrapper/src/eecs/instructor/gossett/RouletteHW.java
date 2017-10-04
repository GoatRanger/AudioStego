/*
 * RouletteHW.java
 *
 * Created on July 8, 2002, 9:29 PM
 */
package eecs.instructor.gossett;

import jago.*;

import eecs.sim.*;


/**
 *  A roulette simulation.  This class uses the simulation interface to create
 * a roulette table.  The functionality provided is only for visuals and
 * generating a random result from spinning the wheel; no scoring capability
 * is included.
 *
 * @author  Karl A. Gossett
 * @version 0.1
 */
public class RouletteHW
    extends eecs.RobotGuiBase
    implements eecs.sim.RouletteData {

    protected static boolean hasRun;
    protected static java.util.List chips;
    protected static eecs.sim.RouletteWheel wheel;

    static {
        sim = new SimBox();
        sim.setBackgroundImage("roulette_layout.gif");
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
    } // end static initializer

    /** Perform one spin of the roulette wheel. */
    public static void spin() {
        wheel.spin();
    }

    /** Determines what space (number) that the ball stopped in. */
    public static int getBallSpace() {

        return wheel.getNumber();
    }

    /** Determines the color of the space that the ball stopped in.  Values
     * returned will be one of RED_SPACE, BLACK_SPACE, or GREEN_SPACE.
     *
     * @return  The space color, from {@link eecs.sim.RouletteData} constants.
     */
    public static int getSpaceColor() {

        return wheel.getColor();
    }

    /** Places a bet on the board over the specified number.
     * 
     * @param value  The number to bet on.
     */
    public static void addChipNumber(int value) {

        RouletteChip chip = new RouletteChip();
        int xPos = (value - 1) % 3 * 44 + 290 + (int)(Math.random() * 7);
        int yPos = ((value - 1) / 3) * 35 + 53 + (int)(Math.random() * 5);
        sim.add(chip, xPos, yPos);
        chips.add(chip);
    }

    /** Adds a bet on the board on the outside black or red bet.
     *
     * @param color a <code>String</code> of either "red" or "black"
     */
    public static void addChipColor(String color) {

        RouletteChip chip = new RouletteChip();
        int yPos = 0;

        if (color.equalsIgnoreCase("red")) {
            yPos = 214 + (int)(Math.random() * 8);
        } else if (color.equalsIgnoreCase("black")) {
            yPos = 282 + (int)(Math.random() * 8);
        } else {
            System.out.println(
                    "You attempted to bet on " + color
                    + ".  You must bet on either red or black.");

            return;
        }

        int xPos = 218;
        sim.add(chip, xPos, yPos);
        chips.add(chip);
    }

    /** Removes all chips from the table */
    public static void clearTable() {

        java.util.Iterator iter = chips.iterator();

        while (iter.hasNext()) {

            RouletteChip chip = (RouletteChip)iter.next();
            sim.remove(chip);
        }

        chips.removeAll(chips);
    }
}