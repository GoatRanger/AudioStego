//
// $Id: CssDropInitialValue.java,v 1.1 2002/08/20 13:53:41 sijtsche Exp $
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
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;

/**
 *
 */

public class CssDropInitialValue extends CssProperty {

    CssValue dropvalue;

    CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssDropInitialValue
     */
    public CssDropInitialValue() {
		dropvalue = initial;
    }

    /**
     * Create a new CssDropInitialValue
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssDropInitialValue(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(initial)) {
		dropvalue = initial;
		expression.next();
	}
	else if (val instanceof CssNumber) {
		dropvalue = val;
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
	if (((Css3Style) style).cssDropInitialValue != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssDropInitialValue = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getDropInitialValue();
	}
	else {
	    return ((Css3Style) style).cssDropInitialValue;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssDropInitialValue &&
		dropvalue.equals(((CssDropInitialValue) property).dropvalue));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "drop-initial-value";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return dropvalue;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return dropvalue.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return dropvalue.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return dropvalue == initial;
    }

}
