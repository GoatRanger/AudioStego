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
 * DistanceRobotGui.java
 *
 * Created on March 1, 2003, 1:25 PM
 */
package eecs.instructor.gossett;

import jago.element.*;

import eecs.simulation.*;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 * @version 0.1
 */
public class DistanceRobotGui extends eecs.RobotGuiBase {
    /** DOCUMENTATION INCOMPLETE */
    public static final int CONVEX = 1;

    /** DOCUMENTATION INCOMPLETE */
    public static final int CONCAVE = -1;
    
    static double pixelsPerInch = 7.08;
    
    static int originX = 330;
    static int originY = 440;

    static {
        sim = new eecs.simulation.PolygonSim();
        setSimulationTitle("IT105 Final Project");
        
        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will
                     // appear in the sim box
        sim.setVisible(true);
        sim.add(new ColoredLine(5,5,BLACK),originX, originY);
        // Ensures that the simulation does not have a robot
        robot = null;
    }

    /** Creates a polygon in the simulation that is concave */
    public static void setConcavePolygon() {
        ((PolygonSim)sim).createPolygon(false, false);
    }
    
    /** Creates a polygon in the simulation that is convex.  Only one vertex
     *  will be concave, and it will be marked with a red vertex.
     */
    public static void setConvexPolygon() {
        ((PolygonSim)sim).createPolygon(false, true);
    }
    
    /** Creates a polygon that may be either concave or convex.  The constraints are the
     *  same as specified in {@link #setConcavePolygon()} and {@link setConvexPolygon}
     */
    public static void setRandomPolygon() {
        ((PolygonSim)sim).createPolygon(true, true); // Second param ignored
    }
    
    /**
     * Returns the current location of the robot in the x direction,
     * as a double value.
     *
     * @return DOCUMENTATION INCOMPLETE
     */
    public static double getMyX() {
        if (robot != null) {
            return Math.round(robot.getDistanceMoved().getX() * 10.0) / 10.0;
        } else {
            return 0;
        }
    }

