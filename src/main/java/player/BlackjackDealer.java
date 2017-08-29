package player;

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
    super.setPlayerType(Type.DEALER);
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

}
