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
 * Created on Jan 26, 2004
 *
 */
package eecs.editor.text;

import java.awt.*;
import java.io.IOException;
import java.io.StringReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.*;

import eecs.syntax.Lexer.JavaLexer;
import eecs.syntax.Lexer.JavaToken;


public class SyntaxDocument extends DefaultStyledDocument {
  private DefaultStyledDocument doc;
  private transient Element rootElement;
  private Hashtable keywords;
  private transient MutableAttributeSet comment;
  private transient MutableAttributeSet keyword;
  private transient MutableAttributeSet normal;
  private transient MutableAttributeSet quote;
  private transient MutableAttributeSet symbol;
  private transient MutableAttributeSet number;
  private transient MutableAttributeSet error;
  private boolean multiLineComment;
  private int tabSize = 4;
  private String spaces = "    ";
  public SyntaxDocument() {
    doc = this;
    rootElement = doc.getDefaultRootElement();
    putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
    normal = new SimpleAttributeSet();
    StyleConstants.setForeground(normal, Color.black);
    StyleConstants.setItalic(normal, false);
    StyleConstants.setBold(normal, false);
    
    comment = new SimpleAttributeSet();
    Color green = new Color(0, 120, 0);
    StyleConstants.setForeground(comment, green);
    StyleConstants.setItalic(comment, true);
    StyleConstants.setBold(comment, false);
    
    error = new SimpleAttributeSet();
    StyleConstants.setBackground(error, Color.white);
    StyleConstants.setForeground(error, Color.red);
    StyleConstants.setBold(error, true);
    StyleConstants.setItalic(error, false);
    
    keyword = new SimpleAttributeSet();
    Color blue = new Color(0, 0, 140);
    StyleConstants.setForeground(keyword, blue);
    StyleConstants.setBold(keyword, true);
    StyleConstants.setItalic(keyword, false);
    
    quote = new SimpleAttributeSet();
    Color red = new Color(140, 0, 0);
    StyleConstants.setForeground(quote, red);
    StyleConstants.setItalic(quote, false);
    StyleConstants.setBold(quote, false);
    
    symbol = new SimpleAttributeSet();
    StyleConstants.setForeground(symbol, Color.black);
    StyleConstants.setBold(symbol, true);
    StyleConstants.setItalic(symbol, false);
    
    number = new SimpleAttributeSet();
    Color purple = new Color(160, 0, 160);
    StyleConstants.setForeground(number, purple);
    StyleConstants.setBold(number, false);
    StyleConstants.setItalic(number, false);
  }

  /** Sets the number of spaces to use to replace a tab.  Assumes that
   * any maximum constraint is enforced elsewhere, as only a minimum is 
   * enforced by this method (must be greater than 0).
   * @param size  The number of spaces used to replace a tab character
   */
  public void setTabSize(int size) {
    if (size > 0) {
      tabSize = size;
    }
    spaces = "";
    while (size-->0) {
      spaces += " "; 
    }
  }
  
  /**
   * Override to apply syntax highlighting after the document has been updated
   *
   * @param offset DOCUMENT ME!
   * @param str DOCUMENT ME!
   * @param a DOCUMENT ME!
   */
  public void insertString(int offset, String str, AttributeSet a)
    throws BadLocationException {
    if (str.equals("\t")) {
      str = spaces;
    }
    if (str.equals("{")) {
      str = addMatchingBrace(offset);
    }
    if (str.equals("\n")) {
      str = indentNewLine(offset);
    }
    str = convertToBasicLatin(str);
    try {
      super.insertString(offset, str, a);
    } catch (NullPointerException npe) {
      // Not exactly sure what causes this, but it originates in the
      // JTextPane replaceSelection from a KeyTypedAction.
      npe.printStackTrace();
    }
    processChangedLines(offset, str.length());
  }
  
  public String convertToBasicLatin(String original) {
    StringBuffer buffer = new StringBuffer();
    for (int i=0; i<original.length();i++) {
      char curr = original.charAt(i);
      if (Character.getType(curr) == Character.DASH_PUNCTUATION) {
        buffer.append("-");
      } else if (Character.getType(curr) == Character.INITIAL_QUOTE_PUNCTUATION ||
          Character.getType(curr) == Character.FINAL_QUOTE_PUNCTUATION) {
        if (curr == '\u2018' || curr == '\u2019') {
          buffer.append("'");
        } else {
          buffer.append("\"");
        }
      } else if (Character.getType(curr) == Character.CONTROL && "\r\n".indexOf(curr) == -1) {
        buffer.append(spaces);
      } else if (Character.UnicodeBlock.of(curr)!=Character.UnicodeBlock.BASIC_LATIN) {
      
          buffer.append("*");
      } else {
        buffer.append(curr);
      }
    }
    return buffer.toString();
  }

