/*
 * Created on Jun 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.editor.util;

import javax.swing.text.Segment;

import eecs.editor.Messages;

import jedit.Token;

/**
 * @author DK8685
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TextUtilities {
    /**
     * Paints the specified line onto the graphics context. Note that this
     * method munges the offset and count values of the segment.
     * @param line The line segment
     * @param tokens The token list for the line
     * @param styles The syntax style list
     * @param expander The tab expander used to determine tab stops. May
     * be null
     * @param gfx The graphics context
     * @param x The x co-ordinate
     * @param y The y co-ordinate
     * @return The x co-ordinate, plus the width of the painted string
     */
    public static String getSyntaxLine(Segment line, Token tokens)
    {
        String htmlLine = "";
        int offset = 0;
        for(;;)
        {
            byte id = tokens.id;
            String style = "";
            if(id == Token.END)
                break;
            int length = tokens.length;
            if(id == Token.COMMENT1 || id == Token.COMMENT2) {
                style = Messages.getString("html.comment.class");
            } else if (id == Token.KEYWORD1 || id == Token.KEYWORD2 || id == Token.KEYWORD3) {
            	style = Messages.getString("html.keyword.class");
            } else if (id == Token.LITERAL1) {
              style = Messages.getString("html.literal.class");  
            }
            line.count = length;
            if (style.length() == 0) {
             htmlLine += toHTML(line.toString());  
            } else {
             htmlLine += "<span class=\"" + style + "\">" + toHTML(line.toString()) +"</span>";   
            }
            line.offset += length;
            offset += length;

            tokens = tokens.next;
        }
        return htmlLine + "\n";
    }
    
    private static String toHTML(String input) {
      String result = input;
      result = result.replaceAll("\r", "");
      result = result.replaceAll("&", "&amp;");
      result = result.replaceAll("<", "&lt;");
      result = result.replaceAll(">", "&gt;");
      result = result.replaceAll("\n", "<br />");
      result = result.replaceAll("\"", "&quot;");
      return result;
    }
}
