package eecs.instructor.burrow;

import jago.*;

import jago.element.*;

import eecs.jago.instructor.burrow.*;


/**
 * A simulated Guardrail Common Sensor (GRCS) mission that has two
 * independent RC12s flying on track, randomly generated emitters,
 * and  a randomly generated enemy radar / ADA missile.  The program
 * also offers the capability to generate messages every minute
 * (ETE) to relay the lead aircraft's Estimated Time Enroute (ETE)
 * to the next turning point, as well as a message to indicate that
 * the lead aircraft is turning.  Several methods are provided to
 * get information about the second aircraft, manipulate the second
 * aircraft, and check for messages from the lead aircraft or
 * intercepted emitter frequencies / locations.
 *
 * @author Guy M. Burrow
 * @version 1.0
 */
public class RC12Gui extends eecs.RobotGuiBase {
    //*********************** Class Variables ********************************			

    /** DOCUMENTATION INCOMPLETE */
    protected static RC12 lead; // lead aircraft

    /** DOCUMENTATION INCOMPLETE */
    protected static RC12 synch; // synchronization aircraft

    /** DOCUMENTATION INCOMPLETE */
    protected static Emitter emitter; // radio signal emitter

    /** DOCUMENTATION INCOMPLETE */
    protected static StraightLine azimuth1; // azimuth from lead to emitter

    /** DOCUMENTATION INCOMPLETE */
    protected static StraightLine azimuth2; // azimuth from synch to emitter

    /** DOCUMENTATION INCOMPLETE */
    protected static double emitterFreq; // emitter frequency (MHZ)

    /** DOCUMENTATION INCOMPLETE */
    protected static double emitterLat; // emitter latititude (deg. N)

    /** DOCUMENTATION INCOMPLETE */
    protected static double emitterLon; // emitter longitude (deg. W)

    /** DOCUMENTATION INCOMPLETE */
    protected static boolean latToTransmit; // flag for emitter Latitude 

    /** DOCUMENTATION INCOMPLETE */
    protected static boolean lonToTransmit; // flag for emitter Longitude

    /** DOCUMENTATION INCOMPLETE */
    protected static boolean freqToTransmit; // flag for emitter frequency 

    /** DOCUMENTATION INCOMPLETE */
    protected static boolean onTrack; // flag for aircraft on track

    /** DOCUMENTATION INCOMPLETE */
    protected static int nextLeadPoint; // next point for the lead RC12

    /** DOCUMENTATION INCOMPLETE */
    protected static int nextSynchPoint; // next point for the synch RC12

    /** DOCUMENTATION INCOMPLETE */
    protected static boolean radarOn; // flag to indicate enemy ADA

    /** DOCUMENTATION INCOMPLETE */
    protected static double ETEMessage; // estimated time enroute value

    /** DOCUMENTATION INCOMPLETE */
    protected static boolean turningMessage; // flag to indicate lead turning

    /** DOCUMENTATION INCOMPLETE */
    protected static boolean autopilotEngaged; // flag for autopilot engaged

    /** DOCUMENTATION INCOMPLETE */
    protected static int emitterXCord; // emitter sim x-coordinate

    /** DOCUMENTATION INCOMPLETE */
    protected static int emitterYCord; // emitter sim y-coordinate

    //*********************** Class Constants *********************************	

    /** DOCUMENTATION INCOMPLETE */
    public static final boolean DEBUG = false; // debug constant

    /** DOCUMENTATION INCOMPLETE */
    public static final double AG7005_LAT = 39.0; // degrees North

    /** DOCUMENTATION INCOMPLETE */
    public static final double AG7005_LON = 76.5; // degrees West

    /** DOCUMENTATION INCOMPLETE */
    public static final int LEAD_SP = 135; // lead track start point

    /** DOCUMENTATION INCOMPLETE */
    public static final int LEAD_EP = 260; // lead track end point 

    /** DOCUMENTATION INCOMPLETE */
    public static final int TRACK = 358; // both tracks 

    /** DOCUMENTATION INCOMPLETE */
    public static final int SYNCH_SP = 340; // synch track start point 

    /** DOCUMENTATION INCOMPLETE */
    public static final int SYNCH_EP = 465; // synch track end point 

    /** DOCUMENTATION INCOMPLETE */
    public static final int INIT_HEADING = 270; // initial heading on track

