public class RW_ToAndFro2 extends bolo.Tank
{
  public void strategy()
  {
    setVelocity(maxVelocity());
    while (true)
	{
	  double ahead = currentHeading();
      if (!scan(ahead))
	  {
	    if (targetDistance() < 15)
	    {
	      pivotRightDegrees(180.0);
        }
      }
	  if (scan(ahead - RADIANS_90)) // look left for a robot
	  {
	    fireMissile(ahead - RADIANS_90, targetDistance());
	  }
    }
  }
}
