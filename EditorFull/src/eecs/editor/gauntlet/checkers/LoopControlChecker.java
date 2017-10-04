package eecs.editor.gauntlet.checkers;

import eecs.editor.gauntlet.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks for a semicolon after the boolean condition in an if, else if, while, and for statement, or, after the declaration of the main
 * method.
 */
public class LoopControlChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] lines = null;
    try {
      lines = file.getLines();
    }
    catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " + ioe.getMessage());
    }
    StringBuffer buffer = new StringBuffer();
    StringBuffer code = new StringBuffer();
    for (int i = 0; i < lines.length; i++) {
      code.append(lines[i]);
    }
    // These don't work quite right yet...
    Pattern whilePat = Pattern.compile("while\\s*\\(.*?\\).*?\\{.*?(\\{.*\\}){0,}.*\\}");
    Pattern counterPat = Pattern
        .compile("while\\s*\\(.*?(\\b[a-z][a-zA-Z0-9]*?)\\b.*?\\).*?\\{.*?\\1(=[^=]|\\+\\+|--|\\+=|-=|\\*=|\\/=).*?\\}|while\\s*\\([^)]*?(\\b[a-z][a-zA-Z0-9]*?)\\b\\(.*?\\).*?\\)");
    Matcher whileMatch = whilePat.matcher(code.toString());
    if (whileMatch.find()) {
      Matcher counterMatch = counterPat.matcher(whileMatch.group());
      if (!counterMatch.find()) {
        buffer.append("It appears that you never change the control variable in the following loop:\n");
        buffer.append("<br><code>" + whileMatch.group() + "</code><br>");
      }
    }
    if (buffer.length() > 0) {
      return new CheckerError(buffer.toString());
    }
    return null;
  }
}