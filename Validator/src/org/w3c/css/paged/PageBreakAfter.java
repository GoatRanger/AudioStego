//
// $Id: PageBreakAfter.java,v 1.2 2002/04/08 21:17:21 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.paged;
import java.util.Vector;

import org.w3c.css.properties.CssProperty;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssOperator;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision: 1.2 $
 */
public class PageBreakAfter extends CssProperty 
    implements CssOperator {
    
    int value;
    
    private static String PAGEBREAKAFTER[] = {
	"auto", "always", "avoid", "left", "right", "inherit" };
    
    private static int[] hash_values;
    
    
    /**
     * Create a new CssPageBreakAfter
     */
    public PageBreakAfter() {
	value = 0;
    }
    
    /**
     * Create a new CssPageBreakAfter
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public PageBreakAfter(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	CssValue val = expression.getValue();
	char op = expression.getOperator();
	
	setByUser();
		
	if (val instanceof CssIdent) {
	    int hash = val.hashCode();
	    
	    for (int i = 0; i < PAGEBREAKAFTER.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	    }
	}
	
	throw new InvalidParamException("value", 
					val.toString(), getPropertyName(), ac);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return null;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "page-break-after";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == PAGEBREAKAFTER.length - 1;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return PAGEBREAKAFTER[value];
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.pageBreakAfter != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.pageBreakAfter = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getPageBreakAfter();
	} else {
	    return ((Css2Style) style).pageBreakAfter;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof PageBreakAfter 
		&& value == ((PageBreakAfter) property).value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == 0;
    }
    
    static {
	hash_values = new int[PAGEBREAKAFTER.length];
	for (int i=0; i<PAGEBREAKAFTER.length; i++)
	    hash_values[i] = PAGEBREAKAFTER[i].hashCode();
    }
}
