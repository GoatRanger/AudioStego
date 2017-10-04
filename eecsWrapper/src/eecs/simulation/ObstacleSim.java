/*
 * PolygonSim.java
 *
 * Created on March 13, 2003, 9:53 PM
 */

// Modified DiagonalColoredLine, WidePolygon, FinalProject, PolygonSim, SimBox
package eecs.simulation;

import jago.element.*;

import java.awt.Color;
import java.awt.geom.GeneralPath;

import eecs.instructor.cobb.AntennaElement;
import eecs.instructor.cobb.ObstacleElement;

//import eecs.jago.instructor.cobb.Concertina2;


/**
 * DOCUMENT ME!
 *
 * @author DK8685
 */



public class ObstacleSim extends jago.SimBox
{
  final int obstacle1YLocation=350;
  final	int obstacleHeight=50;
  //final int obstacleWidth=200;
  final int obstacleSpacing=150;
  final int xStart=50;
  final int xEnd=450;
  final int byPassWidth=100;
  final double satelliteX=500;
  final double satelliteY=10;
  static int[] randomObstacleType=new int[4];
  GeneralPath waterRoad=new GeneralPath();
	GeneralPath waterA=new GeneralPath();
	GeneralPath waterB=new GeneralPath();
	GeneralPath road1=new GeneralPath();
	GeneralPath roadLine=new GeneralPath();
	GeneralPath[] obstaclePL=new GeneralPath[3];
	PhaseLine[] phaseLinesArray=new PhaseLine[3];
  int serverYLocation = 0;
  AntennaElement wirelessAntenna=new AntennaElement();
  int[] byPassXLocation=new int[3];
 double[] satelliteXArray=new double[3];
  double[] satelliteYArray=new double[3];
  int fireXBypassLocation,fireYLocation;
 int[] polarObstacleArray=new int[4];
 boolean[] byPassedObstacles=new boolean[2];
  

  /**
   * Creates a new PolygonSim object.
   */
  public ObstacleSim()
  {
    super();

    setBackgroundImage("background.GIF");
    createArena();
    createObstacles();
   createPolarPoints();
   byPassedObstacles[0]=false;
   byPassedObstacles[1]=false;
    
  }
  
  public  int[] getPolarObstacleArray(){
  	return polarObstacleArray;
  }

  public void createPolarPoints(){
  	//get xy from satellite they will be the base
  	
  	PolarPoint[] polarArray=new PolarPoint[2];
  	for (int i=0;i<2;i++){
  		polarArray[i]=new PolarPoint(satelliteX,satelliteY,satelliteXArray[i],satelliteYArray[i]);
  		//Assign values into the polar array....
  		polarObstacleArray[i*2]=(int)polarArray[i].getDirection();
  		//printLine(polarObstacleArray[i*2]+"direction");
  		polarObstacleArray[i*2+1]=(int)polarArray[i].getMagnitude();

  		

  		//printLine(polarObstacleArray[i*2+1]+"magnitude");
  		//printLine(polarArray[i].toString());

  	} 
  	
  	
  }
 public void createArena()
 {
 	 //Arena....
    Wall wall1=new Wall(10,500);
    add(wall1,450,150);
    Wall wall2=new Wall(1,600);
    add(wall2,885,5);
    Wall wall3=new Wall(880,1);
    add(wall3,10,5);
    Wall wall4=new Wall(880,1);
    add(wall4,10,600);
    Wall wall5=new Wall(1,600);
    add(wall5, 50,5);
   
    
    
    
 }
 
