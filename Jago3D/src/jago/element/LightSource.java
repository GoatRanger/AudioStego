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

package jago.element;


/**
 * Interface to create a light source in the simulation.  Classes implementing
 * this interface in the simulation may not be visible and will not interact
 * with actors directly, but any actor that has a light sensor will detect a
 * LightSource as it gets closer to or points more directly toward the
 * LightSource.
 *
 * @version 1.0
 * @author Karl A. Gossett
 */
public interface LightSource {

    /**
     * Sets the intensity of this light source
     *
     * @param intensity DOCUMENT ME!
     */
    void setIntensity(int intensity);

    /**
     * Returns the intensity of this light source
     *
     * @return DOCUMENT ME!
     */
    int getIntensity();

    /**
     * Undocumented
     *
     * @return Empty
     */
    java.awt.geom.Point2D getPosition();
}