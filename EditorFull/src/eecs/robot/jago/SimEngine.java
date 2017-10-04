/*
 * Copyright (C) 2002 USMA This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU General Public License along with this program; if not,
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

/*
 * Originally based upon the Gamelication Toolkit developed by Mark Tacchi and Jeremy Hutchins as well as the book "The
 * Black Art of Java Game Programming." This version was derived from the modifications made for the Jago simulation
 * created by Rusl Flowers.
 */
package eecs.robot.jago;


import java.applet.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;
import eecs.robot.jago.element.*;

/**
 * The simulation engine that is responsible for managing the addition, deletion, and updating of all elements of the
 * simulation. <BR>
 * <P>
 * The simulation uses a scale that was originally based on the Lego Mindstorms based robot. Using the standard robot
 * images (a robot with tracks and no sensors or bumpers is 38x30 pixels), this defaults to a scale of 1:6 (one inch = 6
 * pixels). Although the perception of this scale can be skewed by adding other <CODE>SimElement</CODE> s that do not
 * adhere to this scale, it is important to remember as the default when considering movement speeds.
 * </p>
 * 
 * @version 6.2 April 2002
 * @author Rusl Flowers
 * @author Karl A. Gossett
 */

//public class SimEngine extends JApplet {
public class SimEngine extends JPanel implements Realism {

  /**
   * Detects collisions between two <CODE>Collidable</CODE> elements. The SimEngine owns the detector, but gives it to
   * the <CODE>ElementManager</CODE> to use.
   */
  protected CollisionDetector collisionDetector = new CollisionDetector();

  /** Manages double buffering and updating the background */
  protected DisplayManager displayManager = new DisplayManager(this);

  /** Manages all elements */
  protected ElementManager elementManager = new ElementManager(this);

  /** Acts upon Timer actions */
  protected TimerListener listener;

  /** The Frames Per Second of the animation */
  int fps = 60;

  /** The timer that controls the simulation */
  javax.swing.Timer timer;

  /** The background music */
  private AudioClip background;

  /** Time keeping variable */
  long currentTickTimeMillis = System.currentTimeMillis();

  long elapsedTime = 0;

  private boolean playMusic = false;

  private long startTickTimeMillis = 0;

  /** Time keeping variable */
  long lastTickTimeMillis = currentTickTimeMillis;

  public SimEngine() {
    super();
    init();
  }

  /**
   * Sets the background image.
   * 
   * @param image
   *          The <CODE>Image</CODE> to use as a background. Automatically resizes the simulation to the size of the
   *          image.
   */
  public void setBackgroundImage(Image image) {

    int width = image.getWidth(this);
    int height = image.getHeight(this);
    setPreferredSize(new Dimension(width, height));
    displayManager.setBackground(image);
    revalidate();
    repaint();
  }

  /**
   * Sets the background tile.
   * 
   * @param image
   *          The <CODE>Image</CODE> to use as a tiled background. This method will tile the specified image to fill
   *          the entire simulation (it will not change the size of the simulation).
   */
  public void setBackgroundTile(Image image) {
    displayManager.setBackgroundTile(image);
    repaint();
  }

  /**
   * Turns debugging on and off
   * 
   * @param debug
   *          <CODE>true</CODE> to turn debugging on.
   */
  public void setDebugging(boolean debug) {
    displayManager.setDebugGraphics(debug);
  }

  /**
   * Returns the animation delay (time between updates of the animation)
   * 
   * @return The delay, in ms
   */
  public double getDelay() {

    return ((fps > 0) ? (1000 / fps) : 100);
  }

  /**
   * Returns the current DisplayManager.
   * 
   * @return The <CODE>DisplayManager</CODE> that this SimEngine is using.
   */
  public DisplayManager getDisplayManager() {

    return displayManager;
  }

  public java.util.List getLightSources() {

    return new java.util.ArrayList(elementManager.lightSources);
  }

  /**
   * Setter for property playMusic.
   * 
   * @param playMusic
   *          New value of property playMusic.
   */
  public void setPlayMusic(boolean playMusic) {
    this.playMusic = playMusic;

    if (background != null) {

      if (!playMusic) {
        background.stop();
      }
      else if (timer.isRunning()) {
        background.loop();
      }
    }
  }

  /**
   * Getter for property playMusic.
   * 
   * @return Value of property playMusic.
   */
  public boolean isPlayMusic() {

    return playMusic;
  }

  /**
   * Overriden to ensure the collision detector is updated anytime the simulation changes size.
   * 
   * @param d
   *          DOCUMENT ME!
   */
  public void setPreferredSize(Dimension d) {
    super.setPreferredSize(d);
    collisionDetector.createBuffer(d.width, d.height);
  }

  public void setRealismLevel(int level) {
    elementManager.setRealismLevel(level);
  }

