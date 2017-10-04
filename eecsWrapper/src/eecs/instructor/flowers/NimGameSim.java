package eecs.instructor.flowers;

import eecs.jago.instructor.flowers.Marble;


/**
* GUI for writing games that play the game of Nim. This game uses
* marbles. The setInitialSize() method sets the initial size of
* the pile of marbles. The pile must be between 1 - 100 marbles.
* Players may remove 1 or no more than half of the marbles on their
* turn.  The person to draw the last marble loses.
*/
public class NimGameSim extends eecs.RobotGuiBase {
    static {
        sim = new jago.SimBox();
        sim.setSimTitle("The Game of Nim");
        sim.setBackgroundImage("nim.gif");
        frame = sim;
        sim.setVisible(true);
        robot = null;
    }

    public static int MAX_MARBLES = 100;
    private static int marbleCount = 0;
    private static int initSize = 0;
    private static Marble[] marbles = new Marble[MAX_MARBLES];
    private static boolean marblesCreated = false;
    private static int START_X = 100;
    private static int START_Y = 300;

	 /**
	 * Sets the initial size of the pile of marbles. May only be
	 * called once. Calling more than once will cause an
	 * IllegalStateException to be thrown.
	 * @param size  The initial size of the pile (1 - 100)
	 */
    public static void setInitialSize(int size) {
        if ((size < 1) || (size > MAX_MARBLES)) {
            throw new IllegalArgumentException(
                "Initial size must be between 1 and " + MAX_MARBLES + ".");
        }

        marbleCount = size;
        initSize = size;

        if (!marblesCreated) {
            createMarbles();
        } else {
            throw new IllegalStateException(
                "The initial size has already been set.");
        }

        initDisplay();
    }

	 /**
	 * Removes marbles from the pile. The number must be between 1 and
	 * 100, and it cannot be more than half of the remaining marbles
	 * on the pile.
	 * @param number  The number to remove from the pile
	 */
    public static void removeMarble(int number) {
        if ((number < 0) ||
                ((marbleCount > 1) && (number > (marbleCount / 2)))) {
            throw new IllegalArgumentException(
                "Illegal argument was passed into " +
                "removeMarble: the argument must be greater than zero and it cannot " +
                "be greater than half of the marbles left on the pile.");
        }

        for (int i = 1; (i <= number) && (marbleCount > 0); i++) {
            sim.remove(marbles[marbleCount - 1]);
            marbleCount--;
        }
    }

	/**
     * Clears the display of marbles. The setInitialSize() method may be
     * called again after this method has been called.
     */
    public static void reset() {
        while (marbleCount > 0) {
            removeMarble(1);
        }

        marblesCreated = false;
    }

    private static void initDisplay() {
        int x = START_X;
        int y = START_Y;
        int deltaX = 15;
        int deltaY = 15;
        int rows = (int) Math.ceil(initSize / 10.0);
        int idx = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; (j < 10) && (idx < initSize); j++) {
                sim.add(marbles[idx], x += deltaX, y);
                idx++;
            }

            x = START_X;
            y = y - deltaY;
        }
    }

    private static void createMarbles() {
        if (!marblesCreated) {
            for (int i = 0; i < initSize; i++) {
                if ((i % 2) == 0) {
                    marbles[i] = new Marble(Marble.RED);
                } else if ((i % 3) == 0) {
                    marbles[i] = new Marble(Marble.BLUE);
                } else {
                    marbles[i] = new Marble(Marble.YELLOW);
                }
            }

            marblesCreated = true;
        }
    }
}
