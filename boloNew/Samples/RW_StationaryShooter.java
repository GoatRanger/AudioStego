public class RW_StationaryShooter extends bolo.Scout {
  public void strategy() {
    double aim = currentHeading();
    while (true) {
	  if (canFire()) {
	    fireChaff(aim,10);
      }
	}
  }
}
