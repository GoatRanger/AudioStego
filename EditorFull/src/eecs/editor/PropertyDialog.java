package eecs.editor;

import java.awt.Font;

// A dialog to allow the user to set properties of the editor.
public class PropertyDialog extends javax.swing.JDialog {
  int textSize = 12;
  int tabSize = 4;
  int tempTabSize = 4;
  int autoSaveTime = 10;
  private javax.swing.JButton acceptButton;
  private javax.swing.JLabel autoSaveLabel;
  private javax.swing.JComboBox autoSaveList;
  private javax.swing.JPanel autoSavePanel;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JButton cancelButton;
  private javax.swing.JPanel indentationPanel;
  private javax.swing.JComboBox tabSizeEntry;
  private javax.swing.JLabel tabSizeLabel;
  private javax.swing.JPanel textSizePanel;
  private javax.swing.JLabel textSizeSample;
  private javax.swing.JSlider textSlider;

  public PropertyDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
  }

  public int getAutoSaveTime() {
    return autoSaveTime;
  }

  public void setAutoSaveTime(int time) {
    autoSaveTime = time;
    autoSaveList.setSelectedItem(String.valueOf(time));
  }

  public int getTextSize() {
    return textSize;
  }

  public void setTextSize(final int size) {
    int newSize = size;
    if (newSize < 6) {
      newSize = 6;
    }
    if (newSize > 34) {
      newSize = 34;
    }
    textSize = newSize;
    textSlider.setValue(textSize);
  }

  public int getTabSize() {
    return tabSize;
  }

  public void setTabSize(final int size) {
    int newSize = size;
    if (newSize < 2) {
      newSize = 2;
    }
    if (newSize > 10) {
      newSize = 10;
    }
    tabSize = newSize;
    int index = 0;
    if (tabSize < 4) {
      index = tabSize - 2;
    }
    else {
      index = tabSize / 2;
    }
    tabSizeEntry.setSelectedIndex(index);
  }

  public void cancelChanges() {
    textSlider.setValue(textSize);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method
   * is always regenerated by the Form Editor.
   */
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;
    textSizePanel = new javax.swing.JPanel();
    textSlider = new javax.swing.JSlider();
    textSizeSample = new javax.swing.JLabel();
    buttonPanel = new javax.swing.JPanel();
    acceptButton = new javax.swing.JButton();
    cancelButton = new javax.swing.JButton();
    indentationPanel = new javax.swing.JPanel();
    tabSizeLabel = new javax.swing.JLabel();
    tabSizeEntry = new javax.swing.JComboBox();
    autoSavePanel = new javax.swing.JPanel();
    autoSaveLabel = new javax.swing.JLabel();
    autoSaveList = new javax.swing.JComboBox();
    getContentPane().setLayout(new java.awt.GridBagLayout());
    setTitle("Options");
    setBackground(java.awt.Color.lightGray);
    setLocationRelativeTo(indentationPanel);
    setModal(true);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });
    textSizePanel.setLayout(new java.awt.GridLayout(1, 0));
    textSizePanel.setBorder(new javax.swing.border.TitledBorder("Text Size"));
    textSizePanel.setMinimumSize(new java.awt.Dimension(250, 77));
    textSizePanel.setPreferredSize(new java.awt.Dimension(500, 80));
    textSlider.setMajorTickSpacing(4);
    textSlider.setMaximum(34);
    textSlider.setMinimum(6);
    textSlider.setMinorTickSpacing(1);
    textSlider.setPaintLabels(true);
    textSlider.setPaintTicks(true);
    textSlider.setSnapToTicks(true);
    textSlider.setValue(12);
    textSlider.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent evt) {
        textScrollChanged(evt);
      }
    });
    textSizePanel.add(textSlider);
    textSizeSample.setFont(new java.awt.Font("Monospaced", 0, 12));
    textSizeSample.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    textSizeSample.setLabelFor(textSlider.getRootPane());
    textSizeSample.setText("Example Text");
    textSizeSample.setFocusable(false);
    textSizeSample.setMaximumSize(new java.awt.Dimension(400, 40));
    textSizeSample.setPreferredSize(new java.awt.Dimension(280, 40));
    textSizeSample.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    textSizePanel.add(textSizeSample);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
    getContentPane().add(textSizePanel, gridBagConstraints);
    acceptButton.setText("OK");
    acceptButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        acceptAction(evt);
      }
    });
    buttonPanel.add(acceptButton);
    cancelButton.setText("Cancel");
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelAction(evt);
      }
    });
    buttonPanel.add(cancelButton);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
    getContentPane().add(buttonPanel, gridBagConstraints);
    indentationPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    indentationPanel.setBorder(new javax.swing.border.TitledBorder(null, "Indentation", javax.swing.border.TitledBorder.LEFT,
        javax.swing.border.TitledBorder.DEFAULT_POSITION));
    indentationPanel.setToolTipText("Converts tabs to the specified number of spaces");
    indentationPanel.setAlignmentX(0.0F);
    indentationPanel.setMinimumSize(new java.awt.Dimension(240, 60));
    indentationPanel.setPreferredSize(new java.awt.Dimension(240, 60));
    tabSizeLabel.setLabelFor(tabSizeEntry);
    tabSizeLabel.setText("Tab Size:");
    indentationPanel.add(tabSizeLabel);
    tabSizeEntry.setMaximumRowCount(6);
    tabSizeEntry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2", "3", "4", "6", "8", "10" }));
    tabSizeEntry.setToolTipText("Number of spaces to insert when TAB key is pressed");
    tabSizeEntry.setDoubleBuffered(true);
    tabSizeEntry.setMinimumSize(new java.awt.Dimension(70, 25));
    tabSizeEntry.setPreferredSize(new java.awt.Dimension(100, 25));
    indentationPanel.add(tabSizeEntry);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
    getContentPane().add(indentationPanel, gridBagConstraints);
    autoSavePanel.setBorder(new javax.swing.border.TitledBorder("Auto Save"));
    autoSavePanel.setMinimumSize(new java.awt.Dimension(240, 59));
    autoSavePanel.setPreferredSize(new java.awt.Dimension(240, 59));
    autoSaveLabel.setLabelFor(autoSaveList);
    autoSaveLabel.setText("Minutes Between Saves:");
    autoSavePanel.add(autoSaveLabel);
    autoSaveList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "5", "10", "15", "30" }));
    autoSaveList.setSelectedIndex(3);
    autoSavePanel.add(autoSaveList);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
    getContentPane().add(autoSavePanel, gridBagConstraints);
    pack();
  }

  void cancelAction(java.awt.event.ActionEvent evt) {
    cancelChanges();
    setVisible(false);
    dispose();
  }

  void acceptAction(java.awt.event.ActionEvent evt) {
    textSize = textSlider.getValue();
    tabSize = Integer.parseInt((String) tabSizeEntry.getSelectedItem());
    autoSaveTime = Integer.parseInt((String) autoSaveList.getSelectedItem());
    setVisible(false);
    dispose();
  }

  void textScrollChanged(javax.swing.event.ChangeEvent evt) {
    textSizeSample.setFont(new Font("Monospaced", Font.PLAIN, textSlider.getValue()));
  }

  void closeDialog(java.awt.event.WindowEvent evt) {
    cancelChanges();
    setVisible(false);
    dispose();
  }

  public static void main(String[] args) {
    new PropertyDialog(new javax.swing.JFrame(), true).show();
  }
}