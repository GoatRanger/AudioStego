//
// $Id: CssInlineBoxAlign.java,v 1.1 2002/08/20 13:53:41 sijtsche Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;

/**
 *
 */

public class CssInlineBoxAlign extends CssProperty {

    CssValue inlineboxalign;

    private static CssIdent initial = new CssIdent("initial");
    private static CssIdent last = new CssIdent("last");

    /**
     * Create a new CssInlineBoxAlign
     */
    public CssInlineBoxAlign() {
		inlineboxalign = initial;
    }

    /**
     * Create a new CssInlineBoxAlign
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssInlineBoxAlign(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(initial)) {
		inlineboxalign = initial;
		expression.next();
	}
	else if (val.equals(last)) {
		inlineboxalign = last;
		expression.next();
	}
	else if (val instanceof CssNumber) {
		inlineboxalign = val;
		expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssInlineBoxAlign != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssInlineBoxAlign = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getInlineBoxAlign();
	}
	else {
	    return ((Css3Style) style).cssInlineBoxAlign;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssInlineBoxAlign &&
		inlineboxalign.equals(((CssInlineBoxAlign) property).inlineboxalign));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "inline-box-align";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return inlineboxalign;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return inlineboxalign.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return inlineboxalign.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return inlineboxalign == initial;
    }

}