    /** DOCUMENTATION INCOMPLETE */
    public static final int INIT_SPEED = 120; // initial speed (knots)

    /** DOCUMENTATION INCOMPLETE */
    public static final double[] FREQS = {
                                             30.125, 31.500, 32.750,
                                             34.050, 36.500, 38.120,
                                             38.750, 40.255, 42.350,
                                             44.450, 44.950
                                         }; // list of emitter frequencies

    /** DOCUMENTATION INCOMPLETE */
    public static final int BILLY_FREQ_INDEX = 5; // Billy's freq in 5th index

    /** DOCUMENTATION INCOMPLETE */
    public static final int EMITTER = 0; // star image

    /** DOCUMENTATION INCOMPLETE */
    public static final int BILLY = 1; // navy mascot image 

    /** DOCUMENTATION INCOMPLETE */
    public static final int THREAD_DELAY = 50; // 50 ms delay for threads

    /**
     * Static initializer for the GUI.
     */
    static {
        sim = new SimBox();
        sim.setBackgroundImage("images/OperationalGraphics.gif");
        setSimulationTitle("Guardrail Common Sensor");
        emitter = null;

        // The following should be in every GUI so that all the windows from
        // eecs.GuiBase will appear in the sim box
        frame = sim;
        setSimulationTitle("Guardrail Common Sensor");
        sim.setVisible(true);

        // Initialize class variables
        lead = null;
        synch = null;
        emitterLat = 0;
        emitterLon = 0;
        emitterFreq = 0;
        latToTransmit = false;
        lonToTransmit = false;
        freqToTransmit = false;
        ETEMessage = -1;
        turningMessage = false;
        radarOn = false;
        nextLeadPoint = LEAD_SP;

        // Give the intelligence report on Billy's frequency
        printLine("Intel Update: 'Billy' is on frequency "
                  + FREQS[BILLY_FREQ_INDEX]);
    }
     // end static initializer

    /*
     * Returns the current heading of the synch aircraft.
     *
     * @return heading in degrees of the synch aircraft.
     */
    public static int getCurrentHeading() {
        return (int) synch.getHeading();
    }

    /*
     * Returns the distance of the synch aircraft to the next turning point.
     *
     * @return distance in miles to the next turning point.
     */
    public static double getDistanceToTurn() {
        return synch.getDME(nextSynchPoint);
    }

    /*
     * Returns the ETE of the synch aircraft.
     *
     * @return synch ETE.
     */
    public static double getETE() {
        double returnValue = synch.getETE(nextSynchPoint);

        // To improve performance, just send 0 if ETE is less than .1 minutes.
        if (returnValue < 0.1) {
            return 0;
        }

        // Otherwise, return synch ETE.
        return returnValue;
    }

    /*
     * Gets the frequency of the next intercepted emitter.
     *
     * @return the frequency of the intercepted emitter.
     */
    public static double getEmitterFrequency() {
        // wait for a frequency to transmit.
        while (!freqToTransmit) {
            threadDelay(THREAD_DELAY);
        }

        // reset the frequency flag.
        freqToTransmit = false;

        return emitterFreq;
    }

    /*
     * Gets the Latitude of the next intercepted emitter.
     *
     * @return the Latitude of the intercepted emitter to 2 decimal places.
     */
    public static double getEmitterLatitude() {
        // wait for a latitude to transmit.
        while (!latToTransmit) {
            threadDelay(THREAD_DELAY);
        }

        // reset the latitude flag.
        latToTransmit = false;

        return Math.round(emitterLat * 100.0) / 100.0;
    }

    /*
     * Gets the Longitude of the next intercepted emitter.
     *
     * @return the Longitude of the intercepted emitter to 2 decimal places.
     */
    public static double getEmitterLongitude() {
        // wait for a latitude to transmit.
        while (!lonToTransmit) {
            threadDelay(THREAD_DELAY);
        }

        // reset the longitude flag.
        lonToTransmit = false;

        return Math.round(emitterLon * 100.0) / 100.0;
    }

    /*
     * Returns the Lead ETE if a message is available.
     *
     * @return lead ETE if available, otherwise return -1.
     */
    public static double getLeadETE() {
        double leadETE = ETEMessage;

        // reset message to -1 as ETE is returned next.
        if (ETEMessage > 0) {
            ETEMessage = -1;
        }

        return leadETE;
    }

