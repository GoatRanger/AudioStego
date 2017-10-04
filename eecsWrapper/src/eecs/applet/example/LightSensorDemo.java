package eecs.applet.example;

/**
 * A small class to test the functioning of the light sensor based on
 * a series of test-pad colors.  Requires that the simulation work
 * in realistic mode.
 *
 * @author Karl A. Gossett
 */
public class LightSensorDemo extends eecs.applet.AppletBase
{
    public void run()
    {
        setBackgroundImage("ColorChart.gif");

        int numPads = 14;
        runRealisticSim();
        createRobot(680, 150, 270);
        setPower(5);

        int transitions = 0;
        int reading = getReflectanceSensor();

        int oldColor = reading;
        printLine("Starting Reading = " + reading);

        // Two colors report same--so 2 less transitions
        while (transitions < (numPads - 2))
        {
            int counter = 0;
            goForward();

            while (getReflectanceSensor() == reading)
            {
                counter++;
            }

            halt();
            reading = getReflectanceSensor();

            /* On a P3 700, The robot will never get more than 5 readings
             * in a transition, but will get over 75 on a solid color
             * So, we'll use a value in the middle to allow for slop.
             */
            if (counter > 15)
            {
                printLine("Transition--primary color-value was " +
                    oldColor);
                transitions++;
            }

            oldColor = reading;
            printLine("Current Reading = " + reading);

            //pause(100);
            if (transitions == 6)
            {
                destroyRobot();
                createRobot(20, 350, 90);
            }
        }

        halt();
    }
}
