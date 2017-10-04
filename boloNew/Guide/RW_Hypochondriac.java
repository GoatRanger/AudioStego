public class RW_Hypochondriac extends bolo.Tank
{
  public void strategy()
  {
    pivotTo(hospitalHeading());
	setVelocity(maxVelocity());
	while (hospitalDistance() > 10)
	{
	  // do nothing
	}
	setVelocity(0);
  } // end strategy
} // end class RW_Hypochondriac
