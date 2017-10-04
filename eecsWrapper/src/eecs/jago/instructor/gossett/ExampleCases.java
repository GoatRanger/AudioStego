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

/*
 * ExampleCases.java
 *
 * Created on March 6, 2002, 9:55 PM
 */
package eecs.jago.instructor.gossett;

import jago.SimBox;

import jago.element.*;


/**
 * @author Karl Gossett
 */
public class ExampleCases
    extends SimBox {

    ActivatorButton button;
    int restartCounter;
    private TimerListener listener;

    /**
     * Creates a new instance of ExampleCases
     */
    public ExampleCases() {
        super();
        add(new Wall(200, 5), 
            50, 
            450);
        add(new ColoredLine(5, 50, ReflectiveColors.BLACK), 
            180, 
            200);
        add(new ColoredLine(5, 50, ReflectiveColors.RED), 
            100, 
            200);
        add(new Message("3 presses to activate"), 
            230, 
            350);
        button = new ActivatorButton(3);
        add(button, 300, 300);
        listener = new TimerListener();
        simEngine.addActionListener(listener);
    }

    class TimerListener
        implements java.awt.event.ActionListener {

        //~ Methods ...........................................................

        /**
         * Updates the state of the simulation based on the current event
         * notification (from the Timer).
         * 
         * @param actionEvent DOCUMENT ME!
         */
        public void actionPerformed(java.awt.event.ActionEvent actionEvent) {

            if (button.isActivated()) {
                restartCounter--;
            } else {
                restartCounter = 40;
            }

            if (restartCounter <= 0) {
                button.reset();
            }
        }
    }
}