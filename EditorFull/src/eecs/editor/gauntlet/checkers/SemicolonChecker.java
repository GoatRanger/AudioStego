package eecs.editor.gauntlet.checkers;

import eecs.editor.gauntlet.Checker;
import eecs.editor.gauntlet.CheckerError;
import eecs.editor.gauntlet.CheckerException;
import eecs.editor.gauntlet.CheckerFile;
import java.io.IOException;

/**
 * Checks for missing semicolons.
 */
public class SemicolonChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] output = null;
    try {
      output = file.getLines();
    }
    catch (IOException ioe) {
      throw new CheckerException("Unable process " + file.getName() + ": " + ioe.getMessage());
    }
    for (int i = 0; i < output.length; i++) {
      if (output[i].indexOf("';' expected") > -1) {
        return new CheckerError("The bad news - you forgot a semicolon. " + "The good news - the compiler message tells you where "
            + "you forgot it. If you have a semicolon on that line, check "
            + "for extra spaces--remember that in Java, variable and method names can't contain spaces.");
      }
    }
    return null;
  }
}