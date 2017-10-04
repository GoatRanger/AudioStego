/*
 * Created on Jun 20, 2008
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package edu.chem;

import edu.chem.util.Messages;

import java.applet.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
/**
 * @author Karl A. Gossett
 *
 */
public class Chemistry extends JApplet {

  private JFrame mainWindow;
  private JLabel statusArea;
  private JProgressBar progressBar;
  private JTabbedPane programTabs;
  private JPanel mainPanel;
  private JTabbedPane messageTabs;
  private JToolBar toolBar;

  public Chemistry() {
    
  }
  public void init() {
    //Execute a job on the event-dispatching thread:
    //creating this applet's GUI.
    try {
        javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                initializeGUI();
            }
        });
    } catch (Exception e) {
        System.err.println("createGUI didn't successfully complete");
        e.printStackTrace();
    }
  }

  private void createGUI() {
    JLabel label = new JLabel(
                       "You are successfully running a Swing applet!");
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
    getContentPane().add(label, BorderLayout.CENTER);
}

  private void initializeGUI() {
    getContentPane().setLayout(new BorderLayout());
    
    mainPanel = new JPanel();
    mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    mainPanel.setLayout(new BorderLayout());
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPanel,
        messageTabs);
    splitPane.setOneTouchExpandable(true);
    splitPane.setResizeWeight(0.8);
    // statusArea = new JLabel(Messages.getString("Editor.status.welcome.message")); //$NON-NLS-1$
    statusArea = new JLabel("Welcome");
    statusArea.setForeground(Color.black);
    createHelpWindow();
    initializeToolBar();
    getContentPane().add(toolBar, BorderLayout.NORTH);
    getContentPane().add(splitPane, BorderLayout.CENTER);
    getContentPane().add(statusArea, BorderLayout.SOUTH);
  }
  
  private void initializeToolBar() {
    // TODO Auto-generated method stub
    toolBar = new JToolBar("Tools");
  }

  private void createHelpWindow() {
    
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    new Chemistry();
  }

}
