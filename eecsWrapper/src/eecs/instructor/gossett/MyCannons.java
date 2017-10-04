package eecs.instructor.gossett;

public class MyCannons extends CannonFodder {

    public static void main(String[] args) {
		String[] stuff = {"one","two","three"};
		chooseButton("String1", stuff);
		startGame();
        while (isLeftAlive() && isRightAlive()) {
            char keyPress = getCurrentChar();
            if (keyPress == 'a') {
                turnLeftCannon(LEFT);
            }
            if (keyPress == 'd') {
                turnLeftCannon(RIGHT);
            }
            if (keyPress == 'j') {
                turnRightCannon(LEFT);
            }
            if (keyPress == 'l') {
                turnRightCannon(RIGHT);
            }
            if (keyPress == 's') {
                fireLeftCannon(15);
            }
            if (keyPress == 'k') {
                fireRightCannon(15);
            }
        }
		printLine("Game over");
    }
}