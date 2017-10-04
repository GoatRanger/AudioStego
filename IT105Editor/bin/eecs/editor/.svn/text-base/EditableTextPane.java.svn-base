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

package eecs.editor;

import eecs.editor.language.LanguageKit;

import eecs.editor.text.CssSyntaxDocument;
import eecs.editor.text.HTMLSyntaxDocument;
import eecs.editor.text.SyntaxDocument;
//import eecs.editor.text.java.JavaEditorKit;

import java.awt.*;

import java.io.*;

import java.util.*;
import java.util.logging.*;
import java.util.regex.*;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;

/**
 * This class implements a JTextPane to provide support for text editing and
 * coloring.  The coloring is used for sytax coloring; the sytax coloring
 * package is the GNU Syntax Coloring package from Steven Ostermiller, {@link
 * www.ostermiller.org} The remainder of this class is a modified form of Dr.
 * Chris Okasaki's (USMA) APES editing package (originally written for a
 * JTextArea).
 */
public class EditableTextPane extends JTextPane {
  /** DOCUMENT ME! */
  private static final int MIN_TAB_SIZE = 1;

  /** DOCUMENT ME! */
  private static final int MAX_TAB_SIZE = 32;

  /** DOCUMENT ME! */
  private static final int DEFAULT_FONT_SIZE = 16;

  /** DOCUMENT ME! */
  private static final int MAX_FONT_SIZE = 100;

  /** DOCUMENT ME! */
  private static final int MIN_FONT_SIZE = 4;

  /** DOCUMENT ME! */
  private static final String maxSpaces = "                                "; // 32 spaces

  /** DOCUMENT ME! */
  protected transient Logger logger = Logger.getLogger("eecs.editor");

  /** Frozen text */
  private String frozenText = "";

  /** DOCUMENT ME! */
  private String language = "java";

  /** Tab */
  private int tabSize = 4;

  /** The size of the text, in points */
  private int textSize = DEFAULT_FONT_SIZE;

  private int selectionStart = 0;
  private int selectionEnd = 0;
  
  private CaretListener caretListener = new CaretListener() {

    public void caretUpdate(CaretEvent e) {
      int dot = e.getDot();
      int mark = e.getMark();
      
      if (dot < mark) {
        selectionStart = dot;
        selectionEnd = mark;
      } else {
        selectionStart = mark;
        selectionEnd = dot;
      }
    }
  };
  
  private ArrayList listeners;
  
  private ArrayList undoListeners = new ArrayList();
  /**
   * Creates a new EditableTextPane object.
   *
   * @param text A String of text to initialize the window.
   */
  public EditableTextPane(String text) {
    super();
    listeners = new ArrayList();
    enableEvents(AWTEvent.KEY_EVENT_MASK);
    addCaretListener(caretListener);
    // create the new document.
    setDocument(new DefaultStyledDocument());
    //DefaultEditorKit kit = new JavaEditorKit();
    //setEditorKitForContentType("text/java", kit);
    
    // Default to text
    // TODO: Add a constructor that allows language selection for syntax coloring
    setLanguage(LanguageKit.TEXT);

    setEditable(true); // is true the default?

    if (text == null) {
      text = "";
    }

    super.setText(text);
  }

  public void addDocumentListener(DocumentListener l) {
  	listeners.add(l);
  	getDocument().addDocumentListener(l);
  }
  
