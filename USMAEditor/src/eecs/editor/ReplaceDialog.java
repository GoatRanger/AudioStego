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
 * ReplaceDialog.java
 *
 * Created on June 18, 2003, 3:42 PM
 */
package eecs.editor;

import java.awt.event.ActionListener;


/**
 *
 * @author  dk8685
 */
public class ReplaceDialog extends javax.swing.JDialog {
  public static final String REPLACE_ACTION = "replace.replace";
  public static final String REPLACE_ALL_ACTION = "replace.replace.all";
  public static final String FIND_ACTION = "replace.find";
  public static final String CLOSE_ACTION = "replace.close";
  private boolean replaceActive = true;

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JButton closeButton;
  private javax.swing.JButton findButton;
  private javax.swing.JLabel findLabel;
  private javax.swing.JTextField findText;
  private javax.swing.JPanel inputPanel;
  private javax.swing.JCheckBox matchCaseCheck;
  private javax.swing.JCheckBox matchWordsCheck;
  private javax.swing.JPanel optionsPanel;
  private javax.swing.JButton replaceAllButton;
  private javax.swing.JButton replaceButton;
  private javax.swing.JLabel replaceLabel;
  private javax.swing.JTextField replaceText;

  /** Creates new form ReplaceDialog */
  public ReplaceDialog(java.awt.Frame parent, boolean modal,
    ActionListener listener) {
    super(parent, modal);
    initComponents();
    findButton.addActionListener(listener);
    replaceButton.addActionListener(listener);
    replaceAllButton.addActionListener(listener);
    closeButton.addActionListener(listener);
    this.getRootPane().setDefaultButton(findButton);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  private void initComponents() { //GEN-BEGIN:initComponents

    java.awt.GridBagConstraints gridBagConstraints;

    inputPanel = new javax.swing.JPanel();
    findLabel = new javax.swing.JLabel();
    findText = new javax.swing.JTextField();
    replaceLabel = new javax.swing.JLabel();
    replaceText = new javax.swing.JTextField();
    buttonPanel = new javax.swing.JPanel();
    findButton = new javax.swing.JButton();
    replaceButton = new javax.swing.JButton();
    replaceAllButton = new javax.swing.JButton();
    closeButton = new javax.swing.JButton();
    optionsPanel = new javax.swing.JPanel();
    matchCaseCheck = new javax.swing.JCheckBox();
    matchWordsCheck = new javax.swing.JCheckBox();

    getContentPane().setLayout(new java.awt.GridBagLayout());

    setTitle("Replace");
    setLocationRelativeTo(this);
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent evt) {
          closeDialog(evt);
        }
      });

    inputPanel.setLayout(new java.awt.GridBagLayout());

    findLabel.setDisplayedMnemonic('F');
    findLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    findLabel.setLabelFor(findText);
    findLabel.setText("Find What:");
    findLabel.setBorder(new javax.swing.border.EmptyBorder(
        new java.awt.Insets(4, 4, 4, 4)));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
    inputPanel.add(findLabel, gridBagConstraints);

    findText.setMinimumSize(new java.awt.Dimension(250, 20));
    findText.setPreferredSize(new java.awt.Dimension(250, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    inputPanel.add(findText, gridBagConstraints);

    replaceLabel.setDisplayedMnemonic('l');
    replaceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    replaceLabel.setLabelFor(replaceText);
    replaceLabel.setText("Replace With:");
    replaceLabel.setBorder(new javax.swing.border.EmptyBorder(
        new java.awt.Insets(4, 4, 4, 4)));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
    inputPanel.add(replaceLabel, gridBagConstraints);

    replaceText.setMinimumSize(new java.awt.Dimension(250, 20));
    replaceText.setPreferredSize(new java.awt.Dimension(250, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    inputPanel.add(replaceText, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
    getContentPane().add(inputPanel, gridBagConstraints);

    buttonPanel.setLayout(new java.awt.GridLayout(4, 1));

    findButton.setText("Find");
    findButton.setActionCommand("replace.find");
    buttonPanel.add(findButton);

    replaceButton.setMnemonic('R');
    replaceButton.setText("Replace");
    replaceButton.setActionCommand("replace.replace");

    buttonPanel.add(replaceButton);

    replaceAllButton.setMnemonic('a');
    replaceAllButton.setText("Replace All");
    replaceAllButton.setActionCommand("replace.replace.all");
    buttonPanel.add(replaceAllButton);

    closeButton.setText("Close");
    closeButton.setActionCommand("replace.close");
    closeButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          closeButtonActionPerformed(evt);
        }
      });

    buttonPanel.add(closeButton);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    getContentPane().add(buttonPanel, gridBagConstraints);

    optionsPanel.setLayout(new javax.swing.BoxLayout(optionsPanel,
        javax.swing.BoxLayout.Y_AXIS));

    matchCaseCheck.setMnemonic('c');
    matchCaseCheck.setText("Match Case");
    matchCaseCheck.setDisplayedMnemonicIndex(6);
    optionsPanel.add(matchCaseCheck);

    matchWordsCheck.setMnemonic('w');
    matchWordsCheck.setText("Match Whole Words Only");
    optionsPanel.add(matchWordsCheck);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
    getContentPane().add(optionsPanel, gridBagConstraints);

    pack();
  }

  private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) { 
    setVisible(false);
    dispose();
  }

  /** Closes the dialog */
  private void closeDialog(java.awt.event.WindowEvent evt) {
    setVisible(false);
    dispose();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ReplaceDialog rd = new ReplaceDialog(new javax.swing.JFrame(), true, null);
    rd.openFindDialog();
    rd.openReplaceDialog();
    rd.openFindDialog();
    rd = null;
  }

  public boolean matchWholeWord() {
    return matchWordsCheck.isSelected();
  }

  public boolean isCaseSensitive() {
    return matchCaseCheck.isSelected();
  }

  public String getFindText() {
    return findText.getText();
  }

  public String getReplaceText() {
    return replaceText.getText();
  }

  public void openReplaceDialog() {
    if (!replaceActive) {
      this.setTitle("Replace");
      replaceButton.setVisible(true);
      buttonPanel.add(replaceButton);
      replaceAllButton.setVisible(true);
      buttonPanel.add(replaceAllButton);
      replaceLabel.setVisible(true);

      java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
      inputPanel.add(replaceLabel, gridBagConstraints);
      replaceText.setVisible(true);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
      inputPanel.add(replaceText, gridBagConstraints);

      this.getRootPane().setDefaultButton(findButton);
      validate();
      pack();
    }

    replaceActive = true;
    setVisible(true);
  }

  public void openFindDialog() {
    if (replaceActive) {
      this.setTitle("Find");
      replaceButton.setVisible(false);
      buttonPanel.remove(replaceButton);
      replaceAllButton.setVisible(false);
      buttonPanel.remove(replaceAllButton);
      replaceLabel.setVisible(false);
      inputPanel.remove(replaceLabel);
      replaceText.setVisible(false);
      inputPanel.remove(replaceText);
      this.getRootPane().setDefaultButton(findButton);
      pack();
      validate();
    }

    replaceActive = false;
    setVisible(true);
  }
}