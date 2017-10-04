package bolo;

import java.awt.*;

class Missile extends Ammunition {
  static final double VELOCITY = 100;
  static final double RADIUS = 2;
  static final double RELOAD_DELAY = 3.0;
  static final Shape SHAPE = Util.circle(RADIUS);
  static final Color COLOR = Color.black;

  private Missile(double x,double y,double heading,double distance) {
    super(x,y,heading,distance,VELOCITY,RADIUS,COLOR,SHAPE);

	Arena.actionQueue.add(this);
  }

  void explode() {
    new Explosion(x,y,
	              Const.MISSILE_EXPLOSION_COLOR,
				  Const.MISSILE_EXPLOSION_SHAPE,
				  Const.MISSILE_EXPLOSION_DAMAGE,
				  Const.EXPLOSION_DURATION,
				  Sound.MISSILE_EXPLOSION);
	obsolete = true;
  }

  void action() {
    super.action();
    Sound.play(Sound.FIRE_MISSILE);
  }

  static Ammunition.Properties properties =
    new Ammunition.Properties(20,200,VELOCITY,RELOAD_DELAY);

  static Weapon newWeapon() {
    return new Weapon(1000,properties,new Missile.Factory());
  }

  static Weapon newWeapon(Ammunition.Properties properties) {
    return new Weapon(1000,properties,new Missile.Factory());
  }

  static Weapon newWeapon(int numRounds) {
   return new Weapon(numRounds,properties,new Missile.Factory());
  }

  static Weapon newWeapon(int numRounds,Ammunition.Properties properties) {
    return new Weapon(numRounds,properties,new Missile.Factory());
  }

  static class Factory implements Ammunition.Factory {
    public void makeInstance(double x,double y,double heading,double distance) {
	  new Missile(x,y,heading,distance);
	}
  }
}
