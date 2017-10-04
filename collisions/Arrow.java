// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html

import java.awt.*;

public class Arrow extends Polygon
{
	static final int pWidth = 2;
	static final int width = 4;

	public Arrow(int pLength, double dAngle, int pX, int pY)
	{
		int[][] rc;
		int len;
		if (pLength>4) {
			rc = new int[][] { {0,-2},{0,1}, {pLength-4,1},
				{pLength-4,4},{pLength,0},{pLength-4,-4},{pLength-4,-2}};
			len = 7;
		} else {
			rc = new int[][] { {0,4},{4,0},{0,-4} };
			len = 3;
		}
		Polygon py = new Polygon();
		double ct = Math.cos(-dAngle);
		double st = Math.sin(-dAngle);
		// We put a negative sine in for the y to change to lefthanded
		// coordinate system.
		for (int i=0; i<len; i++)
			this.addPoint((int)Math.round(ct*rc[i][0]+st*rc[i][1]),
						-(int)Math.round(ct*rc[i][1]+-st*rc[i][0]));
		this.translate(pX,pY);
	}
		
}
