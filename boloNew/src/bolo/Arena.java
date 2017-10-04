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
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;

import javax.swing.*;


class Arena {

    static final double HOSPITAL_X = (Const.LEFT + Const.RIGHT) / 2.0 - 100
                                     + 200 * Math.random();
    static final double HOSPITAL_Y = (Const.TOP + Const.BOTTOM) / 2.0 - 100
                                     + 200 * Math.random();

    // Holds requests from the robot threads to the main animation thread.
    // This is the only sychronized object in the program.
    static final ActionQueue actionQueue = new ActionQueue();
    static boolean gameOver = false;
    static Thing hospital;

    //  static final Pool stationaryThings = new Pool();
    static final MobilePool mobileThings = new MobilePool();

    // the panel where everything is drawn
    static final JPanel panel = new JPanel() {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Arena.paint(g);
        }
    };

    static double remainingTime; // only comes into play at end of game
    static int robotCount = 0;
    static final MobilePool robots = new MobilePool();
    static final Pool scannableThings = new Pool();

    // Maintain all Things (ie, simulation objects) in one or more lists.
    // Each list is for objects that need to be treated in a certain way.
    // - timedThings: things that are ticked
    // - visibleThings: things that are drawn
    // - mobileThings: things that are checked for collisions
    // // - stationaryThings: things that are checked for collisions (but not with each other)
    // - vulnerableThings: things that are damaged by explosions
    // - scannableThings: things that can be scanned
    // - robots: all robots
    static final Pool timedThings = new Pool();
    static final Pool visibleThings = new Pool();
    static final Pool vulnerableThings = new Pool();

    // the main animation loop
    static final Thread animationTicker = new Thread() {
        public void run() {

            while (true) {
                tick();
                panel.repaint();

                try {
                    sleep(Const.TIME_BETWEEN_TICKS);
                } catch (Exception e) {}
            }
        }
    };

    //static Obstacle LEFT_WALL,RIGHT_WALL,TOP_WALL,BOTTOM_WALL;
    // robots must be registered before calling runSimulation()

    /**
     * Undocumented
     */
    public static void runSimulation() {
        Window.open(panel, Const.RIGHT + Const.RIGHT_BORDER, 
                    Const.BOTTOM + Const.BOTTOM_BORDER, Const.TITLE);
        Sound.startBackground();

        // walls

        /*
           LEFT_WALL = new Obstacle(Const.WALL_COLOR,Const.WALL_SHAPES[0]);
           RIGHT_WALL = new Obstacle(Const.WALL_COLOR,Const.WALL_SHAPES[1]);
           TOP_WALL = new Obstacle(Const.WALL_COLOR,Const.WALL_SHAPES[2]);
           BOTTOM_WALL = new Obstacle(Const.WALL_COLOR,Const.WALL_SHAPES[3]);
         */
        for (int i = 0; i < 4; i++) {
            new Obstacle(Const.WALL_COLOR, Const.WALL_SHAPES[i]);
        }

        // hospital
        hospital = new Hospital(HOSPITAL_X, HOSPITAL_Y);

        // boundaries around hospital

        /* ** make it too easy to collide because scan is so narrow
         ** then again, that makes collision detection more useful!
           new Obstacle(Const.WALL_COLOR,Util.rectangle(hx-80,hy,4,50));
           new Obstacle(Const.WALL_COLOR,Util.rectangle(hx+80,hy,4,50));
           new Obstacle(Const.WALL_COLOR,Util.rectangle(hx,hy-80,50,4));
           new Obstacle(Const.WALL_COLOR,Util.rectangle(hx,hy+80,50,4));
         */

        // start the main animation loop
        animationTicker.setPriority(Const.HIGH_PRIORITY);
        animationTicker.start();
    }

    // shut down the game
    static void endGame() {
        Sound.stopBackground();

        switch (robotCount) {

            case 0:
                Sound.play(Sound.TAPS);
                printGameOver("No robots survived.");

                break;

            case 1:

                Robot robot = (Robot)robots.getIterator()
                                           .next();
                Sound.play(Sound.CHEER);
                printGameOver(robot.name + "  is the winner!");

                break;

            default:
                Sound.play(Sound.NO_MORE_TIME);

                String msg = "Time expired.\nThe following robots survived:";
                MobilePool.Iterator it = robots.getIterator();

                for (Mobile thing = it.next();
                     thing != null;
                     thing = it.next()) {
                    msg += "\n     " + ((Robot)thing).name;
                }

                printGameOver(msg);

                break;
        }

        // do NOT ask to play again because of potential problems stopping
        // robot threads
        System.exit(0);
    }

    // only called when the panel is painted
    static void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;

        // show time
        String seconds = "" + (int)(Const.TIME_LIMIT - Clock.read() + 0.5);

        if (seconds.length() == 1) {
            seconds = "00" + seconds;
        }
         else if (seconds.length() == 2) {
            seconds = "0" + seconds;
        }

        FontRenderContext context = g2d.getFontRenderContext();
        TextLayout layout = new TextLayout(seconds, Const.CLOCK_FONT, context);
        g2d.setPaint(Const.CLOCK_COLOR);
        layout.draw(g2d, Const.CLOCK_X, Const.CLOCK_Y);

        // draw each visible thing
        visibleThings.draw(g2d);
    } // end method paint

    static void printGameOver(String msg) {
        JOptionPane.showMessageDialog(panel, msg, "GAME OVER", 
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    // only called by the animation ticker
    //
    // Each tick of the clock do four things:
    //   1. perform all pending actions in the actionQueue
    //   2. tick each timed object
    //   3. check for collisions
    //   4. check if the game is done
    static void tick() {

        // perform all the requested actions
        actionQueue.processActions();

        // time since last update
        double deltaT = Clock.update();

        // tick all timed objects
        timedThings.tick(deltaT);

        // check for collisions
        mobileThings.checkAgainstWalls();
        mobileThings.checkAgainstEachOther();

        // check against hospital!!!
        MobilePool.Iterator it = robots.getIterator();

        for (Mobile thing = it.next(); thing != null; thing = it.next()) {

            if (thing.overlaps(hospital)) {
                ((Robot)thing).heal();
            }
        }

        // check for end of game
        if (gameOver) {
            remainingTime -= deltaT;

            if (remainingTime <= 0) {
                endGame();
            }
        } else if (robotCount < 2) {
            gameOver = true;
            remainingTime = Const.WIN_DELAY;
        }

        if (Clock.read() > Const.TIME_LIMIT) {
            endGame();
        }
    } // end tick()
}