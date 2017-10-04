import eecs.simulation.IT105Project042;


/*
 * Created on Apr 30, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
/**
 * @author Karl
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class FinalSolution extends IT105Project042 {

	public static void main(String[] args) {
		loadDoubleRandomObstacle();
		//checkHighScores();
		printLine("Running");
		setPower(100);
		printLine("Reading: " + getReflectanceSensor());
		if (getReflectanceSensor() == 49) {
			bypassConcertina();
			setPower(100);
			bypassFire();
		} else {
			printLine("Fire!");
			bypassFire();
			printLine("Crossed it!");
			goForward();
			while (getReflectanceSensor() != 49) {}
			bypassConcertina();
			setPower(100);
		}

		gotoPoint(getMyXPosition()+30,getMyYPosition()-50);
		followRoad();
		gotoAA();
	}
	
	private static void bypassFire() {
		gotoPoint(getMyXPosition(),getMyYPosition()-50);
		printLine("start left");
		pivotLeft(60);
		boolean decreasing = true;
		printLine("look for change");
		goForward();
		int startReading = getTemperatureSensor();
		int current = startReading;
		while (decreasing && (current=getTemperatureSensor()) > 90) {
			if (current>startReading) decreasing = false;
			if (getTouchSensor()==ON) {
				goBackward(500);
				decreasing = false;
			}
		}
		printLine("get out!");
		if (decreasing) {
			pivotRight(60);
		} else {
			pivotRight(120);
			decreasing = true;
			goForward();
			while ((current=getTemperatureSensor()) > 90) {
				
			}
			pivotLeft(60);
		}
		goForward();
		while (getReflectanceSensor()==48) {}
		goForward(300);
	}

	/**
	 * 
	 */
	private static void bypassConcertina() {
	  printLine("Concertina!");
			while (getReflectanceSensor() == 49) {
				while (getDualLightSensor() < -2) {
					pivotLeft(5);
				}
				while (getDualLightSensor() > 2) {
					pivotRight(5);
				}
				goForward();
			}
			
			goForward(500);
	}

	/**
	 * 
	 */
	private static void gotoAA() {
		String aa = getAssemblyAreaName();
		if (aa.equals("AA Eagle")) {
			gotoPoint(600,550);
		}
		if (aa.equals("AA River")) {
			gotoPoint(700,550);
		}
		if (aa.equals("AA Cobra")) {
			gotoPoint(800,550);
		}
		transmitMissionComplete();
	}

	/**
	 * 
	 */
	private static void followRoad() {
		goForward();
		while (getReflectanceSensor()>37) {}
		halt();
		while (getMyYPosition() < 350) {
			while (getReflectanceSensor() < 37) {
				pivotRight(3);
			}
			while (getReflectanceSensor() > 45) {
				pivotLeft(3);
			}
			goForward();
		}
		halt();
	}
}
