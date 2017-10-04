package bolo;

import java.awt.*;

class Obstacle extends Thing {
  Obstacle(Color color,Shape shape) {
    this.color = color;
	this.shape = shape;
	Arena.actionQueue.add(this);
  }

  void action() {
    Arena.visibleThings.add(this);
    //Arena.stationaryThings.add(this);
    Arena.scannableThings.add(this);
  }
}
