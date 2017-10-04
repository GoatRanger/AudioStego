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
 * PushableBox.java
 *
 * Created on November 12, 2001, 7:34 PM
 */
package jago.element;



/**
 * @version 1.0
 * @author Karl A. Gossett
 */
public class PushableBox
    extends MobileElement
    implements Pushable {

    double pivotValue = 0;

    /**
     * Creates new PushableBox
     */
    public PushableBox() {
        super();
        setAppearance("images/box2.gif");
    }

    /**
     * Determines the way this object bounces off another Collidable object
     *
     * @param collisionElement DOCUMENT ME!
     */
    public synchronized void bounceOff(Collidable collisionElement) {

        /* Note: This method must have a synchronized block to account for
         * concurrent changes in the heading and velocity.  It should be a
         * synchronized block, not synchronized method, but perhaps the
         * element status (heading, velocity, etc) should be a separate class
         * that any modifier could be synchronized on
         */
        java.awt.geom.Rectangle2D me = getBounds2D();
        java.awt.geom.Rectangle2D other = collisionElement.getBounds2D();

        // Get a rectangle that defines the area where the collision occurred
        java.awt.geom.Rectangle2D intersection = new java.awt.geom.Rectangle2D.Double();
        java.awt.geom.Rectangle2D.intersect(me, other, intersection);

        if (intersection.getWidth() == 0) {
            intersection = other;
        }

        // Get the center point of where I was before the collision
        double myCenterX = previousShape.getBounds2D()
                                        .getCenterX();
        double myCenterY = previousShape.getBounds2D()
                                        .getCenterY();
//        java.awt.geom.Point2D myCenter = new java.awt.geom.Point2D.Double(myCenterX, myCenterY);

        // Get the center point of the actual area where the actors collided
        java.awt.geom.Point2D intersectCenter = new java.awt.geom.Point2D.Double(
                                          intersection.getCenterX(),
                                          intersection.getCenterY());

        // Determine the components between the Robot and point of collision
//        double distance = previousLocation.distance(intersectCenter);
        intersectCenter.setLocation(intersectCenter.getX() - myCenterX,
                                    intersectCenter.getY() - myCenterY);

        double angleFromMe; // Between center of robot and center of collision
        angleFromMe = Math.toDegrees(Math.atan2(intersectCenter.getX(),
                                                -intersectCenter.getY()));

        // TODO: Change bouncing so it obeys physics!

        /* TODO: Update rebounding against a solid object */

        // If box was pushed into something, rebound
        if (!(collisionElement instanceof Mobile)) {
            velVector.addToDirection(90);
            location.setLocation(previousLocation);
        } else {

            Mobile mobileCollider = (Mobile)collisionElement;

            // TODO: get components of movement, and make current action additive
            // For now, assume only one hit, and move accordingly

            /* TODO: Fix this to model the real collision.  Need to model when
             * the elements collide across the center of mass--should push
             * straight ahead. For now, just a hack based on the angles to
             * cause it to be pushed straight
             */
            velVector.setMagnitude(mobileCollider.getVelocity());

            /* Give it a small velocity to ensure it moves.  Although it may
             * not make intuitive sense, it is possible for the collider to
             * have 0 velocity (sometimes due to improperly implemented physics
             * here, and also since the box might get pushed into something
             * that isn't moving), but since there was a collision,
             * this box needs to bounce off, at least a little (if it doesn't,
             * it will continue to get reported as a collision).
             */
            if (velVector.getMagnitude() == 0) {
                velVector.setMagnitude(0.005);
            }

            int tempAngle = (int)angleFromMe % 90;

            if (90 - Math.abs(tempAngle) < 15) {

                // If under the threshold, make box move same as pusher
                angleFromMe = 180 + mobileCollider.getHeading();
            }

            velVector.setDirection(180 + angleFromMe);
        }
    }

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
    public void updateVelocity() {

        if (velVector.getMagnitude() > 0) {
            velVector.setMagnitude(velVector.getMagnitude() * 0.1);
        }

        if (velVector.getMagnitude() < 0.01) {
            velVector.setMagnitude(0);
        }
    }

    /**
     * Undocumented
     */
    protected void runTaskStep() {
        updatePosition();
        updateVelocity();
        previousVel.setDirection(velVector.getDirection());
    }
}