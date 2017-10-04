/*
 * CannonSim.java
 *
 * Created on April 23, 2003, 10:12 AM
 */
package eecs.instructor.gossett;

import jago.SimBox;

import java.awt.event.KeyListener;

/**
 *
 * @author  dk8685
 */
public class CannonSim extends SimBox implements KeyListener {
    char currentChar = '0';
    /** Creates a new instance of CannonSim */
    public CannonSim() {
        // note: the false is "not tiled background"
        super(700, 600, "images/cannonBG.gif", false);
        setTitle("Cannon Fodder");
        addKeyListener(this);
    }
    
    public void keyPressed(java.awt.event.KeyEvent keyEvent) {

//       currentChar = keyEvent.getKeyChar();
//       System.err.println("pressed");
       
    }
  
    public void keyReleased(java.awt.event.KeyEvent keyEvent) {
        //currentChar = '0';
    }
    
    public void keyTyped(java.awt.event.KeyEvent keyEvent) {
    	currentChar = keyEvent.getKeyChar();
    }
    
    public char getCurrentKeyPress() {
        char value = currentChar;
        currentChar = '0';
        return value;
    }
}
