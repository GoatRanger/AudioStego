//
// $Id: CssFouffa.java,v 1.11 2003/07/02 14:39:50 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
This class is the front end of the CSS parser
 */

package org.w3c.css.parser;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
// import org.w3c.www.protocol.http.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Properties;

import org.w3c.css.properties.CssProperty;
import org.w3c.css.parser.analyzer.CssParser;
import org.w3c.css.parser.analyzer.ParseException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.css.StyleSheetOrigin;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.ApplContext;
import org.w3c.css.parser.AtRule;
import org.w3c.css.css.StyleSheetCom;

/**
 * This class is a front end of the CSS1 parser.
 *
 * <p> Example:<br>
 * <code>
 * CssFouffa parser =
 *   new CssFouffa(new URL("http://www.w3.org/drafts.css"));<BR>
 * CssValidatorListener myListener = new MyParserListener();<BR>
 * <BR>
 * parser.addListener(myListener);<BR>
 * parser.parseStyle();<BR>
 * </code>
 *
 * @version $Revision: 1.11 $
 */
public final class CssFouffa extends CssParser {

    // all properties
    static CssPropertyFactory properties;
    // all listeners
    Vector listeners;
    // all errors
    Errors errors;
    // origin of the style sheet
    int origin;

    /**
     * Create a new CssFouffa with a data input and a begin line number.
     *
     * @param input     data input
     * @param file      The source file (use for errors, warnings and import)
     * @param beginLine The begin line number in the file.
     *                  (used for HTML for example)
     * @exception       IOException  if an I/O error occurs.
     */
    public CssFouffa(ApplContext ac, InputStream input,
		     URL file, int beginLine)
	    throws IOException {
	super(input);
	if (ac.getOrigin() == -1) {
	    setOrigin(StyleSheetOrigin.AUTHOR); // default is user
	} else {
	    setOrigin(ac.getOrigin()); // default is user
	}
	ac.setFrame(new Frame(this, file.toString(), beginLine));
	setApplContext(ac);
	// @@this is a default media ...
	/*
	  AtRuleMedia media = new AtRuleMedia();

	  if (ac.getMedium() == null) {
	  try {
	  media.addMedia("all", ac);
	} catch (InvalidParamException e) {} //ignore
	} else {
	    try {
		media.addMedia(ac.getMedium(), ac);
	    } catch (Exception e) {
		System.err.println(e.getMessage());
		try {
		    media.addMedia("all", ac);
		} catch (InvalidParamException ex) {} //ignore
	    }
	}
	setAtRule(media);
	*/
	setURL(file);
	if (Util.onDebug) {
	    System.err.println( "[DEBUG] CSS version " + ac.getCssVersion()
				+ " medium " + ac.getMedium()
				+ " at-rule " + getAtRule()
				+ " profile " + ac.getProfile() );
	}
	loadConfig(ac.getCssVersion(), ac.getProfile());
	listeners = new Vector();
    }

    /**
     * Create a new CssFouffa with a data input.
     *
     * @param input data input
     * @param file  The source file (use for errors, warnings and import)
     * @exception   IOException  if an I/O error occurs.
     */
    public CssFouffa(ApplContext ac, InputStream input, URL file)
	throws IOException {
	this(ac, input, file, 0);
    }

    /**
     * Create a new CssFouffa.
     *
     * @param file  The source file
     *              (use for data input, errors, warnings and import)
     * @exception   IOException  if an I/O error occurs.
     */
    public CssFouffa(ApplContext ac, URL file) throws IOException {
	this(ac, HTTPURL.getConnection(file, ac).getInputStream(), file, 0);
    }

    /**
     * Create a new CssFouffa. Used by handleImport.
     *
     * @param file      The source file
     *                  (use for data input, errors, warnings and import)
     * @param listeners Works with this listeners
     * @exception       IOException  if an I/O error occurs.
     */
    private CssFouffa(ApplContext ac, InputStream in, URL url, Vector listeners)
	throws IOException {
	this(ac, in, url, 0);
	setURL(url);
	ac.setFrame(new Frame(this, url.toString()));
	setApplContext(ac);
	this.listeners = listeners;
    }

