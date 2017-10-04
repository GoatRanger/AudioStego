public class RW_Approach extends bolo.Scout {
  public void strategy() {
	while (true) {
	  scan(currentHeading());
	  double degrees = Math.toDegrees(3.14);
	  setVelocity(targetDistance()/4 - 4);
	}
  }
}
