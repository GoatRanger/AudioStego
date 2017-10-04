/*
 * Robot.java
 *
 * Created on December 16, 2001, 12:38 PM
 */
package eecs.soar;

import jago.*;

import jago.element.*;


/**
 * An instance version of the original RobotGuiBase.  Although
 * functionally identical, this version uses only instance methods
 * to eliminate some difficulty with JNI and integration with the
 * Soar architecture.
 *
 * @author Karl A. Gossett
 * @version 0.1
 */
public class Robot extends eecs.RobotGuiBase
{
    /*********************************************************************/

    // static initializer
    static
    {
        sim = new SimBox();
        setSimulationTitle(DEFAULT_TITLE);

        // Bypasses the RobotGuiBase file checks
        // sim.setBackgroundImage("line.gif");
        sim.add(new Wall(5, 400), 35, 50);
        sim.add(new ColoredLine(5, 400, ReflectiveColors.BLACK), 400,
            50);

        // The follow should be in every GUI 
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will
                     // appear in the sim box

        sim.setVisible(true);

        // Ensures that the simulation does not have a robot
        robot = null;
    }
     // end static initializer

    public int getLight()
    {
        return super.getReflectanceSensor();
    }

    public int getTouch()
    {
        return super.getTouchSensor();
    }

    public void backward()
    {
        super.goBackward();
    }

    public void backward(int val)
    {
        super.goBackward(val);
    }

    public void forward()
    {
        super.goForward();
    }

    public void forward(int val)
    {
        super.goForward(val);
    }

    public void initRobot(int x, int y, int head)
    {
        //setRealismLevel(REALISTIC);
        super.createRobot(x, y, head);
        robot.setPower(8);
    }

    public void left(int val)
    {
        super.pivotLeft(val);
    }

    public void right(int val)
    {
        super.pivotRight(val);
    }

    public void stop()
    {
        super.halt();
    }
}
