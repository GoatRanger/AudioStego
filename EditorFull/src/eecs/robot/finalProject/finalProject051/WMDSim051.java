package eecs.robot.finalProject.finalProject051;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import eecs.GuiBase;
import eecs.robot.instructor.cobb.ObstacleElement;
import eecs.robot.jago.element.*;
import eecs.robot.simulation.MultiSegmentLine;

public class WMDSim051 extends eecs.robot.jago.SimBox {
  private static final int OBSTACLE_APPROACH_LENGTH = 100;
  private static final int WATER_LENGTH = 200;
  static int obstacle1YLocation = 350;
  final int obstacleHeight = 50;
  static int obstacleSpacing = OBSTACLE_APPROACH_LENGTH;
  static boolean isMetalWMD;
  static boolean isWeightWMD;
  static boolean isRadiationWMD;
  final int byPassWidth = 100;
  static int satelliteX;
  static int satelliteY;
  static int[] randomObstacleType = new int[4];
  static int curveFactor = 125;
  int numberOfSectors = 0;
  static int rightSideObstacleBegin = 0;
  static Sector[] simSectors = new Sector[4];
  static final int WEAPON_START_Y = 150;
  static int[] weaponStartY = { 175, 150, 450, 450 };
  static int[] weaponStartX = { 0, 455, 0, 455 };
  static int sectorWidth = 0;// based on scenario
  static int[] teleportStartY = { 25, 0, 300, 300 };
  static int[] teleportStartX = { 0, 455, 0, 455 };
  static int[] obstacleYLocation = new int[4];
  static int[] sectorXStart = new int[4];
  static int objectScalingFactor = 100;
  static boolean[] actualSolution = new boolean[4];
  static Message[] sectorNames = new Message[4];
  static GeneralPath[] obstacleApproachArea = new GeneralPath[4];
  static MultiSegmentLine[] obstacleApproach = new MultiSegmentLine[4];
  static Sector currentSector;
  static int sectorID;
  static int bypassFactor = 0;
  GeneralPath bridge = new GeneralPath();
  GeneralPath waterA = new GeneralPath();
  GeneralPath waterB = new GeneralPath();
  GeneralPath road1 = new GeneralPath();
  GeneralPath bridgeCenterLine = new GeneralPath();
  GeneralPath[] obstaclePL = new GeneralPath[3];
  int serverYLocation = 0;
  //AntennaElement wirelessAntenna = new AntennaElement();
  int[] byPassXLocation = new int[4];
  int fireXBypassLocation, fireYLocation;
  boolean[] byPassedObstacles = new boolean[4];
  static int numOfObstacles;

  public WMDSim051() {
    super();
    setBackgroundImage("/eecs/images/background4.gif");
    byPassedObstacles[0] = false;
    byPassedObstacles[1] = false;
    byPassedObstacles[2] = false;
    setRandomObstacleArray();
  }

  public int getSectorID() {
    return sectorID;
  }

  public void setCurrentSectorID(int sID) {
    sectorID = sID;
    setCurrentSector(simSectors[sectorID]);
  }

