//
// $Id: CssBorderTopWidth.java,v 1.2 2002/04/08 21:17:43 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderTopWidth.java,v $
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 * Revision 3.2  1997/09/09 08:45:08  plehegar
 * Added getValue()
 *
 * Revision 3.1  1997/08/29 13:13:42  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:20  plehegar
 * Freeze
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'border-top-width'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> thin | medium | thick | &lt;length&gt;<BR>
 *   <EM>Initial:</EM> 'medium'<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property sets the width of an element's top border. The width of the
 *   keyword values are UA dependent, but the following holds: 'thin' &lt;= 'medium'
 *   &lt;= 'thick'.
 *   <P>
 *   The keyword widths are constant throughout a document:
 *   <PRE>
 *   H1 { border: solid thick red }
 *   P  { border: solid thick blue }
 * </PRE>
 *   <P>
 *   In the example above, 'H1' and 'P' elements will have the same border width
 *   regardless of font size. To achieve relative widths, the 'em' unit can be
 *   used:
 *   <PRE>
 *   H1 { border: solid 0.5em }
 * </PRE>
 *   <P>
 *   Border widths cannot be negative.
 * @version $Revision: 1.2 $
 */
public class CssBorderTopWidth extends CssProperty {
    
    CssBorderFaceWidth face;
    
    /**
     * Create a new CssBorderTopWidth
     */
    public CssBorderTopWidth() {
	face = new CssBorderFaceWidth();
    }
    
    /**
     * Create a new CssBorderTopWidth with an another CssBorderFaceWidth
     *
     * @param another The another side.
     */
    public CssBorderTopWidth(CssBorderFaceWidth another) {
	setByUser();
	
	face = another;
    }
    
    /**
     * Create a new CssBorderTopWidth
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderTopWidth(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	
	setByUser();
	face = new CssBorderFaceWidth(ac, expression);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return face;
    }
    
    /**
     * Return the value of this property
     */
    public CssValue getValue() {
	return face.getValue();
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return face.toString();
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "border-top-width";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderTop top = ((Css1Style) style).cssBorder.top;

	if (top.width != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	top.width = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderTopWidth();
	} else {
	    return ((Css1Style) style).cssBorder.getTop().width;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderTopWidth &&
		face.equals(((CssBorderTopWidth) property).face));
    }
    
}
