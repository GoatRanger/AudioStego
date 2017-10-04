/*
 * Created on Apr 16, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.instructor.mann;

/**
 * @author DT8452 To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CallForFireSOLN extends CallForFire {
  
	static String grid = "";
	static double computedDeviation = 0;
	static int addDropFactor = 0;
	static int range = 0;
	static int observedDeviation = 0;
	static int observedSpot = 0;
	static int maxAdjustFactor = 0;
	static double OT;
	static int newObservedSpot = 0;
	static int bracket = 0;
	
	public static void main(String[] args) {
//    fire(480, 200);
//    adjustFire(100, 400);
//    adjustFire(0, -200);
//    adjustFire(0, -100);
//    printLine("Fire for Effect.");
//    ffe();
  	
  	grid = laseTarget(OFF);
  	
  	callForFire(grid);
  	
  	printLine("actualRange = "+actualRange);
  	printLine("estimatedRange = "+estimatedRange);
	printLine("test");
  	
  	range = getRange();
  	
   	observedDeviation = getObservedDeviation();
	observedSpot = getObservedSpot();
	
 	adjustCorrectionFactors();
 	
 	addDropFactor = maxAdjustFactor;
 	
 	// Determine if the rounds
 	newObservedSpot = observedSpot;
 	bracket = 0;
  	
    // Determine deviationCorrection
 	computedDeviation = OT * (-observedDeviation);

    // continue to drop rounds while we still have hit the enemy...hitting the enemy is defined as:
 	// left-right deviation not equal to 0 OR we are still dropping rounds either short or long
 	// this is the case when our observed spot isn't on and we have not bracketted to 50 meters
// 	while((observedDeviation != 0) || (observedSpot != ON && addDropFactor != 50)){
// 	while(observedDeviation != 0 && observedSpot != ON && addDropFactor != 50){
 	while(observedDeviation != 0 && observedSpot != ON && addDropFactor >= 100){

      //	  while (observedDeviation != 0 && !(observedSpot == ON || addDropFactor == 50)){
//  	  while (observedDeviation != 0 || (observedSpot != ON && addDropFactor != 50 && bracket !=1)){	  	
// 		while ((observedSpot != ON && addDropFactor != 50 && bracket !=1)){	  	
 		while ((observedSpot != ON && addDropFactor >= 100 && bracket !=1)){	  	
 	 		
		adjustFire(computedDeviation, (-observedSpot*addDropFactor));
		
	  	observedDeviation = getObservedDeviation();
	  	
		observedSpot = getObservedSpot();

		// Determine deviationCorrection
	 	computedDeviation = OT * (-observedDeviation);
	  	
		
		if (newObservedSpot == observedSpot){
			
		}
		else {
			bracket = 1;
			addDropFactor = addDropFactor/2;			
		}
		
	  } // end inner while
  	  
		adjustFire(computedDeviation, (-observedSpot*addDropFactor));
		
	  	observedDeviation = getObservedDeviation();
	  	
		observedSpot = getObservedSpot();

		// Determine deviationCorrection
	 	computedDeviation = OT * (-observedDeviation);

		addDropFactor = addDropFactor/2;			

  	  
	} // end outer while
  	
  	  fireForEffect(0);

    }
  public static void adjustCorrectionFactors() {
  	
    // determine maxAdjustForRange
    if (range<=1000){
    	maxAdjustFactor = 100;
    } else if (range<=2000){
    	maxAdjustFactor = 200;
    } else {
    	maxAdjustFactor = 400;
    }
    
    // determine OT factor
    if (range<1000){
    	OT = range/1000;
    } else if (range<=1499){
    	OT = 1;
    } else if (range<=2499){
    	OT = 2;
    } else if (range<=3499){
    	OT = 3;
    } else if (range<=4499){
    	OT = 4;
    } else {
    	OT = 5;
    }
  	
  } // end adjustCorrectionFactors method
}
