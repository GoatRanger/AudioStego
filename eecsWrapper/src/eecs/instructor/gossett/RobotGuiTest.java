/*
 * RobotGuiTest.java
 *
 * Created on January 11, 2003, 9:16 AM
 */

package eecs.instructor.gossett;
import javax.swing.*;
import java.util.ArrayList;

import jago.element.*;
/**
 *
 * @author  Karl A. Gossett
 */
public class RobotGuiTest extends eecs.RobotGui {
    
    protected static ArrayList<String> commands = new ArrayList<String>(1000);
    protected static boolean firstTest = true;

    /**
     * Changes the background of the simulation to the background image
     * specified by the filename.  Once the file is loaded, the simulation
     * window is resized to fit the new background image. Files must be
     * <code>jpg</code> or <code>gif</code> images.  If the file is not
     * found, the simulation will exit.
     * 
     * @param filename  The name of the file to use.  If it is not in the
     *        current directory, the filename should be a fully qualified
     *        name.
     */
    public static void setBackgroundImage(String filename) {
        commands.add(new String("sbi"));
        checkFile(filename);
        sim.setBackgroundImage(filename);
    }

    /**
     * Sets the background to the specified image, and tiles the image so that
     * it fills the current simulation window.  Using this method ensures
     * that the image fills the simulation, as opposed to
     * <code>setBackgroundImage </code> which ensures the simulation fits the
     * image.  If the file cannot be found, the simulation will exit.
     * 
     * @param filename  The name of the file to use.
     */
    public static void setBackgroundTile(String filename) {
        commands.add(new String("sbi"));
        checkFile(filename);
        sim.setBackgroundTile(filename);
    }

    /**
     * Gets the value from the HIT sensor, if one is connected.  The readings
     * returned represent the side of the sensor that is receiving the most
     * light, with negative values to the left and positive values to the
     * right.
     * 
     * @return Empty
     */
    public static int getDifferentialSensor() {
        commands.add(new String("sbi"));
        checkRobot("getHITSensor");

        int value = robot.getSensorInput(LegoRobot.RCX_3);

        if (value == RobotSensors.NOT_CONNECTED) {
            showError("Tried to get the value of a HIT sensor, but this "
                      + "robot does not have a HIT sensor.");
            System.exit(0);
        }

        return value;
    }

    /**
     * Gets the reading from the light sensor.  The value returned is roughly
     * equivalent to the percentage of light that the sensor detects.  The
     * values are determined by the light sensor, but usually fall between 29
     * and 50.  If the simulation is running in idealistic mode, the values
     * will correspond to color constants from {@link
     * jago.element.ReflectiveColors}.
     * 
     * @return The current reading.
     */
    public static int getReflectanceSensor() {
        commands.add(new String("sbi"));
        checkRobot("getReflectanceSensor");

        // In the robot in this wrapper the light sensor is on RCX Input 1
        int value = robot.getSensorInput(LegoRobot.RCX_1);

        if (value == RobotSensors.NOT_CONNECTED) {
            showError("Tried to get the value of a light sensor, but this "
                      + "robot does not have a light sensor.");
            System.exit(0);
        }

        if (value == SensorState.NOT_READY) {
            showError("The sensor did not have time to properly initialize.");
        }

        // Although totally redundant, the following if is retained because
        // some implementations have produced different values when in
        // the idealistic mode.
        if (realism == IDEALISTIC) {

            return value;
        }

        return value;
    }

    /**
     * Sets the power of the robot to the specified level.
     * 
     * @param power  The power setting, between 1 and 10.
     */
    public static void setPower(int power) {
        commands.add(new String("sbi"));
        checkRobot("setPower");

        if (power < 0 || power > 10) {
            showError("Tried to setPower with power level " + power + ".\n"
                      + "Must be between 1 and 10.");
            System.exit(0);
        }

        robot.setPower(power);
    }

    // wrappers for robot commands

    /**
     * Sets the title of the simulation window to the specified string.
     * 
     * @param name   The new title to use.
     */
    public static void setSimulationTitle(String name) {
        commands.add(new String("sbi"));

        if (realism == REALISTIC) {
            sim.setSimTitle(title + " - REALISTIC Mode");
        } else {
            sim.setSimTitle(title + " - IDEALISTIC Mode");
        }

        title = name;
    }

    /**
     * Gets the value from the touch sensor, as either <code>ON</code> or
     * <code>OFF</code>.
     * 
     * @return the current reading from the touch sensor.
     */
    public static int getTouchSensor() {
        commands.add(new String("sbi"));
        checkRobot("getTouchSensor");

        // in this wrapper, the touch sensor is on RCX 2
        int value = robot.getSensorInput(LegoRobot.RCX_2);

        if (value == RobotSensors.NOT_CONNECTED) {
            showError("Tried to get the value of a touch sensor, but this "
                      + "robot does not have a touch sensor.");
            System.exit(0);
        }

        return value;
    }

    /**
     * Undocumented
     */
    public static void clearOutput() {
        commands.add(new String("sbi"));
        sim.clearOutput();
    }

