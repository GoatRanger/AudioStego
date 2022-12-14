//
// $Id: CssHeightCSS3.java,v 1.1 2002/12/24 13:18:36 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssHeightCSS3.java,v $
 * Revision 1.1  2002/12/24 13:18:36  sijtsche
 * new version for CSS3: value initial added
 *
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:49  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:23  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:20  plehegar
 * Nothing
 *
 * Revision 1.4  1997/08/06 17:30:05  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.3  1997/07/30 13:20:03  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/25 15:41:58  plehegar
 * bug fix
 *
 * Revision 1.1  1997/07/25 15:38:12  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'height'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | auto <BR>
 *   <EM>Initial:</EM> auto<BR>
 *   <EM>Applies to:</EM> block-level and replaced elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property can be applied to text, but it is most useful with replaced
 *   elements such as images. The height is to be enforced by scaling the image
 *   if necessary. When scaling, the aspect ratio of the image is preserved if
 *   the 'width' property is 'auto'.
 *   <P>
 *   Example:
 *   <PRE>
 *   IMG.icon { height: 100px }
 *   </PRE>
 *   <P>
 *   If the 'width' and 'height' of a replaced element are both 'auto', these
 *   properties will be set to the intrinsic dimensions of the element.
 *   <P>
 *   If applied to a textual element, the height can be enforced with e.g. a
 *   scrollbar.
 *   <P>
 *   Negative values are not allowed.
 * @version $Revision: 1.1 $
 */
public class CssHeightCSS3 extends CssProperty {

    CssValue value;

    private static CssIdent auto = new CssIdent("auto");
    private static CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssHeightCSS3
     */
    public CssHeightCSS3() {
	value = auto;
    }

    /**
     * Create a new CssHeightCSS3
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssHeightCSS3(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();
	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val.equals(initial)) {
		value = initial;
	} else if (val instanceof CssLength || val instanceof CssPercentage) {
	    float f = ((Float) val.get()).floatValue();
	    if (f < 0) {
		throw new InvalidParamException("negative-value",
						val.toString(), ac);
	    }
	    value = val;
	} else if (val.equals(auto)) {
	    value = auto;
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}

	expression.next();
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
	return "height";
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
	Css3Style style0 = (Css3Style) style;
	if (style0.cssHeightCSS3 != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssHeightCSS3 = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getHeightCSS3();
	} else {
	    return ((Css3Style) style).cssHeightCSS3;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssHeightCSS3 &&
		value.equals(((CssHeightCSS3) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == auto;
    }

}
