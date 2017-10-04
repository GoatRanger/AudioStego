/* Copyright (C) 2002  USMA
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
/* eecs.Gui:
 *   Provides the Gui for non-robot applications.
 */
package eecs;

import jago.ImageLoader;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * A wrapper for text based programming.
 */
public class Gui extends GuiBase
{
    //protected static JDialog dialog;

    // private gui objects
    private static final String DEFAULT_TITLE = "Output";

    // static initializer
    static
    {
        getProperties();

        if (!runningTests)
        {
            frame = new JFrame();
            frame.setIconImage(ImageLoader.getImage(frame, "/eecs/sim/images/eecs.gif"));
            output = new JTextArea();
            output.setFont(new Font("Monospaced", Font.PLAIN, 12));
            output.setEditable(false);
            JScrollPane pane = new JScrollPane(output);
            frame.setSize(new Dimension(550,380));
            frame.getContentPane().add(pane);
            frame.addWindowListener(new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        System.exit(0);
                    }
                });

            // center the window
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Dimension dialogSize = frame.getSize();
            frame.setLocation((screenSize.width - dialogSize.width) / 2,
                (screenSize.height - dialogSize.height) / 2);
            setOutputTitle(DEFAULT_TITLE);
            frame.setVisible(true); 
        }
    }
     // end static initializer

    /**
     * Undocumented
     *
     * @param title Empty
     */
    public static void setOutputTitle(String title)
    {
        frame.setTitle(title);
    }
}
 // end class Gui
