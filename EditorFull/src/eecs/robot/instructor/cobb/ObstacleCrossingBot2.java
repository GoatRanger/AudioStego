package eecs.robot.instructor.cobb;

import eecs.robot.finalProject.finalProject051.WaterElement;
import eecs.robot.jago.element.Collidable;
import eecs.robot.jago.element.Concertina;
import eecs.robot.jago.element.Destructible;
import eecs.robot.jago.element.Fire;
import eecs.robot.jago.element.StandardRobot;
import eecs.robot.jago.element.Wall;
import java.applet.Applet;
import java.applet.AudioClip;
import javax.swing.JOptionPane;

public class ObstacleCrossingBot2 extends StandardRobot implements Destructible {
  boolean correctlyCrossedConcertina = true;
  boolean correctlyCrossedFire = true;
  boolean hasCrossedConcertina = false;
  boolean isFirstTime = true;
  boolean bridgeBreached = false;
  private long timeDestroyed;
  private AudioClip sound2;
  int x1 = -1;
  int x2 = -1;
  int y1 = -1;
  int y2 = -1;

  public ObstacleCrossingBot2() {
    super();
    sound2 = Applet.newAudioClip(ObstacleCrossingBot2.class
        .getResource("/eecs/sounds/explosion2.au"));
  }

  public boolean getBridgeBreached() {
    return this.bridgeBreached;
  }

  public void setDestroyed() {
    state = Destructible.DESTROYED;
    setAppearance("/eecs/images/bang.gif");
  }

  public boolean isDestroyed() {
    return (state == Destructible.DESTROYED);
  }

  public void handleCollision(Collidable element) {
    if (element instanceof Wall) {
      super.handleCollision(element);
    }
    else
      if (element instanceof WaterElement) {
        System.out.println("Drove into the water!");
        setDestroyed();
        sound2.play();
        super.handleCollision(element);
        JOptionPane.showMessageDialog(null, "This robot can not swim.  You have just cost the government $55 Million",
            "Terminating program....", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
      }
      else
        if (element instanceof Fire) {
          System.err.println("Drove into the fire!");
          sound2.play();
          correctlyCrossedFire = false;
          setPower(1);
          this.timeLeftToMove = 10;
          setDestroyed();
          JOptionPane.showMessageDialog(null, "This Robot is on Fire-like the Red Sox!", "Terminating program....",
              JOptionPane.ERROR_MESSAGE);
          System.exit(0);
        }
        else
          if (element instanceof Concertina) {
            setPower(2);
            correctlyCrossedConcertina = false;
            if (!hasCrossedConcertina) {
              hasCrossedConcertina = true;
              //sound.play();
              System.out.println("Drove through the concertina!");
              Thread t = new Thread(new Runnable() {
                public void run() {
                  int timeTravel = 1;
                  timeTravel = timeLeftToMove;
                  for (int i = 0; i < 10; i++) {
                    //System.out.println("inside concertina loop");
                    if (Math.random() < .5) {
                      int temp = (int) Math.round(Math.random() * 10);
                      pivotLeft(temp);
                      //System.out.println("pivot left"+temp);
                    }
                    else {
                      int temp = (int) Math.round(Math.random() * 10);
                      pivotRight(temp);
                      // System.out.println("pivot right"+temp);
                    }
                    goForward(25);
                  }
                  if (!(timeTravel > 0)) {
                    goForward();
                  }
                  else {
                    goForward(timeTravel);
                  }
                }
              });
              t.start();
            }
          }
  }

  public void updatePosition() {
    long timeElapsed = 0;
    timeElapsed = System.currentTimeMillis() - timeDestroyed;
    if ((state == DESTROYED) && (timeElapsed > 30)) {
      host.remove(this);
    }
    else {
      super.updatePosition();
    }
  }

  public void gotoAngle(float angle) {
    double difference = angle - velVector.getDirection();
    while (difference > 180) {
      difference = difference - 360; // shortest path to goal angle
    }
    while (difference < -180) {
      difference = difference + 360; // shortest path to goal angle
    }
    if (difference >= 0) {
      pivotRight((int) difference);
    }
    else {
      pivotLeft((int) -difference);
    }
  }

  public int getX1() {
    return x1;
  }

  public void setX1(int x1) {
    this.x1 = x1;
  }

  public int getX2() {
    return x2;
  }

  public void setX2(int x2) {
    this.x2 = x2;
  }

  public void gotoPoint(double x, double y) {
    double x1 = 0;
    double y1 = 0;
    double inchDistance = 10;
    double angle = 0;
    while (inchDistance > 5) {
      x1 = x - getLocation().getX();
      y1 = y - getLocation().getY();
      angle = Math.atan2(y1, x1);
      if (y1 != 0) {
        inchDistance = y1 / Math.sin(angle);
      }
      else {
        inchDistance = x1 / Math.cos(angle);
      }
      angle = Math.toDegrees(angle) + 90;
      gotoAngle((float) angle);
      goForward(75);
    }
  }

  public int getY1() {
    return y1;
  }

  public void setY1(int y1) {
    this.y1 = y1;
  }

  public int getY2() {
    return y2;
  }

  public void setY2(int y2) {
    this.y2 = y2;
  }

  public boolean hasCorrectlyCrossedConcertina() {
    return correctlyCrossedConcertina;
  }

  public void setCorrectlyCrossedConcertina(boolean correctlyCrossedConcertina) {
    this.correctlyCrossedConcertina = correctlyCrossedConcertina;
  }

  public boolean hasCorrectlyCrossedFire() {
    return correctlyCrossedFire;
  }

  public void setCorrectlyCrossedFire(boolean correctlyCrossedFire) {
    this.correctlyCrossedFire = correctlyCrossedFire;
  }
}