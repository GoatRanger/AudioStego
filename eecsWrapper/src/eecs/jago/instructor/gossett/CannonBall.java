/*
 * CannonBall.java
 *
 * Created on April 23, 2003, 6:36 PM
 */
package eecs.jago.instructor.gossett;

import jago.SimEngine;
import jago.element.Collidable;
import jago.element.Destructible;
import jago.element.MobileElement;

import java.awt.geom.*;

/**
 *
 * @author  dk8685
 */
public class CannonBall extends MobileElement implements Destructible {
    static final double GRAVITY = 9.81;
    static final double SIM_FACTOR = 32.0;
    int angle;
    long startFlight;
    double velocity;
    double velX0;
    double velY0;
    double x0;
    double y0;
    boolean isDestroyed = false;
    /** Creates a new instance of CannonBall */
    public CannonBall(int angleOfFire, int velocity) {
        super();
        setAppearance("cannonball_sm.gif");
        angle = angleOfFire;
        this.velocity = velocity;
        
    }
    
    public void addedTo(SimEngine sim, Point2D loc) {
        super.addedTo(sim, loc);
        //velVector.setMagnitude(0.62);
        velVector.setMagnitude(velocity/SIM_FACTOR);
        velVector.setDirection(angle);
        startFlight = sim.elapsedTimeMillis();
        jago.element.PolarPoint p = new jago.element.PolarPoint(velocity,angle);
        
        velX0 = p.getX();
        velY0 = p.getY();
        x0 = loc.getX();
        y0 = loc.getY();
    }
    
    /**
     * React to a collision with another Collidable object
     *
     * @param collisionElement DOCUMENT ME!
     */
    public void handleCollision(Collidable collisionElement) {
        setDestroyed();
        host.remove(this);
    }
    
    /**
     * Undocumented
     */
    public void updateVelocity() {
        if (host != null) {
            double time =(host.elapsedTimeMillis() - startFlight) / 1000.0;
            
      
            double vY = velY0+(GRAVITY*time);
            velocity = Math.sqrt(Math.pow(velX0,2) + Math.pow(vY,2));
            velVector.setMagnitude(velocity/SIM_FACTOR);
            double dir = Math.atan2(vY,velX0);
            velVector.setDirection((Math.toDegrees(dir)+90)%360);
        }
    }
    
    public void updatePosition() {
        
        if (location.getY() > 600) {
            setDestroyed();
            host.remove(this);
        } else {
            super.updatePosition();
        }
    }
    /**
     * Undocumented
     */
    protected void runTaskStep() {
        updatePosition();
        updateVelocity();
        previousVel.setDirection(velVector.getDirection());
    }
    
    /** Returns the current state of this object.
     *
     * @return <CODE>true</CODE> if this element was destroyed.
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }
    
    /** Sets the flag indicating if this object was destroyed.
     */
    public void setDestroyed() {
        isDestroyed = true;
    }
    
}
