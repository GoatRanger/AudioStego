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
 * RobotTracks.java
 *
 * Created on May 25, 2002, 10:52 PM
 */
package eecs.robot.jago.element;




/**
 * A tracked system.
 *
 * @author Karl A. Gossett
 */
public class RobotTracks implements Propulsion, eecs.robot.jago.Realism {

    protected double drag;
    protected int inputGear;

    /* 
     * to allow it to compile more easily during development
     */
    protected double inputRPM;
    protected double outputRPM;
    private int realismLevel = IDEALISTIC;

    /**
     * Creates a new instance of RobotTracks
     */
    public RobotTracks() {

        // Default to an 8 tooth input gear
        inputGear = 8;
        inputRPM = 0.0;
        drag = 0.0;
    }

    /**
     * Undocumented
     *
     * @param deltaTime Empty
     * @return Empty
     */
    public double getDistanceMoved(double deltaTime) {

        double deltaMS = deltaTime / 1000.0;
        double pixelsPerRev = 35;
        double distance = pixelsPerRev * (inputGear / 24.0 * inputRPM) / 60.0 * deltaMS;

        return distance;
    }

    /**
     * Returns the amount of drag that this system has
     *
     * @return DOCUMENT ME!
     */
    public double getDrag() {

        return drag;
    }

    /**
     * Undocumented
     *
     * @param teeth Empty
     */
    public void setInputGear(int teeth) {

        if (teeth < 0) {
            teeth = 0;
        }

        inputGear = teeth;
    }

    /**
     * Set the RPM of the motor that is driving this device.
     *
     * @param setting   The power setting
     */
    public void setInputRPM(double setting) {
        inputRPM = setting * (1.0 - drag);
    }

    /**
     * Undocumented
     *
     * @param realism Empty
     */
    public void setRealismLevel(int realism) {
        realismLevel = realism;

        if (realism == IDEALISTIC) {
            drag = 0.0;
        } else {

            //    drag = Math.random()*0.2;
            drag = 0.2;
            setInputRPM(inputRPM);
        }
    }

    /**
     * Gets the realism setting (as a constant)
     *
     * @return Returns one of the Realism constants, currently {@link
     *         #IDEALISTIC} or {@link #REALISTIC}
     */
    public int getRealismLevel() {

        return realismLevel;
    }
}