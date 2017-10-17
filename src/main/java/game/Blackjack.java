package game;

import card.Shoe;
import player.BlackjackDealer;
import player.BlackjackPlayer;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class that represents a game of blackjack.
 *
 * @author David Stewart
 */
public class Blackjack {

  //---------------------------------------------------------------------
  // CONSTANTS
  //---------------------------------------------------------------------

  public static final String[] PLAYER_NAMES = {"Dave", "Paul", "John", "Jay", "Suzy"};
  
  //-------------------------------------
  // Game outcome messages.
  //-------------------------------------

  /** The message associated withe a draw between the player and dealer. */
  public static final String GAME_OUTCOME_PUSH = "Push";

  /** The message associated when the dealer has blackjack. */
  public static final String GAME_OUTCOME_DEALER_BLACKJACK = "Dealer has Blackjack";

  /**
   * The message associated when the player has blackjack. The percent symbol will be
   * replaced with player's name.
   **/
  public static final String GAME_OUTCOME_PLAYER_BLACKJACK = "% has Blackjack";

  /**
   * The message associated when the dealer is bust but the player isn't. The percent symbol
   * will be replaced with player's name.
   **/
  public static final String GAME_OUTCOME_DEALER_BUST = "Dealer Bust, % wins";

  /**
   * The message associated when the player is bust but the dealer isn't. The percent symbol
   * will be replaced with player's name.
   **/
  public static final String GAME_OUTCOME_PLAYER_BUST = "% Bust, Dealer wins";

  /**
   * The message associated with a draw between the player and dealer.  The percent symbol will
   * be replaced with player's name.
   **/
  public static final String GAME_OUTCOME_PLAYER_AND_DEALER_BUST = "% and Dealer are Bust";

  /** The message associated with the dealer winning. */
  public static final String GAME_OUTCOME_DEALER_WINS = "Dealer Wins";

  /** The message associated with the dealer winning. The percent symbol will be replaced with player's name. */
  public static final String GAME_OUTCOME_PLAYER_WINS = "% Wins";

  //-------------------------------------
  // Properties file resources.
  //-------------------------------------

  /** The location of the game parameters properties file. */
  public static final String GAME_PARAMETERS_FILE_RESOURCE_LOCATION = "game_parameters.properties";

  /** The resource that defines the number of decks that should go into a shoe. */
  public static final String NUMBER_OF_DECKS_IN_SHOE_RESOURCE = "number_of_decks_in_shoe";

  /** The resource that defines whether the shoe should be shuffled or not. */
  public static final String SHUFFLE_SHOE_RESOURCE ="shuffle_shoe";

  /** The resource that defines the table minimum bet value. */
  public static final String TABLE_MINIMUM_BET_RESOURCE = "table_minimum_bet";

  /** The resource that defines the upper bound percentile of the reshuffle marker. */
  public static final String RESHUFFLE_MARKER_UPPER_BOUND_PERCENTILE = "reshuffle_marker_upper_bound_percentile";

  /** The resource that defines the upper bound percentile of the reshuffle marker. */
  public static final String RESHUFFLE_MARKER_LOWER_BOUND_PERCENTILE = "reshuffle_marker_lower_bound_percentile";



  //---------------------------------------------------------------------
  // STATIC MEMBERS
  //---------------------------------------------------------------------
  
  /** The location of the properties file that holds the game parameters. */
  public static InputStream sPropertiesFileLocation = null;
  
  /** The minimum bet allowed at the table. */
  public static double sTableMinimumBet = 0.0d;

  public static Properties sProperties = null;
  
  //---------------------------------------------------------------------
  // CONSTRUCTORS
  //---------------------------------------------------------------------

