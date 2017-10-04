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
 * DisarmingSim.java
 *
 * Created on November 13, 2001, 11:46 AM
 */
package eecs.simulation;

import eecs.jago.instructor.gossett.PushableBomb;

import jago.element.*;

import java.awt.event.*;


/**
 * A simulation with multiple detonators that can be moved around.
 * The simulation is designed such that if the detonators are pushed
 * to the boundaries of the simulation space, they will be removed
 * from the simulation ("disarmed"), otherwise they will explode.
 * This simulation also demonstrates the technique to allow the
 * simulation itself to respond during execution.  All previous
 * SimBox classes were only an interface to the simulation engine.
 * (See actionPerformed method for details)
 *
 * @author Karl A. Gossett
 * @version 1.0
 *
 * @since 5.1
 */
public class DisarmingSim extends jago.SimBox
{
    protected TimerListener listener;
    PushableBomb[] bombs;
    double bottomLimit;

    /**
     * The limits of the usable space for placing bombs in the
     * simulation
     */
    double leftLimit;
    double rightLimit;
    double topLimit;

    /** The size of a detonator in the simulation */
    private int DET_SIZE = 30;

    /** The number of bombs in the simulation */
    private int bombCount = 0;

    // Although the sim allows for more than 2 detonators, to meet constraints
    // of placement, it is difficult to get more than 2 placed correctly
    private int detCount = 2;

    /**
     * Creates a new DisarmingSim with fixed placement of two
     * detonators
     */
    public DisarmingSim()
    {
        this(false, 2);
    }

    /**
     * Creates new DisarmingSim with at most three detonators.
     *
     * @param isRandom <CODE>true</CODE> if the detonators should be
     *        placed randomly in the simulation.
     */
    public DisarmingSim(boolean isRandom)
    {
        this(isRandom, 2);
    }

    /**
     * Creates a new DisarmingSim with the specified number of
     * detonators.
     *
     * @param isRandom <CODE>true</CODE> if the detonators should be
     *        placed randomly.
     * @param detCount The number of detonators to place (more than
     *        three could prevent simulation from being able to
     *        place them all.
     */
    public DisarmingSim(boolean isRandom, int detCount)
    {
        super(780, 580);
        setTitle("Detonator Removal Simulation");
        setBackgroundTile("images/gray_checkered.gif");
        setBoundingBox();
        bombs = new PushableBomb[detCount];
        layout(isRandom);
        leftLimit = 80;
        rightLimit = this.getSize().getWidth() - 100 - DET_SIZE;
        topLimit = 80;
        bottomLimit = this.getSize().getHeight() - 115 - DET_SIZE;

        // Create the listener, and react to simulation events
        listener = new TimerListener();
        simEngine.addActionListener(listener);
    }

    /**
     * Returns the number of bombs in the room at the start of the
     * simulation
     *
     * @return DOCUMENT ME!
     */
    public int getBombCount()
    {
        return bombCount;
    }

    /**
     * Start the simulation.
     */
    public void start()
    {
        for (int i = 0; i < bombs.length; i++)
        {
            if (bombs[i] != null)
            {
                bombs[i].start(4, 0);
            }
        }
    }

    /**
     * Adds a PusherRobot at the specified location with a heading
     */

    //    public void add(PusherRobot bot, double x, double y, double heading) {
    //        super.add(bot, x, y);
    //        bot.setInitialHeading((int)heading);
    //    }
    private final void setBoundingBox()
    {
        // Left Side wall
        add(new Wall(7, getSize().height - 42), 0, 8);

        // Top Wall
        add(new Wall(getSize().width - 8, 7), 0, 0);

        // Bottom Wall
        add(new Wall(getSize().width - 8, 7), 0, getSize().height -
            33);

        // Right side wall
        add(new Wall(7, getSize().height - 42), getSize().width - 14,
            8);
    }

