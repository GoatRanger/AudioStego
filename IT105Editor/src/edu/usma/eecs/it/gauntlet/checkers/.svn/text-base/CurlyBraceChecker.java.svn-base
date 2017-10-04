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


/**
 * Checks for mismatched curly braces.
 */
public class CurlyBraceChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    int rightHandBraces = 0;
    int leftHandBraces = 0;
    int leftHandBracket = 0;
    int rightHandBracket = 0;
    int leftHandParens = 0;
    int rightHandParens = 0;
    String[] lines = null;

    try {
      lines = file.getLines();
    } catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " +
        ioe.getMessage());
    }

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];

      for (int j = 0; j < line.length(); j++) {
        char currentChar = line.charAt(j);

        if (currentChar == '{') {
          leftHandBraces++;
        } else if (currentChar == '}') {
          rightHandBraces++;
        } else if (currentChar == '[') {
          leftHandBracket++;
        } else if (currentChar == ']') {
          rightHandBracket++;
        } else if (currentChar == '(') {
          leftHandParens++;
        } else if (currentChar == ')') {
          rightHandParens++;
        }
      }
    }

    if (leftHandBraces != rightHandBraces) {
      if (leftHandParens != rightHandParens) {
        return new CheckerError("The number of opening curly braces \"{\" and " +
          "closing curly braces \"}\" are not the same.  Every opening brace " +
          "should have a corresponding closing brace.  Your parenthesis don't match " +
          "either, so after checking all your braces for pairs, make sure you haven't " +
          "accidentally switched a parenthesis symbol for a brace.");
      } else if (leftHandBracket != rightHandBracket) {
        return new CheckerError("The number of opening curly braces \"{\" and " +
          "closing curly braces \"}\" are not the same.  Every opening brace " +
          "should have a corresponding closing brace.  You also have brackets '[]' " +
          "that aren't in matching pairs, so after checking all your braces " +
          "for pairs, make sure you haven't accidentally switched a bracket " +
          "symbol for a brace.");
      } else {
        return new CheckerError("The number of opening curly braces \"{\" and " +
          "closing curly braces \"}\" are not the same.  Every opening brace " +
          "should have a corresponding closing brace.  To fix this problem, " +
          "match your braces up. The class, the main method, and any other " +
          "method should have an opening and a closing curly brace.  Blocks of " +
          "code that are within an if statement, else statement, while loop, or " +
          "for loop should start with an opening curly brace and end with " +
          "a closing curly brace.");
      }
    } else {
      return null;
    }
  }
}
