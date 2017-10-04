package bolo;

import java.awt.*;

class Bullet extends Ammunition {
  static final double DAMAGE = 5;
  static final double VELOCITY = 300;
  static final double RELOAD_DELAY = 0.3;
  static final double RANGE = 250;
  static final double VISIBLE_RADIUS = 1.5;
  static final double HIT_RADIUS = 4;
  static final double APPROX_RADIUS = 14; // approximate radius of robot

  static final Color COLOR = Color.black;
  static final Shape VISIBLE_SHAPE = Util.circle(VISIBLE_RADIUS);
  // static final Shape HIT_SHAPE = Util.circle(HIT_RADIUS);

  private Bullet(double x,double y,double heading,double distance) {
    super(x + Math.cos(heading) * APPROX_RADIUS,
	      y + Math.sin(heading) * APPROX_RADIUS,
	      heading,distance,VELOCITY,HIT_RADIUS,COLOR,VISIBLE_SHAPE);

	Arena.actionQueue.add(this);
  }

  void draw(Graphics2D g) {
	g.setPaint(color);
	g.fill(Util.translate(VISIBLE_SHAPE,x,y));
  }

  void explode() {
    obsolete = true;
  }

  void collideWithWall() {
    new Impact(x,y);
	obsolete = true;
  }

  void collideWith(Thing other) {
    if (other instanceof Robot) {
	  other.damage(DAMAGE);
      new Impact(x,y);
	  obsolete = true;
    }
  }

  void action() {
    super.action();
    Sound.play(Sound.FIRE_MISSILE);
  }

  static Ammunition.Properties properties =
    new Ammunition.Properties(RANGE,RANGE,VELOCITY,RELOAD_DELAY);

  static Weapon newWeapon() {
    return new Weapon(1000,properties,new Bullet.Factory());
  }

  static class Factory implements Ammunition.Factory {
    public void makeInstance(double x,double y,double heading,double distance) {
	  new Bullet(x,y,heading,distance);
	}
  }

  private static class Impact extends Transient {
    static final Shape SHAPE = Util.circle(2);
	static final Color COLOR = Color.red;
	static final double DURATION = 0.2;

    Impact(double x,double y) {
	  super(DURATION);
	  color = COLOR;
	  shape = Util.translate(SHAPE,x,y);
	  Arena.actionQueue.add(this);
	}

	void action() {
	  Arena.timedThings.add(this);
	  Arena.visibleThings.add(this);
	}
  }
}

