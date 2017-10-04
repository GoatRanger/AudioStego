package eecs.editor.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;
import eecs.editor.jedit.JEditTextArea;
import eecs.util.LookUpTable;

public class CutAction extends AbstractAction {
  public CutAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
    super(text, icon);
    putValue(SHORT_DESCRIPTION, desc);
    putValue(MNEMONIC_KEY, mnemonic);
    putValue(ACCELERATOR_KEY, accel);
    setEnabled(false);
  }

  public void actionPerformed(ActionEvent actionEvent) {
    cutAction();
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    programTabs.getSelectedComponent().requestFocusInWindow();
  }

  void cutAction() {
    JComponent area = (JComponent) LookUpTable.getInstance().get("minorFocus");
    if (area instanceof JEditTextArea) {
      ((JEditTextArea) area).cut();
    }
    else
      if (area instanceof JTextComponent) {
        ((JTextComponent) area).cut();
      }
  }
}