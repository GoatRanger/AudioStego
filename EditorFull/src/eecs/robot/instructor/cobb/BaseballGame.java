package eecs.robot.instructor.cobb;

import eecs.robot.jago.ImageLoader;
import eecs.robot.jago.SimBox;
import eecs.robot.jago.element.Message;
import java.awt.Image;

public class BaseballGame extends eecs.RobotGuiBase {
  static BaseballTeam homeTeam, visitingTeam, hittingTeam, PitchingTeam;
  static int numberOfOuts = 0;
  static int currentInning = 8;
  static boolean homeTeamOffense, endOfAtBat;
  static boolean isRunnerOnFirst = false, isRunnerOnSecond = false, isRunnerOnThird = false;
  static String msg, atBatSummaryMSG;
  static LED firstBaseLED, secondBaseLED, thirdBaseLED;
  static  String[] testPurpose=new String[25];
  static int[] homeTeamScore=new int[25];
  static int[] visitorTeamScore=new int[25];
  static int[] testOutData=new int[25];
  static String[] testOffPlayResult=new String[25];
  static String[] testDefPlayResult=new String[25];
  static {
    sim = new SimBox();
    System.out.println("xxxx..");
    sim.setSimTitle("Baseball 2005-3");
    Image bg = ImageLoader.getImage(Baseball.class, "/eecs/images/Baseball.jpg");
    sim.setBackgroundImage(bg);
    firstBaseLED = new LED(20);
    secondBaseLED = new LED(20);
    thirdBaseLED = new LED(20);
    frame = sim;
    sim.setVisible(true);
    //driveSummaryMSG="";
    Baseball.playGame();
    homeTeam = Baseball.getHomeTeam();
    homeTeam.setName(Baseball.names[1]);
    visitingTeam = Baseball.getVisitingTeam();
    visitingTeam.setName(Baseball.names[0]);
    homeTeam.setMyOpponent(visitingTeam);
    visitingTeam.setMyOpponent(homeTeam);
    //homeTeam.checkForTesting();
    //homeTeam.isTestingOn=true;
    int test=chooseButton("Do you want to run tests?","Yes","no");
    if (test==0) {
      setUpDisplay();
      runTestModule();
    }
    else {
      setUpDisplay();
      startGame();
    }
  }
  private static Message homeTeamMSG, visitingTeamMSG, outMSG, inningMSG, possessionMSG;
  private static Message homeScoreMSG, visitScoreMSG, actualOutMSG, actualInningMSG;

