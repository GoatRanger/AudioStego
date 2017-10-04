/*
 * Copyright (C) 2003 USMA This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License, or any later version. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
package eecs.editor.util;

import org.w3c.css.css.StyleSheet;
import org.w3c.css.css.StyleSheetParser;
import org.w3c.css.parser.CssError;
import org.w3c.css.parser.CssParseException;
import org.w3c.css.parser.Errors;
import org.w3c.css.properties3.*;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.DocumentHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.SACMediaList;
import org.w3c.css.sac.SelectorList;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;
import org.w3c.flute.parser.*;
import org.w3c.flute.parser.selectors.SelectorFactoryImpl;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.text.html.CSS;
import javax.swing.text.html.CSS.Attribute;


/**
 * @author Karl A. Gossett To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code
 *         Generation&gt;Code and Comments
 */
public class CSSChecker implements DocumentHandler {
  StringBuffer buffer;
  Attribute[] attributes = CSS.getAllAttributeKeys();
  String attributeList;
  Properties properties;
  boolean isValid = true;

  /**
   * Creates a new instance of HTMLChecker
   */
  public CSSChecker() {
    //Util.onDebug = true;
    attributeList = "";
    for (int i = 0; i < attributes.length; i++) {
      attributeList += (attributes[i].toString() + "|");
    }
    attributeList += "$";
    properties = new Properties();
    try {
      URL url = Css3Properties.class.getResource("Css3Properties.properties");
      properties.load(url.openStream());
    }
    catch (Exception e) {
      System.err.println("org.w3c.css.properties3.Css3Properties: couldn't load properties ");
      System.err.println("  " + e.toString());
    }
  }

  public boolean getValid() {
    return isValid;
  }

