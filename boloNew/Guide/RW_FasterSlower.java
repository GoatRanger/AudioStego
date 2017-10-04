public class RW_FasterSlower extends bolo.Tank {
  public void strategy() {
    while (true)
	{
	  while (currentVelocity() < maxVelocity())
	  {
	    setVelocity(currentVelocity() + 0.0001);
	  }
	  while (currentVelocity() > 0)
	  {
	    setVelocity(currentVelocity() - 0.0001);
	  }
	}
  }
}
