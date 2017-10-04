package eecs.editor.gauntlet.checkers;

import eecs.editor.gauntlet.*;
import java.io.*;
import java.util.*;

/**
 * Checks to see that the file name matches the class name and that there are no spaces in the filename. Treats the next word after the word
 * "class" to be the class name. Most likely will work with 99% of cadet files.
 */
public class FileNameChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String fileName = file.getName();
    if (fileName.indexOf(".") == -1) {
      return new CheckerError("Your file must have a .java extension." + " Normally this is added by the editor, but you may have opened"
          + " the wrong file, or used an editor that did not automatically"
          + " set the extension.  Resave your file with the extension '.java'."
          + " If you see a compiler message that starts with \"Invalid flag\", " + " it's because your filename is incorrect.");
    }
    fileName = fileName.substring(0, fileName.indexOf("."));
    StringTokenizer tk = new StringTokenizer(fileName);
    if (tk.countTokens() > 1) {
      return new CheckerError("The name of the file must be one word. " + "Your file name is " + tk.countTokens()
          + " words long. We can't have a filename with spaces " + "in it. The class name has to be the "
          + "same as the filename, so you might need to change that, too.");
    }
    String className = getClassName(file);
    if (fileName.equals(className)) {
      return null;
    }
    return new CheckerError("The name of your file (" + fileName + ") does not match the " + "name of your class (" + className
        + ").  To fix this error you " + "can either rename the file or rename the class so that the " + "names are the same.");
  }

  /**
   * Returns the simple name of the class. Simply expects the next word after the first instance of the word "class" to be the class name.
   * Will most likely work for 99.9% of IT 105 student files. Using the word 'class' elsewhere in the file may cause a problem.
   * 
   * @param file
   *          The student's java file
   * @return The simple class name of the file
   * @throws CheckerException
   *           If an I/O error occurs.
   */
  private String getClassName(CheckerFile file) throws CheckerException {
    String className = null;
    String[] lines = null;
    try {
      lines = file.getLines();
    }
    catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " + ioe.getMessage());
    }
    String line = "";
    int idx = -1;
    StringTokenizer tk;
    String remainder;
    for (int i = 0; i < lines.length; i++) {
      line = lines[i];
      idx = line.indexOf("class");
      if (idx > -1) {
        remainder = line.substring(idx);
        tk = new StringTokenizer(remainder);
        if (tk.countTokens() > 1) {
          tk.nextToken();
          className = tk.nextToken();
          break;
        }
      }
    }
    if (className != null) {
      return className;
    }
    throw new CheckerException("Unable to find class name.");
  }
}