  /**
   * Constructor that initialises dealer and players for game.
   */
  public Blackjack() {
    sPropertiesFileLocation = getClass().getResourceAsStream(GAME_PARAMETERS_FILE_RESOURCE_LOCATION);
    sProperties = getProperties();
    
    int numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of players."));
    
    for (int i = 0; i < numberOfPlayers; i++) {
      getPlayers().add(new BlackjackPlayer(PLAYER_NAMES[i]));
    }
    
    setPlayers(getPlayers());
    setShoe(getDealer().cobbleShoe());

    // Initialise the table minimum bet.
    sTableMinimumBet = Double.parseDouble(sProperties.getProperty(TABLE_MINIMUM_BET_RESOURCE));
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
  // property: activePlayer
  //--------------------------------------------------//
  private int mActivePlayer = 0;
  
  /**
   * @param pActivePlayer
   *   The currently active player in the game.
   */
  public void setActivePlayer(int pActivePlayer) {
    mActivePlayer = pActivePlayer;
  }
  
  /**
   * @return
   *   The currently active player in the game.
   */
  public int getActivePlayer() {
    return mActivePlayer;
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
   * Load the game's properties file.
   *
   * @return The game's properties file.
   */
  public static Properties getProperties() {

    // Load properties file.
    Properties properties = new Properties();
    
    try {
      properties.load(sPropertiesFileLocation);
      return properties;
    }
    catch (IOException ioe) {
      System.out.println("Problem loading properties file!");
    }
    
    return null;
  }
  
  /**
   * This method contains the logic for deciding who won the game and updates
   * the player's bank accordingly.
   *
   * @return
   *   The outcome of the game.
   */
  public List<String> determineOutcome() {
  
    List<String> allPlayersOutcomes = new ArrayList<>(this.getPlayers().size());
  
    for (int i = 0; i < this.getPlayers().size(); i++) {
      BlackjackPlayer player = getPlayers().get(i);
      String playerOutcome = "";
    
      if ((getDealer().getHands().get(0).getNumberOfCardsInHand() == 2 && getDealer().getCardTotal() == 21) && player.getHands().get(0).getNumberOfCardsInHand() == 2 && player.getCardTotal() == 21) {
      
        player.setPlayerBank(player.getPlayerBank() + (player.getHands().get(0).getBet()));
  
        playerOutcome = GAME_OUTCOME_PUSH;
      }
      else if ((getDealer().getHands().get(0).getNumberOfCardsInHand() == 2 && getDealer().getCardTotal() == 21) && ((player.getHands().get(0).getNumberOfCardsInHand() == 2 && player.getCardTotal() != 21) || player.getHands().get(0).getNumberOfCardsInHand() > 2)) {
        playerOutcome = GAME_OUTCOME_DEALER_BLACKJACK;
      }
      else if ((player.getHands().get(0).getNumberOfCardsInHand() == 2 && player.getCardTotal() == 21) && ((getDealer().getHands().get(0).getNumberOfCardsInHand() == 2 && getDealer().getCardTotal() != 21) || getDealer().getHands().get(0).getNumberOfCardsInHand() > 2)) {
      
        // Update bank with blackjack winning ratio at 3:2.
        player.setPlayerBank(player.getPlayerBank() + (player.getHands().get(0).getBet() * 2.5));
  
        playerOutcome = GAME_OUTCOME_PLAYER_BLACKJACK.replace("%", player.getUserName());
      }
      else if (getDealer().getCardTotal() > 21 && player.getCardTotal() <= 21) {
      
        // Add bet plus win back to the player bank at 1:1
        player.setPlayerBank(player.getPlayerBank() + (player.getHands().get(0).getBet() * 2));
  
        playerOutcome = GAME_OUTCOME_DEALER_BUST.replace("%", player.getUserName());
      }
      else if (player.getCardTotal() > 21 && getDealer().getCardTotal() <= 21) {
        playerOutcome = GAME_OUTCOME_PLAYER_BUST.replace("%", player.getUserName());
      }
    
      else if (player.getCardTotal() > 21 && getDealer().getCardTotal() > 21) {
        playerOutcome = GAME_OUTCOME_PLAYER_AND_DEALER_BUST.replace("%", player.getUserName());
      }
      else {
        if (getDealer().getCardTotal() > player.getCardTotal()) {
          playerOutcome = GAME_OUTCOME_DEALER_WINS;
        }
        else if (player.getCardTotal() > getDealer().getCardTotal()) {
  
          // Add bet plus win back to the player bank at 1:1
          player.setPlayerBank(player.getPlayerBank() + (player.getHands().get(0).getBet() * 2));
  
          playerOutcome = GAME_OUTCOME_PLAYER_WINS.replace("%", player.getUserName());
        }
        else {
          player.setPlayerBank(player.getPlayerBank() + (player.getHands().get(0).getBet()));
  
          playerOutcome = GAME_OUTCOME_PUSH;
        }
      }
      
      allPlayersOutcomes.add(playerOutcome);
    }
    return allPlayersOutcomes;
  }

}
