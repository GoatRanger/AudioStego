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
/* eecs.GuiBase:
 *   !!! NOT INTENDED TO BE USED DIRECTLY !!!
 *   Contains all the methods common to both eecs.Gui and eecs.RobotGui.
 */
package eecs;

import java.io.*;

import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.*;


/**
 * A set of commands used to wrap normal Java GUI commands into an
 * easy to use implementation that doesn't require knowledge of
 * object oriented programming.
 * @version %I%, %G%
 */
public class GuiBase extends JApplet {
  
  protected static Logger logger = Logger.getLogger("eecs");
  
  /**
   * The result type for the first button from an input dialog that
   * uses buttons.
   */
  public static final int BUTTON1 = 0;

  /**
   * The result type for the second button from an input dialog
   * that uses buttons.
   */
  public static final int BUTTON2 = 1;

  /**
   * The result type for the third button from an input dialog that
   * uses 3 or more buttons.
   */
  public static final int BUTTON3 = 2;

  /**
   * The result type for the cancel button from an input dialog
   * that has a Ok and Cancel button.
   */
  public static final int CANCEL = -1;

  // protected gui object (visible only within the package)

  /** The output box used for text output in the GUI. */
  static JTextArea output;

  /** The main frame of the EECS wrapper. */
  protected static JFrame frame; // main container for all other windows

  /** A reader for text files. */
  protected static BufferedReader fileReader = null;

  // File stuff for writing

  /** A writer for text files. */
  protected static PrintWriter fileWriter = null;
  private static String lookAhead = null;

  // Testing framework

  /**
   * The Properties object used to communicate information from
   * outside applications, currently used to allow the IT105
   * Editor to disable the GUI to run automated testing on code.
   */
  protected static Properties p = null;
  protected static boolean runningTests = false;

  /** The reader to get input from the Standard Input (System.in) */
  protected static BufferedReader testConsoleReader = new BufferedReader(new InputStreamReader(
        System.in));

  /**
   * Undocumented
   *
   * @param prompt Empty
   *
   * @return Empty
   */
  public static double getDouble(String prompt) {
    getProperties();

    double value = 0.0;

    if (runningTests) {
      while (true) {
        try {
          String str = testConsoleReader.readLine();
          value = Double.parseDouble(str);

          break;
        } catch (IOException e) {
          System.err.println("Error reading a double from test file (console)." +
            "  Format in input file was incorrect.");
        } catch (NumberFormatException ne) {
          // Do nothing, just iterate!
        }
      }
    } else {
      while (true) {
        String str = getString(prompt);

        try {
          value = Double.parseDouble(str);

          break;
        } catch (NumberFormatException e) {
          showError("Not a valid double.  Please try again.");
        }
      }
    }

    return value;
  }

  /**
   * Undocumented
   *
   * @param prompt Empty
   *
   * @return Empty
   */
  public static int getInt(String prompt) {
    getProperties();

    int value = 0;

    if (runningTests) {
      while (true) {
        try {
          String str = testConsoleReader.readLine();
          value = Integer.parseInt(str);
          break;
        } catch (IOException e) {
          System.err.println(
            "Error reading an integer from test file (console)." +
            "  Format in input file was incorrect.");
        } catch (NumberFormatException e) {
          // Do nothing
        }
      }
    } else {
      while (true) {
        String str = getString(prompt);

        try {
          value = Integer.parseInt(str);

          break;
        } catch (NumberFormatException e) {
          showError("Not a valid integer.  Please try again.");
        }
      }
    }

    return value;
  }

  /**
   * Undocumented
   *
   * @param prompt Empty
   *
   * @return Empty
   */
  public static String getString(String prompt) {
    getProperties();

    String result = "";

    if (runningTests) {
      try {
        result = testConsoleReader.readLine();
      } catch (IOException e) {
        System.err.println("Error reading a String from test file (console)." +
          "  Format in input file was incorrect.");
      }
    } else {
      //            String ok = "OK";
      //            JTextField field = new JTextField();
      result = JOptionPane.showInputDialog(frame, prompt, "",
          JOptionPane.QUESTION_MESSAGE);

      if (result == null) {
        showError("Dialog Cancelled, exiting program.");
        System.exit(0);
      }
    }

    return result;
  }
  
