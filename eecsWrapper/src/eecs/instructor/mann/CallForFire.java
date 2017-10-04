/*
 * CodeSimulation.java Created on January 18, 2003, 9:51 AM
 */
package eecs.instructor.mann;

import java.awt.Image;
//import java.io.IOException;

import jago.ImageLoader;
import jago.element.Message;


/**
 * @author Tracy L. Mann
 */
public class CallForFire extends eecs.RobotGuiBase {

  public static final int OVER = 1;
  public static final int ON = 0;
  public static final int SHORT = -1;
  public static final int OFF = 1;
    
  static int runGUI = ON;
  static boolean createTestCases = false;
  static String description = "";
  static boolean manual = false;
  
  
  static int maxAdjustFactor = 0;
  static double OT = 0;
  static int factor = 0;
  static int actualRange;
  static double estimatedRange = 0;
  static int adjustedRange = 0;
  static double observedDeviation = 0;
  static int observedSpot = 0;
  static double deltaY = 0;
  static double lastX = 0;
  static double lastY = 0;
  static double deviationCorrection = 0;
  static String addDrop = "";
  static String shift = "";
  static String observedSpotText;

  public static double yFactor = 0;  // 9.5;
  public static double xFactor = 0; //1.0/6.0;
  
  
  static {
  	
    getProperties();

    if (!runningTests && !createTestCases) {
 
        sim = new jago.SimBox();
	    Image bg = ImageLoader.getImage(CallForFire.class,
	        "/eecs/instructor/mann/images/binosWithEnemy2.jpg");
	    sim.setBackgroundImage(bg);
	    // The follow should be in every GUI
	    //  (unless a known, specific reason to omit)
	    frame = sim; // so that all the windows from eecs.GuiBase will
	    // appear in the sim box
	    sim.setVisible(true);
	    robot = null;
	    //sim.add(explosion,100,100);
    }
  }

  public static void fire(int x, int y) {
    lastX = x;
    lastY = y;
    adjustFire(0, 0);
  }

  public static String laseTarget(int isGuiOn) {

    getProperties();

    if (!runningTests && !createTestCases) {
    	runGUI = isGuiOn;
    }
    else {
    	runGUI = OFF;
    }
    
  	return "GT85429399";
  }

  public static void callForFireManualTests(int aRange, int eRange, int dev) {

   	actualRange = aRange;
	estimatedRange = eRange;
	observedDeviation = dev;
	
	manual = true;
  	
	callForFire("");
  }
  
