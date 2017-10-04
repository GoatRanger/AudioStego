package eecs.robot.jago;


import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import eecs.robot.jago.element.*;

/**
 * Determines if two objects have physically collided. This class uses the overlap of the bounding rectangle of the
 * objects to determine where the two objects overlap, then performs pixel-based collision detection between the two
 * objects in that region.
 */
public class CollisionDetector {

  /** The context for drawing images in */
  Graphics2D bufferGraphics;

  /** The image buffer used for pixel-based detection */
  BufferedImage collisionBuffer;

  /** The height of the drawing region */
  int height;

  /** The width of the drawing region */
  int width;

  /**
   * Creates a new CollisionDetector.
   */
  public CollisionDetector() {
    width = 0;
    height = 0;
  }

  /**
   * Determines if there is a collision between the two elements.
   * 
   * @param first
   *          One SimElement potentially involved in a collision
   * @param second
   *          The other SimElement to check
   * @return <CODE>true</CODE> if a collision occured
   */
  public boolean isCollision(SimElement first, SimElement second) {

    return checkCollision((Collidable) first, (Collidable) second);
  }

  /**
   * Determines if two Collidable objects had a physical collision. This method should only be used when neither object
   * completely fills the bounding rectangle. When one object completely fills the bounding rectangle (such as a wall or
   * rectangular building), use
   * 
   * @param elem1
   *          The first element that may have collided
   * @param elem2
   *          The second element that may have collided
   * @return <CODE>true</CODE> if the two elements collided.
   * @since 4.1
   */
  boolean checkCollision(Collidable elem1, Collidable elem2) {

    if (width == 0 || height == 0) {

      return true;
    }

    bufferGraphics.setTransform(new AffineTransform());

    Rectangle2D elem1Bounds = elem1.getBounds2D();
    Rectangle2D elem2Bounds = elem2.getBounds2D();
    Rectangle2D overlap = new Rectangle2D.Double();
    Rectangle2D.intersect(elem1Bounds, elem2Bounds, overlap);

    double minX = Math.min(elem1Bounds.getX(), elem2Bounds.getX());
    double minY = Math.min(elem1Bounds.getY(), elem2Bounds.getY());
    AffineTransform at = new AffineTransform();
    at.translate(-minX, -minY);
    overlap.setRect(overlap.getX() - minX, overlap.getY() - minY, overlap.getWidth(), overlap.getHeight());
    bufferGraphics.setTransform(at);

    // Clear the background of the overlapping region
    bufferGraphics.setColor(Color.black);
    bufferGraphics.fill(overlap);

    // Get the overlapping pixels from the first image
    elem1.draw(bufferGraphics);

    int[] firstPixels = getPixels(collisionBuffer, overlap);

    // Clear the background to check the second image
    bufferGraphics.setColor(Color.black);
    bufferGraphics.fill(overlap);
    elem2.draw(bufferGraphics);

    int[] secondPixels = getPixels(collisionBuffer, overlap);
    boolean collided = false;
    checkCollision: if (firstPixels != null && secondPixels != null) {

      for (int i = 0; i < firstPixels.length; i++) {

        if (firstPixels[i] != 0 && secondPixels[i] != 0) {
          collided = true;

          break checkCollision;
        }
      }
    }

    // Ensure objects are released to the garbage collector
    firstPixels = null;
    secondPixels = null;

    return collided;
  }

  /**
   * Establishes the graphics buffers used for collision detection.
   * 
   * @param width
   *          The width of the buffer, in pixels
   * @param height
   *          The height of the buffer, in pixels
   * @since 5.1
   */
  void createBuffer(int width, int height) {
    this.width = width;
    this.height = height;
    collisionBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    bufferGraphics = collisionBuffer.createGraphics();

    /*
     * Attempt to reduce the rendering time. If the system allows, this will render all objects for speed during
     * collision detection. Although this has the potential to make the collisions detect differently than they appear,
     * variation will be minimal.
     */
    bufferGraphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
    bufferGraphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
  }

  /**
   * Gets a rectangular region of pixels.
   * 
   * @param img
   *          The graphics buffer to capture from.
   * @param overlap
   *          The region to get the pixels from.
   * @return An array of integers--the pixels colors.
   */
  private int[] getPixels(BufferedImage img, Rectangle2D overlap) {

    int[] pixels = new int[(int) (overlap.getWidth() * overlap.getHeight())];
    img.getRGB((int) overlap.getX(), (int) overlap.getY(), (int) overlap.getWidth(), (int) overlap.getHeight(), pixels,
        0, (int) overlap.getWidth());

    return pixels;
  } // end handlePixels
}