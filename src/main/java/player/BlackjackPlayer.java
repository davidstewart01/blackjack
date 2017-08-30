package player;

import card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that represents a blackjack player.
 *
 * @author David Stewart
 */
public class BlackjackPlayer extends Player {

  //---------------------------------------------------------------------
  // CONSTRUCTORS
  //---------------------------------------------------------------------

  /**
   * Default constructor.
   *
   * Sets a random player name and a type of Type.PLAYER.
   */
  public BlackjackPlayer() {
    super();

    Random random = new Random();
    super.setUserName("Player" + random.nextInt());
    super.setPlayerType(Type.PLAYER);
  }

  /**
   * Constructor that sets the username of this player. It will
   * also set the player type to Type.PLAYER.
   *
   * @param pUserName
   *   The username to set for the player.
   */
  public BlackjackPlayer(String pUserName) {
    super.setUserName(pUserName);
    super.setPlayerType(Type.PLAYER);
  }

  /**
   * Constructor that sets the username and type of this player.
   *
   * @param pUserName
   *   The username to set for the player.
   * @param pPlayerType
   *   The type of player.
   */
  public BlackjackPlayer(String pUserName, Type pPlayerType) {
    super.setUserName(pUserName);
    super.setPlayerType(pPlayerType);
  }

  //---------------------------------------------------------------------
  // PROPERTIES
  //---------------------------------------------------------------------

  //--------------------------------------------
  // property: hand
  //--------------------------------------------
  private List<Card> mHand = new ArrayList<>();

  /**
   * @param pHand
   *   The player's hand of cards.
   */
  public void setHand(List<Card> pHand) {
    mHand = pHand;
  }

  /**
   * @return
   *   The player's hand of cards.
   */
  public List<Card> getHand() {
    return mHand;
  }

  //--------------------------------------------
  // property: hand
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

  //---------------------------------------------------------------------
  // METHODS
  //---------------------------------------------------------------------

  /**
   * Append the given card to the player 's hand. After
   * the card has been appended, this method will
   * determine whether or not the player is bust.
   *
   * @param pCard
   *   The card to be appended to the hand.
   */
  public void hit(Card pCard) {
    getHand().add(pCard);

    if (getCardTotal() > 21) {
      setBust(true);
    }
  }

  /**
   * @return
   *   The running total value of all player 's cards in hand.
   */
  public int getCardTotal() {

    // TODO: Make the ace logic more solid. E.g. when a player has more than one ace.

    int total = 0;
    int numAces = 0;

    for (Card card : getHand()) {
      if (card.getRank().getValue() == 1) {
        ++numAces;
        continue;
      }

      total += card.getRank().getValue();
    }

    if (numAces > 0) {
      for (int i = 0; i < numAces; i++) {
        if ((total + 11) <= 21) {
          total += 11;
        }
        else {
          total += 1;
        }
      }
    }

    return total;
  }

  /**
   * @return
   *   Retrieve the number of cards in the player's hand.
   */
  public int getNumberOfCardsInHand() {
    return getHand().size();
  }

}