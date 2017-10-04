/*
 * GossettRobotGUI.java
 *
 * Created on December 10, 2001, 12:51 PM
 */
package eecs.instructor.gossett;

import jago.*;
import jago.element.Wall;
import eecs.jago.instructor.gossett.*;


/**
 *
 * @author  Karl A. Gossett
 * @version 0.1
 */
public class BallTestGui extends eecs.RobotGuiBase {

    static JavaBall ball = new JavaBall();
    
    static {
        sim = new SimBox();

        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will

        // appear in the sim box
        sim.setVisible(true);
        robot = null;
        sim.add(new Wall(600,5),0,0);
        sim.add(new Wall(600,5),0, 495);
        sim.add(new Wall(5,490),0,5);
        sim.add(new Wall(5,490),595,5);
    } // end static initializer
    
    public static void main(String[] args) {
        
        ball.setInitialHeading(30);
        sim.add(ball, 100, 100);
        ball.setPower(10);
        ball.roll();
    }
}