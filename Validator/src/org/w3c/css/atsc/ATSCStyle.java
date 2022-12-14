//
// $Id: ATSCStyle.java,v 1.2 2003/01/08 10:59:44 sijtsche Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.atsc;

import java.util.Enumeration;

import org.w3c.css.util.Warnings;
import org.w3c.css.parser.CssPrinterStyle;

/**
 * @version $Revision: 1.2 $
 */
public class ATSCStyle extends org.w3c.css.font.Css2Style {

    ATSCNavIndex navindex;
    ATSCNavLeft navleft;
    ATSCNavUp navup;
    ATSCNavDown navdown;
    ATSCNavRight navright;
    ATSCDynamicRefresh dynamicRefresh;

    UnitsPerEmATSC unitsPerEmATSC;
    SrcATSC srcATSC;
    Panose1ATSC panose1ATSC;
    BboxATSC bboxATSC;
    WidthsATSC widthsATSC;
    StemvATSC stemvATSC;
    StemhATSC stemhATSC;
    SlopeATSC slopeATSC;
    CapHeightATSC capHeightATSC;
    XHeightATSC xHeightATSC;
    AscentATSC ascentATSC;
    DescentATSC descentATSC;
    BaselineATSC baselineATSC;
    CenterlineATSC centerlineATSC;
    MathlineATSC mathlineATSC;
    ToplineATSC toplineATSC;
    DefinitionSrcATSC definitionSrcATSC;
    ATSCColor ATSCcolor;
    CssBackgroundColorATSC cssBackgroundColorATSC;
    CssBackgroundImageATSC cssBackgroundImageATSC;
    CssBackgroundRepeatATSC cssBackgroundRepeatATSC;
    CssBackgroundAttachmentATSC cssBackgroundAttachmentATSC;
    CssBackgroundPositionATSC cssBackgroundPositionATSC;
    CssBackgroundATSC cssBackgroundATSC;
    protected CssBorderATSC cssBorderATSC = new CssBorderATSC();


    /**
     * Get the atsc-nav-index property
     */
    public final ATSCNavIndex getNavIndex() {
	return navindex;
    }

    /**
     * Get the atsc-nav-index property
     */
    public final ATSCDynamicRefresh getDynamicRefresh() {
		return dynamicRefresh;
    }


    /**
     * Get the atsc-nav-left property
     */
    public final ATSCNavLeft getNavLeft() {
	return navleft;
    }

    /**
     * Get the atsc-nav-up property
     */
    public final ATSCNavUp getNavUp() {
	return navup;
    }

    /**
     * Get the atsc-nav-down property
     */
    public final ATSCNavDown getNavDown() {
	return navdown;
    }

    /**
     * Get the atsc-nav-right property
     */
    public final ATSCNavRight getNavRight() {
	return navright;
    }

    public final UnitsPerEmATSC getUnitsPerEmATSC() {
	return unitsPerEmATSC;
    }

    public final StemvATSC getStemvATSC() {
	return stemvATSC;
    }

    public final SrcATSC getSrcATSC() {
	return srcATSC;
    }

    public final Panose1ATSC getPanose1ATSC() {
	return panose1ATSC;
    }

    public final WidthsATSC getWidthsATSC() {
        return widthsATSC;
    }

    public final BboxATSC getBboxATSC() {
	return bboxATSC;
    }

    public final StemhATSC getStemhATSC() {
	return stemhATSC;
    }

    public final SlopeATSC getSlopeATSC() {
        return slopeATSC;
    }

    public final AscentATSC getAscentATSC() {
	return ascentATSC;
    }

    public final DescentATSC getDescentATSC() {
	return descentATSC;
    }

    public final CapHeightATSC getCapHeightATSC() {
        return capHeightATSC;
    }

    public final XHeightATSC getXHeightATSC() {
        return xHeightATSC;
    }

    public final BaselineATSC getBaselineATSC() {
	return baselineATSC;
    }

    public final CenterlineATSC getCenterlineATSC() {
	return centerlineATSC;
    }

    public final MathlineATSC getMathlineATSC() {
	return mathlineATSC;
    }

    public final ToplineATSC getToplineATSC() {
	return toplineATSC;
    }

    public final DefinitionSrcATSC getDefinitionSrcATSC() {
        return definitionSrcATSC;
    }

