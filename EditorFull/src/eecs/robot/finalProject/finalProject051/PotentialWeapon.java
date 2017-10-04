package eecs.robot.finalProject.finalProject051;

import eecs.robot.jago.element.SolidElement;

public class PotentialWeapon extends SolidElement {
  int xLocation;
  int yLocation;
  double weight;
  int radiationLevel;
  int metal;
  boolean isMetalWMD = false;
  boolean isRadiationWMD = false;
  boolean isWeightWMD = false;
  boolean isWMD;

  public PotentialWeapon() {
    super();
    createWeaponCharacteristics();
    determineWeaponWMDStatus();
    setAppearance();
  }

  public void setAppearance() {
    if (isWMD)
      setAppearance("/eecs/images/redRadiation.gif");
    else
      setAppearance("/eecs/images/greenRadiation.gif");
  }

  public PotentialWeapon(int location, int location2, double weight, int radiationLevel, int temperature) {
    xLocation = location;
    yLocation = location2;
    this.weight = weight;
    this.radiationLevel = radiationLevel;
    this.metal = temperature;
  }

  public boolean isWMD() {
    return isWMD;
  }

  public void generateRadiationLevel() {
    setRadiationLevel(55);
  }

  private void createWeaponCharacteristics() {
    if (Math.random() > .3)
      isWeightWMD = true;
    if (Math.random() > .3)
      isMetalWMD = true;
    if (Math.random() > .4)
      isRadiationWMD = true;
  }

  public void displayWeaponsCharacteristics() {
    System.out.println("weight: " + isWeightWMD + " Metal: " + isMetalWMD + "Radiation " + isRadiationWMD);
  }

  private void determineWeaponWMDStatus() {
    if ((isWeightWMD && isMetalWMD) || (isWeightWMD && isRadiationWMD) || (isMetalWMD && isRadiationWMD))
      isWMD = true;
  }

  public void generateWeight() {
    setWeight(100);
  }

  public void generateTemperature() {
    setMetal(100);
  }

  public void setWMD(boolean isWMD) {
    this.isWMD = isWMD;
  }

  public int getRadiationLevel() {
    return radiationLevel;
  }

  public void setRadiationLevel(int radiationLevel) {
    this.radiationLevel = radiationLevel;
  }

  public int getMetal() {
    return metal;
  }

  public void setMetal(int temperature) {
    this.metal = temperature;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public int getXLocation() {
    return xLocation;
  }

  public void setXLocation(int location) {
    xLocation = location;
  }

  public int getYLocation() {
    return yLocation;
  }

  public void setYLocation(int location) {
    yLocation = location;
  }
}