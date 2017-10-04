/*
 * Copyright (C) 2002 USMA This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU General Public License along with this program; if not,
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package eecs.robot.jago.element;

public class Concertina extends SolidElement {

  private static java.awt.image.BufferedImage bufferedTexture = null;

  private boolean hasImage = false;

  private java.awt.TexturePaint texture = null;

  protected static boolean textureChanged = false;

  public Concertina(int type) {
    //
  }

  /**
   * Creates a new <CODE>Concertina obstacle</CODE> with the specified dimensions. Walls created using this
   * constructor will have a default wall texture.
   * 
   * @param width
   *          The width of the wall (in pixels).
   * @param height
   *          The height of the wall (in pixels).
   */
  public Concertina(int width, int height) {

    if (width > 0 && height > 0) {
      size = new java.awt.Dimension(width, height);
    }
    else {
      size = new java.awt.Dimension(0, 0);
    }

    setTexture("/eecs/images/Concertina_horizontal.GIF");
  }

  /**
   * Draws the image of the object that implements this interface into a Java2D (Graphics2D) context.
   * 
   * @param g2
   *          The Graphics2D context to draw in.
   */
  public void draw(java.awt.Graphics2D g2) {

    java.awt.Paint oldPaint = g2.getPaint();

    if (textureChanged && bufferedTexture != null) {
      texture = new java.awt.TexturePaint(bufferedTexture, getBounds2D());
    }

    if (hasImage) {
      super.draw(g2);
    }
    else {

      // Fill in the wall with the desired texture
      g2.setPaint(texture);
      g2.fill(myShape);
      g2.setStroke(new java.awt.BasicStroke(1, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));

      // Draw a black border around it
      g2.setColor(java.awt.Color.black);
      g2.draw(myShape);
      g2.setPaint(oldPaint);
    }
  }

  /**
   * Sets the texture of a standard wall to the specified image.
   * 
   * @param image
   *          The String name of the texture.
   */
  protected void setTexture(String image) {

    java.awt.Image defaultTexture = null;

    defaultTexture = eecs.robot.jago.ImageLoader.getImage(this, image);

    if (defaultTexture != null) {

      int width;
      int height;
      width = defaultTexture.getWidth(null);
      height = defaultTexture.getHeight(null);
      bufferedTexture = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);

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