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


package eecs.instructor.berlin;

import jago.*;

import jago.element.*;

import java.util.Random;


/**
 * @version %I%
 * @author LTC Jacob Berlin
 */
public class FinalProjectBerlin
    extends eecs.RobotGuiBase {

    static {
        sim = new SimBox();
        sim.setBackgroundImage("berlin_final.gif");
        setSimulationTitle("IT105 Final Project: It's Almost Over!");

        //  FOB / Supply Point Objects:
        sim.add(new ColoredLine(60, 60, MED_GRAY), 
                105, 
                420);
        sim.add(new BrightLight(100), 
                135, 
                450);
        sim.add(new Barrel(12), 
                75, 
                275);
        sim.add(new Wall(125, 10), 
                260, 
                375);

        //  ODA Objects
        sim.add(new Barrel(12), 
                132, 
                75);
        sim.add(new Barrel(12), 
                264, 
                75);
        sim.add(new Barrel(12), 
                402, 
                75);

        //  Decide which ODA to cut off:
        Random r = new Random();
        int laneToOpen = r.nextInt(3);

        if (laneToOpen == 0) {
            sim.add(new ColoredLine(30, 25, WHITE), 
                    128, 
                    161);
        }
         else if (laneToOpen == 1) {
            sim.add(new ColoredLine(30, 25, WHITE), 
                    262, 
                    161);
        }
         else {
            sim.add(new ColoredLine(30, 25, WHITE), 
                    398, 
                    161);
        }

        // The following should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will

        // appear in the sim box
        sim.setVisible(true);
        robot = null;
        runRealisticSim();
    } // end static initializer

    /**
     * Undocumented
     * 
     * @return Empty
     */
    public static int getDifferentialSensor() {

        return robot.getSensorInput(StandardRobot.RCX_3);
    }

    /**
     * Undocumented
     * 
     * @param x Empty
     * @param y Empty
     * @param angle Empty
     */
    public static void createRobot(int x, int y, int angle) {

        if (robot != null) {
            showError("Robot already exists.  Cannot create a new one.");
            System.exit(0);
        }

        robot = new LegoRobot();

        StandardSensor lightSensor = StandardSensorFactory.createSensor(
                                             RobotSensors.LIGHT, robot);
        StandardSensor hitSensor = StandardSensorFactory.createSensor(
                                           RobotSensors.HIT, robot);
        StandardSensor bumperSensor = StandardSensorFactory.createSensor(
                                              RobotSensors.INVERTED_FRONT_BUMPER, 
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