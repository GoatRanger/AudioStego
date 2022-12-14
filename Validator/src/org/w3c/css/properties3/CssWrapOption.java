//
// $Id: CssWrapOption.java,v 1.2 2002/12/23 08:19:46 sijtsche Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.properties.CssProperty;

/**
 *
 */

    public class CssWrapOption extends CssProperty {

	CssValue wrapoption;

	private static CssIdent wrap = new CssIdent("wrap");
	private static CssIdent softwrap = new CssIdent("soft-wrap");
	private static CssIdent hardwrap = new CssIdent("hard-wrap");
	private static CssIdent emergency = new CssIdent("emergency");

	/**
	 * Create a new CssWrapOption
	 */
	public CssWrapOption() {
	    wrapoption = wrap;
	}

	/**
	 * Create a new CssWrapOption
	 *
	 *
	 */
	public CssWrapOption(ApplContext ac, CssExpression expression) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val.equals(wrap)) {
			wrapoption = wrap;
			expression.next();
	    }
	    else if (val.equals(softwrap)) {
			wrapoption = softwrap;
			expression.next();
	    }
	    else if (val.equals(hardwrap)) {
			wrapoption = hardwrap;
			expression.next();
	    }
	    else if (val.equals(emergency)) {
			wrapoption = emergency;
			expression.next();
	    }
	    else if (val.equals(inherit)) {
			wrapoption = inherit;
			expression.next();
	    }

	    else {
		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	    }
	}

	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((Css3Style) style).cssWrapOption != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssWrapOption = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getWrapOption();
	    } else {
		return ((Css3Style) style).cssWrapOption;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssWrapOption &&
		    wrapoption.equals( ((CssWrapOption) property).wrapoption));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "wrap-option";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return wrapoption;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return wrapoption.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return wrapoption.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return wrapoption == wrap;
	}

    }
