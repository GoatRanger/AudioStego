//
// $Id: CssBorderRightColor.java,v 1.2 2002/04/08 21:17:43 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderRightColor.java,v $
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 * Revision 3.3  1997/09/09 10:52:03  plehegar
 * bugs
 *
 * Revision 3.2  1997/09/09 10:51:43  plehegar
 * Added getColor()
 *
 * Revision 3.1  1997/08/29 13:13:38  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:19  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision: 1.2 $
 */
public class CssBorderRightColor extends CssProperty {

  CssBorderFaceColor face;
  
  /**
   * Create a new CssBorderRightColor
   */
  public CssBorderRightColor() {
    face = new CssBorderFaceColor();
  }
  
  /**
   * Create a new CssBorderRightColor with an another CssBorderFaceColor
   *
   * @param anothewr The another side.
   */
  public CssBorderRightColor(CssBorderFaceColor another) {

      setByUser();

    face = another;
  }
  
  /**
   * Create a new CssBorderRightColor.
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssBorderRightColor(ApplContext ac, CssExpression expression) 
    throws InvalidParamException {

	setByUser();
    face = new CssBorderFaceColor(ac, expression);
  }
  
  /**
   * Returns the value of this property
   */
  public Object get() {
    return face;
  }

  /**
   * Returns the color of this property
   */
  public CssValue getColor() {
    return face.getColor();
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
    return "border-right-color";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    CssBorderRight right = ((Css1Style) style).cssBorder.right;
    if (right.color != null)
      style.addRedefinitionWarning(ac, this);
    right.color = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getBorderRightColor();
    } else {
      return ((Css1Style) style).cssBorder.getRight().color;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssBorderRightColor && 
	    face.equals(((CssBorderRightColor) property).face));
  }

  /**
   * Print this property.
   *
   * @param printer The printer.
   */  
  public void print(CssPrinterStyle printer) {
    if (!face.isDefault())
      printer.print(this);
  }
}
