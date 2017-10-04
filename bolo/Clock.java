package bolo;

class Clock {
  private static long startTime; // ms since 1970
  private static double elapsedTime; // seconds since startTime was set

  public static void start() {
    startTime = now();
	elapsedTime = 0.0;
  }

  public static double update() { // returns time since last update
    double now = (now() - startTime) / 1000.0;
	double deltaT = now - elapsedTime;
	elapsedTime = now;
	return deltaT;
  }

  public static double read() { // returns time since last start
    return elapsedTime;
  }

  private static long now() {
    return System.currentTimeMillis();
  }

  static {
    start();
  }
}
