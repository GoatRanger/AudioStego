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
 * RobotGui.java
 *
 * Created on December 16, 2001, 12:38 PM
 */
package eecs;

import jago.*;

/**
 * A GUI used for running simulations using the Lego robot simulator.
 *
 * @author Chris Okasaki
 * @author Karl A. Gossett
 * @version 0.3
 */
public class RobotGui extends RobotGuiBase
{
    static
    {
        sim = new SimBox();
        setSimulationTitle(DEFAULT_TITLE);

        // Bypasses the RobotGuiBase file checks
        sim.setBackgroundTile(DEFAULT_IMAGE);

        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will
                     // appear in the sim box

        sim.setVisible(true);

        // Ensures that the simulation does not have a robot
        robot = null;
    }
     // end static initializer
}
