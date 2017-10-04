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
 * WolfeRobotGui.java
 *
 * Created on December 16, 2001, 12:38 PM
 */
package eecs;

/**
 * DOCUMENT ME!
 *
 * @author MAJ Morel
 * @version 0.1
 */
public class SlotMachineGui extends eecs.RobotGuiBase
{
    // static initializer
    static
    {
        sim = new eecs.sim.SlotsSimulation();

        // Bypasses the RobotGuiBase file checks
        //sim.setBackgroundImage(DEFAULT_IMAGE);
        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will
                     // appear in the sim box

        sim.setVisible(true);

        // Ensures that the simulation does not have a robot
        robot = null;
    }
     // end static initializer

    /**
     * Undocumented
     */
    public static void pullHandle()
    {
        ((eecs.sim.SlotsSimulation) sim).pullHandle();
    }
}
