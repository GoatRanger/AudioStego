/*
 * HITSensorValuesDemo.java
 *
 * Created on May 22, 2002, 1:32 PM
 */
package eecs.applet.example;

import jago.element.*;


/**
 * A small class to test the function of the HIT Sensor.
 *
 * @author Karl A. Gossett
 * @version 1.0
 */
public class HITSensorValuesDemo extends eecs.applet.AppletBase
{
    public int getHITSensor()
    {
        checkRobot("getHITSensor");

        return robot.getSensorInput(LegoRobot.RCX_3);
    }

    /**
     * Overrides createRobot in RobotGui to create a standard Lego
     * robot with a touch, light, and differential-light sensor.
     *
     * @param x DOCUMENT ME!
     * @param y DOCUMENT ME!
     * @param angle DOCUMENT ME!
     */
    public void createRobot(int x, int y, int angle)
    {
        if (robot != null)
        {
            showError(
                "Robot already exists.  Cannot create a new one.");
            System.exit(0);
        }

        robot = new jago.element.LegoRobot();

        StandardSensor lightSensor =
            StandardSensorFactory.createSensor(RobotSensors.LIGHT,
                robot);
        StandardSensor hitSensor =
            StandardSensorFactory.createSensor(RobotSensors.HIT, robot);
        StandardSensor bumperSensor =
            StandardSensorFactory.createSensor(RobotSensors.STANDARD_FRONT_BUMPER,
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

    /**
     * Undocumented
     */
    public void init()
    {
        getContentPane().add(sim = new jago.SimEngine());
        sim.setPreferredSize(new java.awt.Dimension(500, 550));

        java.awt.Image icon =
            jago.ImageLoader.getImage(this,
                "/jago/images/white.gif");
        frame = sim;

        if (icon != null)
        {
            sim.setBackgroundTile(icon);
            repaint();
        }
    }

    /**
     * DOCUMENT ME!
     */
    public void run()
    {
        initSimulation();
        createRobot(300, 450, 0);
        pause(1000);
        setPower(10);
        showMessage("About to display values from hit sensor...");

        int distance = 3000;
        int oldReading = -100;

        while (distance > 500)
        {
            int counter = 0;

            while (counter < 360)
            {
                int reading = getHITSensor();

                if (oldReading != reading)
                {
                    printLine("Turned to " + counter +
                        " degrees, reading is " + reading);
                    oldReading = reading;
                }

                pivotLeft(5);
                counter += 5;
            }

            printLine(
                "------------- Moving Toward Source -------------------");

            while (getHITSensor() > 2)
            {
                pivotRight(3);
            }

            while (getHITSensor() < -2)
            {
                pivotLeft(3);
            }

            goForward(500);
        }

        halt();
        showMessage("Test Complete.");
    }

    private void initSimulation()
    {
        String[] options = { "One", "Two", "Three", "Four" };
        int lights =
            chooseButton("Select number of light sources", options) +
            1;
        int x;
        int y;

        for (int src = lights; src > 0; src--)
        {
            int intensity = 100;

            do
            {
                intensity =
                    getInt(
                        "Enter an intensity (0-100) for light source #" +
                        ((lights + 1) - src));
            }
            while ((intensity < 0) || (intensity > 100));

            switch (src)
            {
                case 2:
                    x = 100;
                    y = 150;

                    break;

                case 3:
                    x = 450;
                    y = 90;

                    break;

                case 4:
                    x = 350;
                    y = 500;

                    break;

                default:
                    x = 300;
                    y = 50;

                    break; // Includes src=1
            }

            sim.add(new jago.element.BrightLight(intensity), x, y);
        }
    }
}