  /**
   * Gets a single character.  Allows entry of multiple characters, but
   * will only accept when the entry is a single character.
   *
   * @param prompt The text to display.
   *
   * @return the character entered
   */
  public static char getChar(String prompt) {
    getProperties();

    String result = "";
    char charResult;
    if (runningTests) {
      while (true) {
	      try {
	        result = testConsoleReader.readLine();
	        if (result.length() == 1) {
	          charResult = result.charAt(0);
	          break;
	        }
	      } catch (IOException e) {
	        System.err.println("Error reading a char from test file (console)." +
	          "  Format in input file was incorrect.");
	      }
      }
    } else {
      while (true) {
	      //            String ok = "OK";
	      //            JTextField field = new JTextField();
	      result = JOptionPane.showInputDialog(frame, prompt, "",
	          JOptionPane.QUESTION_MESSAGE);
	      if (result == null) {
	        showError("Dialog Cancelled, exiting program.");
	        System.exit(0);
	      }
	      if (result.length() == 1) {
	        charResult = result.charAt(0);
	        break;
	      } else {
	        showError("Not a valid character.  Must be exactly one character entered.  Please try again.");
	      }
      }
    }

    return charResult;
  }

  /**
   * Undocumented
   *
   * @param width Empty
   * @param digits Empty
   * @param num Empty
   *
   * @return Empty
   */
  public static String centerAlign(int width, int digits, double num) {
    return centerAlign(width, showDouble(digits, num));
  }

  /**
   * Undocumented
   *
   * @param width Empty
   * @param num Empty
   *
   * @return Empty
   */
  public static String centerAlign(int width, int num) {
    return centerAlign(width, "" + num);
  }

  /**
   * Undocumented
   *
   * @param width Empty
   * @param str Empty
   *
   * @return Empty
   */
  public static String centerAlign(int width, String str) {
    if (str == null) {
      return spaces(width);
    } else {
      int sp = width - str.length();

      return spaces((sp + 1) / 2) + str + spaces(sp / 2);
    }
  }

  /**
   * Undocumented
   *
   * @param prompt Empty
   * @param button1 Empty
   * @param button2 Empty
   *
   * @return Empty
   */
  public static int chooseButton(String prompt, String button1, String button2) {
    return chooseButton(prompt, new String[] { button1, button2 });
  }

  /**
   * Undocumented
   *
   * @param prompt Empty
   * @param button1 Empty
   * @param button2 Empty
   * @param button3 Empty
   *
   * @return Empty
   */
  public static int chooseButton(String prompt, String button1, String button2,
    String button3) {
    return chooseButton(prompt, new String[] { button1, button2, button3 });
  }

  /**
   * Undocumented
   *
   * @param prompt Empty
   * @param buttons Empty
   *
   * @return Empty
   */
  public static int chooseButton(String prompt, String[] buttons) {
    getProperties();

    int value = 0;

    if (runningTests) {
      try {
        String str = testConsoleReader.readLine();
        value = Integer.parseInt(str);
      } catch (IOException e) {
        System.err.println(
          "Error reading an input for chooseButton from test file." +
          "  Format in input file was incorrect.");
      }
    } else {
      // Trying to fix a NullPointerException in 
      // javax.swing.SizeRequirements.getAlignedSizeRequirements(...) which
      // is called during the creation of showOptionDialog.  Only plausible
      // reason is that buttons wasn't properly initialized (couldn't reproduce the error)
      if (buttons == null) {
        showMessage(
          "You need to make sure you have options when using chooseButton.");
        System.exit(0);
      }

      value = JOptionPane.showOptionDialog(frame, prompt, // message
          "", // title
          JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, // icon
          buttons, (buttons.length > 0) ? buttons[0] : null // default button
        );

      if (value == JOptionPane.CLOSED_OPTION) {
        value = CANCEL;
      }
    }

    return value;
  }

  // end method chooseButton

  /**
   * Allows a user to select items from a list box.
   *
   * @param prompt A <code>String</code> prompt for the window.
   * @param items An array of <code>String</code>s to use as the
   *        items of the list.
   *
   * @return The index of the chosen item in the list.
   */
  public static int chooseFromList(String prompt, String[] items) {
    getProperties();

    int value = 0;

    if (runningTests) {
      try {
        String str = testConsoleReader.readLine();
        value = Integer.parseInt(str);
      } catch (IOException e) {
        System.err.println(
          "Error reading an input for chooseList from test file." +
          "  Format in input file was incorrect.");
      }
    } else {
      // assumes: items != null && items.length > 0
      String ok = "OK";
      JList list = new JList(items);
      list.setSelectedIndex(0); // set default choice
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      JScrollPane pane = new JScrollPane(list);
      int result = JOptionPane.showOptionDialog(frame,
          new Object[] { prompt, pane // message
        }, "", // title,
          JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, // icon
          new String[] { ok // default item
        }, ok);

      switch (result) {
      case JOptionPane.CLOSED_OPTION:
        value = CANCEL;
        break;
      default:
        value = list.getSelectedIndex();
      }
    }

    return value;
  }

