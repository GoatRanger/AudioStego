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
 * TouchSensorBean.java
 *
 * Created on August 5, 2002, 1:27 PM
 */
package jago.element;

import java.awt.geom.AffineTransform;




/**
 * @author Karl A. Gossett
 */
public class TouchSensorBean
    extends jago.element.StandardSensor
    implements java.io.Serializable,
               Mobile {

    private static final String PROP_VALUE = "Value";

    /** The heading of this sensor, generally related to the host */
    protected double heading;
    protected double headingAdjust;
    protected boolean hitSomething = false;
    protected String imageName = "robot_bumper_full.gif";
    protected boolean movedThisTick = false;

    /** The location this MobileElement was facing on the last draw */
    protected double previousHeading;

    protected boolean reset = false;
    protected int resetDelay = 0;

    /** The current velocity */
    protected double velocity;
    private java.beans.PropertyChangeSupport propertySupport;
    private String sampleProperty;

    /**
     * Creates new TouchSensorBean
     */
    public TouchSensorBean() {
        this(STANDARD_FRONT_BUMPER);
    }

    public TouchSensorBean(int type) {
        propertySupport = new java.beans.PropertyChangeSupport(this);
        velocity = 0;

        // Default adjustment is front/center
        headingAdjust = 0;

        // Eventually add switches for different bumpers
        if (type == INVERTED_FRONT_BUMPER) {
            imageName = "robot_bumper_inverted.gif";
        }

        this.type = type;
        setAppearance(imageName);
    }

    public TouchSensorBean(int type, Monitor host) {
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
     * Gets the angular velocity of this element.
     *
     * @return DOCUMENT ME!
     */
    public double getAngularVelocity() {

        double aVelocity = 0;

        if (hostMonitor instanceof Mobile) {
            aVelocity = ((Mobile)hostMonitor).getAngularVelocity();
        }

        return aVelocity;
    }

    /**
     * Sets the picture of any object using this interface to the image
     * identified by the URL provided.
     *
     * @param filename     The URL of the picture to use.
     */
    public void setAppearance(String filename) {
        appearance = jago.ImageLoader.getImage(this, filename);

        if (appearance != null) {
            size = new java.awt.Dimension(appearance.getWidth(host),
                                 appearance.getHeight(host));
        }
    }

    /**
     * Gets the current heading.
     *
     * @return DOCUMENT ME!
     */
    public synchronized double getHeading() {

        return heading;
    }

    /**
     * Determines the new power of the mobile element.  In the TouchSensor,
     * this has no meaning.
     *
     * @param speed DOCUMENT ME!
     */
    public void setPower(double speed) {}

    /**
     * Returns the last location of a Collidable element.
     *
     * @return DOCUMENT ME!
     */
    public java.awt.geom.Point2D getPreviousPosition() {

        return previousLocation;
    }

    /**
     * Sets the state of the touch sensor, if in <CODE>IDEALISTIC</CODE> mode.
     * If in <CODE>REALISTIC</CODE> mode, this method does nothing.
     *
     * @param value  The new value for the sensor (usually ON/OFF)
     * @deprecated As of Jago v6.2.3, this method is not used.  The sensor now
     *             determines its own value in all modes of operation.
     */
    public synchronized void setValue(int value) {

        if (realismLevel == IDEALISTIC) {

            int oldValue = this.value;
            this.value = value;

            if (value == ON) {
                hitSomething = true;
            }

            propertySupport.firePropertyChange("touchvalue", oldValue,
                                               this.value);
        }
    }

    /**
     * Gets the current velocity.
     *
     * @return DOCUMENT ME!
     */
    public double getVelocity() {

        return velocity;
    }

    /**
     * Undocumented
     *
     * @param listener Empty
     */
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    /**
     * Undocumented
     */
    public void createArea() {
        super.createArea();

        AffineTransform at = new AffineTransform();
        at.setToIdentity();
        updatePosition();
        previousShape = (java.awt.geom.Area)myShape.clone();
        previousHeading = heading;
        previousLocation.setLocation(location);
    }

    /**
     * Classes must provide the ability to draw.  Normally in idealistic mode
     * the sensor will not do anything in this method.
     *
     * @param g2 DOCUMENT ME!
     */
    public void draw(java.awt.Graphics2D g2) {

        //updatePosition();
        java.awt.Shape oldClip = g2.getClip();

        // Ensure it only draws inside the bounding rectangle
        if (myShape != null) {
            g2.clip(myShape);
        }

        AffineTransform transform = new AffineTransform();

        // Rotate the image according to its azimuth
        transform.rotate(Math.toRadians(heading),
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

        g2.setTransform(new AffineTransform());
        g2.setClip(oldClip);
    }

    /**
     * React to a collision with another Collidable object
     *
     * @param collisionElement DOCUMENT ME!
     */
    public void handleCollision(Collidable collisionElement) {

        if (!(collisionElement instanceof FlatObject)) {
            hitSomething = true;
            setValue(ON);
        }
    }

    /**
     * If this element has changed position during the last update, this
     * method should return <code>true</code>.
     *
     * @return DOCUMENT ME!
     */
    public boolean hasMoved() {

        return movedThisTick;
    }

    /**
     * Undocumented
     *
     * @param listener Empty
     */
    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    /**
     * Undocumented
     */
    public void reset() {
        reset = true;
    }

    /**
     * Updates the position of this sensor
     */
    public synchronized void updatePosition() {

        if (pendingCollision) {

            return;
        }

        // Save the current location
        previousLocation.setLocation(location);
        previousShape = (java.awt.geom.Area)myShape.clone();

        double oldHeading = heading;
        previousHeading = heading;
        movedThisTick = false;

        if (hostMonitor instanceof Mobile) {

            Mobile mobileHost = (Mobile)hostMonitor;
            //double movingSpeed = 0;
            heading = mobileHost.getHeading();
            //movingSpeed = mobileHost.getVelocity();

            java.awt.geom.Rectangle2D hostBound = mobileHost.getBounds2D();
            double hostCenterX = hostBound.getCenterX();
            double hostCenterY = hostBound.getCenterY();
            //java.awt.geom.Rectangle2D bound = myShape.getBounds2D();
            double myRadius = java.awt.geom.Point2D.distance(0.0, 0.0, size.getWidth() / 2,
                                               size.getHeight() / 2);
            double myOffsetX = myRadius * Math.cos(Math.PI / 180 * heading);
            double myOffsetY = myRadius * Math.sin(Math.PI / 180 * heading);
            double radianHeading = Math.toRadians(
                                           heading + thetaFromHostCenter);
            double x = hostCenterX - myOffsetX
                       + (fromHostCenter + size.getHeight()) * Math.sin(radianHeading);
            double y = hostCenterY - myOffsetY
                       - (fromHostCenter + size.getHeight()) * Math.cos(radianHeading);
            AffineTransform at = new AffineTransform();
            at.translate(x - location.getX(), y - location.getY());
            at.rotate(Math.toRadians(heading - oldHeading),
                      location.getX(),
                      location.getY());
            myShape.transform(at);

            if (!(equal(x,
                        location.getX(),
                        0.5) && equal(y,
                                      location.getY(),
                                      0.5) && equal(heading, oldHeading, 0.5))) {
                movedThisTick = true;
            }

            location.setLocation(x, y);
        }
    }

    /**
     * Undocumented
     */
    public void updateValue() {

        if (hitSomething) {
            hitSomething = false;
            resetDelay = 2;
        }

        if (resetDelay > 0) {
            resetDelay--;
        } else {

            if (movedThisTick
                || (value == jago.element.SensorState.NOT_READY && added)) {
                setValue(OFF);
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
            velocity = ((Mobile)hostMonitor).getVelocity();
        } else {
            velocity = 0;
        }
    }

    /**
     * Determines if two doubles are equal (within the specified tolerance).
     *
     * @param val1 DOCUMENT ME!
     * @param val2 DOCUMENT ME!
     * @param tolerance DOCUMENT ME!
     * @return DOCUMENT ME!
     */
    private boolean equal(double val1, double val2, double tolerance) {

        if (Math.abs(val1 - val2) <= tolerance) {

            return true;
        } else {

            return false;
        }
    }
}