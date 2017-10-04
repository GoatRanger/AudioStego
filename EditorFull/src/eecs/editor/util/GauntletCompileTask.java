package eecs.editor.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import eecs.editor.gauntlet.Checker;
import eecs.editor.gauntlet.CheckerError;
import eecs.editor.gauntlet.CheckerException;
import eecs.editor.gauntlet.CheckerFile;
import eecs.editor.gauntlet.checkers.CurlyBraceChecker;
import eecs.editor.gauntlet.checkers.DeclarationChecker;
import eecs.editor.gauntlet.checkers.DoubleQuoteChecker;
import eecs.editor.gauntlet.checkers.EvilSemicolonChecker;
import eecs.editor.gauntlet.checkers.FileNameChecker;
import eecs.editor.gauntlet.checkers.InitializationChecker;
import eecs.editor.gauntlet.checkers.ParenthesesChecker;
import eecs.editor.gauntlet.checkers.PrintlineChecker;
import eecs.editor.gauntlet.checkers.SemicolonChecker;
import eecs.util.SwingTask;
import eecs.util.SwingWorker;

/** Uses a SwingWorker to perform the compilation task. */
public class GauntletCompileTask implements SwingTask {
  int lengthOfTask;
  int current = 0;
  String statMessage;
  String[] compilerResults;
  Collection checkers;
  File file;
  List errors;

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
   * Called from ProgressBarDemo to find out how much work needs to be done.
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
   * The actual long running task. This runs in a SwingWorker thread.
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
      }
      catch (InterruptedException e) {
        //
      }
      CheckerFile checkFile = new CheckerFile(file);
      String[] msg = null;
      try {
        msg = checkFile.getCompilerMessages();
      }
      catch (IOException e) {
        msg = new String[] { "Error during compile.", e.getMessage() };
        //msg = null;
      }
      current++;
      try {
        Thread.sleep(30);
      }
      catch (InterruptedException e) {
        //
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
          }
          catch (CheckerException exc) {
            errors.add(new CheckerError("Unable to compile, " + "cannot find compiler. \nProblem details: " + exc.getMessage()));
          }
          Thread.sleep(30);
          current++;
          statMessage = "Completed " + current + " out of " + lengthOfTask + ".";
        }
        catch (InterruptedException e) {
          //
        }
      }
      current = lengthOfTask;
    }
  }
}