/*
 * Created on Aug 26, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.utility;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.CSS;
import javax.swing.text.html.CSS.Attribute;

import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.SACMediaList;
import org.w3c.css.sac.SelectorList;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;
import org.w3c.css.parser.CssError;
import org.w3c.css.parser.Errors;
import org.w3c.css.properties3.*;
import org.w3c.css.css.StyleSheet;
import org.w3c.css.css.StyleSheetParser;
import org.w3c.flute.parser.*;
import org.w3c.flute.parser.selectors.ElementSelectorImpl;
import org.w3c.flute.parser.selectors.SelectorFactoryImpl;
/**
 * @author Karl
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CSSChecker implements org.w3c.css.sac.DocumentHandler {
	private StringBuffer buffer;
	
	Attribute[] attributes = CSS.getAllAttributeKeys();
	String attributeList;
    Properties properties;
	
	/**
	 * Creates a new instance of HTMLChecker
	 */
	public CSSChecker() {
		//Util.onDebug = true;
		attributeList = "";
		for (int i=0;i<attributes.length;i++) {
			attributeList += attributes[i].toString() + "|";
		}
		attributeList += "$";
		
		properties = new Properties();
		try {
		  URL url = Css3Properties.class.getResource("Css3Properties.properties");
		  properties.load(url.openStream());
		} catch (Exception e) {
		  System.err.println("org.w3c.css.properties3.Css3Properties: couldn't load properties ");
		  System.err.println("  " + e.toString() );
		}
	}

	/**
	 * Validate and parse the specified file (if the DOCTYPE isn't specified,
	 * document cannot be validated).  Returns a list of errors.
	 *
	 * @param file The file to be checked.
	 *
	 * @return The list of errors, as a <CODE>String</CODE>.
	 */
	public String check(final java.io.File file) {
		String errorHeader = "<p>No File. Couldn't check for errors.</p>";
		if (file==null) return errorHeader;
		buffer = new StringBuffer();
		buffer.append("<h2>CSS Errors</h2>");
		Parser parser = new Parser();

		parser.setDocumentHandler(this);
		parser.setSelectorFactory(new SelectorFactoryImpl());
		parser.setErrorHandler(new CSSErrorHandler());
        buffer.append("<h3>Parsing Errors</h3><ol>");
		try {
			parser.parseStyleSheet(file.getCanonicalPath()); 
		} catch (CSSException e) {
			System.out.println("Big time error!");
			// Parse the file and build a Document tree to represent its content
		} catch (java.io.IOException e) {
	
		}
		buffer.append("</ol>");
		ApplContext ac = new ApplContext("en");
		ac.setCssVersion("css2");
		ac.setMedium("all");
		StyleSheetParser fullParser;
		try {
		    URL url = new URL("file",null,file.getPath());
        	fullParser = new StyleSheetParser();
        	fullParser.parseURL(ac,url,"Stylesheet","stylesheet","all",0);
        	StyleSheet style = fullParser.getStyleSheet();
        	Errors errors = style.getErrors();
        	Warnings warnings = style.getWarnings();
        	CssError[] errorList = errors.getErrors();
        	Warning[] warningList = warnings.getWarnings();
//        	if (errorList != null && warningList != null && (errorList.length > 0 || warningList.length > 0)) {
//        		buffer.append("<br><em>Sorry, I was distracted and forgot what line I was on." +
//        		   "You'll have to do a Find to locate where the following mistakes are.</em><br><br>");
//        	}
			buffer.append("<h3>Other CSS Errors</h3>\r\n<ol>\r\n");
        	for (int count=0;count<errorList.length; count++) {
        		String error = errorList[count].getException().getMessage();
        		// This should be a-f, but the other parser catches G-Z, so 
        		// don't duplicate errors.
        		Pattern pat = Pattern.compile("^(#[a-zA-Z0-9]+).*color");
        		Matcher match = pat.matcher(error);
        		if (!(error == null) && !match.find()) {
        
            		buffer.append("<li><em>CSS Error: "+
        	    	     errorList[count].getException().getMessage()+"</em></li>");
        		}
        	}
			for (int count=0;count<warningList.length; count++) {
				buffer.append("<li><em>CSS Warning: " + 
				    warningList[count].getWarningMessage()+"</em></li>");
			}
		} catch (MalformedURLException e) {
			System.out.println("Couldn't open file.");
			return errorHeader;
		}
        buffer.append("\r\n</ol>\r\n");

		// buffer is modified in the errorHandler (HTMLErrorHandler)
		return buffer.toString();
	}

	/** Act on a Namespace declaration.
	 * @see org.w3c.css.sac.DocumentHandler#namespaceDeclaration(java.lang.String, java.lang.String)
	 */
	public void namespaceDeclaration(String prefix, String uri) throws CSSException {
		// System.out.println("Namespace: " + uri);
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#endSelector(org.w3c.css.sac.SelectorList)
	 */
	public void endSelector(SelectorList selectors) throws CSSException {
		// System.out.println("}");
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#startDocument(org.w3c.css.sac.InputSource)
	 */
	public void startDocument(InputSource source) throws CSSException {
		// System.out.println("Start document");
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#endDocument(org.w3c.css.sac.InputSource)
	 */
	public void endDocument(InputSource source) throws CSSException {
		// System.out.println("End document");
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#comment(java.lang.String)
	 */
	public void comment(String text) throws CSSException {
		 System.out.println("Comment: " + text);
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#ignorableAtRule(java.lang.String)
	 */
	public void ignorableAtRule(String atRule) throws CSSException {
		// System.out.println("Ignoring " + atRule);		
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#importStyle(java.lang.String, org.w3c.css.sac.SACMediaList, java.lang.String)
	 */
	public void importStyle(String uri, SACMediaList media, String defaultNamespaceURI) throws CSSException {
		// System.out.println("Import style " + uri);		
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#startMedia(org.w3c.css.sac.SACMediaList)
	 */
	public void startMedia(SACMediaList media) throws CSSException {
		// System.out.println("Start media " + media.toString());
		
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#endMedia(org.w3c.css.sac.SACMediaList)
	 */
	public void endMedia(SACMediaList media) throws CSSException {
		// System.out.println("End media " + media.toString());
		
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#startPage(java.lang.String, java.lang.String)
	 */
	public void startPage(String name, String pseudo_page) throws CSSException {
		// System.out.println("Starting " + name + " of " + pseudo_page);
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#endPage(java.lang.String, java.lang.String)
	 */
	public void endPage(String name, String pseudo_page) throws CSSException {
		// System.out.println("Ending " + name + " of " + pseudo_page);
		
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#startFontFace()
	 */
	public void startFontFace() throws CSSException {
		// System.out.println("Starting FontFace");
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#endFontFace()
	 */
	public void endFontFace() throws CSSException {
		// System.out.println("Ending FontFace");
		
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#startSelector(org.w3c.css.sac.SelectorList)
	 */
	public void startSelector(SelectorList selectors) throws CSSException {
		// System.out.println();
		for (int i=0; i<selectors.getLength(); i++) {
			if (selectors.item(i) instanceof ElementSelectorImpl) {
				ElementSelectorImpl elem = (ElementSelectorImpl)selectors.item(i);
			}
		}
		// System.out.println(" {");
		
	}

	/* (non-Javadoc)
	 * @see org.w3c.css.sac.DocumentHandler#property(java.lang.String, org.w3c.css.sac.LexicalUnit, boolean)
	 */
	public void property(String name, LexicalUnit value, boolean important) throws CSSException {
		
//		String val = value.toString();
//		if (value.getLexicalUnitType() == LexicalUnit.SAC_STRING_VALUE) {
//			val = "'" + value.toString() + "'";
//		} else if (value.getLexicalUnitType() == LexicalUnit.SAC_ATTR) {
//			val = value.getStringValue();
//		} else {
//			if (value.getLexicalUnitType() == 35) val = value.getStringValue();
//		}
//		if (/*properties!=null && properties.getProperty(name,"notfound")*/UserProperties.properties.getProperty(name, "notfound").equals("notfound")) {
//			buffer.append("<b><font color=\"red\">Error: You used an attribute \"" + name + "\" that I've never heard of--check your spelling.</font><br>");
//		} else {
//			System.out.println("   " + name + ": " + val + (important?"!":"") + ";");
//		}
		// System.out.println("   " + name + ": " + val + (important?"!":"") + ";");
	}
	
	class CSSErrorHandler implements org.w3c.css.sac.ErrorHandler {

		public void warning(CSSParseException cpe) {
			int line = cpe.getLineNumber();
			String warning = cpe.getMessage();
			buffer.append("<li>CSS Warning: Line: " + line + ": " + getHTMLString(warning) + "</li>");
		}
		
		public void error(CSSParseException cpe) {
			int line = cpe.getLineNumber();
			String warning = cpe.getMessage();
			buffer.append("<li>CSS Error: Line: " + line + ": " + getHTMLString(warning) + "</li>");
		}
		
		public void fatalError(CSSParseException cpe) {
			int line = cpe.getLineNumber();
			String warning = cpe.getMessage();
			buffer.append("<li>CSS Error: Line: " + line + ": " + getHTMLString(warning) + "</li>");
		}
		
		private String getHTMLString(final String input) {
			String result;
			result = input.replaceAll("<","[");
			result = result.replaceAll(">","]");
			return result;
		}
	}
	
}
