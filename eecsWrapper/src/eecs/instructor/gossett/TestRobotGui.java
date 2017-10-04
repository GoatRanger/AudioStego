/*
 * TestRobotGui.java
 *
 * Created on March 6, 2002, 10:12 PM
 */
package eecs.instructor.gossett;


/**
 *
 * @author  Karl Gossett
 */
public class TestRobotGui
    extends eecs.RobotGuiBase {

    static {
        sim = new eecs.jago.instructor.gossett.ExampleCases();

        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will
                     // appear in the sim box
        sim.setVisible(true);
        robot = null;
    } // end static initializer

    public static void goUntilLight() {
        robot.goForward();

        while (getReflectanceSensor() == OFF) {
            ;
        }

        robot.halt();
    }

    public static void goUntilTouch() {
        robot.goForward();

        while (getTouchSensor() == OFF) {
            ;
        }

        robot.halt();
    }
}