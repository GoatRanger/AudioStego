package bolo;

import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.util.*;

// I've split what used to be RoboWarrior into two parts:
//  -- Robot implements all the internals of robots
//  -- RobotAPI provides the public API for programming robots
abstract class RobotAPI extends Robot {

/* There are XXX categories of commands:
     - Velocity
	 - Heading
	 - Laser Scanner
	 - Passive Scan Detector
	 - Hospital
	 - Weapons
	 - Time
	 - Health
	 - Collision
	 - Weapons information?
*/


// Velocity

  public final void setVelocity(double v) {
    Thread.yield();
    velocity = Util.bound(0,v,maxVelocity);
  }

  public final double currentVelocity() {
    return velocity;
  }

  public final double maxVelocity() {
    return maxVelocity;
  }

// Heading (in radians)

  public final void pivotLeft(double theta) {
    Thread.yield();
    heading -= theta;
	updateRotatedShape();
  }

  public final void pivotRight(double theta) {
    Thread.yield();
    heading += theta;
	updateRotatedShape();
  }

  public final void pivotTo(double theta) {
    Thread.yield();
    heading = theta;
	updateRotatedShape();
  }

  public final double currentHeading() { 
    return heading;
  }

// Heading (in degrees)

  public final void pivotLeftDegrees(double degrees) {
    pivotLeft(toRadians(degrees));
  }

  public final void pivotRightDegrees(double degrees) {
    pivotRight(toRadians(degrees));
  }

  public final void pivotToDegrees(double degrees) {
    pivotTo(toRadians(degrees));
  }

  public final double currentHeadingDegrees() {
    return toDegrees(heading);
  }

// Laser Scanner

  public final boolean scan(double radians) {
	scanReady = false;
	scanHeading = radians;

	Arena.actionQueue.add(
	  new Action() {
	    void action() { scan(); }
	  }
    );

	while (!scanReady) Thread.yield();
	return foundRobot;
  }

  public final double scanDegrees(double degrees) {
    return toRadians(degrees);
  }
 
  public final double targetDistance() {
    return targetDistance;
  }

  public final double targetHeading() {
    return targetHeading;
  }

  public final double targetHeadingDegrees() {
    return toDegrees(targetHeading);
  }

  public final double targetVelocity() {
    return targetVelocity;
  }

// Passive Scan Detector

  public final boolean hitByScan() {
    Thread.yield();
    boolean tmp = hitByScan;
	hitByScan = false;
	return tmp;
  }

  public final double scannedFromDistance() {
    return scannedFromDistance;
  }

  public final double scannedFromHeading() {
    return scannedFromHeading;
  }

  public final double scannedFromHeadingDegrees() {
    return toDegrees(scannedFromHeading);
  }

  public final double whenScanned() {
    return whenScanned;
  }

// Hospital

  public final double hospitalDistance() {
    return distance(Arena.HOSPITAL_X-x,Arena.HOSPITAL_Y-y);
  }

  public final double hospitalHeading() {
    Thread.yield();

    double dx = Arena.HOSPITAL_X - x;
	double dy = Arena.HOSPITAL_Y - y;

    if (dx == 0)
	  return (dy >= 0) ? SOUTH : NORTH;

    double theta = Math.atan(dy/dx);

	return (dx > 0) ? theta : theta+PI;
  }

  public final double hospitalHeadingDegrees() {
    return toDegrees(hospitalHeading());
  }

// Weapons (most weapons related commands are defined by the chassis)

  public final boolean canFire() {
    Thread.yield();
    return (timeToReload <= 0.0);
  }

// Time

  public final double elapsedTime() {
    Thread.yield();
    return Clock.read();
  }

  public final double timeSince(double time) {
    Thread.yield();
    return Clock.read() - time;
  }

// Health

  public final double currentHealth() {
    return health;
  }

  public final double maxHealth() {
    return maxHealth;
  }

// Collision

  public final boolean checkForCollision() {
    Thread.yield();
    boolean tmp = collision;
	collision = false;
	return tmp;
  }

// Radians and Degrees:

  public static final double NORTH = Const.NORTH;
  public static final double SOUTH = Const.SOUTH;
  public static final double EAST = Const.EAST;
  public static final double WEST = Const.WEST;

  public static final double PI = Math.PI;
  public static final double RADIANS_30 = Math.PI/6;
  public static final double RADIANS_45 = Math.PI/4;
  public static final double RADIANS_60 = Math.PI/3;
  public static final double RADIANS_90 = Math.PI/2;
  public static final double RADIANS_180 = Math.PI;
  public static final double RADIANS_360 = 2*Math.PI;

  public static double toRadians(double degrees) {
    return degrees * Math.PI / 180.0;
  }

  public static double toDegrees(double radians) {
    return radians * 180.0 / Math.PI;
  }

  public static double normalizeRadians(double radians) {
    if (radians < 0 || radians >= RADIANS_360)
	  radians -= RADIANS_360 * Math.floor(radians / RADIANS_360);
    return radians;
  }

  public static double normalizeDegrees(double degrees) {
    if (degrees < 0 || degrees >= 360)
	  degrees -= 360 * Math.floor(degrees / 360);
    return degrees;
  }

// Distance

  public static double distance(double dx,double dy) {
    return Math.sqrt(dx*dx + dy*dy);
  }

// by defining main here, you can run bolo by running a robot

  public static void main(String[] args) {
    Bolo.playGame();
  }
}
