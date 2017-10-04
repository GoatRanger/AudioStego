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

package jago;

import jago.element.*;

import java.awt.event.*;
import java.awt.geom.*;

import java.util.*;


/**
 * This class is responsible for managing the link between the SimElements in
 * the simulation, and the SimEngine itself.
 */
public class ElementManager
    implements ActionListener,
               Realism,
               Runnable {

    /**
     * Performs a comparison on two SimElements based on their horizontal (x)
     * position.  This Comparator is used when sorting the vector prior to
     * detecting collisions.
     */
    static final Comparator positionCompare = new Comparator() {
        public int compare(Object o1, Object o2) {

            SimElement s1 = (SimElement)o1;
            SimElement s2 = (SimElement)o2;
            double xLoc = s1.getPosition()
                            .getX();
            double x2Loc = s2.getPosition()
                             .getX();

            return (int)(xLoc - x2Loc);
        }

        public boolean equals(Object obj) {

            return obj.equals(this);
        }
    };

    protected List addQueue = new ArrayList();

    /**
     * The list of elements that will be removed when during the next update
     * of the simulation.
     */
    protected List deleteQueue = new ArrayList();

    /**
     * The list of sources of light in the simulation.
     *
     * @see <CODE>LightSource</CODE>
     */
    protected Collection lightSources = new ArrayList();

    /** The list of mobile elements currently alive in the sim */
    protected Collection mobileElements = new ArrayList();
    /** The list of static (nonmobile) elements currently alive. */
    protected Collection staticElements = new ArrayList();

    /**
     * Flag if the vector of static elements has been modified since the last
     * check (used by the DisplayManager)
     */
    boolean staticChanged = false;

    /**
     * The class responsible for detecting collisions between two SimElements
     */
    private CollisionDetector collisionDetector;
    private int freeChecks = 10;

    /** A reference back to the SimEngine this manager is servicing. */
    private SimEngine owner;

    /** A flag to allow updating of realism during the next action cycle. */
    private boolean realismChanged = false;

    /** The level of realism of the elements in the simulation. */
    private int realismLevel = IDEALISTIC;

    /**
     * If all elements should be removed from the simulation, this is
     * <CODE>true</CODE>
     */
    private boolean removeAllSimElements = false;
    private Thread thread = null;

    /**
     * Creates a new ElementManager with the specified SimEngine as the owner.
     *
     * @param theOwner  The SimEngine that this <CODE>ElementManager</CODE>
     *        updates.
     */
    public ElementManager(SimEngine theOwner) {
        owner = theOwner;
    }

    /**
     * Sets the realism level.
     *
     * @param level A constant from {@link eecs.jago.Realism}
     */
    public void setRealismLevel(int level) {

        // Flag a realism change for the next update, if necessary
        if (realismLevel != level) {
            realismChanged = true;
        }

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
     * Tests if there is an element within a specified
     * <CODE>Rectangle2D</CODE>. This method only checks for active
     * elements--if there is an element in the queue that has not been added,
     * any overlap with that object will not be detected.
     *
     * @param region     The <CODE>Rectangle2D</CODE> to look in for
     *        <CODE>SimElement</CODE>s.
     * @return <CODE>true</CODE> if the region is occupied.
     */
    public boolean isSimElementIn(Rectangle2D region) {

        boolean overlapExisting = false;
        Area localRegion = new Area(region);
        Collection tempMobile = Collections.unmodifiableCollection(
                                        mobileElements);
        Collection tempStatic = Collections.unmodifiableCollection(
                                        staticElements);
        Iterator iter;

        // First check the mobile elements
        iter = tempMobile.iterator();

        while (iter.hasNext()) {

            SimElement anElement = (SimElement)iter.next();

            // If a visible element, see if it intersects
            if (anElement instanceof Collidable) {

                Area otherRegion = anElement.getArea();
                otherRegion.intersect(localRegion);

                if (!otherRegion.isEmpty()) {
                    overlapExisting = true;

                    // Found one, so stop looking
                    break;
                }
            }
        }

        // Only check static if none found in mobile elements
        if (!(overlapExisting)) {

            // Now check the staticElements
            iter = tempStatic.iterator();

            while (iter.hasNext()) {

                SimElement anElement = (SimElement)iter.next();

                // If a visible element, see if it intersects
                if (anElement instanceof Collidable) {

                    Area otherRegion = anElement.getArea();
                    otherRegion.intersect(localRegion);

                    if (!otherRegion.isEmpty()) {
                        overlapExisting = true;

                        // Found one, so stop looking
                        break;
                    }
                }
            }
        } // end if !overlapExisting

        return overlapExisting;
    } /*isSimElementIn*/

    /**
     * Add/delete SimElements, then perform collision detection.  All
     * SimElements are responsible for updating themselves based on the
     * SimEngine timer.  This method should not be called by user programs.
     *
     * @param e The Event causing the action.
     */
    public void actionPerformed(ActionEvent e) {
        // add all elements which were scheduled for addition
        runTask(e);
    }

    /**
     * Not used.  Retained for a potential rewrite to threaded operation.
     */
    public void run() {

        Thread myThread = Thread.currentThread();
        myThread.setPriority(Thread.MAX_PRIORITY);

        long time = System.currentTimeMillis();

        while (myThread == thread) {
            time += owner.getDelay();

            // the ActionEvent is a dummy event--not even a good one
            runTask(new ActionEvent(this, 0, "threaded run"));

            try {
                Thread.sleep(Math.max(0, time - System.currentTimeMillis()));
            } catch (InterruptedException e) {

                // Just stop sleeping
            }
        }
    }

    /**
     * start is not used. It is retained for potential conversion to threads
     */
    public void start() {

        if (thread == null) {
            thread = new Thread(this, "ElementManager");
            thread.start();
        }
    }

    /*
     * Perform collision detection based on rectangles.  Future versions will
     * detect against circles, and polygons.
     */

    /**
     * Determines if a collision has occurred between <CODE>SimElement</CODE>s
     * in the simulation.
     */
    protected final void checkForCollisions() {

        /* Could sort original list (might be faster overall), but introduces
         * synchronization problems, since modifying the original list.
         * Converting to array allows for quicker access, and Arrays.sort
         * is a native call, so fast anyway.
         */
        Object[] mobileArray = mobileElements.toArray();
        Arrays.sort(mobileArray, positionCompare);

        Object[] staticArray = staticElements.toArray();
        Arrays.sort(staticArray, positionCompare);


        // Counts down to simplify management in handleCollisionsBetween
        for (int j = 0; j < mobileArray.length; j++) {

            SimElement firstElement = (SimElement)mobileArray[j];

            if (!(firstElement instanceof Collidable)) {

                continue;
            }

            /* TODO: In v6.2.3, this is still required to ensure that sensors
             * in IDEALISTIC mode will reset (generally they set automatically
             * but will keep the "set" state until directed to change by
             * this method call).  Need to update the sensors or the Monitor
             * to improve this interface.
             */
            if (firstElement instanceof Monitor) {
                ((Monitor)firstElement).updateSensors();
            }

            Collidable firstCollider = (Collidable)firstElement;
            Area firstRegion = firstCollider.getArea();

            for (int i = j + 1; i < mobileArray.length; i++) {

                SimElement secondElement = (SimElement)mobileArray[i];

                // If not Collidable, don't bother to check for collision
                if (!(secondElement instanceof Collidable)) {

                    continue;
                }

                // Elements are sorted by x coord, so stop checking if first
                // element can't collide with any of the remaining
                //                if (secondElement.getBounds2D().getX() > edgeOfFirst) {
                //                    break;
                //                }
                if (connected(firstElement, secondElement)) {

                    continue;
                }

                Area secondRegion = secondElement.getArea();

                /* Check for Collision.  First check for possibiliy using
                 * the (bounding) shape of the element; if bounding shapes
                 * overlap, check pixels in handleCollision.
                 */
                secondRegion.intersect(firstRegion);

                if (!secondRegion.isEmpty()) {

                    Collidable secondCollider = (Collidable)secondElement;
                    notifyPendingCollision(firstCollider, secondCollider);

                    if (collisionDetector.isCollision(firstElement,
                                                      secondElement)) {

                        // Pixels indicate collision, notify colliders
                        handleCollision(firstCollider, secondCollider);
                    }

                    notifyCollisionClear(firstCollider, secondCollider);
                }
            } /* end for */

            // Now handle collisions with the static elements
            for (int i = 0; i < staticArray.length; i++) {

                SimElement secondElement = (SimElement)staticArray[i];

                // If not Collidable, don't bother to check for collision
                if (!(secondElement instanceof Collidable)) {

                    continue;
                }

                // Elements are sorted by x coord, so stop checking if first
                // element can't collide with any of the remaining
                //                if (secondElement.getPosition().getX() > edgeOfFirst) {
                //                    break;
                //                }
                if (connected(firstElement, secondElement)) {

                    continue;
                }

                Area secondRegion = secondElement.getArea();

                //Rectangle2D secondBounds = secondRegion.getBounds2D();

                /* Check for Collision.  First check for possibiliy using
                 * the (bounding) shape of the element; if bounding shapes
                 * overlap, check pixels in handleCollision.
                 */
                secondRegion.intersect(firstRegion);

                if (!secondRegion.isEmpty()) {

                    Collidable secondCollider = (Collidable)secondElement;
                    notifyPendingCollision(firstCollider, secondCollider);

                    if (collisionDetector.isCollision(firstElement,
                                                      secondElement)) {

                        // Pixels indicate collision, notify colliders
                        handleCollision(firstCollider, secondCollider);
                    }

                    notifyCollisionClear(firstCollider, secondCollider);
                }
            } /* end for */
        } /*next_j*/
    } /*detectCollision*/

    /**
     * Undocumented
     *
     * @param firstElement Empty
     * @param secondElement Empty
     * @return Empty
     */
    protected boolean connected(SimElement firstElement,
                                SimElement secondElement) {

        /* TODO: Fix attachments so they never report collisions
         * with the element they are attached to.
         */

        // Prevent reporting collisions with elements attached to
        // each other, or to the same element
        boolean attached = false;
        Attachment firstAtch = null;
        Attachment secondAtch = null;

        if (firstElement instanceof Attachment) {
            firstAtch = (Attachment)firstElement;
        }

        if (secondElement instanceof Attachment) {
            secondAtch = (Attachment)secondElement;
        }

        // Make sure the colliders aren't attached to each other.
        if (firstAtch != null && firstAtch.isAttachedTo(secondElement)) {
            attached = true;
        }

        if (secondAtch != null && secondAtch.isAttachedTo(firstElement)) {
            attached = true;
        }

        /* If both are attachments, make sure they're not attached to the
         * same host.
         */
        if (firstAtch != null && secondAtch != null
            && firstAtch.attachedTo() == secondAtch.attachedTo()) {
            attached = true;
        }

        return attached;
    }

    /**
     * Notify the elements involved in a collision.
     *
     * @param first One <CODE>Collidable</CODE> element involved in a
     *        collision.
     * @param second Another <CODE>Collidable</CODE> element involved in a
     *        collision.
     */
    protected void handleCollision(Collidable first, Collidable second) {
        first.handleCollision(second);
        second.handleCollision(first);
    } /*handleCollision*/

    /**
     * Notifies elements that any pending collisions that they were involved
     * in have been resolved.
     *
     * @param first DOCUMENT ME!
     * @param second DOCUMENT ME!
     */
    protected void notifyCollisionClear(Collidable first, Collidable second) {
        first.pendingCollision(false);
        second.pendingCollision(false);
    }

    /**
     * Notifies the elements that might be involved in a collision.
     *
     * @param first The first <CODE>Collidable</CODE> element that might
     *        collide.
     * @param second The second <CODE>Collidable</CODE> element that might
     *        collide.
     */
    protected void notifyPendingCollision(Collidable first, Collidable second) {
        first.pendingCollision(true);
        second.pendingCollision(true);
    }

    /**
     * Undocumented
     *
     * @param cd Empty
     */
    void setCollisionDetector(CollisionDetector cd) {
        collisionDetector = cd;
    }


    public void setStaticChanged(boolean flag) {
        staticChanged = flag;
    }

    /**
     * Attempts to add a <CODE>SimElement</CODE> to the simulation.
     *
     * @param element   The <CODE>SimElement</CODE> to add.
     * @return <CODE>true</CODE> if the element can be added.
     */
    boolean add(SimElement element) {

        if (staticElements.contains(element)
            || mobileElements.contains(element) || addQueue.contains(element)) {

            return false;
        }

        // Wait if queue is locked
        synchronized (addQueue) {
          addQueue.add(element);
        }

        return true;
    } /*add*/

    /**
     * Requests the removal of a <CODE>SimElement</CODE> from the simulation.
     * Although the request is queued, the element is immediately notified
     * that is has been removed from the simulation, and its timer listener
     * is removed from the timer.
     *
     * @param element     The <CODE>SimElement</CODE> to remove.
     */
    void remove(SimElement element) {

        // Wait if queue is locked
        synchronized (deleteQueue) {
          deleteQueue.add(element);
        }

        // Notify the element that it isn't in a sim anymore
        element.removedFromSim();
    } /*remove*/

    /**
     * Dump all references to SimElements.
     */
    void removeAll() {

        // destroy pending lists
        synchronized (addQueue) {
          addQueue.clear();
        }

        synchronized (deleteQueue) {

            synchronized (staticElements) {
                deleteQueue.addAll(staticElements);
            }

            synchronized (mobileElements) {
                deleteQueue.addAll(mobileElements);
            }
        }
    } /*removeAll*/

    /**
     * Undocumented
     *
     * @param e Empty
     */
    private void runTask(ActionEvent e) {
        synchronized (addQueue) {

            Iterator iter = addQueue.iterator();

            while (iter.hasNext()) {

                SimElement newElement = (SimElement)iter.next();

                // If the element is Mobile, add it to the mobile list
                if (newElement instanceof Mobile) {

                    synchronized (mobileElements) {
                      mobileElements.add(newElement);
                    }
                } // Add it to the static list
                else {

                    synchronized (staticElements) {
                      staticElements.add(newElement);
                        staticChanged = true;
                    }
                }

                // If it is a lightsource, also add it to the light source list
                if (newElement instanceof LightSource) {
                    synchronized (lightSources) {
                      lightSources.add(newElement);
                    }
                }

                if (newElement instanceof Realism) {
                    ((Realism)newElement).setRealismLevel(realismLevel);
                }

                // And add its timer listener to the SimEngine timer
                //                if (newElement.getTimerListener() != null) {
                //                    owner.addActionListener(newElement.getTimerListener());
                //                }
            }

            addQueue.clear();
        }

        synchronized (deleteQueue) {

            // remove all elements which were scheduled for removal
            int queueElement = 0;
            int queueSize = deleteQueue.size();

            while (queueElement < queueSize) {

                SimElement oldElement = (SimElement)deleteQueue.get(
                                                queueElement);

                // Remove it from the correct list
                if (oldElement instanceof Mobile) {

                    synchronized (mobileElements) {
                      mobileElements.remove(oldElement);
                    }
                } else {

                    synchronized (staticElements) {
                      staticElements.remove(oldElement);
                    }

                    // Update flag so the DisplayManager will update properly
                    staticChanged = true;
                }

                // If it was a light source, remove it from that list too
                if (oldElement instanceof LightSource) {

                    synchronized (lightSources) {
                      lightSources.remove(oldElement);
                    }
                }

                //Remove this if statement if controlling elements from the ElementManager
                // Remove its timer from the list
                //                if (oldElement.getTimerListener() != null) {
                //                    owner.removeActionListener(oldElement.getTimerListener());
                //               }
                queueElement++;
            }

            deleteQueue.clear();
        }

        /* TODO: The elements are all able to listen to events, but since the
         * collision detection (in the ElementManager) and element updates
         * can occur simultaneously, the current implementation does not
         * handle collisions well (it essentially ignores them, since the
         * element's positions are updated before collision detection can
         * notify them that they shouldn't change position).
         */

        // perform collision detection
        if (freeChecks > 0) {
            freeChecks--;
        } else {
            checkForCollisions();
        }

        // Go through all mobile elements, and update them
        Iterator mIter = mobileElements.iterator();

        while (mIter.hasNext()) {

            SimElement elem = (SimElement)mIter.next();

            // Update realism, if necessary
            if (realismChanged && elem instanceof Realism) {
                ((Realism)elem).setRealismLevel(realismLevel);
            }

            if (elem.getTimerListener() != null) {
                elem.getTimerListener()
                    .actionPerformed(e);
            }
        }

        // Go through all static elements, and update them
        Iterator sIter = staticElements.iterator();

        while (sIter.hasNext()) {

            SimElement elem = (SimElement)sIter.next();

            // Update realism, if necessary
            if (realismChanged && elem instanceof Realism) {
                ((Realism)elem).setRealismLevel(realismLevel);
            }

            if (elem.getTimerListener() != null) {
                elem.getTimerListener()
                    .actionPerformed(e);
            }
        }

        realismChanged = false;
    }
} /*ElementManager*/