  public static boolean areRunnersInScoringPosition() {
  	if (!runningTests){
        return (isRunnerOnSecond || isRunnerOnThird);
  	} 
    else {
    	return (getInt("number")==1);
      }
    }
    	
  
  public static void loadTestPurposes(){
  	 testOutData[0]=0;
     homeTeamScore[0] =  0;
     visitorTeamScore[0] = 0;
     testOffPlayResult[0]="Normal";
     testDefPlayResult[0]="Slow";
     
     testOutData[1]=0;
     homeTeamScore[1] =  1;
     visitorTeamScore[1] = 0;
     testOffPlayResult[1]="Full";
     testDefPlayResult[1]="Slow";
     
     testOutData[2]=0;
     homeTeamScore[2] =  3;
     visitorTeamScore[2] = 0;
     testOffPlayResult[2]="Bunt";
     testDefPlayResult[2]="Slow";
          
     testOutData[3]=1;
     homeTeamScore[3] =  2;
     visitorTeamScore[3] = 0;
     testOffPlayResult[3]="Normal";
     testDefPlayResult[3]="Sinker";
     
     testOutData[4]=2;
     homeTeamScore[4] =  0;
     visitorTeamScore[4] = 0;
     testOffPlayResult[4]="Bunt";
     testDefPlayResult[4]="Sinker";
     
     testOutData[5]=0;
     homeTeamScore[5] =  0;
     visitorTeamScore[5] = 1;
     testOffPlayResult[5]="Normal";
     testDefPlayResult[5]="Sinker";
     
     testOutData[6]=1;
     homeTeamScore[6] =  0;
     visitorTeamScore[6] = 2;
     testOffPlayResult[6]="Full";
     testDefPlayResult[6]="Slow";
     
     testOutData[7]=1;
     homeTeamScore[7] =  0;
     visitorTeamScore[7] = 3;
     testOffPlayResult[7]="Full";
     testDefPlayResult[7]="Knuckle";
     
     testOutData[8]=2;
     homeTeamScore[8] =  0;
     visitorTeamScore[8] = 1;
     testOffPlayResult[8]="Full";
     testDefPlayResult[8]="Knuckle";
     
     testOutData[8]=2;
     homeTeamScore[8] =  0;
     visitorTeamScore[8] = 2;
     testOffPlayResult[8]="Normal";
     testDefPlayResult[8]="Knuckle";
     
    for (int i=0;i<9;i++){
         testPurpose[i]="Outs="+testOutData[i]+", homeScore="+homeTeamScore[i]+", visitorTeamScore="+visitorTeamScore[i];
    }
  }
  public static void runTestModule() {
    printLine("TESTING **********");
    hittingTeam = homeTeam;
    PitchingTeam = visitingTeam;
     
    loadTestPurposes();
    isRunnerOnFirst = false;
    isRunnerOnSecond = false;
    isRunnerOnThird = false;
    //String[] testOffPlayResult = { "Normal", "Take", "Take", "Power", "Power", "Power" };
    //String[] testDefPlayResult = { "Change", "Fast", "Curve", "Curve", "Change", "curve" };
    int numberOfTests = 9;
    int numberCorrect = 0;
    for (int i = 0; i < numberOfTests; i++) {
      //(testDownData[i],testYTGData[i]);
      //setDown(testOutData[i]);
      // setYardsToGo(testYTGData[i]);
      printLine("Test Case Number "+(i+1)+": "+testPurpose[i]);
      printLine("-------------");
      numberOfOuts = testOutData[i];
      homeTeam.score = homeTeamScore[i];
      visitingTeam.score = visitorTeamScore[i];
      hittingTeam.BattingStrategy();
      hittingTeam.pitchingStrategy(); //testing the same team
      String currentOffPlayResult = hittingTeam.getHittingSelection();
      String currentDefPlayResult = hittingTeam.getPitchingSelection();// test the same team..
      if (currentOffPlayResult == null) {
        currentOffPlayResult = "Power2";
        printLine("offensive play not set");
      }
      if (currentDefPlayResult == null) {
        currentDefPlayResult = "Fast2";
        printLine("Defensive play not set");
      }
      if (currentOffPlayResult.equalsIgnoreCase(testOffPlayResult[i])) {
        numberCorrect++;
        printLine("Hitting Test " + (i + 1) + " successful");
      }
      else {
        printLine("Hitting Test " + (i + 1) + " unsucessful.  Expected " + testOffPlayResult[i] + " but saw " + currentOffPlayResult);
      }
      if (currentDefPlayResult.equalsIgnoreCase(testDefPlayResult[i])) {
        numberCorrect++;
        printLine("Pitching Test " + (i + 1) + " successful");
      }
      else {
        printLine("Pitching Test " + (i + 1) + " unsucessful.  Expected " + testDefPlayResult[i] + " but saw " + currentDefPlayResult);
      }
      printLine();
    }
    printLine(numberCorrect + " Correct, out of " + (numberOfTests * 2) + ".");
  }

  public void dummy() {
    //
  }

  public static void setUpDisplay() {
    secondBaseLED.setColor(RED);
    firstBaseLED.setColor(RED);
    thirdBaseLED.setColor(RED);
    homeTeamMSG = new Message(Baseball.names[1]);
    sim.add(homeTeamMSG, 0, 15);
    visitingTeamMSG = new Message(Baseball.names[0]);
    sim.add(visitingTeamMSG, 475, 15);
    outMSG = new Message("OUTS");
    inningMSG = new Message("Inning");
    actualInningMSG = new Message("9");
    actualOutMSG = new Message("0");
    possessionMSG = new Message("*");
    sim.add(outMSG, 200, 15);
    sim.add(inningMSG, 300, 15);
    //sim.add(ballOnTheMSG,475, 15);
    //sim.add(visitingTeamMSG,650,15);
    homeScoreMSG = new Message(homeTeam.getScore() + "");
    visitScoreMSG = new Message(visitingTeam.getScore() + "");
    //updateDisplay();
    sim.add(homeScoreMSG, 10, 30);
    sim.add(visitScoreMSG, 500, 30);
    sim.add(actualOutMSG, 225, 30);
    sim.add(actualInningMSG, 325, 30);
  }

