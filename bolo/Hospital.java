package bolo;

import java.awt.*;
import java.awt.geom.*;

class Hospital extends Thing {
  static final Area SHAPE = new Area(Util.rectangle(40,90));
  static {
    SHAPE.add(new Area(Util.rectangle(90,40)));
  }

  Color makeColor() {
//  double red = 0.90 + 0.10 * Math.sin(2*Clock.read());
//	return new Color((float)red,0.0f,0.0f,0.5f);
    double transparency = 0.5 + 0.25 * Math.sin(2*Clock.read());
	return new Color(1.0f,0.0f,0.0f,(float)transparency);
  }

  Hospital(double x,double y) {
    this.color = makeColor();
	this.shape = Util.translate(SHAPE,x,y);
	Arena.actionQueue.add(this);
  }

  void tick(double deltaT) {
    color = makeColor();
  }

  void action() {
    Arena.visibleThings.add(this);
	Arena.timedThings.add(this);
    //Arena.stationaryThings.add(this);
  }
}