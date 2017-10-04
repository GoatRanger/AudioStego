/*
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.util.tips;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
/**
 * A text area for the JTips quick tip presenter.
 * Code taken from Java Developer's Journal, Volume 3, Issue 11
 * 
 * @author Claude Duguay
 *
 */
public class TipTextArea extends JTextArea 
{ 
  public TipTextArea() 
  { 
    super(); 
    setFont(new Font("Helvetica", Font.PLAIN, 12)); 
    setBorder(new EmptyBorder(10, 10, 10, 10)); 
    setWrapStyleWord(true); 
    setEditable(false); 
    setLineWrap(true); 
  } 

  public boolean isFocusTraversable() 
  { 
    return false; 
  } 
} 
