/*
 * Created on Dec 21, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.instructor.cobb;

import jago.ImageLoader;
//import jago.element.LegoRobot;
import jago.element.Message;

import java.awt.Image;

/**
 * @author Owner
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class FootballGame105 extends eecs.RobotGuiBase {
		
	static FootballTeam105 homeTeam, visitingTeam,offensiveTeam, defensiveTeam;
	static int currentDown=1;
	static int yardsToGo=10;
	static int lineOfScrimmage=25;
	static boolean homeTeamOffense,endOfDrive;
	static String msg, driveSummaryMSG;
	static {
		sim = new jago.SimBox();
		sim.setSimTitle("FOOTBALL105");
		Image bg = ImageLoader.getImage(Football.class, "/eecs/instructor/cobb/football_field.GIF");
		sim.setBackgroundImage(bg);
		
		frame = sim;
		sim.setVisible(true);
		
		
		driveSummaryMSG="";
		Football105.playGame();
		homeTeam=Football105.getHomeTeam();
		homeTeam.setName(Football105.names[1]);
		visitingTeam=Football105.getVisitingTeam();
		visitingTeam.setName(Football105.names[0]);
		homeTeam.setMyOpponent(visitingTeam);
		visitingTeam.setMyOpponent(homeTeam);
		homeTeam.checkForTesting();
		
		
		if (homeTeam.isTestingOn){
			setUpDisplay();
			runTestModule();
		}
		else 
		{	
		 setUpDisplay();
		
	     startGame();
		} 
	}
	private static Message homeTeamMSG, visitingTeamMSG, DownMSG, yardsToGoMSG, ballOnTheMSG, possessionMSG;
	private static Message homeScoreMSG, visitScoreMSG, actualDownMSG, actualYTGMSG, actualScrimmageMSG;
	public static void runTestModule()
	 {
		printLine("TESTING **********");
		offensiveTeam=homeTeam;
		defensiveTeam=visitingTeam;
		int[] testDownData={1,2,3,4,3,3};
		int[] testYTGData={10,10,10,5,12,2};
		String[] testOffPlayResult={"Pass_Long","PASS_Long","PASS_LONG","FG","Pass_Long","Pass_Short"};
		String[] testDefPlayResult={"BLITZ","BLITZ","Blitz","BLITZ","run","zone"};
		int numberOfTests=testDownData.length;
		int numberCorrect=0;
		for (int i=0;i<numberOfTests;i++)
		{
			//(testDownData[i],testYTGData[i]);
		  setDown(testDownData[i]);
		  setYardsToGo(testYTGData[i]);
		  offensiveTeam.offensiveStrategy();
		  offensiveTeam.defensiveStrategy(); //testing the same team
		   String currentOffPlayResult=offensiveTeam.getOffensivePlay();
		    String currentDefPlayResult=offensiveTeam.getDefensivePlay();// test the same team..
		    if (currentOffPlayResult==null){
		    	currentOffPlayResult="run";
		    	printLine("offensive play not set");
		    }
		    if (currentDefPlayResult==null){
		    	currentDefPlayResult="run";
		    	printLine("offensive play not set");
		    }
		    if (currentOffPlayResult.equalsIgnoreCase(testOffPlayResult[i]))
		    {
		    	numberCorrect++;
		    	printLine("Offensive Test "+(i+1)+" successful");
		    }
		    else
		    {
		     	printLine("Offensive Test "+(i+1)+" unsucessful.  Expected "+testOffPlayResult[i]+" but saw "+currentOffPlayResult);
		    }
		    
		    if (currentDefPlayResult.equalsIgnoreCase(testDefPlayResult[i]))
		    {
		    	numberCorrect++;
		    	printLine("Defensive Test "+(i+1)+" successful");
		    }
		    else
		    {
		    	printLine("Defensive Test "+(i+1)+" unsucessful.  Expected "+testDefPlayResult[i]+" but saw "+currentDefPlayResult);
		    }
		    printLine();
		}
		
	printLine(numberCorrect+" Correct, out of "+(numberOfTests*2)+".");
	}
	private static void setDown(int down)
	{
		currentDown=down;
	}
	
	private static void setYardsToGo(int ytg)
	{
		yardsToGo=ytg;
	}
	public static void setUpDisplay(){
		homeTeamMSG=new Message(Football105.names[1]);
		sim.add(homeTeamMSG,0,15);
		visitingTeamMSG=new Message(Football105.names[0]);
		//sim.add(visitingTeamMSG, 800,15);
		DownMSG=new Message("DOWN");
		yardsToGoMSG=new Message("YARDS TO GO");
		ballOnTheMSG=new Message("BALL ON");
		possessionMSG=new Message("*");
		sim.add(DownMSG,200,15);
		sim.add(yardsToGoMSG,300,15);
		sim.add(ballOnTheMSG,475, 15);
		sim.add(visitingTeamMSG,650,15);
		homeScoreMSG=new Message(homeTeam.getScore()+"");
		visitScoreMSG=new Message(visitingTeam.getScore()+"");
		actualDownMSG=new Message(currentDown+"");
		actualYTGMSG=new Message(yardsToGo+"");
		actualScrimmageMSG=new Message(lineOfScrimmage+"");
		//updateDisplay();
		sim.add(homeScoreMSG,10,30);
		sim.add(visitScoreMSG,675,30);
		sim.add(actualDownMSG, 225,30);
		sim.add(actualYTGMSG,350,30);
		sim.add(actualScrimmageMSG,525,30);
		
		
	}
	
	public static void updateDisplay()
	{
		
		
		
		if (actualYTGMSG.inASimulation())
		{
		  
		   sim.remove(homeScoreMSG);
		   sim.remove(visitScoreMSG);
		   
		   sim.remove(actualDownMSG);
		   
		   sim.remove(actualYTGMSG);
		   sim.remove(actualScrimmageMSG);
		
		//initial values....
		
		   try
		   {
		    Thread.sleep(200);
		   }	
		     catch (InterruptedException e)
		   {
			
		   }
		}
		
		homeScoreMSG=new Message(homeTeam.getScore()+"");
		
		visitScoreMSG=new Message(visitingTeam.getScore()+"");
		actualDownMSG=new Message(currentDown+"");
		actualYTGMSG=new Message(yardsToGo+"");
		
		actualScrimmageMSG=new Message(lineOfScrimmage+"");
		sim.add(homeScoreMSG,10,30);
		sim.add(visitScoreMSG,675,30);
		sim.add(actualDownMSG, 225,30);
		sim.add(actualYTGMSG,350,30);
		sim.add(actualScrimmageMSG,525,30);
		
		
		
		
		
	}
	public void setHomeTeam(FootballTeam105 team)
	{
		homeTeam=team;
	}
	
	public void setVistingTeam(FootballTeam105 team)
	{
		visitingTeam=team;
	}
	
	
	public static void startGame() 
	{
		homeTeamOffense=false;
		boolean winner=false;
		while (!winner)
		  {
		    //change offense and defense
			sim.remove(possessionMSG);
			
			possessionMSG=new Message("*");
			if (homeTeamOffense)
			{   
				
				sim.add(possessionMSG,695,30);
				offensiveTeam=visitingTeam;
				defensiveTeam=homeTeam;
				homeTeamOffense=false;
			}
			else
			{	
				sim.add(possessionMSG,30,30);
				offensiveTeam=homeTeam;
				defensiveTeam=visitingTeam;
				homeTeamOffense=true;
			}
			updateDisplay();
			newDrive();
			//Determine if another drive is needed.  Both have to have a turn
			offensiveTeam.offensiveDrives++;
			if (offensiveTeam.offensiveDrives==defensiveTeam.offensiveDrives && offensiveTeam.score!=defensiveTeam.score)
				{
				 winner=true;
				 if (homeTeam.getScore()>visitingTeam.getScore())
				     showMessage(homeTeam.getName()+" Wins!!!");
				 else
				 	 showMessage(visitingTeam.getName()+" Wins!!!");
				}
		  }
	}
	
	public static int getDown()
	{
		return currentDown;
	}
	
	public static int getYardsToGo()
	{
		return yardsToGo;
	}
	
	private static void createRobot()
	  {
	    int angle = 0;
	    int robotStartY = 0;

	    if (robot != null)
	    {
	      showError("Robot already exists.  Cannot create a new one.");
	      System.exit(0);
	    }

	    robot = new FootballRobot();

	    //System.out.println(robot);
	    robot.setPower(5);

	    robot.setRealismLevel(realism);
	    angle = 0;
	    robotStartY = 570;
	    robot.setInitialHeading(90);
	    sim.add(robot,445,220);

	    //sim.add(robot, 50, 50);
	    
	    //	resetLocation((((TwoPolygonSim)sim).getMaxX()-50)*1.0, robotStartY*1.0);
	    //sim.add(robot,x,y);
	    // Pause to allow updates prior to attempting to use light sensor
	    // Trying to use light sensor prior to it being ready may result in
	    // an infinite loop
	   
	  }

	public static void newDrive()
	{
		
		endOfDrive=false;
		currentDown=1;
		lineOfScrimmage=25;
		
		if (robot!=null)
			destroyRobot();
		createRobot();
		//Image bg = ImageLoader.getImage(Football105.class, "/eecs/instructor/cobb/helmet.gif");
		
		robot.setAppearance("/eecs/instructor/cobb/helmet.gif");
		yardsToGo=10;
		driveSummaryMSG += "\n";
		showMessage(driveSummaryMSG+" New drive Started ");
		driveSummaryMSG="";
		while (!endOfDrive)
		{
			
			offensiveTeam.offensiveStrategy();
			String offPlay=offensiveTeam.getOffensivePlay();
			defensiveTeam.defensiveStrategy();
			String defPlay=defensiveTeam.getDefensivePlay();
			//offPlay="run";
			//defPlay="blitz";
			printLine("off play"+offPlay);
			int yards=determinePlayResult(offPlay,defPlay);
			if (yards>0 && yards<100)
			   goForward(yards*125);
			
				
			else if (yards<0)
				goBackward(yards*125*-1);
			
			msg="DOWN: "+currentDown+"\n"+"Offensive Play: "+offPlay+"\n"+"Defensive Play "+defPlay+"\n"+"\n"+"Yards:"+yards+"\n";
		    processResults(yards);
		}
		
	}
	public static void processResults(int yardage)
	{   
		if (yardage==999)
		{
			//turnover
			endOfDrive=true;
			
			driveSummaryMSG=offensiveTeam.getName()+ " turns the ball over";
			msg += driveSummaryMSG;
			currentDown=1;
			yardsToGo=10;
		}
		else if (yardage==333){
			endOfDrive=true;
			driveSummaryMSG="The Field Goal was good for "+offensiveTeam.getName();
			msg +=driveSummaryMSG;
			currentDown=1;
			yardsToGo=10;
			offensiveTeam.addToScore(3);
		}
		else if (yardage==444){
			endOfDrive=true;
			driveSummaryMSG=offensiveTeam.getName()+"'s Field Goal Attempt was no Good!";
			msg +="The Field Goal Attempt was no Good!";
			currentDown=1;
			yardsToGo=10;
			
		}
		else if(yardage>=lineOfScrimmage)
		{
			//touchdown...
           offensiveTeam.addToScore(7);
			//update score
			driveSummaryMSG=offensiveTeam.getName()+" TOUCHDOWN";
			msg +="TOUCHDOWN!!!!";
			endOfDrive=true;
			yardsToGo=10;
		}
		else if (yardage>=yardsToGo)
		{   
			msg +="First Down....";
			
			currentDown=1;
			lineOfScrimmage=lineOfScrimmage-yardage;
			if (lineOfScrimmage<10)
				yardsToGo=lineOfScrimmage;
			else
				yardsToGo=10;
		}
		else if (yardage<yardsToGo && currentDown==4)
		{
			driveSummaryMSG=offensiveTeam.getName()+" Ball Turned Over on Downs";
			msg +="Ball turned over on downs";
			currentDown=1;
			endOfDrive=true;
			yardsToGo=10;
		}
		else
		{
			msg +="Gained "+yardage;
			currentDown++;
			yardsToGo=yardsToGo-yardage;
			lineOfScrimmage=lineOfScrimmage-yardage;
		}
		
		
		
		
		updateDisplay();
		printLine(msg);
		try
		 {
		  Thread.sleep(1000);
		 }	
		catch (InterruptedException e)
		{
			
		}
		
		//showMessage(msg);
	}
	public static int determinePlayResult(String offPlay, String defPlay)
	{
		int yardageGained=0;
		int scenario=0;
		
		if (offPlay.equalsIgnoreCase("RUN")&& defPlay.equalsIgnoreCase("RUN"))
			scenario=1;
		else if (offPlay.equalsIgnoreCase("RUN")&& defPlay.equalsIgnoreCase("BLITZ"))
			scenario=2;
		else if (offPlay.equalsIgnoreCase("RUN")&& defPlay.equalsIgnoreCase("ZONE"))
			scenario=3;
		else if (offPlay.equalsIgnoreCase("PASS_SHORT")&& defPlay.equalsIgnoreCase("RUN"))
			scenario=4;
		else if (offPlay.equalsIgnoreCase("PASS_SHORT")&& defPlay.equalsIgnoreCase("BLITZ"))
			scenario=5;
		else if (offPlay.equalsIgnoreCase("PASS_SHORT")&& defPlay.equalsIgnoreCase("ZONE"))
			scenario=6;
		else if (offPlay.equalsIgnoreCase("PASS_LONG")&& defPlay.equalsIgnoreCase("RUN"))
			scenario=7;
		else if (offPlay.equalsIgnoreCase("PASS_LONG")&& defPlay.equalsIgnoreCase("BLITZ"))
			scenario=8;
		else if (offPlay.equalsIgnoreCase("PASS_LONG")&& defPlay.equalsIgnoreCase("ZONE"))
			scenario=9;
		else if (offPlay.equalsIgnoreCase("FG"))
			scenario=10;
		else
		    printLine("ERROR>>> You must use one of the plays as described in the Homework (run, pass_short, pass_long, FG) or (run, zone, blitz)");
		
		
		int subCaseRandom=(int)Math.round(100.0*Math.random());
	//	printLine(subCaseRandom);
		//printLine (scenario+"Scenario");
		switch (scenario)
		{
		
		case 1:// run run
			     if (subCaseRandom<=80)
			     	yardageGained=(int)Math.round(5*Math.random()-2);
			     else if (subCaseRandom<=90)
			     {
			        if (Math.random()>.5)
			        	yardageGained=-5;
				    else
				    	yardageGained=10;
			     }
			     else
			     	yardageGained=999;
			     break;
		case 2: //run blitz
			if (subCaseRandom<=80)
				yardageGained=7;
			else if (subCaseRandom<=95)
			{
				yardageGained=(int)Math.round(-5+Math.random()*7);
			}
			else
				yardageGained=999;
			break;
	    case 3://run zone
	    	if (subCaseRandom<=60)
	    		yardageGained=3+(int)Math.round(Math.random()*9);
	    	else if (subCaseRandom<=90)
	    	{
	    		yardageGained=0;
	    	}
	    	else
	    		yardageGained=999;
	    	break;
	    case 4:  //Pass Short vs run
	    	if (subCaseRandom<=85)
	    		yardageGained=(int)Math.round(-3+Math.random()*10);
	    	else
	    	{
	    		yardageGained=(int)Math.round(Math.random()*-5);
	    	}
	    	
	    	break;
	    case 5:// Pass Short vs Blitz
	    	if (subCaseRandom<=80)
	    		yardageGained=(int)Math.round(Math.random()*5);
	    	else if (subCaseRandom<=95)
	    	{
	    		yardageGained=(int)Math.round(Math.random()*15)-5;
	    	}
	    	else
	    		yardageGained=999;
	    	break;
	    case 6:// Pass short vs Zone
	    	if (subCaseRandom<=50)
	    		yardageGained=(int)Math.round(Math.random()*15)-5;
	    	else if (subCaseRandom<=75)
	    	{
	    		yardageGained=(int)Math.round(Math.random()*-10);
	    	}
	    	else
	    		yardageGained=999;
	    	break;
	    case 7:// Pass Long vs run
	    	if (subCaseRandom<=50)
	    		yardageGained=(int)Math.round(Math.random()*10);
	    	else if (subCaseRandom<=70)
	    	{
	    		yardageGained=(int)Math.round(Math.random()*5)+10;
	    	}
	    	else if (subCaseRandom<=75)
	    	{
	    		yardageGained=-5;
	    	}
	    	else
	    		yardageGained=999;
	    	break;
	    case 8: /* Pass Long vs blitz */
	    	if (subCaseRandom<=80)
	    		yardageGained=(int)Math.round(Math.random()*5);
	    	else if (subCaseRandom<=85)
	    	{
	    		yardageGained=0;
	    	}
	    	else
	    		yardageGained=999;
	    	break;
	    case 9: /* Pass Long vs Zone */
	    	if (subCaseRandom<=50)
	    		yardageGained=5;
	    	else if (subCaseRandom<=70)
	    	{
	    		yardageGained=(int)Math.round(Math.random()*-5);
	    	}
	    	else
	    		yardageGained=999;
	    	break;
		case 10: /* FG */
			if ((lineOfScrimmage <35)) 
			{
				if (subCaseRandom<=60)
		  	    	yardageGained=333;
		  		else
		  		    yardageGained=444;
			}
			
			else if (subCaseRandom<=40)
			{
				yardageGained=333; 
		   	}
			else
			
				yardageGained=444; //field Missed
			
	}			
		return yardageGained;
	
	
}
	public static int getMyScore1(){
	       return (3);
	}
	
	public static int getMyOpponentsScore1(){
		   return defensiveTeam.score;
	}
	public static int getLineOfScrimmage(){
		return lineOfScrimmage;
	}
}