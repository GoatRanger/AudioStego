public class RW_Lurker extends bolo.Tank
{
  public void strategy()
  {
	while (true) {
	  if (hitByScan()) {
		fireMissile(scannedFromHeading(),scannedFromDistance());
	  }
	}
  } // end strategy
} // end class RW_Lurker