  public static void callForFire(String grid) {
  	
    if (!manual) {
  	
  	getProperties();

    if (runningTests) {

    	actualRange = getInt("");
    	estimatedRange = getInt("");
    	observedDeviation = getInt("");
    	
    	
//    	//get input from the testing system for actualRange
//    	try {
//	
//	        String result = "0";
//	        result = testConsoleReader.readLine();
//	        actualRange = Integer.parseInt(result);
//	    } catch (IOException e) {
//	        System.err.println("Error reading the actualRange int from test file (console)." +
//	          "  Format in input file was incorrect.");
//	    }  // end getting input from the testing system for actualRange
//
//    	//get input from the testing system for estimatedRange
//	    try {
//	
//	        String result = "0";
//	        result = testConsoleReader.readLine();
//	        estimatedRange = Integer.parseInt(result);
//	    } catch (IOException e) {
//	        System.err.println("Error reading the estimatedRange int from test file (console)." +
//	          "  Format in input file was incorrect.");
//	    }  // end getting input from the testing system for estimatedRange
//
//    	//get input from the testing system for observedDeviation
//    	try {
//    		
//	        String result = "0";
//	        result = testConsoleReader.readLine();
//	        observedDeviation = Integer.parseInt(result);
//	    } catch (IOException e) {
//	        System.err.println("Error reading the observedDeviation int from test file (console)." +
//	          "  Format in input file was incorrect.");
//	    }  // end getting input from the testing system for observedDeviation
    		    
    }
    else {
    	
	    if (grid.equals("createTestCases")) {
	    	
	    	createTestCases = true;

	    	// Declare local variables
	        String actualRangeInput, estimatedRangeInput, observedDeviationInput, temp;
	        
	        description = readLine(); 
	         actualRangeInput = readLine(); 
	         estimatedRangeInput = readLine(); 
	         observedDeviationInput = readLine(); 
	         temp = readLine(); 

	         actualRange = Integer.parseInt(actualRangeInput); 
	         estimatedRange = Integer.parseInt(estimatedRangeInput); 
	         observedDeviation = Integer.parseInt(observedDeviationInput); 
	    	
	    }  // end if (grid.equals("createTestCases"))
	    else {
	    	createTestCases = false;
		    
	    	// actualDistance random # 500-5000
		    actualRange = randNumBtwn(500, 5000);
		
		    // determine maxAdjustForRange
		    if (actualRange<=1000){
		    	maxAdjustFactor = 100;
		    } else if (actualRange<=2000){
		    	maxAdjustFactor = 200;
		    } else {
		    	maxAdjustFactor = 400;
		    }
		    
		    // Determine the estimatedRange it will be off by plus/minus a factor
		    // The factor is a random number between 3 times the maxAdjustFactor
		    factor = 3 * maxAdjustFactor;
		     
		    // Ensure that the estimatedRange is always off by at least 50
		    int amountOff = 0;
		    while(Math.abs(amountOff)<50){
		    	amountOff = randNumBtwn(-factor, factor);
		    }
		    estimatedRange = actualRange + amountOff;
		    
		    // Determine observedDeviation...Ensure that it is never 0
		    observedDeviation = 0;
		    while (observedDeviation == 0) {
		    	observedDeviation = randNumBtwn(-5, 5)*10;
		    }  // determine observedDeviation while loop

	/*  Uncomment the lines below to run tests by hand******************************************************/
		    
/*	these will over write the random generated inputs
		    actualRange = 1550;  // estimated is + 50
		    actualRange = 1100;  // estimated is + 50
	    	actualRange = 1500;  // estimated is + 100
	    	actualRange = 600;  // estimated is + 200
	    	actualRange = 650;  // estimated is + 3*200-50
		    estimatedRange = 700;
		    observedDeviation = -40;
		     
		    actualRange = 1550;  // estimated is + 50
		    actualRange = 1100;  // estimated is + 50
	    	actualRange = 1500;  // estimated is + 100
	    	actualRange = 1400;  // estimated is + 200
	    	actualRange = 3890;  // estimated is + 3*200-50
		    estimatedRange = 3200;
		    observedDeviation = -40;

		    
	    
		    actualRange = 1550;  // estimated is + 50
		    actualRange = 1100;  // estimated is + 50
	    	actualRange = 1500;  // estimated is + 100
	    	actualRange = 1400;  // estimated is + 200
	    	actualRange = 1050;  // estimated is + 3*200-50
		    estimatedRange = 1200;
		    observedDeviation = -40;

		    
		    actualRange = 2855;  // estimated is + 3*400-55
		    actualRange = 2955;  // estimated is + 3*400-155
		    actualRange = 3055;  // estimated is + 3*400-255
		    actualRange = 3950;  // estimated is + 50
		    actualRange = 3900;  // estimated is + 100
		    actualRange = 3800;  // estimated is + 200
		    actualRange = 3600;  // estimated is + 400
		    estimatedRange = 4000;
		    observedDeviation = -40;
	*/

	/*  Uncomment the lines above to run tests by hand******************************************************/

	    	
	    } // end if-else (grid.equals("createTestCases"))
	    
    }  // end if-else (runningTests)
    
    } // end if (!manual)
    
    // determine determine modified maxAdjustForRange 
    // based on the estimated range...this is all that the user knows
    if (estimatedRange<=1000){
    	maxAdjustFactor = 100;
    } else if (estimatedRange<=2000){
    	maxAdjustFactor = 200;
    } else {
    	maxAdjustFactor = 400;
    } // end if for modified maxAdjustForRange
    
    // determine OT factor
    if (estimatedRange<1000){
    	OT = estimatedRange/1000;
    } else if (estimatedRange<=1499){
    	OT = 1;
    } else if (estimatedRange<=2499){
    	OT = 2;
    } else if (estimatedRange<=3499){
    	OT = 3;
    } else if (estimatedRange<=4499){
    	OT = 4;
    } else {
    	OT = 5;
    }

    // Determine observedSpot
    if (Math.abs(estimatedRange-actualRange) < 50){
    	observedSpot = ON;
    } else if ((estimatedRange-actualRange) < 0){
    	observedSpot = SHORT;
    } else {
    	observedSpot = OVER;
    }

    // Determine deviationCorrection
    deviationCorrection = OT * (-observedDeviation);
    
    
	    printCallForFireInformation();
	  	printObservedRoundInformation();
	
	    deltaY = estimatedRange-actualRange;
	    
	    // yFactor = 200 pix / deltaY meters ...units pix/meter
	    yFactor = -220/Math.abs(deltaY); 
	    
	    // xFactor = 90 pix / 10 mils...units pix/mils
	    xFactor = 9; 
	    
	  	lastX=583+observedDeviation*xFactor;
	  	lastY=310+deltaY*yFactor;
	  	
	    if (!createTestCases) {

	    	callForFireGUI();
	    }
	    
//	  	printLine("actualRange = "+actualRange);
//	  	printLine("estimatedRange = "+estimatedRange);
//	  	printLine("distance from target =" + deltaY);
//	  	printLine();
  }