  public void createScenario(int scenario, int numOfSectors, boolean isRandom) {
    double random = 0.0;
    this.numberOfSectors = numOfSectors;
    createSectorLabels();
    createArena();
    for (int i = 0; i < numberOfSectors; i++) {
      simSectors[i] = new Sector();
    }
    this.numberOfSectors = numOfSectors;
    createSectorLabels();
    createArena();
    for (int i = 0; i < numberOfSectors; i++) {
      simSectors[i] = new Sector();
    }
    switch (scenario) {
      case 1:
        // single fixed fire
        initializeSingleSectorVariables();
        createTeleporter(0);
        createWeapon(0);
        createFireObstacle(0);
        break;
      case 2:
        // Single fixed wire
        initializeSingleSectorVariables();
        createTeleporter(0);
        createWeapon(0);
        createWireObstacle(0);
        break;
      case 3:
        // Single fixed water
        initializeSingleSectorVariables();
        createTeleporter(0);
        createWeapon(0);
        obstacleYLocation[0] = 325;
        createWaterObstacle(0);
        break;
      case 4: //Single Fixed Wall Obstacle
        initializeSingleSectorVariables();
        createTeleporter(0);
        createWeapon(0);
        createWallObstacle(0);
        break;
      case 5: //Single Random obstacle
        initializeSingleSectorVariables();
        for (int i = 0; i < 1; i++) {
          createTeleporter(i);
          createWeapon(i);
        }
        random = Math.random();
        if (random < .3) {
          createFireObstacle(0);
        }
        else
          if (random < .8) {
            createWaterObstacle(0);
          }
          else {
            createWireObstacle(0);
          }
        break;
      case 6://Fixed Double
        initializeTwoSectorVariables();
        for (int i = 0; i < 2; i++) {
          createTeleporter(i);
          createWeapon(i);
          //createWireObstacle(i);
        }
        createFireObstacle(0);
        // simSectors[0].setMyObstacleType("Fire");
        createWireObstacle(1);
        // simSectors[1].setMyObstacleType("Wire");
        break;
      case 7:// random 2 obstacle
        initializeTwoSectorVariables();
        for (int i = 0; i < 2; i++) {
          createTeleporter(i);
          createWeapon(i);
        }
        random = Math.random();
        if (random < .30) {
          createFireObstacle(0);
          createWaterObstacle(1);
        }
        else
          if (random < .70) {
            createWaterObstacle(1);
            createWireObstacle(0);
          }
          else {
            createWireObstacle(0);
            createFireObstacle(1);
          }
        break;
      case 8: //Fixed Triple
        intializeThreeSectorVariables();
        for (int i = 0; i < 3; i++) {
          createTeleporter(i);
          createWeapon(i);
          //createWireObstacle(i);
        }
        createFireObstacle(0);
        createWireObstacle(2);
        createWaterObstacle(1);
        break;
      case 9://Random Triple
        intializeThreeSectorVariables();
        for (int i = 0; i < 3; i++) {
          createTeleporter(i);
          createWeapon(i);
          //createWireObstacle(i);
        }
        random = Math.random();
        if (random < .5) {
          createFireObstacle(0);
          createWireObstacle(2);
          createWaterObstacle(1);
        }
        else {
          createFireObstacle(2);
          createWireObstacle(0);
          createWaterObstacle(1);
        }
        break;
    }
    setCurrentSector(simSectors[0]);
    setCurrentSectorID(0);
    setSolution();
    //  System.out.println("Actual solution " + actualSolution[0]);
    // simSectors[0].myWeapon.displayWeaponsCharacteristics();
    //  createObstacles();
    //  createPolarPoints();
  }

  private void setSolution() {
    for (int i = 0; i < numberOfSectors; i++) {
      actualSolution[i] = simSectors[i].myWeapon.isWMD();
    }
  }

  public boolean[] getSolution() {
    return actualSolution;
  }

  private void createSectorLabels() {
    if (numberOfSectors < 3) {
      sectorNames[0] = new Message("NEW YORK");
      sectorNames[1] = new Message("VIRGINIA");
      sectorNames[2] = new Message("FLORIDA");
    }
    else {
      sectorNames[0] = new Message("NEW YORK");
      sectorNames[2] = new Message("VIRGINIA");
      sectorNames[1] = new Message("FLORIDA");
    }
  }

  private void initializeSingleSectorVariables() {
    //	weaponStartX[0]=0;
    //weaponStartY[0]=150;
    //	teleportStartX[0]=0;
    //	teleportStartY[0]=0;
    sectorWidth = 900;
    obstacleYLocation[0] = 300;
    // sectorXStart[0]=0;
    //xEnd[0]=950;
    bypassFactor = 10;
  }

  private void initializeTwoSectorVariables() {
    sectorWidth = 450;
    obstacleYLocation[0] = 350;
    obstacleYLocation[1] = 300;
    bypassFactor = 5;
    sectorXStart[0] = 0;
    sectorXStart[1] = 455;
  }

  private void intializeThreeSectorVariables() {
    sectorXStart[0] = 0;
    sectorXStart[1] = 455;
    sectorXStart[2] = 0;
    sectorWidth = 450;
    bypassFactor = 4;
  }

  private void createWeapon(int sector) {
    int weaponXLoc = 25 + (int) Math.round(Math.random() * (sectorWidth - 100)) + weaponStartX[sector];
    int weaponYLoc = (int) Math.round(Math.random() * (objectScalingFactor - 25)) + weaponStartY[sector];
    simSectors[sector].createMyPotentialWeapon(weaponXLoc + 15, weaponYLoc + 15);
    add(simSectors[sector].myWeapon, weaponXLoc, weaponYLoc);
  }

