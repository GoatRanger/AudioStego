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
 * A Building represents any SolidElement in the simulation that appears as a
 * large rectangular object, generally with the appearance of a building. The
 * standard Building has several predifined appearances (specified by a
 * <CODE>type</CODE> parameter), which can be extended by the user by adding
 * additional images with unique identifiers.  Buildings can also be created
 * using an explicitly defined file name.  All Buildings must have an image
 * (they do not just have a texture), and the size is automatically assigned
 * to be equal to the size of the image.
 *
 * @version 0.1
 * @author Karl A. Gossett
 * @since 4.1
 */
public class Building
    extends SolidElement {

    /**
     * Creates a Building of the specified type.  The type number must specify
     * a building in the images directory with the name format of
     * <CODE>building_x.gif</CODE> where <CODE>x</CODE> is the type number.
     *
     * @param type   An integer identifier for the building
     */
    public Building(int type) {
        super();
        setType(type);
    }

    /**
     * Creates a Building using the supplied image to determine appearance and
     * size.  If the image does not exist, an empty rectangle will be used
     * instead.
     *
     * @param imageName   The name of the image.  The image must be in the
     *        user directory or in a subdirectory of the package labeled
     *        <CODE>images</CODE>.
     */
    public Building(String imageName) {
        setAppearance(imageName);
    }

    /**
     * Sets the type of building.  Normally only called from the constructor.
     *
     * @param type  The integer identifier for the building
     */
    protected void setType(int type) {

        if (type < 1 || type > 12) {
            type = 1;
        }

        setAppearance("/eecs/images/building_" + type + ".gif");
    }
}