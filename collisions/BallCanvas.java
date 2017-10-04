// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html

import java.awt.*;

class BallCanvas extends Component
{
	static final int pRadius = 7;
	Color color;

	BallCanvas(Color c)
	{
		color = c;
        this.setSize(2*pRadius,2*pRadius);
	}

	public void paint(Graphics g)
	{
		Dimension size = this.getSize();
		g.clearRect(0,0,size.width,size.height);
		g.setColor(color);
		g.fillOval(0,0,size.width,size.height);
	}

	public Dimension getPreferredSize()
	{
		return(this.getSize());
	}

	public Dimension getMinimumSize()
	{
		return(new Dimension(2*pRadius,2*pRadius));
	}
}
