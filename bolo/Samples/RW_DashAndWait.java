public class RW_DashAndWait extends bolo.Tank {
  public void strategy() {
    while (true) {
	  dash(3);
	  lurk();
	}
  }

  void dash(int duration) {
    double startTime = elapsedTime();
	pivotRight(0.3);
	setVelocity(maxVelocity());
	while (elapsedTime() - startTime <= duration) {
	  scan(currentHeading());
	  if (targetDistance() < 50) pivotRight(2.0);
	}
    setVelocity(0);
  }

  void lurk() {
    double startTime = elapsedTime();
	while (!hitByScan()) ;
	if (elapsedTime() - whenScanned() < 0.5) {
      fireMissile(scannedFromHeading(),scannedFromDistance());
    }
  }
}