    public final ATSCColor getColorATSC() {
	return ATSCcolor;
    }

    public final CssBackgroundColorATSC getBackgroundColorATSC() {
	if (cssBackgroundATSC.color == null) {
	    cssBackgroundATSC.color =
		(CssBackgroundColorATSC) style.CascadingOrder(
					     new CssBackgroundColorATSC(),
					     style, selector);
	}
	return cssBackgroundATSC.color;
    }

    public final CssBackgroundImageATSC getBackgroundImageATSC() {
	if (cssBackgroundATSC.image == null) {
	    cssBackgroundATSC.image =
		(CssBackgroundImageATSC) style.CascadingOrder(new CssBackgroundImageATSC(),
							  style, selector);
	}
	return cssBackgroundATSC.image;
    }

    public final CssBackgroundRepeatATSC getBackgroundRepeatATSC() {
	if (cssBackgroundATSC.repeat == null) {
	    cssBackgroundATSC.repeat =
		(CssBackgroundRepeatATSC) style.CascadingOrder(new CssBackgroundRepeatATSC(),
							   style, selector);
	}
	return cssBackgroundATSC.repeat;
    }

    public final CssBackgroundAttachmentATSC getBackgroundAttachmentATSC() {
	if (cssBackgroundATSC.attachment == null) {
	    cssBackgroundATSC.attachment =
		(CssBackgroundAttachmentATSC) style.CascadingOrder(new CssBackgroundAttachmentATSC(),
							       style, selector);
	}
	return cssBackgroundATSC.attachment;
    }

    public final CssBackgroundPositionATSC getBackgroundPositionATSC() {
	if (cssBackgroundATSC.position == null) {
	    cssBackgroundATSC.position =
		(CssBackgroundPositionATSC) style.CascadingOrder(new CssBackgroundPositionATSC(),
							     style, selector);
	}
	return cssBackgroundATSC.position;
    }

    public final CssBackgroundATSC getBackgroundATSC() {
	if (cssBackgroundATSC.getColor() == null) {
	    cssBackgroundATSC.color = getBackgroundColorATSC();
	}
	if (cssBackgroundATSC.image == null) {
	    cssBackgroundATSC.image = getBackgroundImageATSC();
	}
	if (cssBackgroundATSC.repeat == null) {
	    cssBackgroundATSC.repeat = getBackgroundRepeatATSC();
	}
	if (cssBackgroundATSC.attachment == null) {
	    cssBackgroundATSC.attachment = getBackgroundAttachmentATSC();
	}
	if (cssBackgroundATSC.position == null) {
	    cssBackgroundATSC.position = getBackgroundPositionATSC();
	}
	return cssBackgroundATSC;
    }

    public final CssBorderTopWidthATSC getBorderTopWidthATSC() {
	if (cssBorderATSC.getTop().getWidth() == null) {
	    cssBorderATSC.getTop().width =
		(CssBorderTopWidthATSC) style.CascadingOrder(new CssBorderTopWidthATSC(),
							 style, selector);
	}
	return cssBorderATSC.getTop().width;
    }

    public final CssBorderTopStyleATSC getBorderTopStyleATSC() {
	if (cssBorderATSC.getTop().getStyle() == null) {
	    cssBorderATSC.getTop().style =
		(CssBorderTopStyleATSC) style.CascadingOrder(new CssBorderTopStyleATSC(),
							 style, selector);
	}
	return cssBorderATSC.getTop().style;
    }

    public final CssBorderTopColorATSC getBorderTopColorATSC() {
	if (cssBorderATSC.getTop().getColor() == null) {
	    cssBorderATSC.getTop().color =
		(CssBorderTopColorATSC) style.CascadingOrder(new CssBorderTopColorATSC(),
							 style, selector);
	}
	return cssBorderATSC.getTop().color;
    }

    public final CssBorderRightWidthATSC getBorderRightWidthATSC() {
	if (cssBorderATSC.getRight().getWidth() == null) {
	    cssBorderATSC.getRight().width =
		(CssBorderRightWidthATSC) style.CascadingOrder(new CssBorderRightWidthATSC(),
							   style, selector);
	}
	return cssBorderATSC.getRight().width;
    }