    /**
     * Adds a robot to the simulation at the specified coordinates and initial
     * heading.  This method creates a <code>LegoRobot</code>, which by
     * default has a standard Lego light sensor (see {@link
     * jago.element.RobotSensors#LIGHT}) and a standard bumper (see
     * {@link jago.element.RobotSensors#TOUCH}).  The light sensor is
     * connected to the RCX input 1, (RCX_1) and the touch sensor is
     * connected to the RCX input 2 (RCX_2).
     * 
     * @param x   The x coordinate, with the upper left being 0.
     * @param y   The y coordinate, with the upper left being 0.
     * @param angle  The initial direction; straight up is a heading of 0.
     */
    public static void createRobot(int x, int y, int angle) {
        commands.add(new String("sbi"));
        checkAngle("createRobot", angle);

        if (robot != null) {
            showError("Robot already exists.  Cannot create a new one.");
            System.exit(0);
        }

        robot = new LegoRobot();
        robot.addSensor(LegoRobot.RCX_2, RobotSensors.TOUCH);
        robot.addSensor(LegoRobot.RCX_1, RobotSensors.LIGHT);
        robot.setPower(5);
        robot.setInitialHeading(angle);

        if (realism != IDEALISTIC) {
            robot.setRealismLevel(realism);
        }

        sim.add(robot, x, y);
    }

    /**
     * Removes the robot from the simulation
     */
    public static void destroyRobot() {
        commands.add(new String("sbi"));

        if (robot != null) {
            sim.remove(robot);
            robot = null;
        }
    }

    /**
     * Undocumented
     * 
     * @param on Empty
     */
    public static void drawTrails(boolean on) {
        commands.add(new String("sbi"));
        checkRobot("drawTrails");
        robot.drawTrails(on);
    }

    /**
     * Causes the robot to begin driving backward.  The robot will continue to
     * move until another command is issued.
     */
    public static void goBackward() {
        commands.add(new String("sbi"));
        checkRobot("goBackward");
        robot.goBackward();
    }

    /**
     * Causes the robot to move backward for the specified amount of time. The
     * amount of time is actually measured in simulation time, which may not
     * mirror real time.
     * 
     * @param ms  The amount of time that the robot should move.
     */
    public static void goBackward(int ms) {
        commands.add(new String("sbi"));
        checkRobot("goBackward");
        checkTime("goBackward", ms);
        robot.goBackward(ms);
    }

    /**
     * Causes the robot's motors to turn on in the forward direction.  Once
     * this command is issued, the robot will continue to move forward until
     * another command is issued.
     */
    public static void goForward() {
        commands.add(new String("sbi"));
        checkRobot("goForward");
        robot.goForward();
    }

    /**
     * Causes the robot to move forward for the specified amount of time. The
     * time specified is relative to the amount of time that passes in the
     * simulation, which may not exactly mirror real-world time.
     * 
     * @param ms  How long the robot should drive forward, in milliseconds
     */
    public static void goForward(int ms) {
        commands.add(new String("sbi"));
        checkRobot("goForward");
        checkTime("goForward", ms);
        robot.goForward(ms);
    }

    /**
     * Undocumented
     */
    public static void halt() {
        commands.add(new String("sbi"));
        checkRobot("halt");
        robot.halt();
    }

    /**
     * Causes the robot to wait for the specified amount of time.
     * 
     * @param ms The number of milliseconds to wait.
     */
    public static void pause(int ms) {
        commands.add(new String("sbi"));
        checkRobot("pause");
        checkTime("pause", ms);

        /* Previous iteration used the robot wait, but there really isn't
         * any reason to use it, and waiting here will ensure that the time
         * waited is more accurate.  If returning to the robot's waitFor
         * instead, check it for accuracy--sometimes didn't wait the
         * appropriate amount of time if called publicly.
         */
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }

    /**
     * Undocumented
     * 
     * @param degrees Empty
     */
    public static void pivotLeft(int degrees) {
        commands.add(new String("sbi"));
        checkRobot("pivotLeft");
        checkAngle("pivotLeft", degrees);
        robot.pivotLeft(degrees);
    }

    /**
     * Undocumented
     * 
     * @param degrees Empty
     */
    public static void pivotRight(int degrees) {
        commands.add(new String("sbi"));
        checkRobot("pivotRight");
        checkAngle("pivotRight", degrees);
        robot.pivotRight(degrees);
    }

    /**
     * Causes the simulation to run in a mode that isn't idealistic, but will
     * be consistent between runs.  For robots, this means that the robot
     * might drift (not drive straight), but it will drift exactly the same
     * every time the program is run in this mode.
     */
    public static void runFixedRealisticSim() {
        commands.add(new String("sbi"));
        realism = FIXED_REALISTIC;
        sim.setRealismLevel(realism);
        sim.setSimTitle(title + " - Fixed-REALISTIC Mode");
    }

    /**
     * Causes the simulation to run in a mode that represents the ideal
     * function of all elements.  Although the exact operation is determined
     * by the individual elements, this generally means that the behavior is
     * one that would allow the most accurate use of the component.
     */
    public static void runIdealisticSim() {
        commands.add(new String("sbi"));
        realism = IDEALISTIC;
        sim.setRealismLevel(realism);

        //if (robot != null) {
        //    robot.setRealismLevel(realism);
        //}
        sim.setSimTitle(title + " - IDEALISTIC Mode");
    }