    private void ReInit(ApplContext ac, InputStream input,
			URL file, Frame frame) {
	// reinitialize the parser with a new data input
	// and a new frame for errors and warnings
	super.ReInit(input, ac);
	// @@this is a default media ...
	AtRuleMedia media;
	if ("css1".equals(ac.getCssVersion())) {
	    media = new AtRuleMediaCSS1();
	} else if ("css2".equals(ac.getCssVersion())) {
	    media = new AtRuleMediaCSS2();
	} else {
	    media = new AtRuleMediaCSS2();
	}
	/*
	if (ac.getMedium() == null) {
	    try {
		media.addMedia("all", ac);
	    } catch (InvalidParamException e) {} //ignore
	} else {
	    try {
		media.addMedia(ac.getMedium(), ac);
	    } catch (Exception e) {
		System.err.println(e.getMessage());
		try {
		    media.addMedia("all", ac);
		} catch (InvalidParamException ex) {} //ignore
	    }
	}
	setAtRule(media);
	*/
	setURL(file);
	if (Util.onDebug) {
	    System.err.println( "[DEBUG] CSS version " + ac.getCssVersion()
				+ " medium " + ac.getMedium()
				+ " profile " + ac.getProfile() );
	}

	loadConfig(ac.getCssVersion(), ac.getProfile());
    }

    /**
     * Reinitializes a new CssFouffa with a data input and a begin line number.
     *
     * @param input     data input
     * @param file      The source file (use for errors, warnings and import)
     * @param beginLine The begin line number in the file.
     *                  (used for HTML for example)
     * @exception       IOException  if an I/O error occurs.
     */
    public void ReInit(ApplContext ac, InputStream input,
		       URL file, int beginLine)
	    throws IOException {
	Frame f = new Frame(this, file.toString(), beginLine);
	ac.setFrame(f);
	ReInit(ac, input, file, f);
    }

    /**
     * Reinitializes a new CssFouffa with a data input.
     *
     * @param input     data input
     * @param file      The source file (use for errors, warnings and import)
     * @exception       IOException  if an I/O error occurs.
     */
    public void ReInit(ApplContext ac, InputStream input, URL file)
	    throws IOException {
	Frame f = new Frame(this, file.toString());
	ac.setFrame(f);
	ReInit(ac, input, file, f);
    }

    /**
     * Reinitializes a new CssFouffa.
     *
     * @param file  The source file
     *              (use for data input, errors, warnings and import)
     * @exception   IOException  if an I/O error occurs.
     */
    public void ReInit(ApplContext ac, URL file) throws IOException {
	Frame f = new Frame(this, file.toString());
	ac.setFrame(f);
	ReInit(ac, HTTPURL.getConnection(file, ac).getInputStream(), file, f);
    }

    /**
     * Set the attribute origin
     *
     * @param origin the new value for the attribute
     */
    private final void setOrigin(int origin) {
        this.origin = origin;
    }

    /**
     * Returns the attribute origin
     *
     * @return the value of the attribute
     */
    public final int getOrigin() {
        return origin;
    }

    /**
     * Adds a listener to the parser.
     *
     * @param listener The listener
     * @see            org.w3c.css.parser.CssValidatorListener
     */
    public final void addListener(CssValidatorListener listener) {
	listeners.addElement(listener);
    }

    /**
     * Removes a listener from the parser
     *
     * @param listener The listener
     * @see            org.w3c.css.parser.CssValidatorListener
     */
    public final void removeListener(CssValidatorListener listener) {
	listeners.removeElement(listener);
    }

    /**
     * Parse the style sheet. This is the main function of this parser.
     *
     * <p> Example:<br>
     * <code>
     * CssFouffa parser = new CssFouffa(new URL("http://www.w3.org/drafts.css"));<BR>
     * CssValidatorListener myListener = new MyParserListener();<BR>
     * <BR>
     * parser.addListener(myListener);<BR>
     * parser.parseStyle();<BR>
     * </code>
     * @see            org.w3c.css.parser.CssFouffa#addListener
     */
    public void parseStyle() {
	try {
	    parserUnit();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	// That's all folks, notify all errors and warnings
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    CssValidatorListener listener = (CssValidatorListener) e.nextElement();
	    listener.notifyErrors(ac.getFrame().getErrors());
	    listener.notifyWarnings(ac.getFrame().getWarnings());
	}
    }

