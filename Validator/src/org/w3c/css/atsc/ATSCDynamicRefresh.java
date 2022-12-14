//
// $Id: ATSCDynamicRefresh.java,v 1.1 2003/01/08 10:57:10 sijtsche Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.atsc;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.Util;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;

public class ATSCDynamicRefresh extends CssProperty {

    CssValue dynamicRefresh;
    ApplContext ac;
	CssIdent auto = new CssIdent("auto");
	CssIdent none = new CssIdent("none");

    /**
     * Create a new ATSCDynamicRefresh
     */
    public ATSCDynamicRefresh () {
	//nothing to do
    }

    /**
     * Create a new ATSCDynamicRefresh
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ATSCDynamicRefresh (ApplContext ac, CssExpression expression)
	throws InvalidParamException {

	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();
	if (val instanceof CssNumber) {
	    if (((CssNumber) val).getValue() > 0 &&
			((CssNumber) val).getValue() < 32767 &&
			((CssNumber) val).isInteger()) {

			dynamicRefresh = val;
			expression.next();
	    } else {
			throw new InvalidParamException("value", val.toString(),
						getPropertyName(), ac);
	    }
    } else if (val.equals(auto) || val.equals(none) || val.equals(inherit)) {
		dynamicRefresh = val;
		expression.next();
	} else {
	    throw new InvalidParamException("value", val.toString(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	 if (((ATSCStyle) style).dynamicRefresh != null)
	     style.addRedefinitionWarning(ac, this);
	 ((ATSCStyle) style).dynamicRefresh = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ATSCStyle) style).getDynamicRefresh();
	} else {
	    return ((ATSCStyle) style).dynamicRefresh;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ATSCDynamicRefresh &&
                dynamicRefresh.equals( ((ATSCDynamicRefresh) property).dynamicRefresh));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "atsc-dynamic-refresh";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return dynamicRefresh;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return false;
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return dynamicRefresh.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}
