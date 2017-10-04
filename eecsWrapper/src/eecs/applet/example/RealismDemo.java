/*
 * RealismDemo.java
 *
 * Created on August 21, 2002, 3:31 PM
 */
package eecs.applet.example;

import jago.element.*;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public class RealismDemo extends eecs.applet.AppletBase
{
    /**
     * DOCUMENT ME!
     */
    public void run()
    {
        initSimulation();

        //sim.setDebugging(true);
        printLine(
            "This program demonstrates some of the differences between" +
            " the realism settings of the simulation.");

        int btn =
            chooseButton(
                "Would you like to view the IDEALISTIC demo, " +
                "or skip it?", "View", "Skip");

        if (btn == BUTTON1)
        {
            clearOutput();
            printLine(
                "This mode is demonstrates the idealistic mode.  It is " +
                "set using:");
            printCode("    runIdealisticSim();");
            printLine(
                "This setting is also the default for the simulation.");
            printLine(
                "In this mode, you will see that the robot will drive " +
                "perfectly straight.  Using the idealistic setting also" +
                " makes the light sensor detect the lines perfectly--so" +
                " the black line will return the color BLACK.");
            showMessage("Press OK when you are ready to begin.");
            createRobot(50, 300, 90);
            setPower(10);
            runIdealisticSim();
            goForward();

            int reading;

            while ((reading = getReflectanceSensor()) != BLACK)
            {
                ;
            }

            halt();

            printLine();
            printLine("The final reading from the light sensor was " +
                reading);
            printLine("(All white is 50, all black is about 29-30)");

            pivotLeft(90);
            goForward(1000);
            pivotLeft(360);
            showMessage(
                "Press OK when you are ready to move into the next demo");
            destroyRobot();
        }

        btn = chooseButton(
                "Would you like to view the REALISTIC demo, " +
                "or skip it?", "View", "Skip");

        if (btn == BUTTON1)
        {
            clearOutput();
            printLine(
                "The second mode is REALISTIC.  It is set using");
            printCode("    runRealisticSim();");
            printLine(
                "In this mode, you will see that the robot does not drive " +
                "perfectly straight.  If you run the demo several times," +
                "you will notice that it is not consistent, either--the " +
                "robot randomly generates performance characteristics " +
                "so it may drift left or right, move slightly slower or " +
                "faster for a given power setting, and turn slightly " +
                "more or less for a given pivot angle." +
                "  In this mode, the light sensor detects in a much more " +
                "realistic manner, so it might detect a \"reflection\" " +
                "between black and white as it starts to cross over the " +
                "line.");
            printLine("The final command issued is ");
            printCode("    pivotLeft(360);");
            printLine(
                "but you will notice that the robot seldom turns " +
                "exactly 360 degrees.");
            showMessage("Press OK to begin");
            createRobot(50, 300, 90);
            setPower(10);
            runRealisticSim();
            goForward();

            int reading;

            while ((reading = getReflectanceSensor()) > 40)
            {
                ;
            }

            halt();
            pause(1200);
            printLine();
            printLine("The final reading from the light sensor was " +
                reading);
            printLine("(All white is 50, all black is about 29-30)");

            pivotLeft(90);
            goForward(1000);
            pivotLeft(360);
            showMessage("Press OK when you are ready to move on");
            destroyRobot();
        }

        btn = chooseButton(
                "Would you like to view the Fixed REALISTIC demo, " +
                "or skip it?", "View", "Skip");

        if (btn == BUTTON1)
        {
            clearOutput();
            printLine(
                "This demo shows the fixed-realistic mode.  It is set " +
                "using");
            printCode("    runFixedRealisticSim();");
            printLine(
                "This mode is identical to the realistic mode, except " +
                "in this mode you will see that no matter how many " +
                "times you run the demo, the robot's performance " +
                "is consistent--it will always drift to the right, " +
                "will always pivot better right than left, and will " +
                "always move at nearly maximum speed (speed is " +
                "determined by the battery power, which is also " +
                "variable in normal realistic mode)." +
                "  This mode is primarily useful during testing, " +
                "so you can test a robot with realistic " +
                "characteristics, but with consistency between runs.");
            showMessage("Press OK when you are ready to begin");
            createRobot(50, 305, 90);
            setPower(10);
            runFixedRealisticSim();
            goForward();

            int reading;

            while ((reading = getReflectanceSensor()) > 40)
            {
                ;
            }

            halt();

            printLine();
            printLine("The final reading from the light sensor was " +
                reading);
            printLine("(All white is 50, all black is about 29-30)");

            pivotLeft(90);
            goForward(1000);
            pivotLeft(360);
            showMessage("Press OK when you are ready to move on");
            destroyRobot();
        }

        showMessage("That is the end of the realism demo.");
    }

    void initSimulation()
    {
        Message msg =
            new Message("Demonstration of Realism", 30,
                Message.COLOR_RED);
        msg.setDuration(10);
        sim.add(msg, 40, 40);
        sim.add(new ColoredLine(400, 10, ReflectiveColors.BLACK), 50,
            290);
        sim.add(new ColoredLine(400, 10, ReflectiveColors.BLACK), 50,
            345);
        sim.add(new ColoredLine(10, 65, ReflectiveColors.BLACK), 450,
            290);
    }
}
