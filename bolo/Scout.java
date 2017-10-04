package bolo;

import java.awt.*;
import java.awt.geom.*;

/**
  A lightly armed and armored vehicle that relies on speed.
*/
public abstract class Scout extends RobotAPI {
  final Weapon MISSILE = Missile.newWeapon(6);
  final Weapon BULLET = Bullet.newWeapon();
  final Weapon CHAFF = Chaff.newWeapon();

  static final double RADIUS = 6.0;

  void initialize(String name,int pos) {
    maxHealth = 75;
	maxVelocity = 50;
	radius = RADIUS;
	rebound = RADIUS+2;

    // define the tank shape
	double angle = 7*Math.PI/8;
	float sin = (float)(RADIUS * Math.sin(angle));
	float cos = (float)(RADIUS * Math.cos(angle));
    GeneralPath scoutShape = new GeneralPath();
	Shape circle = Util.circle(RADIUS);
	scoutShape.append(circle,false);
    scoutShape.moveTo(cos+1,sin);
	scoutShape.lineTo((float)RADIUS,0);
	scoutShape.lineTo(cos+1,-sin);

/*
    // make a narrow triangle shape
	double angle = 3*Math.PI/4;
	float sin = (float)(RADIUS * Math.sin(angle));
	float cos = (float)(RADIUS * Math.cos(angle));
    GeneralPath scoutShape = new GeneralPath();
    scoutShape.moveTo((float)RADIUS,0.0f);
	scoutShape.lineTo(cos,sin);
	scoutShape.lineTo(cos/2,0.0f);
	scoutShape.lineTo(cos,-sin);
	scoutShape.closePath();
*/

	originalShape = scoutShape;

    super.initialize(name,pos);
  }

  public final void fireMissile(double radians,double distance) {
    fire(MISSILE,radians,distance);
  }

  public final void fireBullet(double radians) {
    fire(BULLET,radians,0);
  }

  public final void fireChaff(double radians,double distance) {
    fire(CHAFF,radians,distance);
  }

  public final void fireMissileDegrees(double degrees,double distance) {
    fire(MISSILE,toRadians(degrees),distance);
  }

  public final void fireBulletDegrees(double degrees) {
    fire(BULLET,toRadians(degrees),0);
  }

  public final void fireChaffDegrees(double degrees,double distance) {
    fire(CHAFF,toRadians(degrees),distance);
  }

  public final double maxMissileRange() {
    return MISSILE.properties.maxRange;
  }

  public final double maxBulletRange() {
    return BULLET.properties.maxRange;
  }

  public final double maxChaffRange() {
    return CHAFF.properties.maxRange;
  }

  public final double minMissileRange() {
    return MISSILE.properties.minRange;
  }

  public final double minChaffRange() {
    return CHAFF.properties.minRange;
  }

  public final double missileVelocity() {
    return MISSILE.properties.velocity;
  }

  public final double bulletVelocity() {
    return BULLET.properties.velocity;
  }

  public final double chaffVelocity() {
    return CHAFF.properties.velocity;
  }

  public final double missileDelay() {
    return MISSILE.properties.reloadDelay;
  }

  public final double bulletDelay() {
    return BULLET.properties.reloadDelay;
  }

  public final double chaffDelay() {
    return CHAFF.properties.reloadDelay;
  }

  public final int numMissiles() {
    return MISSILE.rounds;
  }

  public final int numBullet() {
    return BULLET.rounds;
  }

  public final int numChaff() {
    return CHAFF.rounds;
  }

}
