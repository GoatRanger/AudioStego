package eecs.editor.gauntlet.checkers;

import eecs.editor.gauntlet.Checker;
import eecs.editor.gauntlet.CheckerError;
import eecs.editor.gauntlet.CheckerException;
import eecs.editor.gauntlet.CheckerFile;
import java.io.IOException;

/**
 * Checks for variables that are used without being declared.
 */
public class DeclarationChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] output = null;
    try {
      output = file.getLines();
    }
    catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " + ioe.getMessage());
    }
    for (int i = 0; i < output.length; i++) {
      if (output[i].indexOf("cannot resolve symbol") > -1) {
        int position;
        if ((position = output[i + 1].indexOf("variable")) > -1) {
          String name = output[i + 1].substring(position + 9);
          return new CheckerError("It looks like you tried to use the " + "variable named '" + name + "', but you never declared it."
              + "  Remember that when programming, you must always tell " + "Java what kind of information (data type) a variable "
              + "holds (such as int, double, or String).");
        }
      }
    }
    return null;
  }
}