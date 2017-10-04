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
 * Checks for variables that are used without being declared.
 */
public class DeclarationChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] output = null;

    try {
      output = file.getLines();
    } catch (IOException ioe) {
      throw new CheckerException("Unable to process " + file.getName() + ": " +
        ioe.getMessage());
    }

    for (int i = 0; i < output.length; i++) {
      if (output[i].indexOf("cannot resolve symbol") > -1) {
        int position;

        if ((position = output[i + 1].indexOf("variable")) > -1) {
          String name = output[i + 1].substring(position + 9);

          return new CheckerError("It looks like you tried to use the " +
            "variable named '" + name + "', but you never declared it." +
            "  Remember that when programming, you must always tell " +
            "Java what kind of information (data type) a variable " +
            "holds (such as int, double, or String).");
        }
      }
    }

    return null;
  }
}
