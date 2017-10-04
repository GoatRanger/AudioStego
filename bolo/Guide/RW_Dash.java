public class RW_Dash extends bolo.Tank
{
  public void strategy()
  {
    setVelocity(10.0);
	double startTime = elapsedTime();
	while (timeSince(startTime) < 4.5)
	{
	  // wait
	}
	setVelocity(0);
  }
}