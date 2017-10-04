public class RW_HerkyJerky extends bolo.Tank
{
  public void strategy()
  {
    double maxV = maxVelocity();
	double timeMark; // keeps track of when each phase started
    while (true)
	{
	  // hurry up...
	  timeMark = elapsedTime();
	  setVelocity(maxV);
	  while (timeSince(timeMark) < 2)
	  {
	    // do nothing
	  }
	  
	  // ...and wait
	  timeMark = elapsedTime();
	  setVelocity(0);
	  while (timeSince(timeMark) < 2)
	  {
	    // do nothing
	  }
    }
  }
}