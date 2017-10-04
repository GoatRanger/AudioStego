/*
 * Copyright (C) 2003 USMA This program is free software; you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the License,
 * or any later version. This program is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU
 * General Public License along with this program; if not, write to the Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
/*
 * Copyright (C) 2003 USMA This program is free software; you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the License,
 * or any later version. This program is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU
 * General Public License along with this program; if not, write to the Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
/*
 * ProblemManager.java Created on June 14, 2003, 4:58 PM
 */
package eecs.problem;

import eecs.util.PrintText;
//import eecs.editor.util.Constants;
import eecs.util.FileManager;
import eecs.util.InterfaceCheck;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.util.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;


/**
 * DOCUMENT ME!
 * 
 * @author Karl Gossett
 */
public class ProblemManager extends javax.swing.JDialog {
  
  private boolean networkAvailable = false;
  
  private static final Dimension BUTTON_SIZE = new java.awt.Dimension(120, 24);
  /** DOCUMENT ME! */
  static final String LOCAL_DIR = System.getProperty("user.home")
      + "\\.it105editor\\ices";
  static final boolean OVERWRITE = true;
  static final boolean NEW_ONLY = false;
  /** DOCUMENT ME! */
  File selectedFile = null;
  MouseListener ml = new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
      int selRow = localTree.getRowForLocation(e.getX(), e.getY());
      if (localTree.getLastSelectedPathComponent() == null) {
        return;
      }
      if (selRow != -1) {
        if (e.getClickCount() == 2) {
          String name = localTree.getLastSelectedPathComponent().toString();
          if (problemNames.indexOf(name) != -1) {
            openButtonActionPerformed();
          }
        }
      }
    }
  };
  /** DOCUMENT ME! */
  File[] fileList;
  String[] problemNameList;
  /** DOCUMENT ME! */
  private JButton cancelButton;
  /** A button used to clear the local problem set cache */
  private JButton clearCacheButton;
  /** DOCUMENT ME! */
  private JButton openButton;
  /** Used to print the list of completed exercises */
  private JButton printCompletedButton;
  /** Used to update the local problem set cache from the network */
  private JButton updateButton;
  /** DOCUMENT ME! */
  private JLabel localLabel;
  /** DOCUMENT ME! */
  private JList completedList;
  /** DOCUMENT ME! */
  private JList localList;
  /** DOCUMENT ME! */
  private JPanel actionPanel;
  /** DOCUMENT ME! */
  private JPanel completedPanel;
  /** DOCUMENT ME! */
  private JScrollPane completedScroller;
  /** DOCUMENT ME! */
  private JScrollPane localScroller;
  private JTree localTree;
  private List problemNames;
  private Vector problemFiles;

  /**
   * Creates a new ProblemManager.
   * 
   * @param parent
   *            The <code>Frame</code> to which this manager belongs.
   * @param modal
   *            If this window should be modal.
   */
  public ProblemManager(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    Constants.PREFERENCES
        .addPreferenceChangeListener(new PreferenceChangeListener() {
          public void preferenceChange(PreferenceChangeEvent pe) {
            populateCompleted();
          }
        });
    // Initially disabled until an exercise is selected
    openButton.setEnabled(false);
    InterfaceCheck ic = new InterfaceCheck();
    networkAvailable = ic.isURLAvailable("http://www-internal.eecs.usma.edu/courses/it105/");
    setupLocal();
    if (networkAvailable) {
      copyFromNetwork(OVERWRITE);
      //resetProblemSetCache();
    }
    populateLocal();
    populateCompleted();
  }

  /**
   * DOCUMENT ME!
   * 
   * @return DOCUMENT ME!
   */
  public File getSelectedFile() {
    return selectedFile;
  }

  /**
   * DOCUMENT ME!
   * 
   * @param visible
   *            DOCUMENT ME!
   */
  public void setVisible(boolean visible) {
    if (networkAvailable) {
      updateButton.setEnabled(true);
      clearCacheButton.setEnabled(true);
    } else {
      updateButton.setEnabled(false);
      clearCacheButton.setEnabled(false);
    }
    if (visible) selectedFile = null;
    super.setVisible(visible);
  }

  /**
   * DOCUMENT ME!
   * 
   * @param overwrite
   *            DOCUMENT ME!
   */
  public void copyFromNetwork(boolean overwrite) {
    if (!networkAvailable) {
      return;
    }
    
    List<StringBuffer> fileList = getFileList();
    
    for (StringBuffer file : fileList) {
      String remoteName = file.toString();
      int startIdx = remoteName.indexOf("\\ices\\");
      if (startIdx > 0) {
        remoteName = remoteName.substring(startIdx + 6);
      }
      if (remoteName.startsWith("CVS")) {
        continue;
      }
      //FileWriter newFile = null;
      String remoteDir = "http://www-internal.eecs.usma.edu/Courses/it105/Resources/IT105%20Editor/ices/";
      String localFile = LOCAL_DIR + "\\" + remoteName;
      File check = new File(localFile);
      int lastSlash = remoteName.lastIndexOf("\\");
      if (lastSlash > 0) {
        String newDir = LOCAL_DIR + "\\" + remoteName.substring(0, lastSlash);
        (new File(newDir)).mkdirs();
      }
      if (check.exists() && !overwrite) {
        continue;
      }
      if (!FileManager.copy(remoteDir+remoteName,localFile)) {
        System.err.println("Couldn't write Problem Set file "
            + LOCAL_DIR + "\\" + remoteName);
      }
    }
  }

  /**
   * DOCUMENT ME!
   * 
   * @param args
   *            the command line arguments
   */
  public static void main(String[] args) {
    new ProblemManager(new javax.swing.JFrame(), true).setVisible(true);
  }

  /**
   * DOCUMENT ME!
   */
  public void populateCompleted() {
    String[] keys = null;
    try {
      keys = Constants.PREFERENCES.keys();
      DateFormat df = DateFormat.getInstance();
      Vector entries = new Vector();
      //int width = completedList.getWidth();
      //Font fnt = new Font("Monospaced", Font.PLAIN, 12);
      for (int i = 0; i < keys.length; i++) {
        if (keys[i].startsWith("ice")) {
          String keyname = keys[i].substring(3);
          String spaces = "                                         ";
          int space = ((keyname.length() >= spaces.length()) ? 0 : (spaces
              .length() - keyname.length()));
          String spacer = spaces.substring(0, space);
          entries.add(keyname + spacer
              + df.format(new Date(Constants.PREFERENCES.getLong(keys[i], 0))));
        }
      }
      completedList.setListData(entries);
    } catch (BackingStoreException e) {
      e.printStackTrace();
    } catch (Exception ae) {
      System.err.println("Couldn't populate, based on \r\n"
          + keys.toString() +", message:\r\n" + ae.toString());
    }
  }

  /**
   * DOCUMENT ME!
   */
  public void setupLocal() {
    File dir = new File(LOCAL_DIR);
    if (!dir.exists()) {
      dir.mkdirs();
    }
  }

  /**
   * DOCUMENT ME!
   * 
   * @param filename
   *            DOCUMENT ME!
   * @return DOCUMENT ME!
   */
  protected String getDescription(String filename) {
    String path = "http://www-internal.eecs.usma.edu/Courses/It105/Resources/IT105%20Editor/ices/";
    File dir = new File(path, filename);
    if (dir.exists()) {
      Problem p = new Problem();
      p.readProblem(dir);
      return p.getTitle();
    } else {
      return null;
    }
  }

  /**
   * DOCUMENT ME!
   */
  protected void populateLocal() {
    String home = LOCAL_DIR;
    File dir = new File(home);
    problemFiles = new Vector();
    problemNames = new ArrayList();
    Object[] localList = getProblemList(dir, "Problem Sets");
    DefaultMutableTreeNode root = processHierarchy(localList);
    localTree = new JTree(root);
    localTree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent evt) {
        treeItemChanged(evt);
      }
    });
    localTree.addMouseListener(ml);
    localScroller.setViewportView(localTree);
  }

  private List<StringBuffer> getFileList() {
    List<StringBuffer> fileList = new ArrayList<StringBuffer>();
    String remoteDir = "http://www-internal.eecs.usma.edu/Courses/It105/Resources/IT105%20Editor/ices/";
    String name = remoteDir + "filelist.txt";
    URL url = null;
    try {
      url = new URL(name);
    } catch (MalformedURLException mue) {
      System.err.println("Invalid URL formed from " + name + ", message:\r\n" + mue.toString());
      return fileList;
    }
    try {
      // Get an input stream for reading
      InputStream in = url.openStream();
      BufferedInputStream bufIn = new BufferedInputStream(in);
      StringBuffer current = new StringBuffer();
      int count = 0;
      // Repeat until end of file
      for (; ; ) {
        int data = bufIn.read();
        // Check for EOF
        if (data == -1) {
          break;
        } else if (data > 13) {
          current.append((char) data);
        } else {
          if (data == 13) {
            fileList.add(current);
            current = new StringBuffer();
            count++;
          }
        }
      }
      bufIn.close();
      in.close();
    } catch (IOException ioe) {
      // do nothing -- this exception is thrown when cannot make
      // connection
      // so if not on the network. May want to update a status.
    }
    return fileList;
  }

  private Object[] getProblemList(File directory, String title) {
    Object[] problemList = null;
    List set = new ArrayList();
    set.add(title);
    java.io.FilenameFilter filter = new ExerciseFilter();
    File[] fileList = directory.listFiles(filter);
    if (fileList != null) {
      // Process directories first, so they show at the top.
      for (int i = 0; i < fileList.length; i++) {
        if (fileList[i].isDirectory()) {
          if (System.getProperty("user.name", "x30000").startsWith("x")
              && fileList[i].getName().equalsIgnoreCase("grading")) {
            // just ignore it for now
          } else {
            Object[] subList = getProblemList(fileList[i], fileList[i]
                .getName());
            if (subList.length > 1) {
              set.add(subList);
            }
          }
        }
      }
      for (int i = 0; i < fileList.length; i++) {
        if (fileList[i].isFile()
            && fileList[i].getName().toLowerCase().endsWith(".ice")) {
          try {
            Problem p = new Problem();
            if (p.readProblem(fileList[i])) {
              set.add(p.getTitle());
              problemFiles.add(fileList[i]);
              problemNames.add(p.getTitle());
            }
          } catch (NullPointerException e) {
            System.err.println("Error in problem " + fileList[i]
                + " so it was deleted.");
            fileList[i].delete();
            e.printStackTrace();
          }
        }
      }
    }
    problemList = set.toArray();
    return problemList;
  }

  /**
   * DOCUMENT ME!
   * 
   * @param evt
   *            DOCUMENT ME!
   */
  private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
    selectedFile = null;
    setVisible(false);
    dispose();
  }

  /**
   * Closes the dialog
   * 
   * @param evt
   *            DOCUMENT ME!
   */
  private void closeDialog(java.awt.event.WindowEvent evt) {
    setVisible(false);
    dispose();
  }

  /**
   * DOCUMENT ME!
   */
  private void initComponents() {
    localLabel = new javax.swing.JLabel();
    actionPanel = new javax.swing.JPanel();
    openButton = new javax.swing.JButton();
    cancelButton = new javax.swing.JButton();
    clearCacheButton = new JButton();
    localScroller = new javax.swing.JScrollPane();
    localList = new javax.swing.JList();
    localTree = new JTree();
    updateButton = new javax.swing.JButton();
    getContentPane().setLayout(
        new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    setTitle("Problem Set Manager");
    setModal(true);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });
    JPanel northPanel = new JPanel();
    northPanel.setLayout(new GridLayout(1, 0));
    localLabel.setText("Exercises:");
    northPanel.add(localLabel);
    JLabel completeLabel = new JLabel("Completed Exercises:");
    northPanel.add(completeLabel);
    getContentPane().add(northPanel);
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(1, 0));
    localScroller
        .setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    localScroller.setMinimumSize(new java.awt.Dimension(170, 220));
    localScroller.setPreferredSize(new java.awt.Dimension(250, 520));
    localList.setBorder(new javax.swing.border.EtchedBorder());
    localList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    localList
        .addListSelectionListener(new javax.swing.event.ListSelectionListener() {
          public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            listItemChanged(evt);
          }
        });
    localScroller.setViewportView(localTree);
    centerPanel.add(localScroller);
    completedList = new JList();
    completedScroller = new JScrollPane(completedList);
    completedScroller
        .setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    completedScroller.setMinimumSize(new java.awt.Dimension(170, 220));
    completedScroller.setPreferredSize(new java.awt.Dimension(440, 420));
    completedList.setBorder(new javax.swing.border.EtchedBorder());
    completedList
        .setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    completedList.setMaximumSize(new java.awt.Dimension(440, 400));
    completedList.setMinimumSize(new java.awt.Dimension(150, 200));
    completedList.setPreferredSize(new java.awt.Dimension(420, 400));
    completedList.setFont(new Font("Monospaced", Font.PLAIN, 12));
    completedScroller.setViewportView(completedList);
    completedScroller.setBorder(BorderFactory.createBevelBorder(3));
    centerPanel.add(completedScroller);
    getContentPane().add(centerPanel);
    actionPanel.setLayout(new GridLayout(1, 0));
    openButton.setText("Open Problem");
    openButton.setMaximumSize(BUTTON_SIZE);
    openButton.setMinimumSize(BUTTON_SIZE);
    openButton.setPreferredSize(BUTTON_SIZE);
    openButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        openButtonActionPerformed();
      }
    });
    actionPanel.add(openButton);
    updateButton.setText("Update List");
    updateButton.setToolTipText("Synchronize with course listing of exercises");
    updateButton.setMaximumSize(BUTTON_SIZE);
    updateButton.setMinimumSize(BUTTON_SIZE);
    updateButton.setPreferredSize(BUTTON_SIZE);
    updateButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        updateButtonActionPerformed();
      }
    });
    actionPanel.add(updateButton);
    if (!networkAvailable) {
      updateButton.setEnabled(false);
    }
    clearCacheButton.setText("Reset List");
    clearCacheButton
        .setToolTipText("Synchronize and remove outdated exercises");
    clearCacheButton.setMaximumSize(BUTTON_SIZE);
    clearCacheButton.setMinimumSize(BUTTON_SIZE);
    clearCacheButton.setPreferredSize(BUTTON_SIZE);
    clearCacheButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        resetProblemSetCache();
      }
    });
    actionPanel.add(clearCacheButton);
    cancelButton.setText("Cancel");
    cancelButton.setMaximumSize(BUTTON_SIZE);
    cancelButton.setMinimumSize(BUTTON_SIZE);
    cancelButton.setPreferredSize(BUTTON_SIZE);
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelButtonActionPerformed(evt);
      }
    });
    actionPanel.add(cancelButton);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 0));
    buttonPanel.add(actionPanel);
    // Create completed List
    JPanel printPanel = new JPanel();
    printPanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
    printCompletedButton = new JButton("Print Completed Exercises Report");
    printCompletedButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        printButtonActionPerformed();
      }
    });
    printPanel.add(printCompletedButton);
    buttonPanel.add(printPanel);
    getContentPane().add(buttonPanel);
    pack();
  }

  private void listItemChanged(javax.swing.event.ListSelectionEvent evt) {
    if (localList.getSelectedIndex() != -1) {
      openButton.setEnabled(true);
    } else {
      openButton.setEnabled(false);
    }
  }

  private void openButtonActionPerformed() {
    // TODO: Revise the means for getting the correct file path.
    // The issue here: Only titles are stored in the tree, but multiple
    // files may have the same title (i.e. where two people copied
    // the same ICE to their local list).  By just searching on the title,
    // the first match is returned, not necessarily the CORRECT match.
    // The logic here of determining the path, then getting the file name
    // and appending it to the path ensures the correct file is loaded,
    // but there must be a better way to accomplish this.
    // At a minimum, make the tree nodes an object that has a name
    // that is displayed, and a filename that is used for loading.
    String fileName = localTree.getLastSelectedPathComponent().toString();
    Object[] path = localTree.getSelectionPath().getPath();
    String pathName = LOCAL_DIR;
    for (int i=1;i<path.length-1;i++) {
      pathName += "\\" + path[i].toString();
    }
    int fileNum = 0;
    fileNum = problemNames.indexOf(fileName);
    File tempSelectedFile = (File) problemFiles.get(fileNum);
    selectedFile = new File(pathName + "\\" + tempSelectedFile.getName());
    setVisible(false);
  }

  /**
   * DOCUMENT ME!
   */
  private void printButtonActionPerformed() {
    try {
      String[] keys = Constants.PREFERENCES.keys();
      DateFormat df = DateFormat.getInstance();
      if (keys.length == 0) {
        JOptionPane.showMessageDialog(null,
            "There are no completed ICEs to print.");
        return;
      }
      String spaces = "                                                    ";
      String heading = "Exercise Name";
      String spacer = spaces.substring(0, spaces.length() - heading.length());
      StringBuffer buffer = new StringBuffer(heading + spacer
          + "Date First Completed\r\n");
      for (int i = 0; i < keys.length; i++) {
        if (keys[i].startsWith("ice")) {
          String keyname = keys[i].substring(3);
          int space = ((keyname.length() >= spaces.length()) ? 0 : (spaces
              .length() - keyname.length()));
          spacer = spaces.substring(0, space);
          buffer.append("\r\n" + keyname + spacer
              + df.format(new Date(Constants.PREFERENCES.getLong(keys[i], 0))));
        }
      }
      String username = System.getProperty("user.name", "Unknown");
      String result = buffer.toString();
      PrintText.printText("Completed Exercise Report for " + username
          + ", Printed at " + df.format(new Date(System.currentTimeMillis())),
          result, false);
    } catch (BackingStoreException e) {
      e.printStackTrace();
    } catch (NullPointerException ne) {
      ne.printStackTrace();
    } catch (ArrayIndexOutOfBoundsException aioe) {
      aioe.printStackTrace();
    }
  }

  /*
   * The following was taken from a Swing Tutorial at
   * http://www.apl.jhu.edu/~hall/java/Swing-Tutorial/Swing-Tutorial-JTree.html
   */
  /**
   * Small routine that will make node out of the first entry in the array,
   * then make nodes out of subsequent entries and make them child nodes of the
   * first one. The process is repeated recursively for entries that are
   * arrays.
   * 
   * @param hierarchy
   *            DOCUMENT ME!
   * @return DOCUMENT ME!
   */
  private DefaultMutableTreeNode processHierarchy(Object[] hierarchy) {
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(hierarchy[0]);
    DefaultMutableTreeNode child;
    for (int i = 1; i < hierarchy.length; i++) {
      Object nodeSpecifier = hierarchy[i];
      if (nodeSpecifier instanceof Object[]) // node with children
      {
        child = processHierarchy((Object[]) nodeSpecifier);
      } else {
        child = new DefaultMutableTreeNode(nodeSpecifier); // Ie Leaf
      }
      node.add(child);
    }
    return (node);
  }

  private void resetProblemSetCache() {
    FileManager.deleteAll(LOCAL_DIR);
    updateButtonActionPerformed();
  }

  private void treeItemChanged(javax.swing.event.TreeSelectionEvent evt) {
    if (problemNames.indexOf(((JTree) evt.getSource())
        .getLastSelectedPathComponent().toString()) != -1) {
      openButton.setEnabled(true);
    } else {
      openButton.setEnabled(false);
    }
  }

  /**
   * DOCUMENT ME!
   */
  private void updateButtonActionPerformed() {
    updateButton.setEnabled(false);
    copyFromNetwork(OVERWRITE);
    populateLocal();
    repaint();
    updateButton.setEnabled(true);
  }

  class ExerciseFilter implements FilenameFilter {
    public boolean accept(File f, String s) {
      String extension = getExtension(s);
      return (extension.equalsIgnoreCase("ice") || f.isDirectory());
    }

    /**
     * Gets the extension from the filename.
     */
    private String getExtension(String s) {
      int length = s.length();
      int i = s.lastIndexOf('.');
      if ((i > 0) && (i < (length - 1))) {
        return s.substring(i + 1).toLowerCase();
      }
      return "";
    }
  }
}
