package eecs.robot.finalProject.finalProject051;

public class finalProjectSolution extends IT105Project051 {
  static int numberOfSectorsCleared = 0;
  static int numberOfSectors = 0;
  static String currentObstacle = "";

  public static void bypassFire() {
    //	pivotTo(90);
    //goForward();
    setXStartPoint();
    goForward();
    while (getTemperatureSensor() > 90) {
      //
    }
    halt();
    pivotTo(0);
    //find red
    while (getColorSensor() != 48) {
      goForward();
    }
    while (getColorSensor() == 48) {
      goForward();
    }
    goForward(2000);
  }

  public static void bypassWire() {
    while (getColorSensor() != 49) {
      goForward(); //get to the Wire area..
    }
    while (getColorSensor() == 49) {
      //goForward();
      if (getTouchSensor() == ON) {
        goBackward(1000);
        if (getDualLightSensor() > 0)
          pivotTo(90);
        else {
          pivotTo(270);
        }
        goForward(1000);
        pivotTo(0);
      }
      if (getDualLightSensor() > 0) {
        pivotRight(10);
        goForward(100);
      }
      else {
        pivotLeft(10);
        goForward(100);
      }
    }
    pivotTo(0);
    goForward(1000);
  }

  private static void setXStartPoint() {
    if (getMyXPosition() < 450) {
      pivotTo(270);
    }
    else {
      pivotTo(90);
    }
    while (getTouchSensor() == OFF) {
      goForward();
    }
    goBackward(500);
    pivotLeft(180);
  }

  public static void bypassWater() {
    setXStartPoint();
    while (!(getColorSensor() > 32 && (getColorSensor() < 34))) {
      goForward();
    }
    halt();
    while (getMyYPosition() > 300) {
      if (getColorSensor() == 33) {
        pivotRight(5);
      }
      else
        if (getColorSensor() == 50) {
          pivotLeft(5);
        }
        else
          goForward();
    }
    halt();
    goForward(500);
  }

  public static void bypassWall() {
    //
  }

  public static void determineWeaponStatus() {
    boolean weightStatus = checkWeight();
    boolean metalStatus = checkMetal();
    boolean radiationStatus = checkRadiation();
    if (weightStatus && metalStatus || weightStatus && radiationStatus || metalStatus && radiationStatus)
      setCurrentSectorsWeaponStatus(true);
    else
      setCurrentSectorsWeaponStatus(false);
    printLine("My solution is: weight " + weightStatus + " metalStatus " + metalStatus + " radiationStatus " + radiationStatus);
  }

  public static void storeCurrentSectorWeaponStatus(boolean b) {
    setCurrentSectorsWeaponStatus(b);
  }

  public static boolean checkWeight() {
    return (getWeightSensor() >= 5000);
  }

  public static boolean checkMetal() {
    return (getMetalSensor().equalsIgnoreCase("lead"));
  }

  public static boolean checkRadiation() {
    return (getRadiationSensor() > .0002 && getRadiationSensor() < .0003);
  }

  public static void clearObstacle() {
    currentObstacle = getCurrentSectorsObstacleType();
    if (currentObstacle.equalsIgnoreCase("fire")) {
      bypassFire();
    }
    else
      if (currentObstacle.equalsIgnoreCase("wire")) {
        bypassWire();
      }
      else
        if (currentObstacle.equalsIgnoreCase("water")) {
          bypassWater();
        }
        else
          if (currentObstacle.equalsIgnoreCase("wall")) {
            bypassWall();
          }
  }

  public static void main(String[] args) {
    //loadSingleFixedFire();
    //loadSingleFixedWater();
    // loadSingleFixedWire();
    //loadSingleRandom();
    loadTwoFixed();
    // loadTwoRandom();
    //loadThreeFixed();
    // loadThreeRandom();
    numberOfSectorsCleared = 0;
    numberOfSectors = 1;
    while (numberOfSectorsCleared < numberOfSectors) {
      clearObstacle();
      gotoPoint(getCurrentWeaponsXLocation(), getCurrentWeaponsYLocation());
      determineWeaponStatus();
      numberOfSectorsCleared++;
      gotoPoint(getCurrentTeleportersXLocation(), getCurrentTeleportersYLocation());
      //teleportRobotToVirginiaSector();
      //printLine(getCurrentSectorsObstacleType() + "*****");
      // gotoPoint(getCurrentSectorsWeaponXLocation(), getCurrentSectorsWeaponYLocation());
      if (numberOfSectorsCleared != numberOfSectors)
        teleportRobotToNextSector();
      //Thread.sleep(2000);
    }
    submitUNReport();
  }
}