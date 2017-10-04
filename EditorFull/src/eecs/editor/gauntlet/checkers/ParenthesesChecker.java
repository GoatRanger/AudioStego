package eecs.editor.gauntlet.checkers;

import java.io.IOException;
import eecs.editor.gauntlet.Checker;
import eecs.editor.gauntlet.CheckerError;
import eecs.editor.gauntlet.CheckerException;
import eecs.editor.gauntlet.CheckerFile;

/**
 * Checks for mismatched parentheses.
 */
public class ParenthesesChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    int rightHandParens = 0;
    int leftHandParens = 0;
    String[] lines = null;
    try {
      lines = file.getLines();
    }
    catch (IOException ioe) {
      throw new CheckerException("Unable process " + file.getName() + ": " + ioe.getMessage());
    }
    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
      for (int j = 0; j < line.length(); j++) {
        char currentChar = line.charAt(j);
        if (currentChar == '(') {
          leftHandParens++;
        }
        else
          if (currentChar == ')') {
            rightHandParens++;
          }
      }
    }
    if (leftHandParens != rightHandParens) {
      return new CheckerError("The number of opening parentheses and "
          + "closing parentheses are not the same.  Every opening parenthesis "
          + "should have a corresponding closing parenthesis. To fix this problem, " + "match your parentheses.");
    }
    return null;
  }
}