  /**
   * Undocumented
   */
  public static void clearOutput() {
    output.setText("");
  }

  // end method chooseFromList

  /**
   * Undocumented
   */
  public static void closeReadFile() {
    if (fileReader != null) {
      try {
        fileReader.close();
      } catch (Exception e) {
        // ignore the exception
      }

      fileReader = null;
      lookAhead = null;
    }
  }

  // end method closeReadFile

  /**
   * Undocumented
   */
  public static void closeWriteFile() {
    if (fileWriter != null) {
      try {
        fileWriter.close();
      } catch (Exception e) {
        // ignore the exception
      }

      fileWriter = null;
    }
  }

  // end method closeWriteFile

  /**
   * If running as an Applet
   */
  public void init() {
    setContentPane(frame);
  }

  // replaces the old Format stuff

  /**
   * Undocumented
   *
   * @param width Empty
   * @param digits Empty
   * @param num Empty
   *
   * @return Empty
   */
  public static String leftAlign(int width, int digits, double num) {
    return leftAlign(width, showDouble(digits, num));
  }

  /**
   * Undocumented
   *
   * @param width Empty
   * @param num Empty
   *
   * @return Empty
   */
  public static String leftAlign(int width, int num) {
    return leftAlign(width, "" + num);
  }

  /**
   * Undocumented
   *
   * @param width Empty
   * @param str Empty
   *
   * @return Empty
   */
  public static String leftAlign(int width, String str) {
    if (str == null) {
      return spaces(width);
    } else {
      return str + spaces(width - str.length());
    }
  }

  public static double log10(double n) {
    return Math.log(n)/Math.log(10);
  }
  
  /**
   * Undocumented
   *
   * @return Empty
   */
  public static boolean moreToRead() {
    // post-condition:
    //   if moreToRead() returns true, then lookAhead != null
    // readLine depends on this fact
    if (fileReader == null) {
      showError("Must open file before calling moreToRead().");
      System.exit(0);
    }

    if (lookAhead != null) {
      return true;
    }

    try {
      lookAhead = fileReader.readLine();

      return (lookAhead != null);
    } catch (Exception e) {
      // assume error means you cannot read any more
      // but otherwise do nothing about the error
      return false;
    }
  }

  /**
   * Undocumented
   *
   * @param filename Empty
   */
  public static void openReadFile(String filename) {
    if (fileReader != null) {
      showError("Old read file still open.  Can't open new one.");
      System.exit(0);
    }

    try {
      fileReader = new BufferedReader(new FileReader(filename));
    } catch (Exception e) {
      showError("Could not open file " + filename);
      System.exit(0);
    }

    lookAhead = null; // should already be null, but just to be safe
  }

  /**
   * Undocumented
   */
  public static void openReadFile() {
    if (fileReader != null) {
      showError("Old read file still open.  Can't open new one.");
      System.exit(0);
    }

    while (true) {
      JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));
      chooser.setDialogTitle("Open Read File");

      int option = chooser.showOpenDialog(frame);
      File file = chooser.getSelectedFile();

