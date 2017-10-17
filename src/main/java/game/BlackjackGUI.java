package game;

import card.Card;
import player.BlackjackDealer;
import player.BlackjackPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

/**
 * Class that adds the game to a proper GUI.
 *
 *  @author David Stewart
 */
public class BlackjackGUI {
  
  //-------------------------------------------------------------
  // CONSTANTS
  //-------------------------------------------------------------

  /** The name that will be the title of the game. */
  public static final String GAME_TITLE = "Blackjack";

  /** The format that the player's bank money will appear in. */
  public static NumberFormat FORMATTER = new DecimalFormat("#0.00");

  //-------------------------------------------------
  // Game Panels
  //-------------------------------------------------
  
  /** Panel at top of GUI that will hold the action buttons. */
  private JPanel topPanel = new JPanel();
  
  /** Panel that will hold the dealer's cards. */
  private JPanel dealerCardPanel = new JPanel();
  
  /** Panels that will hold the player's cards. */
  private JPanel playerCardPanel = new JPanel();
  private List<JPanel> playerCardPanels = new ArrayList<>(5);
  
  /** Panel that will hold the player's available betting pool. */
  private JPanel playerBankPanel = new JPanel();
  private List<JPanel> playerBankPanels = new ArrayList<>(5);
  
  //-------------------------------------------------
  // Game Labels
  //-------------------------------------------------
  
  /** Label that indicates that the player is the dealer. */
  private JLabel dealerLabel = new JLabel();
  
  /** Label that indicates that the player is the player. */
  //private JLabel playerLabel = new JLabel();
  private List<JLabel> playerLabels = new ArrayList(5);
  
  /** Label that displays the player total. */
  private List<JLabel> playerBankLabels = new ArrayList(5);

  
  //-------------------------------------------------
  // Game Buttons
  //-------------------------------------------------
  
  /** Button that performs a hit for the player. */
  private JButton hitButton = new JButton();
  
  /** Button that deals the cards at the start of the game. */
  private JButton dealButton = new JButton();
  
  /** Button that performs a stick for the player. */
  private JButton stickButton = new JButton();

  /** Button that performs a double down for the player. */
  private JButton doubleDownButton = new JButton();

  /** Button that resets the game. */
  private JButton playAgainButton = new JButton();
  
  //-------------------------------------------------
  // Action commands.
  //-------------------------------------------------
  
  private static final String DEAL_ACTION_COMMAND = "DEAL";
  private static final String STICK_ACTION_COMMAND = "STICK";
  private static final String DOUBLE_DOWN_ACTION_COMMAND = "DOUBLE_DOWN";
  private static final String HIT_ACTION_COMMAND = "HIT";
  private static final String PLAY_AGAIN_ACTION_COMMAND = "PLAY_AGAIN";

  //-------------------------------------------------
  // The labels to represent the cards for the game.
  //-------------------------------------------------
  
  /** This will represent the dealer's first card that is not shown to the user until the end of the game. */
  private JLabel dealerInitialCard1 = null;

  // TODO: Remove this, just for testing...
  JLabel shoeSizeDisplay = new JLabel();


  /** The colour that will be used for the table. */
  private Color TABLE_COLOUR = new Color(0, 122, 0);
  
  /** The colour that will indicate the inactive players. */
  private Color INACTIVE_PLAYER_COLOUR = new Color(0, 155, 0);
  
  /** The colour that will indicate the active players. */
  private Color ACTIVE_PLAYER_COLOUR = new Color(0, 200, 0);

  //-------------------------------------------------------------
  // MEMBERS
  //-------------------------------------------------------------
  
  /** The game instance. */
  private Blackjack game = new Blackjack();
  
  /** Flag that indicates the the current game is the first one. */
  private boolean mFirstGame = true;
  
  //-------------------------------------------------------------
  // METHODS
  //-------------------------------------------------------------
  
