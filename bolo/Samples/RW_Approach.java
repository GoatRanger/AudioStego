public class RW_Approach extends bolo.Scout {
  public void strategy() {
	while (true) {
	  scan(currentHeading());
	  setVelocity(targetDistance()/4 - 4);
	}
  }
}
