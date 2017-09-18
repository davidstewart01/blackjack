package player;

import card.Card;
import card.Shoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a blackjack dealer.
 *
 * @author David Stewart
 */
public class BlackjackDealer extends BlackjackPlayer {

  //---------------------------------------------------------------------
  // CONSTRUCTORS
  //---------------------------------------------------------------------

  /**
   * Default constructor.
   *
   * Sets a default dealer name and type.
   */
  public BlackjackDealer() {
    super();
    super.setUserName("Dealer");
    super.setPlayerType(Type.DEALER);

    setShoe(new Shoe(1, true));
  }

  /**
   * Constructor that sets the username of this player. It will
   * also set the player type to Type.Dealer.
   *
   * @param pUserName
   *   The username to set for the player.
   */
  public BlackjackDealer(String pUserName) {
    super.setUserName(pUserName);
    super.setPlayerType(Type.DEALER);

    setShoe(new Shoe(1, true));
  }

  /**
   * Constructor that sets the username and type of this player.
   *
   * @param pUserName
   *   The username to set for the player.
   * @param pPlayerType
   *   The type of player.
   */
  public BlackjackDealer(String pUserName, Type pPlayerType) {
    super.setUserName(pUserName);
    super.setPlayerType(pPlayerType);

    setShoe(new Shoe(1, true));
  }

  //---------------------------------------------------------------------
  // PROPERTIES
  //---------------------------------------------------------------------

  //---------------------------------------------------
  // property: shoe
  //---------------------------------------------------
  Shoe mShoe = null;

  /**
   * @param pShoe
   *
   */
  public void setShoe(Shoe pShoe) {
    mShoe = pShoe;
  }

  /**
   * @return
   *
   */
  public Shoe getShoe() {
    return mShoe;
  }

  //---------------------------------------------------
  // property: players
  //---------------------------------------------------
  List<BlackjackPlayer> mPlayers = new ArrayList<>(5);

  /**
   * @param pPlayers
   *
   */
  public void setPlayers(List<BlackjackPlayer> pPlayers) {
    mPlayers = pPlayers;
  }

  /**
   * @return
   *
   */
  public List<BlackjackPlayer> getPlayers() {
    return mPlayers;
  }

  //---------------------------------------------------------------------
  // METHODS
  //---------------------------------------------------------------------

  /**
   * Deal all cards to players and dealer for a new game of blackjack.
   */
  public void dealNewGame() {
    // TODO: deal multiple players.
    getPlayers().get(0).getHands().add(new Hand());
    this.getHands().add(new Hand());

    getPlayers().get(0).getHands().get(0).add(getShoe().removeLast());
    this.getHands().get(0).add(getShoe().removeLast());
    getPlayers().get(0).getHands().get(0).add(getShoe().removeLast());
    this.getHands().get(0).add(getShoe().removeLast());
  }

  /**
   * Deal a card to the given player.
   *
   * @param pPlayer
   *   The player to deal the card to.
   *
   * @return
   *   The card that was dealt to the player.
   */
  public Card dealCard(BlackjackPlayer pPlayer) {
    Card card = getShoe().removeLast();
    pPlayer.hit(card);

    return card;
  }
}
