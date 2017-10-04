/*
 * Created on Mar 1, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package jago.element;

/** A solid object in the simulation that may have special properties that would
 *  initiate reactions different than a standard SolidElement such as a <code>Wall</code>.
 *  By default, an <code>Obstacle</code> is not <code>Pushable</code> or <code>Mobile</code>,
 *  but can extend either interface to allow additional functionality.
 * @author DM0266
 *
 */
public abstract class Obstacle extends SolidElement {
  // Not sure this is abstract...unless there's an ability to be destroyed or something, or perhaps 
  // an added field of mobility (as opposed to testing instanceof Mobile). 
  
  
  // Might also be abstract if the handleCollision is labeled as abstract, forcing a unique implementation
  // since SolidElement implements an empty handleCollision
  public abstract void handleCollision(Collidable collisionElement);

}