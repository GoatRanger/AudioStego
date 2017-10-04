/*
 * Copyright (C) 2002 USMA This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU General Public License along with this program; if not,
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package eecs.robot.jago;


import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

import javax.swing.*;
import eecs.robot.jago.element.*;

/**
 * This class is responsible for updating the display of all elements, including the background image and any elements
 * that are <CODE>Visible</CODE>.
 * <P>
 * As of Version 6.1, any element that does not implement the <CODE>Mobile</CODE> interface are added to the
 * background, and are not redrawn during every update.
 * </p>
 */
public class DisplayManager extends JPanel {

  /** Indicates whether debugging information is desired (not used) */
  protected boolean debugging = false;

  protected SimEngine owner;

  private Image background;

  private BufferedImage backgroundBuffer;

  private Graphics2D bg2;

  /** The height of the parent frame, used for scaling */
  private int frameHeight;

  /** The width of the parent frame, used for scaling */
  private int frameWidth;

  private BufferedImage offScreenBuffer;

  private Graphics2D osbg2;

  /** Flags an update of the background for the next paint cycle */
  private boolean updateNeeded = false;

  /**
   * Creates a new DisplayManager, within the specified SimEngine
   * 
   * @param theOwner
   *          The that requires service.
   */
  public DisplayManager(SimEngine theOwner) {
    super();
    owner = theOwner;
    frameWidth = (int) owner.getPreferredSize().getWidth();
    frameHeight = (int) owner.getPreferredSize().getHeight();
  }

  /**
   * Set the background image to the specified image.
   * 
   * @param theImage
   *          An <CODE>Image</CODE> to use as the background.
   */
  public void setBackground(Image theImage) {
    background = theImage;

    int imageWidth = theImage.getWidth(this);
    int imageHeight = theImage.getHeight(this);
    this.setPreferredSize(new Dimension(imageWidth, imageHeight));
    revalidate();
    frameWidth = (int) owner.getPreferredSize().getWidth();
    frameHeight = (int) owner.getPreferredSize().getHeight();

    // Create the graphics buffer and Graphics2D context
    offScreenBuffer = (BufferedImage) owner.createImage(frameWidth, frameHeight);
    backgroundBuffer = (BufferedImage) owner.createImage(frameWidth, frameHeight);
    osbg2 = offScreenBuffer.createGraphics();
    bg2 = backgroundBuffer.createGraphics();
    updateNeeded = true;
  }

  /**
   * Tile the background with the specifed tile image.
   * 
   * @param theImage
   *          An <CODE>Image</CODE> to use to create a tiled background.
   */
  public void setBackgroundTile(Image theImage) {

    Image tiles = TiledImage.createTiledImage(theImage, owner.getPreferredSize().width,
        owner.getPreferredSize().height, owner);
    setBackground(tiles);
  }

  /**
   * Turns on debugging. For the DisplayManager, debugging will draw all bounding rectangles for everything that
   * implements the interface.
   * 
   * @param debug
   *          As of Version 6.0, this method is not used. (Kept for future use)
   */
  public void setDebugGraphics(boolean debug) {
    debugging = debug;
  }

  /**
   * Returns the current drawing buffer. Useful for SimElements that use knowledge of the visual appearance of the
   * simulation (such as sensors)
   * 
   * @return The current drawing buffer, as a <CODE>BufferedImage</CODE>
   */
  public BufferedImage getImageBuffer() {

    if (bg2 == null) {

      return null;
    }

    return backgroundBuffer;
  }

  /**
   * Repaint the screen. Draws all static elements before mobile ones to ensure that mobile elements are always
   * displayed on top, and that sensors will detect events from the background. May need to be updated eventually to
   * update sensors last so they will also be able to respond accurately to mobile elements. In this version, there is
   * no guarantee that sensor results will be consistent when detecting against mobile elements in the simulation.
   * 
   * @param g
   *          The <CODE>Graphics2D</CODE> context.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (osbg2 == null) {

      return;
    }

    if (owner.elementManager.staticChanged || updateNeeded) {
      updateBackground();
      updateNeeded = false;
      owner.elementManager.staticChanged = false;
    }

    // Draw the background
    osbg2.drawImage(backgroundBuffer, null, 0, 0);

    Object[] elements = owner.elementManager.mobileElements.toArray();

    // Now draw the mobile elements. Added this to ensure that all static
    // elements are drawn first, so sensors will work (more) correctly.
    // May need to eventually ensure that sensors are updated last.
    for (int elem = 0; elem < elements.length; elem++) {

      if (elements[elem] instanceof Visible) {
        ((Visible) elements[elem]).draw(osbg2);

        if (debugging == true) {

          if (elements[elem] instanceof SimElement) {
            osbg2.setColor(Color.blue);

            Area elemArea = ((SimElement) elements[elem]).getArea();
            osbg2.draw(elemArea);
            osbg2.setColor(Color.black);
            osbg2.draw(elemArea.getBounds2D());
            osbg2.drawRect((int) ((SimElement) elements[elem]).getPosition().getX(),
                (int) ((SimElement) elements[elem]).getPosition().getY(), 2, 2);
          }
        }
      }
    }

    Graphics2D g2 = (Graphics2D) g;

    /*
     * The following will rescale the image so that it always completely fits inside the simulation frame.
     * Unfortunately, a significant amount of processing is required, so the simulation runs significantly slower. Maybe
     * JDK1.4 VolatileImage model will eecs.editor.help...
     */

    //if (frameWidth != owner.getWidth() || frameHeight != owner.getHeight()) {
    //    scaleTransform = new AffineTransform();
    //    double hScale = (double)background.getWidth(this)
    //            / (double)owner.getWidth();
    //    double vScale = (double)background.getHeight(this)
    //            / (double)owner.getHeight();
    //    scaleTransform.scale(1.0/hScale,1.0/vScale);
    //}
    //g2.drawImage(offScreenBuffer,scaleTransform,this);
    g2.drawImage(offScreenBuffer, null, 0, 0);
    g2.dispose();
  }

  /**
   * The background consists of any background image and all static elements in the simulation. If the DisplayManager
   * notices that any of these components have changed, it will update. Otherwise, the background "image" is prerendered
   * (with all static elements added) to increase speed.
   */
  void updateBackground() {
    bg2.drawImage(background, 0, 0, null);

    // Draw the static elements
    Object[] statics = owner.elementManager.staticElements.toArray();

    for (int i = 0; i < statics.length; i++) {

      if (statics[i] instanceof Visible) {
        ((Visible) statics[i]).draw(bg2);
      }
    }
  }
}