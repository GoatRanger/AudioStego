/* Copyright (C) 1998 Michael Fowler under Gnu Public License
 * http://www.gnu.org/copyleft/gpl.html
 *
 * LPanel.java
 * Makes a useable class from the abstract Container class.
 * It functions as a Canvas without niceties.
 */
import java.awt.*;

class LPanel extends Container
{
    static final int borderSize = 2;
	public LPanel(LayoutManager mgr)
	{
		super();
		this.setLayout(mgr);
	}

    public Insets getInsets()
    {
        Insets insets = (Insets)(super.getInsets()).clone();
        //insets.top += borderSize;
        //insets.left += borderSize;
        //insets.bottom += borderSize;
        //insets.right += borderSize;
        return insets;
    }
	
	public void paint(Graphics g)
	{
		super.paint(g);
	}
}
