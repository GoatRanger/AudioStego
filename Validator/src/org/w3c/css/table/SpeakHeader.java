//
// $Id: SpeakHeader.java,v 1.2 2002/04/08 21:18:23 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */

package org.w3c.css.table;

import org.w3c.css.properties.CssProperty;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;


/**
 */
public class SpeakHeader extends TableProperty {
    
    CssValue value;
    
    private static CssIdent once = new CssIdent("once");
    private static CssIdent always = new CssIdent("always");

    /**
     * Create a new SpeakHeader
     */  
    public SpeakHeader() {
	value = once;
    }
    
    /**
     * Creates a new CssSpeakHeader
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */  
    public SpeakHeader(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val.equals(once)) {
	    value = once;
	} else if (val.equals(always)) {
	    value = always;
	} else {
	    throw new InvalidParamException("value", 
					    val.toString(), 
					    getPropertyName(), ac);
	}

	expression.next();
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return value;
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
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "speak-header";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.speakHeader != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	style0.speakHeader = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getSpeakHeader();
	} else {
	    return ((Css2Style) style).speakHeader;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	if (value == null) {
	    return (property instanceof SpeakHeader && 
		    ((SpeakHeader) property).value == value);
	} else {
	    return (property instanceof SpeakHeader && 
		    ((SpeakHeader) property).value.equals(value));
	}
    }
    
}
