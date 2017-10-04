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

package eecs.jago.instructor.wolfe;

import jago.*;

import jago.element.*;

import java.util.*;


/**
 * Creates a simulation with several lines of reflective tape scattered around
 * the West Point central area.
 * 
 * @author Doug Wolfe
 * @since 4.0
 */
public class CentralAreaSimulation
    extends SimBox {

    /** The array of lines */
    protected ColoredLine[] lines;

    /**
     * Creates new DetonatorSimulation
     */
    public CentralAreaSimulation() {
        this("Central Area Robot");
    }

    public CentralAreaSimulation(String title) {

        // note: the false is "not tiled background"
        super(720, 540, "centralarea.gif", false);
        setSimTitle(title);
        lines = new ColoredLine[5];
        layLines();
    }

    /**
     * Undocumented
     */
    private final void layLines() {

        int upperBound = 1;
        int lowerBound = 6;
        int y = 0;
        Random r = new Random();

        for (int i = 0; i < 4; i++) {
            y = (r.nextInt(lowerBound - upperBound)) + upperBound;

            if (y == 1) {
                lines[i] = new ColoredLine(7, 30, ReflectiveColors.RED);
            } else if (y == 2) {
                lines[i] = new ColoredLine(7, 30, ReflectiveColors.BLACK);
            } else if (y == 3) {
                lines[i] = new ColoredLine(7, 30, ReflectiveColors.BLUE);
            } else if (y == 4) {
                lines[i] = new ColoredLine(7, 30, ReflectiveColors.GREEN);
            } else if (y == 5) {
                lines[i] = new ColoredLine(7, 30, ReflectiveColors.WHITE);
            }
        }

        add(lines[0], 104, 340);
        add(lines[1], 210, 361);
        add(lines[2], 456, 53);
        add(lines[3], 510, 445);
    }
}