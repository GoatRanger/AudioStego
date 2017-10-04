package eecs.editor.gauntlet.checkers;

import eecs.editor.gauntlet.Checker;
import eecs.editor.gauntlet.CheckerError;
import eecs.editor.gauntlet.CheckerException;
import eecs.editor.gauntlet.CheckerFile;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitializationChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] output = null;
    try {
      output = file.getLines();
    }
    catch (IOException ioe) {
      throw new CheckerException("Unable process " + file.getName() + ": " + ioe.getMessage());
    }
    for (int i = 0; i < output.length; i++) {
      if (output[i].indexOf("may not have been initialized") > -1) {
        return new CheckerError("You may have initialized " + "the variable named " + getVariableName(output[i])
            + " before you attempted to use it on line " + getLineNumber(output[i]) + ". We recommend that you always give a "
            + "variable an initial value when you declare it, as in:<br/>" + "<code>static int age = 18;</code><br/>");
      }
    }
    return null;
  }

  private String getVariableName(String line) {
    String name = null;
    Pattern pattern = Pattern.compile("\\bvariable\\s*\\b");
    Matcher matcher = pattern.matcher(line);
    if (matcher.find()) {
      name = line.substring(matcher.end(), line.indexOf(" ", matcher.end()));
    }
    return name;
  }

  private int getLineNumber(final String line) {
    Pattern pattern = Pattern.compile(".*:[0-9]+:");
    Matcher matcher = pattern.matcher(line);
    int lineNo = 0;
    if (matcher.find()) {
      String number = line.substring(line.lastIndexOf(":"), line.length());
      lineNo = Integer.parseInt(number);
    }
    return lineNo;
  }
}