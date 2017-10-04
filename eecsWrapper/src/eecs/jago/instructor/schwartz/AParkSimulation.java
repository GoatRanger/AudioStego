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

package eecs.jago.instructor.schwartz;

import jago.*;

import jago.element.*;

import java.util.*;


/**
 * Creates a simulation with several lines of reflective tape scattered around
 * an amusement park.
 * 
 * @author Susan Schwartz (based on work byDoug Wolfe)
 * @since 4.0
 */
public class AParkSimulation
    extends SimBox {

    /** The array of lines */
    protected ColoredLine[] lines;

    /**
     * Creates new AParkSimulation
     */
    public AParkSimulation() {
        this("Amusement Park Robot");
    }

    public AParkSimulation(String title) {

        // note: the false is "not tiled background"
        super(540, 720, "rides.gif", false);
        setTitle(title);
        lines = new ColoredLine[5];
        layLines();
    } //end AParkSimulation

    /**
     * Undocumented
     */
    private final void layLines() {

        int upperBound = 1;
        int lowerBound = 5;
        int y = 0;
        Random r = new Random();

        /*  randomly uses one of 4 colors - strip #2 (3rd one) is horizontal and needs
           coordinates for rectangle size to be different from the other 3
         */
        for (int i = 0; i < 4; i++) {
            y = (r.nextInt(lowerBound - upperBound)) + upperBound;

            if (i != 2) {

                if (y == 1) {
                    lines[i] = new ColoredLine(7, 30, ReflectiveColors.RED);
                } else if (y == 2) {
                    lines[i] = new ColoredLine(7, 30, ReflectiveColors.BLACK);
                } else if (y == 3) {
                    lines[i] = new ColoredLine(7, 30, ReflectiveColors.GREEN);
                } else if (y == 4) {
                    lines[i] = new ColoredLine(7, 30, ReflectiveColors.WHITE);
                }
            } //end if 
            else {

                if (y == 1) {
                    lines[i] = new ColoredLine(30, 7, ReflectiveColors.RED);
                } else if (y == 2) {
                    lines[i] = new ColoredLine(30, 7, ReflectiveColors.BLACK);
                } else if (y == 3) {
                    lines[i] = new ColoredLine(30, 7, ReflectiveColors.GREEN);
                } else if (y == 4) {
                    lines[i] = new ColoredLine(30, 7, ReflectiveColors.WHITE);
                }
            } //end else
        } //end for

        // place reflection strips in simulator
        add(lines[0], 142, 136);
        add(lines[1], 240, 256);
        add(lines[2], 318, 254);
        add(lines[3], 258, 460);
    } //end layLines
} //end AParkSimulation