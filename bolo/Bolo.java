package bolo;

import javax.swing.*;
import java.io.*;

class Bolo {

  private static boolean locked = false;

  static void playGame() {
    if (locked) return;

	locked = true;
    String[] candidates = findCandidates();
	java.util.Arrays.sort(candidates);
    String[] names = chooseRobots(candidates);
	instantiateRobots(names);
	Arena.runSimulation();
  }

  // find all files of the form RW_<something>.class
  private static String[] findCandidates() {
    File[] files =
	  new File(".").listFiles(
        new FilenameFilter() {
	      public boolean accept(File dir,String name) {
		    return name.startsWith(Const.PREFIX) && name.endsWith(Const.SUFFIX);
	      }
	    });

    if (files == null || files.length == 0)
	  Util.showFatalError("No robots in this folder!");

    String[] candidates = new String[files.length];

	for (int i = 0; i < files.length; i++) {
	  String filename = files[i].getName();
	  candidates[i] = filename.substring(Const.PREFIX.length(),
	                                     filename.length()-Const.SUFFIX.length());
	}

	return candidates;
  } // end findCandidates


  // choose a robot for each position
  private static String[] chooseRobots(String[] candidates) {

    String[] choices = new String[candidates.length+1];

	// add [none] as the first choice
	final String NONE = "[none]";
	choices[0] = NONE;
    for (int i = 0; i < candidates.length; i++)
	  choices[i+1] = candidates[i];

	// build the gui
    JComboBox[] player =
	  new JComboBox[] {new JComboBox(choices),new JComboBox(choices),
	                   new JComboBox(choices),new JComboBox(choices)};
    // set the first two selections randomly (duplicates okay)
	player[0].setSelectedIndex(1 + (int)(Math.random() * candidates.length));
	player[1].setSelectedIndex(1 + (int)(Math.random() * candidates.length));

    String RUN = "Battle!";
	int value = 
	  JOptionPane.showOptionDialog(
	    null,
        new Object[] {"Player 1   (West)", player[0],
		              "Player 2   (East)", player[1],
					  "Player 3   (North)", player[2],
					  "Player 4   (South)", player[3]}, // message
        "Choose Robots", // title,
		JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,  // icon
		new String[] { RUN, "Quit" },
        RUN // default item
	  );

    switch (value)
	{
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
	  }
	  else {
	    names[i] = (String)name;
		numPicked++;
	  }
	}

	if (numPicked < 2)
	  Util.showFatalError("Must choose at least two robots.");

    return names;
  } // end chooseRobots

  private static void showLoadError(String name,String msg) {
    Util.showError("Error loading robot " + name + ": " + msg);
    System.exit(0);
  }

  private static void instantiateRobots(String[] names) {
    Pool robots = new Pool();

    for (int i = 0; i < names.length; i++) {
	  if (names[i] != null) {
	    String name = names[i];

	    try {
	      Class roboClass = Class.forName(Const.PREFIX + name);
		  Robot robot = (Robot)roboClass.newInstance();
		  robot.initialize(name,i);
        }
		catch (ClassNotFoundException e) {
	      showLoadError(name,"Could not find class.");
		}
		catch (IllegalAccessException e) {
		  showLoadError(name,"Did you forget to make class public?");
		}
		catch (InstantiationException e) {
		  showLoadError(name,"Could not create robot.");
		}
		catch (ClassCastException e) {
		  showLoadError(name,"Class is not a robot.");
		}
		catch (Exception e) {
		  showLoadError(name,"Unknown error.");
		}
      }
	}
  } // end instantiateRobots

} // end Bolo
