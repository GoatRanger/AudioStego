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
 * Created on Aug 30, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.editor;

import jedit.CSSTokenMarker;
import jedit.DefaultInputHandler;
import jedit.JEditTextArea;
import jedit.JavaTokenMarker;
import jedit.SyntaxStyle;
import jedit.TextAreaDefaults;
import jedit.Token;
import jedit.TokenMarker;
import jedit.XMLTokenMarker;
import eecs.editor.language.LanguageKit;
import eecs.editor.language.LanguageKitFactory;
import eecs.editor.util.TextUtilities;

import eecs.util.ProcessWrapper;
import eecs.util.html.BrowserControl;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import java.nio.channels.FileChannel;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.PlainDocument;
import javax.swing.text.Segment;

/**
 * DOCUMENT ME!
 * 
 * @author Karl A. Gossett
 */
class EditPanel extends JPanel {

	/**
	 * The input handler for this editing panel. Handles all key events from the
	 * JEDitTextArea.
	 */
	DefaultInputHandler inputHandler;
	/** DOCUMENT ME! */
	static TextTransfer clipboard;
	JEditTextArea programArea;
	boolean isCompiled = false;
	boolean saved = true;

	private static final int MIN_FONT_SIZE = 6;

	private static final int MAX_FONT_SIZE = 32;

	/** DOCUMENT ME! */
	private CaretListener programAreaCaretListener = new CaretListener() {
		public void caretUpdate(CaretEvent e) {
			int pos = e.getDot();
			int lineNumber = 1;
			int columnNumber = 1;
			if (pos != e.getMark()) {
				firePropertyChange("copyEnabled", false, true);
			} else {
				firePropertyChange("copyEnabled", true, false);
			}
			lineNumber = programArea.getCaretLine() + 1;
			columnNumber = pos - programArea.getLineStartOffset(lineNumber - 1);
			lineLabel.setText("" + (lineNumber));
			columnLabel.setText("" + (columnNumber + 1));
		}
	};

