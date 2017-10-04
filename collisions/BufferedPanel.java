// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html
/*
 * BufferedPanel
 * A panel that is double-buffered so it can contain lightweight components.
 * A lot of code taken from The Java Class Libraries chapter on Containers.
 */

import java.awt.*;

public class BufferedPanel extends Panel implements CollisionColors
{
	public BufferedPanel(LayoutManager mgr)
	{
		super(mgr);
	}
	
	public void paint(Graphics g)
	{
		update(g);
	}
	
	Image bbuf;
	Graphics bbufG;
	public void update(Graphics g)
	{
		int w = getSize().width;
		int h = getSize().height;
		if (bbuf == null
				|| bbuf.getWidth(null) < w
				|| bbuf.getHeight(null)< h ) {
			bbuf = createImage(w,h);
			bbufG = bbuf.getGraphics();
		}

		// The book says this is needed to work around Java 1.1.2 bug.
		bbufG.setClip(0,0,w,h);

		bbufG.setColor(panelBGColor);
		bbufG.fillRect(0,0,w,h);

		super.paint(bbufG);
		g.drawImage(bbuf,0,0,this);
	}
}
