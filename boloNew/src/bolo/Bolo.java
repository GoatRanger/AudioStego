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

package bolo;

import java.io.*;

import javax.swing.*;


class Bolo {

    private static boolean locked = false;

    static void playGame() {

        if (locked) {

            return;
        }

        locked = true;

        String[] candidates = findCandidates();
        java.util.Arrays.sort(candidates);

        String[] names = chooseRobots(candidates);
        instantiateRobots(names);
        Arena.runSimulation();
    }

    // choose a robot for each position

    /**
     * Undocumented
     * 
     * @param candidates Empty
     * @return Empty 
     */
    private static String[] chooseRobots(String[] candidates) {

        String[] choices = new String[candidates.length + 1];

        // add [none] as the first choice
        final String NONE = "[none]";
        choices[0] = NONE;

        for (int i = 0; i < candidates.length; i++) {
            choices[i + 1] = candidates[i];
        }

        // build the gui
        JComboBox[] player = new JComboBox[] {
            new JComboBox<Object>(choices), new JComboBox<Object>(choices), 
            new JComboBox<Object>(choices), new JComboBox<Object>(choices)
        };

        // set the first two selections randomly (duplicates okay)
        player[0].setSelectedIndex(
                1 + (int)(Math.random() * candidates.length));
        player[1].setSelectedIndex(
                1 + (int)(Math.random() * candidates.length));

        String RUN = "Battle!";
        int value = JOptionPane.showOptionDialog(null, 
                                                 new Object[] {
            "Player 1   (West)", player[0], "Player 2   (East)", player[1], 
            "Player 3   (North)", player[2], "Player 4   (South)", // message
            player[3]
        }, 
                                                 "Choose Robots", // title,                                         
                                                 JOptionPane.DEFAULT_OPTION, 
                                                 JOptionPane.QUESTION_MESSAGE, 
                                                 null, // icon                                         
                                                 new String[] { RUN, "Quit" }, 
                                                 RUN // default item
                                                 );

        switch (value) {

            case JOptionPane.CLOSED_OPTION:
            case 1: // Quit
                System.exit(0);
        }

        String[] names = new String[4];
        int numPicked = 0;

        for (int i = 0; i < 4; i++) {

            Object name = player[i].getSelectedItem();
            if (name == null || name == NONE) {
                names[i] = null;
            } else {
                names[i] = (String)name;
                numPicked++;
            }
        }

        if (numPicked < 2) {
            Util.showFatalError("Must choose at least two robots.");
        }

        return names;
    } // end chooseRobots

    // find all files of the form RW_<something>.class

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    private static String[] findCandidates() {

        File[] files = new File(".").listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {

                return name.startsWith(Const.PREFIX)
                       && name.endsWith(Const.SUFFIX);
            }
        });

        if (files == null || files.length == 0) {
            Util.showFatalError("No robots in this folder!");
        }

        String[] candidates = new String[files.length];

        for (int i = 0; i < files.length; i++) {

            String filename = files[i].getName();
            
            candidates[i] = filename.substring(Const.PREFIX.length(), 
                                               filename.length()
                                               - Const.SUFFIX.length());
        }

        return candidates;
    } // end findCandidates

    /**
     * Undocumented
     * 
     * @param names Empty
     */
    private static void instantiateRobots(String[] names) {

        Pool robots = new Pool();

        for (int i = 0; i < names.length; i++) {

            if (names[i] != null) {

                String name = names[i];
                try {

                    Class<?> roboClass = Class.forName(Const.PREFIX + name);
                    Robot robot = (Robot)roboClass.newInstance();
                    robot.initialize(name, i);
                } catch (ClassNotFoundException e) {
                    showLoadError(name, "Could not find class.");
                }
                 catch (IllegalAccessException e) {
                    showLoadError(name, "Did you forget to make class public?");
                }
                 catch (InstantiationException e) {
                    showLoadError(name, "Could not create robot.");
                }
                 catch (ClassCastException e) {
                    showLoadError(name, "Class is not a robot.");
                }
                 catch (Exception e) {
                    showLoadError(name, "Unknown error.");
                }
            }
        }
    } // end instantiateRobots

    /**
     * Undocumented
     * 
     * @param name Empty
     * @param msg Empty
     */
    private static void showLoadError(String name, String msg) {
        Util.showError("Error loading robot " + name + ": " + msg);
        System.exit(0);
    }
} // end Bolo