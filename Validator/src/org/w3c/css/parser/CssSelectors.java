//
// $Id: CssSelectors.java,v 1.8 2003/04/08 09:23:41 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssSelectors.java,v $
 * Revision 1.8  2003/04/08 09:23:41  sijtsche
 * bug in display of class names fixed
 *
 * Revision 1.7  2002/08/19 07:24:39  sijtsche
 * tv profile restrictions added
 *
 * Revision 1.6  2002/07/24 14:52:21  sijtsche
 * compile bug fixed
 *
 * Revision 1.5  2002/07/22 15:03:33  sijtsche
 * extra pseudoelements, functions and constants added
 *
 * Revision 1.2  2002/05/23 09:59:20  dejong
 * extra pseudoelements, functions and constants added
 *
 * Revision 2.5  1997/08/27 15:31:43  plehegar
 * Desactivated warning on block and inline element ...
 *
 * Revision 2.4  1997/08/22 14:55:48  plehegar
 * Added isEmpty()
 *
 * Revision 2.3  1997/08/21 07:25:30  plehegar
 * Added auralMode, getNext, setNext
 *
 * Revision 2.2  1997/08/20 11:41:28  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:34  plehegar
 * Nothing
 *
 * Revision 1.1  1997/08/01 19:35:24  plehegar
 * Initial revision
 *
 */
package org.w3c.css.parser;

import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Enumeration;

import org.w3c.css.util.Warnings;
import org.w3c.css.util.Messages;
import org.w3c.css.util.Util;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.Util;

/**
 * This class manages all contextual selector.
 *
 * <p>Note:<BR>
 * Invoke a <code>set</code> function to change the selector clears all
 * properties !
 *
 * @version $Revision: 1.8 $
 */
public final class CssSelectors implements CssSelectorsConstant {

    ApplContext ac;

    /**
     * At rule statement
     */
    AtRule atRule;

    /**
     * The element.
     */
    String element;

    /**
     * All attributes (with class and id)
     */
    Hashtable attributes = new Hashtable();

    /**
     * All pseudo classes
     */
    boolean[] pseudoClass = new boolean[PSEUDOCLASS_CONSTANTS.length];

    /**
     * All pseudo classes
     */
    boolean[] pseudoElement = new boolean[PSEUDOELEMENT_CONSTANTS.length];

    /**
     * The pseudo-class function (contains, nth-child, nth-of-type or lang)
     */
    String pseudofun;
    String pseudofunval;

    char connector = DESCENDANT;

    /**
     * The next context.
     */
    protected CssSelectors next;

    // the specificity
    private int specificity;

    // true if the element is a block-level element
    private  boolean isBlock;

    CssStyle properties;

    // all hashCode (for performance)
    private int hashElement;

    private int hashGeneral;

    // External representation of the selector
    // (for performance)
    private String representation;

    // The CssStyle to use
    private static Class style;

    // see isEmpty and addProperty
    private boolean Init;

    // all HTML element in HTML 4.0
    private static Properties elements = new Properties();

    // special (?) HTML tag
    private static final int HTMLCode = "HTML".hashCode();
    private static final int BODYCode = "BODY".hashCode();

    private String usermedium;

