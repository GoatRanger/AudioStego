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
 * Checks for mismatched parentheses.
 */
public class ParenthesesChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    int rightHandParens = 0;
    int leftHandParens = 0;

    String[] lines = null;

    try {
      lines = file.getLines();
    } catch (IOException ioe) {
      throw new CheckerException("Unable process " + file.getName() + ": " +
        ioe.getMessage());
    }

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];

      for (int j = 0; j < line.length(); j++) {
        char currentChar = line.charAt(j);

        if (currentChar == '(') {
          leftHandParens++;
        } else if (currentChar == ')') {
          rightHandParens++;
        }
      }
    }

    if (leftHandParens != rightHandParens) {
      return new CheckerError("The number of opening parentheses and " +
        "closing parentheses are not the same.  Every opening parenthesis " +
        "should have a corresponding closing parenthesis. To fix this problem, " +
        "match your parentheses. This problem is usually caused by " +
        "ATDDS (Attention to Detail Deficiency Syndrome)");
    } else {
      return null;
    }
  }
}