 public  void createObstacles()
 {
 	  Wall  leftObstacle1, rightObstacle1, leftObstacle2, rightObstacle2, leftObstacle3, rightObstacle3;
 ObstacleElement obstacle1, obstacle2, obstacle3;
 int concertinaY=0;
 int concertinaX=0;
 BrightLight concertinaLight=new BrightLight();
 	GeneralPath[] obstacleApproachArea=new GeneralPath[3];
 	MultiSegmentLine[] obstacleApproach=new MultiSegmentLine[3];
   GeneralPath waterCrossingSafeZone=new GeneralPath();
 	PhaseLine waterSafeZone;
 	  // 	obstacles (num 1)
 	    
 	 /*   leftObstacle1=new Wall(200,50);
 	    add(leftObstacle1, 70,425);
 	   add(circle[0],270+25,425+25);
 	    rightObstacle1=new Wall(130,50);
 	    add(rightObstacle1,320,425);*/
 	
 	    waterCrossingSafeZone.reset();
 	    waterCrossingSafeZone.moveTo(630,330);
 	    waterCrossingSafeZone.lineTo(740,330);
 	    waterCrossingSafeZone.lineTo(740,332);
 	    waterCrossingSafeZone.lineTo(630,332);
 	    waterCrossingSafeZone.closePath();
 	    waterSafeZone=new PhaseLine(waterCrossingSafeZone,Color.GREEN,true,1);
 	   // add(waterSafeZone,0,0);
 	    //
 	    Color fillColor;
 	    int currentY;
 	    int rightObstacleBegin;
 	    ObstacleElement[] obstacleArray=new ObstacleElement[2];
 	    //int[] byPassX1Location=new int[3]; //stores the left side X coord of the bypass
 	    currentY=obstacle1YLocation;
 	    setRandomObstacleArray();
 	    
 	    for (int i=0;i<=1;i++){
 	          byPassXLocation[i]=(50*((int)Math.round(Math.random()*5)+1));
 	        //  System.out.println(byPassXLocation[i]+"Bypass start");
 	           satelliteXArray[i]=byPassXLocation[i]+byPassWidth/2+xStart-20;//-20 is an adjustment to make sure the gotoPoint works
 	           satelliteYArray[i]=currentY;
 	         //  printLine("point "+i+"("+satelliteXArray[i]+","+satelliteYArray[i]);
 	         obstacleArray[i]=new ObstacleElement();
 	         rightObstacleBegin=  byPassXLocation[i]+xStart+byPassWidth;
 	         
 	       /*  if (randomObstacleType[i+1]==3){
 	             obstacleArray[i].setLeftObstacle(new Wall(byPassXLocation[i],10));
 	             obstacleArray[i].setRightObstacle(new Wall(xEnd-rightObstacleBegin,10));
 	             fillColor=Color.GREEN;
 	         }*/
 	         if (randomObstacleType[i+1]==1){


 	         	obstacleArray[i].setLeftObstacle(new Concertina(byPassXLocation[i],10));
	             obstacleArray[i].setRightObstacle(new Concertina(xEnd-rightObstacleBegin,10));
	             fillColor=Color.YELLOW;
 	        	/*
 	         	 * @TODO add the "invisible" line to detect robots crossing point"
 	         	 */
                
                concertinaLight.setSize(10,10);
                concertinaX=xStart+byPassXLocation[i]+(byPassWidth/2);
                concertinaY=currentY+5;
               // System.out.println(currentY+5);
                add(concertinaLight,concertinaX,concertinaY);
                //printLine("conertina"+concertinaX+" "+concertinaY);
                }
 	         else {//obstacle type 3=Conertina wire
 	         	obstacleArray[i].setLeftObstacle(new Fire(byPassXLocation[i],10));
	             obstacleArray[i].setRightObstacle(new Fire(xEnd-rightObstacleBegin,10));
	             fillColor=Color.RED;
	             setFireXByPassLocation(byPassXLocation[i]+xStart+byPassWidth/2);
	             setFireYLocation(currentY);
	            // System.err.println(currentY+ " fire y location");
 	         }
 	  
 	  
 	  //obstacle appraoch painting...
 	  obstacleApproachArea[i]=new GeneralPath();
 	  obstacleApproachArea[i].reset();
 	  obstacleApproachArea[i].moveTo(xStart+1,currentY+11);
 	  obstacleApproachArea[i].lineTo(xStart+1,currentY+51+obstacleSpacing);
 	  obstacleApproachArea[i].lineTo(449,currentY+51+obstacleSpacing);
 	  obstacleApproachArea[i].lineTo(449,currentY+11);
 	  obstacleApproachArea[i].closePath();
 	  obstacleApproach[i]=new MultiSegmentLine(obstacleApproachArea[i],fillColor,true,1);
 	  add(obstacleApproach[i],0,0);
 	  
 	  //phase lines
 	  obstaclePL[i]=new GeneralPath();
 	  obstaclePL[i].reset();
 	  
 	  obstaclePL[i].moveTo(xStart,currentY);
 	  obstaclePL[i].lineTo(xStart,currentY+1);
 	  obstaclePL[i].lineTo(449,currentY+1);
 	  obstaclePL[i].lineTo(449,currentY);
 	  
 	  obstaclePL[i].closePath();
 	  phaseLinesArray[i]=new PhaseLine(obstaclePL[i],Color.GRAY,true,1);
 	  add(phaseLinesArray[i],0,0);
 	  
 	 
 	  
 	  add(obstacleArray[i].getLeftObstacle(), xStart,currentY);
 	 
 	  add(obstacleArray[i].getRightObstacle(), rightObstacleBegin,currentY);
 	  
 	  currentY=currentY-((obstacleHeight+obstacleSpacing));
 	    }
 	  
 	    road1.reset();
 	    road1.moveTo(70,80);
 	    road1.lineTo(690,80);
 	    road1.lineTo(690,125);
 	    
	MultiSegmentLine lineLeftToRight=new MultiSegmentLine(road1,Color.WHITE,false,40);
 	    add(lineLeftToRight,0,0);
 	   add(waterSafeZone,0,0);                         
 	    //line following obstacle over water
        if (Math.random()>.5){
        	curveRight();
        }
        else{
        	curveLeft();
        }
 	  
 	  
 	  add(wirelessAntenna,700,400);
 	
 }  
 
