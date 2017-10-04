// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html
/*
 * AngleCanvas.java
 * Displays the two projectiles at the moment of impact.
 * Shows velocity vectors.
 */

import java.awt.*;
import java.awt.event.*;

public class AngleCanvas extends Component implements CollisionColors
{
	double [][] pos;
	int pBallRadius;
	int pMaxArrowLength;
	Arrow a1, a2, b1, b2;
	int[] pBall1, center;
	Dimension size;
	AngleCalculator angleCalc;
	boolean bBall1, bBall2, bMouseDown;
	int pMouseX, pMouseY;

	public AngleCanvas()
	{
		super();
		pBallRadius = 10;
		pMaxArrowLength = 40;
		pos = null;
		pBall1 = new int[2];
		center = new int[2];
		a1=a2=b1=b2 = null;
		size = getSize();
		angleCalc = null;
		bMouseDown = false;

		enableEvents( AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK );
	}

	public void setAngleCalculator( AngleCalculator ac)
	{
		angleCalc = ac;
	}

	public void processMouseEvent(MouseEvent evt)
	{
		if (evt.getID() == MouseEvent.MOUSE_PRESSED) {
			pMouseX = evt.getX();
			pMouseY = evt.getY();
			//System.out.println("Mouse x "+pMouseX+" y "+pMouseY+" center x "+center[0]+" y "+center[1]);
			int dx = pMouseX-(center[0]+pBall1[0]+pBallRadius);
			int dy = pMouseY-(center[1]+pBall1[1]+pBallRadius);
			bBall1 = bBall2 = false;
			if (dx*dx+dy*dy < pBallRadius*pBallRadius) bBall1 = true;
			else {
				dx = pMouseX-center[0];
				dy = pMouseY-center[1];
				if (dx*dx+dy*dy < pBallRadius*pBallRadius)
						bBall2 = true;
			}
			if (bBall1 || bBall2) {
				bMouseDown = true;
				//System.out.println("Mouse pressed in ball");
				repaint();
			}
		} else if (evt.getID() == MouseEvent.MOUSE_RELEASED) {
			bMouseDown = false;
			bBall1 = bBall2 = false;
			repaint();
		}
	}

	public void processMouseMotionEvent(MouseEvent evt)
	{
		if (evt.getID() == MouseEvent.MOUSE_DRAGGED) {
			// System.out.println("drag event");
			if (!bMouseDown) return;
			int pNewMouseX = evt.getX();
			int pNewMouseY = evt.getY();
			int dy = size.height/2-pNewMouseY;
			int dx = pNewMouseX-size.width/2;
			double newb;
			if (bBall1) {
				if (dx<0) newb = 2*dy/Math.sqrt(dx*dx+dy*dy);
				else return;
			} else {
				if (dx>0) newb = -2*dy/Math.sqrt(dx*dx+dy*dy);
				else return;
			}
			if (angleCalc != null) angleCalc.setImpactParameter(newb);
			pMouseX = pNewMouseX;
			pMouseY = pNewMouseY;
		}
	}

	// pos is (primes indicate values after collision)
	// v1, v1', theta1'
	// v2, v2', theta2'
	// dx, angle	where dx is dx from m2 to center of mass.
	// 				angle is angle from m1 to m2 from x axis.
	public void setPosition(double[][] p)
	{
		pos = p;
		recalc();
		repaint();
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void paint(Graphics g)
	{
		if (getSize()==null) return;
		if (!getSize().equals(size)) changeSize();
		g.setColor(impactBGColor);
		g.fillRect(0,0,size.width,size.height);
		
		g.setColor(crossColor);
		g.drawLine(0,size.height/2,size.width,size.height/2);
		g.drawLine(size.width/2,0,size.width/2,size.height);

		if (pos == null) return;
		//System.out.println("Translate: x "+
		//					(int)(size.width/2-pos[2][0]*pBallRadius*Math.cos(pos[2][1]))+
		//					" y "+(int)(size.height/2+pos[2][0]*pBallRadius*Math.sin(pos[2][1]))+
		//					" pos "+pos[2][0]+" th "+pos[2][1]);
		g.translate(center[0],center[1]);
		// now the ball2 goes at 0,0 as usual.
		g.setColor(m2Color);
		g.fillOval(-pBallRadius,-pBallRadius,pBallRadius*2,pBallRadius*2);
		if (bBall2) {
			g.setColor(Color.black);
			g.drawOval(-pBallRadius,-pBallRadius,pBallRadius*2,pBallRadius*2);
		}
		g.setColor(m1Color);
		g.fillOval(pBall1[0],pBall1[1],pBallRadius*2,pBallRadius*2);
		if (bBall1) {
			g.setColor(Color.black);
			g.drawOval(pBall1[0],pBall1[1],pBallRadius*2,pBallRadius*2);
		}

		g.setColor(arrowInColor);
		// This test ABSOLUTELY IS NOT SUFFICIENT.
		if (a1 != null) g.fillPolygon(a1);
		if (b1 != null) g.fillPolygon(b1);
		g.setColor(arrowOutColor);
		if (a2 != null) g.fillPolygon(a2);
		if (b2 != null) g.fillPolygon(b2);
	}

	private void changeSize()
	{
		size = this.getSize();
		if (size==null) return;
		int pMax, pMin;
		if (size.width>size.height) {
			pMax = size.width;
			pMin = size.height;
		} else {
			pMax = size.height;
			pMin = size.width;
		}
		pBallRadius = pMin/6;
		pMaxArrowLength = pMin/4;
		recalc();
		repaint();
	}

	private void recalc()
	{
		if (size==null) return;
		if (pos == null) return;
		synchronized (pos) {
			center[0] = (int)(size.width/2-pos[2][0]*pBallRadius*Math.cos(pos[2][1]));
			center[1] = (int)(size.height/2+pos[2][0]*pBallRadius*Math.sin(pos[2][1]));
			double x1 = 2*pBallRadius*Math.cos(pos[2][1]);
			// minus b/c screen coordinates are lefthanded.
			double y1 = -2*pBallRadius*Math.sin(pos[2][1]);

			pBall1[0] = (int)x1-pBallRadius;
			pBall1[1] = (int)y1-pBallRadius;

			a1 = new Arrow((int)(pMaxArrowLength*pos[0][0]),0,(int)(x1-pMaxArrowLength*pos[0][0]),(int)y1);
			if (Math.abs(pMaxArrowLength*pos[0][1])<1) a2 = null;
			else a2 = new Arrow((int)(pMaxArrowLength*pos[0][1]),pos[0][2],(int)x1,(int)y1);
			if (Math.abs(pMaxArrowLength*pos[1][0])<1) b1 = null;
			else b1 = new Arrow((int)(pMaxArrowLength*pos[1][0]),Math.PI,(int)(pMaxArrowLength*pos[1][0]),0);
			if (Math.abs(pMaxArrowLength*pos[1][1])<1) b2 = null;
			else b2 = new Arrow((int)(pMaxArrowLength*pos[1][1]),pos[1][2],0,0);
		}
	}
}
