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
 * Creates wall sections for mazes and other obstacles. The class defines five
 * different types of sections, each defined by the image that represents it:
 *
 * <ul>
 * <li>
 * Vertical Long
 * </li>
 * <li>
 * Horizontal Long
 * </li>
 * <li>
 * Horizontal Half
 * </li>
 * <li>
 * Horizontal Three-Quarters
 * </li>
 * <li>
 * Horizontal Quarter
 * </li>
 * </ul>
 *
 * In addition to the standard-sized wall sections, the user can also create a
 * wall by specifying only the size.  A wall defined by size will have the
 * default textured appearance of a grey rock-like wall.
 *
 * @version 4.1   October 2001
 * @author Rusl Flowers
 * @author Karl A. Gossett
 */
public class Wall
    extends SolidElement {

    public static final int HORZ_HALF = 2;
    public static final String HORZ_HALF_IMG = "images/horz_half_piece.gif";
    public static final int HORZ_HALF_LENGTH = 250;
    public static final int HORZ_LONG = 1;
    public static final String HORZ_LONG_IMG = "images/horz_long_piece.gif";
    public static final int HORZ_LONG_LENGTH = 500;
    public static final int HORZ_QUARTER = 4;
    public static final String HORZ_QUARTER_IMG =
            "images/horz_quarter_piece.gif";
    public static final int HORZ_QUARTER_LENGTH = 125;
    public static final int HORZ_THREE_QUARTER = 3;
    public static final String HORZ_THREE_QUARTER_IMG =
            "images/horz_threeQuarter_piece.gif";
    public static final int HORZ_THREE_QUARTER_LENGTH = 375;

    // Types of Walls
    public static final int VERT_LONG = 0;

    // The file names of the images
    public static final String VERT_LONG_IMG = "images/vert_long_piece.gif";

    // The length of the wall images
    public static final int VERT_LONG_LENGTH = 600;
    public static final int VERT_SHORTER = 5;
    public static final String VERT_SHORTER_IMG =
            "images/vert_shorter_piece.gif";
    public static final int VERT_SHORTEST = 6;
    public static final String VERT_SHORTEST_IMG =
            "images/vert_shortest_piece.gif";

    // The width of the wall images
    public static final int WIDTH = 6;

    protected static boolean textureChanged = false;
    /**
     * A default texture for all walls.  This is a static member to optimize
     * loading--it will only be loaded once, regardless of the number of
     * walls.
     */
    private static java.awt.image.BufferedImage bufferedTexture = null;
    private boolean hasImage = false;
    private java.awt.TexturePaint texture = null;

    /**
     * Creates a wall specified by the type. The <i>type</i> must be one of
     * the following constants:
     *
     * <ul>
     * <li>
     * Wall.VERT_LONG
     * </li>
     * <li>
     * Wall.HORZ_LONG
     * </li>
     * <li>
     * Wall.HORZ_HALF
     * </li>
     * <li>
     * Wall.HORZ_THREE_QUARTER
     * </li>
     * <li>
     * Wall.HORZ_QUARTER
     * </li>
     * </ul>
     *
     *
     * @param type DOCUMENT ME!
     */
    public Wall(int type) {

        String imageFile = "unknown";

        switch (type) {

            case VERT_LONG:
                imageFile = VERT_LONG_IMG;

                break;

            case VERT_SHORTER:
                imageFile = VERT_SHORTER_IMG;

                break;

            case VERT_SHORTEST:
                imageFile = VERT_SHORTEST_IMG;

                break;

            case HORZ_LONG:
                imageFile = HORZ_LONG_IMG;

                break;

            case HORZ_HALF:
                imageFile = HORZ_HALF_IMG;

                break;

            case HORZ_QUARTER:
                imageFile = HORZ_QUARTER_IMG;

                break;

            case HORZ_THREE_QUARTER:
                imageFile = HORZ_THREE_QUARTER_IMG;
        }

        // Sets the appearance of this Wall
        setAppearance(imageFile);
        hasImage = true;
    } // end Wall constructor

    /**
     * Creates a new <CODE>Wall</CODE> with the specified dimensions.  Walls
     * created using this constructor will have a default wall texture.
     *
     * @param width      The width of the wall (in pixels).
     * @param height     The height of the wall (in pixels).
     */
    public Wall(int width, int height) {

        if (width > 0 && height > 0) {
            size = new java.awt.Dimension(width, height);
        } else {
            size = new java.awt.Dimension(0, 0);
        }

        setTexture("images/DefaultWall.gif");
    }

    /**
     * Draws the image of the object that implements this interface into a
     * Java2D (Graphics2D) context.
     *
     * @param g2    The Graphics2D context to draw in.
     */
    public void draw(java.awt.Graphics2D g2) {

        java.awt.Paint oldPaint = g2.getPaint();

        if (textureChanged && bufferedTexture != null) {
            texture = new java.awt.TexturePaint(bufferedTexture,
                                       getBounds2D());
        }

        if (hasImage) {
            super.draw(g2);
        } else {

            // Fill in the wall with the desired texture
            g2.setPaint(texture);
            g2.fill(myShape);

            // Draw a black border around it
            g2.setColor(java.awt.Color.black);
            g2.draw(myShape);
            g2.setPaint(oldPaint);
        }
    }

    /**
     * Sets the texture of a standard wall to the specified image.
     *
     * @param image    The String name of the texture.
     */
    protected void setTexture(String image) {

        java.awt.Image defaultTexture = null;

        defaultTexture = jago.ImageLoader.getImage(this, image);

        if (defaultTexture != null) {

            int width;
            int height;
            width = defaultTexture.getWidth(null);
            height = defaultTexture.getHeight(null);
            bufferedTexture = new java.awt.image.BufferedImage(width, height,
                                                java.awt.image.BufferedImage.TYPE_INT_RGB);

            java.awt.Graphics g = bufferedTexture.getGraphics();
            g.drawImage(defaultTexture, 0, 0, null);
            g.dispose();
			textureChanged = true;
        }
    }

	public void setAppearance(String image) {
	    setTexture(image);
    }

}