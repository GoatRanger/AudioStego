package bolo;

import java.awt.*;
import java.awt.geom.*;

// the root class for all bolo objects
abstract class Thing extends Action {
  boolean obsolete = false;

  Color color;
  Shape shape;

  boolean overlaps(Thing other) {
    return Util.overlaps(this.shape,other.shape);
  }

  // the following methods should only be called by the animation thread

  void tick(double deltaT) {}
  void collideWith(Thing other) {}
  void damage(double dmg) {}

  void draw(Graphics2D g) {
	g.setPaint(color);
	g.fill(shape);
  }
} // end class Thing
