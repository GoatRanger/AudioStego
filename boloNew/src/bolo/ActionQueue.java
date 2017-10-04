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

class ActionQueue {

    private static final int MAX_SIZE = 1000;
    private int size = 0;
    private Action[] currentActions = new Action[MAX_SIZE];
    private Action[] pendingActions = new Action[MAX_SIZE];

    // can be called by any thread
    synchronized void add(Action newAction) {

        // ignores possibility that queue is full!!!!!!
        pendingActions[size++] = newAction;
    }

    // can only be called by the animation thread

    /**
     * Undocumented
     */
    void processActions() {

        int currentSize = startingDequeues();
        Action[] a = currentActions;

        for (int i = 0; i < currentSize; i++) {
            a[i].action();
            a[i] = null;
        }
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    private synchronized int startingDequeues() {

        // currentActions must be empty
        Action[] tmp = currentActions;
        currentActions = pendingActions;
        pendingActions = tmp;

        int currentSize = size;
        size = 0;

        return currentSize;
    }
}