  /**
   * Add all of the game components to the frame.
   *
   * @param pPane
   *   The container to add the components to.
   */
  public void addComponentsToPane(Container pPane) {
    pPane.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.HORIZONTAL;

    createGameButtons();

    // TOP PANEL.
    topPanel.setBackground(TABLE_COLOUR);
    topPanel.setLayout(new FlowLayout());
    topPanel.add(dealButton);
    topPanel.add(hitButton);
    topPanel.add(stickButton);
    topPanel.add(doubleDownButton);
    topPanel.add(playAgainButton);
    topPanel.add(shoeSizeDisplay);
    topPanel.setBackground(TABLE_COLOUR);
    topPanel.setLayout(new FlowLayout());
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 0;
    constraints.gridy = 0;
    pPane.add(topPanel, constraints);
  
    // DEALER PANEL
    dealerCardPanel.add(dealerLabel);
    dealerCardPanel.setBackground(TABLE_COLOUR);
    dealerCardPanel.setLayout(new FlowLayout());
    constraints.insets = new Insets(10,0,0,0);  //top padding
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weightx = 0.5;
    constraints.gridx = 0;
    constraints.gridy = 1;
    pPane.add(dealerCardPanel, constraints);
    
    // PLAYER CARD PANELS
    playerCardPanel.setBackground(TABLE_COLOUR);
    playerCardPanel.setBackground(TABLE_COLOUR);
    playerCardPanel.setLayout(new FlowLayout());
    constraints.insets = new Insets(80, 0, 0, 0);  //top padding
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weightx = 0.5;
    constraints.gridx = 0;
    constraints.gridy = 2;
    pPane.add(playerCardPanel, constraints);
    
    // BANK PANEL
    playerBankPanel.setBackground(TABLE_COLOUR);
    playerBankPanel.setLayout(new FlowLayout());
    constraints.insets = new Insets(20,0,0,0);  //top padding
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weightx = 0.5;
    constraints.gridx = 0;
    constraints.gridy = 6;
    pPane.add(playerBankPanel, constraints);
  
    for (int i = 0; i < game.getPlayers().size(); i++) {
      JPanel playerXBankPanel = new JPanel();
      JLabel playerXBankLabel = new JLabel();
  
      playerXBankPanel.setBackground(new Color(0, 152, 0));
      playerXBankPanel.setLayout(new FlowLayout());
      playerXBankPanel.add(playerXBankLabel);
  
      playerBankLabels.add(playerXBankLabel);
      playerBankPanels.add(playerXBankPanel);
      playerXBankPanel.add(playerXBankLabel);
  
      playerXBankPanel.setBackground(INACTIVE_PLAYER_COLOUR);
      
      playerBankPanel.add(playerXBankPanel);
    }
    
  }

  /**
   * Deal out the dealer and player cards.
   */
  public void deal() {
    game.getDealer().dealNewGame(game.getPlayers(), game.getShoe());

    // TODO: Remove this, just for testing...
    shoeSizeDisplay.setText("    Cards in shoe: " + Integer.toString(game.getShoe().size()));
  
    for (int i = 0; i < game.getPlayers().size(); i++) {
      placeBet(game.getPlayers().get(i));
    }
    
    // Deal all of the players initial cards.
    for (int i = 0; i < game.getPlayers().size(); i++) {
  
      if (mFirstGame) {
        JPanel playerXCardPanel = new JPanel();
        JLabel playerXLabel = new JLabel();
  
        playerXCardPanel.setLayout(new FlowLayout());
        playerXCardPanel.add(playerXLabel);
  
        playerLabels.add(playerXLabel);
        playerCardPanels.add(playerXCardPanel);
        playerCardPanel.add(playerXCardPanel);
  
        if (i == 0) {
          playerXCardPanel.setBackground(ACTIVE_PLAYER_COLOUR);
        }
        else {
          playerXCardPanel.setBackground(INACTIVE_PLAYER_COLOUR);
        }
      }
      else {
        if (i == 0) {
          playerCardPanels.get(i).setBackground(ACTIVE_PLAYER_COLOUR);
        }
        else {
          playerCardPanels.get(i).setBackground(INACTIVE_PLAYER_COLOUR);
        }
      }
      
      dealInitialCards(game.getPlayers().get(i), i);
      playerLabels.get(i).setText(Integer.toString(game.getPlayers().get(i).getHands().get(0).getCardTotal()));
    }
  
    mFirstGame = false;
    
    for (int i = 0; i < game.getPlayers().size(); i++) {
      JLabel playerXBankLabel = new JLabel();
      playerBankLabels.add(playerXBankLabel);
      playerBankPanels.get(i).add(playerXBankLabel);
    }
  
    updateBankLabels();
    
    dealerLabel.setText("Dealer: ");
    dealerCardPanel.add(dealerLabel);
  
    // Deal the dealer's initial cards.
    dealInitialCards(game.getDealer(), 0);
    dealerLabel.setText("Dealer:  " + game.getDealer().getHands().get(0).getCardTotal());
    
    
    hitButton.setEnabled(true);
    stickButton.setEnabled(true);
    dealButton.setEnabled(false);
    doubleDownButton.setEnabled(true);

    // TODO: If dealer has blackjack here, he wins.
    // Player has blackjack.
    if (game.getPlayers().get(game.getActivePlayer()).getHands().get(0).getCardTotal() == 21) {
      if (game.getActivePlayer() == game.getPlayers().size() - 1) {
        dealersTurn();
      }
      else {
        playerCardPanels.get(game.getActivePlayer()).setBackground(INACTIVE_PLAYER_COLOUR);
        game.setActivePlayer(game.getActivePlayer() + 1);
        playerCardPanels.get(game.getActivePlayer()).setBackground(ACTIVE_PLAYER_COLOUR);
      }
    }
  }
  
