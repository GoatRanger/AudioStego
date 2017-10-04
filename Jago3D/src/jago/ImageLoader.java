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
 * ImageLoader.java
 *
 * Created on April 26, 2002, 10:29 PM
 */
package jago;

import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * A class for loading images from a file.
 * @author Karl A. Gossett
 * @version 2.0
 */
public class ImageLoader {

    /**
     * A convenience method for loading images.  This method will check
     * for the specified resource in the following locations:
     * <ol>
     *   <li>As an absolute filename specified by in <i>filename</i></li>
     *   <li>Relative to the package of the requestor</li>
     *   <li>Relative to the package of the requestor, but in an 
     *       <code>images</code> directory</li>.
     *
     * @param requestor The<code>URL</code> of the image
     * @param filename The name of the resource to load.
     * @return An <code>Image</code> loaded from the file.  <code>null</code> if the
     *         file is not found.
     */
    public static Image getImage(Object requestor, String filename) {

        java.io.File local = new java.io.File(filename);
        java.net.URL iconURL;

        try {

            if (local.exists()) {

                try {
                    iconURL = local.getAbsoluteFile()
                                   .toURL();
                } catch (java.net.MalformedURLException me) {
                    iconURL = null;
                }
            } else {

                // Try to find it relative to the package
                iconURL = requestor.getClass()
                                   .getResource(filename);

                if (iconURL == null) {


                    // Still didn't find it, try relative to an images directory
                    iconURL = requestor.getClass()
                                       .getResource("images/" + filename);
                }
            }
        } catch (SecurityException ace) {

            System.err.println(ace.toString());
            // Try to find it relative to the package
            iconURL = requestor.getClass()
                               .getResource(filename);

            if (iconURL == null) {

                // Still didn't find it, try relative to an images directory
                iconURL = requestor.getClass()
                                   .getResource("images/" + filename);
            }
        }

        ImageIcon icon = null;

        if (iconURL != null) {

            // Uses an ImageIcon--potential for future use, currently just
            // to take advantage of the automatic MediaTracker
            icon = new ImageIcon(iconURL, "Simulation Element");
        } else {
            System.err.print("Could not find " + filename + ", checked root, ");
            System.err.print( requestor.getClass().getPackage().toString()
                    + "."+ filename + " and ");
            System.err.println( requestor.getClass().getPackage().toString()
                    + ".images."+ filename + ".");
        }

        if (icon != null) {

            return icon.getImage();
        } else {

            return null;
        }
    }
}