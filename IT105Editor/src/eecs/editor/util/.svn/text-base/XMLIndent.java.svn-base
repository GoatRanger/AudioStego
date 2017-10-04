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
 * XMLIndent.java
 *
 * Created on June 23, 2003, 8:51 AM
 */
package eecs.editor.util;

import eecs.editor.language.LanguageKit;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;


/**
 *
 * @author  dk8685
 */
public class XMLIndent {
  StringBuffer errorBuffer = new StringBuffer();
  StringBuffer buffer;
  int tabSize = 2;
  private int resultCode = LanguageKit.NOT_SUPPORTED;
  String[] selfClosingList = { "img", "link", "br", "hr", "META" };
  // title and a are added to the inline to make formatting look nicer
  String[] inlineElements = {
    "a", "abbr", "acronym", "b", "big", "cite", "code", "dfn", "em", "kbd", "q",
    "samp", "small", "span", "strong", "sub", "sup", "title", "var", "i"
  };
  DefaultHandler handler = new FormatHandler();

  /** Sets the number of spaces to indent for a tab (each indent level is a 'tab')
 * Values < 1 default to 0; maximum is 10.
 *
 * @param spaces  The number of spaces for each indent.
 */
  public void setTabSize(final int spaces) {
    tabSize = spaces;

    if (spaces < 0) {
      tabSize = 0;
    }

    if (spaces > 10) {
      tabSize = 10;
    }
  }

  public int formatDocument(File file) {
    resultCode = LanguageKit.REFORMAT_SUCCESS;
    buffer = new StringBuffer();

    javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
    javax.xml.parsers.DocumentBuilder parser = null;
    org.w3c.dom.Document document = null;
    String publicID = "";
    String systemID = "";

    try {
      // Now use the factory to create a DOM parser (a.k.a. a DocumentBuilder)
      factory.setValidating(true);
      parser = factory.newDocumentBuilder();
      parser.setErrorHandler(handler);
      document = parser.parse(file);
      publicID = document.getDoctype().getPublicId();
      systemID = document.getDoctype().getSystemId();

      StringBuffer headerBuffer = new StringBuffer();
      headerBuffer.append("<?xml version=\"1.0\" encoding=\"" + "UTF-8" +
        "\"?>");
      headerBuffer.append('\n');
      headerBuffer.append("<!DOCTYPE html PUBLIC \"" + publicID + "\"\n\"" +
        systemID + "\">");
      buffer.insert(0, headerBuffer.toString());

      //String result = localBuffer.toString() + buffer.toString();
      // Parse the file and build a Document tree to represent its content
    } catch (org.xml.sax.SAXParseException spe) {
      //			   spe.printStackTrace();
    } catch (org.xml.sax.SAXException se) {
      //			   se.printStackTrace();
    } catch (javax.xml.parsers.ParserConfigurationException pce) {
      //			   pce.printStackTrace();
    } catch (java.io.IOException e) {
      //			   e.printStackTrace();
    } catch (Exception e) {
      //			   e.printStackTrace();
    }

    processNode(document, "");

    int result = getResultCode();

    return result;

    //        if (result.length() == 0) {
    //            result = null;
    //        }
    //
    //        return result;
  }

  private int getResultCode() {
    return resultCode;
  }

  public String getResultMessages() {
    return buffer.toString();
  }

