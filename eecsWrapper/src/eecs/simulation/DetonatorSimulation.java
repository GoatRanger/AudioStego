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
package eecs.simulation;

import jago.*;

import jago.element.*;

import java.util.*;


/**
 * Creates a simulation with four push-button detonators.
 *
 * @author Rusl Flowers
 * @author Karl A. Gossett
 * @version 5.1
 *
 * @since 4.0
 */
public class DetonatorSimulation extends SimBox
{
    private static final long serialVersionUID = 8202137619578793110L;
    protected final int DET_SIZE = 30;

    /** The array of detonators */
    protected Detonator[] detonator;
    protected boolean running = false;
    protected int detCount = 3;

    /**
     * Creates new DetonatorSimulation
     *
     * @param randomLayout DOCUMENT ME!
     */
    public DetonatorSimulation(boolean randomLayout)
    {
        this("Detonator Disarm Array", randomLayout);
    }

    public DetonatorSimulation(String title, boolean randomLayout)
    {
        super(780, 580);
        setTitle(title);
        setBackgroundTile("images/gray_checkered.gif");
        setupSimulation(randomLayout);
    }

    /**
     * Starts the detonators in the simulation.
     */
    public void startDetonators()
    {
        if ((detonator[0] != null) && !running)
        {
            for (int i = 0; i < detCount; i++)
            {
                detonator[i].start(4, 0);
            }

            running = true;
        }
    }

    /**
     * Undocumented
     *
     * @param randomLayout Empty
     */
    protected void setupSimulation(boolean randomLayout)
    {
        setBoundingBox();
        detonator = new Detonator[detCount];
        layDetonatorArray(randomLayout);
    }

    /**
     * Undocumented
     */
    private final void setBoundingBox()
    {
        // Left Side wall
        add(new Wall(7, getSize().height - 42), 0, 8);

        // Top Wall
        add(new Wall(getSize().width - 7, 7), 0, 0);

        // Bottom Wall
        add(new Wall(getSize().width - 7, 7), 0, getSize().height -
            33);

        // Right side wall
        add(new Wall(7, getSize().height - 42), getSize().width - 14,
            8);
    }

    /**
     * Undocumented
     *
     * @param isLayoutRandom Empty
     */
    private final void layDetonatorArray(boolean isLayoutRandom)
    {
        final int NUM_COLUMNS = detCount;
        int startingX = 130;
        int offset = ((this.getSize().width) - 300) / (detCount - 1);
        int padding = 10;
        int upperBound = 130;
        int lowerBound = (this.getSize().height) - 190;
        int[] detType =
        {
            Detonator.LEFT, Detonator.RIGHT, Detonator.BOTTOM,
            Detonator.TOP
        };
        int x = startingX;
        int y = 0;
        int type = Detonator.LEFT;
        Random r = new Random();
        int[] yCoords =
        {
            this.getSize().height / 2, lowerBound, upperBound,
            this.getSize().height / 3 * 2
        };

        for (int i = 0; i < NUM_COLUMNS; i++)
        {
            if (isLayoutRandom)
            {
                y = (r.nextInt(lowerBound - upperBound)) +
                    upperBound;
                type = detType[r.nextInt(detType.length)];
            }
            else
            {
                y = yCoords[i];
                type = detType[i];
            }

            detonator[i] = new Detonator(type, DET_SIZE);
            add(detonator[i], x, y);
            x = x + offset;
        }
    }
}
