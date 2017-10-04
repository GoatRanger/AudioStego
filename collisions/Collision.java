/* Copyright (C) 1998 Michael Fowler under Gnu Public License
 * http://www.gnu.org/copyleft/gpl.html
 *
 * Collision.class
 * This is the Scrollbar controlling the time.  It is also the
 * threaded object that computes the current location of the balls.
 * It tells CollisionGraph where the balls are.
 */
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class Collision  extends Scrollbar
		implements Runnable, DoubleListener, ActionListener, AdjustmentListener {

    CollisionGraph cgLab, cgCM;
	boolean please_stop;
	boolean bCollisionCalculated;
	Thread animator;
    // One copy of the relevant parameters holds the values set
	// by the controls.
	double dImpactParameter, dVelocity, dMass;
    // An internal copy holds the values we use during the shot
	// in case we decide to let the user change the controls
	// during a shot.
	double b, v, m; // The actual ones we use.
	double dMaxDistance;
	double t, tCollision, dt;
	double[] dM1x, dM2x, dCMx, dM1v, dM2v, dM1x0;
	int sleeptime = 40;
	int iNumSteps = 20;
	double tEndCollision;
	static final int iScrollMax = 400;

	public Collision(CollisionGraph Lab, CollisionGraph CM)
	{
		super(Scrollbar.HORIZONTAL,0,15,0,iScrollMax);
		please_stop = true;
		cgLab = Lab;
		cgCM = CM;
		dVelocity = 1.0;
		dMaxDistance = 30.0;
		dt = 0.01;
		dM1x = new double[] { -40, 0 };
		dM2x = new double[] { 0,0 };
		dCMx = new double[] { -20, 0 };
        // Our Scrollbar talks with us using events.
		this.addAdjustmentListener(this);
		bCollisionCalculated = false;
	}

    // Accepts messages from the Scrollbar.
	public void adjustmentValueChanged(AdjustmentEvent evt)
	{
		please_stop = true;
		if (!bCollisionCalculated) initListeners();
		t = getValue()*tEndCollision/iScrollMax;
		calculatePositions();
		informListeners();
	}

    // Accepts messages from all the other controls,
	// all of which fire doubleValue events.
	public void doubleValueChanged(String cmd, double val)
	{
		please_stop = true;
		if (cmd=="b") dImpactParameter = val;
		else if (cmd=="v") dVelocity = val;
		else if (cmd=="m") dMass = val;
		t = 0;
		if (getValue()!=0) {
			calculatePositions();
			informListeners();
			setValue(0);
		}
		bCollisionCalculated = false;
	}

    // This is the fire button.
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getActionCommand().equals("fire")) fire();
	}

    // Time in milliseconds the thread sleeps each run.
	public void setSleepTime(int sleep)
	{
		if (sleep>=0) {
			sleeptime = sleep;
		}
	}

    // Number of timesteps for mass 1 to hit mass 2.
	public void setTimeStep(int ts)
	{
		if (ts>5) {
			iNumSteps = ts;
		}
	}

    // Tell the two CollisionGraphs what the current position is.
	private void informListeners()
	{
        CollisionState current;
        current = new CollisionState(dM1x, dM2x, dCMx);
        cgLab.setPosition(current,(t>tCollision));
        current.dM1x[0] -= current.dCMx[0];
        current.dM1x[1] -= current.dCMx[1];
        current.dM2x[0] -= current.dCMx[0];
        current.dM2x[1] -= current.dCMx[1];
        current.dCMx[0] = 0;
        current.dCMx[1] = 0;
        cgCM.setPosition(current,(t>tCollision));
	}

    // Tell the two CollisionGraphs where the start and collision
	// points are so they can draw the dotted lines for trails.
    // Called just after the user presses "fire."
	private void initListeners()
	{
        CollisionState start, bounce;
        recalculate();
        start = new CollisionState(dM1x, dM2x, dCMx);
        start.dM1v = v;
        start.dM2v = 0;
        start.dCMv = v*m/(1+m);
        bounce = new CollisionState(new double[] {dM1x0[0],dM1x[1]},
                new double[] {0,0} ,
                new double[] {m*dM1x0[0]/(1+m),m*dM1x[1]/(1+m)});
        bounce.dM1v = Math.sqrt(dM1v[0]*dM1v[0]+dM1v[1]*dM1v[1]);
        bounce.dM2v = Math.sqrt(dM2v[0]*dM2v[0]+dM2v[1]*dM2v[1]);
        bounce.dCMv = start.dCMv; // (bounce.dM1v*m+bounce.dM2v)/(1+m);
        cgLab.initShot(start,bounce,dMaxDistance);
        start.dM1x[0] -= start.dCMx[0];
        start.dM1x[1] -= start.dCMx[1];
        start.dM2x[0] -= start.dCMx[0];
        start.dM2x[1] -= start.dCMx[1];
        start.dCMx[0] = 0;
        start.dCMx[1] = 0;
        start.dM1v = v/(1+m);
        start.dM2v = v*m/(1+m);
        start.dCMv = 0;
        bounce.dM1x[0] -= bounce.dCMx[0];
        bounce.dM1x[1] -= bounce.dCMx[1];
        bounce.dM2x[0] -= bounce.dCMx[0];
        bounce.dM2x[1] -= bounce.dCMx[1];
        bounce.dCMx[0] = 0;
        bounce.dCMx[1] = 0;
        bounce.dM1v = Math.sqrt((dM1v[0]-bounce.dCMv)*(dM1v[0]-bounce.dCMv)+
                            dM1v[1]*dM1v[1]);
        bounce.dM2v = Math.sqrt((dM2v[0]-bounce.dCMv)*(dM2v[0]-bounce.dCMv)+
                            dM2v[1]*dM2v[1]);
        bounce.dCMv = 0;

        cgCM.initShot(start,bounce,dMaxDistance);
	}

	// Calculate the current position.
	private void calculatePositions()
	{
		// this should be synchronized.
		if ( t < tCollision ) {
			dM1x[0] = -dMaxDistance+v*t;
			dM1x[1] = b;
			dM2x[0] = 0;
			dM2x[1] = 0;
			dCMx[0] = m*dM1x[0]/(1+m);
			dCMx[1] = m*dM1x[1]/(1+m);
		} else {
			dM1x[0] = dM1x0[0]+dM1v[0]*(t-tCollision);
			dM1x[1] = dM1x0[1]+dM1v[1]*(t-tCollision);
			dM2x[0] = dM2v[0]*(t-tCollision);
			dM2x[1] = dM2v[1]*(t-tCollision);
			dCMx[0] = (m*dM1x[0]+dM2x[0])/(1+m);
			dCMx[1] = (m*dM1x[1]+dM2x[1])/(1+m);
		}
	}
	
    // This is the thread.  It runs until it wants to quit.
	public void run()
	{
        calculatePositions();
		while (!please_stop) {
			// first tell listeners where balls are.
			informListeners();

            // Set our scrollbar to the current time.
			this.setValue((int)(t*iScrollMax/tEndCollision));
			t += dt;
			if (t>tEndCollision) please_stop = true;
			calculatePositions();
			
			try { animator.sleep(sleeptime); } catch (InterruptedException e) { ; }
		}
		animator = null;
	}

	public void fire()
	{
		please_stop = true;

        // Wait for the last shot to finish.
		if (animator != null) {
			try {
				animator.join(1000); // wait 1000 milliseconds
			} catch (Exception e) { ; }
		}
		
        // Tell the CollisionGraphs what the parameters are for
		// the new shot.
        initListeners();

        // Start the new thread.
		please_stop = false;
		animator = new Thread(this);
		animator.start();
	}

    // Calculate the collison times and velocities for the next shot.
	private void recalculate()
	{
		double[][] vel;
		
		b = dImpactParameter;
		v = dVelocity;
		m = dMass;

		vel = labCollision(v,0,0,0,m,b);
		dM1x[0] = -dMaxDistance;
		dM1x[1] = b;
		dM2x[0] = 0;
		dM2x[1] = 0;
		dCMx[0] = m*dM1x[0]/(1+m);
		dCMx[1] = m*dM1x[1]/(1+m);
		
		dM1v = vel[0];
		dM2v = vel[1];
		dM1x0 = new double[] { -Math.sqrt(4-b*b), b };
		tCollision = (dMaxDistance-Math.sqrt(4-b*b))/v;
		dt = tCollision/iNumSteps;
		t = 0;
		this.setValue(0);
		tEndCollision = 3*tCollision;
		bCollisionCalculated = true;
	}

    // These allow us to start and stop the thread.
	// There is little point b/c the thread stops in a few seconds anyway.
	public void start()
	{
	    ; // I could restart it if it were in the middle of the shot,
	    // but I don't think it would be quite right to have it shooting
	    // when you return.
	}

    // This makes good sense.
	public void stop()
	{
		please_stop = true;
	}

    // The following are static functions that calculate information
	// about collisions.  AngleCalculator uses some of these.
	// Calculate center of mass location
	static public double[] cmx(double x1, double y1, double x2, double y2, double m)
	{
		return(new double[] {(m*x1+x2)/(1+m),(m*y1+y2)/(1+m)});
	}

	// Calculate center of mass velocity
	static public double[] cmv(double vx1, double vy1, double vx2, double vy2, double m)
	{
		return(new double[] {(m*vx1+vx2)/(1+m),(m*vy1+vy2)/(1+m)});
	}

	// This will find the impact parameter if the second mass is stationary.
	static public double dImpactParameter(double x1, double y1, double vx1, double vy1,
									double x2, double y2)
	{
		double m, b;
		if (vx1 == 0.0) return(x2-x1);
		m = vy1/vx1;  b = y1-m*x1;
		double x0 = (x2+m*y2-m*b)/(1+m*m);
		double y0 = m*x0+b;
		return(Math.sqrt(Math.pow(x0-x2,2)+Math.pow(y0-y2,2)));
	}

	// Calculate new velocities after a collision in the lab frame.
	static public double[][] labCollision(double vx1, double vy1, double vx2, double vy2,
									double m, double b)
	{
		double[] vcm = cmv(vx1,vy1,vx2,vy2,m);
		double[] v1 = cmCollision(vx1-vcm[0],vy1-vcm[1],m,b);
		double[] v2 = new double[] {-m*v1[0],-m*v1[1]};
		v1[0] += vcm[0];  v1[1] += vcm[1];
		v2[0] += vcm[0];  v2[1] += vcm[1];
		return( new double[][] { v1,v2 } );
	}

	// given v1, reduced mass, and reduced impact parameter,
	// returns new v1.  No need for v2 b/c v2 = -m v1 always.
	static public double[] cmCollision(double vx1, double vy1, double m, double b)
	{
		// final velocity equals initial velocity
		double vt1 = Math.sqrt(vx1*vx1+vy1*vy1);
		// initial angle is ArcTan(vy1,vx1).
		// final angle is that plus cos phi = 2 sin^2 theta-1
		double dAngleA = Math.atan2(vy1,vx1);
		if (b>0) dAngleA += Math.acos(b*b/2-1.0d);
		else dAngleA -= Math.acos(b*b/2-1.0d);
		return( new double[] { vt1*Math.cos(dAngleA),vt1*Math.sin(dAngleA) } );
	}
}
