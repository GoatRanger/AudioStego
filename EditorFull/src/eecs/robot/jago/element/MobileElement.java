package eecs.robot.jago.element;

import java.awt.geom.AffineTransform;

/**
 * <P>
 * This class provides a skeletal implementation of an element that implements the <CODE>Collidable, Mobile,</CODE>
 * and <CODE>Visible</CODE> interfaces.
 * </p>
 * <P>
 * To implement a mobile element, the user needs only to extend this class and provide a method that implements <CODE>
 * handleCollision</CODE>. The other abstract methods are optional to allow extended functionality.
 * </p>
 * <P>
 * The motion of elements is replicated at scale values when in realistic mode. In the simulation, this means that using
 * the simulation default scaling (see {@link eecs.robot.jago.SimEngine}), any class based on a MobileElement must ensure that the
 * value for <CODE>power</CODE> is set to m/s. The position management of the <CODE>MobileElement</CODE> will manage
 * the adjustment due to scale, but assumes that the power settings are m/s. If the <CODE>updatePosition</CODE> method
 * is overridden, ensure you account for this assumption.
 * </p>
 */
public abstract class MobileElement extends VisibleElement implements Collidable, Mobile {
  /** The maximum speed of any MobileElement, in m/s */
  public static int MAX_SPEED = 100;

  /** The minimum speed of any MobileElement, in m/s */
  public static int MIN_SPEED = 0;

  /** The scale of the simulation. Based on 13cm = 30 pixels */
  protected double SIMULATION_SCALE = 3000.0 / 13.0;

  /** The current angular velocity (due to turning) measured in m/s */
  protected double angularVelocity;

  /** The distance between the propulsion devices */
  protected double axleLength;

  /** A flag indicating if this element has had it's initial heading set */
  protected boolean headingSet = false;

  protected Propulsion leftWheel;

  /** If the element is currently being checked for a possible collision */
  protected boolean pendingCollision = false;

  /**
   * The current power of this MobileElement, in m/s. Increasing the power will only be visible when the MobileElement
   * is moving (power does not <I>cause </I> the MobileElement to move), since power is the <I>capability </I>, not the
   * actual movement.
   */
  protected double power;

  /** The last valid location of the MobileElement */
  public java.awt.geom.Point2D previousLocation;

  /** The Area of this MobileElement during the last successful draw */
  public java.awt.geom.Area previousShape;

  /** The previous velocity vector for this element. */
  public PolarPoint previousVel;

  protected java.util.ArrayList propulsionDevice = null;

  protected Propulsion rightWheel;

  /**
   * The amount of time (in ms) that this MobileElement still must move. This field is used to prevent a MobileElement
   * from moving farther than directed, based on thread timing.
   */
  public int timeLeftToMove = 0;

  /** The current velocity vector for this element */
  public PolarPoint velVector;

  /**
   * Creates new MobileElement
   */
  public MobileElement() {
    super();
    appearance = null;
    velVector = new PolarPoint();
    previousVel = new PolarPoint();
    angularVelocity = 0;
    power = 0;
    axleLength = 35;
    previousLocation = new java.awt.geom.Point2D.Double();
    previousLocation.setLocation(location);

  }

  /**
   * Returns the angular velocity of this element.
   * 
   * @return DOCUMENT ME!
   */
  public double getAngularVelocity() {

    return angularVelocity;
  }

  /**
   * Sets the picture to the image identified by the URL provided.
   * 
   * @param name
   *          The URL of the picture to use.
   */
  public void setAppearance(String name) {
    appearance = eecs.robot.jago.ImageLoader.getImage(this, name);

    if (appearance != null) {
      size = new java.awt.Dimension(appearance.getWidth(host), appearance.getHeight(host));
    }
    createArea();
  }

  /**
   * Gets the current heading.
   * 
   * @return The current heading of this element.
   */
  public double getHeading() {

    return velVector.getDirection();
  }

