//
// $Id: CssNavUp.java,v 1.1 2002/12/20 16:00:15 sijtsche Exp $
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
import org.w3c.css.values.CssURL;

public class CssNavUp extends CssProperty {

    CssValue navUp;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssNavUp
     */
    public CssNavUp() {
	// nothing to do
    }

    /**
     * Create a new CssNavUp
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssNavUp(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    navUp = val;
	    expression.next();
	} else if (val.equals(auto)) {
	    navUp = val;
	    expression.next();
	} else if (val instanceof CssURL) {
	    navUp = val;
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
	if (((Css3Style) style).cssNavUp != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssNavUp = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getNavUpCSS3();
	}
	else {
	    return ((Css3Style) style).cssNavUp;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssNavUp &&
		navUp.equals(((CssNavUp) property).navUp));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "nav-up";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return navUp;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return navUp.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return navUp.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (navUp == auto);
    }

}
