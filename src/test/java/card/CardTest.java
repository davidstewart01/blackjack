package card;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Tests the Card class.
 */
public class CardTest {

  /**
   * Ensure the constructor creates a card correctly.
   */
  @Test
  public void whenCardIsConstructedAsAceOfSpades_toStringOutputsAceOfSpades() {
    Card card = new Card(Card.Suit.SPADES, Card.Rank.ACE);
    assertEquals("Ace of Spades", card.toString());
  }

  /**
   * When a card's rank is 10, jack, queen or king, the number
   * of the card will be 10.
   */
  /*@Test
  public void whenValueIsGreaterThan9_10isTheValueOfTheCard() {
    Card card = null;

    for (int i = 10; i <= 13; i++) {
      card = new Card("s", i);
      assertEquals(10, card.getValue());
    }
  }*/

  /**
   * When a card's rank is 9 or less, the number of the card
   * is a correct value.
   */
  /*@Test
  public void whenValueIs9orLess_cardFaceValueIsReturned() {
    Card card = null;

    for (int i = 1; i <= 9; i++) {
      card = new Card("s", i);
      assertEquals(i, card.getValue());
    }
  }*/

}
