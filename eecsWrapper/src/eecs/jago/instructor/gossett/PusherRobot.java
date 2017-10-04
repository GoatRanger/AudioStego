/* Copyright (C) 2002  USMA
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

/*
 * PusherRobot.java
 *
 * Created on November 17, 2001, 8:53 PM
 */
package eecs.jago.instructor.gossett;

import jago.element.*;

import java.awt.geom.*;


/**
 * A robot that is designed to push <CODE>Pushable</CODE> objects.  The
 * differernce in implementation between this robot and a
 * <CODE>StandardRobot</CODE> is that the standard robot assumes it is
 * blocked when it makes contact with any object, while this robot will
 * attempt to continue moving when it contacts a <CODE>Pushable</CODE>
 * object.  This robot is also implemented with a bumper
 * (<CODE>Sensor.TOUCH</CODE>) connected to RCX Input 2 and a light sensor
 * (<CODE>Sensor.LIGHT</CODE>) connected to RCX Input 1, both assumed to
 * react to any contact across the entire front of the robot.
 * 
 * @version 1.0
 * @author Karl A. Gossett
 */
public class PusherRobot
    extends StandardRobot {

    /**
     * Set to <CODE>true</CODE> if the robot has collided with something
     * Pushable.
     */
    boolean canPush = false;

    /**
     * The visible homing marker that the robot uses when it needs to return
     * to a given point.
     */
    ColoredLine marker;

    /**
     * <CODE>true</CODE> if the robot has already dropped a marker that it can
     * return to.
     */
    boolean markerActive = false;

    /**
     * <CODE>true</CODE> if the robot has dropped a marker to indicate where
     * to return if it is pushing an object.
     */
    boolean pushing = false;

    /** The location of the marker that the pusher uses to return to a spot */
    Point2D savedLocation;

    /**
     * Creates new PusherRobot with a TOUCH and LIGHT sensor.
     */
    public PusherRobot() {
        super();
        addSensor(RCX_2, Sensor.TOUCH);
        addSensor(RCX_1, Sensor.LIGHT);
        savedLocation = new Point2D.Double(50, 50);
    }

    /**
     * Performs actions when a collision occurs. The current implementation
     * assumes that any TOUCH sensor will react to any collision on the front
     * of the robot, and that any light sensor will react to reflections
     * across most of the front of the robot.
     * 
     * @param collider   A collidable actor to react to
     */
    public void handleCollision(Collidable collider) {

        Rectangle2D me = getBounds2D();
        Rectangle2D other = collider.getBounds2D();

        // Get a rectangle that defines the area where the collision occurred
        Rectangle2D intersection = new Rectangle2D.Double();
        Rectangle2D.intersect(me, other, intersection);

        // Get the center point of where I was before the collision
        double myCenterX = previousLocation.getX() + size.getWidth() / 2.0;
        double myCenterY = previousLocation.getY() + size.getHeight() / 2.0;
        Point2D myCenter = new Point2D.Double(myCenterX, myCenterY);

        // Get the center point of the actual area where the actors collided
        Point2D intersectCenter = new Point2D.Double(
                                          intersection.getCenterX(), 
                                          intersection.getCenterY());

        // Determine the components between the Robot and point of collision
        double distance = previousLocation.distance(intersectCenter);
        intersectCenter.setLocation(intersectCenter.getX() - myCenterX, 
                                    intersectCenter.getY() - myCenterY);

        double angleFromMe;

        // Get angle between center of robot and center of collision
        angleFromMe = Math.toDegrees(Math.atan2(intersectCenter.getX(), 
                                                -intersectCenter.getY()));

        // Release all Rectangle2Ds to the garbage collector
        me = other = intersection = null;

        // Calculate the direction: Positive angles to the right, negative left
        double diffAngle = angleFromMe - velVector.getDirection(); /*heading*/

        // Angle is too large, wrap around to the negative side (left)
        if (diffAngle > 180) {
            diffAngle -= 360;
        }

        // Angle is too small, wrap around to positive side (right)
        if (diffAngle <= -180) {
            diffAngle += 360;
        }

        // Set Light sensors
        if (collider instanceof Reflective) {

            Reflective a = (Reflective)collider;
            int reflection = a.getReflection();

            if (Math.abs(diffAngle) < 45) {
                inputs[RCX_1].setValue(reflection);
            }

            return;
        }

        // Expected state used when pushing--normally robot will stop, but
        // if collided with a pushable object, then continue to move
        int expectedState = state;

        // Hit directly in front
        if (Math.abs(diffAngle) < 10) {

            // Hit in front, so turn on bumper
            inputs[RCX_2].setValue(Sensor.ON);

            if (state != GOING_BACKWARD) {
                expectedState = BLOCKED;
            }

            // end direct frontal hit
        } else if (diffAngle > -45 && diffAngle <= -10) {

            // Hit on front left
            inputs[RCX_2].setValue(Sensor.ON);

            if (state != GOING_BACKWARD) {
                expectedState = BLOCKED;
            }
        } else if (diffAngle >= 10 && diffAngle < 45) {

            // Hit on front right
            inputs[RCX_2].setValue(Sensor.ON);

            if (state != GOING_BACKWARD) {
                expectedState = BLOCKED;
            }
        } else if (diffAngle <= -45 && diffAngle > -90) {

            if (state == GOING_FORWARD || state == PIVOTING_RIGHT) {
                expectedState = BLOCKED;
            }
        } else if (diffAngle >= 45 && diffAngle < 90) {

            if (state == GOING_FORWARD || state == PIVOTING_LEFT) {
                expectedState = BLOCKED;
            }
        } else if (Math.abs(diffAngle) >= 90) {

            if (state != GOING_FORWARD) {
                expectedState = BLOCKED;
            }
        }

        if (expectedState == BLOCKED) {
            location.setLocation(previousLocation);

            // Only change state if the object isn't Pushable
            if (!(collider instanceof Pushable)) {
                state = expectedState;
            } else {

                // Found something pushable
                canPush = true;
            }
        }
    }

    /**
     * Undocumented
     */
    public void startPushing() {
        savedLocation.setLocation(location);
        pushing = true;

        if (!markerActive) {
            marker = new ColoredLine(5, 5, ReflectiveColors.GREEN);
            host.add(marker, location.getX() + 10, location.getY() + 10);
            markerActive = true;
        }
    }

    /**
     * Tells the robot that it has completed pushing and should return to the
     * dropped marker.
     */
    public void stopPushing() {

        double oldPower = power;
        setPower(10);

        // Note that in the following, there is a brief sleep for the thread
        // to ensure the robot does not move beyond its marker.  Without the
        // sleep, the thread can move the robot beyond its marker.
        if (markerActive) {

            if (savedLocation.getX() < location.getX()) {

                while (savedLocation.getX() <= location.getX()) {
                    goBackward();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        ;
                    }
                }
            } else if (savedLocation.getX() > location.getX()) {

                while (savedLocation.getX() >= location.getX()) {
                    goBackward();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        ;
                    }
                }
            } else if (savedLocation.getY() > location.getY()) {

                while (savedLocation.getY() >= location.getY()) {
                    goBackward();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        ;
                    }
                }
            } else if (savedLocation.getY() < location.getY()) {

                while (savedLocation.getY() <= location.getY()) {
                    goBackward();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        ;
                    }
                }
            }

            location.setLocation(savedLocation);
            host.remove(marker);
            markerActive = false;
            savedLocation.setLocation(50, 50);
            pushing = false;
            canPush = false;
        }

        setPower(oldPower);
    }
}