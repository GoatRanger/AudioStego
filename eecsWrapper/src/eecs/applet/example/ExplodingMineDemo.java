/*
 * ExplodingMineDemo.java
 *
 * Created on December 18, 2002, 2:02 PM
 */
package eecs.applet.example;

import jago.*;

import jago.element.*;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public class ExplodingMineDemo extends eecs.applet.AppletBase
{
    eecs.jago.instructor.flowers.Mine mine;

    /**
     * Adds a robot to the simulation at the specified coordinates
     * and initial heading.  This method creates a
     * <code>LegoRobot</code>, which by default has a standard Lego
     * light sensor (see {@link
     * jago.element.RobotSensors#LIGHT}) and a standard bumper
     * (see {@link jago.element.RobotSensors#TOUCH}).  The
     * light sensor is connected to the RCX input 1, (RCX_1) and the
     * touch sensor is connected to the RCX input 2 (RCX_2).
     *
     * @param x The x coordinate, with the upper left being 0.
     * @param y The y coordinate, with the upper left being 0.
     * @param angle The initial direction; straight up is a heading
     *        of 0.
     */
    public void createRobot(int x, int y, int angle)
    {
        checkAngle("createRobot", angle);

        if (robot != null)
        {
            showError(
                "Robot already exists.  Cannot create a new one.");
            System.exit(0);
        }

        robot = new eecs.jago.instructor.flowers.DozerBot();
        robot.setPower(5);
        robot.setInitialHeading(angle);

        if (realism != IDEALISTIC)
        {
            robot.setRealismLevel(realism);
        }

        sim.add(robot, x, y);
    }

    /**
     * Undocumented
     */
    public void init()
    {
        getContentPane().add(sim = new SimEngine());
        sim.setPreferredSize(new java.awt.Dimension(500, 400));

        java.awt.Image icon =
            ImageLoader.getImage(this, "/jago/images/white.gif");
        frame = sim;

        if (icon != null)
        {
            sim.setBackgroundTile(icon);
            repaint();
        }

        mine = new eecs.jago.instructor.flowers.Mine();
        sim.add(mine, 200, 200);
        sim.add(new ColoredLine(10, 100, ReflectiveColors.MED_GRAY),
            300, 150);
    }

    public void run()
    {
        createRobot(50, 200, 90);
        printLine(
            "This robot will drive forward over the mine to its front.");
        printLine("The mine will explode on contact with the dozer.");
        printLine(
            "If the mine is struck by any robot other than a DozerBot");
        printLine(
            "the robot will be destroyed (if it can be destroyed).");
        printLine(
            "--------------------------------------------------------");
        pause(2000);
        printLine("Driving...");
        goForward(3000);
    }
}
