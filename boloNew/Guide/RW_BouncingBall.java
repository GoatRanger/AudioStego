public class RW_BouncingBall extends bolo.Tank
{
  public void strategy()
  {
    pivotRightDegrees(45);
    setVelocity(maxVelocity());
	while (true) {
	  if (checkForCollision())
	  {
		pivotLeftDegrees(85);
	  }
	}
  } // end strategy
} // end class RW_BouncingBall
