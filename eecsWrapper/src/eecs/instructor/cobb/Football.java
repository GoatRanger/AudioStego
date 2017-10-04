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

package eecs.instructor.cobb;

import java.io.*;


import javax.swing.*;




class Football {

	private static boolean locked = false;
    public static FootballTeam Home=null;
    public static FootballTeam Visitor=null;
    public static String[] names = new String[2];
    public static void playGame() {
    	
        if (locked) {

            return;
        }

        locked = true;

        String[] candidates = findCandidates();
        System.out.println(candidates.length);
        java.util.Arrays.sort(candidates);
        
        String[] names = chooseRobots(candidates);
        instantiateRobots(names);
   //     Arena.runSimulation();
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
            new JComboBox(choices), new JComboBox(choices) 
            
        };

        /* set the first two selections randomly (duplicates okay)
        player[0].setSelectedIndex(
                1 + (int)(Math.random() * candidates.length));
        player[1].setSelectedIndex(
                1 + (int)(Math.random() * candidates.length));
*/
        String RUN = "Play Ball";
        int value = JOptionPane.showOptionDialog(null, 
                                                 new Object[] {
            "Visitors   ", player[0], "Home   ", player[1] // message
  
        }, 
                                                 "Choose Teams", // title,                                         
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

        
        int numPicked = 0;

        for (int i = 0; i < 2; i++) {

            Object name = player[i].getSelectedItem();
            if (name == null || name == NONE) {
                names[i] = null;
            } else {
                names[i] = (String)name;
               // System.out.println("names of teams"+names[i]);
       /*         if (i==0)
                	Visitor.setName(names[i]);
                else
                	Home.setName(names[i]);*/
                numPicked++;
            }
        }

        if (numPicked < 2) {
           // Util.showFatalError("Must choose at least two robots.");
        	System.out.println("less than 2 teams chosen");
        }
        else
        {
        	//Visitor.setName(names[0]);
        	//Home.setName(names[1]);
        }

        return names;
    } // end chooseRobots

    // find all files of the form TEAM_<something>.class

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    private static String[] findCandidates() {

        File[] files = new File (".").listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
            //   System.out.println(name);
                return name.startsWith(Const.PREFIX)
                       && name.endsWith(Const.SUFFIX);
            }
        });

        if (files == null || files.length == 0) {
    //  Util.showFatalError("No robots in this folder!");
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
       
        
        for (int i = 0; i < names.length; i++) {

            if (names[i] != null) {
                String name=names[i];
                try {
                    String teamName = Const.PREFIX+names[i];

                    Class teamClass = ClassLoader.getSystemClassLoader().loadClass(teamName);
                    if (i!=1){
                        Visitor= (FootballTeam)teamClass.newInstance();
                    }
                    else
                    {    
                        Home = (FootballTeam)teamClass.newInstance();
                    }   
                    
                    //robot.initialize(name, i);
                } catch (ClassNotFoundException e) {
                  	e.printStackTrace();
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
                   e.printStackTrace();
                    showLoadError(name, "Unknown error.");
                }
            }
        }
       
      
    } // end instantiateRobots

    public static FootballTeam getHomeTeam()
	{
    	return Home;
    }
    public static FootballTeam getVisitingTeam()
	{
    	return Visitor;
    }
    /**
     * Undocumented
     * 
     * @param name Empty
     * @param msg Empty
     */
    private static void showLoadError(String name, String msg) {
      // Util.showError("Error loading robot " + name + ": " + msg);
        System.out.println("error"+" "+msg);
    	//System.exit(0);
    }
} // end Bolo