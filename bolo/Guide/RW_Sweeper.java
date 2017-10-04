public class RW_Sweeper extends bolo.Tank {
  public void strategy() {
    double aim = currentHeading();
	while (true) {
	  if (canFire()) {
		fireMissile(aim,150);
	    aim += 0.5; // radians, not degrees
	  }
	}
  }
}
