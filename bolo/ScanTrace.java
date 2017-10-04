package bolo;

import java.awt.*;
import java.awt.geom.*;

class ScanTrace extends Transient {
  ScanTrace(double x,double y,double radius,double heading,double distance) {
    super(Const.SCAN_DURATION);

	color = Const.SCAN_COLOR;
	Shape line = new Rectangle2D.Double(radius,-0.5,distance-radius,1);
	line = Util.rotate(line,heading);
	shape = Util.translate(line,x,y);
  }

  void action() {}
}