	private KeyAdapter keyListener = new KeyAdapter() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
		 */
		public void keyTyped(KeyEvent e) {
			super.keyTyped(e);
		}
	};
	/** DOCUMENT ME! */
	private File autoSave = null;

	/** DOCUMENT ME! */
	private File saveFile = null;

	/** DOCUMENT ME! */
	private Font commonFont = new Font("monospaced", Font.PLAIN, 12);

	/** DOCUMENT ME! */
	private JFileChooser chooser;

	/** DOCUMENT ME! */
	private JLabel columnLabel;

	/** DOCUMENT ME! */
	private JLabel lineLabel;

	/** DOCUMENT ME! */
	private JLabel programLabel;

	/** DOCUMENT ME! */
	private LanguageKit languageKit;

	private PropertyChangeSupport changes = new PropertyChangeSupport(this);

	/** DOCUMENT ME! */
	private javax.swing.Timer autoSaveTimer;

	/** DOCUMENT ME! */
	private boolean compilationNecessary = false;

	/** DOCUMENT ME! */
	private int timeForAutoSave = 10;

	private Preferences prefs = Preferences.userRoot().node("/eecs/editor");
	/**
	 * Creates a new EditPanel object.
	 * 
	 * @param owner
	 *            DOCUMENT ME!
	 * @param kit
	 *            DOCUMENT ME!
	 */
	public EditPanel(LanguageKit kit) {
		enableEvents(AWTEvent.KEY_EVENT_MASK);
		if (clipboard == null) {
			clipboard = new TextTransfer();
		}
		programArea = new JEditTextArea(getTextAreaDefaults());
		String language = kit.getLanguage();
		if (language.equals(LanguageKit.JAVA))
			programArea.setTokenMarker(new JavaTokenMarker());
		else if (language.equals(LanguageKit.HTML))
			programArea.setTokenMarker(new XMLTokenMarker());
		else if (language.equals(LanguageKit.CSS))
			programArea.setTokenMarker(new CSSTokenMarker());
		programArea.setFont(commonFont);
		int currentTextSize = prefs.getInt(Editor.TEXT_SIZE_KEY,
				Editor.DEFAULT_TEXTSIZE);
		programArea.setTextSize(currentTextSize);
		int currentTabSize = prefs.getInt(Editor.TAB_SIZE_KEY, 4);
		setTabSize(currentTabSize);

		addKeyListener(keyListener);
		programArea.getDocument().addDocumentListener(new MyDocumentListener());
		programArea.addCaretListener(programAreaCaretListener);

		JPanel editingPanel = new JPanel();
		editingPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		editingPanel.setLayout(new BorderLayout());
		editingPanel.add(programArea);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(200, 0));
		this.setPreferredSize(new Dimension(600, 600));

		this.add(createGUIHeaderLabels(), BorderLayout.NORTH);

		this.add(editingPanel, BorderLayout.SOUTH);
		prefs.addPreferenceChangeListener(new PreferenceListener());
		getPreferences();
		initializeAutoSave();

		if (kit == null) {
			languageKit = LanguageKitFactory.getLanguageKit(LanguageKit.TEXT);
		} else {
			languageKit = kit;
		}

		programArea.setText(languageKit.getTemplate());
		// Seed the property with a saved value
		firePropertyChange("saved", true, false);
		saved = false;
	}

	/**
	 * @param currentTabSize
	 */
	private void setTabSize(int currentTabSize) {
		programArea.getDocument().putProperty(PlainDocument.tabSizeAttribute,
				new Integer(currentTabSize));
	}

	/**
	 * @return
	 */
	private TextAreaDefaults getTextAreaDefaults() {
		TextAreaDefaults defaults = TextAreaDefaults.getDefaults();
		inputHandler = new EditorInputHandler();
		inputHandler.addDefaultKeyBindings();
		defaults.styles[Token.KEYWORD1] = new SyntaxStyle(Color.blue, false,
				false);
		defaults.styles[Token.KEYWORD2] = new SyntaxStyle(Color.blue, false,
				false);
		defaults.styles[Token.KEYWORD3] = new SyntaxStyle(Color.blue, false,
				false);
		defaults.paintInvalid = false;
		defaults.eolMarkers = false;
		defaults.inputHandler = inputHandler;
		defaults.document = new EditorDocument();
		return defaults;
	}

	public void gotoLine(int line) {
		programArea.setCaretPosition(programArea.getLineStartOffset(line - 1));
		programArea.scrollTo(line - 1, 0);
	}
    
	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public LanguageKit getLanguageKit() {
		return languageKit;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public JEditTextArea getProgramArea() {
		return programArea;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public File getSaveFile() {
		return saveFile;
	}

    public StringBuffer getAsXHTML() {
        TokenMarker tokenMarker = programArea.getTokenMarker();
        StringBuffer buffer = new StringBuffer("<pre>");
        int lastLine = programArea.getLineCount();
        for (int line = 0; line < lastLine; line++) {
          Segment currentLine = new Segment();
          programArea.getLineText(line,currentLine);
          if (currentLine != null) {
          	Token currentLineTokens = tokenMarker.markTokens(currentLine,line);
            buffer.append(TextUtilities.getSyntaxLine(currentLine, currentLineTokens));
          }
        }
        buffer.deleteCharAt(buffer.length()-1);
        buffer.append("</pre>\n");
        return buffer;
    }

    public StringBuffer getAsXHTMLTable() {
     StringBuffer contents = getAsXHTML();
     StringBuffer table = new StringBuffer("<table border=\"0\">\n  <tr>\n   <td style=\"vertical-align: top;\">");
     // Start breaks at -2 since two extra automatically inserted
     int breaks = 0;
     int index = 0;
     while ((index = contents.indexOf("\n",index+1)) != -1) {
      breaks++;  
     }
     StringBuffer lineNums = new StringBuffer("<pre>");
     for (int i=1; i<=breaks; i++) {
        lineNums.append(i + "\n");
     }
     table.append(lineNums);
     table.append("</pre>\n   </td>\n   <td style=\"vertical-align: top;\">\n");
     table.append(contents);
     table.append("   </td>\n  </tr>\n </table>\n");
     return table;
    }
	/**
	 * DOCUMENT ME!
	 * 
	 * @param listener
	 *            DOCUMENT ME!
	 */
	public void addKeyListener(KeyListener listener) {
		super.addKeyListener(listener);
		programArea.addKeyListener(listener);
	}

	public void addUndoableEditListener(UndoableEditListener listener) {
		programArea.getDocument().addUndoableEditListener(listener);
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		super.addPropertyChangeListener(pcl);
		programArea.addPropertyChangeListener(pcl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#addMouseListener(java.awt.event.MouseListener)
	 */
	public synchronized void addMouseListener(MouseListener arg0) {
		super.addMouseListener(arg0);
		programArea.addMouseListener(arg0);
	}

	public synchronized void addPopup(JPopupMenu menu) {
		programArea.setRightClickPopup(menu);
	}

	public void addInputHandler(String keyBinding, ActionListener action) {
		if (inputHandler == null)
			return;
		inputHandler.addKeyBinding(keyBinding, action);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public boolean close() {
		if (!saveIfNecessary("closing", true, true)) {
			return false;
		}

		return true;
	}

	/** Copies the current selection to the clipboard. */
	public void copy() {
		programArea.copy();
	}

    public void copyXHTMLToClipboard() {
      clipboard.setClipboardContents(getAsXHTMLTable().toString());   
    }
	/**
	 * Replaces the current selection with the contents of the clipboard. <br />
	 * If there is no selection, results in an insert of the clipboard contents
	 * at the cursor location.
	 *  
	 */
	public void paste() {
		programArea.paste();
	}
	/**
	 * DOCUMENT ME!
	 */
	protected void deployWeb() {
		if (!languageKit.getLanguage().equals(LanguageKit.HTML)) {
			return;
		}

		File deployMe = null;

		if (saveFile != null) {
			String current = saveFile.getPath();
			String path = current.substring(0, current.lastIndexOf("\\"));
			int option = JOptionPane.showConfirmDialog(this,
					"Deploy Directory \"" + path + "\"?");

			if (option == JOptionPane.OK_OPTION) {
				deployMe = new File(path);
			}
		}

		if (deployMe == null) {
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfc.setDialogTitle("Choose Directory to Deploy");
			jfc.setApproveButtonText("Select");

			int option = jfc.showDialog(this, null);

			switch (option) {
				case JFileChooser.APPROVE_OPTION :
					deployMe = jfc.getSelectedFile();

					break;

				default :
					firePropertyChange("status", "", "Deploy Cancelled");

					return;
			}
		}

		if (deployMe.getPath().indexOf("\\IT105") >= 0) {
			deployAll(deployMe, 0);
			firePropertyChange("status", "", "Web Successfully Deployed");
		} else {
			firePropertyChange("status", "",
					"Deploy Failed - Must be in IT105 Directory");
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	protected int exit() {
		if (!saveIfNecessary("exiting this file", true, true)) {
			return 1;
		}

		return 0;
	}

	/**
	 * DOCUMENT ME!
	 */
	protected void formatSettings() {
		if (languageKit.hasFormatPreferences()) {
			languageKit.setFormatPrefs(new JFrame());
		}
	}

	/**
	 * DOCUMENT ME!
	 */
	protected void previewWeb() {
		boolean success = true;
		if (languageKit.getLanguage().equals(LanguageKit.HTML)) {
			File temp = null;
			BufferedWriter out = null;
			try {
				// Create temp file.
				temp = File.createTempFile("preview", ".htm");

				// Delete temp file when program exits.
				temp.deleteOnExit();

				// Write to temp file
				out = new BufferedWriter(new FileWriter(temp));
				out.write(programArea.getText());
			} catch (IOException e) {
				success = false;
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					out = null;
				} catch (Exception e) {
				}
			}

			if (success && temp != null) {
				BrowserControl.displayURL("file:///" + temp.getPath());
			}
		}
	}

	/**
	 * Reformat the editor window using the LanguageKit reformatter. Returns
	 * LanguageKit format result constants, or NOT_SUPPORTED.
	 * 
	 * @return One of the formatter results from LanguageKit
	 */
	protected int reformat() {
		int result = LanguageKit.NOT_SUPPORTED;

		if (languageKit.hasFormatter()) {
			languageKit.reformat(programArea);
			result = languageKit.getFormatResult();
		}

		return result;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public ProcessWrapper run() {
		ProcessWrapper pw = languageKit.run(saveFile);

		return pw;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param file
	 *            DOCUMENT ME!
	 * 
	 * @throws IOException
	 *             DOCUMENT ME!
	 */
	protected void setSaveFile(File file) throws IOException {
		saveFile = file;
		programLabel.setText(file.getCanonicalPath());
	}

	protected boolean openAction(File file) {
		boolean opened = true;
		try {
			programArea.loadFromFile(file);
			saved = true;
			firePropertyChange("saved", false, true);
			setSaveFile(file);
		} catch (IOException ioe) {
			opened = false;
		}
		return opened;
	}

	public void setText(String text) {
		programArea.setText(text);
	}

	/**
	 * Saves the current document. If the file was not previously saved, the
	 * allows entry of a name.
	 * 
	 * @see #saveAsAction
	 */
	protected boolean saveAction() {
		if (chooser == null) {
			firePropertyChange("status", "",
					"Can't save file yet.  Try again later...");
			return false;
		}

		firePropertyChange("status", "", "Saving...");

		boolean isSaved = true;
		if (saveFile == null) {
			saveAsAction();
			if (saveFile == null)
				isSaved = false;
		} else {
			try {
				programArea.saveToFile(saveFile);
				firePropertyChange("status", "", "Saved "
						+ saveFile.getCanonicalPath());
			} catch (IOException ioe) {
				String additionalInfo = ioe.getMessage();
				if (ioe.getMessage().indexOf("Access is denied") > 0) {
					additionalInfo = "It appears that you do not have the correct permissions to save a file in that directory.";
				}
				JOptionPane.showMessageDialog(this,
						"The file could not be saved.\r\n" + additionalInfo);
				isSaved = false;
			}
			if (autoSave != null && isSaved) {
				autoSave.delete();
				autoSaveTimer.restart();
			}
		}
		firePropertyChange("saved", saved, isSaved);
		saved = isSaved;
		return isSaved;
	}

	/**
	 * Saves the file with a new file name.
	 */
	protected void saveAsAction() {
		if (chooser == null) {
			firePropertyChange("status", "",
					"Can't save yet.  Try again later...");
			return;
		}
		firePropertyChange("status", "", "Save As");

		chooser.setDialogTitle("Save Program File");
		chooser.setApproveButtonText("Save");

		if (saveFile != null) {
			chooser.setSelectedFile(saveFile);
		} else {
			firePropertyChange("filefilter", "", languageKit.getDefaultSuffix());
			chooser.setSelectedFile(new File("<new>"));
		}

		switch (chooser.showSaveDialog(this)) {
			case JFileChooser.CANCEL_OPTION :
			case JFileChooser.ERROR_OPTION :
				firePropertyChange("status", "", "Save As... was cancelled");
				break;

			case JFileChooser.APPROVE_OPTION :

				// Before saving, ensure file has a suffix. If one wasn't
				// entered, then ask to use the current file type as the suffix.
				String name = chooser.getSelectedFile().getAbsolutePath();

				if (name.matches(".*\\.[a-zA-Z_0-9]{1,5}")) {
					try {
						setSaveFile(chooser.getSelectedFile());
					} catch (IOException e) {
						saveFile = null;
						firePropertyChange("status", "", "Couldn't save file!");
						firePropertyChange("saved", saved, false);
						saved = false;
						return;
					}
				} else {
					Object[] options = {
							"Use " + languageKit.getDefaultSuffix(), "CANCEL"};
					int option = JOptionPane
							.showOptionDialog(
									null,
									"You did not enter an extension on "
											+ "the file name (such as .htm or .java).\nThis may cause your "
											+ "file to not work as expected.\nWould you like me to use "
											+ "the current extension ("
											+ languageKit.getDefaultSuffix()
											+ ")?", "Missing File Extension",
									JOptionPane.DEFAULT_OPTION,
									JOptionPane.WARNING_MESSAGE, null, options,
									options[0]);

					if (option == JOptionPane.YES_OPTION) {
						name = name + languageKit.getDefaultSuffix();
					}

					File newFile = new File(name);

					try {
						setSaveFile(newFile);
					} catch (IOException e) {
						saveFile = null;
						firePropertyChange("status", "", "Couldn't save file!");
						firePropertyChange("saved", saved, false);
						saved = false;
						return;
					}
				}

				try {
					programLabel.setText(" " + saveFile.getCanonicalPath());
				} catch (Exception exn) {
					firePropertyChange("status", "", "Invalid Save File");
					firePropertyChange("saved", saved, false);
					saveFile = null;
					programLabel.setText(" Program");
				}

				saveAction();

				break;

			default :
				firePropertyChange("status", "", "Unknown option");
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param time
	 *            DOCUMENT ME!
	 */
	private void setAutoSaveTime(int time) {
		autoSaveTimer.setDelay(time * 60 * 1000);
		timeForAutoSave = time;
	}

	/**
	 * DOCUMENT ME!
	 */
	private void getPreferences() {
		timeForAutoSave = prefs.getInt(Editor.AUTOSAVE_TIME_KEY,
				Editor.DEFAULT_AUTOSAVE);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param size
	 *            DOCUMENT ME!
	 */
	private void setTextSize(int size) {

		programArea.setTextSize(size);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	private JPanel createGUIHeaderLabels() {
		programLabel = new JLabel("");

		JLabel lineHdr = new JLabel("Line: ");
		JLabel columnHdr = new JLabel("Col: ");
		lineLabel = new JLabel("1");
		columnLabel = new JLabel("1");
		programLabel.setForeground(Color.black);
		lineHdr.setForeground(Color.black);
		columnHdr.setForeground(Color.black);
		lineLabel.setForeground(Color.black);
		columnLabel.setForeground(Color.black);
		lineLabel.setPreferredSize(new Dimension(30, 0));
		columnLabel.setPreferredSize(new Dimension(30, 0));

		JPanel labelPane = new JPanel();
		labelPane.setLayout(new BoxLayout(labelPane, BoxLayout.X_AXIS));
		labelPane.add(programLabel);
		labelPane.add(Box.createHorizontalGlue());
		labelPane.add(lineHdr);
		labelPane.add(lineLabel);
		labelPane.add(columnHdr);
		labelPane.add(columnLabel);

		return labelPane;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param f
	 *            DOCUMENT ME!
	 * @param level
	 *            DOCUMENT ME!
	 */
	private void deployAll(File f, int level) {
		if (f != null) {
			File[] files = f.listFiles(new FilenameFilter() {
				public boolean accept(File f, String s) {
					return true;
				}
			});

			String userName = System.getProperty("user.name");
			String path;
			String fname = f.getPath();
			int slashPos = fname.length() - 1;
			int found = 0;

			while ((found < level) && (slashPos >= 0)) {
				if (fname.charAt(slashPos) == '\\') {
					found++;
				}

				slashPos--;
			}

			String pathExt = "";

			if (level != 0) {
				pathExt = fname.substring(slashPos + 1);
			}

			if (userName.startsWith("x")) {
				path = "\\\\z-trooper\\" + userName + "\\web\\" + pathExt;
			} else {
				path = System.getProperty("user.dir") + "\\DeployWeb\\"
						+ pathExt;

				if (level == 0) {
					javax.swing.JOptionPane.showMessageDialog(this,
							"Delete Filesystem?", "Confirm Delete",
							JOptionPane.WARNING_MESSAGE);
					javax.swing.JOptionPane.showMessageDialog(this,
							new String[]{"Kidding. Faculty Deploy to " + path});
				}
			}

			File dir = new File(path);

			// Make the directory, if it doesn't already exist
			dir.mkdirs();

			for (int i = 0; i < files.length; i++) {
				FileChannel srcChannel = null;
				FileChannel dstChannel = null;
				try {
					String name = files[i].getPath();
					File current = new File(name);

					if (current.isDirectory()) {
						deployAll(current, level + 1);
					} else {
						// Create channel on the source
						srcChannel = new FileInputStream(name).getChannel();

						// Create channel on the destination
						String filename = name
								.substring(name.lastIndexOf('\\'));
						dstChannel = new FileOutputStream(path + "\\"
								+ filename).getChannel();

						// Copy file contents from source to destination
						dstChannel.transferFrom(srcChannel, 0, srcChannel
								.size());

					}
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				} catch (IOException ioe) {
					System.out.println("Couldn't copy file.");
					ioe.printStackTrace();
				} catch (Exception e) {
				} finally {
					try {
						if (srcChannel != null) {
							// Close the channels
							srcChannel.close();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						if (dstChannel != null) {
							dstChannel.close();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * DOCUMENT ME!
	 */
	private void initializeAutoSave() {
		autoSaveTimer = new javax.swing.Timer(timeForAutoSave * 60 * 1000,
				new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							programArea.saveToFile(autoSave);
						} catch (Exception e) {
						}
					}
				});
		autoSaveTimer.setInitialDelay(2 * 60 * 1000);
		autoSaveTimer.start();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param actionName
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	protected boolean saveIfNecessary(final String actionName) {
		return saveIfNecessary(actionName, true, false);
	}

	/**
	 * Saves the file, if the editing area was changed since the last save.
	 * 
	 * @param actionName
	 *            A <code>String</code> description of the action that
	 *            required a file-save check. Used in the confirmation dialog.
	 * @param allowCancel
	 *            <code>true</code> if the dialog should display a
	 *            <em>Cancel</em> button.
	 * @param allowNo
	 *            A <code>boolean</code> indicating if <em>No</em> is an
	 *            acceptable response. If <em>No</em> is selected but this
	 *            parameter is <code>false</code>, then the method will
	 *            return <code>false</code>.
	 * 
	 * @return <code>true</code> if the save was successful, or if the user
	 *         elected not to save (if allowed).
	 */
	protected boolean saveIfNecessary(final String actionName,
			boolean allowCancel, boolean allowNo) {
		//if (programArea.hasChanged()) {
		if (!saved) {
			int count = 1 + (allowCancel ? 1 : 0) + (allowNo ? 1 : 0);
			String[] options = new String[count];
			options[0] = "Yes";

			int opt = 1;

			if (allowNo) {
				options[opt++] = "No";
			}

			if (allowCancel) {
				options[opt] = "Cancel";
			}
			String name = "new file";
			if (saveFile != null) {
				name = saveFile.getName();
			}
			int answer = JOptionPane.showOptionDialog(this, "You have unsaved "
					+ "changes.\nSave file \"" + name + "\" before "
					+ actionName + "?", "Save before " + actionName + "?",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION,
					null, options, "Yes");

			switch (answer) {
				case JOptionPane.YES_OPTION :
					return saveAction();

				//return !(programArea.hasChanged() || (saveFile == null)); //
				// in case aborted the save

				case JOptionPane.NO_OPTION :
					return allowNo;

				default :
					assert ((answer == JOptionPane.CANCEL_OPTION) || (answer == JOptionPane.CLOSED_OPTION));

					return false; // JOptionPane.CANCEL_OPTION/CLOSED_OPTION
			}
		}

		return true;
	}

	/**
	 * @return Returns the isCompiled.
	 */
	public boolean isCompiled() {
		return isCompiled;
	}
	/**
	 * @param isCompiled
	 *            The isCompiled to set.
	 */
	public void setCompiled(final boolean isCompiled) {
		this.isCompiled = isCompiled;
		// Fires the property change with not compiled as the old value to
		// ensure
		// that the property change is fired (listener uses the event to update
		// the GUI)
		firePropertyChange("compiled", !isCompiled, isCompiled);
	}

	/**
	 * Requests focus on this EditPanel, which implies a request to focus on the
	 * editable text pane that it contains. This method just forwards the
	 * request to the JTextPane that this panel contains.
	 * 
	 * @see javax.swing.JComponent#requestFocusInWindow()
	 */
	public boolean requestFocusInWindow() {
		return programArea.requestFocusInWindow();
	}

	/**
	 * Requests focus on this EditPanel, which implies a request to focus on the
	 * editable text pane that it contains. This method just forwards the
	 * request to the JTextPane that this panel contains.
	 * 
	 * @see javax.swing.JComponent#requestFocus()
	 */
	public void requestFocus() {
		programArea.requestFocus();
	}

	/**
	 * Enables/disables this panel, which implies enabling its editing area.
	 * 
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 */
	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		super.setEnabled(arg0);
		programArea.setEnabled(arg0);
	}

	public void setFileChooser(JFileChooser jfc) {
		chooser = jfc;
	}

	/**
	 * @return Returns <code>true</code> if the file has been saved.
	 */
	public boolean isSaved() {
		return saved;
	}

	/**
	 * A class to allow the transfer of text to/from the system clipboard.
	 * 
	 * @author Karl A. Gossett
	 *  
	 */
	public final class TextTransfer implements ClipboardOwner {

		/**
		 * Empty implementation of the ClipboardOwner interface.
		 */
		public void lostOwnership(Clipboard aClipboard, Transferable aContents) {
			//do nothing
		}

		/**
		 * Place a String on the clipboard, and make this class the owner of the
		 * Clipboard's contents.
		 */
		public void setClipboardContents(String aString) {
			StringSelection stringSelection = new StringSelection(aString);
			Clipboard clipboard = Toolkit.getDefaultToolkit()
					.getSystemClipboard();
			clipboard.setContents(stringSelection, this);
		}

		/**
		 * Get the String residing on the clipboard.
		 * 
		 * @return any text found on the Clipboard; if none found, return an
		 *         empty String.
		 */
		public String getClipboardContents() {
			String result = "";
			Clipboard clipboard = Toolkit.getDefaultToolkit()
					.getSystemClipboard();
			//odd: the Object param of getContents is not currently used
			Transferable contents = clipboard.getContents(null);
			boolean hasTransferableText = (contents != null)
					&& contents.isDataFlavorSupported(DataFlavor.stringFlavor);
			if (hasTransferableText) {
				try {
					result = (String) contents
							.getTransferData(DataFlavor.stringFlavor);
				} catch (UnsupportedFlavorException ex) {
					//highly unlikely since we are using a standard DataFlavor
					System.out.println(ex);
				} catch (IOException ex) {
					System.out.println(ex);
				}
			}
			return result;
		}
	}

	private class PreferenceListener implements PreferenceChangeListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.prefs.PreferenceChangeListener#preferenceChange(java.util.prefs.PreferenceChangeEvent)
		 */
		public void preferenceChange(PreferenceChangeEvent event) {
			updatePreference(event.getKey(), event);
		}

		public void updatePreference(String key, PreferenceChangeEvent event) {
			if (key.equals(Editor.AUTOSAVE_TIME_KEY)) {
				setAutoSaveTime(event.getNode().getInt(key,
						Editor.DEFAULT_AUTOSAVE));
			} else if (key.equals(Editor.TEXT_SIZE_KEY)) {
				setTextSize(event.getNode()
						.getInt(key, Editor.DEFAULT_TEXTSIZE));
			} else if (key.equals(Editor.TAB_SIZE_KEY)) {
				setTabSize(event.getNode().getInt(key, Editor.DEFAULT_TABSIZE));
			}
		}
	}

	/**
	 * A DocumentListener to respond to changes in the editing area. Listens to
	 * the editing area and updates the GUI in response to change events in the
	 * document.
	 * 
	 * @author Karl A. Gossett
	 */
	protected class MyDocumentListener implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			fireNotifications();
		}

		public void insertUpdate(DocumentEvent e) {
			fireNotifications();
		}

		public void removeUpdate(DocumentEvent e) {
			fireNotifications();
		}

		private void fireNotifications() {
			firePropertyChange("compiled", isCompiled, false);
			isCompiled = false;
			firePropertyChange("saved", saved, false);
			saved = false;
		}
	}

	/**
	 * A DefaultInputHandler that includes the additional input handlers
	 * specific to the editor.
	 * 
	 * @author Karl A. Gossett
	 * @see jedit.DefaultInputHandler
	 */
	protected class EditorInputHandler extends DefaultInputHandler {

		public final ActionListener INCREASE_SIZE = new increaseTextSize();
		public final ActionListener DECREASE_SIZE = new decreaseTextSize();
		public final ActionListener COMMENT_LINES = new commentLines();

		public void addDefaultKeyBindings() {
			super.addDefaultKeyBindings();
			addKeyBinding("C+UP", INCREASE_SIZE);
			addKeyBinding("C+DOWN", DECREASE_SIZE);
			addKeyBinding("CS+C", COMMENT_LINES);
		}

		public class increaseTextSize implements ActionListener {
			public void actionPerformed(ActionEvent evt) {
				int size = prefs.getInt(Editor.TEXT_SIZE_KEY,
						Editor.DEFAULT_TEXTSIZE);
				if (size < MAX_FONT_SIZE) {
					prefs.putInt(Editor.TEXT_SIZE_KEY, size + 2);
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		}

		public class decreaseTextSize implements ActionListener {
			public void actionPerformed(ActionEvent evt) {
				int size = prefs.getInt(Editor.TEXT_SIZE_KEY,
						Editor.DEFAULT_TEXTSIZE);
				if (size > MIN_FONT_SIZE) {
					prefs.putInt(Editor.TEXT_SIZE_KEY, size - 2);
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		}

		/**
		 * Comments/uncomments the selected block of lines using the single-line
		 * comment. <br />
		 * The action is based on the first line of the selected block; if it
		 * starts with a comment marker (&quot;//&quot;) in the first two
		 * positions (must be at the left margin), then this will attempt to
		 * uncomment the selected block of lines. If the first line does not
		 * start with a comment marker, then each line in the selected block
		 * will be commented. <br />
		 * If the language type of this panel is not Java (
		 * {@link eecs.editor.language.LanguageKit.JAVA}) then this does
		 * nothing.
		 * 
		 * @author Karl A. Gossett
		 *  
		 */
		public class commentLines implements ActionListener {
			public void actionPerformed(ActionEvent evt) {
				JEditTextArea textArea = programArea;

				if (!textArea.isEditable()
						|| !languageKit.getLanguage().equals(LanguageKit.JAVA)) {
					textArea.getToolkit().beep();
					return;
				}
				System.err.println("Start commenting");
				textArea.getDocument().beginCompoundEdit();
				int start = textArea.getSelectionStart();
				int end = textArea.getSelectionEnd();
				int startLine = textArea.getLineOfOffset(start);
				int endLine = textArea.getLineOfOffset(end);
				boolean addComment = true;
				int modifiedLines = 0;
				if (textArea.getLineText(startLine).startsWith("//")) {
					addComment = false;
				}
				for (int i = startLine; i <= endLine; ++i) {
					int offset = textArea.getLineStartOffset(i);
					if (addComment) {
						textArea.select(offset, offset);
						textArea.overwriteSetSelectedText("//");
						modifiedLines++;
					} else {
						if (textArea.getLineText(i).startsWith("//")) {
							textArea.select(offset, offset + 2);
							textArea.overwriteSetSelectedText("");
							modifiedLines++;
						}
					}
				}
				if (modifiedLines == 0)
					return;
				if (addComment) {
					textArea.select(start + 2, end + (modifiedLines - 1) * 2
							+ 2);
				} else {
					textArea.select(start - 2, end - (modifiedLines - 1) * 2
							- 2);
				}
				textArea.getDocument().endCompoundEdit();
				System.err.println("End commenting");
			}
		}

	}

}