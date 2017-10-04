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

import java.applet.AudioClip;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;

import javax.swing.JApplet;


/**
 * Implements the methods for operating a Lego Mindstorms robot within the Jago
 * SimBox.
 *
 * <p>
 * This class allows a robot to be operated in both a standard (idealistic)
 * mode and a realistic mode.  Changes in realistic mode:
 * </p>
 *
 * <ul>
 * <li>
 * The left and right motor implement "drag"; the robot may not drive straight.
 * </li>
 * <li>
 * Because of the drag, the robot may not pivot the exact amount specified.
 * </li>
 * <li>
 * The robot does not immediately halt when colliding with an object; it will
 * continue to try to move in the direction it was moving, according to the
 * instruction given (i.e. goForward(1000) will attempt to move for 1000ms
 * regardless of any contact).
 * </li>
 * <li>
 * Sensors attached to the robot return values more representative of the
 * actual sensor (if they implement realism)
 * </li>
 * </ul>
 *
 *
 * @author Rusl Flowers
 * @author Karl A. Gossett
 * @version 6.2.1  July 2002
 */
public class StandardRobot extends MobileElement implements Monitor, RobotState, jago.Realism {
    /** The default file name of the robot's image */
    protected static final String defaultName = "robot0.gif";
    protected java.util.List pointList;
    protected Point2D distanceTraveled;
    protected Point2D previousPoint;
    protected PolarPoint idealVelVector;

    /** The default image */
    protected String baseImageName = "robot";

    /** The sensors that are attached to the unit */
    protected StandardSensor[] inputs;

    /* TODO: Fix this to reflect an attachment location interface for
     * consistency */

    /** The location where the sensor is attached. */
    protected int[] sensorAttachment;

    /** Flags for each sensor if they were updated on the current process */
    protected boolean[] sensorUpdated;
    protected boolean drawTrails;
    protected boolean permanentTrails = false;

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

    /**
     * The voltage reading from the robot's battery.  Used in REALISTIC mode to
     * allow functions based on the battery power
     */
    protected double batteryPower = 9.5;

    /**
     * The most recently set power level.  This is equivalent to the gear on a
     * transmission; <CODE>power</CODE> measures the actual output based on
     * this gearing.
     */
    protected double currentPowerLevel = 0;

    /**
     * The amount of resistance on the left motor; this is always 0 when in
     * IDEALISTIC mode
     */
    protected double leftMotorDrag = 0;

    /** The amount to pivot during a turn */
    protected double pivotValue;

    /** The amount of resistance on the right motor; 0 when IDEALISTIC */
    protected double rightMotorDrag = 0;

    /** The last successful command to the robot. */
    protected int lastCommand = STOPPED;

    /** The level of realism, from <CODE>Realism</CODE> interface. */
    protected int realismLevel;

    /** The state of the robot, from <CODE>RobotState</CODE> */
    protected int state = STOPPED;

    /** The sensors that have not been registered with the simulation */
    private java.util.LinkedList unregisteredSensors;

    private AudioClip sound,sound2;

    /**
     * Creates an idealistic robot. This will create a robot that can have 3
     * sensors attached.  The robot must be added to a simulation before it
     * can be used.
     */
    public StandardRobot() {
        super();

        // Default to an idealistic simulation.
        realismLevel = IDEALISTIC;

        // Currently 3 inputs, but remainder of code written to allow
        // different number of inputs
        sensorUpdated = new boolean[3];
        java.util.Arrays.fill(sensorUpdated, false);
        setAppearance(defaultName);
        inputs = new StandardSensor[3];
        sensorAttachment = new int[3];

        // Initialize the sensor inputs
        for (int i = 0; i < inputs.length; i++) {
            sensorAttachment[i] = 0; // Set them all to the front-center
        }

        unregisteredSensors = new java.util.LinkedList();
        leftWheel = new RobotTracks();
        rightWheel = new RobotTracks();

        // Initialize everything for the trail drawing capability
        pointList = java.util.Collections.synchronizedList(new java.util.ArrayList());
        previousPoint = new Double(-1.0, -1.0);
        drawTrails = false;
        distanceTraveled = new Double();
        idealVelVector = new PolarPoint(0, 0);

        // Simple use of sound

     //  sound = JApplet.newAudioClip(getClass()
           //        .getResource("sounds/BOOM.AU"));

        // Need to make sure the code to play the sound is called at
        // the appropriate time.
        // Note: the use of StandardRobot.class ensures that the sound
        // will be loaded relative to THIS class, and not relative to 
        // a descendant class (using getClass() will return the class
        // of the descendant).
        sound = JApplet.newAudioClip(StandardRobot.class
                      .getResource("sounds/helmet_buzzer.au"));
        sound2 = JApplet.newAudioClip(StandardRobot.class
               .getResource("sounds/explosion2.au"));
    }

