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

package bolo;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Window {

    /**
     * Undocumented
     * 
     * @param component Empty
     * @param width Empty
     * @param height Empty
     * @param title Empty
     */
    public static void open(JComponent component, int width, int height, 
                            String title) {

        JFrame frame = new JFrame(title);
        frame.setBackground(Const.BACKGROUND_COLOR);
        component.setBackground(Const.BACKGROUND_COLOR);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setContentPane(component);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}