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
 * Checks for unclosed string literals.
 */
public class DoubleQuoteChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] output = null;

    try {
      output = file.getLines();
    } catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " +
        ioe.getMessage());
    }

    for (int i = 0; i < output.length; i++) {
      if (output[i].indexOf("unclosed string literal") > -1) {
        return new CheckerError("Do you feel a draft in here? " +
          "You forgot to close your literal. This means that you are " +
          "missing one or more quotation marks. Like parentheses, quotation " +
          "marks also come in pairs. Check all of your quotation marks " +
          "and make sure they have a buddy.  The compiler is pretty good at " +
          "finding these. You can compile the file and then look for " +
          "an error message that says something like " + "'C:\\crazystuff\\" +
          file.getName() + ":25: unclosed string literal'. " +
          "This is the compiler's highly scientific way of telling you that " +
          "you forgot a quotation mark somewhere around line 25. (BTW - I'm " +
          "using line 25 only as an example. You'll have to read your own " +
          "error messages to find the line number.)");
      }
    }

    return null;
  }
}
