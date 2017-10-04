package bolo;

import java.awt.*;
import java.awt.geom.*;

public abstract class Howitzer extends RobotAPI {
  // give the missiles longer range and faster reloads
  final Ammunition.Properties MISSILE_PROPERTIES =
    new Ammunition.Properties(20,300,Missile.VELOCITY,2.0);

  final Weapon MISSILE = Missile.newWeapon(MISSILE_PROPERTIES);
  final Weapon CRUISE_MISSILE = CruiseMissile.newWeapon();
  final Weapon CHAFF = Chaff.newWeapon();

  static final double RADIUS = 8.0;

  void initialize(String name,int pos) {
    maxHealth = 90;
	maxVelocity = 15;
	radius = RADIUS;
	rebound = RADIUS+2;

    // define the howitzer shape
	double angle = 4*Math.PI/5;
	float sin = (float)(RADIUS * Math.sin(angle));
	float cos = (float)(RADIUS * Math.cos(angle));
    GeneralPath howitzerShape = new GeneralPath();
	howitzerShape.append(Util.circle(RADIUS),false);
    howitzerShape.moveTo((float)RADIUS,0.0f);
	howitzerShape.lineTo(-(float)RADIUS/2,(float)RADIUS/4);
	howitzerShape.lineTo(-(float)RADIUS/2,-(float)RADIUS/4);
/*
	howitzerShape.lineTo(cos,sin);
	howitzerShape.lineTo(cos,sin/3);
	howitzerShape.lineTo(-(float)RADIUS,0.0f);
	howitzerShape.lineTo(cos,-sin/3);
	howitzerShape.lineTo(cos,-sin);
*/
	howitzerShape.closePath();

/* NON-CIRCULAR SHAPE
	double angle = 3*Math.PI/4;
	float sin = (float)(RADIUS * Math.sin(angle));
	float cos = (float)(RADIUS * Math.cos(angle));
    GeneralPath howitzerShape = new GeneralPath();
    howitzerShape.moveTo((float)RADIUS,0.0f);
	howitzerShape.lineTo(cos,sin);
	howitzerShape.lineTo(cos,sin/3);
	howitzerShape.lineTo(-(float)RADIUS,0.0f);
	howitzerShape.lineTo(cos,-sin/3);
	howitzerShape.lineTo(cos,-sin);
	howitzerShape.closePath();
*/

	originalShape = howitzerShape;

    super.initialize(name,pos);
  }

  public final void fireMissile(double radians,double distance) {
    fire(MISSILE,radians,distance);
  }

  public final void fireCruiseMissile(double radians,double distance) {
    fire(CRUISE_MISSILE,radians,distance);
  }

  public final void fireChaff(double radians,double distance) {
    fire(CHAFF,radians,distance);
  }

  public final void fireMissileDegrees(double degrees,double distance) {
    fire(MISSILE,toRadians(degrees),distance);
  }

  public final void fireCruiseMissileDegrees(double degrees) {
    fire(CRUISE_MISSILE,toRadians(degrees),0);
  }

  public final void fireChaffDegrees(double degrees,double distance) {
    fire(CHAFF,toRadians(degrees),distance);
  }

  public final double maxMissileRange() {
    return MISSILE.properties.maxRange;
  }

  public final double maxCruiseMissileRange() {
    return CRUISE_MISSILE.properties.maxRange;
  }

  public final double maxChaffRange() {
    return CHAFF.properties.maxRange;
  }

  public final double minMissileRange() {
    return MISSILE.properties.minRange;
  }

  public final double minCruiseMissileRange() {
    return CRUISE_MISSILE.properties.minRange;
  }

  public final double minChaffRange() {
    return CHAFF.properties.minRange;
  }

  public final double missileVelocity() {
    return MISSILE.properties.velocity;
  }

  public final double cruiseMissileVelocity() {
    return CRUISE_MISSILE.properties.velocity;
  }

  public final double chaffVelocity() {
    return CHAFF.properties.velocity;
  }

  public final double missileDelay() {
    return MISSILE.properties.reloadDelay;
  }

  public final double cruiseMissileDelay() {
    return CRUISE_MISSILE.properties.reloadDelay;
  }

  public final double chaffDelay() {
    return CHAFF.properties.reloadDelay;
  }

  public final int numMissiles() {
    return MISSILE.rounds;
  }

  public final int numCruiseMissiles() {
    return CRUISE_MISSILE.rounds;
  }

  public final int numChaff() {
    return CHAFF.rounds;
  }

}