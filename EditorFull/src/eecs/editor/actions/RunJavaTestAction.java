package eecs.editor.actions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import eecs.editor.EditPanel;
import eecs.editor.Editor;
import eecs.editor.environment.Environment;
import eecs.editor.language.LanguageKit;
import eecs.editor.problems.Problem;
import eecs.editor.problems.Problem.TestCallBack;
import eecs.util.JavaWebStartManager;
import eecs.util.LookUpTable;
import eecs.util.ProcessWrapper;

public class RunJavaTestAction {
  Editor editor = null;
  EditPanel currentProgramTab = null;
  volatile Problem currentProblem = null;
  AbstractAction runAction = null;
  JFrame mainWindow = null;
  volatile ProcessWrapper process = null;
  JTabbedPane messageTabs = null;
  JLabel statusArea = null;
  JTextPane console = null;
  JProgressBar progressBar = null;
  Timer timer = null;
  
  public void runJavaTestAction() {
    editor = (Editor) LookUpTable.getInstance().get("editor");
    currentProgramTab = editor.getCurrentProgramTab();
    String type = currentProgramTab.getLanguageKit().getLanguage();
    if (type.equals(LanguageKit.JAVA)) {
      currentProblem = editor.getCurrentProblem();
      runAction = editor.getRunAction();
      mainWindow = editor.getMainWindow();
      process = editor.getProcess();
      messageTabs = editor.getMessageTabs();
      statusArea = editor.getStatusArea();
      console = editor.getConsole();
      progressBar = editor.getProgressBar();
      runJavaTest();
    }
  }

  protected synchronized void runJavaTest() {
    runAction.setEnabled(false);
    editor.getTestMenuItem().setEnabled(false);
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    programTabs.setEnabled(false);
    currentProgramTab.setEnabled(false);
    final EditPanel panel = currentProgramTab;
    final File saveFile = panel.getSaveFile();
    if (currentProblem == null) {
      JOptionPane.showMessageDialog(null,
          "You do not have an active Problem.\n To see if this program works, you should use the Compile and Run options in"
              + "\nTools (or use the buttons in the toolbar). If you are solving a homework"
              + "\nProblem Set, then you must open the Problem using the Problem Set Manager\\nbefore you load your program.");
      editor.updateStatusBar("Not loaded as an ICE. No tests to run.");
      runAction.setEnabled(true);
      programTabs.setEnabled(true);
      panel.setEnabled(true);
      return;
    }
    if (process != null) {
      editor.updateStatusBar("Still executing, cannot run tests.");
      programTabs.setEnabled(true);
      panel.setEnabled(true);
      editor.beep();
      return;
    }
    if (!panel.saveIfNecessary("running tests", true, false)) {
      editor.updateStatusBar("File not saved, didn't test.");
      programTabs.setEnabled(true);
      panel.setEnabled(true);
      return;
    }

    clearConsoleWindowAndMakeVisible();
    
    ProgressBarTimerListener progressBarTimerListener = new ProgressBarTimerListener(saveFile, panel.getLanguageKit().isRunnable());
    timer = new javax.swing.Timer(80, progressBarTimerListener);
    timer.start();
  }

  protected void clearConsoleWindowAndMakeVisible() {
    messageTabs.setSelectedIndex(Editor.CONSOLE_TAB);
    mainWindow.getContentPane().remove(statusArea);
    progressBar.setString("Testing");
    progressBar.setStringPainted(true);
    progressBar.setIndeterminate(true);
    mainWindow.getContentPane().add(progressBar, BorderLayout.SOUTH);
    mainWindow.validate();
  }

  protected class ProgressBarTimerListener implements ActionListener {
    File saveFile = null;
    boolean isPanelRunnable = false;
    boolean runningTests = false;
    
    public ProgressBarTimerListener(File saveFile, boolean isPanelRunnable) {
      this.saveFile = saveFile;
      this.isPanelRunnable = isPanelRunnable;
    }  
    
    public void actionPerformed(ActionEvent evt) {
      if (!runningTests) {
        runningTests = true;
        String fileName = saveFile.getName();
        int lastDotPos = fileName.lastIndexOf(".");
        String extension = "";
        if (lastDotPos > 0) {
          extension = fileName.substring(lastDotPos);
        }
        console.setText("");
        editor.printLineToConsole("----- BEGIN TESTING -----");
        String programName = fileName.substring(0, lastDotPos);
        String command ="java -Djava.ext.dirs=\"\" -Deecs.testing=true -classpath .;" 
            + "\"" + Environment.getJagoPath() + "\""
            + ";" + "\"" + Environment.getWrapperPath() + "\"" 
            + ";" + "\"" + Environment.getInstructorPath() + "\"" 
            + ";" + "\"" + Environment.getMediaPath() + "\"" 
            + ";" + "\"" + Environment.getEclipseLibDirectory() 
            + "\";\"" + saveFile.getParentFile() + "\" "+ programName;
   
        JavaTestCallBack testCallBack = new JavaTestCallBack(isPanelRunnable);
        currentProblem.verify(command, saveFile.getParentFile(), testCallBack);
      }
      else {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            console.setText("<html><body>" + currentProblem.getResults() + "</body></html>");
          }
        });
      }
    }
  }
  
  protected class JavaTestCallBack implements TestCallBack {
    boolean isPanelRunnable = false;
    
    public JavaTestCallBack(boolean isPanelRunnable) {
      this.isPanelRunnable = isPanelRunnable;
    }
    
    public void done(final String result) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          String output = "<html><head><title>Test Results</title></head><body>";
          output += result + "</body></html>";
          console.setText(output);
          mainWindow.getContentPane().remove(progressBar);
          mainWindow.getContentPane().add(statusArea, BorderLayout.SOUTH);
          editor.updateStatusBar("Testing Complete");
          timer.stop();
          runAction.setEnabled(isPanelRunnable);
          editor.getTestMenuItem().setEnabled(true);
          JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
          programTabs.setEnabled(true);
          currentProgramTab.setEnabled(true);
          mainWindow.validate();
        }
      });
    }
  }  
}