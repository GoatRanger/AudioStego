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
package eecs.sim;

import jago.*;

import jago.element.*;

import java.util.*;


public class SlotsSimulation extends SimBox
{
    /** The array of lines */
    protected ColoredLine[] lines;

    /**
     * Creates new AParkSimulation
     */
    public SlotsSimulation()
    {
        this("Cash Me In!");
    }

    public SlotsSimulation(String title)
    {
        // note: the false is "not tiled background"
        super(540, 720, "slotsbackground.gif", false);
        setTitle(title);
        lines = new ColoredLine[5];
        layLines();
    }
     //end AParkSimulation

    /**
     * Undocumented
     */
    public void pullHandle()
    {
        for (int i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep(150);
            }
            catch (InterruptedException ie)
            {
                //do nothing
            }

            remove(lines[0]);
            remove(lines[1]);
            remove(lines[1]);

            try
            {
                Thread.sleep(150);
            }
            catch (InterruptedException ie)
            {
                //do nothing
            }

            layLines();
        }
    }

    /**
     * Undocumented
     */
    private final void layLines()
    {
        int upperBound = 1;
        int lowerBound = 5;
        int y = 0;
        Random r = new Random();

        /*  randomly uses one of 4 colors - strip #2 (3rd one) is horizontal and needs
           coordinates for rectangle size to be different from the other 3
         */
        int color = 0;

        for (int i = 0; i < 3; i++)
        {
            color = r.nextInt(4);

            if (color == 0)
            {
                lines[i] =
                    new ColoredLine(23, 45, ReflectiveColors.RED);
            }
            else if (color == 1)
            {
                lines[i] =
                    new ColoredLine(23, 45, ReflectiveColors.GREEN);
            }
            else if (color == 2)
            {
                lines[i] =
                    new ColoredLine(23, 45, ReflectiveColors.BLACK);
            }
            else
            {
                lines[i] =
                    new ColoredLine(23, 45, ReflectiveColors.BLUE);
            }
        }

        add(lines[0], 165, 158);
        add(lines[1], 218, 158);
        add(lines[2], 270, 158);
    }
     //end layLines
}
 //end SlotsSimulation
