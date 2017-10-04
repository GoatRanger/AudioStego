/*
 * Copyright (C) 2002 USMA This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU General Public License along with this program; if not,
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package eecs.robot.jago.element;


import java.awt.geom.Area;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import eecs.robot.jago.SimEngine;

/**
 * The basic object from which everything in a simulation is derived. This abstract class defines the absolute minimum
 * functionality that all elements in a simulation must have. In general, they must have a location and the ability to
 * react to simulation events.
 */
public abstract class SimElement extends Object implements Runnable {
  /** Constant indicating that the simulation has not started. */
  protected static final int NOT_STARTED = -1;

  static int currentID = 0;

  /**
   * The shape of the element, defined by an Area (not implemented in all SimElements)
   */
  public Area myShape = new Area(new java.awt.geom.Rectangle2D.Double());

  /** The size of this SimElement (defaults to 0 x 0) */
  public Dimension2D size = new java.awt.Dimension();

  /** The current location of this SimElement */
  public Point2D location = new java.awt.geom.Point2D.Double();

  /** The frame to which this SimElement belongs */
  protected SimEngine host = null;

  /** The thread used if this element must be threaded */
  protected Thread myThread = null;

  protected TimerListener listener;

  /** Flag indicating that this element belongs to a simulation */
  protected boolean added = false;

  /**
   * The weight of this <CODE>SimElement</CODE>, in pounds. As of v6.2.2, included for future use in adding physical
   * reality to collisions, but not implemented.
   */
  protected double weight = Double.POSITIVE_INFINITY;

  /**
   * The last time, in simulation time, that this element was updated
   */
  protected long lastUpdateTime = 0;

  private int id;

  public SimElement() {
    listener = new TimerListener();
    id = currentID;
    currentID++;
  }

  /**
   * Returns a copy of the shape of this SimElement, as an Area. If the SimElement does not support the Area model, will
   * return the bounding rectangle.
   * 
   * @return An Area that encompasses this SimElement
   */
  public Area getArea() {
    if (myShape == null) {
      createArea();
    }

    return (Area) myShape.clone();
  }

  public java.awt.geom.Rectangle2D getBounds2D() {
    if (myShape == null) {
      createArea();
    }

    return myShape.getBounds2D();
  }

  public Point2D getPosition() {
    return (Point2D) location.clone();
  }

  public Dimension2D getSize() {
    return (Dimension2D) size.clone();
  }

  public TimerListener getTimerListener() {
    return listener;
  }

  public double getWeight() {
    return weight;
  }

  /**
   * Notifies this SimElement where it was added, both by giving the SimEngine that is hosting it and the location in
   * the SimEngine's space.
   * 
   * @param sim
   *          The SimEngine that is hosting this SimElement
   * @param loc
   *          The location where this SimElement was added
   */
  public void addedTo(eecs.robot.jago.SimEngine sim, java.awt.geom.Point2D loc) {
    this.host = sim;
    location = loc;
    createArea();
    added = true;
    lastUpdateTime = NOT_STARTED;
  }

  /**
   * Determines if two elements are the same.
   */
  public boolean equals(Object o) {
    SimElement s = (SimElement) o;

    return (s.id == this.id);
  }

  /**
   * Returns <CODE>true</CODE> if this SimElement belongs to a simulation
   * 
   * @return A boolean flag indicating if this element was already added
   * @since 6.0
   */
  public boolean inASimulation() {
    return added;
  }

  /**
   * Notifies this SimElement that it is pending addition to a simulation
   */
  public void pendingAdd() {
    added = true;
  }

  /**
   * Notifies this SimElement that it was removed from the simulation
   */
  public void removedFromSim() {
    myThread = null;
    host = null;
    added = false;
  }

  /**
   * The actions that this element takes as it runs (for threads). The default is no action.
   */
  public void run() {
    //
  }

  /**
   * Returns a String representation of this SimElement.
   * 
   */
  public String toString() {
    return (this.getClass().getName() + "--Element ID: " + this.id);
  }

  /**
   * Creates the shape of this object. The default shape is a <CODE>Rectangle2D</CODE>, so this method should be
   * overridden for any objects with a non-rectangular shape.
   */
  protected void createArea() {
    myShape = new Area(new java.awt.geom.Rectangle2D.Double(location.getX(), location.getY(), size.getWidth(), size
        .getHeight()));
  }

  /**
   * The tasks to execute during a single time step of the simulation.
   */
  protected void runTaskStep() {
    //
  }

  /**
   * Attaches the specified object to this SimElement. Assumes that both objects are in the correct relative position at
   * the time of attachment. This does not change the functionality of either element; attachment only ensures that this
   * SimElement accounts for the new element during collisions.
   */
  void attach(Area newArea) {
    myShape.exclusiveOr(newArea);
  }

  /**
   * A class to manage timer events.
   */
  public class TimerListener implements java.awt.event.ActionListener {
    /**
     * Executes a single time step in the actions of this element.
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
      runTaskStep();
    }
  }
}