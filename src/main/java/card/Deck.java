package card;

import java.util.*;

/**
 * Class that represents a single deck of cards.
 *
 * @author David Stewart
 */
public class Deck {

  public static Map<Integer, String> RANKS = new HashMap<>();
  public static Map<String, String> SUITS = new HashMap<>();

  static {
    // Set ranks.
    RANKS.put(1, "Ace"); RANKS.put(2, "Two"); RANKS.put(3, "Three");
    RANKS.put(4, "Four"); RANKS.put(5, "Five"); RANKS.put(6, "Six");
    RANKS.put(7, "Seven"); RANKS.put(8, "Eight"); RANKS.put(9, "Nine");
    RANKS.put(10, "Ten"); RANKS.put(11, "Jack"); RANKS.put(12, "Queen");
    RANKS.put(13, "King");

    // Set suits.
    SUITS.put("h", "Hearts"); SUITS.put("c", "Clubs");
    SUITS.put("d", "Diamonds"); SUITS.put("s", "Spades");
  }

  //----------------------------------------
  // property: cards
  //----------------------------------------
  private List<Card> mCards = new ArrayList<>();

  /**
   * @param pCards
   *   The cards in the deck.
   */
  public void setCards(List<Card> pCards) {
    mCards = pCards;
  }

  /**
   * @return
   *   The cards in the deck.
   */
  public List<Card> getCards() {
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
  public static List<Card> buildDeck() {
    List<Card> deck = new ArrayList<>(52);

    SUITS.forEach((suitKey, suitValue) -> {
      RANKS.forEach(
        (rankKey, rankValue) -> deck.add(new Card(suitKey, rankKey)));
    });

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
