public class Deck {

    public static int numSuits = 4;
    public static int numCardsPerSuit = 13;
    public static int numCards = numSuits * numCardsPerSuit;

    private Card[][] cards;

    public Deck() {
        cards = new Card[numSuits][numCardsPerSuit];
        for (int suit = Card.DIAMONDS; suit <= Card.SPADES; suit++) {
            for (int number = Card.ACE; number <= Card.KING; number++) {
                cards[suit-1][number-1] = new Card(number, suit);
            }
        }
    }
    public Card getCard(int suit, int number) {
        return cards[suit-1][number-1];
    }
}
