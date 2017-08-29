package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Class that represents a single deck of cards.
 *
 * @author David Stewart
 */
public class Deck {

  //----------------------------------------
  // property: cards
  //----------------------------------------
  private LinkedList<Card> mCards = new LinkedList<>();

  /**
   * @param pCards
   *   The cards in the deck.
   */
  public void setCards(LinkedList<Card> pCards) {
    mCards = pCards;
  }

  /**
   * @return
   *   The cards in the deck.
   */
  public LinkedList<Card> getCards() {
    return mCards;
  }

  /**
   * Default constructor. Build the deck of cards.
   */
  public Deck() {
    mCards = Deck.buildDeck();
  }

  /**
   * Build a fresh deck of cards.
   */
  public static LinkedList<Card> buildDeck() {
    LinkedList<Card> deck = new LinkedList<>();
  
    for (Card.Suit suit : Card.Suit.values()) {
      for (Card.Rank rank : Card.Rank.values()) {
        deck.add(new Card(suit, rank));
      }
    }

    return deck;
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
