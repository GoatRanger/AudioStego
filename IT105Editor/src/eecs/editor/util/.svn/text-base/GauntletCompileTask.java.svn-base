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

package eecs.editor.util;

import edu.usma.eecs.it.gauntlet.*;
import edu.usma.eecs.it.gauntlet.checkers.*;
import eecs.util.*;

import java.io.*;

import java.util.*;


/** Uses a SwingWorker to perform the compilation task. */
public class GauntletCompileTask implements SwingTask {
  private int lengthOfTask;
  private int current = 0;
  private String statMessage;
  private String[] compilerResults;
  private Collection checkers;
  private File file;
  private List errors;

  public GauntletCompileTask() {
    checkers = new ArrayList();

    checkers.add(new FileNameChecker());
    checkers.add(new CurlyBraceChecker());
    checkers.add(new ParenthesesChecker());
    checkers.add(new PrintlineChecker());
    checkers.add(new DoubleQuoteChecker());
    checkers.add(new SemicolonChecker());
    checkers.add(new EvilSemicolonChecker());
    //checkers.add(new LoopControlChecker());
    checkers.add(new DeclarationChecker());
    checkers.add(new InitializationChecker());
    lengthOfTask = checkers.size() + 2;
  }

/**
 * Called from ProgressBarDemo to start the task.
 */
  public void go() {
    current = 0;
    final SwingWorker worker = new SwingWorker() {
      public Object construct() {
        return new ActualTask(file);
      }
    };
    worker.start();
  }

  public void setFile(File file) {
    this.file = file;
  }

  /**
   * Called from ProgressBarDemo to find out how much work needs
   * to be done.
   */
  public int getLengthOfTask() {
    return lengthOfTask;
  }

  /**
   * Called from ProgressBarDemo to find out how much has been done.
   */
  public int getCurrent() {
    return current;
  }

  public void stop() {
    current = lengthOfTask;
  }

  /**
   * Called from ProgressBarDemo to find out if the task has completed.
   */
  public boolean done() {
    return (current >= lengthOfTask);
  }

  public String getMessage() {
    return statMessage;
  }

  public List getErrors() {
    return errors;
  }

  public String[] getCompilerMessages() {
    return compilerResults;
  }

  /**
   * The actual long running task.  This runs in a SwingWorker thread.
   */
  class ActualTask {
    ActualTask(File file) {
      errors = new ArrayList();
      compilerResults = null;

      if (file == null) {
        return;
      }
      
	  try {
		Thread.sleep(30);
	  } catch (InterruptedException e) {
	  }
      CheckerFile checkFile = new CheckerFile(file);
      String[] msg = null;

      try {
        msg = checkFile.getCompilerMessages();
      } catch (IOException e) {
        msg = new String[] { "Error during compile.", e.getMessage() };

        //msg = null;
      }

      current++;

      try {
        Thread.sleep(30);
      } catch (InterruptedException e) {
      }

      if (msg != null) {
        compilerResults = new String[msg.length];
        System.arraycopy(msg, 0, compilerResults, 0, msg.length);
      }

      Iterator i = checkers.iterator();

      while (i.hasNext()) {
        try {
	        Checker checker = (Checker) i.next();
	
	        try {
	          CheckerError ce = checker.execute(checkFile);
	
	          if (ce != null) {
	            errors.add(ce);
	          }
	        } catch (CheckerException exc) {
	          errors.add(new CheckerError("Unable to compile, " +
	              "cannot find compiler. \nProblem details: " +
	              exc.getMessage()));
	
	          //exc.printStackTrace();
	        }

          Thread.sleep(30);
          current++;
          
          statMessage = "Completed " + current + " out of " + lengthOfTask +
            ".";
        } catch (InterruptedException e) {
        }
      }
      current = lengthOfTask;
    }
  }
}