  /**
   * This method retrieves the initial cards in the dealer's/player's
   * hand and sets the corresponding image in order to be displayed
   * in the GUI.
   *
   * @param pPlayer
   *   The player whose cards will be retrieved and displayed.
   */
  protected void dealInitialCards(BlackjackPlayer pPlayer, int pPlayerIndex) {
    
    if (pPlayer instanceof BlackjackDealer) {
      // Deal dealer's initial cards.
      dealerCardPanel.add(new JLabel(new ImageIcon(this.getClass().getResource("/card_images/back.jpg"))));
      
      for (int i = 0; i < 2; i++) {
        if (i == 0) {
          dealerInitialCard1 = new JLabel(pPlayer.getHands().get(0).get(i).getCardImage());
        }
        else {
          dealerCardPanel.add(new JLabel(pPlayer.getHands().get(0).get(i).getCardImage()));
        }
      }
    }
    else {
      // Deal player's initial cards.
      for (int i = 0; i < 2; i++) {
        playerCardPanels.get(pPlayerIndex).add(new JLabel(pPlayer.getHands().get(0).get(i).getCardImage()));
      }
    }
    
    // TODO: Remove this, just for testing...
    shoeSizeDisplay.setText("    Cards in shoe: " + Integer.toString(game.getShoe().size()));
  }
  
  /**
   * Deals the player another card.
   */
  public void hit() {
    BlackjackPlayer activePlayer = game.getPlayers().get(game.getActivePlayer());
    
    Card hitCard = game.getDealer().dealCard(activePlayer, game.getShoe());
    playerLabels.get(game.getActivePlayer()).setText(Integer.toString(activePlayer.getHands().get(0).getCardTotal()));
    
    // TODO: Remove this, just for testing...
    shoeSizeDisplay.setText("    Cards in shoe: " + Integer.toString(game.getShoe().size()));
    
    JLabel hitCardImage = new JLabel(hitCard.getCardImage());
    playerCardPanels.get(game.getActivePlayer()).add(hitCardImage, 1);
    
    if (activePlayer.getHands().get(0).isBust() || activePlayer.getHands().get(0).getCardTotal() == 21) {
      
      if (game.getActivePlayer() == game.getPlayers().size() - 1) {
        hitButton.setEnabled(false);
        dealButton.setEnabled(false);
        stickButton.setEnabled(false);
        playAgainButton.setEnabled(true);
        playerCardPanels.get(game.getActivePlayer()).setBackground(INACTIVE_PLAYER_COLOUR);
        dealersTurn();
      }
      else {
        playerCardPanels.get(game.getActivePlayer()).setBackground(INACTIVE_PLAYER_COLOUR);
        game.setActivePlayer(game.getActivePlayer() + 1);
        playerCardPanels.get(game.getActivePlayer()).setBackground(ACTIVE_PLAYER_COLOUR);
      }
      
    }
  }

  /**
   * Sets the player's hand to stick and switches to the dealer.
   */
  public void stick() {
    game.getPlayers().get(game.getActivePlayer()).getHands().get(0).setSticking(true);
  
    if (game.getActivePlayer() == game.getPlayers().size() - 1) {
      playerCardPanels.get(game.getActivePlayer()).setBackground(INACTIVE_PLAYER_COLOUR);
      dealersTurn();
    }
    else {
      playerCardPanels.get(game.getActivePlayer()).setBackground(INACTIVE_PLAYER_COLOUR);
      game.setActivePlayer(game.getActivePlayer() + 1);
      playerCardPanels.get(game.getActivePlayer()).setBackground(ACTIVE_PLAYER_COLOUR);
    }
  }

  /**
   * Double down on the player's bet.
   */
  public void doubleDown() {
    int activePlayerIndex = game.getActivePlayer();
    BlackjackPlayer activePlayer = game.getPlayers().get(activePlayerIndex);
    
    if (activePlayer.getPlayerBank() < activePlayer.getHands().get(0).getBet()) {
      JOptionPane.showMessageDialog(null, "You don't have sufficient funds to double down.");
      return;
    }

    activePlayer.doubleDown(activePlayer.getHands().get(0));
    updateBankLabels();
    hit();
    stick();

    if (game.getActivePlayer() != game.getPlayers().size() - 1){
      game.setActivePlayer(activePlayerIndex + 1);
    }
  }

