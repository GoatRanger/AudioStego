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
 * RobotBumper.java
 *
 * Created on January 19, 2002, 11:02 PM
 */
package jago.element;


//import jago.ImageLoader;

/**
 * <p>
 * This class implements a touch sensor that can be used to detect and react
 * to collisions with {@link Collidable} objects.  Version 1.0 of the
 * TouchSensor only implements a full front bumper effectively, and has not
 * been tested with any other configuration.  Future version will allow more
 * variation in the location and type of sensors used.
 * </p>
 * <br>
 * <h5>
 * <h6>
 *
 * <ul>
 * <li>
 * The host of a <code>TouchSensor</code> is responsible for resetting the
 * value of the sensor.  A <code>TouchSensor</code> will always turn ON due
 * to a collision, but does not automatically reset itself.
 * </li>
 * <li>
 * Although it varies between <code>Monitor</code>s, a standard
 * <code>Monitor</code> like the <code>StandardRobot</code> only turns a
 * <code>TouchSensor</code> off when it attempts to change direction. (see
 * the individual implementations for more details)
 * </li>
 * </ul>
 *
 * <h6>
 *
 * <ul>
 * <li>
 * The sensor resets itself automatically. The most significant implication is
 * that the sensor cannot be trusted to <i>stay</i><code>ON</code>, even when
 * pushing against another object.
 * </li>
 * <li>
 * The sensor will allow its host <code>Monitor</code> to set its state to
 * <code>ON</code>, but not <code>OFF</code>.  This allows a touch sensor to
 * report the correct value under varying conditions, most importantly when
 * something else is attached to the front of the touch sensor (if the touch
 * sensor is used as a front bumper for some mobile object). See {@link
 * #setValue} for more details.
 * </li>
 * </ul>
 *
 *
 * @version 1.0
 * @author Karl A. Gossett
 */
