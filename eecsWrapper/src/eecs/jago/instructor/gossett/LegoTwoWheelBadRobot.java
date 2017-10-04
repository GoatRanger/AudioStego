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
 * LegoRobot.java
 *
 * Created on December 28, 2001, 1:28 PM
 */
package eecs.jago.instructor.gossett;

import jago.element.*;


/**
 * @version 0.1
 * @author Karl A. Gossett
 */
public class LegoTwoWheelBadRobot extends StandardRobot {

    public static final int RCX_1 = 0;
    public static final int RCX_2 = 1;
    public static final int RCX_3 = 2;

    /** The default file name of the robot's image */
    protected static final String defaultName = "robot_wheeled.gif";

    /** The default image */
    protected String baseImageName = "robot_wheeled";

    /**
     * Creates new LegoRobot
     */
    public LegoTwoWheelBadRobot() {
        super();
        setAppearance(defaultName);
    }

    /**
     * Sets the new power of the mobile element.  Power is the potential of
     * the element, not the instantaneous velocity. This overrides setPower
     * in <code>StandardRobot</code> because this two wheeled robot is
     * significantly faster than the tracked robot that v6.2.1 of
     * <code>StandardRobot</code> implements.
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
        if (realismLevel == REALISTIC || realismLevel == FIXED_REALISTIC) {

            // Left as an if--in case want to add another level of realism

            /* Using a non-linear equation to model the relationship between
             * the power setting and speed (in m/s).  The regression was based
             * on readings taken with a battery power of 7.6
             */
            power = (Math.sqrt(level + 1) * 0.11 + 0.07) * impactOfBatteryPower();

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
     * Sets the level of realism.  This robot does not allow true realism,
     * only fixed realism, and very poor performance in that mode.
     * 
     * @param level  A realism level from <CODE>eecs.jago.Realism</CODE>
     */
    public void setRealismLevel(int level) {
        if (level == REALISTIC) level = FIXED_REALISTIC;
        if (level == REALISTIC || level == IDEALISTIC
            || level == FIXED_REALISTIC) {
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
                batteryPower = batteryPower - 0.9 * 2.5;
                leftMotorDrag = 0.98 / 20.0;
                rightMotorDrag = 0.05 / 20.0;
            }

            if (level == REALISTIC) {

                // Create a battery power between 9.5 and 7.0
                batteryPower = batteryPower - Math.random() * 2.5;
                leftMotorDrag = Math.random() / 20.0;
                rightMotorDrag = Math.random() / 20.0;
            }

            // Update the power to the new setting.
            setPower(currentPowerLevel);
        }

        realismSet = true;
    }
}