    /**
     * Create a new CssSelectors with no previous selector.
     */
    public CssSelectors(ApplContext ac) {
	try {
	    properties = (CssStyle) style.newInstance();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	this.ac = ac;
    }

    /**
     * Create a new CssSelectors with a previous selector.
     *
     * @param next the next selector
     */
    public CssSelectors(CssSelectors next) {
	this((ApplContext) null);
	this.next = next;
    }

    /**
     * Create a new CssSelectors with a previous selector.
     *
     * @param next the next selector
     */
    public CssSelectors(ApplContext ac, CssSelectors next) {
	this(ac);
	this.next = next;
    }

    /**
     * Set the style for all contexts.
     * Don't forget to invoke this method if you want a style !
     *
     * @param style0 the style
     */
    public static void setStyle(Class style0) {
	Util.verbose("Style is : " + style0);
	style = style0;
    }

    /**
     * Set the attribute atRule
     *
     * @param atRule the new value for the attribute
     */
    public void setAtRule(AtRule atRule) {
        this.atRule = atRule;
    }

    /**
     * Returns the attribute atRule
     *
     * @return the value of the attribute
     */
    public AtRule getAtRule() {
        return atRule;
    }

    /**
     * Set the connector between simple selector
     * @see CssSelectorsConstant
     */
    public void setConnector(char connector) {
	this.connector = connector;
	Invalidate();
    }

    public char getConnector() {
	return connector;
    }

    /**
     * Set the element.
     * Be careful, you should work with upper case not lower case
     * (it's more practical)
     *
     * @param element the element.
     */
    public void setElement(String element) {
	if (element == null) {
	    return;
	}
	this.element = element;
	hashElement = element.hashCode();
	verifyPseudoElement(null);
	Invalidate();
    }

    /**
     * Set the element with verification.
     * Be careful, you should work with upper case not lower case
     * (it's more practical)
     *
     * @param element the element.
     * @param frame   For errors and warnings.
     */
    public void setElement(String element, ApplContext ac) {
	if (element == null) {
	    return;
	}

	String isHTML = elements.getProperty(element.toUpperCase());

/*
	if (Util.fromHTMLFile) {
	    if (isHTML == null) {
		ac.getFrame().addWarning("unknown-html", element);
	    } else if (isHTML.equals("true")) {
		isBlock = true;
		if ((next != null)
		    && (next.element != null)
		    && !next.isBlock) {
		    ac.getFrame().addWarning("noinside", element);
		}
	    }
	}
*/

	this.element = element;
	hashElement = element.hashCode();

/*
	if (Util.fromHTMLFile) {
	    if (hashElement == HTMLCode && next != null) {
		ac.getFrame().addWarning("html-inside");
	    } else if (hashElement == BODYCode && next != null &&
		       next.hashElement != 0 && next.hashElement != HTMLCode) {
		ac.getFrame().addWarning("body-inside");
	    }
	}
*/

	verifyPseudoElement(ac);
	Invalidate();
    }

    /**
     * Get the element.
     */
    public String getElement() {
	return element;
    }

    /**
     * Returns <code>true</code> if the element is a block level element (HTML
     * only)
     */
    public boolean isBlockLevelElement() {
	return isBlock;
    }

    void addAttribute(Attribute attr) {
	Attribute old = (Attribute) attributes.get(attr.getName());
	if (old != null) {
	    try {
		Util.verbose("OLD : " + old);
		Util.verbose("ADD : " + attr);
		Attribute news = old.applyAttribute(attr);
		Util.verbose("NEW : " + news);
		attributes.put(attr.getName(), news);
	    } catch (AttributeException e) {
		//		e.printStackTrace();
		if (ac != null) {
		    InvalidParamException error =
			new InvalidParamException("incompatible", old, attr, ac);
		    ac.getFrame().addError(new CssError(error));
		}
	    }
	} else {
	    Util.verbose("ADD : " + attr);
	    attributes.put(attr.getName(), attr);
	}
    }

    /**
     * Add an attribute to this selector.
     * if the selector type is ATTRIBUTE_ANY, the value is ignored.
     */
    public void addAttribute(String attName, String value)
	throws InvalidParamException {
	if (ac.getProfile() != null && !"".equals(ac.getProfile())) {
	    if (ac.getProfile().equals("mobile")) {
		throw new InvalidParamException("notformobile", "attributes", ac);
	    }
	} else {
	    Attribute attr =
		new AttributeExact().setValue(value).setName(attName);

	    attributes.put(attName, attr);
	    addAttribute(attr);
	    Invalidate();
	}
    }

    /**
     * Add an attribute to this selector.
     * if the selector type is ATTRIBUTE_ANY, the value is ignored.
     */
    public void addAttribute(String attName, String value,
			     int selectorType) throws InvalidParamException {

		Attribute attr = null;

		if (ac.getProfile() != null && !"".equals(ac.getProfile())) {

		    if (ac.getProfile().equals("mobile")) {
				if (selectorType == ATTRIBUTE_CLASS_SEL) {
					attr = new AttributeOneOf().addValue(value).setName(attName);
				    addAttribute(attr);
				    Invalidate();
				    return;
				} else if (selectorType == ATTRIBUTE_EXACT) {
					attr = new AttributeExact().setValue(value).setName(attName);
				    addAttribute(attr);
				    Invalidate();
				    return;
				} else {
					throw new InvalidParamException("notformobile", "attributes" , ac);
				}
		    } else if (ac.getProfile().equals("tv")) {
				throw new InvalidParamException("notfortv", "attributes", ac);
			}
		}

	    switch (selectorType) {
	    	case ATTRIBUTE_ANY:
	    		if (ac.getCssVersion().equals("css1")) {
					throw new InvalidParamException("notversion","[" + attName +"]", ac.getCssVersion(), ac);
				} else {
					attr = new AttributeAny().setName(attName);
				}
				break;
	    	case ATTRIBUTE_EXACT:
				attr = new AttributeExact().setValue(value).setName(attName);
				break;
	    	case ATTRIBUTE_BEGIN:
	    		if (ac.getCssVersion().equals("css1")) {
					throw new InvalidParamException("notversion","[" + attName + "|=" + value + "]", ac.getCssVersion(), ac);
				} else {
					attr = new AttributeBegin().setValue(value).setName(attName);
				}
				break;
	    	case ATTRIBUTE_ONE_OF:
				if (value.indexOf(' ') != -1) {
				    InvalidParamException error =
					new InvalidParamException("space", value, ac);
				    ac.getFrame().addError(new CssError(error));
				    return;
				}
				if (ac.getCssVersion().equals("css1")) {
					throw new InvalidParamException("nocomb", "~=", ac);
				} else {
					attr = new AttributeOneOf().addValue(value).setName(attName);
				}
				break;
			case ATTRIBUTE_CLASS_SEL:
				attr = new AttributeOneOf().addValue(value).setName(attName);
				break;
			case ATTRIBUTE_START:
				if (ac.getCssVersion().equals("css3")) {
				    attr = new AttributeStart().setValue(value).setName(attName);
				    break;
				} else {
				    throw new InvalidParamException("nocomb", "^=", ac);
				}
	    	case ATTRIBUTE_SUFFIX:
				if (ac.getCssVersion().equals("css3")) {
				    attr = new AttributeSuffix().setValue(value).setName(attName);
				    break;
				} else {
				    throw new InvalidParamException("nocomb", "$=", ac);
				}
	    	case ATTRIBUTE_SUBSTR:
				if (ac.getCssVersion().equals("css3")) {
				    attr = new AttributeSubstr().setValue(value).setName(attName);
				    break;
				} else {
				    throw new InvalidParamException("nocomb", "*=", ac);
				}
	    	default:
				throw new NullPointerException("Invalid access in CssSelectors"
					       + (char) selectorType);
	    }

	    addAttribute(attr);
	    Invalidate();

    }

    public Enumeration getAttributes() {
	return attributes.elements();
    }

    public void addPseudo(String pseudo) throws InvalidParamException {
	if (pseudo == null) {
	    return;
	}
	if (ac.getProfile() != null && !"".equals(ac.getProfile())) {
	    if (ac.getProfile().equals("mobile") &&
		pseudo.equals("first-child") ) {
		throw new InvalidParamException("notformobile", pseudo, ac);
	    }
	}

	int index = getPseudoClassIndex(pseudo);
	if (index != -1) {
		if ((getAtRule() != null)
		&& getAtRule().toString() != null
		&& getAtRule().toString().equals("@media atsc-tv")) {
			if (!pseudo.equals("target")) {
			    addPseudoClass(index);
			} else {
			    throw new InvalidParamException("notforatsc", pseudo, ac);
			}
	    } else {
			addPseudoClass(index);
	    }
	} else {

		if (ac.getProfile().equals("tv")) {
			throw new InvalidParamException("pseudo", pseudo, ac);
		}

	    index = getPseudoElementIndex(pseudo);
	    if (index != -1) {
		addPseudoElement(index);
	    } else {
		    throw new InvalidParamException("pseudo", pseudo, ac);
	    }
	}
    }

    private int getPseudoClassIndex(String pseudo) {

	if (ac.getCssVersion().equals("css3")) {
	    for (int i = 0; i < PSEUDOCLASS_CONSTANTS.length; i++) {
			if (pseudo.equals(PSEUDOCLASS_CONSTANTS[i])) {
			    return i;
			}
	    }
	} else if (ac.getCssVersion().equals("css2")) {

		 if (ac.getProfile() != null && !"".equals(ac.getProfile())) {
			if (ac.getProfile().equals("mobile")) {
			    for (int i = 0; i < PSEUDOCLASS_CONSTANTS_MOBILE.length; i++) {
					if (pseudo.equals(PSEUDOCLASS_CONSTANTS_MOBILE[i])) {
					    return i;
					}
			    }
			} else if (ac.getProfile().equals("tv")) {
			    for (int i = 0; i < PSEUDOCLASS_CONSTANTSTV.length; i++) {
					if (pseudo.equals(PSEUDOCLASS_CONSTANTSTV[i])) {
					    return i;
					}
			    }
			} else {
			    for (int i = 0; i < PSEUDOCLASS_CONSTANTSCSS2.length; i++) {
					if (pseudo.equals(PSEUDOCLASS_CONSTANTSCSS2[i])) {
					    return i;
					}
			    }
			}
		} else {
	    	for (int i = 0; i < PSEUDOCLASS_CONSTANTSCSS2.length; i++) {
				if (pseudo.equals(PSEUDOCLASS_CONSTANTSCSS2[i])) {
				    return i;
				}
			}
		}
	} else if (ac.getCssVersion().equals("css1")) {
	    for (int i = 0; i < PSEUDOCLASS_CONSTANTSCSS1.length; i++) {
			if (pseudo.equals(PSEUDOCLASS_CONSTANTSCSS1[i])) {
			    return i;
			}
	    }
	}
	return -1;
    }

    /**
     * Set the pseudoClass with verification.
     *
     * Be careful, you should work with lower case not upper case
     * (it's more practical)
     *
     * @param pseudoClass The pseudo class.
     * @param frame       For errors and warnings.
     * @deprecated
     */
    private void addPseudoClass(int index) {
	//	hashPseudoClass = pseudoClass.hashCode();
	this.pseudoClass[index] = true;
	//if (element != null && !element.equals("A")) {
	//    ac.getFrame().addWarning("pseudo-classes", pseudoClass);
	//}
    }

    /**
     * Get pseudo class
     *
     * <p> There is no semi-colon at the beginning of the string.
     */
    public Enumeration getPseudoClass() {

		if (ac.getCssVersion().equals("css3")) {
			return new PseudoEnumeration(pseudoClass, PSEUDOCLASS_CONSTANTS);
		} else if (ac.getCssVersion().equals("css2")) {
			if (ac.getProfile() != null) {
				if (ac.getProfile().equals("mobile")) {
					return new PseudoEnumeration(pseudoClass, PSEUDOCLASS_CONSTANTS_MOBILE);
				} else if (ac.getProfile().equals("tv")) {
					return new PseudoEnumeration(pseudoClass, PSEUDOCLASS_CONSTANTSTV);
				} else {
					return new PseudoEnumeration(pseudoClass, PSEUDOCLASS_CONSTANTSCSS2);
				}
			} else {
				return new PseudoEnumeration(pseudoClass, PSEUDOCLASS_CONSTANTSCSS2);
			}
		} else if (ac.getCssVersion().equals("css1")) {
			return new PseudoEnumeration(pseudoClass, PSEUDOCLASS_CONSTANTSCSS1);
		} else {
			return new PseudoEnumeration(pseudoClass, PSEUDOCLASS_CONSTANTS);
		}
    }

    public void setPseudoFun(String pseudo, String param)
    	throws InvalidParamException {
	if (pseudo.equals("lang")) {

		if (ac.getCssVersion().equals("css1")) {
			throw new InvalidParamException("notversion", pseudo, ac.getCssVersion(), ac);
		}

		if (ac.getProfile() != null) {
			if (ac.getProfile().equals("mobile")) {
			    throw new InvalidParamException("notformobile", pseudo, ac);
			} else if (ac.getProfile().equals("tv")) {
				throw new InvalidParamException("notfortv", pseudo, ac);
			}
		}
	    pseudofun = pseudo;
		pseudofunval = param;
	} else if (pseudo.equals("nth-child")) {
	    if (ac.getCssVersion().equals("css1") || ac.getCssVersion().equals("css2")) {
			throw new InvalidParamException("notversion", pseudo, ac.getCssVersion(), ac);
		}

	    try {
			Integer i = new Integer(param);
			pseudofun = pseudo;
			pseudofunval = param;
		return;
	    } catch (NumberFormatException nfe) {
			throw new InvalidParamException("pseudoval", param, ac);
	    }
	} else if (pseudo.equals("nth-of-type")) {
	    if (ac.getCssVersion().equals("css1") || ac.getCssVersion().equals("css2")) {
			throw new InvalidParamException("notversion", pseudo, ac.getCssVersion(), ac);
		}

	    try {
			Integer i = new Integer(param);
			pseudofun = pseudo;
			pseudofunval = param;
			return;
	    } catch (NumberFormatException nfe) {
			throw new InvalidParamException("pseudoval", param, ac);
	    }
	} else if (pseudo.equals("nth-last-of-type")) {
	    if (ac.getCssVersion().equals("css1") || ac.getCssVersion().equals("css2")) {
			throw new InvalidParamException("notversion", pseudo, ac.getCssVersion(), ac);
		}

	    try {
			Integer i = new Integer(param);
			pseudofun = pseudo;
			pseudofunval = param;
			return;
	    } catch (NumberFormatException nfe) {
			throw new InvalidParamException("pseudoval", param, ac);
	    }

	} else if (pseudo.equals("contains")) {
	    if (param.startsWith("\"") && param.endsWith("\"")) {
			pseudofun = pseudo;
			pseudofunval = param;
			return;
	    } else {
			throw new InvalidParamException("pseudoval", param, ac);
		}
	} else if (pseudo.equals("not")) {
		pseudofun = pseudo;
		pseudofunval = param;
		return;
	} else {
	    CssErrorToken e =
		new CssErrorToken(0, ac.getMsg().getErrorString("pseudo"),
				  new String[0]);
	    e.skippedString = pseudo;
	    ac.getFrame().addError(e);
	}
    }

    public String getPseudoFun() {
		return pseudofun;
    }

    private int getPseudoElementIndex(String pseudo) throws InvalidParamException {

	if (ac.getCssVersion().equals("css3")) {
	    for (int i = 0; i < PSEUDOELEMENT_CONSTANTS.length; i++) {
		if (pseudo.equals(PSEUDOELEMENT_CONSTANTS[i])) {
		    return i;
		}
	    }
	} else if (ac.getCssVersion().equals("css2")) {
		if (ac.getProfile() != null) {
			if (ac.getProfile().equals("mobile")) {
			    throw new InvalidParamException("notformobile", pseudo, ac);
			} else {
				for (int i = 0; i < PSEUDOELEMENT_CONSTANTSCSS2.length; i++) {
					if (pseudo.equals(PSEUDOELEMENT_CONSTANTSCSS2[i])) {
					    return i;
					}
		    	}
			}
		} else {
			for (int i = 0; i < PSEUDOELEMENT_CONSTANTSCSS2.length; i++) {
				if (pseudo.equals(PSEUDOELEMENT_CONSTANTSCSS2[i])) {
				    return i;
				}
	    	}
		}
	} else if (ac.getCssVersion().equals("css1")) {
	    for (int i = 0; i < PSEUDOELEMENT_CONSTANTSCSS1.length; i++) {
		if (pseudo.equals(PSEUDOELEMENT_CONSTANTSCSS1[i])) {
		    return i;
		}
	    }
	}
	return -1;
    }

    /**
     * Set the pseudoElement.
     * Be careful, you should work with lower case not upper case
     * (it's more practical)
     *
     * @param pseudoElement the pseudo element
     * @param frame         For errors and warnings.
     * @deprecated
     */
    private void addPseudoElement(int index) {
	//     if (!isBlock) {
	//       ac.getFrame().addWarning("withblock");
	//     }

	this.pseudoElement[index] = true;
	// hashPseudoElement = pseudoElement.hashCode();
    }

    /**
     * Get the pseudoElement.
     *
     * <p> There is no semi-colon at the beginning of the string.
     */
    public Enumeration getPseudoElement() {
		if (ac.getCssVersion().equals("css3")) {
			return new PseudoEnumeration(pseudoElement, PSEUDOELEMENT_CONSTANTS);
		} else if (ac.getCssVersion().equals("css2")) {
			return new PseudoEnumeration(pseudoElement, PSEUDOELEMENT_CONSTANTSCSS2);
		} else if (ac.getCssVersion().equals("css1")) {
			return new PseudoEnumeration(pseudoElement, PSEUDOELEMENT_CONSTANTSCSS1);
		} else {
			return new PseudoEnumeration(pseudoElement, PSEUDOELEMENT_CONSTANTS);
		}
    }

    /**
     * Adds a property to this selector.
     *
     * @param property The property.
     * @param warnings For warning report.
     */
    public void addProperty(CssProperty property, Warnings warnings) {
	Init = true;
	if (properties != null) {
	    properties.setProperty(ac, property, warnings);
	} else {
	    System.err.println("[ERROR] Invalid state in org.w3c.css.parser.CssSelectors#addProperty");
	    System.err.println("[ERROR] Please report BUG");
	}
    }

    public CssStyle getStyle() {
	return properties;
    }

    /**
     * Get the specificity of this selector.
     *
     * @see CssContextualSelector
     */
    public int getSpecificity() {

		if (specificity == 0) {
		    // compute the specificity
		    if (next != null) {
				specificity = next.getSpecificity();
		    }

		    if (element != null) {
				specificity += 1;
		    }

		    for (Enumeration e = attributes.elements(); e.hasMoreElements();) {
				Attribute attr = (Attribute) e.nextElement();
				if (attr.isId()) {
				    specificity += 10000;
				} else {
				    specificity += 100;
				}
		    }

	    	for (Enumeration e = getPseudoClass(); e.hasMoreElements();e.nextElement() ) {
		    	specificity += 100;
		    }
		}

		return specificity;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if ((atRule instanceof AtRulePage)
	    || (atRule instanceof AtRuleFontFace)) {
	    return atRule.toString() + " ";
	}
	if (representation == null) {
	    representation = "";

	    // I'm in reverse order, so compute the next before the current
	    if (next != null) {
		representation += next.toString();
	    }

	    String local = "";
	    if (element != null) {
		local += element;
	    }
	    for (Enumeration e = attributes.elements(); e.hasMoreElements();) {
		local += e.nextElement().toString();
	    }
	    for (Enumeration e = getPseudoClass(); e.hasMoreElements();) {
		local += ":" + e.nextElement().toString();
	    }

	    if (pseudofun != null) {
		local += ":" + pseudofun + "(" + pseudofunval + ")";
	    }
	    if (ac.getCssVersion().equals("css1") || ac.getCssVersion().equals("css2")) {
		   	for (Enumeration e = getPseudoElement(); e.hasMoreElements();) {
				local += ":" + e.nextElement().toString();
	    	}
		} else {
		   	for (Enumeration e = getPseudoElement(); e.hasMoreElements();) {
				local += "::" + e.nextElement().toString();
	    	}
		}
	    if (local.length() == 0) {
		// avoid problem with * { color : red }
		local = "*";
	    }
	    representation += local;
	    if (connector != DESCENDANT) {
		representation += " " + new Character(connector);
	    }
	    representation += " ";
	}

	return representation;
    }

    /**
     * Get a hashCode.
     */
    public int hashCode() {
	if (hashGeneral == 0) {
	    if (atRule instanceof AtRuleFontFace) {
		hashGeneral = atRule.hashCode();
	    } else {
		String s = toString();
		hashGeneral = s.hashCode();
		for (int i = 0; i < s.length(); i++) {
		    hashGeneral += (int) s.charAt(i);
		}
	    }
	}
	return hashGeneral;
    }

    /**
     * Returns <code>true</code> if the selector is equals to an another.
     *
     * @param selector The selector to compare
     */
    public boolean equals(Object selector) {
	if ((selector == null) || !(selector instanceof CssSelectors)) {
	    return false;
	}
	CssSelectors s = (CssSelectors) selector;

	if ((atRule instanceof AtRulePage)
	    || (atRule instanceof AtRuleFontFace)) {
	    return atRule.equals(s.atRule);
	}
	if (hashCode() == s.hashCode()) {
	    if (atRule == null) {
		return (s.getAtRule() == null);
	    } else {
		return atRule.canApply(s.getAtRule());
	    }
	} else {
	    return false;
	}
    }

    /**
     * Set the previous selector.
     *
     * @param next the previous selector.
     */
    public void setNext(CssSelectors next) {
	this.next = next;
	verifyPseudoElement(null);
	Invalidate();
    }

    /**
     * Get the previous selector.
     */
    public CssSelectors getNext() {
	return next;
    }

    final boolean canApply(Hashtable attrs, Hashtable attrs2) {
	if (attrs.size() > 0) {
	    for (Enumeration e = attrs.elements(); e.hasMoreElements();) {
		Attribute attr = (Attribute) e.nextElement();
		Attribute other =
		    (Attribute) attrs2.get(attr.getName());
		if (other != null) {
		    if (!attr.canApply(other)) {
			return false;
		    }
		} else {
		    return false;
		}
	    }
	    return true;
	}
	return true;
    }

    /**
     * Returns <code>true</code> if the selector can matched this selector.
     *
     * <p>Examples:<br>
     * <OL>
     * <LI><code>H1.canApply(HTML BODY H1)</code> returns <code>true</code>
     * <LI><code>H1.canApply(HTML BODY H1 EM)</code> returns <code>false</code>
     * <LI><code>(H1 EM).canApply(HTML BODY H2 EM)</code> returns
     *     <code>false</code>
     * <LI><code>(HTML EM).canApply(HTML BODY H2 EM)</code> returns
     *     <code>true</code>
     * </OL>
     *
     * <p> Note:<BR>
     * In principle, if you work with a HTML document, your selector should
     * start with HTML BODY. Because you are always in this context when you
     * parse the text in a HTML document.
     *
     * @param selector the selector to match
     * @see            org.w3c.css.css.CssCascadingOrder#order
     */
    public boolean canApply(CssSelectors selector) {
	if ((atRule instanceof AtRulePage)
	    || (atRule instanceof AtRuleFontFace)) {
	    return atRule.canApply(selector.atRule);
	}
	boolean result = canApply(attributes, selector.attributes);
	// current work - don't touch
	Util.verbose(getSpecificity() + " canApply this " + this + " selector: " + selector);
	Util.verbose( "connector " + connector);
	Util.verbose( attributes.toString() );
	Util.verbose( selector.attributes.toString() );
	Util.verbose("canApply for attributes :" + result);

	if ((hashElement != selector.hashElement) && hashElement != 0) {
	    // here we are in this case :
	    // H1 and HTML BODY H1 EM
	    // don't do anything !
	    // the cascading order algorithm resolves this case like this :
	    //
	    // if (for all contexts) !canApply(selector)
	    //       go and see canApply(selector.getNext())
	    //
	    // for further informations, see org.w3c.css.css.CssCascadingOrder#order
	    Util.verbose("canApply RETURNS FALSE");
	    return false;
	} else {
	    if (next == null || selector.next == null) {
		Util.verbose("canApply RETURNS " + result);
		return canApply(attributes, selector.attributes);
	    } else {
		return next.canMatched(selector.next);
	    }
	}
    }

    /**
     * Returns true if the selector can matched an another selector.
     * called by canApply
     *
     * @param selector The selector to compare
     */
    private boolean canMatched(CssSelectors selector) {
	boolean result = canApply(attributes, selector.attributes);
	// current work
	Util.verbose("canMatched this " + this + " selector: " + selector);
	Util.verbose( "connector " + connector);
	Util.verbose( attributes.toString() );
	Util.verbose( selector.attributes.toString() );
	Util.verbose( "canMatched for attributes :" + result);

	if ((hashElement != selector.hashElement) && hashElement != 0) {
	    if ((connector == DESCENDANT) && (selector.next != null)) {
		// here we are in this case :
		// H1 and HTML BODY H1 EM
		// H1 can't matched EM but EM have next
		return canMatched(selector.next);
	    } else {
		// here we are in this case :
		// H1 and HTML
		// H1 can't matched HTML and HTML don't have next
		Util.verbose("canMatched RETURN FALSE");
		return false;
	    }
	}

	if (next == null || selector.next == null) {
	    // here we are in this case :
	    // H1 and BODY HTML H1
	    // or :
	    // HTML BODY and BODY (this case won't appear in principle)
	    Util.verbose("canMatched RETURN " + result);
	    return canApply(attributes, selector.attributes);
	} else {
	    // here we are in this case :
	    // BODY H1 and HTML BODY H1
	    return next.canMatched(selector.next);
	}
    }

    /**
     * Returns <code>true</code> if there is no property in this document.
     */
    public boolean isEmpty() {
	return !Init;
    }

    void verifyPseudoElement(ApplContext ac) {
	/*
	if (next != null && next.pseudoElement != null) {
	    // eliminate this error
	    if (frame != null) {
		InvalidParamException error =
		    new InvalidParamException("pseudo"-element",
					      next.pseudoElement,
					      this.toString());
		ac.getFrame().addError(new CssError(error));
	    }
	    next.pseudoElement = null;
	    next.verifyPseudoElement(frame);
	    next.Invalidate();
	}
	*/
    }

    void verifyPseudoClass(ApplContext ac) {
	/*
	if (next != null && next.pseudoClass != null) {
	    // eliminate this error
	    if (frame != null) {
		InvalidParamException error =
		    new InvalidParamException("pseudo-class",
					      next.pseudoClass,
					      this.toString());
		ac.getFrame().addError(new CssError(error));
	    }
	    next.pseudoClass = null;
	    next.verifyPseudoClass(frame);
	    next.Invalidate();
	}
	*/
    }

    void Invalidate() {
	// invalidate all pre-computation in this selectors
	representation = null;
	specificity = 0;
	hashGeneral = 0;

	if (Init) {
	    // yes I invalidate all properties too !
	    try {
		properties = (CssStyle) style.newInstance();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public void setUserMedium(String medium) {
	usermedium = medium;
    }

    static {
	try {
	    URL url = CssSelectors.class.getResource("Elements.dtd4");
	    java.io.InputStream f = url.openStream();
	    try {
		elements.load(f);
	    } finally {
		f.close();
	    }
	} catch (Exception e) {
	    System.err.println("org.w3c.css.properties.CssSelectors: couldn't load properties");
	    System.err.println("  " + e.toString() );
	}
    }
}




class PseudoEnumeration implements Enumeration {
    boolean[] classes;
    String[] values;
    int current = -1;

    PseudoEnumeration(boolean[] classes, String[] values) {
	this.classes = classes;
	this.values = values;

	while ((++current < classes.length)
	       && !classes[current]) /*nothing*/;
    }

    public boolean hasMoreElements() {
	return (current != classes.length);
    }

    public Object nextElement() {
	String v = values[current];
	while ((++current < classes.length)
	       && !classes[current]) /*nothing*/;
	return v;
    }
}
