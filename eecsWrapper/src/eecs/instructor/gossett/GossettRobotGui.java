/*
 * GossettRobotGUI.java
 *
 * Created on December 10, 2001, 12:51 PM
 */
package eecs.instructor.gossett;

import eecs.jago.instructor.gossett.*;


/**
 *
 * @author  Karl A. Gossett
 * @version 0.1
 */
public class GossettRobotGui
    extends eecs.RobotGuiBase {

    static {
        sim = new eecs.jago.instructor.gossett.IterationHW();

        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will

        // appear in the sim box
        sim.setVisible(true);
        robot = null;
    } // end static initializer

    public static void resetSimulation() {
        ((IterationHW)sim).resetRoom();
        destroyRobot();
    }
}