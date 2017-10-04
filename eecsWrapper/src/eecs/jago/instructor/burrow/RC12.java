package eecs.jago.instructor.burrow;

import jago.element.*;

/**
 * A simulated RC-12 aircraft that can be set to a specific speed
 * between 105 and 240 knots, get the Estimated Time Enroue (ETE)
 * to a specific point, get the distance (DME) to a specific point,
 * get the current speed.  In addition the RC12 is destructible
 * when it collides with another RC12 object. 
 * Modified from class DozerBot.java
 * 
 * @version 1.0
 * @author Guy M. Burrow
 */

public class RC12
    extends StandardRobot 
	implements Destructible {

	protected double speed;		// Speed of the aircraft in knots
	protected double DME;		// Distance in miles to a given point
	protected double ETE;		// Estmated Time Enroute in minutes to a given point
	protected static final double POINTS_PER_MILE = 125/12; // Scales the simulation
															// screen to the operational
															// graphics															

    /**
     * Creates a new RC12 with the default image with an initial speed of 120 knots.
     */
    public RC12() {
        super();
        setAppearance("images/RC12.gif");
		setNewSpeed(120);
		goForward();
    }

    /**
     * Sets speed of aircraft in knots. Aircraft is destroyed if speed >240 or <105. 
	 *
	 * @param newSpeed desired speed of the RC12 in knots.
     */
    public void setNewSpeed(int newSpeed) {
		// Limits desired speed to a value between 105 and 240 knots.
		if ((newSpeed > 240) || (newSpeed < 105))
		{
			setDestroyed();
			halt();
		} else {
			speed = newSpeed;
			// Scaled power for realistic aircraft speed on screen.
			power = 0.03 + ((speed - 100)/1500);
			goForward();
		}
    } // end setNewSpeed()

    /**
     * Gets speed of aircraft in knots.
	 *
	 * @return the current speed of the RC12 in knots.
     */
    public double getSpeed() {
		return (int)speed;
    } 

    /**
     * Returns the distance of the aircraft from a specific point.  This method 
	 * measures the distance left to right (i.e. based on the difference in the
	 * x-coordiantes of the RC12 and the next point.
	 *
	 * @param nextPoint X-coordinate of the next point to measure the distance to.
	 * @return the distnace to the next point in miles.
     */    
	public double getDME(int nextPoint)
	{
		// Determine the x-axis distance between the RC12 and the desired point.
		double returnValue = Math.abs((getPosition().getX() - nextPoint)/POINTS_PER_MILE);
		// Rounds the number to two significant decimal places.
		return ((double)Math.round(returnValue*100))/100;
	} 

    /**
     * Returns the estimated time enrout of the aircraft to a specific point.
	 *
	 * @param nextPoint X-coordinate of the next point to measure the ETE to.
	 * @return the ETE to the next point in minutes.
     */    
	public double getETE(int nextPoint)
	{
		// ETE is the distance to the next point divided by the speed * 60 minutes.
		return (getDME(nextPoint)/getSpeed()*60);
	}
       	
    /**
     * Destroys the RC12.
     */
    public void setDestroyed() {
        state = Destructible.DESTROYED;
 //       setAppearance("images/bang.gif");
    }

    /**
     * Returns the current state of this object.
     * 
     * @return true if destroyed, false if not destroyed.
     */
 	public boolean isDestroyed() {
        return (state == Destructible.DESTROYED);
    }

    /**
     * Overridden to handle another RC12 object.
     * 
     * @param element simulation element collided with.
     */
    public void handleCollision(Collidable element) {
	
		if ((element instanceof RC12)){
			setAppearance("images/bang.gif");
			setDestroyed();		
		} else {
			// Do nothing.
		}
    }

    /**
     * Overridden so that the RC12 can be destroyed.
     */
    public void updatePosition() {

        if (state == DESTROYED) {
            host.remove(this);
        } else {
            super.updatePosition();
        }
    }
} // end RC12