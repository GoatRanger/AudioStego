/*
 * Cannon.java
 *
 * Created on April 23, 2003, 9:25 AM
 * Copyright (C) 2002  USMA
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package eecs.jago.instructor.gossett;

import jago.element.*;

import jago.element.PolarPoint;

import java.applet.AudioClip;

import java.awt.*;
import java.awt.geom.*;


import javax.swing.JApplet;
/**
 *
 * @author  dk8685
 */
public class Cannon extends MobileElement implements Destructible {
   
    /** State representing no action */
    static final int NONE = -1;

    /** State representing that the robot is pivoting to the left */
    static final int PIVOTING_LEFT = 4;

    /** State representing that the robot is pivoting to the right */
    static final int PIVOTING_RIGHT = 3;

    /** State representing that the robot is stopped */
    static final int STOPPED = 0;
    
    protected PolarPoint idealVelVector;

    /**
     * Prevents changing the realism once the user has set it once, or since
     * the robot has started moving
     */
    protected boolean realismSet = false;

    /** The scale of the movement of the robot, in ms/inch.  The default value
     * is 280ms/inch.  For better accuracy, the robot should be calibrated ({@see
     * #calibrateDistance(int)} 
     */
    protected double MOVEMENT_SCALE = 280.0;

    /** The amount to pivot during a turn */
    protected double pivotValue;


    /** The last successful command to the robot. */
    protected int lastCommand = STOPPED;

    /** The level of realism, from <CODE>Realism</CODE> interface. */
    protected int realismLevel;

    /** The state of the robot, from <CODE>RobotState</CODE> */
    protected int state = STOPPED;

    static private AudioClip shootSound;
    static private AudioClip explosionSound;
    private Image destroyedImage;
    private boolean destroyed = false;
    private long timeDestroyed = 0;
    private CannonBall ball;
    /** Returns the current state of this object.
     *
     * @return <CODE>true</CODE> if this element was destroyed.
     */
    public boolean isDestroyed() {
        return destroyed;
    }
    
    public void removedFromSim() {
        
        if (ball != null) {
            host.remove(ball);
            ball = null;
        }
		super.removedFromSim();
    }    
    
    public void fire(int velocity) {
         if (ball != null) {
            if (ball.isDestroyed()) {
                ball = null;
            }
        }
        if (ball == null) {
            
            double heading = getHeading();
            double x = 48 * Math.sin(Math.toRadians(heading));
            double y = 48 * Math.cos(Math.toRadians(heading));
            host.add(ball = new CannonBall((int)heading, velocity),location.getX()+12+x,location.getY()+16-y);
            if (shootSound != null) {
                shootSound.play();
            } else {
            	System.out.println("No sounds");
            }
        }
    }
    /** Sets the flag indicating if this object was destroyed.
     */
    public void setDestroyed() {
        destroyed = true;
        setAppearance("bang.gif");
        //setAppearance(destroyedImage);
        explosionSound.play();
        createArea();
        timeDestroyed = System.currentTimeMillis();
    }
    /**
     * Creates a new Cannon.
     */
    public Cannon() {
        super();

		//destroyedImage=ImageLoader.getImage(this,"images/bang.gif");
        setAppearance("images/cannon_sm.gif");
        idealVelVector = new PolarPoint(0, 0);
        setInitialHeading(0);
        // Simple use of shootSound
        shootSound = JApplet.newAudioClip(getClass()
                         .getResource("sounds/ARTILLER.wav"));
        explosionSound = JApplet.newAudioClip(getClass()
                        .getResource("sounds/EXPLOSI1.wav"));
    }

    /**
     * Determines the way this object bounces off another Collidable object.
     * Provided as part of the interface to ensure elements bounce off as
     * required
     *
     * @param collisionElement The {@link jago.element.Collidable} element
     *        to bounce off of.
     */
    public void bounceOff(Collidable collisionElement) {
    }

    /**
     * Draws this robot.  Since attachments don't draw themselves (and aren't
     * drawn by the simulation), the element to which they are attached is
     * expected to draw them.
     *
     * @param g2 The <code>Graphics2D</code> context to draw in.
     */
    public void draw(Graphics2D g2) {
		AffineTransform transform = new AffineTransform();

        if (!isDestroyed()) {

	        // Rotate the image according to its azimuth
	        transform.rotate(Math.toRadians(velVector.getDirection()), 
	                         location.getX() + 13, 
	                         location.getY() + 34);
	
	        // and move it to the correct location
	        transform.translate(location.getX(), 
	                            location.getY());
        } else {
        	transform.translate(location.getX()-10,location.getY()-34);
        }
        // Draw it in the graphics context, using the rotation transform
        if (appearance != null) {
            g2.drawImage(appearance, transform, host);
        } else {
            g2.draw(myShape);
        }

    }

