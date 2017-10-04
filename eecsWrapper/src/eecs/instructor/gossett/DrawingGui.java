package eecs.instructor.gossett;

import java.awt.geom.Point2D;
import java.io.IOException;

import jago.SimBox;
import jago.element.PolarPoint;
import jago.element.ReflectiveColors;
import jago.element.StraightLine;
import eecs.RobotGuiBase;
import eecs.jago.instructor.gossett.TurtleIcon;


/**
 * @author Karl A. Gossett
 *
 */
public class DrawingGui extends RobotGuiBase implements ReflectiveColors {
    static Point2D turtleLoc = new Point2D.Double(0,0);
    static double heading = 0;
    static String[] colors = {"BLACK","WHITE","LIGHT GRAY","MEDIUM GRAY","DARK GRAY","RED","BLUE","GREEN"};
    static int[] values = {BLACK, WHITE, LIGHT_GRAY, MED_GRAY, DARK_GRAY, RED, BLUE, GREEN};
    static int currentColor = 30;
    static int currentWidth = 1;
    static TurtleIcon turtle;
    static
    {
      getProperties();

      if (!runningTests) {
        sim = new SimBox(800,600);
        setSimulationTitle("IT105 Drawing Program");

        // Bypasses the RobotGuiBase file checks
        sim.setBackgroundTile(DEFAULT_IMAGE);

        // The follow should be in every GUI
        //  (unless a known, specific reason to omit)
        frame = sim; // so that all the windows from eecs.GuiBase will
                     // appear in the sim box

        sim.setVisible(true);
        turtle = new TurtleIcon();
        sim.add(turtle,0,0);
        // Ensures that the simulation does not have a robot
        robot = null;
      }
    }
    
    /** Draws a line between two points.
     * 
     * @param x1  The horizontal component of the start point.
     * @param y1  The vertical component of the start point.
     * @param x2  The horizontal component of the end point. 
     * @param y2  The vertical component of the end point.
     * @param color  The color to use. Uses the jago.ReflectiveColors colors.
     * @param width  The width of the line, in pixels.
     */
    public static void drawLine(double x1, double y1, double x2, double y2, int color, int width) {
      if (runningTests) {
        System.out.print("dl"+x1+","+y1+"-"+x2+","+y2+"c"+color+"w"+width);
      } else {
        sim.add(new StraightLine((int)x1,(int)y1,(int)x2,(int)y2,color,width),0,0);
        turtle.moveTo(x2, y2);
        pauseFor(100);
      } 
    }
    
    /** Moves to the specified location.
     * 
     * @param x1 The horizontal component of the destination.
     * @param y1 The vertical component of the destination.
     */
    public static void moveTo(double x1, double y1) {
      getProperties();
      PolarPoint newPpt = new PolarPoint(turtleLoc.getX(), turtleLoc.getY(), x1, y1);
      heading = newPpt.getDirection();
      turtleLoc.setLocation(x1,y1);
      
      if (runningTests) {
        System.out.print("m"+x1+","+y1);
      } else {
        turtle.turnTo(heading);
        turtle.moveTo(x1, y1);
        pauseFor(100);
      }
    }
    
    /** Turns the drawing icon to face in the specified direction.
     * 
     * @param newHeading   The new direction (in degrees, from up) to face.
     */
    public static void turnTo(int newHeading) {
      getProperties();
      heading = newHeading;
      if (runningTests) {
        System.out.print("tt"+newHeading);
      } else {
        turtle.turnTo(newHeading);
      }
    }
    
    /** Turns the drawing icon toward the left.
     * 
     * @param amt  The number of degrees to turn counterclockwise.
     */
    public static void turnLeft(double amt) {
      getProperties();
      heading -= amt;
      heading = (heading+360) % 360;
      if (runningTests) {
        System.out.print("tl"+round(amt,2));
      } else {
        for (int i=0;i<amt;i++) {
          turtle.turn(-1);
          pauseFor(10);
        }
      }
    }
    
