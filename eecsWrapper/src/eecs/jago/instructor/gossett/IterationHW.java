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
 * IterationHW.java
 *
 * Created on March 1, 2002, 10:46 PM
 */
package eecs.jago.instructor.gossett;

import jago.element.*;


/**
 * The Iteration Homework, March 2002.
 * 
 * <P>
 * All of the try clauses are a hack, due to the organization of the packages
 * and what is available--didn't account properly for removal of elements.
 * Need to fix in version 6.2
 * </p>
 * 
 * @author Karl Gossett
 */
public class IterationHW
    extends jago.SimBox {

    protected ActivatorButton airlockButton;
    protected Wall[] doors;
    protected boolean isOpen = false;
    protected ActivatorButton[] mainButton;
    protected final int totalCount = 5;
    protected Wall[] walls;
    final int MAX_TIME = 1000;
    int[][] buttonLocations = {
        { 370, 395 }, { 190, 395 }, { 190, 104 }, { 370, 104 }
    };
    int[] buttonResetTime;
    int[][] doorLocations = {
        { 140, 155 }, { 140, 280 }, { 490, 35 }, { 630, 35 }
    };
    int[][] locations = {
        { 140, 30 }, { 140, 430 }, { 490, 155 }, { 140, 36 }, 
        { 140, 380 }, { 491, 155 }, { 630, 155 }, { 730, 30 }, { 39, 145 }, 
        { 39, 276 }, { 39, 410 }, { 33, 145 }
    };
    private boolean airlockOpen = false;
    private int currentButton = 0;
    private boolean failed = false;
    private boolean finished = false;
    private ColoredLine[] line;
    private TimerListener listener;
    private int runCounter = 0;
    private int[] type;
    int waitCount = totalCount;

    /**
     * Creates a new instance of IterationHW
     */
    public IterationHW() {
        super();
        setBackgroundImage("lab.jpg");
        walls = new Wall[13];
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
        walls[12] = new Wall(5, 270);

        for (int i = 0; i < walls.length; i++) {
            add(walls[i], locations[i][0], locations[i][1]);
        }

        // Don't add the last door--it's the "airlock"
        for (int i = 0; i < doors.length - 1; i++) {
            add(doors[i], doorLocations[i][0], doorLocations[i][1]);
        }

        airlockButton = new ActivatorButton();
        airlockButton.setActivationCount(1);
        airlockButton.setOn(true);
        add(airlockButton, 555, 90);
        mainButton = new ActivatorButton[4];
        buttonResetTime = new int[mainButton.length];
        line = new ColoredLine[mainButton.length];
        type = new int[mainButton.length];

        for (int i = 0; i < mainButton.length; i++) {
            type[i] = (int)(Math.random() * 4) + 1;

            if (i == runCounter) {
                type[i] = 1;
            }

            if (type[i] == 1 && runCounter != i) {
                type[i] = 2;
            }

            mainButton[i] = new ActivatorButton(type[i]);
            buttonResetTime[i] = MAX_TIME;
            add(mainButton[i], buttonLocations[i][0], buttonLocations[i][1]);

            if (type[i] == 1) {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.BLACK);
            } else if (type[i] == 2) {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.BLUE);
            } else if (type[i] == 3) {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.MED_GRAY);
            } else if (type[i] == 4) {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.GREEN);
            } else {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.RED);
            }

            int offset = 35;

            if (buttonLocations[i][1] > 250) {
                offset = -15;
            }

            add(line[i], buttonLocations[i][0] - 15, 
                buttonLocations[i][1] + offset);
        }

        listener = new TimerListener();
        simEngine.addActionListener(listener);
    }

    /**
     * Undocumented
     */
    public void resetRoom() {
        runCounter++;
        runCounter = runCounter % 4;

        for (int i = 0; i < doors.length; i++) {

            try {
                remove(doors[i]);
            } catch (Exception e) {
                ;
            }
        }

        for (int i = 0; i < mainButton.length; i++) {

            try {
                remove(line[i]);
            } catch (Exception e) {
                ;
            }

            try {
                remove(mainButton[i]);
            } catch (Exception e) {
                ;
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            ;
        }

        airlockButton.reset();

        for (int i = 0; i < mainButton.length; i++) {
            type[i] = (int)(Math.random() * 4) + 1;

            if (i == runCounter) {
                type[i] = 1;
            }

            if (type[i] == 1 && runCounter != i) {
                type[i] = 2;
            }

            mainButton[i] = new ActivatorButton(type[i]);
            buttonResetTime[i] = MAX_TIME;
            add(mainButton[i], buttonLocations[i][0], buttonLocations[i][1]);

            if (type[i] == 1) {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.BLACK);
            } else if (type[i] == 2) {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.BLUE);
            } else if (type[i] == 3) {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.MED_GRAY);
            } else if (type[i] == 4) {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.GREEN);
            } else {
                line[i] = new ColoredLine(50, 7, ReflectiveColors.RED);
            }

            int offset = 35;

            if (buttonLocations[i][1] > 250) {
                offset = -15;
            }

            add(line[i], buttonLocations[i][0] - 15, 
                buttonLocations[i][1] + offset);
        }

        // Don't add the last door--it's the "airlock"
        for (int i = 0; i < doors.length - 1; i++) {
            add(doors[i], doorLocations[i][0], doorLocations[i][1]);
        }

        airlockOpen = false;
        currentButton = 0;
        failed = false;
        finished = false;
    }

    /**
     * Undocumented
     */
    void changeAirlock() {
        airlockButton.reset();

        if (!airlockOpen) {
            remove(doors[doors.length - 2]);
            add(doors[doors.length - 1], doorLocations[doors.length - 1][0], 
                doorLocations[doors.length - 1][1]);
            airlockOpen = true;
            airlockButton.setType(0);
        } else {
            remove(doors[doors.length - 1]);
            add(doors[doors.length - 2], doorLocations[doors.length - 2][0], 
                doorLocations[doors.length - 2][1]);
            airlockOpen = false;
            airlockButton.setType(1);
        }
    }

    /**
     * Undocumented
     */
    void openRoom() {

        if (currentButton < 2) {
            remove(doors[0]);
        } else if (currentButton < 3) {
            remove(doors[1]);
        }
    }

    class TimerListener
        implements java.awt.event.ActionListener {

        //~ Methods ...........................................................

        /**
         * Updates the state of the simulation based on the current event
         * notification (from the Timer).
         * 
         * @param actionEvent DOCUMENT ME!
         */
        public void actionPerformed(java.awt.event.ActionEvent actionEvent) {

            if (airlockButton.isActivated()) {
                changeAirlock();
            }

            if (failed || finished) {

                return;
            }

            for (int i = 0; i < mainButton.length; i++) {

                if (mainButton[i].isActivated()) {
                    buttonResetTime[i]--;

                    if (buttonResetTime[i] < 0) {
                        mainButton[i].reset();
                        buttonResetTime[i] = MAX_TIME;
                    }

                    if (i == currentButton) {

                        if (type[i] == 1 || i == mainButton.length - 1) {
                            finished = true;
                        } else {
                            currentButton++;
                        }
                    } else if (i > currentButton) {
                        failed = true;
                    }
                }
            }

            if (finished) {

                for (int i = 0; i < mainButton.length; i++) {
                    mainButton[i].setOn(false);
                }

                openRoom();
            }
        }
    }
}