  /**
   * Validate and parse the specified file (if the DOCTYPE isn't specified, document cannot be validated). Returns a list of errors.
   * 
   * @param file
   *          The file to be checked.
   * @return The list of errors, as a <CODE>String</CODE>.
   */
  public String check(final java.io.File file) {
    isValid = true;
    String errorHeader = "<html><body><p>Couldn't check for errors.</p>";
    if (file == null) {
      return errorHeader;
    }
    buffer = new StringBuffer();
    buffer.append("<html><head><title>XHTML Error Check</title><body>");
    int headerLength = buffer.length();
    buffer.append("<h2>--- Checking for Errors ---</h2>");
    Parser parser = new Parser();
    parser.setDocumentHandler(this);
    parser.setSelectorFactory(new SelectorFactoryImpl());
    parser.setErrorHandler(new CSSErrorHandler());
    try {
      parser.parseStyleSheet(file.getCanonicalPath());
    }
    catch (CSSException e) {
      e.printStackTrace();
      // Parse the file and build a Document tree to represent its content
    }
    catch (java.io.IOException e) {
      //
    }
    ApplContext ac = new ApplContext("en");
    ac.setCssVersion("css2");
    ac.setMedium("all");
    StyleSheetParser fullParser;
    try {
      URL url = new URL("file", null, file.getPath());
      fullParser = new StyleSheetParser();
      fullParser.parseURL(ac, url, "Stylesheet", "stylesheet", "all", 0);
      StyleSheet style = fullParser.getStyleSheet();
      Errors errors = style.getErrors();
      Warnings warnings = style.getWarnings();
      CssError[] errorList = errors.getErrors();
      Warning[] warningList = warnings.getWarnings();
      if ((errorList != null) && (warningList != null) && ((errorList.length > 0) || (warningList.length > 0))) {
        buffer.append("<br><em>Sorry, I was distracted and forgot what line I was on."
            + "You'll have to do a Find to locate where the following mistakes are.</em><br><br>");
      }
      for (int count = 0; count < errorList.length; count++) {
        Exception e = errorList[count].getException();
        isValid = false;
        if (e instanceof CssParseException) {
          CssParseException cssError = (CssParseException) e;
          String property = cssError.getProperty();
          String msg = cssError.getMessage();
          if (msg != null && msg.equalsIgnoreCase("unknown dimension"))
            msg = "Invalid Value";
          String errorString = cssError.getSkippedString();
          String contextList = "";
          if (cssError.getContexts() != null) {
            Iterator contexts = cssError.getContexts().iterator();
            while (contexts.hasNext()) {
              contextList += contexts.next().toString();
              if (contexts.hasNext())
                contextList += " ";
            }
          }
          buffer.append("<em><font color=\"red\">- Error in selector </font></em>" + contextList
              + "<em><font color=\"red\"> for property: </font></em>" + property
              + ".<br><em><font color=\"red\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
          if (msg != null)
            buffer.append(msg);
          if (errorString != null) {
            buffer.append(" : </font></em>" + errorString + "<br>");
          }
          else {
            buffer.append(".</font></em><br>");
          }
        }
        else {
          buffer.append("<em><font color=\"red\">Error: " + errorList[count].getException().getMessage() + "</font></em><br>");
        }
      }
      for (int count = 0; count < warningList.length; count++) {
        buffer.append("<em><font color=\"#A0522D\">Warning: " + warningList[count].getWarningMessage() + "</font></em><br>");
      }
    }
    catch (MalformedURLException e) {
      System.out.println("Couldn't open file.");
      return errorHeader;
    }
    if (isValid) {
      buffer.append("<em>No errors found!</em><br>");
      buffer.append("<p><font color=\"red\">Note: Web page presentation relies on both the XHTML and CSS, so "
          + "make sure your XHTML is valid (and you have the appropriate <code>link</code> tag).</font></em></p>");
    }
    else {
      buffer.insert(headerLength, "<h2><font color=\"red\">Errors Detected</font></h2>");
    }
    buffer.append("<h3>-- End Error Checking ---</h3></body></html>");
    return buffer.toString();
  }

  /**
   * Act on a Namespace declaration.
   * 
   * @see org.w3c.css.sac.DocumentHandler#namespaceDeclaration(java.lang.String, java.lang.String)
   */
  public void namespaceDeclaration(String prefix, String uri) throws CSSException {
    // System.out.println("Namespace: " + uri);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#endSelector(org.w3c.css.sac.SelectorList)
   */
  public void endSelector(SelectorList selectors) throws CSSException {
    //     System.out.println("}");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#startDocument(org.w3c.css.sac.InputSource)
   */
  public void startDocument(InputSource source) throws CSSException {
    // System.out.println("Start document");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#endDocument(org.w3c.css.sac.InputSource)
   */
  public void endDocument(InputSource source) throws CSSException {
    //     System.out.println("End document");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#comment(java.lang.String)
   */
  public void comment(String text) throws CSSException {
    //    System.out.println("Comment: " + text);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#ignorableAtRule(java.lang.String)
   */
  public void ignorableAtRule(String atRule) throws CSSException {
    // System.out.println("Ignoring " + atRule);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#importStyle(java.lang.String, org.w3c.css.sac.SACMediaList, java.lang.String)
   */
  public void importStyle(String uri, SACMediaList media, String defaultNamespaceURI) throws CSSException {
    // System.out.println("Import style " + uri);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#startMedia(org.w3c.css.sac.SACMediaList)
   */
  public void startMedia(SACMediaList media) throws CSSException {
    // System.out.println("Start media " + media.toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#endMedia(org.w3c.css.sac.SACMediaList)
   */
  public void endMedia(SACMediaList media) throws CSSException {
    // System.out.println("End media " + media.toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#startPage(java.lang.String, java.lang.String)
   */
  public void startPage(String name, String pseudo_page) throws CSSException {
    // System.out.println("Starting " + name + " of " + pseudo_page);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#endPage(java.lang.String, java.lang.String)
   */
  public void endPage(String name, String pseudo_page) throws CSSException {
    // System.out.println("Ending " + name + " of " + pseudo_page);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#startFontFace()
   */
  public void startFontFace() throws CSSException {
    // System.out.println("Starting FontFace");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#endFontFace()
   */
  public void endFontFace() throws CSSException {
    // System.out.println("Ending FontFace");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#startSelector(org.w3c.css.sac.SelectorList)
   */
  public void startSelector(SelectorList selectors) throws CSSException {
    // System.out.println();
    //    for (int i = 0; i < selectors.getLength(); i++) {
    //      if (selectors.item(i) instanceof ElementSelectorImpl) {
    //        ElementSelectorImpl elem = (ElementSelectorImpl) selectors.item(i);
    //        System.out.println(elem.getLocalName());
    //      }
    //    }
    //
    //     System.out.println(" {");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.w3c.css.sac.DocumentHandler#property(java.lang.String, org.w3c.css.sac.LexicalUnit, boolean)
   */
  public void property(String name, LexicalUnit value, boolean important) throws CSSException {
    //    		String val = value.toString();
    //    		if (value.getLexicalUnitType() == LexicalUnit.SAC_STRING_VALUE) {
    //    			val = "'" + value.toString() + "'";
    //    		} else if (value.getLexicalUnitType() == LexicalUnit.SAC_ATTR) {
    //    			val = value.getStringValue();
    //    		} else {
    //    			if (value.getLexicalUnitType() == 35) val = value.getStringValue();
    //    		}
    //    		if (properties!=null && properties.getProperty(name,"notfound").equals("notfound")) {
    //    			//buffer.append("<b><font color=\"red\">Error: You used an attribute \"" + name + "\" that I've never heard of--check your
    // spelling.</font><br>");
    //    			System.out.println("Property error: " +name);
    //    		} else {
    //    			System.out.println(" " + name + ": " + val + (important?"!":"") + ";");
    //    		}
    //     System.out.println(" " + name + ": " + val + (important?"!":"") + ";");
  }

  class CSSErrorHandler implements org.w3c.css.sac.ErrorHandler {
    public void warning(CSSParseException cpe) {
      int line = cpe.getLineNumber();
      String warning = cpe.getMessage();
      buffer.append("<b><font color=\"#A0522D\">Warning: Line: " + line + ": " + getHTMLString(warning) + "</font></b><br>");
    }

    public void error(CSSParseException cpe) {
      int line = cpe.getLineNumber();
      String warning = cpe.getMessage();
      buffer.append("<b><font color=\"red\">Error: Line: " + line + ": " + getHTMLString(warning) + "</font></b><br>");
      isValid = false;
    }

    public void fatalError(CSSParseException cpe) {
      int line = cpe.getLineNumber();
      String warning = cpe.getMessage();
      buffer.append("<b><font color=\"red\">Error: Line: " + line + ": " + getHTMLString(warning) + "<b><font color=\"red\">");
      isValid = false;
    }

    private String getHTMLString(final String input) {
      String result;
      result = input.replaceAll("<", "[");
      result = result.replaceAll(">", "]");
      return result;
    }
  }
}