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
import java.awt.geom.*;


class Hospital
    extends Thing {

    static final Area SHAPE = new Area(Util.rectangle(40, 90));

    static {
        SHAPE.add(new Area(Util.rectangle(90, 40)));
    }

    Hospital(double x, double y) {
        this.color = makeColor();
        this.shape = Util.translate(SHAPE, x, y);
        Arena.actionQueue.add(this);
    }

    /**
     * Undocumented
     */
    void action() {
        Arena.visibleThings.add(this);
        Arena.timedThings.add(this);

        //Arena.stationaryThings.add(this);
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    Color makeColor() {

        //  double red = 0.90 + 0.10 * Math.sin(2*Clock.read());
        //   return new Color((float)red,0.0f,0.0f,0.5f);
        double transparency = 0.5 + 0.25 * Math.sin(2 * Clock.read());

        return new Color(1.0f, 0.0f, 0.0f, (float)transparency);
    }

    /**
     * Undocumented
     * 
     * @param deltaT Empty
     */
    void tick(double deltaT) {
        color = makeColor();
    }
}