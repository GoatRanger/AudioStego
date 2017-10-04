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

class DelayedAction
    extends Thing {

    Action delayedAction;
    double remainingTime;

    DelayedAction(double delay, Action delayedAction) {
        this.delayedAction = delayedAction;
        remainingTime = delay;
        Arena.actionQueue.add(this);
    }

    /**
     * Undocumented
     */
    void action() {
        Arena.timedThings.add(this);
    }

    /**
     * Undocumented
     * 
     * @param deltaT Empty
     */
    void tick(double deltaT) {
        remainingTime -= deltaT;

        if (remainingTime <= 0) {
            delayedAction.action();
            obsolete = true;
        }
    }
}