    /*
     * Returns the lead turning message as required.
     *
     * @return true if lead turns before synch reaches turning point, o/w false.
     */
    public static boolean isLeadTurning() {
        if (turningMessage == true) {
            // Reset turning message flag;
            turningMessage = false;

            return true;
        }

        return false;
    }

    /**
     * User Interface Methods
     *
     * @param newSpeed DOCUMENTATION INCOMPLETE
     */

    /*
     * Sets the speed of the synch aircraft at a selected speed.
     *
     * @param newSpeed desired speed in knots.
     */
    public static void setSpeed(int newSpeed) {
        synch.setNewSpeed(newSpeed);
        synch.goForward();
    }

    /**
     * Engages the autopilot, adjusting heading on track to the next
     * point.
     */
    public static void engageAutopilot() {
        autopilotEngaged = true;

        // Adjust heading to next turning point.
        double currentHeading = synch.getHeading();

        if ((currentHeading > 0) && (currentHeading < 180)) {
            nextSynchPoint = SYNCH_EP;
            synch.pivotLeft((int) currentHeading - 90);
        } else {
            nextSynchPoint = SYNCH_SP;
            synch.pivotRight(270 - (int) currentHeading);
        }

        synch.goForward();
    }

    /**
     * Emitter Generation / reporting
     */

    /*
     *        Starts the random emitter generation thread.
     */
    public static void generateEmitters() {
        Thread emitters = new Thread(new Runnable() {
                public void run() {
                    emittersThread();
                }
            });
        emitters.start();
    }
     // end generateEmitters

    /**
     * Message Generation
     */

    /*
     *        Starts the message generation thread.
     */
    public static void generateMessages() {
        Thread messages = new Thread(new Runnable() {
                public void run() {
                    messagesThread();
                }
            });
        messages.start();
    }

    /*
     * Returns whether or not the enemy ADA radar warning is active.
     *
     * @return true if an ADA radar is active, false if not active.
     */
    public static boolean radarWarning() {
        // Small thread delay to improve the performance of the simulation.
        threadDelay(THREAD_DELAY);

        return radarOn;
    }

    /**
     * DOCUMENTATION INCOMPLETE
     */
    public static void startLeadTrack() {
        Thread leadTrack = new Thread(new Runnable() {
                public void run() {
                    leadTrackThread();
                }
            });
        leadTrack.start();
    }

    /**
     * Starts the synch aircraft track thread.
     */
    public static void startSynchTrack() {
        Thread synchTrack = new Thread(new Runnable() {
                public void run() {
                    synchTrackThread();
                }
            });
        synchTrack.start();
    }

