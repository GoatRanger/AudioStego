package eecs;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;
import javax.swing.*;

// A set of commands used to wrap normal Java GUI commands into an easy to use implementation that doesn't require knowledge of object
// oriented programming.
public class GuiBase extends JApplet {
  // protected gui object (visible only within the package)
  // The output box used for text output in the GUI.
  static JTextArea output;
  protected static Logger logger = Logger.getLogger("eecs.robot.wrapper");

  // The result type for the first button from an input dialog that uses buttons.
  public static final int BUTTON1 = 0;

  // The result type for the second button from an input dialog that uses buttons.

  public static final int BUTTON2 = 1;

  // The result type for the third button from an input dialog that uses 3 or more buttons.
  public static final int BUTTON3 = 2;

  // The result type for the cancel button from an input dialog that has a Ok and Cancel button.
  public static final int CANCEL = -1;
  protected static JFrame frame; // main container of the EECS wrapper for all other windows
  protected static BufferedReader fileReader = null;
  protected static PrintWriter fileWriter = null;
  private static String lookAhead = null;
  // Testing framework
  // The Properties object used to communicate information from outside applications, currently used to allow the IT105 Editor to disable
  // the GUI to run automated testing on code.
  protected static Properties p = null;
  protected static boolean runningTests = false;
  // The reader to get input from the Standard Input (System.in)
  protected static BufferedReader testConsoleReader = new BufferedReader(new InputStreamReader(System.in));

  public static double getDouble(String prompt) {
    getProperties();
    double value = 0.0;
    if (runningTests) {
      while (true) {
        try {
          String str = testConsoleReader.readLine();
          value = Double.parseDouble(str);
          break;
        }
        catch (IOException e) {
          System.err.println("Error reading a double from test file (console)." + "  Format in input file was incorrect.");
        }
        catch (NumberFormatException ne) {
          // Do nothing, just iterate!
        }
      }
    }
    else {
      while (true) {
        String str = getString(prompt);
        try {
          value = Double.parseDouble(str);
          break;
        }
        catch (NumberFormatException e) {
          showError("Not a valid double.  Please try again.");
        }
      }
    }
    return value;
  }

  public static int getInt(String prompt) {
    getProperties();
    int value = 0;
    if (runningTests) {
      while (true) {
        try {
          String str = testConsoleReader.readLine();
          value = Integer.parseInt(str);
          break;
        }
        catch (IOException e) {
          System.err.println("Error reading an integer from test file (console)." + "  Format in input file was incorrect.");
        }
        catch (NumberFormatException e) {
          // Do nothing
        }
      }
    }
    else {
      while (true) {
        String str = getString(prompt);
        try {
          value = Integer.parseInt(str);
          break;
        }
        catch (NumberFormatException e) {
          showError("Not a valid integer.  Please try again.");
        }
      }
    }
    return value;
  }

  public static String getString(String prompt) {
    getProperties();
    String result = "";
    if (runningTests) {
      try {
        result = testConsoleReader.readLine();
      }
      catch (IOException e) {
        System.err.println("Error reading a String from test file (console)." + "  Format in input file was incorrect.");
      }
    }
    else {
      result = JOptionPane.showInputDialog(frame, prompt, "", JOptionPane.QUESTION_MESSAGE);
      if (result == null) {
        showError("Dialog Cancelled, exiting program.");
        System.exit(0);
      }
    }
    return result;
  }

