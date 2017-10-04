// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html
import java.awt.*;

class OvalCanvas extends BallCanvas
{
    OvalCanvas(Color c)
    {
        super(c);
    }

	public void paint(Graphics g)
	{
		Dimension size = this.getSize();
        g.clearRect(0,0,size.width,size.height);
		g.setColor(color);
		g.drawOval(2,2,size.width-3,size.height-3);
	}
}