  public static void moveRunners(int numberOfBases) {
    if (numberOfBases == 1) {
      if (isRunnerOnThird && isRunnerOnSecond && isRunnerOnFirst) {
        hittingTeam.addToScore(1);
        //bases still loaded;
      }
      else
        if (isRunnerOnSecond && isRunnerOnFirst) {
          isRunnerOnThird = true;
        }
        else
          if (isRunnerOnFirst) {
            isRunnerOnSecond = true;
          }
      isRunnerOnFirst = true;
    }
    else
      if (numberOfBases == 2) {
        if (isRunnerOnThird && isRunnerOnSecond && isRunnerOnFirst) {
          //increment score by 2;
          hittingTeam.addToScore(2);
          isRunnerOnThird = true;
        }
        else
          if (isRunnerOnSecond && isRunnerOnFirst) {
            //increment score by 1;
            hittingTeam.addToScore(1);
            isRunnerOnThird = true;
          }
          else
            if (isRunnerOnFirst) {
              isRunnerOnThird = true;
            }
        isRunnerOnSecond = true;
        isRunnerOnFirst = false;
      }
      else
        if (numberOfBases == 3) {
          if (isRunnerOnThird && isRunnerOnSecond && isRunnerOnFirst) {
            //increment score by 3;
            hittingTeam.addToScore(3);
          }
          else
            if (isRunnerOnSecond && isRunnerOnFirst) {
              //increment score by 2;
              hittingTeam.addToScore(2);
            }
            else
              if (isRunnerOnFirst) {
                //increment score by 1
                hittingTeam.addToScore(1);
              }
          isRunnerOnThird = true;
          isRunnerOnSecond = false;
          isRunnerOnFirst = false;
        }
        else { //homerun
          if (isRunnerOnThird && isRunnerOnSecond && isRunnerOnFirst) {
            //increment score by 4;
            hittingTeam.addToScore(4);
          }
          else
            if (isRunnerOnSecond && isRunnerOnFirst) {
              //increment score by 3;
              hittingTeam.addToScore(3);
            }
            else
              if (isRunnerOnFirst) {
                //increment score by 2
                hittingTeam.addToScore(2);
              }
              else {
                hittingTeam.addToScore(1);
              }
          isRunnerOnFirst = false;
          isRunnerOnSecond = false;
          isRunnerOnThird = false;
        }
  }

  public static void showBaseRunners() {
    if (isRunnerOnFirst) {
      if (!firstBaseLED.inASimulation()) {
        sim.add(firstBaseLED, 420, 420);
      }
    }
    else
      if (firstBaseLED.inASimulation()) {
        sim.remove(firstBaseLED);
      }
    if (isRunnerOnSecond) {
      if (!secondBaseLED.inASimulation()) {
        sim.add(secondBaseLED, 260, 250);
      }
    }
    else
      if (secondBaseLED.inASimulation()) {
        sim.remove(secondBaseLED);
      }
    // Third base processing
    if (isRunnerOnThird) {
      if (!thirdBaseLED.inASimulation()) {
        sim.add(thirdBaseLED, 100, 420);
      }
    }
    else
      if (thirdBaseLED.inASimulation()) {
        sim.remove(thirdBaseLED);
      }
  }

  public static void updateDisplay() {
    if (actualInningMSG.inASimulation()) {
      sim.remove(homeScoreMSG);
      sim.remove(visitScoreMSG);
      sim.remove(actualOutMSG);
      sim.remove(actualInningMSG);
      //initial values....
      try {
        Thread.sleep(200);
      }
      catch (InterruptedException e) {
        //
      }
    }
    homeScoreMSG = new Message(homeTeam.getScore() + "");
    //visitingTeam.addToScore(50);
    visitScoreMSG = new Message(visitingTeam.getScore() + "");
    actualOutMSG = new Message(getNumberOfOuts() + "");
    actualInningMSG = new Message(getCurrentInning() + "");
    sim.add(homeScoreMSG, 10, 30);
    sim.add(visitScoreMSG, 500, 30);
    sim.add(actualOutMSG, 225, 30);
    sim.add(actualInningMSG, 325, 30);
    showBaseRunners();
  }

  public static int getCurrentInning() {
    return currentInning;
  }

  public void setHomeTeam(BaseballTeam team) {
    homeTeam = team;
  }

  public void setVistingTeam(BaseballTeam team) {
    visitingTeam = team;
  }

  public static void startGame() {
    homeTeamOffense = true;
    boolean winner = false;
    while (!winner) {
      currentInning++;
      for (int i = 0; i < 2; i++) {
        //change offense and defense
        sim.remove(possessionMSG);
        possessionMSG = new Message("*");
        changeBattingTeam();
        numberOfOuts = 0;
        updateDisplay();
        newAtBat();
        //Determine if another drive is needed. Both have to have a turn
      }
      if (hittingTeam.score != PitchingTeam.score) {
        winner = true;
        if (homeTeam.getScore() > visitingTeam.getScore())
          showMessage(homeTeam.getName() + " Wins!!!");
        else
          showMessage(visitingTeam.getName() + " Wins!!!");
      }
    }
  }

