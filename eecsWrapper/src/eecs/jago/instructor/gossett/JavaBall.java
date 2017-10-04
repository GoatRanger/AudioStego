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

package eecs.jago.instructor.gossett;

import jago.element.PolarPoint;
import jago.element.Collidable;
import jago.element.MobileElement;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;



public class JavaBall
    extends MobileElement {

    double imageSeq = 0;

    public JavaBall() {
        super();
        setAppearance("pingPong.gif");
    }

    /**
     * Draws this MobileElement.
     * 
     * @param g2 The <code>Graphics2D</code> context.
     */
    public void draw(Graphics2D g2) {

        Shape oldClip = g2.getClip();

        // Ensure it only draws inside the bounding rectangle
        Rectangle2D bound = myShape.getBounds2D();
        Shape clip = new java.awt.Rectangle((int)bound.getX(), (int)bound.getY(), 16, 16);
        if (myShape != null) {
            g2.clip(clip);
        }

        AffineTransform transform = new AffineTransform();

        // Rotate the image according to its azimuth
        //        transform.rotate(Math.toRadians(velVector.getDirection()),
        //                         location.getX() + size.getWidth() / 2.0,
        //                         location.getY() + size.getHeight() / 2.0);
        // and move it to the correct location
        transform.translate(location.getX()+((int)imageSeq%6)*16, 
                            location.getY()+((int)imageSeq/6)*16);
        // Draw it in the graphics context, using the rotation transform
        if (appearance != null) {
            g2.drawImage(appearance, transform, host);
        } else {
            g2.draw(myShape);
        }

        // Need to make sure the clipping area is returned to original state
        g2.setClip(oldClip);
    } /*draw*/

    /**
     * Undocumented
     * 
     * @param collisionElement Empty
     */
    public void handleCollision(Collidable collisionElement) {

        Rectangle2D me = getBounds2D();
        Rectangle2D other = collisionElement.getBounds2D();

        // Get a rectangle that defines the area where the collision occurred
        Rectangle2D intersection = new Rectangle2D.Double();
        Rectangle2D.intersect(me, other, intersection);

        if (collisionElement instanceof jago.element.Wall) {

            double dir = velVector.getDirection();
            if (intersection.getWidth() < 10) {
                if (intersection.getX() < me.getCenterX()) {
                    velVector.setDirection(90 - (dir - 270));
                } else {
                    velVector.setDirection(270 - (dir - 90));
                }
            } else {
                velVector.setDirection(180 - dir);
            }
        }

        rollbackPosition();
    }

    /**
     * Undocumented
     */
    public void roll() {
        velVector.setMagnitude(1);
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
    public void updatePosition() {

        /* TODO: Update the exit criteria to skip an update.
         * If pending a collision, don't update position until resolved.
         * (host==null is a hack due to sequencing problems when removing
         *  elements).
         */
        if (host == null || pendingCollision) {

            return;
        }

        long deltaTime = 0;

        if (lastUpdateTime == NOT_STARTED) {
            lastUpdateTime = host.elapsedTimeMillis();
        }

        long elapsedTime = host.elapsedTimeMillis();
        deltaTime = (elapsedTime - lastUpdateTime);
        lastUpdateTime = elapsedTime;
        imageSeq += deltaTime*100;
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

        // Determine component distances to move based on velocity
        double xMove = (deltaTime / 1000.0) * SIMULATION_SCALE * velVector.getX();
        double yMove = ((deltaTime / 1000.0) * SIMULATION_SCALE * velVector.getY());

        if (deltaTime == 0) {
            angularVelocity = 0;
        } else {

            double tempHeading = velVector.getDirection();
            double tempOldHeading = savedVel.getDirection();

            // Instantaneous velocity = change/time * radius

            /* If the robot rotated past 0 on this update, adjust to prevent
             * incorrect large readings
             */
            if (Math.abs(tempHeading - tempOldHeading) > 300) {

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

        //        at.rotate(Math.toRadians(
        //                          velVector.getDirection() - savedVel.getDirection()),
        //                  location.getX() + size.getWidth() / 2.0,
        //                  location.getY() + size.getHeight() / 2.0);
        at.translate(xMove, yMove);
        myShape.transform(at);

        // Move the specified amount by translating the current location
        location.setLocation(location.getX() + xMove, location.getY() + yMove);
    }

    /**
     * Undocumented
     */
    protected void createArea() {
        myShape = new Area(new java.awt.geom.Ellipse2D.Double(location.getX(), 
                                                location.getY(), 
                                                size.getWidth(), 
                                                size.getHeight()));

        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(velVector.getDirection()), 
                  location.getX() + size.getWidth() / 2.0, 
                  location.getY() + size.getHeight() / 2.0);
        myShape.transform(at);
        at.setToIdentity();
        previousShape = (Area)myShape.clone();
        previousVel = (PolarPoint)velVector.clone();
        previousLocation.setLocation(location);
    }

    /**
     * Undocumented
     */
    protected void rollbackPosition() {
        location.setLocation(previousLocation);
        myShape = (Area)previousShape.clone();
    }

    /**
     * Perform one step of execution of this element
     */
    protected void runTaskStep() {
        imageSeq = (++imageSeq) % 16;
        updateVelocity();
        updatePosition();
    }
}