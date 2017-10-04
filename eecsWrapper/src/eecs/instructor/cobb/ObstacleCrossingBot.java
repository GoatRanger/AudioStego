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

package eecs.instructor.cobb;


//import eecs.jago.instructor.cobb.Concertina2;

import java.applet.AudioClip;
import java.awt.geom.AffineTransform;

import javax.swing.JApplet;

import eecs.simulation.PhaseLine;
import eecs.simulation.WaterElement;
import jago.element.*;


/**
 * This robot has a blade and a light sensor on RCX-1, it suffers if runs into
 * concertina or dead tanks, and it can remove mines.
 */
public class ObstacleCrossingBot
    extends StandardRobot
    implements Destructible {
	private static final int OBSTACLE1_Y_MAX = 352;
	private static final int OBSTACLE1_Y_MIN = 348;
	private static final int OBSTACLE2_Y_MAX = 128;
	private static final int OBSTACLE2_Y_MIN = 122;
	boolean correctlyCrossedConcertina=true;
	boolean correctlyCrossedFire=true;
	boolean hasCrossedConcertina=false;
	boolean isFirstTime=true;
	boolean bridgeBreached=false;
    private long timeDestroyed;
	 private AudioClip sound,sound2;
	 int x1=-1;
	 int x2=-1;
	 int y1=-1;
	 int y2=-1;
	 

    public ObstacleCrossingBot() {
        super();
        //setAppearance("images/dozer.gif");
        //addSensor(Monitor.RCX_1, Sensor.LIGHT);
        sound2 = JApplet.newAudioClip(StandardRobot.class
                .getResource("sounds/explosion2.au"));

       sound = JApplet.newAudioClip(StandardRobot.class
                             .getResource("sounds/helmet_buzzer.au"));

      
    }
    
    public boolean getBridgeBreached(){
    	return this.bridgeBreached;
    }
    
    
    /**
     * Makes this DozerBot destroy itself.
     */
    public void setDestroyed() {
        state = Destructible.DESTROYED;
     //timeDestroyed=System.currentTimeMillis();
        setAppearance("images/bang.gif");
       
       	
       }
   
    
  
    
    
    /**
     * Returns the current state of this object.
     * 
     * @return DOCUMENT ME!
     */
    public boolean isDestroyed() {

        return (state == Destructible.DESTROYED);
    }

    /**
     * Overridden to handle concertina wire, mines, and dead tanks.
     * 
     * @param element DOCUMENT ME!
     */
    public void handleCollision(Collidable element) {
    	
        if (element instanceof PhaseLine){
        	
        	//System.out.println(getLocation());
        	int currentY=(int)getLocation().getY();
        	int currentX=(int)getLocation().getX();
        	
        	if (currentX>500 && isFirstTime){
        		isFirstTime=false;
        		this.bridgeBreached=true;
        		System.out.println("Hit the safe Zone");
        		new Thread(new Runnable(){
                 	public void run(){

                 		

                   
                   PolarPoint savedVel = (PolarPoint)previousVel.clone();
                   double xMove = 630-location.getX();
                   double yMove = 380-location.getY();
                   AffineTransform at = new AffineTransform();
                   at.rotate(Math.toRadians(
                                     velVector.getDirection() - savedVel.getDirection()),
                             location.getX() + size.getWidth() / 2.0,
                             location.getY() + size.getHeight() / 2.0);
                   at.translate(xMove, yMove);
                   myShape.transform(at);

                   // Move the specified amount by translating the current location
                   location.setLocation(location.getX() + xMove, location.getY() + yMove);
                   // If you were to drop it on something, this would break, but assuming
                   // moving to a valid location, reset previous location to be the new spot.
                   // Should be unnecessary to do this (next update would fix it)
                   previousLocation.setLocation(location);
                   previousShape = (java.awt.geom.Area)myShape.clone();

        	        }
        		}).start();
        	}
        	//System.out.println("checking to see if we are near an obstacle");
        	if (currentY>OBSTACLE1_Y_MIN && currentY<OBSTACLE1_Y_MAX){
        		setX1(currentX);
        		setY1(currentY);
        	    //System.out.println(currentX+"at obstacle 1");
        	}
        	else if (currentY>OBSTACLE2_Y_MIN && currentY<OBSTACLE2_Y_MAX){
        		setX2(currentX);
        		setY2(currentY);
        	   // System.out.println(currentX+"at obstacle 2");
        	}
        	
     }  
      	
        
        
        else if (element instanceof Wall) {
            //setPower(2);
            
            super.handleCollision(element);
        }
      
       else if (element instanceof WaterElement) 
        {
        	
               setDestroyed();
               super.handleCollision(element); 
               
        } 
       else if (element instanceof Fire) 
       {
             
       	      sound2.play();
       	      correctlyCrossedFire=false;
       	      setPower(1);
       	      this.timeLeftToMove=10;
              setDestroyed();
              
       }
       else if (element instanceof Concertina) 
       {

       	
        setPower(2);
        correctlyCrossedConcertina=false;
        

       	
       	if (!hasCrossedConcertina){
       	   hasCrossedConcertina=true;
       	   
       	   sound.play();
             Thread t=new Thread(new Runnable(){
             	public void run(){
             		int timeTravel=1;
             		
             	
             			timeTravel=timeLeftToMove;
             	
             		//setPower(2);
             		for (int i=0;i<10;i++){
             			//System.out.println("inside concertina loop");
             		    if (Math.random()<.5){
             		    	int temp=(int)Math.round(Math.random()*10);
             		       pivotLeft(temp);
             		        //System.out.println("pivot left"+temp);
             		    }
             		    else{
             		    	int temp=(int)Math.round(Math.random()*10);
             		       	pivotRight(temp);
             		      // System.out.println("pivot right"+temp);
             		    }
             		goForward(25);
             		}
             		if (!(timeTravel>0)){
             			goForward();
             			
             		}
             		else{
             			
             		    
             			goForward(timeTravel);
             		}
             	}
             });
            
             
             t.start();
                   	
         }
       	 else
       	 {
       	 	//System.out.println("not the first detection of concertina wire");
       	 }

       }
       

            // None of the above, so use the standard collision her

            //super.handleCollision(element);

            

        }
        // if a mine, just ignore the collision
    
  
    /**
     * Overridden so that the dozer can die.
     */
    public void updatePosition() {
        long timeElapsed=0;
        timeElapsed=System.currentTimeMillis()-timeDestroyed;
       
        if ((state == DESTROYED)&& (timeElapsed>30)) {
            
            host.remove(this);
        
        } else {
            super.updatePosition();
        }
    }
    
    
	    
	    public  void gotoAngle(float angle) {
	        // in future, use modulo instead of while loop???
	        //System.err.println("Angles: " + angle + " current: " + velVector.getDirection());
	        double difference = angle - velVector.getDirection();

	        //System.err.print("Turning " + difference);
	        while (difference > 180) {
	            difference = difference - 360; // shortest path to goal angle
	        }

	        while (difference < -180) {
	            difference = difference + 360; // shortest path to goal angle
	        }

	        //System.err.println(" which translated to " + difference);
	        if (difference >= 0) {
	            pivotRight((int) difference);
	        } else {
	            pivotLeft((int) -difference);
	        }
	    }
	/**
	 * @return Returns the x1.
	 */
	public int getX1() {
		return x1;
	}
	/**
	 * @param x1 The x1 to set.
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}
	/**
	 * @return Returns the x2.
	 */
	public int getX2() {
		return x2;
	}
	/**
	 * @param x2 The x2 to set.
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}
	
	
	
	 public  void gotoPoint(double x, double y)
	  {
	    // Determine relative points
	    double x1 = 0;
	    double y1 = 0;

	    //double distance=Math.sqrt(x1*x1+y1*y1);
	    double inchDistance = 10;
	    double angle = 0;

	    // Note: since vert axis is reverse of standard, negate y1
	    // y1 = -y1;
	    while (inchDistance > 5)
	    {
	      x1 = x - getLocation().getX();
	      y1 = y - getLocation().getY();

	      //System.err.println("Going relative ("+x1 + ", " + y1 +")");
	      // Calculate angle to go to:
	      angle = Math.atan2(y1, x1);

	      // Calculate distance to travel:
	      //System.err.println("  and angle of " + Math.toDegrees(angle));
	      if (y1 != 0)
	      {
	        inchDistance = y1 / Math.sin(angle);
	      }
	      else
	      {
	        inchDistance = x1 / Math.cos(angle);
	      }

	      //printLine("inch dist "+inchDistance+"distance "+distance);
	      //System.err.println("   and I am " + inchDistance + " away.");
	      angle = Math.toDegrees(angle) + 90;

	      //	printLine("Angle = "+angle+"; getHeading= "+getHeading());
	      // Now convert theory into action:
	      //angle=330;
	      //System.err.println(angle);
	      gotoAngle((float) angle);
	      goForward(75);
	     
	    }
	  
	   
	  
	  }
	/**
	 * @return Returns the y1.
	 */
	public int getY1() {
		return y1;
	}
	/**
	 * @param y1 The y1 to set.
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}
	/**
	 * @return Returns the y2.
	 */
	public int getY2() {
		return y2;
	}
	/**
	 * @param y2 The y2 to set.
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}
	/**
	 * @return Returns the correctlyCrossedConcertina.
	 */
	public boolean hasCorrectlyCrossedConcertina() {
		return correctlyCrossedConcertina;
	}

	/**
	 * @param correctlyCrossedConcertina The correctlyCrossedConcertina to set.
	 */
	public void setCorrectlyCrossedConcertina(boolean correctlyCrossedConcertina) {
		this.correctlyCrossedConcertina = correctlyCrossedConcertina;
	}

	/**
	 * @return Returns the correctlyCrossedFire.
	 */
	public boolean hasCorrectlyCrossedFire() {
		return correctlyCrossedFire;
	}

	/**
	 * @param correctlyCrossedFire The correctlyCrossedFire to set.
	 */
	public void setCorrectlyCrossedFire(boolean correctlyCrossedFire) {
		this.correctlyCrossedFire = correctlyCrossedFire;
	}

}