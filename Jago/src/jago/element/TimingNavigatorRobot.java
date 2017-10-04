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
package jago.element;


/**
 * Implements the methods for operating a Lego Mindstorms robot
 * within the Jago Simulation.  This implementation includes a Light
 * Sensor on RCX_1 because it requires it for calibration.  The
 * other two inputs (RCX_2 and 3) are left open.
 *
 * <p>
 * This class allows a robot to be operated in both a standard
 * (idealistic) mode and a realistic mode.  Changes in realistic
 * mode:
 * </p>
 *
 * <ul>
 * <li>
 * The left and right motor implement "drag"; the robot may not drive
 * straight.
 * </li>
 * <li>
 * Because of the drag, the robot may not pivot the exact amount
 * specified.
 * </li>
 * <li>
 * The robot does not immediately halt when colliding with an object;
 * it will continue to try to move in the direction it was moving,
 * according to the instruction given (i.e. goForward(1000) will
 * attempt to move for 1000ms regardless of any contact).
 * </li>
 * <li>
 * Sensors attached to the robot return values more representative of
 * the actual sensor (if they implement realism)
 * </li>
 * </ul>
 *
 *
 * @author Rusl Flowers
 * @author Karl A. Gossett
 * @version 6.2.1  July 2002
 */
public class TimingNavigatorRobot extends StandardRobot {
    private long lastNavTime = NOT_STARTED;

    /**
     * Creates an idealistic robot. This will create a robot that can
     * have 3 sensors attached.  The robot must be added to a
     * simulation before it can be used.
     */
    public TimingNavigatorRobot() {
        super();
    }

