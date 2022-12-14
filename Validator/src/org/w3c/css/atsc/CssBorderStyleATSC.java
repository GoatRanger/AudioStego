//
// $Id: CssBorderStyleATSC.java,v 1.1 2002/07/24 14:42:28 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderStyleATSC.java,v $
 * Revision 1.1  2002/07/24 14:42:28  sijtsche
 * ATSC TV profile files
 *
 * Revision 1.1  2002/05/31 09:00:16  dejong
 * ATSC TV profile objects
 *
 * Revision 3.1  1997/08/29 13:13:40  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 13:59:15  plehegar
 * Added setSelectors()
 *
 * Revision 2.2  1997/08/20 11:41:19  plehegar
 * Freeze
 *
 */

package org.w3c.css.atsc;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'border-style'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> none | dotted | dashed | solid | double | groove | ridge
 *   | inset | outset<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   The 'border-style' property sets the style of the four borders. It can have
 *   from one to four values, and the values are set on the different sides as
 *   for 'border-width' above.
 *   <PRE>
 *   #xy34 { border-style: solid dotted }
 * </PRE>
 *   <P>
 *   In the above example, the horizontal borders will be 'solid' and the vertical
 *   borders will be 'dotted'.
 *   <P>
 *   Since the initial value of the border styles is 'none', no borders will be
 *   visible unless the border style is set.
 *   <P>
 *   The border styles mean:
 *   <DL>
 *     <DT>
 *       none
 *     <DD>
 *       no border is drawn (regardless of the 'border-width' value)
 *     <DT>
 *       dotted
 *     <DD>
 *       the border is a dotted line drawn on top of the background of the element
 *     <DT>
 *       dashed
 *     <DD>
 *       the border is a dashed line drawn on top of the background of the element
 *     <DT>
 *       solid
 *     <DD>
 *       the border is a solid line
 *     <DT>
 *       double
 *     <DD> the border is a double line drawn on top of the background of the
 *     element.  The sum of the two single lines and the space between equals
 *     the &lt;border-width&gt; value.
 *     <DT>
 *       groove
 *     <DD>
 *       a 3D groove is drawn in colors based on the &lt;color&gt; value.
 *     <DT>
 *       ridge
 *     <DD>
 *       a 3D ridge is drawn in colors based on the &lt;color&gt; value.
 *     <DT>
 *       inset
 *     <DD>
 *       a 3D inset is drawn in colors based on the &lt;color&gt; value.
 *     <DT>
 *       outset
 *     <DD>
 *       a 3D outset is drawn in colors based on the &lt;color&gt; value.
 *   </DL>
 *   <P>
 *   <EM>CSS1 core:</EM> UAs may interpret all of 'dotted', 'dashed', 'double',
 *   'groove', 'ridge', 'inset' and 'outset' as 'solid'.
 *
 * @version $Revision: 1.1 $ 
 */
public class CssBorderStyleATSC extends CssProperty implements CssOperator {

  CssBorderTopStyleATSC top;
  CssBorderBottomStyleATSC bottom;
  CssBorderRightStyleATSC right;
  CssBorderLeftStyleATSC left;

  /**
   * Create a new CssBorderStyleATSC
   */
  public CssBorderStyleATSC(CssBorderTopStyleATSC top,
			CssBorderBottomStyleATSC bottom,
			CssBorderRightStyleATSC right,
			CssBorderLeftStyleATSC left) {
    this.top = top;
    this.bottom = bottom;
    this.left = left;
    this.right = right;
  }  

