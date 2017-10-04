package eecs.jago.instructor.burrow;


import jago.element.*;

/**
 * A simulated radio emitter element that dsiplays a star as a 
 * default emitter with the capablitly to display another image. 
 * Modified from class DozerBot.java
 * 
 * @version 1.0
 * @author Guy M. Burrow
 */

public class Emitter
    extends VisibleElement {

	// Flag to signal if the emitter is destroyed.
    boolean destroyed = false;		

    /**
     * Creates a new emitter element with the default 
	 * star image.
     */
    public Emitter() {
        setAppearance("images/emitter.gif");
    }

	/**
	 * Creates a new emitter with a specified image passed 
	 * to the constructor.
	 * @param imageFile path and name of the emitter image.
	 */
	public Emitter(String imageFile) {
		setAppearance(imageFile);
	}

    /**
     * Removes the emitter from the simulation.
     */
	 public void removeFromSim() {
      host.remove(this);
     } // end removeFromSim

} // end Emitter