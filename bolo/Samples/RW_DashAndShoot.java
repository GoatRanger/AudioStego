public class RW_DashAndShoot extends bolo.Howitzer {
  public void strategy() {
    while (true) {
	  dash(3);
	  shoot();
	}
  }

  void dash(int duration) {
    double startTime = elapsedTime();
	pivotRight(0.3);
	setVelocity(maxVelocity());
	while (timeSince(startTime) <= duration) {
	  scan(currentHeading());
	  if (targetDistance() < 50) pivotRight(2.0);
	}
    setVelocity(0);
  }

  void shoot() {
    double angle = currentHeading();
	while (!scan(angle)) {
	  angle += 0.05;
	}

	// calculate where missile should go
	// first the angle
/*
	double v_m = (numCruiseMissiles() > 0) ? 
	             cruiseMissileVelocity() : missileVelocity();
*/
    double v_m = missileVelocity();

	double v_t = targetVelocity();
	double theta_t = targetHeading() - angle;
	double theta_m = Math.asin(v_t * Math.sin(theta_t) / v_m);
    double missileHeading = theta_m + angle;

	// now the distance
	double closingRate = v_m * Math.cos(theta_m) - v_t * Math.cos(theta_t);
    // ignore possibility that closing rate is 0 or negative
	double timeToIntercept = targetDistance() / closingRate;
    double distanceToIntercept = timeToIntercept * v_m;

/*
    if (numCruiseMissiles() > 0)
	  fireCruiseMissile(missileHeading,distanceToIntercept);
    else
*/
	  fireMissile(missileHeading,distanceToIntercept);
  }
}
