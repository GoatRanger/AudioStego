package eecs.robot.instructor.becknel;

public class BlackJack extends eecs.Gui {
  static final int PLAYER1 = 1;

  static final int PLAYER2 = 2;

  static final int PLAYER3 = 3;

  static final int DEALER = 0;

  static int index = 0;

  static int j = 0;

  static double[] bet = { 0, 0, 0, 0, 0 };

  static double[] bank = { 100000, 500, 500, 500, 100 };

  static int[][] hand = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

  static void bust(int playerNumber) {
    bank[DEALER] = bank[DEALER] + bet[playerNumber];
    bet[playerNumber] = 0;
    j = 0;
    while (j < 18) {
      hand[playerNumber][j] = 0;
    }
  }

  static void placeBet(int playerNumber) {
    bet[playerNumber] = getDouble("Enter the amount of money that Player " + playerNumber + " would like to wager.");
    bank[playerNumber] = bank[playerNumber] - bet[playerNumber];
  }

  static void clearTable() {
    index = PLAYER1;

    while (index <= PLAYER3) {
      //
    }
  }

  static void placeBets() {
    //
  }

  static void dealCards() {
    //
  }

  static void hitOrHold(int playerNumber) {
    //
  }

  public static void main(String[] args) {
    while ((bank[DEALER] >= bank[PLAYER1] + bank[PLAYER2] + bank[PLAYER3])
        && (bank[PLAYER1] > 0 || bank[PLAYER2] > 0 || bank[PLAYER3] > 0)) {
      placeBets();
      dealCards();
      index = PLAYER1;
      while (index <= PLAYER3) {
        hitOrHold(index);
      }
    }
  }
}