package player;

import card.Card;
import card.Shoe;

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
  }

  //---------------------------------------------------------------------
  // METHODS
  //---------------------------------------------------------------------

  /**
   * Deal all cards to players and dealer for a new game of blackjack.
   */
  public void dealNewGame(List<BlackjackPlayer> pPlayers, Shoe pShoe) {

    // TODO: When shoe reaches a certain size, re-deck it so we don't run out of cards. What happens in real blackjack?

    // TODO: deal multiple players.
    pPlayers.get(0).getHands().add(new Hand());
    this.getHands().add(new Hand());

    pPlayers.get(0).getHands().get(0).add(pShoe.removeLast());
    this.getHands().get(0).add(pShoe.removeLast());
    pPlayers.get(0).getHands().get(0).add(pShoe.removeLast());
    this.getHands().get(0).add(pShoe.removeLast());
  }

  /**
   * Deal a card to the given player.
   *
   * @param pPlayer
   *   The player to deal the card to
   *
   * @return
   *   The card that was dealt to the player..
   */
  public Card dealCard(BlackjackPlayer pPlayer, Shoe pShoe) {

    // TODO: When shoe reaches a certain size, re-deck it so we don't run out of cards. What happens in real blackjack?

    Card card = pShoe.removeLast();
    pPlayer.hit(card);

    return card;
  }
}