    /**
     * Call by the import statement.
     *
     * @param url  The style sheet where this import statement appears.
     * @param file the file name in the import statement
     */
    public void handleImport(URL url, String file, AtRuleMedia media) {
	CssError cssError = null;

	try {
	    if (Util.importSecurity) {
		throw new FileNotFoundException("[SECURITY] You can't import URL sorry.");
	    }

	    URL importedURL = HTTPURL.getURL(url, file);
	    URLConnection importURL = HTTPURL.getConnection(importedURL, ac);

	    if (importURL instanceof HttpURLConnection) {
		HttpURLConnection httpURL = (HttpURLConnection) importURL;
		if (httpURL.getContentType().toLowerCase().indexOf("text/html") != -1) {
		    throw new FileNotFoundException(importURL.getURL() +
						    ": You can't import an HTML document");
		}
	    }
	    Frame f = ac.getFrame();
	    try {
		CssFouffa cssFouffa
		    = new CssFouffa(ac, importURL.getInputStream(), importedURL,
				    listeners);

		cssFouffa.setOrigin(getOrigin());
		if (!media.isEmpty()) {
		    cssFouffa.setAtRule(media);
		} else {
		    cssFouffa.setAtRule(getAtRule());
		}
		cssFouffa.parseStyle();
	    } finally {
		ac.setFrame(f);
	    }
	} catch (Exception e) {
	    if (!Util.noErrorTrace) {
		ac.getFrame().addError(new CssError(e));
	    }
	}
    }