  private void createTeleporter(int sector) {
    int teleportationXLoc = 25 + (int) Math.round(Math.random() * (sectorWidth - 125)) + teleportStartX[sector];
    int teleportationYLoc = (int) Math.round(Math.random() * objectScalingFactor) + teleportStartY[sector];
    simSectors[sector].createMyTeleportationCenter(teleportationXLoc + 15, teleportationYLoc + 15);
    add(simSectors[sector].myCenter, teleportationXLoc, teleportationYLoc);
  }

  public void createArena() {
    //Arena....
    switch (numberOfSectors) {
      case 1:
        add(sectorNames[0], 5, 585);
        break;
      case 2:
        Wall wall5 = new Wall(5, 600);
        add(wall5, 450, 0);
        add(sectorNames[0], 5, 585);
        add(sectorNames[1], 455, 585);
        break;
      case 3:
        Wall wall3 = new Wall(450, 5);
        add(wall3, 0, 300);
        Wall wall6 = new Wall(5, 600);
        add(wall6, 450, 0);
        teleportStartY[0] = 315;
        teleportStartY[1] = 15;
        teleportStartY[2] = 15;
        weaponStartY[0] = 365;
        weaponStartY[1] = 85;
        weaponStartY[2] = 85;
        obstacleYLocation[0] = 480;
        obstacleYLocation[1] = 300;
        obstacleYLocation[2] = 180;
        bypassFactor = 5;
        objectScalingFactor = 10;
        add(sectorNames[0], 5, 585);
        add(sectorNames[1], 5, 285);
        add(sectorNames[2], 455, 585);
        break;
    }
    createBorderWalls();
  }

  private void createBorderWalls() {
    Wall wall2 = new Wall(1, 600);
    add(wall2, 899, 0);
    Wall wall3 = new Wall(900, 1);
    add(wall3, 0, 0);
    //Wall wall4 = new Wall(900, 1);
    //add(wall4, 0, 600);
    Wall wall5 = new Wall(1, 600);
    add(wall5, 0, 0);
  }

  public void createFireObstacle(int sector) {
    Color fillColor;
    //int currentY;
    int rightObstacleBegin;
    ObstacleElement sectorObstacle = new ObstacleElement();
    byPassXLocation[sector] = (50 * ((int) Math.round(Math.random() * bypassFactor + 1)));
    sectorObstacle = new ObstacleElement();
    rightObstacleBegin = byPassXLocation[sector] + sectorXStart[sector] + byPassWidth;
    //The next two lines create the width of the fire line. While the add lines a couple lines below, place obstacles.
    sectorObstacle.setLeftObstacle(new Fire(byPassXLocation[sector], 10));
    sectorObstacle.setRightObstacle(new Fire(sectorWidth + sectorXStart[sector] - rightObstacleBegin, 10));
    fillColor = Color.RED;
    setFireXByPassLocation(byPassXLocation[sector] + sectorXStart[sector] + byPassWidth / 2);
    // System.out.println("Sector " + sector + " fire bypass center is at " + byPassXLocation[sector]);
    setFireYLocation(obstacleYLocation[sector]);
    add(sectorObstacle.getLeftObstacle(), sectorXStart[sector], obstacleYLocation[sector]);
    add(sectorObstacle.getRightObstacle(), rightObstacleBegin, obstacleYLocation[sector]);
    simSectors[sector].myObstacle = sectorObstacle;
    simSectors[sector].myObstacleType = "fire";
    //System.out.println("creating fire approach area" + sector);
    createApproachArea(sector, fillColor);
  }

  public void createWallObstacle(int sector) {
    Color fillColor;
    //int currentY;
    int rightObstacleBegin;
    ObstacleElement sectorObstacle = new ObstacleElement();
    byPassXLocation[sector] = (50 * ((int) Math.round(Math.random() * bypassFactor + 1)));
    //setFireXByPassLocation(byPassXLocation[sector]);
    sectorObstacle = new ObstacleElement();
    rightObstacleBegin = byPassXLocation[sector] + sectorXStart[sector] + byPassWidth;
    //The next two lines create the width of the fire line. While the add lines a couple lines below, place obstacles.
    sectorObstacle.setLeftObstacle(new Wall(byPassXLocation[sector], 10));
    sectorObstacle.setRightObstacle(new Wall(sectorWidth + sectorXStart[sector] - rightObstacleBegin, 10));
    fillColor = Color.GREEN;
    // setFireXByPassLocation(byPassXLocation[sector] + sectorXStart[sector] + byPassWidth / 2);
    // setFireYLocation(obstacleYLocation[sector]);
    add(sectorObstacle.getLeftObstacle(), sectorXStart[sector], obstacleYLocation[sector]);
    add(sectorObstacle.getRightObstacle(), rightObstacleBegin, obstacleYLocation[sector]);
    simSectors[sector].myObstacle = sectorObstacle;
    simSectors[sector].myObstacleType = "wall";
    // System.out.println("creating approach area" + sector);
    createApproachArea(sector, fillColor);
  }

