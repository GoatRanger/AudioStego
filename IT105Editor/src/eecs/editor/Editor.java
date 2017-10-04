/*
 * Copyright (C) 2003 USMA This program is free software; you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the License,
 * or any later version. This program is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU
 * General Public License along with this program; if not, write to the Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
/*
 * KNOWN BUGS:
 */
package eecs.editor;

import eecs.problem.Problem;
import eecs.problem.ProblemManager;
import eecs.util.EMail;
import eecs.util.InterfaceCheck;
import eecs.util.PrintText;
import eecs.util.html.BrowserControl;
import eecs.util.html.PatchedHTMLEditorKit;
import eecs.util.html.TestBrowser;
import eecs.util.tips.JTips;
import eecs.util.ProcessWrapper;
import eecs.editor.language.*;
import eecs.editor.util.Constants;
import eecs.util.FileManager;
import eecs.editor.util.MRUManager;
import eecs.editor.util.URLSubmit;
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
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.help.JHelp;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
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
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import jedit.JEditTextArea;


/**
 * The IT105 Editor.
 * 
 * @author Chris Okasaki
 * @author Karl A. Gossett
 * @version 2.1
 */
public class Editor {
  private static final int DESCRIPTION_TAB = 0;
  private static final int CONSOLE_TAB = 1;
  private static final int MRU_CAPACITY = 5;
  /** DOCUMENT ME! */
  public static final String AUTO_INDENT = Messages
      .getString("Editor.key.auto.indent"); //$NON-NLS-1$
  public static final String LANGUAGE_KEY = Messages
      .getString("Editor.key.default.language"); //$NON-NLS-1$
  public static final String DEFAULT_LANGUAGE = LanguageKit.JAVA;
  public static final String AUTOSAVE_TIME_KEY = Messages
      .getString("Editor.key.autosave.time"); //$NON-NLS-1$
  public static final int DEFAULT_AUTOSAVE = 10;
  public static final int DEFAULT_TEXTSIZE = 12;
  public static final String TEXT_SIZE_KEY = Messages
      .getString("Editor.key.text.size"); //$NON-NLS-1$
  public static final String TAB_SIZE_KEY = Messages
      .getString("Editor.key.tab.size"); //$NON-NLS-1$
  public static final int DEFAULT_TABSIZE = 4;
  public static final String STYLE_KEY = Messages.getString("Editor.key.style"); //$NON-NLS-1$
  public static final String DEFAULT_STYLE = Messages
      .getString("Editor.default.style"); //$NON-NLS-1$
  public static final String HANDBOOK_URL_KEY = Messages
      .getString("Editor.key.handbook.url"); //$NON-NLS-1$
  public static final String DEFAULT_HANDBOOKURL = Messages
      .getString("Editor.handbook.url"); //$NON-NLS-1$
  /** The handler method for allowing Redo function */
  protected transient RedoAction redoAction;
  /** The handler method for allowing Undo functions */
  protected transient UndoAction undoAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction closeAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction compileAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction copyAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction cutAction;
  protected transient AbstractAction findAction;
  protected transient AbstractAction gotoAction;
  
  /** DOCUMENT ME! */
  protected transient AbstractAction haltAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction newAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction openAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction pasteAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction printAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction problemAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction formatAction;
  protected transient AbstractAction replaceAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction runAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction saveAction;
  /** DOCUMENT ME! */
  protected transient AbstractAction saveAsAction;
  
  protected transient AbstractAction submitAction;
  
