package eecs.editor.gauntlet.checkers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import eecs.editor.gauntlet.Checker;
import eecs.editor.gauntlet.CheckerError;
import eecs.editor.gauntlet.CheckerException;
import eecs.editor.gauntlet.CheckerFile;

/**
 * Checks to see if the method printLine is misspelled with a lower-case L, as in 'printline'.
 */
public class PrintlineChecker extends Checker {
  public CheckerError execute(CheckerFile file) throws CheckerException {
    String[] lines = null;
    try {
      lines = file.getLines();
    }
    catch (IOException ioe) {
      throw new CheckerException("Unable process " + file.getName() + ": " + ioe.getMessage());
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
      return new CheckerError("You have misspelled printLine on " + buffer.toString() + ". The method is spelled with a capital L.");
    }
    return null;
  }
}