  /**
   * Override to apply syntax highlighting after the document has been updated
   *
   * @param offset DOCUMENT ME!
   * @param length DOCUMENT ME!
   */
  public void remove(int offset, int length) throws BadLocationException {
    int startOffset = offset;
    int endLength = 0;
    if (offset > 0 && doc.getText(offset-1,2).equals("*/")) {
      startOffset = doc.getText(0,offset).lastIndexOf("/*");
      if (startOffset < 0) startOffset = 0;
      endLength = doc.getText(offset,doc.getLength()-offset).indexOf("*/")-startOffset;
      if (endLength < 0) endLength = doc.getLength()-startOffset;
    }
    super.remove(offset, length);
    processChangedLines(startOffset, endLength);
  }

  /**
   * Returns the block-comment end-delimiter
   *
   * @return &quot;*\/&quot;
   */
  protected String getEndDelimiter() {
    return "*/";
  }

  /**
   * Returns the block-comment start delimiter
   *
   * @return DOCUMENT ME!
   */
  protected String getStartDelimiter() {
    return "/*";
  }

  protected String addMatchingBrace(int offset) throws BadLocationException {
    StringBuffer whiteSpace = new StringBuffer();
    int line = rootElement.getElementIndex(offset);
    int i = rootElement.getElement(line).getStartOffset();
    String endOfLine = System.getProperty("line.separator","\n");
    int total = 0;
    while (true) {
      String temp = doc.getText(i, 1);

      if (temp.equals(" ") || temp.equals("\t")) {
        whiteSpace.append(temp);
        i++;
        total ++;
      } else {
        break;
      }
    }
    return "{" + endOfLine + whiteSpace.toString() + whiteSpace.toString() + endOfLine + whiteSpace.toString() + "}";
  }

  private String getLine(String content, int offset) {
    int line = rootElement.getElementIndex(offset);
    Element lineElement = rootElement.getElement(line);
    int start = lineElement.getStartOffset();
    int end = lineElement.getEndOffset();

    return content.substring(start, end - 1);
  }

  private void setMultiLineComment(boolean value) {
    multiLineComment = value;
  }

  /**
   * We have found a start delimiter and are still searching for the end
   * delimiter
   *
   * @return DOCUMENT ME!
   */
  private boolean isMultiLineComment() {
    return multiLineComment;
  }

  /**
   * Parse the line to determine the appropriate highlighting
   *
   * @param content DOCUMENT ME!
   * @param line DOCUMENT ME!
   */
  private void applyHighlighting(String content, int line)
    throws BadLocationException {
    int startOffset = rootElement.getElement(line).getStartOffset();
    int endOffset = rootElement.getElement(line).getEndOffset() - 1;
    int contentLength = content.length();

    if (endOffset >= contentLength) {
      endOffset = contentLength - 1;
    }

    // check for tokens
    if(startOffset>endOffset) {
      //tokensInBold(content, startOffset);
    } else {
      checkForTokens(content, startOffset, endOffset);
    }
  }
  protected boolean unmatchedCommentClosing(String content, int start, int end) {
    boolean unmatched = false;
    Pattern comment = Pattern.compile("/\\*.*?\\*/");
    Matcher match = comment.matcher(content.substring(start,end));
    if (content.substring(start,end).indexOf("*/") != -1 && !match.find()) {
      unmatched = true;
    }
    return unmatched;
  }
  
  protected boolean unmatchedCommentOpening(String content, int start, int end) {
    boolean unmatched = false;
    Pattern comment = Pattern.compile("/\\*.*?\\*/");
    Matcher match = comment.matcher(content.substring(start,end));
    if (content.substring(start,end).indexOf("/*") != -1 && !match.find()) {
      unmatched = true;
    }
    return unmatched;
  }
  
  private void checkForTokens(String content, int startOffset, int endOffset) {
    if (startOffset > endOffset) {
      int temp = startOffset;
      endOffset = startOffset;
      startOffset = temp;
    }
    
    if (startOffset >= 0 && endOffset >= 0) {
      if (unmatchedCommentClosing(content, startOffset, endOffset)) {
        int index=content.lastIndexOf("/*",startOffset-1);
        if (index != -1) startOffset = index;
        int endIndex = content.indexOf("/*",endOffset);
        if (endIndex == -1) {
          endOffset = content.length();
        } else {
          endOffset = endIndex;
        }
      }
      if (unmatchedCommentOpening(content, startOffset, endOffset)) {
        int index=content.indexOf("*/",endOffset-2);
        if (index != -1) {
          endOffset = index;
        } else {
          endOffset = content.length();
        }
      }

      JavaLexer shredder = new JavaLexer(new StringReader(content.substring(startOffset,
          (endOffset>=content.length()?content.length():endOffset+1))));
      JavaToken t;
      try {
        while ((t = (JavaToken)shredder.getNextToken()) != null){
          int start = startOffset + t.getCharBegin();
          int end = startOffset + t.getCharEnd();
          if (t.isWhiteSpace()) {
            doc.setCharacterAttributes(start, end - start,
                normal, false);         
          } else if (t.isSeparator()) {
            doc.setCharacterAttributes(start, end - start,
                symbol, false);          
          } else if (t.isReservedWord()) {
            doc.setCharacterAttributes(start, end - start,
                keyword, false);
          } else if (t.isComment()) {
            doc.setCharacterAttributes(start, end - start,
                comment, false);
          } else if (t.isIdentifier()) {
            doc.setCharacterAttributes(start, end - start,
                normal, false);
          } else if (t.isOperator()) {
            doc.setCharacterAttributes(start, end - start,
                symbol, false);
          } else if (t.isError()) {
            doc.setCharacterAttributes(start, end - start,
                error, false);
          } else if (t.isLiteral()){
            doc.setCharacterAttributes(start, end - start,
                quote, false);
          } else {
            doc.setCharacterAttributes(start, end - start, normal, false);
          }
        }
      } catch (IOException io) {
      } catch (ArrayIndexOutOfBoundsException aio) {
        aio.printStackTrace();
        // Just catch this for now
      } finally {
        
      }
    }
  }
  
