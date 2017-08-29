package card;

/**
 * Class that represents a single card.
 *
 * @author David Stewart
 */
public class Card {
  
  //---------------------------------------------------------------------
  // ENUMS
  //---------------------------------------------------------------------
  
  /**
   * The ranks that can be associated with a card.
   */
  public enum Rank {
    ACE("Ace", 1), TWO("Two", 2), THREE("Three", 3), FOUR("Four", 4), FIVE("Five", 5), SIX("Six", 6), SEVEN("Seven", 7),
    EIGHT("Eight", 8), NINE("Nine", 9), TEN("Ten", 10), JACK("Jack", 10), QUEEN("Queen", 10), KING("King", 10);
    
    Rank(String pName, int pValue) {
      setName(pName);
      setValue(pValue);
    }
    
    private String mName = "";
    
    public void setName(String pName) {
      mName = pName;
    }
    
    public String getName() {
      return mName;
    }
    
    private int mValue = 0;
    
    public void setValue(int pValue) {
      mValue = pValue;
    }
    
    public int getValue() {
      return mValue;
    }
  }
  
  /**
   * The suits that can be associated with a card.
   */
  public enum Suit {
    HEARTS("Hearts"), DIAMONDS("Diamonds"), CLUBS("Clubs"), SPADES("Spades");
    
    Suit(String pName) {
      setName(pName);
    }
    
    private String mName = "";
    
    public void setName(String pName) {
      mName = pName;
    }
    
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
    if (getRank().getValue() > 9) {
      return 10;
    }
    else {
      return getRank().getValue();
    }
  }

  /**
   * @return
   *   A string representation of the card.
   */
  public String toString() {
    return getRank().getName() + " of " + getSuit().getName();
  }

}