      if ((option == JFileChooser.APPROVE_OPTION) && (file != null)) {
        try {
          fileReader = new BufferedReader(new FileReader(file));
          lookAhead = null;

          return;
        } catch (Exception e) {
          showError("Error opening file.  Please try again.");
        }
      } else {
        showError("Not a valid file.  Please try again.");
      }
    }
  }

  // end method openReadFile()

  /**
   * Undocumented
   *
   * @param filename Empty
   */
  public static void openWriteFile(String filename) {
    if (fileWriter != null) {
      showError("Old write file still open.  Can't open new one.");
      System.exit(0);
    }

    try {
      fileWriter = new PrintWriter(new FileWriter(filename));
    } catch (Exception e) {
      showError("Could not open file " + filename);
      System.exit(0);
    }
  }

  /**
   * Undocumented
   */
  public static void openWriteFile() {
    if (fileWriter != null) {
      showError("Old write file still open.  Can't open new one.");
      System.exit(0);
    }

    while (true) {
      JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));
      chooser.setDialogTitle("Open Write File");
      chooser.setApproveButtonText("Create");

      int option = chooser.showSaveDialog(frame);
      File file = chooser.getSelectedFile();

      if ((option == JFileChooser.APPROVE_OPTION) && (file != null)) {
        try {
          fileWriter = new PrintWriter(new FileWriter(file));

          return;
        } catch (Exception e) {
          showError("Error opening file.  Please try again.");
        }
      } else {
        showError("Not a valid file.  Please try again.");
      }
    }
  }

  /**
   * Undocumented
   *
   * @param msg Empty
   */
  public static void print(String msg) {
    getProperties();

    if (runningTests) {
      System.out.print(msg);
    } else {
      output.append(msg);
    }

    // Including the following two lines would make it scroll
    // horizontally if the line extended past the edge of the screen.
    // This seems to be a little disconcerting, so I've left it off...
    // int pos = output.getText().length();
    // output.setCaretPosition(pos);
  }

  /**
   * Undocumented
   *
   * @param obj Empty
   */
  public static void print(Object obj) {
    print(obj.toString());
  }

  /**
   * Undocumented
   *
   * @param number Empty
   */
  public static void print(int number) {
    print("" + number);
  }

  /**
   * Undocumented
   *
   * @param number Empty
   */
  public static void print(double number) {
    print("" + number);
  }

  /**
   * Undocumented
   *
   * @param b Empty
   */
  public static void print(boolean b) {
    print("" + b);
  }

  /**
   * Undocumented
   *
   * @param ch Empty
   */
  public static void print(char ch) {
    print("" + ch);
  }

  /**
   * Undocumented
   *
   * @param msg Empty
   */
  public static void printLine(String msg) {
    getProperties();

    if (runningTests) {
      System.out.println(msg);
    } else {
      output.append(msg);
      output.append("\n");

      // make it scroll, if necessary
      int pos = output.getText().length();
      output.setCaretPosition(pos);
    }
  }

  /**
   * Undocumented
   *
   * @param obj Empty
   */
  public static void printLine(Object obj) {
    printLine(obj.toString());
  }

  /**
   * Undocumented
   *
   * @param number Empty
   */
  public static void printLine(int number) {
    printLine("" + number);
  }

  /**
   * Undocumented
   *
   * @param number Empty
   */
  public static void printLine(double number) {
    printLine("" + number);
  }

  /**
   * Undocumented
   *
   * @param b Empty
   */
  public static void printLine(boolean b) {
    printLine("" + b);
  }

  /**
   * Undocumented
   *
   * @param ch Empty
   */
  public static void printLine(char ch) {
    printLine("" + ch);
  }

  /**
   * Undocumented
   */
  public static void printLine() {
    printLine("");
  }

  // end method openWriteFile()

  /**
   * Reads a line of text from the currently open text file.
   *
   * @return The line of text read, as a <CODE>String</CODE>.
   */
  public static String readLine() {
    if (fileReader == null) {
      showError("Must open file before calling readLine().");
      System.exit(0);
    }

    if (!moreToRead()) {
      showError("Cannot read past end of file.");
      System.exit(0);
    }

    // lookAhead contains next line (guaranteed by moreToRead)
    String nextLine = lookAhead;
    lookAhead = null;

    return nextLine;
  }

  // end method readLine

  /**
   * Undocumented
   *
   * @param width Empty
   * @param digits Empty
   * @param num Empty
   *
   * @return Empty
   */
  public static String rightAlign(int width, int digits, double num) {
    return rightAlign(width, showDouble(digits, num));
  }

  /**
   * Undocumented
   *
   * @param width Empty
   * @param num Empty
   *
   * @return Empty
   */
  public static String rightAlign(int width, int num) {
    return rightAlign(width, "" + num);
  }

  /**
   * Undocumented
   *
   * @param width Empty
   * @param str Empty
   *
   * @return Empty
   */
  public static String rightAlign(int width, String str) {
    if (str == null) {
      return spaces(width);
    } else {
      return spaces(width - str.length()) + str;
    }
  }

  /**
   * Uses the FreeTTS text-to-speech package to speak.
   *
   * @param number The number to speak.
   */
  public static void say(int number) {
    say("" + number);
  }

  /**
   * Uses the FreeTTS text-to-speech package to speak.
   *
   * @param number a real number to speak.
   */
  public static void say(double number) {
    say("" + number);
  }

  /**
   * Uses the FreeTTS text-to-speech package to speak.  Speaking
   * characters can be difficult as the time to initialize the
   * engine and the style of utterance may result in
   * unintelligible speech.
   *
   * @param ch The character to say.
   */
  public static void say(char ch) {
    say("" + ch);
  }

  /**
   * Currently not supported.
   *
   * @param text The message to speak.
   */
  public static void say(String text) {
    getProperties();

    if (runningTests) {
      System.out.println(text);
    } else {
      printLine("Saying: " + text);
    }
  }

  /**
   * Undocumented
   *
   * @param msg Empty
   */
  public static void showError(String msg) {
    getProperties();

    if (runningTests) {
      System.out.println(msg);
    } else {
      JOptionPane.showMessageDialog(frame, msg, "Error",
        JOptionPane.ERROR_MESSAGE);
    }
  }

  // replaces the old MessageBox stuff

  /**
   * Undocumented
   *
   * @param msg Empty
   */
  public static void showMessage(String msg) {
    getProperties();

    if (runningTests) {
      System.out.println(msg);
    } else {
      JOptionPane.showMessageDialog(frame, msg);
    }
  }

  /**
   * Undocumented
   *
   * @param str Empty
   */
  public static void write(String str) {
    if (fileWriter == null) {
      showError("Must open file before calling write.");
      System.exit(0);
    }

    fileWriter.print(str);
  }

  /**
   * Undocumented
   *
   * @param number Empty
   */
  public static void write(int number) {
    write("" + number);
  }

  /**
   * Undocumented
   *
   * @param number Empty
   */
  public static void write(double number) {
    write("" + number);
  }

  /**
   * Undocumented
   *
   * @param b Empty
   */
  public static void write(boolean b) {
    write("" + b);
  }

  /**
   * Undocumented
   *
   * @param ch Empty
   */
  public static void write(char ch) {
    write("" + ch);
  }

  /**
   * Undocumented
   *
   * @param str Empty
   */
  public static void writeLine(String str) {
    if (fileWriter == null) {
      showError("Must open file before calling writeLine.");
      System.exit(0);
    }

    fileWriter.println(str);
  }

  /**
   * Undocumented
   *
   * @param number Empty
   */
  public static void writeLine(int number) {
    writeLine("" + number);
  }

  /**
   * Undocumented
   *
   * @param number Empty
   */
  public static void writeLine(double number) {
    writeLine("" + number);
  }

  /**
   * Undocumented
   *
   * @param b Empty
   */
  public static void writeLine(boolean b) {
    writeLine("" + b);
  }

  /**
   * Undocumented
   *
   * @param ch Empty
   */
  public static void writeLine(char ch) {
    writeLine("" + ch);
  }

  /** Gets any properties that might affect how the simulation/wrapper is run.
   * <br />
   * <p>Normal use of the jvm would require adding the parameter to the
   * java loader, &quot;java -Deecs.testing=true &lt;classname&gt;</p>.
   * 
   * <p>Additional properties could be added here (to allow creating of testing files,
   * or other features).</p>
   *
   */
  protected static void getProperties() {
    if (runningTests) return;
    // Property needs to be set prior to running the simulation/wrapper.
    
    if (System.getProperty("eecs.testing","false").equals("true")) {
      runningTests = true;
    }
    
  }

  /**
   * Undocumented
   *
   * @param digits Empty
   * @param num Empty
   *
   * @return Empty
   */
  private static String showDouble(int digits, double num) {
    java.text.NumberFormat f = java.text.NumberFormat.getInstance();
    f.setMinimumFractionDigits(digits);
    f.setMaximumFractionDigits(digits);
    f.setGroupingUsed(false);

    return f.format(num);
  }

  /**
   * Undocumented
   *
   * @param n Empty
   *
   * @return Empty
   */
  private static String spaces(int n) {
    if (n <= 0) {
      return "";
    }

    char[] cs = new char[n];

    for (int i = 0; i < n; i++) {
      cs[i] = ' ';
    }

    return new String(cs);
  }
}


// end class GuiBase
