public class RW_EdgeHugger extends bolo.Tank {
  public void strategy() {
    pivotLeft(RADIANS_180);
    setVelocity(maxVelocity());
    while (true) {
      if (scan(currentHeading()) || targetDistance() < 25)
	    pivotRight(RADIANS_90);
      double inward = currentHeading() + RADIANS_90;
	  if (scan(inward))
	    fireMissile(inward,targetDistance());
    }
  }
}