    public final CssBorderRightStyleATSC getBorderRightStyleATSC() {
	if (cssBorderATSC.getRight().getStyle() == null) {
	    cssBorderATSC.getRight().style =
		(CssBorderRightStyleATSC) style.CascadingOrder(new CssBorderRightStyleATSC(),
							   style, selector);
	}
	return cssBorderATSC.getRight().style;
    }

    public final CssBorderRightColorATSC getBorderRightColorATSC() {
	if (cssBorderATSC.getRight().getColor() == null) {
	    cssBorderATSC.getRight().color =
		(CssBorderRightColorATSC) style.CascadingOrder(new CssBorderRightColorATSC(),
							   style, selector);
	}
	return cssBorderATSC.getRight().color;
    }

    public final CssBorderBottomWidthATSC getBorderBottomWidthATSC() {
	if (cssBorderATSC.getBottom().getWidth() == null) {
	    cssBorderATSC.getBottom().width =
		(CssBorderBottomWidthATSC) style.CascadingOrder(new CssBorderBottomWidthATSC(),
							    style, selector);
	}
	return cssBorderATSC.getBottom().width;
    }

    public final CssBorderBottomStyleATSC getBorderBottomStyleATSC() {
	if (cssBorderATSC.getBottom().getStyle() == null) {
	    cssBorderATSC.getBottom().style =
		(CssBorderBottomStyleATSC) style.CascadingOrder(new CssBorderBottomStyleATSC(),
							    style, selector);
	}
	return cssBorderATSC.getBottom().style;
    }

    public final CssBorderBottomColorATSC getBorderBottomColorATSC() {
	if (cssBorderATSC.getBottom().getColor() == null) {
	    cssBorderATSC.getBottom().color =
		(CssBorderBottomColorATSC) style.CascadingOrder(new CssBorderBottomColorATSC(),
							    style, selector);
	}
	return cssBorderATSC.getBottom().color;
    }

    public final CssBorderLeftWidthATSC getBorderLeftWidthATSC() {
	if (cssBorderATSC.getLeft().getWidth() == null) {
	    cssBorderATSC.getLeft().width =
		(CssBorderLeftWidthATSC) style.CascadingOrder(new CssBorderLeftWidthATSC(),
							  style, selector);
	}
	return cssBorderATSC.getLeft().width;
    }

    public final CssBorderLeftStyleATSC getBorderLeftStyleATSC() {
	if (cssBorderATSC.getLeft().getStyle() == null) {
	    cssBorderATSC.getLeft().style =
		(CssBorderLeftStyleATSC) style.CascadingOrder(new CssBorderLeftStyleATSC(),
							  style, selector);
	}
	return cssBorderATSC.getLeft().style;
    }

    public final CssBorderLeftColorATSC getBorderLeftColorATSC() {
	if (cssBorderATSC.getLeft().getColor() == null) {
	    cssBorderATSC.getLeft().color =
		(CssBorderLeftColorATSC) style.CascadingOrder(new CssBorderLeftColorATSC(),
							  style, selector);
	}
	return cssBorderATSC.getLeft().color;
    }

    public final CssBorderTopATSC getBorderTopATSC() {
	if (cssBorderATSC.getTop().getWidth() == null) {
	    cssBorderATSC.getTop().width = getBorderTopWidthATSC();
	}
	if (cssBorderATSC.getTop().getStyle() == null) {
	    cssBorderATSC.getTop().style = getBorderTopStyleATSC();
	}
	if (cssBorderATSC.getTop().getColor() == null) {
	    cssBorderATSC.getTop().color = getBorderTopColorATSC();
	}
	return cssBorderATSC.getTop();
    }

    public final CssBorderRightATSC getBorderRightATSC() {
	if (cssBorderATSC.getRight().getWidth() == null) {
	    cssBorderATSC.getRight().width = getBorderRightWidthATSC();
	}
	if (cssBorderATSC.getRight().getStyle() == null) {
	    cssBorderATSC.getRight().style = getBorderRightStyleATSC();
	}
	if (cssBorderATSC.getRight().getColor() == null) {
	    cssBorderATSC.getRight().color = getBorderRightColorATSC();
	}
	return cssBorderATSC.getRight();
    }