    /**
     * Delays a thread by a specified number of milliseconds (used on
     * threads that loop to improve the simulation run).
     *
     * @param delay number of milliseconds to delay the thread.
     */
    public static void threadDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException exc) {
            printLine("thread delay exception");
        }
    }

    /**
     * Start a 15 degree turn to the left, disengaging autopilot.
     */
    public static void turnLeft() {
        autopilotEngaged = false;
        synch.pivotLeft(15);
        synch.goForward();
    }

    /**
     * Start a 15 degree turn to the right, disengaging autopilot.
     */
    public static void turnRight() {
        autopilotEngaged = false;
        synch.pivotRight(15);
        synch.goForward();
    }

    /*
     * Creates an emitter at a specified x-y coordinate location with a
     * specified image.
     *
     * @param x x-coordinate for the emitter.
     * @param y y-coordinate for the emitter.
     * @param image image file to be displayed.
     */
    private static void createEmitter(int x, int y, int image) {
        // If an emitter already exists, remove it from the sim
        if (emitter != null) {
            emitter.removeFromSim();
        }

        // Create a 'Billy' emitter
        if (image == BILLY) {
            emitter = new Emitter("images/billy.gif");
        } else
        // Else, create a standard emitter
         {
            emitter = new Emitter();
        }

        sim.add(emitter, x, y);
    }
     // end create emitter

    /**
     * Creates the lead RC12 at the EP of the lead track with an
     * initial heading and speed to go to the SP.
     */
    private static void createLeadRC12() {
        lead = new RC12();
        lead.setNewSpeed(INIT_SPEED);
        lead.setInitialHeading(INIT_HEADING);
        lead.setRealismLevel(IDEALISTIC);
        sim.add(lead, LEAD_EP, TRACK);
    }
     // end createLeadRC12

    /**
     * radar / ADA missile
     *
     * @param xCord DOCUMENTATION INCOMPLETE
     * @param yCord DOCUMENTATION INCOMPLETE
     */

    /*
     * Creates a missile as an RC12 object with a missile image.  The missile tracks the
     * synch RC12 until it reached the bottom of the screen.
     *
     * @param xCord x-coordinate for the missile.
     * @param yCord y-coordinate for the missile.
     */
    private static void createMissile(int xCord, int yCord) {
        // initialize the method variable
        double headingAdjustment = 0;
        double xDiff = 0;
        double yDiff = 0;
        double distance = 0;

        // Sets the missile at the top of the screen.
        yCord = 50;

        // Create a new missile (RC12) with a missile image initially oriented south.
        RC12 missile = new RC12();
        missile.setInitialHeading(180);
        missile.setAppearance("images/radar.gif");
        missile.setRealismLevel(IDEALISTIC);
        sim.add(missile, xCord, yCord);
        threadDelay(THREAD_DELAY);
        missile.goForward();

        // Evacuate the lead RC12.
        evacuate();
        missile.setNewSpeed(200);

        // Track the synch aircraft until it reaches the bottom of the screen. 
        // The missile will be destroyed if it collides with the synch RC12.
        double missileLatitude = missile.getPosition().getY();

        while (missileLatitude < 500) {
            // Calculate the required heading to the synch RC12.
            xDiff = xCord - synch.getPosition().getX();
            yDiff = yCord - synch.getPosition().getY();
            distance = Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
            headingAdjustment = (180.0 / 3.141) * Math.asin(xDiff / distance);

            // Adjust the missile heading to within 5 degrees of the synch RC12
            if (headingAdjustment < -5) {
                missile.pivotLeft((int) missile.getHeading() - 180
                                  - (int) headingAdjustment);
            } else if (headingAdjustment > 5) {
                missile.pivotRight(180 - (int) missile.getHeading()
                                   + (int) headingAdjustment);
            }

            missile.goForward();
            missileLatitude = missile.getPosition().getY();
            threadDelay(THREAD_DELAY);
        }

        // Destroy the missile if it reaches the bottom of the screen.
        missile.setDestroyed();

        // stops all the threads.
        onTrack = false;
    }
     // end createMissile

    /**
     * Creates the synch RC12 at the EP of the synch track with an
     * initial heading and speed to go to the SP.
     */
    private static void createSynchRC12() {
        synch = new RC12();
        synch.setNewSpeed(INIT_SPEED);
        synch.setInitialHeading(INIT_HEADING);
        synch.setRealismLevel(IDEALISTIC);
        sim.add(synch, SYNCH_EP, TRACK);
        autopilotEngaged = true;
    }
     // end createLeadRC12

    /*
     * Draws a line from both aircraft to the current emitter.
     */
    private static void drawAzimuths() {
        // If azimuths already exist, remove them from the sim.
        if (azimuth1 != null && azimuth2 != null) {
            sim.remove(azimuth1);
            sim.remove(azimuth2);
            
            while (azimuth1.inASimulation() || azimuth2.inASimulation()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        }
        
        // Creates straight lines from both aircraft to the center of the emitter.
        azimuth1 = new StraightLine((int) lead.getPosition().getX(),
                                    (int) lead.getPosition().getY(),
                                    (emitterXCord + 23),
                                    (emitterYCord + 23), BLUE);
        azimuth2 = new StraightLine((int) synch.getPosition().getX(),
                                    (int) synch.getPosition().getY(),
                                    (emitterXCord + 23),
                                    (emitterYCord + 23), BLUE);
        sim.add(azimuth1, 100, 100);
        sim.add(azimuth2, 100, 100);
    }
     // end drawAzimuths

    /*
     * While the lead aircraft is on track, this method generates random
     * radio signal emitters every 1-2 minutes based on the lead aircraft
     * ETE to the next point.
     */
    private static void emittersThread() {
        onTrack = true;

        // Initialize the thread method variables
        double nextEmitterTime = 0;
        double ETE = 6;
        int nextPoint = nextLeadPoint;

        // Generate the time for the next emitter (random number between 1 
        // and 2 minutes from the current ETE.
        nextEmitterTime = ETE - (1 + (Math.random() * 2));

        while (onTrack) {
            // Get the lead ETE to the next point.
            if (!(lead == null)) {
                ETE = lead.getETE(nextLeadPoint);
            }

            // Check to see if the lead aircraft has reached the ETE to generate 
            // the next emitter.
            if (ETE <= nextEmitterTime) {
                // generate the emitter and choose an ETE for the next emitter.
                generateEmitter();
                nextEmitterTime = ETE - (1 + (Math.random() * 2));

                // If the ETE is less than zero, wait until the aircraft turns
                // before the while loop resumes.  
                if (nextEmitterTime <= 0) {
                    nextEmitterTime = 6 + nextEmitterTime;

                    // delays the while loop until the aircraft turns.
                    while (nextPoint == nextLeadPoint) {
                        threadDelay(THREAD_DELAY);
                    }

                    nextPoint = nextLeadPoint;
                }
            }

            // small delay to improve the performance of the simulation
            threadDelay(THREAD_DELAY);
        }
         //end while
    }
     // end emittersThread

    /**
     * Evacuates the lead RC12 if enemy ADA missile radar is
     * encountered.
     */
    private static void evacuate() {
        // Go to max speed (240 knots) and head south to friendly skies.
        lead.setNewSpeed(240);

        if (lead.getHeading() > 180) {
            lead.pivotLeft((int) lead.getHeading() - 180);
            lead.goForward();
        } else {
            lead.pivotRight(180 - (int) lead.getHeading());
            lead.goForward();
        }

        return;
    }
     // end evacuate

    /*
     * Creates an emitter in a random location in the top third of the simulation
     * screen.  There is a 1 in 11 chance that the emitter is 'Billy'.  There is
     * also a 1 in 10 chance that the emitter is an enemy ADA radar / missile.
     */
    private static void generateEmitter() {
        // Randomly choose one of the 11 frequencies in the list.
        emitterFreq = FREQS[Math.round((float) Math.random() * 10)];

        // Randomly choose emitter x-coordinate (max ~ 575)
        emitterXCord = Math.round((float) Math.random() * 575);

        // Random choose emitter y-coordinate (max ~ 300
        emitterYCord = Math.round((float) Math.random() * 300);

        // Converts x coordinate to Longitude based on operational graphics
        emitterLon = ((78.0 + ((79.0 * 3.0) / 436.0))
                     - ((((double) emitterXCord) * 3.0) / 436.0));

        // Converts y coordinate to Latitude based on operational graphics
        emitterLat = ((38.0 + (171.0 / 126.0))
                     - (((double) emitterYCord) / 126.0));

        // If Billy, create an emitter with Billy's image
        if (emitterFreq == FREQS[BILLY_FREQ_INDEX]) {
            createEmitter(emitterXCord, emitterYCord, BILLY);
            ;
        }
        // Else, 1 in 10 chance that the emitter will be an enemy missile
        else if (Math.random() <= 0.1) {
            radarOn = true;
            printLine("ENEMY ADA RADAR ALERT!");
            createMissile(emitterXCord, emitterYCord);

            return;
        } else
        // Otherwise, generate a standard emitter
         {
            createEmitter(emitterXCord, emitterYCord, EMITTER);
        }

        // draw lines to the emitter, simulating the intercept.	
        drawAzimuths();

        // Set the flags for emitter messages ready to transmit.	
        latToTransmit = true;
        lonToTransmit = true;
        freqToTransmit = true;
    }
     // end genrateEmitter

    /**
     * This method loops as a continuous thread while on track.
     * Basically, the  lead aircraft flies back and forth between
     * the start point and the end point.  In addition, the lead
     * sends a message to the synch aircraft if it turns before the
     * synch aircraft gets to its corresponding turning point on
     * track. Tracks are 12 miles long which takes 6 minutes  at 120
     * knots.
     */
    private static void leadTrackThread() {
        // Create a new RC12 on the lead track en route to the Start Point (SP).
        nextLeadPoint = LEAD_SP;
        onTrack = true;
        createLeadRC12();

        while (onTrack) {
            // Go forward until the SP is reached.
            while (lead.getPosition().getX() > LEAD_SP) {
                threadDelay(THREAD_DELAY);
            }

            // Send a turning message to the synch plane if they have not already 
            // reached the start point.
            if (synch.getHeading() == 270) {
                turningMessage = true;
            }

            // Turn 180 degrees and proceed back to the end Point (EP) 
            lead.pivotLeft(180);
            nextLeadPoint = LEAD_EP;
            lead.goForward();

            // Go forward until the end point is reached.
            while (lead.getPosition().getX() < LEAD_EP) {
                threadDelay(THREAD_DELAY);
            }

            // Send a turning message to the synch plane if they have not already 
            // reached the end point.
            if (synch.getHeading() == 90) {
                turningMessage = true;
            }

            // Turn 180 degrees and proceed back to the start point.
            lead.pivotRight(180);
            nextLeadPoint = LEAD_SP;
            lead.goForward();
        }
         // end while
    }
     // end startTrackMissionThread

    /*
     * Generates ETE messages from the lead RC12 for the synch RC12 at one
     * minute intervals based on the lead ETE.  In addition, the speed of
     * the lead aircraft is varied randomly between 105 and 135 knots before
     * each ETE message is generated.
     */
    private static void messagesThread() {
        onTrack = true;

        // Initialize the method variables
        double nextMessageTime = 5;
        double ETE = 6;
        int nextPoint = nextLeadPoint;
        int newSpeed = 120;

        // Generate messages while the lead is on track
        while (onTrack) {
            // Get the lead ETE to the next point.
            if (!(lead == null)) {
                ETE = lead.getETE(nextLeadPoint);
            }

            // Wait to generate the next message until one minute intervals
            // while the enemy ADA radar is not on.
            if ((ETE <= nextMessageTime) && !(radarWarning())) {
                // Randomly set the leads speed between 105 and 135 knots
                newSpeed = 120 - 15
                           + Math.round((float) Math.random() * 30);
                lead.setNewSpeed(newSpeed);

                // Send the current ETE message.
                ETEMessage = lead.getETE(nextLeadPoint);

                // Make sure both aircraft remain within 2 minutes of each other
                // with relation to their respective ETEs.
                if (Math.abs(ETEMessage
                                 - synch.getETE(nextSynchPoint)) > 2) {
                    // If synchronization is lost, evacuate the lead RC12 and
                    // stop the simulation.
                    printLine("Lost synchronization, return to base!");
                    evacuate();
                    onTrack = false;
                }

                // If the next message is to be sent at 1 minute from the next point,
                // wait until the aircraft has turned and wait to report the ETE
                // until one minute into the next leg.
                if (nextMessageTime == 1) {
                    nextMessageTime = 5;

                    while (nextPoint == nextLeadPoint) {
                        threadDelay(THREAD_DELAY);
                    }

                    nextPoint = nextLeadPoint;
                } else {
                    // Set the next ETE report for one minute later (ETE).
                    nextMessageTime -= 1;
                }
                 // end else
            }
             // end if

            // Delay the thread to improve the performance of the simulation.
            threadDelay(THREAD_DELAY);
        }
         // end while
    }
     // end message thread

    /**
     * This method loops as a continuous thread while on track and
     * autopilot is engaged.  Basically, the synch aircraft flies
     * back and forth between the  start point and the end point.
     */
    private static void synchTrackThread() {
        // Create a new RC12 on the synch track en route to the Start Point (SP).
        onTrack = true;
        nextSynchPoint = SYNCH_SP;
        createSynchRC12();

        while (onTrack) {
            // Go forward until the SP is reached.
            while (synch.getPosition().getX() >= SYNCH_SP) {
                threadDelay(THREAD_DELAY);
            }

            // If the autopilot is on, turn 180 degrees and proceed back to the EP 
            if (autopilotEngaged) {
                synch.pivotLeft(180);
            }

            nextSynchPoint = SYNCH_EP;
            synch.goForward();

            // Go forward until the EP is reached.
            while (synch.getPosition().getX() <= SYNCH_EP) {
                threadDelay(THREAD_DELAY);
            }

            // If the autopilot is on, turn 180 degrees and proceed back to the SP 
            if (autopilotEngaged) {
                synch.pivotRight(180);
            }

            nextSynchPoint = SYNCH_SP;
            synch.goForward();
        }
         // end while
    }
     // end startTrackMissionThread
}
 // end RC12Gui class
