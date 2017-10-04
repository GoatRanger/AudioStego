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
 * Barrel.java
 *
 * Created on June 25, 2002, 9:46 AM
 */
package jago.element;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;



/**
 * @version 0.4
 * @author Karl A. Gossett
 */
public class Barrel
    extends MobileElement
    implements Pushable {

    /**
     *  If the barrel attempts to move faster than this value, it will be
     * slowed down.  Values larger than the threshold are usually due to
     * invalid calculations based on overlap or other conditions that might
     * arise, but can be safely ignored.  If the barrel does not move when it
     * it is struck by a fast moving object, try increasing this threshold.
     */
    static final double VELOCITY_THRESHOLD = 0.9;

    /**
     * Heading at next update.  Used to ensure that changes to the heading
     * only happen as a result of the action listener.
     */
    double newHeading = Double.POSITIVE_INFINITY;

    /**
     * Velocity at next update.  Used to ensure that changes to velocity only
     * happen as a result of the action listener.
     */
    double newVelocity = Double.POSITIVE_INFINITY;
    double pivotValue = 0;
    double radius;

    /**
     * Creates new Barrel with a radius of 8 pixels.
     */
    public Barrel() {
        this(8);
    }

    /**
     * Creates a new Barrel with the specified radius.
     *
     * @param radius DOCUMENT ME!
     */
    public Barrel(double radius) {
        super();

        if (radius > 0) {
            this.radius = radius;
        }
    }

    /**
     * Determines the way this object bounces off another Collidable object.
     *
     * @param collisionElement DOCUMENT ME!
     */
    public synchronized void bounceOff(Collidable collisionElement) {

        /* TODO: Update this to account for collisions when this element
         * is already in motion.  The current version assumes that this element
         * is stationary, and collides with only 1 element.  The implementation
         * here is a simplified version of a perfectly elastic collision,
         * and does not account for standard physics, including the weight
         * of the objects.
         */
        /* Note: This method must have a synchronized block to account for
         * concurrent changes in the heading and velocity.  It should be a
         * synchronized block, not synchronized method, but perhaps the
         * element status (heading, velocity, etc) should be a separate class
         * that any modifier could be synchronized on
         */
        double interimVelocity = 0;
        double interimHeading = 0;
        double angleFromCollider = 0;
        Area myArea = getArea();
        Area otherArea = collisionElement.getArea();
        Rectangle2D other = collisionElement.getBounds2D();
        otherArea.intersect(myArea);

        // Should never happen--if collision, then shouldn't be empty
        if (otherArea.isEmpty()) {
            System.err.println("ERROR: Collision reported with barrel, "
                    + "but cannot confirm with shape intersections.");
            return;
        }

        Rectangle2D intersection = otherArea.getBounds2D();

        // get angle between collider and collision
        double otherCenterX = other.getCenterX();
        double otherCenterY = other.getCenterY();
        Point2D otherCenter = new java.awt.geom.Point2D.Double(otherCenterX, otherCenterY);

        // Get the center point of where I was before the collision
        double myCenterX = getBounds2D()
                               .getCenterX();
        double myCenterY = getBounds2D()
                               .getCenterY();
        Point2D myCenter = new java.awt.geom.Point2D.Double(myCenterX, myCenterY);

        // Get the center point of the actual area where the actors collided
        Point2D intersectCenter = new java.awt.geom.Point2D.Double(
                                          intersection.getCenterX(),
                                          intersection.getCenterY());

        // Get the radius of the collider at the point of collision
        double radiusOfCollider = otherCenter.distance(intersectCenter);

        // Determine the components between my center of mass and
        // center of mass of the collision
        double distance = myCenter.distance(intersectCenter);
        angleFromCollider = (360
                            + Math.toDegrees(Math.atan2(intersectCenter.getX() - otherCenterX,
                                                        otherCenterY - intersectCenter.getY()))) % 360;

        double angleFromMe; // Between center of robot and center of collision

        // Might be negative angle, so convert to 0-360 range while reversing
        angleFromMe = (360
                      + Math.toDegrees(Math.atan2(myCenterX - intersectCenter.getX(),
                                                  intersectCenter.getY() - myCenterY))) % 360;

        // TODO: Change bouncing so it obeys physics!

        /* TODO: Update rebounding against a solid object */

        // If box was pushed into something, rebound
        if (!(collisionElement instanceof Mobile)) {
            interimHeading = 90 + angleFromMe;
        } else {
            Mobile mobileCollider = (Mobile)collisionElement;

            // TODO: get components of movement, and make current action additive
            // For now, assume only one hit, and move accordingly

            /* TODO: Fix this to model the real collision.  Need to model when
             * the elements collide across the center of mass--should push
             * straight ahead. For now, just a hack based on the angles to
             * cause it to be pushed straight
             */
            interimVelocity = mobileCollider.getVelocity();
            boolean usingAngular = false;

            if (interimVelocity == 0) {
                interimVelocity = mobileCollider.getAngularVelocity() * radiusOfCollider;
                usingAngular = true;
            }

            /* Give it a small velocity to ensure it moves.  Although it may
             * not make intuitive sense, it is possible for the collider to
             * have 0 velocity (sometimes due to improperly implemented physics
             * here, and also since the box might get pushed into something
             * that isn't moving), but since there was a collision,
             * this box needs to bounce off, at least a little (if it doesn't,
             * it will continue to get reported as a collision).
             */

            //            if (interimVelocity == 0) {
            //                interimVelocity = 0.005;
            //            }
            if (interimVelocity == 0) {
                interimVelocity = velVector.getMagnitude(); /*velocity*/
            }

            int tempAngle = (int)angleFromMe % 90;
            double colliderHeading = mobileCollider.getHeading();
            /* If using angular velocity, then the velocity vector is at
             * a 90 degree angle to the collision
             */
            if (usingAngular) {

                if (interimVelocity > 0) {
                    colliderHeading = angleFromCollider + 90;
                } else {
                    colliderHeading = angleFromCollider + 270;
                }
            }

            interimHeading = angleFromMe;

            // If bouncing at a new angle, adjust velocity
            double radAngle = Math.toRadians(
                                      (interimHeading + 360)
                                      - (colliderHeading % 360));

            if (Math.cos(radAngle) == 0) {
                newVelocity = interimVelocity;
            } else {
                newVelocity = interimVelocity / Math.cos(radAngle);
            }

            newHeading = interimHeading;
        }
    }

    /**
     * Draws this Barrel in a Graphics2D context.
     *
     * @param g2 DOCUMENT ME!
     */
    public void draw(Graphics2D g2) {

        Shape oldClip = g2.getClip();

        // Ensure it only draws inside the bounding rectangle
        if (myShape != null) {
            g2.clip(myShape);
        }

        // Draw it in the graphics context, using the transform
        g2.fill(myShape);

        Color oldColor = g2.getColor();
        g2.setColor(Color.gray);

        double insideRadius = radius * 0.75;
        g2.fill(new java.awt.geom.Ellipse2D.Double(location.getX() + radius - insideRadius,
                                     location.getY() + radius - insideRadius,
                                     insideRadius * 2, insideRadius * 2));
        g2.setColor(oldColor);

        // Need to make sure the clipping area is returned to original state
        g2.setClip(oldClip);
    } /*draw*/

    /**
     * React to a collision with another Collidable object
     *
     * @param collisionElement DOCUMENT ME!
     */
    public void handleCollision(Collidable collisionElement) {
        bounceOff(collisionElement);
    }

    /**
     * Undocumented
     */
    public void runTaskStep() {
        updateVelocity();
        updatePosition();

        // previousHeading = heading;
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
     * <CODE> timeLeftToMove = milliseconds</CODE><BR>
     */
    public synchronized void updatePosition() {

        if (host == null || pendingCollision) {

            return;
        }
        if (newHeading != Double.POSITIVE_INFINITY) {

            previousVel = (PolarPoint)velVector.clone();

            //            previousHeading = heading;
            velVector.setDirection(newHeading);

            //            heading = newHeading;
            newHeading = Double.POSITIVE_INFINITY;
        }

        /* TODO: Update the exit criteria to skip an update.
         * If pending a collision, don't update position until resolved.
         * (host==null is a hack due to sequencing problems when removing
         *  elements).
         */
        long deltaTime = 0;

        if (host != null) {

            if (lastUpdateTime == NOT_STARTED) {
                lastUpdateTime = host.elapsedTimeMillis();
            }

            long elapsedTime = host.elapsedTimeMillis();
            deltaTime = (elapsedTime - lastUpdateTime);
            lastUpdateTime = elapsedTime;
        }

        // Prevent this MobileElement from moving more than the time specified
        // Elements that extend the MobileElement must ensure timeLeftToMove
        // is properly set to allow this function.
        if (timeLeftToMove > 0) {

            if (timeLeftToMove < deltaTime) {
                deltaTime = timeLeftToMove;

                // If deltaTime is too small, the xMove/yMove are too small
                // to be noticed.
                if (deltaTime < 10) {
                    deltaTime = 10;
                }

                timeLeftToMove = 0;
            } else {
                timeLeftToMove -= (int)deltaTime;
            }
        }

        // Save the current location
        previousLocation.setLocation(location);
        previousShape = (Area)myShape.clone();

        double oldHeading = previousVel.getDirection();

        /* TODO: Get the Propulsion interface devices working, and implement
         * correctly here.  Need to fix:  The state of the system needs to be
         * provided to the Propulsion device.  Since this method always assumes
         * a correct return value from getDistanceMoved, the updates to use it
         * must include the means to ensure that valid values are returned--
         * so if the element is stopped, it returns 0, even though it is
         * keeping a power setting (a difference between simulation
         * implementation.
         */

        //        double xMove = propulsionDevice.getDistanceMoved(deltaTime)
        //                * SIMULATION_SCALE * Math.sin(Math.toRadians(heading));
        // Determine component distances to move based on velocity
        double xMove =
        (deltaTime / 1000.0) * SIMULATION_SCALE * velVector.getMagnitude() * Math.sin(Math.toRadians(
                                                                                              velVector.getDirection()));

        // yMove is negated due to reversal of y-axis in graphics system
        double yMove = -((deltaTime / 1000.0) * SIMULATION_SCALE * velVector.getMagnitude() * Math.cos(Math.toRadians(
                                                                                                               velVector.getDirection())));

        if (deltaTime == 0) {
            angularVelocity = 0;
        } else {
            angularVelocity = (velVector.getDirection() - oldHeading) / deltaTime; /*heading*/
        }

        AffineTransform at = new AffineTransform();
        at.translate(xMove, yMove);
        myShape.transform(at);

        // Move the specified amount by translating the current location
        location.setLocation(location.getX() + xMove, location.getY() + yMove);
    }

    /**
     * Undocumented
     */
    public synchronized void updateVelocity() {
        previousVel = (PolarPoint)velVector.clone();
        if (newVelocity > 0.9 && newVelocity < Double.POSITIVE_INFINITY) {
            newVelocity = velVector.getMagnitude(); /*velocity*/
        }

        if (newVelocity != Double.POSITIVE_INFINITY) {

            velVector.setMagnitude(newVelocity);
            newVelocity = Double.POSITIVE_INFINITY;
        }
        else if (velVector.getMagnitude() > 0) {
            velVector.setMagnitude(velVector.getMagnitude() * 0.01);
        }

        if (velVector.getMagnitude() < 0.001) {
            velVector.setMagnitude(0);
        }
    }

    /**
     * Undocumented
     */
    protected void createArea() {
        myShape = new Area(new java.awt.geom.Ellipse2D.Double(location.getX(),
                                                location.getY(),
                                                radius * 2,
                                                radius * 2));
        previousShape = (Area)myShape.clone();
        previousVel = (PolarPoint)velVector.clone();

        previousLocation.setLocation(location);
    }
}