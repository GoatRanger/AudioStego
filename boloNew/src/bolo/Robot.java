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

package bolo;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

import java.util.*;


// I've split what used to be RoboWarrior into two parts:
//  -- Robot implements all the internals of robots
//  -- RobotAPI provides the public API for programming robots
abstract class Robot
    extends Mobile {

    boolean collision = false;
    boolean foundRobot = false;
    double health;
    boolean hitByScan = false;

    // the following fields are customized by each chassis
    double maxHealth;
    double maxVelocity;
    String name;
    Shape originalShape;
    double scanHeading = 0.0;
    boolean scanReady = false;
    double scannedFromDistance = 0.0;
    double scannedFromHeading = 0.0;
    double targetDistance = 0.0;
    double targetHeading = 0.0;
    double targetVelocity = 0.0;
    Thread thread = new Thread() {
        public void run() {

            // sleep for a little while so that the robot doesn't start
            // moving until the window is ready
            try {
                sleep(Const.INITIAL_DELAY);
            } catch (Exception e) {}

            strategy();
        }
    };

    double timeToReload = 0.0;
    double whenScanned = 0.0;

    // cadets will implement this method

    /**
     * Undocumented
     */
    public abstract void strategy();

    /**
     * Undocumented
     */
    void action() {
        Arena.timedThings.add(this);
        Arena.visibleThings.add(this);
        Arena.mobileThings.add(this);
        Arena.vulnerableThings.add(this);
        Arena.scannableThings.add(this);
        Arena.robots.add(this);
        Arena.robotCount++;
        thread.setPriority(Const.LOW_PRIORITY);
        thread.start();
    }

    /**
     * Undocumented
     * 
     * @param other Empty
     */
    void collideWith(Thing other) {

        if (other instanceof Robot) {
            collision = true;
            damage(Const.COLLISION_DAMAGE);
        } else if (other instanceof Hospital) {
            health = Math.min(health + Const.HEALING_RATE, maxHealth);
        }
    }

    /**
     * Undocumented
     */
    void collideWithWall() {
        collision = true;
        damage(Const.COLLISION_DAMAGE);
    }

    /**
     * Undocumented
     * 
     * @param dmg Empty
     */
    void damage(double dmg) {
        health -= dmg;

        if (health <= 0) {
            explode();
        }
    }

    /**
     * Undocumented
     * 
     * @param g Empty
     */
    void draw(Graphics2D g) {

        if (!obsolete) {
            g.setPaint(Const.ROBOT_COLOR);
            g.draw(shape);

            double label_x = radius + 3;
            double name_y = radius + 1;
            double status_y = name_y - 3;
            FontRenderContext context = g.getFontRenderContext();
            TextLayout layout = new TextLayout(name, Const.NAME_FONT, context);
            layout.draw(g, (float)(x + label_x), (float)(y - name_y));

            if (health <= maxHealth - 1) {

                Shape sickLine = new Rectangle2D.Double(x + label_x + 1, 
                                                        y - status_y, 
                                                        0.2 * maxHealth, 
                                                        Const.STATUS_WIDTH);
                g.setPaint(Const.SICK_COLOR);
                g.fill(sickLine);
            }

            Shape healthLine = new Rectangle2D.Double(x + label_x + 1, 
                                                      y - status_y, 
                                                      0.2 * health, 
                                                      Const.STATUS_WIDTH);
            g.setPaint(Const.HEALTHY_COLOR);
            g.fill(healthLine);
        }
    }

    /**
     * Undocumented
     */
    void explode() {
        new Explosion(x, y, Const.ROBOT_EXPLOSION_COLOR, 
                      Const.ROBOT_EXPLOSION_SHAPE, 
                      Const.ROBOT_EXPLOSION_DAMAGE, Const.EXPLOSION_DURATION, 
                      Sound.ROBOT_EXPLOSION);
        Arena.robotCount--;
        obsolete = true;
        thread.stop();
    }

    /**
     * Undocumented
     * 
     * @param weapon Empty
     * @param theta Empty
     * @param distance Empty
     */
    void fire(Weapon weapon, double theta, double distance) {

        if (timeToReload <= 0) {
            distance = Util.bound(weapon.properties.minRange, distance, 
                                  weapon.properties.maxRange);

            /*
               double r = Const.ROBOT_RADIUS + Const.MISSILE_RADIUS + 0.1;
               double mx = x + r * Math.cos(theta);
               double my = y + r * Math.sin(theta);
               new Missile(x,y,theta,distance-r);
             */
            weapon.fire(x, y, theta, distance);
            timeToReload = weapon.properties.reloadDelay;
        }
    }

    /**
     * Undocumented
     */
    void heal() {
        health = Math.min(health + Const.HEALING_RATE, maxHealth);
    }

    // each chassis should overide initialize to customize
    //   weapons,maxHealth,maxVelocity,radius,originalShape

    /**
     * Undocumented
     * 
     * @param name Empty
     * @param position Empty
     */
    void initialize(String name, int position) {
        this.name = name + " (" + (position + 1) + ")";
        x = Const.START_X[position];
        y = Const.START_Y[position];
        heading = Const.START_HEADING[position];
        health = maxHealth;
        velocity = 0.0;
        updateRotatedShape(); // set zeroShape and shape
        Arena.actionQueue.add(this);
    }

    /**
     * Undocumented
     */
    void scan() {

        Shape baseScanLine = new Rectangle2D.Double(0, -0.5, Const.SCAN_RANGE, 
                                                    1);
        baseScanLine = Util.rotate(baseScanLine, scanHeading);
        baseScanLine = Util.translate(baseScanLine, x, y);

        Thing target = null;
        Pool.Iterator it = Arena.scannableThings.getIterator();

        for (Thing thing = it.next(); thing != null; thing = it.next()) {

            if (thing != this && Util.overlaps(baseScanLine, thing.shape)) {

                // convert other shape to local coordinates
                Shape other = Util.translate(thing.shape, -x, -y);
                other = Util.rotate(other, -scanHeading);

                Area otherArea = new Area(other);

                // binary search to find distance
                Shape scanLine;
                double hi = Const.SCAN_RANGE; // upper bound on distance
                double lo = radius;

                while (hi - lo > Const.SCAN_ACCURACY) {

                    double mid = (hi + lo) / 2;
                    scanLine = new Rectangle2D.Double(0, -0.5, mid, 1);

                    if (Util.overlaps(scanLine, otherArea)) {
                        hi = mid;
                    }
                     else {
                        lo = mid;
                    }
                }

                double distanceToOther = (hi + lo) / 2;

                if (target == null || distanceToOther < targetDistance) {
                    target = thing;
                    targetDistance = distanceToOther;

                    if (target instanceof Robot) {

                        Robot robot = (Robot)target;
                        targetHeading = robot.heading;
                        targetVelocity = robot.velocity;
                    }
                }
            }
        }

        // must find something, a wall if nothing else, so target != null
        foundRobot = (target instanceof Robot);

        if (foundRobot) {

            Robot robot = (Robot)target;
            robot.hitByScan = true;
            robot.scannedFromDistance = targetDistance;
            robot.scannedFromHeading = scanHeading + Math.PI;
            robot.whenScanned = Clock.read();
        } else {
            targetHeading = 0;
            targetVelocity = 0;
        }

        ScanTrace trace = new ScanTrace(x, y, radius, scanHeading, 
                                        targetDistance);
        Arena.timedThings.add(trace);
        Arena.visibleThings.add(trace);
        scanReady = true;
    }

    /**
     * Undocumented
     * 
     * @param deltaT Empty
     */
    void tick(double deltaT) {
        super.tick(deltaT);
        timeToReload -= deltaT;
    }

    // plus radius from Mobile
    // plus weapons...

    /**
     * Undocumented
     */
    void updateRotatedShape() {
        zeroShape = Util.rotate(originalShape, heading);
        shape = Util.translate(zeroShape, x, y);
    }
}