    public final CssBorderBottomATSC getBorderBottomATSC() {
	if (cssBorderATSC.getBottom().getWidth() == null) {
	    cssBorderATSC.getBottom().width = getBorderBottomWidthATSC();
	}
	if (cssBorderATSC.getBottom().getStyle() == null) {
	    cssBorderATSC.getBottom().style = getBorderBottomStyleATSC();
	}
	if (cssBorderATSC.getBottom().getColor() == null) {
	    cssBorderATSC.getBottom().color = getBorderBottomColorATSC();
	}
	return cssBorderATSC.getBottom();
    }

    public final CssBorderLeftATSC getBorderLeftATSC() {
	if (cssBorderATSC.getLeft().getWidth() == null) {
	    cssBorderATSC.getLeft().width = getBorderLeftWidthATSC();
	}
	if (cssBorderATSC.getLeft().getStyle() == null) {
	    cssBorderATSC.getLeft().style = getBorderLeftStyleATSC();
	}
	if (cssBorderATSC.getLeft().getColor() == null) {
	    cssBorderATSC.getLeft().color = getBorderLeftColorATSC();
	}
	return cssBorderATSC.getLeft();
    }

    public final CssBorderATSC getBorderATSC() {
	getBorderTopATSC();
	getBorderRightATSC();
	getBorderBottomATSC();
	getBorderLeftATSC();
	return cssBorderATSC;
    }

    public final CssBorderWidthATSC getBorderWidthATSC() {
	// WARNING invalid fields in this property ....
	return new CssBorderWidthATSC(getBorderTopWidthATSC(),
				  getBorderBottomWidthATSC(),
				  getBorderRightWidthATSC(),
				  getBorderLeftWidthATSC());
    }

    public final CssBorderStyleATSC getBorderStyleATSC() {
	// WARNING invalid fields in this property ....
	return new CssBorderStyleATSC(getBorderTopStyleATSC(),
				  getBorderBottomStyleATSC(),
				  getBorderRightStyleATSC(),
				  getBorderLeftStyleATSC());
    }

    public final CssBorderColorATSC getBorderColorATSC() {
	// WARNING invalid fields in this property ....
	return new CssBorderColorATSC(getBorderTopColorATSC(),
				  getBorderBottomColorATSC(),
				  getBorderRightColorATSC(),
				  getBorderLeftColorATSC());
    }



    /**
     * Print this style.
     *
     * @param printer The printer interface.
     */
    public void print(CssPrinterStyle printer) {
	super.print(printer);
	if (navindex != null) {
	    navindex.print(printer);
	}
	if (navleft != null) {
	    navleft.print(printer);
	}
	if (navup != null) {
	    navup.print(printer);
	}
	if (navdown != null) {
	    navdown.print(printer);
	}
	if (navright != null) {
	    navright.print(printer);
	}
	if (unitsPerEmATSC != null) {
	    unitsPerEmATSC.print(printer);
	}
	if (srcATSC != null) {
	    srcATSC.print(printer);
	}
	if (panose1ATSC != null) {
	    panose1ATSC.print(printer);
	}
	if (widthsATSC != null) {
	    widthsATSC.print(printer);
	}
	if (bboxATSC != null) {
	    bboxATSC.print(printer);
	}
	if (stemvATSC != null) {
	    stemvATSC.print(printer);
	}
	if (stemhATSC != null) {
	    stemhATSC.print(printer);
	}
	if (slopeATSC != null) {
	    slopeATSC.print(printer);
	}
	if (ascentATSC != null) {
	    ascentATSC.print(printer);
	}
	if (descentATSC != null) {
	    descentATSC.print(printer);
	}
	if (capHeightATSC != null) {
	    capHeightATSC.print(printer);
	}
	if (xHeightATSC != null) {
	    xHeightATSC.print(printer);
	}
	if (baselineATSC != null) {
	    baselineATSC.print(printer);
	}
	if (centerlineATSC != null) {
	    centerlineATSC.print(printer);
	}
	if (mathlineATSC != null) {
	    mathlineATSC.print(printer);
	}
	if (toplineATSC != null) {
	    toplineATSC.print(printer);
	}
	if (definitionSrcATSC != null) {
	    definitionSrcATSC.print(printer);
	}
	if (ATSCcolor != null) {
	    ATSCcolor.print(printer);
	}
	if (dynamicRefresh != null) {
		dynamicRefresh.print(printer);
	}
	cssBackgroundATSC.print(printer);
	cssBorderATSC.print(printer);
    }


}
