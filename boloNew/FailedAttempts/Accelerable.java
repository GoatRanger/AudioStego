package bolo;

import java.awt.*;

// I tried this out, and it works ok, but makes programming the
// robots significantly harder

abstract class Accelerable extends Mobile {
  static final double ACCELERATION = 50.0;
  static final double TURN_RATE = 2 * Math.PI;
    // can turn a complete circle every second

  double desiredVelocity;
  double desiredHeading;

  Shape unrotatedZeroShape = Const.ROBOT_SHAPE;

  void updateRotatedShape() {
    zeroShape = Util.rotate(unrotatedZeroShape, heading);
	shape = Util.translate(zeroShape,x,y);
  }

  void tick(double deltaT) {
    double deltaV = ACCELERATION * deltaT;
    if (desiredVelocity > velocity) {
	  velocity = Math.min(velocity+deltaV, desiredVelocity);
	}
	else if (desiredVelocity < velocity) {
	  velocity = Math.max(velocity-deltaV, desiredVelocity);
	}

    double deltaAngle = TURN_RATE * deltaT;
	double diffAngle = RoboWarrior.normalizeRadians(desiredHeading - heading);
	if (diffAngle < Math.PI) { // turn right
	  heading += Math.min(deltaAngle,diffAngle);
	}
	else { // turnLeft
	  heading -= Math.min(deltaAngle, 2*Math.PI-diffAngle);
	}

    super.tick(deltaT);

		updateRotatedShape();

  }

  void collideWith(Thing other) {
    // rebound only if hit a stationary object
	if (other instanceof Obstacle) rebound();
  }

  void rebound() {
    velocity = -velocity / 2;

	double distance = velocity * 0.25;
	move(distance * Math.cos(heading),
	     distance * Math.sin(heading));
  }
}
