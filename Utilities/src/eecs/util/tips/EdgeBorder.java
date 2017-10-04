/*
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.util.tips;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * A border used in the JTips dialog.
 * Code taken from Java Developer's Journal, Volume 3, Issue 11
 * 
 * @author Claude Duguay
 *
 */
public class EdgeBorder implements Border, SwingConstants 
{ 
  public static final int RAISED = 1; 
  public static final int LOWERED = 2; 

  protected int edge = NORTH; 
  protected int lift = LOWERED; 

  public EdgeBorder() 
  { 
    this(NORTH); 
  } 

  public EdgeBorder(int edge) 
  { 
    this.edge = edge; 
  } 

  public Insets getBorderInsets(Component component) 
  { 
    switch (edge) 
    { 
      case SOUTH: return new Insets(0, 0, 2, 0); 
      case EAST: return new Insets(0, 2, 0, 0); 
      case WEST: return new Insets(0, 0, 0, 2); 
      default: return new Insets(2, 0, 0, 0); 
    } 
  } 

  public boolean isBorderOpaque() 
  { 
    return true; 
  } 

  public void paintBorder(Component component, 
    Graphics g, int x, int y, int w, int h) 
  { 
    if (lift == RAISED) 
      g.setColor(component.getBackground().brighter()); 
    else 
      g.setColor(component.getBackground().darker()); 
    switch (edge) 
    { 
      case SOUTH: 
        g.drawLine(x, y + h - 2, w, y + h - 2); 
      break; 

      case EAST: 
        g.drawLine(x + w - 2, y, x + w - 2, y + h); 
      break; 

      case WEST: 
        g.drawLine(x + 1, y, x + 1, y + h); 
      break; 

      default: 
        g.drawLine(x, y, x + w, y); 
    } 
    if (lift == RAISED) 
      g.setColor(component.getBackground().darker()); 
    else 
      g.setColor(component.getBackground().brighter()); 
    switch (edge) 
    { 
      case SOUTH: 
        g.drawLine(x, y + h - 1, w, y + h - 1); 
      break; 

      case EAST: 
        g.drawLine(x + w - 1, y, x + w - 1, y + h); 
      break; 

      case WEST: 
        g.drawLine(x + 1, y, x + 1, y + h); 
      break; 

      default: 
        g.drawLine(x, y + 1, x + w, y + 1); 
    } 
  } 
} 

