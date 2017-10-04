
package eecs.instructor.gossett;

/**
 * Plays the game of roulette.
 * @author Karl A. Gossett
 */
public class Roulette042 extends eecs.RouletteGui {

    public static void playNumberSum() {
        // Put your code here to implement the Number-Sum strategy

    }
    
    public static void playNumberDifference() {
        // Put your code here to implement the Number-Difference Strategy
        
    }
    
    public static void playRedBlack() {
        // Put your code here to implement the Red/Black Strategy
        
    }
    
    public static void playOddEven() {
        // Put your code here to implement the Odd/Even strategy
        
    }
    
    public static void play1To18() {
        // Put your code here to implement the 1-18/19-36 strategy
        
    }
    
    public static void play1To12() {
        // Put your code here to implement the 1-12/13-24/25-36 strategy
        
    }
    
    public static void playAnyNumber() {
        // Put your code here to implement the Any Number strategy
        
    }
    
    public static void playColumn() {
        // Put your code here to implement the Column "2:1" strategy
        
    }
    
    /**
     * Play the game of roulette.
     * 
     */
    public static void main(String[] args) {
        // You can look at this code, but there's no reason you should be modifying it.
        boolean keepPlaying = true;
        while (keepPlaying) {
            int gameToPlay = getGame();
            if (gameToPlay == 1) {
                playNumberSum();
            } else if (gameToPlay == 2) {
                playNumberDifference();
            } else if (gameToPlay == 3) {
                playRedBlack();
            } else if (gameToPlay == 4) {
                playOddEven();
            } else if (gameToPlay == 5) {
                play1To18();
            } else if (gameToPlay == 6) {
                play1To12();
            } else if (gameToPlay == 7) {
                playAnyNumber();
            } else if (gameToPlay == 8) {
                playColumn();
            } else {
                // It wasn't one of the 1-8 options, so must've wanted to quit
                keepPlaying = false;
            }
        }
    }
    
    /**
     * Prompts the user to select the roulette game they want to play and returns the choice.
     * Choices are returned as an integer 0-8, with 0 being "Quit" (also returns 0 if the choice is closed
     * with the 'X' close button).
     * @return The user's choice as an integer 0-8.
     */
    public static int getGame(){
        int game = 0;
        String[] gameList = {"Quit","Number Sum","Number Difference","Red/Black","Odd/Even","1-18/19-36",
                "1-12/13-24/25-36","Any Number","Column 2:1"};
        // chooseFromList returns a number starting at 0, but since Quit is #0, this matches the selection
        game = chooseFromList("Choose a Game",gameList);
        return game;
    }
}
