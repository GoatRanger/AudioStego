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

package eecs.jago.instructor.flowers;

import eecs.simulation.*;


/**
 * A <CODE>DetonatorSimulation</CODE> with the title "NYSE".  Included to for
 * legacy code that used the StockExchange class from JAGO v4.0.
 * 
 * @version 1.0
 * @author Rusl Flowers
 * @since 5.0
 */
public class StockExchange
    extends DetonatorSimulation {

    /**
     * Creates new StockExchange
     * 
     * @param isLayoutRandom DOCUMENT ME!
     */
    public StockExchange(boolean isLayoutRandom) {
        super("NYSE", isLayoutRandom);
    }
}