    /**
     * Returns the distance that this element has traveled as a
     * <code>Point2D</code>.
     *
     * @return The total distance moved horizontally and vertically, as a
     *         Point2D
     */
    public Point2D getDistanceMoved() {
        return distanceTraveled;
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param heading DOCUMENTATION INCOMPLETE
     *
     * @return DOCUMENTATION INCOMPLETE
     */
    public int setInitialHeading(int heading) {
        int setting = super.setInitialHeading(heading);
        idealVelVector = new PolarPoint(0, setting);

        return setting;
    }

    /**
     * Sets the new power of the mobile element.  Power is the potential of the
     * element, not the instantaneous velocity.
     *
     * @param level The power setting to use.
     */
    public void setPower(double level) {
        if (level > MAX_SPEED) {
            level = MAX_SPEED;
        }

        if (level < MIN_SPEED) {
            level = MIN_SPEED;
        }

        /* TODO: Find a better way to do this in idealistic mode.  Current
         * technique is just based on a spreadsheet analysis to get values
         * that are good enough.
         */
        if ((realismLevel == REALISTIC) || (realismLevel == FIXED_REALISTIC)) {
            // Left as an if--in case want to add another level of realism

            /* Using a non-linear equation to model the relationship between
             * the power setting and speed (in m/s).  The regression was based
             * on readings taken with a battery power of 7.6
             */
            power = ((Math.sqrt(level + 1) * 0.025) + 0.07) * impactOfBatteryPower();

            /* TODO: Fix this to match the power settings of the robot when set
             * from 0-10. For the moment, just force a small power if set to 0.
             */
        } else {
            // default is IDEALISTIC
            power = (level + 1) / 30.0;
        }

        if (power < 0.05) {
            power = 0.05;
        }

        currentPowerLevel = level;
    }

    /**
     * Sets the level of realism.
     *
     * @param level A realism level from <CODE>jago.Realism</CODE>
     */
    public void setRealismLevel(int level) {
        if ((level == REALISTIC) || (level == IDEALISTIC)
                || (level == FIXED_REALISTIC)) {
            realismLevel = level;

            for (int i = 0; i < inputs.length; i++) {
                if (inputs[i] == null) {
                    continue;
                }

                inputs[i].setRealismLevel(level);
            }

            if (level == FIXED_REALISTIC) {
                // Create a battery power and drag that are not random, but
                // not idealistic either
                batteryPower = batteryPower - (0.1 * 2.5);
                leftMotorDrag = 0.3 / 200.0;
                rightMotorDrag = 0.8 / 200.0;
            }

            if (level == REALISTIC) {
                // Create a battery power between 9.5 and 7.0
                batteryPower = batteryPower - (Math.random() * 2.5);
                // Create drift of a max of 1 degree / 200 ms on each side
                leftMotorDrag = Math.random() / 200.0;
                rightMotorDrag = Math.random() / 200.0;
            }

            // Update the power to the new setting.
            setPower(currentPowerLevel);
        }

        realismSet = true;
    }

    /**
     * Returns the level of realism as a constant from
     * <CODE>jago.Realism</CODE>.
     *
     * @return The level of realism.
     */
    public int getRealismLevel() {
        return realismLevel;
    }

    /**
     * Returns the value of the sensor connected to the specified input Return
     * SensorMonitor.NOT_CONNECTED if there is no sensor, or the input is out
     * of range.
     *
     * @param inputNumber The input, one of <CODE>RCX_1, RCX_2, RCX_3</CODE>
     *
     * @return The value of the input
     */
    public int getSensorInput(int inputNumber) {
        int value;

        if ((inputNumber >= 0) && (inputNumber <= inputs.length)
                && (inputs[inputNumber] != null)) {
            value = inputs[inputNumber].getValue();
        } else {
            System.err.println("No sensor found on input " + inputNumber);
            value = RobotSensors.NOT_CONNECTED;
        }

        return value;
    }

    /**
     * Connects a sensor to the specified sensor input.
     *
     * @param input The input number to add to, one of <CODE>RCX_1, RCX_2,
     *        </CODE>or <CODE>RCX_3</CODE>
     * @param sensorType The type of sensor
     */
    public void addSensor(int input, int sensorType) {
        addSensor(input, StandardSensorFactory.createSensor(sensorType, this));
    }

    /**
     * Adds the specified <CODE>StandardSensor</CODE> to this robot on the
     * specified input port.
     *
     * @param input The input port where the sensor should be connected
     * @param sensor A sensor to add (generally created using the
     *        <CODE>StandardSensorFactory</CODE>)
     */
    public void addSensor(int input, StandardSensor sensor) {
        if ((input < 0) || (input > inputs.length)) {
            return;
        }

        inputs[input] = sensor;
        sensor.setMonitor(this);

        // If it is a Sensor, then no realistic sensor available
        if (inputs[input] instanceof Sensor) {
            updateImage();
        }

        sensorAttachment[input] = 0;
        unregisteredSensors.add(inputs[input]);
        updateSensorAttachments();
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
    public void draw(java.awt.Graphics2D g2) {
        for (int i = 0; i < inputs.length; i++) {
            // Allow for sensors that don't have a visual representation
            if (inputs[i] instanceof Visible) {
                ((Visible) inputs[i]).draw(g2);
            }
        }

        // Draw the trails, if using them
        synchronized (pointList) {
            java.util.Iterator iter = pointList.iterator();

            while (iter.hasNext()) {
                Point2D drop = (Point2D) iter.next();
                g2.setColor(java.awt.Color.green);
                g2.drawRect((int) drop.getX(), (int) drop.getY(), 2, 2);
            }
        }

        super.draw(g2);
    }

    /**
     * If <CODE>true</CODE> is passed, this ArtistBot will begin leaving a
     * trail of green dots as it moves.
     *
     * @param drawing DOCUMENT ME!
     */
    public void drawTrails(boolean drawing) {
        drawTrails = drawing;
    }
    
    public void setPermanentTrails(boolean drawing) {
    	permanentTrails = drawing;
    	drawTrails = drawing;
    }

    /**
     * For simulation internal use only.
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * Causes the robot to go backward. The robot will continue to travel
     * backwards until another movement command is issued (including halt).
     */
    public void goBackward() {
        setState(GOING_BACKWARD);
        leftWheel.setInputRPM(-currentPowerLevel * 35);
        rightWheel.setInputRPM(-currentPowerLevel * 35);
    }

    /**
     * Causes the robot to move backward for the specified number of
     * milliseconds or until a collision occurs, whichever occurs first, and
     * stop.
     *
     * <p>
     * If the time to travel is less than a simulation update cycle (which is
     * generally > 50ms), the robot will move abnormally slow.  This occurs
     * because it does not take full advantage of a simulation cycle, and much
     * of the time is wasted.  Although it will travel the appropriate
     * distance (1000ms = 1000 x 1ms), it will take significantly longer in
     * the simulation.
     * </p>
     *
     * @param milliseconds The number of milliseconds to travel
     */
    public void goBackward(int milliseconds) {
        timeLeftToMove = milliseconds;
        goBackward();

        while (timeLeftToMove > 0) {
            try {
                // Sleep for the period of one update
                Thread.sleep(Math.min(timeLeftToMove, (int) host.getDelay()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        halt();
    }

    /**
     * Causes the robot to go forward. The robot will continue to travel
     * forward until another movement command is issued.
     */
    public void goForward() {
        setState(GOING_FORWARD);
        leftWheel.setInputRPM(currentPowerLevel * 35);
        rightWheel.setInputRPM(currentPowerLevel * 35);
    }
     // end goForward

    /**
     * Causes the robot to move forward for the specified number of
     * milliseconds or until a collision occurs, whichever occurs first, and
     * stop.
     *
     * <p>
     * If the time to travel is less than a simulation update cycle (which is
     * generally > 50ms), the robot will move abnormally slow.  This occurs
     * because it does not take full advantage of a simulation cycle, and much
     * of the time is wasted.  Although it will travel the appropriate
     * distance (1000ms = 1000 x 1ms), it will take significantly longer in
     * the simulation.
     * </p>
     *
     * @param milliseconds The number of milliseconds to travel
     */
    public void goForward(int milliseconds) {
        timeLeftToMove = milliseconds;
        goForward();

        while (timeLeftToMove > 0) {
            try {
                // Sleep for the period of one update
                Thread.sleep(Math.min(timeLeftToMove, (int) host.getDelay()/2));
            } catch (Exception e) {
               // e.printStackTrace();
            }
        }

        halt();
    }

    /**
     * Stops the robot from moving, unless the robot is pivoting. If the robot
     * is pivoting, then the robot will wait for the pivot to complete and
     * then halt.
     */
    public void halt() {
        while ((state == PIVOTING_RIGHT) || (state == PIVOTING_LEFT)) {
            try {
                Thread.sleep((int) host.getDelay()/2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setState(STOPPED);
        leftWheel.setInputRPM(0);
        rightWheel.setInputRPM(0);
    }

    /**
     * Performs actions when a collision occurs. The current implementation
     * assumes that any TOUCH sensor will react to any collision on the front
     * of the robot, and that any light sensor will react only to reflections
     * near the front center of the robot.
     *
     * @param collisionElement A collidable actor to react to
     */
    public synchronized void handleCollision(Collidable collisionElement) {
        if (collisionElement.equals(this)) {
            System.err.println("Getting a collision with myself.");

            return;
        }

        /* TODO: When updated to 3-dimensional, should be able to eliminate
         * the FlatOject, which would change the collision handling here (since
         * a flat object would be defined by height.
         */
        /* If collided with a FlatObject, just keep moving, with no reaction.
         */
        if ((collisionElement instanceof jago.element.FlatObject)|| (collisionElement instanceof jago.element.Fire)) {
            return;
        }

        /* The potential state derived from the result of the collision */
        int proposedState = state;
        Rectangle2D me = getBounds2D();
        Rectangle2D other = collisionElement.getBounds2D();

        // Get a rectangle that defines the area where the collision occurred
        Rectangle2D intersection = new java.awt.geom.Rectangle2D.Double();
        Rectangle2D.intersect(me, other, intersection);

        // Possible that intersection width is 0 - if reported by a sensor
        if (intersection.getWidth() == 0) {
            intersection = other;
        }

        // Get the center point of where I was before the collision
        Rectangle2D lastBounds = previousShape.getBounds2D();
        double myCenterX = lastBounds.getCenterX();
        double myCenterY = lastBounds.getCenterY();
        Point2D myCenter = new Double(myCenterX, myCenterY);

        // Get the center point of the actual area where the actors collided
        Point2D intersectCenter = new Double(intersection.getCenterX(),
                                                     intersection.getCenterY());

        // Determine the components between the Robot and point of collision
        double distance = previousLocation.distance(intersectCenter);
        intersectCenter.setLocation(intersectCenter.getX() - myCenterX,
                                    intersectCenter.getY() - myCenterY);

        double angleFromMe; // Between center of robot and center of collision
        angleFromMe = Math.toDegrees(Math.atan2(intersectCenter.getX(),
                                                -intersectCenter.getY()));

        // drop references
        me = other = intersection = null;

        // Calculate the direction: Positive angles to the right, negative left
        double diffAngle = angleFromMe - velVector.getDirection();

        // Angle is too large, wrap around to the negative side (left)
        if (diffAngle > 180) {
            diffAngle -= 360;
        }

        // Angle is too small, wrap around to positive side (right)
        if (diffAngle <= -180) {
            diffAngle += 360;
        }

        /* Although the following sets the touch sensors ON, any touch sensor
         * set in REALISTIC mode ignores that call, so it has no impact.
         */

        // Hit directly in front
        if (Math.abs(diffAngle) < 10) {
            // Hit in front, so turn on all front bumper sensors
            for (int i = 0; i < inputs.length; i++) {
                if (inputs[i] == null) {
                    continue;
                }

                int current = inputs[i].getType();

                if ((current == RobotSensors.TOUCH)
                        || (current == RobotSensors.INVERTED_FRONT_BUMPER)) {
                    inputs[i].setValue(Sensor.ON);
                }
            }

            if (state != GOING_BACKWARD) {
                proposedState = BLOCKED;
            }

            // end direct frontal hit
        } else if ((diffAngle > -45) && (diffAngle <= -10)) {
            // Hit on front left
            for (int i = 0; i < inputs.length; i++) {
                if (inputs[i] == null) {
                    continue;
                }

                int current = inputs[i].getType();

                if ((current == RobotSensors.TOUCH)
                        || (current == RobotSensors.INVERTED_FRONT_BUMPER)) {
                    inputs[i].setValue(Sensor.ON);
                }
            }

            if (state != GOING_BACKWARD) {
                proposedState = BLOCKED;
            }
        } else if ((diffAngle >= 10) && (diffAngle < 45)) {
            // Hit on front right
            for (int i = 0; i < inputs.length; i++) {
                if (inputs[i] == null) {
                    continue;
                }

                int current = inputs[i].getType();

                if ((current == RobotSensors.TOUCH)
                        || (current == RobotSensors.INVERTED_FRONT_BUMPER)) {
                    inputs[i].setValue(Sensor.ON);
                }
            }

            if (state != GOING_BACKWARD) {
                proposedState = BLOCKED;
            }
        } else if ((diffAngle <= -45) && (diffAngle > -90)) {
            if ((state == GOING_FORWARD) || (state == PIVOTING_RIGHT)) {
                proposedState = BLOCKED;
            }
        } else if ((diffAngle >= 45) && (diffAngle < 90)) {
            if ((state == GOING_FORWARD) || (state == PIVOTING_LEFT)) {
                proposedState = BLOCKED;
            }
        } else if (Math.abs(diffAngle) >= 90) {
            if (state != GOING_FORWARD) {
                proposedState = BLOCKED;
            }
        }

        /* TODO: Update for weight, physical collision.  Temporarily, assume
         * that the robot is heavier than any pushable object
         */
        if (collisionElement instanceof Pushable) {
            proposedState = state;
        }

        // In IDEALISTIC mode, always do the proposed action
        if (realismLevel == IDEALISTIC) {
            state = proposedState;
        }

        // Go back to previous
        if (proposedState == BLOCKED) {
            rollbackPosition();
        }

        /* Note: In REALISTIC mode, the state is not changed upon a collision.
         * this causes the robot to continue to try to move in the original
         * direction until the command is completed.
         */
    }

    /**
     * Turns the robot the number of specified degrees to the left. The robot
     * will stop if it is currently moving and then pivot.
     *
     * @param degrees The number of degrees to turn.
     */
    public void pivotLeft(int degrees) {
        /*
         * If the robot is not currently pivoting, then pivot. Else wait for
         * the current pivot to complete before pivoting again.
         */
        if (degrees > 0) {
//            lastUpdateTime = host.elapsedTimeMillis();
            pivotValue = degrees
                         - ((rightMotorDrag * degrees)
                         - (leftMotorDrag * degrees));
            setState(PIVOTING_LEFT);
        }

        leftWheel.setInputRPM(-currentPowerLevel * 35);
        rightWheel.setInputRPM(currentPowerLevel * 35);

        // Added in Version 6.0 to ensure command finishes before exiting
        halt();
    }

    /**
     * Pivots the robot the number of specified degrees to the right.  The
     * robot will stop if it is currently moving and then pivot.
     *
     * @param degrees The number of degrees to turn.
     */
    public void pivotRight(int degrees) {
        /*
         * If the robot is not currently pivoting, then pivot. Otherwise, wait
         * for the current pivot to complete before pivoting again.
         */
        if (degrees > 0) {
            pivotValue = degrees
                         - ((leftMotorDrag * degrees)
                         - (rightMotorDrag * degrees));
            setState(PIVOTING_RIGHT);
        }

        leftWheel.setInputRPM(currentPowerLevel * 35);
        rightWheel.setInputRPM(-currentPowerLevel * 35);

        // Added in version 6.0 to ensure command waits until complete
        halt();
    }

    /**
     * Notifies this SimElement that it was removed from the simulation.
     */
    public void removedFromSim() {
        // Make sure all my sensors are removed from the sim as well
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] != null) {
                host.remove(inputs[i]);
            }
        }

        super.removedFromSim();
    }

    /**
     * Define the behavior of the robot here based upon its current state.
     */
    public synchronized void updatePosition() {
        if (pendingCollision || host == null) {
            return;
        }

        if (previousPoint.getX() == -1) {
            previousPoint.setLocation(location);
        }

        // Add the ability to draw a trail behind the robot
        if (location.distance(previousPoint) > 5) {
            if (drawTrails) {
                Point2D tempLoc = new Double();
                Rectangle2D bounds = this.getBounds2D();
                tempLoc.setLocation(bounds.getCenterX(), bounds.getCenterY());
                if (permanentTrails) {
                	host.add(new ColoredCircle(3,ReflectiveColors.GREEN),tempLoc.getX(),tempLoc.getY());
                }
                synchronized (pointList) {
                    pointList.add(tempLoc);
                }
            }

            previousPoint.setLocation(location);
        }

        previousVel = (PolarPoint) velVector.clone();

        // If attempting to move the robot, disable ability to set the heading
        if (state != STOPPED) {
            headingSet = true;
            realismSet = true;
        }

        double changeFromDrag = (leftMotorDrag - rightMotorDrag) / 10;
        double adjustment = changeFromDrag * (((timeLeftToMove > host.getDelay())
                                              || (timeLeftToMove == 0))
                                              ? host.getDelay() : timeLeftToMove);

        // Act on the state
        switch (state) {
        case GOING_FORWARD:

            //updateDistanceMoved(power);
            if (host!=null && host.isPlayMusic()) {
              sound.play();
            }
            if (changeFromDrag >= 0) {
                changeAzimuth(adjustment, PIVOTING_LEFT);
            } else {
                changeAzimuth(Math.abs(adjustment), PIVOTING_RIGHT);
            }

            velVector.setMagnitude(power);

            break;

        case GOING_BACKWARD:

            //updateDistanceMoved(-power);
            if (changeFromDrag >= 0) {
                changeAzimuth(adjustment, PIVOTING_RIGHT);
            } else {
                changeAzimuth(Math.abs(adjustment), PIVOTING_LEFT);
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

            //idealVelVector.setMagnitude(0);
            if (pivotValue > 0) {
                /* Set the amount to rotate, based on the power of the
                 * robot.
                 */
                double rotate = power;

                // Ensure we don't pivot too far
                if ((pivotValue - rotate) < 0) {
                    rotate = pivotValue;
                }

                //idealVelVector.addToDirection(state==PIVOTING_RIGHT?rotate:-rotate);
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

    /**
     * Updates all the sensors.  This method will be called by the simulation
     * when it requires sensors to be updated.
     */
    public void updateSensors() {
        for (int inp = 0; inp < inputs.length; inp++) {
            // For now, just reset touch sensors if necessary

            /* TODO: Fix the sensor model so updateSensors isn't required.
             * This either needs to be expanded to include touch sensors in
             * more locations (currently only looks in front), or update sensor
             * model so this isn't required.
             * This version resets the sensor every time if the robot is going
             * forward--may cause false "off" if the reading is taken during
             * an update cycle.
             */
            if ((((state != BLOCKED) && (state != STOPPED)
                    && (lastCommand != state)) || (state == GOING_FORWARD))
                    && inputs[inp] instanceof TouchSensor) {
                inputs[inp].reset();
            }
        }
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
        while (pendingCollision) {
            try {
                // Block here if pending collision, notified when collision
                // is handled
                wait();
            } catch (InterruptedException e) {
                // Just ignore the interrupt for now
            }
        }

        if ((state == GOING_FORWARD) || (state == GOING_BACKWARD)) {
            lastCommand = state;
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
     * Causes robot to continue its last instruction until <i>milliseconds</i>
     * have passed, and then it stops.
     *
     * @param milliseconds Stop after this many milliseconds
     */
    protected void haltAfter(int milliseconds) {
        waitFor(milliseconds);
        halt();
    }

    /**
     * Determines the impact that battery power has, between idealistic and
     * realistic mode.
     *
     * @return A scaling factor 0.0-1.0, where <CODE>1.0</CODE> is no impact
     */
    protected double impactOfBatteryPower() {
        double impact = 1.0;

        if ((realismLevel == REALISTIC) || (realismLevel == FIXED_REALISTIC)) {
            impact = (batteryPower * 0.11) - 0.05;
        }

        return impact;
    }

    /**
     * Undocumented
     */
    protected void rollbackPosition() {
        location.setLocation(previousLocation);
        myShape = (java.awt.geom.Area) previousShape.clone();

        // Copy direction only, so magnitude doesn't get undone
        velVector.setDirection(previousVel.getDirection());
    }

    /**
     * Undocumented
     */
    protected void runTaskStep() {
        // Register (with the SimEngine) any sensors that haven't
        // been registered
        while (!unregisteredSensors.isEmpty()) {
            StandardSensor s = (StandardSensor) unregisteredSensors.getFirst();
            host.add(s, s.getPosition().getX(), s.getPosition().getY());
            unregisteredSensors.removeFirst();
        }

        updateVelocity();
        updatePosition();
    }

    /* TODO: Make this work correctly--right now a quick fix hardcoding. */

    /**
     * Updates the sensor locations.
     */
    protected void updateSensorAttachments() {
        final int FRONT_CENTER = 0;
        final int FRONT_RIGHT = 1;
        final int RIGHT_SIDE = 2;
        final int REAR_RIGHT = 3;
        final int REAR_CENTER = 4;
        final int REAR_LEFT = 5;
        final int LEFT_SIDE = 6;
        final int FRONT_LEFT = 7;
        double mySizeX = size.getWidth() / 2.0;
        double mySizeY = size.getHeight() / 2.0;
        double radius = Point2D.distance(0, 0, mySizeX, mySizeY);
        double angle;
        double touchSize = 0;

        // If has touch/HIT sensor, refer size as larger to force light sensor
        // to attach at the outside of the touch/HIT sensor
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] instanceof TouchSensor) {
                if (inputs[i].getType() == RobotSensors.INVERTED_FRONT_BUMPER) {
                    // On the inverted front bumper, light sensors attach between
                    // the sensor and the RCX, so no adjustments here
                    touchSize = 0;
                } else {
                    touchSize = Math.max(touchSize,
                                         inputs[i].getSize().getHeight());
                }
            }
        }

        for (int i = 0; i < sensorAttachment.length; i++) {
            // No sensor attached, so move on
            if (inputs[i] == null) {
                continue;
            }

            int position = sensorAttachment[i];

            // TODO: Eventually fix this to use an AttachmentPoints interface
            // with constants for the different attachment points
            switch (position) {
            case FRONT_CENTER:
                angle = 0;
                radius = Math.ceil(mySizeY);

                // Move a HIT Sensor back
                if (inputs[i].getType() == RobotSensors.HIT) {
                    radius -= 4;
                }

                break;

            case FRONT_RIGHT:
                angle = 15;
                radius -= 2;

                break;

            case RIGHT_SIDE:
                angle = 90;
                radius = mySizeX;

                break;

            case REAR_RIGHT:
                angle = 165;
                radius -= 2;

                break;

            case REAR_CENTER:
                angle = 180;
                radius = mySizeY;

                break;

            case REAR_LEFT:
                angle = 195;
                radius -= 2;

                break;

            case LEFT_SIDE:
                angle = 270;
                radius = mySizeX;

                break;

            case FRONT_LEFT:
                angle = 345;
                radius -= 2;

                break;

            default:
                angle = 0;
                radius = Math.ceil(mySizeY);

                break;
            }

            // TODO: Complete the attachment points
            switch (inputs[i].getType()) {
            case RobotSensors.LIGHT:
                radius += (touchSize + 1);
            }

            inputs[i].setReference(radius, angle);
        }
    }

    /**
     * Updates the image to reflect any sensors that were added
     */
    void updateImage() {
        int total = 0;

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] == null) {
                continue;
            }

            if ((inputs[i].getType() != Sensor.NO_SENSOR)
                    && inputs[i] instanceof Sensor) {
                total += (int) Math.pow(2.0, inputs[i].getType());
            }
        }

        String filename = baseImageName + total + ".gif";
        setAppearance(filename);

        // Update the bounding Area to the new area.
        createArea();
    }
}