  public void createWireObstacle(int sector) {
    Color fillColor;
    int concertinaY = 0;
    int concertinaX = 0;
    BrightLight concertinaLight = new BrightLight();
    //int currentY;
    int rightObstacleBegin;
    ObstacleElement sectorObstacle = new ObstacleElement();
    byPassXLocation[sector] = (50 * ((int) Math.round(Math.random() * bypassFactor + 1)));
    sectorObstacle = new ObstacleElement();
    rightObstacleBegin = byPassXLocation[sector] + sectorXStart[sector] + byPassWidth;
    //The next two lines create the width of the fire line. While the add lines a couple lines below, place obstacles.
    sectorObstacle.setLeftObstacle(new Concertina(byPassXLocation[sector], 10));
    sectorObstacle.setRightObstacle(new Concertina(sectorWidth + sectorXStart[sector] - rightObstacleBegin, 10));
    fillColor = Color.YELLOW;
    // System.out.println("creating wire approach area" + sector);
    /*
     * @TODO add the "invisible" line to detect robots crossing point"
     */
    concertinaLight.setSize(10, 10);
    concertinaX = sectorXStart[sector] + byPassXLocation[sector] + (byPassWidth / 2);
    concertinaY = obstacleYLocation[sector] + 5;
    // System.out.println(currentY+5);add(concertinaLight, concertinaX, concertinaY);
    add(concertinaLight, concertinaX, concertinaY);
    add(sectorObstacle.getLeftObstacle(), sectorXStart[sector], obstacleYLocation[sector]);
    add(sectorObstacle.getRightObstacle(), rightObstacleBegin, obstacleYLocation[sector]);
    simSectors[sector].myObstacle = sectorObstacle;
    simSectors[sector].myObstacleType = "wire";
    //  System.out.println("creating approach area" + sector);
    createApproachArea(sector, fillColor);
  }

  /*
   * public void createWaterObstacle(int sector) { GeneralPath[] obstacleApproachArea = new GeneralPath[3]; GeneralPath
   * waterCrossingSafeZone = new GeneralPath(); ObstacleElement sectorObstacle=new ObstacleElement(); byPassXLocation[sector] = (50 * ((int)
   * Math.round(Math.random() * bypassFactor + 1))); rightSideObstacleBegin=byPassXLocation[sector] + sectorXStart[sector] + byPassWidth;
   * simSectors[sector].myObstacle=sectorObstacle; simSectors[sector].myObstacleType="water"; Color fillColor; sectorObstacle = new
   * ObstacleElement(); rightObstacleBegin = byPassXLocation[sector] + sectorXStart[sector] + byPassWidth; //The next two lines create the
   * width of the fire line. While the add lines a couple lines below, place obstacles. sectorObstacle.setLeftObstacle(new
   * Concertina(byPassXLocation[sector], 10)); sectorObstacle.setRightObstacle(new Concertina(sectorWidth + sectorXStart[sector] -
   * rightObstacleBegin, 10)); fillColor = Color.YELLOW; concertinaLight.setSize(10, 10); concertinaX = sectorXStart[sector] +
   * byPassXLocation[sector] + (byPassWidth / 2); concertinaY = obstacleYLocation[sector] + 5; //
   * System.out.println(currentY+5);add(concertinaLight, concertinaX, concertinaY); add(concertinaLight, concertinaX, concertinaY);
   * add(sectorObstacle.getLeftObstacle(), sectorXStart[sector], obstacleYLocation[sector]); add(sectorObstacle.getRightObstacle(),
   * rightObstacleBegin, obstacleYLocation[sector]); simSectors[sector].myObstacle = sectorObstacle; simSectors[sector].myObstacleType =
   * "fire"; System.out.println("creating approach area" + sector); createApproachArea(sector, fillColor); }
   */
  public void createWaterObstacle(int sector) {
    //GeneralPath[] obstacleApproachArea = new GeneralPath[3];
    ObstacleElement sectorObstacle = new ObstacleElement();
    //GeneralPath waterCrossingSafeZone = new GeneralPath();
    byPassXLocation[sector] = (50 * ((int) Math.round(Math.random() * bypassFactor + 1)));
    rightSideObstacleBegin = byPassXLocation[sector] + sectorXStart[sector] + byPassWidth;
    //Color fillColor;
    //ObstacleElement[] obstacleArray = new ObstacleElement[2];
    //int[] byPassX1Location=new int[3]; //stores the left side X coord of the
    // bypass
    //currentY = obstacle1YLocation;
    simSectors[sector].myObstacle = sectorObstacle;
    simSectors[sector].myObstacleType = "water";
    /*
     * road1.reset(); road1.moveTo(70, 80); road1.lineTo(690, 80); road1.lineTo(690, 125);
     */
    //  MultiSegmentLine lineLeftToRight = new MultiSegmentLine(road1, Color.WHITE, false, 40);
    //   add(lineLeftToRight, 0, 0);
    //  add(waterSafeZone, 0, 0);
    //line following obstacle over water
    if (System.getProperty("eecs.instructorWrapper.instructorWrapper.instructorWrapper.testing", "false").equals("true")) {
      String leftOrRight = "left";
      leftOrRight = GuiBase.getString("");
      System.err.println(leftOrRight);
      if (leftOrRight.equalsIgnoreCase("left")) {
        curveLeft(sector);
      }
      else {
        curveRight(sector);
      }
    }
    else
      if (Math.random() > .5) {
        curveLeft(sector);
      }
      else {
        curveRight(sector);
      }
    GeneralPath crossPath = new GeneralPath();
    crossPath.reset();
    crossPath.moveTo(655, 380);
    crossPath.lineTo(655, 430);
    crossPath.moveTo(630, 405);
    crossPath.lineTo(680, 405);
  }

