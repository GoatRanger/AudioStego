/*
 * IdealisticLightSensorDemo.java
 *
 * Created on August 1, 2002, 10:54 AM
 */
package eecs.applet.example;

/**
 * A demonstration of the Light Sensor, running in IDEALISTIC mode.
 *
 * @author Karl A. Gossett
 */
public class IdealisticLightSensorDemo extends eecs.applet.AppletBase
{
    /**
     * DOCUMENT ME!
     */
    public void run()
    {
        initSimulation();
        createRobot(350, 130, 270);
        printLine(
            "Demonstration of the Light Sensor, in idealistic (standard)" +
            " mode.");
        printLine(
            "Normally, the light sensor will report OFF in this mode.");
        printLine("  The current reading is " +
            numberToColor(getReflectanceSensor()));
        printLine();
        printLine(
            "So, the robot can drive while the light sensor shows OFF...");
        printLine();
        printCode("  goForward();");
        printCode("  while (getReflectanceSensor()==OFF) {}");
        printCode("  halt();");

        pause(1000);
        goForward();

        while (getReflectanceSensor() == OFF)
        {
        }

        halt();
        printLine();
        printLine("Then stop.");
        printLine("  The current reading is " +
            numberToColor(getReflectanceSensor()));
        pause(1000);
        printLine();
        goBackward(300);
        printLine("Here are the results of each of the color pads:");

        int color = getReflectanceSensor();
        int colorsTested = 0;
        boolean turned = false;

        while (colorsTested < 8)
        {
            turned = false;

            if ((colorsTested == 3) && !turned)
            {
                goForward(1400);
                pivotRight(90);
                turned = true;
            }

            if ((colorsTested == 4) && !turned)
            {
                goForward(1200);
                pivotRight(90);
                turned = true;
            }

            goForward();

            int temp = color;

            while (color == getReflectanceSensor())
            {
                //temp = getReflectanceSensor();
            }

            halt();
            color = getReflectanceSensor();
            printLine(" Entering a " + numberToColor(color) +
                " colored pad.");
            colorsTested++;
        }

        printLine();
        printLine(
            "Note that the final report is a WHITE pad.  Even though it" +
            " doesn't show against the white background, it is possible" +
            " to have a WHITE object that can be detected.  This shows" +
            " that in IDEALISTIC mode, you must be conscious of the" +
            " contrast between the objects that you want detected, and" +
            " the color of the background.");
    }

    void initSimulation()
    {
        sim.add(new jago.element.ColoredLine(100, 100, BLACK), 0, 0);
        sim.add(new jago.element.ColoredLine(100, 100, LIGHT_GRAY),
            100, 0);
        sim.add(new jago.element.ColoredLine(100, 100, MED_GRAY),
            200, 0);
        sim.add(new jago.element.ColoredLine(100, 100, DARK_GRAY),
            300, 0);
        sim.add(new jago.element.ColoredLine(100, 100, WHITE), 400, 0);
        sim.add(new jago.element.ColoredLine(100, 100, BLUE), 0, 100);
        sim.add(new jago.element.ColoredLine(100, 100, GREEN), 100,
            100);
        sim.add(new jago.element.ColoredLine(100, 100, RED), 200, 100);
    }

    String numberToColor(int value)
    {
        String result = "UNKNOWN";

        if (value == BLACK)
        {
            result = "BLACK";
        }
        else if (value == LIGHT_GRAY)
        {
            result = "LIGHT_GRAY";
        }
        else if (value == MED_GRAY)
        {
            result = "MED_GRAY";
        }
        else if (value == DARK_GRAY)
        {
            result = "DARK_GRAY";
        }
        else if (value == WHITE)
        {
            result = "WHITE";
        }
        else if (value == RED)
        {
            result = "RED";
        }
        else if (value == BLUE)
        {
            result = "BLUE";
        }
        else if (value == GREEN)
        {
            result = "GREEN";
        }
        else if (value == OFF)
        {
            result = "OFF";
        }
        else
        {
            printLine("Unknown value of " + value);
        }

        return result;
    }
}
