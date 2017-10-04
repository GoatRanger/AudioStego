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


/**
 * A variation of a <CODE>SolidElement</CODE> that is just a stationary
 * destroyed tank.
 */
public class DeadTank
    extends jago.element.SolidElement {

    /**
     * Creates a new DeadTank with the default image.
     */
    public DeadTank() {
        this("dead_tank_1.gif");
    }

    /**
     * Creates a new DeadTank object.
     * 
     * @param imgFile The file name for the image that represents this DeadTank
     */
    public DeadTank(String imgFile) {
        super();
        setAppearance(imgFile);
    }
}