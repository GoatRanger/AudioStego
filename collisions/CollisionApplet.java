/*
 * Copyright (C) 1998 Michael Fowler under Gnu Public License
 * http://www.gnu.org/copyleft/gpl.html
 *
 * CollisionApplet.class
 * Drew Dolgert ajd2m@virginia.edu
 * Shows physics of a two-dimensional collision of two balls.
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

// CollisionColors is a single interface holding all color definitions.
public class CollisionApplet extends Applet implements CollisionColors
{
	// One large init to layout the applet.
	public void init()
	{
		boolean bLines = false; // Whether collision graph uses lines or dots.
		ResourceBundle rb;

		// The resource bundle contains strings in various languages.
		// See the CollisionStrings*.properties files.
		// Some browsers (Netscape) can't handle these properly yet,
		// so we allow the user to turn them off with a parameter.
		String sUseRB = this.getParameter("international");
		rb = null;
		if ((sUseRB == null) || !sUseRB.equalsIgnoreCase("no")) {
			String sLang = this.getParameter("language");
			String sCountry = this.getParameter("country");
			try {
				try {
					if (sLang == null)
						rb = ResourceBundle.getBundle("CollisionStrings",Locale.getDefault());
					else {
						Locale thisloc;
						if (sCountry == null)
							thisloc = new Locale(sLang,"");
						else
							thisloc = new Locale(sLang,sCountry);
						rb = ResourceBundle.getBundle("CollisionStrings",thisloc);
					}
				} catch (java.lang.ClassFormatError e) {
					System.out.println("Cannot load the ResourceBundle class.  Must be Netscape.");
					System.out.println("Turning off Internationalization.  Sorry.");
					rb = null;
				}
			} catch (MissingResourceException e) {
				System.out.println(e.getMessage());
				rb = null;
			}
		}
		// Start the main layout.  It will be a gridbag.
		this.setLayout(new GridBagLayout());
		// Colors are defined in a CollisionColors interface.
		this.setBackground(mainBGColor);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4,4,4,4);

		// This legend identifies colors for mass1, mass2, and center of mass.
		LPanel legend = new LPanel(new GridBagLayout());
		gridset(c,0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		legend.add(new BallCanvas(m1Color),c);
		gridset(c,1,0,1,1,1,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
		if (rb != null)
			legend.add(new MultiLineLabel(rb.getString("mass1"),0,0,MultiLineLabel.LEFT),c);
		else
			legend.add(new MultiLineLabel("Mass 1",0,0,MultiLineLabel.LEFT),c);
		gridset(c,0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		legend.add(new BallCanvas(m2Color),c);
		gridset(c,1,1,1,1,1,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
		if (rb != null)
			legend.add(new MultiLineLabel(rb.getString("mass2"),0,0,MultiLineLabel.LEFT),c);
		else
			legend.add(new MultiLineLabel("Mass 2",0,0,MultiLineLabel.LEFT),c);
		gridset(c,0,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		legend.add(new OvalCanvas(cmColor),c);
		gridset(c,1,2,1,1,1,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
		if (rb != null)
			legend.add(new MultiLineLabel(rb.getString("cm"),0,0,MultiLineLabel.LEFT),c);
		else
			legend.add(new MultiLineLabel("Center\nof Mass",0,0,MultiLineLabel.LEFT),c);
		gridset(c,0,3,2,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
		CmdCheckbox cbShowCM;
		if (rb != null)
			cbShowCM = new CmdCheckbox(rb.getString("cmcheck"),true,"showcm");
		else
			cbShowCM = new CmdCheckbox("Show CM",true,"showcm");
		legend.add(cbShowCM,c);
		CmdCheckbox cbShowTrails;
		if (rb != null)
			cbShowTrails = new CmdCheckbox(rb.getString("trailcheck"),true,"showtrails");
		else
			cbShowTrails = new CmdCheckbox("Show trails",true,"showtrails");
		gridset(c,0,4,2,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
		legend.add(cbShowTrails,c);
		

		// Add that legend at (0,0).
		c.insets = new Insets(4,4,4,4);
		gridset(c,0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		this.add(legend);

		// We use a buffered panel to hold the main action of the applet
		// because the panels with the collision and angles are components,
		// not canvases.  Since components insist on redrawing the whole
		// region (not allowing you to define a small clipping region), it may
		// be smart to switch to canvases later.  Have to test it.
		BufferedPanel cmp = new BufferedPanel(new GridBagLayout());
		cmp.setBackground(panelBGColor);
		// This shows the balls in motion.
		String sLines = this.getParameter("trails");
		if (sLines != null)
		    if (sLines.equalsIgnoreCase("lines"))
		        bLines = true;
		CollisionGraph cmp1 = new CollisionGraph(bLines);
		cmp1.setBackground(collisionBGColor);
		// This shows the angles as they collide.
		AngleCanvas accm = new AngleCanvas();
		gridset(c,0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
		cmp.add(accm,c);
		// This is a label to identify our panel as the center of mass panel.
		gridset(c,1,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		// A turned label is a homebrew class.
		TurnedLabel cmlabel;
		if (rb != null)
			cmlabel = new TurnedLabel(rb.getString("cmframe"));
		else
			cmlabel = new TurnedLabel("Center of Mass");
		// We'll use this font again below.
		Font frameFont = new Font("SansSerif",Font.BOLD,12);
		cmlabel.setFont(frameFont);
		cmp.add(cmlabel,c);
		// Now add the motion panel.
		gridset(c,2,0,1,1,2,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
		cmp.add(cmp1,c);
		// Lastly, the whole buffered panel goes into the applet.
		gridset(c,1,0,2,1,3,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
		this.add(cmp,c);

		// Just as we did for the center of mass, replace "cm" with "lab"
		BufferedPanel labp = new BufferedPanel(new GridBagLayout());
		labp.setBackground(panelBGColor);
		CollisionGraph labp1 = new CollisionGraph(bLines);
		labp1.setBackground(collisionBGColor);
		AngleCanvas aclab = new AngleCanvas();
		gridset(c,0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
		labp.add(aclab,c);

		// The collision itself is the scrollbar.  Just for fun.
		Collision action = new Collision(labp1,cmp1);
		gridset(c,1,1,2,1,3,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL);
		this.add(action,c);

		gridset(c,1,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		TurnedLabel labFrameLabel;
		if (rb != null)
			labFrameLabel = new TurnedLabel(rb.getString("labframe"));
		else
			labFrameLabel = new TurnedLabel("Lab Frame");
		labFrameLabel.setFont(frameFont);
		labp.add(labFrameLabel,c);
		gridset(c,2,0,1,1,2,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
		labp.add(labp1,c);
		// Now add the lab frame to the whole applet.
		gridset(c,1,2,2,1,3,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
		this.add(labp,c);

		AngleCalculator ac = new AngleCalculator(aclab,accm);
		aclab.setAngleCalculator(ac);
		accm.setAngleCalculator(ac);

		Panel firePanel = new Panel(new GridBagLayout());
		MultiLineLabel instructionsLabel;
		if (rb != null)
			instructionsLabel =
				new MultiLineLabel(rb.getString("instructions"),0,0,
					MultiLineLabel.LEFT);
		else
			instructionsLabel =
				new MultiLineLabel("Drag the\nred ball.\nThen fire.",0,0,
					MultiLineLabel.LEFT);
		instructionsLabel.setFont(new Font("Serif",Font.PLAIN,18));
		gridset(c,0,0,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
		firePanel.add(instructionsLabel,c);
		Button but;
		if (rb != null)
			but = new Button(rb.getString("fire"));
		else
			but = new Button("Fire");
		gridset(c,0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		firePanel.add(but,c);
		// Now add firepanel to the whole.
		gridset(c,0,2,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
		this.add(firePanel,c);

		Panel massPanel = new Panel(new GridBagLayout());
		c.insets = new Insets(2,2,2,2);
		CenteredRatioBar massBar = new CenteredRatioBar("m",10.0);
		gridset(c,0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		massPanel.add(new BallCanvas(m2Color),c);
		gridset(c,1,0,2,1,4,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL);
		massPanel.add(massBar,c);
		gridset(c,3,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		massPanel.add(new BallCanvas(m1Color),c);
		gridset(c,0,1,2,1,1,0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL);
		if (rb != null)
			massPanel.add(new MultiLineLabel(rb.getString("heavier"),0,0,MultiLineLabel.LEFT),c);
		else
			massPanel.add(new MultiLineLabel("Heavier",0,0,MultiLineLabel.LEFT),c);
		gridset(c,2,1,2,1,1,0,GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL);
		if (rb != null)
			massPanel.add(new MultiLineLabel(rb.getString("heavier"),0,0,MultiLineLabel.RIGHT),c);
		else
			massPanel.add(new MultiLineLabel("Heavier",0,0,MultiLineLabel.RIGHT),c);
		// Now add the massPanel to the whole applet.
		gridset(c,0,3,2,1,4,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL);
		this.add(massPanel,c);

		Panel readoutPanel = new Panel(new GridBagLayout());
		gridset(c,0,0,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
		if (rb != null)
			readoutPanel.add(new Label(rb.getString("ip")),c);
		else
			readoutPanel.add(new Label("Impact Parameter [radii]"),c);
		gridset(c,0,1,1,1,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
		if (rb != null)
			readoutPanel.add(new Label(rb.getString("m")),c);
		else
			readoutPanel.add(new Label("Mass Ratio [m1/m2]"),c);
		
		DoubleTextField dtf = new DoubleTextField(-2,2,1.0,"b");
		gridset(c,1,0,1,1,0,0,GridBagConstraints.EAST,GridBagConstraints.NONE);
		readoutPanel.add(dtf,c);
		DoubleTextField massTF = new DoubleTextField(0.01,100,1.0,"m");
		gridset(c,1,1,1,1,0,0,GridBagConstraints.EAST,GridBagConstraints.NONE);
		readoutPanel.add(massTF,c);
		gridset(c,2,3,1,1,0,0,GridBagConstraints.EAST,GridBagConstraints.NONE);
		this.add(readoutPanel,c);

		// Now hook everything together.
		// Isn't this fun?
		// The CollisionGraph listens to the Center of Mass checkbox.
		cbShowCM.addItemListener(cmp1); 
		// The CollisionGraph listens to the Center of Mass checkbox.
		cbShowCM.addItemListener(labp1);
		// Same thing for the show trails checkbox.
        cbShowTrails.addItemListener(cmp1);
        cbShowTrails.addItemListener(labp1);
		// The collision.class itself listens to the fire button.
		but.setActionCommand("fire");
		but.addActionListener(action);
		// When you change the mass text field, three folks need listen.
		massTF.addDoubleListener(ac); // The contoller of the angle canvases.
		massTF.addDoubleListener(action); // The collision itself
		massTF.addDoubleListener(massBar); // The sliding bar to change mass.
		// And the same folks listen to the mass bar.
		massBar.addDoubleListener(massTF);
		massBar.addDoubleListener(ac);
		massBar.addDoubleListener(action);
		// The impact parameter text field reports to the angle canvases and the collision.
		dtf.addDoubleListener(ac);
		dtf.addDoubleListener(action);
		ac.addTextReadout(dtf); // Chaning the angle canvas changes the impact parameter text.

		// Finally, set starting values.
		dtf.setValue(1.0d);
		massTF.setValue(1.0d);

		String sSleep = this.getParameter("threadsleep");
		if (sSleep != null) {
			try {
				int sleeptime = Integer.parseInt(sSleep);
				action.setSleepTime(sleeptime);
			} catch (NumberFormatException e) {
				System.out.println("Threadsleep should be an integer number of milliseconds.");
			}
		}
		String sTimestep = this.getParameter("timestep");
		if (sTimestep != null) {
			try {
				int timestep = Integer.parseInt(sTimestep);
				action.setTimeStep(timestep);
			} catch (NumberFormatException e) {
				System.out.println("Timestep should be the integer number of timesteps\n"+
									"to for the mass 1 to reach mass 2.");
			}
		}
		
		action.fire();
	}

	// The applet has a paint only so it can draw its border.
	public void paint(Graphics g)
	{
		int w = getSize().width;
		int h = getSize().height;
		
		g.setColor(mainBorderColor);
		g.drawRect(1,1,w-3,h-3);
		g.setColor(mainBorderColor.brighter());
		g.drawRect(0,0,w-1,h-1);

		// End by painting your contents.
		// Let them double-buffer themselves.
		super.paint(g);
	}

	// Utility routine to set gridbagconstraints.
	private void gridset(GridBagConstraints c,int x, int y, int width, int height, int weightx, int weighty,
					int anchor, int fill)
	{
		c.gridx = x; c.gridy = y;
		c.gridwidth = width; c.gridheight = height;
		c.weightx = weightx; c.weighty = weighty;
		c.anchor = anchor; c.fill = fill;
	}

	public String getAppletInfo() {
		return "CollisionApplet v. 1.0. September 1998.\n"+
		"Written by Drew Dolgert for Dr. Michael Fowler, mf1i@virginia.edu.\n"+
		"Distributed under the Gnu Public License.\nSee "+
		"http://www.gnu.org/copyleft/gpl.html.";
	}

	static final String[][] parameterInfo = {
			{"international","yes or no [yes]", "Setting this to \"no\" turns off internationalization "+
					"in case your browser cannot handle it."},
			{"language","two letter language code", "EN for English, FR for French, etc."},
			{"country","two letter country code", "CN for China, IT for Italy.  Not required."},
			{"threadsleep","integer [40]","Number of milliseconds the firing thread should sleep."},
			{"timestep","integer [20]","Number of timesteps for Mass 1 to strike Mass 2."},
			{"trails","lines or dots [dots]","Whether trails should be small dots or just lines."}
		};
		
	public String[][] getParameterInfo() {
		return parameterInfo;
	}
}