  public void processNode(Node node, String indent) {
    String spaces = indent;
    boolean hadOnlyText = false;

    if (node == null) {
      return;
    }

    int type = node.getNodeType();
    boolean selfClosing = false;

    for (int i = 0; i < selfClosingList.length; i++) {
      if (node.getNodeName().equalsIgnoreCase(selfClosingList[i])) {
        selfClosing = true;

        break;
      }
    }

    boolean isInlineElement = false;

    for (int i = 0; i < inlineElements.length; i++) {
      if (node.getNodeName().equalsIgnoreCase(inlineElements[i])) {
        isInlineElement = true;

        break;
      }
    }

    switch (type) {
    case Node.DOCUMENT_NODE: {
      processNode(((Document) node).getDocumentElement(), "");

      break;
    }

    case Node.ELEMENT_NODE: {
      // currently a hack to ensure title tag is indented.  Treated
      // as an inline element for formatting, but really isn't
      //					if (!isInlineElement) {
      buffer.append('\n');
      buffer.append(spaces);

      //					}
      buffer.append("<");
      buffer.append(node.getNodeName());

      int length = (node.getAttributes() != null)
        ? node.getAttributes().getLength() : 0;

      Attr[] attributes = new Attr[length];

      for (int loopIndex = 0; loopIndex < length; loopIndex++) {
        attributes[loopIndex] = (Attr) node.getAttributes().item(loopIndex);
      }

      for (int loopIndex = 0; loopIndex < attributes.length; loopIndex++) {
        Attr attribute = attributes[loopIndex];

        // if namespace but not html tag, skip it
        if (attribute.getNodeName().equals("xmlns") &&
              !node.getNodeName().equals("html")) {
          continue;
        }

        buffer.append(" ");
        buffer.append(attribute.getNodeName());
        buffer.append("=\"");
        buffer.append(attribute.getNodeValue());
        buffer.append("\"");
      }

      if (selfClosing) {
        buffer.append(" />");
      } else {
        buffer.append(">");
      }

      NodeList childNodes = node.getChildNodes();

      if (childNodes != null) {
        length = childNodes.getLength();

        for (int more = 0; more < tabSize; more++) {
          spaces += ' ';
        }

        for (int loopIndex = 0; loopIndex < length; loopIndex++) {
          processNode(childNodes.item(loopIndex), spaces);
        }

        if ((length == 1) &&
              (childNodes.item(0).getNodeType() == Node.TEXT_NODE)) {
          hadOnlyText = true;
        }
      }

      break;
    }

    case Node.CDATA_SECTION_NODE: {
      buffer.append("\n");
      buffer.append(spaces);
      buffer.append("<![CDATA[");
      buffer.append(node.getNodeValue());
      buffer.append("]]>");

      break;
    }

    case Node.TEXT_NODE: {
      //buffer.append(indent);
      String newText = node.getNodeValue().trim();
      if (newText.length() > 0) {
        StringBuffer temp = new StringBuffer(newText);
        int start = temp.indexOf("\n");

        while (start > -1) {
          temp.replace(start, start + 1, " ");
          start = temp.indexOf("\n", start);
        }

        // replace all multiple spaces now (since HTML ignores)
        start = temp.indexOf("  ");

        while (start > -1) {
          temp.replace(start, start + 2, " ");
          start = temp.indexOf("  ");
        }

        int nextEOL = 75;

        //						temp.insert(0, "\n" + indent);
        while ((nextEOL < temp.length()) && (nextEOL >= 0)) {
          int index = temp.indexOf(" ", nextEOL);

          if (index >= 0) {
            temp.replace(index, index + 1, "\n" + spaces);
            nextEOL += 75;

            if (spaces.length() < 12) {
              nextEOL -= spaces.length();
            }
          } else {
            nextEOL = -1;
          }
        }

        buffer.append(temp);

        //buffer.append('\n');
      }

      break;
    }

    case Node.PROCESSING_INSTRUCTION_NODE: {
      buffer.append(spaces);
      buffer.append("<?");
      buffer.append(node.getNodeName());

      String text = node.getNodeValue();

      if ((text != null) && (text.length() > 0)) {
        buffer.append(text);
      }

      buffer.append("?>");
      buffer.append('\n');

      break;
    }

    case Node.COMMENT_NODE: {
      buffer.append('\n');

      //buffer.append(indent);
      buffer.append("<!--");
      buffer.append(node.getNodeValue());
      buffer.append("-->");

      break;
    }

    default:
      break;
    }

    if ((type == Node.ELEMENT_NODE) && !selfClosing) {
      if (!(hadOnlyText && isInlineElement)) {
        buffer.append('\n');
        buffer.append(spaces.substring(0, spaces.length() - tabSize));
      }

      buffer.append("</");
      buffer.append(node.getNodeName());
      buffer.append(">");

      //            buffer.append('\n');
    }
  }

  class FormatHandler extends DefaultHandler {
    String indentString = "  ";

    /** The current indent level of the document
 * Initially set to -1 so xml header and html tag stay on same level
 * (assumes xml tag)
 */
    int indentLevel = -1;
    String lastTag = "";

    public void error(org.xml.sax.SAXParseException sAXParseException)
      throws org.xml.sax.SAXException {
      int line = sAXParseException.getLineNumber();
      String error = sAXParseException.getMessage();

      if (error.indexOf("(#PCDATA") >= 0) {
        String endStuff = error.substring(error.indexOf("(#PCDATA") + 8);
        error = error.substring(0, error.indexOf("(#PCDATA") - 1);
        error += (" must be one of " + endStuff);
      }

      errorBuffer.append("Error: Line: " + line + ": " + error + "\n");
      resultCode = LanguageKit.REFORMAT_ERRORS;
    }

    public void fatalError(org.xml.sax.SAXParseException sAXParseException)
      throws org.xml.sax.SAXException {
      errorBuffer.append("Fatal Error: " + sAXParseException.getMessage() +
        "\n");
      resultCode = LanguageKit.REFORMAT_ERRORS;
    }

    public void warning(org.xml.sax.SAXParseException sAXParseException)
      throws org.xml.sax.SAXException {
      int line = sAXParseException.getLineNumber();
      String warning = sAXParseException.getMessage();

      if (warning.indexOf("Using original") < 0) {
        errorBuffer.append("Warning: Line: " + line + ": " + warning + "\n");
      }

      // Don't change result of already found errors
      if (resultCode != LanguageKit.REFORMAT_ERRORS) {
        resultCode = LanguageKit.REFORMAT_WARNINGS;
      }
    }
  }
}
