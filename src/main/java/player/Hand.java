package player;

import card.Card;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents the hand of cards that a player can bet on.
 *
 * @author Paul Anderson
 */
public class Hand extends LinkedList<Card> {

  //----------------------------------------
  // property: Bet
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

  //----------------------------------------
  // Variables
  //----------------------------------------
  private boolean isSplittable = false;


  /**
   * Double down the wagered bet on the Hand in play.  This can only be called on the first play on the hand,
   * when it has only 2 cards.
   */
  public void doubleDown(){
    if(!hasDoubledDown() && getNumberOfCardsInHand() == 2){
      setHasDoubledDown(true);
      setBet(getBet() * 2);
    }
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
