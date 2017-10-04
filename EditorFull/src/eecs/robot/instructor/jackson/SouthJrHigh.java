package eecs.robot.instructor.jackson;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.Random;
import eecs.RobotGui;
import eecs.robot.jago.ImageLoader;

public class SouthJrHigh extends RobotGui {
  static Random randomNumGenerator = null;
  static Treasure[] treasures = new Treasure[3];
  static boolean[] isColorUsed = new boolean[4];
  static boolean[] isLocationUsed = new boolean[4];
  static boolean[] isDestinationUsed = new boolean[4];
  final static int MONSTER_ROCK = 0;
  final static int SKELETON_FALLS = 1;
  final static int WATERFALLS_OF_DOOM = 2;
  final static int SERPENTS_PASS = 3;

  public static void setup() {
    Image background = ImageLoader.getImage(SouthJrHigh.class,
        "/eecs/images/treasureMap.jpg");
    sim.setBackgroundImage(background);
    createRobot(700, 535, 270);
    setPower(10);
    printLine("South Junior High and Engineering Technology Rock!");
  }

  public static void setup2() {
    Image background = ImageLoader.getImage(SouthJrHigh.class,
        "/eecs/images/treasureMap.jpg");
    sim.setBackgroundImage(background);
    createRobot(350, 275, 0);
    drawTrails(true);
    setPower(10);
    printLine("South Junior High and Engineering Technology Rock!");
  }

  public static void setTeamNumber(int number) {
    randomNumGenerator = new Random(number);
    initializeTreasure();
    showMessage("Go to " + treasures[0].getLocation() + " to find the " + Treasure.translateKeyColor(treasures[0].getKeyColor()) + " key.");
  }

  public static void initializeTreasure() {
    if (randomNumGenerator == null) {
      showMessage("Must set team number!");
      return;
    }
    for (int i = 0, size = isColorUsed.length; i < size; ++i) {
      isColorUsed[i] = false;
    }
    for (int i = 0, size = isLocationUsed.length; i < size; ++i) {
      isLocationUsed[i] = false;
    }
    for (int i = 0, size = isDestinationUsed.length; i < size; ++i) {
      isDestinationUsed[i] = false;
    }
    int randomLocationNum = randomNumGenerator.nextInt(isLocationUsed.length);
    for (int i = 0, size = treasures.length; i < size; ++i) {
      int randomColorNum = randomNumGenerator.nextInt(isColorUsed.length);
      int randomDestinationNum = randomNumGenerator.nextInt(isDestinationUsed.length);
      while (randomLocationNum == randomDestinationNum || isColorUsed[randomColorNum] || isLocationUsed[randomLocationNum]
          || isDestinationUsed[randomDestinationNum]) {
        randomColorNum = randomNumGenerator.nextInt(isColorUsed.length);
        randomDestinationNum = randomNumGenerator.nextInt(isDestinationUsed.length);
      }
      isColorUsed[randomColorNum] = true;
      isLocationUsed[randomLocationNum] = true;
      isDestinationUsed[randomDestinationNum] = true;
      Treasure treasure = new Treasure();
      treasure.setLocation(translateLocation(randomLocationNum));
      treasure.setKeyColor(randomColorNum);
      treasure.setDestination(translateLocation(randomDestinationNum));
      treasures[i] = treasure;
      randomLocationNum = randomDestinationNum;
    }
  }

  protected static String translateLocation(int location) {
    switch (location) {
      case MONSTER_ROCK:
        return "Monster Rock";
      case SKELETON_FALLS:
        return "Skeleton Jungle";
      case WATERFALLS_OF_DOOM:
        return "Waterfalls of Doom";
      case SERPENTS_PASS:
        return "Serpent Pass";
    }
    return "Error";
  }

  public static void check() {
    if (checkIfLocationIsCorrect(MONSTER_ROCK, 314, 403, 450, 540))
      return;
    if (checkIfLocationIsCorrect(SKELETON_FALLS, 107, 171, 315, 425))
      return;
    if (checkIfLocationIsCorrect(WATERFALLS_OF_DOOM, 215, 275, 101, 213))
      return;
    if (checkIfLocationIsCorrect(SERPENTS_PASS, 509, 618, 139, 205))
      return;
    if (checkXLocationIsCorrect())
      return;
    if (allKeysAreFound()) {
      showMessage("You have found all of the keys.  Go to the Treasure Chest at the 'X'.");
    }
    else {
      showMessage("There is no key here.");
    }
  }

  protected static boolean allKeysAreFound() {
    boolean allFound = true;
    for (int i = 0, size = treasures.length; i < size; ++i) {
      Treasure treasure = treasures[i];
      allFound = allFound && treasure.isFound();
    }
    return allFound;
  }

  protected static boolean checkIfLocationIsCorrect(int location, int x1, int x2, int y1, int y2) {
    Point2D coordinate = robot.getLocation();
    if (contains(x1, x2, y1, y2, coordinate)) {
      for (int i = 0, size = treasures.length; i < size; ++i) {
        Treasure treasure = treasures[i];
        if (treasure.getLocation().equals(translateLocation(location))) {
          treasures[i].setFound(true);
          showMessage("Great!  You have found the " + Treasure.translateKeyColor(treasures[i].getKeyColor()) + " key!\n" + "Go to "
              + treasures[i].getDestination() + " to find the next key.");
          return true;
        }
      }
    }
    return false;
  }

  protected static boolean checkXLocationIsCorrect() {
    Point2D coordinate = robot.getLocation();
    if (contains(429, 474, 273, 317, coordinate)) {
      if (allKeysAreFound()) {
        showMessage("Congratulations!  You have found all of the keys to the treasure!");
        return true;
      }
      showMessage("The treasure chest won't open!  Perhaps you do not have all of the keys?");
      return true;
    }
    return false;
  }

  public static double getMyX() {
    return robot.getLocation().getX();
  }

  public static double getMyY() {
    return robot.getLocation().getY();
  }

  public static boolean contains(int x1, int x2, int y1, int y2, Point2D coordinate) {
    return (coordinate.getX() > x1 - 25 && coordinate.getX() < x2 + 25 && coordinate.getY() > y1 - 25 && coordinate.getY() < y2 + 25);
  }

  public static void right(int angle) {
    pivotRight(angle);
  }

  public static void left(int angle) {
    pivotLeft(angle);
  }

  public static void go(int ms) {
    goForward(ms);
  }
}