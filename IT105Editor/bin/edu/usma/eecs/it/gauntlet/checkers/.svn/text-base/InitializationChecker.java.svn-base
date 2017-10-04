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

import edu.usma.eecs.it.gauntlet.Checker;
import edu.usma.eecs.it.gauntlet.CheckerError;
import edu.usma.eecs.it.gauntlet.CheckerException;
import edu.usma.eecs.it.gauntlet.CheckerFile;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InitializationChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] output = null;

    try {
      output = file.getLines();
    } catch (IOException ioe) {
      throw new CheckerException("Unable process " + file.getName() + ": " +
        ioe.getMessage());
    }

    for (int i = 0; i < output.length; i++) {
      if (output[i].indexOf("may not have been initialized") > -1) {
        return new CheckerError("You may have initialized " +
          "the variable named " + getVariableName(output[i]) +
          " before you attempted to use it on line " +
          getLineNumber(output[i]) +
          ". You can declare a variable without giving it a value, as in:<br/>" +
          "<code>int age;</code><br/>" +
          "But this is not recommended. If you forget to give it a value " +
          "later, or if you try to give it a value inside of an if-statement, " +
          "you will get this error. We recommend that you always give the " +
          "variable a value when you declare it, as in:<br/>" +
          "<code>int age = 18;</code><br/>");
      }
    }

    return null;
  }

  private String getVariableName(String line) {
    // The compiler message should look something like:
    // E:\JBPROJ~1\gauntlet\printLine.java:41: variable k 
    // might not have been initialized. The first part of 
    // the message varies depending upon whether or not the
    // path was included with the file.  The part starting 
    // with the filename, however is the same.  So, the 
    //sixth word from the end is the variable name.
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
