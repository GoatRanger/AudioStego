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


// ASSUMES YOU WILL NEVER TRY TO ADD MORE THAN 1000 OBJECTS
// ALSO DOES NO SYNCHRONIZATION SO POSSIBLE PROBLEMS WITH CONCURRENT ACCESS
package bolo;

class MobilePool {

    private static final int MAX_SIZE = 1000;
    private int size = 0;
    private Mobile[] a = new Mobile[MAX_SIZE];

    /*
       void scan(Robot r,double heading) {
         double cos = Math.cos(heading);
         double sin = Math.sin(heading);
       
           double startX = r.x + (r.radius+0.5) * cos;
           double startY = r.y + (y.radius+0.5) * sin;
       
           double endX = r.x + Const.SCAN_RANGE * cos;
           double endY = r.y + Const.SCAN_RANGE * sin;
       
           Shape baseScanLine = new Rectangle2D.Double(0,-0.5,Const.SCAN_RANGE,1);
           baseScanLine = Util.rotate(baseScanLine,scanHeading);
           baseScanLine = Util.translate(baseScanLine,x,y);
       
           Thing target = null;
       
           Pool.Iterator it = Arena.scannableThings.getIterator();
           for (Thing thing = it.next(); thing != null; thing = it.next()) {
             if (thing != this && Util.overlaps(baseScanLine,thing.shape)) {
               // convert other shape to local coordinates
               Shape other = Util.translate(thing.shape,-x,-y);
               other = Util.rotate(other, -scanHeading);
               Area otherArea = new Area(other);
       
               // binary search to find distance
               Shape scanLine;
               double hi = Const.SCAN_RANGE; // upper bound on distance
               double lo = radius;
               while (hi - lo > Const.SCAN_ACCURACY) {
                 double mid = (hi+lo)/2;
                 scanLine = new Rectangle2D.Double(0,-0.5,mid,1);
                 if (Util.overlaps(scanLine,otherArea)) hi = mid;
                 else lo = mid;
               }
               double distanceToOther = (hi+lo)/2;
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
           }
           else {
             targetHeading = 0;
             targetVelocity = 0;
           }
       
           ScanTrace trace = new ScanTrace(x,y,radius,scanHeading,targetDistance);
           Arena.timedThings.add(trace);
           Arena.visibleThings.add(trace);
           scanReady = true;
         }
     */

    /**
     * Undocumented
     * 
     * @return Empty 
     */
    Iterator getIterator() {

        return new Iterator();
    }

    /**
     * Undocumented
     * 
     * @param m Empty
     */
    void add(Mobile m) {

        // ignore overflow!!!!!!
        a[size++] = m;
    }

    /**
     * Undocumented
     * 
     * @param m1 Empty
     * @param m2 Empty
     */
    void bounce(Mobile m1, Mobile m2) {

        if (m1 instanceof Robot && m2 instanceof Robot) {

            Robot r1 = (Robot)m1;
            Robot r2 = (Robot)m2;
            double halfD = Util.distance(m1.x - m2.x, m1.y - m2.y) / 2;
            double centerX = (m1.x + m2.x) / 2;
            double centerY = (m1.y + m2.y) / 2;

            if (halfD == 0) {

                return;
            }

            r1.x = Util.bound(Const.LEFT + m1.radius, 
                              centerX + m1.rebound * (m1.x - centerX) / halfD, 
                              Const.RIGHT - m1.radius);
            r1.y = Util.bound(Const.TOP + m1.radius, 
                              centerY + m1.rebound * (m1.y - centerY) / halfD, 
                              Const.BOTTOM - m1.radius);
            r2.x = Util.bound(Const.LEFT + m2.radius, 
                              centerX + m2.rebound * (m2.x - centerX) / halfD, 
                              Const.RIGHT - m2.radius);
            r2.y = Util.bound(Const.TOP + m2.radius, 
                              centerY + m2.rebound * (m2.y - centerY) / halfD, 
                              Const.BOTTOM - m2.radius);
        }
    }

    /**
     * Undocumented
     */
    void checkAgainstEachOther() {

        int i = 0;

        while (i < size - 1) {

            if (a[i].obsolete) {
                a[i] = a[--size];
                a[size] = null;
            } else {

                Mobile m = a[i++];
                int j = i;

                while (j < size) {

                    if (a[j].obsolete) {
                        a[j] = a[--size];
                        a[size] = null;
                    } else {

                        Mobile other = a[j++];
                        double dx = m.x - other.x;
                        double dy = m.y - other.y;
                        double dsep = m.radius + other.radius;

                        if (dx * dx + dy * dy < dsep * dsep) {
                            m.collideWith(other);
                            other.collideWith(m);
                            bounce(m, other);
                        }
                    }
                }
            }
        }
    }

    /**
     * Undocumented
     */
    void checkAgainstWalls() {

        int i = 0;

        while (i < size) {

            if (a[i].obsolete) {
                a[i] = a[--size];
                a[size] = null;
            } else {

                Mobile m = a[i++];
                boolean collision = false;

                if (m.x < Const.LEFT + m.radius) {
                    m.x = Const.LEFT + m.rebound;
                    collision = true;
                } else if (m.x > Const.RIGHT - m.radius) {
                    m.x = Const.RIGHT - m.rebound;
                    collision = true;
                }

                if (m.y < Const.TOP + m.radius) {
                    m.y = Const.TOP + m.rebound;
                    collision = true;
                } else if (m.y > Const.BOTTOM - m.radius) {
                    m.y = Const.BOTTOM - m.rebound;
                    collision = true;
                }

                if (collision) {
                    m.shape = Util.translate(m.zeroShape, m.x, m.y);
                    m.collideWithWall();
                }
            }
        }
    }

    class Iterator {

        private int i;

        private Iterator() {
            i = 0;
        }

        private Iterator(int start) {
            i = start;
        }

        Iterator copy() {

            return new Iterator(i);
        }

        Mobile next() {

            while (i < size) {

                if (!a[i].obsolete) {

                    return a[i++];
                }
                 else {
                    a[i] = a[--size];
                    a[size] = null;
                }
            }

            return null;
        }
    }
}