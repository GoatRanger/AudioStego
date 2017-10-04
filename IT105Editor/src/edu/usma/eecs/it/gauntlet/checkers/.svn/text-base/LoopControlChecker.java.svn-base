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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Checks for a semicolon after the boolean condition in an if, else if, while,
 * and for statement, or, after the declaration of the main method.
 */
public class LoopControlChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] lines = null;

    try {
      lines = file.getLines();
    } catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " +
        ioe.getMessage());
    }
    StringBuffer buffer = new StringBuffer();
    StringBuffer code = new StringBuffer();
    for (int i=0; i<lines.length;i++) {
      code.append(lines[i]);
    }
    
    // These don't work quite right yet...
    Pattern whilePat = Pattern.compile("while\\s*\\(.*?\\).*?\\{.*?(\\{.*\\}){0,}.*\\}");
    Pattern counterPat = Pattern.compile("while\\s*\\(.*?(\\b[a-z][a-zA-Z0-9]*?)\\b.*?\\).*?\\{.*?\\1(=[^=]|\\+\\+|--|\\+=|-=|\\*=|\\/=).*?\\}|while\\s*\\([^)]*?(\\b[a-z][a-zA-Z0-9]*?)\\b\\(.*?\\).*?\\)");
    Matcher whileMatch = whilePat.matcher(code.toString());
    if (whileMatch.find()) {
      Matcher counterMatch = counterPat.matcher(whileMatch.group());
      if (!counterMatch.find()) {
        buffer.append("It appears that you never change the control variable in the following loop:\n");
        buffer.append("<br><code>"+whileMatch.group()+"</code><br>");
      }
    }
    if (buffer.length() > 0) {
      return new CheckerError(buffer.toString());
    }

    return null;
  }
}
