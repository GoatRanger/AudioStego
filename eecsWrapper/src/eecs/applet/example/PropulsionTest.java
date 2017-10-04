/*
 * PropulsionTest.java
 *
 * Created on August 8, 2002, 5:29 PM
 */
package eecs.applet.example;

/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public class PropulsionTest extends eecs.applet.AppletBase
{
    /**
     * DOCUMENT ME!
     */
    public void run()
    {
        createRobot(300, 300, 0);
        setPower(10);
        goForward(1000);
        pivotLeft(45);
        goBackward(1000);
    }
}
