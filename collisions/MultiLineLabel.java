/*
 * Taken from the Nutshell book on Java Examples by Flanagan.
 * Once again, I haven't even typed in the whole thing.  I should.
 * This is a lightweight component, so caveat emptor.  If it tends to
 * get redrawn and flickers, don't bother double-buffering this class.
 * The only solution is to double-buffer the native component (Frame,
 * Canvas, Container) that this is drawn in.  The alternative solution
 * is to change the word "Component" to "Canvas".
 */

import java.awt.*;
import java.util.*;

/* This is from the Java in a Nutshell book by Flanagan. */
public class MultiLineLabel extends Component {
        protected String label;
        protected int margin_width;
        protected int margin_height;
        protected int alignment;
        public static final int LEFT=0, CENTER=1, RIGHT=2;
        protected int num_lines;
        protected String[] lines;
        protected int[] line_widths;
        protected int max_width;
        protected int line_height;
        protected int line_ascent;
        protected boolean measured = false;
        protected Font drawFont;
		protected boolean bHaveFont;

        public MultiLineLabel(String label, int margin_width,
                                int margin_height, int alignment) {
            this.label = label;
            this.margin_width = margin_width;
            this.margin_height = margin_height;
            this.alignment = alignment;
			bHaveFont = false;
            newLabel();
        }

        public void setLabel(String label) {
            this.label = label;
            newLabel();
            measured = false;
            repaint();
        }

        public void setFont(Font f) {
			drawFont = f;
            super.setFont(f);
			bHaveFont = true;
            measured = false;
            repaint();
        }

        public Dimension getPreferredSize() {
            if (!measured) measure();
            return new Dimension(max_width + 2*margin_width,
                                num_lines*line_height+2*margin_height);

        }

        public Dimension getMinimumSize() { return getPreferredSize(); }

        public void paint(Graphics g) {
			g.setColor(Color.black);
            int x,y;
            Dimension size = this.getSize();
            if (!measured) measure();
            y = line_ascent+(size.height-num_lines*line_height)/2;
            for (int i=0; i<num_lines; i++, y+= line_height) {
                switch(alignment) {
                    default:
                    case LEFT: x = margin_width; break;
                    case CENTER: x = (size.width-line_widths[i])/2; break;
                    case RIGHT: x = size.width-margin_width - line_widths[i]; break;
                }
				if (bHaveFont) g.setFont(drawFont);
                g.drawString(lines[i],x,y);
            }
        }

        protected synchronized void newLabel() {
            StringTokenizer t = new StringTokenizer(label,"\n");
            num_lines = t.countTokens();
            lines = new String[num_lines];
            line_widths = new int[num_lines];
            for (int i =0; i<num_lines; i++) lines[i] = t.nextToken();
        }

        protected synchronized void measure() {
            FontMetrics fm;
            if (bHaveFont) fm = this.getToolkit().getFontMetrics(drawFont);
            else fm = this.getToolkit().getFontMetrics(this.getFont());
            line_height = fm.getHeight();
            line_ascent = fm.getAscent();
            max_width = 0;
            for (int i=0; i<num_lines; i++) {
                line_widths[i] = fm.stringWidth(lines[i]);
                if (line_widths[i]>max_width) max_width = line_widths[i];
            }
            measured = true;
        }
}
