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
 * LightSensor.java
 *
 * Created on December 18, 2001, 4:19 PM
 */
package jago.element;

/**
 * A sensor, similar in function to the blue light sensor that is included
 * with a Lego Mindstorms robot kit.
 *
 * <p>
 * Since a LightSensor implements the {@link jago.Realism} interface, it
 * will function differently depending on the realism setting.
 * </p>
 *
 * <p>
 * <b><code>IDEALISTIC</code> mode:</b> The LightSensor does not actually
 * <I>detect</I> anything; it is only a means to convey the information. It
 * requires that the host to which it is attached notify it of a new value.
 * </p>
 *
 * <p>
 * The only true functionality in this mode is that it will reset itself after
 * a very small amount of time with no new "reading" from the host.
 * </p>
 *
 * <p>
 * Because of this link, the host is fully responsible for determining when
 * the LightSensor "detects" something; in essence, using a LightSensor in
 * this mode allows a host to detect light anywhere, based on it's
 * implementation (the standard implementation is to base it on a collision,
 * see {@link jago.element.StandardRobot#handleCollision} for one such
 * implementation).
 * </p>
 *
 * <p>
 * <b>&nbsp;&nbsp;Readings:</b>  In <code>IDEALISTIC</code> mode, the reading
 * will always be exactly what is specified by the host, since the
 * LightSensor is not actually generating the reading.  When no reading has
 * been offered by the host, the LightSensor will revert to the
 * <code>SensorState.OFF</code> state (even though OFF really has no meaning
 * for a light sensor).
 * </p>
 *
 * <p>
 * <code>REALISTIC Mode:</code>  In this mode, the sensor will continuously
 * detect the level of reflection from the simulation.  It will detect
 * everything that was drawn before it, so readings will be given for both
 * the normal background, and for any objects that have been added (see
 * {@link jago.DisplayManager} to determine the order of presentation).
 * In this mode, there is no OFF state, and readings will be much less
 * predictable. The sensor averages the intensity of the group of pixels
 * where it will be drawn, so a single pixel will be sufficient to generate a
 * completely different, and unreliable reading.
 * </p>
 *
 * <p>
 * If the host attempts to set the value of the sensor in this mode, the call
 * will merely be ignored--no messages or exceptions will be generated to
 * indicate that the setting did not occur.
 * </p>
 *
 * <p>
 * This mode much more accurately represents the Lego light sensor, and the
 * difficulty in using the sensor for much more than bi-level differentiation
 * (it is nearly impossible to accurately detect "color" using the Lego
 * sensor).
 * </p>
 *
 * @version 2.0
 * @author Karl A. Gossett
 */
