package eecs.robot.simulation;

public class Solution042 extends FinalProject042 {
  static String divisionMessage;

  public static void breachObstacleBelt() {
    int counter = 0;
    goForward();
    while (counter < 2) {
      //identify obstacle
      if (getReflectanceSensor() == 48) {
        //breach fire
        goForward(2000);
        int yPosition = getMyYPosition();
        gotoPoint(100, yPosition);
        pivotTo(90);
        goForward();
        while (getTemperature() > 90) {
          //
        }
        halt();
        pivotTo(0);
        while (getReflectanceSensor() == 48) {
          goForward();
        }
        goForward(1000);
        counter++;
      }
      else
        if (getReflectanceSensor() == 49) {
          //breach concertina
          while (getReflectanceSensor() == 49) {
            //goForward();
            if (getDualLightSensor() > 0) {
              pivotRight(3);
              goForward(500);
            }
            else {
              pivotLeft(3);
              goForward(500);
            }
          }
          halt();
          goForward(1000);
          counter++;
        }
    }
  }

  public static void findBlackLine() {
    while (!(getReflectanceSensor() > 32 && (getReflectanceSensor() < 34))) {
      goForward();
    }
    halt();
  }

  public static void followBlackLine() {
    while (getMyYPosition() < 350) {
      if (getReflectanceSensor() == 33) {
        pivotRight(5);
      }
      else
        if (getReflectanceSensor() > 33 && getReflectanceSensor() < 50) {
          goForward();
        }
        else {
          pivotLeft(5);
        }
    }
  }

  public static void getMessageFromTower() {
    int myPrivateKey = getMyPrivateKey();
    String msg = getAssemblyAreaInfo();
    divisionMessage = decryptMessage(myPrivateKey, msg);
    System.out.println(divisionMessage);
  }

  public static void writeXHTML() {
    openWriteFile("bypass.htm");
    writeLine("<?xml version=\"1.0\" encoding=\"UTF-8\"' ?>");
    writeLine("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN \"");
    writeLine("    \"http://www.w3.org/tr/xhtml1/dtd/xhtml1-strict.dtd\">");
    writeLine("	<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" ");
    writeLine(" <head>");
    writeLine("   <title>");
    writeLine("   This is my second web page.");
    writeLine("  </title>");
    writeLine(" </head>");
    writeLine(" <body>");
    writeLine("    <p>");
    writeLine("      Look at the");
    writeLine("   <!-- wild and crazy -->");
    writeLine("      title bar of this window.");
    writeLine("      We can't see the words 'wild and crazy'?");
    writeLine("    </p>");
    int counter = 0;
    writeLine("<ul>");
    while (counter < 3) {
      writeLine("<li>");
      writeLine(counter);
      writeLine("</li>");
      counter++;
    }
    writeLine("</ul>");
    writeLine(" </body>");
    writeLine("</html>");
    closeWriteFile();
  }

  public static void gotoAA() {
    if (divisionMessage.equalsIgnoreCase("AA Eagle")) {
      gotoPoint(600, 550);
    }
    else
      if (divisionMessage.equalsIgnoreCase("AA River")) {
        gotoPoint(700, 550);
      }
      else
        if (divisionMessage.equalsIgnoreCase("AA Cobra")) {
          gotoPoint(800, 550);
        }
        else
          System.out.println("oops");
  }

  public static void main(String[] args) {
    int[] p = new int[4];
    p = getPolarCoordinates();
    double x = 500 + Math.sin(Math.toRadians(p[0])) * p[1];
    double y = 10 - Math.cos(Math.toRadians(p[0])) * p[1];
    gotoPoint(x, y);
    x = 500 + Math.sin(Math.toRadians(p[2])) * p[3];
    y = 10 - Math.cos(Math.toRadians(p[2])) * p[3];
    gotoPoint(x, y);
    findBlackLine();//
    followBlackLine(); // this should bypass the water as well.
    getMessageFromTower();
    gotoAA();
    writeXHTML();
    transmitMissionComplete();
  }
}