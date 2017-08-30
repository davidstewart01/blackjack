package card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Class that represents a single deck of cards.
 *
 * @author David Stewart
 */
public class Deck extends LinkedList<Card> {

  /**
   * Default constructor. Build the deck of cards.
   */
  public Deck() {
    buildDeck();
  }

  /**
   * Build a fresh deck of cards.
   */
  public void buildDeck() {
    for (Card.Suit suit : Card.Suit.values()) {
      for (Card.Rank rank : Card.Rank.values()) {
        add(new Card(suit, rank));
      }
    }
  }

  /**
   * To ensure that the deck is sufficiently shuffled,
   * it will be randomly shuffled between 5 to 20 times.
   *
   * @param pDeck
   *   The deck to be shuffled.
   */
  public void shuffleDeck(List<Card> pDeck) {

    // Set numShuffles to random number between 5 and 20.
    Random random = new Random();
    int numShuffles = random.nextInt(20 - 5) + 5;

    for (int i = 0; i < numShuffles; i++) {
      Collections.shuffle(pDeck);
    }
  }
}
