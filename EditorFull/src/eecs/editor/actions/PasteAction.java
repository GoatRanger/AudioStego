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

public class PasteAction extends AbstractAction {
  public PasteAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
    super(text, icon);
    putValue(SHORT_DESCRIPTION, desc);
    putValue(MNEMONIC_KEY, mnemonic);
    putValue(ACCELERATOR_KEY, accel);
    setEnabled(false);
  }

  public void actionPerformed(ActionEvent evt) {
    pasteAction();
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    programTabs.getSelectedComponent().requestFocusInWindow();
  }
  
  void pasteAction() {
    JComponent area = (JComponent) LookUpTable.getInstance().get("minorFocus");
    if (area != null) {
      if (area instanceof JEditTextArea) {
        ((JEditTextArea) area).paste();
      }
      else
        if (area instanceof JTextComponent) {
          ((JTextComponent) area).paste();
        }
    }
  }
}