  /**
   * Sets the initial heading (in degrees) of the MobileElement. If successful, this method returns the heading. Heading
   * requests less than 0 or greater than 360 are accepted. If the robot has been added to a simulation, moved, or the
   * heading was already set, this method denies the request.
   * 
   * @param heading
   *          The requested heading
   * @return the new heading of the robot (value 0-360). Return <B>-1 </B> if the heading was already set (so the
   *         request fails)
   */
  public int setInitialHeading(int heading) {

    int setting = -1;

    // Ensure the heading is in the range 0-360
    while (heading < 0) {
      heading += 360;
    }

    if (!headingSet) {
      setting = heading % 360;
      velVector.setDirection(setting);
      previousVel.setDirection(setting);
    }

    headingSet = true;

    return setting;
  }

  /**
   * Sets the new power of the mobile element, in m/s. Power is the potential velocity of the element, not the
   * instantaneous velocity.
   * 
   * @param level
   *          DOCUMENT ME!
   */
  public void setPower(double level) {

    if (level > MAX_SPEED) {
      level = MAX_SPEED;
    }

    if (level < MIN_SPEED) {
      level = MIN_SPEED;
    }

    power = level;
  }

  /**
   * Returns the last location of a Collidable element.
   * 
   * @return DOCUMENT ME!
   */
  public java.awt.geom.Point2D getPreviousPosition() {

    return (java.awt.geom.Point2D) previousLocation.clone();
  }

  /**
   * Gets the current velocity.
   * 
   * @return The current velocity of the element, in m/s
   */
  public double getVelocity() {

    return velVector.getMagnitude();
  }

  /**
   * Notifies this <CODE>MobileElement</CODE> that it was added to the specified <CODE>SimEngine</CODE> at the
   * location given. Once it has been added, the initial heading is fixed (calls to <CODE>setInitialHeading</CODE>
   * will be ignored). This method is only called by the SimEngine that is acknowledging addition of this element.
   * 
   * @param sim
   *          The SimEngine where this element was added
   * @param loc
   *          The location in the SimEngine where it was added
   */
  public void addedTo(eecs.robot.jago.SimEngine sim, java.awt.geom.Point2D loc) {
    super.addedTo(sim, loc);
    headingSet = true;
  }

  /**
   * Determines the way this object bounces off another Collidable object
   * 
   * @param collisionElement
   *          The element to bound off of
   */
  public void bounceOff(Collidable collisionElement) {

    // Standard Implementation--no bounce
  }

  /**
   * Draws this MobileElement.
   * 
   * @param g2
   *          The <code>Graphics2D</code> context.
   */
  public void draw(java.awt.Graphics2D g2) {

    java.awt.Shape oldClip = g2.getClip();

    // Ensure it only draws inside the bounding rectangle
    if (myShape != null) {
      g2.clip(myShape);
    }

    AffineTransform transform = new AffineTransform();

    // Rotate the image according to its azimuth
    transform.rotate(Math.toRadians(velVector.getDirection()), location.getX() + size.getWidth() / 2.0, location.getY()
        + size.getHeight() / 2.0);

    // and move it to the correct location
    transform.translate(location.getX(), location.getY());

    // Draw it in the graphics context, using the rotation transform
    if (appearance != null) {
      g2.drawImage(appearance, transform, host);
    }
    else {
      g2.draw(myShape);
    }

    // Need to make sure the clipping area is returned to original state
    g2.setClip(oldClip);
  } /* draw */

  /**
   * React to a collision with another Collidable object
   * 
   * @param collisionElement
   *          The <CODE>Collidable</CODE> element that this element collided with.
   */
  public void handleCollision(Collidable collisionElement) {
    //
  }

  /**
   * Undocumented
   * 
   * @param pending
   *          Empty
   */
  public synchronized void pendingCollision(boolean pending) {
    pendingCollision = pending;

    if (!pendingCollision) {
      notifyAll();
    }
  }

