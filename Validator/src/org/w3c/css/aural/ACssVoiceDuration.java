//
// $Id: ACssVoiceDuration.java,v 1.1 2003/07/30 06:34:52 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: ACssVoiceDuration.java,v $
 * Revision 1.1  2003/07/30 06:34:52  sijtsche
 * new speech property
 *
 * Revision 1.2  2002/04/08 21:16:56  plehegar
 * New
 *
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.6  1997/08/25 13:52:36  plehegar
 * Added getValue()
 *
 * Revision 1.5  1997/08/22 15:00:35  plehegar
 * Bugs
 *
 * Revision 1.4  1997/08/22 14:58:25  plehegar
 * Added getPropertyInStyle()
 *
 * Revision 1.3  1997/08/21 21:13:38  plehegar
 * Added time
 *
 * Revision 1.2  1997/08/21 14:34:19  vmallet
 * Minor modifications so we could compile it.
 *
 * Revision 1.1  1997/08/14 12:58:48  plehegar
 * Initial revision
 *
 */

package org.w3c.css.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTime;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * &nbsp;&nbsp; 'pause-before'
 *
 * <P>
 * <EM>Value: </EM>&lt;time&gt; | &lt;percentage&gt;<BR>
 * <EM>Initial:</EM> UA specific<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> NA<BR>
 *
 * <P>This property specifies the pause before an element is spoken. It
 * may be given in an absolute units (seconds, milliseconds) or as a
 * relative value - in which case it is relative to the reciprocal of the
 * 'speed' property: if speed is 120 words per minute (ie a word takes
 * half a second, 500 milliseconds) then a pause-before of 100% means a
 * pause of 500 ms and a pause-before of 20% means 100ms.
 *
 * <p>Using relative units gives more robust stylesheets in the face of
 * large changes in speed and is recommended practice.
 *
 * @version $Revision: 1.1 $
 */
public class ACssVoiceDuration extends ACssProperty {

    CssValue value;

    private static CssTime defaultValue;

    /**
     * Create a new ACssVoiceDuration
     */
    public ACssVoiceDuration() {
	// Initial is User Agent Specific
	if (defaultValue == null) {
	    defaultValue = new CssTime(ACssProperties.getValue(this, "default"));
	}
	value = defaultValue;
    }

    /**
     * Creates a new ACssVoiceDuration
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */
    public ACssVoiceDuration(ApplContext ac, CssExpression expression)
	   throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssTime) {
	    float num = ((Float) val.get()).floatValue();
	    if (num < 0) {
		throw new InvalidParamException("negative-value",
						val.toString(), ac);
	    }
	    value = val;
	    expression.next();
	    return;
	}

	throw new InvalidParamException("value", val.toString(),
					getPropertyName(), ac);
    }

    /**
     * Returns the current value
     */
    public Object get() {
	return value;
    }

    /**
     * Returns some usable value of this property...
     */
    public int getValue() { // vm
	return ((Float) value.get()).intValue();
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (value != null)
	    return value.toString();
	else
	    return null;
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "voice-duration";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssVoiceDuration != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssVoiceDuration = this;
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (value != null) {
	    return (property instanceof ACssVoiceDuration &&
		    value.equals(((ACssVoiceDuration) property).value));
	} else {
	    return false;
	}
    }


    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getVoiceDuration();
	} else {
	    return ((ACssStyle) style).acssVoiceDuration;
	}
    }

}