  // Gets a single character. Allows entry of multiple characters, but will only accept when the entry is a single character.
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
        }
        catch (IOException e) {
          System.err.println("Error reading a char from test file (console)." + "  Format in input file was incorrect.");
        }
      }
    }
    else {
      while (true) {
        //            String ok = "OK";
        //            JTextField field = new JTextField();
        result = JOptionPane.showInputDialog(frame, prompt, "", JOptionPane.QUESTION_MESSAGE);
        if (result == null) {
          showError("Dialog Cancelled, exiting program.");
          System.exit(0);
        }
        if (result.length() == 1) {
          charResult = result.charAt(0);
          break;
        }
        showError("Not a valid character.  Must be exactly one character entered.  Please try again.");
      }
    }
    return charResult;
  }

  public static String centerAlign(int width, int digits, double num) {
    return centerAlign(width, showDouble(digits, num));
  }

  public static String centerAlign(int width, int num) {
    return centerAlign(width, "" + num);
  }

  public static String centerAlign(int width, String str) {
    if (str == null) {
      return spaces(width);
    }
    int sp = width - str.length();
    return spaces((sp + 1) / 2) + str + spaces(sp / 2);
  }

  public static int chooseButton(String prompt, String button1, String button2) {
    return chooseButton(prompt, new String[] { button1, button2 });
  }

  public static int chooseButton(String prompt, String button1, String button2, String button3) {
    return chooseButton(prompt, new String[] { button1, button2, button3 });
  }

  public static int chooseButton(String prompt, String[] buttons) {
    getProperties();
    int value = 0;
    if (runningTests) {
      try {
        String str = testConsoleReader.readLine();
        value = Integer.parseInt(str);
      }
      catch (IOException e) {
        System.err.println("Error reading an input for chooseButton from test file." + "  Format in input file was incorrect.");
      }
    }
    else {
      if (buttons == null) {
        showMessage("You need to make sure you have options when using chooseButton.");
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

  public static int chooseFromList(String prompt, String[] items) {
    getProperties();
    int value = 0;
    if (runningTests) {
      try {
        String str = testConsoleReader.readLine();
        value = Integer.parseInt(str);
      }
      catch (IOException e) {
        System.err.println("Error reading an input for chooseList from test file." + "  Format in input file was incorrect.");
      }
    }
    else {
      // assumes: items != null && items.length > 0
      String ok = "OK";
      JList list = new JList(items);
      list.setSelectedIndex(0); // set default choice
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane pane = new JScrollPane(list);
      int result = JOptionPane.showOptionDialog(frame, new Object[] { prompt, pane // message
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

  public static void clearOutput() {
    output.setText("");
  }

  public static void closeReadFile() {
    if (fileReader != null) {
      try {
        fileReader.close();
      }
      catch (Exception e) {
        // ignore the exception
      }
      fileReader = null;
      lookAhead = null;
    }
  }

  public static void closeWriteFile() {
    if (fileWriter != null) {
      try {
        fileWriter.close();
      }
      catch (Exception e) {
        // ignore the exception
      }
      fileWriter = null;
    }
  }

  public void init() {
    setContentPane(frame);
  }

  public static String leftAlign(int width, int digits, double num) {
    return leftAlign(width, showDouble(digits, num));
  }

  public static String leftAlign(int width, int num) {
    return leftAlign(width, "" + num);
  }

  public static String leftAlign(int width, String str) {
    if (str == null) {
      return spaces(width);
    }
    return str + spaces(width - str.length());
  }

  public static double log10(double n) {
    return Math.log(n) / Math.log(10);
  }

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
    }
    catch (Exception e) {
      // assume error means you cannot read any more
      // but otherwise do nothing about the error
      return false;
    }
  }

  public static void openReadFile(String filename) {
    if (fileReader != null) {
      showError("Old read file still open.  Can't open new one.");
      System.exit(0);
    }
    try {
      fileReader = new BufferedReader(new FileReader(filename));
    }
    catch (Exception e) {
      showError("Could not open file " + filename);
      System.exit(0);
    }
    lookAhead = null; // should already be null, but just to be safe
  }

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
        }
        catch (Exception e) {
          showError("Error opening file.  Please try again.");
        }
      }
      else {
        showError("Not a valid file.  Please try again.");
      }
    }
  }

  public static void openWriteFile(String filename) {
    if (fileWriter != null) {
      showError("Old write file still open.  Can't open new one.");
      System.exit(0);
    }
    try {
      fileWriter = new PrintWriter(new FileWriter(filename));
    }
    catch (Exception e) {
      showError("Could not open file " + filename);
      System.exit(0);
    }
  }

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
        }
        catch (Exception e) {
          showError("Error opening file.  Please try again.");
        }
      }
      else {
        showError("Not a valid file.  Please try again.");
      }
    }
  }

  public static void print(String msg) {
    getProperties();
    if (runningTests) {
      System.out.print(msg);
    }
    else {
      output.append(msg);
    }
    // Including the following two lines would make it scroll
    // horizontally if the line extended past the edge of the screen.
    // This seems to be a little disconcerting, so I've left it off...
    // int pos = output.getText().length();
    // output.setCaretPosition(pos);
  }

  public static void print(Object obj) {
    print(obj.toString());
  }

  public static void print(int number) {
    print("" + number);
  }

  public static void print(double number) {
    print("" + number);
  }

  public static void print(boolean b) {
    print("" + b);
  }

  public static void print(char ch) {
    print("" + ch);
  }

  public static void printLine(String msg) {
    getProperties();
    if (runningTests) {
      System.out.println(msg);
    }
    else {
      output.append(msg);
      output.append("\n");
      // make it scroll, if necessary
      int pos = output.getText().length();
      output.setCaretPosition(pos);
    }
  }

  public static void printLine(Object obj) {
    printLine(obj.toString());
  }

  public static void printLine(int number) {
    printLine("" + number);
  }

  public static void printLine(double number) {
    printLine("" + number);
  }

  public static void printLine(boolean b) {
    printLine("" + b);
  }

  public static void printLine(char ch) {
    printLine("" + ch);
  }

  public static void printLine() {
    printLine("");
  }

  // Reads a line of text from the currently open text file.
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

  public static String rightAlign(int width, int digits, double num) {
    return rightAlign(width, showDouble(digits, num));
  }

  public static String rightAlign(int width, int num) {
    return rightAlign(width, "" + num);
  }

  public static String rightAlign(int width, String str) {
    if (str == null) {
      return spaces(width);
    }
    return spaces(width - str.length()) + str;
  }

  public static void showError(String msg) {
    getProperties();
    if (runningTests) {
      System.out.println(msg);
    }
    else {
      JOptionPane.showMessageDialog(frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void showMessage(String msg) {
    getProperties();
    if (runningTests) {
      System.out.println(msg);
    }
    else {
      JOptionPane.showMessageDialog(frame, msg);
    }
  }

  public static void write(String str) {
    if (fileWriter == null) {
      showError("Must open file before calling write.");
      System.exit(0);
    }
    fileWriter.print(str);
  }

  public static void write(int number) {
    write("" + number);
  }

  public static void write(double number) {
    write("" + number);
  }

  public static void write(boolean b) {
    write("" + b);
  }

  public static void write(char ch) {
    write("" + ch);
  }

  public static void writeLine(String str) {
    if (fileWriter == null) {
      showError("Must open file before calling writeLine.");
      System.exit(0);
    }
    fileWriter.println(str);
  }

  public static void writeLine(int number) {
    writeLine("" + number);
  }

  public static void writeLine(double number) {
    writeLine("" + number);
  }

  public static void writeLine(boolean b) {
    writeLine("" + b);
  }

  public static void writeLine(char ch) {
    writeLine("" + ch);
  }

  // Gets any properties that might affect how the simulation/wrapper is run.
  // Normal use of the jvm would require adding the parameter to the java loader, 'java -Deecs.testing=true <classname>
  // Additional properties could be added here (to allow creating of testing files, or other features).
  protected static void getProperties() {
    if (runningTests)
      return;
    // Property needs to be set prior to running the simulation/wrapper.
    try {
      if (System.getProperty("eecs.testing", "false").equals("true")) {
        runningTests = true;
      }
    }
    catch (SecurityException se) {
      // If security doesn't allow property access (JNLP launched files have this restriction)
      runningTests = false;
    }
  }

  private static String showDouble(int digits, double num) {
    java.text.NumberFormat f = java.text.NumberFormat.getInstance();
    f.setMinimumFractionDigits(digits);
    f.setMaximumFractionDigits(digits);
    f.setGroupingUsed(false);
    return f.format(num);
  }

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