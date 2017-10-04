/*
 * Created on Apr 18, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.instructor.mann;

/**
 * @author DT8452
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CallForFireOneLoopSOLN extends CallForFire {

	static String grid = "";
	static double computedDeviation = 0;
	static int addDropFactor = 0;
	static double range = 0;
	static int observedDeviation = 0;
	static int observedSpot = 0;
	static int maxAdjustFactor = 0;
	static double OT;
	static int originalSpot = 0;
	static boolean bracket = false;
	static boolean rangeCorrect = false;
	
	public static void main(String[] args) {

		callForFireMain("DoNotCreateTestCases");
		
//		openReadFile();
//        openWriteFile();
//        
//        while(moreToRead())
//        {
//        	callForFireMain("createTestCases");
//
//        }
//
//       closeReadFile();
//       closeWriteFile(); 
//         
//       showMessage("All Done!");

	
	} // end main method

	  public static void callForFireMain(String createTestCases) {

	  	grid = laseTarget(ON);
	  	
//	  	callForFire(createTestCases);
	  	callForFireManualTests(2800, 3200, -10);
	  	
	  	range = getRange();
	  	
	   	observedDeviation = getObservedDeviation();
		observedSpot = getObservedSpot();
		
	 	adjustCorrectionFactors();
	 	
	 	// set the addDropFactor the the maximum for that range
	 	addDropFactor = maxAdjustFactor;
	 	
	 	// remember the orignal spot so that you can compare it
	 	// with subsequent spots to see if the rounds have bracketted
	 	originalSpot = observedSpot;
	  	
	    // Determine deviationCorrection
	 	computedDeviation = OT * (-observedDeviation);

        // initialize bracket to false....cuz we haven't bracketted yet
        bracket = false;
        
	 	// Determine if the range of the gun is correct
	 	rangeCorrect = observedSpot == ON;

	 	while (observedDeviation != 0 || !rangeCorrect){
	 	 		
			adjustFire(computedDeviation, (-observedSpot*addDropFactor));
			
		  	observedDeviation = getObservedDeviation();
		  	
			observedSpot = getObservedSpot();

			// Determine deviationCorrection
		 	computedDeviation = OT * (-observedDeviation);
		  	
		 	// if we bracketted the target with the previous rounds
		 	// we want to half the addDropFactor
		 	if (bracket){
			 	// Determine if the range on the gun is correct
			 	rangeCorrect = observedSpot == ON || addDropFactor <= 100;

				addDropFactor = addDropFactor/2;			
			}
		 	else {
			 	// if we bracketted the target this time
			 	// we want to half the addDropFactor and set bracket to true
		 		// 

		 		// Determine if the range on the gun is correct
			 	rangeCorrect = observedSpot == ON;

		 		if (originalSpot != observedSpot){
				
				 	// Determine if the range on the gun is correct
				 	rangeCorrect = observedSpot == ON || addDropFactor <= 100;

					bracket = true;
					addDropFactor = addDropFactor/2;			
				}  
		 	} // end if-else (bracket)
		 	
		} // end inner while
	  	  
	 	// Always fire for effect...but if you are ffe'ing
	 	// because you  
	 	if (Math.abs(addDropFactor) == 50) {
	 		fireForEffect(-observedSpot*addDropFactor);
	 	}
	 	else {
	 		fireForEffect(0);
	 	}
	  	
	  } // end callForFire method
	
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
//	    printLine("range = "+ range);
//	    printLine("User OT = "+ OT);
	  	
	  } // end adjustCorrectionFactors method
	
}  // end class CallForFireOneLoopSOLN
