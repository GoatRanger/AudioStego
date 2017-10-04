/* Copyright (C) 2002  USMA
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
/*
 * RobotDemo.java
 *
 * Created on December 16, 2002, 4:39 PM
 */
package eecs.applet;

import jago.*;

import jago.element.*;

import java.io.*;

import javax.swing.*;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public abstract class AppletBase extends javax.swing.JApplet
    implements Runnable, SensorState, ReflectiveColors, Realism
{
    public final int BUTTON1 = 0;
    public final int BUTTON2 = 1;
    public final int BUTTON3 = 2;

    // replaces the old ResponseBox stuff
    public final int CANCEL = -1;
    protected final String DEFAULT_IMAGE = "white.gif";
    protected final String DEFAULT_TITLE =
        "IT105/155 Lego Robot Simulation";
    protected SimEngine frame;
    protected SimEngine sim;
    protected StandardRobot robot;

    // gui objects
    protected String title = DEFAULT_TITLE;
    protected Thread runThread = null;
    protected int realism = IDEALISTIC;

    // File stuff for reading
    // private file objects
    private BufferedReader fileReader = null;

    // File stuff for writing
    private PrintWriter fileWriter = null;
    private String lookAhead = null;

    /**
     * Changes the background of the simulation to the background
     * image specified by the filename.  Once the file is loaded,
     * the simulation window is resized to fit the new background
     * image. Files must be <code>jpg</code> or <code>gif</code>
     * images.  If the file is not found, the simulation will exit.
     *
     * @param filename The name of the file to use.  If it is not in
     *        the current directory, the filename should be a fully
     *        qualified name.
     */
    public void setBackgroundImage(String filename)
    {
        java.awt.Image icon = ImageLoader.getImage(this, filename);
        sim.setBackgroundImage(icon);
    }

    /**
     * Sets the background to the specified image, and tiles the
     * image so that it fills the current simulation window.  Using
     * this method ensures that the image fills the simulation, as
     * opposed to <code>setBackgroundImage </code> which ensures the
     * simulation fits the image.  If the file cannot be found, the
     * simulation will exit.
     *
     * @param filename The name of the file to use.
     */
    public void setBackgroundTile(String filename)
    {
        java.awt.Image icon = ImageLoader.getImage(this, filename);
        sim.setBackgroundTile(icon);
    }

    /**
     * Undocumented
     *
     * @param prompt Empty
     *
     * @return Empty
     */
    public double getDouble(String prompt)
    {
        double value = 0.0;

        while (true)
        {
            String str = getString(prompt);

            try
            {
                value = Double.parseDouble(str);

                break;
            }
            catch (NumberFormatException e)
            {
                showError("Not a valid double.  Please try again.");
            }
        }

        return value;
    }

    /**
     * Gets the value from the HIT sensor, if one is connected.  The
     * readings returned represent the side of the sensor that is
     * receiving the most light, with negative values to the left
     * and positive values to the right.
     *
     * @return Empty
     */
    public int getHITSensor()
    {
        checkRobot("getHITSensor");

        int value = robot.getSensorInput(LegoRobot.RCX_3);

        if (value == RobotSensors.NOT_CONNECTED)
        {
            showError(
                "Tried to get the value of a HIT sensor, but this " +
                "robot does not have a HIT sensor.");
            System.exit(0);
        }

        return value;
    }

    /**
     * Undocumented
     *
     * @param prompt Empty
     *
     * @return Empty
     */
    public int getInt(String prompt)
    {
        int value = 0;

        while (true)
        {
            String str = getString(prompt);

            try
            {
                value = Integer.parseInt(str);

                break;
            }
            catch (NumberFormatException e)
            {
                showError("Not a valid integer.  Please try again.");
            }
        }

        return value;
    }

    /**
     * Gets the reading from the light sensor.  The value returned is
     * roughly equivalent to the percentage of light that the sensor
     * detects.  The values are determined by the light sensor, but
     * usually fall between 29 and 50.  If the simulation is running
     * in idealistic mode, the values will correspond to color
     * constants from {@link jago.element.ReflectiveColors}.
     *
     * @return The current reading.
     */
    public int getReflectanceSensor()
    {
        checkRobot("getReflectanceSensor");

        // In the robot in this wrapper the light sensor is on RCX Input 1
        int value = robot.getSensorInput(LegoRobot.RCX_1);

        if (value == RobotSensors.NOT_CONNECTED)
        {
            showError(
                "Tried to get the value of a light sensor, but this " +
                "robot does not have a light sensor.");
            System.exit(0);
        }

        if (value == SensorState.NOT_READY)
        {
            showError(
                "The sensor did not have time to properly initialize.");
        }

        // Although totally redundant, the following if is retained because
        // some implementations have produced different values when in
        // the idealistic mode.
        if (realism == IDEALISTIC)
        {
            return value;
        }

        return value;
    }

    /** Gets the value of the light sensor.
     * @deprecated
     * @return The reading from the sensor.
     */
    public int getLightSensor() {
      return getReflectanceSensor();
    }
    /**
     * Sets the power of the robot to the specified level.
     *
     * @param power The power setting, between 1 and 10.
     */
    public void setPower(int power)
    {
        checkRobot("setPower");

        if ((power < 0) || (power > 10))
        {
            showError("Tried to setPower with power level " + power +
                ".\n" + "Must be between 1 and 10.");
            System.exit(0);
        }

        robot.setPower(power);
    }

    /**
     * This method is included to be consistent with the Realism
     * interface. The preferred method in the EECS wrapper is to use
     * the setIdealisticSim and setRealisticSim methods.
     *
     * @param level <code>REALISTIC</code> or <code>IDEALISTIC</code>
     */
    public void setRealismLevel(int level)
    {
        if (level == REALISTIC)
        {
            runRealisticSim();
        }
        else if (level == IDEALISTIC)
        {
            runIdealisticSim();
        }
        else
        {
            showMessage(
                "Realism must be set to IDEALISTIC or REALISTIC");
        }
    }

    /**
     * Undocumented
     *
     * @return Empty
     */
    public int getRealismLevel()
    {
        return realism;
    }

    // wrappers for robot commands

    /**
     * Sets the title of the simulation window to the specified
     * string.
     *
     * @param name The new title to use.
     */
    public void setSimulationTitle(String name)
    {
        title = name;
    }

    // replaces old InputBox stuff

    /**
     * Undocumented
     *
     * @param prompt Empty
     *
     * @return Empty
     */
    public String getString(String prompt)
    {
        /* Simplest to just use the following code.
           String str = JOptionPane.showInputDialog(frame,prompt);
           return str==null ? "" : str;
         * I'm doing the fancier stuff below just to get rid of the Cancel button.
         */
        String ok = "OK";
        JTextField field = new JTextField();
        int value =
            JOptionPane.showOptionDialog(frame,
                new Object[] { prompt, field // message
            }, "", // title,                                         
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, // icon                                         
                new String[] { ok // default item
            }, ok);

        switch (value)
        {
            case JOptionPane.CLOSED_OPTION:
                return "";

            default:
                return field.getText();
        }
    }

    /**
     * Gets the value from the touch sensor, as either
     * <code>ON</code> or <code>OFF</code>.
     *
     * @return the current reading from the touch sensor.
     */
    public int getTouchSensor()
    {
        checkRobot("getTouchSensor");

        // in this wrapper, the touch sensor is on RCX 2
        int value = robot.getSensorInput(LegoRobot.RCX_2);

        if (value == RobotSensors.NOT_CONNECTED)
        {
            showError(
                "Tried to get the value of a touch sensor, but this " +
                "robot does not have a touch sensor.");
            System.exit(0);
        }

        return value;
    }

    /**
     * Undocumented
     *
     * @param width Empty
     * @param digits Empty
     * @param num Empty
     *
     * @return Empty
     */
    public String centerAlign(int width, int digits, double num)
    {
        return centerAlign(width, showDouble(digits, num));
    }

    /**
     * Undocumented
     *
     * @param width Empty
     * @param num Empty
     *
     * @return Empty
     */
    public String centerAlign(int width, int num)
    {
        return centerAlign(width, "" + num);
    }

    /**
     * Undocumented
     *
     * @param width Empty
     * @param str Empty
     *
     * @return Empty
     */
    public String centerAlign(int width, String str)
    {
        if (str == null)
        {
            return spaces(width);
        }
        else
        {
            int sp = width - str.length();

            return spaces((sp + 1) / 2) + str + spaces(sp / 2);
        }
    }

    /**
     * Undocumented
     *
     * @param prompt Empty
     * @param button1 Empty
     * @param button2 Empty
     *
     * @return Empty
     */
    public int chooseButton(String prompt, String button1,
        String button2)
    {
        return chooseButton(prompt, new String[] { button1, button2 });
    }

    /**
     * Undocumented
     *
     * @param prompt Empty
     * @param button1 Empty
     * @param button2 Empty
     * @param button3 Empty
     *
     * @return Empty
     */
    public int chooseButton(String prompt, String button1,
        String button2, String button3)
    {
        return chooseButton(prompt,
            new String[] { button1, button2, button3 });
    }

    /**
     * Undocumented
     *
     * @param prompt Empty
     * @param buttons Empty
     *
     * @return Empty
     */
    public int chooseButton(String prompt, String[] buttons)
    {
        // assumes: buttons != null
        int value =
            JOptionPane.showOptionDialog(frame, prompt, // message
                "", // title
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, // icon
                buttons,
                (buttons.length > 0) ? buttons[0]
                                     : null // default button
            );

        switch (value)
        {
            case JOptionPane.CLOSED_OPTION:
                return CANCEL;

            default:
                return value;
        }
    }
     // end method chooseButton

    /**
     * Undocumented
     *
     * @param prompt Empty
     * @param items Empty
     *
     * @return Empty
     */
    public int chooseFromList(String prompt, String[] items)
    {
        // assumes: items != null && items.length > 0
        String ok = "OK";
        JList list = new JList(items);
        list.setSelectedIndex(0); // set default choice
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane pane = new JScrollPane(list);
        int value =
            JOptionPane.showOptionDialog(frame,
                new Object[] { prompt, pane // message
            }, "", // title,                                         
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, // icon                                         
                new String[] { ok // default item
            }, ok);

        switch (value)
        {
            case JOptionPane.CLOSED_OPTION:
                return CANCEL;

            default:
                return list.getSelectedIndex();
        }
    }
     // end method chooseFromList

    /**
     * Undocumented
     */
    public void clearOutput()
    {
        JApplet receiver = null;
        receiver = (JApplet) getAppletContext().getApplet("output");

        if ((receiver != null) && receiver instanceof OutputApplet)
        {
            ((OutputApplet) receiver).clearOutput();
        }
    }

    /**
     * Undocumented
     */
    public void closeReadFile()
    {
        if (fileReader != null)
        {
            try
            {
                fileReader.close();
            }
            catch (Exception e)
            {
                // ignore the exception
            }

            fileReader = null;
            lookAhead = null;
        }
    }
     // end method closeReadFile

    /**
     * Undocumented
     */
    public void closeWriteFile()
    {
        if (fileWriter != null)
        {
            try
            {
                fileWriter.close();
            }
            catch (Exception e)
            {
                // ignore the exception
            }

            fileWriter = null;
        }
    }
     // end method closeWriteFile

    /**
     * Adds a robot to the simulation at the specified coordinates
     * and initial heading.  This method creates a
     * <code>LegoRobot</code>, which by default has a standard Lego
     * light sensor (see {@link
     * jago.element.RobotSensors#LIGHT}) and a standard bumper
     * (see {@link jago.element.RobotSensors#TOUCH}).  The
     * light sensor is connected to the RCX input 1, (RCX_1) and the
     * touch sensor is connected to the RCX input 2 (RCX_2).
     *
     * @param x The x coordinate, with the upper left being 0.
     * @param y The y coordinate, with the upper left being 0.
     * @param angle The initial direction; straight up is a heading
     *        of 0.
     */
    public void createRobot(int x, int y, int angle)
    {
        checkAngle("createRobot", angle);

        if (robot != null)
        {
            showError(
                "Robot already exists.  Cannot create a new one.");
            System.exit(0);
        }

        robot = new LegoRobot();
        robot.addSensor(LegoRobot.RCX_2, RobotSensors.TOUCH);
        robot.addSensor(LegoRobot.RCX_1, RobotSensors.LIGHT);
        robot.setPower(5);
        robot.setInitialHeading(angle);

        if (realism != IDEALISTIC)
        {
            robot.setRealismLevel(realism);
        }

        sim.add(robot, x, y);
    }

    /**
     * Removes the robot from the simulation
     */
    public void destroyRobot()
    {
        if (robot != null)
        {
            sim.remove(robot);
            robot = null;
        }
    }

    /**
     * Undocumented
     *
     * @param on Empty
     */
    public void drawTrails(boolean on)
    {
        checkRobot("drawTrails");
        robot.drawTrails(on);
    }

    /**
     * Causes the robot to begin driving backward.  The robot will
     * continue to move until another command is issued.
     */
    public void goBackward()
    {
        checkRobot("goBackward");
        robot.goBackward();
    }

    /**
     * Causes the robot to move backward for the specified amount of
     * time. The amount of time is actually measured in simulation
     * time, which may not mirror real time.
     *
     * @param ms The amount of time that the robot should move.
     */
    public void goBackward(int ms)
    {
        checkRobot("goBackward");
        checkTime("goBackward", ms);
        robot.goBackward(ms);
    }

    /**
     * Causes the robot's motors to turn on in the forward direction.
     * Once this command is issued, the robot will continue to move
     * forward until another command is issued.
     */
    public void goForward()
    {
        checkRobot("goForward");
        robot.goForward();
    }

    /**
     * Causes the robot to move forward for the specified amount of
     * time. The time specified is relative to the amount of time
     * that passes in the simulation, which may not exactly mirror
     * real-world time.
     *
     * @param ms How long the robot should drive forward, in
     *        milliseconds
     */
    public void goForward(int ms)
    {
        checkRobot("goForward");
        checkTime("goForward", ms);
        robot.goForward(ms);
    }

    /**
     * Undocumented
     */
    public void halt()
    {
        checkRobot("halt");
        robot.halt();
    }

    /**
     * Undocumented
     */
    public void init()
    {
        getContentPane().add(sim = new SimEngine());
        sim.setPreferredSize(new java.awt.Dimension(500, 400));

        java.awt.Image icon =
            ImageLoader.getImage(this, "/jago/images/white.gif");
        frame = sim;

        if (icon != null)
        {
            sim.setBackgroundTile(icon);
            repaint();
        }
    }

    /**
     * Undocumented
     *
     * @param width Empty
     * @param digits Empty
     * @param num Empty
     *
     * @return Empty
     */
    public String leftAlign(int width, int digits, double num)
    {
        return leftAlign(width, showDouble(digits, num));
    }

    /**
     * Undocumented
     *
     * @param width Empty
     * @param num Empty
     *
     * @return Empty
     */
    public String leftAlign(int width, int num)
    {
        return leftAlign(width, "" + num);
    }

    /**
     * Undocumented
     *
     * @param width Empty
     * @param str Empty
     *
     * @return Empty
     */
    public String leftAlign(int width, String str)
    {
        if (str == null)
        {
            return spaces(width);
        }
        else
        {
            return str + spaces(width - str.length());
        }
    }

    /**
     * Undocumented
     *
     * @return Empty
     */
    public boolean moreToRead()
    {
        // post-condition:
        //   if moreToRead() returns true, then lookAhead != null
        // readLine depends on this fact
        if (fileReader == null)
        {
            showError("Must open file before calling moreToRead().");
            System.exit(0);
        }

        if (lookAhead != null)
        {
            return true;
        }

        try
        {
            lookAhead = fileReader.readLine();

            return (lookAhead != null);
        }
        catch (Exception e)
        {
            // assume error means you cannot read any more
            // but otherwise do nothing about the error
            return false;
        }
    }

    /**
     * Undocumented
     *
     * @param filename Empty
     */
    public void openReadFile(String filename)
    {
        if (fileReader != null)
        {
            showError(
                "Old read file still open.  Can't open new one.");
            System.exit(0);
        }

        try
        {
            fileReader = new BufferedReader(new FileReader(filename));
        }
        catch (Exception e)
        {
            showError("Could not open file " + filename);
            System.exit(0);
        }

        lookAhead = null; // should already be null, but just to be safe
    }

    /**
     * Undocumented
     */
    public void openReadFile()
    {
        if (fileReader != null)
        {
            showError(
                "Old read file still open.  Can't open new one.");
            System.exit(0);
        }

        while (true)
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogTitle("Open Read File");

            int option = chooser.showOpenDialog(frame);
            File file = chooser.getSelectedFile();

            if ((option == JFileChooser.APPROVE_OPTION) &&
                  (file != null))
            {
                try
                {
                    fileReader =
                        new BufferedReader(new FileReader(file));
                    lookAhead = null;

                    return;
                }
                catch (Exception e)
                {
                    showError(
                        "Error opening file.  Please try again.");
                }
            }
            else
            {
                showError("Not a valid file.  Please try again.");
            }
        }
    }
     // end method openReadFile()

    /**
     * Undocumented
     *
     * @param filename Empty
     */
    public void openWriteFile(String filename)
    {
        if (fileWriter != null)
        {
            showError(
                "Old write file still open.  Can't open new one.");
            System.exit(0);
        }

        try
        {
            fileWriter = new PrintWriter(new FileWriter(filename));
        }
        catch (Exception e)
        {
            showError("Could not open file " + filename);
            System.exit(0);
        }
    }

    /**
     * Undocumented
     */
    public void openWriteFile()
    {
        if (fileWriter != null)
        {
            showError(
                "Old write file still open.  Can't open new one.");
            System.exit(0);
        }

        while (true)
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogTitle("Open Write File");
            chooser.setApproveButtonText("Create");

            int option = chooser.showSaveDialog(frame);
            File file = chooser.getSelectedFile();

            if ((option == JFileChooser.APPROVE_OPTION) &&
                  (file != null))
            {
                try
                {
                    fileWriter =
                        new PrintWriter(new FileWriter(file));

                    return;
                }
                catch (Exception e)
                {
                    showError(
                        "Error opening file.  Please try again.");
                }
            }
            else
            {
                showError("Not a valid file.  Please try again.");
            }
        }
    }
     // end method openWriteFile()

    /**
     * Causes the robot to wait for the specified amount of time.
     *
     * @param ms The number of milliseconds to wait.
     */
    public void pause(int ms)
    {
        checkRobot("pause");
        checkTime("pause", ms);

        /* Previous iteration used the robot wait, but there really isn't
         * any reason to use it, and waiting here will ensure that the time
         * waited is more accurate.  If returning to the robot's waitFor
         * instead, check it for accuracy--sometimes didn't wait the
         * appropriate amount of time if called publicly.
         */
        try
        {
            Thread.sleep(ms);
        }
        catch (InterruptedException e)
        {
        }
    }

    /**
     * Undocumented
     *
     * @param degrees Empty
     */
    public void pivotLeft(int degrees)
    {
        checkRobot("pivotLeft");
        checkAngle("pivotLeft", degrees);
        robot.pivotLeft(degrees);
    }

    /**
     * Undocumented
     *
     * @param degrees Empty
     */
    public void pivotRight(int degrees)
    {
        checkRobot("pivotRight");
        checkAngle("pivotRight", degrees);
        robot.pivotRight(degrees);
    }

    /**
     * Undocumented
     *
     * @param msg Empty
     */
    public void print(String msg)
    {
        JApplet receiver = null;
        receiver = (JApplet) getAppletContext().getApplet("output");

        if ((receiver != null) && receiver instanceof OutputApplet)
        {
            ((OutputApplet) receiver).print(msg);
        }
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void print(int number)
    {
        print("" + number);
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void print(double number)
    {
        print("" + number);
    }

    /**
     * Undocumented
     *
     * @param b Empty
     */
    public void print(boolean b)
    {
        print("" + b);
    }

    /**
     * Undocumented
     *
     * @param ch Empty
     */
    public void print(char ch)
    {
        print("" + ch);
    }

    /**
     * Undocumented
     *
     * @param code Empty
     */
    public void printCode(String code)
    {
        JApplet receiver = null;
        receiver = (JApplet) getAppletContext().getApplet("output");

        if ((receiver != null) && receiver instanceof OutputApplet)
        {
            ((OutputApplet) receiver).printCode(code);
        }
    }

    /**
     * Undocumented
     *
     * @param msg Empty
     */
    public void printError(String msg)
    {
        JApplet receiver = null;
        receiver = (JApplet) getAppletContext().getApplet("output");

        if ((receiver != null) && receiver instanceof OutputApplet)
        {
            ((OutputApplet) receiver).printError(msg);
        }
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void printError(int number)
    {
        printError("" + number);
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void printError(double number)
    {
        printError("" + number);
    }

    /**
     * Undocumented
     *
     * @param b Empty
     */
    public void printError(boolean b)
    {
        printError("" + b);
    }

    /**
     * Undocumented
     *
     * @param ch Empty
     */
    public void printError(char ch)
    {
        printError("" + ch);
    }

    /**
     * Undocumented
     */
    public void printError()
    {
        printError("");
    }

    /**
     * Undocumented
     *
     * @param msg Empty
     */
    public void printLine(String msg)
    {
        JApplet receiver = null;
        receiver = (JApplet) getAppletContext().getApplet("output");

        if ((receiver != null) && receiver instanceof OutputApplet)
        {
            ((OutputApplet) receiver).printLine(msg);
        }
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void printLine(int number)
    {
        printLine("" + number);
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void printLine(double number)
    {
        printLine("" + number);
    }

    /**
     * Undocumented
     *
     * @param b Empty
     */
    public void printLine(boolean b)
    {
        printLine("" + b);
    }

    /**
     * Undocumented
     *
     * @param ch Empty
     */
    public void printLine(char ch)
    {
        printLine("" + ch);
    }

    /**
     * Undocumented
     */
    public void printLine()
    {
        printLine("");
    }

    // readLine() gives an error if there is no more to read.
    // Alternative would be to have it return either null or "" but
    // the error approach is intended to encourage good programming habits.

    /**
     * Undocumented
     *
     * @return Empty
     */
    public String readLine()
    {
        if (fileReader == null)
        {
            showError("Must open file before calling readLine().");
            System.exit(0);
        }

        if (!moreToRead())
        {
            showError("Cannot read past end of file.");
            System.exit(0);
        }

        // lookAhead contains next line (guaranteed by moreToRead)
        String nextLine = lookAhead;
        lookAhead = null;

        return nextLine;
    }
     // end method readLine

    /**
     * Undocumented
     *
     * @param width Empty
     * @param digits Empty
     * @param num Empty
     *
     * @return Empty
     */
    public String rightAlign(int width, int digits, double num)
    {
        return rightAlign(width, showDouble(digits, num));
    }

    /**
     * Undocumented
     *
     * @param width Empty
     * @param num Empty
     *
     * @return Empty
     */
    public String rightAlign(int width, int num)
    {
        return rightAlign(width, "" + num);
    }

    /**
     * Undocumented
     *
     * @param width Empty
     * @param str Empty
     *
     * @return Empty
     */
    public String rightAlign(int width, String str)
    {
        if (str == null)
        {
            return spaces(width);
        }
        else
        {
            return spaces(width - str.length()) + str;
        }
    }

    /**
     * Causes the simulation to run in a mode that isn't idealistic,
     * but will be consistent between runs.  For robots, this means
     * that the robot might drift (not drive straight), but it will
     * drift exactly the same every time the program is run in this
     * mode.
     */
    public void runFixedRealisticSim()
    {
        realism = FIXED_REALISTIC;
        sim.setRealismLevel(realism);
    }

    /**
     * Causes the simulation to run in a mode that represents the
     * ideal function of all elements.  Although the exact operation
     * is determined by the individual elements, this generally
     * means that the behavior is one that would allow the most
     * accurate use of the component.
     */
    public void runIdealisticSim()
    {
        realism = IDEALISTIC;
        sim.setRealismLevel(realism);

        //if (robot != null) {
        //    robot.setRealismLevel(realism);
        //}
    }

    /**
     * Causes the simulation to run in a mode that more accurately
     * represents how the elements would act in the real world.  The
     * implementation determines what impact this command actually
     * has, but in general it will cause some level of stochiastic
     * behavior.
     */
    public void runRealisticSim()
    {
        realism = REALISTIC;
        sim.setRealismLevel(realism);

        //if (robot != null) {
        //    robot.setRealismLevel(realism);
        //}
    }

    /**
     * Undocumented
     *
     * @param msg Empty
     */
    public void showError(String msg)
    {
        sim.stop();
        JOptionPane.showMessageDialog(sim, msg, "Error",
            JOptionPane.ERROR_MESSAGE);
        sim.start();
    }

    /**
     * Undocumented
     *
     * @param msg Empty
     */
    public void showMessage(String msg)
    {
        //sim.stop();
        JOptionPane.showMessageDialog(sim, msg);

        //sim.start();
    }

    /**
     * Undocumented
     */
    public void start()
    {
        if (runThread == null)
        {
            runThread = new Thread(this, "Simulation");
        }
        else
        {
        }

        sim.start();
        runThread.start();
    }

    /**
     * Undocumented
     */
    public void stop()
    {
        sim.removeAllElements();
        sim.stop();
        runThread = null;
    }

    /**
     * Undocumented
     *
     * @param str Empty
     */
    public void write(String str)
    {
        if (fileWriter == null)
        {
            showError("Must open file before calling write.");
            System.exit(0);
        }

        fileWriter.print(str);
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void write(int number)
    {
        write("" + number);
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void write(double number)
    {
        write("" + number);
    }

    /**
     * Undocumented
     *
     * @param b Empty
     */
    public void write(boolean b)
    {
        write("" + b);
    }

    /**
     * Undocumented
     *
     * @param ch Empty
     */
    public void write(char ch)
    {
        write("" + ch);
    }

    /**
     * Undocumented
     *
     * @param str Empty
     */
    public void writeLine(String str)
    {
        if (fileWriter == null)
        {
            showError("Must open file before calling writeLine.");
            System.exit(0);
        }

        fileWriter.println(str);
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void writeLine(int number)
    {
        writeLine("" + number);
    }

    /**
     * Undocumented
     *
     * @param number Empty
     */
    public void writeLine(double number)
    {
        writeLine("" + number);
    }

    /**
     * Undocumented
     *
     * @param b Empty
     */
    public void writeLine(boolean b)
    {
        writeLine("" + b);
    }

    /**
     * Undocumented
     *
     * @param ch Empty
     */
    public void writeLine(char ch)
    {
        writeLine("" + ch);
    }

    /**
     * Undocumented
     *
     * @param command Empty
     * @param degrees Empty
     */
    protected void checkAngle(String command, int degrees)
    {
        if ((degrees < 0) || (degrees > 360))
        {
            showError("Tried to call " + command + " with " +
                degrees + " degrees.\n" +
                "Must be between 0 and 360.");
            System.exit(0);
        }
    }

    /**
     * Undocumented
     *
     * @param filename Empty
     */
    protected void checkFile(String filename)
    {
        java.io.File file = new java.io.File(filename);

        if (!file.exists())
        {
            showError("File " + filename + " does not exist.");
            System.exit(0);
        }
    }

    // private error checking routines
    //
    // When an error is detected, display the error and halt the program.
    // Halting the program would be unacceptable for general-purpose
    // software, but is intended to encourage good programming habits,
    // and to help the cadets realize when they have made an error (rather
    // than silently recovering from the error).

    /**
     * Undocumented
     *
     * @param command Empty
     */
    protected void checkRobot(String command)
    {
        if (robot == null)
        {
            showError("Must create robot before issuing " + command +
                " command.");
            System.exit(0);
        }
    }

    /**
     * Undocumented
     *
     * @param command Empty
     * @param ms Empty
     */
    protected void checkTime(String command, int ms)
    {
        if (ms <= 0)
        {
            showError("Tried to call " + command + " with " + ms +
                " milliseconds.\n" + "Must be positive.");
            System.exit(0);
        }
    }

    /**
     * Undocumented
     *
     * @param digits Empty
     * @param num Empty
     *
     * @return Empty
     */
    private String showDouble(int digits, double num)
    {
        java.text.NumberFormat f =
            java.text.NumberFormat.getInstance();
        f.setMinimumFractionDigits(digits);
        f.setMaximumFractionDigits(digits);
        f.setGroupingUsed(false);

        return f.format(num);
    }

    /**
     * Undocumented
     *
     * @param n Empty
     *
     * @return Empty
     */
    private String spaces(int n)
    {
        if (n <= 0)
        {
            return "";
        }

        char[] cs = new char[n];

        for (int i = 0; i < n; i++)
        {
            cs[i] = ' ';
        }

        return new String(cs);
    }
}
