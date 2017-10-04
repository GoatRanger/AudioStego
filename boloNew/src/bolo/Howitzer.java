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
import java.awt.geom.*;


public abstract class Howitzer
    extends RobotAPI {

    static final double MISSILE_MAX_RANGE = 500;
    static final double MISSILE_MIN_RANGE = 20;
    static final double MISSILE_RELOAD_TIME = 2.0;
    static final double RADIUS = 8.0;
    final Weapon CHAFF = Chaff.newWeapon();
    final Weapon CRUISE_MISSILE = CruiseMissile.newWeapon();

    // give the missiles longer range and faster reloads
    final Ammunition.Properties MISSILE_PROPERTIES = 
            new Ammunition.Properties(MISSILE_MIN_RANGE, MISSILE_MAX_RANGE, 
                                      Missile.VELOCITY, MISSILE_RELOAD_TIME);
    final Weapon MISSILE = Missile.newWeapon(MISSILE_PROPERTIES);

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double chaffDelay() {

        return CHAFF.properties.reloadDelay;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double chaffVelocity() {

        return CHAFF.properties.velocity;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double cruiseMissileDelay() {

        return CRUISE_MISSILE.properties.reloadDelay;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double cruiseMissileVelocity() {

        return CRUISE_MISSILE.properties.velocity;
    }

    /**
     * Undocumented
     * 
     * @param radians Empty
     * @param distance Empty
     */
    public final void fireChaff(double radians, double distance) {
        fire(CHAFF, radians, distance);
    }

    /**
     * Undocumented
     * 
     * @param degrees Empty
     * @param distance Empty
     */
    public final void fireChaffDegrees(double degrees, double distance) {
        fire(CHAFF, 
             toRadians(degrees), 
             distance);
    }

    /**
     * Undocumented
     * 
     * @param radians Empty
     * @param distance Empty
     */
    public final void fireCruiseMissile(double radians, double distance) {
        fire(CRUISE_MISSILE, radians, distance);
    }

    /**
     * Undocumented
     * 
     * @param degrees Empty
     */
    public final void fireCruiseMissileDegrees(double degrees, double distance) {
        fire(CRUISE_MISSILE, 
             toRadians(degrees), 
             distance);
    }

    /**
     * Undocumented
     * 
     * @param radians Empty
     * @param distance Empty
     */
    public final void fireMissile(double radians, double distance) {
        fire(MISSILE, radians, distance);
    }

    /**
     * Undocumented
     * 
     * @param degrees Empty
     * @param distance Empty
     */
    public final void fireMissileDegrees(double degrees, double distance) {
        fire(MISSILE, 
             toRadians(degrees), 
             distance);
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double maxChaffRange() {

        return CHAFF.properties.maxRange;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double maxCruiseMissileRange() {

        return CRUISE_MISSILE.properties.maxRange;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double maxMissileRange() {

        return MISSILE.properties.maxRange;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double minChaffRange() {

        return CHAFF.properties.minRange;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double minCruiseMissileRange() {

        return CRUISE_MISSILE.properties.minRange;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double minMissileRange() {

        return MISSILE.properties.minRange;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double missileDelay() {

        return MISSILE.properties.reloadDelay;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double missileVelocity() {

        return MISSILE.properties.velocity;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final int numChaff() {

        return CHAFF.rounds;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final int numCruiseMissiles() {

        return CRUISE_MISSILE.rounds;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final int numMissiles() {

        return MISSILE.rounds;
    }

    /**
     * Undocumented
     * 
     * @param name Empty
     * @param pos Empty
     */
    void initialize(String name, int pos) {
        maxHealth = 90;
        maxVelocity = 25;
        radius = RADIUS;
        rebound = RADIUS + 2;

        // define the howitzer shape
        double angle = 4 * Math.PI / 5;
        float sin = (float)(RADIUS * Math.sin(angle));
        float cos = (float)(RADIUS * Math.cos(angle));
        GeneralPath howitzerShape = new GeneralPath();
        howitzerShape.append(Util.circle(RADIUS), 
                             false);
        howitzerShape.moveTo((float)RADIUS, 0.0f);
        howitzerShape.lineTo(-(float)RADIUS / 2, (float)RADIUS / 4);
        howitzerShape.lineTo(-(float)RADIUS / 2, -(float)RADIUS / 4);

        /*
           howitzerShape.lineTo(cos,sin);
           howitzerShape.lineTo(cos,sin/3);
           howitzerShape.lineTo(-(float)RADIUS,0.0f);
           howitzerShape.lineTo(cos,-sin/3);
           howitzerShape.lineTo(cos,-sin);
         */
        howitzerShape.closePath();

        /* NON-CIRCULAR SHAPE
           double angle = 3*Math.PI/4;
           float sin = (float)(RADIUS * Math.sin(angle));
           float cos = (float)(RADIUS * Math.cos(angle));
           GeneralPath howitzerShape = new GeneralPath();
           howitzerShape.moveTo((float)RADIUS,0.0f);
           howitzerShape.lineTo(cos,sin);
           howitzerShape.lineTo(cos,sin/3);
           howitzerShape.lineTo(-(float)RADIUS,0.0f);
           howitzerShape.lineTo(cos,-sin/3);
           howitzerShape.lineTo(cos,-sin);
           howitzerShape.closePath();
         */
        originalShape = howitzerShape;
        super.initialize(name, pos);
    }
}