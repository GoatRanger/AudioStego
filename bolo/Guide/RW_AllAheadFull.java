public class RW_AllAheadFull extends bolo.Tank
{
  public void strategy()
  {
    double maxV = maxVelocity(); // get the maximum speed for this robot
    setVelocity(maxV);
  }
}