package bolo;

import java.awt.*;
import java.awt.geom.*;

class Explosion extends Transient {
  double x;
  double y;
  double duration;
  double blastStrength;
  Shape blastShape;
  int sound;
 
  Explosion(double x,double y,Color color,Shape blastShape,
            double strength,double duration,int sound) {
    super(duration);

    this.x = x;
	this.y = y;
	this.duration = duration;
	this.color = color;
	this.blastShape = blastShape;
	this.sound = sound;

	shape = Util.translate(blastShape,x,y);
	blastStrength = strength;

    Arena.actionQueue.add(this);
  }

  void tick(double deltaT) {
    super.tick(deltaT);
    if (!obsolete) {
	  // blast expands over time
	  double scaleFactor = 1.0 - 0.75*Math.pow(remainingTime/duration,5.0);
	  Shape scaledShape = Util.scale(blastShape,scaleFactor,scaleFactor);
	  shape = Util.translate(scaledShape,x,y);
	}
  }

  // damage those things caught in the blast
  void checkBlastArea() {
    Pool.Iterator it = Arena.vulnerableThings.getIterator();
	for (Thing thing = it.next(); thing != null; thing = it.next()) {
	  if (overlaps(thing))
	    thing.damage(blastStrength);
	}
  }

  void action() {
    Sound.play(sound);
	Arena.visibleThings.add(this);
	Arena.timedThings.add(this);
	new DelayedAction(0.3,
	  new Action () {
	    public void action () { checkBlastArea(); }
      }
    );
  }

} // end class Explosion
