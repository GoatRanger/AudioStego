package eecs.robot.finalProject.finalProject052;

public class Sector {
  int SectorID;
  String myObstacleType; //(wall, water, fire, wire)
  Object myObstacle;
  TeleportationCenter myCenter;
  PotentialWeapon myWeapon;

  public Sector() {
    super();
    myObstacle = new Object();
    myCenter = new TeleportationCenter();
    myWeapon = new PotentialWeapon();
  }

  public void createMyTeleportationCenter(int xloc, int yloc) {
    myCenter.setXLocation(xloc);
    myCenter.setYLocation(yloc);
  }

  public void createMyPotentialWeapon(int xloc, int yloc) {
    myWeapon.setXLocation(xloc);
    myWeapon.setYLocation(yloc);
    myWeapon.generateRadiationLevel();
    myWeapon.generateTemperature();
    myWeapon.generateWeight();
  }

  public String getMyObstacleType() {
    return myObstacleType;
  }

  public void setMyObstacleType(String myObstacleType) {
    this.myObstacleType = myObstacleType;
  }

  public TeleportationCenter getMyCenter() {
    return myCenter;
  }

  public Object getMyObstacle() {
    return myObstacle;
  }

  public PotentialWeapon getMyWeapon() {
    return myWeapon;
  }
}