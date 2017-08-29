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
   * When a card's rank is 9 or less, the number of the card
   * is a correct value.
   */
  @Test
  public void whenGetValueIsInvoked_theCorrectValueIsReturned() {
    Card card = new Card(Card.Suit.DIAMONDS, Card.Rank.KING);
    assertEquals(10, card.getValue());
  }

}
