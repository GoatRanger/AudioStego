package eecs.instructor.mann;

import java.awt.Image;
import jago.ImageLoader;
import jago.element.*;
import java.applet.AudioClip;
import javax.swing.JApplet;


/**
 * A simulated radio emitter element that dsiplays a star as a default emitter
 * with the capablitly to display another image. Modified from class
 * DozerBot.java
 * 
 * @version 1.0
 * @author Guy M. Burrow
 */
public class Explosion extends MobileElement {
  static private AudioClip explosionSound;
  static Image fire = ImageLoader.getImage(Explosion.class,
      "/eecs/instructor/mann/images/Explode-04.gif");
  static Image smoke = ImageLoader.getImage(Explosion.class,
      "/eecs/instructor/mann/images/smoke.gif");
  long timeAdded;
  private int explosionTime;

  /**
   * Creates a new element with the default image.
   */
  public Explosion() {
    timeAdded = 0;//System.currentTimeMillis();
    explosionSound = JApplet.newAudioClip(getClass().getResource(
        "sounds/EXPLOSI1.wav"));
  }

  /**
   * @param imageFile
   *          path and name of the image.
   */
  public Explosion(String imageFile) {
    setAppearance(imageFile);
    createArea();
  }

  void setLocation(int x, int y) {
    this.location.setLocation(x, y);
  }

  public void makeFire(int milliseconds) {
    setAppearance("/eecs/instructor/mann/images/Explode-04.gif");
    createArea();
    timeAdded = System.currentTimeMillis();
    explosionTime = milliseconds;
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    explosionSound.play();
  }

  public void makeSmoke(int milliseconds) {
    setAppearance("/eecs/instructor/mann/images/smoke.gif");
    createArea();
    timeAdded = System.currentTimeMillis();
    explosionTime = milliseconds;
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    explosionSound.play();
  }

  /**
   * Removes the emitter from the simulation.
   */
  public void removeFromSim() {
    host.remove(this);
  } // end removeFromSim

  protected void runTaskStep() {
    if (this.inASimulation() && timeAdded > 0) {
      if (System.currentTimeMillis() - timeAdded > explosionTime) {
        host.remove(this);
      }
    }
  }
} // end
