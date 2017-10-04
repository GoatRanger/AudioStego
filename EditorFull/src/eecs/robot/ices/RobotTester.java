package eecs.robot.ices;

public class RobotTester extends RobotTestArena {
  public static void main(String[] args) {
    moveToTask1();
    solveWall();
    moveToTask2();
    solveFindWhite();
    moveToTask3();
    solveTrackLight();
    moveToTask4();
    solveBlackLines();
  }

  public static void solveWall() {
    while (getTouchSensor() == OFF) {
      goForward();
    }
    goBackward(1000);
    pivotRight(360);
    halt();
    wait(500);
  }

  public static void wait(int time) {
    try {
      Thread.sleep(time);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void solveFindWhite() {
    while (getColorSensor() != 50) {
      goForward();
    }
    halt();
    System.out.println("FOUND WHITE!");
    wait(500);
  }

  public static void solveTrackLight() {
    while (getMyXPosition() <= 700) {
      if (getDualLightSensor() > 0) {
        pivotRight(10);
      }
      else {
        pivotLeft(10);
      }
      goForward(1500);
    }
    halt();
    wait(500);
  }

  public static void solveBlackLines() {
    int numberLinesToCount = 2;//cadets should declare as static
    int numberLinesCounted = 0;
    while (numberLinesCounted < numberLinesToCount) {
      while (getColorSensor() != 33) {//find black
        goForward();
      }
      printLine("found a black line");
      while (getColorSensor() == 33) {//move until you are off of black
        goForward();
      }
      numberLinesCounted++;
    }
    halt();
    wait(500);
  }
}