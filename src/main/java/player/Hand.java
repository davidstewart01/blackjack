package player;

import card.Card;

import java.util.LinkedList;

/**
 * Class that represents the hand of cards that a player can bet on.
 *
 * @author Paul Anderson
 */
public class Hand extends LinkedList<Card> {

  //-----------------------------------------------------------------
  // MEMBERS
  //-----------------------------------------------------------------

  /** Flag that determines whether or not the hand is splittable. */
  private boolean isSplittable = false;

  //-----------------------------------------------------------------
  // PROPERTIES
  //-----------------------------------------------------------------

  //----------------------------------------
  // property: bet
  //----------------------------------------
  private double mBet = 0;

  /**
   * @return
   *   The bet wagered on the Hand.
   */
  public double getBet() {
    return mBet;
  }

  /**
   * @param pBet
   *   The bet wagered on the Hand.
   */
  public void setBet(double pBet) {
    mBet = pBet;
  }
  
  //--------------------------------------------
  // property: sticking
  //--------------------------------------------
  private boolean mSticking = false;
  
  /**
   * @param pSticking
   *   Flag determining whether the player is sticking or not.
   */
  public void setSticking(boolean pSticking) {
    mSticking = pSticking;
  }
  
  /**
   * @return
   *   Flag determining whether the player is sticking or not.
   */
  public boolean isSticking() {
    return mSticking;
  }

  //--------------------------------------------
  // property: bust
  //--------------------------------------------
  private boolean mBust = false;

  /**
   * @param pBust
   *   Flag determining whether the player is bust or not.
   */
  public void setBust(boolean pBust) {
    mBust = pBust;
  }

  /**
   * @return
   *   Flag determining whether the player is bust or not.
   */
  public boolean isBust() {
    return  mBust;
  }

  //----------------------------------------
  // property: hasDoubledDown
  //----------------------------------------
  private boolean mHasDoubledDown = false;

  /**
   * @return
   *   Whether or not the player has made a Double Down play.
   */
  public boolean hasDoubledDown(){
    return mHasDoubledDown;
  }

  /**
   * @param pHasDoubledDown
   */
  public void setHasDoubledDown(boolean pHasDoubledDown) {
    mHasDoubledDown = pHasDoubledDown;
  }

  //-----------------------------------------------------------------
  // METHODS
  //-----------------------------------------------------------------

  /**
   * @return
   *   The running total value of all player 's cards in hand.
   */
  public int getCardTotal() {
    int total = 0;
    int numAces = 0;

    //TODO - tidy up for the hand being played, not just the first one
    for (Card card : this) {
      if (card.getRank().getValue() == 1) {
        ++numAces;
        continue;
      }

      total += card.getRank().getValue();
    }

    // Switch ace values if necessary.
    if (numAces > 0) {

      // Because only one ace in a hand can have a value of 11, check that one ace with a
      // value of 11 plus the other aces with a value of one doesn't exceed 21. If it does
      // exceed 21, all aces in the hand need to have a value of one.

      int acesTotal = 11 + (numAces - 1);
      total += ((acesTotal + total) > 21) ? numAces : acesTotal;
    }

    return total;
  }

  /**
   * @return
   *   Whether or not the player can split the current hand
   */
  public boolean canSplitHand(){
    if((getNumberOfCardsInHand() == 2) && (get(0).getValue() == get(1).getValue())){
      isSplittable = true;
    }
    return isSplittable;
  }

  /**
   * Adds a Card to the current players Hand.
   *
   * @param pCard
   *   the Card to add to the Hand
   */
  public void addCard(Card pCard){
    add(pCard);
  }

  /**
   * Returns the number of Cards in the Hand.
   *
   * @return
   *   The number of Cards in the current Hand.
   */
  public int getNumberOfCardsInHand(){
    return size();
  }

}
