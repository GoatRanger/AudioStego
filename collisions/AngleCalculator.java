// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html
/*
 * AngleCalculator.java
 * This object will listen to changes in user choices about
 * velocity and impact parameter and tell its graphs to display those
 * choices.  I could make each graph a listener that does its own
 * calculation, but then the calculation would be done twice.
 * Suspecting time problems, I'm making this object to do the whole job
 * once.
 */

import java.awt.*;

public class AngleCalculator implements DoubleListener
{
	AngleCanvas LabFrame;
	AngleCanvas CMFrame;
	DoubleTextField textReadout;
	double dMass, dB, dVelocity;
	double[][] pos = new double[3][3];
	double[][] po = new double[3][3];

	public AngleCalculator( AngleCanvas ac1, AngleCanvas ac2 )
	{
		LabFrame = ac1;
		CMFrame = ac2;
		dB = 0.1d;
		dMass = 0.01d;
		dVelocity = 2.0d;
		textReadout = null;
	}

	public void addTextReadout( DoubleTextField dtf )
	{
		textReadout = dtf;
	}

	public void doubleValueChanged(String cmd, double val)
	{
		if (cmd=="b") setImpactParameter(val);
		else if (cmd=="v") setVelocity(val);
		else if (cmd=="m") setMass(val);
	}
	
	// reduced mass = m1/m2.
	public void setMass(double m)
	{
		if (m!= dMass) {
			dMass = m;
			recalc();
		}
	}

	// reduced impact parameter = b/r.
	public void setImpactParameter(double dImpactParameter)
	{
		if (dImpactParameter != dB) {
			//System.out.println(dImpactParameter);
			dB = dImpactParameter;
			if (dB>2.0) dB = 2.0;
			if (dB<-2.0) dB = -2.0;
			recalc();
		}
	}

	public void translateImpactParameter(double deltaB)
	{
		setImpactParameter(dB+deltaB);
	}


	public void setVelocity(double dV)
	{
		if (dV != dVelocity) {
			//System.out.println("Velocity changed to "+dV);
			dVelocity = dV;
			recalc();
		}
	}

	public void recalc()
	{
		double y1 = dB;
		double x1 = -Math.sqrt(4-y1*y1);
		// x2 = y2 = 0;
		double m = dMass;
		double[] cm = new double[] { m*x1/(1+m),m*y1/(1+m) };
		double reducedV = dVelocity/(1+m);
		double cmv =  m*reducedV;
		double b2 = dB*dB;
		double[] vcm;
		if (dB<0)
			vcm = new double[] { reducedV*(b2*0.5-1), reducedV*(0.25*b2-1)*b2 };
		else
			vcm = new double[] { reducedV*(b2*0.5-1), -reducedV*(0.25*b2-1)*b2 };
		double[][] vlab = new double[][] {
			{ vcm[0]+ cmv, vcm[1] },
			{ -m*vcm[0] + cmv, -m*vcm[1] } };
		double vmax = Math.abs(dVelocity);

		// See AngleCanvas for what these arrays are.
		synchronized( pos ) {
		synchronized (po) {
	 		pos[0][0] = dVelocity;
			pos[0][1] = Math.sqrt(vlab[0][0]*vlab[0][0]+vlab[0][1]*vlab[0][1]);
			//System.out.println("New lab velocity: "+pos[0][0]);
			pos[0][2] = Math.atan2(vlab[0][1],vlab[0][0]);
			pos[1][0] = 0;
			pos[1][1] = Math.sqrt(vlab[1][0]*vlab[1][0]+vlab[1][1]*vlab[1][1]);
			pos[1][2] = Math.atan2(vlab[1][1],vlab[1][0]);
			pos[2][0] = 0;
			pos[2][1] = Math.atan2(cm[1],cm[0]);
			for (int i=0; i<2; i++)
				for (int j=0; j<2; j++)
					if (Math.abs(pos[i][j])>vmax) vmax = Math.abs(pos[i][j]);

			po[0][0] = reducedV; po[0][1] = reducedV;  po[0][2] = Math.atan2(vcm[1],vcm[0]);
			po[1][0] = cmv;  po[1][1] = cmv; po[1][2] = Math.atan2(-vcm[1],-vcm[0]);
			po[2][0] = Math.sqrt(cm[0]*cm[0]+cm[1]*cm[1]);
			po[2][1] = Math.atan2(cm[1],cm[0]);
			for (int i=0; i<2; i++)
				for (int j=0; j<2; j++)
					if (Math.abs(po[i][j])>vmax) vmax = Math.abs(pos[i][j]);
			for (int i=0; i<2; i++)
				for (int j=0; j<2; j++)
					po[i][j] /= vmax;
			for (int i=0; i<2; i++)
				for (int j=0; j<2; j++)
					pos[i][j] /= vmax;
			//System.out.println("New lab velocity/vmax: "+pos[0][0]);
		}
		}

		if (textReadout != null) textReadout.setValue(dB);
		LabFrame.setPosition( pos );
		CMFrame.setPosition( po );
	}
}
