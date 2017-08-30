package card;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author David Stewart.
 */
public class DeckTest {

  /**
   * Ensure the constructor creates a card correctly.
   */
  @Test
  public void whenDeckIsBuilt_all52cardsAreCorrect() {
    Deck deck = new Deck();

    List<String> cardNames = new ArrayList<>(52);
    cardNames.add("Ace of Hearts");
    cardNames.add("Two of Hearts");
    cardNames.add("Three of Hearts");
    cardNames.add("Four of Hearts");
    cardNames.add("Five of Hearts");
    cardNames.add("Six of Hearts");
    cardNames.add("Seven of Hearts");
    cardNames.add("Eight of Hearts");
    cardNames.add("Nine of Hearts");
    cardNames.add("Ten of Hearts");
    cardNames.add("Jack of Hearts");
    cardNames.add("Queen of Hearts");
    cardNames.add("King of Hearts");

    cardNames.add("Ace of Clubs");
    cardNames.add("Two of Clubs");
    cardNames.add("Three of Clubs");
    cardNames.add("Four of Clubs");
    cardNames.add("Five of Clubs");
    cardNames.add("Six of Clubs");
    cardNames.add("Seven of Clubs");
    cardNames.add("Eight of Clubs");
    cardNames.add("Nine of Clubs");
    cardNames.add("Ten of Clubs");
    cardNames.add("Jack of Clubs");
    cardNames.add("Queen of Clubs");
    cardNames.add("King of Clubs");

    cardNames.add("Ace of Diamonds");
    cardNames.add("Two of Diamonds");
    cardNames.add("Three of Diamonds");
    cardNames.add("Four of Diamonds");
    cardNames.add("Five of Diamonds");
    cardNames.add("Six of Diamonds");
    cardNames.add("Seven of Diamonds");
    cardNames.add("Eight of Diamonds");
    cardNames.add("Nine of Diamonds");
    cardNames.add("Ten of Diamonds");
    cardNames.add("Jack of Diamonds");
    cardNames.add("Queen of Diamonds");
    cardNames.add("King of Diamonds");

    cardNames.add("Ace of Spades");
    cardNames.add("Two of Spades");
    cardNames.add("Three of Spades");
    cardNames.add("Four of Spades");
    cardNames.add("Five of Spades");
    cardNames.add("Six of Spades");
    cardNames.add("Seven of Spades");
    cardNames.add("Eight of Spades");
    cardNames.add("Nine of Spades");
    cardNames.add("Ten of Spades");
    cardNames.add("Jack of Spades");
    cardNames.add("Queen of Spades");
    cardNames.add("King of Spades");

    assertEquals(52, deck.size());

    deck.stream().forEach(card ->
      assertTrue(cardNames.contains(card.toString())));
  }

}
