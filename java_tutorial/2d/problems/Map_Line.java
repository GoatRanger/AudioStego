
import java.awt.*;
import java.applet.Applet;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;


public class Map_Line extends Applet {

    private BufferedImage bi;

    public Map_Line() {

            setBackground(Color.white);

            Image img = getToolkit().getImage("images/bld.jpg");
	    try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                tracker.waitForID(0);
            } catch (Exception e) {}

            int iw = img.getWidth(this);
            int ih = img.getHeight(this);
            bi = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
            Graphics2D big = bi.createGraphics();
            big.drawImage(img,0,0,this);

    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int w = getSize().width;
        int h = getSize().height;
        int bw = bi.getWidth(this);
        int bh = bi.getHeight(this);

        g2.drawImage(bi, null, 0, 0);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5.0f));
        g2.drawLine(10, 10, bw-10, bh-10);
    }

    public static void main(String s[]) {
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        };
        Frame f = new Frame("Map_Line");
        f.addWindowListener(l);
        f.add("Center", new Map_Line());
        f.pack();
        f.setSize(new Dimension(350, 250));
        f.show();
    }
}


