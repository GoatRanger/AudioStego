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
 * Simulation.java
 *
 * Created on April 5, 2002, 9:49 PM
 */
package jago;

import java.awt.Dimension;

import javax.swing.text.StyleConstants;


/**
 * <p>
 * This is the main simulation window that contains the simulation and a text
 * area that can be used for simulation (system) and user messages.
 * </p>
 *
 * <p>
 * This class also implements the ability to start and stop the simulation via
 * a menu option; these options should be used sparingly, since they make the
 * assumption that stopping the simulation will not adversely affect the
 * user's thread.
 * </p>
 *
 * <p>
 * <b>Bugs:</b>
 * </p>
 *
 * <ul>
 * <li>
 * If the user's thread is not interacting with the simulation, the simulation
 * will stop, but the user's thread will continue, and the resulting
 * executing might differ from an execution where the simulation wasn't
 * stopped.
 * </li>
 * <li>
 * If the user's thread used a goForward or goBackward command for a robot (or
 * other methods that return immediately), execution of the user's thread
 * will continue even though the simulation stopped.  So, any assumptions
 * about time will be invalid.
 * </li>
 * <li>
 * Use of the System time (currentTimeMillis) will not coincide with the
 * simulation, since simulation time will be stopped.
 * </li>
 * </ul>
 *
 *
 * @version 6.2.2
 * @author Karl Gossett
 */