  /**
   * Highlight lines to start or end delimiter
   *
   * @param content DOCUMENT ME!
   * @param line DOCUMENT ME!
   */
  private void highlightLinesAfter(String content, int line)
    throws BadLocationException {
    int offset = rootElement.getElement(line).getEndOffset();

    // Start/End delimiter not found, nothing to do
    int startDelimiter = indexOf(content, getStartDelimiter(), offset);
    int endDelimiter = indexOf(content, getEndDelimiter(), offset);

    if (startDelimiter < 0) {
      startDelimiter = content.length();
    }

    if (endDelimiter < 0) {
      endDelimiter = content.length();
    }

    int delimiter = Math.min(startDelimiter, endDelimiter);

    if (delimiter < offset) {
      return;
    }

    // Start/End delimiter found, reapply highlighting
    int endLine = rootElement.getElementIndex(delimiter);

    for (int i = line + 1; i < endLine; i++) {
      Element branch = rootElement.getElement(i);
      Element leaf = doc.getCharacterElement(branch.getStartOffset());
      AttributeSet as = leaf.getAttributes();

      if (as.isEqual(comment)) {
        applyHighlighting(content, i);
      }
    }
  }

  /**
   * Assume the needle will the found at the start/end of the line
   *
   * @param content DOCUMENT ME!
   * @param needle DOCUMENT ME!
   * @param offset DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private int indexOf(String content, String needle, int offset) {
    int index;

    while ((index = content.indexOf(needle, offset)) != -1) {
      String text = getLine(content, index).trim();

      if (text.startsWith(needle) || text.endsWith(needle)) {
        break;
      } else {
        offset = index + 1;
      }
    }

    return index;
  }/**
   * Determine how many lines have been changed, then apply highlighting to
   * each line
   *
   * @param offset DOCUMENT ME!
   * @param length DOCUMENT ME!
   */
  private void processChangedLines(int offset, int length)
    throws BadLocationException {
    String content = doc.getText(0, doc.getLength());

    // The lines affected by the latest document update
    int startLine = rootElement.getElementIndex(offset);
    int endLoc = content.indexOf(getEndDelimiter(),offset-1);
    int endLine;
    if (endLoc <= offset) {
      endLine = rootElement.getElementIndex(rootElement.getEndOffset());
    } else {
      endLine = rootElement.getElementIndex(offset + length);
    }
    
    // Do the actual highlighting
    for (int i = startLine; i <= endLine; i++) {
      applyHighlighting(content, i);
    }
  }

  /**
   * Does this line contain the start delimiter
   *
   * @param content DOCUMENT ME!
   * @param startOffset DOCUMENT ME!
   * @param endOffset DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private boolean startingMultiLineComment(String content, int startOffset,
    int endOffset) throws BadLocationException {
    int index = indexOf(content, getStartDelimiter(), startOffset);

    if ((index < 0) || (index > endOffset)) {
      return false;
    } else {
      setMultiLineComment(true);

      return true;
    }
  }

  /**
   * Assuming the last key typed was <code>enter</code>, automatically indents
   * the new line to line up with the indentation of the previous line.  This
   * method must be called when the caret is at the position of the new line; if
   * it is called at any other time, the character at the current edit point will
   * be replace with a newline character.
   */
  public String indentNewLine(int offset) {
    int start = offset-1;
    int end = 0;
    String insert = "\n";
    try {
	    while (start > 0 && !getText(start,1).equals("\n")) {
	      start--;
	    }
	    end = (start>0?start + 1:0);
	    String current;
	    while ((current = getText(end,1)).equals(" ") || current.equals("\t")) {
	      insert += current;
	      end++;
	    }
    } catch (BadLocationException ble) {
      System.err.println("Location--start: " + start + ", end: " + end);
      ble.printStackTrace();
    }
    return insert;
  }
}