  private void createApproachArea(int sector, Color fillColor) {
    obstacleApproachArea[sector] = new GeneralPath();
    obstacleApproachArea[sector].reset();
    //System.out.println(sectorXStart[sector]+ " x start for painting"+obstacleYLocation[sector]);
    obstacleApproachArea[sector].moveTo(sectorXStart[sector] + 1, obstacleYLocation[sector] + 11);
    obstacleApproachArea[sector].lineTo(sectorXStart[sector] + 1, obstacleYLocation[sector] + OBSTACLE_APPROACH_LENGTH);
    obstacleApproachArea[sector].lineTo(sectorWidth + sectorXStart[sector], obstacleYLocation[sector] + OBSTACLE_APPROACH_LENGTH);
    obstacleApproachArea[sector].lineTo(sectorWidth + sectorXStart[sector], obstacleYLocation[sector] + 11);
    obstacleApproachArea[sector].closePath();
    obstacleApproach[sector] = new MultiSegmentLine(obstacleApproachArea[sector], fillColor, true, 1);
    add(obstacleApproach[sector], 0, 0);
    //  System.out.println(obstacleApproach[sector].inASimulation());
  }

  public void setFireYLocation(int currentY) {
    fireYLocation = currentY;
  }

  public int getFireYLocation() {
    return fireYLocation;
  }

  public int getFireXByPassLocation() {
    return fireXBypassLocation;
  }

  public void setFireXByPassLocation(int xlocation) {
    fireXBypassLocation = xlocation;
  }

