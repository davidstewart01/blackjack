package game;

import card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Class that adds the game to a proper GUI.
 *
 *  @author David Stewart
 */
public class BlackjackGUI extends JPanel {

  /** Panel at top of GUI that will hold the action buttons. */
  private JPanel topPanel = new JPanel();
  
  /** Panel that will hold the dealer's cards. */
  private JPanel dealerCardPanel = new JPanel();
  
  /** Panel that will hold the player's cards. */
  private JPanel playerCardPanel = new JPanel();

  /** Panel that will hold the player's available betting pool. */
  private JPanel playerBankPanel = new JPanel();

  /** Label that displays the player total. */
  private JLabel playerBankLabel = new JLabel();
  
  /** Text box that displays the outcome of the game. */
  private JTextPane statusTextBox = new JTextPane();
  
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
  
  /** Label that indicates that the player is the dealer. */
  private JLabel dealerLabel = new JLabel();
  
  /** Label that indicates that the player is the player. */
  private JLabel playerLabel = new JLabel();

  // Action commands.
  private static final String DEAL_ACTION_COMMAND = "DEAL";
  private static final String STICK_ACTION_COMMAND = "STICK";
  private static final String DOUBLE_DOWN_ACTION_COMMAND = "DOUBLE_DOWN";
  private static final String HIT_ACTION_COMMAND = "HIT";
  private static final String PLAY_AGAIN_ACTION_COMMAND = "PLAY_AGAIN";

  // The labels to represent the cards for the game.

  // TODO: Make local variables where appropriate.
  private JLabel playercard1 = null;
  private JLabel playercard2 = null;
  private JLabel playercardhit = null;
  private JLabel dealercard0 = null;
  private JLabel dealercard2 = null;
  private JLabel dealercard1 = null;
  private JLabel dealercardhit = null;

  /** The game. */
  private Blackjack game = null;

  /**
   * Constructs the GUI.
   */
  public BlackjackGUI () {
    
    /** Instantiate the game. */
    game = new Blackjack();
    
    topPanel.setBackground(new Color(0, 122, 0));
    dealerCardPanel.setBackground(new Color(0, 122, 0));
    playerCardPanel.setBackground(new Color(0, 122, 0));

    topPanel.setLayout(new FlowLayout());

    statusTextBox.setText(" ");
    statusTextBox.setFont(new java.awt.Font("Helvetica Bold", 1, 20));
    statusTextBox.setVisible(false);

    dealButton.setText("  Deal");
    dealButton.addActionListener(new GameButtonListener());
    dealButton.setActionCommand(DEAL_ACTION_COMMAND);

    hitButton.setText("  Hit");
    hitButton.addActionListener(new GameButtonListener());
    hitButton.setActionCommand(HIT_ACTION_COMMAND);
    hitButton.setEnabled(false);

    stickButton.setText("  Stick");
    stickButton.addActionListener(new GameButtonListener());
    stickButton.setActionCommand(STICK_ACTION_COMMAND);
    stickButton.setEnabled(false);

    doubleDownButton.setText("  Double Down");
    doubleDownButton.addActionListener(new GameButtonListener());
    doubleDownButton.setActionCommand(DOUBLE_DOWN_ACTION_COMMAND);
    doubleDownButton.setEnabled(false);

    playAgainButton.setText("  Play Again");
    playAgainButton.addActionListener(new GameButtonListener());
    playAgainButton.setActionCommand(PLAY_AGAIN_ACTION_COMMAND);
    playAgainButton.setEnabled(false);

    dealerLabel.setText("  Dealer:  ");
    playerLabel.setText("  Player:  ");
    updateBankLabel();

    topPanel.add(statusTextBox);
    topPanel.add(dealButton);
    topPanel.add(hitButton);
    topPanel.add(stickButton);
    topPanel.add(doubleDownButton);
    topPanel.add(playAgainButton);

    playerCardPanel.add(playerLabel);
    dealerCardPanel.add(dealerLabel);

    setLayout(new BorderLayout());

    add(topPanel,BorderLayout.NORTH);
    add(dealerCardPanel,BorderLayout.CENTER);
    add(playerCardPanel,BorderLayout.SOUTH);
  }

