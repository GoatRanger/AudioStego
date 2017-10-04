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
 * Realism.java
 *
 * Created on December 16, 2001, 1:35 PM
 */
package jago;


/**
 * This interface is implemented by any object that has functionality that
 * matches a physical device.
 *
 * @version 1.0
 * @author Karl A. Gossett
 */
public interface Realism {

    /**
     * Indicates that all elements should operate in a realistic manner, but
     * should not be random each time.  This allows testing of a system with
     * components acting realistically, but predictably.  As an example, if a
     * robot is created in this mode, it will be created with drag on each
     * motor, but it will always have the same drag (REALISTIC mode generates
     * a new random drag each time a robot is created).
     */
    public static final int FIXED_REALISTIC = 2;

    /**
     * Indicates that any element should react idealistically (does not adhere
     * to the expectations of the real object)
     */
    public static final int IDEALISTIC = 1;

    /**
     * Indicates that all elements should attempt to act realistically (as
     * close to the modeled object as possible).
     */
    public static final int REALISTIC = 0;

    /**
     * Sets the realism level.
     *
     * @param level A constant from {@link eecs.jago.Realism}
     */
    public void setRealismLevel(int level);

    /**
     * Gets the realism setting (as a constant)
     *
     * @return Returns one of the Realism constants, currently {@link
     *         #IDEALISTIC}, {@link #REALISTIC}, or {@link #FIXED_REALISTIC}
     */
    public int getRealismLevel();
}