public class LightSensor
    extends StandardSensor {

    /* The TIME_TO_LIVE ensures that the sensor hold a value for a couple
     * cycles to help with readings--causes some false values, but acceptable */
    /* Number of ticks to stay active in IDEALISTIC mode, even if not
     * triggered */
    private static final int TIME_TO_LIVE = 2;
    protected boolean changedLastTick = false;
    protected int newReading = StandardSensor.NOT_READY;

    /**
     * Creates new LightSensor
     */
    LightSensor() {
        this(null);
    }

    /**
     * Creates a new LightSensor attached to a <CODE>SensorMonitor</CODE>.
     *
     * @param host The element that is monitoring this sensor.
     */
    LightSensor(Monitor host) {
        super();
        value = NOT_READY;
        type = LIGHT;
        size = new java.awt.Dimension(3, 2);
        location = new java.awt.geom.Point2D.Double();
        setMonitor(host);
        previousLocation = new java.awt.geom.Point2D.Double();
        previousLocation.setLocation(location);
        updatePosition();
    }

    /**
     * Do nothing. Required implementation from SimElement.
     *
     * @param url    A String (ignored).
     */
    public void setAppearance(String url) {

        // Do nothing
    }

    /**
     * Sets the value of the sensor.  This call is only valid if the sensor is
     * operating in IDEALISTIC mode (the set is ignored in REALISTIC mode).
     * In IDEALISTIC mode: The value of the sensor is set by the host, and
     * the sensor will maintain that state for approximately 160ms (3
     * updates) unless the host sets the value again (either to the same or a
     * different color).
     *
     * @param value DOCUMENT ME!
     * @deprecated This is no longer required or desired.  As of v6.2.3 of the
     *             simulation, the sensor is fully responsible for its own
     *             setting
     */
    public void setValue(int value) {

        if (realismLevel == IDEALISTIC) {

//            int oldVal = this.value;
            this.newReading = value;
            activeTimer = TIME_TO_LIVE;

            //            if (value != oldVal) {
            // Cause this thread to sleep to allow user thread to detect sensor
            //                try {
            //                    Thread.sleep(25);
            //                } catch (Exception e) {}
            //            }
            changed = true;
        }
    }

    /**
     * Draws the image of the sensor into a Java2D (Graphics2D) context.
     *
     * @param g2    The Graphics2D context to draw in.
     */
    public synchronized void draw(java.awt.Graphics2D g2) {

        if (hostMonitor instanceof SimElement) {

            //updatePosition();
            java.awt.Shape oldClip = g2.getClip();

            if (myShape != null) {
                g2.clip(myShape);
            }

            java.awt.Color oldColor = g2.getColor();
            g2.setColor(java.awt.Color.blue);
            g2.fill(myShape);
            g2.setColor(oldColor);
            g2.setClip(oldClip);
        }
    }

    /**
     * Determines the outcome of a collision.  If in IDEALISTIC mode, this is
     * the only way that {@link element.Reflective} elements will
     * be detected by the light sensor.  This method also passes the
     * collision to its parent (see {@link
     * jago.element.StandardSensor#handleCollision}) so the host will be
     * notified of a collision.
     *
     * @param collider   The {@link jago.element.Collidable} element that
     *        the LightSensor made contact with.
     */
    public void handleCollision(Collidable collider) {

        if (realismLevel == IDEALISTIC && collider instanceof Reflective) {
            newReading = ((Reflective)collider).getReflection();
            changedLastTick = true;
        }

        super.handleCollision(collider);
    }

    /**
     * Updates the sensor at each step in the simulation.  In
     * <CODE>REALISTIC</CODE> mode, no action is performed (updates are
     * performed when the sensor is <I>drawn</I> when in
     * <CODE>REALISTIC</CODE> mode).
     */
    protected void runTaskStep() {

        /* Update the value of the sensor based on the new location.
         * This must occur before the sensor is actually drawn, otherwise
         * it will just report the color of the sensor.
         */
        updatePosition();
        updateReading();
    }

/* (non-Javadoc)
 * @see jago.element.StandardSensor#getValue()
 */
public int getValue() {
	// TODO Auto-generated method stub
	if (value == Sensor.NOT_READY) {
		updateReading();
	}
	return super.getValue();
}
    /**
     * Updates the current reading of the sensor.
     *
     * <P>
     * Limitation:
     * </p>
     *
     * <P>
     * Since it reads the pixel values of the background at its current
     * location and uses those pixel readings to return a value, the value
     * will be based only on what the <CODE>DisplayManager</CODE> reports as
     * background. (see {@link jago.DisplayManager#getImageBuffer})
     * </p>
     */
    private synchronized void updateReading() {

        // If in IDEALISTIC mode, the reading is set by collisions/setValue.
        if ((realismLevel == REALISTIC || realismLevel == FIXED_REALISTIC)
            && hostMonitor instanceof SimElement) {

            SimElement myHost = (SimElement)hostMonitor;

            /* TODO: Figure out why the host is sometimes null.  For now, a
             * hack to prevent null pointers.  This might have been fixed by
             * a change in the remove method of the element manager, but hasn't
             * been verified as fixed.
             */
            if (myHost.host == null) {
                return;
            }

            java.awt.image.BufferedImage environ = myHost.host.getDisplayManager()
                                               .getImageBuffer();

            // Possible to update sensor before buffer is created (how?)
//            int timerCount = 0;
//
//            while (timerCount++ < 200 && environ == null) {
//
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {}
//            }

            if (environ == null) {

                return;
            }

            double brightness = 0;
            int pixelCount = 0;

            /* Read each pixel, to allow error checking for off-screen.
             * This section adjusts so that it is actually reading the portions
             * of the pixels that the sensor is covering.  So, if it is located
             * at (120.234, 200.119) with a width of 3.022, then it will read
             * the pixels at 120, 121, 122, and 123, with the ends weighted
             * appropriately.
             */
            java.awt.geom.Rectangle2D rect = myShape.getBounds2D();
            int leftBound = (int)rect.getX();
            int rightBound = (int)Math.ceil(rect.getX() + rect.getWidth()) - 1;
            int topBound = (int)rect.getY();
            int bottomBound = (int)Math.ceil(rect.getY() + rect.getHeight()) - 1;

            for (int pxlX = leftBound; pxlX <= rightBound; pxlX++) {

                for (int pxlY = topBound; pxlY <= bottomBound; pxlY++) {

                    try {

                        int pixel = environ.getRGB(pxlX, pxlY);

                        /* Convert to intensity.  The following equation was
                         * determined by regressing values from the actual
                         * sensor based on 14 colors.  The basis for the colors
                         * was the HP455CL Inkjet printer, using 2 variants of:
                         * red, blue, green, and yellow, and 4 levels of gray.
                         */
                        int red = (pixel >> 16) & 0xff;
                        int green = (pixel >> 8) & 0xff;
                        int blue = (pixel) & 0xff;
                        double pixelLevel = red * 0.057986 + green * 0.003103
                                            + blue * .003739 + 33.39861;

                        /* Adjust the value at the edges.  If the sensor is
                         * only partially over a pixel (based on the location,
                         * which is a double value), then the value reported
                         * should reflect only the percent of the pixel that
                         * impacts the returned value.
                         */
                        if (pxlX == leftBound) {
                            pixelLevel *= 1.0 - (rect.getX() - leftBound);
                        }

                        if (pxlX == rightBound) {
                            pixelLevel *= 1.0 - (rightBound - (rect.getX() + rect.getWidth() - 1));
                        }

                        if (pxlY == topBound) {
                            pixelLevel *= 1.0 - (rect.getY() - topBound);
                        }

                        if (pxlY == bottomBound) {
                            pixelLevel *= 1.0 - (bottomBound - (rect.getY() + rect.getHeight() - 1));
                        }

                        brightness += pixelLevel;
                        pixelCount++;
                    } catch (ArrayIndexOutOfBoundsException e) {

                        // Off screen, ignore
                    }
                }
            }

            // At least one pixel read
            if (pixelCount > 0) {
                value = (int)Math.round(
                                brightness / (rect.getWidth() * rect.getHeight()));
            } else {

                /* TODO: If allowing objects to interact offscreen, then this
                 * will be fixed.  As it is, the light sensor will return an
                 * UNKNOWN value if the robot is off the screen.
                 */
                value = ReflectiveColors.UNKNOWN;
            }
        } else {
            /* TODO: Verify that this is working acceptably.  Since this
             * is where the value is reset when in IDEALISTIC mode, this
             * version makes it possible for a query to return an OFF reading
             * even though it shouldn't (has to do with timing between
             * the user thread, handleCollision, and this method).  The
             * one tick delay seems to be a fix, but expect it isn't
             * completely consistent.
             */

            // In idealistic mode, so set the reading according to collision
            // Add a one tick delay to resetting readings
            if (changedLastTick) {
                value = newReading;
                changedLastTick = false;
            } else {
                newReading = StandardSensor.OFF;
                changedLastTick = true;
            }
        }

        // If getting the sensor value in a tight loop, yield here to allow
        // a reading to be taken
        //        Thread.yield();
    }
}