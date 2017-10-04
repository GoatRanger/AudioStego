package eecs.applet.example;


/*
 * DrawingRobotDemo.java
 *
 * Created on August 1, 2002, 9:53 AM
 */

/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public class DrawingRobotDemo extends eecs.applet.AppletBase
{
    public void run()
    {
        showMessage("Ready to Start");

        int realism =
            chooseButton("Realism Level", "Idealistic", "Realistic");

        if (realism == BUTTON2)
        {
            runRealisticSim();
            printLine("Using REALISTIC Mode");
        }

        createRobot(100, 250, 90);
        setPower(10);
        printLine("DRAWING ROBOT");
        printLine("-------------");
        printLine(
            "This robot will draw some lines around the screen.");
        printLine("The commands:");
        printCode("  drawTrails(true)");
        printCode("  goForward(1000)");
        printLine();
        pause(1000);
        drawTrails(true);
        goForward(1000);

        printCode("  drawTrails(false)");
        printCode("  goForward(1000)");
        printLine();
        pause(1000);
        drawTrails(false);
        goForward(1000);

        printCode("  drawTrails(true)");
        printCode("  pivotLeft(35)");
        printCode("  goForward(1000)");
        printLine();
        pause(1000);
        drawTrails(true);
        pivotLeft(35);
        goForward(1000);

        printCode("  drawTrails(false)");
        printCode("  pivotLeft(55)");
        printCode("  goForward(1000)");
        printLine();
        pause(1000);
        drawTrails(false);
        pivotLeft(55);
        goForward(1000);

        printCode("  drawTrails(true)");
        printCode("  setPower(2)");
        printCode("  goForward(1000)");
        printLine();
        pause(1000);
        drawTrails(true);
        setPower(2);
        goForward(2000);

        printCode("  drawTrails(false)");
        printCode("  goForward(2000)");
        printLine();
        pause(1000);
        drawTrails(false);
        goForward(2000);

        showMessage("This completes the Drawing demo");
    }
}
