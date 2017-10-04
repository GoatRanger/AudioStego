//
// $Id: CssUnicodeBidi.java,v 1.3 2003/07/28 10:34:21 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 */
public class CssUnicodeBidi extends CssProperty {

    CssValue value;

    private static CssIdent normal = new CssIdent("normal");

    private static CssIdent embed = new CssIdent("embed");

    private static CssIdent bidi_override = new CssIdent("bidi-override");

    /**
     * Create a new CssUnicodeBidi
     */
    public CssUnicodeBidi() {
	value = normal;
    }

    /**
     * Create a new CssUnicodeBidi
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssUnicodeBidi(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val.equals(normal)) {
	    value = normal;
	    expression.next();
	} else if (val.equals(embed)) {
	    value = embed;
	    expression.next();
	} else if (val.equals(bidi_override)) {
	    value = bidi_override;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}

    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "unicode-bidi";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssUnicodeBidi != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssUnicodeBidi = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getUnicodeBidi();
	} else {
	    return ((Css1Style) style).cssUnicodeBidi;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssUnicodeBidi &&
		value.equals(((CssUnicodeBidi) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == normal;
    }

}
