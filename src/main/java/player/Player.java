package player;

/**
 *  Class that represents a player.
 *
 *  @author David Stewart
 */
public class Player {

  //---------------------------------------------------------------------
  // ENUMS
  //---------------------------------------------------------------------

  /**
   * All of the types of player that can join game.
   */
  public enum Type {
    DEALER, PLAYER
  }

  //---------------------------------------------------------------------
  // CONSTRUCTORS
  //---------------------------------------------------------------------

  /**
   * Default Constructor.
   */
  public Player() {
  }

  /**
   * Constructor that sets the username of this player. It will
   * also set the player type to Type.PLAYER.
   *
   * @param pUserName
   *   The username to set for the player.
   */
  public Player(String pUserName) {
    setUserName(pUserName);
  }

  /**
   * Constructor that sets the username and type of this player.
   *
   * @param pUserName
   *   The username to set for the player.
   * @param pPlayerType
   *   The type of player.
   */
  public Player(String pUserName, Type pPlayerType) {
    setUserName(pUserName);
    setPlayerType(pPlayerType);

  }

  //---------------------------------------------------------------------
  // PROPERTIES
  //---------------------------------------------------------------------

  //------------------------
  // property: userName
  //------------------------
  private String mUserName = "";

  /**
   * @param pUserName
   *   The player's username.
   */
  public void setUserName(String pUserName) {
    mUserName = pUserName;
  }

  /**
   * @return
   *   The player's username.
   */
  public String getUserName() {
    return mUserName;
  }

  //------------------------
  // property: playerType
  //------------------------
  private Type mPlayerType = null;

  /**
   * @param pPlayerType
   *   The type of user playing.
   */
  public void setPlayerType(Type pPlayerType) {
    mPlayerType = pPlayerType;
  }

  /**
   * @return
   *   The type of user playing.
   */
  public Type getPlayerType() {
    return mPlayerType;
  }

}
