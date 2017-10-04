/* Copyright (C) 1998 Michael Fowler under Gnu Public License
 * http://www.gnu.org/copyleft/gpl.html
 *
 * CollisionColors
 * This interface defines all the colors in the applet.
 * Handy, no?
 */
import java.awt.*;


public interface CollisionColors
{
	static final Color mainBGColor = new Color(235,200,159); // background of main applet.
	static final Color mainBorderColor = Color.darkGray; // edge around whole applet

	static final Color m1Color = new Color(207,59,19);	// mass 1
	static final Color m2Color = new Color(0,154,96);			// mass 2
	static final Color cmColor = new Color(119,98,76);		// center of mass

	static final Color panelBGColor = new Color(211,124,69);	// panel holding the two main panels
	static final Color impactBGColor = Color.darkGray; // panel showing two balls and arrows
	static final Color arrowInColor = new Color(255,186,5); // arrows into impact
	static final Color arrowOutColor = new Color(255,255,255); // arrows out of impact
	
	static final Color collisionBGColor = mainBGColor; // panel showing moving balls collide
	static final Color crossColor = Color.white; // cross on impact canvas where moving balls collide
}

