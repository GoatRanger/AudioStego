//
// $Id: CssBackground.java,v 1.3 2002/07/22 09:08:52 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBackground.java,v $
 * Revision 1.3  2002/07/22 09:08:52  sijtsche
 * shorthand for background-size added
 *
 * Revision 1.2  2002/05/22 15:04:13  dejong
 * new property background-size added
 *
 * Revision 3.2  1997/09/09 13:03:45  plehegar
 * Added getColor()
 *
 * Revision 3.1  1997/08/29 13:13:28  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 13:55:55  plehegar
 * Added setSelectors()
 *
 * Revision 2.2  1997/08/20 11:41:11  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:01  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:29:45  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:19:43  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/23 21:17:04  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties3.CssBackgroundSize;

/**
 *   <H4>
 *     <A NAME="background">5.3.7 &nbsp;&nbsp; 'background'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;background-color&gt; || &lt;background-image&gt; ||
 *   &lt;background-repeat&gt; || &lt;background-attachment&gt; ||
 *   &lt;background-position&gt;<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> allowed on &lt;background-position&gt;<BR>
 *   <P>
 *   The 'background' property is a shorthand property for setting the individual
 *   background properties (i.e., 'background-color', 'background-image',
 *   'background-repeat', 'background-attachment' and 'background-position') at
 *   the same place in the style sheet.
 *   <P>
 *   Possible values on the 'background' properties are the set of all possible
 *   values on the individual properties.
 *   <PRE>
 *   BODY { background: red }
 *   P { background: url(chess.png) gray 50% repeat fixed }
 * </PRE>
 *   <P> The 'background' property always sets all the individual background
 *   properties.  In the first rule of the above example, only a value for
 *   'background-color' has been given and the other individual properties are
 *   set to their initial value. In the second rule, all individual properties
 *   have been specified.
 *
 * @version $Revision: 1.3 $
 * @see CssBackgroundColor
 * @see CssBackgroundImage
 * @see CssBackgroundRepeat
 * @see CssBackgroundAttachment
 * @see CssBackgroundPosition
 */
public class CssBackground extends CssProperty
        implements CssOperator, CssBackgroundConstants {

    CssBackgroundColor color;
    CssBackgroundImage image;
    CssBackgroundRepeat repeat;
    CssBackgroundAttachment attachment;
    CssBackgroundPosition position;
	CssBackgroundSize size;
	boolean sizedefined;

    boolean same;

    /**
     * Create a new CssBackground
     */
    public CssBackground() {
    }

    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBackground(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;
	sizedefined = false;
	setByUser();

	if (val.equals(inherit)) {
	    color = new CssBackgroundColor();
	    color.color = inherit;
	    image = new CssBackgroundImage();
	    image.url = inherit;
	    repeat = new CssBackgroundRepeat();
	    repeat.repeat = REPEAT.length - 1;
	    attachment = new CssBackgroundAttachment();
	    attachment.attachment = ATTACHMENT.length - 1;
	    position = new CssBackgroundPosition();
	    position.horizontal = inherit;
	    position.vertical = inherit;
		size = new CssBackgroundSize();
		size.value1 = inherit;
	    same = true;
	    expression.next();
	    return;
	}

	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();

	    if (val == null) {
			break;
	    }

	    if (color == null) {
			try {
			    color = new CssBackgroundColor(ac, expression);
			    find = true;
			} catch (InvalidParamException e) {}
	    }
	    if (!find && image == null) {
			try {
			    image = new CssBackgroundImage(ac, expression);
			    find = true;
			} catch (InvalidParamException e) {}
	    }
	    if (!find && repeat == null) {
			try {
			    repeat = new CssBackgroundRepeat(ac, expression);
			    find = true;
			} catch (InvalidParamException e) {}
	    }
	    if (!find && attachment == null) {
			try {
			    attachment = new CssBackgroundAttachment(ac, expression);
			    find = true;
			} catch (InvalidParamException e) {}
	    }
	    if (!find && position == null) {
			try {
			    position = new CssBackgroundPosition(ac, expression);
			    find = true;
			} catch (InvalidParamException e) {}
	    }
	    if (op != SPACE) {

		    if (op != SLASH) {
				throw new InvalidParamException("operator",
							((new Character(op)).toString()),
							ac);
		    } else {
				//try {
					size = new CssBackgroundSize(ac, expression);
					sizedefined = true;
					break;
				//} catch (InvalidParamException e) {
					// error!
				//}
			}
	    }
	}

	if (color == null)
	    color = new CssBackgroundColor();
	if (image == null)
	    image = new CssBackgroundImage();
	if (repeat == null)
	    repeat = new CssBackgroundRepeat();
	if (attachment == null)
	    attachment = new CssBackgroundAttachment();
	if (position == null)
	    position = new CssBackgroundPosition();
    if (!sizedefined)
    	size = new CssBackgroundSize();
    }


    /**
     * Returns the value of this property
     */
    public Object get() {
	return color;
    }

    /**
     * Returns the color
     */
    public final CssValue getColor() {
	if (color == null) {
	    return null;
	} else {
	    return color.getColor();
	}
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "background";
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (same) {
	    return inherit.toString();
	} else {
	    String ret = "";
	    if (color.byUser)
		ret += " " + color.toString();
	    if (image.byUser)
		ret += " " + image.toString();
	    if (image.byUser)
		ret += " " + repeat.toString();
	    if (attachment.byUser)
		ret += " " + attachment.toString();
	    if (position.byUser)
			ret += " " + position.toString();
	    if (sizedefined)
			ret += "/" + size.toString();
		return ret.trim();
	}
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	color.important = true;
	image.important = true;
	repeat.important = true;
	attachment.important = true;
	position.important = true;
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((color == null || color.important) &&
		(image == null || image.important) &&
		(repeat == null || repeat.important) &&
		(attachment == null || attachment.important) &&
		(position == null || position.important));
    }

    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */
    public void print(CssPrinterStyle printer) {
	if ((color != null && image != null &&
	     repeat != null && attachment !=null &&
	     position != null) &&
	    (getImportant() ||
	     (!image.important &&
	      !color.important &&
	      !repeat.important &&
	      !attachment.important &&
	      !position.important))) {
	    if (color.byUser || image.byUser || repeat.byUser
		|| attachment.byUser || position.byUser) {
		printer.print(this);
	    }
	} else {
	    if (color != null)
		color.print(printer);
	    if (image != null)
		image.print(printer);
	    if (repeat != null)
		repeat.print(printer);
	    if (attachment != null)
		attachment.print(printer);
	    if (position != null)
		position.print(printer);
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
	if (color != null) {
	    color.setSelectors(selector);
	}
	if (image != null) {
	    image.setSelectors(selector);
	}
	if (repeat != null) {
	    repeat.setSelectors(selector);
	}
	if (attachment != null) {
	    attachment.setSelectors(selector);
	}
	if (position != null) {
	    position.setSelectors(selector);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	((Css1Style) style).cssBackground.same = same;
	((Css1Style) style).cssBackground.byUser = byUser;

	color.addToStyle(ac, style);
	image.addToStyle(ac, style);
	repeat.addToStyle(ac, style);
	attachment.addToStyle(ac, style);
	position.addToStyle(ac, style);
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBackground();
	} else {
	    return ((Css1Style) style).cssBackground;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return false; // FIXME
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
	color.setInfo(line, source);
	image.setInfo(line, source);
	repeat.setInfo(line, source);
	attachment.setInfo(line, source);
	position.setInfo(line, source);
    }

}