  /**
   * Show the game.
   */
    public void display() {
      JFrame myFrame = new JFrame("BlackJack");
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.setContentPane(this);
      myFrame.setPreferredSize(new Dimension(800,650));
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //Display the window.
      myFrame.pack();
      myFrame.setVisible(true);
      myFrame.setLocationRelativeTo(null);

    }//end display

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
          playagain();
      }
    }
  }

  /**
   * Deal out the dealer and player cards.
   */
  public void deal() {
    game.deal();
    placeBet();

    dealerCardPanel.add(dealerLabel);
    playerCardPanel.add(playerLabel);
    playerBankPanel.add(playerBankLabel);

    dealercard0 = new JLabel(new ImageIcon(this.getClass().getResource("../card/card_images/back.jpg")));

    // To iterate set and get current dealer cards.
    Card dcard=null;

    Iterator<Card> dscan = (game.dealer.getHands().get(0)).iterator();
    int count = 0;

    while (dscan.hasNext()) {
      dcard = dscan.next();

      if(count==0)
        dealercard1 = new JLabel(dcard.getCardImage());
      else
        dealercard2 = new JLabel(dcard.getCardImage());

      count++;
    }

    //to iterate set and get current player cards
    Iterator<Card> pscan = (game.player1.getHands().get(0)).iterator();
    count = 0;

    while (pscan.hasNext()) {
      Card pcard = pscan.next();

      if (count==0) {
        playercard1 = new JLabel(pcard.getCardImage());
      }
      else {
        playercard2 = new JLabel(pcard.getCardImage());
      }

      count++;
    }

    dealerCardPanel.add(dealercard0);
    dealerCardPanel.add(dealercard2);

    playerCardPanel.add(playercard1);
    playerCardPanel.add(playercard2);

    dealerLabel.setText("  Dealer:  " + game.dealer.getCardTotal());
    playerLabel.setText("  Player:  " + game.player1.getCardTotal());

    hitButton.setEnabled(true);
    stickButton.setEnabled(true);
    dealButton.setEnabled(false);
    doubleDownButton.setEnabled(true);

    // TODO: If dealer has blackjack here, he wins.

    if (game.player1.getCardTotal() == 21) {
      hitButton.setEnabled(false);
      stickButton.setEnabled(false);
      dealButton.setEnabled(false);
      playAgainButton.setEnabled(true);

      dealersTurn();
    }

    add(dealerCardPanel, BorderLayout.CENTER);
    add(playerCardPanel, BorderLayout.SOUTH);
    add(playerBankPanel, BorderLayout.EAST);
  }

  /**
   * Deals the payer another card.
   */
  public void hit() {
    doubleDownButton.setEnabled(false);

    Card hitCard = game.shoe.removeLast();
    game.player1.hit(hitCard);
    playercardhit = new JLabel(hitCard.getCardImage());
    playerCardPanel.add(playercardhit);
    playerCardPanel.repaint();

    if (game.player1.getHands().get(0).isBust() || game.player1.getCardTotal() == 21) {
      hitButton.setEnabled(false);
      dealButton.setEnabled(false);
      stickButton.setEnabled(false);
      playAgainButton.setEnabled(true);

      dealersTurn();
    }

    playerLabel.setText("  Player:   " + game.player1.getCardTotal());
  }

  /**
   * Sets the player's hand to stick and switches to the dealer.
   */
  public void stick() {
    game.player1.getHands().get(0).setSticking(true);
    dealersTurn();
  }

  /**
   * Double down on the player's bet.
   */
  public void doubleDown() {
    doubleDownButton.setEnabled(false);
    game.player1.doubleDown(game.player1.getHands().get(0));
    updateBankLabel();
    hit();
    stick();
  }

  /**
   * Captures user input for placing a bet.
   */
  public void placeBet() {
    boolean isValidChoice = false;

    while (!isValidChoice) {
      String userInput = JOptionPane.showInputDialog("How much do you want to bet? Press \"M\" for minimum bet.");

      if (userInput.equalsIgnoreCase("M") || userInput.isEmpty()) {
        game.player1.getHands().get(0).setBet(Blackjack.TABLE_MINIMUM);
        game.player1.setPlayerBank(game.player1.getPlayerBank() - Blackjack.TABLE_MINIMUM);
        updateBankLabel();
        isValidChoice = true;
      }
      else {
        try {
          game.player1.getHands().get(0).setBet(Double.parseDouble(userInput));
          game.player1.setPlayerBank(game.player1.getPlayerBank() - Double.valueOf(userInput));
          updateBankLabel();
          isValidChoice = true;
        }
        catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(this.getParent(), "Enter \"M\" for minimum bet or a numerical value.");
        }
      }
    }
  }

  /**
   * Reset the GUI for a new game. This doesn't reset the bank.
   */
  public void playagain() {
    dealerLabel.setText("Dealer: ");
    playerLabel.setText("Player: ");
    statusTextBox.setText("");

    game.dealer.getHands().clear();
    game.player1.getHands().clear();

    dealerCardPanel.removeAll();
    playerCardPanel.removeAll();

    hitButton.setEnabled(false);
    stickButton.setEnabled(false);
    playAgainButton.setEnabled(false);
    doubleDownButton.setEnabled(false);
    dealButton.setEnabled(true);
    statusTextBox.setVisible(false);

    deal();
  }

  /**
   * Switches to the dealer.
   */
  public void dealersTurn() {
    dealerCardPanel.remove(dealercard0);
    dealerCardPanel.add(dealercard1);
    dealerCardPanel.removeAll();
    dealerCardPanel.add(dealerLabel);
    dealerLabel.setText(" " + dealerLabel.getText() + ": " + game.dealer.getCardTotal());

    //iterate through cards and re-display
    Card dhitcard = null;
    Iterator<Card> scan = (game.dealer.getHands().get(0).iterator());

    while (scan.hasNext()) {
      dhitcard = scan.next();
      dealercardhit = new JLabel(dhitcard.getCardImage());
      dealerCardPanel.add(dealercardhit);
    }

    playerLabel.setText("Player: " + game.player1.getHands().get(0));

    hitButton.setEnabled(false);
    stickButton.setEnabled(false);

    playAgainButton.setEnabled(true);
    dealerCardPanel.repaint();

    boolean isGameFinished = game.player1.getHands().get(0).isBust();

    while(!isGameFinished) {
      if (!game.dealer.getHands().get(0).isSticking() && !game.dealer.getHands().get(0).isBust()) {
        if (game.dealer.getCardTotal() < 17) {
          Card card = game.shoe.removeLast();
          game.dealer.hit(card);
          dealercardhit = new JLabel(card.getCardImage());
          dealerCardPanel.add(dealercardhit);
          playerCardPanel.repaint();
        }
        else {
          game.dealer.getHands().get(0).setSticking(true);
        }
      }

      if (game.dealer.getHands().get(0).isSticking() || game.dealer.getHands().get(0).isBust()) {
        isGameFinished = true;
      }
    }

    dealerLabel.setText("Dealer: " + game.dealer.getCardTotal());
    playerLabel.setText("Player1: " + game.player1.getCardTotal());
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
    statusTextBox.setVisible(true);

    if ((game.dealer.getHands().get(0).getNumberOfCardsInHand() == 2 && game.dealer.getCardTotal() == 21)
        && game.player1.getHands().get(0).getNumberOfCardsInHand() == 2 && game.player1.getCardTotal() == 21) {

      statusTextBox.setText("Push");
      game.player1.setPlayerBank(game.player1.getPlayerBank() + (game.player1.getHands().get(0).getBet()));
    }
    else if ((game.dealer.getHands().get(0).getNumberOfCardsInHand() == 2 && game.dealer.getCardTotal() == 21)
      && ((game.player1.getHands().get(0).getNumberOfCardsInHand() == 2 && game.player1.getCardTotal() != 21)
        || game.player1.getHands().get(0).getNumberOfCardsInHand() > 2)) {

      statusTextBox.setText("Dealer has Blackjack!");
    }
    else if ((game.player1.getHands().get(0).getNumberOfCardsInHand() == 2 && game.player1.getCardTotal() == 21)
      && ((game.dealer.getHands().get(0).getNumberOfCardsInHand() == 2 && game.dealer.getCardTotal() != 21)
        || game.dealer.getHands().get(0).getNumberOfCardsInHand() > 2)) {

      statusTextBox.setText("Player has Blackjack!");

      // Update bank with blackjack winning ratio at 3:2.
      game.player1.setPlayerBank(game.player1.getPlayerBank() + (game.player1.getHands().get(0).getBet() * 2.5));
    }
    else if (game.dealer.getCardTotal() > 21 && game.player1.getCardTotal() <= 21) {
      statusTextBox.setText("Dealer Bust, Player1 wins");

      // Add bet plus win back to the player bank at 1:1
      game.player1.setPlayerBank(game.player1.getPlayerBank() + (game.player1.getHands().get(0).getBet() * 2));
    }
    else if (game.player1.getCardTotal() > 21 && game.dealer.getCardTotal() <= 21) {
      statusTextBox.setText("Player1 BUST, Dealer wins");
    }
  
    else if (game.player1.getCardTotal() > 21 && game.dealer.getCardTotal() > 21) {
      statusTextBox.setText("Player1 and Dealer BUST!");
    }
    else {
      if (game.dealer.getCardTotal() > game.player1.getCardTotal()) {
        statusTextBox.setText("DEALER WINS!");
      }
      else if (game.player1.getCardTotal() > game.dealer.getCardTotal()) {
        statusTextBox.setText("PLAYER1 WINS!");

        // Add bet plus win back to the player bank at 1:1
        game.player1.setPlayerBank(game.player1.getPlayerBank() + (game.player1.getHands().get(0).getBet() * 2));
      }
      else {
        statusTextBox.setText("Push");
        game.player1.setPlayerBank(game.player1.getPlayerBank() + (game.player1.getHands().get(0).getBet()));
      }
    }

    updateBankLabel();
  }

  /**
   * Convenience method for updating the player's bank status.
   */
  private void updateBankLabel() {
    playerBankLabel.setText("  Bank:  " + game.player1.getPlayerBank());
  }

}