public class TouchSensor
    extends jago.element.StandardSensor {

    protected double headingAdjust;
    protected boolean hitSomething = false;
    protected String imageName = "robot_bumper_full.gif";
    protected boolean reset = false;
    protected int resetDelay = 0;

    public TouchSensor(int type) {
        super();

        // Default adjustment is front/center
        headingAdjust = 0;

        // Eventually add switches for different bumpers
        if (type == INVERTED_FRONT_BUMPER) {
            imageName = "robot_bumper_inverted.gif";
        }

        this.type = type;
        setAppearance(imageName);
    }

    public TouchSensor(int type, Monitor host) {
        this(type);
        value = NOT_READY;

        // Set it to some location off the screen
        location = new java.awt.geom.Point2D.Double(-1000, -1000);
        setMonitor(host);
        myShape = new java.awt.geom.Area(new java.awt.geom.Rectangle2D.Double(
                                   location.getX(),
                                   location.getY(),
                                   size.getWidth(),
                                   size.getHeight()));
        previousLocation = new java.awt.geom.Point2D.Double();
        previousLocation.setLocation(location);
        updatePosition();

        if (hostMonitor instanceof SimElement) {
            ((SimElement)hostMonitor).attach(myShape);
        }
    }

    /**
     * Sets the picture of any object using this interface to the image
     * identified by the URL provided.
     *
     * @param filename     The URL of the picture to use.
     */
    public void setAppearance(String filename) {
        appearance = jago.ImageLoader.getImage(this, "images/"+filename);

        if (appearance != null) {
            size = new java.awt.Dimension(appearance.getWidth(host),
                                 appearance.getHeight(host));
        }
    }

    /**
     * Sets the state of the touch sensor, if in <CODE>IDEALISTIC</CODE> mode.
     * As of Jago v6.2.3, this method is primarily used to ensure the touch
     * sensor can be notified that it <i>should</i> have collided.  Although
     * normally not required, a situation that might require external
     * notification: <br>
     *
     * <ol>
     * <li>
     * The robot has another sensor attached to the front of the touch sensor
     * (a light sensor is commonly attached in the very front)
     * </li>
     * <li>
     * The robot is moving forward, and collides with something to its direct
     * front.   The initial collision occurs between the light sensor and the
     * other object.
     * </li>
     * <li>
     * The robot detects a change from the light sensor, but the bumper does
     * not detect a collision (since it didn't collide, and isn't aware of
     * the light sensor).
     * </li>
     * </ol>
     *
     * In this situation, the robot <b>must</b> notify the touch sensor of its
     * expected state, since the objects in the simulation have no other
     * means of ensuring the touch sensor has the correct state.
     *
     * @param value  The new value for the sensor (usually ON/OFF)
     */
    public synchronized void setValue(int value) {

        /* Allows the robot to notify the touch sensor that it should be on
         * Necessary if there is another sensor in front of the bumper
         * (like a light sensor), so the bumper doesn't actually make contact
         */
        if (realismLevel == IDEALISTIC || value == ON) {
            this.value = value;

            if (value == ON) {
                hitSomething = true;
                reset = false;
            }
        }
    }

    /**
     * Undocumented
     */
    public void createArea() {
        super.createArea();
        updatePosition();
        previousShape = (java.awt.geom.Area)myShape.clone();
        previousVel.setDirection(velVector.getDirection());
        previousLocation.setLocation(location);
    }

    /**
     * Classes must provide the ability to draw.  Normally in idealistic mode
     * the sensor will not do anything in this method.
     *
     * @param g2 DOCUMENT ME!
     */
    public void draw(java.awt.Graphics2D g2) {

        java.awt.Shape oldClip = g2.getClip();

        // Ensure it only draws inside the bounding rectangle
        if (myShape != null) {
            g2.clip(myShape);
        }

        java.awt.geom.AffineTransform transform = new java.awt.geom.AffineTransform();

        // Rotate the image according to its azimuth
        transform.rotate(Math.toRadians(velVector.getDirection()),
                         location.getX(),
                         location.getY());

        // and move it to the correct location
        transform.translate(location.getX(),
                            location.getY());

        // Draw it in the graphics context, using the rotation transform
        if (appearance != null) {
            g2.drawImage(appearance, transform, host);
        } else {
            g2.draw(myShape);
        }

        g2.setTransform(new java.awt.geom.AffineTransform());
        g2.setClip(oldClip);
    }

    /**
     * React to a collision with another Collidable object
     *
     * @param collisionElement DOCUMENT ME!
     */
    public void handleCollision(Collidable collisionElement) {
      if (!(collisionElement instanceof Fire)){
        if (!(collisionElement instanceof FlatObject)) {
            reset = false;
            hitSomething = true;
            value = ON;
           
        }
       
        
        super.handleCollision(collisionElement);
      }
    }
    /**
     * Undocumented
     */
    public void reset() {
        reset = true;
    }

    /**
     * Undocumented
     */
    public void updateValue() {

        if (value == jago.element.SensorState.NOT_READY && added) {
            value = OFF;
        }

        // If running idealistically, doesn't reset automatically
        if (realismLevel == IDEALISTIC) {

            // Resets only if directed to (from reset method)
            if (reset) {
                reset = false;
                hitSomething = false;
                value = OFF;
            }
        } else {
            if (hitSomething) {
                hitSomething = false;
                resetDelay = 2;
            }

            if (resetDelay > 0) {
                resetDelay--;
            } else {
                value = OFF;
            }
        }
    }

    /**
     * Undocumented
     */
    protected void runTaskStep() {
        updateVelocity();

        /* TODO: See if updatePosition should be in draw() to ensure the
         * sensor only moves when the robot has a new, valid position.
         * Other issues are affecting how updates work (see ElementManager)
         * so in the current state, this might not be necessary.
         */
        updatePosition();
        updateValue();
    }

    /**
     * Updates the velocity of this sensor.  Since it is always attached to
     * another element, its velocity will match that of its host.
     */
    protected void updateVelocity() {

        if (hostMonitor instanceof Mobile) {
            velVector.setMagnitude(((Mobile)hostMonitor).getVelocity());
        } else {
            velVector.setMagnitude(0);
        }
    }
}