  /**
   * 
   */
  private static void changeBattingTeam() {
    if (homeTeamOffense) {
      //visiting team hits first
      sim.add(possessionMSG, 695, 30);
      hittingTeam = visitingTeam;
      PitchingTeam = homeTeam;
      homeTeamOffense = false;
    }
    else {
      sim.add(possessionMSG, 30, 30);
      hittingTeam = homeTeam;
      PitchingTeam = visitingTeam;
      homeTeamOffense = true;
    }
  }

  public static void newAtBat() {
    endOfAtBat = false;
    if (robot != null)
      destroyRobot();
    atBatSummaryMSG = hittingTeam.getName() + "\n";
    showMessage(atBatSummaryMSG + " New at Bat Started ");
    atBatSummaryMSG = "";
    while (!endOfAtBat) {
      hittingTeam.BattingStrategy();
      //makes the cadet code execute, to select a new strategy based on the current situation.
      String offPlay = hittingTeam.getHittingSelection();
      PitchingTeam.pitchingStrategy();
      String defPlay = PitchingTeam.getPitchingSelection();
      //offPlay="run";
      //defPlay="blitz";
      printLine("**** NEW BATTER FOR " + hittingTeam.getName() + " ****");
      printLine("Hitter chose " + offPlay);
      printLine("Pitcher chose " + defPlay);
      int batResult = determinePlayResult(offPlay, defPlay);
      processResults(batResult);
      if (numberOfOuts == 3) {
        endOfAtBat = true;
        isRunnerOnFirst = false;
        isRunnerOnSecond = false;
        isRunnerOnThird = false;
        updateDisplay();
      }
    }
  }

  public static void processResults(int hitCode) {
    if (hitCode == 0) {
      //outs +1
      numberOfOuts++;
      if (Math.random() > .5) {
        atBatSummaryMSG = hittingTeam.getName() + " Flied Out";
      }
      else {
        atBatSummaryMSG = hittingTeam.getName() + " Grounded out";
      }
    }
    else
      if (hitCode == 1) {
        atBatSummaryMSG = " Line shot up the middle, Solid Single for " + hittingTeam.getName();
        msg += atBatSummaryMSG;
        moveRunners(1);
      }
      else
        if (hitCode == 2) {
          atBatSummaryMSG = hittingTeam.getName() + "Solid hit down the line, he will is in for a stand-up double";
          //moveRunners(2);
          moveRunners(2);
        }
        else
          if (hitCode == 3) {
            //update score
            atBatSummaryMSG = hittingTeam.getName() + " This could be trouble, in the gap, he is in for a triple ";
            moveRunners(3);
          }
          else
            if (hitCode == 4) {
              atBatSummaryMSG = hittingTeam.getName() + ": Back Back Back Back......HomeRun ";
              moveRunners(4);
            }
            else
              if (hitCode == 5) {
                atBatSummaryMSG = hittingTeam.getName() + " nice bunt...";
                moveRunners(1);
              }
    updateDisplay();
    msg = atBatSummaryMSG;
    printLine(msg);
    try {
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      //
    }
    //showMessage(msg);
  }