  public void addUndoableEditListener(UndoableEditListener listener) {
    undoListeners.add(listener);
    getDocument().addUndoableEditListener(listener);
  }
  /**
   * Sets the language for the editor (affects compile, run, syntax coloring).
   *
   * @param lang The language to use, one of: <ul><li>text/java</li>
   *        <li>text/html</li> <li>text/css</li> <li>text/plain</li></ul>
   */
  public void setLanguage(String lang) {
  	if (logger.isLoggable(Level.FINEST)) {
      logger.entering(this.getClass().getName(), "setLanguage");
    }
    language = lang.toLowerCase();
    String text = getText();
    if (lang.equals(LanguageKit.JAVA)) {
      //setDocument(new SyntaxDocument());
      setContentType("text/java");
    } else if (lang.equalsIgnoreCase(LanguageKit.CSS)) {      
      setDocument(new CssSyntaxDocument());
    } else if (lang.equalsIgnoreCase("java properties")) {
    	setDocument(new DefaultStyledDocument());
    } else if (lang.equalsIgnoreCase(LanguageKit.HTML)) {
      setDocument(new HTMLSyntaxDocument());
    } else if (lang.equalsIgnoreCase(LanguageKit.TEXT)) {
      setDocument(new DefaultStyledDocument());
    }
    if (text.length() > 0) {
      setText(text);
    }
    updateListeners();
    
    if (logger.isLoggable(Level.FINEST)) {
      logger.exiting(this.getClass().getName(), "setLanguage");
    }
  }

  private void updateListeners() {
  	if (listeners != null) {
		for (int i=0;i<listeners.size();i++) {
	    	getDocument().addDocumentListener((DocumentListener)listeners.get(i));
	    }
  	}
  	if (undoListeners != null) {
	    for (int i=0;i<undoListeners.size();i++) {
	    	getDocument().addUndoableEditListener((UndoableEditListener)undoListeners.get(i));
	    }
  	}
}

/**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int getLineCount() {
    return getDocument().getDefaultRootElement().getElementCount();
  }

  /**
   * DOCUMENT ME!
   *
   * @param line DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int getLineEndOffset(int line) {
  	Document doc = getDocument();
    Element e = doc.getDefaultRootElement().getElement(line);
    int pos = 0;
    if (e != null) {
    	pos = e.getEndOffset();
    }
    // Because a document models an implied break at the end, getEndOffset
    // might return a value past the end of the document. If so, correct it.
    if (pos >= doc.getLength()) {
      pos = doc.getLength();
    }

    return pos;
  }

  /**
   * Returns the line number that the specified offset is in.
   *
   * @param pos The offset
   *
   * @return The line number
   */
  public int getLineOfOffset(int pos) {
    return 
    	getDocument().getDefaultRootElement().getElementIndex(pos) + 1;
  }

  /**
   * Returns the offset of the start of the specified line.
   *
   * @param line An integer line number.
   *
   * @return The offset into the buffer of the start of the line.
   */
  public int getLineStartOffset(int line) {
    return getDocument().getDefaultRootElement().getElement(line).getStartOffset();
  }

  /**
   * Overriden to prevent word wrapping.  Requires that setSize be overriden
   * to prevent artifacts (because size of window defaults to the amount of
   * text in the window).
   *
   * @see #setSize
   */
  public boolean getScrollableTracksViewportWidth() {
    return false; // Returning false for non-wrapping
  }

  /**
   * Overriden to support removal of auto word-wrap. If this isn't
   * overriden, but getScrollableTracksViewportWidth is, then the window
   * will have a white background where there is text, but gray on the
   * right (for any unoccupied portion of the window).
   *
   * @param d DOCUMENT ME!
   */
  public void setSize(Dimension d) {
    if (d.width < getParent().getSize().width) {
      d.width = getParent().getSize().width;
    }

    super.setSize(d);
  }