  /**
   * Updates the current location based on velocity and heading. This method will ensure the mobile object does not move
   * more than the time specified in its move methods as long as <CODE>timeLeftToMove</CODE> is set to the amount of
   * time to move.
   * <P>
   * For example, if the element has a method declared as:
   * </p>
   * <BR>
   * <CODE>public void move(int milliseconds)</CODE><BR>
   * <P>
   * then the move method should set
   * </p>
   * <CODE>timeLeftToMove = milliseconds</CODE><BR>
   */
  public void updatePosition() {
    if (host == null || pendingCollision) {

      return;
    }

    long deltaTime = 0;

    if (lastUpdateTime == NOT_STARTED) {
      lastUpdateTime = host.elapsedTimeMillis();
    }

    long elapsedTime = host.elapsedTimeMillis();
    deltaTime = (elapsedTime - lastUpdateTime);
    lastUpdateTime = elapsedTime;

    // Prevent this MobileElement from moving more than the time specified
    // Elements that extend the MobileElement must ensure timeLeftToMove
    // is properly set to allow this function.
    if (timeLeftToMove > 0) {

      if (timeLeftToMove < deltaTime) {

        if (timeLeftToMove < 5) {
          deltaTime = 5;
        }
        else {
          deltaTime = timeLeftToMove;
        }

        timeLeftToMove = 0;
      }
      else {
        timeLeftToMove -= (int) deltaTime;
      }
    }

    // Save the current location
    previousLocation.setLocation(location);
    previousShape = (java.awt.geom.Area) myShape.clone();

    PolarPoint savedVel = (PolarPoint) previousVel.clone();

    /**
     * Propulsion interface stuff. Left here for future use, but the following seems to work (pivoting might need some
     * adjustment)
     */

    /** End of Propulsion interface stuff. */

    // Determine component distances to move based on velocity
    double xMove = (deltaTime / 1000.0) * SIMULATION_SCALE * velVector.getX();
    double yMove = ((deltaTime / 1000.0) * SIMULATION_SCALE * velVector.getY());

    if (deltaTime == 0) {
      angularVelocity = 0;
    }
    else {

      double tempHeading = velVector.getDirection();
      double tempOldHeading = savedVel.getDirection();

      // Instantaneous velocity = change/time * radius

      /*
       * If the robot rotated past 0 on this update, adjust to prevent incorrect large readings
       */
      if (Math.abs(tempHeading - tempOldHeading) > 360) {

        if (tempHeading < tempOldHeading) {
          tempHeading += 360;
        }
        else {
          tempOldHeading += 360;
        }
      }

      // Divide by 4 is a hack so the angular ~ linear for a given power
      angularVelocity = (tempHeading - tempOldHeading) / deltaTime * size.getHeight() / 2 / 4;
    }

    AffineTransform at = new AffineTransform();
    at.rotate(Math.toRadians(velVector.getDirection() - savedVel.getDirection()), location.getX() + size.getWidth()
        / 2.0, location.getY() + size.getHeight() / 2.0);
    at.translate(xMove, yMove);
    myShape.transform(at);

    // Move the specified amount by translating the current location
    location.setLocation(location.getX() + xMove, location.getY() + yMove);
  }

  /**
   * Undocumented
   */
  protected void createArea() {
    myShape = new java.awt.geom.Area(new java.awt.geom.Rectangle2D.Double(location.getX(), location.getY(), size
        .getWidth(), size.getHeight()));

    AffineTransform at = new AffineTransform();
    at.rotate(Math.toRadians(velVector.getDirection()), location.getX() + size.getWidth() / 2.0, location.getY()
        + size.getHeight() / 2.0);
    myShape.transform(at);
    at.setToIdentity();
    previousShape = (java.awt.geom.Area) myShape.clone();
    previousVel = (PolarPoint) velVector.clone();
    previousLocation.setLocation(location);
  }

  /**
   * Perform one step of execution of this element
   */
  protected void runTaskStep() {
    updateVelocity();
    updatePosition();
  }

  /**
   * Determines the new velocity of the mobile element. Classes derived from a MobileElement must provide their own
   * functionality, as this class does not provide any implementation.
   */
  protected void updateVelocity() {
    //
  }
}