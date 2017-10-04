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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.*;


public class CssSyntaxDocument extends DefaultStyledDocument {
	transient private DefaultStyledDocument doc;
	transient private Element rootElement;
  transient private MutableAttributeSet attribute;
  transient private MutableAttributeSet comment;
  transient private MutableAttributeSet property;
  transient private MutableAttributeSet normal;
  transient private MutableAttributeSet quote;
  transient private MutableAttributeSet error;
  
  public CssSyntaxDocument() {
    doc = this;
    rootElement = doc.getDefaultRootElement();
    putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
    normal = new SimpleAttributeSet();
    StyleConstants.setForeground(normal, Color.black);
    
    comment = new SimpleAttributeSet();
    Color green = new Color(0, 120, 0);
    StyleConstants.setForeground(comment, green);
    StyleConstants.setItalic(comment, true);
    
    error = new SimpleAttributeSet();
    Color errorred = new Color(230, 25, 25);
    StyleConstants.setForeground(error, errorred);
    StyleConstants.setItalic(error, true);
    StyleConstants.setBold(error,true);
    
    property = new SimpleAttributeSet();
    Color blue = new Color(0, 0, 140);
    StyleConstants.setForeground(property, blue);
    StyleConstants.setBold(property, true);
    
    quote = new SimpleAttributeSet();
    attribute = new SimpleAttributeSet();
    Color amber = new Color(140,30,30);
    StyleConstants.setForeground(attribute,amber);
    StyleConstants.setBold(attribute,true);
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

  	//super.insertString(offset,str,attribute);
    super.insertString(offset, str, a);
    processChangedLines(offset, str.length());
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
  
  protected boolean unmatchedCommentClosing(String content, int start, int end) {
  	boolean unmatched = false;
  	Pattern comment = Pattern.compile("/\\*.*?\\*/");
  	Matcher match = comment.matcher(content.substring(start,end));
  	if (content.substring(start,end).indexOf("*/") != -1 && !match.find()) {
  		unmatched = true;
  		System.out.println("Unmatched close " +start);
  	}
  	return unmatched;
  }
  
  protected boolean unmatchedCommentOpening(String content, int start, int end) {
  	boolean unmatched = false;
  	Pattern comment = Pattern.compile("/\\*.*?\\*/");
  	Matcher match = comment.matcher(content.substring(start,end));
  	if (content.substring(start,end).indexOf("/*") != -1 && !match.find()) {
  		unmatched = true;
  		System.out.println("Unmatched open " +start);
  	}
  	return unmatched;
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
    int lineLength = endOffset - startOffset;
    int contentLength = content.length();

    if (endOffset >= contentLength) {
      endOffset = contentLength - 1;
    }

    // set normal attributes for the line
    doc.setCharacterAttributes(startOffset, lineLength, normal, true);

    // check for tokens
    checkForTokens(content, startOffset, endOffset);
  }

  private void checkForTokens(String content, int startOffset, int endOffset) {
   
  	if (startOffset<endOffset && startOffset >-1 && endOffset >-1) {
  		if (unmatchedCommentClosing(content, startOffset, endOffset)) {
  			int index=content.lastIndexOf("/*",startOffset-1);
  			if (index != -1) startOffset = index;
  		}
  		if (unmatchedCommentOpening(content, startOffset, endOffset)) {
  			int index=content.indexOf("*/",endOffset-3);
  			if (index != -1) endOffset = index+2;
  		}
	  	Pattern prop = Pattern.compile("\\s*.*?\\{");
	  	Matcher match = prop.matcher(content.substring(startOffset, endOffset));
	  	while (match.find()) {
	  		int start = match.start();
	  		int end = match.end();
	  		System.out.println("*{ start " +start + " to " +end);
	  		doc.setCharacterAttributes(start+startOffset, end - start +startOffset-1,
	  				property, false);
	  	}
	  	Pattern endGroup = Pattern.compile("\\}");
	  	Matcher group = endGroup.matcher(content.substring(startOffset, endOffset));
	  	while (group.find()) {
	  		int start = group.start();
	  		int end = group.end();
	  		System.out.println("} start " +start + " to " +end);
	  		doc.setCharacterAttributes(start+startOffset, end - start+startOffset-1,
	  				property, false);
	  	}
	  	
//	  	Pattern attr = Pattern.compile("\\s*.*?\\:\\s*?.*?\\s*?");
//	  	Matcher atmatch = attr.matcher(content.substring(startOffset,endOffset));
//	  	while (atmatch.find()) {
//	  		int start = atmatch.start();
//	  		int end = atmatch.end();
//	  		doc.setCharacterAttributes(start+startOffset, end - start+startOffset,
//	  				attribute, false);
//	  	}
	  	
	  	Pattern cmt = Pattern.compile("/\\*.*?\\*/");
	  	Matcher cmtmatch = cmt.matcher(content.substring(startOffset,endOffset));
	  	while (cmtmatch.find()) {
	  		int start = cmtmatch.start();
	  		int end = cmtmatch.end();

	  		System.out.println("/**/ start " +start + " to " +end);
	  		doc.setCharacterAttributes(start+startOffset, end - start+startOffset-1,
	  				comment, false);
	  	}
	  	Pattern attr = Pattern.compile("\\s*[^\\{\\}]*?\\:\\s*?[^\\{\\}\\;\\ ]*?");
	  	Matcher atmatch = attr.matcher(content.substring(startOffset,endOffset));
	  	while (atmatch.find()) {
	  		int start = atmatch.start();
	  		int end = atmatch.end();
	  		System.out.println("attribute start " +start + " to " +end);
	  		doc.setCharacterAttributes(start+startOffset, end - start+startOffset,
	  				attribute, false);
	  	}	  	
  	}
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
    char curr;
    // Back the offset up to the last character typed
    offset--;
    while (offset > 0 && (curr = content.charAt(offset))!='{' && curr != '}') {
    	offset--;
    	length++;
    }
    // Do the actual highlighting
    for (int i = startLine; i <= endLine; i++)
      applyHighlighting(content, i);

  }

}
