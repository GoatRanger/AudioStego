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


/**
 * Checks to see if the method printLine is misspelled with a lower-case L, as
 * in 'printline'.
 */
public class PrintlineChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] lines = null;

    try {
      lines = file.getLines();
    } catch (IOException ioe) {
      throw new CheckerException("Unable process " + file.getName() + ": " +
        ioe.getMessage());
    }

    List lineNumbers = new ArrayList();

    for (int i = 0; i < lines.length; i++) {
      if (lines[i].indexOf("printline") > -1) {
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

      return new CheckerError("You have misspelled printLine on " +
        buffer.toString() + ". The method is spelled " +
        "with a capital L, like the one on your forehead.");
    }

    return null;
  }
}