    /**
     * Returns the current location of the robot in the y direction,
     * as a double value.
     *
     * @return The current y location, as a double value.
     */
    public static double getMyY() {
        if (robot != null) {
            return Math.round(robot.getDistanceMoved().getY() * 10.0) / 10.0;
        } else {
            return 0;
        }
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @return DOCUMENTATION INCOMPLETE
     */
    public static int[] getXArray() {
        int[] points = ((PolygonSim) sim).getXPoints();
        // The points are kept as pixel locations, so they must be converted
        // to inch measurements, from the translated origin
        for (int i=0; i < points.length; i++) {
            points[i] =(int)((points[i]-originX) / pixelsPerInch);
        }
//        for (int i=0; i<points.length; i++)
//            printLine("-( "+points[i]+" )");
        return points;
    }

    // end static initializer
    public static int getXPoint(int index) {
        int[] points = ((PolygonSim) sim).getXPoints();

        if (index > points.length) {
            String indexExt = "th";

            switch (index) {
                case 1:
                    indexExt = "st";

                    break;

                case 2:
                    indexExt = "nd";

                    break;

                case 3:
                    indexExt = "rd";

                    break;

                default:
                    indexExt = "th";

                    break;
            }

            showError("Tried to get the x coordinate for the "
                      + index + indexExt
                      + "vertex, but the polygon does not have "
                      + "that many vertices (remember the vertices are numbered "
                      + "starting at 0).");
            System.exit(0);
        }
//        printLine("( "+(points[index]-originX)/pixelsPerInch+" )");
        // The points are kept as pixel locations, so they must be converted
        // to inch measurements, from the translated origin
        return (int)((points[index]-originX)/pixelsPerInch);
    }

    /**
     * Returns the Y values of the vertices of the polygon, as an array of 
     * integers.
     *
     * @return DOCUMENTATION INCOMPLETE
     */
    public static int[] getYArray() {
        int[] points = ((PolygonSim) sim).getYPoints();
        // The points are kept as pixel locations, so they must be converted
        // to inch measurements, from the translated origin
        for (int i=0; i < points.length; i++) {
            points[i] = (int)((originY-points[i]) / pixelsPerInch);
        }
//        for (int i=0; i<points.length; i++)
//            printLine("-( "+points[i]+" )");
        return points;
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param index DOCUMENTATION INCOMPLETE
     *
     * @return DOCUMENTATION INCOMPLETE
     */
    public static int getYPoint(int index) {
        int[] points = ((PolygonSim) sim).getYPoints();

        if (index > points.length) {
            String indexExt = "th";

            switch (index) {
                case 1:
                    indexExt = "st";

                    break;

                case 2:
                    indexExt = "nd";

                    break;

                case 3:
                    indexExt = "rd";

                    break;

                default:
                    indexExt = "th";

                    break;
            }

            showError("Tried to get the y coordinate for the "
                      + index + indexExt
                      + "vertex, but the polygon does not have "
                      + "that many vertices (remember the vertices are numbered "
                      + "starting at 0).");
            System.exit(0);
        }
//        printLine("( "+(originY-points[index])/pixelsPerInch+" )");
        // The points are kept as pixel locations, so they must be converted
        // to inch measurements, from the translated origin
        return (int)((originY-points[index])/pixelsPerInch);
    }

    /**
     * Given the correct test pad, calibrates the robot for both
     * rotation and forward/backward movement.  Upon completion, this will
     * move the robot to originX, originY, with a heading of 25.
     */
    public static void calibrate() {
        ((TimingNavigatorRobot) robot).calibrateRotation();
        goForward();

        // Make sure it's not still on the rotation calibration line
        while (getReflectanceSensor() <= 36) {}

        halt();
        pause(3000);
        ((TimingNavigatorRobot) robot).calibrateDistance(24);

//        double scale = ((TimingNavigatorRobot) robot)
//                       .getMovementScale();
        //System.err.println("scale " + scale);
        pause(1000);
        java.awt.geom.Point2D loc = robot.getLocation();
        double error = (originX-loc.getX())+(originY-loc.getY());
        //System.err.println("error " + error);
        while (error > 3 ) {
            
            //System.err.println("Error " + error);
            pivotTowardOrigin();

            goForward(100);
        
            loc = robot.getLocation();
            // Determine relative points
//            double x1 = originX - loc.getX();
//            double y1 = originY - loc.getY();
            error = Math.abs(originX-loc.getX())+Math.abs(loc.getY() - originY);
            //error = 0;
            //System.err.println("Now Going relative ("+x1 + ", " + y1 +")");
        }
        while (robot.getHeading() > 25) {
            pivotLeft(1);
        }
        while (robot.getHeading() < 25) pivotRight(1);
/*        destroyRobot();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        // After recreating the robot, need to set its movement scale to the
        // one just discovered
        createRobot(originX, originY, 25);
        ((TimingNavigatorRobot) robot).setMovementScale(scale);
*/
        ((TimingNavigatorRobot)robot).resetLocation();
    }
    
    protected static void pivotTowardOrigin() {
        java.awt.geom.Point2D loc = robot.getLocation();
        // Determine relative points
        double x1 = originX - loc.getX();
        double y1 = originY - loc.getY();

        //System.err.println("Going relative ("+x1 + ", " + y1 +")");
        // Calculate angle to go to:
        double angle = Math.atan2(y1, x1);

        
        int heading = (int)robot.getHeading();
//        double old = angle;
        angle = ((Math.toDegrees(angle)+90)-heading)%360;
        //System.err.println("Turning by " + angle+"degrees, currently " + heading + ", to get to " + Math.toDegrees(old));
        if (angle > 0) {
            pivotRight((int)angle);
        } else {
            pivotLeft(-(int)angle);
        }
    }

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
    public static void createRobot(int x, int y, int angle) {
        checkAngle("createRobot", angle);

        if (robot != null) {
            showError("Robot already exists.  Cannot create a new one.");
            System.exit(0);
        }

        robot = new TimingNavigatorRobot();
        robot.addSensor(LegoRobot.RCX_3, RobotSensors.HIT);
        robot.addSensor(LegoRobot.RCX_2, RobotSensors.TOUCH);

        robot.setPower(10);
        robot.setInitialHeading(angle);

        if (realism != IDEALISTIC) {
            robot.setRealismLevel(realism);
        }

        sim.add(robot, x, y);
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param x DOCUMENTATION INCOMPLETE
     * @param y DOCUMENTATION INCOMPLETE
     */
    public static void gotoPoint(double x, double y) {
        ((TimingNavigatorRobot) robot).gotoPoint((float) x, (float) y);
    }

    /**
     * Allows the user's robot to transmit the results.  In the
     * simulation, this method is essentially a dummy action, since
     * it does not allow any communication with the
     * &quot;server&quot; robot (all computations and responses come
     * from this GUI, and not from the other robot).
     *
     * @param type The type of polygon, either CONVEX or CONCAVE.
     * @param x The x coordinate of the concave vertex.  If the
     *        polygon is convex, this value has no meaning.
     * @param y The y coordinate of the concave vertex.  If the
     *        polygon is convex, this value has no meaning.
     */
    public static void transmitResult(int type, int x, int y) {
        printLine("Rcv " + x);
        printLine("Rcv " + y);

        ((PolygonSim) sim).dataComplete();

        /* TODO: Add the verification of the point transmitted. and display
         * a message.
         */
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param inches DOCUMENTATION INCOMPLETE
     */
    public static void travelBackward(int inches) {
        ((TimingNavigatorRobot) robot).travelBackward(inches);
    }

    /**
     * DOCUMENTATION INCOMPLETE
     *
     * @param inches DOCUMENTATION INCOMPLETE
     */
    public static void travelForward(int inches) {
        ((TimingNavigatorRobot) robot).travelForward(inches);
    }
}
