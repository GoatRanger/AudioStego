/*
 * Created on Mar 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.simulation;

/**
 * @author DM0266
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class IT105Solution042 extends IT105Project042 {
	
	static String divisionMessage;
	public static void breachObstacleBelt(){
		int counter=0;
		goForward();
		while (counter<2){
			//identify obstacle
			if (getReflectanceSensor()==48){
        printLine("Fire");
				//breach fire
				goForward(2000);
				 int  yPosition=getMyYPosition();
				 
				gotoPoint(100,yPosition);
				pivotTo(90);
				goForward();
				while (getTemperatureSensor()>90){
					
				}
				halt();
				pivotTo(0);
				while (getReflectanceSensor()==48){
					goForward();
				}
				goForward(1000);
				counter++;
			}
			else if (getReflectanceSensor()==49){
				//breach concertina
				
				while (getReflectanceSensor()==49){
					
					//goForward();
					if (getDualLightSensor()>0){
						pivotRight(3);
						goForward(500);
					}
					else {
						pivotLeft(3);
						goForward(500);
					}
					
				}
				halt();
				goForward(1000);
				counter++;
			}
			
			      
		}
	}
	
	public static void findBlackLine(){
		while (!(getReflectanceSensor()>32 && (getReflectanceSensor()<34))){
			goForward();
		}
		
		halt();
		
	}
	
	
	
	public static void followBlackLine(){
		 while (getMyYPosition()<350)
	        {
	           
	           while (getReflectanceSensor() < 46)
	            {
	            	pivotRight(5);
	               
	                
	            }
                
	           
	                
	                

	            
	            
	            while(getReflectanceSensor()>48)
	            {
	                pivotLeft(5);
	               
	            }
	            
	           
	            	goForward();
	        
		 }
	}
	public static void getMessageFromTower(){
		int myPrivateKey=getMyPrivateKey();
		String msg=getAssemblyAreaName();
		divisionMessage=msg;//decryptMessage(myPrivateKey,msg);
		System.out.println(divisionMessage);
	}
	
	public static void writeXHTML(){
		openWriteFile("bypass.htm");
		       
		     writeLine("<?xml version=\"1.0\" encoding=\"UTF-8\"' ?>");
		     writeLine("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN \"");
		     	   writeLine( "    \"http://www.w3.org/tr/xhtml1/dtd/xhtml1-strict.dtd\">");
		     	   	   writeLine("	<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" ");
		     	   	    writeLine(" <head>");
		     	   	    	    writeLine("   <title>");
		     	   	    	    writeLine("   This is my second web page.");
		     	   	    	    writeLine("  </title>");
		     	   	    	    writeLine(" </head>");
		     	   	    	    writeLine(" <body>");
		     	   	    	    	 writeLine("    <p>");
		     	   	    	    	 
		     	   	    	    	       writeLine("      Look at the");
		     	   	    	    	       writeLine("   <!-- wild and crazy -->");
		     	   	    	    	       writeLine("      title bar of this window.");
		     	   	    	    	       writeLine("      We can't see the words 'wild and crazy'?");
		     	   	    	    	 writeLine("    </p>");	
		     	   	    	    	 int counter=0;
		     	   	    	    	 writeLine("<ul>");
		     	   	    	    	 while (counter<3){
		     	   	    	    	 	writeLine("<li>");
										writeLine(counter);
		     	   	    	    	 	writeLine("</li>");
		     	   	    	    	 	counter++;
		     	   	    	    	 }
		     	   	    	    	 writeLine("</ul>");
		     	   	      	    writeLine(" </body>");
		     	   	     writeLine("</html>");


		       
		       closeWriteFile();

	}
	public static void gotoAA(){
		if (divisionMessage.equalsIgnoreCase("AA Eagle")){
			gotoPoint(600,550);
		}
		else if (divisionMessage.equalsIgnoreCase("AA River")){
			gotoPoint(700,550);
		}
		else if (divisionMessage.equalsIgnoreCase("AA Cobra")){
			gotoPoint(800,550);
		}
		else
			System.out.println("oops");
	}
	public static void main(String[] args) {

     //loadDoubleRandomObstacle();
		loadWaterTest();
	// printLine(getReflectanceSensor());  
	//goForward(5000);
     //	while (getTemperature()>90){
     		
     setPower(100);		

		

    // }
     	
		//breachObstacleBelt();

   //getPolarCoordinates();
	
   //  breachObstacleBelt();
		
	/*	int[] p=new int[4];
		p=getPolarCoordinates();
		//both of the following equations work.  The second set of equations
		
		double x=500+Math.sin(Math.toRadians(p[0]))*p[1];
		double y=10-Math.cos(Math.toRadians(p[0]))*p[1];
		
		gotoPoint(x,y);
		//printLine(getMyYPosition()+"Y position");
	     x=500+Math.sin(Math.toRadians(p[2]))*p[3];
		 y=10-Math.cos(Math.toRadians(p[2]))*p[3];
		
		
		 gotoPoint(x,y);
		 
		 
       findBlackLine();*/
		followBlackLine(); // this should bypass the water as well.
		 
	   halt();
		getMessageFromTower();
		//decryptMessage();
		gotoAA();
		//writeXHTML();

		transmitMissionComplete();
		
	/*	int[] p=new int[4];
		p=getPolarCoordinates();
		for (int i=0;i<4;i++){

		   printLine(p[i]);
		   
		}
		printLine(Math.cos(Math.toRadians(p[0]))*p[1]+"y value");
		printLine(Math.sin(Math.toRadians(p[0]))*p[1]+"x value");
		double x=500-Math.sin(Math.toRadians(p[0]+180))*p[1];
		double y=10-Math.cos(Math.toRadians(p[0]))*p[1];
		printLine(x+" x "+y+ " y");
		gotoPoint(x,y);
		 x=500-Math.sin(Math.toRadians(p[2]+180))*p[3];
		 y=10-Math.cos(Math.toRadians(p[2]))*p[3];
		 gotoPoint(x,y);
        

		transmitMissionComplete();  */
	

   }
}