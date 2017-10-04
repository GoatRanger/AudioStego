package bolo;

import java.awt.*;

abstract class Ammunition extends Mobile {
  double remainingTime;

  Ammunition(double x,double y,double heading,double distance,double velocity,
             double radius,Color color,Shape zeroShape) {
    this.x = x;
	this.y = y;
	this.heading = heading;
	this.velocity = velocity;
	this.radius = radius;

    this.color = color;
	this.zeroShape = zeroShape;
	shape = Util.translate(zeroShape,x,y);

    remainingTime = distance/velocity;
  }

  void tick(double deltaT) {
    super.tick(deltaT);

    // could explode during super.tick, so check if obsolete
	// IS THAT STILL TRUE?
    if (!obsolete) {
	  remainingTime -= deltaT;
	  if (remainingTime <= 0.0) explode();
    }
  }

  abstract void explode();

  void collideWithWall() { explode(); }

  void action() {
    Arena.timedThings.add(this);
    Arena.visibleThings.add(this);
    Arena.mobileThings.add(this);
  }

  static class Properties {
    final double minRange;
	final double maxRange;
	final double velocity;
	final double reloadDelay;

    Properties(double minRange,double maxRange,double velocity,double delay) {
	  this.minRange = minRange;
	  this.maxRange = maxRange;
	  this.velocity = velocity;
	  this.reloadDelay = delay;
	}
  }

  interface Factory {
    void makeInstance(double x,double y,double theta,double distance);
  }
}