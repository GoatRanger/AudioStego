/* Copyright (C) 2002  USMA
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
/*
 * PushTest.java
 *
 * Created on June 17, 2002, 12:07 PM
 */
package eecs.applet.example;

import jago.ImageLoader;
import jago.SimEngine;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 * @version 0.1
 */
public class PushDemo extends eecs.applet.AppletBase
{
    /**
     * Undocumented
     *
     * @param x Empty
     * @param y Empty
     * @param angle Empty
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

        robot = new jago.element.StandardRobot();
        robot.addSensor(jago.element.Monitor.RCX_2,
            jago.element.Sensor.TOUCH);
        robot.setPower(5);
        robot.setInitialHeading(angle);

        if (realism != IDEALISTIC)
        {
            robot.setRealismLevel(realism);
        }

        sim.add(robot, x, y);
    }

    /**
     * The main method
     */
    public void run()
    {
        initSimulation();
        setSimulationTitle("Demonstration of Pushing Objects");

        int realism =
            chooseButton("Realism Level", "Idealistic", "Realistic");

        if (realism == BUTTON2)
        {
            runRealisticSim();
            printLine("Using REALISTIC Mode");
        }

        //sim.setDebugging(true);
        printLine("This is a demonstration of pushable objects.");
        printLine(
            "The \"barrels\" should be pushed out of the way of the robot");
        printLine(
            "Although not fully realistic, they should move with " +
            "at least a minimal representation of true physics.");
        printLine(
            "This also shows some of the limitations of the touch " +
            "sensor.  In particular, it is possible for the sensor to " +
            "report OFF even though the robot appears pressed against " +
            "an object, or it just finished pushing it.  Although " +
            "this is partly a limitation in the simulation (if " +
            "you wait for any length of time without moving, the " +
            "sensor will surely report OFF), it is also a limitation " +
            "of the actual sensor, since it is possible for the sensor " +
            "to lose pressure to the point where it turns off. This " +
            "usually happens when an object or the robot slides, but " +
            "can also occur due to the design of the touch sensor.");
        printLine("--------------------");
        printLine();
        printLine(
            "The robot will drive until it runs into something...");
        createRobot(260, 450, 0);
        pause(1000);
        setPower(2);
        goForward();

        while (getTouchSensor() == OFF)
        {
        }

        halt();
        printLine("Hit a barrel");

        int counter = 0;
        printLine("Pushing until barrel isn't on the bumper...");
        goForward();

        while ((getTouchSensor() == ON) && (counter++ < 100000))
        {
        }

        halt();

        if (counter < 100000)
        {
            printLine("Touch sensor turned OFF...");
        }
        else
        {
            printLine(
                "Went farther than expected, but still pushing something.");
        }

        printLine("Going forward for 2000ms...");
        goForward(2000);

        String sensor;

        if (getTouchSensor() == ON)
        {
            sensor = "ON";
        }
        else
        {
            sensor = "OFF";
        }

        printLine("My bumper is " + sensor);
        pause(1000);

        printLine("Going forward for another 2000ms...");
        goForward(2000);

        if (getTouchSensor() == ON)
        {
            sensor = "ON";
        }
        else
        {
            sensor = "OFF";
        }

        printLine("My bumper is " + sensor);
        printLine();
        printLine();
        printLine(
            "Note: sometimes the robot will stop pushing because the " +
            "touch sensor becomes \"off\" while pushing (due to " +
            "the way the simulation works, but it also occurs sometimes " +
            "in the real robot).  Most times it will push all three " +
            "barrels.  This also demonstrates that using the touch " +
            "sensor isn't an ideal way to determine movement.");
        pause(1000);
        printLine("Going forward for another 4000ms...");
        goForward(4000);
    }

    //------------------------------------------------------------------------------

    /* Methods for the demonstration, if you modify below this comment, the
     * demo might not work!
     */

    //-----------------------------------------------------------------------------

    /**
     * Undocumented
     */
    private void initSimulation()
    {
    	sim.add(new jago.element.Barrel(12), 250, 375);
        sim.add(new jago.element.Barrel(12), 290, 325);
        sim.add(new jago.element.Barrel(12), 285, 250);
        sim.add(new jago.element.ColoredLine(80, 80, MED_GRAY), 50, 50);
        sim.add(new jago.element.ColoredLine(80, 80, BLACK), 250, 50);

        //runRealisticSim();
    }
    
	public void init()
	{
		getContentPane().add(sim = new SimEngine());
		sim.setPreferredSize(new java.awt.Dimension(500, 500));

		java.awt.Image icon =
			ImageLoader.getImage(this, "/jago/images/white.gif");
		frame = sim;

		if (icon != null)
		{
			sim.setBackgroundTile(icon);
			repaint();
		}
	}
}