    /**
     * Undocumented
     *
     * @param type Empty
     * @param horiz Empty
     * @param vert Empty
     */
    private void addDisarmingBox(int type, int horiz, int vert)
    {
        switch (type)
        {
            case PushableBomb.BOTTOM:
                horiz -= 20;
                vert = 40;

                break;

            case PushableBomb.TOP:
                horiz -= 20;
                vert = getSize().height - 150;

                break;

            case PushableBomb.RIGHT:
                horiz = 40;
                vert -= 20;

                break;

            case PushableBomb.LEFT:
                horiz = getSize().width - 130;
                vert -= 20;

                break;

            default:
                horiz = 10;
                vert = 10;

                break;
        }

        add(new ColoredLine(80, 4, ReflectiveColors.MED_GRAY), horiz,
            vert);
        add(new ColoredLine(80, 4, ReflectiveColors.MED_GRAY), horiz,
            vert + 76);
        add(new ColoredLine(4, 72, ReflectiveColors.MED_GRAY), horiz,
            vert + 4);
        add(new ColoredLine(4, 72, ReflectiveColors.MED_GRAY),
            horiz + 76, vert + 4);
    }

    /**
     * Undocumented
     *
     * @param randomLayout Empty
     */
    private void layout(boolean randomLayout)
    {
        bombCount = 0;

        int[] xLoc = new int[detCount];
        int[] yLoc = new int[detCount];
        int[] upperBound = { 180, 180, 220, 180 };
        int[] lowerBound =
        {
            (int) this.getSize().getHeight() - 260,
            (int) this.getSize().getHeight() - 260,
            
            (int) this.getSize().getHeight() - 260,
            (int) this.getSize().getHeight() - 260
        };
        int[] detType =
        {
            PushableBomb.TOP, PushableBomb.RIGHT, PushableBomb.BOTTOM,
            PushableBomb.LEFT
        };
        int type = PushableBomb.LEFT;
        java.util.Random r = new java.util.Random();
        int[] xCoords = { 180, 500, 350, 600 };
        int[] yCoords = { 200, 360, 500, 400 };

        for (int i = 0; i < detCount; i++)
        {
            boolean aligned = false;

            if (randomLayout)
            {
                xLoc[i] =
                    xCoords[i] +
                    (DET_SIZE - r.nextInt(DET_SIZE * 2));

                int tries = 0;

                do
                {
                    aligned = false;
                    type = detType[r.nextInt(detType.length)];
                    yLoc[i] =
                        (r.nextInt(lowerBound[type] -
                            upperBound[type])) + upperBound[type];

                    for (int chk = 0; chk < i; chk++)
                    {
                        if (Math.abs(yLoc[i] - yLoc[chk]) < 80)
                        {
                            aligned = true;
                        }
                    }

                    tries++;
                }
                while (aligned && (tries < 10));
            }
            else
            {
                xLoc[i] = xCoords[i];
                yLoc[i] = yCoords[i];
                type = detType[i];
            }

            // If aligned, then couldn't find a position, so don't add the det
            if (!aligned)
            {
                bombCount++;
                bombs[i] = new PushableBomb(type, DET_SIZE);
                addDisarmingBox(type, xLoc[i], yLoc[i]);
                add(bombs[i], xLoc[i], yLoc[i]);
            }
        }
    }

    class TimerListener implements ActionListener
    {
        /**
         * Updates the state of the simulation based on the current
         * event notification (from the Timer).  This allows the
         * DisarmingSim to update its contents, to include removing
         * elements from the simulation or moving objects
         * automatically.
         *
         * @param p1 DOCUMENT ME!
         */
        public void actionPerformed(java.awt.event.ActionEvent p1)
        {
            for (int i = 0; i < bombs.length; i++)
            {
                if (bombs[i] == null)
                {
                    continue;
                }

                if (bombs[i].hasMoved() && bombs[i].hasTape())
                {
                    bombs[i].removeTape();
                }

                if ((bombs[i].getPosition().getY() < topLimit) ||
                      (bombs[i].getPosition().getY() > bottomLimit) ||
                      (bombs[i].getPosition().getX() < leftLimit) ||
                      (bombs[i].getPosition().getX() > rightLimit))
                {
                    remove(bombs[i]);
                }
            }
        }
    }
}