    /**
     * Call by the at-rule statement.
     *
     * @param ident The ident for this at-rule (for example: 'font-face')
     * @param string The string representation of this at-rule
     */
    public void handleAtRule(String ident, String string) {
	if (mode) {
	    for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
		CssValidatorListener listener =
		    (CssValidatorListener) e.nextElement();
		listener.handleAtRule(ac, ident, string);
	    }
	} else {
	    if (!Util.noErrorTrace) {
		// only @import <string>; or @import <url>; are valids in CSS1
		ParseException error =
		    new ParseException("at-rules are not implemented in CSS1");
		ac.getFrame().addError(new CssError(error));
	    }
	}
    }

    /**
     * Assign an expression to a property.  This function create a new property
     * with <code>property</code> and assign to it the expression with the
     * importance.
     *
     * @param  property   the name of the property
     * @param  expression The expression representation of expression
     * @param  important  true if expression id important
     * @param  InvalidParamException An error appears during the property creation.
     * @return            a CssProperty
     */
    public CssProperty handleDeclaration(String property,
					 CssExpression expression,
					 boolean important)
	    throws InvalidParamException {

	CssProperty prop;

	if (Util.onDebug) {
	    System.err.println( "Creating " + property
				+ ": " + expression );
	}

	try {
		if (getMediaDeclaration().equals("on") && getAtRule() instanceof AtRuleMedia) {
			prop = properties.createMediaFeature(ac, getAtRule(), property, expression);
		} else {
			prop = properties.createProperty(ac, getAtRule(),
					     property, expression);
		}

	} catch (InvalidParamException e) {
	    throw e;
	} catch (Exception e) {
	    if (Util.onDebug) {
			e.printStackTrace();
	    }
	    throw new InvalidParamException(e.toString(), ac);
	}

	// set the importance
	if (important) {
		prop.setImportant();
	}
	prop.setOrigin(origin);
	// set informations for errors and warnings
	prop.setInfo(ac.getFrame().getLine(), ac.getFrame().getSourceFile());

	// ok, return the new property

	return prop;

    }

    /**
     * Parse only a list of declarations. This is useful to parse the
     * <code>STYLE</code> attribute in a HTML document.
     *
     * <p> Example:<br>
     * <code>
     * CssFouffa parser =
     *    new CssFouffa(new URL("http://www.w3.org/drafts.css"));<BR>
     * CssValidatorListener myListener = new MyParserListener();<BR>
     * CssSelector selector = new CssSelector();
     * selector.setElement("H1");
     * <BR>
     * parser.addListener(myListener);<BR>
     * parser.parseDeclarations(selector);<BR>
     * </code>
     *
     * @param context The current context
     * @see           org.w3c.css.parser.CssFouffa#addListener
     * @see           org.w3c.css.parser.CssSelectors#setElement
     */
    public void parseDeclarations(CssSelectors context) {
	// here we have an example for reusing the parser.
	try {
	    Vector properties = declarations();

	    if (properties != null && properties.size() != 0) {
		handleRule(context, properties);
	    }
	} catch (ParseException e) {
	    if (!Util.noErrorTrace) {
		CssParseException ex = new CssParseException(e);
		ex.skippedString = "";
		ex.property = currentProperty;
		ex.contexts = currentContext;
		CssError error = new CssError(getSourceFile(), getLine(), ex);
		ac.getFrame().addError(error);
	    }
	}

	if (!Util.noErrorTrace) {
	    for (Enumeration e = listeners.elements();
		 e.hasMoreElements();) {
		CssValidatorListener listener =
		    (CssValidatorListener) e.nextElement();
		listener.notifyErrors(ac.getFrame().getErrors());
		listener.notifyWarnings(ac.getFrame().getWarnings());
	    }
	}
    }

    /**
     * used for the output of the stylesheet
     * @param atRule the @rule that just has been found by the parser in the
     stylesheet, it is added to the storage for the output
    */
    public void newAtRule(AtRule atRule) {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).newAtRule(atRule);
	}
    }

    /**
     * used for the output of the stylesheet
     * @param charset the @charset rule that has been found by the parser
     */
    public void addCharSet(String charset) {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).addCharSet(charset);
	}
    }

    /**
     * used for the output of the stylesheet
     * the @rule that had been found before is closed here after the content
     that's in it.
    */
    public void endOfAtRule() {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).endOfAtRule();
	}
    }

    /**
     * used for the output of the stylesheet
     * @param important true if the rule was declared important in the stylesheet
     */
    public void setImportant(boolean important) {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).setImportant(important);
	}
    }

    /**
     * used for the output of the stylesheet
     * @param selectors a list of one or more selectors to be added to the output stylesheet
     */
    public void setSelectorList(Vector selectors) {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).setSelectorList(selectors);
	}
    }

    /**
     * used for the output of the stylesheet
     * @param properties A list of properties that are following eachother in the stylesheet (for example: all properties in an @rule)
     */
    public void addProperty(Vector properties) {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).setProperty(properties);
	}
    }

    /**
     * used for the output of the stylesheet
     * used to close a rule when it has been read by the parser
     */
    public void endOfRule() {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).endOfRule();
	}
    }

    /**
     * used for the output of the stylesheet
     * if an error is found this function is used to remove the whole stylerule
     from the memorystructure so that it won't appear on the screen
    */
    public void removeThisRule() {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).removeThisRule();
	}
    }

    /**
     * used for the output of the stylesheet
     * if an error is found this function is used to remove the whole @rule
     from the memorystructure so that it won't appear on the screen
    */
    public void removeThisAtRule() {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).removeThisAtRule();
	}
    }

    /**
     * Adds a vector of properties to a selector.
     *
     * @param selector     the selector
     * @param declarations Properties to associate with contexts
     */
    public void handleRule(CssSelectors selector, Vector declarations) {
	for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
	    ((CssValidatorListener) e.nextElement()).handleRule(ac, selector,
								declarations);
	}
    }

    /**
     * Return the class name for a property
     *
     * @param  property the property name ('font-size' for example)
     * @return          the class name ('org.w3c.css.properties.CssFontSize' for example)
     */
    public String getProperty(String property) {
	return properties.getProperty(property);
    }

    /**
     * Set the style
     */
    public void setStyle(Class style) {
	CssSelectors.setStyle(style);
    }

    /**
     * Load the parser properties configuration.
     *
     * <p>By default, the parser is configure for cascading style sheet 1.
     *
     * <OL>You have three parser properties :
     * <LI> style:      the class name of your CssStyle.
     * <LI> properties: the file name where the parser can find all CSS properties
     *                  names.
     * <LI> extended-parser: <code>true</code> if you want to parse cascading
     *                  style sheet 2 or 3.
     * <OL>
     */
    public static void loadConfig(String version, String profile) {
	try {

		URL allprops = CssFouffa.class.getResource("allcss.properties");

	    if (version == null) {
			// load the CssStyle
			String classStyle = config.getProperty("style2");
			Class style = Class.forName(classStyle);
			CssSelectors.setStyle(style);

			// load properties
			URL url = style.getResource(config.getProperty("properties2"));
			properties = new CssPropertyFactory(url, allprops);

			// aural mode
			String mode0 = config.getProperty("extended-parser");
			if (mode0 != null) {
			    mode = mode0.equals("true");
			}
	    }
	    else if (version.equals("css1")) {
			// load the CssStyle
			String classStyle = config.getProperty("style1");
			Class style = Class.forName(classStyle);
			CssSelectors.setStyle(style);

			// load properties
			URL url = style.getResource(config.getProperty("properties1"));
			properties = new CssPropertyFactory(url, allprops);

			// aural mode
			String mode0 = config.getProperty("extended-parser");
			if (mode0 != null) {
			    mode = mode0.equals("true");
			}
	    } else if ("atsc-tv".equals(profile)) {
			String classStyle = config.getProperty("styleatsc");
			Class style = Class.forName(classStyle);
			CssSelectors.setStyle(style);
			URL url = style.getResource(config.getProperty("atsc-tv"));
		    properties = new CssPropertyFactory(url, allprops);
		}
	    else if (version.equals("css2")) {
			// load the CssStyle
			String classStyle = config.getProperty("style2");
			Class style = Class.forName(classStyle);
			CssSelectors.setStyle(style);

			// load properties
			if (profile == null || "".equals(profile)) {
			    URL url = style.getResource(config.getProperty("properties2"));
			    properties = new CssPropertyFactory(url, allprops);
			} else if (profile.equals("mobile")) {
			    URL url = style.getResource(config.getProperty("mobile"));
			    properties = new CssPropertyFactory(url, allprops);
			} else if (profile.equals("tv")) {
				URL url = style.getResource(config.getProperty("tv"));
				properties = new CssPropertyFactory(url, allprops);
			}

			// aural mode
			String mode0 = config.getProperty("extended-parser");
			if (mode0 != null) {
			    mode = mode0.equals("true");
			}
	    } else if (version.equals("css3")) {
			// load the CssStyle
			String classStyle = config.getProperty("style3");
			Class style = Class.forName(classStyle);
			CssSelectors.setStyle(style);

			// load properties
			URL url = style.getResource(config.getProperty("properties3"));
			properties = new CssPropertyFactory(url, allprops);

			// aural mode
			String mode0 = config.getProperty("extended-parser");
			if (mode0 != null) {
			    mode = mode0.equals("true");
			}
	    } else if (version.equals("svg")) {
			// load the CssStyle
			String classStyle = config.getProperty("svgstyle");
			Class style = Class.forName(classStyle);
			CssSelectors.setStyle(style);

			URL url = style.getResource(config.getProperty("svg"));
			properties = new CssPropertyFactory(url, allprops);

			// aural mode
			String mode0 = config.getProperty("extended-parser");
			if (mode0 != null) {
			    mode = mode0.equals("true");
			}
	    } else if (version.equals("svgtiny")) {
 			// load the CssStyle
 			String classStyle = config.getProperty("svgtinystyle");
 			Class style = Class.forName(classStyle);
 			CssSelectors.setStyle(style);

 			URL url = style.getResource(config.getProperty("svgtiny"));
 			properties = new CssPropertyFactory(url, allprops);

 			// aural mode
 			String mode0 = config.getProperty("extended-parser");
 			if (mode0 != null) {
 			    mode = mode0.equals("true");
 			}
 		} else if (version.equals("svgbasic")) {
 			// load the CssStyle
 			String classStyle = config.getProperty("svgbasicstyle");
 			Class style = Class.forName(classStyle);
 			CssSelectors.setStyle(style);

 			URL url = style.getResource(config.getProperty("svgbasic"));
 			properties = new CssPropertyFactory(url, allprops);

 			// aural mode
 			String mode0 = config.getProperty("extended-parser");
 			if (mode0 != null) {
 			    mode = mode0.equals("true");
 			}
		}

	} catch (Exception e) {
	    System.err.println("org.w3c.css.parser.CssFouffa: couldn't load the style");
	    e.printStackTrace();
	}
    }

    static private Properties config = new Properties();

    /* config by default! */
    static {
	try {
	    URL url = CssFouffa.class.getResource("Config.properties");
	    java.io.InputStream f = url.openStream();
	    config.load(f);
	    f.close();
	    loadConfig("css2", null);
	} catch (Exception e) {
	    System.err.println("org.w3c.css.parser.CssFouffa: couldn't load the config");
	    e.printStackTrace();
	}
    }
}
