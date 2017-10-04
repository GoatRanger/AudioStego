public class RW_ToAndFro extends bolo.Tank
{
  public void strategy()
  {
    setVelocity(maxVelocity());
    while (true)
	{
	  // is a wall ahead?
	  double ahead = currentHeading();
      if (!scan(ahead)) // scan returns false for walls
	  {
	    // is it closer than 15 meters?
	    if (targetDistance() < 15)
	    {
	      pivotRightDegrees(180.0);
        }
      }
    }
  }
}
