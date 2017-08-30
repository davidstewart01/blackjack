package card;

/**
 * Class that represents a single card.
 *
 * @author David Stewart
 */
public class Card {

  //---------------------------------------------------------------------
  // CONSTANTS
  //---------------------------------------------------------------------

  public static final String ACE_CARD_NAME = "Ace";
  public static final String TWO_CARD_NAME = "Two";
  public static final String THREE_CARD_NAME = "Three";
  public static final String FOUR_CARD_NAME = "Four";
  public static final String FIVE_CARD_NAME = "Five";
  public static final String SIX_CARD_NAME = "Six";
  public static final String SEVEN_CARD_NAME = "Seven";
  public static final String EIGHT_CARD_NAME = "Eight";
  public static final String NINE_CARD_NAME = "Nine";
  public static final String TEN_CARD_NAME = "Ten";
  public static final String JACK_CARD_NAME = "Jack";
  public static final String QUEEN_CARD_NAME = "Queen";
  public static final String KING_CARD_NAME = "King";

  //---------------------------------------------------------------------
  // ENUMS
  //---------------------------------------------------------------------

  /**
   * The ranks that can be associated with a card.
   */
  public enum Rank {

      ACE(ACE_CARD_NAME, 1), TWO(TWO_CARD_NAME, 2), THREE(THREE_CARD_NAME, 3), FOUR(FOUR_CARD_NAME, 4),
      FIVE(FIVE_CARD_NAME, 5), SIX(SIX_CARD_NAME, 6), SEVEN(SEVEN_CARD_NAME, 7), EIGHT(EIGHT_CARD_NAME, 8),
      NINE(NINE_CARD_NAME, 9), TEN(TEN_CARD_NAME, 10), JACK(JACK_CARD_NAME, 10), QUEEN(QUEEN_CARD_NAME, 10),
      KING(KING_CARD_NAME, 10);

    /**
     * Rank constructor.
     *
     * @param pName
     *   The rank name e.g. Ace, Two, Jack etc.
     * @param pValue
     *   The rank value. This is basically what the card is worth i.e. 1 - 11.
     */
    Rank(String pName, int pValue) {
      setName(pName);
      setValue(pValue);
    }

    //-------------------------
    // property: name
    //-------------------------
    private String mName = "";

    /**
     * @param pName
     *   The rank name e.g. Ace, Two, Jack etc.
     */
    public void setName(String pName) {
      mName = pName;
    }

    /**
     * @return
     *   The rank name e.g. Ace, Two, Jack etc.
     */
    public String getName() {
      return mName;
    }

    //-------------------------
    // property: value
    //-------------------------
    private int mValue = 0;

    /**
     * @param pValue
     *   The rank value. This is basically what the card is worth i.e. 1 - 11..
     */
    public void setValue(int pValue) {
      mValue = pValue;
    }

    /**
     * @return
     *   The rank value. This is basically what the card is worth i.e. 1 - 11.
     */
    public int getValue() {
      return mValue;
    }
  }

  /**
   * The suits that can be associated with a card.
   */
  public enum Suit {
    HEARTS("Hearts"), DIAMONDS("Diamonds"), CLUBS("Clubs"), SPADES("Spades");

    /**
     * Suit constructor.
     *
     * @param pName
     *   The name of card suit.
     */
    Suit(String pName) {
      setName(pName);
    }

    //-------------------------
    // property: name
    //-------------------------
    private String mName = "";

    /**
     * @param pName
     *   The name of card suit.
     */
    public void setName(String pName) {
      mName = pName;
    }

    /**
     * @return
     *   The name of card suit.
     */
    public String getName() {
      return mName;
    }
  }

  //---------------------------------------------------------------------
  // PROPERTIES
  //---------------------------------------------------------------------

  //--------------------------
  // property: rank
  //--------------------------
  public Rank mRank = null;

  /**
   * @param pRank
   *   The rank of this card.
   */
  public void setRank(Rank pRank) {
    mRank = pRank;
  }

  /**
   * @return
   *   The rank of this card.
   */
  public Rank getRank() {
    return mRank;
  }

  //--------------------------
  // property: suit
  //--------------------------
  public Suit mSuit = null;

  /**
   * @param pSuit
   *   The suit of this card.
   */
  public void setSuit(Suit pSuit) {
    mSuit = pSuit;
  }

  /**
   * @return
   *   The suit of this card.
   */
  public Suit getSuit() {
    return mSuit;
  }

  //--------------------------
  // property: id
  //--------------------------
  public String mId = null;

  /**
   * @param pId
   *   The Id of this card. This should be the shorthand identifier for the card.
   */
  public void setId(String pId) {
    mId = pId;
  }

  /**
   * @return
   *   The Id of this card. This should be the shorthand identifier for the card.
   */
  public String getId() {
    return mId;
  }

  //---------------------------------------------------------------------
  // CONSTRUCTORS
  //---------------------------------------------------------------------

  /**
   * Initialise the card.
   *
   *@param pSuit:
   *  This is the card's suit i.e. Clubs, Hearts, Diamonds, Spades.
   *@param pRank:
   *  This is the card's rank i.e. 1, 2, 3, 4 etc.
   */
  public Card(Suit pSuit, Rank pRank) {
    setRank(pRank);
    setSuit(pSuit);

    // Set the shorthand id of the card.
    switch (pRank.getName()) {
      case ACE_CARD_NAME:
        setId("a" + pSuit.getName().toLowerCase().substring(0, 1));
        break;
      case JACK_CARD_NAME:
        setId("j" + pSuit.getName().toLowerCase().substring(0, 1));
        break;
      case QUEEN_CARD_NAME:
        setId("q" + pSuit.getName().toLowerCase().substring(0, 1));
        break;
      case KING_CARD_NAME:
        setId("k" + pSuit.getName().toLowerCase().substring(0, 1));
        break;
      default:
        setId(Integer.toString(pRank.getValue()) + pSuit.getName().toLowerCase().substring(0, 1));
    }
  }

  //---------------------------------------------------------------------
  // METHODS
  //---------------------------------------------------------------------

  /**
   * Determine the blackjack value of the card.
   *
   * @return
   *   When the value is greater than 9, return 10, otherwise
   *   the rank of the card.
   */
  public int getValue() {
    return getRank().getValue();
  }

  /**
   * @return
   *   A string representation of the card.
   */
  public String toString() {
    return getRank().getName() + " of " + getSuit().getName();
  }

}
