/*
 * Created on Aug 3, 2008
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package edu.chem;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JApplet;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import java.awt.GridBagLayout;
import javax.swing.JMenu;
import java.awt.GridBagConstraints;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class ChemAppletGUI extends JApplet {

  private JInternalFrame jInternalFrame = null;
  private JPanel jContentPane = null;

  /**
   * This is the xxx default constructor
   */
  public ChemAppletGUI() {
    super();
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  public void init() {
    this.setSize(450, 322);
    this.setContentPane(getJInternalFrame());
  }

  /**
   * This method initializes jInternalFrame	
   * 	
   * @return javax.swing.JInternalFrame	
   */
  private JInternalFrame getJInternalFrame() {
    if (jInternalFrame == null) {
      jInternalFrame = new JInternalFrame();
      jInternalFrame.setContentPane(getJContentPane());
    }
    return jInternalFrame;
  }

  /**
   * This method initializes jContentPane	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
    }
    return jContentPane;
  }

}  //  @jve:decl-index=0:visual-constraint="13,7"
