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

import eecs.util.ProcessWrapper;
import eecs.util.html.BrowserControl;

import java.io.*;
import java.util.prefs.Preferences;

import jedit.JEditTextArea;


/**
 * @author Karl
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
class HTMLKit extends DefaultLanguageKit {
  private String runMessages = null;
  private String compilerMessages = null;
  
  /** The percent of compilation that is complete.
   * It is declared volatile since it may be accessed asynchronously
   * from multiple threads.
   */
  private volatile int percentComplete = 0;

  private Preferences prefs = Preferences.userRoot().node("/eecs/editor");
  /** DOCUMENT ME! */
  private StringBuffer htmlTemplate = new StringBuffer(
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
      "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
      "    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
      "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\n" +
      "  <head>\n" + "    <title>Untitled</title>\n  </head>\n" +
      "  <body>\n\n" + "  </body>\n" + "</html>\n");

  HTMLKit() {
    super();
    setTemplate(htmlTemplate);
    language = LanguageKit.HTML;
    description = "HTML/XHTML web page";
    fileSuffix = ".htm";
  }

  public boolean hasFormatter() {
    return true;
  }

  public boolean hasCompiler() {
    return true;
  }

  public void reformat(JEditTextArea textpane) {
    formatResult = NOT_SUPPORTED;
    formatMessages = new StringBuffer();

    eecs.editor.util.XMLIndent formatter = new eecs.editor.util.XMLIndent();
    int tabSize = prefs.getInt("tab.size",4);
    
    formatter.setTabSize(tabSize);

    File temp;

    try {
      // Create temp file.
      temp = File.createTempFile("frmt", ".htm");

      // Delete temp file when program exits.
      temp.deleteOnExit();
      String text = textpane.getText();
      // Write to temp file
      BufferedWriter out = new BufferedWriter(new FileWriter(temp));
      out.write(text);
      out.close();
    } catch (IOException e) {
      return;
    }

    formatResult = formatter.formatDocument(temp);

    if (formatResult != REFORMAT_ERRORS) {
      textpane.setText(formatter.getResultMessages());
    } else {
      formatMessages.append("<h2>File has errors.</h2>" +
        "You must fix the errors before this file can be reformatted.\n");
    }
  }

  public boolean isRunnable() {
    return true;
  }

  public synchronized void compile(final File file) {
    compileResult = LanguageKit.COMPILE_INCOMPLETE;
    percentComplete = 5;
    new Thread(new Runnable(){

		public void run() {
			final eecs.editor.util.HTMLChecker checker = new eecs.editor.util.HTMLChecker();
			new Thread(new Runnable() {
				public void run() {
          compilerMessages = "";
					synchronized (compilerMessages) {
						compilerMessages = checker.check(file);
					}
				}
			},"html_checker").start();
			while (checker.getPercentComplete()<100) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException ie) {}
				percentComplete = checker.getPercentComplete();
			}
			compileResult = checker.getValidHTML() ? LanguageKit.COMPILE_SUCCESS
			: LanguageKit.COMPILE_FAILURE;
			percentComplete = 100;
		}
    	
    },"html_compile").start();
  }

  /**
   *
   * @see eecs.editor.language.DefaultLanguageKit#getCompilerPercentComplete()
   */
  public int getCompilerPercentComplete() {
    return percentComplete;
  }

  public synchronized int getCompileResult() {
    return compileResult;
  }

  public String getCompilerMessages() {
  	while (compileResult == LanguageKit.COMPILE_INCOMPLETE) {
  		try {
  			Thread.sleep(100);
  		} catch (InterruptedException e) {
  		}
  	}
  	String results = "";
  	synchronized (compilerMessages) {
  		results = compilerMessages;
  	}
    return results;
  }

  public ProcessWrapper run(File file) {
    String programName = "";
    runMessages = "";
    try {
      programName = "file://" + file.getCanonicalPath();
    } catch (IOException e) {
      runMessages = "Failed to find file using URL \"" + programName + "\".";
    } catch (NullPointerException e) {
    	runMessages = "File must be saved to use the view option.";
    }

    // Open the HTML in a browser window
    BrowserControl.displayURL(programName);

    return null;
  }

  public String getRunResults() {
    return runMessages;
  }
}
