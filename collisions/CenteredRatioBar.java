// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CenteredRatioBar extends CustomScrollBar
		implements AdjustmentListener, DoubleListener
{
	static final int iScrollMax = 100;
	double dMaxRatio;
	double dValue;
	Vector Listeners;
	String sCommand;

	public CenteredRatioBar(String cmd, double maxRatio)
	{
		super(Scrollbar.HORIZONTAL,0,8,-iScrollMax,iScrollMax);
		sCommand = cmd;
		dMaxRatio = iScrollMax/Math.log(maxRatio);
		Listeners = new Vector(2);
		this.addAdjustmentListener(this);
	}

	public void adjustmentValueChanged(AdjustmentEvent evt)
	{
		dValue = Math.pow(Math.E,this.getValue()/dMaxRatio);
		tellListeners();
	}

	private void tellListeners()
	{
		for (int i=0; i<Listeners.size(); i++)
		{
			((DoubleListener)Listeners.elementAt(i)).doubleValueChanged(sCommand,dValue);
		}
	}

	public void setValue(Double dVal)
	{
		setValue((int)(dMaxRatio*Math.log(dValue)),false);
		tellListeners();
	}

	public void doubleValueChanged(String cmd, double dValue)
	{
		setValue((int)(dMaxRatio*Math.log(dValue)),false);
	}

	public void addDoubleListener(DoubleListener d)
	{
		Listeners.addElement(d);
	}

	public void removeDoubleListener(DoubleListener d)
	{
		Listeners.removeElement(d);
	}
}
