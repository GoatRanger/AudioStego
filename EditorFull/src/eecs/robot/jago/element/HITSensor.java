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
 * HITSensor.java
 *
 * Created on January 6, 2002, 1:52 PM
 */
package eecs.robot.jago.element;

/**
 * <p>
 * A High-Intensity Tracking Sensor.  This sensor is actually a simple
 * differential light sensor; this class is modeled after the light sensor
 * built at the United States Military Academy.  The sensor returns a voltage
 * between 0.0 and 5.0 volts based on the difference in light detected
 * between the two detectors attached to the front of the sensor.  The values
 * returned here are in the range -50...50 (light fully to the left...light
 * fully to the right).
 * </p>
 * <br/><b>Limitations:</b>
 *
 * <ul>
 * <li>
 * The HIT Sensor, as designed, gives no indication of the intensity of light.
 * If calibrated upon initialization, it returns values based on changes from
 * that initial calibration--if the robot is rotated 180 degrees, it may give
 * exactly the same reading as if it is directly facing a  bright light.
 * </li>
 * <li>
 * Increasing the brightness of a light source will have an impact on the
 * readings from the sensor, but there is no guarantee of accurate  modeling
 * (the simulation uses a completely linear model).
 * </li>
 * <li>
 * Although the simulation will respond to multiple light sources, there is no
 * guarantee that the response will identically model the real world. This
 * version simply calculates the direction of highest response using the
 * model described above.
 * </li>
 * </ul>
 *
 * <br/>
 *
 * <p>
 * <b><code>IDEALISTIC</code> mode:</b> In this mode, the sensor responds as
 * follows:
 * </p>
 *
 * <ul>
 * <li>
 * All lights are received with 100% of their specified intensity, regardless
 * of the distance between the sensor and the light source.
 * </li>
 * <li>
 * There is no adjustment made for light sources behind the sensor (usually a
 * product of shadows).  The result of this is that if the sensor is rotated
 * 180 degrees, the value of the sensor for a single light source will be
 * exactly the same as before it was rotated.
 * </li>
 * </ul>
 *
 * <br/>
 *
 * <p>
 * <b><code>REALISTIC</code> mode:</b> In this mode, the sensor responds as
 * follows:
 * </p>
 *
 * <ul>
 * <li>
 * This sensor replicates (in limited fashion) the propagation of light; as
 * the sensor is farther from the light source, the less responsive it is.
 * Thus, if it is several lengths from the light source, it may only have a
 * range of -10...10 (instead of -50...50); as it gets closer to the source,
 * the range will increase (due to increased detection).
 * </li>
 * <li>
 * Light sources behind the sensor are detected only through a simulated
 * reflection of light.  As such, readings when the light source is behind
 * the sensor will generally be very near 0.
 * </li>
 * </ul>
 *
 *
 * @version 2.0
 * @author Karl A. Gossett
 * @since 6.1
 */
