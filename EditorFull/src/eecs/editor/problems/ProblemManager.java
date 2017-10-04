package eecs.editor.problems;

import eecs.editor.environment.Environment;
import eecs.util.InterfaceCheck;
import eecs.util.PrintText;
import eecs.util.FileManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class ProblemManager extends javax.swing.JDialog {
  private static final String EDITOR_URL = Environment.getUserPref("java.webstart.editor.url") + "/default.html";
  private static final String ICES_URL = Environment.getUserPref("java.webstart.editor.url") + "/ices/";
  private boolean isNetworkAvailable = false;
  private static final Dimension BUTTON_SIZE = new java.awt.Dimension(120, 24);
  static final String LOCAL_DIR = Environment.getUsersHomeDirectory() + "/.it105editor/ices/";
  static final boolean OVERWRITE = true;
  static final boolean NEW_ONLY = false;
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
  File[] fileList;
  String[] problemNameList;
  private JButton cancelButton;
  private JButton clearCacheButton;
  private JButton openButton;
  private JButton printCompletedButton;
  private JButton updateButton;
  private JLabel localLabel;
  private JList completedList;
  private JList localList;
  private JPanel actionPanel;
  private JScrollPane completedScroller;
  private JScrollPane localScroller;
  JTree localTree;
  List problemNames;
  private Vector problemFiles;

  public ProblemManager(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    Constants.PREFERENCES.addPreferenceChangeListener(new PreferenceChangeListener() {
      public void preferenceChange(PreferenceChangeEvent pe) {
        populateCompleted();
      }
    });
    openButton.setEnabled(false);
    InterfaceCheck ic = new InterfaceCheck();
    isNetworkAvailable = ic.isURLAvailable(EDITOR_URL);

    setupLocalDirectories();
System.out.println("Is network available:" + isNetworkAvailable);    
System.out.println(ICES_URL);    
System.out.println(LOCAL_DIR);
    populateLocal();
    populateCompleted();
  }

  public void setupLocalDirectories() {
    File dir = new File(LOCAL_DIR);
    if (!dir.exists()) {
      dir.mkdirs();
    }
  }

  protected void populateLocal() {
    File dir = new File(LOCAL_DIR);
    problemFiles = new Vector();
    problemNames = new ArrayList();
    Object[] localList = getLocalProblemList(dir, "Problem Sets");

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

  private Object[] getLocalProblemList(File directory, String title) {
    Object[] problemList = null;
    List set = new ArrayList();
    set.add(title);
    FilenameFilter filter = new JavaProblemFilenameFilter();
    File[] fileList = directory.listFiles(filter);
    if (fileList != null) {
      // Process directories first, so they show at the top.
      for (int i = 0; i < fileList.length; i++) {
        if (fileList[i].isDirectory()) {
          String defaultValue = "x80000";
          if (!(System.getProperty("user.name", defaultValue).startsWith("x") && fileList[i].getName().equalsIgnoreCase("grading"))) {
            Object[] subList = getLocalProblemList(fileList[i], fileList[i].getName());
            if (subList.length > 1) {
              set.add(subList);
            }
          }
        }
      }
      for (int i = 0; i < fileList.length; i++) {
        if (fileList[i].isFile() && fileList[i].getName().toLowerCase().endsWith(".ice")) {
          try {
            Problem p = new Problem();
            if (p.readProblem(fileList[i])) {
              set.add(p.getTitle());
              problemFiles.add(fileList[i]);
              problemNames.add(p.getTitle());
            }
          }
          catch (NullPointerException e) {
            System.err.println("Error in problem " + fileList[i] + " so it was deleted.");
            fileList[i].delete();
            e.printStackTrace();
          }
        }
      }
    }
    problemList = set.toArray();
    return problemList;
  }

  public void populateCompleted() {
    String[] keys = null;
    try {
      keys = Constants.PREFERENCES.keys();
      DateFormat df = DateFormat.getInstance();
      Vector entries = new Vector();
      for (int i = 0; i < keys.length; i++) {
        if (keys[i].startsWith("ice")) {
          String keyname = keys[i].substring(3);
          String spaces = "                                         ";
          int space = ((keyname.length() >= spaces.length()) ? 0 : (spaces.length() - keyname.length()));
          String spacer = spaces.substring(0, space);
          entries.add(keyname + spacer + df.format(new Date(Constants.PREFERENCES.getLong(keys[i], 0))));
        }
      }
      completedList.setListData(entries);
    }
    catch (BackingStoreException e) {
      e.printStackTrace();
    }
    catch (Exception ae) {
      System.err.println("Couldn't populate, based on \r\n" + keys.toString() + ", message:\r\n" + ae.toString());
    }
  }

  public File getSelectedFile() {
    return selectedFile;
  }

  public void setVisible(boolean visible) {
    if (isNetworkAvailable) {
      updateButton.setEnabled(true);
      clearCacheButton.setEnabled(true);
    }
    else {
      updateButton.setEnabled(false);
      clearCacheButton.setEnabled(false);
    }
    super.setVisible(visible);
  }

  public void copyFromNetwork(boolean beOverwriten) {
    if (!isNetworkAvailable) {
      return;
    }
    List fileList = getRemoteFileList();
    Iterator iter = fileList.iterator();
    while (iter.hasNext()) {
      String remoteName = (String) iter.next();
      String remoteFile = ICES_URL + remoteName;
      String localFile = LOCAL_DIR + remoteName;
      File file = new File(localFile);
      int lastSlash = remoteName.lastIndexOf("/");
      if (lastSlash > 0) {
        String newDir = LOCAL_DIR + remoteName.substring(0, lastSlash);
        (new File(newDir)).mkdirs();
      }
      if (file.exists() && !beOverwriten) {
        continue;
      }
      if (!FileManager.copy(remoteFile, localFile)) {
        System.err.println("Couldn't write Problem Set file " + localFile);
      }
    }
  }

  private List getRemoteFileList() {
    List fileList = new ArrayList();
    String remoteDir = ICES_URL + "filelist.txt";
    URL url = null;
    try {
      url = new URL(remoteDir);
    }
    catch (MalformedURLException mue) {
      System.err.println("Invalid URL formed from " + remoteDir + ", message:\r\n" + mue.toString());
      return fileList;
    }
    try {
      // Get an input stream for reading
      InputStream in = url.openStream();
      BufferedInputStream bufIn = new BufferedInputStream(in);
      StringBuffer current = new StringBuffer();
      int count = 0;
      // Repeat until end of file
      for (;;) {
        int data = bufIn.read();
        // Check for EOF
        if (data == -1) {
          break;
        }
        else
          if (data > 13) {
            current.append((char) data);
          }
          else {
            if (data == 13) {
              fileList.add(current.toString().replace('\\', '/'));
              current = new StringBuffer();
              count++;
            }
          }
      }
      bufIn.close();
      in.close();
    }
    catch (IOException ioe) {
      // 
    }
    return fileList;
  }

  protected String getDescription(String filename) {
    String path = ICES_URL;
    File dir = new File(path, filename);
    if (dir.exists()) {
      Problem p = new Problem();
      p.readProblem(dir);
      System.out.println(p.getTitle());
      return p.getTitle();
    }
    return null;
  }

  void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
    selectedFile = null;
    setVisible(false);
    dispose();
  }

  void closeDialog(java.awt.event.WindowEvent evt) {
    setVisible(false);
    dispose();
  }

  void listItemChanged(javax.swing.event.ListSelectionEvent evt) {
    if (localList.getSelectedIndex() != -1) {
      openButton.setEnabled(true);
    }
    else {
      openButton.setEnabled(false);
    }
  }

  void openButtonActionPerformed() {
    String fileName = localTree.getLastSelectedPathComponent().toString();
    int fileNum = 0;
    fileNum = problemNames.indexOf(fileName);
    selectedFile = (File) problemFiles.get(fileNum);
    setVisible(false);
  }

  void printButtonActionPerformed() {
    try {
      String[] keys = Constants.PREFERENCES.keys();
      DateFormat df = DateFormat.getInstance();
      if (keys.length == 0) {
        JOptionPane.showMessageDialog(null, "There are no completed ICEs to print.");
        return;
      }
      String spaces = "                                                    ";
      String heading = "Exercise Name";
      String spacer = spaces.substring(0, spaces.length() - heading.length());
      StringBuffer buffer = new StringBuffer(heading + spacer + "Date First Completed\r\n");
      for (int i = 0; i < keys.length; i++) {
        if (keys[i].startsWith("ice")) {
          String keyname = keys[i].substring(3);
          int space = ((keyname.length() >= spaces.length()) ? 0 : (spaces.length() - keyname.length()));
          spacer = spaces.substring(0, space);
          buffer.append("\r\n" + keyname + spacer + df.format(new Date(Constants.PREFERENCES.getLong(keys[i], 0))));
        }
      }
      String username = System.getProperty("user.name", "Unknown");
      String result = buffer.toString();
      PrintText.printText("Completed Exercise Report for " + username + ", Printed at " + df.format(new Date(System.currentTimeMillis())),
          result, false);
    }
    catch (BackingStoreException e) {
      e.printStackTrace();
    }
    catch (NullPointerException ne) {
      ne.printStackTrace();
    }
    catch (ArrayIndexOutOfBoundsException aioe) {
      aioe.printStackTrace();
    }
  }

  // The following was taken from a Swing Tutorial at http://www.apl.jhu.edu/~hall/java/Swing-Tutorial/Swing-Tutorial-JTree.html Small
  // routine that will make node out of the first entry in the array, then make nodes out of subsequent entries and make them child nodes of
  // the first one. The process is repeated recursively for entries that are arrays.
  private DefaultMutableTreeNode processHierarchy(Object[] hierarchy) {
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(hierarchy[0]);
    DefaultMutableTreeNode child;
    for (int i = 1; i < hierarchy.length; i++) {
      Object nodeSpecifier = hierarchy[i];
      if (nodeSpecifier instanceof Object[]) // node with children
      {
        child = processHierarchy((Object[]) nodeSpecifier);
      }
      else {
        child = new DefaultMutableTreeNode(nodeSpecifier); // Ie Leaf
      }
      node.add(child);
    }
    return (node);
  }

  void resetProblemSetCache() {
    FileManager.deleteAll(LOCAL_DIR);
    updateButtonActionPerformed();
  }

  void treeItemChanged(javax.swing.event.TreeSelectionEvent evt) {
    if (problemNames.indexOf(((JTree) evt.getSource()).getLastSelectedPathComponent().toString()) != -1) {
      openButton.setEnabled(true);
    }
    else {
      openButton.setEnabled(false);
    }
  }

  void updateButtonActionPerformed() {
    updateButton.setEnabled(false);
    copyFromNetwork(OVERWRITE);
    populateLocal();
    repaint();
    updateButton.setEnabled(true);
  }

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
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
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
    localScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    localScroller.setMinimumSize(new java.awt.Dimension(170, 220));
    localScroller.setPreferredSize(new java.awt.Dimension(250, 520));
    localList.setBorder(new javax.swing.border.EtchedBorder());
    localList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    localList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        listItemChanged(evt);
      }
    });
    localScroller.setViewportView(localTree);
    centerPanel.add(localScroller);
    completedList = new JList();
    completedScroller = new JScrollPane(completedList);
    completedScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    completedScroller.setMinimumSize(new java.awt.Dimension(170, 220));
    completedScroller.setPreferredSize(new java.awt.Dimension(440, 420));
    completedList.setBorder(new javax.swing.border.EtchedBorder());
    completedList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
    if (!isNetworkAvailable) {
      updateButton.setEnabled(false);
    }
    clearCacheButton.setText("Reset List");
    clearCacheButton.setToolTipText("Synchronize and remove outdated exercises");
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
    printPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
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
}