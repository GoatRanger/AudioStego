/*
 * Copyright (C) 2003 USMA This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License, or any later version. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
/*
 * SimpleLinkListener.java A hyperlink listener for use with JEditorPane. This listener will change the cursor over hotspots based on
 * enter/exit events and also load a new page when a valid hyperlink is clicked.
 */
package eecs.util.html;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class SimpleLinkListener implements HyperlinkListener {
  private JEditorPane pane; // The pane we're using to display HTML
  private JTextField urlField; // An optional textfield for showing
  // the current URL being displayed
  private JLabel statusBar; // An option label for showing where

  // a link would take you
  public SimpleLinkListener(JEditorPane jep, JTextField jtf, JLabel jl) {
    pane = jep;
    urlField = jtf;
    statusBar = jl;
  }

  public SimpleLinkListener(JEditorPane jep) {
    this(jep, null, null);
  }

  public void hyperlinkUpdate(HyperlinkEvent he) {
    // We'll keep some basic debuggin information in here so you can
    // verify our new editor kit is working.
    //System.out.print("Hyperlink event started...");
    HyperlinkEvent.EventType type = he.getEventType();
    // Ok. Decide which event we got...
    if (type == HyperlinkEvent.EventType.ENTERED) {
      // Enter event. Go the the "hand" cursor and fill in the status bar
      //System.out.println("entered");
      pane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      statusBar.setText(he.getURL().toString());
    }
    else
      if (type == HyperlinkEvent.EventType.EXITED) {
        // Exit event. Go back to the default cursor and clear the status bar
        //System.out.println("exited");
        pane.setCursor(Cursor.getDefaultCursor());
        statusBar.setText(" ");
      }
      else {
        // Jump event. Get the url, and if it's not null, switch to that
        // page in the main editor pane and update the "site url" label.
        //System.out.println("activated");
        try {
          pane.setPage(he.getURL());
          if (urlField != null) {
            urlField.setText(he.getURL().toString());
          }
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
  }
}