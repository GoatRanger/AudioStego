public class RW_Vulture extends bolo.Tank {
  public void strategy() {
    int radius = 35;
    setVelocity(maxVelocity());
	pivotRightDegrees(90);
	while (true) {
	  double dh = hospitalDistance();
	  /* want a function that is
	       - approaches zero as dh gets very large
		   - slightly below one if dh > radius
	       - close to one if dh == radius
		   - slightly above one if dh < radius
      */
	  double correction = radius/dh;
	  pivotTo(hospitalHeading() + RADIANS_90 * correction);

	  if (hitByScan()) {
	    fireBullet(scannedFromHeading());
      }
	}
  }
}
