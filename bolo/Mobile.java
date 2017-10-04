package bolo;

import java.awt.*;
import java.awt.geom.*;

abstract class Mobile extends Thing {
  double x;
  double y;
  double velocity;
  double heading; // in radians
  double radius; // all mobile things are circular
  double rebound = 0.0;

  Shape zeroShape; // maintain the shape at the origin

  void move(double dx,double dy) {
    x += dx;
	y += dy;

    shape = Util.translate(zeroShape,x,y);
  }

  void tick(double deltaT) {
    double distance = velocity * deltaT;
	move(distance * Math.cos(heading),
	     distance * Math.sin(heading));
  }

  void collideWithWall() {}
  void collideWith(Thing other) {}
}
