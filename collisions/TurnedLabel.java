/* Copyright (C) 1998 Michael Fowler under Gnu Public License
 * http://www.gnu.org/copyleft/gpl.html
 *
 * TurnedLabel.java
 * This makes a label turned on its side either clockwise or counterclockwise.
 * It does it by drawing to an image and turning that image.
 */
import java.awt.*;
import java.awt.image.*;

public class TurnedLabel extends Component
{
	static int COUNTERCLOCKWISE = 1;
	static int CLOCKWISE = 2;

	String message;
	int orientation;
	Image rotatedImage;
	Font labelFont;

	public TurnedLabel(String s1, int orient)
	{
		message = s1;
		orientation = orient;
		rotatedImage = null;
		labelFont = getFont();
		if (labelFont==null) labelFont = new Font("SansSerif",Font.PLAIN,12);
		makeImage();
		repaint();
	}

	public TurnedLabel(String s1)
	{
		this(s1,COUNTERCLOCKWISE);
	}

	public void setFont(Font f)
	{
		labelFont = f;
		makeImage();
		repaint();
	}

	public void setBackground(Color c)
	{
		super.setBackground(c);
		makeImage();
		repaint();
	}

	private void makeImage()
	{
		FontMetrics lfm = getToolkit().getFontMetrics(labelFont);
		int width = lfm.stringWidth(message);
		int height = lfm.getHeight()+lfm.getLeading();
		Image ti = createImage(width,height);
		if (ti==null) { return; }
		Graphics tb = ti.getGraphics();
		tb.setFont(labelFont);
		tb.drawString(message,0,lfm.getAscent());
		ImageProducer ip = ti.getSource();
		RotFilter rf = new RotFilter();
		if (orientation == CLOCKWISE) rf.setCounterClockwise(false);
		ip = new FilteredImageSource(ip,rf);
		rotatedImage = Toolkit.getDefaultToolkit().createImage(ip);
	}

	public void update(Graphics g) { paint(g); }

	public void paint(Graphics g)
	{
		if (rotatedImage == null) makeImage();
		if (rotatedImage != null)
			g.drawImage(rotatedImage,0,0,null);
	}

    public Dimension getMaximumSize()
    {
        return(getPreferredSize());
    }

	public Dimension getPreferredSize()
	{
		if (rotatedImage == null) makeImage();
		if (rotatedImage == null) return(new Dimension(40,40));
		return(new Dimension(rotatedImage.getWidth(null),rotatedImage.getHeight(null)) );
	}

	public Dimension getMinimumSize()
	{
		return( getPreferredSize() );
	}
}

