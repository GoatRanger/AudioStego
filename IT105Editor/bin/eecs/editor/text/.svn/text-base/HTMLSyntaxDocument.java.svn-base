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
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.editor.text;

import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.*;

import eecs.syntax.Lexer.HTMLLexer;
import eecs.syntax.Lexer.HTMLToken;


public class HTMLSyntaxDocument extends DefaultStyledDocument {
  private DefaultStyledDocument doc;
  private transient Element rootElement;
  private transient MutableAttributeSet normal;
  
  public HTMLSyntaxDocument() {
    doc = this;
    rootElement = doc.getDefaultRootElement();
    putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
    normal = new SimpleAttributeSet();
    StyleConstants.setForeground(normal, Color.black);
    
    Style style = addStyle("separator", null);
    StyleConstants.setBackground(style, Color.white);
    StyleConstants.setForeground(style, new Color(0x000080) /*Color.navy*/);
    StyleConstants.setBold(style, false);
    StyleConstants.setItalic(style, false);
    
    style = addStyle("comment", null);
    StyleConstants.setBackground(style, Color.white);
    StyleConstants.setForeground(style, Color.green.darker());
    StyleConstants.setBold(style, false);
    StyleConstants.setItalic(style, false);
    
    style = addStyle("script", null);
    StyleConstants.setBackground(style, Color.white);
    StyleConstants.setForeground(style, new Color(0x9933FF));
    StyleConstants.setBold(style, false);
    StyleConstants.setItalic(style, false);
    
    style = addStyle("tag", null);
    StyleConstants.setBackground(style, Color.white);
    StyleConstants.setForeground(style, Color.blue);
    StyleConstants.setBold(style, true);
    StyleConstants.setItalic(style, false);

    style = addStyle("name", null);
    StyleConstants.setBackground(style, Color.white);
    StyleConstants.setForeground(style, new Color(0xB03060));
    StyleConstants.setBold(style, true);
    StyleConstants.setItalic(style, false);    

    style = addStyle("value", null);
    StyleConstants.setBackground(style, Color.white);
    StyleConstants.setForeground(style, new Color(0xB03060));
    StyleConstants.setBold(style, false);
    StyleConstants.setItalic(style, true);
    
    style = addStyle("reference", null);
    StyleConstants.setBackground(style, Color.white);
    StyleConstants.setForeground(style, Color.black);
    StyleConstants.setBold(style, false);
    StyleConstants.setItalic(style, false);
    
    style = addStyle("error", null);
    StyleConstants.setBackground(style, Color.white);
    StyleConstants.setForeground(style, Color.red);
    StyleConstants.setBold(style, true);
    StyleConstants.setItalic(style, false);
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

    String text = replaceQuotes(str);
    if (text.equals("\n")) {
      text = indentNewLine(offset);
    }
    for (int i=0;i<text.length();i++) {
    	if (text.charAt(i)>254) {
    		System.out.println("Character: " + text.charAt(i) +", " +(int)text.charAt(i));
    	}
    }
    super.insertString(offset, text, a);
    processChangedLines(offset, text.length());
  }

  private String replaceQuotes(String str) {
  	Pattern pat = Pattern.compile("\\u2018|\\u2019");
  	Matcher match = pat.matcher(str);
  	str = match.replaceAll("'");
  	Pattern pat2 = Pattern.compile("\\u201c|\\u201d");
  	Matcher match2=pat2.matcher(str);
  	str = match2.replaceAll(""+'"');
  	return str;
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
	    while (!getText(start,1).equals("\n") && start > 0) {
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
  
  /**
   * Override to apply syntax highlighting after the document has been updated
   *
   * @param offset DOCUMENT ME!
   * @param length DOCUMENT ME!
   */
  public void remove(int offset, int length) throws BadLocationException {
    super.remove(offset, length);
    processChangedLines(offset, 0);
  }

  /**
   * Parse the line to determine the appropriate highlighting
   *
   * @param content DOCUMENT ME!
   * @param line DOCUMENT ME!
   */
  private void applyHighlighting(String content, int line)
    throws BadLocationException {
    int startOffset = rootElement.getElement(line >= 10?line-10:line).getStartOffset();
    int endOffset = rootElement.getElement(line).getEndOffset() - 1;
    int lineLength = endOffset - startOffset;
    int contentLength = content.length();

    if (endOffset >= contentLength) {
      endOffset = contentLength - 1;
    }

    doc.setCharacterAttributes(startOffset, lineLength, normal, true);
    checkForTokens(content, startOffset, endOffset);
  }

  private void checkForTokens(String content, int startOffset, int endOffset) {
  	if (startOffset > endOffset) {
  		int temp = startOffset;
  		endOffset = startOffset;
  		startOffset = temp;
  	}
  	if (startOffset >= 0 && endOffset >= 0) {
	  	if (unmatchedCommentClosing(content, startOffset, endOffset)) {
	  		int index=content.lastIndexOf("<!--",startOffset-1);
	  		if (index != -1) startOffset = index;
	  	}
	  	if (unmatchedCommentOpening(content, startOffset, endOffset)) {
	  		int index=content.indexOf("-->",endOffset-3);
	  		if (index != -1) endOffset = index;
	  	}

	  	HTMLLexer shredder = new HTMLLexer(new StringReader(content.substring(startOffset,
	  			(endOffset>=content.length()?content.length():endOffset+1))));
	  	HTMLToken t;
	  	try {
	  		while ((t = (HTMLToken)shredder.getNextToken()) != null){
	  			int start = startOffset + t.getCharBegin();
	  			int end = startOffset + t.getCharEnd();
	  			if (t.isScript()) {
	  				doc.setCharacterAttributes(start, end - start,
	  						getStyle("script"), false); 				
	  			} else if (t.isSeparator()) {
	  				doc.setCharacterAttributes(start, end - start,
	  						getStyle("separator"), false);  				
	  			} else if (t.isTagName()) {
	  				doc.setCharacterAttributes(start, end - start,
	  						getStyle("tag"), false);
	  			} else if (t.isComment()) {
	  				doc.setCharacterAttributes(start, end - start,
	  						getStyle("comment"), false);
	  			} else if (t.isName()) {
	  				doc.setCharacterAttributes(start, end - start,
	  						getStyle("name"), false);
	  			} else if (t.isValue()) {
	  				doc.setCharacterAttributes(start, end - start,
	  						getStyle("value"), false);
	  			} else if (t.isError()) {
	  				doc.setCharacterAttributes(start, end - start,
	  						getStyle("error"), false);
	  			} else if (t.isCharacterReference()){
	  				doc.setCharacterAttributes(start, end - start,
	  						getStyle("reference"), false);
	 
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
  
  protected boolean unmatchedCommentClosing(String content, int start, int end) {
  	boolean unmatched = false;
  	Pattern comment = Pattern.compile("<!--.*?-->");
  	Matcher match = comment.matcher(content.substring(start,end));
  	if (content.substring(start,end).indexOf("-->") != -1 && !match.find()) {
  		unmatched = true;
  	}
  	return unmatched;
  }
  
  protected boolean unmatchedCommentOpening(String content, int start, int end) {
  	boolean unmatched = false;
  	Pattern comment = Pattern.compile("<!--.*?-->");
  	Matcher match = comment.matcher(content.substring(start,end));
  	if (content.substring(start,end).indexOf("<!--") != -1 && !match.find()) {
  		unmatched = true;
  	}
  	return unmatched;
  }

  /**
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
    int endLine = rootElement.getElementIndex(offset + length);

    // Do the actual highlighting
    for (int i = startLine; i <= endLine; i++)
      applyHighlighting(content, i);

  }

}
