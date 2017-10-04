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
 * Creates an omni-directional light, similar to a standard light bulb.
 *
 * @version 1.0
 * @author Karl A. Gossett
 */
public class BrightLight
    extends VisibleElement
    implements LightSource {

    /** The maximum intensity for this light. */
    public static final int MAX_INTENSITY = 100;

    /** The intensity of this light source (0-100) */
    protected int intensity = 0;

    /** The size of the light source.  Potentially changeable */
    protected int currentSize = intensity;

    /**
     * Creates a new BrightLight with maximum intensity.
     */
    public BrightLight() {
        this(MAX_INTENSITY);
    }

    /**
     * Creates a new BrightLight with a specified intensity.
     *
     * @param intensity The intensity of the light, between 0 and
     *        <CODE>MAX_INTENSITY</CODE>
     */
    public BrightLight(int intensity) {
        super();
        this.intensity = intensity;
        this.size = new java.awt.Dimension(30, 30);
        createArea();
    }

    /**
     * Sets the intesity of this BrightLight.  If an out-of-range intensity is
     * requested, the default intensity is set (100).
     *
     * @param intensity   The desired intensity of this BrightLight
     */
    public void setIntensity(int intensity) {

        if (intensity >= 0 && intensity <= 100) {
            this.intensity = intensity;
        } else {

            // Out of bounds, default intensity is maximum
            this.intensity = 100;
        }

        currentSize = intensity / 10;
    }

    public void setSize(int width, int height){
    	this.size=new java.awt.Dimension(width,height);
    	createArea();
		
    }
    /**
     * Returns the intensity of this light
     *
     * @return The intensity.
     */
    public int getIntensity() {

        return intensity;
    }

    /**
     * Draws this light in the supplied context.
     *
     * @param g2 The <CODE>Graphics2D</CODE> context.
     */
    public void draw(java.awt.Graphics2D g2) {

        java.awt.Color c = new java.awt.Color(250, 250, 210, (int)(intensity * 2));
        java.awt.Color oldColor = g2.getColor();
        g2.setColor(c);
        g2.fill(myShape);
        g2.setColor(oldColor);
    }

    /**
     * Creates the shape of this object.  The default shape is a
     * <CODE>Rectangle2D</CODE>, so this method should be overridden for any
     * objects with a non-rectangular shape.
     */
    protected void createArea() {
        myShape = new java.awt.geom.Area(new java.awt.geom.Ellipse2D.Double(location.getX()
                                                    - (size.getWidth() / 2),
                                                location.getY()
                                                    - (size.getHeight() / 2),
                                                size.getWidth(),
                                                size.getHeight()));
    }
}