    /** Turns the drawing icon toward the right.
     * 
     * @param amt The number of degrees to turn clockwise.
     */
    public static void turnRight(double amt) {
      getProperties();
      heading += amt;
      heading = heading % 360;
      if (runningTests) {
        System.out.print("tr"+round(amt,2));
      } else {
        for (int i=0;i<amt;i++) {
          turtle.turn(1);
          pauseFor(10);
        }
      }
    }
    
    private static double round(double num, int places) {
      return Math.round(num*Math.pow(10.0,places))/Math.pow(10.0,places);
    }
    
    public static int selectShape() {
      getProperties();

      if (runningTests) {
        int value = 0;
        try {
            String result = "0";
            result = testConsoleReader.readLine();
            value = Integer.parseInt(result);
          } catch (IOException e) {
            System.err.println("Error reading a selectShape int from test file (console)." +
              "  Format in input file was incorrect.");
          }
          return value;
      } else {
        String[] shapes = {"Quit","Line","Triangle","Polygon"};
        return chooseFromList("Select a shape:",shapes);
      }
    }
    
    /** Moves the drawing icon forward, leaving a drawn trail behind.
     * 
     * @param length  The distance to draw, in pixels.
     */
    public static void draw(double length) {
      getProperties();

      if (runningTests) {
        System.out.print("d"+round(length,2));
      } else {
        PolarPoint p = new PolarPoint(length, heading);
        double x = turtleLoc.getX();
        double y = turtleLoc.getY();
        drawLine(x, y, (x+p.getX()), (y+p.getY()), currentColor, currentWidth);
        moveTo(x+p.getX(), y+p.getY());
        turtle.moveTo(turtleLoc.getX(),turtleLoc.getY());
        pauseFor(300);
      }
    }
    
    /**
     * 
     */
    private static void pauseFor(int ms) {
      try {
        Thread.sleep(ms);
      } catch (InterruptedException ioe) {}
    }

    /** Draws a line between the current location and the specified one.
     * 
     * @param x2  The desired horizontal end position.
     * @param y2  The desired vertical end position.
     */
    public static void drawTo(double x2, double y2){
      PolarPoint newPpt = new PolarPoint(turtleLoc.getX(), turtleLoc.getY(), x2, y2);
      heading = newPpt.getDirection();
      if (runningTests) {
        System.out.print("dt"+x2+","+y2);
      } else {
        drawLine(turtleLoc.getX(),turtleLoc.getY(),x2,y2,currentColor, currentWidth);
        turtleLoc.setLocation(x2,y2);
        turtle.moveTo(turtleLoc.getX(),turtleLoc.getY());
        pauseFor(300);
      }
    }
    
    /** Returns a value for the color selected by the user. If the selection is
     * cancelled, defaults to black.
     * 
     * @return the color value, from jago.ReflectiveColors.
     */
    public static int getColor() {
      getProperties();

      if (runningTests) {
        int value = 0;
        try {

            String result = "0";
            result = testConsoleReader.readLine();
            value = Integer.parseInt(result);
          } catch (IOException e) {
            System.err.println("Error reading a getColor int from test file (console)." +
              "  Format in input file was incorrect.");
          }
          return value;
      } else {
        int val = chooseFromList("Select a Color",colors);
        if (val == -1) val = 0;
        return values[val];
      }
    }
    
    /** Changes the drawing color.  All lines drawn after issuing this
     * command will be drawn in the new color unless otherwise specified.
     * 
     * @param color  The color to use, as a value from ReflectiveColors.
     */
    public static void setDrawColor(int color) {
      if (runningTests) {
        System.out.print("c"+color);
      }
      currentColor = color;
    }
    
    /** Sets the width of the drawing "brush".
     * 
     * @param width The width to use, in pixels.
     */
    public static void setDrawWidth(int width) {
      if (runningTests) {
        System.out.print("w"+width);
      }
      currentWidth = width;
    }
}
