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
  // CONSTANTS
  //---------------------------------------------------------------------

  public static final int HAND_ONE = 0;
  public static final int HAND_TWO = 1;
  public static final int HAND_THREE = 2;
  public static final int HAND_FOUR = 3;

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
    //initialise a blank hand for play
    //getHands().add(new Hand());
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
  // property: hands
  //--------------------------------------------
  private List<Hand> mHands = new ArrayList<>(4);

  /**
   * @param pHands
   *   The player's hand of cards.
   */
  public void setHands(List<Hand> pHands) {
    mHands = pHands;
  }

  /**
   * @return
   *   The player's hand of cards.
   */
  public List<Hand> getHands() {
    return mHands;
  }

  //--------------------------------------------
  // property: currentHandMarker
  //--------------------------------------------
  private int mCurrentHandMarker = HAND_ONE;

  public int getCurrentHandMarker() {
    return mCurrentHandMarker;
  }

  public void setCurrentHandMarker(int pCurrentHandMarker) {
    mCurrentHandMarker = pCurrentHandMarker;
  }

  //--------------------------------------------
  // property: playerBank
  //--------------------------------------------
  private double mPlayerBank = 1000;

  /**
   * @return
   *   how much the player has in their bank/chip stack
   */
  public double getPlayerBank() {
    return mPlayerBank;
  }

  /**
   * @param pPlayerBank
   *   Value to set the player bank/chip stack to
   */
  public void setPlayerBank(double pPlayerBank) {
    mPlayerBank = pPlayerBank;
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
    getHands().get(0).add(pCard);

    if (getHands().get(0).getCardTotal() > 21) {
      getHands().get(0).setBust(true);
    }
    //TODO - move stick logic to hand and set here when value = 21
    if (getHands().get(0).getCardTotal() == 21) {
      //getHands().get(0).setSticking(true);
    }
  }

  /**
   * Double down the wagered bet on the Hand in play.  This can only be called on the first play on the hand,
   * when it has only 2 cards.
   */
  public void doubleDown(Hand pHand){
    if (!pHand.hasDoubledDown() && pHand.getNumberOfCardsInHand() == 2) {
      pHand.setHasDoubledDown(true);
      setPlayerBank(getPlayerBank() - pHand.getBet());
      pHand.setBet(pHand.getBet() * 2);
    }
  }

}