    /**
     * For simulation internal use only.
     */
    public Point2D getLocation() {
        return location;
    }
    
    /**
     * Stops the robot from moving, unless the robot is pivoting. If the robot
     * is pivoting, then the robot will wait for the pivot to complete and
     * then halt.
     */
    public void halt() {
        while ((state == PIVOTING_RIGHT) || (state == PIVOTING_LEFT)) {
            try {
                Thread.sleep((int) host.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setState(STOPPED);
    }

    /**
     * Performs actions when a collision occurs. Any collision should destroy
     * the cannon.
     *
     * @param collisionElement A collidable actor to react to
     */
    public synchronized void handleCollision(Collidable collisionElement) {
        setDestroyed();
    }

    /**
     * Turns the robot the number of specified degrees to the left. The robot
     * will stop if it is currently moving and then pivot.
     *
     * @param degrees The number of degrees to turn.
     */
    public void turnLeft() {
        if (velVector.getDirection() > 180 && velVector.getDirection() < 265 || destroyed) {
            return;
        }
        pivotValue = 1;
        setState(PIVOTING_LEFT);
        
        // Added in version 6.0 to ensure command waits until complete
        halt();
    }

    /**
     * Pivots the robot the number of specified degrees to the right.  The
     * robot will stop if it is currently moving and then pivot.
     *
     * @param degrees The number of degrees to turn.
     */
    public void turnRight() {

        if (velVector.getDirection() > 80 && velVector.getDirection() < 180 || destroyed) {
            return;
        }
        pivotValue = 1;
        setState(PIVOTING_RIGHT);
        
        // Added in version 6.0 to ensure command waits until complete
        halt();
    }

    /**
     * Define the behavior of the robot here based upon its current state.
     */
    public synchronized void updatePosition() {
        if (pendingCollision) {
            return;
        }
        
        previousVel = (PolarPoint) velVector.clone();
        // Act on the state
        switch (state) {

        case STOPPED:
            velVector.setMagnitude(0);
            timeLeftToMove = 0;

            //sound.stop();
            break;

        case PIVOTING_RIGHT:

        // fall through to PIVOTING_LEFT
        case PIVOTING_LEFT:
            velVector.setMagnitude(0);
            if (pivotValue > 0) {
                
                changeAzimuth(pivotValue, state);
                pivotValue -= 1;
            } else {
                pivotValue = 0;
                setState(STOPPED);
            }

            break;

        default:
            break;
        }

        // Save the robot's current state (position, heading) and move
        // the robot to its new location
        finishUpdatePosition();
    }
    /**
     * Updates the current location based on velocity and heading.  This
     * method will ensure the mobile object does not move more than the time
     * specified in its move methods as long as <CODE>timeLeftToMove</CODE>
     * is set to the amount of time to move.
     * 
     * <P>
     * For example, if the element has a method declared as:
     * </p>
     * <BR><CODE>   public void move(int milliseconds)</CODE><BR>
     * 
     * <P>
     * then the move method should set
     * </p>
     * <CODE>      timeLeftToMove = milliseconds</CODE><BR>
     */
    public void finishUpdatePosition() {

        /* TODO: Update the exit criteria to skip an update.
         * If pending a collision, don't update position until resolved.
         * (host==null is a hack due to sequencing problems when removing
         *  elements).
         */

        long deltaTime = 0;

        if (lastUpdateTime == NOT_STARTED) {
            lastUpdateTime = host.elapsedTimeMillis();
        }

        long elapsedTime = host.elapsedTimeMillis();
        deltaTime = (elapsedTime - lastUpdateTime);
        lastUpdateTime = elapsedTime;

        // Prevent this MobileElement from moving more than the time specified
        // Elements that extend the MobileElement must ensure timeLeftToMove
        // is properly set to allow this function.
        if (timeLeftToMove > 0) {

            if (timeLeftToMove < deltaTime) {

                if (timeLeftToMove < 5) {
                    deltaTime = 5;
                } else {
                    deltaTime = timeLeftToMove;
                }

                timeLeftToMove = 0;
            } else {
                timeLeftToMove -= (int)deltaTime;
            }
        }

        // Save the current location
        previousLocation.setLocation(location);
        previousShape = (Area)myShape.clone();

        PolarPoint savedVel = (PolarPoint)previousVel.clone();

        /* TODO: Get the Propulsion interface devices working, and implement
         * correctly here.  Need to fix:  The state of the system needs to be
         * provided to the Propulsion device.  Since this method always assumes
         * a correct return value from getDistanceMoved, the updates to use it
         * must include the means to ensure that valid values are returned--
         * so if the element is stopped, it returns 0, even though it is
         * keeping a power setting (a difference between simulation
         * implementation.
         */

        /**
         * Propulsion interface stuff. Left here for future use, but the
         * following seems to work (pivoting might need some adjustment)
         */

        //double leftDist = leftWheel.getDistanceMoved(deltaTime);
        //double rightDist = rightWheel.getDistanceMoved(deltaTime);
        //double fwd = leftDist > rightDist
        //                 ? leftDist - (leftDist - rightDist)
        //                 : rightDist - (rightDist - leftDist);
        //double diff = (leftDist - rightDist)/2;
        //double pivotAngle = Math.toDegrees(Math.atan2(Math.abs(diff),
        //                                     axleLength));

        /** End of Propulsion interface stuff. */


        if (deltaTime == 0) {
            angularVelocity = 0;
        } else {

            double tempHeading = velVector.getDirection();
            double tempOldHeading = savedVel.getDirection();

            // Instantaneous velocity = change/time * radius

            /* If the robot rotated past 0 on this update, adjust to prevent
             * incorrect large readings
             */
            if (Math.abs(tempHeading - tempOldHeading) > 360) {

                if (tempHeading < tempOldHeading) {
                    tempHeading += 360;
                } else {
                    tempOldHeading += 360;
                }
            }

            // Divide by 4 is a hack so the angular ~ linear for a given power
            angularVelocity = (tempHeading - tempOldHeading) / deltaTime * size.getHeight() / 2 / 4;
        }

        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(
                          velVector.getDirection() - savedVel.getDirection()), 
                  location.getX() + 13, 
                  location.getY() + 34);
        myShape.transform(at);

        // Move the specified amount by translating the current location
       //location.setLocation(location.getX() + xMove, location.getY() + yMove);        
    }
    /**
     * Causes the robot to continue what it is currently doing for a specified
     * amount of time before executing the next command.
     *
     * @param milliseconds Specifies for how long the robot will wait
     */
    public void waitFor(int milliseconds) {
        // If just wanting robot to wait, make sure timer is set
        // If called from another movement, timeLeftToMove will be set already
        timeLeftToMove = milliseconds;

        while (timeLeftToMove > 0) {
            try {
                // Sleep for the period of one update
                Thread.sleep(Math.min(timeLeftToMove, (int) host.getDelay()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the current state of the robot.  It is managed in this method to
     * ensure synchronization, and to force the robot to wait if it is pending
     * a collision.  If it is pending a collision, it will wait in this method
     * until it is notified by the completion of collision handling (see
     * {@link jago.element.MobileElement#pendingCollision}
     *
     * @param state The new state of the robot
     */
    protected synchronized void setState(int state) {
        if (pendingCollision) {
            try {
                // Block here if pending collision, notified when collision
                // is handled
                wait();
            } catch (InterruptedException e) {
                // Just ignore the interrupt for now
            }
        }

        this.state = state;
    }

    /**
     * Sets the heading of the robot to <i>degrees</i>, which is guaranteed to
     * be between 0 and 360 degrees. If the method argument is greater than
     * 360 or less than 1, then the call is ignored.
     *
     * @param delta Change the azimuth this many degrees.
     * @param direction PIVOTING_LEFT or PIVOTING_RIGHT
     */
    protected void changeAzimuth(double delta, int direction) {
        // ignore calls with invalid arguments
        if ((delta > 360) || (delta < 0)) {
            return;
        }

        if (direction == PIVOTING_RIGHT) {
            velVector.addToDirection(delta);
        } else if (direction == PIVOTING_LEFT) {
            velVector.addToDirection(-delta);
        }
    }

    /**
     * Undocumented
     */
    protected void runTaskStep() {
        // Register (with the SimEngine) any sensors that haven't
        // been registered

        updateVelocity();
        updatePosition();
        if (destroyed) {
        	if (System.currentTimeMillis()-timeDestroyed>4000) {
        		host.remove(this);
        	}
        }
        
    }  
}
