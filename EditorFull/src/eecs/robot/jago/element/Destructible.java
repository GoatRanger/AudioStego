package eecs.robot.jago.element;

/**
 * This interface describes any object that can be destroyed during the course of the simulation. In this context, the
 * object will change state, perhaps react to its surroundings, and the visual representation will change when the
 * element is destroyed.
 */
public interface Destructible {
  /** Indicates that the element was destroyed. */
  public static final int DESTROYED = 99;

  /**
   * Sets the flag indicating if this object was destroyed.
   */
  public void setDestroyed();

  /**
   * Returns the current state of this object.
   * 
   * @return <CODE>true</CODE> if this element was destroyed.
   */
  public boolean isDestroyed();
}