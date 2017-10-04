/*
 * FinalProject.java
 *
 * Created on February 2, 2002, 6:07 PM
 */
package eecs.instructor.gossett;

import jago.*;

import jago.element.*;


/**
 *
 * @author  Karl A. Gossett
 * @version 0.1
 */
public class FinalProject022 extends eecs.RobotGuiBase {

    private static StandardRobot robbie;

    static {
        sim = new SimBox();

        //sim.setBackgroundImage("/eecs/instructor/gossett/images/floor1.gif");
        sim.setSimTitle("Embassy Agent Detection Simulation");
        sim.add(new BrightLight(100), 240, 60);

        // The following should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will

        // appear in the sim box
        sim.setVisible(true);
        robot = null;
        runRealisticSim();

        // Add the dummy robot
        robbie = new StandardRobot();
        robbie.setRealismLevel(REALISTIC);
        robbie.setInitialHeading(180);
        sim.add(robbie, 220, 15);
    } // end static initializer

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
                                              RobotSensors.STANDARD_FRONT_BUMPER, 
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

    public static int getLightSensor() {
        checkRobot("getLightSensor");

        int value = robot.getSensorInput(LegoRobot.RCX_1);

        if (value < 5) {
            value = ReflectiveColors.BLACK;
        } else if (value >= 85 && value <= 92) {
            value = ReflectiveColors.RED;
        } else if (value > 95) {
            value = ReflectiveColors.WHITE;
        }

        return value;
    }

    public static int getHITSensor() {
        checkRobot("getHITSensor");

        return robot.getSensorInput(LegoRobot.RCX_3);
    }

    public static void sendDataIR(int[] buffer) {

        int total = 0;

        for (int i = 0; i < buffer.length; i++) {
            total += buffer[i];
        }

        showMessage("Robbie Response: " + buffer.length + " bins read");
        robbie.goBackward(1600);
        robbie.pivotLeft(180);
        robbie.setPower(10);
        robbie.goForward(2000);
    }
}