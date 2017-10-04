public class RW_WarCriminal extends bolo.Tank
{
  public void strategy()
  {
    pivotTo(hospitalHeading());
	setVelocity(maxVelocity());
	while (hospitalDistance() > 100)
	{
	  // do nothing
	}
	setVelocity(0);
	while (true)
	{
	  fireMissile(hospitalHeading(),hospitalDistance());
	}
  } // end strategy
} // end class RW_WarCriminal
