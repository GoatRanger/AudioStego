/* Copyright (C) 2003  USMA
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

package eecs.instructor.cobb;

import jago.ImageLoader;

import jago.element.Message;

import java.awt.Image;


/**
* GUI for writing games that play the game of Nim. This game uses
* marbles. The setInitialSize() method sets the initial size of
* the pile of marbles. The pile must be between 1 - 100 marbles.
* Players may remove 1 or no more than half of the marbles on their
* turn.  The person to draw the last marble loses.
*/
public class Two22 extends eecs.RobotGuiBase {
  private static final int MAX_DOWN_CARDS = 250;

  static {
    sim = new jago.SimBox();
    sim.setSimTitle("2-22");

    Image bg = ImageLoader.getImage(Two22.class, "/eecs/instructor/cobb/images/7-27.GIF");
    sim.setBackgroundImage(bg);

    frame = sim;
    sim.setVisible(true);
    robot = null;
  }

  public static int MAX_MARBLES = 100;
  public static int MAX_CARDS = 1000;
  private static int marbleCount = 0;
  private static int cardCount = 0;
  private static int initSize = 0;
  private static Card[] cards = new Card[MAX_CARDS];
  private static Card[] downCard = new Card[MAX_DOWN_CARDS];
  private static boolean cardsCreated = false;
  private static boolean marblesCreated = false;
  private static int START_X = 100;
  private static int START_Y = 240;
  private static PlayerObj humanPlayer = new PlayerObj();
  private static PlayerObj dealer = new PlayerObj();
  private static int idx = 0; //array index for created cards.
  private static int dnCardCount = 0;
  private static int round = 0;
  private static Message msg;
  private static Message bankMsg;
  private static Message roundMsg;
  private static Message scoreMsg;
  private static Message humanPlayerBankValueMsg;
  private static Message dealerBankValueMsg;
  private static Message humanCardValueMsg;
  private static Message dealerCardValueMsg;
  private static int humanY = 350;

  /**
     * Sets up the entire match.  Currently creates both the dealer and human player objects
     *
     */
  public static void initializeMatch() {
    printLine("intializing Match.....");
    humanPlayer.setBank(5000);
    dealer.setBank(5000);
    humanPlayer.setTitle(new Message("Human Player"));
    humanPlayer.setTitleLocation(150, humanY - 10);
    dealer.setTitle(new Message("Dealer"));
    dealer.setTitleLocation(150, 15);
    initDisplay2(humanPlayer);
    initDisplay2(dealer);
    roundMsg = new Message("ROUND " + round);
    humanPlayerBankValueMsg = new Message("$" + humanPlayer.getBank());
    dealerBankValueMsg = new Message("$" + dealer.getBank());
    humanCardValueMsg = new Message("" + humanPlayer.getCurCardTotal());
    dealerCardValueMsg = new Message("" + dealer.getCurCardTotal());
    setInitialSize(1000);

    //createMarbles();
    printLine("Match initialized");
  }

  /**
     * deals player one card face up, updated card total, and updates the display.
     *
     */
  public static void givePlayerACard() {
    sim.add(cards[idx], humanPlayer.getCurCardPositionX(), humanY);
    humanPlayer.addtoCardTotal(cards[idx].value);
    idx++;
    humanPlayer.advanceCurCardPositionX();
    updateDisplay();
  }

  public static int getDealerBank() {
    return dealer.getBank();
  }

  public static int getHumanBank() {
    return humanPlayer.getBank();
  }

  public static int getRoundNumber() {
    return round;
  }

  public static int convertStringToInt(String str) {
    int value = 0;

    try {
      value = Integer.parseInt(str);
    } catch (NumberFormatException e) {
      showError("Converting " + str + " to an integer: failed.");
    }

    return value;
  }

  public static void resumeMatch(int playerBank, int dealerBank, int savedRound) {
    round = savedRound;
    humanPlayer.setBank(playerBank);
    dealer.setBank(dealerBank);
    printLine("Match resumed");
    updateDisplay();
  }

  /**
   * deals player one card face up, updated card total, and updates the display.
   *
   */
  public static void giveDealerACard() {
    sim.add(cards[idx], dealer.getCurCardPositionX(), 20);
    dealer.addtoCardTotal(cards[idx].value);
    idx++;
    dealer.advanceCurCardPositionX();

    updateDisplay();
  }

