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
 * RobotDemo.java
 *
 * Created on December 16, 2002, 4:39 PM
 */
package eecs.applet;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public class RobotDemo extends AppletBase
{
    /**
     * Undocumented
     */
    public void run()
    {
        int toRun =
            chooseButton("Demo with or without drawing paint trails?",
                "With", "Without");

        if (toRun == 0)
        {
            createRobot(300, 300, 0);
            goForward(1000);
            drawTrails(true);
            pivotLeft(45);
            goForward(2000);
            setPower(10);
            printLine("This is really cool stuff!");
            goForward(3000);
        }
        else if (toRun == 1)
        {
            createRobot(100, 100, 90);
            print("Starting...");
            goForward(1000);
            printLine("Almost finished");
            pivotLeft(270);
            goForward(2000);
            printCode("goForward");
            printLine(" is completed.");
        }
    }
}
