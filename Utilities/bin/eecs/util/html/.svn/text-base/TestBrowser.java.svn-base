/* Copyright (C) 2003  USMA
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

/*
 * TestBrowser.java
 * A test bed for the JEditorPane and a custom editor kit.
 * This extremely simple browser has a text field for typing in
 * new urls, a JEditorPane to display the HTML page, and a status
 * bar to display the contents of hyperlinks the mouse passes over.
 */
package eecs.util.html;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class TestBrowser extends JFrame {
  JTextField urlField = null;
  final JEditorPane jep = new JEditorPane();
  final JLabel statusBar = new JLabel(" ");

  public TestBrowser(String startingUrl) {
    this(startingUrl, "Internal Browser", 400, 300);
  }

  public TestBrowser(String startingURL, String title, int sizeX, int sizeY) {
    // Ok, first just get a screen up and visible, with an appropriate
    // handler in place for the kill window command
    super(title);

    //setSize(sizeX,sizeY);
    //        addWindowListener(new WindowAdapter() {
    //            public void windowClosing(WindowEvent we) {
    //                we.getWindow().setVisible(false);
    //                //System.exit(0);
    //            }
    //        });
    // Now set up our basic screen components, the editor pane, the
    // text field for URLs, and the label for status and link information
    JPanel urlPanel = new JPanel();
    urlPanel.setLayout(new BorderLayout());
    urlField = new JTextField(startingURL);
    urlPanel.add(new JLabel("Site: "), BorderLayout.WEST);
    urlPanel.add(urlField, BorderLayout.CENTER);

    // Here's the editor pane configuration.  It's important to make
    // the "setEditable(false)" call, otherwise our hyperlinks won't
    // work.  (If the text is editable, then clicking on a hyperlink
    // simply means that you want to change the text...not follow the
    // link.)
    jep.setSize(sizeX, sizeY);
    jep.setPreferredSize(new Dimension(sizeX, sizeY));
    jep.setEditable(false);

    // Here's where we force the pane to use our new editor kit
    jep.setEditorKitForContentType("text/html", new PatchedHTMLEditorKit());

    setURL(startingURL);

    JScrollPane jsp = new JScrollPane(jep);

    // and get the GUI components onto our content pane
    getContentPane().add(jsp, BorderLayout.CENTER);
    getContentPane().add(urlPanel, BorderLayout.NORTH);
    getContentPane().add(statusBar, BorderLayout.SOUTH);
    this.pack();

    // and last but not least, hook up our event handlers
    urlField.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          try {
            jep.setPage(ae.getActionCommand());
          } catch (Exception e) {
            statusBar.setText("Could not open starting page.  Using a blank.");
          }
        }
      });
    jep.addHyperlinkListener(new SimpleLinkListener(jep, urlField, statusBar));
  }

  public void setURL(String url) {
    boolean pageFound = true;

    try {
      jep.setPage(url);
    } catch (Exception e) {
      pageFound = false;
    }

    if (!pageFound) {
      try {
        jep.setPage(TestBrowser.class.getResource(url));
        pageFound = true;
      } catch (Exception e) {
        statusBar.setText("Could not open starting page.  Using a blank.");
        pageFound = false;
      }
    }

    if (pageFound) {
      urlField.setText(url);
    }
  }

  public static void main(String[] args) {
    (new TestBrowser((args.length == 0) ? "file:///c:/tmp/test.html" : args[0])).setVisible(true);
  }
}
