import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.applet.*;

class Main extends Frame
{
	Main()
	{
		super("Collision Applet");
		Applet ac = new CollisionApplet();
		ac.init();
		add(ac,BorderLayout.CENTER);
		pack();
		show();
		ac.start();
	}

	static public void main(String[] args)
	{		
		if (args.length == 0) {
			new Main();
		} else {
			System.err.println("usage: java Main");
		}
	}
}

