/* Copyright (C) 1998 Michael Fowler under Gnu Public License
 * http://www.gnu.org/copyleft/gpl.html
 *
 * CustomScrollBar.java
 * This is basically an adapter for custom Scrollbars.
 * It's even double-buffered.  It could look better.
 */
import java.awt.*;
import java.awt.event.*;

public class CustomScrollBar extends Canvas implements Adjustable
{
	static final int pRailArcWidth = 2;
	static final int pRailArcHeight = 2;
	static final int pSlideArcWidth = 2;
	static final int pSlideArcHeight = 2;
	transient AdjustmentListener adjustmentListener;
	int iOrientation;
	int iValue;
	int iVisible;
	int iMin, iMax;
	int iBlockIncrement, iUnitIncrement;
	Image bbuf;
	Graphics bbufG;

	public CustomScrollBar(int orient, int val, int vis, int min, int max)
	{
		iOrientation = orient;
		iValue = val;
		iVisible = vis;
		iMin = min;
		iMax = max;
		iBlockIncrement = (iMax-iMin)/10;
		iUnitIncrement = 1;
		enableEvents( AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK );
	}

	public CustomScrollBar(int orient)
	{
		this(orient,0,10,0,100);
	}

	public CustomScrollBar()
	{
		this(Scrollbar.HORIZONTAL);
	}

	public int getBlockIncrement()
	{
		return(iBlockIncrement);
	}

	public int getMaximum()
	{
		return(iMax);
	}

	public int getMinimum()
	{
		return(iMin);
	}

	public int getOrientation()
	{
		return(iOrientation);
	}

	public int getUnitIncrement()
	{
		return(iUnitIncrement);
	}

	public int getValue()
	{
		return (iValue);
	}

	public int getVisibleAmount()
	{
		return( iVisible);
	}

	public void setBlockIncrement(int b)
	{
		iBlockIncrement = b;
	}

	public void setMaximum(int max)
	{
		iMax = max;
		repaint();
	}

	public void setMinimum(int min)
	{
		iMin = min;
		repaint();
	}

	public void setUnitIncrement(int u)
	{
		iUnitIncrement = u;
	}

	public void setValue(int val)
	{
		if (val<iMin) val = iMin;
		else if (val>iMax) val = iMax;
		iValue = val;
		advertise();
		repaint();
	}


	public void setValue(int val, boolean advertise)
	{
		if (val<iMin) val = iMin;
		else if (val>iMax) val = iMax;
		iValue = val;
		if (advertise) advertise();
		repaint();
	}


	public void setVisibleAmount(int v)
	{
		iVisible = v;
		repaint();
	}
	
	protected void processEvent(AWTEvent evt)
	{
		if (evt instanceof AdjustmentEvent) {
			processAdjustmentEvent((AdjustmentEvent) evt);
			return;
		}
		super.processEvent(evt);
	}

	public void processMouseEvent(MouseEvent evt)
	{
		if (evt.getID() == MouseEvent.MOUSE_PRESSED) {
			int x = evt.getX();
			iValue = (int) (iMin+(iMax-iMin)*((double)x)/getSize().width);
			advertise();
			repaint();
		} else
			super.processMouseEvent(evt);
	}

	public void processMouseMotionEvent(MouseEvent evt)
	{
		if (evt.getID() == MouseEvent.MOUSE_DRAGGED) {
			int x = evt.getX();
			iValue = (int) (iMin+(iMax-iMin)*((double)x)/getSize().width);
            if (iValue>iMax) iValue = iMax;
            if (iValue<iMin) iValue = iMin;
			advertise();
			repaint();
		} else
			super.processMouseMotionEvent(evt);
	}

	protected void processAdjustmentEvent(AdjustmentEvent evt)
	{
		if (adjustmentListener != null) {
			adjustmentListener.adjustmentValueChanged(evt);
		}
	}

	public void update(Graphics g) { paint(g); }

	public void paint(Graphics g)
	{
		int w = getSize().width;
		int h = getSize().height;
		if (bbuf == null || bbuf.getWidth(this)<w || bbuf.getHeight(this)<h) {
			bbuf = createImage(w,h);
			bbufG = bbuf.getGraphics();
		}
		bbufG.setColor(getBackground());
		bbufG.fillRect(0,0,w,h);

		bbufG.setColor(Color.black);
		bbufG.drawRoundRect(0,h/2-2,w-1,h/2+1,pRailArcWidth,pRailArcHeight);

		bbufG.drawLine(w/2,0,w/2,h);

		int pSlidePosition = (int) (w*((double)iValue-iMin)/(iMax-iMin));
		bbufG.fillRoundRect(pSlidePosition-iVisible/2,0,iVisible,h-1,pSlideArcWidth,pSlideArcHeight);
		g.drawImage(bbuf,0,0,this);
	}

	public Dimension getMinimumSize()
	{
		return(new Dimension(iVisible+pSlideArcWidth,pSlideArcHeight*4));
	}

	public Dimension getPreferredSize()
	{
		return getMinimumSize();
	}

	private void advertise()
	{
		AdjustmentEvent adj = new AdjustmentEvent(this,
				AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED,
				AdjustmentEvent.TRACK, iValue);
		processEvent(adj);
	}
	
	public synchronized void addAdjustmentListener(AdjustmentListener l)
	{
		adjustmentListener = AWTEventMulticaster.add(adjustmentListener,l);
	}

	public synchronized void removeAdjustmentListener(AdjustmentListener l)
	{
		adjustmentListener = AWTEventMulticaster.remove(adjustmentListener,l);
	}
}
