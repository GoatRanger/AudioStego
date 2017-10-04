package bolo;

import java.awt.*;

class CruiseMissile extends Ammunition {
  // long-range and powerful, but slow
  static final double VELOCITY = 100;
  static final double RELOAD_DELAY = 5.0;
  static final double RADIUS = 2.5;
  static final Color COLOR = Color.darkGray;
  static final Shape SHAPE = Util.circle(RADIUS);

  static final Shape EXPLOSION_SHAPE = Util.circle(75);
  static final double EXPLOSION_DAMAGE = 55;
  static final double EXPLOSION_DURATION = 1.5;

  private CruiseMissile(double x,double y,double heading,double distance) {
    super(x,y,heading,distance,VELOCITY,RADIUS,COLOR,SHAPE);

	Arena.actionQueue.add(this);
  }

  void explode() {
    new Explosion(x,y,
	              Const.MISSILE_EXPLOSION_COLOR,
				  EXPLOSION_SHAPE,
				  EXPLOSION_DAMAGE,
				  EXPLOSION_DURATION,
				  Sound.MISSILE_EXPLOSION);
	obsolete = true;
  }

  void action() {
    super.action();
    Sound.play(Sound.FIRE_MISSILE);
  }

  static Ammunition.Properties properties =
    new Ammunition.Properties(120,300,VELOCITY,RELOAD_DELAY);

  static Weapon newWeapon() {
    return new Weapon(3,properties,new CruiseMissile.Factory());
  }

  static Weapon newWeapon(Ammunition.Properties properties) {
    return new Weapon(3,properties,new CruiseMissile.Factory());
  }

  static class Factory implements Ammunition.Factory {
    public void makeInstance(double x,double y,double heading,double distance) {
	  new CruiseMissile(x,y,heading,distance);
	}
  }
}
