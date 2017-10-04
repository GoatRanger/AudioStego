public class RW_Sweeper extends bolo.Tank {
  public void strategy() {
    double aim = currentHeading();
	while (true) {
	  if (canFire()) {
		fireBullet(aim);
	    aim += 0.1; // radians, not degrees
	  }
	}
  }
}