    /**
     * Causes the simulation to run in a mode that more accurately represents
     * how the elements would act in the real world.  The implementation
     * determines what impact this command actually has, but in general it
     * will cause some level of stochiastic behavior.
     */
    public static void runRealisticSim() {
        commands.add(new String("sbi"));
        realism = REALISTIC;
        sim.setRealismLevel(realism);

        //if (robot != null) {
        //    robot.setRealismLevel(realism);
        //}
        sim.setSimTitle(title + " - REALISTIC Mode");
    }

    /**
     * This method is included to be consistent with the Realism interface.
     * The preferred method in the EECS wrapper is to use the
     * setIdealisticSim and setRealisticSim methods.
     * 
     * @param level     <code>REALISTIC</code> or <code>IDEALISTIC</code>
     */
    public void setRealismLevel(int level) {
        commands.add(new String("sbi"));

        if (level == REALISTIC) {
            runRealisticSim();
        } else if (level == IDEALISTIC) {
            runIdealisticSim();
        } else {
            showMessage("Realism must be set to IDEALISTIC or REALISTIC");
        }
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public int getRealismLevel() {
        commands.add(new String("sbi"));

        return realism;
    }

    /**
     * Undocumented
     * 
     * @param msg Empty
     */
    public static void print(String msg) {
        commands.add(new String("sbi"));
        sim.print(msg);
    }

    /**
     * Undocumented
     * 
     * @param number Empty
     */
    public static void print(int number) {
        commands.add(new String("sbi"));
        print("" + number);
    }

    /**
     * Undocumented
     * 
     * @param number Empty
     */
    public static void print(double number) {
        commands.add(new String("sbi"));
        print("" + number);
    }

    /**
     * Undocumented
     * 
     * @param b Empty
     */
    public static void print(boolean b) {
        commands.add(new String("sbi"));
        print("" + b);
    }

    /**
     * Undocumented
     * 
     * @param ch Empty
     */
    public static void print(char ch) {
        commands.add(new String("sbi"));
        print("" + ch);
    }

    /**
     * Undocumented
     * 
     * @param code Empty
     */
    public static void printCode(String code) {
        commands.add(new String("sbi"));
        sim.printCode(code);
    }

    /**
     * Undocumented
     * 
     * @param msg Empty
     */
    public static void printError(String msg) {
        commands.add(new String("sbi"));
        sim.printError(msg);
    }

    /**
     * Undocumented
     * 
     * @param number Empty
     */
    public static void printError(int number) {
        commands.add(new String("sbi"));
        printError("" + number);
    }

    /**
     * Undocumented
     * 
     * @param number Empty
     */
    public static void printError(double number) {
        commands.add(new String("sbi"));
        printError("" + number);
    }

    /**
     * Undocumented
     * 
     * @param b Empty
     */
    public static void printError(boolean b) {
        commands.add(new String("sbi"));
        printError("" + b);
    }

    /**
     * Undocumented
     * 
     * @param ch Empty
     */
    public static void printError(char ch) {
        printError("" + ch);
    }

    /**
     * Undocumented
     */
    public static void printError() {
        commands.add(new String("sbi"));
        printError("");
    }

    /**
     * Undocumented
     * 
     * @param msg Empty
     */
    public static void printLine(String msg) {
        commands.add(new String("sbi"));
        sim.printLine(msg);
    }

    /**
     * Undocumented
     * 
     * @param number Empty
     */
    public static void printLine(int number) {
        printLine("" + number);
    }

    /**
     * Undocumented
     * 
     * @param number Empty
     */
    public static void printLine(double number) {
        commands.add(new String("sbi"));
        printLine("" + number);
    }

    /**
     * Undocumented
     * 
     * @param b Empty
     */
    public static void printLine(boolean b) {
        commands.add(new String("sbi"));
        printLine("" + b);
    }

    /**
     * Undocumented
     * 
     * @param ch Empty
     */
    public static void printLine(char ch) {
        commands.add(new String("sbi"));
        printLine("" + ch);
    }

    /**
     * Undocumented
     */
    public static void printLine() {
        commands.add(new String("sbi"));
        printLine("");
    }

    /**
     * Undocumented
     * 
     * @param msg Empty
     */
    public static void showError(String msg) {
        commands.add(new String("sbi"));
        sim.stop();
        JOptionPane.showMessageDialog(frame, msg, "Error", 
                                      JOptionPane.ERROR_MESSAGE);
        sim.start();
    }

    /**
     * Undocumented
     * 
     * @param msg Empty
     */
    public static void showMessage(String msg) {
        commands.add(new String("sbi"));

        //sim.stop();
        JOptionPane.showMessageDialog(frame, msg);

        //sim.start();
    }

    protected static void testMe() {
        // If started testing, this method shouldn't do anything
        if (!firstTest) return;
        firstTest = false;
        for (int i=0; i<commands.size(); i++) {
            System.out.println("Command " + i +":" + commands.get(i).toString());
        }
    }
        
    
}
