package eecs.robot.instructor.bennett;

// Description: Pick3 analyzes the past 52 weeks of Pick 3 lottery drawings and
// determines which numbers were picked with the greatest and least frequency.
// It also displays a histogram for the user which graphically depicts how many times each number
// occurs. It does this by marking a "*" next to each of the numbers 1 thru 10.
public class Pick3 extends eecs.Gui {
  final static int TOTAL_WEEKS = 52;
  final static int NUMBERS_PER_WEEK = 3;
  private static int pick3Array[] = { 8, 3, 5, 5, 8, 3, 2, 7, 8, 4, 7, 3, 4, 7, 1, 4, 7, 4, 9, 4, 2, 8, 8, 10, 8, 10, 4, 5, 8, 7, 7, 2, 8,
      5, 2, 4, 6, 7, 2, 2, 1, 1, 1, 2, 2, 9, 4, 7, 3, 5, 7, 3, 6, 10, 5, 8, 5, 10, 2, 10, 4, 8, 6, 10, 2, 7, 8, 2, 9, 2, 7, 3, 9, 3, 9, 10,
      5, 4, 5, 3, 6, 4, 3, 3, 2, 6, 1, 5, 4, 8, 9, 8, 2, 3, 10, 3, 3, 3, 2, 2, 6, 1, 6, 1, 1, 2, 6, 1, 9, 5, 4, 2, 4, 2, 2, 9, 7, 4, 2, 1,
      6, 1, 10, 5, 1, 7, 1, 6, 2, 2, 2, 10, 8, 2, 6, 5, 8, 5, 1, 8, 3, 2, 8, 9, 9, 2, 6, 3, 10, 4, 10, 3, 3, 10, 6, 6 };
  private static int weeks = 0;
  private static int numbersToGoBack = 0;
  private static int row = 1;
  private static int mostOftenOccuring = -1;
  private static int leastOftenOccuring = -1;
  private static int counterForMostOften = -1;
  private static int counterForLeastOften = pick3Array.length;
  private static int counter = 0;
  private static boolean keepGoing = true;
  private static double sum = 0.0;
  private static double average = 0.0;
  private static int numberOfStars = 0;
  private static int number = 0;
  private static int index = 0;

  public static int getNextNumber() {
    return pick3Array[index++];
  }

  public static int resetNextNumber() {
    return index = 0;
  }

  public static void main(String[] args) {
    //    Pick3.solution();
  }

  public static void solution() {
    while (keepGoing == true) {
      weeks = 0;
      while ((weeks <= 0) || (weeks > 52)) {
        weeks = getInt("How many weeks back would you like to analyze?") - 1;
      }
      if (weeks < TOTAL_WEEKS) {
        numbersToGoBack = ((weeks - 1) * NUMBERS_PER_WEEK);
        sum = 0;
        row = 1;
        while (row <= 10) {
          print(row);
          if (row < 10) {
            print(" ");
          }
          print("- ");
          resetNextNumber();
          numberOfStars = 0;
          counter = 0;
          while (counter < numbersToGoBack) {
            number = getNextNumber();
            if (row == number) {
              print("*");
              sum = sum + number;
              numberOfStars++;
            }
            counter++;
          }
          printLine();
          if (numberOfStars > counterForMostOften) {
            mostOftenOccuring = row;
            counterForMostOften = numberOfStars;
          }
          if (numberOfStars < counterForLeastOften) {
            leastOftenOccuring = row;
            counterForLeastOften = numberOfStars;
          }
          row++;
        }
        average = (sum / numbersToGoBack);
        printLine("========================");
        printLine("The most frequently occuring number was " + mostOftenOccuring + " which was picked " + counterForMostOften + " times.");
        printLine("The least frequently occuring number was " + leastOftenOccuring + " which was picked " + counterForLeastOften
            + " times.");
        printLine("The mean of all numbers chosen was " + average);
        printLine("");
        keepGoing = (BUTTON1 == chooseButton("Would you like to go again or exit?", "Keep Going", "Exit"));
      }
      else {
        printLine("You entered more weeks than are in the data set.");
        print("Currently, there are only " + TOTAL_WEEKS + " weeks of data available.");
        printLine("Please input a number less than or equal to " + TOTAL_WEEKS);
      }
    }
  }
}