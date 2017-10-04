package eecs.editor.gauntlet.checkers;

import eecs.editor.gauntlet.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks for a semicolon after the boolean condition in an if, else if, while, and for statement, or, after the declaration of the main
 * method.
 */
public class EvilSemicolonChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] lines = null;
    try {
      lines = file.getLines();
    }
    catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " + ioe.getMessage());
    }
    String line = "";
    ArrayList lineNumbers = new ArrayList();
    Pattern ifPat = Pattern.compile("if\\s*\\(.*?\\)[^{]*;");
    Pattern whilePat = Pattern.compile("while\\s*\\(.*?\\)[^{]*;");
    Pattern methodPat = Pattern.compile("static\\s*(int|double|void|String|char)[^=]*\\(.*?\\)[^{]*;");
    for (int i = 0; i < lines.length; i++) {
      line = lines[i];
      Matcher matcher = ifPat.matcher(line);
      if (matcher.find()) {
        lineNumbers.add(new Integer(i + 1));
      }
      matcher = whilePat.matcher(line);
      if (matcher.find()) {
        lineNumbers.add(new Integer(i + 1));
      }
      matcher = methodPat.matcher(line);
      if (matcher.find()) {
        lineNumbers.add(new Integer(i + 1));
      }
    }
    int size = lineNumbers.size();
    if (size > 0) {
      StringBuffer buffer = new StringBuffer();
      if (size == 1) {
        buffer.append("line ");
      }
      else {
        buffer.append("lines ");
      }
      int count = 0;
      for (Iterator i = lineNumbers.iterator(); i.hasNext();) {
        Integer lineNumber = (Integer) i.next();
        buffer.append(lineNumber.toString());
        if (count < (size - 2)) {
          buffer.append(", ");
        }
        else
          if (count == (size - 2)) {
            buffer.append(" and ");
          }
        count++;
      }
      return new CheckerError("You may have placed one or more semicolons where they don't belong on " + buffer.toString()
          + ". You may have written something like '<code>if (age > 18);</code>' or "
          + "'<code>while (laps < 10);</code>' or '<code>public static void main(String[] args);</code>'.  "
          + "Placing the semicolon here will cause a logic error when the program runs. "
          + "If this isn't the problem, you may not have followed recommend code formatting "
          + "conventions in IT105. The following if-statement is legal, but not recommended: "
          + "'<code>if (laps < 10) printLine(\"Run another lap!\");</code>'<br/>"
          + "It is recommended that you format if-statements, for-loops, and while-loops "
          + "as follows:<br/><br/><code>if (laps < 10)<br/>" + "{<br/>" + "&nbsp;&nbsp;&nbsp;&nbsp;printLine(\"Run another lap!\"); <br/>"
          + "}</code>");
    }
    return null;
  }
}