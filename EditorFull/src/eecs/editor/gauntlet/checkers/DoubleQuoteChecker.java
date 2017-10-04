package eecs.editor.gauntlet.checkers;

import eecs.editor.gauntlet.*;
import java.io.*;

/**
 * Checks for unclosed string literals.
 */
public class DoubleQuoteChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] output = null;
    try {
      output = file.getLines();
    }
    catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " + ioe.getMessage());
    }
    for (int i = 0; i < output.length; i++) {
      if (output[i].indexOf("unclosed string literal") > -1) {
        return new CheckerError("You forgot to close your literal. This means that you are "
            + "missing one or more quotation marks. Like parentheses, quotation "
            + "marks also come in pairs. Check all of your quotation marks "
            + "and make sure they have a buddy.  The compiler is pretty good at "
            + "finding these. You can compile the file and then look for " + "an error message that says something like "
            + "'C:\\crazystuff\\" + file.getName() + ":25: unclosed string literal'. " + "This is the compiler's way of telling you that "
            + "you forgot a quotation mark somewhere around line 25. (BTW - I'm "
            + "using line 25 only as an example. You'll have to read your own " + "error messages to find the line number.)");
      }
    }
    return null;
  }
}