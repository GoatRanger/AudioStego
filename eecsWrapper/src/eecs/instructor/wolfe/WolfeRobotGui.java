/*
 * WolfeRobotGui.java
 *
 * Created on December 16, 2001, 12:38 PM
 */
package eecs.instructor.wolfe;

/**
 *
 * @author  Doug Wolfe
 * @version 0.1
 */
public class WolfeRobotGui
    extends eecs.RobotGuiBase {

    /*********************************************************************/

    // static initializer
    static {
        sim = new eecs.jago.instructor.wolfe.CentralAreaSimulation();

        // Bypasses the RobotGuiBase file checks
        //sim.setBackgroundImage(DEFAULT_IMAGE);
        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will
                     // appear in the sim box
        sim.setVisible(true);

        // Ensures that the simulation does not have a robot
        robot = null;
    } // end static initializer
}