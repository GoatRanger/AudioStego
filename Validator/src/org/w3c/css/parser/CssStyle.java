//
// $Id: CssStyle.java,v 1.3 2003/04/13 15:30:16 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssStyle.java,v $
 * Revision 1.3  2003/04/13 15:30:16  sijtsche
 * addToStyle invocation commented out, obsolete
 *
 * Revision 1.2  2002/04/08 21:24:12  plehegar
 * New
 *
 */
package org.w3c.css.parser;

import java.util.Enumeration;
import org.w3c.css.util.Warnings;
import org.w3c.css.util.Warning;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.css.StyleSheet;

/**
 * This class represents a class for one context
 *
 * @version $Revision: 1.3 $
 */
public class CssStyle {

  /**
   * For warnings report.
   */
  protected Warnings warnings;

  /**
   * The entire style sheet.
   */
  protected StyleSheet style;

  /**
   * The context of this style.
   */
  protected CssSelectors selector;

  /**
   * Set the context of this style.
   *
   * @param selectors The context.
   */
  public final void setSelector(CssSelectors selectors) {
      this.selector = selectors;
  }

  /**
   * Set the style sheet of this style.
   *
   * @param style The style sheet.
   */
  public final void setStyleSheet(StyleSheet style) {
    this.style = style;
  }

  /**
   * Add a warning definition to this style.
   *
   * @param property The property.
   */
  public final void addRedefinitionWarning(ApplContext ac,
					   CssProperty property) {
    warnings.addWarning(new Warning(property, "redefinition", 2, ac));
  }

  /**
   * Add a property to this style
   *
   * @param property the property to add
   * @param warnings where to add warnings if required
   */
  public final void setProperty(ApplContext ac, CssProperty property, Warnings warnings) {
    this.warnings = warnings;
    //property.addToStyle(ac, this);
  }

  /**
   * Print this style.
   * Overrides this method to create your own style.
   *
   * @param printer The printer interface.
   */
  public void print(CssPrinterStyle printer) {
    // nothing to do
  }

  /**
   * Find conflicts in this Style
   *
   * @param warnings For warnings reports.
   * @param allSelectors All contexts is the entire style sheet.
   */
  public void findConflicts(ApplContext ac, Warnings warnings,
			    Enumeration allSelectors) {
    // nothing to do
  }
}
