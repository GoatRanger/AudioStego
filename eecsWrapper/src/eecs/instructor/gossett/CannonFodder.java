/*
 * CannonFodder.java
 *
 * Created on April 23, 2003, 10:32 AM
 */
package eecs.instructor.gossett;

import eecs.jago.instructor.gossett.Cannon;
import eecs.jago.instructor.gossett.CannonBall;
/**
 *
 * @author  dk8685
 */
public class CannonFodder extends eecs.RobotGuiBase {
    static char currentChar = '0';
    static Cannon leftCannon = null, rightCannon=null;
    static CannonBall ball = null;
    static boolean gameOn = false;
    public static boolean LEFT = true, RIGHT = false;
    static {
        sim = new CannonSim();
        setSimulationTitle("Cannon Fodder");
        //sim.addKeyListener(this);
        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will
        // appear in the sim box
        sim.setVisible(true);

        // Ensures that the simulation does not have a robot
        robot = null;
    }
    
    public static void createRobot(int x, int y, int heading) {
    	showError("Sorry, you can't deploy robots to fight for you.  Cannons only...");
    }
    
    public static void startGame() {
        if (leftCannon != null) {
        	if (leftCannon.inASimulation()) {
        		sim.remove(leftCannon);
        	}
        }
        if (rightCannon != null) {
        	if (rightCannon.inASimulation()) {
        		sim.remove(rightCannon);
        	}
        }
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {}
        leftCannon = new Cannon();
        rightCannon = new Cannon();
        sim.add(leftCannon, 86, 529);
        sim.add(rightCannon, 521, 529);
        gameOn = true;
        sim.requestFocus();
    }
    
    protected static boolean isLeftAlive() {
        if (leftCannon == null) return false;
        else return !leftCannon.isDestroyed();
    }
    
    protected static boolean isRightAlive() {
        if (rightCannon == null) return false;
        else return !rightCannon.isDestroyed();
    }  
    
    protected static char getCurrentChar() {
    	char press = ((CannonSim)sim).getCurrentKeyPress();

        return press;
    }
    
    protected static void turnLeftCannon(boolean moveLeft) {
        if (!gameOn || leftCannon == null || !leftCannon.inASimulation()) return;
        if (moveLeft) {
            leftCannon.turnLeft();
        } else {
            leftCannon.turnRight();
        }
    }
    protected static void turnRightCannon(boolean moveRight) {
        if (!gameOn || rightCannon == null || !rightCannon.inASimulation()) return;
        if (moveRight) {
            rightCannon.turnLeft();
        } else {
            rightCannon.turnRight();
        }
    }
    protected static void fireLeftCannon(int velocity) {
        if (!gameOn) return;        
        leftCannon.fire(velocity);
    }
    
    protected static void fireRightCannon(int velocity) {
        if (!gameOn) return;
        rightCannon.fire(velocity);
    }
        
}
