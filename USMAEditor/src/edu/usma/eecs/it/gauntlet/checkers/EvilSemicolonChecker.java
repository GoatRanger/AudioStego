/* Copyright (C) 2003  USMA
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

package edu.usma.eecs.it.gauntlet.checkers;

import edu.usma.eecs.it.gauntlet.*;

import java.io.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Checks for a semicolon after the boolean condition in an if, else if, while,
 * and for statement, or, after the declaration of the main method.
 */
public class EvilSemicolonChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] lines = null;

    try {
      lines = file.getLines();
    } catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " +
        ioe.getMessage());
    }

    String line = "";
    ArrayList lineNumbers = new ArrayList();

    Pattern ifPat = Pattern.compile("if\\s*\\(.*?\\)[^{]*;");
    Pattern whilePat = Pattern.compile("while\\s*\\(.*?\\)[^{]*;");
    Pattern forPat = Pattern.compile("for\\s*\\(.*?\\)[^{]*;");
    Pattern methodPat = Pattern.compile(
        "static\\s*(int|double|void|String|char)[^=]*\\(.*?\\)[^{]*;");

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

      matcher = forPat.matcher(line);

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
      } else {
        buffer.append("lines ");
      }

      int count = 0;

      for (Iterator i = lineNumbers.iterator(); i.hasNext();) {
        Integer lineNumber = (Integer) i.next();

        buffer.append(lineNumber.toString());

        if (count < (size - 2)) {
          buffer.append(", ");
        } else if (count == (size - 2)) {
          buffer.append(" and ");
        }

        count++;
      }

      return new CheckerError(
        "You may have placed one or more semicolons where they don't belong on " +
        buffer.toString() +
        ". You may have written something like '<code>if (age > 18);</code>' or " +
        "'<code>while (laps < 10);</code>' or '<code>public static void main(String[] args);</code>'.  " +
        "Placing the semicolon here is legal, but it may cause a logic error when the program runs. " +
        "If this isn't the problem, you may not have followed recommend code formatting " +
        "conventions in IT105. The following if-statement is legal, but not recommended: " +
        "'<code>if (laps < 10) printLine(\"Run another lap!\");</code>'<br/>" +
        "It is recommended that you format if-statements, for-loops, and while-loops " +
        "as follows:<br/><br/><code>if (laps < 10)<br/>" + "{<br/>" +
        "&nbsp;&nbsp;&nbsp;&nbsp;printLine(\"Run another lap!\"); <br/>" +
        "}</code>");
    }

    return null;
  }

  /**
* Returns true if the line contains an erroneous semicolon. The line
* should contain 'while', 'if', 'for', or 'args'
* @param line
* @return true - if the semicolon is ill-advised or illegal
*/
  private boolean containsError(String line) {
    // 1. If the line is a for-loop, count the number of semicolons and return
    //    true if the there are more than 2 semicolons
    // 2. Else if the line is the end of a do-while loop, return false
    // 3. Else if the line contains a semicolon, then return true
    // 4. Else return false
    if ((line.indexOf("for(") > 0) || (line.indexOf("for (") > 0)) {
      int numberOfSemis = 0;

      for (int i = 0; i < line.length(); i++) {
        if (line.charAt(i) == ';') {
          numberOfSemis++;
        }
      }

      if (numberOfSemis > 2) {
        return true;
      }
    } else if ((line.indexOf("}while") > 0) || (line.indexOf("} while") > 0)) {
      return false;
    } else if (line.indexOf(';') > 0) {
      return true;
    }

    return false;
  }
}
