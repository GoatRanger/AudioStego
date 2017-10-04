package eecs.robot.instructor.jackson;

public class Treasure {
  final static int RED = 0;
  final static int GREEN = 1;
  final static int BLUE = 2;
  final static int YELLOW = 3;
  private String location = "";
  private String destination = "";
  private int keyColor = -1;
  private boolean found = false;

  protected static String translateKeyColor(int color) {
    switch (color) {
      case RED:
        return "Red";
      case GREEN:
        return "Green";
      case BLUE:
        return "Blue";
      case YELLOW:
        return "Yellow";
    }
    return "Error";
  }

  public boolean isFound() {
    return this.found;
  }

  public void setFound(boolean found) {
    this.found = found;
  }

  public int getKeyColor() {
    return this.keyColor;
  }

  public void setKeyColor(int keyColor) {
    this.keyColor = keyColor;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDestination() {
    return this.destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("Treasure");
    sb.append("  Location:" + location + "\n");
    sb.append("  Key Color:" + keyColor + "\n");
    sb.append("  Found:" + found + "\n");
    sb.append("  Destination:" + destination + "\n");
    return sb.toString();
  }
}