//
// $Id: CssColumnWidth.java,v 1.1 2002/07/19 20:30:12 sijtsche Exp $
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
 *  <P>
 *  <EM>Value:</EM> &lt;integer&gt; || auto || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  The 'column-width' property describes the optimal width of columns within the element. 
 *  Depending on the value of 'column-width-policy', the actual column width may be 
 *  larger than the specified one. 
 */

public class CssColumnWidth extends CssProperty {

    CssValue width;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssColumnWidth
     */
    public CssColumnWidth() {
	// nothing to do
    }

    /**
     * Create a new CssColumnWidth
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssColumnWidth(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    width = val;
	    expression.next();
	} else if (val.equals(auto)) {
	    width = val;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    width = val;
	    expression.next();
	} else {
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
	if (((Css3Style) style).cssColumnWidth != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumnWidth = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getColumnWidth();
	}
	else {
	    return ((Css3Style) style).cssColumnWidth;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColumnWidth &&
		width.equals(((CssColumnWidth) property).width));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "column-width";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return width;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return width.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return width.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (width == auto);
    }

}