    /**
     * Returns the distance that this element has traveled as a
     * <code>Point2D</code>.
     *
     * @return The total distance moved horizontally and vertically,
     *         as a Point2D
     */
    public java.awt.geom.Point2D getDistanceMoved() {
        return distanceTraveled;
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param scale DOCUMENTATION INCOMPLETE
     */
    public void setMovementScale(double scale) {
        MOVEMENT_SCALE = scale;
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @return DOCUMENTATION INCOMPLETE
     */
    public double getMovementScale() {
        return MOVEMENT_SCALE;
    }

    /**
     * Calibrate the forward/backward movement of the robot.  This
     * requires two black lines a specified distance apart, with a
     * background as close to white as possible (it will accept many
     * other background colors, but white will ensure the best
     * results).
     *
     * @param inches The number of inches between the two calibration
     *        lines
     */
    public void calibrateDistance(int inches) {
        long startTime = 0;
        long stopTime = 0;
        setPower(10);
        goForward();

        while (getSensorInput(LegoRobot.RCX_1) > 35) {}

        while (getSensorInput(LegoRobot.RCX_1) <= 35) {}

        startTime = System.currentTimeMillis();

        while (getSensorInput(LegoRobot.RCX_1) > 35) {}

        stopTime = System.currentTimeMillis();
        halt();
        MOVEMENT_SCALE = (stopTime - startTime) / inches;
    }

    /**
     * This simulates the rotational calibration of the robot (since
     * rotation is already tracked via headings).
     */
    public void calibrateRotation() {
        setPower(10);

        while (getSensorInput(LegoRobot.RCX_1) > 35) {
            pivotRight(3);
        }

        while (getSensorInput(LegoRobot.RCX_1) <= 35) {
            pivotRight(5);
        }

        while (getSensorInput(LegoRobot.RCX_1) > 35) {
            pivotRight(2);
        }
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param angle DOCUMENTATION INCOMPLETE
     */
    public void gotoAngle(float angle) {
        // in future, use modulo instead of while loop???
        //System.err.println("Angles: " + angle + " current: " + velVector.getDirection());
        double difference = angle - velVector.getDirection();

        //System.err.print("Turning " + difference);
        while (difference > 180) {
            difference = difference - 360; // shortest path to goal angle
        }

        while (difference < -180) {
            difference = difference + 360; // shortest path to goal angle
        }

        //System.err.println(" which translated to " + difference);
        if (difference >= 0) {
            pivotRight((int) difference);
        } else {
            pivotLeft((int) -difference);
        }
    }

    /**
     * Rotates the RCX robot towards the target point and moves the
     * required distance.
     *
     * @param x The x coordinate to move to.
     * @param y The y coordinate to move to.
     */
    public void gotoPoint(float x, float y) {
        // Determine relative points
        double x1 = x - distanceTraveled.getX();
        double y1 = y - distanceTraveled.getY();

        // Note: since vert axis is reverse of standard, negate y1
        y1 = -y1;

        //System.err.println("Going relative ("+x1 + ", " + y1 +")");
        // Calculate angle to go to:
        double angle = Math.atan2(y1, x1);

        // Calculate distance to travel:
        double inchDistance;

        //System.err.println("  and angle of " + Math.toDegrees(angle));
        if (y1 != 0) {
            inchDistance = y1 / Math.sin(angle);
        } else {
            inchDistance = x1 / Math.cos(angle);
        }

        //System.err.println("   and I am " + inchDistance + " away.");
        angle = Math.toDegrees(angle) + 90;

        // Now convert theory into action:
        gotoAngle((float) angle);
        travelForward(Math.round(inchDistance));
    }

    /**
     * Resets the location counter to (0,0)
     */
    public void resetLocation() {
        distanceTraveled.setLocation(0.0, 0.0);
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param distance The number of inches to back up
     */
    public void travelBackward(double distance) {
        goBackward((int) (distance * MOVEMENT_SCALE));
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param distance DOCUMENTATION INCOMPLETE
     */
    public void travelForward(double distance) {
        goForward((int) (distance * MOVEMENT_SCALE));
    }

    /**
     * Define the behavior of the robot here based upon its current
     * state.
     */
    public synchronized void updatePosition() {
        if (pendingCollision) {
            return;
        }

        if (previousPoint.getX() == -1) {
            previousPoint.setLocation(location);
        }

        // Act on the state
        switch (state) {
            case GOING_FORWARD:
                updateDistanceMoved(power);

                break;

            case GOING_BACKWARD:
                updateDistanceMoved(-power);

                break;

            case STOPPED:

            // fall through to PIVOTING_LEFT
            case BLOCKED:

            // fall through to PIVOTING_LEFT
            case PIVOTING_RIGHT:

            // fall through to PIVOTING_LEFT
            case PIVOTING_LEFT:
                idealVelVector.setMagnitude(0);

                if (pivotValue > 0) {
                    /* Set the amount to rotate, based on the power of the
                     * robot.
                     */
                    double rotate = power * 15;

                    // Ensure we don't pivot too far
                    if ((pivotValue - rotate) < 0) {
                        rotate = pivotValue;
                    }

                    idealVelVector.addToDirection((state == PIVOTING_RIGHT)
                                                  ? rotate : (-rotate));
                }

                break;

            default:
                break;
        }

        // Now just update as a StandardRobot
        super.updatePosition();
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param power DOCUMENTATION INCOMPLETE
     */
    protected void updateDistanceMoved(double power) {
        idealVelVector.setMagnitude(power);

        /* TODO: Update the exit criteria to skip an update.
         * If pending a collision, don't update position until resolved.
         * (host==null is a hack due to sequencing problems when removing
         *  elements).
         */
        if ((host == null) || pendingCollision) {
            return;
        }

        long deltaTime = 0;

        if (lastNavTime == NOT_STARTED) {
            lastNavTime = host.elapsedTimeMillis();
        }

        long elapsedTime = host.elapsedTimeMillis();
        deltaTime = (elapsedTime - lastUpdateTime);

        /* Determine the amount of time it is actually moving during this update.
         * Since the movement algorithm does not allow movement of less than 5ms,
         * maintain that here.
         */
        if (timeLeftToMove > 0) {
            if (timeLeftToMove < deltaTime) {
                if (timeLeftToMove < 5) {
                    deltaTime = 5;
                } else {
                    deltaTime = timeLeftToMove;
                }
            }
        }

        /* Determine component distances to move based on velocity.
         * Since MOVEMENT_SCALE is ms/inch (at a power of 10), the factor based
         * on the power (idealVelVector) is a percentage of 10 for each component.
         */
        double xMove = deltaTime / MOVEMENT_SCALE * (idealVelVector
                                                     .getX() / idealVelVector
                                                               .getMagnitude());
        double yMove = deltaTime / MOVEMENT_SCALE * (idealVelVector
                                                     .getY() / idealVelVector
                                                               .getMagnitude());

        distanceTraveled.setLocation(distanceTraveled.getX() + xMove,
                                     distanceTraveled.getY() - yMove);
    }
}
