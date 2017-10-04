/*
 * FDWSelectionHW.java
 *
 * Created on December 10, 2001, 12:51 PM
 */
package eecs.instructor.gossett;

import eecs.jago.instructor.gossett.IterationHW;

//import eecs.jago.instructor.gossett.*;


/**
 *
 * @author  Karl A. Gossett
 * @version 0.1
 */
public class FDWSelectionHW
    extends eecs.RobotGuiBase {

		int powerSetting = 0;

			static {
				sim = new eecs.simulation.FDWSelection();

				// The follow should be in every GUI
				//  (unless a known, specific reason to omit)
				frame = sim; // so that all the windows from eecs.GuiBase will

				// appear in the sim box
				sim.setVisible(true);
				robot = null;
			} // end static initializer

			public static void resetSimulation() {
				((IterationHW)sim).resetRoom();
				destroyRobot();
			}

			public static void pushButton(int times) {

				if (times > 10) {
					printError("You requested that I push the button " + times
							   + " times.  I don't think that's a good idea, so I am"
							   + " going to ignore that request.");

					return;
				}

				for (int pushed = 0; pushed < times; pushed++) {
					goForward(300);
					goBackward(300);
				}
			}

			public static int pressesForColor(int color) {

				int count = 0;

				if (color == BLACK) {
					count = 1;
				} else if (color == BLUE) {
					count = 2;
				} else if (color == MED_GRAY) {
					count = 3;
				} else if (color == GREEN) {
					count = 4;
				} else if (color == RED) {
					count = 5;
				} else {
					printError("I don't recognize the color you specified.");
				}

				return count;
			}

			public static void goStartToAirlock() {
				goForward(4000);
				pivotLeft(90);
				goForward(1500);
			}

			public static void goUntilLight() {
				goForward();

				do {

					// nothing
				} while (getReflectanceSensor() == OFF);

				halt();
			}
}