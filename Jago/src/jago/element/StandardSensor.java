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
 * A sensor that can be used to detect changes in an element's surroundings.
 *
 * @version 1.0
 * @author Karl A. Gossett
 */
public abstract class StandardSensor extends MobileElement implements Attachment, RobotSensors, SensorState, jago.Realism {

    /** A timer for sensors that must stay active for a period of time */
    protected int activeTimer;
    protected Monitor hostMonitor = null;

    /** Whether the simulation is REALISTIC or IDEALISTIC */
    protected int realismLevel;

    /**
     * This sensor's type.  Allowable types are all types in the RobotSensors
     * interface.
     */
    protected int type;

    /** the current value of this sensor */
    protected int value;
    boolean changed = false;
    double fromHostCenter;
    double thetaFromHostCenter;

    /**
     * Gets the angular velocity of this element.
     *
     * @return The linear component of the angular (rotational) velocity.
     */
    public double getAngularVelocity() {

        double aVelocity = 0;

        if (hostMonitor instanceof Mobile) {
            aVelocity = ((Mobile)hostMonitor).getAngularVelocity();
        }

        return aVelocity;
    }

    /**
     * Determines if this sensor is attached to the specified element,
     * assuming that if a SimElement is monitoring this sensor, they are
     * attached.
     *
     * @param s  The <code>SimElement</code> to check for attachment with
     * @return <code>true</code> if this sensor is attached to the specified
     *         element.
     */
    public boolean isAttachedTo(SimElement s) {

        boolean attached = false;

        if (hostMonitor != null && s instanceof Monitor) {

            if (s.equals(hostMonitor)) {
                attached = true;
            }
        }

        return attached;
    }

    /**
     * Gets the <code>Monitor</code> that is watching for actions from this
     * element.
     *
     * @return The observing <code>Monitor</code>
     */
    public Monitor getMonitor() {

        return hostMonitor;
    }

    /**
     * Since the a normal sensor does not move on its own, it has no power
     * setting.  This method is maintained only for consistency with the
     * mobile interface.  (Maybe changed to a Powered interface?)
     *
     * @param speed Ignored.
     */
    public void setPower(double speed) {

        // do nothing
    }

    /**
     * This sensor is updated based on the location of its host, and does not
     * maintain previous state information.  The return is always (0,0).
     *
     * @return DOCUMENT ME!
     */
    public java.awt.geom.Point2D getPreviousPosition() {

        return previousLocation;
    }

    /**
     * Sets the realism for this sensor.
     *
     * @param level Empty
     */
    public void setRealismLevel(int level) {
        realismLevel = level;
    }

    /**
     * Returns the realism, as a constant from {@link jago.Realism}.
     *
     * @return The realism setting.
     */
    public int getRealismLevel() {

        return realismLevel;
    }

    /**
     * Sets the relative position of this element.  Specified as a vector, the
     * parameters should describe the position from the center of the element
     * (attached to) to the inside center of this element (so the radius is
     * in pixels, to the point nearest the host's center).
     *
     * @param radius   The radius of the vector, from the center of the
     *        element attached to.
     * @param theta    The angle between the center of the two elements, in
     *        degrees.  Assumes 0 is from the initial orientation of the
     *        host.
     */
    public void setReference(double radius, double theta) {

        if (radius >= 0) {
            fromHostCenter = radius;
            thetaFromHostCenter = theta % 360;
        }
    }

    /**
     * Returns the type of sensor that this element represents.
     *
     * @return A constant that represents the type of sensor.
     */
    public int getType() {

        return type;
    }

    /**
     * Undocumented
     *
     * @param value Empty
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Queries the sensor for its current value.  May return
     * <code>SensorState.NOT_READY</code> if the sensor has not had time to
     * get an initial value.
     *
     * @return The value of the sensor (range depends on the sensor).
     */
    public int getValue() {

        /* A short delay, so a tight loop doesn't try to control the system.
         * Since called from the user thread, give the sim a chance to update.
         */
        int currentValue = value;

//        try {
//            Thread.sleep(25);
//        } catch (InterruptedException e) {
//            ;
//        }

        // Possible to try to get the value before the sim starts, so check
        while (host == null) {
            Thread.yield();
        }

        if (host != null) {

            int timeout = 30;

            while (value == NOT_READY && --timeout > 0) {

                try {
                    Thread.sleep((int)host.getDelay());
                } catch (InterruptedException e) {
                    ;
                }
            }
        }

        if (currentValue == ON) {

            return currentValue;
        }

        return value;
    }

