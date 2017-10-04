package eecs.editor.language;

import eecs.editor.jedit.JEditTextArea;
import eecs.editor.util.XMLIndent;
import eecs.util.ProcessWrapper;
import eecs.util.html.BrowserControl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.prefs.Preferences;

class HTMLKit extends DefaultLanguageKit {
  private String runMessages = null;
  String compilerMessages = null;

  // The percent of compilation that is complete. It is declared volatile since it may be accessed asynchronously from multiple threads.
  volatile int percentComplete = 0;
  private Preferences prefs = Preferences.userRoot().node("/eecs/editor");
  private StringBuffer htmlTemplate = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
      + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" + "    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
      + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\n" + "  <head>\n" + "    <title>Untitled</title>\n  </head>\n"
      + "  <body>\n\n" + "  </body>\n" + "</html>\n");

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
    XMLIndent formatter = new XMLIndent();
    int tabSize = prefs.getInt("tab.size", 4);
    formatter.setTabSize(tabSize);
    File temp;
    try {
      temp = File.createTempFile("frmt", ".htm");
      temp.deleteOnExit();
      String text = textpane.getText();
      BufferedWriter out = new BufferedWriter(new FileWriter(temp));
      out.write(text);
      out.close();
    }
    catch (IOException e) {
      return;
    }
    formatResult = formatter.formatDocument(temp);
//    if (formatResult != REFORMAT_ERRORS) {
      textpane.setText(formatter.getResultMessages());
//    }
//    else {
//      formatMessages.append("<h2>File has errors.</h2>" + "You must fix the errors before this file can be reformatted.\n");
//    }
  }

  public boolean isRunnable() {
    return true;
  }

  public synchronized void compile(final File file) {
    compileResult = LanguageKit.COMPILE_INCOMPLETE;
    percentComplete = 5;
    new Thread(new Runnable() {
      public void run() {
        final eecs.editor.util.HTMLChecker checker = new eecs.editor.util.HTMLChecker();
        new Thread(new Runnable() {
          public void run() {
            compilerMessages = "";
            synchronized (compilerMessages) {
              compilerMessages = checker.check(file);
            }
          }
        }, "html_checker").start();
        while (checker.getPercentComplete() < 100) {
          try {
            Thread.sleep(50);
          }
          catch (InterruptedException ie) {
            //
          }
          percentComplete = checker.getPercentComplete();
        }
        compileResult = checker.getValidHTML() ? LanguageKit.COMPILE_SUCCESS : LanguageKit.COMPILE_FAILURE;
        percentComplete = 100;
      }
    }, "html_compile").start();
  }

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
      }
      catch (InterruptedException e) {
        //
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
    }
    catch (IOException e) {
      runMessages = "Failed to find file using URL \"" + programName + "\".";
    }
    catch (NullPointerException e) {
      runMessages = "File must be saved to use the view option.";
    }
    BrowserControl.displayURL(programName);
    return null;
  }

  public String getRunResults() {
    return runMessages;
  }
}