public class RW_EdgeHugger extends bolo.Tank
{
  public void strategy()
  {
    pivotLeftDegrees(180.0); // face the wall
    setVelocity(maxVelocity());
    while (true)
	{
	  // check if a wall is closer than 15 meters
      if (!scan(currentHeading()) && targetDistance() < 15)
	  {
	    pivotRightDegrees(90.0);
      }
    }
  }
}