  /**
   * Gets the realism setting (as a constant)
   * 
   * @return Returns one of the Realism constants, currently {@link#IDEALISTIC},{@link #REALISTIC}, or
   *         {@link #FIXED_REALISTIC}
   */
  public int getRealismLevel() {

    return elementManager.getRealismLevel();
  }

  /**
   * Adds an element to the sim engine's element manager. Changes:
   * <ul>
   * <li>(5.1) Allows All elements that implement Reflective to be added anywhere</li>
   * <li>(5.0) Checks for overlap and denies addition of overlapping objects</li>
   * </ul>
   * 
   * @param element
   *          The <CODE>SimElement</CODE> to add to the simulation.
   * @param x
   *          The desired x (horizontal) coordinate for the element.
   * @param y
   *          The desired y (vertical) coordinate for the element.
   * @since 4.1
   */
  public void add(SimElement element, double x, double y) {
    Point2D loc = new Point2D.Double(x, y);
    if (elementManager.add(element)) {
      element.addedTo(this, loc);
    }
    else {
      System.out.println(element.toString() + " is already in" + " the simulation.");
    }
  }

  /**
   * Adds the listener as a listener to the simulation execution timer.
   * 
   * @param listener
   *          an <CODE>ActionListener</CODE> to add
   */
  public void addActionListener(ActionListener listener) {
    timer.addActionListener(listener);
  }

  /**
   * Calculates the difference in milliseconds between the current tick and the last tick. Used for elements that
   * require the real-time that has elapsed during the simulation. Although the simulation notifies elements at regular
   * intervals, due to thread timing, the intervals are not guaranteed to correlate with realtime. This method ensures
   * that real-time is reported to elements.
   * 
   * @return The amount of time that has passed since the last tick, in milliseconds
   */
  public long deltaTickTimeMillis() {
    return (currentTickTimeMillis - lastTickTimeMillis);
  }

  /**
   * Returns the total elapsed time in the simulation
   * 
   * @return DOCUMENT ME!
   */
  public long elapsedTimeMillis() {

    //return (currentTickTimeMillis - startTickTimeMillis);
    return elapsedTime;
  }

  /**
   * Removes an element from the sim engine's element manager.
   * 
   * @param element
   *          The <CODE>SimElement</CODE> to remove.
   */
  public void remove(SimElement element) {
    elementManager.remove(element);
  }

  /**
   * Removes the listener so it won't react to the simulation timer.
   * 
   * @param listener
   *          The <CODE>ActionListener</CODE> to remove
   */
  public void removeActionListener(ActionListener listener) {
    timer.removeActionListener(listener);
  }

  /**
   * Removes all elements from the simulation.
   */
  public void removeAllElements() {
    elementManager.removeAll();
  }

  public void setStaticChanged() {
    elementManager.setStaticChanged(true);
  }

  /**
   * Start the thread that runs the simulation.
   */
  public void start() {

    if (!timer.isRunning()) {
      timer.start();

      // If it wasn't running before, set the clocks
      if (startTickTimeMillis == 0) {
        startTickTimeMillis = System.currentTimeMillis();
        currentTickTimeMillis = startTickTimeMillis;
      }

      currentTickTimeMillis = System.currentTimeMillis();

      // Start the background music
      if (playMusic && background != null) {
        background.loop();
      }
    }

  }

  /**
   * Stops the simulation
   */
  public void stop() {

    if (timer.isRunning()) {
      timer.stop();
    }

    if (background != null) {
      background.stop();
    }
  }

  /**
   * The initialization method of the SimEngine
   */
  void init() {
    // Give the collision detector to the element manager
    elementManager.setCollisionDetector(collisionDetector);
    add(displayManager);

    // Set the delay and create a Timer to control the simulation
    int delay = (fps > 0) ? (1000 / fps) : 100;
    listener = new TimerListener();
    timer = new Timer(delay, listener);
    timer.setInitialDelay(100);
    timer.setCoalesce(true);

    // Add the element manager to the list of listeners, so it will
    // be controlled by the timer
    timer.addActionListener(elementManager);

    try {
      background = Applet.newAudioClip(getClass().getResource("sounds/techno.rmf"));
    }
    catch (Exception e) {
      background = null;
    }
  }

  /**
   * Inner class to react to timer events
   */
  class TimerListener implements ActionListener {

    /**
     * Causes the SimEngine to update, as a result of an event. The only events that this engine should be triggered to
     * act upon are the Timer events from the internal timer.
     * 
     * @param event
     *          The <CODE>ActionEvent</CODE>.
     */
    public void actionPerformed(ActionEvent event) {

      lastTickTimeMillis = currentTickTimeMillis;
      currentTickTimeMillis = System.currentTimeMillis();

      elapsedTime += deltaTickTimeMillis();
      repaint();
    }
  }
}