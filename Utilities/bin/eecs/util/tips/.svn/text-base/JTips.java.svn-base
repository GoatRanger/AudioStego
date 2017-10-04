/*
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.util.tips;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


/**
 * Creates a Quick Tips dialog.
 * Code taken from Java Developer's Journal, Volume 3, Issue 11
 * 
 * @author Claude Duguay
 *
 */
public class JTips extends JFrame 
  implements ActionListener 
{ 
  private Preferences preferences;
  protected String filename; 
  protected Vector tips; 

  protected JButton next, close; 
  protected JCheckBox show; 
  protected JTextArea text; 

  public JTips(String filename) {
    this(filename,null);
  }
  public JTips(String filename, String store) 
  { 
    super("Tip of the Day");
    openPreferences(store);


    int w = 440; 
    int h = 280; 
    Dimension screen = 
      Toolkit.getDefaultToolkit().getScreenSize(); 
    int x = (screen.width - w) / 2; 
    int y = (screen.height - h) / 2; 
    setBounds(x, y, w, h); 

    this.filename = filename; 

    getContentPane().setLayout(new BorderLayout()); 
    tips = new Vector(); 
    readTipFile(); 

    JPanel iconPanel = new JPanel(); 
    iconPanel.setLayout(new BorderLayout()); 
    iconPanel.setBackground(Color.gray); 
    iconPanel.setPreferredSize(new Dimension(53, 53)); 
    JLabel icon = new JLabel(createIcon("eecs/util/images/TipOfTheDay24.gif"));
    icon.setVerticalAlignment(JLabel.CENTER); 
    icon.setHorizontalAlignment(JLabel.CENTER); 
    icon.setPreferredSize(new Dimension(53, 53)); 
    iconPanel.add("North", icon); 

    JPanel titlePanel = new JPanel(); 
    JLabel title = new JLabel("Did you know..."); 
    title.setBorder(new EmptyBorder(10, 10, 0, 0)); 
    title.setFont(new Font("Helvetica", Font.PLAIN, 18)); 
    titlePanel.setLayout(new BorderLayout()); 
    titlePanel.setBorder(new EdgeBorder(EdgeBorder.SOUTH)); 
    titlePanel.setPreferredSize(new Dimension(46, 46)); 
    titlePanel.add("Center", title); 

    text = new TipTextArea(); 
    text.setBackground(getBackground()); 

    JPanel centerPanel = new JPanel(); 
    centerPanel.setLayout(new BorderLayout()); 
    centerPanel.add("North", titlePanel); 
    centerPanel.add("Center", text); 

    JPanel tipsPanel = new JPanel(); 
    tipsPanel.setLayout(new BorderLayout()); 
    tipsPanel.setBorder( 
      new CompoundBorder( 
        new EmptyBorder(10, 10, 0, 10), 
        new BevelBorder(BevelBorder.LOWERED))); 
    tipsPanel.add("Center", centerPanel); 
    tipsPanel.add("West", iconPanel); 

    getContentPane().add("Center", tipsPanel); 

    JPanel buttonPanel = new JPanel(); 
    buttonPanel.setLayout( 
      new FlowLayout(FlowLayout.RIGHT, 10, 10)); 
    buttonPanel.add(next = new JButton("Next Tip")); 
    buttonPanel.add(close = new JButton("Close")); 

    JPanel showPanel = new JPanel(); 
    showPanel.setLayout( 
      new FlowLayout(FlowLayout.LEFT, 10, 10)); 
      showPanel.add(show = 
        new JCheckBox("Show tips at startup", getShow())); 

    JPanel navPanel = new JPanel(); 
    navPanel.setLayout(new BorderLayout()); 
    navPanel.add("East", buttonPanel); 
    navPanel.add("West", showPanel); 

    getContentPane().add("South", navPanel); 

    next.addActionListener(this); 
    close.addActionListener(this); 
    show.addActionListener(this); 

    nextTip(); 
  } 
  /**
   * @param store
   */
  private void openPreferences(String store) {
    if (store == null) {
      // Package.toString() returns a string with the prefix "package ", so skip that part.
      store = this.getClass().getPackage().toString().substring(8).replaceAll("\\.","/");
    }
    preferences = Preferences.userRoot().node(store);
  }
  
  private ImageIcon createIcon(String file) {
  	// Get current classloader
  	ClassLoader cl = this.getClass().getClassLoader();
  	java.net.URL url = cl.getResource(file);
  	ImageIcon icon = new ImageIcon(url);

  	return icon;
  }
  private void increment() 
  { 
    int current = getNext() + 1; 
    if (current >= tips.size()) current = 0; 
    setNext(current); 
  } 

  private void nextTip() 
  { 
    text.setText((String)tips.elementAt(getNext())); 
    increment(); 
  } 

  private int getNext() 
  { 
  	int next = preferences.getInt("tips.index",0);
  	if (next >= tips.size()) {
  	  next = 0;
  	  setNext(0);
  	}
    return next; 
  } 

  private void setNext(int next) 
  { 
    try {
	    preferences.putInt("tips.index",next);
	    preferences.flush();
    } catch (BackingStoreException be) {
      be.printStackTrace();
    }
  } 

  public boolean getShow() 
  { 
  	boolean prop = preferences.getBoolean("tips.show",true);
    return prop;
  } 

  public void setShow(boolean show) 
  { 
  	preferences.putBoolean("tips.show",show);
  	try {
      preferences.flush();
    } catch (BackingStoreException e) {
      e.printStackTrace();
    }
  } 

  public void readTipFile() 
  { 
    tips.removeAllElements();
    try 
    {
    	BufferedReader input = null;
    	InputStream is = null;
    	if (filename.startsWith("http:")) {
	    	URL url = null;
	    	url = new URL(filename);
	    	is = url.openStream();
    	} else if (filename.indexOf(":") > 0) {
    	    is = new FileInputStream(filename);
    	} else {
    	
	    	is = getClass().getResourceAsStream(filename);

    	}
    	input = new BufferedReader(new InputStreamReader(is));
    	if (input == null) {
    		tips.addElement("When connected to the network, you can get useful information in this window.");
    		return;
    	}

      String line; 
      while ((line = input.readLine()) != null) 
      { 
        line = replaceParagraphMarkers(line); 
        tips.addElement(line); 
      } 

      input.close(); 
      is.close(); 
    } 
    catch (FileNotFoundException e) 
    { 
    	tips.addElement("When connected to the network, you can get useful information in this window."); 
    } 
    catch (IOException e) 
    { 
      tips.addElement("Error reading '" + filename + "'"); 
      e.printStackTrace();
    } catch (NullPointerException npe) {
      tips.addElement("There are no tips available to display.  Connect to the USMA network to learn more about this editor through the daily Tips!");
    }
  } 

  public String replaceParagraphMarkers(String line) 
  { 
    StringBuffer buffer = new StringBuffer(line); 
    int pos; 
    while ((pos = buffer.indexOf("\\p")) > -1) 
    { 
      buffer.replace(pos,pos+2,"\n"); 
      //buffer.setCharAt(pos + 1, '\n'); 
    }
    line = buffer.toString();
    return line; 
  } 

  public void actionPerformed(ActionEvent event) 
  { 
    Object source = event.getSource(); 
    if (source == close) 
 { 
      setVisible(false); 
    } 
    if (source == next) 
    { 
      nextTip(); 
    } 
    if (source == show) 
    { 
      setShow(show.isSelected()); 
    } 
  } 

  public void startup() 
  { 
    if (getShow()) {
    	nextTip();
    	setVisible(true); 
    }
  } 
} 

