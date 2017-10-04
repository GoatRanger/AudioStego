/*
 * CodeSimulation.java
 *
 * Created on January 18, 2003, 9:51 AM
 */

package eecs.instructor.gossett;

import eecs.jago.instructor.gossett.LED;
/**
 *
 * @author  Karl A. Gossett
 */
public class CodeSimulation extends eecs.RobotGui {
    
    static LED leftLight;
    static LED middleLight;
    static LED rightLight;


    static {
        sim = new jago.SimBox();
        sim.setBackgroundImage("keypad.gif");
        setSimulationTitle("Keycode Simulator");
        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will

        // appear in the sim box
        sim.setVisible(true);
        robot = null;
        leftLight = new LED(20);
        middleLight = new LED(20);
        rightLight = new LED(20);
        sim.add(leftLight, 59, 33);
        sim.add(middleLight, 98, 33);
        sim.add(rightLight, 137, 33);
    }
    
    public static void setFirstLight(int color) {
        leftLight.setColor(color);

    }
    public static void setSecondLight(int color) {
            middleLight.setColor(color);

    }
    public static void setThirdLight(int color) {
        rightLight.setColor(color);

    }
}