  /** Performs find and replace operations */
  private ActionListener findListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      String action = e.getActionCommand();
      String pattern = replaceDialog.getFindText();
      boolean caseSensitive = replaceDialog.isCaseSensitive();
      boolean matchWords = replaceDialog.matchWholeWord();
      if (action.equals(ReplaceDialog.FIND_ACTION)) {
        find(pattern, caseSensitive, matchWords);
      } else if (action.equals(ReplaceDialog.CLOSE_ACTION)) {
        replaceDialog.setVisible(false);
      } else if (action.equals(ReplaceDialog.REPLACE_ACTION)) {
        String content = replaceDialog.getReplaceText();
        replace(pattern, content, caseSensitive, matchWords, false);
      } else if (action.equals(ReplaceDialog.REPLACE_ALL_ACTION)) {
        String content = replaceDialog.getReplaceText();
        replace(pattern, content, caseSensitive, matchWords, true);
      }
    }
  };
  /** DOCUMENT ME! */
  private ActionListener myActionListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      String action = e.getActionCommand();
      if (action.equals(Messages.getString("Editor.action.exit"))) { //$NON-NLS-1$
        exitAction();
      } else if (action.equals(Messages.getString("Editor.action.selectall"))) { //$NON-NLS-1$
        selectAllAction();
      } else if (action.equals(Messages.getString("Editor.action.preview"))) { //$NON-NLS-1$
        previewAction();
      } else if (action.equals(Messages.getString("Editor.action.view.web"))) { //$NON-NLS-1$
        previewDeployed();
      } else if (action.equals(Messages.getString("Editor.action.options"))) { //$NON-NLS-1$
        propertiesAction();
      } else if (action.equals(Messages.getString("Editor.action.reformat.settings"))) { //$NON-NLS-1$
        formatSettingsAction();
      } else if (action.equals(Messages.getString("Editor.action.deploy.web"))) { //$NON-NLS-1$
        deployWeb();
      } else if (action.equals(Messages.getString("Editor.action.submit"))) { //$NON-NLS-1$
        submitAction();
      } else if (action.equals(Messages.getString("Editor.action.runtests"))) { //$NON-NLS-1$
        runTestsAction();
      } else if (action.equals(Messages.getString("Editor.action.help"))) { //$NON-NLS-1$
        helpAction();
      } else if (action.equals(Messages.getString("Editor.action.logging"))) { //$NON-NLS-1$
        enableLoggingAction();
      } else if (action.equals(Messages
          .getString("Editor.action.open.handbook"))) { //$NON-NLS-1$
        openHandbookAction();
      } else if (action.equals(Messages.getString("Editor.action.about"))) { //$NON-NLS-1$
        aboutAction();
      } else { /* Unknown action */
        debug(Messages.getString("Editor.unknown.action.string") + action); //$NON-NLS-1$
      }
    }
  };
  
  private CaretListener programAreaCaretListener = new CaretListener() {
    public void caretUpdate(CaretEvent e) {
      int pos = e.getDot();
      if ((pos != e.getMark()) && !isCopyEnabled()) {
        setCopyEnabled(true);
      }
      if ((pos == e.getMark()) && isCopyEnabled()) {
        setCopyEnabled(false);
      }
    }
  };
  
  private PropertyChangeListener myChangeListener = new MyChangeListener();
  /** DOCUMENT ME! */
  private Clipboard clip;
  private Component currentProblemTab;
  private javax.swing.filechooser.FileFilter cssFilter = new GenericFileFilter(
      new String[]{"css"}, Messages.getString("Editor.filter.css.label")); //$NON-NLS-1$ //$NON-NLS-2$
  /** DOCUMENT ME! */
  private javax.swing.filechooser.FileFilter htmlFilter = new GenericFileFilter(
      new String[]{"htm", "html", "shtml"}, Messages
          .getString("Editor.filter.html.label")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  private javax.swing.filechooser.FileFilter hudsonFilter = new GenericFileFilter(
      new String[]{"hud"}, Messages.getString("Editor.filter.hudson.label")); //$NON-NLS-1$ //$NON-NLS-2$
  private javax.swing.filechooser.FileFilter javaFilter = new GenericFileFilter(
      new String[]{"java"}, Messages.getString("Editor.filter.java.label")); //$NON-NLS-1$ //$NON-NLS-2$
  private javax.swing.filechooser.FileFilter problemFilter = new GenericFileFilter(
      new String[]{"ice"}, Messages.getString("Editor.filter.exercises.label")); //$NON-NLS-1$ //$NON-NLS-2$
  /** DOCUMENT ME! */
  private FileHandler logHandler;
  /** DOCUMENT ME! */
  private Font commonFont = new Font(Messages
      .getString("Editor.common.font.name"), Font.PLAIN, 12); //$NON-NLS-1$

  /** DOCUMENT ME! */
  private JButton undoButton;
  /** DOCUMENT ME! */
  private JButton redoButton;
  /** DOCUMENT ME! */
  private JCheckBoxMenuItem loggerMenuItem;
  /** DOCUMENT ME! */
  private JFileChooser chooser = null;
  /** DOCUMENT ME! */
  private JFrame helpWindow;
  /** DOCUMENT ME! */
  private JFrame mainWindow;
  /** DOCUMENT ME! */
  private JLabel columnLabel;
  /** DOCUMENT ME! */
  private JLabel lineLabel;
  /** DOCUMENT ME! */
  private JLabel programLabel;
  /** DOCUMENT ME! */
  private JLabel statusArea;
  private JMenu fileMenu;
  /** DOCUMENT ME! */
  private JMenu newMenu;
  /** DOCUMENT ME! */
  private JMenu toolMenu;
  /** DOCUMENT ME! */
  private JMenuBar menuBar;
  private JMenuItem[] mruMenuItem;
  private JMenuItem newCSSMenuItem;
  private JMenuItem newHTMLMenuItem;
  private JMenuItem newJavaMenuItem;
  private JMenuItem newTextMenuItem;
  private JMenuItem reformatSettingsMenuItem;
  private JMenuItem testMenuItem;
  /** DOCUMENT ME! */
  private JPanel consolePane;
  /** DOCUMENT ME! */
  private JPopupMenu editingPopup;
  /** DOCUMENT ME! */
  private JProgressBar progressBar;
  private JSeparator mruSeparator;
  /** DOCUMENT ME! */
  private JSplitPane splitPane;
  /** DOCUMENT ME! */
  private JTabbedPane messageTabs;
  private JTabbedPane programTabs;
  /** DOCUMENT ME! */
  private JTextArea descriptionArea;
  /** DOCUMENT ME! */
  private JComponent majorFocus;
  /** DOCUMENT ME! */
  private JComponent minorFocus;
  /** DOCUMENT ME! */
  private JTextPane console;
  /** DOCUMENT ME! */
  private JToolBar toolBar;
  private java.util.List fileMRUList;
  /** DOCUMENT ME! */
  private volatile Logger logger = Logger.getLogger(Messages
      .getString("Editor.logger.name")); //$NON-NLS-1$
  public Preferences prefs = Preferences.userRoot().node(
      Messages.getString("Editor.preferences.node")); //$NON-NLS-1$
  /** DOCUMENT ME! */
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
        System.err.println(console.getText(last,found-last));
        text = console.getText(last, found - (last));
      } catch (BadLocationException e) {
      }
      try {
        int line = Integer.parseInt(text.trim());
        gotoLine(line);
      } catch (Exception e) {
      }
    }
  };

  /**
   * A listener for changes in the edit window.
   */
  private MyUndoableEditListener myUndoableEditListener;
  /**
   * A Point that is used when mouse context is needed (Popup Paste, others...)
   */
  private Point mouseLocation = new Point(0, 0);
  /** DOCUMENT ME! */
  private volatile Problem currentProblem = null;
  /** DOCUMENT ME! */
  private ProblemManager problemManager;
  /** DOCUMENT ME! */
  private volatile ProcessWrapper process;
  /** DOCUMENT ME! */
  private PropertyDialog propertiesDialog;
  /** DOCUMENT ME! */
  private ReplaceDialog replaceDialog;
  /** DOCUMENT ME! */
  private String description = Messages
      .getString("Editor.unlabeled.description.string"); //$NON-NLS-1$
  /** DOCUMENT ME! */
  private String handbookURL = Messages.getString("Editor.49"); //$NON-NLS-1$
  /** DOCUMENT ME! */
  private String runCommand = Messages.getString("Editor.java.run.command"); //$NON-NLS-1$
  /** DOCUMENT ME! */
  private volatile javax.swing.Timer timer;
  /** DOCUMENT ME! */
  private UndoManager undoManager = new UndoManager();
  /** DOCUMENT ME! */
  private boolean compilationNecessary = true;
  /** DOCUMENT ME! */
  private boolean pasteEnabled = false;
  /** DOCUMENT ME! */
  private boolean silent = true;
  /** DOCUMENT ME! */
  private boolean useLogging = false;
  private int newFileNum = 0;
  /** DOCUMENT ME! */
  private int timeForAutoSave = 5; // 5 minutes
  private MRUManager mruManager;

  private Timer clipboardTimer;
  private ActionMap actions;
  /**
   * Creates a new Editor object.
   */
  public Editor() {
    System.out.println("Classpath: " +System.getProperty("java.class.path"));
    System.out.println("Working Dir: " +System.getProperty("user.dir"));
    new SplashWindow3(Messages.getString("Editor.splash.image"), null, 6000);
    InterfaceCheck nic = new InterfaceCheck();
    //Constants.networkAvailable = nic.isURLAvailable(Messages.getString("Editor.web.base.url"));
    Constants.networkAvailable = false;
    if (Constants.networkAvailable) {
      copyJagoWrapper();
    }
    Thread.currentThread().setContextClassLoader(
        this.getClass().getClassLoader());
    initActions();
    mruManager = new MRUManager(MRU_CAPACITY);
    initializeFileArea();
    initLogging();
    updateProperties();
    initializeGUI();
    initHelp();
    checkForAutoSaveExists();
    initializeFileChooser();
    getClipboard();
    clipboardTimer = new Timer(500, new ClipboardListener());
    clipboardTimer.setInitialDelay(500);
    clipboardTimer.start();
    splitPane.setDividerLocation(0.85);
    mainWindow.validate();
    mainWindow.setVisible(true);
    mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
    openTipsWindow();
  }

  /**
   * 
   */
  private void openTipsWindow() {
    JTips tips;
    String localTips = System.getProperty(Messages
        .getString("Editor.user.home.property")) //$NON-NLS-1$
        + Messages.getString("Editor.editor.tips.location.from.user.home"); //$NON-NLS-1$
    String networkTips = Messages
        .getString("Editor.network.editor.tips.location"); //$NON-NLS-1$
    if (Constants.networkAvailable) {
      tips = new JTips(networkTips, "eecs/editor"); //$NON-NLS-1$
      FileManager.copy(networkTips, localTips);
      tips.startup();
    } else {
      File tipFile = new File(localTips);
      if (tipFile.exists()) {
        tips = new JTips(localTips, "eecs/editor"); //$NON-NLS-1$
        tips.startup();
      }
    }
  }

  /**
   * 
   */
  private void getClipboard() {
    try {
      clip = mainWindow.getToolkit().getSystemClipboard();
      
      // Throws IllegalStateException if clipboard is in use
      // or otherwise unavailable
      if (clip.getContents(this) != null) {
        pasteAction.setEnabled(true);
      }
    } catch (IllegalStateException ile) {
      pasteAction.setEnabled(false);
    } catch (Exception e) {
      logger
          .log(Level.WARNING, Messages.getString("Editor.clipboard.error"), e); //$NON-NLS-1$
    }
  }

  /**
   * 
   */
  private void copyJagoWrapper() {
    JDialog updateDlg = new JDialog(new JFrame(), Messages
        .getString("Editor.file.copying.message")); //$NON-NLS-1$
    updateDlg.setSize(500, 200);
    updateDlg.setLocation(100, 100);
    JLabel label = new JLabel(Messages.getString("Editor.file.update.label")); //$NON-NLS-1$
    updateDlg.getContentPane().setLayout(new BorderLayout(10, 10));
    updateDlg.getContentPane().add(label, BorderLayout.CENTER);
    updateDlg.setVisible(true);
    //      if (FileManager
    //          .copyFileToJavaExt(
    //              "http://www-internal.eecs.usma.edu/courses/it105/Resources/IT105%20Editor/",
    //              "jago.jar", true)) {
    //        FileManager
    //            .copyFileToJavaExt(
    //                "http://www-internal.eecs.usma.edu/courses/it105/Resources/IT105%20Editor/",
    //                "it105Wrapper.jar", true);
    //      }
    // EditorDev version
//    if (FileManager.copyFileToJavaExt(Messages
//        .getString("Editor.development.base.url"), //$NON-NLS-1$
//        Messages.getString("Editor.jago.archive.name"), true)) { //$NON-NLS-1$
//      FileManager.copyFileToJavaExt(Messages
//          .getString("Editor.development.base.url"), //$NON-NLS-1$
//          Messages.getString("Editor.it105wrapper.archive.name"), true); //$NON-NLS-1$
//    }
    updateDlg.setVisible(false);
    updateDlg.dispose();
  }

  /**
   * 
   */
  private void initActions() {
    compileAction = new CompileAction("Compile", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Compile24.gif"), //$NON-NLS-1$
        Messages.getString("Editor.compile.action.tooltip"), new Integer(
            KeyEvent.VK_C), KeyStroke //$NON-NLS-1$
            .getKeyStroke("F9")); //$NON-NLS-1$
    compileAction.setEnabled(false);
    closeAction = new CloseAction("Close", null, Messages
        .getString("Editor.close.action.tooltip"), new Integer( //$NON-NLS-1$ //$NON-NLS-2$
        KeyEvent.VK_C), KeyStroke.getKeyStroke("ctrl F4")); //$NON-NLS-1$
    closeAction.setEnabled(false);
    findAction = new FindAction("Find...", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Replace24.gif"), Messages
            .getString("Editor.find.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_F), KeyStroke.getKeyStroke("ctrl F")); //$NON-NLS-1$
    findAction.setEnabled(false);
    gotoAction = new GotoAction(Messages.getString("Editor.menu.edit.goto.line"),
        null, Messages.getString("Editor.goto.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_G), KeyStroke.getKeyStroke("ctrl L")); //$NON-NLS-1$
    gotoAction.setEnabled(false);
    haltAction = new HaltAction("Stop", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Stop24.gif"), //$NON-NLS-1$
        Messages.getString("Editor.stop.action.tooltip"), new Integer(
            KeyEvent.VK_H), KeyStroke //$NON-NLS-1$
            .getKeyStroke("F12")); //$NON-NLS-1$
    haltAction.setEnabled(false);
    newAction = new NewAction("New", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/New24.gif"), Messages
            .getString("Editor.new.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_N), null);
    openAction = new OpenAction("Open", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Open24.gif"), Messages
            .getString("Editor.open.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_O), KeyStroke.getKeyStroke("ctrl O")); //$NON-NLS-1$
    formatAction = new FormatAction("Format", null, Messages
        .getString("Editor.reformat.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_F), KeyStroke.getKeyStroke("ctrl shift F")); //$NON-NLS-1$
    formatAction.setEnabled(false);
    replaceAction = new ReplaceAction("Replace...", null, Messages
        .getString("Editor.replace.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_R), KeyStroke.getKeyStroke("F3")); //$NON-NLS-1$
    replaceAction.setEnabled(false);
    cutAction = new CutAction("Cut", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Cut24.gif"), Messages
            .getString("Editor.cut.action.tooltip"), new Integer( //$NON-NLS-1$ //$NON-NLS-2$
            KeyEvent.VK_T), KeyStroke.getKeyStroke("ctrl X")); //$NON-NLS-1$
    cutAction.setEnabled(false);
    copyAction = new CopyAction("Copy", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Copy24.gif"), Messages
            .getString("Editor.copy.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_C), KeyStroke.getKeyStroke("ctrl C")); //$NON-NLS-1$
    copyAction.setEnabled(false);
    pasteAction = new PasteAction("Paste", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Paste24.gif"), Messages
            .getString("Editor.paste.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_P), KeyStroke.getKeyStroke("ctrl V")); //$NON-NLS-1$
    pasteAction.setEnabled(false);
    printAction = new PrintAction("Print...", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Print24.gif"), Messages
            .getString("Editor.print.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_P), KeyStroke.getKeyStroke("ctrl P")); //$NON-NLS-1$
    printAction.setEnabled(false);
    problemAction = new ProblemAction("Problem Set Manager", null, //$NON-NLS-1$
        Messages.getString("Editor.problemset.action.tooltip"), new Integer(
            KeyEvent.VK_M), KeyStroke //$NON-NLS-1$
            .getKeyStroke("ctrl M")); //$NON-NLS-1$
    saveAction = new SaveAction("Save", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Save24.gif"), Messages
            .getString("Editor.save.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_S), KeyStroke.getKeyStroke("ctrl S")); //$NON-NLS-1$
    saveAction.setEnabled(false);
    saveAsAction = new SaveAsAction("Save As...", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/SaveAs24.gif"), Messages
            .getString("Editor.saveas.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_A), null);
    saveAsAction.setEnabled(false);
    submitAction = new SaveAsAction("Submit...", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Submit24.gif"), Messages
            .getString("Editor.submit.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_U), null);
    submitAction.setEnabled(false);
    undoAction = new UndoAction("Undo", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Undo24.gif"), Messages
            .getString("Editor.undo.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        null, KeyStroke.getKeyStroke("ctrl Z"));
    submitAction.setEnabled(false);
    runAction = new RunAction("Run/View", //$NON-NLS-1$
        createToolBarIcon("eecs/editor/images/Play24.gif"), Messages
            .getString("Editor.run.action.tooltip"), //$NON-NLS-1$ //$NON-NLS-2$
        new Integer(KeyEvent.VK_R), KeyStroke.getKeyStroke("F10")); //$NON-NLS-1$
    runAction.setEnabled(false);
    actions = createActionMap();
    myUndoableEditListener = new MyUndoableEditListener();
  }

  /**
   * The application entry point.
   * 
   * @param args
   *          Command line arguments (not used)
   */
  public static void main(String[] args) {
    new Editor();
    /*
     * // show available fonts String[] fontNames =
     * //Toolkit.getDefaultToolkit().getFontList();
     * GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
     * for (int i = 0; i < fontNames.length; i++)
     * printLineToConsole(fontNames[i]);
     */
  }

  protected void setCopyEnabled(boolean enable) {
    copyAction.setEnabled(enable);
    cutAction.setEnabled(enable);
  }

  protected boolean isCopyEnabled() {
    return copyAction.isEnabled();
  }

  protected JFileChooser getFileChooser() {
    return chooser;
  }

  /**
   * DOCUMENT ME!
   * 
   * @param fileExt
   *          DOCUMENT ME!
   */
  protected void setFileFilter(String fileExt) {
    if (fileExt.equals(Messages.getString("Editor.html.filefilter.extension"))) { //$NON-NLS-1$
      chooser.setFileFilter(htmlFilter);
    } else if (fileExt.equals(Messages
        .getString("Editor.hudson.filefilter.extension"))) { //$NON-NLS-1$
      chooser.setFileFilter(hudsonFilter);
    } else if (fileExt.equals(Messages
        .getString("Editor.java.filefilter.extension"))) { //$NON-NLS-1$
      chooser.setFileFilter(javaFilter);
    } else if (fileExt.equals(Messages
        .getString("Editor.css.filefilter.extension"))) { //$NON-NLS-1$
      chooser.setFileFilter(cssFilter);
    } else {
      chooser.setAcceptAllFileFilterUsed(true);
    }
  }

  /**
   * Updates the status bar.
   * 
   * @param msg
   *          The <code>String</code> messages to display.
   */
  protected void updateStatus(String msg) {
    statusArea.setText(" " + msg); //$NON-NLS-1$
  }

  private JEditTextArea getActiveProgramArea() {
    return ((EditPanel) programTabs.getSelectedComponent()).getProgramArea();
  }

  /**
   * DOCUMENT ME!
   */
  private void setButtonSizes() {
//    // If initially setting parameters, toolbar doesn't exist
//    if (toolBar == null) {
//      return;
//    }
//    Component[] buttons = toolBar.getComponents();
//    for (int i = 0; i < buttons.length; i++) {
//      if (buttons[i] instanceof JButton) {
//        JButton button = (JButton) buttons[i];
//        button.setSize(30, 30);
//        button.setPreferredSize(new Dimension(30, 30));
//      }
//    }
  }

  private String getFileType(String filename) {
    String type = LanguageKit.TEXT;
    int dot = filename.lastIndexOf(".") + 1; //$NON-NLS-1$
    if (dot > 0) {
      String ext = filename.substring(dot).toLowerCase();
      if (ext.equals("java")) { //$NON-NLS-1$
        type = LanguageKit.JAVA;
      } else if (ext.equals("css")) { //$NON-NLS-1$
        type = LanguageKit.CSS;
      } else if (ext.equals("htm") || ext.equals("html") || ext.equals("shtml")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        type = LanguageKit.HTML;
      }
    }
    return type;
  }

  /**
   * DOCUMENT ME!
   * 
   * @param component
   *          DOCUMENT ME!
   */
  private void setSpecialKeys(JComponent component) {
    InputMap imap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap amap = component.getActionMap();
//    imap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_UP,
//        java.awt.event.InputEvent.CTRL_DOWN_MASK), "Editor.IncreaseText"); //$NON-NLS-1$
//    amap.put("Editor.IncreaseText", new AbstractAction() { //$NON-NLS-1$
//          public void actionPerformed(ActionEvent e) {
//            increaseTextSizeAction();
//          }
//        });
//    imap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DOWN,
//        java.awt.event.InputEvent.CTRL_DOWN_MASK), "Editor.DecreaseText"); //$NON-NLS-1$
//    amap.put("Editor.DecreaseText", new AbstractAction() { //$NON-NLS-1$
//          public void actionPerformed(ActionEvent e) {
//            decreaseTextSizeAction();
//          }
//        });
    imap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V,
        java.awt.event.InputEvent.ALT_DOWN_MASK), "Editor.Preview"); //$NON-NLS-1$
    amap.put("Editor.Preview", new AbstractAction() { //$NON-NLS-1$
          public void actionPerformed(ActionEvent e) {
            previewAction();
          }
        });
    imap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W,
        java.awt.event.InputEvent.ALT_DOWN_MASK), "Editor.ViewWeb"); //$NON-NLS-1$
    amap.put("Editor.ViewWeb", new AbstractAction() { //$NON-NLS-1$
          public void actionPerformed(ActionEvent e) {
            previewDeployed();
          }
        });
    imap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D,
        java.awt.event.InputEvent.ALT_DOWN_MASK), "Editor.DeployWeb"); //$NON-NLS-1$
    amap.put("Editor.DeployWeb", new AbstractAction() { //$NON-NLS-1$
          public void actionPerformed(ActionEvent e) {
            deployWeb();
          }
        });
  }

  /**
   * Displays the Editor About message/dialog.
   */
  private void aboutAction() {
    updateStatus(Messages.getString("Editor.editor.version.string")); //$NON-NLS-1$
  }

  /**
   * Adds the Most Recently Used file listing to the specified menu.
   * 
   * @param menu  The JMenu where the MRU should be added.
   */
  private void addFileMRU(JMenu menu) {
    mruMenuItem = new JMenuItem[MRU_CAPACITY];
    //Preferences prefs = Preferences.userNodeForPackage(this.getClass());
    int total = 0;
    String[] list = new String[mruMenuItem.length];
    while (total < mruMenuItem.length) {
      mruMenuItem[total] = new JMenuItem();
      String item = prefs.get("mru" + total, "empty"); //$NON-NLS-1$ //$NON-NLS-2$
      list[total] = item;
      menu.add(mruMenuItem[total]);
      total++;
    }
    // Files should be added to the mruManager in reverse order the first time
    for (int idx = list.length - 1; idx >= 0; idx--) {
      mruManager.add(list[idx]);
    }
    mruSeparator = new JSeparator();
    mruSeparator.setVisible(false);
    menu.add(mruSeparator);
    updateMRU();
  }

  /**
   * Adds a new item to the Most Recently Used file list.
   * 
   * @param name   A String with the full file name to add.
   */
  private void addMRUMenuItem(String name) {
    mruManager.add(name);
    updateMRU();
  }

  private void addProgramTab(String name, LanguageKit type) {
    EditPanel programPane = new EditPanel(type);
    setSpecialKeys(programPane.getProgramArea());
    programPane.setFileChooser(chooser);
    programPane.addUndoableEditListener(myUndoableEditListener);
    // TODO: Update the undoableEditListener -- this version uses the same listener for all windows; so hitting "undo" might undo something on another window!    
    programPane.addPropertyChangeListener(myChangeListener);
    programPane.addMouseListener(new Editor.PopupListener());
    programPane.addPopup(editingPopup);
    programPane.getProgramArea().addFocusListener(
        new MinorFocusListener(programPane.getProgramArea()));
    programPane.addFocusListener(new MajorFocusListener(programPane));
    programPane.addFocusListener(new
     MinorFocusListener(programPane.getProgramArea()));
    setPanelActions(programPane);
    programTabs.addTab(name, programPane);
    programTabs.setSelectedComponent(programPane);
    saveAction.setEnabled(false);
  }

  /**
   * DOCUMENT ME!
   */
  private void beep() {
    Toolkit.getDefaultToolkit().beep();
  }

  /**
   * DOCUMENT ME!
   * 
   * @return DOCUMENT ME!
   */
  private boolean canCheckForErrors() {
    boolean ok = true;
    EditPanel currentPanel = currentProgramTab();
    int tab = programTabs.getSelectedIndex();
    String action = Messages.getString("Editor.validation.action.message"); //$NON-NLS-1$
    LanguageKit kit = currentPanel.getLanguageKit();
    if (kit.getDescription().equals("Java Program")) { //$NON-NLS-1$
      action = Messages.getString("Editor.compilation.action.message"); //$NON-NLS-1$
    }
    if (kit.hasCompiler()) {
      if (!currentPanel.saveIfNecessary(Messages
          .getString("Editor.error.check.save.label"))) { //$NON-NLS-1$
        console.setText(action); //$NON-NLS-1$
        if (currentPanel.getSaveFile() != null) {
          messageTabs.setTitleAt(CONSOLE_TAB, Messages
              .getString("Editor.console.msg.prefix") //$NON-NLS-1$
              + currentPanel.getSaveFile().getName());
        } else {
          messageTabs.setTitleAt(CONSOLE_TAB, Messages
              .getString("Editor.console.newfile.label")); //$NON-NLS-1$
        }
        messageTabs.setSelectedIndex(CONSOLE_TAB);
        beep();
        ok = false;
      } else {
        programTabs.setTitleAt(tab, currentPanel.getSaveFile().getName());
      }
    }
    return ok;
  }

  /**
   * Checks for autosave files, and loads them if requested.
   */
  private void checkForAutoSaveExists() {
    String home = System.getProperty(Messages
        .getString("Editor.user.home.property"))
        + Messages.getString("Editor.local.dir"); //$NON-NLS-1$ //$NON-NLS-2$
    File dir = new File(home);
    File[] fileList = dir.listFiles(new FilenameFilter() {
      public boolean accept(File f, String s) {
        String extension = getExtension(s);
        boolean match = s.startsWith(Messages
            .getString("Editor.editor.autosave.prefix")) //$NON-NLS-1$
            && extension.equalsIgnoreCase(Messages
                .getString("Editor.editor.autosave.extension")); //$NON-NLS-1$
        return match;
      }

      private String getExtension(String s) {
        int length = s.length();
        int i = s.lastIndexOf('.');
        if ((i > 0) && (i < (length - 1))) {
          return s.substring(i + 1).toLowerCase();
        }
        return ""; //$NON-NLS-1$
      }
    });
    for (int i = 0; i < fileList.length; i++) {
      File autoSave = fileList[i];
      Object[] options = {Messages.getString("Editor.autosave.recover.button"),
          Messages.getString("Editor.autosave.delete.button")}; //$NON-NLS-1$ //$NON-NLS-2$
      int choice = JOptionPane.showOptionDialog(mainWindow, Messages
          .getString("Editor.autosave.found.question"), Messages
          .getString("Editor.autosave.found.message"), //$NON-NLS-1$ //$NON-NLS-2$
          JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
          options, options[0]);
      if (choice == JOptionPane.YES_OPTION) {
        try {
          // TODO: Fix the autosave file type
          // Need a default languagekit to make the editing tab
          LanguageKit kit = LanguageKitFactory.getLanguageKit(LanguageKit.TEXT);
          addProgramTab(Messages.getString("Editor.autosave.tab.label"), kit); //$NON-NLS-1$
          openFile(autoSave);
          JOptionPane.showMessageDialog(mainWindow, Messages
              .getString("Editor.autosave.loaded.message")); //$NON-NLS-1$
        } catch (IOException e) {
          if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, Messages
                .getString("Editor.autosave.load.error"), e); //$NON-NLS-1$
          }
        }
      }
      autoSave.delete();
    }
  }

  /**
   * Stops the running process. Attempts to stop the run process in a new Thread
   * (since Process.destroy() can block). Once the Thread is started, the
   * Editor's ProcessWrapper is set to null.
   */
  private void clearRunProcess() {
    if (process == null) {
      return;
    }
    new Thread(new Runnable() {
      public synchronized void run() {
        ProcessWrapper p = process;
        p.stop();
      }
    }, Messages.getString("Editor.stop.thread.name")).start(); //$NON-NLS-1$
    process = null;
  }

  /**
   * DOCUMENT ME!
   * 
   * @param allowCancel
   *          DOCUMENT ME!
   */
  private void closeAction(boolean allowCancel) {
    if (programTabs.getTabCount() == 0) {
      return;
    }
    updateStatus(Messages.getString("Editor.close.action.status.message")); //$NON-NLS-1$
    if (currentProgramTab().close()) {
      programTabs.remove(programTabs.getSelectedComponent());
    }
    if (programTabs.getTabCount() == 0) {
      filesOpen(false);
    }
  }

  private synchronized void compileAction() {
    //compileAction.setEnabled(false);
    programTabs.setEnabled(false);
    final EditPanel tab = currentProgramTab();
    final LanguageKit language = tab.getLanguageKit();
    if (language.hasCompiler() == false) {
      logger.log(Level.WARNING, Messages.getString("Editor.184") //$NON-NLS-1$
          + tab.getSaveFile().getName() + Messages.getString("Editor.185") //$NON-NLS-1$
          + language.getLanguage());
      compileAction.setEnabled(false);
      programTabs.setEnabled(true);
      return;
    }
    if (canCheckForErrors()) {
      tab.setCompiled(false);
      // the Tab fires a message to enable compiling, so need to shut it off
      compileAction.setEnabled(false);
      updateStatus(Messages.getString("Editor.compile.action.status.message")); //$NON-NLS-1$
      mainWindow.getContentPane().remove(statusArea);
      progressBar.setMinimum(0);
      progressBar.setMaximum(100);
      progressBar.setString(Messages
          .getString("Editor.compile.progress.message")); //$NON-NLS-1$
      progressBar.setStringPainted(true);
      progressBar.setIndeterminate(false);
      mainWindow.getContentPane().add(progressBar, BorderLayout.SOUTH);
      mainWindow.validate();
      int lastDivider = splitPane.getLastDividerLocation();
      // If divider is in maximized location, move up to display the console
      if (splitPane.getDividerLocation() >= splitPane
          .getMaximumDividerLocation()) {
        splitPane.setDividerLocation(lastDivider);
      }
      messageTabs.setSelectedIndex(CONSOLE_TAB);
      console.setText(Messages.getString("Editor.empty.string")); //$NON-NLS-1$
      printLineToConsole(Messages.getString("Editor.compile.console.header")); //$NON-NLS-1$
      //Create a timer to control the update of the progressbar
      timer = new javax.swing.Timer(80, new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          progressBar.setValue(language.getCompilerPercentComplete());
          if (language.getCompilerPercentComplete() == 100) {
            Toolkit.getDefaultToolkit().beep();
            timer.stop();
            int counter = 0;
            while (language.getCompilerMessages() == null && counter < 50) {
              try {
                Thread.sleep(100);
                counter++;
              } catch (InterruptedException ie) {
              }
            }
            if (language.getCompilerMessages() == null) {
              logger.log(Level.WARNING,
                  "Finished compiling, but messages still null. Filename: " //$NON-NLS-1$
                      + tab.getSaveFile().getName() + ", type: " //$NON-NLS-1$
                      + language.getLanguage() + ", compiler result: " //$NON-NLS-1$
                      + language.getCompileResult());
              messageTabs.setTitleAt(CONSOLE_TAB, Messages
                  .getString("Editor.console.msg.prefix") //$NON-NLS-1$
                  + tab.getSaveFile().getName());
              if (language.getCompileResult() == LanguageKit.COMPILE_SUCCESS) {
                console
                    .setText(Messages
                        .getString("Editor.compile.action.success.console.message")); //$NON-NLS-1$
              } else {
                console.setText(Messages
                    .getString("Editor.compile.error.console.message")); //$NON-NLS-1$
              }
            } else {
              messageTabs.setTitleAt(CONSOLE_TAB, Messages
                  .getString("Editor.console.msg.prefix") //$NON-NLS-1$
                  + tab.getSaveFile().getName());
              console.setText(language.getCompilerMessages());
            }
            console.setCaretPosition(0);
            mainWindow.getContentPane().remove(progressBar);
            mainWindow.getContentPane().add(statusArea, BorderLayout.SOUTH);
            statusArea.setText(Messages
                .getString("Editor.compile.action.status.complete")); //$NON-NLS-1$
            tab
                .setCompiled(language.getCompileResult() == LanguageKit.COMPILE_SUCCESS);
            programTabs.setEnabled(true);
            mainWindow.validate();
          }
        }
      });
      language.compile(tab.getSaveFile());
      timer.start();
    } else {
      compileAction.setEnabled(language.hasCompiler());
      programTabs.setEnabled(true);
    }
  }

  /**
   * Copies the highlighted area to the clipboard.
   */
  private void copyAction() {
    JComponent area = minorFocusArea();
    if (area != null) {
      if (area instanceof JEditTextArea) {
        ((JEditTextArea)area).copy();
      } else if (area instanceof JTextComponent) {
        ((JTextComponent)area).copy();
      }
    } else {
      logger.log(Level.INFO, Messages.getString("Editor.203")); //$NON-NLS-1$
    }
  }

  /** Initializes the console panel. */
  private void createConsole() {
    console = new JTextPane();
    console.setFont(commonFont);
    console.setEditable(false);
    HTMLEditorKit kit = new PatchedHTMLEditorKit();
    HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
    console.setEditorKit(kit);
    console.setContentType(Messages.getString("Editor.default.content.type")); //$NON-NLS-1$
    console.setDocument(doc);
    console.setText(Messages.getString("Editor.console.html.welcome")); //$NON-NLS-1$
    console.addFocusListener(new MajorFocusListener(console));
    console.addMouseListener(mouseAction);
    setSpecialKeys(console);
  }

  /** Initializes the description pane */
  private void createDescriptionArea() {
    descriptionArea = new JTextArea(20, 80);
    descriptionArea.setFont(commonFont);
    descriptionArea.setEditable(false);
    descriptionArea.setText(description);
    descriptionArea.addFocusListener(new MajorFocusListener(descriptionArea));
    setSpecialKeys(descriptionArea);
  }

  /**
   * Populates the Edit menu.
   */
  private void createEditMenu() {
    JMenu menu;
    menu = new JMenu(Messages.getString("Editor.menu.edit")); //$NON-NLS-1$
    menu.setMnemonic(KeyEvent.VK_E);
    JMenuItem item;
    //undoAction = new UndoAction();
    //item = menu.add(undoAction);
    item = menu.add(undoAction);
    item.setIcon(null);
    menu.add(item);
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
    menuItem(menu, Messages.getString("Editor.menu.edit.select.all"),
        KeyEvent.VK_A, "ctrl A"); //$NON-NLS-1$ //$NON-NLS-2$
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

  /**
   * Populates the File menu.
   * 
   * @return DOCUMENT ME!
   */
  private JMenu createFileMenu() {
    JMenu menu = new JMenu(Messages.getString("Editor.menu.file")); //$NON-NLS-1$
    menu.setMnemonic(KeyEvent.VK_F);
    newMenu = new JMenu();
    newMenu.setMnemonic(KeyEvent.VK_N);
    newMenu.setText(Messages.getString("Editor.menu.file.new")); //$NON-NLS-1$
    newMenu.setIcon(null);
    menu.add(newMenu);
    JMenuItem item;
    newJavaMenuItem = new JMenuItem(newAction);
    newJavaMenuItem.setText(Messages.getString("Editor.menu.file.new.java")); //$NON-NLS-1$
    newJavaMenuItem.setIcon(null);
    newMenu.add(newJavaMenuItem);
    newHTMLMenuItem = new JMenuItem(newAction);
    newHTMLMenuItem.setText(Messages.getString("Editor.menu.file.new.xhtml")); //$NON-NLS-1$
    newHTMLMenuItem.setIcon(null);
    newMenu.add(newHTMLMenuItem);
    newCSSMenuItem = new JMenuItem(newAction);
    newCSSMenuItem.setText(Messages.getString("Editor.menu.file.new.css")); //$NON-NLS-1$
    newCSSMenuItem.setIcon(null);
    newMenu.add(newCSSMenuItem);
    newTextMenuItem = new JMenuItem(newAction);
    newTextMenuItem.setText(Messages.getString("Editor.menu.file.new.text")); //$NON-NLS-1$
    newTextMenuItem.setIcon(null);
    newMenu.add(newTextMenuItem);
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
    menuItem(menu, Messages.getString("Editor.menu.file.exit"), KeyEvent.VK_X,
        null); //$NON-NLS-1$
    return menu;
  }

  private void createHelpMenu() {
    JMenu menu;
    menu = new JMenu(Messages.getString("Editor.menu.help")); //$NON-NLS-1$
    menu.setMnemonic(KeyEvent.VK_H);
    menuItem(menu, Messages.getString("Editor.menu.help.editor.help"),
        KeyEvent.VK_H, "F1"); //$NON-NLS-1$ //$NON-NLS-2$
    menuItem(menu, Messages.getString("Editor.menu.help.course.handbook"),
        KeyEvent.VK_C, null); //$NON-NLS-1$
    menuItem(menu, Messages.getString("Editor.menu.help.about"), KeyEvent.VK_A,
        null); //$NON-NLS-1$
    try {
      loggerMenuItem = new JCheckBoxMenuItem(Messages
          .getString("Editor.menu.help.logging"), false); //$NON-NLS-1$
      loggerMenuItem.addActionListener(myActionListener);
    } catch (java.awt.HeadlessException he) {
      loggerMenuItem = null;
    }
    menu.add(loggerMenuItem);
    menuBar.add(menu);
  }

  private void createHelpWindow() {
    try {
      helpWindow = new TestBrowser(Messages
          .getString("Editor.help.relative.url"), //$NON-NLS-1$
          Messages.getString("Editor.help.browser.title"), 500, 600); //$NON-NLS-1$
      helpWindow.setVisible(false); // redundant since default
      //    helpWindow.setIconImage(icon);
      helpWindow.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          helpWindow.setVisible(false);
        }

        public void windowIconified(WindowEvent e) {
          helpWindow.setVisible(false);
        }
      });
      helpWindow.setLocation(200, 0);
      helpWindow.pack();
    } catch (Exception exn) {
      logger.log(Level.WARNING, "Couldn't initialize help window.", exn); //$NON-NLS-1$
      helpWindow = null;
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

  /**
   * DOCUMENT ME!
   */
  private void createToolsMenu() {
    JMenu menu;
    menu = new JMenu(Messages.getString("Editor.menu.tools")); //$NON-NLS-1$
    menu.setMnemonic(KeyEvent.VK_T);
    JMenuItem item;
    item = new JMenuItem(formatAction);
    item.setIcon(null);
    menu.add(item);
    reformatSettingsMenuItem = menuItem(menu, Messages
        .getString("Editor.menu.tools.reformat.settings"), //$NON-NLS-1$
        KeyEvent.VK_E, null);
    item = new JMenuItem(compileAction);
    item.setIcon(null);
    menu.add(item);
    item = new JMenuItem(runAction);
    item.setIcon(null);
    menu.add(item);
    item = menuItem(menu, Messages.getString("Editor.menu.tools.submit"),
        KeyEvent.VK_S, "F11"); //$NON-NLS-1$ //$NON-NLS-2$
    item.setEnabled(false);
    testMenuItem = menuItem(menu, Messages
        .getString("Editor.menu.tools.run.tests"), KeyEvent.VK_T, null); //$NON-NLS-1$
    testMenuItem.setEnabled(false);
    item = new JMenuItem(haltAction);
    item.setIcon(null);
    menu.add(item);
    menu.addSeparator();
    item = menuItem(menu, Messages.getString("Editor.menu.tools.deploy.web"),
        KeyEvent.VK_D, "alt D"); //$NON-NLS-1$ //$NON-NLS-2$
    menu.addSeparator();
    menuBar.add(menu);
    toolMenu = menu;
  }

  /**
   * DOCUMENT ME!
   */
  private void createViewMenu() {
    JMenu menu;
    menu = new JMenu(Messages.getString("Editor.menu.view")); //$NON-NLS-1$
    menu.setMnemonic(KeyEvent.VK_V);
    menuItem(menu, Messages.getString("Editor.menu.view.webpage"),
        KeyEvent.VK_V, "alt W"); //$NON-NLS-1$ //$NON-NLS-2$
    menu.addSeparator();
    menuItem(menu, Messages.getString("Editor.menu.view.options"),
        KeyEvent.VK_O, null); //$NON-NLS-1$
    menuBar.add(menu);
  }

  /**
   * DOCUMENT ME!
   * 
   * @param file
   *          DOCUMENT ME!
   * @return DOCUMENT ME!
   */
  private ImageIcon createToolBarIcon(String file) {
    // Get current classloader
    ClassLoader cl = this.getClass().getClassLoader();
    java.net.URL url = cl.getResource(file);
    ImageIcon icon = new ImageIcon(url);
    return icon;
  }

  private EditPanel currentProgramTab() {
    return (EditPanel) (programTabs.getSelectedComponent());
  }

  /**
   * DOCUMENT ME!
   */
  private void cutAction() {
    JComponent area = minorFocusArea();
    if (area instanceof JEditTextArea) {
      ((JEditTextArea)area).cut();
    } else if (area instanceof JTextComponent) {
      ((JTextComponent)area).cut();
    }
  }

  /**
   * DOCUMENT ME!
   * 
   * @param msg
   *          DOCUMENT ME!
   */
  private void debug(String msg) {
    // System.out.println(msg);
    Document doc = console.getDocument();
    //Load the text pane with styled text.
    try {
      doc.insertString(doc.getLength(), msg + "\n", null); //$NON-NLS-1$
    } catch (BadLocationException ble) {
      System.err.println(Messages
          .getString("Editor.document.initialization.message")); //$NON-NLS-1$
    }
    // make it scroll, if necessary
    int pos = doc.getLength() - 1;
    console.setCaretPosition(pos);
  }

  private void deployWeb() {
    currentProgramTab().deployWeb();
  }

  /**
   * DOCUMENT ME!
   */
  private void dumpInputMap() {
    debug("-------BEGIN LOCAL KEYS------------"); //$NON-NLS-1$
    InputMap map = getActiveProgramArea().getInputMap(JComponent.WHEN_FOCUSED);
    KeyStroke[] keys = map.keys();
    for (int i = 0; i < keys.length; i++) {
      debug(i + ": " + keys[i].toString() + " = " + map.get(keys[i]).toString()); //$NON-NLS-1$ //$NON-NLS-2$
    }
    debug("-------BEGIN ALL KEYS------------"); //$NON-NLS-1$
    keys = getActiveProgramArea().getInputMap(JComponent.WHEN_FOCUSED)
        .allKeys();
    for (int i = 0; i < keys.length; i++) {
      debug(i + ": " + keys[i].toString() + " = " + map.get(keys[i]).toString()); //$NON-NLS-1$ //$NON-NLS-2$
    }
    debug("-------BEGIN LOCAL ACTIONS------------"); //$NON-NLS-1$
    Object[] objs = getActiveProgramArea().getActionMap().keys();
    for (int i = 0; i < objs.length; i++) {
      debug(i + ": " + objs[i].toString()); //$NON-NLS-1$
    }
    debug("-------BEGIN ALL ACTIONS------------"); //$NON-NLS-1$
    objs = getActiveProgramArea().getActionMap().keys();
    for (int i = 0; i < objs.length; i++) {
      debug(i + ": " + objs[i].toString()); //$NON-NLS-1$
    }
  }

  /**
   * DOCUMENT ME!
   */
  private void enableLoggingAction() {
    if (loggerMenuItem.getState()) {
      logger.setLevel(Level.CONFIG);
    } else {
      logger.setLevel(Level.WARNING);
    }
  }

  /**
   * DOCUMENT ME!
   */
  private void exitAction() {
    updateStatus(Messages.getString("Editor.exit.action.status.message")); //$NON-NLS-1$
    int errors = 0;
    while ((errors == 0) && (programTabs.getTabCount() > 0)) {
      programTabs.setSelectedIndex(0);
      int exitValue = ((EditPanel) programTabs.getComponent(0)).exit();
      errors += exitValue;
      if (exitValue == 0) {
        programTabs.remove(0);
      }
    }
    String logName = System.getProperty(Messages
        .getString("Editor.user.home.property"), "")
        + //$NON-NLS-1$ //$NON-NLS-2$
        Messages.getString("Editor.log.file.name"); //$NON-NLS-1$
    File logFile = new File(logName);
    String[] logs = {logName};
    // Assume that an empty log will always be less that 200 bytes
    if (logFile.exists() && (logFile.length() > 200)) {
      EMail.sendMsgAttachFile(Messages.getString("Editor.maintainer.email"),
          Messages.getString("Editor.error.log.email.header"), //$NON-NLS-1$ //$NON-NLS-2$
          Messages.getString("Editor.error.log.email.body"), logs); //$NON-NLS-1$
    }
    if (errors == 0) {
      System.exit(0);
    }
  }

  private void filesOpen(boolean oneOpen) {
    closeAction.setEnabled(oneOpen);
    printAction.setEnabled(oneOpen);
    saveAsAction.setEnabled(oneOpen);
    gotoAction.setEnabled(oneOpen);
    findAction.setEnabled(oneOpen);
    replaceAction.setEnabled(oneOpen);
  }

  /**
   * Finds the specified pattern in the current editing area.
   * 
   * @param pattern The String pattern to find.
   * @param isCaseSensitive <code>true</code> specifies that it must be an exact match. 
   * @param matchWholeWord <code>true</code> matches whole word boundaries only.
   */
  private void find(String pattern, boolean isCaseSensitive,
      boolean matchWholeWord) {
    if ((pattern == null) || (pattern.length() == 0)) {
      updateStatus(Messages.getString("Editor.find.status.error.message")); //$NON-NLS-1$
      beep();
      return;
    }
    getActiveProgramArea().requestFocus();
    if (logger.isLoggable(Level.FINE)) {
      logger.fine(Messages.getString("Editor.find.start.message") + pattern); //$NON-NLS-1$
    }
    if (!getActiveProgramArea().find(pattern, isCaseSensitive, matchWholeWord)) {
      updateStatus(Messages.getString("Editor.find.unsuccessful.message")); //$NON-NLS-1$
      beep();
    }
    if (logger.isLoggable(Level.FINE)) {
      logger.fine(Messages.getString("Editor.find.complete.message")); //$NON-NLS-1$
    }
  }

  /**
   * When the <em>Search</em> button is pressed, execute this action to find
   * the text specified.
   */
  private void findAction() {
    if (replaceDialog == null) {
      replaceDialog = new ReplaceDialog(mainWindow, false, findListener);
    }
    replaceDialog.openFindDialog();
  }

  /**
   * DOCUMENT ME!
   */
  private void formatSettingsAction() {
    if (currentProgramTab() != null)
      currentProgramTab().formatSettings();
  }

  /**
   * DOCUMENT ME!
   */
  private void gotoAction() {
    updateStatus(Messages.getString("Editor.goto.action.status.message")); //$NON-NLS-1$
    int maxLine = getActiveProgramArea().getLineCount();
    String line = JOptionPane.showInputDialog(mainWindow, "Enter line (1-" //$NON-NLS-1$
        + maxLine + ")",
        Messages.getString("Editor.goto.action.message.label"),
        JOptionPane.QUESTION_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
    if ((line == null) || (line.length() == 0)) {
      return;
    }
    int n;
    try {
      n = Integer.parseInt(line);
    } catch (Exception e) {
      updateStatus(Messages.getString("Editor.goto.error.status.message")); //$NON-NLS-1$
      return;
    }
    gotoLine(n);
  }

  private void gotoLine(int line) {
    currentProgramTab().gotoLine(line);
  }

  /**
   * DOCUMENT ME!
   */
  private void haltAction() {
    if (process == null) {
      return;
    }
    if (logger.isLoggable(Level.INFO)) {
      logger.log(Level.INFO, Messages
          .getString("Editor.process.kill.log.message")); //$NON-NLS-1$
    }
    process.stop();
    process = null;
    if (logger.isLoggable(Level.INFO)) {
      logger.log(Level.INFO, Messages
          .getString("Editor.process.killed.log.message")); //$NON-NLS-1$
    }
    updateStatus(Messages.getString("Editor.halt.action.status.message")); //$NON-NLS-1$
  }

  /**
   * DOCUMENT ME!
   */
  private void helpAction() {
    updateStatus(Messages.getString("Editor.help.action.status.message")); //$NON-NLS-1$
    if (helpWindow != null) {
      helpWindow.setVisible(true);
    }
    initHelp();
//    if (helpWindow != null) {
//      ((TestBrowser) helpWindow).setURL(Messages
//          .getString("Editor.help.relative.url")); //$NON-NLS-1$
//      helpWindow.setVisible(true);
//      if (helpWindow.getState() != JFrame.NORMAL) {
//        helpWindow.setState(JFrame.NORMAL);
//      }
//    } else {
//      updateStatus(Messages.getString("Editor.help.error.message")); //$NON-NLS-1$
//    }
  }

  /**
   * 
   */
  private void initHelp() {
    JHelp help = new JHelp();
    String helpsetName = "editor.hs";
    try {
      ClassLoader cl = Editor.class.getClassLoader();
      URL url = HelpSet.findHelpSet(cl, helpsetName);
      HelpSet set = new HelpSet(cl, url);

      help = new JHelp(set);
      JFrame window = new JFrame("IT Editor Help");
      window.getContentPane().add(help);
      window.pack();
    } catch (HelpSetException hse) {
      hse.printStackTrace();
    }
  }

  /**
   * DOCUMENT ME!
   */
  private void initializeFileArea() {
    String home = System.getProperty(Messages
        .getString("Editor.user.home.property")); //$NON-NLS-1$
    File dir = new File(home, ".it105editor"); //$NON-NLS-1$
    // mkdirs Returns a boolean, but we don't use it yet
    dir.mkdirs();
  }

  /**
   * DOCUMENT ME!
   */
  private void initializeFileChooser() {
    new Thread("FileChooser_init") { //$NON-NLS-1$
      public void run() {
        JFileChooser tempChooser = new JFileChooser();
        File documents = new File(Messages
            .getString("Editor.default.cadet.document.dir")); //$NON-NLS-1$
        if (documents.exists()) {
          tempChooser.setCurrentDirectory(documents);
        } else {
          documents = new File(System.getProperty(Messages
              .getString("Editor.user.home.property")) //$NON-NLS-1$
              + Messages.getString("Editor.alternate.document.dir")); //$NON-NLS-1$
          if (documents.exists()) {
            tempChooser.setCurrentDirectory(documents);
          } else {
            tempChooser.setCurrentDirectory(new File(System
                .getProperty(Messages.getString("Editor.user.home.property")))); //$NON-NLS-1$
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
      } catch (InterruptedException e) {
      }
    }
    openAction.setEnabled(true);
  }

  /**
   * DOCUMENT ME!
   */
  private void initializeGUI() {
    mainWindow = new JFrame();
    mainWindow.setResizable(true);
    mainWindow.getContentPane().setLayout(new BorderLayout());
    mainWindow.setTitle(Messages.getString("Editor.title")); //$NON-NLS-1$
    //mainWindow.setIconImage(icon);
    mainWindow.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exitAction();
      }
    });
    // Now ensure the window listener is the only action executed
    mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    createMenu();
    createDescriptionArea();
    createConsole();
    consolePane = new JPanel();
    consolePane.setLayout(new BoxLayout(consolePane, BoxLayout.Y_AXIS));
    JScrollPane consoleScroll = new JScrollPane(console);
    consolePane.add(consoleScroll);
    JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
    messageTabs = new JTabbedPane();
    messageTabs.addTab(Messages.getString("Editor.description.tab.title"),
        descriptionScroll); //$NON-NLS-1$
    messageTabs.addTab(Messages.getString("Editor.console.tab.title"),
        consolePane); //$NON-NLS-1$
    messageTabs.setSelectedIndex(CONSOLE_TAB);
    messageTabs.setMinimumSize(new Dimension(300, 0));
    messageTabs.setPreferredSize(new Dimension(600, 150));
    programTabs = new JTabbedPane();
    programTabs.addFocusListener(new MajorFocusListener(programTabs));
    programTabs.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent evt) {
        JTabbedPane pane = (JTabbedPane) evt.getSource();
        Component c = pane.getSelectedComponent();
        if (c != null && c instanceof EditPanel) {
//          ((EditPanel) c).getProgramArea().requestFocusInWindow();
          c.requestFocusInWindow();
          setPanelActions((EditPanel) c);
          //pane.requestFocusInWindow();
        }
      }
    });
    setSpecialKeys(programTabs);
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, programTabs,
        messageTabs);
    splitPane.setOneTouchExpandable(true);
    splitPane.setResizeWeight(0.8);
    statusArea = new JLabel(Messages.getString("Editor.status.welcome.message")); //$NON-NLS-1$
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

  /**
   * Creates a popup menu. Assumes that all standard menu items have been
   * initialized.
   */
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

  /**
   * DOCUMENT ME!
   */
  private void initializeToolBar() {
    toolBar = new JToolBar(Messages.getString("Editor.toolbar.label")); //$NON-NLS-1$
    //first button
    JButton button = null;
    // Get current classloader
    ClassLoader cl = this.getClass().getClassLoader();
    ImageIcon[] pics = {createToolBarIcon("eecs/editor/images/New24.gif"),
        createToolBarIcon("eecs/editor/images/NewHTML24.GIF"),
        createToolBarIcon("eecs/editor/images/NewCSS24.GIF"),
        createToolBarIcon("eecs/editor/images/NewJava24.GIF"),
        createToolBarIcon("eecs/editor/images/New24.gif")};
    String[] strings = {"New", "XHTML Page", "Style Sheet", "Java Program",
        "Text File"};
    Integer[] intArray = new Integer[pics.length];
    for (int i = 0; i < pics.length; i++) {
      intArray[i] = new Integer(i);
      if (i > 0)
        pics[i].setDescription(strings[i]);
    }
    JComboBox newBox = new JComboBox(intArray);
    ComboBoxRenderer renderer = new ComboBoxRenderer(pics, strings);
    renderer.setPreferredSize(new Dimension(100, 36));
    newBox.setRenderer(renderer);
    newBox.setMaximumRowCount(8);
    newBox.setMaximumSize(new Dimension(130, 36));
    newBox.setToolTipText(Messages.getString("Editor.new.tooltip")); //$NON-NLS-1$
    // Create and register listener
    newBox.addActionListener(new ActionListener() {
      // This method is called only if a new item has been selected.
      public void actionPerformed(ActionEvent evt) {
        JComboBox cb = (JComboBox) evt.getSource();
        int item = cb.getSelectedIndex();
        switch (item) {
          case 0 :
            break;
          case 1 :
            newAction(LanguageKit.HTML);
            break;
          case 2 :
            newAction(LanguageKit.CSS);
            break;
          case 3 :
            newAction(LanguageKit.JAVA);
            break;
          default :
            newAction(LanguageKit.TEXT);
        }
      }
    });
    toolBar.add(newBox);
    button = new JButton(openAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    button = new JButton(saveAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    button = new JButton(saveAsAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    button = new JButton(printAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    toolBar.addSeparator();
    button = new JButton(copyAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    button = new JButton(cutAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    button = new JButton(pasteAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    undoButton = new JButton(undoAction);
    undoButton.setText("");
    toolBar.add(undoButton);
    button = new JButton(new ImageIcon(cl
        .getResource("eecs/editor/images/Redo24.gif")));
    button.setToolTipText(Messages.getString("Editor.redo.tooltip")); //$NON-NLS-1$
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
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    toolBar.addSeparator();
    button = new JButton(compileAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    button = new JButton(runAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    button = new JButton(haltAction);
    button.setText(""); //$NON-NLS-1$
    toolBar.add(button);
    toolBar.addSeparator();
    button = new JButton(submitAction);
    button.setText("");
    toolBar.add(button);
    setButtonSizes();
  }

  /**
   * DOCUMENT ME!
   */
  private void initLogging() {
    String subDir = Messages.getString("Editor.local.dir");
    String logName = Messages.getString("Editor.log.file.name");
    String homePath = System.getProperty(Messages.getString("Editor.user.home.property"), "") + subDir; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    FileManager fm = new FileManager();
    fm.deleteFilesStartingWith(homePath, logName); //$NON-NLS-1$
    try {
      logHandler = new FileHandler("%h" + subDir + logName); //$NON-NLS-1$
    } catch (IOException e) {
      logger.warning("Couldn't initialized FileHandler for the logger!"); //$NON-NLS-1$
    }
    logger.addHandler(logHandler);
    if (useLogging) {
      logger.setLevel(Level.CONFIG);
    } else {
      logger.setLevel(Level.WARNING);
    }
    if (logger.isLoggable(Level.INFO)) {
      logger.info("Started Editor"); //$NON-NLS-1$
    }
  }

  /**
   * DOCUMENT ME!
   * 
   * @return DOCUMENT ME!
   */
  private JComponent majorFocusArea() {
    return majorFocus;
  }

  /**
   * DOCUMENT ME!
   * 
   * @param menu
   *          DOCUMENT ME!
   * @param name
   *          DOCUMENT ME!
   * @param keyEvent
   *          DOCUMENT ME!
   * @param accelerator
   *          DOCUMENT ME!
   * @return DOCUMENT ME!
   */
  private JMenuItem menuItem(JMenu menu, String name, int keyEvent,
      final String accelerator) {
    JMenuItem item = new JMenuItem(name, keyEvent);
    item.setActionCommand(name);
    if (accelerator != null) {
      item.setAccelerator(KeyStroke.getKeyStroke(accelerator));
    }
    item.addActionListener(myActionListener);
    menu.add(item);
    return item;
  }

  /**
   * DOCUMENT ME!
   * 
   * @return DOCUMENT ME!
   */
  private JComponent minorFocusArea() {
    return minorFocus;
  }

  /**
   * DOCUMENT ME!
   * 
   * @param type
   *          DOCUMENT ME!
   */
  private void newAction(String type) {
    updateStatus(Messages.getString("Editor.new.action.status.message")); //$NON-NLS-1$
    LanguageKit kit = LanguageKitFactory.getLanguageKit(type);
    addProgramTab(Messages.getString("Editor.programtab.new.file.prefix")
        + type + "[" + (newFileNum++) + "]", kit); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    messageTabs.setSelectedIndex(CONSOLE_TAB);
    filesOpen(true);
//    getActiveProgramArea().requestFocusInWindow();
    currentProgramTab().requestFocusInWindow();
  }

  /**
   * DOCUMENT ME!
   */
  private void openAction() {
    if (chooser == null) {
      updateStatus(Messages.getString("Editor.open.filechooser.error.message")); //$NON-NLS-1$
      logger.warning(Messages.getString("Editor.open.filechooser.error.log.message")); //$NON-NLS-1$
      return;
    }
    updateStatus(Messages.getString("Editor.open.action.status.message")); //$NON-NLS-1$
    chooser.setDialogTitle(Messages.getString("Editor.open.dialog.title")); //$NON-NLS-1$
    chooser.setApproveButtonText(Messages.getString("Editor.open.button.text")); //$NON-NLS-1$
    switch (chooser.showOpenDialog(mainWindow)) {
      case JFileChooser.CANCEL_OPTION :
      case JFileChooser.ERROR_OPTION :
        updateStatus(Messages.getString("Editor.open.cancelled.status.message")); //$NON-NLS-1$
        return;
      case JFileChooser.APPROVE_OPTION :
        try {
          openFile(chooser.getSelectedFile());
        } catch (Exception e) {
          logger.log(Level.WARNING, "Error opening file.", e);
          updateStatus(Messages.getString("Editor.open.error.status.message")); //$NON-NLS-1$
        }
        currentProgramTab().requestFocusInWindow();
        return;
      default :
        updateStatus(Messages
            .getString("Editor.open.unknownerror.status.message")); //$NON-NLS-1$
        return;
    }
  }

  private void openFile(File file) throws IOException {
    LanguageKit kit = LanguageKitFactory.getLanguageKit(getFileType(file
        .getName()));
    addProgramTab(file.getName(), kit);
    if (currentProgramTab().openAction(file)) {
      updateStatus(Messages.getString("Editor.open.filename.status.message")
          + file.getCanonicalPath()); //$NON-NLS-1$
      addMRUMenuItem(file.getAbsolutePath());
      filesOpen(true);
    } else {
      updateStatus(Messages.getString("Editor.open.error.status.message") + file.getCanonicalPath());
    }
  }

  /**
   * DOCUMENT ME!
   */
  private void openHandbookAction() {
    updateStatus(Messages.getString("Editor.handbook.open.message")); //$NON-NLS-1$
    BrowserControl.displayURL(handbookURL);
  }

  /**
   * Opens a new Problem (ICE), which includes a description, code template, and
   * (optionally) test cases to run automated testing.
   */
  private void openProblemAction() {
    if (currentProblem != null) {
      if (JOptionPane.showConfirmDialog(null, Messages
          .getString("Editor.problem.already.open.message")) != JOptionPane.YES_OPTION) { //$NON-NLS-1$
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
          LanguageKit kit = LanguageKitFactory.getLanguageKit(problem
              .getLanguage());
          addProgramTab(Messages
              .getString("Editor.program.tab.new.type.name.prefix")
              + kit.getLanguage(), kit); //$NON-NLS-1$
          filesOpen(true);
        }
      } catch (Exception e) {
        logger.log(Level.WARNING, "Error opening problem file.", e);
        printLineToConsole(Messages
            .getString("Editor.problemset.error.console.message")); //$NON-NLS-1$
        updateStatus(Messages
            .getString("Editor.problemset.error.status.message")); //$NON-NLS-1$
      }
    } else {
      updateStatus(Messages.getString("Editor.problemset.cancel.message")); //$NON-NLS-1$
      return;
    }
    if (problem == null) {
      updateStatus(Messages.getString("Editor.problemset.load.error.message")); //$NON-NLS-1$
      return;
    }
    currentProgramTab().setText(problem.getTemplate());
    descriptionArea.setText(problem.getDescription());
    descriptionArea.setLineWrap(true);
    descriptionArea.setWrapStyleWord(true);
    descriptionArea.setCaretPosition(0);
    messageTabs.setSelectedIndex(DESCRIPTION_TAB);
    StringBuffer status = new StringBuffer(Messages
        .getString("Editor.problemset.open.status.message")
        + problem.getTitle()); //$NON-NLS-1$
    testMenuItem.setEnabled(false);
    if (!problem.isTestable()) {
      status.append(Messages.getString("Editor.problemset.untestable.message")); //$NON-NLS-1$
    }
    updateStatus(status.toString());
    currentProblem = problem;
    currentProblemTab = currentProgramTab();
    if (problem.getExternalLink() != null) {
      BrowserControl.displayURL(problem.getExternalLink());
    }
  }

  /**
   * Action when paste is selected. Pastes the contents of the clipboard into
   * the current location in the document.
   */
  private void pasteAction() {
    JComponent area = minorFocusArea();
    if (area != null) {
      if (area instanceof JEditTextArea) {
        ((JEditTextArea)area).paste();
      } else if (area instanceof JTextComponent) {
        ((JTextComponent)area).paste();
      }
    } else {
      logger.log(Level.INFO, Messages.getString("Editor.203")); //$NON-NLS-1$
    }
  }

  /**
   * Paste at the location of the mouse click. This should only be called as a
   * result of a Popup menu request, otherwise the paste location will not be
   * consistent with expected results.
   */
  private void pastePopupAction() {
    // XXX: Fix this to work with JEditTextArea
    JComponent area = minorFocusArea();
    if (area != null) {
      if (area instanceof JEditTextArea) {
        JEditTextArea editArea = (JEditTextArea)area;
        
        int offset = editArea.xyToOffset(mouseLocation.x, mouseLocation.y);
        int dot = editArea.getSelectionStart();
        int mark = editArea.getSelectionEnd();
        if ((offset < dot && offset < mark) || offset > dot && offset > mark) {
            editArea.select(offset,offset);
        }
        editArea.paste();        
      }
    } else {
      logger.log(Level.INFO, Messages.getString("Editor.203")); //$NON-NLS-1$
    }
//    JTextComponent area = minorFocusArea();
//    if ((area != null) && area.isEditable()) {
//      // Move the caret to the clicked position
//      int offset = area.viewToModel(mouseLocation);
//      Caret c = area.getCaret();
//  if ((offset < c.getDot() && offset < c.getMark()) || offset > c.getDot()
//  && offset > c.getMark()) {
//area.getCaret().setDot(offset);
//}
//area.paste();
//    }
  }

  /**
   * Preview the current document
   */
  private void previewAction() {
    currentProgramTab().previewWeb();
  }

  private void previewDeployed() {
    String name = System.getProperty(Messages.getString("Editor.user.name.property.key"), 
        Messages.getString("Editor.user.name.default")); //$NON-NLS-1$ //$NON-NLS-2$
    if (name.equals(Messages.getString("Editor.user.name.default"))) { //$NON-NLS-1$
      return;
    }
    String url = Messages.getString("Editor.url.protocol.prefix")
        + name.toLowerCase(); //$NON-NLS-1$
    BrowserControl.displayURL(url);
  }

  /**
   * DOCUMENT ME!
   */
  private void printAction() {
    programTabs.setEnabled(false);
    int tabNum = programTabs.getSelectedIndex();
    EditPanel tab = currentProgramTab();
    updateStatus(Messages.getString("Editor.print.action.status.message")); //$NON-NLS-1$
    String title = Messages.getString("Editor.print.action.newfile.title"); //$NON-NLS-1$
    try {
      title = tab.getSaveFile().getCanonicalPath();
    } catch (Exception exn) {
      // So far, the only cause for this exception is that the file has not been
      // saved
      logger.log(Level.WARNING, "Problem getting title of file for tab "
          + programTabs.getTitleAt(tabNum) + ".", exn);
    }
    PrintText.printText(title, tab.getProgramArea().getText(), true);
    programTabs.setEnabled(true);
  }

  /**
   * DOCUMENT ME!
   * 
   * @param msg
   *          DOCUMENT ME!
   */
  private void printLineToConsole(String msg) {
    printToConsole(msg + "\n"); //$NON-NLS-1$
  }

  /**
   * DOCUMENT ME!
   * 
   * @param msg
   *          DOCUMENT ME!
   */
  private void printToConsole(String msg) {
    Document doc = console.getDocument();
    //Load the text pane with styled text.
    try {
      doc.insertString(doc.getLength(), msg, null);
    } catch (BadLocationException ble) {
      if (logger.isLoggable(Level.INFO)) {
        logger.info("Couldn't print to console.");
      }
    }
    console.setCaretPosition(doc.getLength() - 1);
  }

  /**
   * DOCUMENT ME!
   */
  private void propertiesAction() {
    if (propertiesDialog == null) {
      propertiesDialog = new PropertyDialog(mainWindow, true);
    }
    //propertiesDialog.setTextSize(getActiveProgramArea().getTextSize());
    propertiesDialog.setTextSize(prefs.getInt(TEXT_SIZE_KEY, 12));
    //propertiesDialog.setTabSize(getActiveProgramArea().getTabSize());
    propertiesDialog.setTabSize(prefs.getInt(TAB_SIZE_KEY, 4));
    propertiesDialog.setAutoSaveTime(timeForAutoSave);
    propertiesDialog.pack();
    propertiesDialog.setVisible(true);
    prefs.putInt(AUTOSAVE_TIME_KEY, propertiesDialog.getAutoSaveTime());
    prefs.putInt(TAB_SIZE_KEY, propertiesDialog.getTabSize());
    prefs.putInt(TEXT_SIZE_KEY, propertiesDialog.getTextSize());
    // The editor window and main properties are separated
    updateProperties();
  }

  /**
   * Reformats the document according to the reformatting properties of the LanguageKit.
   */
  private void formatAction() {
    EditPanel currentPanel = currentProgramTab();
    LanguageKit languageKit = currentPanel.getLanguageKit();
    int result = currentPanel.reformat();
    if (result == LanguageKit.REFORMAT_SUCCESS) {
      updateStatus(Messages.getString("Editor.reformat.success.status.message")); //$NON-NLS-1$
    } else if (result == LanguageKit.REFORMAT_WARNINGS) {
      updateStatus(Messages.getString("Editor.reformat.warning.status.message")); //$NON-NLS-1$
      console.setText(languageKit.getFormatMessages().toString());
    } else if (result == LanguageKit.REFORMAT_ERRORS) {
      updateStatus(Messages.getString("Editor.reformat.error.status.message")); //$NON-NLS-1$
      console.setText(languageKit.getFormatMessages().toString());
    } else { // NOT_SUPPORTED
      updateStatus(Messages.getString("Editor.reformat.notsupported.message")); //$NON-NLS-1$
      beep();
      console.setText(""); //$NON-NLS-1$
    }
    beep();
    messageTabs.setSelectedIndex(CONSOLE_TAB);
  }

  /**
   * Replaces text in the active editing pane.
   * 
   * @param pattern The pattern to find.
   * @param content The String text to replace with.
   * @param isCaseSensitive a boolean that is <code>true</code> if this is a case sensitive search.
   * @param matchWords a boolean that is <code>true</code> if this search should look for whole words only.
   * @param replaceAll a boolean that is <code>true</code> if all occurrences should be replaced.
   */
  private void replace(String pattern, String content, boolean isCaseSensitive,
      boolean matchWords, boolean replaceAll) {
    String replacement = content;
    if ((pattern == null) || (pattern.length() == 0)) {
      updateStatus(Messages.getString("Editor.replace.nostring.message")); //$NON-NLS-1$
      beep();
      return;
    }
    if (replacement == null) {
      replacement = ""; //$NON-NLS-1$
    }
    if (logger.isLoggable(Level.INFO)) {
      logger.info("Performing replace.");
    }
    if (replaceAll) {
      if (!getActiveProgramArea().replaceAll(pattern, replacement,
          isCaseSensitive, matchWords)) {
        beep();
      }
    } else if (!getActiveProgramArea().replace(pattern, replacement,
        isCaseSensitive, matchWords)) {
      beep();
    }
  }

  /**
   * DOCUMENT ME!
   */
  private void replaceAction() {
    if (replaceDialog == null) {
      replaceDialog = new ReplaceDialog(mainWindow, false, findListener);
    }
    replaceDialog.openReplaceDialog();
  }

  /**
   * DOCUMENT ME!
   */
  private void runAction() {
    programTabs.setEnabled(false);
    currentProgramTab().setEnabled(false);
    if (process != null) {
      clearRunProcess();
      //      return;
    }
    final EditPanel runningPanel = currentProgramTab();
    if (runningPanel.getLanguageKit().isRunnable()) {
      updateStatus(Messages.getString("Editor.run.action.status.message")); //$NON-NLS-1$
      runAction.setEnabled(false);
      haltAction.setEnabled(true);
      messageTabs.setSelectedIndex(CONSOLE_TAB);
      process = runningPanel.run();
      //    Start a new thread to wait for this to finish running
      new Thread("Run_program") { //$NON-NLS-1$
        public void run() {
          while ((process != null) && process.isRunning()) {
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
            }
          }
          console.setText(runningPanel.getLanguageKit().getRunResults());
          messageTabs.setTitleAt(CONSOLE_TAB, Messages
              .getString("Editor.console.msg.prefix") //$NON-NLS-1$
              + runningPanel.getSaveFile().getName());
          runAction.setEnabled(true);
          haltAction.setEnabled(false);
          process = null;
          programTabs.setEnabled(true);
          currentProgramTab().setEnabled(true);
          currentProgramTab().requestFocusInWindow();
          updateStatus(Messages.getString("Editor.run.action.complete.message")); //$NON-NLS-1$
        }
      }.start();
    } else {
      console.setText("A "
          + runningPanel.getLanguageKit().getDescription() //$NON-NLS-1$
          + " cannot be executed/viewed."); //$NON-NLS-1$
      messageTabs.setTitleAt(CONSOLE_TAB, Messages.getString("Editor.console.msg.pefix") //$NON-NLS-1$
          + runningPanel.getSaveFile().getName());
      updateStatus(Messages.getString("Editor.run.action.failed.message")); //$NON-NLS-1$
      programTabs.setEnabled(true);
      currentProgramTab().setEnabled(true);
      currentProgramTab().requestFocusInWindow();
    }
  }

  /**
   * Runs tests on an HTML document. @todo Create testing framework for HTML
   * files
   */
  private synchronized void runHTMLTest() {
    // current does nothing
  }

  /**
   * DOCUMENT ME!
   */
  private synchronized void runJavaTest() {
    runAction.setEnabled(false);
    testMenuItem.setEnabled(false);
    programTabs.setEnabled(false);
    currentProgramTab().setEnabled(false);
    final EditPanel panel = currentProgramTab();
    final File saveFile = panel.getSaveFile();
    if (currentProblem == null) {
      JOptionPane
          .showMessageDialog(
              null, Messages.getString("Editor.problemset.notloaded.message")); //$NON-NLS-1$
      updateStatus("Not loaded as an ICE. No tests to run."); //$NON-NLS-1$
      runAction.setEnabled(true);
      programTabs.setEnabled(true);
      panel.setEnabled(true);
    } else if (process != null) {
      updateStatus(Messages.getString("Editor.runtest.status.message")); //$NON-NLS-1$
      programTabs.setEnabled(true);
      panel.setEnabled(true);
      beep();
    } else if (!panel.saveIfNecessary(Messages
        .getString("Editor.runtest.save.info.string"), true, false)) { //$NON-NLS-1$
      updateStatus(Messages.getString("Editor.runtest.notsaved.message")); //$NON-NLS-1$
      programTabs.setEnabled(true);
      panel.setEnabled(true);
    } else {
      // Make sure the console window is visible, and clear
      messageTabs.setSelectedIndex(CONSOLE_TAB);
      mainWindow.getContentPane().remove(statusArea);
      progressBar.setString(Messages
          .getString("Editor.runtest.progress.message")); //$NON-NLS-1$
      progressBar.setStringPainted(true);
      progressBar.setIndeterminate(true);
      mainWindow.getContentPane().add(progressBar, BorderLayout.SOUTH);
      mainWindow.validate();
      //Create a timer to control the update of the progressbar
      timer = new javax.swing.Timer(80, new ActionListener() {
        boolean runningTests = false;

        public void actionPerformed(ActionEvent evt) {
          if (!runningTests) {
            runningTests = true;
            String fileName = saveFile.getName();
            int lastDotPos = fileName.lastIndexOf("."); //$NON-NLS-1$
            String extension = ""; //$NON-NLS-1$
            if (lastDotPos > 0) {
              extension = fileName.substring(lastDotPos);
            }
            String programName = fileName.substring(0, fileName.length()
                - extension.length());
            String command = runCommand
                + Messages.getString("Editor.java.option.eecs.testing.property")
                + programName; //$NON-NLS-1$
            console.setText(""); //$NON-NLS-1$
            printLineToConsole(Messages
                .getString("Editor.testing.console.begin.message")); //$NON-NLS-1$
            currentProblem.verify(command, saveFile.getParentFile(),
                new Problem.TestCallback() {
                  public void done(final String result) {
                    SwingUtilities.invokeLater(new Runnable() {
                      public void run() {
                        String output = Messages
                            .getString("Editor.testing.result.html.header"); //$NON-NLS-1$
                        output += (result + Messages
                            .getString("Editor.testing.result.html.footer")); //$NON-NLS-1$
                        console.setText(output);
                        if (logger.isLoggable(Level.INFO)) {
                          logger.info("Validation is completed."); //$NON-NLS-1$
                        }
                        mainWindow.getContentPane().remove(progressBar);
                        mainWindow.getContentPane().add(statusArea,
                            BorderLayout.SOUTH);
                        updateStatus(Messages
                            .getString("Editor.testing.complete.status.message")); //$NON-NLS-1$
                        timer.stop();
                        runAction.setEnabled(panel.getLanguageKit()
                            .isRunnable());
                        testMenuItem.setEnabled(true);
                        programTabs.setEnabled(true);
                        currentProgramTab().setEnabled(true);
                        mainWindow.validate();
                      }
                    });
                  }
                }); // end verify
            beep();
          } else {
            SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                console.setText(Messages
                    .getString("Editor.testing.final.results.html.header")
                    + currentProblem.getResults() //$NON-NLS-1$
                    + Messages
                        .getString("Editor.testing.final.results.html.footer")); //$NON-NLS-1$
              }
            });
          }
        }
      });
      timer.start();
    }
  }

  /**
   * Runs tests against the currently open file (active tab)
   * using the problem set test cases.
   */
  private void runTestsAction() {
    String type = currentProgramTab().getLanguageKit().getLanguage();
    if (type.equals(LanguageKit.JAVA)) {
      runJavaTest();
    } else if (type.equals(LanguageKit.HTML)) {
      runHTMLTest();
    }
  }

  /**
   * Saves the current document. If the file was not previously saved, the
   * allows entry of a name.
   * 
   * @see #saveAsAction
   */
  private void saveAction() {
    int tab = programTabs.getSelectedIndex();
    EditPanel currentPanel = (EditPanel) programTabs.getComponentAt(tab);
    if (chooser == null) {
      updateStatus(Messages.getString("Editor.save.unavailable.message")); //$NON-NLS-1$
      return;
    }
    updateStatus(Messages.getString("Editor.save.status.message")); //$NON-NLS-1$
    if (currentPanel.saveAction()) {
      programTabs.setTitleAt(tab, currentPanel.getSaveFile().getName());
      prefs.put(LANGUAGE_KEY, currentPanel.getLanguageKit().getLanguage());
    } else {
      updateStatus(Messages.getString("Editor.save.error.message")); //$NON-NLS-1$
    }
  }

  /**
   * Saves the file with a new file name.
   */
  private void saveAsAction() {
    int tab = programTabs.getSelectedIndex();
    EditPanel currentPanel = (EditPanel) programTabs.getComponentAt(tab);
    if (chooser == null) {
      updateStatus("Can't save yet.  Try again later..."); //$NON-NLS-1$
      return;
    }
    updateStatus(Messages.getString("Editor.saveas.status.message")); //$NON-NLS-1$
    chooser.setDialogTitle(Messages.getString("Editor.save.dialog.title")); //$NON-NLS-1$
    chooser.setApproveButtonText(Messages
        .getString("Editor.save.dialog.button")); //$NON-NLS-1$
    currentPanel.saveAsAction();
    if (currentPanel.getSaveFile() != null) {
      prefs.put(LANGUAGE_KEY, currentPanel.getLanguageKit().getLanguage());
      programTabs.setTitleAt(tab, currentPanel.getSaveFile().getName());
    }
  }

  /**
   * Selects the entire contents of the current focus area. Note: isn't
   * necessarily the editing window.
   */
  private void selectAllAction() {
    if (minorFocusArea() != null) {
      if (minorFocusArea() instanceof JEditTextArea) {
        ((JEditTextArea)minorFocusArea()).selectAll();
      } else if (minorFocusArea() instanceof JTextComponent) {
        ((JTextComponent)minorFocusArea()).selectAll();
      }
    } else {
      updateStatus(Messages.getString("Editor.selectall.error.message")); //$NON-NLS-1$
      beep();
    }
  }

  private void setMRUMenuItem(int item, String name) {
    String file = name.substring(name.lastIndexOf("\\") + 1); //$NON-NLS-1$
    int end = name.lastIndexOf("\\"); //$NON-NLS-1$
    if (end < 0) {
      end = 0;
    }
    int start = (end > 24) ? (end - 24) : 0;
    String summaryPath = " " + (item + 1) + "  " + file + " [" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + ((start == 0) ? "":"...") + name.substring(start, end) + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    Action menuAction = new AbstractAction() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (chooser == null) {
            updateStatus("Problem getting file chooser, try again in a minute..."); //$NON-NLS-1$
            logger.warning("FileChooser not ready when opening a file."); //$NON-NLS-1$
            return;
          }
          openFile(new File(e.getActionCommand()));
        } catch (IOException ioe) {
          JOptionPane.showMessageDialog(mainWindow, Messages
              .getString("Editor.mru.open.error.prefix.message") //$NON-NLS-1$
              + e.getActionCommand());
        }
      }
    };
    menuAction.putValue(Action.SHORT_DESCRIPTION, summaryPath);
    menuAction.putValue(Action.LONG_DESCRIPTION, name);
    menuAction.putValue(Action.ACTION_COMMAND_KEY, name);
    mruMenuItem[item].setAction(menuAction);
    mruMenuItem[item].setText(summaryPath);
  }

  private void setPanelActions(EditPanel panel) {
    LanguageKit language = panel.getLanguageKit();
    runAction.setEnabled(language.isRunnable() && panel.isCompiled());
    compileAction.setEnabled(language.hasCompiler() && !panel.isCompiled());
    testMenuItem.setEnabled(currentProblem != null && runAction.isEnabled());
    formatAction.setEnabled(language.hasFormatter() && panel.isCompiled());
    reformatSettingsMenuItem.setEnabled(language.hasFormatPreferences());
    saveAsAction.setEnabled(true);
    saveAction.setEnabled(!panel.isSaved());
    printAction.setEnabled(true);
    /* Some of the actions should be moved into the EditPanel InputHandler
     * since they aren't needed at this level.  They're all here now because
     * they started here with the original editor model, and haven't been
     * refactored down.
     */
    Object[] keys = actions.allKeys();
    for (int i=0; i<keys.length; i++) {
      panel.addInputHandler((String)keys[i], actions.get(keys[i]));
    }
  }

  /**
   * @param panel
   * @param keys
   */
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

  /**
   * DOCUMENT ME!
   */
  private void submitAction() {
    updateStatus(Messages.getString("Editor.submit.action.status.message")); //$NON-NLS-1$
    File saveFile = currentProgramTab().getSaveFile();
    URLSubmit.submit(saveFile);
  }

  private void updateMRU() {
    Preferences prefs = Preferences.userNodeForPackage(Editor.class);
    int size = mruManager.size();
    String[] fileList = mruManager.getFiles();
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (fileList[i].equals("empty")) { //$NON-NLS-1$
      } else {
        mruMenuItem[count].setVisible(true);
        prefs.put("mru" + i, fileList[i]); //$NON-NLS-1$
        setMRUMenuItem(count++, fileList[i]);
        mruSeparator.setVisible(true);
      }
    }
    for (int i = count; i < MRU_CAPACITY; i++) {
      mruMenuItem[i].setVisible(false);
      prefs.put("mru" + i, "empty"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * Updates all the user definable properties of the editor from the
   * <code>Properties</code> appProperties.
   */
  private void updateProperties() {
    if (logger.isLoggable(Level.INFO)) {
      logger.info("Updating window and editor properties."); //$NON-NLS-1$
    }
    //        int saveTime;
    //
    //        try {
    //            saveTime = Integer.parseInt(appProperties.getProperty(
    //                        "autosave_time"));
    //        } catch (NumberFormatException ne) {
    //            logger.log(Level.WARNING,
    //                "Autosave was incorrectly stored in properties. ", ne);
    //            assert false;
    //            saveTime = 10;
    //        }
    //
    //        if (saveTime != timeForAutoSave) {
    //            timeForAutoSave = saveTime;
    //            autoSaveTimer.setDelay(timeForAutoSave * 60 * 1000);
    //        }
    handbookURL = prefs.get(HANDBOOK_URL_KEY, DEFAULT_HANDBOOKURL);
  }

  /** Creates a combination dropdown box that includes images and labels.
   * Used for the &quot;New...&quot; dropdown on the toolbar to allow
   * inclusion of several different new file type options.
   */
  class ComboBoxRenderer extends JLabel implements ListCellRenderer {
    ImageIcon[] images;
    String[] strings;

    public ComboBoxRenderer() {
      setOpaque(true);
      setHorizontalAlignment(LEFT);
      setVerticalAlignment(CENTER);
      images = new ImageIcon[0];
      strings = new String[0];
    }

    public ComboBoxRenderer(ImageIcon[] icons, String[] messages) {
      this();
      images = icons;
      strings = messages;
    }

    /*
     * This method finds the image and text corresponding to the selected value
     * and returns the label, set up to display the text and image.
     */
    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {
      //Get the selected index. (The index param isn't
      //always valid, so just use the value.)
      int selectedIndex = ((Integer) value).intValue();
      if (isSelected) {
        setBackground(list.getSelectionBackground());
        setForeground(list.getSelectionForeground());
      } else {
        setBackground(list.getBackground());
        setForeground(list.getForeground());
      }
      //Set the icon and text. If icon was null, say so.
      ImageIcon icon = images[selectedIndex];
      String pet = strings[selectedIndex];
      setIcon(icon);
      if (icon != null) {
        setText(pet);
        setFont(list.getFont());
      }
      return this;
    }
  }

  class AllFilesFilter implements FilenameFilter {
    public boolean accept(File f, String s) {
      return true;
    }
  }

  /**
   * Used with a timer to update paste based on the contents of the clipboard.
   * This class should become obsolete with JDK 1.5, since it includes a 
   * FlavorListener that can listen for changes to DataFlavors on the clipboard.
   * @author Karl A. Gossett
   */
  private class ClipboardListener implements ActionListener {

    /** When the timer fires, check for changes to the system clipboard.
     * @param evt  The ActionEvent from the Timer.
     */
    public void actionPerformed(ActionEvent evt) {
      if (clip==null || pasteAction==null) return;
      try {
        clip.getContents(this).getTransferData(DataFlavor.stringFlavor);
        pasteAction.setEnabled(true);
      } catch (Exception e) {
        pasteAction.setEnabled(false);
      }
    }
  }
  
  class CloseAction extends AbstractAction {
    public CloseAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
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
    public CompileAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      compileAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class CopyAction extends AbstractAction {
    public CopyAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      copyAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class CutAction extends AbstractAction {
    public CutAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      cutAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class FindAction extends AbstractAction {
    public FindAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      findAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }
  
  class GotoAction extends AbstractAction {
    public GotoAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      gotoAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }
  
  class HaltAction extends AbstractAction {
    public HaltAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      haltAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }
  

  private class NewAction extends AbstractAction {
    public NewAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent evt) {
      Object source = evt.getSource();
      if (source.equals(newJavaMenuItem)) {
        newAction(LanguageKit.JAVA);
      } else if (source.equals(newHTMLMenuItem)) {
        newAction(LanguageKit.HTML);
      } else if (source.equals(newCSSMenuItem)) {
        newAction(LanguageKit.CSS);
      } else {
        newAction(LanguageKit.TEXT);
      }
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class OpenAction extends AbstractAction {
    public OpenAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      openAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class PasteAction extends AbstractAction {
    public PasteAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent evt) {
      if (((JComponent) evt.getSource()).getParent() instanceof JPopupMenu) {
        pastePopupAction();
      } else {
        pasteAction();
      }
      programTabs.getSelectedComponent().requestFocusInWindow();
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
    public PrintAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      printAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class ProblemAction extends AbstractAction {
    public ProblemAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      openProblemAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class RedoAction extends AbstractAction {
    public RedoAction() {
      super("Redo"); //$NON-NLS-1$
      putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Y")); //$NON-NLS-1$
      putValue(Action.ACTION_COMMAND_KEY, "ctrl Y"); //$NON-NLS-1$
      putValue(Action.NAME, "Redo"); //$NON-NLS-1$
      setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
      try {
        undoManager.redo();
      } catch (CannotRedoException ex) {
        logger.log(Level.WARNING, "Unable to redo.", ex); //$NON-NLS-1$
      } catch (Exception exc) {
        logger.log(Level.WARNING, "Exception during redo.", exc); //$NON-NLS-1$
      }
      updateRedoState();
      undoAction.updateUndoState();
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
      } else {
        setEnabled(false);
        putValue(Action.NAME, "Redo"); //$NON-NLS-1$
        if (redoButton != null) {
          redoButton.setEnabled(false);
          redoButton.setToolTipText(undoManager.getRedoPresentationName());
        }
      }
    }
  }

  class FormatAction extends AbstractAction {
    public FormatAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      formatAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class ReplaceAction extends AbstractAction {
    public ReplaceAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      replaceAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class RunAction extends AbstractAction {
    public RunAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      runAction();
      // No need to request focus on this action, because focus is controlled by
      // the running app at this point.
      // Only change is based on the callback from the run thread.
    }
  }

  class SaveAction extends AbstractAction {
    public SaveAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      saveAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }

  class SaveAsAction extends AbstractAction {
    public SaveAsAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      saveAsAction();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }
  }
  
  class SubmitAction extends AbstractAction {
    public SubmitAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(ACCELERATOR_KEY, accel);
    }

    public void actionPerformed(ActionEvent arg0) {
      submitAction();
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
      setLocation((screenSize.width / 2) - (labelSize.width / 2),
          (screenSize.height / 2) - (labelSize.height / 2));
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
          } catch (Exception e) {
            e.printStackTrace();
            // can catch InvocationTargetException
            // can catch InterruptedException
          }
        }
      };
      setVisible(true);
      Thread splashThread = new Thread(waitRunner, "SplashThread"); //$NON-NLS-1$
      splashThread.start();
    }
  }

  class UndoAction extends AbstractAction {
    public UndoAction(String text, ImageIcon icon, String desc,
        Integer mnemonic, KeyStroke accel) {
      super(text,icon); 
      putValue(SHORT_DESCRIPTION, desc);
      putValue(Action.ACCELERATOR_KEY, accel);
      putValue(Action.ACTION_COMMAND_KEY, accel.toString());
      putValue(MNEMONIC_KEY, mnemonic);
//      putValue(Action.ACTION_COMMAND_KEY, "ctrl Z"); //$NON-NLS-1$
      setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
      try {
        undoManager.undo();
      } catch (CannotUndoException ex) {
        logger.log(Level.INFO, "Unable to undo.", ex); //$NON-NLS-1$
      } catch (Exception ex) {
        logger.log(Level.WARNING, "Exception during undo.", ex); //$NON-NLS-1$
      }
      updateUndoState();
      
      redoAction.updateRedoState();
      programTabs.getSelectedComponent().requestFocusInWindow();
    }

    protected void updateUndoState() {
      if (undoManager.canUndo()) {
        setEnabled(true);
        putValue(Action.NAME, undoManager.getUndoPresentationName());
        putValue(Action.SHORT_DESCRIPTION, undoManager.getUndoPresentationName());
      } else {
        setEnabled(false);
        putValue(Action.NAME, "Undo"); //$NON-NLS-1$
        putValue(Action.SHORT_DESCRIPTION,"Undo"); //$NON-NLS-1$
      }
      //    if this has a button with it, make sure no text!
      if (undoButton != null) {
        undoButton.setText("");
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
        minorFocus = (JTextComponent) component;
      }
      //cutMenuItem.setEnabled(component.isEditable());
      //pasteMenuItem.setEnabled(component.isEditable());
      //replaceButton.setEnabled(component.isEditable());
    }
  }

  private class MinorFocusListener extends FocusAdapter {
    private JComponent component;

    public MinorFocusListener(JComponent comp) {
      this.component = comp;
    }

    public void focusGained(FocusEvent e) {
      minorFocus = component;
      //      cutMenuItem.setEnabled(component.isEditable());
      //      pasteMenuItem.setEnabled(component.isEditable());
      //      selectAllMenuItem.setEnabled(component.isEditable());
      //      component.selectAll();
    }
  }

  private class MyUndoableEditListener implements UndoableEditListener {
    public void undoableEditHappened(UndoableEditEvent e) {
      if (!e.getEdit().getPresentationName().equals("style change")) { //$NON-NLS-1$
        //Remember the edit and update the menus
        undoManager.addEdit(e.getEdit());
        undoAction.updateUndoState();
        redoAction.updateRedoState();
      }
    }
  }

  /** JDK 1.5 supports a FlavorListener that will make watching for changes to the clipboard
   * to be much more effective and direct.  It's current managed by the CaretListener.
   * @author DK8685
   */
//  private class ClipboardListener implements FlavorListener {
//    public void flavorsChanged(FlavorEvent fe) {
//      // handle the change by updating paste function
//    }
//  }
  
  private class MyChangeListener implements PropertyChangeListener {
    /**
     * Update GUI based on changes to Editor properties
     * @see javax.swing.event.PropertyChangeListener#propertyChanged(javax.swing.event.ChangeEvent)
     */
    private String statusProperty = Messages.getString("Editor.status.property");
    private String compiledProperty = Messages.getString("Editor.compiled.property");
    private String filefilterProperty = Messages.getString("Editor.filefilter.property");
    private String copyEnabledProperty = Messages.getString("Editor.copy.enabled.property");
    private String savedProperty = Messages.getString("Editor.saved.property");
    public void propertyChange(PropertyChangeEvent evt) {
      if (evt.getPropertyName().equals(statusProperty)) { //$NON-NLS-1$
        updateStatus((String) evt.getNewValue());
      } else if (evt.getPropertyName().equals(compiledProperty)) { //$NON-NLS-1$
        boolean isCompiled = ((Boolean) (evt.getNewValue())).booleanValue();
        compileAction.setEnabled(!isCompiled);
        runAction.setEnabled(isCompiled
            && ((EditPanel) (evt.getSource())).getLanguageKit().isRunnable());
        formatAction.setEnabled(isCompiled
            && ((EditPanel) (evt.getSource())).getLanguageKit().hasFormatter());
        if (currentProblem == null) {
          testMenuItem.setEnabled(false);
        } else {
          testMenuItem.setEnabled(isCompiled
              && ((EditPanel) (evt.getSource())).getLanguageKit().isRunnable());
        }
      } else if (evt.getPropertyName().equals(filefilterProperty)) { //$NON-NLS-1$
        setFileFilter((String) (evt.getNewValue()));
      } else if (evt.getPropertyName().equals(copyEnabledProperty)) { //$NON-NLS-1$
        boolean state = ((Boolean) evt.getNewValue()).booleanValue();
        copyAction.setEnabled(state);
        cutAction.setEnabled(state);
      } else if (evt.getPropertyName().equals(savedProperty)) {
        boolean saved = ((Boolean) evt.getNewValue()).booleanValue();
        saveAction.setEnabled(!saved);
      }
    }
  }
}
// end class Editor