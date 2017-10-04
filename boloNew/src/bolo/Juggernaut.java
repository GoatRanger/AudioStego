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


public abstract class Juggernaut
    extends RobotAPI {

    static final double RADIUS = 10.0;
    final Weapon BULLET = Bullet.newWeapon();
    final Weapon CHAFF = Chaff.newWeapon();
    final Weapon MISSILE = Missile.newWeapon(8);

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double bulletDelay() {

        return BULLET.properties.reloadDelay;
    }

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    public final double bulletVelocity() {

        return BULLET.properties.velocity;
    }

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
     * @param radians Empty
     */
    public final void fireBullet(double radians) {
        fire(BULLET, radians, 0);
    }

    /**
     * Undocumented
     * 
     * @param degrees Empty
     */
    public final void fireBulletDegrees(double degrees) {
        fire(BULLET, 
             toRadians(degrees), 
             0);
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
    public final double maxBulletRange() {

        return BULLET.properties.maxRange;
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
    public final int numBullet() {

        return BULLET.rounds;
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
    public final int numMissiles() {

        return MISSILE.rounds;
    }

    /**
     * Undocumented
     * 
     * @param other Empty
     */
    void collideWith(Thing other) {

        if (other instanceof Robot) {
            collision = true;
            damage(Const.COLLISION_DAMAGE / 3);
        }
    }

    /**
     * Undocumented
     */
    void collideWithWall() {
        collision = true;
        damage(Const.COLLISION_DAMAGE / 3);
    }

    /**
     * Undocumented
     * 
     * @param name Empty
     * @param pos Empty
     */
    void initialize(String name, int pos) {
        maxHealth = 130;
        maxVelocity = 28;
        radius = RADIUS;
        rebound = RADIUS + 2;

        // define the tank shape
        double angle = 7 * Math.PI / 8;
        float sin = (float)(RADIUS * Math.sin(angle));
        float cos = (float)(RADIUS * Math.cos(angle));
        GeneralPath tankShape = new GeneralPath();
        Shape circle = Util.circle(RADIUS);
        tankShape.append(circle, false);
        tankShape.moveTo(cos + 1, sin);
        tankShape.lineTo((float)RADIUS, 0);
        tankShape.lineTo(cos + 1, -sin);

        /* NON-CIRCULAR SHAPE
           double angle = 3*Math.PI/4;
           float sin = (float)(RADIUS * Math.sin(angle));
           float cos = (float)(RADIUS * Math.cos(angle));
           GeneralPath tankShape = new GeneralPath();
           tankShape.moveTo((float)RADIUS,0);
           tankShape.lineTo(cos,sin);
           tankShape.lineTo(-(float)RADIUS,0);
           tankShape.lineTo(cos,-sin);
           tankShape.closePath();
         */
        /*
           GeneralPath tankShape = new GeneralPath();
           Shape circle = Util.circle(radius);
           tankShape.append(circle,false);
           tankShape.moveTo(-VEE_LENGTH/2,-VEE_WIDTH/2);
           tankShape.lineTo(VEE_LENGTH/2,0);
           tankShape.lineTo(-VEE_LENGTH/2,VEE_WIDTH/2);
         */
        originalShape = tankShape;
        super.initialize(name, pos);
    }
}