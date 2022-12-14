//
// $Id: CssBackgroundColor.java,v 1.3 2002/07/22 09:03:10 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBackgroundColor.java,v $
 * Revision 1.3  2002/07/22 09:03:10  sijtsche
 * transparent is no value anymore, has become a color itself
 *
 * Revision 1.2  2002/05/22 14:49:34  dejong
 * transparent is no value anymore, but has become a color itself
 *
 * Revision 3.3  1997/09/09 13:03:57  plehegar
 * Added getColor()
 *
 * Revision 3.2  1997/09/08 14:03:45  plehegar
 * Suppressed a conflict
 *
 * Revision 3.1  1997/08/29 13:13:29  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:12  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:03  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:29:47  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:19:44  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/22 11:20:50  plehegar
 * Initial revision
 *
 */

package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssColor;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'background-color'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;color&gt; | transparent<BR>
 *   <EM>Initial:</EM> transparent<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property sets the background color of an element.
 *   <PRE>
 *   H1 { background-color: #F00 }
 *   </PRE>
 * @version $Revision: 1.3 $
 */
public class CssBackgroundColor extends CssProperty {

    CssValue color;

    static CssIdent transparent = new CssIdent("transparent"); //not in CSS3 anymore, has become color value

    /**
     * Create a new CssBackgroundColor
     */
    public CssBackgroundColor() {
		color = transparent;
    }

    /**
     * Create a new CssBackgroundColor
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBackgroundColor(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	if (val instanceof org.w3c.css.values.CssColor) {
	    color = val;
	    expression.next();
	} else if (val instanceof CssIdent) {
	    //if (val.equals(transparent)) { // obsolete, transparent is a color value now
		//color = transparent;
		//expression.next();
	    //} else
	    if (val.equals(inherit)) {
		color = inherit;
		expression.next();
	    } else {
		color = new org.w3c.css.values.CssColor(ac, (String) val.get());
		expression.next();
	    }
	} else {
	    throw new InvalidParamException("value", val.toString(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return color;
    }

    /**
     * Returns the color
     */
    public final CssValue getColor() {
	return color;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return color.equals(inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return color.toString();
    }


    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBackground cssBackground = ((Css1Style) style).cssBackground;
	if (cssBackground.color != null)
	    style.addRedefinitionWarning(ac, this);
	cssBackground.color = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBackgroundColor();
	} else {
	    return ((Css1Style) style).cssBackground.color;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundColor &&
		color.equals( ((CssBackgroundColor) property).color));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "background-color";
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return color == transparent;
    }

}
