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
 * PatchedHTMLEditorKit.java
 * A simple extension of the HTMLEditor kit that fires Enter/Exit
 * hyperlink events.
 */
package eecs.util.html;

import java.awt.*;
import java.awt.event.*;

import java.io.Serializable;

import java.net.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;


public class PatchedHTMLEditorKit extends HTMLEditorKit {
  // Since we only have two mouse events to listen to, we'll use the same
  // method to generate the appropriate hyperlinks and distinguish 
  // between them when we react to the mouse events.
  public static final int JUMP = 0;
  public static final int MOVE = 1;
  LinkController myController = new LinkController();

  public void install(JEditorPane c) {
    c.addMouseListener(myController);
    c.addMouseMotionListener(myController);
  }

  public static class LinkController extends MouseInputAdapter
    implements Serializable {
    URL currentUrl = null;

    // here's the mouseClicked event similar to the one in
    // the regular HTMLEditorKit, updated to indicate this is
    // a "jump" event
    public void mouseClicked(MouseEvent e) {
      JEditorPane editor = (JEditorPane) e.getSource();

      if (!editor.isEditable()) {
        Point pt = new Point(e.getX(), e.getY());
        int pos = editor.viewToModel(pt);

        if (pos >= 0) {
          activateLink(pos, editor, JUMP);
        }
      }
    }

    // And here's our addition.  Now the mouseMove events will
    // also call activateLink, but with a "move" type
    public void mouseMoved(MouseEvent e) {
      JEditorPane editor = (JEditorPane) e.getSource();

      if (!editor.isEditable()) {
        Point pt = new Point(e.getX(), e.getY());
        int pos = editor.viewToModel(pt);

        if (pos >= 0) {
          activateLink(pos, editor, MOVE);
        }
      }
    }

    // activateLink has now been updated to decide which hyperlink
    // event to generate, based on the event type and status of the
    // currentUrl field.  Rather than have two handlers (one for
    // enter/exit, one for active) we do all the work here.  This
    // saves us the effort of duplicating the href location code.
    // But that's really minor point.  You could certainly provide
    // two handlers if that makes more sense to you.
    protected void activateLink(int pos, JEditorPane html, int type) {
      Document doc = html.getDocument();

      if (doc instanceof HTMLDocument) {
        HTMLDocument hdoc = (HTMLDocument) doc;
        Element e = hdoc.getCharacterElement(pos);
        AttributeSet a = e.getAttributes();
        AttributeSet anchor = (AttributeSet) a.getAttribute(HTML.Tag.A);
        String href = (anchor != null)
          ? (String) anchor.getAttribute(HTML.Attribute.HREF) : null;
        boolean shouldExit = false;

        HyperlinkEvent linkEvent = null;

        if (href != null) {
          URL u;

          try {
            u = new URL(hdoc.getBase(), href);
          } catch (MalformedURLException m) {
            u = null;
          }

          if ((type == MOVE) && (!u.equals(currentUrl))) {
            linkEvent = new HyperlinkEvent(html,
                HyperlinkEvent.EventType.ENTERED, u, href);
            currentUrl = u;
          } else if (type == JUMP) {
            linkEvent = new HyperlinkEvent(html,
                HyperlinkEvent.EventType.ACTIVATED, u, href);
            shouldExit = true;
          } else {
            return;
          }

          html.fireHyperlinkUpdate(linkEvent);
        } else if (currentUrl != null) {
          shouldExit = true;
        }

        if (shouldExit) {
          linkEvent = new HyperlinkEvent(html, HyperlinkEvent.EventType.EXITED,
              currentUrl, null);
          html.fireHyperlinkUpdate(linkEvent);
          currentUrl = null;
        }
      }
    }
  }
}
