package eecs.robot.finalProject.finalProject052;

public class finalProjectSolution extends IT105Project052 {
  static int numberOfSectorsCleared = 0;
  static int numberOfSectors = 0;
  static String currentObstacle = "";
  static int totalTimeSeconds = 0;
  static int seconds = 0;
  static int minutes = 0;

  public static void main(String[] args) {
    minutes = getInt("Enter the amount of minutes to start the  countdown");
    startCountDown(minutes);
    while (minutes > 0) {
      --minutes;
      seconds = 60;
      while (seconds > 0) {
        --seconds;
        displayTime(minutes, seconds);
        waitASecond();
        if (seconds == 55) {
          checkTemperatureSensor();
        }
      }
    }
    launchShuttle();
  }
}