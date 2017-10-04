/*
 * Created on Oct 31, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.simulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import jago.element.LegoRobot;
import jago.element.RobotSensors;
import jago.element.StandardSensor;
import jago.element.StandardSensorFactory;

import eecs.instructor.cobb.ObstacleCrossingBot;
//import eecs.jago.instructor.flowers.DozerBot;
import jago.element.Wall;




/**
 * @author dm0266
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FinalProject042 extends eecs.RobotGuiBase
{
  public static boolean areIntersectingPointsClose = false;
  static int divisionPublicKey, divisionPrivateKey, cadetsPublicKey, cadetsPrivateKey;
  static long startTime;
  static double totalTime;
  static boolean areCrossingPointsValid;
  static String assemblyArea;
  static boolean testing;
  static
  {
    sim = new ObstacleSim();
    setSimulationTitle("IT105/155 Final Project");
    frame = sim;
    sim.setVisible(true);
    //sim.setDebugging(true);
    robot = null;
    runRealisticSim();
    Wall  leftObstacle1, rightObstacle1, leftObstacle2, rightObstacle2, leftObstacle3, rightObstacle3;
    createRobot();
    createKeys();
    startTime=System.currentTimeMillis();

  }
  public static void createKeys(){
  	divisionPublicKey=(int)Math.round(Math.random()*1000);
  	divisionPrivateKey=(int)Math.round(Math.random()*1000);
	cadetsPublicKey=(int)Math.round(Math.random()*1000);
	cadetsPrivateKey=(int)Math.round(Math.random()*1000);
  }
  public static void createRobot(int x, int y, int angle)
  {
  	createRobot();
  }
 
  

  private static void createRobot()
  {
    int angle = 0;
    int robotStartY = 500;


    int robotStartX=140;



    if (robot != null)
    {
      showError("Robot already exists.  Cannot create a new one.");
      System.exit(0);
    }

    robot = new ObstacleCrossingBot();

    StandardSensor lightSensor = StandardSensorFactory.createSensor(RobotSensors.LIGHT,
        robot);
    StandardSensor hitSensor = StandardSensorFactory.createSensor(RobotSensors.HIT,
        robot);
    StandardSensor bumperSensor = StandardSensorFactory.createSensor(RobotSensors.INVERTED_FRONT_BUMPER,
        robot);
    robot.addSensor(LegoRobot.RCX_2, bumperSensor);
    robot.addSensor(LegoRobot.RCX_3, hitSensor);
    robot.addSensor(LegoRobot.RCX_1, lightSensor);
    robot.setPower(50);

    robot.setRealismLevel(realism);
    



    

    robot.setInitialHeading(angle);
    int startPosition=chooseButton("Where do you want to start?","beginning","road","Tower");
    if (startPosition==BUTTON1){
    	robotStartX=140;
    	robotStartY=550;
    	testing=false;
        }
    else if (startPosition==BUTTON2){
    	robotStartX=340;
    	robotStartY=100;
    	testing=true;
    	/*
    	 * @TODO need to add some crossing point data...
    	 */
    }
    else{
    	robotStartX=600;
        robotStartY=400;
        testing=true;
        robot.setInitialHeading(180);
    }
    sim.add(robot, robotStartX, robotStartY);

    //sim.add(robot, 50, 50);
  //  ((ObstacleCrossingBot) robot).resetLocation();
   // ((ObstacleCrossingBot) robot).setMovementScale(40.0);
   
    //	resetLocation((((TwoPolygonSim)sim).getMaxX()-50)*1.0, robotStartY*1.0);
    //sim.add(robot,x,y);
    // Pause to allow updates prior to attempting to use light sensor
    // Trying to use light sensor prior to it being ready may result in
    // an infinite loop
    pause(500);
  }

  /**
   * overrides the TimingNavigatorRobots's reset method?
   *
   */


  /*public static void resetLocation(double x, double y)
  {
    ((TimingNavigatorRobot)robot).distanceTraveled.setLocation(x, y);
  }
  */
  
  public static boolean getBridgingStatus(){
  	return (((ObstacleCrossingBot)robot).getBridgeBreached());
  }
  public static void checkHighScores(){
  	//  	openReadFile("highScores.txt");
  	
  	File myf=new File("highScores.txt");
  	if(myf.exists()){
  		printLine("Exists");
  		
  		
  	}
  	else{
  		try{	
  			myf.createNewFile();
  			printLine("File created");
  			openWriteFile("highScores.txt");
  			for (int i=0;i<5;i++){
  				writeLine("1000.0");
  			}
  			closeWriteFile();
  		}
  		
  		catch (Exception e){
  			}
  	
  	}
  	try
	{
  	 fileReader = new BufferedReader(new FileReader("highScores.txt"));
  	}
  	catch (Exception e) {
    showError("Could not open file " + myf);
    System.exit(0);
    }
  	String highScores="Best Times"+"\n";
  	double[] scores=new double[6];
  	int counter=0;
  	while(moreToRead()){
  		String line=readLine();
  		scores[counter]=Double.parseDouble(line);
  		
  		counter++;
  		
  	}
  	scores[5]=totalTime;
  	closeReadFile();
  	Arrays.sort(scores);
  	if (scores[5]==totalTime){
  		highScores+="\n"+"No High Score this time"+"\n";
  	}
  	
  	else
  		highScores+="\n"+"Great Job, A new high Score"+"\n";
  	
  	
  	openWriteFile("highScores.txt");
  	for (int i=0;i<5;i++){
  		writeLine(scores[i]);
  		highScores+=(i+1)+" "+scores[i]+"\n";
  	}
  	closeWriteFile();
  	showMessage(highScores);
  	//sort Array
  	//find out if current score is lower than any
  }
  
  public static boolean foundValidCrossingPoints(){
  	//int x1
  	/*int x1=((ObstacleCrossingBot)robot).getX1();
    int x2=((ObstacleCrossingBot)robot).getX2();
    int y1=((ObstacleCrossingBot)robot).getY1();
    int y2=((ObstacleCrossingBot)robot).getY2();
  	//send x1, x2 to obstaclesim and then check there because the base points are known.
  	((ObstacleSim)sim).validateRobotPoints(x1,y1,x2,y2);
  	//if validated then msg+ passed obstacle 1
  	//then check the second obstacle
  	//if validated then msg+ passed obstacle 2
  	//if both validated set areCrossingPoints=true;
     if (((ObstacleSim)sim).byPassedObstacles[0] && ((ObstacleSim)sim).byPassedObstacles[1]){
         return true;
     }
     else 
         return false;*/
    if (((ObstacleCrossingBot)robot).hasCorrectlyCrossedConcertina() && ((ObstacleCrossingBot)robot).hasCorrectlyCrossedFire()){
        return true;
     }
    else {
       return false;
    }
         
  }
  public static boolean foundCorrectAssemblyArea(){
  	int currentX=getMyXPosition();
  	int currentY=getMyYPosition();
  	if ((assemblyArea.equals("AA Eagle")) && ((ObstacleSim)sim).arePointsClose(600,550,currentX,currentY,25)){
  		return true;
  	}
  	else if  ((assemblyArea.equals("AA River")) && ((ObstacleSim)sim).arePointsClose(700,550,currentX,currentY,25)){
  		
  		return true;
  		}
  	else if   ((assemblyArea.equals("AA Cobra")) && ((ObstacleSim)sim).arePointsClose(800,550,currentX,currentY,25)){
  		return true;	
  	}  		
  	else
  		return false;
  	
  }
  
  public static void transmitMissionComplete(){
  	//System.out.println("Still need to finish this method");
    totalTime=(System.currentTimeMillis()-startTime)/1000.0;
	System.out.println("TotalTime="+totalTime+" seconds");
	
	/*
	 * @TODO add the opening of file read file and check for high score...
	 */
	;
	if (foundValidCrossingPoints()&& foundCorrectAssemblyArea()&&(!testing)){
		checkHighScores();
	}
	else if (testing){
		showMessage("Testing Complete");
	}
    else {
    showError("Failed to correctly identify crossing points or you are in the wrong AA");
    }
  }
  public static void setPower(int power)
  {
	  checkRobot("setPower");

	  if ((power < 0) || (power > 100))
	  {
		  showError("Tried to setPower with power level " + power +
			  ".\n" + "Must be between 1 and 100.");
		  System.exit(0);
	  }

	  robot.setPower(power);
  }
  
  public static void setRobotAppearance(String filename) {
  	checkFile(filename);
  	robot.setAppearance(filename);
  }
 
  public static int getHeading()
  {
    

    return (int)getHeadingDouble();
  }

  public static double getHeadingDouble()
  {
    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;
    double angle;
    if (robot==null)
    	return Double.NaN;
    x1 = robot.getLocation().getX();
    y1 = robot.getLocation().getY();
    goForward(75);
    x2 = robot.getLocation().getX();
    y2 = robot.getLocation().getY();
    x3 = x1 - x2;
    y3 = y1 - y2;

    //System.err.println("Going relative ("+x1 + ", " + y1 +")");
    // Calculate angle to go to:
    angle = Math.atan2(y3, x3);

    if (Math.toDegrees(angle) <= 90)
    {
      angle = Math.toDegrees(angle) + 270;
    }
    else
    {
      angle = Math.abs(90 - Math.toDegrees(angle));
    }
    return angle;
  }

  /**
   * 
   * @return 3 integer pairs. each pair represents the direction and distance from the Satellit
   * potition at 500,10 in the simulation.The first number of each pair represents the distance while
   * the second number is the direction from 500,10.
   */
  public int[] getBypassInfo(){
    int[] array=new int[3];
  	/*
  	 * @TODO 042:getBypassInfo
  	 */
  	return array;
  }
  
  

  

  

  /**
   *
   * @return an integer of the x-coordinate of the current location of the robot.
   */

  public static int getMyXPosition()
  {
    return (int) robot.getLocation().getX();
  }

  /**
     *
     * @return an integer of the y-coordinate of the current location of the robot.
     */
  public static int getMyYPosition()
  {
    return (int) robot.getLocation().getY();
  }

  /**
   * This method will move your robot to the x,y coordinate.
   *
   * @param x X coordinate
   * @param y Y coordinate
   */
  public static void gotoPoint(double x, double y)
  {
    // Determine relative points
    double x1 = 0;
    double y1 = 0;

    //double distance=Math.sqrt(x1*x1+y1*y1);
    double inchDistance = 10;
    double angle = 0;

    // Note: since vert axis is reverse of standard, negate y1
    // y1 = -y1;
    while (inchDistance > 5)
    {
      x1 = x - robot.getLocation().getX();
      y1 = y - robot.getLocation().getY();

      //System.err.println("Going relative ("+x1 + ", " + y1 +")");
      // Calculate angle to go to:
      angle = Math.atan2(y1, x1);

      // Calculate distance to travel:
      //System.err.println("  and angle of " + Math.toDegrees(angle));
      if (y1 != 0)
      {
        inchDistance = y1 / Math.sin(angle);
      }
      else
      {
        inchDistance = x1 / Math.cos(angle);
      }

      //printLine("inch dist "+inchDistance+"distance "+distance);
      //System.err.println("   and I am " + inchDistance + " away.");
      angle = Math.toDegrees(angle) + 90;

      //	printLine("Angle = "+angle+"; getHeading= "+getHeading());
      // Now convert theory into action:
      //angle=330;
      //System.err.println(angle);
      ((ObstacleCrossingBot) robot).gotoAngle((float) angle);
      goForward(75);
     
    }
  
   
  
  }
  
  public static void pivotTo(float angle){
	    ((ObstacleCrossingBot)robot).gotoAngle(angle);
}
     public static int getDivisionPublicKey(){
     	
		return divisionPublicKey;
     }
     public static int getDivisionPrivateKey(){
     	
     	
		return divisionPrivateKey;
     }
     public static int getMyPublicKey(){
     	
		return cadetsPublicKey;
     }
     public static int getMyPrivateKey(){
     	
     	
		return cadetsPrivateKey;
     }
     public static String decryptMessage(int key, String msg){
     	
     	double randomAA=Math.random();
     	if (key==cadetsPrivateKey){
     		if (randomAA<.33){
     		    assemblyArea="AA Eagle";
     		}
     		else if (randomAA<.66){
     			assemblyArea="AA River";
     		}
     		else{
     			assemblyArea="AA Cobra";
     		}
     	}
        else{
        	assemblyArea="asl;dkfj-32";
        }
     	return assemblyArea;
     }	
    
     public static String getAssemblyAreaInfo(){
     	return "JKJFDSI*&)(*K:";
     }
     /*
      * @TODO Need to access the robots stored crossing locations to provide to user.
      */
     public static int getObstacle1XLocation(){
     	return ((ObstacleCrossingBot)robot).getX1();
     }
     public static int	getObstacle1YLocation(){
     	return ((ObstacleCrossingBot)robot).getY1();
     }
     public static int getObstacle2XLocation(){
   	     return ((ObstacleCrossingBot)robot).getX2();
     }
     public static int	getObstacle2YLocation(){
     		return ((ObstacleCrossingBot)robot).getY2();
     }
    
     
     public static int[] getPolarCoordinates(){
     	int[] pcArray=new int[4];
     	pcArray=((ObstacleSim)sim).getPolarObstacleArray();
     	
     	return pcArray;
     }
    
     /*
      * @TODO Finish GetTemperature.  perhaps if you know the location of fire just test for it?
      */
     public static int getTemperature(){
     	int currentX;
     	int currentY;
     	int currentFireY;
     	int deltaY;
     	int bypassXMin, bypassXMax;
     	
     	currentX=getMyXPosition();
     	currentY=getMyYPosition();
        currentFireY=((ObstacleSim)sim).getFireYLocation();
        deltaY=currentY-currentFireY;

     	
     	

     	bypassXMin=((ObstacleSim)sim).getFireXByPassLocation()-20;
     	bypassXMax=((ObstacleSim)sim).getFireXByPassLocation()+20;
     	//System.out.println("Bypass min and max: "+bypassXMin+ " "+bypassXMax);
     	if (currentX>bypassXMin && currentX<bypassXMax && deltaY<=150) {    // && currentX>bypassXMin && currentX<bypassXMax){
     		
     		//System.out.println(currentX+" currentX");
     		return 10;
     		

     	}
     	else   
     	   	return (100);
     }
     
} 
     
 

