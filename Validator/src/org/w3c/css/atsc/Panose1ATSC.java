//
// $Id: Panose1ATSC.java,v 1.1 2002/07/24 14:42:28 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.atsc;

import java.util.Vector;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssNumber;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 */
public class Panose1ATSC extends CssProperty {
    
    CssValue[] value = new CssValue[10];
    
    /**
     * Create a new Panose1ATSC
     */
    public Panose1ATSC() {
	// nothing to do
    }
    
    /**
     * Creates a new Panose1ATSC
     *
     * @param expression the unicode em
     * @exception InvalidParamException values are incorrect
     */  
    public Panose1ATSC(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val;
	char op;
	int i = 0;
	setByUser();	

	{
	    val = expression.getValue();
	    ac.getFrame().addWarning("atsc", val.toString());
	}

	do {
	    val = expression.getValue();
	    op = expression.getOperator();
	    if (val instanceof CssNumber) {
		value[i++] = val;
		expression.next();
	    } else {
		throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
	    }
	} while (!expression.end() 
		 && (op == CssOperator.SPACE)
		 && (i < 10));

	if (i != 10) {
	    throw new InvalidParamException("few-value", 
					    getPropertyName(), ac);
	}
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return value[0];
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {  
	String ret = "";
	for (int i = 0; i < 10; i++) {
	    ret += " " + value[i];
	}
	return ret.substring(1);
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "panose-1";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	ATSCStyle style0 = (ATSCStyle) style;
	if (style0.panose1ATSC != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.panose1ATSC = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ATSCStyle) style).getPanose1ATSC();
	} else {
	    return ((ATSCStyle) style).panose1ATSC;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	// @@TODO
	return false;
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return false;
    }
    
}
