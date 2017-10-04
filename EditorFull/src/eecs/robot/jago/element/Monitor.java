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

package eecs.robot.jago.element;


/**
 * An object that can monitor sensors.
 *
 * @version 1.0
 * @author Karl A. Gossett
 */
public interface Monitor {

    /** The input on the RCX labeled "1" */
    int RCX_1 = 0;

    /** The input on the RCX labeled "2" */
    int RCX_2 = 1;

    /** The input on the RCX labeled "3" */
    int RCX_3 = 2;

    /**
     * Returns the value of the sensor connected to the specified input
     *
     * @param inputNumber    The input, one of <CODE>RCX_1, RCX_2, RCX_3</CODE>
     * @return The value of the input
     */
    int getSensorInput(int inputNumber);

    /**
     * Connects a sensor to the specified sensor input.
     *
     * @param input   The input number to add to, one of <CODE>RCX_1, RCX_2,
     *        </CODE>or <CODE>RCX_3</CODE>
     * @param sensorType  The type of sensor
     */
    void addSensor(int input, int sensorType);

    /**
     * Connects a sensor to the specified input.
     *
     * @param input  The input number that the sensor is connected to, one of
     *        <CODE>RCX_1, RCX_2, </CODE>or <CODE>RCX_3</CODE>
     * @param sensor  A StandardSensor to connect.
     */
    void addSensor(int input, StandardSensor sensor);

    /**
     * Updates all the sensors.  This method must send the required
     * information to each connected sensor to ensure they all have current
     * readings. Generally this should be done each time the Monitor is
     * updated or drawn.
     */
    void updateSensors();
}