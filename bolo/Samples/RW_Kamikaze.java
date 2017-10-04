public class RW_Kamikaze extends bolo.Juggernaut {
  public void strategy() {
    while (true) {
	  search();
	  ram();
	}
  }

  void search() {
    setVelocity(0);

    double angle = currentHeading();
	while (!scan(angle)) {
	  angle += 0.05;
	}

	pivotTo(angle);
	setVelocity(maxVelocity());
  }

  void ram() {
    double lastSeen = elapsedTime();
	double now;
	double arc = 0.0;
	double aim = currentHeading();

	while (true) {
	  if (!scan(currentHeading()) && targetDistance() < 15) return;
	  now = elapsedTime();
	  if (now - lastSeen > 2.0) return;  // stop charging
      if (scan(aim)) {
	    lastSeen = now;
		arc = 0.0;

		// calculate projected position in the future
		// aim robot toward that
		double deltaT = targetDistance() / 100;
		double relativeAngle = targetHeading() - aim;
		double dx = Math.cos(relativeAngle) * targetVelocity() * deltaT;
		double dy = Math.sin(relativeAngle) * targetVelocity() * deltaT;
		dx += targetDistance();
		if (dx != 0.0) {
		  pivotTo(Math.atan(dy/dx) + aim);
		}
		else {
		  pivotTo(dy > 0 ? SOUTH : NORTH);
		}

		// aim the scan 0.4 seconds in the future
		dx = Math.cos(relativeAngle) * targetVelocity() * 0.4;
		dy = Math.sin(relativeAngle) * targetVelocity() * 0.4;
		dx += targetDistance();
		if (dx != 0.0) {
		  aim += Math.atan(dy/dx);
		}
		else {
		  aim = (dy > 0 ? SOUTH : NORTH);
		}

	  }
	  else { // didn't see it this time
		arc += 0.03;
		if (scan(aim+arc)) {
		  aim = aim+arc;
		  arc = 0.0;
		}
		else if (scan(aim-arc)) {
		  aim = aim-arc;
		  arc = 0.0;
		}
	  }
	} 
  }
}