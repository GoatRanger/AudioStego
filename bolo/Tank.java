package bolo;

import java.awt.*;
import java.awt.geom.*;

public abstract class Tank extends RobotAPI{
  final Weapon MISSILE = Missile.newWeapon(20);
  final Weapon BULLET = Bullet.newWeapon();
  final Weapon CHAFF = Chaff.newWeapon();

  static final double RADIUS = 7.0;

  void initialize(String name,int pos) {
    maxHealth = 100;
	maxVelocity = 30;
	radius = RADIUS;
	rebound = RADIUS+2;

    // define the tank shape
	double angle = 7*Math.PI/8;
	float sin = (float)(RADIUS * Math.sin(angle));
	float cos = (float)(RADIUS * Math.cos(angle));
    GeneralPath tankShape = new GeneralPath();
	Shape circle = Util.circle(RADIUS);
	tankShape.append(circle,false);
    tankShape.moveTo(cos+1,sin);
	tankShape.lineTo((float)RADIUS,0);
	tankShape.lineTo(cos+1,-sin);

/* NON-CIRCULAR SHAPE
	double angle = 3*Math.PI/4;
	float sin = (float)(RADIUS * Math.sin(angle));
	float cos = (float)(RADIUS * Math.cos(angle));
    GeneralPath tankShape = new GeneralPath();
	tankShape.moveTo((float)RADIUS,0);
	tankShape.lineTo(cos,sin);
	tankShape.lineTo(-(float)RADIUS,0);
	tankShape.lineTo(cos,-sin);
	tankShape.closePath();
*/

/*
	GeneralPath tankShape = new GeneralPath();
	Shape circle = Util.circle(radius);
	tankShape.append(circle,false);
    tankShape.moveTo(-VEE_LENGTH/2,-VEE_WIDTH/2);
	tankShape.lineTo(VEE_LENGTH/2,0);
	tankShape.lineTo(-VEE_LENGTH/2,VEE_WIDTH/2);
*/

	originalShape = tankShape;

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