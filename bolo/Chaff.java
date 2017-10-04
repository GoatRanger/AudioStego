package bolo;

import java.awt.*;

class Chaff extends Ammunition {
  static final double VELOCITY = 100;
  static final double RELOAD_DELAY = 3.0;
  static final double RADIUS = 2;
  static final Color COLOR = Color.black;
  static final Shape SHAPE = Util.circle(RADIUS);

  static final Color EX_COLOR = new Color(0.3f,0.3f,0.3f,0.4f);
  static final Shape EX_SHAPE = Util.circle(45);
  static final double EX_DURATION = 2.0;
  static final double DAMAGE = 1;

  private Chaff(double x,double y,double heading,double distance) {
    super(x,y,heading,distance,VELOCITY,RADIUS,COLOR,SHAPE);

	Arena.actionQueue.add(this);
  }

  void explode() {
    new Explosion(x,y,EX_COLOR,EX_SHAPE,DAMAGE,EX_DURATION,
				  Sound.MISSILE_EXPLOSION) {
      void action() {
	    super.action();
		Arena.scannableThings.add(this);
	  }
    };
	obsolete = true;
  }

  void action() {
    super.action();
    Sound.play(Sound.FIRE_MISSILE);
  }

  static Ammunition.Properties properties =
    new Ammunition.Properties(1,50,VELOCITY,RELOAD_DELAY);

  static Weapon newWeapon() {
    return new Weapon(1000,properties,new Chaff.Factory());
  }

  static class Factory implements Ammunition.Factory {
    public void makeInstance(double x,double y,double heading,double distance) {
	  new Chaff(x,y,heading,distance);
	}
  }
}

