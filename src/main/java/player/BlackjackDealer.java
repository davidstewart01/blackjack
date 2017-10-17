package player;

import card.Card;
import card.Shoe;
import game.Blackjack;

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
   * Creates a new shoe with a randomly placed reshuffle marker.
   *
   * @return
   *   A newly cobbled shoe.
   */
  public Shoe cobbleShoe() {
    Shoe shoe = new Shoe(
      Integer.parseInt(Blackjack.sProperties.getProperty(Blackjack.NUMBER_OF_DECKS_IN_SHOE_RESOURCE)),
        Boolean.parseBoolean(Blackjack.sProperties.getProperty(Blackjack.SHUFFLE_SHOE_RESOURCE)));

    shoe.insertReshuffleMarker(
      Integer.parseInt(Blackjack.sProperties.getProperty(Blackjack.RESHUFFLE_MARKER_LOWER_BOUND_PERCENTILE)),
        Integer.parseInt(Blackjack.sProperties.getProperty(Blackjack.RESHUFFLE_MARKER_UPPER_BOUND_PERCENTILE)));

    return shoe;
  }

  /**
   * Deal all cards to players and dealer for a new game of blackjack.
   */
  public void dealNewGame(List<BlackjackPlayer> pPlayers, Shoe pShoe) {
    int numberOfCardsInFullShoe =
      Integer.parseInt(Blackjack.sProperties.getProperty(Blackjack.NUMBER_OF_DECKS_IN_SHOE_RESOURCE)) * 52;

    // Ensure the shoe gets recreated when the marker position has been reached.
    if (pShoe.size() <= (numberOfCardsInFullShoe - pShoe.getReshuffleMarkerLocation())) {
      pShoe.clear();
      pShoe.addAll(cobbleShoe());
    }
  
    // Deal dealer cards.
    this.getHands().add(new Hand());
    this.getHands().get(0).add(pShoe.removeLast());
    this.getHands().get(0).add(pShoe.removeLast());
    
    // Deal player cards.
    for (int i = 0; i < pPlayers.size(); i++) {
      pPlayers.get(i).getHands().add(new Hand());
      pPlayers.get(i).getHands().get(0).add(pShoe.removeLast());
      pPlayers.get(i).getHands().get(0).add(pShoe.removeLast());
    }
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
    Card card = pShoe.removeLast();
    pPlayer.hit(card);

    return card;
  }
}
