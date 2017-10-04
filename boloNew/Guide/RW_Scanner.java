public class RW_Scanner extends bolo.Tank
{
  public void strategy()
  {
    double aim = currentHeading();
	while (true)
	{
	  if (scan(aim))
	  {
		fireMissile(aim,targetDistance());
	  }
	  aim += 0.05; // radians, not degrees
	}
  } // end strategy
} // end class RW_Scanner