    /**
     * Gets the <CODE>Monitor</CODE> that this <CODE>Attachment</CODE> is
     * attached to.
     *
     * @return The <code>Monitor</code>.
     */
    public Monitor attachedTo() {

        return hostMonitor;
    }

    /**
     * Actions when this sensor should deflect from a collision.  By default,
     * take no action.
     *
     * @param collider DOCUMENT ME!
     */
    public void bounceOff(Collidable collider) {}

    /**
     * Actions when notified of a collision.  By default, a
     * <CODE>StandardSensor</CODE> just passes the information to its
     * monitor, if its monitor is a <CODE>Collidable</CODE> element (which it
     * should be, although this relationship is not enforced)
     *
     * @param collider DOCUMENT ME!
     */
    public void handleCollision(Collidable collider) {
    	if ((collider instanceof FlatObject) || (collider instanceof Fire)|| (collider instanceof Concertina)) 
    		return;
        else if  (hostMonitor instanceof Collidable) {
        	
            ((Collidable)hostMonitor).handleCollision(collider);
        }
       
        
              
    }

    /**
     * Notifies this sensor that it might be involved in a collision. The
     * standard action is to notify the host monitor as well.
     *
     * @param pending DOCUMENT ME!
     */
    public void pendingCollision(boolean pending) {
        pendingCollision = pending;

        // And notify host that we might be colliding
        if  (hostMonitor instanceof Collidable) {
            ((Collidable)hostMonitor).pendingCollision(pending);
        }
    }

    /**
     * Updates the position of this sensor. Overrides the default for a
     * <code>MobileElement</code> since the location of a sensor is tied to
     * its host.
     */
    public void updatePosition() {

        if (pendingCollision) {

            return;
        }

        // Save the current location
        previousLocation.setLocation(location);
        previousShape = (java.awt.geom.Area)myShape.clone();

        double oldHeading = velVector.getDirection();
        previousVel.setDirection(velVector.getDirection());

        if (hostMonitor instanceof Mobile) {

            Mobile mobileHost = (Mobile)hostMonitor;
            velVector.setVector(mobileHost.getVelocity(),
                                mobileHost.getHeading());

            java.awt.geom.Rectangle2D hostBound = mobileHost.getBounds2D();
            double hostCenterX = hostBound.getCenterX();
            double hostCenterY = hostBound.getCenterY();
            double myRadius = java.awt.geom.Point2D.distance(0.0, 0.0, size.getWidth() / 2,
                                               size.getHeight() / 2);
            double myOffsetX = myRadius * Math.cos(Math.toRadians(
                                                           velVector.getDirection()));
            double myOffsetY = myRadius * Math.sin(Math.toRadians(
                                                           velVector.getDirection()));
            double radianHeading = Math.toRadians(
                                           velVector.getDirection()
                                           + thetaFromHostCenter);
            double x = hostCenterX - myOffsetX
                       + (fromHostCenter + size.getHeight()) * Math.sin(radianHeading);
            double y = hostCenterY - myOffsetY
                       - (fromHostCenter + size.getHeight()) * Math.cos(radianHeading);
            java.awt.geom.AffineTransform at = new java.awt.geom.AffineTransform();
            at.translate(x - location.getX(), y - location.getY());
            at.rotate(Math.toRadians(velVector.getDirection() - oldHeading),
                      location.getX(),
                      location.getY());
            myShape.transform(at);
            location.setLocation(x, y);
        }
    }

    /**
     * Sets the host Monitor for this sensor
     *
     * @param host   The Monitor to which this sensor is attached
     */
    protected void setMonitor(Monitor host) {
        hostMonitor = host;

        if (host instanceof jago.Realism) {
            realismLevel = ((jago.Realism)host).getRealismLevel();
        }

        added = true;
    }

    /**
     * Resets the value of sensors
     */
    protected void reset() {
        value = OFF;
    }

    /* A StandardSensor does not implement the TimerListener / actionPerformed
     * since a sensor has no default actions (thus it inherits from SimElement).
     */
}