  public static void flipDownCard() {
    giveDealerACard();
  }

  public static void givePotToPlayer() {
    humanPlayer.addToBank(2000);
  }

  public static void splitPot() {
    humanPlayer.addToBank(1000);
    dealer.addToBank(1000);
  }

  public static void givePotToDealer() {
    dealer.addToBank(2000);
  }

  /**
   * Checks to make sure both players have money.  returns true if both have money.
   *
   * @param size
   */
  public static boolean doBothPlayersHaveMoney() {
    if ((humanPlayer.getBank() > 0) & (dealer.getBank() > 0)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   *  deals one card to either the player or the dealer depending on the parameter past.
   *
   */
  public static double getPlayerCardTotal() {
    return humanPlayer.getCurCardTotal();
  }

  public static double getDealerCardTotal() {
    return dealer.getCurCardTotal();
  }

  public static void getCard(String player) {
  }

  /**
   * deals the first two cards to both players with one card dealt down to the dealer.
   * Display is updated (round, card totals, etc)
   */
  public static void initializeNewRound() {
    int x = START_X;
    int y = START_Y;
    int deltaX = 15;

    printLine("Initializing a New Round");
    humanPlayer.setCurCardPositionX(100);
    humanPlayer.setCurCardPositionY(humanY);
    humanPlayer.setCurCardValue(0);
    dealer.setCurCardPositionX(100);
    dealer.setCurCardPositionY(20);
    dealer.setCurCardValue(0);

    //clear table
    if (round != 0) {
      reset();
    }

    round++;
    cardCount = initSize;
    humanPlayer.anteUp(1000);
    dealer.anteUp(1000);

    //deal 2 cards to humanplayer
    for (int i = 0; i < 2; i++) {
      //THis deals 1 card/loop the 4th card is the down card. 

      /*sim.add(cards[idx], humanPlayer.getCurCardPositionX(),230);
      printLine("***just added card "+idx);
      humanPlayer.addtoCardTotal(cards[idx].value);
      idx++; */
      givePlayerACard();

      if (i == 1) {
        sim.add(downCard[dnCardCount], dealer.getCurCardPositionX(), 20);
        dnCardCount++;
      } else {
        sim.add(cards[idx], dealer.getCurCardPositionX(), 20);

        dealer.addtoCardTotal(cards[idx].value);
        printLine("***just added card " + idx);
        idx++;
      }

      printLine(dealer.getCurCardTotal());

      //humanPlayer.advanceCurCardPositionX();
      dealer.advanceCurCardPositionX();
    }

    updateDisplay();
    dealer.setCurCardPositionX(dealer.getCurCardPositionX() - 15); //this allows the face down card to be covered on dealer's turn          
  }

  /**
  * Sets the initial size of the pile of marbles. May only be
  * called once. Calling more than once will cause an
  * IllegalStateException to be thrown.
  * @param size  The initial size of the pile (1 - 100)
  */
  public static void setInitialSize(int size) {
    cardCount = size;

    initSize = size;

    if (!cardsCreated) {
      createCards();
    } else {
      throw new IllegalStateException("The initial size has already been set.");
    }

    //initDisplay();
  }

  /**
    * Removes cards from the table. The number must be between 1 and
    *
    *
    * @param number  The number to remove from the pile
    */
  public static void removeCards(int number) {
    if ((number < 0) || ((cardCount > 1) && (number > (cardCount / 2)))) {
      throw new IllegalArgumentException("Illegal argument was passed into " +
        "removeMarble: the argument must be greater than zero and it cannot " +
        "be greater than half of the marbles left on the pile.");
    }

    for (int i = 1; (i <= number) && (cardCount > 0); i++) {
      sim.remove(cards[cardCount - 1]);
      cardCount--;
    }
  }

  /**
  * Removes marbles from the pile. The number must be between 1 and
  * 100, and it cannot be more than half of the remaining marbles
  * on the pile.
  * @param number  The number to remove from the pile
  */

  /* public static void removeMarble(int number) {
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
  }*/

  /**
   * Clears the display of marbles. The setInitialSize() method may be
   * called again after this method has been called.
   */
  public static void reset() {
    //   while (cardCount > 0) {
    //       removeCards(1);
    for (int i = 0; i < 1000; i++) {
      if (cards[i].inASimulation()) {
        sim.remove(cards[i]);
      }
    }

    for (int i = 0; i < MAX_DOWN_CARDS; i++) {
      if (downCard[i].inASimulation()) {
        sim.remove(downCard[i]);
      }
    }

    //cardsCreated = false;
    humanPlayer.setCurCardValue(0.0);
    dealer.setCurCardValue(0.0);
  }

  private static void initDisplay2(PlayerObj player) {
    int x = player.getCurCardPositionX();
    int y = player.getCurCardPositionY();
    int deltaX = 15;
    int titleX = player.getTitleXLocation();
    int titleY = player.getTitleYLocation();

    int idx = 0;

    msg = player.getTitle();
    bankMsg = new Message("BANK");

    scoreMsg = new Message("Current Hand");
    printLine(player.getBank());

    //currently the cards display horizontally
    sim.add(msg, titleX, titleY);
    sim.add(bankMsg, 20, titleY);

    sim.add(scoreMsg, 300, titleY);

    //display the pot!
    printLine("player Initialized");
  }

  public static void updateDisplay() {
    if (humanPlayerBankValueMsg.inASimulation()) {
      sim.remove(humanPlayerBankValueMsg);
    }

    if (dealerBankValueMsg.inASimulation()) {
      sim.remove(dealerBankValueMsg);
    }

    if (roundMsg.inASimulation()) {
      sim.remove(roundMsg);
    }

    if (humanCardValueMsg.inASimulation()) {
      sim.remove(humanCardValueMsg);
    }

    if (dealerCardValueMsg.inASimulation()) {
      sim.remove(dealerCardValueMsg);
    }

    roundMsg = new Message("ROUND " + round);
    humanPlayerBankValueMsg = new Message("$" + humanPlayer.getBank());
    dealerBankValueMsg = new Message("$" + dealer.getBank());
    humanCardValueMsg = new Message("" + humanPlayer.getCurCardTotal());
    dealerCardValueMsg = new Message("" + dealer.getCurCardTotal());
    sim.add(humanPlayerBankValueMsg, 20, humanPlayer.getTitleYLocation() + 20);
    sim.add(dealerBankValueMsg, 20, dealer.getTitleYLocation() + 20);
    sim.add(roundMsg, 20, 225);
    sim.add(humanCardValueMsg, 300, humanPlayer.getTitleYLocation() + 20);
    sim.add(dealerCardValueMsg, 300, dealer.getTitleYLocation() + 20);

    //RoundMsg.setAppearance("Round "+round);
    //sim.add(RoundMsg,300,175);
  }

  private static void initDisplay() {
    int x = START_X;
    int y = START_Y;
    int deltaX = 30;

    int idx = 0;
    Message msg;
    Message msg2;
    msg = new Message("HUMAN PLAYER");
    msg2 = new Message("DEALER");

    //currently the cards display horizontally
    sim.add(msg, 150, 225);
    sim.add(msg2, 150, 0);

    /*      for (int j = 0; (j < 10); j++) {
        sim.add(marbles[idx], x += deltaX,y);

         printLine(" x "+x+" y "+y);
        idx++;




    }*/
  }

  private static void createCards() {
    int randomIndex;
    String randomSuit;
    String[] deck = {
      "k", "a", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q"
    };
    String[] suit = { "c", "d", "h", "s" };

    //printLine("creating Cards"+cardsCreated+initSize);
    if (!cardsCreated) {
      for (int i = 0; i < initSize; i++) {
        randomIndex = getRandomIndex(deck.length);
        randomSuit = suit[getRandomIndex(suit.length)];

        cards[i] = new Card(randomIndex, randomSuit);
      }
    }

    for (int j = 0; j < MAX_DOWN_CARDS; j++) {
      downCard[j] = new Card(333, "h");
    }

    cardsCreated = true;
  }

  private static int getRandomIndex(int size) {
    int randomVal;

    randomVal = (int) (size * Math.random());

    return randomVal;
  }

  /* private static void createMarbles() {
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
     marbles[3]=new Marble(Marble.down);
     marblesCreated = true;
   }
  }*/
}
