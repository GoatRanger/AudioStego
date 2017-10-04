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
 * FDWFinal.java
 *
 * Created on June 25, 2002, 9:21 AM
 */
package eecs.simulation;

import jago.*;

import jago.element.*;


/**
 * The base final project class used for the Fall Semester, 2002.
 * Provides a black path with 3 forks and a pushable barrel at the
 * end of each fork. Also has a gray border, and a small gray box
 * with a lightsource in it.  Designed to allow demonstration of
 * skills using iteration, selection, and the use of a touch, light,
 * and differential-light sensor.
 *
 * @author Karl A. Gossett
 * @version %I%
 */
public class FinalProject extends eecs.RobotGuiBase
{
    static
    {
        sim = new SimBox();
        sim.setBackgroundImage("ArcPath.gif");
        setSimulationTitle("IT105/155 Final Project");
        sim.add(new ColoredLine(80, 80, ColoredLine.MED_GRAY), 260, 35);
        sim.add(new BrightLight(100), 300, 50);
        sim.add(new Barrel(12), 77, 425);
        sim.add(new Barrel(12), 650, 425);
        sim.add(new Barrel(12), 358, 420);

        // The following should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will

        // appear in the sim box
        sim.setVisible(true);
        robot = null;
        runRealisticSim();
    }
     // end static initializer

    /**
     * Undocumented
     *
     * @return Empty
     */
    public static int getDifferentialSensor()
    {
        return robot.getSensorInput(StandardRobot.RCX_3);
    }

    /**
     * Undocumented
     *
     * @param x Empty
     * @param y Empty
     * @param angle Empty
     */
    public static void createRobot(int x, int y, int angle)
    {
        if (robot != null)
        {
            showError(
                "Robot already exists.  Cannot create a new one.");
            System.exit(0);
        }

        robot = new LegoRobot();

        StandardSensor lightSensor =
            StandardSensorFactory.createSensor(RobotSensors.LIGHT,
                robot);
        StandardSensor hitSensor =
            StandardSensorFactory.createSensor(RobotSensors.HIT, robot);
        StandardSensor bumperSensor =
            StandardSensorFactory.createSensor(RobotSensors.INVERTED_FRONT_BUMPER,
                robot);
        robot.addSensor(LegoRobot.RCX_2, bumperSensor);
        robot.addSensor(LegoRobot.RCX_3, hitSensor);
        robot.addSensor(LegoRobot.RCX_1, lightSensor);
        robot.setPower(5);
        robot.setInitialHeading(angle);
        robot.setRealismLevel(realism);
        sim.add(robot, x, y);

        // Pause to allow updates prior to attempting to use light sensor
        // Trying to use light sensor prior to it being ready may result in
        // an infinite loop
        pause(500);
    }
}
