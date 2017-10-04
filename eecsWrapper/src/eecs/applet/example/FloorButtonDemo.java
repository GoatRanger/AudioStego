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
 * FloorButtons.java
 *
 * Created on January 15, 2003, 2:32 PM
 */
package eecs.applet.example;

import eecs.jago.instructor.gossett.ActivatorButton;

import jago.*;

import jago.element.*;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public class FloorButtonDemo extends eecs.applet.AppletBase
{
    protected ActivatorButton airlockButton;
    protected Wall[] doors;
    protected ActivatorButton[] mainButton;
    protected boolean isOpen = false;
    protected final int totalCount = 5;
    protected Wall[] walls;
    final int MAX_TIME = 1000;
    int[][] buttonLocations =
    {
        { 370, 395 },
        { 190, 395 },
        { 190, 104 },
        { 370, 104 }
    };
    int[] buttonResetTime;
    int[][] doorLocations =
    {
        { 140, 155 },
        { 140, 280 },
        { 490, 35 },
        { 630, 35 }
    };
    int[][] locations =
    {
        { 140, 30 },
        { 140, 430 },
        { 490, 155 },
        { 140, 36 },
        { 140, 380 },
        { 491, 155 },
        { 630, 155 },
        { 730, 30 },
        { 39, 145 },
        { 39, 276 },
        { 39, 410 },
        { 33, 145 }
    };
    int waitCount = totalCount;
    private TimerListener listener;
    private ColoredLine[] line;
    private int[] type;
    private boolean airlockOpen = false;
    private boolean failed = false;
    private boolean finished = false;
    private int currentButton = 0;
    private int runCounter = 0;

    /**
     * Undocumented
     */
    public void init()
    {
        getContentPane().add(sim = new SimEngine());

        java.awt.Image icon = ImageLoader.getImage(this, "lab.jpg");

        if (icon != null)
        {
            sim.setBackgroundImage(icon);
            repaint();
        }

        frame = sim;
        walls = new Wall[12];
        doors = new Wall[4];
        doors[0] = new Wall(5, 124);
        doors[1] = new Wall(5, 99);
        doors[2] = new Wall(5, 119);
        doors[3] = new Wall(5, 119);
        walls[0] = new Wall(590, 5);
        walls[1] = new Wall(355, 5);
        walls[2] = new Wall(5, 275);
        walls[3] = new Wall(5, 118);
        walls[4] = new Wall(5, 50);
        walls[5] = new Wall(138, 5);
        walls[6] = new Wall(5, 335);
        walls[7] = new Wall(5, 460);
        walls[8] = new Wall(100, 5);
        walls[9] = new Wall(100, 5);
        walls[10] = new Wall(100, 5);
        walls[11] = new Wall(5, 270);

        for (int i = 0; i < walls.length; i++)
        {
            sim.add(walls[i], locations[i][0], locations[i][1]);
        }

        // Don't add the last door--it's the "airlock"
        for (int i = 0; i < (doors.length - 1); i++)
        {
            sim.add(doors[i], doorLocations[i][0], doorLocations[i][1]);
        }

        airlockButton = new ActivatorButton();
        airlockButton.setActivationCount(1);
        airlockButton.setOn(true);
        sim.add(airlockButton, 555, 90);
        mainButton = new ActivatorButton[4];
        buttonResetTime = new int[mainButton.length];
        line = new ColoredLine[mainButton.length];
        type = new int[mainButton.length];

        for (int i = 0; i < mainButton.length; i++)
        {
            type[i] = (int) (Math.random() * 4) + 1;

            if (i == runCounter)
            {
                type[i] = 1;
            }

            if ((type[i] == 1) && (runCounter != i))
            {
                type[i] = 2;
            }

            mainButton[i] = new ActivatorButton(type[i]);
            buttonResetTime[i] = MAX_TIME;
            sim.add(mainButton[i], buttonLocations[i][0],
                buttonLocations[i][1]);

            if (type[i] == 1)
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.BLACK);
            }
            else if (type[i] == 2)
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.BLUE);
            }
            else if (type[i] == 3)
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.MED_GRAY);
            }
            else if (type[i] == 4)
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.GREEN);
            }
            else
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.RED);
            }

            int offset = 35;

            if (buttonLocations[i][1] > 250)
            {
                offset = -15;
            }

            sim.add(line[i], buttonLocations[i][0] - 15,
                buttonLocations[i][1] + offset);
        }

        listener = new TimerListener();
        sim.addActionListener(listener);
    }

    /**
     * Undocumented
     */
    public void resetRoom()
    {
        runCounter++;
        runCounter = runCounter % 4;

        for (int i = 0; i < doors.length; i++)
        {
            try
            {
                sim.remove(doors[i]);
            }
            catch (Exception e)
            {
                ;
            }
        }

        for (int i = 0; i < mainButton.length; i++)
        {
            try
            {
                sim.remove(line[i]);
            }
            catch (Exception e)
            {
                ;
            }

            try
            {
                sim.remove(mainButton[i]);
            }
            catch (Exception e)
            {
                ;
            }
        }

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            ;
        }

        airlockButton.reset();

        for (int i = 0; i < mainButton.length; i++)
        {
            type[i] = (int) (Math.random() * 4) + 1;

            if (i == runCounter)
            {
                type[i] = 1;
            }

            if ((type[i] == 1) && (runCounter != i))
            {
                type[i] = 2;
            }

            mainButton[i] = new ActivatorButton(type[i]);
            buttonResetTime[i] = MAX_TIME;
            sim.add(mainButton[i], buttonLocations[i][0],
                buttonLocations[i][1]);

            if (type[i] == 1)
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.BLACK);
            }
            else if (type[i] == 2)
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.BLUE);
            }
            else if (type[i] == 3)
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.MED_GRAY);
            }
            else if (type[i] == 4)
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.GREEN);
            }
            else
            {
                line[i] =
                    new ColoredLine(50, 7, ReflectiveColors.RED);
            }

            int offset = 35;

            if (buttonLocations[i][1] > 250)
            {
                offset = -15;
            }

            sim.add(line[i], buttonLocations[i][0] - 15,
                buttonLocations[i][1] + offset);
        }

        // Don't add the last door--it's the "airlock"
        for (int i = 0; i < (doors.length - 1); i++)
        {
            sim.add(doors[i], doorLocations[i][0], doorLocations[i][1]);
        }

        airlockOpen = false;
        currentButton = 0;
        failed = false;
        finished = false;
    }

    /**
     * Undocumented
     */
    public void resetSimulation()
    {
        resetRoom();
        destroyRobot();
    }

    /**
     * Undocumented
     */
    public void run()
    {
        printLine(
            "This simulation demonstrates the use of several different concepts:");
        printLine(
            "  1. Buttons--Objects that change (in this case, when they're run over)");
        printLine(
            "     a. Immediate change in appearance when touched (light blue to dark blue)");
        printLine(
            "     b. When pressed the correct number of times, button turns red");
        printLine("     c. If pressed too many times, resets");
        printLine(
            "  2. Interactive Simulation--The walls in the simulation change (aka doors) based on actions in the simulation");
        printLine(
            "     a. The airlock button changes the airlock immediately");
        printLine(
            "     b. The exit door opens based on entry of the correct sequence and order of the buttons");
        printLine();
        printLine("The source code for this simulation is in:");
        printLine(
            "- eecs.jago.instructor.gossett.ActivatorButton  (the interactive buttons)");
        printLine(
            "- eecs.jago.instructor.gossett.IterationHW  (the interactive simulation)");
        printLine(
            "- eecs.instructor.gossett.GossettRobotGui (the wrapper)");

        boolean runAgain = false;
        int choice =
            chooseButton("Use this simulation or regenerate a new one?",
                "Use", "Regenerate");

        while (choice == BUTTON2)
        {
            resetSimulation();
            choice =
                chooseButton("Use this simulation or regenerate a new one?",
                    "Use", "Regenerate");
        }

        do
        {
            createRobot(670, 550, 0);
            setPower(10);
            goForward(5400);
            pivotLeft(90);
            goForward(2900);
            pivotLeft(90);
            goForward(1000);
            pivotRight(90);
            goForward(700);
            pivotLeft(90);

            int color = 0;
            goForward();

            do
            {
                color = getReflectanceSensor();
            }
            while (color == OFF);

            halt();

            int count = 0;

            if (color == BLACK)
            {
                count = 1;
            }
            else if (color == BLUE)
            {
                count = 2;
            }
            else if (color == MED_GRAY)
            {
                count = 3;
            }
            else if (color == GREEN)
            {
                count = 4;
            }
            else if (color == RED)
            {
                count = 5;
            }

            // Push Top Right
            goBackward(100);

            for (int i = 0; i < count; i++)
            {
                goForward(500);
                goBackward(500);
            }

            goBackward(100);

            if (count != 1)
            {
                pivotRight(90);
                goForward(2000);
                pivotLeft(90);
                goForward();

                do
                {
                    color = getReflectanceSensor();
                }
                while (color == OFF);

                halt();
                count = 0;

                if (color == BLACK)
                {
                    count = 1;
                }
                else if (color == BLUE)
                {
                    count = 2;
                }
                else if (color == MED_GRAY)
                {
                    count = 3;
                }
                else if (color == GREEN)
                {
                    count = 4;
                }
                else if (color == RED)
                {
                    count = 5;
                }

                // Push Top Left
                goBackward(100);

                for (int i = 0; i < count; i++)
                {
                    goForward(500);
                    goBackward(500);
                }

                if (count != 1)
                {
                    pivotRight(180);
                    goForward();

                    do
                    {
                        color = getReflectanceSensor();
                    }
                    while (color == OFF);

                    halt();
                    count = 0;

                    if (color == BLACK)
                    {
                        count = 1;
                    }
                    else if (color == BLUE)
                    {
                        count = 2;
                    }
                    else if (color == MED_GRAY)
                    {
                        count = 3;
                    }
                    else if (color == GREEN)
                    {
                        count = 4;
                    }
                    else if (color == RED)
                    {
                        count = 5;
                    }

                    // Push Bottom Left Button
                    goBackward(100);

                    for (int i = 0; i < count; i++)
                    {
                        goForward(500);
                        goBackward(500);
                    }

                    if (count != 1)
                    {
                        pivotRight(90);
                        goForward(2000);
                        pivotLeft(90);
                        goForward();

                        do
                        {
                            color = getReflectanceSensor();
                        }
                        while (color == OFF);

                        halt();
                        count = 0;

                        if (color == BLACK)
                        {
                            count = 1;
                        }
                        else if (color == BLUE)
                        {
                            count = 2;
                        }
                        else if (color == MED_GRAY)
                        {
                            count = 3;
                        }
                        else if (color == GREEN)
                        {
                            count = 4;
                        }
                        else if (color == RED)
                        {
                            count = 5;
                        }

                        // Push Top Right Button
                        goBackward(100);

                        for (int i = 0; i < count; i++)
                        {
                            goForward(400);
                            goBackward(400);
                        }

                        pivotRight(90);
                        goForward(600);
                        pivotLeft(90);
                        goForward(900);
                        pivotRight(90);
                        goForward(2200);
                    }
                    else
                    {
                        goBackward(200);
                        pivotLeft(180);
                        goForward(1700);
                        pivotRight(90);
                        goForward(1200);
                    }
                }
                else
                {
                    pivotLeft(180);
                    goForward(1600);
                    pivotLeft(90);
                    goForward(1000);
                }
            }
            else
            {
                pivotRight(90);
                goForward(800);
                pivotRight(90);
                goForward(1200);
                pivotLeft(90);
                goForward(2400);
            }

            if (chooseButton("Run Again?", "Yes", "No") == BUTTON1)
            {
                runAgain = true;
                resetSimulation();
            }
            else
            {
                runAgain = false;
            }
        }
        while (runAgain);
    }

    /**
     * Undocumented
     */
    void changeAirlock()
    {
        airlockButton.reset();

        if (!airlockOpen)
        {
            sim.remove(doors[doors.length - 2]);
            sim.add(doors[doors.length - 1],
                doorLocations[doors.length - 1][0],
                doorLocations[doors.length - 1][1]);
            airlockOpen = true;
            airlockButton.setType(0);
        }
        else
        {
            sim.remove(doors[doors.length - 1]);
            sim.add(doors[doors.length - 2],
                doorLocations[doors.length - 2][0],
                doorLocations[doors.length - 2][1]);
            airlockOpen = false;
            airlockButton.setType(1);
        }
    }

    /**
     * Undocumented
     */
    void openRoom()
    {
        if (currentButton < 2)
        {
            sim.remove(doors[0]);
        }
        else if (currentButton < 3)
        {
            sim.remove(doors[1]);
        }
    }

    class TimerListener implements java.awt.event.ActionListener
    {
        /**
         * Updates the state of the simulation based on the current
         * event notification (from the Timer).
         *
         * @param actionEvent DOCUMENT ME!
         */
        public void actionPerformed(
            java.awt.event.ActionEvent actionEvent)
        {
            if (airlockButton.isActivated())
            {
                changeAirlock();
            }

            if (failed || finished)
            {
                return;
            }

            for (int i = 0; i < mainButton.length; i++)
            {
                if (mainButton[i].isActivated())
                {
                    buttonResetTime[i]--;

                    if (buttonResetTime[i] < 0)
                    {
                        mainButton[i].reset();
                        buttonResetTime[i] = MAX_TIME;
                    }

                    if (i == currentButton)
                    {
                        if ((type[i] == 1) ||
                              (i == (mainButton.length - 1)))
                        {
                            finished = true;
                        }
                        else
                        {
                            currentButton++;
                        }
                    }
                    else if (i > currentButton)
                    {
                        failed = true;
                    }
                }
            }

            if (finished)
            {
                for (int i = 0; i < mainButton.length; i++)
                {
                    mainButton[i].setOn(false);
                }

                openRoom();
            }
        }
    }
}