  /**
   * Create a new CssBorderStyleATSC
   *
   * @param expression The expression for this property
   * @exception InvalidParamException Values are incorrect
   */  
  public CssBorderStyleATSC(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();

    switch (expression.getCount()) {
    case 1:
      top = new CssBorderTopStyleATSC(ac, expression);
      bottom = new CssBorderBottomStyleATSC((CssBorderFaceStyleATSC) top.get());
      right = new CssBorderRightStyleATSC((CssBorderFaceStyleATSC) top.get());
      left = new CssBorderLeftStyleATSC((CssBorderFaceStyleATSC) top.get());
      break;
    case 2:
      if (expression.getOperator() != SPACE)
	throw new InvalidParamException("operator", 
					((new Character(expression.getOperator())).toString()),
					ac);
      top = new CssBorderTopStyleATSC(ac, expression);
      right = new CssBorderRightStyleATSC(ac, expression);
      bottom = new CssBorderBottomStyleATSC((CssBorderFaceStyleATSC) top.get());
      left = new CssBorderLeftStyleATSC((CssBorderFaceStyleATSC) right.get());
      break;
    case 3:
      if (expression.getOperator() != SPACE)
	throw new InvalidParamException("operator", 
					((new Character(expression.getOperator())).toString()),
					ac);
      top = new CssBorderTopStyleATSC(ac, expression);
      if (expression.getOperator() != SPACE)
	throw new InvalidParamException("operator", 
					((new Character(expression.getOperator())).toString()),
					ac);
      right = new CssBorderRightStyleATSC(ac, expression);
      bottom = new CssBorderBottomStyleATSC(ac, expression);
      left = new CssBorderLeftStyleATSC((CssBorderFaceStyleATSC) right.get());
      break;
    default:
      if (expression.getOperator() != SPACE)
	throw new InvalidParamException("operator", 
					((new Character(expression.getOperator())).toString()),
					ac);
      top = new CssBorderTopStyleATSC(ac, expression);
      if (expression.getOperator() != SPACE)
	throw new InvalidParamException("operator", 
					((new Character(expression.getOperator())).toString()),
					ac);
      right = new CssBorderRightStyleATSC(ac, expression);
      if (expression.getOperator() != SPACE)
	throw new InvalidParamException("operator", 
					((new Character(expression.getOperator())).toString()),
					ac);
      bottom = new CssBorderBottomStyleATSC(ac, expression);
      left = new CssBorderLeftStyleATSC(ac, expression);
      break;
    }
  }

  /**
   * Returns the value of this property
   */
  public Object get() {
    return top;
  }

  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "border-style";
  }

  /**
   * Returns a string representation of the object.
   */
  public String toString() {
    if (right.face.equals(left.face)) {
      if (top.face.equals(bottom.face)) {
	if (top.face.equals(right.face)) {
	  return top.toString();
	} else {
	  return top + " " + right;
	}
      } else {
	return top + " " + right + " " + bottom;
      }
    } else {
      return top + " " + right + " " + bottom + " " + left;
    }
  }

  /**
   * Set this property to be important.
   * Overrides this method for a macro
   */  
  public void setImportant() {
    top.setImportant();
    right.setImportant();
    left.setImportant();
    bottom.setImportant();
  }

  /**
   * Returns true if this property is important.
   * Overrides this method for a macro
   */
  public boolean getImportant() {
    return ((top == null || top.getImportant()) &&
	    (right == null || right.getImportant()) &&
	    (left == null || left.getImportant()) &&
	    (bottom == null || bottom.getImportant()));
  }

  /**
   * Print this property.
   *
   * @param printer The printer.
   * @see #toString()
   * @see #getPropertyName()
   */  
  public void print(CssPrinterStyle printer) {
    if ((top != null && right != null &&
	  left != null && bottom != null) &&
	(getImportant() ||
	 (!top.getImportant() &&
	  !right.getImportant() &&
	  !left.getImportant() &&
	  !bottom.getImportant()))) {
      printer.print(this);
    } else {
      if (top != null)
	top.print(printer);
      if (right != null)
	right.print(printer);
      if (left != null)
	left.print(printer);
      if (bottom != null)
	bottom.print(printer);
    }
	
  }

  /**
   * Set the context.
   * Overrides this method for a macro
   *
   * @see org.w3c.css.css.CssCascadingOrder#order
   * @see org.w3c.css.css.StyleSheetParser#handleRule
   */
  public void setSelectors(CssSelectors selector) {
    super.setSelectors(selector);
    if (top != null) {
      top.setSelectors(selector);
    }
    if (right != null) {
      right.setSelectors(selector);
    }
    if (bottom != null) {
      bottom.setSelectors(selector);
    }
    if (left != null) {
      left.setSelectors(selector);
    }
  }
  
  /**
   * Add this property to the CssStyle
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    top.addToStyle(ac, style);
    right.addToStyle(ac, style);
    left.addToStyle(ac, style);
    bottom.addToStyle(ac, style);
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    throw new IllegalStateException("Can't invoke this method on the property " + 
				    getPropertyName());
  }

  /**
   * Update the source file and the line.
   * Overrides this method for a macro
   *
   * @param line The line number where this property is defined
   * @param source The source file where this property is defined
   */  
  public void setInfo(int line, String source) {
    super.setInfo(line, source);
    top.setInfo(line, source);
    right.setInfo(line, source);
    left.setInfo(line, source);
    bottom.setInfo(line, source);
  }
  
  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return false;
  }

}
