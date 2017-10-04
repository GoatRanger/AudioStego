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
 * ReflectiveColors.java
 *
 * Created on December 16, 2001, 1:10 PM
 */
package eecs.robot.jago.element;


/**
 * The colors that can be detected using a reflectance sensitive sensor.  As
 * of version 6.2.2 of the simulation, the only colors supported in the real
 * robot using the Lego supplied light sensor are <CODE>BLACK</CODE>, the
 * three levels of gray, and <CODE>WHITE</CODE> (the Lego sensor is fairly
 * limited). Other colors may be supported in limited situations, and should
 * be used sparingly.  To better guarantee accuracy, colors should be limited
 * to BLACK and WHITE whenever possible.
 *
 * <P>
 * The other colors are left for completeness and for use in simulations,  but
 * ineffectively detected with the Lego system, so should not be used for
 * simulations unless there is no requirement to execute in the Lego
 * Mindstorms using the standard (blue) light sensor.
 * </p>
 *
 * <P>
 * The values returned in the simulation may not match the values returned by
 * the Lego Light Sensor.  If the constants are used, the values are
 * reasonably close to accurate, as long as the light sensor is extremely
 * close to the ground.
 * </p>
 *
 * <P>
 * <B>Notice:</B> In <CODE>REALISTIC</CODE> mode, there is no guarantee that
 * the color returned will exactly match the color here, due to variances in
 * the detection and lighting conditions when the robot is used.  Readings
 * within a certain range are converted to these constants, but may appear
 * incorrect due to the simulation detector.
 * </p>
 *
 * @version 2.0
 * @author Karl A. Gossett
 * @since 5.1
 */
public interface ReflectiveColors {

    /** A black color */
    public static final int BLACK = 33;

    /** A blue color */
    public static final int BLUE = 35;

    /** A dark gray color */
    public static final int DARK_GRAY = 38;

    /** A green color */
    public static final int GREEN = 34;

    /** A light gray color */
    public static final int LIGHT_GRAY = 46;

    /** A medium gray color */
    public static final int MED_GRAY = 42;

    /** A red color--based on a dark red */
    public static final int RED = 41;

    /** An unknown color of a line */
    public static final int UNKNOWN = -1;

    /** A white color */
    public static final int WHITE = 50;
}