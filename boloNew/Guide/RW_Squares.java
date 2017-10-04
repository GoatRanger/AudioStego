public class RW_Squares extends bolo.Tank
{
  public void strategy()
  {
    double maxV = maxVelocity();
	setVelocity(maxV);
	double timeMark; // keeps track of when each side started
    while (true)
	{
	  // one side of the square
	  timeMark = elapsedTime();
	  while (timeSince(timeMark) < 3.0)
	  {
	    // do nothing
	  }
	  pivotRightDegrees(90);
    }
  }
}
