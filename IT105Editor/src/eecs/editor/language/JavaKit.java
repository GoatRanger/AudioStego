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

/*
 * Created on Aug 24, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.editor.language;

import de.hunsicker.jalopy.Jalopy;
import de.hunsicker.jalopy.swing.SettingsDialog;

import eecs.editor.util.GauntletCompileTask;
import eecs.util.ProcessWrapper;

import java.awt.Toolkit;

import java.io.*;

import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import jedit.JEditTextArea;


/**
 * DOCUMENT ME!
 *
 * @author Karl To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
class JavaKit extends DefaultLanguageKit {
  /** DOCUMENT ME! */
  private volatile GauntletCompileTask compileTask = new GauntletCompileTask();

  /** DOCUMENT ME! */
  private Jalopy jalopy;

  /** DOCUMENT ME! */
  private ProcessWrapper runProcess;

  /** DOCUMENT ME! */
  private String compilerMessages = null;

  /** DOCUMENT ME! */
  private StringBuffer javaTemplate = new StringBuffer("//\n" + "// Name: \n" +
      "// Section: \n" + "// Date: \n" + "// Description: \n" + "\n" +
      "public class \\ClassName\\ extends eecs.\\Gui\\RobotGui\\\n" + "{\n" +
      "    public static void main(String[] args)\n" + "    {\n" +
      "         // Code for the main program\n" + "\n" + "    } //end main\n" +
      "} //end <ClassName>\n");

  /** DOCUMENT ME! */
  private StringBuffer runMessages = null;

  /** DOCUMENT ME! */
  private boolean canReformat = true;

  /** DOCUMENT ME! */
  private boolean isRunning = false;

  /** DOCUMENT ME! */
  private int compileStatus;

  /**
   * Creates a new JavaKit object.
   */
  JavaKit() {
    super();

    //	When creating the Jalopy formatter, sometimes throws an exception.
    // Catch it here so it doesn't show on the screen.
    try {
      jalopy = new Jalopy();
    } catch (ClassCastException e) {
      canReformat = true;

      // Do nothing. Not sure why this exception is throw.  Not consistent.
    } catch (RuntimeException e) {
      canReformat = false;

      // Thrown by Jalopy when can't load the property settings
    }

    setTemplate(javaTemplate);
    language = LanguageKit.JAVA;
    description = "Java Program";
    fileSuffix = ".java";
  }

  /** Returns the result code of the last compile.  If the program was fully
   * compiled, but results are not available (due to thread timing), this method
   * will block until the results are available.
   * @see #compile()
   * @see eecs.editor.language.DefaultLanguageKit#getCompileResult()
   */
  public int getCompileResult() {
    if (getCompilerPercentComplete() < 100) {
      return COMPILE_INCOMPLETE;
    }

    // Compiling is finished, 
    while (compileResult == COMPILE_INCOMPLETE) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException ie) {
        // Not sure what we should do with it...maybe nothing?
      }
    }

    return compileResult;
  }

  /* (non-Javadoc)
   * @see eecs.editor.language.DefaultLanguageKit#getCompilerMessages()
   */
  public String getCompilerMessages() {
    if (getCompilerPercentComplete() < 100) {
      return null;
    }

    while (compileResult == COMPILE_INCOMPLETE) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException ie) {
      }
    }

    StringBuffer buffer = new StringBuffer(
        "<html><body><h4>---BEGIN CHECKS FOR COMMON MISTAKES---</h4>");
    boolean gauntletOk = false;
    Toolkit.getDefaultToolkit().beep();

    if (compileTask == null) {
      buffer.append("<em>Couldn't Compile.</em>");
    } else {
    	List errors;
    	synchronized (compileTask) {
    		errors = compileTask.getErrors();
    	}

      if (errors.size() == 0) {
        gauntletOk = true;
      }

      Iterator i = errors.iterator();
      int n = 1;

      while (i.hasNext()) {
        edu.usma.eecs.it.gauntlet.CheckerError ce = (edu.usma.eecs.it.gauntlet.CheckerError) i.next();
        buffer.append("<p><b>Mistake ");
        buffer.append((n++));
        buffer.append(":</b><br>");
        buffer.append(ce.getMessage());
        buffer.append("</p>");
      }

      if (gauntletOk) {
        buffer.append("<p><i>  None of the common mistakes were found! " +
          "Now just check below to make sure the compiler didn't find any 'uncommon' mistakes.</i></p>");
      }

      buffer.append("<b><h4>--- END COMMON MISTAKE CHECKS---</h4></b>");

      String[] msgs;
      synchronized (compileTask) {
      	msgs = compileTask.getCompilerMessages();
      }

      if ((msgs != null) && (msgs.length > 0)) {
        // Errors, so insert a big, red header to let the user know!
        buffer.insert(buffer.indexOf("<body>") + 6,
          "<h1><font color=\"red\">You have errors.  Messages Follow.</font></h1>\r\n");
        buffer.append(
          "<p><font color=\"red\"> --- COMPILING: ERRORS DETECTED ---</font><br><code>");
        for (int msgNum = 0; msgNum < msgs.length; msgNum++) {
          buffer.append(textToHTML(msgs[msgNum]) + "<br>");
        }
        buffer.append("</code></p>");
      } else {
        buffer.append("<h3>--- BEGIN COMPILING ---</h3><p><i>No Syntax Errors Detected</i></p>");
      }
    }

    buffer.append("<h3>--- Done Compiling ---</h3></body></html>");

    compilerMessages = buffer.toString();

    return compilerMessages;
  }

  /* (non-Javadoc)
   * @see eecs.editor.language.DefaultLanguageKit#getCompilerPercentComplete()
   */
  public int getCompilerPercentComplete() {
    if (compileTask == null) {
      return 100;
    } else {
    	synchronized (compileTask) {
    		return (int) ((double) compileTask.getCurrent() / (double) compileTask.getLengthOfTask() * 100);
    	}
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param window DOCUMENT ME!
   */
  public void setFormatPrefs(java.awt.Window window) {
    if (window == null) {
      window = new JFrame();
    }
    SettingsDialog dlg = SettingsDialog.create(window);
    dlg.setSize(500, 500);
    dlg.setVisible(true);
    //dlg.show();
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getRunResults() {
    while (isRunning && (runProcess != null)) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }
    }

    if (runMessages == null) {
      runMessages = new StringBuffer();
    }

    return runMessages.toString();
  }
  
  /** Converts special characters in a string to their HTML entities.
   * 
   * @param text  The String to convert.
   * @return      The String with entities.
   */
  private String textToHTML(String text) {
    String result;
    result = text.replaceAll("&", "&amp;");
    result = result.replaceAll("<", "&lt;"); 
    result = result.replaceAll(">", "&gt;");
    result = result.replaceAll("\"","&quot;");
    result = result.replaceAll("'","&#39;");
    result = result.replaceAll("\n", "<br>"); 
    result = result.replaceAll("\\s", "&nbsp;");
    return result;
  }

  /**
   * Returns <code>true</code>, since Java is an executable language.
   *
   * @see eecs.editor.language.DefaultLanguageKit#isRunnable()
   */
  public boolean isRunnable() {
    return true;
  }

  /* (non-Javadoc)
   * @see eecs.editor.language.DefaultLanguageKit#compile(java.io.File)
   */
  public void compile(File file) {
    compileResult = COMPILE_INCOMPLETE;
    compileStatus = 0;
    //compileTask = new GauntletCompileTask();
    synchronized (compileTask) {
	    compileTask.setFile(file);
	    compileTask.go();
    }
    new Thread(new Runnable() {
        public void run() {
          while (!compileTask.done()) {
            try {
              Thread.sleep(50);
            } catch (InterruptedException ie) {
            }
          }
          String[] msg;
          synchronized (compileTask) {
          	msg = compileTask.getCompilerMessages();
          }
          if ((msg == null) || (msg.length == 0)) {
            compileResult = COMPILE_SUCCESS;
          } else {
            compileResult = COMPILE_FAILURE;
          }
        }
      },"java_compile").start();
  }

  /* (non-Javadoc)
   * @see eecs.editor.language.LanguageKit#hasCompiler()
   */
  public boolean hasCompiler() {
    return true;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public boolean hasFormatPreferences() {
    // No reason to allow preferences if formatter is broken
    return canReformat;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public boolean hasFormatter() {
    return canReformat;
  }

  /* (non-Javadoc)
   * @see eecs.editor.language.LanguageKit#reformatAction(java.io.File)
   */
  public void reformat(JEditTextArea textpane) {
    formatMessages = new StringBuffer();
    formatResult = REFORMAT_ERRORS;

    StringBuffer newBuffer = new StringBuffer();
    String buffer = textpane.getText();

    // Since we're operating from a StringReader, create a temp
    // file to use for Jalopy setInput (why does it need a path?)
    File temp;

    try {
      temp = File.createTempFile("fmt", "java");
      temp.deleteOnExit();
    } catch (IOException e) {
      return;
    }

    jalopy.setInput(new StringReader(buffer), temp.getPath());
    jalopy.setOutput(newBuffer);

    if (jalopy.format()) {
      textpane.setText(newBuffer.toString());
    }

    if (jalopy.getState() == Jalopy.State.OK) {
      formatMessages.append("Reformat completed successfully");
      formatResult = REFORMAT_SUCCESS;
    } else if (jalopy.getState() == Jalopy.State.WARN) {
      formatMessages.append("Formatted with warnings");
      formatResult = REFORMAT_WARNINGS;
    } else if (jalopy.getState() == Jalopy.State.ERROR) {
      formatMessages.append(
        "<h2>Your code has syntax errors.</h2> You must compile " +
        "it and fix the errors before it can be reformatted.");
      formatResult = REFORMAT_ERRORS;
    }
  }

  /* Returns a ProcessWrapper, prepared to execute the specified
   * file.
   * @see eecs.editor.language.DefaultLanguageKit#run(java.io.File)
   */
  public ProcessWrapper run(File file) {
    runMessages = new StringBuffer();

    String fileName = file.getName();
    final String programName = fileName.substring(0, fileName.lastIndexOf("."));
    runMessages.append("<html><head><title>Running \"" + programName +
      "\"</title></head><body>");
    runMessages.append("<h3>--- Running \"" + programName +
      "\" - Output: ---</h3>");

    String command = "java " + programName;
    isRunning = true;
    runProcess = new ProcessWrapper(command, file.getParentFile()) {
          public void onOutput(char c) {
            runMessages.append(String.valueOf(c));
          }

          public void onStop() {
            runFinished();
            runMessages.append("<h3>--- End " + programName + "---</h3>");
          }
        };

    return runProcess;
  }

  /**
   * DOCUMENT ME!
   */
  protected void runFinished() {
    isRunning = false;
    runProcess = null;
  }
}
