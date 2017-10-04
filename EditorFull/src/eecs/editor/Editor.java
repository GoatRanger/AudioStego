package eecs.editor;

import eecs.util.EMail;
import eecs.util.InterfaceCheck;
import eecs.util.LookUpTable;
import eecs.util.PrintText;
import eecs.util.html.PatchedHTMLEditorKit;
import eecs.util.ProcessWrapper;
import eecs.editor.actions.CopyAction;
import eecs.editor.actions.CutAction;
import eecs.editor.actions.PasteAction;
import eecs.editor.actions.ProblemAction;
import eecs.editor.actions.RunJavaTestAction;
import eecs.editor.environment.Environment;
import eecs.editor.jedit.JEditTextArea;
import eecs.editor.language.LanguageKit;
import eecs.editor.language.LanguageKitFactory;
import eecs.editor.problems.Problem;
import eecs.editor.util.Constants;
import eecs.util.FileManager;
import eecs.editor.util.MRUManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.prefs.Preferences;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
import org.apache.log4j.PropertyConfigurator;

public class Editor {
  public static final int CONSOLE_TAB = 1;
  private static final int MRU_CAPACITY = 5;
  public static final String LANGUAGE_KEY = Messages.getString("Editor.key.default.language");
  public static final String DEFAULT_LANGUAGE = LanguageKit.JAVA;
  public static final String AUTOSAVE_TIME_KEY = Messages.getString("Editor.key.autosave.time");
  public static final int DEFAULT_AUTOSAVE = 10;
  public static final int DEFAULT_TEXTSIZE = 12;
  public static final String TEXT_SIZE_KEY = Messages.getString("Editor.key.text.size");
  public static final String TAB_SIZE_KEY = Messages.getString("Editor.key.tab.size");
  public static final int DEFAULT_TABSIZE = 2;
  public static final String STYLE_KEY = Messages.getString("Editor.key.style");
  public static final String DEFAULT_STYLE = "Java";
  public static final String HANDBOOK_URL_KEY = Messages.getString("Editor.key.handbook.url");
  public static final String DEFAULT_HANDBOOKURL = Messages.getString("Editor.handbook.url");
  protected transient RedoAction redoAction;
  protected transient UndoAction undoAction;
  protected transient AbstractAction closeAction;
  protected transient AbstractAction compileAction;
  protected transient AbstractAction copyAction;
  protected transient AbstractAction cutAction;
  protected transient AbstractAction findAction;
  protected transient AbstractAction gotoAction;
  protected transient AbstractAction haltAction;
  protected transient AbstractAction newAction;
  protected transient AbstractAction openAction;
  protected transient AbstractAction pasteAction;
  protected transient AbstractAction printAction;
  protected transient AbstractAction problemAction;
  protected transient AbstractAction formatAction;
  protected transient AbstractAction replaceAction;
  protected transient AbstractAction runAction;
  protected transient AbstractAction saveAction;
  protected transient AbstractAction saveAsAction;
  private ActionListener findListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      String action = e.getActionCommand();
      String pattern = replaceDialog.getFindText();
      boolean caseSensitive = replaceDialog.isCaseSensitive();
      boolean matchWords = replaceDialog.matchWholeWord();
      if (action.equals(ReplaceDialog.FIND_ACTION)) {
        find(pattern, caseSensitive, matchWords);
      }
      else
        if (action.equals(ReplaceDialog.CLOSE_ACTION)) {
          replaceDialog.setVisible(false);
        }
        else
          if (action.equals(ReplaceDialog.REPLACE_ACTION)) {
            String content = replaceDialog.getReplaceText();
            replace(pattern, content, caseSensitive, matchWords, false);
          }
          else
            if (action.equals(ReplaceDialog.REPLACE_ALL_ACTION)) {
              String content = replaceDialog.getReplaceText();
              replace(pattern, content, caseSensitive, matchWords, true);
            }
    }
  };
  private ActionListener myActionListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      String action = e.getActionCommand();
      if (action.equals("Exit")) {
        exitAction();
      }
      if (action.equals("Select All")) {
        selectAllAction();
      }
      if (action.equals("Editor.Preview")) {
        previewAction();
      }
      if (action.equals("Options...")) {
        propertiesAction();
      }
      if (action.equals("Reformat Settings...")) {
        formatSettingsAction();
      }
      if (action.equals("Run Tests")) {
        new RunJavaTestAction().runJavaTestAction();
      }
      if (action.equals("Editor Help")) {
        helpAction();
      }
      if (action.equals("About Editor")) {
        aboutAction();
      }
    }
  };
  private PropertyChangeListener myChangeListener = new MyChangeListener();
  Clipboard clip;
  javax.swing.filechooser.FileFilter cssFilter = new GenericFileFilter(new String[] { "css" }, "Style Sheets (*.css)");
  javax.swing.filechooser.FileFilter htmlFilter = new GenericFileFilter(new String[] { "htm", "html", "shtml" },
      "HTML (*.htm, *.html, *.shtml)");
  javax.swing.filechooser.FileFilter javaFilter = new GenericFileFilter(new String[] { "java" }, "Java programs (*.java)");
  javax.swing.filechooser.FileFilter problemFilter = new GenericFileFilter(new String[] { "ice" }, "Exercises (*.ice)");
  private Font commonFont = new Font("monospaced", Font.PLAIN, 12);
  JButton undoButton;
  JButton redoButton;
  JFileChooser chooser = null;
  JFrame mainWindow;
  JLabel statusArea;
  private JMenu newMenu;
  private JMenuBar menuBar;
  private JMenuItem[] mruMenuItem;
  JMenuItem newCSSMenuItem;
  JMenuItem newHTMLMenuItem;
  JMenuItem newJavaMenuItem;
  private JMenuItem reformatSettingsMenuItem;
  JMenuItem testMenuItem;
  private JPanel consolePane;
  JPopupMenu editingPopup;
  JProgressBar progressBar;
  private JSeparator mruSeparator;
  private JSplitPane splitPane;
  JTabbedPane messageTabs;
  private JTextArea descriptionArea;
  JComponent majorFocus;
  JTextPane console;
  private JToolBar toolBar;
  public Preferences prefs = Preferences.userRoot().node(Messages.getString("Editor.preferences.node"));
  private MouseInputAdapter mouseAction = new MouseInputAdapter() {
    public void mouseClicked(MouseEvent event) {
      if (event.getClickCount() != 2) {
        return;
      }
      super.mouseClicked(event);
      Caret caret = console.getCaret();
      int last = caret.getMark();
      int found = caret.getDot();
      String text = null;
      try {
        System.err.println(console.getText(last, found - last));
        text = console.getText(last, found - (last));
      }
      catch (BadLocationException e) {
        //
      }
      try {
        int line = Integer.parseInt(text.trim());
        gotoLine(line);
      }
      catch (Exception e) {
        //
      }
    }
  };
  Point mouseLocation = new Point(0, 0);
  volatile Problem currentProblem = null;
  volatile ProcessWrapper process;
  private PropertyDialog propertiesDialog;
  ReplaceDialog replaceDialog;
  private String description = "<no description yet>";
  volatile Timer timer;
  UndoManager undoManager = new UndoManager();
  private int newFileNum = 0;
  private int timeForAutoSave = 5;
  private MRUManager mruManager;
  private Timer clipboardTimer;
  private ActionMap actions;
  private HelpBroker helpBroker;
  private HelpSet helpSet;

  public static void main(String[] args) {
    new Editor();
  }

  public Editor() {
    new SplashWindow3(Messages.getString("Editor.splash.image"), null, 7000);
    LookUpTable.getInstance().put("editor", this);
    PropertyConfigurator.configure(Editor.class.getResource("log4j.properties"));
    InterfaceCheck nic = new InterfaceCheck();
    String editorURL = Environment.getUserPref("java.webstart.editor.url");
    Constants.networkAvailable = nic.isURLAvailable(editorURL);
    if (Constants.networkAvailable) {
      copyXHTMLStrictDTDToUsersConfigDir();
    }
    Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
    initActions();
    mruManager = new MRUManager(MRU_CAPACITY);
    initializeFileArea();

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    catch (InstantiationException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

    initializeGUI();
    checkForAutoSaveExists();
    initializeFileChooser();
    getClipboard();
    clipboardTimer = new Timer(500, new ClipboardListener());
    clipboardTimer.setInitialDelay(500);
    clipboardTimer.start();
    splitPane.setDividerLocation(0.85);
    mainWindow.validate();
    mainWindow.setVisible(true);
    mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
    forceEnvironmentClassToLoad();
  }

  protected void forceEnvironmentClassToLoad() {
    try {
      Class.forName("eecs.editor.environment.Environment");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public JProgressBar getProgressBar() {
    return progressBar;
  }
  
  public JTextPane getConsole() {
    return console;
  }
  
  public JLabel getStatusArea() {
    return statusArea;
  }

  public ProcessWrapper getProcess() {
    return process;
  }

  public AbstractAction getRunAction() {
    return runAction;
  }
  
  public Problem getCurrentProblem() {
    return currentProblem;
  }

  public void setCurrentProblem(Problem problem) {
    currentProblem = problem;
  }

  public JFrame getMainWindow() {
    return mainWindow;
  }

  public JTextArea getDescriptionArea() {
    return descriptionArea;
  }

  public JTabbedPane getMessageTabs() {
    return messageTabs;
  }

  public JMenuItem getTestMenuItem() {
    return testMenuItem;
  }

  private void getClipboard() {
    try {
      clip = mainWindow.getToolkit().getSystemClipboard();
      if (clip.getContents(this) != null) {
        pasteAction.setEnabled(true);
      }
    }
    catch (IllegalStateException e) {
      pasteAction.setEnabled(false);
    }
  }

  private void copyXHTMLStrictDTDToUsersConfigDir() {
    String remotePath = Environment.getUserPref("java.webstart.editor.url");
    if (remotePath.equals("")) {
      return;
    }
    String localPath = System.getProperty("java.ext.dirs");
    boolean overwrite = false;

    System.out.println("Copying XHTML DTD From:" + remotePath);
    System.out.println("Copying XHTML DTD To:" + localPath);
    FileManager.copyFileFromNetwork(remotePath, localPath, "/xhtml1-strict.dtd", overwrite);
    FileManager.copyFileFromNetwork(remotePath, localPath, "/xhtml-lat1.ent", overwrite);
    FileManager.copyFileFromNetwork(remotePath, localPath, "/xhtml-special.ent", overwrite);
    FileManager.copyFileFromNetwork(remotePath, localPath, "/xhtml-symbol.ent", overwrite);
  }

  private void initActions() {
    compileAction = new CompileAction("Compile", createToolBarIcon("eecs/editor/images/Compile24.gif"), "Compile/Check for Errors",
        new Integer(KeyEvent.VK_C), KeyStroke.getKeyStroke("F9"));
    compileAction.setEnabled(false);
    closeAction = new CloseAction("Close", null, "Close", new Integer(KeyEvent.VK_C), KeyStroke.getKeyStroke("ctrl F4"));
    closeAction.setEnabled(false);
    findAction = new FindAction("Find...", createToolBarIcon("eecs/editor/images/Replace24.gif"), "Find", new Integer(KeyEvent.VK_F),
        KeyStroke.getKeyStroke("ctrl F"));
    findAction.setEnabled(false);
    gotoAction = new GotoAction("Go to Line...", null, "Go to a specific line number", new Integer(KeyEvent.VK_G), KeyStroke
        .getKeyStroke("ctrl L"));
    gotoAction.setEnabled(false);
    haltAction = new HaltAction("Stop", createToolBarIcon("eecs/editor/images/Stop24.gif"), "Stop a Running Program", new Integer(
        KeyEvent.VK_H), KeyStroke.getKeyStroke("F12"));
    haltAction.setEnabled(false);
    newAction = new NewAction("New", null, "New File", new Integer(KeyEvent.VK_N), null);
    openAction = new OpenAction("Open", createToolBarIcon("eecs/editor/images/Open24.gif"), "Open File", new Integer(KeyEvent.VK_O),
        KeyStroke.getKeyStroke("ctrl O"));
    formatAction = new FormatAction("Format", null, "Reformat", new Integer(KeyEvent.VK_F), KeyStroke.getKeyStroke("ctrl shift F"));
    formatAction.setEnabled(false);
    replaceAction = new ReplaceAction("Replace...", null, "Replace", new Integer(KeyEvent.VK_R), KeyStroke.getKeyStroke("F3"));
    replaceAction.setEnabled(false);
    cutAction = new CutAction("Cut", createToolBarIcon("eecs/editor/images/Cut24.gif"), "Cut", new Integer(KeyEvent.VK_T), KeyStroke
        .getKeyStroke("ctrl X"));
    copyAction = new CopyAction("Copy", createToolBarIcon("eecs/editor/images/Copy24.gif"), "Copy", new Integer(KeyEvent.VK_C), KeyStroke
        .getKeyStroke("ctrl C"));
    pasteAction = new PasteAction("Paste", createToolBarIcon("eecs/editor/images/Paste24.gif"), "Paste", new Integer(KeyEvent.VK_P),
        KeyStroke.getKeyStroke("ctrl V"));
    printAction = new PrintAction("Print...", createToolBarIcon("eecs/editor/images/Print24.gif"), "Print", new Integer(KeyEvent.VK_P),
        KeyStroke.getKeyStroke("ctrl P"));
    printAction.setEnabled(false);
    problemAction = new ProblemAction("Problem Set Manager", null, "Open the Problem Set Manager", new Integer(KeyEvent.VK_M), KeyStroke
        .getKeyStroke("ctrl M"));
    saveAction = new SaveAction("Save", createToolBarIcon("eecs/editor/images/Save24.gif"), "Save", new Integer(KeyEvent.VK_S), KeyStroke
        .getKeyStroke("ctrl S"));
    saveAction.setEnabled(false);
    saveAsAction = new SaveAsAction("Save As...", createToolBarIcon("eecs/editor/images/SaveAs24.gif"), "Save As", new Integer(
        KeyEvent.VK_A), null);
    saveAsAction.setEnabled(false);
    runAction = new RunAction("Run/View", createToolBarIcon("eecs/editor/images/run.gif"), "Run/View", new Integer(KeyEvent.VK_R),
        KeyStroke.getKeyStroke("F10"));
    runAction.setEnabled(false);
    actions = createActionMap();
  }

  void setFileFilter(String fileExt) {
    if (fileExt.equals(".htm")) {
      chooser.setFileFilter(htmlFilter);
    }
    else
      if (fileExt.equals(".java")) {
        chooser.setFileFilter(javaFilter);
      }
      else
        if (fileExt.equals(".css")) {
          chooser.setFileFilter(cssFilter);
        }
        else {
          chooser.setAcceptAllFileFilterUsed(true);
        }
  }

  public void updateStatusBar(String msg) {
    statusArea.setText(" " + msg);
  }

  private JEditTextArea getActiveProgramArea() {
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    return ((EditPanel) programTabs.getSelectedComponent()).getProgramArea();
  }

  private String getFileType(String filename) {
    String type = LanguageKit.TEXT;
    int dot = filename.lastIndexOf(".") + 1;
    if (dot > 0) {
      String ext = filename.substring(dot).toLowerCase();
      if (ext.equals("java")) {
        type = LanguageKit.JAVA;
      }
      else
        if (ext.equals("css")) {
          type = LanguageKit.CSS;
        }
        else
          if (ext.equals("htm") || ext.equals("html") || ext.equals("shtml")) {
            type = LanguageKit.HTML;
          }
    }
    return type;
  }

  private void setSpecialKeys(JComponent component) {
    InputMap imap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap amap = component.getActionMap();
    imap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_DOWN_MASK), "Editor.Preview");
    amap.put("Editor.Preview", new AbstractAction() {
      public void actionPerformed(ActionEvent e) {
        previewAction();
      }
    });
  }

  void aboutAction() {
    updateStatusBar(Messages.getString("Editor.editor.version.string"));
  }
  
  private void addFileMRU(JMenu menu) {
    mruMenuItem = new JMenuItem[MRU_CAPACITY];
    int total = 0;
    String[] list = new String[mruMenuItem.length];
    while (total < mruMenuItem.length) {
      mruMenuItem[total] = new JMenuItem();
      String item = prefs.get("mru" + total, "empty");
      list[total] = item;
      menu.add(mruMenuItem[total]);
      total++;
    }
    for (int idx = list.length - 1; idx >= 0; idx--) {
      mruManager.add(list[idx]);
    }
    mruSeparator = new JSeparator();
    mruSeparator.setVisible(false);
    menu.add(mruSeparator);
    updateMRU();
  }

  private void addMRUMenuItem(String name) {
    mruManager.add(name);
    updateMRU();
  }

  public void addProgramTab(String name, LanguageKit type) {
    EditPanel programPane = new EditPanel(type);
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    programTabs.addTab(name, programPane);
    programTabs.setSelectedComponent(programPane);
    setSpecialKeys(programPane.getProgramArea());
    programPane.setFileChooser(chooser);
    programPane.addUndoableEditListener(new MyUndoableEditListener());
    programPane.addPropertyChangeListener(myChangeListener);
    programPane.addMouseListener(new Editor.PopupListener());
    programPane.addPopup(editingPopup);
    programPane.getProgramArea().addFocusListener(new MinorFocusListener(programPane.getProgramArea()));
    programPane.addFocusListener(new MajorFocusListener(programPane));
    programPane.addFocusListener(new MinorFocusListener(programPane.getProgramArea()));
    setPanelActions(programPane);
    saveAction.setEnabled(false);
  }

  public void beep() {
    Toolkit.getDefaultToolkit().beep();
  }

  private boolean canCheckForErrors() {
    boolean ok = true;
    EditPanel currentPanel = getCurrentProgramTab();
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    int tab = programTabs.getSelectedIndex();
    String action = "Current file couldn't be checked for errors. Either you've never saved it, or you made changes since the last time it was saved.";
    LanguageKit kit = currentPanel.getLanguageKit();
    if (kit.getDescription().equals("Java Program")) {
      action = "The current program couldn't be compiled. Either you've never saved it, or you've edited it since the last time it was saved.";
    }
    if (kit.hasCompiler()) {
      if (!currentPanel.saveIfNecessary("compile/check for errors")) {
        console.setText(action);
        if (currentPanel.getSaveFile() != null) {
          messageTabs.setTitleAt(CONSOLE_TAB, "Console - " + currentPanel.getSaveFile().getName());
        }
        else {
          messageTabs.setTitleAt(CONSOLE_TAB, "Console - New File");
        }
        messageTabs.setSelectedIndex(CONSOLE_TAB);
        beep();
        ok = false;
      }
      else {
        programTabs.setTitleAt(tab, currentPanel.getSaveFile().getName());
      }
    }
    return ok;
  }

  // Checks for autosave files, and loads them if requested.
  private void checkForAutoSaveExists() {
    String home = Environment.getUsersConfigPath();
    File dir = new File(home);
    File[] fileList = dir.listFiles(new FilenameFilter() {
      public boolean accept(File f, String s) {
        String extension = getExtension(s);
        boolean match = s.startsWith("itedit") && extension.equalsIgnoreCase("sav");
        return match;
      }

      private String getExtension(String s) {
        int length = s.length();
        int i = s.lastIndexOf('.');
        if ((i > 0) && (i < (length - 1))) {
          return s.substring(i + 1).toLowerCase();
        }
        return "";
      }
    });
    for (int i = 0; i < fileList.length; i++) {
      File autoSave = fileList[i];
      Object[] options = { "Recover", "Delete" };
      int choice = JOptionPane.showOptionDialog(mainWindow,
          "Autosave file found.\nWould you like to recover it?\nNOTE: if you do not reccover it now, it will be permanently deleted.",
          "Autosave File Found", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
      if (choice == JOptionPane.YES_OPTION) {
        try {
          LanguageKit kit = LanguageKitFactory.getLanguageKit(LanguageKit.TEXT);
          addProgramTab("Autosave", kit);
          openFile(autoSave);
          JOptionPane
              .showMessageDialog(mainWindow,
                  "Autosave file loaded.\nThe type of file could not be determined, so you should\nmanually ensure the correct language is selected.");
        }
        catch (IOException e) {
          System.out.println("Couldn't open autosave file." + e);
        }
      }
      autoSave.delete();
    }
  }

  // Stops the running process. Attempts to stop the run process in a new Thread (since Process.destroy() can block).
  // Once the Thread is started, the Editor's ProcessWrapper is set to null.
  private void clearRunProcess() {
    if (process == null) {
      return;
    }
    new Thread(new Runnable() {
      public synchronized void run() {
        ProcessWrapper p = process;
        p.stop();
      }
    }, "close_run").start();
    process = null;
  }

  void closeAction(boolean allowCancel) {
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    if (programTabs.getTabCount() == 0) {
      return;
    }
    updateStatusBar("Close");
    if (getCurrentProgramTab().close()) {
      programTabs.remove(programTabs.getSelectedComponent());
    }
    if (programTabs.getTabCount() == 0) {
      filesOpen(false);
    }
  }

  synchronized void compileAction() {
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    programTabs.setEnabled(false);
    final EditPanel tab = getCurrentProgramTab();
    final LanguageKit language = tab.getLanguageKit();
    if (language.hasCompiler() == false) {
      compileAction.setEnabled(false);
      programTabs.setEnabled(true);
      return;
    }
    if (canCheckForErrors()) {
      tab.setCompiled(false);
      // the Tab fires a message to enable compiling, so need to shut it off
      compileAction.setEnabled(false);
      updateStatusBar("Validate Code/Compile");
      mainWindow.getContentPane().remove(statusArea);
      progressBar.setMinimum(0);
      progressBar.setMaximum(100);
      progressBar.setString("Validating / Compiling");
      progressBar.setStringPainted(true);
      progressBar.setIndeterminate(false);
      mainWindow.getContentPane().add(progressBar, BorderLayout.SOUTH);
      mainWindow.validate();
      int lastDivider = splitPane.getLastDividerLocation();
      // If divider is in maximized location, move up to display the console
      if (splitPane.getDividerLocation() >= splitPane.getMaximumDividerLocation()) {
        splitPane.setDividerLocation(lastDivider);
      }
      messageTabs.setSelectedIndex(CONSOLE_TAB);
      console.setText("");
      printLineToConsole("--- RUNNING CHECKS ---");
      //Create a timer to control the update of the progressbar
      timer = new javax.swing.Timer(80, new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          progressBar.setValue(language.getCompilerPercentComplete());
          if (language.getCompilerPercentComplete() == 100) {
            Toolkit.getDefaultToolkit().beep();
            timer.stop();
            int counter = 0;
            while (!language.isCompilerDone() && counter < 50) {
              try {
                Thread.sleep(100);
                counter++;
              }
              catch (InterruptedException ie) {
                //
              }
            }
            String compilerMessages = language.getCompilerMessages();
            if (compilerMessages == null) {
              messageTabs.setTitleAt(CONSOLE_TAB, tab.getSaveFile().getName());
              if (language.getCompileResult() == LanguageKit.COMPILE_SUCCESS) {
                console.setText("Compile Successful.");
              }
              else {
                console.setText("There were errors, but unfortunately I can't tell you where (or what they were).  \r\n"
                    + "You might try again, and if you continue to receive this message, restart the editor and retry.  "
                    + "If you continue to receive this message, please notify your instructor "
                    + "(and send him/her the file that you are currently editing).");
              }
            }
            else {
              messageTabs.setTitleAt(CONSOLE_TAB, tab.getSaveFile().getName());
              console.setText(compilerMessages);
            }
            console.setCaretPosition(0);
            mainWindow.getContentPane().remove(progressBar);
            mainWindow.getContentPane().add(statusArea, BorderLayout.SOUTH);
            statusArea.setText("Validate/Compile Completed");
            tab.setCompiled(language.getCompileResult() == LanguageKit.COMPILE_SUCCESS);
            JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
            programTabs.setEnabled(true);
            mainWindow.validate();
          }
        }
      });
      language.compile(tab.getSaveFile());
      timer.start();
    }
    else {
      compileAction.setEnabled(language.hasCompiler());
      programTabs.setEnabled(true);
    }
  }

  private void createConsole() {
    console = new JTextPane();
    console.setFont(commonFont);
    console.setEditable(false);
    HTMLEditorKit kit = new PatchedHTMLEditorKit();
    HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
    console.setEditorKit(kit);
    console.setContentType("text/html");
    console.setDocument(doc);
    console.setText("<html><body><b>Hello!</b></body></html>");
    console.addFocusListener(new MajorFocusListener(console));
    console.addMouseListener(mouseAction);
    setSpecialKeys(console);
  }

  private void createDescriptionArea() {
    descriptionArea = new JTextArea(20, 80);
    descriptionArea.setFont(commonFont);
    descriptionArea.setEditable(false);
    descriptionArea.setText(description);
    descriptionArea.addFocusListener(new MajorFocusListener(descriptionArea));
    setSpecialKeys(descriptionArea);
  }

  private void createEditMenu() {
    JMenu menu;
    menu = new JMenu("Edit");
    menu.setMnemonic(KeyEvent.VK_E);
    JMenuItem item;
    undoAction = new UndoAction();
    item = menu.add(undoAction);
    redoAction = new RedoAction();
    item = menu.add(redoAction);
    menu.addSeparator();
    item = new JMenuItem(cutAction);
    item.setIcon(null);
    menu.add(item);
    item = new JMenuItem(copyAction);
    item.setIcon(null);
    menu.add(item);
    item = new JMenuItem(pasteAction);
    item.setIcon(null);
    menu.add(item);
    menu.addSeparator();
    menuItem(menu, "Select All", KeyEvent.VK_A, "ctrl A");
    menu.addSeparator();
    item = new JMenuItem(findAction);
    item.setIcon(null);
    menu.add(item);
    item = new JMenuItem(replaceAction);
    item.setIcon(null);
    menu.add(item);
    menu.addSeparator();
    item = new JMenuItem(gotoAction);
    menu.add(item);
    menuBar.add(menu);
  }

  private JMenu createFileMenu() {
    JMenu menu = new JMenu("File");
    menu.setMnemonic(KeyEvent.VK_F);
    newMenu = new JMenu();
    newMenu.setMnemonic(KeyEvent.VK_N);
    newMenu.setText("New");
    newMenu.setIcon(null);
    menu.add(newMenu);
    JMenuItem item;
    newJavaMenuItem = new JMenuItem(newAction);
    newJavaMenuItem.setText("Java Program");
    newJavaMenuItem.setIcon(null);
    newMenu.add(newJavaMenuItem);
    newHTMLMenuItem = new JMenuItem(newAction);
    newHTMLMenuItem.setText("XHTML Page");
    newHTMLMenuItem.setIcon(null);
    newMenu.add(newHTMLMenuItem);
    newCSSMenuItem = new JMenuItem(newAction);
    newCSSMenuItem.setText("Style Sheet");
    newCSSMenuItem.setIcon(null);
    newMenu.add(newCSSMenuItem);
    item = new JMenuItem(openAction);
    item.setIcon(null);
    menu.add(item);
    item = new JMenuItem(problemAction);
    item.setIcon(null);
    menu.add(item);
    menu.addSeparator();
    item = new JMenuItem(saveAction);
    item.setIcon(null);
    menu.add(item);
    item = new JMenuItem(saveAsAction);
    item.setIcon(null);
    menu.add(item);
    menu.addSeparator();
    item = new JMenuItem(printAction);
    item.setIcon(null);
    menu.add(item);
    menu.addSeparator();
    item = new JMenuItem(closeAction);
    item.setIcon(null);
    menu.add(item);
    menu.addSeparator();
    addFileMRU(menu);
    menuItem(menu, "Exit", KeyEvent.VK_X, null);
    return menu;
  }

  private void createHelpMenu() {
    JMenu menu;
    menu = new JMenu("Help");
    menu.setMnemonic(KeyEvent.VK_H);
    menuItem(menu, "Editor Help", KeyEvent.VK_H, "F1");
    menuItem(menu, "About Editor", KeyEvent.VK_A, null);
    menuBar.add(menu);
  }

  private void createHelpSet() {
    ClassLoader loader = Editor.class.getClassLoader();
    URL url;
    String helpSetName = "eecs/editor/help/editor.hs";
    try {
      url = loader.getResource(helpSetName);
      helpSet = new HelpSet(loader, url);
    }
    catch (Exception ee) {
      System.out.println("Trouble in createHelpSet;");
      ee.printStackTrace();
      return;
    }
  }

  private void createHelpWindow() {
    if (helpSet == null) {
      createHelpSet();
      helpBroker = helpSet.createHelpBroker();
    }
  }

  private void createMenu() {
    menuBar = new JMenuBar();
    mainWindow.setJMenuBar(menuBar);
    menuBar.add(createFileMenu());
    createEditMenu();
    createViewMenu();
    createToolsMenu();
    createHelpMenu();
  }

  private void createToolsMenu() {
    JMenu menu;
    menu = new JMenu("Tools");
    menu.setMnemonic(KeyEvent.VK_T);
    JMenuItem item;
    item = new JMenuItem(formatAction);
    item.setIcon(null);
    menu.add(item);
    reformatSettingsMenuItem = menuItem(menu, "Reformat Settings...", KeyEvent.VK_E, null);
    item = new JMenuItem(compileAction);
    item.setIcon(null);
    menu.add(item);
    item = new JMenuItem(runAction);
    item.setIcon(null);
    menu.add(item);
    testMenuItem = menuItem(menu, "Run Tests", KeyEvent.VK_T, null);
    testMenuItem.setEnabled(false);
    item = new JMenuItem(haltAction);
    item.setIcon(null);
    menu.add(item);
    menuBar.add(menu);
  }

  private void createViewMenu() {
    JMenu menu;
    menu = new JMenu("View");
    menu.setMnemonic(KeyEvent.VK_V);
    menuItem(menu, "Options...", KeyEvent.VK_O, null);
    menuBar.add(menu);
  }

  ImageIcon createToolBarIcon(String file) {
    ClassLoader cl = this.getClass().getClassLoader();
    java.net.URL url = cl.getResource(file);
    ImageIcon icon = new ImageIcon(url);
    return icon;
  }

  public EditPanel getCurrentProgramTab() {
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    return (EditPanel) (programTabs.getSelectedComponent());
  }

  void debug(String msg) {
    Document doc = console.getDocument();
    //Load the text pane with styled text.
    try {
      doc.insertString(doc.getLength(), msg + "\n", null);
    }
    catch (BadLocationException ble) {
      System.err.println("Couldn't insert initial text.");
    }
    // make it scroll, if necessary
    int pos = doc.getLength() - 1;
    console.setCaretPosition(pos);
  }

  void exitAction() {
    //CollectErrors.sendErrorsToServlet();
    updateStatusBar("Exit");
    int errors = 0;
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    while ((errors == 0) && (programTabs.getTabCount() > 0)) {
      programTabs.setSelectedIndex(0);
      int exitValue = ((EditPanel) programTabs.getComponent(0)).exit();
      errors += exitValue;
      if (exitValue == 0) {
        programTabs.remove(0);
      }
    }
    String logName = Environment.getUsersConfigPath() + Messages.getString("Editor.log.file.name");
    File logFile = new File(logName);
    String[] logs = { logName };
    // Assume that an empty log will always be less that 200 bytes
    if (logFile.exists() && (logFile.length() > 200)) {
      EMail.sendMsgAttachFile(Messages.getString("Editor.maintainer.email"), "IT105 Editor Error Log", "Error Log Attached", logs);
    }
    if (errors == 0) {
      System.exit(0);
    }
  }

  public void filesOpen(boolean oneOpen) {
    closeAction.setEnabled(oneOpen);
    printAction.setEnabled(oneOpen);
    saveAsAction.setEnabled(oneOpen);
    gotoAction.setEnabled(oneOpen);
    findAction.setEnabled(oneOpen);
    replaceAction.setEnabled(oneOpen);
  }

  void find(String pattern, boolean isCaseSensitive, boolean matchWholeWord) {
    if ((pattern == null) || (pattern.length() == 0)) {
      updateStatusBar("Missing search string");
      beep();
      return;
    }
    getActiveProgramArea().requestFocus();
    if (!getActiveProgramArea().find(pattern, isCaseSensitive, matchWholeWord)) {
      updateStatusBar("Search string not found");
      beep();
    }
  }

  // When the Search button is pressed, execute this action to find the text specified.
  void findAction() {
    if (replaceDialog == null) {
      replaceDialog = new ReplaceDialog(mainWindow, false, findListener);
    }
    replaceDialog.openFindDialog();
  }

  void formatSettingsAction() {
    if (getCurrentProgramTab() != null)
      getCurrentProgramTab().formatSettings();
  }

  void gotoAction() {
    updateStatusBar("Goto");
    int maxLine = getActiveProgramArea().getLineCount();
    String line = JOptionPane.showInputDialog(mainWindow, "Enter line (1-" + maxLine + ")", "Goto", JOptionPane.QUESTION_MESSAGE);
    if ((line == null) || (line.length() == 0)) {
      return;
    }
    int n;
    try {
      n = Integer.parseInt(line);
    }
    catch (Exception e) {
      updateStatusBar("Bad Line Number");
      return;
    }
    gotoLine(n);
  }

  void gotoLine(int line) {
    getCurrentProgramTab().gotoLine(line);
  }

  void haltAction() {
    if (process == null) {
      return;
    }
    process.stop();
    process = null;
    updateStatusBar("Halt Program");
  }

  void helpAction() {
    updateStatusBar("Help");
    helpBroker.setSize(new Dimension(750, 800));
    helpBroker.setDisplayed(true);
  }

  private void initializeFileArea() {
    String home = System.getProperty(Messages.getString("Editor.user.home.property"));
    File dir = new File(home, ".it105editor");
    dir.mkdirs();
  }

  private void initializeFileChooser() {
    new Thread("FileChooser_init") {
      public void run() {
        JFileChooser tempChooser = new JFileChooser();
        File documents = new File(Messages.getString("Editor.default.cadet.document.dir"));
        if (documents.exists()) {
          tempChooser.setCurrentDirectory(documents);
        }
        else {
          documents = new File(System.getProperty(Messages.getString("Editor.user.home.property"))
              + Messages.getString("Editor.alternate.document.dir"));
          if (documents.exists()) {
            tempChooser.setCurrentDirectory(documents);
          }
          else {
            tempChooser.setCurrentDirectory(new File(System.getProperty(Messages.getString("Editor.user.home.property"))));
          }
        }
        tempChooser.addChoosableFileFilter(htmlFilter);
        tempChooser.addChoosableFileFilter(javaFilter);
        tempChooser.addChoosableFileFilter(cssFilter);
        tempChooser.addChoosableFileFilter(problemFilter);
        tempChooser.setFileFilter(javaFilter);
        chooser = tempChooser;
      }
    }.start();
    // Sleep and lock up the GUI until the filechooser is ready
    while (chooser == null) {
      try {
        Thread.sleep(100);
      }
      catch (InterruptedException e) {
        //
      }
    }
    openAction.setEnabled(true);
  }

  private void initializeGUI() {
    mainWindow = new JFrame();
    mainWindow.setResizable(true);
    mainWindow.getContentPane().setLayout(new BorderLayout());
    mainWindow.setTitle("IT105 Editor");
    mainWindow.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exitAction();
      }
    });
    // Now ensure the window listener is the only action executed
    mainWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    createMenu();
    createDescriptionArea();
    createConsole();
    consolePane = new JPanel();
    consolePane.setLayout(new BoxLayout(consolePane, BoxLayout.Y_AXIS));
    JScrollPane consoleScroll = new JScrollPane(console);
    consolePane.add(consoleScroll);
    JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
    messageTabs = new JTabbedPane();
    messageTabs.addTab("Problem Description", descriptionScroll);
    messageTabs.addTab("Console", consolePane);
    messageTabs.setSelectedIndex(CONSOLE_TAB);
    messageTabs.setMinimumSize(new Dimension(300, 0));
    messageTabs.setPreferredSize(new Dimension(600, 150));
    LookUpTable.getInstance().put("programTabs", new JTabbedPane());
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    programTabs.addFocusListener(new MajorFocusListener(programTabs));
    programTabs.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent evt) {
        JTabbedPane pane = (JTabbedPane) evt.getSource();
        Component c = pane.getSelectedComponent();
        if (c != null && c instanceof EditPanel) {
          c.requestFocusInWindow();
          setPanelActions((EditPanel) c);
        }
      }
    });
    setSpecialKeys(programTabs);
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, programTabs, messageTabs);
    splitPane.setOneTouchExpandable(true);
    splitPane.setResizeWeight(0.8);
    statusArea = new JLabel("Welcome to the EECS Editor!");
    statusArea.setForeground(Color.black);
    progressBar = new JProgressBar();
    progressBar.setAlignmentX(0.0f);
    progressBar.setVisible(true);
    createHelpWindow();
    initializeToolBar();
    mainWindow.getContentPane().add(toolBar, BorderLayout.NORTH);
    mainWindow.getContentPane().add(splitPane, BorderLayout.CENTER);
    mainWindow.getContentPane().add(statusArea, BorderLayout.SOUTH);
    // The popup relies on these menu items, so initialize it last
    initializePopup();
  }

  private void initializePopup() {
    editingPopup = new JPopupMenu();
    JMenuItem item;
    item = new JMenuItem(saveAction);
    item.setIcon(null);
    editingPopup.add(item);
    editingPopup.addSeparator();
    item = new JMenuItem(closeAction);
    item.setIcon(null);
    editingPopup.add(item);
    editingPopup.addSeparator();
    item = new JMenuItem(formatAction);
    item.setIcon(null);
    editingPopup.add(item);
    item = new JMenuItem(compileAction);
    item.setIcon(null);
    editingPopup.add(item);
    item = new JMenuItem(runAction);
    item.setIcon(null);
    editingPopup.add(item);
    editingPopup.addSeparator();
    item = new JMenuItem(cutAction);
    item.setIcon(null);
    editingPopup.add(item);
    item = new JMenuItem(copyAction);
    item.setIcon(null);
    editingPopup.add(item);
    item = new JMenuItem(pasteAction);
    item.setIcon(null);
    editingPopup.add(item);
    editingPopup.addSeparator();
    item = new JMenuItem(replaceAction);
    item.setIcon(null);
    editingPopup.add(item);
  }

  private void initializeToolBar() {
    toolBar = new JToolBar("IT105 Editor Tools");
    toolBar.setFloatable(false);
    JButton button = null;
    ClassLoader cl = this.getClass().getClassLoader();
    button = new JButton(openAction);
    button.setText("");
    toolBar.add(button);
    button = new JButton(saveAction);
    button.setText("");
    toolBar.add(button);
    button = new JButton(saveAsAction);
    button.setText("");
    toolBar.add(button);
    button = new JButton(printAction);
    button.setText("");
    toolBar.add(button);
    toolBar.addSeparator();
    button = new JButton(copyAction);
    button.setText("");
    toolBar.add(button);
    button = new JButton(cutAction);
    button.setText("");
    toolBar.add(button);
    button = new JButton(pasteAction);
    button.setText("");
    toolBar.add(button);
    toolBar.addSeparator();
    button = new JButton(new ImageIcon(cl.getResource("eecs/editor/images/Undo24.gif")));
    button.setToolTipText("Undo");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        undoAction.actionPerformed(e);
      }
    });
    toolBar.add(button);
    undoButton = button;
    undoButton.setEnabled(false);
    button = new JButton(new ImageIcon(cl.getResource("eecs/editor/images/Redo24.gif")));
    button.setToolTipText("Redo");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        redoAction.actionPerformed(e);
      }
    });
    toolBar.add(button);
    redoButton = button;
    redoButton.setEnabled(false);
    toolBar.addSeparator();
    button = new JButton(findAction);
    button.setText("");
    toolBar.add(button);
    toolBar.addSeparator();
    button = new JButton(compileAction);
    button.setText("");
    toolBar.add(button);
    button = new JButton(runAction);
    button.setText("");
    toolBar.add(button);
    button = new JButton(haltAction);
    button.setText("");
    toolBar.add(button);
  }

  private JMenuItem menuItem(JMenu menu, String name, int keyEvent, final String accelerator) {
    JMenuItem item = new JMenuItem(name, keyEvent);
    item.setActionCommand(name);
    if (accelerator != null) {
      item.setAccelerator(KeyStroke.getKeyStroke(accelerator));
    }
    item.addActionListener(myActionListener);
    menu.add(item);
    return item;
  }

  void newAction(String type) {
    updateStatusBar("New Program");
    LanguageKit kit = LanguageKitFactory.getLanguageKit(type);
    addProgramTab("New " + type + "[" + (newFileNum++) + "]", kit);
    messageTabs.setSelectedIndex(CONSOLE_TAB);
    filesOpen(true);
    getCurrentProgramTab().requestFocusInWindow();
  }

  void openAction() {
    if (chooser == null) {
      updateStatusBar("Problem getting file chooser, try again in a minute...");
      return;
    }
    updateStatusBar("Opening...");
    chooser.setDialogTitle("Open Program File");
    chooser.setApproveButtonText("Open");
    switch (chooser.showOpenDialog(mainWindow)) {
      case JFileChooser.CANCEL_OPTION:
      case JFileChooser.ERROR_OPTION:
        updateStatusBar("Open cancelled");
        return;
      case JFileChooser.APPROVE_OPTION:
        try {
          openFile(chooser.getSelectedFile());
        }
        catch (Exception e) {
          updateStatusBar("Error opening file.");
        }
        getCurrentProgramTab().requestFocusInWindow();
        return;
      default:
        updateStatusBar("Unknown result on open.");
        return;
    }
  }

  void openFile(File file) throws IOException {
    LanguageKit kit = LanguageKitFactory.getLanguageKit(getFileType(file.getName()));
    addProgramTab(file.getName(), kit);
    if (getCurrentProgramTab().openAction(file)) {
      updateStatusBar("Opened " + file.getCanonicalPath());
      addMRUMenuItem(file.getAbsolutePath());
      filesOpen(true);
    }
    else {
      updateStatusBar("Error opening file." + file.getCanonicalPath());
    }
  }

  void pastePopupAction() {
    JComponent area = (JComponent) LookUpTable.getInstance().get("minorFocus");
    if (area != null) {
      if (area instanceof JEditTextArea) {
        JEditTextArea editArea = (JEditTextArea) area;
        int offset = editArea.xyToOffset(mouseLocation.x, mouseLocation.y);
        int dot = editArea.getSelectionStart();
        int mark = editArea.getSelectionEnd();
        if ((offset < dot && offset < mark) || offset > dot && offset > mark) {
          editArea.select(offset, offset);
        }
        editArea.paste();
      }
    }
  }

  void previewAction() {
    getCurrentProgramTab().previewWeb();
  }

  void printAction() {
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    programTabs.setEnabled(false);
    EditPanel tab = getCurrentProgramTab();
    updateStatusBar("Print");
    String title = "<No Title>";
    try {
      title = tab.getSaveFile().getCanonicalPath();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    PrintText.printText(title, tab.getProgramArea().getText(), true);
    programTabs.setEnabled(true);
  }

  public void printLineToConsole(String msg) {
    printToConsole(msg + "\n");
  }

  private void printToConsole(String msg) {
    Document doc = console.getDocument();
    try {
      doc.insertString(doc.getLength(), msg, null);
    }
    catch (BadLocationException e) {
      e.printStackTrace();
    }
    console.setCaretPosition(doc.getLength() - 1);
  }

  void propertiesAction() {
    if (propertiesDialog == null) {
      propertiesDialog = new PropertyDialog(mainWindow, true);
    }
    propertiesDialog.setTextSize(prefs.getInt(TEXT_SIZE_KEY, DEFAULT_TEXTSIZE));
    propertiesDialog.setTabSize(prefs.getInt(TAB_SIZE_KEY, DEFAULT_TABSIZE));
    propertiesDialog.setAutoSaveTime(timeForAutoSave);
    propertiesDialog.pack();
    propertiesDialog.setVisible(true);
    prefs.putInt(AUTOSAVE_TIME_KEY, propertiesDialog.getAutoSaveTime());
    prefs.putInt(TAB_SIZE_KEY, propertiesDialog.getTabSize());
    prefs.putInt(TEXT_SIZE_KEY, propertiesDialog.getTextSize());
  }

  // Reformats the document according to the reformatting properties of the LanguageKit.
  public void formatAction() {
    EditPanel currentPanel = getCurrentProgramTab();
    LanguageKit languageKit = currentPanel.getLanguageKit();
    int result = currentPanel.reformat();
    if (result == LanguageKit.REFORMAT_SUCCESS) {
      updateStatusBar("Reformat completed successfully");
    }
    if (result == LanguageKit.REFORMAT_WARNINGS) {
      updateStatusBar("Formatted with warnings");
      console.setText(languageKit.getFormatMessages().toString());
    }
    if (result == LanguageKit.REFORMAT_ERRORS) {
      updateStatusBar("Errors-Reformat failed");
      console.setText(languageKit.getFormatMessages().toString());
    }
    beep();
    messageTabs.setSelectedIndex(CONSOLE_TAB);
  }

  void replace(String pattern, String content, boolean isCaseSensitive, boolean matchWords, boolean replaceAll) {
    String replacement = content;
    if ((pattern == null) || (pattern.length() == 0)) {
      updateStatusBar("Missing search string");
      beep();
      return;
    }
    if (replacement == null) {
      replacement = "";
    }
    if (replaceAll) {
      if (!getActiveProgramArea().replaceAll(pattern, replacement, isCaseSensitive, matchWords)) {
        beep();
      }
    }
    else
      if (!getActiveProgramArea().replace(pattern, replacement, isCaseSensitive, matchWords)) {
        beep();
      }
  }

  void replaceAction() {
    if (replaceDialog == null) {
      replaceDialog = new ReplaceDialog(mainWindow, false, findListener);
    }
    replaceDialog.openReplaceDialog();
  }

  void runAction() {
    System.out.println("Editor.runAction()");
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    programTabs.setEnabled(false);
    getCurrentProgramTab().setEnabled(false);
    if (process != null) {
      clearRunProcess();
    }
    final EditPanel runningPanel = getCurrentProgramTab();
    if (runningPanel.getLanguageKit().isRunnable()) {
      updateStatusBar("Run");
      runAction.setEnabled(false);
      haltAction.setEnabled(true);
      messageTabs.setSelectedIndex(CONSOLE_TAB);
      process = runningPanel.run();
      //    Start a new thread to wait for this to finish running
      new Thread("Run_program") {
        public void run() {
          while ((process != null) && process.isRunning()) {
            try {
              Thread.sleep(100);
            }
            catch (InterruptedException e) {
              //
            }
          }
          console.setText(runningPanel.getLanguageKit().getRunResults());
          messageTabs.setTitleAt(CONSOLE_TAB, runningPanel.getSaveFile().getName());
          runAction.setEnabled(true);
          haltAction.setEnabled(false);
          process = null;
          JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
          programTabs.setEnabled(true);
          getCurrentProgramTab().setEnabled(true);
          getCurrentProgramTab().requestFocusInWindow();
          updateStatusBar("Run Complete");
        }
      }.start();
    }
    else {
      console.setText("A" + runningPanel.getLanguageKit().getDescription() + "\\ cannot be executed/viewed.");
      messageTabs.setTitleAt(CONSOLE_TAB, "Console - " + runningPanel.getSaveFile().getName());
      updateStatusBar("Run failed.");
      programTabs.setEnabled(true);
      getCurrentProgramTab().setEnabled(true);
      getCurrentProgramTab().requestFocusInWindow();
    }
  }

  void saveAction() {
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    int tab = programTabs.getSelectedIndex();
    EditPanel currentPanel = (EditPanel) programTabs.getComponentAt(tab);
    if (chooser == null) {
      updateStatusBar("Can't save file yet.  Try again later...");
      return;
    }
    updateStatusBar("Saving...");
    if (currentPanel.saveAction()) {
      programTabs.setTitleAt(tab, currentPanel.getSaveFile().getName());
      prefs.put(LANGUAGE_KEY, currentPanel.getLanguageKit().getLanguage());
    }
    else {
      updateStatusBar("Error saving file.");
    }
  }

  void saveAsAction() {
    JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
    int tab = programTabs.getSelectedIndex();
    EditPanel currentPanel = (EditPanel) programTabs.getComponentAt(tab);
    if (chooser == null) {
      updateStatusBar("Can't save yet.  Try again later...");
      return;
    }
    updateStatusBar("Save As");
    chooser.setDialogTitle("Save Program File");
    chooser.setApproveButtonText("Save");
    currentPanel.saveAsAction();
    if (currentPanel.getSaveFile() != null) {
      prefs.put(LANGUAGE_KEY, currentPanel.getLanguageKit().getLanguage());
      programTabs.setTitleAt(tab, currentPanel.getSaveFile().getName());
    }
  }

  void selectAllAction() {
    JComponent minorFocus = (JComponent) LookUpTable.getInstance().get("minorFocus");
    if (minorFocus != null) {
      if (minorFocus instanceof JEditTextArea) {
        ((JEditTextArea) minorFocus).selectAll();
      }
      else
        if (minorFocus instanceof JTextComponent) {
          ((JTextComponent) minorFocus).selectAll();
        }
    }
    else {
      updateStatusBar("Your cursor must be in the editing area or console to select.");
      beep();
    }
  }

  private void setMRUMenuItem(int item, String name) {
    String file = name.substring(name.lastIndexOf("\\") + 1);
    int end = name.lastIndexOf("\\");
    if (end < 0) {
      end = 0;
    }
    int start = (end > 24) ? (end - 24) : 0;
    String summaryPath = " " + (item + 1) + "  " + file + " [" + ((start == 0) ? "" : "...") + name.substring(start, end) + "]";
    Action menuAction = new AbstractAction() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (chooser == null) {
            updateStatusBar("Problem getting file chooser, try again in a minute...");
            return;
          }
          openFile(new File(e.getActionCommand()));
        }
        catch (IOException ioe) {
          JOptionPane.showMessageDialog(mainWindow, "Couldn't open \n" + e.getActionCommand());
        }
      }
    };
    menuAction.putValue(Action.SHORT_DESCRIPTION, summaryPath);
    menuAction.putValue(Action.LONG_DESCRIPTION, name);
    menuAction.putValue(Action.ACTION_COMMAND_KEY, name);
    mruMenuItem[item].setAction(menuAction);
    mruMenuItem[item].setText(summaryPath);
  }

  void setPanelActions(EditPanel panel) {
    LanguageKit language = panel.getLanguageKit();
    runAction.setEnabled(language.isRunnable() && panel.isCompiled());
    compileAction.setEnabled(language.hasCompiler() && !panel.isCompiled());
    testMenuItem.setEnabled(currentProblem != null && runAction.isEnabled());
    formatAction.setEnabled(language.hasFormatter() && panel.isCompiled());
    reformatSettingsMenuItem.setEnabled(language.hasFormatPreferences());
    saveAsAction.setEnabled(true);
    saveAction.setEnabled(!panel.isSaved());
    printAction.setEnabled(true);
    // Some of the actions should be moved into the EditPanel InputHandler since they aren't needed at this level.
    // They're all here now because they started here with the original editor model, and haven't been refactored down.
    Object[] keys = actions.allKeys();
    for (int i = 0; i < keys.length; i++) {
      panel.addInputHandler((String) keys[i], actions.get(keys[i]));
    }
  }

  private ActionMap createActionMap() {
    ActionMap map = new ActionMap();
    map.put("C+F4", closeAction);
    map.put("C+L", gotoAction);
    map.put("C+M", problemAction);
    map.put("C+S", saveAction);
    map.put("C+O", openAction);
    map.put("C+P", printAction);
    map.put("CS+F", formatAction);
    map.put("C+X", cutAction);
    map.put("C+C", copyAction);
    map.put("C+V", pasteAction);
    map.put("F9", compileAction);
    map.put("F10", runAction);
    map.put("C+F", findAction);
    map.put("F3", replaceAction);
    map.put("C+Z", undoAction);
    map.put("C+Y", redoAction);
    return map;
  }

  private void updateMRU() {
    Preferences prefs = Preferences.userNodeForPackage(Editor.class);
    int size = mruManager.size();
    String[] fileList = mruManager.getFiles();
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (!fileList[i].equals("empty")) {
        mruMenuItem[count].setVisible(true);
        prefs.put("mru" + i, fileList[i]);
        setMRUMenuItem(count++, fileList[i]);
        mruSeparator.setVisible(true);
      }
    }
    for (int i = count; i < MRU_CAPACITY; i++) {
      mruMenuItem[i].setVisible(false);
      prefs.put("mru" + i, "empty");
    }
  }

  class AllFilesFilter implements FilenameFilter {
    public boolean accept(File f, String s) {
      return true;
    }
  }

  private class ClipboardListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (clip == null || pasteAction == null) {
        return;
      }
      try {
        clip.getContents(this).getTransferData(DataFlavor.stringFlavor);
        pasteAction.setEnabled(true);
      }
      catch (Exception e) {
        pasteAction.setEnabled(false);
      }
    }
  }

  class CloseAction extends AbstractAction {
    public CloseAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      closeAction(true);
    }
  }

  class CompileAction extends AbstractAction {
    public CompileAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      compileAction();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class FindAction extends AbstractAction {
    public FindAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      findAction();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class GotoAction extends AbstractAction {
    public GotoAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      gotoAction();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class HaltAction extends AbstractAction {
    public HaltAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      haltAction();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  private class NewAction extends AbstractAction {
    public NewAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent evt) {
      Object source = evt.getSource();
      if (source.equals(newJavaMenuItem)) {
        newAction(LanguageKit.JAVA);
      }
      else
        if (source.equals(newHTMLMenuItem)) {
          newAction(LanguageKit.HTML);
        }
        else
          if (source.equals(newCSSMenuItem)) {
            newAction(LanguageKit.CSS);
          }
          else {
            newAction(LanguageKit.TEXT);
          }
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class OpenAction extends AbstractAction {
    public OpenAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      openAction();
    }
  }

  class PopupListener extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
      if (e.isPopupTrigger()) {
        mouseLocation = e.getPoint();
        editingPopup.show(e.getComponent(), e.getX(), e.getY());
      }
    }
  }

  class PrintAction extends AbstractAction {
    public PrintAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      printAction();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class RedoAction extends AbstractAction {
    public RedoAction() {
      super("Redo");
      putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Y"));
      putValue(Action.ACTION_COMMAND_KEY, "ctrl Y");
      putValue(Action.NAME, "Redo");
      setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
      undoManager.redo();
      updateRedoState();
      undoAction.updateUndoState();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }

    protected void updateRedoState() {
      if (undoManager.canRedo()) {
        setEnabled(true);
        putValue(Action.NAME, undoManager.getRedoPresentationName());
        if (redoButton != null) {
          redoButton.setEnabled(true);
          redoButton.setToolTipText(undoManager.getRedoPresentationName());
        }
      }
      else {
        setEnabled(false);
        putValue(Action.NAME, "Redo");
        if (redoButton != null) {
          redoButton.setEnabled(false);
          redoButton.setToolTipText(undoManager.getRedoPresentationName());
        }
      }
    }
  }

  class FormatAction extends AbstractAction {
    public FormatAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      formatAction();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class ReplaceAction extends AbstractAction {
    public ReplaceAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      replaceAction();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class RunAction extends AbstractAction {
    public RunAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      System.out.println("Editor:RunAction.actionPerformed()");
      runAction();
    }
  }

  class SaveAction extends AbstractAction {
    public SaveAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      saveAction();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class SaveAsAction extends AbstractAction {
    public SaveAsAction(String text, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      saveAsAction();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class SplashWindow3 extends JWindow {
    public SplashWindow3(String filename, Frame f, int waitTime) {
      super(f);
      JLabel l = new JLabel(createToolBarIcon(filename));
      getContentPane().add(l, BorderLayout.CENTER);
      pack();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension labelSize = l.getPreferredSize();
      setLocation((screenSize.width / 2) - (labelSize.width / 2), (screenSize.height / 2) - (labelSize.height / 2));
      addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          setVisible(false);
          dispose();
        }
      });
      final int pause = waitTime;
      final Runnable closerRunner = new Runnable() {
        public void run() {
          setVisible(false);
          dispose();
        }
      };
      Runnable waitRunner = new Runnable() {
        public void run() {
          try {
            Thread.sleep(pause);
            SwingUtilities.invokeAndWait(closerRunner);
          }
          catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
      setVisible(true);
      Thread splashThread = new Thread(waitRunner, "SplashThread");
      splashThread.start();
    }
  }

  class UndoAction extends AbstractAction {
    public UndoAction() {
      super("Undo");
      putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Z"));
      putValue(Action.ACTION_COMMAND_KEY, "ctrl Z");
      putValue(Action.NAME, "Undo");
      setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
      undoManager.undo();
      updateUndoState();
      redoAction.updateRedoState();
      JTabbedPane programTabs = (JTabbedPane) LookUpTable.getInstance().get("programTabs");
      programTabs.getSelectedComponent().requestFocusInWindow();
    }

    protected void updateUndoState() {
      if (undoManager.canUndo()) {
        setEnabled(true);
        putValue(Action.NAME, undoManager.getUndoPresentationName());
        if (undoButton != null) {
          undoButton.setEnabled(true);
          undoButton.setToolTipText(undoManager.getUndoPresentationName());
        }
      }
      else {
        setEnabled(false);
        putValue(Action.NAME, "Undo");
        if (undoButton != null) {
          undoButton.setEnabled(false);
          undoButton.setToolTipText("Undo");
        }
      }
    }
  }

  private class MajorFocusListener extends FocusAdapter {
    private JComponent component;

    public MajorFocusListener(JComponent comp) {
      this.component = comp;
    }

    public void focusGained(FocusEvent e) {
      majorFocus = component;
      if (component instanceof JTextComponent) {
        LookUpTable.getInstance().put("minorFocus", component);
      }
    }
  }

  private class MinorFocusListener extends FocusAdapter {
    private JComponent component;

    public MinorFocusListener(JComponent comp) {
      this.component = comp;
    }

    public void focusGained(FocusEvent e) {
      LookUpTable.getInstance().put("minorFocus", component);
    }
  }

  private class MyUndoableEditListener implements UndoableEditListener {
    public void undoableEditHappened(UndoableEditEvent e) {
      undoManager.addEdit(e.getEdit());
      undoAction.updateUndoState();
      redoAction.updateRedoState();
    }
  }

  private class MyChangeListener implements PropertyChangeListener {
    private String statusProperty = "status";
    private String compiledProperty = "compiled";
    private String filefilterProperty = "filefilter";
    private String copyEnabledProperty = "copyEnabled";
    private String savedProperty = "saved";

    public void propertyChange(PropertyChangeEvent evt) {
      if (evt.getPropertyName().equals(statusProperty)) {
        updateStatusBar((String) evt.getNewValue());
      }
      if (evt.getPropertyName().equals(compiledProperty)) {
        boolean isCompiled = ((Boolean) (evt.getNewValue())).booleanValue();
        compileAction.setEnabled(!isCompiled);
        runAction.setEnabled(isCompiled && ((EditPanel) (evt.getSource())).getLanguageKit().isRunnable());
        formatAction.setEnabled(isCompiled && ((EditPanel) (evt.getSource())).getLanguageKit().hasFormatter());
        if (currentProblem == null) {
          testMenuItem.setEnabled(false);
        }
        else {
          testMenuItem.setEnabled(isCompiled && ((EditPanel) (evt.getSource())).getLanguageKit().isRunnable());
        }
      }
      if (evt.getPropertyName().equals(filefilterProperty)) {
        setFileFilter((String) (evt.getNewValue()));
      }
      if (evt.getPropertyName().equals(copyEnabledProperty)) {
        boolean state = ((Boolean) evt.getNewValue()).booleanValue();
        copyAction.setEnabled(state);
        cutAction.setEnabled(state);
      }
      if (evt.getPropertyName().equals(savedProperty)) {
        boolean saved = ((Boolean) evt.getNewValue()).booleanValue();
        saveAction.setEnabled(!saved);
      }
    }
  }
}