  public static int getRange() {
    return (int)estimatedRange;
  }

  public static int getObservedSpot() {
    return observedSpot;
  }

  public static int getObservedDeviation() {
    return (int)observedDeviation;
  }

  public static void adjustFire(double deviation, int adjustFactor) {
    
//    printLine("computed OT = " + OT);
//    printLine("Userdeviation = " + deviation);
//    printLine("computeddeviation = " + deviationCorrection);
  	
  	observedDeviation = (int)Math.round((deviation-deviationCorrection)/10)*10;
    
    // 
    deviationCorrection = OT * (-observedDeviation);
    
    estimatedRange = estimatedRange + adjustFactor;
    
    // Determine observedSpot
    if (Math.abs(estimatedRange-actualRange) < 50){
    	observedSpot = ON;
    } else if ((estimatedRange-actualRange) < 0){
    	observedSpot = SHORT;
    } else {
    	observedSpot = OVER;
    }    
    
    printAdjustFireInformation(deviation, adjustFactor);
  	printObservedRoundInformation();

    deltaY = estimatedRange-actualRange;
    
//  	printLine("actualRange = "+actualRange);
//  	printLine("estimatedRange = "+estimatedRange);
//  	printLine("distance from target =" + deltaY);
//  	printLine();

    if (!createTestCases) {
    	adjustFireGUI(deviation/OT, adjustFactor);
    }
	
  } // end adjustFire method
  public static void callForFireGUI(){

    if (runGUI == ON){
	  	Explosion exp = new Explosion();
	    sim.add(exp, lastX, lastY);
	    exp.makeSmoke(4000);
	    while (exp.inASimulation()) {
	      try {
	        Thread.sleep(100);
	      } catch (InterruptedException ie) {
	        ie.printStackTrace();
	      }
	    }
    } // end if (runGUI=on)
  	
  }

  public static void callForFireGUITest(int lastX, int lastY){

  	Explosion exp = new Explosion();
    sim.add(exp, lastX, lastY);
    exp.makeSmoke(5000);
    while (exp.inASimulation()) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  	
  }
  public static void adjustFireGUI(double deltaX, int deltaY) {

    if (runGUI == ON){
        
    	    Message msg = new Message("Dev: " + deltaX + "   Adjust: " + deltaY ,15,Message.COLOR_RED);
    	    msg.setDuration(3);
    	    sim.add(msg,540,50);

    	    lastX = lastX + deltaX * xFactor;
    	    lastY = lastY + deltaY * yFactor;
    	    
    	    Explosion exp = new Explosion();
    	    sim.add(exp, lastX, lastY);
    	    exp.makeSmoke(5000);
    	    while (exp.inASimulation()) {
    	      try {
    	        Thread.sleep(100);
    	      } catch (InterruptedException ie) {
    	        ie.printStackTrace();
    	      }
    	    }
    	    sim.remove(msg);
    } // end if (runGUI=on)
  	
  } // end adjustFire method
  
  public static void fireForEffect(int addDrop) {

  	if (createTestCases) {

    	if (addDrop == 50 && observedSpot != ON){
        	writeLine("      <entry>" + "Add 50, Fire For Effect!" + "</entry>");
    	    lastY = lastY + 50 * yFactor;
        }
    	else if (addDrop == -50 && observedSpot != ON) {
        	writeLine("      <entry>" + "Drop 50, Fire For Effect!" + "</entry>");
    	    lastY = lastY + -50 * yFactor;
    	}
    	else {
    		writeLine("      <entry>" + "Fire For Effect!" + "</entry>");
    	}
		writeLine("    </output>");
        writeLine("  </test-case>");
    }
    else {
      	if (addDrop == 50 && observedSpot != ON){
        	printLine("Add 50, Fire For Effect!");
    	    lastY = lastY + 50 * yFactor;
        }
    	else if (addDrop == -50 && observedSpot != ON) {
        	printLine("Drop 50, Fire For Effect!");
    	    lastY = lastY + -50 * yFactor;
    	}
    	else {
    		printLine("Fire For Effect!");

    	}

      	fireForEffectGUI();
    } // end if-else

	
  }  // end ffe method

