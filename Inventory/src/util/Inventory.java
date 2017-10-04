/*
 * Created on Sep 30, 2005
 *
 */
package util;

import javax.swing.JFrame;

/**
 * @author Karl A. Gossett
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Inventory extends JFrame {

	/**
   * 
   */
  private static final long serialVersionUID = 1L;
  private javax.swing.JPanel jContentPane = null;

	/**
	 * This is the default constructor
	 */
	public Inventory() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300,200);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
		}
		return jContentPane;
	}
}
