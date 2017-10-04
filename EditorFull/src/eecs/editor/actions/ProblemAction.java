package eecs.editor.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import eecs.editor.Editor;
import eecs.editor.language.LanguageKit;
import eecs.editor.language.LanguageKitFactory;
import eecs.editor.problems.Problem;
import eecs.editor.problems.ProblemManager;
import eecs.util.LookUpTable;
import eecs.util.html.BrowserControl;

public class ProblemAction extends AbstractAction {
  private static final int DESCRIPTION_TAB = 0;
  private ProblemManager problemManager = null;
  volatile Problem currentProblem = null;
  
  public ProblemAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
    super(text, icon);
    putValue(SHORT_DESCRIPTION, desc);
    putValue(MNEMONIC_KEY, mnemonic);
    putValue(ACCELERATOR_KEY, accel);
  }

  public void actionPerformed(ActionEvent arg0) {
    openProblemAction();
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    programTabs.getSelectedComponent().requestFocusInWindow();
  }
  
  void openProblemAction() {
    Editor editor = (Editor) LookUpTable.getInstance().get("editor");
    JFrame mainWindow = editor.getMainWindow();
    currentProblem = editor.getCurrentProblem();
    if (currentProblem != null) {
      if (JOptionPane.showConfirmDialog(null, "You already have one problem open.  Close that problem and open a new one?") != JOptionPane.YES_OPTION) {
        return;
      }
    }
    Problem problem = null;
    if (problemManager == null) {
      problemManager = new ProblemManager(mainWindow, true);
    }
    problemManager.setVisible(true);
    File save = problemManager.getSelectedFile();
    if (save != null) {
      try {
        problem = new Problem();
        if (problem.readProblem(save)) {
          LanguageKit kit = LanguageKitFactory.getLanguageKit(problem.getLanguage());
          editor.addProgramTab("new " + kit.getLanguage(), kit);
          editor.filesOpen(true);
        }
      }
      catch (Exception e) {
        //
      }
    }
    else {
      return;
    }
    if (problem == null) {
      return;
    }
    editor.getCurrentProgramTab().setText(problem.getTemplate());
    editor.getDescriptionArea().setText(problem.getDescription());
    editor.getDescriptionArea().setLineWrap(true);
    editor.getDescriptionArea().setWrapStyleWord(true);
    editor.getDescriptionArea().setCaretPosition(0);
    editor.getMessageTabs().setSelectedIndex(DESCRIPTION_TAB);
    StringBuffer status = new StringBuffer("New Problem: " + problem.getTitle());
    editor.getTestMenuItem().setEnabled(false);
    if (!problem.isTestable()) {
      status.append("\\ (not testable)");
    }
   
    currentProblem = problem;
    editor.setCurrentProblem(currentProblem);
    if (problem.getExternalLink() != null) {
      BrowserControl.displayURL(problem.getExternalLink());
    }
  }
}