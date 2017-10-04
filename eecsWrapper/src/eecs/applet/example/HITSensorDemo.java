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
 * HITSensorTest.java
 *
 * Created on May 22, 2002, 1:32 PM
 */
package eecs.applet.example;

/**
 * A small class to test the function of the HIT Sensor.
 *
 * @author Karl A. Gossett
 * @version 2.0
 */
public class HITSensorDemo extends eecs.applet.AppletBase
{
    /**
     * Undocumented
     */
    public void addHITSensor()
    {
        jago.element.StandardSensor hitSensor =
            jago.element.StandardSensorFactory.createSensor(jago.element.RobotSensors.HIT,
                robot);
        robot.addSensor(jago.element.LegoRobot.RCX_3, hitSensor);
    }

    /**
     * DOCUMENT ME!
     */
    public void run()
    {
        initSimulation();
        createRobot(300, 300, 0);
        addHITSensor();
        setPower(4);

        int realism =
            chooseButton("Realism Level", "Idealistic", "Realistic");

        if (realism == BUTTON2)
        {
            runRealisticSim();
            setPower(10);
            printLine("Using REALISTIC Mode");
        }

        pause(1000);

        int counter = 0;
        int numReadings = 36;
        int[] readings = new int[numReadings];
        printLine("Taking Readings, every " + (360 / numReadings) +
            " degrees...");

        do
        {
            int correctAngle = 0;
            int angle = 0;
            print("Moved: " + (counter * 800) + ", readings: ");

            while (angle < 360)
            {
                readings[correctAngle++] = getHITSensor();
                print(readings[correctAngle - 1] + ", ");
                pivotLeft(360 / numReadings);
                angle += (360 / numReadings);
            }

            printLine();
            pause(1000);
            goForward(800);
            counter++;
        }
        while (counter < 10);

        halt();
        showMessage("Finished");
    }

    /**
     * Undocumented
     */
    private void initSimulation()
    {
        sim.add(new jago.element.BrightLight(100), 300, 50);
    }
}