  public static void fireForEffectGUI(){

    if (runGUI == ON){
      	
      	Explosion exp = new Explosion();
        Explosion exp2 = new Explosion();
        Explosion exp3 = new Explosion();
        sim.add(exp, lastX - 30, lastY - 30); // adjust for size of explosion
        sim.add(exp2, lastX-20, lastY - 10);
        sim.add(exp3, lastX-30, lastY-20);
        exp.makeFire(4000);
        exp2.makeFire(3500);
        exp3.makeFire(3700);
        while (exp.inASimulation()) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException ie) {
            ie.printStackTrace();
          }
        }
    } // end if (runGUI=on)
  	
  } // end fireForEffect method
  
  public static void printCallForFireInformation() {

    if (createTestCases) {

        writeLine("");
        writeLine("  <test-case description=\"" + "; actualRange = " + actualRange + "; estimatedRange = " + estimatedRange + "; observedDeviation = " + observedDeviation + "; why = " + description + "\">");
        writeLine("    <input>");

        writeLine("      <entry>" + actualRange + "</entry>");         
        writeLine("      <entry>" + estimatedRange + "</entry>");
        writeLine("      <entry>" + observedDeviation + "</entry>");         

        writeLine("    </input>");

        
        writeLine("    <output>");
        
        
//        writeLine("      <entry>" + "You observe enemy T-72 and troops in the open at grid: GT85429399." + "</entry>");
//        writeLine("      <entry></entry>");
//      	
//      	writeLine("      <entry>" + "Black 1, this is Red 1! Adjust Fire, over!" + "</entry>");
//      	writeLine("      <entry>" + "Red 1, this is Black 1! Adjust Fire, out!" + "</entry>");
//      	writeLine("      <entry></entry>");
//      	
//      	writeLine("      <entry>" + "Grid: GT85429399, over!" + "</entry>");
//      	writeLine("      <entry>" + "Grid: GT85429399, out!" + "</entry>");
//      	writeLine("      <entry></entry>");
//      	
//      	writeLine("      <entry>" + "1 Tank and Troops in the open, over!" + "</entry>");
//      	writeLine("      <entry>" + "1 Tank and Troops in the open, out!" + "</entry>");
//      	writeLine("      <entry></entry>");
//      	
//      	writeLine("      <entry>" + "1 Round, HE, Target AB1001, over!" + "</entry>");
//      	writeLine("      <entry>" + "1 Round, HE, Target AB1001, out!" + "</entry>");
//      	writeLine("      <entry></entry>");
//
//    	writeLine("      <entry>" + "Direction 1680, over!" + "</entry>");
//    	writeLine("      <entry>" + "Direction 1680, out!" + "</entry>");	
//      	writeLine("      <entry></entry>");
//
//
//      	writeLine("      <entry>" + "Shot, over!" + "</entry>");
//      	writeLine("      <entry>" + "Shot, out!" + "</entry>");
//      	writeLine("      <entry></entry>");
//      	
//      	writeLine("      <entry>" + "Splash, over!" + "</entry>");
//      	writeLine("      <entry>" + "Splash, out!" + "</entry>");
//      	writeLine("      <entry></entry>");
    	
    }
    else {
    
        getProperties();

        if (!runningTests) {
        	
	       	printLine("You observe enemy T-72 and troops in the open at grid: GT85429399.");
	        printLine();
	      	
	      	printLine("Black 1, this is Red 1! Adjust Fire, over!");
	      	printLine("Red 1, this is Black 1! Adjust Fire, out!");
	      	printLine();
	      	
	      	printLine("Grid: GT85429399, over!");
	      	printLine("Grid: GT85429399, out!");
	      	printLine();
	      	
	      	printLine("1 Tank and Troops in the open, over!");
	      	printLine("1 Tank and Troops in the open, out!");
	      	printLine();
	      	
	      	printLine("1 Round, HE, Target AB1001, over!");
	      	printLine("1 Round, HE, Target AB1001, out!");
	      	printLine();
	
	    	printLine("Direction 1680, over!");
	    	printLine("Direction 1680, out!");	
	      	printLine();
	
	
	      	printLine("Shot, over!");
	      	printLine("Shot, out!");
	      	printLine();
	      	
	      	printLine("Splash, over!");
	      	printLine("Splash, out!");
	      	printLine();
        } // end if (!runningTests)
        
    } //end if-else
  }  // end printCallForFireInformation
  
  public static void printAdjustFireInformation(double deviation, int adjustFactor) {

  	// translate adjustFactor to addDrop
    if (adjustFactor<0){
    	addDrop = " Drop";
    }
    else {
    	addDrop = " Add";
    }
    
    // translate deviation to leftRight
    if (deviation<0){
    	shift = "Left";
    }
    else if (deviation>0){
    	shift = "Right";
    } else {
    	shift = "ON";
    }
    
    if (createTestCases) {

    	writeLine("      <entry>" + "Correction: " + "</entry>");

      	if (deviation == 0){

    	  	writeLine("      <entry>" + addDrop + " " + Math.abs(adjustFactor) + ", over!" + "</entry>");
    	  	writeLine("      <entry>" + addDrop + " " + Math.abs(adjustFactor) + ", out!" + "</entry>");
    	  	writeLine("      <entry></entry>");
      	} 
      	else {
    	  	writeLine("      <entry>" + shift + " " + Math.abs(deviation) + ", " + addDrop + " " + Math.abs(adjustFactor) + ", over!" + "</entry>");
    	  	writeLine("      <entry>" + shift + " " + Math.abs(deviation) + ", " + addDrop + " " + Math.abs(adjustFactor) + ", out!" + "</entry>");
    	  	writeLine("      <entry></entry>");
      	}

//      	writeLine("      <entry>" + "Shot, over!" + "</entry>");
//      	writeLine("      <entry>" + "Shot, out!" + "</entry>");
//      	writeLine("      <entry></entry>");
//      	
//      	writeLine("      <entry>" + "Splash, over!" + "</entry>");
//      	writeLine("      <entry>" + "Splash, out!" + "</entry>");
//      	writeLine("      <entry></entry>");

    	
    }
    else {
        printLine("Correction: ");

      	if (deviation == 0){

    	  	printLine(addDrop + " " + Math.abs(adjustFactor) + ", over!");
    	  	printLine(addDrop + " " + Math.abs(adjustFactor) + ", out!");
    	  	printLine();
      	} 
      	else {
    	  	printLine(shift + " " + Math.abs(deviation) + ", " + addDrop + " " + Math.abs(adjustFactor) + ", over!");
    	  	printLine(shift + " " + Math.abs(deviation) + ", " + addDrop + " " + Math.abs(adjustFactor) + ", out!");
    	  	printLine();
      	}

        getProperties();

        if (!runningTests) {
        	
	      	printLine("Shot, over!");
	      	printLine("Shot, out!");
	      	printLine();
	      	
	      	printLine("Splash, over!");
	      	printLine("Splash, out!");
	      	printLine();
        } // end if (!runningTests)

    }  // end if-else
  } // end printAdjustFireInformation method
  
  public static void printObservedRoundInformation() {

    // translate deviation to leftRight
    if (observedDeviation<0){
    	shift = "Left";
    }
    else if (observedDeviation>0){
    	shift = "Right";
    } else {
    	shift = "ON";
    }
  	// Display the observed round information
    // OVER = 1;
    // ON = 0;
    // SHORT = -1;

    if (observedSpot == OVER){
    	observedSpotText = " OVER";
    }  else if (observedSpot == ON){
    	observedSpotText = " ON";
    } else {
    	observedSpotText = " SHORT";
    } 

    if (createTestCases) {

        if (observedDeviation == 0){
            
        	  	writeLine("      <entry>" + "Observed round from the target is :" + "</entry>");
        	  	writeLine("      <entry>" + "ON Left-Right and " + observedSpotText + "</entry>");
        	  	writeLine("      <entry></entry>");
          	}
      	else {
    	  	writeLine("      <entry>" + "Observed round from the target is :" + "</entry>");
    	  	writeLine("      <entry>" + shift + " " + Math.abs(observedDeviation) +" mils and " + observedSpotText + "</entry>");
    	  	writeLine("      <entry></entry>");
      	}
    	
    }
    else {
        if (observedDeviation == 0){
            
        	  	printLine("Observed round from the target is :");
        	  	printLine("ON Left-Right and " + observedSpotText);
        	  	printLine();
          	}
      	else {
    	  	printLine("Observed round from the target is :");
    	  	printLine(shift + " " + Math.abs(observedDeviation) +" mils and " + observedSpotText);
    	  	printLine();
      	}
    
    }  // end if-else

  } // printObservedRoundInformation end method

  public static int randNumBtwn(int lower, int upper) {
    int range = upper - lower;
    return (int) Math.round(Math.random() * range + lower);
  }  // end randNumBtwn method


} // end class
