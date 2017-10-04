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

package eecs.robot.jago.element;


/**
 * A <CODE>Reflective</CODE> object will appear to reflect lighting.
 * Implementing this interface will allow objects with sensors to detect the
 * reflection.  Useful for sensors such as the Lego Mindstorms standard Light
 * sensor.
 *
 * @version 0.1
 * @author Karl A. Gossett
 * @since 4.1
 */
public interface Reflective
    extends ReflectiveColors {

    /** The maximum amount of reflected light. */
    public static final int MAX_REFLECTION = 100;

    /** The minimum amount of reflected light */
    public static final int MIN_REFLECTION = 0;

    /**
     * Sets the level of reflected light by any class that implements this
     * interface.
     *
     * @param level   The level of reflected light, between MIN_REFLECTION and
     *        MAX_REFLECTION, inclusively.
     */
    public void setReflection(int level);

    /**
     * Returns the level of reflection of light.
     *
     * @return The amount of reflected light.
     */
    public int getReflection();
}