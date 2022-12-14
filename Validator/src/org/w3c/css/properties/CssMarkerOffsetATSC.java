//
// $Id: CssMarkerOffsetATSC.java,v 1.2 2002/04/08 21:17:44 plehegar Exp $
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
public class CssMarkerOffsetATSC extends CssProperty {
    
    CssValue value;
    
    private static CssIdent auto = new CssIdent("auto");
    
    /**
     * Create a new CssMarkerOffsetATSC
     */
    public CssMarkerOffsetATSC() {
	value = auto;
    }  
    
    /**
     * Create a new CssMarkerOffsetATSC
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssMarkerOffsetATSC(ApplContext ac, CssExpression expression) 
	    throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val instanceof CssLength) {
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
	return "marker-offset";
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
	if (style0.cssMarkerOffsetATSC != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssMarkerOffsetATSC = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getMarkerOffsetATSC();
	} else {
	    return ((Css1Style) style).cssMarkerOffsetATSC;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssMarkerOffsetATSC && 
		value.equals(((CssMarkerOffsetATSC) property).value));
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == auto;
    }
    
}
