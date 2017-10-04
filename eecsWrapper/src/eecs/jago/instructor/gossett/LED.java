/*
 * LED.java
 *
 * Created on January 18, 2003, 9:39 AM
 */

package eecs.jago.instructor.gossett;

import java.applet.AudioClip;
import javax.swing.JApplet;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author  Karl A. Gossett
 */
public class LED extends jago.element.BrightLight implements jago.element.Mobile, jago.element.ReflectiveColors {
    
    int color;
    AudioClip keypress = JApplet.newAudioClip(getClass().getResource("sounds/ddloo.au"));
    /** Creates a new instance of LED */
    public LED() {
        this(10);
    }
    
    public LED(int size) {
        super();
        color = LIGHT_GRAY;
        currentSize = size;
        this.size = new Dimension(currentSize, currentSize);
        createArea();
        intensity = 100;
    }
    
    protected void createArea() {
      myShape = new java.awt.geom.Area(new RoundRectangle2D.Double(location.getX()
                                                  - (size.getWidth() / 2),
                                              location.getY()
                                                  - (size.getHeight() / 2),
                                              size.getWidth(),
                                              size.getHeight(),5,5));
   }
    public void setColor(int color) {
        keypress.play();
        if (color != RED && color != GREEN && color != LIGHT_GRAY) {
            color = RED;
        }
        this.color = color;
    }
    
    public void setOff() {
        color = LIGHT_GRAY;
    }
    
    public void draw(Graphics2D g2) {
        Color c;
        if (color == GREEN) {
            c = new Color(0, 255, 0);
        } else if (color == RED) {
            c = new Color(255, 0, 0);
        } else {
            c = new Color(64, 64, 64);
        }
        Color oldColor = g2.getColor();
        g2.setColor(c);
        g2.fill(myShape);
        g2.setColor(oldColor);
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

    }
    
    /** Gets the angular velocity of this element. Convention is positive
     * values are clockwise.
     *
     * @return DOCUMENT ME!
     */
    public double getAngularVelocity() {
        return 0;
    }
    
    /** Gets the current heading
     *
     * @return DOCUMENT ME!
     */
    public double getHeading() {
        return 0;
    }
    
    /** Gets the last valid location of this object
     *
     * @return DOCUMENT ME!
     */
    public java.awt.geom.Point2D getPreviousPosition() {
        return location;
    }
    
    /** Gets the current velocity.
     *
     * @return DOCUMENT ME!
     */
    public double getVelocity() {
        return 0;
    }
    
    /** Determines the new power of the mobile element
     *
     * @param speed DOCUMENT ME!
     */
    public void setPower(double speed) {
    }
    
    /** Updates the current location based on velocity and heading.
     */
    public void updatePosition() {
    }
    
}
