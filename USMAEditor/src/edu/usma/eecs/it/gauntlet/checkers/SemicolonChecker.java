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


/**
 * Checks for missing semicolons.
 */
public class SemicolonChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] output = null;

    try {
      output = file.getLines();
    } catch (IOException ioe) {
      throw new CheckerException("Unable process " + file.getName() + ": " +
        ioe.getMessage());
    }

    for (int i = 0; i < output.length; i++) {
      if (output[i].indexOf("';' expected") > -1) {
        return new CheckerError("The bad news - you forgot a semicolon. " +
          "The good news - the compiler message tells you where " +
          "you forgot it. If you have a semicolon on that line, check " +
          "for extra spaces--remember that in Java names can't contain spaces.");
      }
    }

    return null;
  }
}
