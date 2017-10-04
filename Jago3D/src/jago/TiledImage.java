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

package jago;

import java.awt.*;


public class TiledImage {

    static Image tiledImage;

    /**
     * Undocumented
     *
     * @param theImage Empty
     * @param width Empty
     * @param height Empty
     * @param owner Empty
     * @return Empty
     */
    public static Image createTiledImage(Image theImage, int width, int height,
                                         SimEngine owner) {
        tiledImage = owner.createImage(width, height);

        int imgWidth = theImage.getWidth(null);
        int imgHeight = theImage.getHeight(null);

        for (int i = 0; i < width; i += imgWidth) {

            for (int j = 0; j < height; j += imgHeight) {
                tiledImage.getGraphics()
                          .drawImage(theImage, i, j, owner);
            }
        }

        return tiledImage;
    }
}