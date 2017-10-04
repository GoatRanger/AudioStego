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
 * DistanceTrackerRobot.java
 *
 * Created on February 10, 2003, 9:39 PM
 */

package jago.element;


/**
 *
 * @author  Karl Gossett
 */
public class DistanceTrackerRobot extends StandardRobot {

    protected java.awt.geom.Point2D distanceMoved;

    /** Creates a new instance of DistanceTrackerRobot */
    public DistanceTrackerRobot() {
        super();
        distanceMoved = new java.awt.geom.Point2D.Double(0,0);
    }

    /**
     * Define the behavior of the robot here based upon its current state.
     */
    public synchronized void updatePosition() {

        if (pendingCollision) {

            return;
        }

        if (previousPoint.getX() == -1) {
            previousPoint.setLocation(location);
        }

        // Add the ability to draw a trail behind the robot
        if (location.distance(previousPoint) > 5) {

            if (drawTrails) {

                java.awt.geom.Point2D tempLoc = new java.awt.geom.Point2D.Double();
                java.awt.geom.Rectangle2D bounds = this.getBounds2D();
                tempLoc.setLocation(bounds.getCenterX(),
                                    bounds.getCenterY());

                synchronized (pointList) {
                    pointList.add(tempLoc);
                }
            }

            previousPoint.setLocation(location);
        }

        previousVel = (PolarPoint)velVector.clone();

        // If attempting to move the robot, disable ability to set the heading
        if (state != STOPPED) {
            headingSet = true;
            realismSet = true;
        }

        double changeFromDrag = (leftMotorDrag - rightMotorDrag)/10;
        double adjustment = changeFromDrag * ((timeLeftToMove > host.getDelay()
                                                || timeLeftToMove == 0)
                                                  ? host.getDelay()
                                                  : timeLeftToMove);
        // Act on the state
        switch (state) {

            case GOING_FORWARD:
                updateDistanceMoved(power);
                //sound.play();
                if (changeFromDrag >= 0) {
                    changeAzimuth(adjustment, PIVOTING_LEFT);
                } else {
                    changeAzimuth(Math.abs(adjustment),
                                  PIVOTING_RIGHT);
                }

                velVector.setMagnitude(power);

                break;

            case GOING_BACKWARD:
                updateDistanceMoved(-power);
                if (changeFromDrag >= 0) {
                    changeAzimuth(adjustment, PIVOTING_RIGHT);
                } else {
                    changeAzimuth(Math.abs(adjustment),
                                  PIVOTING_LEFT);
                }

                velVector.setMagnitude(-power);

                break;

            case STOPPED:

            // fall through to blocked
            case BLOCKED:
                velVector.setMagnitude(0);
                timeLeftToMove = 0;

                //sound.stop();
                break;

            case PIVOTING_RIGHT:

            // fall through to PIVOTING_LEFT
            case PIVOTING_LEFT:
                velVector.setMagnitude(0);

                if (pivotValue > 0) {

                    /* Set the amount to rotate, based on the power of the
                     * robot.
                     */
                    double rotate = power * 15;

                    // Ensure we don't pivot too far
                    if (pivotValue - rotate < 0) {
                        rotate = pivotValue;
                    }

                    changeAzimuth(rotate + adjustment, state);
                    pivotValue = pivotValue - rotate;
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
        super.updatePosition();
    }


    protected void updateDistanceMoved(double power) {
        PolarPoint localVector = new PolarPoint(velVector);
        localVector.setMagnitude(power);
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
            }
        } else deltaTime = 0;

        // Determine component distances to move based on velocity
        double xMove = (deltaTime / 1000.0) * SIMULATION_SCALE * velVector.getX();
        double yMove = ((deltaTime / 1000.0) * SIMULATION_SCALE * velVector.getY());
        distanceMoved.setLocation(distanceMoved.getX() + xMove, distanceMoved.getY() + yMove);
    }

    public java.awt.geom.Point2D getDistanceMoved() {
        return distanceMoved;
    }
}