  public static int determinePlayResult(String offPlay, String defPlay) {
    int batResult = 0;
    int scenario = 0;
    if (offPlay.equalsIgnoreCase("FULL") && defPlay.equalsIgnoreCase("Knuckle"))
      scenario = 1;
    else
      if (offPlay.equalsIgnoreCase("FULL") && defPlay.equalsIgnoreCase("Sinker"))
        scenario = 2;
      else
        if (offPlay.equalsIgnoreCase("FULL") && defPlay.equalsIgnoreCase("Slow"))
          scenario = 3;
        else
          if (offPlay.equalsIgnoreCase("Normal") && defPlay.equalsIgnoreCase("Knuckle"))
            scenario = 4;
          else
            if (offPlay.equalsIgnoreCase("Normal") && defPlay.equalsIgnoreCase("Sinker"))
              scenario = 5;
            else
              if (offPlay.equalsIgnoreCase("Normal") && defPlay.equalsIgnoreCase("Slow"))
                scenario = 6;
              else
                if (offPlay.equalsIgnoreCase("Bunt") && defPlay.equalsIgnoreCase("Knuckle"))
                  scenario = 7;
                else
                  if (offPlay.equalsIgnoreCase("Bunt") && defPlay.equalsIgnoreCase("Sinker"))
                    scenario = 8;
                  else
                    if (offPlay.equalsIgnoreCase("Bunt") && defPlay.equalsIgnoreCase("Slow"))
                      scenario = 9;
                    else
                      printLine("ERROR>>> You must use one of the plays as described in the Homework (Full, Normal, Bunt) or (Knuckle, Sinker,or Slow");
    int subCaseRandom = (int) Math.round(100.0 * Math.random());
    switch (scenario) {
      case 1:// Power Fast
        if (subCaseRandom <= 20) {
          batResult = getHitResult();
        }
        else
          if (subCaseRandom <= 50) {
            batResult = 0; //out
          }
          else
            batResult = 4;
        break;
      case 2: //Power Curve
        if (subCaseRandom <= 20) {
          batResult = getHitResult();
        }
        else
          if (subCaseRandom <= 80) {
            batResult = 0; //out
          }
          else
            batResult = 4;
        break;
      case 3://Power Change
        if (subCaseRandom <= 30) {
          batResult = getHitResult();
        }
        else
          if (subCaseRandom <= 95) {
            batResult = 0; //out
          }
          else
            batResult = 4;
        break;
      case 4: //Normal Fast
        if (subCaseRandom <= 35) {
          batResult = getHitResult();
        }
        else
          if (subCaseRandom <= 85) {
            batResult = 0; //out
          }
          else
            batResult = 4;
        break;
      case 5:// Normal Curve
        if (subCaseRandom <= 20) {
          batResult = getHitResult();
        }
        else
          if (subCaseRandom <= 90) {
            batResult = 0; //out
          }
          else
            batResult = 4;
        break;
      case 6:// Normal Change
        if (subCaseRandom <= 50) {
          batResult = getHitResult();
        }
        else
          if (subCaseRandom <= 80) {
            batResult = 0; //out
          }
          else
            batResult = 4;
        break;
      case 7:// Take Fast
        if (subCaseRandom <= 25) {
          batResult = 5; //walk
        }
        else {
          batResult = 0; //out
        }
        break;
      case 8: // Take Curve
        if (subCaseRandom <= 45) {
          batResult = 5; //walk
        }
        else {
          batResult = 0; //out
        }
        break;
      case 9: //Take Change
        if (subCaseRandom <= 35) {
          batResult = 5; //walk
        }
        else {
          batResult = 0; //out
        }
        break;
    }
    return batResult;
  }

  public static int getHitResult() {
    double hitRandom = Math.random();
    int hit = 0;
    if (hitRandom > .85) {
      hit = 3;
    }
    else
      if (hitRandom > .50) {
        hit = 2;
      }
      else {
        hit = 1;
      }
    return hit;
  }

  /**
   * @return Returns the numberOfOuts.
   */
  public static int getNumberOfOuts() {
    if (!runningTests){
  	return numberOfOuts;
    }
    else {
    	return getInt("getting outs");
    }
    }
    
    
    

  /**
   * @param numberOfOuts
   *          The numberOfOuts to set.
   */
  public static void setNumberOfOuts(int numberOfOuts) {
    BaseballGame.numberOfOuts = numberOfOuts;
  }

  /**
   * @return Returns the isRunnerOnFirst.
   */
  public static boolean isRunnerOnFirst() {
    return isRunnerOnFirst;
  }

  /**
   * @param isRunnerOnFirst
   *          The isRunnerOnFirst to set.
   */
  public static void setRunnerOnFirst(boolean isRunnerOnFirst) {
    BaseballGame.isRunnerOnFirst = isRunnerOnFirst;
  }

  /**
   * @return Returns the isRunnerOnSecond.
   */
  public static boolean isRunnerOnSecond() {
    return isRunnerOnSecond;
  }

  /**
   * @param isRunnerOnSecond
   *          The isRunnerOnSecond to set.
   */
  public static void setRunnerOnSecond(boolean isRunnerOnSecond) {
    BaseballGame.isRunnerOnSecond = isRunnerOnSecond;
  }

  /**
   * @return Returns the isRunnerOnThird.
   */
  public static boolean isRunnerOnThird() {
    return isRunnerOnThird;
  }

  /**
   * @param isRunnerOnThird
   *          The isRunnerOnThird to set.
   */
  public static void setRunnerOnThird(boolean isRunnerOnThird) {
    BaseballGame.isRunnerOnThird = isRunnerOnThird;
  }
}