public class SimBox
    extends javax.swing.JFrame
    implements Realism {

    /** The output window document object */
    protected javax.swing.text.Document outputDocument = null;

    /** The actual PrintStream that System.out will redirect into */
    protected java.io.PrintStream ps;

    /** The simulation engine that this SimBox is using. */
    protected SimEngine simEngine;

    /** The input side of the pipe from System.out */
    protected java.io.PipedInputStream textInStream;

    /** A pipe to get System.out into the simulation text area */
    protected java.io.PipedOutputStream textOutStream;
    private javax.swing.JMenuItem aboutItem;
    private javax.swing.JMenuItem apiItem;
    private javax.swing.JMenuItem fileBGLoad;
    private javax.swing.JMenuItem fileExit;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JCheckBoxMenuItem musicItem;

    /**
     * Indicates that the size of the simulation needs to change.  Using this
     * flag ensures that any changes to the components will only happen in
     * the event dispatching thread (necessary for Swing components).
     */
    private boolean needsUpdated = false;
    private javax.swing.JTextPane outputArea;

    /**
     * The setting for the amount of realism to use.  As of version 6.3, there
     * are 3 settings.  See {@link eecs.jago.Realism}.
     */
    private int realismLevel = IDEALISTIC;
    private Thread runner = null;

    // Variables declaration - do not modify
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JInternalFrame simFrame;
    private javax.swing.JMenu simMenu;
    private javax.swing.JSeparator simSeparator;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JMenuItem startItem;
    private javax.swing.JMenuItem stopItem;

    /** The new dimension of the simulation area */
    private Dimension updatedDimensions = null;

    /** A Runnable listener to update the text area from System.out */
    private jago.SimBox.StreamListener updater;

    /**
     * Creates new form Simulation
     */
    public SimBox() {
        this(600, 500, null, false);
    }

    public SimBox(int width, int height) {
        this(width, height, null, false);
    }

    /**
     * Creates a new <CODE>SimBox</CODE> with the size and background.
     *
     * @param width     The desired width of the simulation area, in pixels
     * @param height    The desired height of the simulation area, in pixels
     * @param bkgnd     The name of the image to use as a background (must be
     *        a jpg or gif image)
     * @param tiled     <CODE>true</CODE> if the image should be tiled to fill
     *        the simulation space.  If this parameter is <CODE>false</CODE>,
     *        the simulation area will be resized to match the size of the
     *        image.
     */
    public SimBox(int width, int height, String bkgnd, boolean tiled) {
        super();
        initComponents();
        initTextStyles();
        outputDocument = outputArea.getDocument();
        enableEvents(java.awt.AWTEvent.WINDOW_EVENT_MASK);
        centerFrame();
        setResizable(true);
        updatedDimensions = this.getPreferredSize();
        simEngine = new SimEngine();
        simEngine.setPreferredSize(new Dimension(width, height));
        setTitle("Jago Simulation");
        simFrame.getContentPane()
                .add(simEngine);

        if (bkgnd == null) {
            setDefaultBackground();
        } else {

            if (tiled) {
                setBackgroundTile(bkgnd);
            } else {
                setBackgroundImage(bkgnd);
            }
        }

        textOutStream = new java.io.PipedOutputStream();
        ps = new java.io.PrintStream(textOutStream);

        try {
            textInStream = new java.io.PipedInputStream(textOutStream);
            System.setOut(ps);

            // Start the thread that updates text from System.out
            updater = new jago.SimBox.StreamListener(textInStream);
            updater.start();
        } catch (java.io.IOException e) {
            System.err.println("Couldn't create System.out stream");
            e.printStackTrace();
        } catch (java.security.AccessControlException e) {
            // Can't do this in an applet, so just catch the error and skip
        }

        simFrame.pack();
        setVisible(true);

        // Due to the user thread, we start the simEngine immediately.
        // Eventually, need to implement a block on the user thread until the
        // selection of the Start command.
        simEngine.start();

        //        simEngine.stop();
    }

    /**
     * Sets the background image of the SimBox.  The SimBox will use a default
     * background if the image does not exist in either the current (user)
     * directory, or relative to the SimBox package (the {@link
     * eecs.jago.ImageLoader} will check both).
     *
     * @param fileName    The name of the image to load (must be either
     *        <CODE>jpg</CODE> or <CODE>gif</CODE> image)
     */
    public void setBackgroundImage(String fileName) {

        java.awt.Image image = ImageLoader.getImage(this, fileName);

        if (image != null) {
            setBackgroundImage(image);
        } else {
            printError("The background image named \"" + fileName
                       + "\" could not be found. Using default.");
            setDefaultBackground();
        }
    }

    /**
     * Sets the background image of the simulation to the specified image.
     *
     * @param img   The <CODE>Image</CODE> to use as a background.
     */
    public void setBackgroundImage(java.awt.Image img) {
        simEngine.setBackgroundImage(img);
        updateSizes(new Dimension(img.getWidth(this),
                                  img.getHeight(this)));
        repaint();
    }

    /**
     * Sets the background tile of the SimBox. You can tile an image as the
     * background by using this method. To use a single image as the
     * background, use setBackgroundImage instead.  If the image cannot be
     * found, a default background will be used.
     *
     * @param fileName The name of the image file.
     */
    public void setBackgroundTile(String fileName) {

        java.awt.Image bkgnd = ImageLoader.getImage(this, fileName);

        if (bkgnd != null) {
            simEngine.setBackgroundTile(bkgnd);
            updateSizes((int)simEngine.getPreferredSize()
                                      .getWidth(),
                        (int)simEngine.getPreferredSize()
                                      .getHeight());
            repaint();
        } else {
            setDefaultBackground();
        }
    }

    /**
     * Causes the simulation to provide debugging feedback to the user
     *
     * @param debug <CODE>true</CODE> if the simulation should provide
     *        debugging information. Only provides information from those
     *        classes that have implemented debugging.
     */
    public void setDebugging(boolean debug) {
        simEngine.setDebugging(debug);
    }

    /**
     * Sets the title of the simulation frame
     * @param title Empty
     */
    public void setSimTitle(String title) {
        simFrame.setTitle(title);
    }

    /**
     * Attempts to add the SimElement to the simulation at the specified
     * location.  The usable canvas of a SimBox is 8 pixels narrower than its
     * size, and about 35 pixels shorter than its height (due to the title
     * bar).
     *
     * @param element   The element to add
     * @param xLoc      The desired horizontal location
     * @param yLoc      The desired vertical location
     */
    public void add(jago.element.SimElement element, double xLoc,
                    double yLoc) {

        // If not already in a simulation, add it
        if (!(element.inASimulation())) {
            simEngine.add(element, xLoc, yLoc);

            // Let the element know that it is pending addition to the sim.
            element.pendingAdd();
        } else {
            printError("The element " + element.toString()
                       + " was already added to the simulation.");
        }
    }

    /**
     * Adds a robot to the simulation with an initial heading.  Provided for
     * compatability with legacy Jago applications.  Deprecated as of version
     * 5.1 of the simulation.
     *
     * @param element The <CODE>StandardRobot</CODE> to add.
     * @param xLoc The desired horizontal location
     * @param yLoc The desired vertical location
     * @param heading The initial heading of the <CODE>StandardRobot</CODE>
     * @deprecated As of Version 5.1 of Jago (eecs.jago)
     */
    public void add(jago.element.StandardRobot element, double xLoc,
                    double yLoc, int heading) {
        element.setInitialHeading(heading);
        add(element, xLoc, yLoc);
    }

    /**
     * Allows an object to use the timer from the simengine.
     *
     * @param listener     The <CODE>ActionListener</CODE> that will act on
     *        timer events from the simengine.
     */
    public void addActionListener(java.awt.event.ActionListener listener) {
        simEngine.addActionListener(listener);
    }

    /**
     * Clears all text from the output window.
     */
    public void clearOutput() {

        try {
            outputDocument.remove(0,
                                  outputDocument.getLength());
        } catch (javax.swing.text.BadLocationException e) {
            System.err.println("Could not clear the output area.");
        }
    }

    /**
     * Stops the simulation thread and exits the program.
     */
    public void exit() {
        simEngine.stop();
        System.exit(0);
    }

    /**
     * Loads a background from a file.  Opens a <CODE>JFileChooser</CODE>
     * allowing the selection of a specified background (either jpg or gif).
     */
    public void loadBackground() {

        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setFileSelectionMode(
                javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(java.io.File dir) {

                String filename = dir.toString()
                                     .toLowerCase();

                return filename.endsWith(".jpg") || filename.endsWith(".gif")
                       || dir.isDirectory();
            }

            public String getDescription() {

                return "GIF & JPG Images";
            }
        });

        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            setBackgroundImage(chooser.getSelectedFile().getPath());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SimBox().setVisible(true);
    }

    /**
     * Sets the realism level.
     *
     * @param level A constant from {@link eecs.jago.Realism}
     */
    public void setRealismLevel(int level) {
        simEngine.setRealismLevel(level);
        realismLevel = level;
    }

    /**
     * Gets the realism setting (as a constant)
     *
     * @return Returns one of the Realism constants, currently {@link
     *         #IDEALISTIC}, {@link #REALISTIC}, or {@link #FIXED_REALISTIC}
     */
    public int getRealismLevel() {

        return realismLevel;
    }

    /**
     * Updates the display.
     * @param g Empty
     */
    public void paint(java.awt.Graphics g) {

        if (needsUpdated) {
            updateWindows();
        }

        super.paint(g);
    }

    /**
     * Prints a message in the text window using the normal text style.
     *
     * @param msg   The text message to display.
     */
    public void print(String msg) {
        print(msg, "normal");
    }

    /**
     * Prints a message using a "code" style of text (smaller, sansserif). The
     * message is terminated with a newline marker.
     *
     * @param code   The line of code or text message to display.
     */
    public void printCode(String code) {
        print(code + "\n", "system");
    }

    /**
     * Prints an error message in the text window using the error style. The
     * message will be prefixed with a newline and "ERROR:", and terminated
     * with a newline.
     *
     * @param error   The contents of the error message.
     */
    public void printError(String error) {
        print("\nERROR: " + error + "\n", "error");
    }

    /**
     * Prints a message with a terminal newline marker in the text window
     * using the normal text style.
     *
     * @param msg   The text message to display.
     */
    public void printLine(String msg) {
        print(msg + "\n");
    }

    /**
     * Removes a SimElement from the simulation
     *
     * @param element   The element to remove.
     */
    public void remove(jago.element.SimElement element) {
        simEngine.remove(element);
    }

    /**
     * Removes all elements from the simulation.
     */
    public void removeAll() {
        simEngine.removeAllElements();
    }

    /**
     * Starts running the simualation. <br> In this version, the start and
     * stop commands are potentially dangerous because although they stop the
     * simulation, they assume that stopping the execution of the simulation
     * will not affect the user's program, since the user thread is still
     * executing.
     */
    public void start() {

        if (simEngine != null) {
            simEngine.start();
        }
    }

    /**
     * Stops running the simualation. <br>
     *
     * <P>
     * In this version, the start and stop commands are potentially dangerous
     * because although they stop the simulation, they assume that stopping
     * the execution of the simulation will not affect the user's program,
     * since the user thread is still executing.
     * </p>
     *
     * <P>
     * Any movement that relies on the system time may be corrupted if the
     * simulation is stopped because although the simulation is not running,
     * all time-based information is based on the system clock, not on a
     * simulation clock.
     * </p>
     */
    public void stop() {

        if (simEngine != null) {
            simEngine.stop();
        }
    }

    /**
     * Sets the size of the SimBox and simulation engine workspace.
     *
     * @param d   The new <CODE>Dimension</CODE> of the simulation.  The
     *        <CODE>Dimension</CODE> should reflect the size of the actual
     *        <I>simulation</I> area, not the size of the entire Jago
     *        workspace.
     */
    public void updateSizes(Dimension d) {
        updatedDimensions = d;

        // Indicator for this painter that an update is required
        needsUpdated = true;
    }

    /**
     * Sets the size of the SimBox and simulation engine workspace.
     *
     * @param width   The new width of the simulation
     * @param height  The new height of the simulation
     */
    public void updateSizes(int width, int height) {
        updateSizes(new Dimension(width, height));
    }

    /**
     * Places the SimBox in the center of the user's screen.
     */
    protected void centerFrame() {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
                                               .getScreenSize();
        Dimension frameSize = getPreferredSize();

        // Prevent errors if the frame is larger than the screen
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }

        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }

        setLocation((screenSize.width - frameSize.width) / 2,
                    (screenSize.height - frameSize.height) / 2);
    }

    /**
     * Creates the <CODE>Style</CODE>s used for the different display fonts in
     * the output window.
     */
    protected void initTextStyles() {

        //Initialize some styles.
        javax.swing.text.Style def = javax.swing.text.StyleContext.getDefaultStyleContext()
                                                                  .getStyle(
                                             javax.swing.text.StyleContext.DEFAULT_STYLE);
        javax.swing.text.Style regular = outputArea.addStyle("normal", def);
        StyleConstants.setFontFamily(def, "SansSerif");

        javax.swing.text.Style s = outputArea.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);
        s = outputArea.addStyle("bold", regular);
        StyleConstants.setBold(s, true);
        s = outputArea.addStyle("system", def);
        StyleConstants.setFontSize(s, 12);
        StyleConstants.setItalic(s, false);
        StyleConstants.setFontFamily(s, "Serif");
        s = outputArea.addStyle("error", regular);
        StyleConstants.setFontSize(s, 12);
        StyleConstants.setForeground(s, java.awt.Color.red);
    }

    /**
     * Closes the window exits the program.
     *
     * @param e The <CODE>WindowEvent</CODE> that requires processing.
     */
    protected void processWindowEvent(java.awt.event.WindowEvent e) {
        super.processWindowEvent(e);

        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING) {
            exit();
        }
    }

    /**
     * If the windows need updated, this method will be called.  This method
     * should not be called by the user; it should only be called from
     * <CODE>paint</CODE>.
     */
    protected void updateWindows() {
        needsUpdated = false;

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
                                               .getScreenSize();
//        int width = (int)updatedDimensions.getWidth();
//        int height = (int)updatedDimensions.getHeight();
//        int myHorizInsets = this.getInsets().left + this.getInsets().right;
//        int myVertInsets = this.getInsets().top + this.getInsets().bottom;
        int simVertInsets = simFrame.getInsets().top
                            + simFrame.getInsets().bottom + 28;
        int simHorizInsets = simFrame.getInsets().left
                             + simFrame.getInsets().right;
        simFrame.setLocation(2, 2);
        simFrame.setPreferredSize(new Dimension(
                                          (int)simEngine.getPreferredSize().width + simHorizInsets,
                                          (int)simEngine.getPreferredSize().height + simVertInsets));

        int splitWidth = splitPane.getInsets().left
                         + splitPane.getInsets().right
                         + splitPane.getDividerSize()
                         + simFrame.getPreferredSize().width
                         + scrollPane.getPreferredSize().width;

        if (splitWidth > screenSize.width - 30) {
            splitWidth = screenSize.width - 30;
        }

        int splitHeight = splitPane.getInsets().top
                          + splitPane.getInsets().bottom
                          + simFrame.getPreferredSize().height + 2;
        simFrame.pack();
        splitPane.setPreferredSize(new Dimension(splitWidth, splitHeight));
        splitPane.setDividerLocation(
                simEngine.getPreferredSize().width + simHorizInsets);
        centerFrame();
        this.pack();
    }

    /**
     * Sets the default background to a tiled gray background
     */
    private void setDefaultBackground() {

        java.awt.Image icon = ImageLoader.getImage(this,
                                                   "/jago/images/white.gif");

        if (icon != null) {
            simEngine.setBackgroundTile(icon);
            updateSizes((int)simEngine.getPreferredSize()
                                      .getWidth(),
                        (int)simEngine.getPreferredSize()
                                      .getHeight());
            repaint();
        }
    }

    /**
     * Undocumented
     *
     * @param evt Empty
     */
    private void apiItemActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_apiItemActionPerformed
    } //GEN-LAST:event_apiItemActionPerformed

    /**
     * Undocumented
     *
     * @param evt Empty
     */
    private void exit(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_exit
        exit();
    } //GEN-LAST:event_exit

    /**
     * Exit the Application
     * @param evt Empty
     */
    private void exitForm(java.awt.event.WindowEvent evt) { //GEN-FIRST:event_exitForm
        System.exit(0);
    } //GEN-LAST:event_exitForm

    /**
     * This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() { //GEN-BEGIN:initComponents
        splitPane = new javax.swing.JSplitPane();
        scrollPane = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextPane();
        simFrame = new javax.swing.JInternalFrame();
        menu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileBGLoad = new javax.swing.JMenuItem();
        simSeparator = new javax.swing.JSeparator();
        fileExit = new javax.swing.JMenuItem();
        simMenu = new javax.swing.JMenu();
        startItem = new javax.swing.JMenuItem();
        stopItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        musicItem = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutItem = new javax.swing.JMenuItem();
        apiItem = new javax.swing.JMenuItem();
        getContentPane()
            .setLayout(new java.awt.FlowLayout());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        splitPane.setResizeWeight(1.0);
        splitPane.setPreferredSize(new java.awt.Dimension(800, 600));
        splitPane.setDoubleBuffered(true);
        scrollPane.setVerticalScrollBarPolicy(
                javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 100));
        scrollPane.setMinimumSize(new java.awt.Dimension(200, 150));
        scrollPane.setDoubleBuffered(true);
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(outputArea);
        splitPane.setRightComponent(scrollPane);
        simFrame.setTitle("Simulation");
        simFrame.setPreferredSize(new java.awt.Dimension(500, 524));
        simFrame.setMinimumSize(new java.awt.Dimension(200, 234));
        simFrame.setDoubleBuffered(true);
        simFrame.setVisible(true);
        simFrame.setOpaque(false);
        splitPane.setLeftComponent(simFrame);
        getContentPane()
            .add(splitPane);
        menu.setDoubleBuffered(true);
        fileMenu.setText("File");
        fileBGLoad.setToolTipText("Loads a background image");
        fileBGLoad.setText("Load Background...");
        fileBGLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBG(evt);
            }
        });
        fileMenu.add(fileBGLoad);
        fileMenu.add(simSeparator);
        fileExit.setToolTipText("Closes the simulation");
        fileExit.setText("Exit");
        fileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit(evt);
            }
        });
        fileMenu.add(fileExit);
        menu.add(fileMenu);
        simMenu.setText("Options");
        startItem.setToolTipText("Starts running the simulation");
        startItem.setText("Start");
        startItem.setEnabled(false);
        startItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimulation(evt);
            }
        });
        simMenu.add(startItem);
        stopItem.setToolTipText("Pauses the simulation");
        stopItem.setText("Stop");
        stopItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopSimulation(evt);
            }
        });
        simMenu.add(stopItem);
        simMenu.add(jSeparator1);
        musicItem.setToolTipText("Turns the background music on/off");
        musicItem.setText("Background Music");
        musicItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musicSetting(evt);
            }
        });
        simMenu.add(musicItem);
        menu.add(simMenu);
        helpMenu.setToolTipText("Not Implemented in this version");
        helpMenu.setText("Help");
        helpMenu.setEnabled(false);
        aboutItem.setText("About...");
        helpMenu.add(aboutItem);
        apiItem.setToolTipText("Open the JAGO API in a browser");
        apiItem.setText("View JAGO API");
        apiItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apiItemActionPerformed(evt);
            }
        });
        helpMenu.add(apiItem);
        menu.add(helpMenu);
        setJMenuBar(menu);
        pack();
    } //GEN-END:initComponents

    /**
     * Undocumented
     *
     * @param evt Empty
     */
    private void loadBG(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_loadBG
        loadBackground();
    } //GEN-LAST:event_loadBG

    /**
     * Undocumented
     *
     * @param evt Empty
     */
    private void musicSetting(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_musicSetting

        if (musicItem.isSelected()) {
            simEngine.setPlayMusic(true);
        } else {
            simEngine.setPlayMusic(false);
        }
    } //GEN-LAST:event_musicSetting

    /**
     * Prints a message in the text window. Uses the specified style.
     *
     * @param msg   The <CODE>String</CODE> to display.
     * @param style The Style of the message.
     */
    private void print(String msg, String style) {

        try {
            outputDocument.insertString(outputDocument.getLength(),
                                        msg,
                                        outputArea.getStyle(style));
        } catch (javax.swing.text.BadLocationException e) {
            System.err.println("Unable to insert text into output area");
        }
        int pos = outputDocument.getLength();
        outputArea.setCaretPosition(pos-1);
    }

    /**
     * Undocumented
     *
     * @param evt Empty
     */
    private void startSimulation(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_startSimulation
        start();
        startItem.setEnabled(false);
        stopItem.setEnabled(true);
    } //GEN-LAST:event_startSimulation

    /**
     * Undocumented
     *
     * @param evt Empty
     */
    private void stopSimulation(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_stopSimulation
        stop();
        startItem.setEnabled(true);
        stopItem.setEnabled(false);
    } //GEN-LAST:event_stopSimulation

    /**
     * An inner class <CODE>Runnable</CODE> listener that monitors the
     * <CODE>System.out</CODE> stream and forwards it to the output window
     * <CODE>Document</CODE>.
     */
    public class StreamListener
        implements Runnable {

        java.io.PipedInputStream stream;
        private Thread listenerThread = null;

        public StreamListener(java.io.PipedInputStream pis) {
            stream = pis;
        }

        public void run() {

            Thread myThread = Thread.currentThread();

            while (myThread == listenerThread) {

                try {

                    int charCount = stream.available();

                    if (charCount > 0) {

                        byte[] letters = new byte[charCount];
                        stream.read(letters, 0, charCount);

                        javax.swing.text.Document doc =
                                outputArea.getDocument();

                        try {
                            doc.insertString(doc.getLength(),
                                             new String(letters),
                                             outputArea.getStyle("system"));
                        } catch (javax.swing.text.BadLocationException e) {
                            System.err.println(
                                    "Unable to insert text into "
                                    + "output area");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.yield();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.err.println("Thread Interrupted!");
                    e.printStackTrace();
                }
            }
        }

        public void start() {

            if (listenerThread == null) {
                listenerThread = new Thread(this, "SystemOutListener");
                listenerThread.setPriority(Thread.MIN_PRIORITY);
                listenerThread.start();
            }
        }
    }

    // End of variables declaration
}