  /**
   * Captures user input for placing a bet.
   */
  public void placeBet(BlackjackPlayer pPlayer) {
    boolean isValidChoice = false;
  
    while (!isValidChoice) {
      String userInput = JOptionPane.showInputDialog(pPlayer.getUserName() + ", how much do you want to bet? Press \"M\" for minimum bet.");
  
      if (userInput == null) {
        System.exit(1);
      }
  
      if (userInput.equalsIgnoreCase("M") || userInput.isEmpty()) {
        pPlayer.getHands().get(0).setBet(Blackjack.sTableMinimumBet);
        pPlayer.setPlayerBank(pPlayer.getPlayerBank() - Blackjack.sTableMinimumBet);
        updateBankLabels();
        isValidChoice = true;
      }
      else {
        double bet = 0.0d;
    
        try {
          bet = Double.valueOf(userInput);
      
          if (bet > pPlayer.getPlayerBank()) {
            JOptionPane.showMessageDialog(null,
              "You have entered an amount greater than you have in the bank.  " + "Y'all ain't no High Rolla!!");
            continue;
          }
        }
        catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "Enter \"M\" or a numerical value for minimum bet.");
          continue;
        }
    
        pPlayer.getHands().get(0).setBet(Double.parseDouble(userInput));
        pPlayer.setPlayerBank(game.getPlayers().get(0).getPlayerBank() - bet);
        updateBankLabels();
        isValidChoice = true;
      }
    }
  }

  /**
   * Reset the GUI for a new game. This doesn't reset the bank.
   */
  public void playAgain() {
    dealerLabel.setText("Dealer: ");
    game.getDealer().getHands().clear();
  
    playerCardPanel.removeAll();
    
    for (int i = 0; i < game.getPlayers().size(); i++) {
      playerLabels.get(i).setText(" ");
      game.getPlayers().get(i).getHands().clear();
      playerCardPanels.get(i).removeAll();
      playerCardPanels.get(i).add(playerLabels.get(i));
      playerCardPanel.add(playerCardPanels.get(i));
    }
    
    dealerCardPanel.removeAll();
    game.setActivePlayer(0);
    
    hitButton.setEnabled(false);
    stickButton.setEnabled(false);
    playAgainButton.setEnabled(false);
    doubleDownButton.setEnabled(false);
    dealButton.setEnabled(true);

    // TODO: Maybe remove player if they have insufficient funds?
    //if (game.getPlayers().get(0).getPlayerBank() < Blackjack.sTableMinimumBet) {
    //  JOptionPane.showMessageDialog(null, "You don't have sufficient funds in your bank. Game finished!");
    //  System.exit(1);
    //}
    //else {
      deal();
    //}
  }

  /**
   * Switches to the dealer.
   */
  public void dealersTurn() {
    dealerCardPanel.removeAll();
    dealerCardPanel.add(dealerLabel);
    dealerLabel.setText(dealerLabel.getText() + ": " + game.getDealer().getHands().get(0).getCardTotal());

    // Iterate through cards and re-display.
    Card dealerHitCard = null;
    Iterator<Card> scan = (game.getDealer().getHands().get(0).iterator());
  
    JLabel dealerHitCardImage = null;
    
    while (scan.hasNext()) {
      dealerHitCard = scan.next();
      dealerHitCardImage = new JLabel(dealerHitCard.getCardImage());
      dealerCardPanel.add(dealerHitCardImage);
    }

    hitButton.setEnabled(false);
    stickButton.setEnabled(false);
    playAgainButton.setEnabled(true);
    dealerCardPanel.repaint();

    boolean allPlayersBust = true;
    boolean allPlayersHave21 = true;

    for (int i = 0; i < game.getPlayers().size(); i++) {
      if (!game.getPlayers().get(i).getHands().get(0).isBust()) {
        allPlayersBust = false;
      }

      if (game.getPlayers().get(i).getHands().get(0).getCardTotal() != 21) {
        allPlayersHave21 = false;
      }
    }

    // The dealer doesn't need to take any more cards if all players are bust or have 21.
    boolean isGameFinished = allPlayersBust || allPlayersHave21;

    while(!isGameFinished) {
      if (!game.getDealer().getHands().get(0).isSticking() && !game.getDealer().getHands().get(0).isBust()) {
        if (game.getDealer().getHands().get(0).getCardTotal() < 17) {
          Card card = game.getDealer().dealCard(game.getDealer(), game.getShoe());

          // TODO: Remove this, just for testing...
          shoeSizeDisplay.setText("    Cards in shoe: " + Integer.toString(game.getShoe().size()));

          dealerHitCardImage = new JLabel(card.getCardImage());
          dealerCardPanel.add(dealerHitCardImage);
          playerCardPanel.repaint();
        }
        else {
          game.getDealer().getHands().get(0).setSticking(true);
        }
      }

      if (game.getDealer().getHands().get(0).isSticking() || game.getDealer().getHands().get(0).isBust()) {
        isGameFinished = true;
      }
    }

    dealerLabel.setText("Dealer: " + game.getDealer().getHands().get(0).getCardTotal());
    
    for (int i = 0; i < game.getPlayers().size(); i++) {
      playerLabels.get(i).setText(Integer.toString(game.getPlayers().get(i).getHands().get(0).getCardTotal()));
    }
    
    determineGameOutcome();
  }

  /**
   * This method contains the logic for deciding who won the game and updates
   * the player's bank accordingly.
   */
  public void determineGameOutcome() {
    hitButton.setEnabled(false);
    dealButton.setEnabled(false);
    stickButton.setEnabled(false);
    playAgainButton.setEnabled(true);
    doubleDownButton.setEnabled(false);

    StringBuilder sb = new StringBuilder();
    List<String> outcome = game.determineOutcome();
    
    for (int i = 0; i < outcome.size(); i++) {
      sb.append(outcome.get(i));
      sb.append("\n");
    }

    JOptionPane.showMessageDialog(null, sb.toString());
    updateBankLabels();
  }

  /**
   * Convenience method for updating the player's bank status.
   */
  private void updateBankLabels() {
    for (int i = 0; i < game.getPlayers().size(); i++) {
      playerBankLabels.get(i).setText(game.getPlayers().get(i).getUserName()
        + " Bank:  " + FORMATTER.format(game.getPlayers().get(i).getPlayerBank()));
    }
  }
  
  /**
   * Create and initialise all of the game's buttons.
   */
  protected void createGameButtons() {
    dealButton.setText("Deal");
    dealButton.addActionListener(new GameButtonListener());
    dealButton.setActionCommand(DEAL_ACTION_COMMAND);
    
    hitButton.setText("Hit");
    hitButton.addActionListener(new GameButtonListener());
    hitButton.setActionCommand(HIT_ACTION_COMMAND);
    hitButton.setEnabled(false);
    
    stickButton.setText("Stick");
    stickButton.addActionListener(new GameButtonListener());
    stickButton.setActionCommand(STICK_ACTION_COMMAND);
    stickButton.setEnabled(false);
    
    doubleDownButton.setText("Double Down");
    doubleDownButton.addActionListener(new GameButtonListener());
    doubleDownButton.setActionCommand(DOUBLE_DOWN_ACTION_COMMAND);
    doubleDownButton.setEnabled(false);
    
    playAgainButton.setText("Play Again");
    playAgainButton.addActionListener(new GameButtonListener());
    playAgainButton.setActionCommand(PLAY_AGAIN_ACTION_COMMAND);
    playAgainButton.setEnabled(false);
  }
  
  /**
   * Action event listener class for all game buttons such as hit, deal, stick etc.
   */
  class GameButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent pEvent) {
      
      switch (pEvent.getActionCommand()) {
        case DEAL_ACTION_COMMAND:
          deal();
          break;
        case HIT_ACTION_COMMAND:
          hit();
          break;
        case STICK_ACTION_COMMAND:
          stick();
          break;
        case DOUBLE_DOWN_ACTION_COMMAND:
          doubleDown();
          break;
        case PLAY_AGAIN_ACTION_COMMAND:
          playAgain();
      }
    }
  }
  
  /**
   * Create the GUI and show it.  For thread safety, this method should be invoked from the
   * event-dispatching thread.
   */
  private void createAndShowGUI() {
    JFrame frame = new JFrame(GAME_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(TABLE_COLOUR);
    
    int percentIncrease = game.getPlayers().size() * 20;
    
    frame.setPreferredSize(new Dimension((800 * percentIncrease / 100) + 800, 450));

    addComponentsToPane(frame.getContentPane());
    
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
  }
  
  /**
   * Main method.
   *
   * @param args
   */
  public static void main(String[] args) {
    BlackjackGUI blackjack = new BlackjackGUI();
    
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        blackjack.createAndShowGUI();
      }
    });
  }
}
