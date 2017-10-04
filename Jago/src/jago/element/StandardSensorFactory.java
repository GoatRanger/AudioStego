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
 * StandardSensorFactory.java
 *
 * Created on December 28, 2001, 12:57 PM
 */
package jago.element;


/**
 * This class is a static factory for creating <CODE>StandardSensor</CODE>s.
 *
 * @version 0.1
 * @author Karl A. Gossett
 */
public final class StandardSensorFactory
    implements RobotSensors {

    /**
     * Creates a new <CODE>StandardSensor</CODE> of the specified type
     *
     * @param type DOCUMENT ME!
     * @param host DOCUMENT ME!
     * @return DOCUMENT ME!
     */
    public static final StandardSensor createSensor(int type, Monitor host) {

        switch (type) {

            case LIGHT:
                return new LightSensor(host);

            case HIT:
                return new HITSensor(host);

            case INVERTED_FRONT_BUMPER: // fall through to STANDARD_FRONT_BUMPER
            case STANDARD_FRONT_BUMPER: // Also works for TOUCH type (same value)
                return new TouchSensor(type, host);

            // Return a standard sensor otherwise
            default:

                Sensor s = new Sensor();
                s.setType(type);

                return s;
        }
    }
}