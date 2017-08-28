package card;

/**
 * Class that represents a single card.
 *
 * @author David Stewart
 */
public class Card {

  //--------------------------
  // property: rank
  //--------------------------
  public int mRank = 0;

  /**
   * @param pRank
   *   The rank of this card.
   */
  public void setRank(int pRank) {
    mRank = pRank;
  }

  /**
   * @return
   *   The rank of this card.
   */
  public int getRank() {
    return mRank;
  }

  //--------------------------
  // property: suit
  //--------------------------
  public String mSuit = null;

  /**
   * @param pSuit
   *   The suit of this card.
   */
  public void setSuit(String pSuit) {
    mSuit = pSuit;
  }

  /**
   * @return
   *   The suit of this card.
   */
  public String getSuit() {
    return mSuit;
  }

  /**
   * Initialise the card.
   *
   *@param pSuit:
   *  This is the card's suit i.e. Clubs, Hearts, Diamonds, Spades.
   *@param pRank:
   *  This is the card's rank i.e. 1, 2, 3, 4 etc.
   */
  public Card(String pSuit, int pRank) {
    setRank(pRank);
    setSuit(pSuit);
  }

  /**
   * Determine the blackjack value of the card.
   *
   * @return
   *   When the value is greater than 9, return 10, otherwise
   *   the rank of the card.
   */
  public int getValue() {
    if (getRank() > 9) {
      return 10;
    }
    else {
      return getRank();
    }
  }

  /**
   * @return
   *   A string representation of the card.
   */
  public String toString() {
    return Deck.RANKS.get(getRank()) + " of " + Deck.SUITS.get(getSuit());
  }

}
