/*
 * CollisionTest.java
 *
 * Created on May 29, 2002, 10:07 AM
 */
package eecs.applet.example;

import jago.element.*;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 * @version 1.0
 */
public class BumperDemo extends eecs.applet.AppletBase
{
    /**
     * DOCUMENT ME!
     */
    public void run()
    {
        initSimulation();
        createRobot(200, 150, 180);

        int realism =
            chooseButton("Realism Level", "Idealistic", "Realistic");

        if (realism == BUTTON2)
        {
            runRealisticSim();
            setPower(10);
            printLine("Using REALISTIC Mode");
        }

        printLine(
            "This program verifies the operation of the touch sensor, " +
            "and can also demonstrate the difference in operation of " +
            "the robot between IDEALISTIC and REALISTIC modes.  In " +
            "IDEALISTIC mode, the robot will stop immediately upon " +
            "hitting the wall (and the command terminates); in " +
            "REALISTIC mode, the robot will continue " +
            "to attempt to move until the command time expires.");

        showMessage("Ready to Start--going forward for 7000ms");

        goForward(7000);
        showMessage("Command finished executing.");
        showMessage("Ready to start functionality demo");
        destroyRobot();
        createRobot(200, 150, 180);
        printLine();
        printLine("--------------------------");
        printLine("Going forward until touch sensor is ON...");
        goForward();

        while (getTouchSensor() == OFF)
        {
        }

        halt();

        int temp = getTouchSensor();
        printLine("Hit the wall and halted.");

        int counter = 0;

        if (temp == ON)
        {
            print("Touch sensor is still ON.");

            while ((getTouchSensor() == temp) && (counter++ < 100))
            {
                print(".");
            }
        }

        printLine();

        if (counter < 100)
        {
            printLine(
                "Touch sensor is now OFF, but robot didn't move.");
        }
        else
        {
            printLine("The touch sensor remained on.");
        }

        printLine("Operational notes:");

        if (realism == BUTTON2)
        {
            printRealisticInfo();
            printIdealisticInfo();
        }
        else
        {
            printIdealisticInfo();
            printRealisticInfo();
        }
    }

    void initSimulation()
    {
        sim.add(new Wall(300, 10), 100, 300);
    }

    void printIdealisticInfo()
    {
        printLine(
            " - In idealistic mode (default), the touch sensor will" +
            " report ON until the robot backs up or attempts to" +
            " turn.  Although this seems more realistic than the" +
            " performance described above, the real sensor will" +
            " sometimes turn off prematurely due to variations in" +
            " the quality of the sensor, as well as objects sliding" +
            " and allowing the sensor to turn off.");
    }

    void printRealisticInfo()
    {
        printLine(
            " - In realistic mode, the sensor generally will not" +
            " stay ON unless the robot is moving.  Thus, the only" +
            " way to use a touch sensor effectively in this mode" +
            " is to use code similar to the following:");
        printCode("        goForward();");
        printCode(
            "        while (getTouchSensor() == OFF {/*just wait*/}");
        printCode("        halt();");
        printLine(
            "   This small loop will cause the robot to drive until it" +
            " runs into something.  Note that the condition of the" +
            " loop is the value of the sensor.  Checking for the" +
            " value of the sensor in any other way may not provide" +
            " consistent results.");
    }
}
