/* Copyright (C) 1998 Michael Fowler under Gnu Public License
 * http://www.gnu.org/copyleft/gpl.html
 *
 * CollisionGraph.java
 * Displays moving collision.  Given information by the Collision class.
 */

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CollisionGraph extends Component
		implements CollisionColors, ItemListener
{
	// pixel ball locations.  x and y.
    int pM1[],pM2[],pCM[];
	protected CollisionState csPos; // Current position
	private CollisionState csStart, csBounce;
	double dScaleMax, dscale, dTickScale;
	boolean bShowCM, bBounced, bShowTrails;

	protected double[] center; // center of screen.
	Dimension size; // current size.
	static final double dBallRadFactor = 1.0;
	static final double dTickRadFactor = 0.2;
	int pBallRadius = 5;
	int pTickRadius = 2;
	boolean bSet, bLines;

	public CollisionGraph(boolean Lines)
	{
		center = new double[2];
		pM1 = new int[2];
		pM2 = new int[2];
		pCM = new int[2];
		bSet = false;
		bLines = Lines;
		bShowTrails = true;
		bShowCM = true;
		size = this.getSize();
	}

	// The checkboxes call this method.
	public void itemStateChanged(ItemEvent evt)
	{
		Object obj = evt.getSource();
		if (obj instanceof CmdCheckbox) {
            String command = ((CmdCheckbox) obj).getCommand();
            if (command.equals("showcm"))
    			bShowCM = ((Checkbox)obj).getState();
    		else
    		    bShowTrails = ((Checkbox)obj).getState();
		}
	}

	// The next two methods are how Collision.class tells the graph
	// what to display.
    public void initShot(CollisionState start, CollisionState bounce,
                            double dMaxDistance)
    {
        csStart = new CollisionState(start);
        csPos = new CollisionState(start);
        csBounce = new CollisionState(bounce);
        dScaleMax = dMaxDistance;
        bSet = true;
        bBounced = false;
        initializeEndpoints();
        calculateCurrentPosition();
        repaint();
    }

	public void setPosition(CollisionState pos, boolean bPostBounce)
	{
        csPos =  new CollisionState(pos);
		bSet = true;
		bBounced = bPostBounce;
		calculateCurrentPosition();
		repaint();
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void paint(Graphics g)
	{
		if (!this.getSize().equals(size)) initializeEndpoints();

		g.setColor(collisionBGColor);
		g.fillRect(0,0,size.width,size.height);
		if (!bSet) return;
        if (bShowTrails) {
            if (bBounced) {
                if (bShowCM) {
        			g.setColor(cmColor);
                    drawTrail(g,csStart.dCMx,csBounce.dCMx,dTickScale*csStart.dCMv);
                    drawTrail(g,csBounce.dCMx,csPos.dCMx,dTickScale*csBounce.dCMv);
                }
        		g.setColor(m1Color);
        		drawTrail(g,csStart.dM1x,csBounce.dM1x,dTickScale*csStart.dM1v);
        		drawTrail(g,csBounce.dM1x,csPos.dM1x,dTickScale*csBounce.dM1v);
	        	g.setColor(m2Color);
        		drawTrail(g,csStart.dM2x,csBounce.dM2x,dTickScale*csStart.dM2v);
        		drawTrail(g,csBounce.dM2x,csPos.dM2x,dTickScale*csBounce.dM2v);
            } else {
                if (bShowCM) {
        			g.setColor(cmColor);
                    drawTrail(g,csStart.dCMx,csPos.dCMx,dTickScale*csStart.dCMv);
                }
        		g.setColor(m1Color);
        		drawTrail(g,csStart.dM1x,csPos.dM1x,dTickScale*csStart.dM1v);
	        	g.setColor(m2Color);
        		drawTrail(g,csStart.dM2x,csPos.dM2x,dTickScale*csStart.dM2v);
            }
        }
		if (bShowCM) {
			g.setColor(cmColor);
			g.drawOval(pCM[0]-pBallRadius/2,pCM[1]-pBallRadius/2,pBallRadius,pBallRadius);
		}
		g.setColor(m1Color);
		g.fillOval(pM1[0]-pBallRadius,pM1[1]-pBallRadius,2*pBallRadius,2*pBallRadius);
		g.setColor(m2Color);
		g.fillOval(pM2[0]-pBallRadius,pM2[1]-pBallRadius,2*pBallRadius,2*pBallRadius);
	}


	private void calculateCurrentPosition()
	{
		if (!bSet) return;
		pM1[0] = (int) (size.width/2+(csPos.dM1x[0])*dscale);
		pM1[1] = (int) (size.height/2-(csPos.dM1x[1])*dscale);
		pM2[0] = (int) (size.width/2+(csPos.dM2x[0])*dscale);
		pM2[1] = (int) (size.height/2-(csPos.dM2x[1])*dscale);
		pCM[0] = (int) (size.width/2+(csPos.dCMx[0])*dscale);
		pCM[1] = (int) (size.height/2-(csPos.dCMx[1])*dscale);
	}

	private void initializeEndpoints()
	{		
		size = this.getSize();
		dscale = (size.width+4)/(dScaleMax-2*dBallRadFactor);
		pBallRadius = (int)Math.round(dBallRadFactor*dscale);
        calculateCurrentPosition();
        if (!bSet) return;
		dTickScale = 2*dscale/csStart.dM1v;
        pTickRadius = (int)Math.round(dTickRadFactor*dscale);
	}

    private void drawTrail(Graphics g, double[] x1, double[] x2,double dTickSpacing)
    {
        int[] x = new int[2];
        int[] pX1 = new int[2];
   		int[] pX2 = new int[2];
        // dLen is the length in pixels.
        double dLen = Math.sqrt((x2[1]-x1[1])*(x2[1]-x1[1])+
                            (x2[0]-x1[0])*(x2[0]-x1[0]));
        if (!(dLen>0) || (dTickSpacing < 0.1)) return;
		pX1[0] = (int) (size.width/2+x1[0]*dscale);
		pX1[1] = (int) (size.height/2-x1[1]*dscale);
        if (bLines) {
			pX2[0] = (int) (size.width/2+x2[0]*dscale);
			pX2[1] = (int) (size.height/2-x2[1]*dscale);
            g.drawLine(pX1[0],pX1[1],pX2[0],pX2[1]);
        } else {
            double dX = x2[0]-x1[0];
            double dY = x2[1]-x1[1];
            double t = 0;
            while (t<dLen) {
                x[0] = (int)Math.round(dscale*t*dX/dLen+pX1[0]);
                x[1] = (int)Math.round(-dscale*t*dY/dLen+pX1[1]);
                g.fillOval(x[0]-pTickRadius,x[1]-pTickRadius,2*pTickRadius,2*pTickRadius);
                t += dTickSpacing/dscale;
            }
        }
    }
}