  public void curveRight(int sector) {
    bridge.reset();
    bridge.moveTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector]);
    bridge.curveTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector], sectorXStart[sector]
        + byPassXLocation[sector] + curveFactor, obstacleYLocation[sector] + WATER_LENGTH / 2, sectorXStart[sector]
        + byPassXLocation[sector], obstacleYLocation[sector] + WATER_LENGTH);
    bridge.lineTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH);
    bridge.curveTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH, rightSideObstacleBegin + curveFactor,
        obstacleYLocation[sector] + WATER_LENGTH / 2, rightSideObstacleBegin, obstacleYLocation[sector]);
    bridge.moveTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector] + WATER_LENGTH);
    bridge.lineTo(sectorXStart[sector] + byPassXLocation[sector], 600);
    bridge.lineTo(rightSideObstacleBegin, 600);
    bridge.lineTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH);
    bridge.closePath();
    /*
     * waterA.reset(); waterA.moveTo(100,50); waterA.lineTo(690,50); waterA.lineTo(700,100); waterA.curveTo(680,100,590,150,715,300);
     * waterA.lineTo(705,350);
     */
    //MultiSegmentLine line2=new MultiSegmentLine(test2,Color.WHITE,false,10);
    MultiSegmentLine bridgePath = new MultiSegmentLine(bridge, Color.WHITE, true, 20);
    bridgeCenterLine.moveTo(sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2, obstacleYLocation[sector] - 10);
    bridgeCenterLine.curveTo(sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2, obstacleYLocation[sector],
        sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2 + curveFactor, obstacleYLocation[sector] + WATER_LENGTH / 2,
        sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2, obstacleYLocation[sector] + WATER_LENGTH);
    bridgeCenterLine.lineTo(sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2, 580);
    // bridgeCenterLine.lineTo(690, 125);
    MultiSegmentLine centerPath = new MultiSegmentLine(bridgeCenterLine, Color.BLACK, false, 20);
    // Water on the Left side
    /*
     * waterRoad.closePath(); waterA.reset(); waterA.moveTo(500, 125); waterA.lineTo(500, 350); waterA.lineTo(620, 350); waterA.curveTo(620,
     * 350, 465, 175, 620, 125); waterA.lineTo(500, 125);
     */
    waterA.moveTo(sectorXStart[sector], obstacleYLocation[sector]);
    waterA.lineTo(sectorXStart[sector], obstacleYLocation[sector] + WATER_LENGTH);
    waterA.lineTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector] + WATER_LENGTH);
    waterA.curveTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector] + WATER_LENGTH, sectorXStart[sector]
        + byPassXLocation[sector] + curveFactor, obstacleYLocation[sector] + WATER_LENGTH / 2, sectorXStart[sector]
        + byPassXLocation[sector], obstacleYLocation[sector]);
    waterA.closePath();
    //   Water on the right side
    waterB.reset();
    waterB.moveTo(900, obstacleYLocation[sector]);
    waterB.lineTo(900, obstacleYLocation[sector] + WATER_LENGTH);
    waterB.lineTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH);
    waterB.curveTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH, rightSideObstacleBegin + curveFactor,
        obstacleYLocation[sector] + WATER_LENGTH / 2, rightSideObstacleBegin, obstacleYLocation[sector]);
    waterB.closePath();
    WaterElement waterLeft = new WaterElement(waterA, Color.BLUE, true, 2);
    WaterElement waterRight = new WaterElement(waterB, Color.BLUE, true, 2);
    add(bridgePath, 0, 0);
    //  add(path,0,0);
    add(waterLeft, 0, 0);
    add(waterRight, 0, 0);
    add(centerPath, 0, 0);
  }

  public void curveLeft(int sector) {
    bridge.reset();
    bridge.moveTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector]);
    bridge.curveTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector], sectorXStart[sector]
        + byPassXLocation[sector] - curveFactor, obstacleYLocation[sector] + WATER_LENGTH / 2, sectorXStart[sector]
        + byPassXLocation[sector], obstacleYLocation[sector] + WATER_LENGTH);
    bridge.lineTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH);
    bridge.curveTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH, rightSideObstacleBegin - curveFactor,
        obstacleYLocation[sector] + WATER_LENGTH / 2, rightSideObstacleBegin, obstacleYLocation[sector]);
    bridge.moveTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector] + WATER_LENGTH);
    bridge.lineTo(sectorXStart[sector] + byPassXLocation[sector], 600);
    bridge.lineTo(rightSideObstacleBegin, 600);
    bridge.lineTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH);
    bridge.closePath();
    /*
     * waterA.reset(); waterA.moveTo(100,50); waterA.lineTo(690,50); waterA.lineTo(700,100); waterA.curveTo(680,100,590,150,715,300);
     * waterA.lineTo(705,350);
     */
    //MultiSegmentLine line2=new MultiSegmentLine(test2,Color.WHITE,false,10);
    MultiSegmentLine bridgePath = new MultiSegmentLine(bridge, Color.WHITE, true, 20);
    bridgeCenterLine.moveTo(sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2, obstacleYLocation[sector] - 10);
    bridgeCenterLine.curveTo(sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2, obstacleYLocation[sector],
        sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2 - curveFactor, obstacleYLocation[sector] + WATER_LENGTH / 2,
        sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2, obstacleYLocation[sector] + WATER_LENGTH);
    bridgeCenterLine.lineTo(sectorXStart[sector] + byPassXLocation[sector] + byPassWidth / 2, 580);
    // bridgeCenterLine.lineTo(690, 125);
    MultiSegmentLine centerPath = new MultiSegmentLine(bridgeCenterLine, Color.BLACK, false, 20);
    // Water on the Left side
    /*
     * waterRoad.closePath(); waterA.reset(); waterA.moveTo(500, 125); waterA.lineTo(500, 350); waterA.lineTo(620, 350); waterA.curveTo(620,
     * 350, 465, 175, 620, 125); waterA.lineTo(500, 125);
     */
    waterA.moveTo(sectorXStart[sector], obstacleYLocation[sector]);
    waterA.lineTo(sectorXStart[sector], obstacleYLocation[sector] + WATER_LENGTH);
    waterA.lineTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector] + WATER_LENGTH);
    waterA.curveTo(sectorXStart[sector] + byPassXLocation[sector], obstacleYLocation[sector] + WATER_LENGTH, sectorXStart[sector]
        + byPassXLocation[sector] - curveFactor, obstacleYLocation[sector] + WATER_LENGTH / 2, sectorXStart[sector]
        + byPassXLocation[sector], obstacleYLocation[sector]);
    waterA.closePath();
    //   Water on the right side
    waterB.reset();
    waterB.moveTo(900, obstacleYLocation[sector]);
    waterB.lineTo(900, obstacleYLocation[sector] + WATER_LENGTH);
    waterB.lineTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH);
    waterB.curveTo(rightSideObstacleBegin, obstacleYLocation[sector] + WATER_LENGTH, rightSideObstacleBegin - curveFactor,
        obstacleYLocation[sector] + WATER_LENGTH / 2, rightSideObstacleBegin, obstacleYLocation[sector]);
    waterB.closePath();
    WaterElement waterLeft = new WaterElement(waterA, Color.BLUE, true, 2);
    WaterElement waterRight = new WaterElement(waterB, Color.BLUE, true, 2);
    add(bridgePath, 0, 0);
    //  add(path,0,0);
    add(waterLeft, 0, 0);
    add(waterRight, 0, 0);
    add(centerPath, 0, 0);
  }

  public void setRandomObstacleArray() {
    /*
     * int randomNumber; randomNumber = (int) Math.round(Math.random() * 100); if (randomNumber <= 50) { randomObstacleType[1] = 1;//1 is
     * concertina type randomObstacleType[2] = 2; } else { randomObstacleType[1] = 2; // 2 is fire randomObstacleType[2] = 1; }
     */
  }

  public void addSquare(int xCoord, int yCoord, int length) {
    int[] squareX;
    int[] squareY;
    squareX = new int[4];
    squareY = new int[4];
    squareX[0] = xCoord - length;
    squareY[0] = yCoord - length;
    squareX[1] = xCoord - length;
    squareY[1] = yCoord + length;
    squareX[2] = xCoord + length;
    squareY[2] = yCoord + length;
    squareX[3] = xCoord + length;
    squareY[3] = yCoord - length;
    //WidePolygon polygonA = new WidePolygon(squareX, squareY, 4, lineWidth,
    //   WidePolygon.BLUE, false, false, shapeWidth, WidePolygon.BLACK, redIndex);
    //   add(polygonA, xCoord, yCoord);
  }

  /**
   * This method is used to determine if the Xloc,yloc is within a square area of the xbase, ybase The square are is defined by width.
   */
  public boolean arePointsClose(int xbase, int ybase, int xLoc, int yLoc, int width) {
    //printLine("width="+width+"distance= "+Math.sqrt(Math.pow(xbase - xLoc, 2.0) + Math.pow(ybase - yLoc, 2.0)));
    if (Math.sqrt(Math.pow(xbase - xLoc, 2.0) + Math.pow(ybase - yLoc, 2.0)) < width) {
      //printLine("robot is close enough");
      return true;
    }
    return false;
  }

  /**
   * @return Returns the currentSector.
   */
  public Sector getCurrentSector() {
    return currentSector;
  }

  /**
   * @param currentSector
   *          The currentSector to set.
   */
  public void setCurrentSector(Sector currentSector) {
    WMDSim051.currentSector = currentSector;
  }

  /**
   * @return Returns the simSectors.
   */
  public static Sector[] getSimSectors() {
    return simSectors;
  }
}