public class HITSensor
    extends StandardSensor {

    /** The height of the sensor, in pixels */
    final int height = 8;

    /** The width of the sensor, in pixels */
    final int width = 20;

    /**
     * Creates a new HITSensor.  The sensor must be attached to a host before
     * it can be used.
     */
    HITSensor() {
        this(null);
    }

    /**
     * Creates a new HITSensor, attached to the specified
     * <CODE>SensorMonitor</CODE>.
     *
     * @param host   The SensorMonitor that this HITSensor provides values to
     */
    HITSensor(Monitor host) {
        super();
        value = NOT_READY;
        type = HIT;
        fromHostCenter = 0;
        thetaFromHostCenter = 0;
        location = new java.awt.geom.Point2D.Double();
        size = new java.awt.Dimension(width, height);
        setMonitor(host);
        myShape = new java.awt.geom.Area(new java.awt.geom.Rectangle2D.Double(
                                   location.getX(),
                                   location.getY(),
                                   size.getWidth(),
                                   size.getHeight()));
        updatePosition();
    }

    /**
     * Draws this sensor in the current graphics context.
     *
     * @param g2    The Graphics2D context to draw in.
     */
    public void draw(java.awt.Graphics2D g2) {
        g2.setColor(java.awt.Color.gray);
        g2.fill(myShape);
        g2.setColor(java.awt.Color.black);
        g2.draw(myShape);
    }

    /**
     * Updates the position and reading of the sensor.  Called when the
     * simulation engine requires elements to be updated.
     */
    protected void runTaskStep() {
        updatePosition();
        updateValue();
    }

    /**
     * Updates the value of this light sensor based on the
     * <CODE>LightSource</CODE>s that are active in the simulation.
     */
    protected void updateValue() {

        java.util.List lights;

        if (host != null) {
            lights = host.getLightSources();

            // Set value (intensity) at center
            value = 0;

            java.util.Iterator lightIter = lights.iterator();
            int sourceCount = 0;
            java.awt.geom.Rectangle2D r2 = myShape.getBounds2D();
            double myCenterX = r2.getCenterX();
            double myCenterY = r2.getCenterY();

            /* Iterate through the light sources in the simulation
             * to determine the value of this sensor during this iteration
             */
            while (lightIter.hasNext()) {

                LightSource source = (LightSource)lightIter.next();
                java.awt.geom.Point2D sourceLoc = source.getPosition();
                int sourceIntensity = source.getIntensity();

                // The angle between me and the light source
                double angle = -Math.atan2(myCenterY - sourceLoc.getY(),
                                           sourceLoc.getX() - myCenterX);

                // The distance from me to the light source
                double distance = java.awt.geom.Point2D.distance(
                                          myCenterX,
                                          myCenterY,
                                          sourceLoc.getX(),
                                          sourceLoc.getY());

                // Don't ever let the distance be < 1--used in division later
                if (distance < 1) {
                    distance = 1;
                }

                double heading = 0d; // Just in case it's not on a mobile host

                // If my host is mobile, update my current facing
                if (hostMonitor instanceof Mobile) {

                    Mobile mobileHost = (Mobile)hostMonitor;
                    heading = mobileHost.getHeading();
                }

                // Convert to radians, and reorient to standard trig direction
                double radHeading = Math.toRadians((heading + 270) % 360.0);

                if (radHeading > Math.PI) {
                    radHeading -= Math.PI * 2;
                }

                // Calculate a value for the intensity based on location
                angle = angle - radHeading;
                angle = (angle > Math.PI) ? angle - Math.PI * 2 : angle;

                double adjustment;

                /* If angle is positive, then light is to the right, so the
                 * reading should reflect a positive value for this lightsource.
                 */
                if (angle >= 0) {
                    adjustment = (Math.PI / 2 - Math.abs(angle - Math.PI / 2)) / (Math.PI / 2) * sourceIntensity;
                } else {

                    // Light to left, so a negative value
                    adjustment = -((Math.PI / 2
                                     - Math.abs(-angle - (Math.PI / 2))) / (Math.PI / 2) * sourceIntensity);
                }

                // Now update the current reading, based on realism level
                if (realismLevel == REALISTIC) {

                    /* If the light is behind the sensor, significantly reduce
                     * the impact of it on the overall reading.
                     */
                    if (Math.abs(angle) > Math.PI / 2) {
                        adjustment *= 0.5 / Math.abs(angle);
                    }

                    // and adjust for distance from the light as well
                    adjustment = adjustment / ((int)distance / 100 + 1);
                } else {

                    /* In idealistic mode, the method is nearly the same,
                     * but there is no adjustment made for distance, or for
                     * the light being behind the sensor. In essence,
                     * the ideal situation represented is that the sensor
                     * always receives 100% intensity, regardless of direction.
                     * This is how the adjustment was calculated, so no change
                     * is required here.
                     */
                }

                value += adjustment;

                // and increment the number of valid sources
                sourceCount++;
            }

            /* Rescale the intensity to -50 to 50. If there are no sources,
             * then ensure that the value returned is 0 (no light means no
             * differential value).
             */
            value = (sourceCount > 0) ? value / (sourceCount * 2) : 0;
        }
    }
}