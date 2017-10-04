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
/*
 * RouletteWheel.java
 *
 * Created on July 8, 2002, 9:57 PM
 */
package eecs.sim;

import java.awt.*;
import java.awt.geom.*;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 * @version 0.5
 */
public class RouletteWheel extends jago.element.VisibleElement
    implements jago.element.Mobile, RouletteData
{
    protected Image ball;
    protected Point2D previousLocation;

    /**
     * The colors for each of the spaces.  Since the spaces don't
     * always alternate, need to assign individually.
     */
    protected int[] spaceColors =
    {
        RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE,
        BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE,
        RED_SPACE, GREEN_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE,
        BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE,
        RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE,
        BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE,
        GREEN_SPACE, BLACK_SPACE, RED_SPACE, BLACK_SPACE, RED_SPACE,
        BLACK_SPACE, RED_SPACE, BLACK_SPACE
    };

    /**
     * The values for the spaces on the roulette wheel. Based on
     * orientation of the image of the wheel, #32 is at Space=1
     */
    protected int[] spaceNumbers =
    {
        32, 17, 5, 22, 34, 15, 3, 24, 36, 13, 1, 100, 27, 10, 25, 29,
        12, 8, 19, 31, 18, 6, 21, 33, 16, 4, 23, 35, 14, 2, 0, 28, 9,
        26, 30, 11, 7, 20
    };
    protected boolean ballStopped;
    protected boolean spinning;
    protected double ballLocation;
    protected double ballSpinRadius;
    protected double ballVelocity;
    protected double heading;
    protected double velocity;

    /** The amount of time (in ms) for the wheel to spin */
    protected int spinTime;

    /**
     * Creates new RouletteWheel
     */
    public RouletteWheel()
    {
        super();
        setAppearance("images/rwheel.gif");
        size = new Dimension(200, 200);
        velocity = 0;
        heading = 0.0;
        spinning = false;
        ballLocation = 0;
        ballVelocity = 0;
        ball =
            jago.ImageLoader.getImage(this, "images/rouletteball.gif");
    }

    /**
     * Gets the angular velocity of this element. Convention is
     * positive values are clockwise.
     *
     * @return Empty
     */
    public double getAngularVelocity()
    {
        // Perhaps eventually return something, but...why?
        return 0;
    }

    /**
     * Gets the color of the space that the ball is in.
     *
     * @return a {@link eecs.sim.RouletteData} color of
     *         <code>RED_SPACE, BLACK_SPACE,</code> or
     *         <code>GREEN_SPACE</code>.
     */
    public int getColor()
    {
        // If spinning, wait
        while (spinning)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
            }
        }

        int space = getBallSpace();

        if (space == -1)
        {
            return -1;
        }
        else
        {
            return spaceColors[space - 1];
        }
    }

    /**
     * Gets the current heading.  Doesn't really have any meaning
     * with the roulette wheel.
     *
     * @return The current rotational heading of the wheel.
     */
    public double getHeading()
    {
        return heading;
    }

    /**
     * Based on the location of the ball, determines the number that
     * the ball has landed on.
     *
     * @return The number of the space.  If the space is the "00"
     *         space, the value returned will be 100.
     */
    public int getNumber()
    {
        // If spinning, wait
        while (spinning)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
            }
        }

        int space = getBallSpace();

        if (space == -1)
        {
            return -1;
        }
        else
        {
            return spaceNumbers[space - 1];
        }
    }

    /**
     * Based on the location of the ball, determines the number that
     * the ball has landed on.
     *
     * @return The number of the space, as a String.
     */
    public String getNumberString()
    {
        String valueString;
        int value = getNumber();

        // Error value
        if (value == -1)
        {
            valueString = "ERROR";
        }
        else if (value == 100)
        {
            // Handle the special case
            valueString = "00";
        }
        else
        {
            // Convert all others
            valueString = Integer.toString(value);
        }

        return valueString;
    }

    /**
     * Gets the location and returns it in a Point object
     *
     * @return Empty
     */
    public Point2D getPosition()
    {
        return location;
    }

    /**
     * Determines the new power of the mobile element
     *
     * @param speed Empty
     */
    public void setPower(double speed)
    {
        System.out.println(
            "Power has no meaning with a roulette wheel...");
    }

    /**
     * Gets the last valid location of this object
     *
     * @return Empty
     */
    public Point2D getPreviousPosition()
    {
        return previousLocation;
    }

    /**
     * Gets the current velocity.
     *
     * @return Empty
     */
    public double getVelocity()
    {
        return 0;
    }

    /**
     * Draws this MobileElement in a Graphics2D context.
     *
     * @param g2 Empty
     */
    public void draw(Graphics2D g2)
    {
        Shape oldClip = g2.getClip();

        // Ensure it only draws inside the bounding rectangle
        if (myShape != null)
        {
            //g2.clip(myShape);
        }

        AffineTransform transform = new AffineTransform();

        // Rotate the image according to its azimuth
        transform.rotate(Math.toRadians(heading),
            location.getX() + (size.getWidth() / 2.0),
            location.getY() + (size.getHeight() / 2.0));

        // and move it to the correct location
        transform.translate(location.getX(), location.getY());

        // Draw it in the graphics context, using the rotation transform
        if (appearance != null)
        {
            g2.drawImage(appearance, transform, host);
        }
        else
        {
            g2.draw(myShape);
        }

        // Need to make sure the clipping area is returned to original state
        g2.setClip(oldClip);
        transform = new AffineTransform();
        g2.setTransform(transform);

        // Now, figure out where the ball goes
        double ballHeadingRads = Math.toRadians(ballLocation);
        double ballX =
            (ballSpinRadius * Math.cos(ballHeadingRads)) +
            location.getX() + (size.getWidth() / 2.0);
        double ballY =
            (ballSpinRadius * Math.sin(ballHeadingRads)) +
            location.getY() + (size.getHeight() / 2.0);

        if (ball != null)
        {
            g2.drawImage(ball, (int) ballX - 6, (int) ballY - 8, host);
        }
    }
     /*draw*/

    /**
     * Start spinning the wheel.  Starts the ball at a random
     * location and with a random velocity (about 1-2 deg/ms), and
     * spins the wheel at random initial velocity (tries to spin for
     * 7-8 sec).
     */
    public void spin()
    {
        lastUpdateTime = NOT_STARTED;
        spinning = true;
        ballStopped = false;
        ballLocation = (int) (Math.random() * 360);

        // ball velocity is negative since it is rotating the opposite dir at first.
        ballVelocity = -(int) ((Math.random() * 5) + 10);
        ballSpinRadius = 95;
        spinTime = 5000 + (int) (Math.random() * 2000);
    }

    /**
     * Updates the position of the wheel and ball.
     */
    public void updatePosition()
    {
        /* (host==null is a hack due to sequencing problems when removing
         *  elements).
         */
        if ((host == null) || !spinning)
        {
            return;
        }

        long deltaTime = 0;

        if (host != null)
        {
            if (lastUpdateTime == NOT_STARTED)
            {
                lastUpdateTime = host.elapsedTimeMillis();
            }

            long elapsedTime = host.elapsedTimeMillis();
            deltaTime = (elapsedTime - lastUpdateTime);
            lastUpdateTime = elapsedTime;
        }

        int wheelVelocity = 0;

        if (spinTime > 0)
        {
            if (spinTime > 5000)
            {
                wheelVelocity = 0;
            }
            else if (spinTime > 2000)
            {
                wheelVelocity = 8;
            }
            else
            {
                wheelVelocity = spinTime / 250;
            }

            if (spinTime < deltaTime)
            {
                deltaTime = spinTime;

                // If deltaTime is too small, the xMove/yMove are too small
                // to be noticed.
                if (deltaTime < 10)
                {
                    deltaTime = 10;
                }

                spinTime = 0;
                spinning = false;
            }
            else
            {
                spinTime -= (int) deltaTime;
            }

            heading = (heading + wheelVelocity) % 360;
        }
        else
        {
            spinning = false;
        }

        // Initially, velocity is negative (wheel velocity is positive)
        if (ballVelocity < 0)
        {
            ballVelocity += 0.10;
            // Getting close to stopping, so ball better fall!
            if (spinTime < 800)
            {
                ballVelocity = wheelVelocity;
            }
        }
        else
        {
            ballSpinRadius = 77;
            ballVelocity = wheelVelocity;

            if (!ballStopped)
            {
                ballStopped = true;

                // Make sure it falls into a real slot
                ballLocation -= ((ballLocation - heading) % (360.0 / 38.0));

                // A small adjustment since the wheel didn't start at 0
                ballLocation += 2.5;
            }
        }

        ballLocation = (ballLocation + ballVelocity) % 360;
    }

    /**
     * Updates the position of the roulette wheel and ball, based on
     * a single step of execution of the simulation.
     */
    protected void runTaskStep()
    {
        updatePosition();
    }

    /**
     * Determines what space the ball landed in when the wheel
     * stopped spinning.
     *
     * @return The index of the space that the ball is in.
     */
    private int getBallSpace()
    {
        int ballSpace =
            (int) Math.ceil((((ballLocation + 720) - heading) % 360.0) / (360.0 / 38));

        if ((ballSpace < 1) || (ballSpace > 38))
        {
            System.err.println(
                "The roulette wheel is reporting an invalid " +
                " space on the wheel of " + ballSpace +
                " for a location" + " of " + ballLocation +
                " and heading of " + heading);

            return -1;
        }

        return ballSpace;
    }
}