 public void setFireYLocation(int currentY){
        fireYLocation=currentY;
}
 
 public int getFireYLocation(){
    return fireYLocation;
}
 public int getFireXByPassLocation(){
 	return fireXBypassLocation;
 }
 public void setFireXByPassLocation(int xlocation){
 	fireXBypassLocation=xlocation;
 }
 
 public void validateRobotLocation(int x1, int y1,int x2, int y2){
 	if (this.arePointsClose((int)this.satelliteXArray[0]+20,(int)this.satelliteYArray[0],x1,y1,35)){
 		byPassedObstacles[0]=true;
 	}
 	if (this.arePointsClose((int)this.satelliteXArray[1]+20,(int)this.satelliteYArray[1],x2,y2,35)){
 		byPassedObstacles[1]=true;
 	}	
 }
 public void curveRight(){
 	 waterRoad.reset();
  	 
  	    waterRoad.moveTo(640,350);
  	    waterRoad.curveTo(640,350,765,175,640,125);
  	    waterRoad.lineTo(740,125);
  	    waterRoad.curveTo(740,125,865,175,740,350);
  	    
  	    waterRoad.closePath();
  	  ;
  	   
  	   
  	   
  	   //MultiSegmentLine line2=new MultiSegmentLine(test2,Color.WHITE,false,10);

  	   
  	    MultiSegmentLine path=new MultiSegmentLine(waterRoad,Color.WHITE,true,20);
  	    roadLine.moveTo(65,80);
 	    roadLine.lineTo(690,80);
  	    roadLine.lineTo(690,125);
 	    roadLine.curveTo(690,125,815,175,690,355);
 	    MultiSegmentLine centerPath=new MultiSegmentLine(roadLine,Color.BLACK,false,10);
 	  // Water on the Left side 
 	    waterRoad.closePath();
 	    waterA.reset();
  	    waterA.moveTo(500,125);
 	    waterA.lineTo(500,350);
 	    waterA.lineTo(640,350);
 	    waterA.curveTo(640,350,765,175,640,125);
 	    waterA.lineTo(500,125);
 	    waterA.closePath();
 	    
 	   //   Water on the right side
  	  waterB.reset();
  	  waterB.moveTo(740,125);
  	  waterB.curveTo(740,125,865,175,740,350);
  	  waterB.lineTo(850,350);
  	  waterB.lineTo(850,125);
  	  waterB.closePath();
  	    
  
  	  WaterElement waterLeft=new WaterElement(waterA,Color.BLUE,true,2);
  	  WaterElement waterRight=new WaterElement(waterB,Color.BLUE,true,2);
  	  
  	add(path,0,0);

 	//  add(path,0,0);

	add(waterLeft,0,0);
 	 add(waterRight,0,0);
 	add(centerPath,0,0);
 }
 public void curveLeft(){
 	 waterRoad.reset();
  	
  	    waterRoad.moveTo(640,350);
  	    waterRoad.curveTo(640,350,465,175,640,125);
  	    waterRoad.lineTo(740,125);
  	    waterRoad.curveTo(740,125,565,175,740,350);
  	    
  	    waterRoad.closePath();
  	  ;
  	   
  	    /*waterA.reset();
  	    waterA.moveTo(100,50);
  	    waterA.lineTo(690,50);
  	    waterA.lineTo(700,100);
  	    waterA.curveTo(680,100,590,150,715,300);
  	    waterA.lineTo(705,350);*/
  	   
  	   //MultiSegmentLine line2=new MultiSegmentLine(test2,Color.WHITE,false,10);

  	   
  	    MultiSegmentLine path=new MultiSegmentLine(waterRoad,Color.WHITE,true,20);
  	    roadLine.moveTo(65,80);
 	    roadLine.lineTo(690,80);
  	    roadLine.lineTo(690,125);
 	    roadLine.curveTo(690,125,515,175,690,350);
 	    MultiSegmentLine centerPath=new MultiSegmentLine(roadLine,Color.BLACK,false,10);
 	  // Water on the Left side 
 	    waterRoad.closePath();
 	    waterA.reset();
  	    waterA.moveTo(500,125);
 	    waterA.lineTo(500,350);
 	    waterA.lineTo(620,350);
 	    waterA.curveTo(620,350,465,175,620,125);
 	    waterA.lineTo(500,125);
 	    waterA.closePath();
 	    
 	   //   Water on the right side
  	  waterB.reset();
  	  waterB.moveTo(760,125);
  	  waterB.curveTo(760,125,565,175,740,350);
  	  waterB.lineTo(850,350);
  	  waterB.lineTo(850,125);
  	  waterB.closePath();
  	    
  
  	  WaterElement waterLeft=new WaterElement(waterA,Color.BLUE,true,2);
  	  WaterElement waterRight=new WaterElement(waterB,Color.BLUE,true,2);
  	add(path,0,0);

 	//  add(path,0,0);

	add(waterLeft,0,0);
 	 add(waterRight,0,0);
 	add(centerPath,0,0);
 }
 public void setRandomObstacleArray(){
    int randomNumber;
 	randomNumber=(int)Math.round(Math.random()*100);
 	
    if (randomNumber<=50){
    	randomObstacleType[1]=1;
    	randomObstacleType[2]=2;
    	
    }
    else {
    	randomObstacleType[1]=2;
    	randomObstacleType[2]=1;
    	
    }
   
   
 	}
 
  public void addSquare(int xCoord, int yCoord, int length)
  {
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
   *  This method is used to determine if the Xloc,yloc is within a square area of the xbase, ybase
   *   The square are is defined by width.
   *
   *
   */
  public boolean arePointsClose(int xbase, int ybase, int xLoc, int yLoc,
    int width)
  {
    if ((xLoc >= (xbase - width)) && (xLoc <= (xbase + width)) &&
          (yLoc >= (ybase - width)) && (yLoc <= (ybase + width)))
    {
      return (true);
    }
    else
    {
      //    printLine("Point that fails is "+xLoc+","+yLoc);		
      //	printLine("Base point is "+xbase+","+ybase);
      return (false);
    }
  }


}
