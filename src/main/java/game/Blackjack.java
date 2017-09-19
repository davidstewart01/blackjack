package game;

import card.Shoe;
import player.BlackjackDealer;
import player.BlackjackPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a game of blackjack.
 *
 * @author David Stewart
 */
public class Blackjack {

  //---------------------------------------------------------------------
  // CONSTANTS
  //---------------------------------------------------------------------

  // Game outcome messages.

  /** The message associated withe a draw between the player and dealer. */
  public static final String GAME_OUTCOME_PUSH = "Push";

  /** The message associated when the dealer has blackjack. */
  public static final String GAME_OUTCOME_DEALER_BLACKJACK = "Dealer has Blackjack";

  /** The message associated when the player has blackjack. */
  public static final String GAME_OUTCOME_PLAYER_BLACKJACK = "Player has Blackjack";

  /** The message associated when the dealer is bust but the player isn't. */
  public static final String GAME_OUTCOME_DEALER_BUST = "Dealer Bust, Player wins";

  /** The message associated when the player is bust but the dealer isn't. */
  public static final String GAME_OUTCOME_PLAYER_BUST = "Player Bust, Dealer wins";

  /** The message associated with a draw between the player and dealer. */
  public static final String GAME_OUTCOME_PLAYER_AND_DEALER_BUST = "Player and Dealer are Bust";

  /** The message associated with the dealer winning. */
  public static final String GAME_OUTCOME_DEALER_WINS = "Dealer Wins";

  /** The message associated with the dealer winning. */
  public static final String GAME_OUTCOME_PLAYER_WINS = "Player Wins";

  // Game defaults.

  /** The default table minimum bet */
  public static final double TABLE_MINIMUM = 10;

  //---------------------------------------------------------------------
  // CONSTRUCTORS
  //---------------------------------------------------------------------

  /**
   * Constructor that initialises dealer and players for game.
   */
  public Blackjack() {
    // TODO: Add players in a loop. number of from properties file?
    getPlayers().add(new BlackjackPlayer());
    setPlayers(getPlayers());

    setShoe(new Shoe(1, true));
  }

  //---------------------------------------------------------------------
  // PROPERTIES
  //---------------------------------------------------------------------

  //--------------------------------------------------
  // property: players
  //--------------------------------------------------
  List<BlackjackPlayer> mPlayers = new ArrayList<>(5);

  /**
   * @param pPlayers
   *   The players in the game.
   */
  public void setPlayers(List<BlackjackPlayer> pPlayers) {
    mPlayers = pPlayers;
  }

  /**
   * @return
   *   The players in the game.
   */
  public List<BlackjackPlayer> getPlayers() {
    return mPlayers;
  }

  //--------------------------------------------------
  // property: dealer
  //--------------------------------------------------
  BlackjackDealer mDealer = new BlackjackDealer();

  /**
   * @param pDealer
   *   The dealer of the game.
   */
  public void setDealer(BlackjackDealer pDealer) {
    mDealer = pDealer;
  }

  /**
   * @return
   *   The dealer of the game.
   */
  public BlackjackDealer getDealer() {
    return mDealer;
  }

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
  //---------------------------------------------------------------------
  // METHODS
  //---------------------------------------------------------------------

  /**
   * This method contains the logic for deciding who won the game and updates
   * the player's bank accordingly.
   *
   * @return
   *   The outcome of the game.
   */
  public String determineOutcome() {

    if ((getDealer().getHands().get(0).getNumberOfCardsInHand() == 2 && getDealer().getCardTotal() == 21)
        && getPlayers().get(0).getHands().get(0).getNumberOfCardsInHand() == 2 && getPlayers().get(0).getCardTotal() == 21) {

      getPlayers().get(0).setPlayerBank(
        getPlayers().get(0).getPlayerBank() + (getPlayers().get(0).getHands().get(0).getBet()));

      return GAME_OUTCOME_PUSH;
    }
    else if ((getDealer().getHands().get(0).getNumberOfCardsInHand() == 2 && getDealer().getCardTotal() == 21)
      && ((getPlayers().get(0).getHands().get(0).getNumberOfCardsInHand() == 2 && getPlayers().get(0).getCardTotal() != 21)
        || getPlayers().get(0).getHands().get(0).getNumberOfCardsInHand() > 2)) {

      return GAME_OUTCOME_DEALER_BLACKJACK;
    }
    else if ((getPlayers().get(0).getHands().get(0).getNumberOfCardsInHand() == 2 && getPlayers().get(0).getCardTotal() == 21)
      && ((getDealer().getHands().get(0).getNumberOfCardsInHand() == 2 && getDealer().getCardTotal() != 21)
        || getDealer().getHands().get(0).getNumberOfCardsInHand() > 2)) {

      // Update bank with blackjack winning ratio at 3:2.
      getPlayers().get(0).setPlayerBank(
        getPlayers().get(0).getPlayerBank() + (getPlayers().get(0).getHands().get(0).getBet() * 2.5));

      return GAME_OUTCOME_PLAYER_BLACKJACK;
    }
    else if (getDealer().getCardTotal() > 21 && getPlayers().get(0).getCardTotal() <= 21) {

      // Add bet plus win back to the player bank at 1:1
      getPlayers().get(0).setPlayerBank(
        getPlayers().get(0).getPlayerBank() + (getPlayers().get(0).getHands().get(0).getBet() * 2));

      return GAME_OUTCOME_DEALER_BUST;
    }
    else if (getPlayers().get(0).getCardTotal() > 21 && getDealer().getCardTotal() <= 21) {
      return GAME_OUTCOME_PLAYER_BUST;
    }

    else if (getPlayers().get(0).getCardTotal() > 21 && getDealer().getCardTotal() > 21) {
      return GAME_OUTCOME_PLAYER_AND_DEALER_BUST;
    }
    else {
      if (getDealer().getCardTotal() > getPlayers().get(0).getCardTotal()) {
        return GAME_OUTCOME_DEALER_WINS;
      }
      else if (getPlayers().get(0).getCardTotal() > getDealer().getCardTotal()) {

        // Add bet plus win back to the player bank at 1:1
        getPlayers().get(0).setPlayerBank(
          getPlayers().get(0).getPlayerBank() + (getPlayers().get(0).getHands().get(0).getBet() * 2));

        return GAME_OUTCOME_PLAYER_WINS;
      }
      else {
        getPlayers().get(0).setPlayerBank(
          getPlayers().get(0).getPlayerBank() + (getPlayers().get(0).getHands().get(0).getBet()));

        return GAME_OUTCOME_PLAYER_WINS;
      }
    }
  }

}
