public class RW_Tester extends bolo.Tank {
  public void strategy() {
    pivotRight(RADIANS_180);
    setVelocity(maxVelocity());
    while (true) {
      if (scan(currentHeading()) || targetDistance() < 25)
	    pivotRight(RADIANS_90);
      double inward = currentHeading() + RADIANS_90;
	  if (scan(inward))
	    fireMissile(inward,targetDistance());
      scan(hospitalHeading());
    }
  }
}
