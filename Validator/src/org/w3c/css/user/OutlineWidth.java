//
// $Id: OutlineWidth.java,v 1.2 2002/04/08 21:18:33 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 *
 */
package org.w3c.css.user;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssIdent;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision: 1.2 $
 */
public class OutlineWidth extends UserProperty {
    
    CssValue value;
    
    private static CssIdent thin = new CssIdent("thin");
    private static CssIdent medium = new CssIdent("medium");
    private static CssIdent thick = new CssIdent("thick");
    
    /**
     * Create a new OutlineWidth
     */
    public OutlineWidth() {
	value = medium;
    }
    
    /**
     * Create a new OutlineWidth from an another OutlineWidth
     *
     * @param another The another side.
     */
    public OutlineWidth(OutlineWidth another) {
	value = another.value;
    }
    
    /**
     * Create a new OutlineWidth
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public OutlineWidth(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	
	CssValue val = expression.getValue();
	setByUser();

	if (val instanceof CssLength) {
	    float f = ((Float) val.get()).floatValue();
	    if (f >= 0) {
		this.value = val;
	    } else {
		throw new InvalidParamException("negative-value", 
						val.toString(), ac);
	    }
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	} else if (val.equals(thin)) {
	    value = thin;
	} else if (val.equals(medium)) {
	    value = medium;
	} else if (val.equals(thick)) {
	    value = thick;
	} else if (val.equals(inherit)) {
	    value = CssProperty.inherit;
	} else {
	    throw new InvalidParamException("value", val.toString(), 
					    "width", ac);
	}
	
	expression.next();
    }  
    
    /**
     * Returns the internal value
     */  
    public Object get() {
	return value;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
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
	Outline outline = ((Css2Style) style).outline;
	if (outline.width != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	outline.width = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getOutlineWidth();
	} else {
	    return ((Css2Style) style).outline.width;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof OutlineWidth && 
		value.equals(((OutlineWidth) property).value));
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "outline-width";
    }
    
}



