package eecs.editor.util;

import javax.swing.text.Segment;
import eecs.editor.jedit.Token;

public class TextUtilities {
  public static String getSyntaxLine(Segment line, Token tokens) {
    String htmlLine = "";
    int offset = 0;
    for (;;) {
      byte id = tokens.id;
      String style = "";
      if (id == Token.END)
        break;
      int length = tokens.length;
      if (id == Token.COMMENT1 || id == Token.COMMENT2) {
        style = "codekeyword";
      }
      else
        if (id == Token.KEYWORD1 || id == Token.KEYWORD2 || id == Token.KEYWORD3) {
          style = "codecomment";
        }
        else
          if (id == Token.LITERAL1) {
            style = "codeliteral";
          }
      line.count = length;
      if (style.length() == 0) {
        htmlLine += toHTML(line.toString());
      }
      else {
        htmlLine += "<span class=\"" + style + "\">" + toHTML(line.toString()) + "</span>";
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