  /**
   * DOCUMENT ME!
   *
   * @param size DOCUMENT ME!
   */
  public void setTabSize(int size) {
    tabSize = Math.min(Math.max(size, MIN_TAB_SIZE), MAX_TAB_SIZE);
    // TODO: Create a new abstract Document to provide tab functionality to all types
    Document doc = getDocument();
    if (doc instanceof SyntaxDocument) {
      ((SyntaxDocument)doc).setTabSize(tabSize);
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int getTabSize() {
    return tabSize;
  }

  /**
   * Sets the contents of the buffer to the specified text.
   *
   * @param text The String contents.
   */
  public void setText(String text) {
    super.setText(text);
    getCaret().setDot(0);
  }

  /**
   * Sets the size of the font in the window.
   *
   * @param points The size of the text, in points.
   */
  public void setTextSize(int points) {
    textSize = points;

    if (points <= MIN_FONT_SIZE) {
      textSize = MIN_FONT_SIZE;
    }

    if (points >= MAX_FONT_SIZE) {
      textSize = MAX_FONT_SIZE;
    }
    this.setFont(new Font("monospaced", Font.PLAIN, textSize));
    
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int getTextSize() {
    return textSize;
  }

  /**
   * Cuts from the cursor to the end of the line (F5)
   */
  public void cutLine() {
    try {
      Caret caret = getCaret();
      int pos = caret.getDot();
      int line = getLineOfOffset(pos);
      int endpos = getLineEndOffset(line-1);

      if (pos == endpos) {
        return;
      }

      caret.setDot(pos);

      // set mark to pos even if there was already a selection
      caret.moveDot(endpos);
      cut();
    } catch (Exception exn) {
      logger.log(Level.WARNING, "Cut line failed.", exn);
    }
  }

  /**
   * Reduces the size of the text by 2 points
   */
  public void decreaseTextSize() {
    setTextSize(textSize - 2);
  }

  /**
   * Find the next occurrence of the specified search string.  Uses regular
   * expression matching.
   *
   * @param searchFor A String pattern to search for (regex)
   * @param isCaseSensitive A boolean that is <code>true</code> if the
   *        search should be case sensitive.
   * @param wholeWordOnly A boolean that is <code>true</code> if the search
   *        should only match whole words.
   *
   * @return <code>true</code> if the search pattern was found.
   */
  public boolean find(String searchFor, boolean isCaseSensitive,
    boolean wholeWordOnly) {
    boolean found = false;
    int flags;
    int startPos = this.getCaret().getDot() + 1;

    if (isCaseSensitive) {
      flags = 0;
    } else {
      flags = Pattern.CASE_INSENSITIVE;
    }

    String findMe = searchFor;

    if (wholeWordOnly) {
      findMe = "\\b" + findMe + "\\b";
    }

    Pattern pattern = Pattern.compile(findMe, flags);
    Matcher matcher = pattern.matcher(getText());

    if (matcher.find(startPos)) {
      found = true;
    } else if (startPos != 0) {
      startPos = 0;

      if (matcher.find(startPos)) {
        found = true;
      }
    }

    if (found) {
      setCaretPosition(matcher.start());
      moveCaretPosition(matcher.end());
    }

    return found;
  }

  /**
   * Increases the size of the text in the editor by 2 points
   */
  public void increaseTextSize() {
    setTextSize(textSize + 2);
  }

  /**
   * Loads a file into the edit buffer.
   *
   * @param file The <code>File</code> that specifies which file to load.
   *
   * @throws IOException If the file cannot be opened.
   */
  public void loadFromFile(File file) throws IOException {
    FileInputStream fis = null;
    Reader in = null;

    try {
      fis = new FileInputStream(file);
      in = new FileReader(file);
	
	    this.read(in, null);
    } catch (FileNotFoundException fnfe) {
    } finally {
    	try {
	    	if (in != null) {
	    		in.close();
	    	}
	    	in = null;
    	} catch (Exception ignored) {}
    	try {
	    	if (fis != null) {
	    		fis.close();
	    	}
	    	fis = null;
    	} catch (Exception ignored) {}
    }
  }

  /**
   * Read in a file without creating a new document.
   *
   * @param in the Reader to read from.
   * @param description not used.
   *
   * @throws IOException if an error occurs while reading from the file.
   */
  public void read(Reader in, Object description) throws IOException {
    // replace current text, but DO NOT create a new document (would mess up undo/redo)
    // ignores desc.
    // there must be a better way to read into a string.
    BufferedReader reader = new BufferedReader(in);
    String text = "";
    try {
	    for (String line = reader.readLine(); line != null;
	          line = reader.readLine()) {
	      text = text + (line + "\n");
	    }
	    text = text.replaceAll("\\t",maxSpaces.substring(0,getTabSize()));
	    setText(text);
    } finally {
    	reader.close();
    }
  }

  /**
   * Find a substring in the document and replace it with the specified
   * text.
   *
   * @param searchPattern The String of text to find
   * @param replace The String to replace with
   * @param isCaseSensitive a boolean indicating if case should be used in
   *        the search
   * @param matchWholeWords a boolean indicating if the search should only
   *        replace when the searchPattern matches a complete word (not a
   *        substring)
   *
   * @return <code>true</code> if a replacement was made.
   *
   * @see #find
   */
  public boolean replace(String searchPattern, String replace,
    boolean isCaseSensitive, boolean matchWholeWords) {
    boolean success = false;

    if (find(searchPattern, isCaseSensitive, matchWholeWords)) {
      replaceSelection(replace);
      success = true;
    }

    return success;
  }
  
  /* (non-Javadoc)
   * @see javax.swing.JTextPane#replaceSelection(java.lang.String)
   */
  public void replaceSelection(String newText) {
    // TODO Auto-generated method stub
    if (newText.equals("\t")) {
      int selectionStart = getCaret().getDot();
      int selectionEnd = getCaret().getMark();
      if (selectionStart > selectionEnd) {
        int temp = selectionStart;
        selectionStart = selectionEnd;
        selectionEnd = temp;
      }
      if (selectionStart != selectionEnd) {
        // Subtract 1 from following to account for 0-based getLineStartOffset
        int startLine = getLineOfOffset(selectionStart)-1;
        int endLine = getLineOfOffset(selectionEnd)-1;
        // Working backward to ensure that a new insert doesn't affect subsequent ones
        for (int i=endLine; i>=startLine;i--) {
          int start = getLineStartOffset(i);
          getCaret().setDot(start);
          super.replaceSelection("    ");
        }
        getCaret().setDot(selectionStart+4);
        getCaret().moveDot(selectionEnd+4*(endLine-startLine+1));
      } else {
        super.replaceSelection(newText);
      }
    } else {
      System.err.println(newText);
      super.replaceSelection(newText);
    }
  }
  
  /**
   * Replaces all instances of a specified string with another string.
   *
   * @param searchPattern The pattern to find
   * @param replace The new text
   * @param isCaseSensitive <code>true</code> if the replacement is case sensitive
   * @param matchWholeWords <code>true</code> only if the pattern should only match whole words
   *
   * @return <code>true</code> if any replacements were made
   */
  public boolean replaceAll(String searchPattern, String replace,
    boolean isCaseSensitive, boolean matchWholeWords) {
    boolean replacedAny = false;
    int caseInsensitive;

    if (isCaseSensitive) {
      caseInsensitive = 0;
    } else {
      caseInsensitive = Pattern.CASE_INSENSITIVE;
    }

    String findMe = searchPattern;

    if (matchWholeWords) {
      findMe = "\\b" + findMe + "\\b";
    }

    Pattern pattern = Pattern.compile(findMe, caseInsensitive);
    Matcher matcher = pattern.matcher(getText());

    if (matcher.find()) {
      replacedAny = true;
      setText(matcher.replaceAll(replace));
    }

    return replacedAny;
  }

  /**
   * Saves the current document to the specified file.
   *
   * @param file The <code>File</code> object where the document should be saved.
   */
  public void saveToFile(File file) throws IOException {
  	FileWriter writer = null;
  	PrintWriter out = null;
  	try {
	  	writer = new FileWriter(file);
	    out = new PrintWriter(writer);
	    out.print(getText());
  	} finally {
  		if (out != null) {
  			out.flush();
  		}
  		if (writer != null) {
  			writer.flush();
  			writer.close();
  		}
  	}
  }
}
