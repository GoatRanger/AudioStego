/*
 * MovingDemo.java
 *
 * Created on August 5, 2002, 10:46 AM
 */
package eecs.applet.example;

/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public class MovingDemo extends eecs.applet.AppletBase
{
    /**
     * DOCUMENT ME!
     */
    public void run()
    {
        setBackgroundTile("blue_tile.gif");

        //sim.setDebugging(true);
        createRobot(72, 300, 90);

        int realism =
            chooseButton("Realism Level", "Idealistic", "Realistic");
        setPower(10);
        printLine("This program demonstrates the moving speed of " +
            "a robot.  The \"time\" specified is in simulation scale " +
            "so might take longer in real time, but ensures consistency " +
            "in the movement of the robot.");
        printLine();

        if (realism == BUTTON2)
        {
            runRealisticSim();
            printLine("Using REALISTIC Mode");
            printLine(
                "In this mode, power settings have very little effect " +
                "on speed, but do affect the power/torque of the motor " +
                "on the real robot.");
        }
        else
        {
            printLine("Using IDEALISTIC Mode");
            printLine(
                "In this mode, power settings will significantly change " +
                "how fast the robot moves.  In the more realistic " +
                "modes, power settings have a much smaller impact.");
        }

        printLine("------------------------");
        showMessage("Ready to Start--going forward for 2000ms, " +
            "in 1ms increments.");
        printCode("  goForward(1);  // Repeats 2000 times");
        printLine();

        for (int fwd = 0; fwd < 2000; fwd++)
        {
            goForward(1);

            if (fwd == 500)
            {
                printLine("Note that since the requested time to " +
                    "move is less than the frame rate of the " +
                    "simulation, the robot moves <b>abnormally</b> slow.");
            }
        }

        pause(1000);
        showMessage("Ready to Start--going forward for 2000ms, " +
            "in 50ms increments.");
        printCode("  goForward(50);  // Repeats 40 times");
        printLine();

        for (int fwd = 0; fwd < 40; fwd++)
        {
            goForward(50);

            if (fwd == 500)
            {
                printLine("Note that since the requested time to " +
                    "move is less than the frame rate of the " +
                    "simulation, the robot moves <b>abnormally</b> slow.");
            }
        }

        pause(1000);
        pivotLeft(180);
        showMessage("And returning, in 1000ms increments.");
        printCode("  goForward(100);  // Repeats 2 times");
        printLine();

        for (int fwd = 0; fwd < 2; fwd++)
        {
            goForward(1000);

            if (fwd == 0)
            {
                printLine(
                    "Now the movement rate is greater than the frame " +
                    "rate, so it will move at a more appropriate rate " +
                    "(Also note that the distance moved in 3 x forward " +
                    "1000 is not equal to 3000 x forward 1.)");
            }
        }

        showMessage("End of the movement demo.");
    }
}
