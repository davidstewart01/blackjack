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

  /** The game. */
  private Blackjack game = null;

  /*************************************************************
   the labels to represent the cards for the game
   *************************************************************/
  // TODO: Make local variables where appropriate.
  private JLabel playercard1 = null;
  private JLabel playercard2 = null;
  private JLabel playercardhit = null;
  private JLabel dealercard0 = null;
  private JLabel dealercard2 = null;
  private JLabel dealercard1 = null;
  private JLabel dealercardhit = null;

  /*************************************************************
   Constructs the screen
   *************************************************************/
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
    dealButton.addActionListener(new DealButton());

    hitButton.setText("  Hit");
    hitButton.addActionListener(new HitButton());
    hitButton.setEnabled(false);

    stickButton.setText("  Stick");
    stickButton.addActionListener(new StickButton());
    stickButton.setEnabled(false);

    doubleDownButton.setText("  Double Down");
    doubleDownButton.addActionListener(new DoubleDownButton());
    doubleDownButton.setEnabled(false);

    playAgainButton.setText("  Play Again");
    playAgainButton.addActionListener(new playAgainButton());
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
      myFrame.setPreferredSize(new Dimension(700,550));

      //Display the window.
      myFrame.pack();
      myFrame.setVisible(true);

    }//end display

    /*************************************************************
     DealButton
     @param e Deal button pressed
     *************************************************************/
    class DealButton implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        deal();
      }
    }

  public void deal() {
    game.deal();

    placeBet();

    dealerCardPanel.add(dealerLabel);
    playerCardPanel.add(playerLabel);
    playerBankPanel.add(playerBankLabel);

    // Get's dealer and player cards from Hand
    // and the image associated with that random
    // card and puts them on the screen.


    dealercard0 = new JLabel(new ImageIcon(this.getClass().getResource("../card/card_images/back.jpg")));

    //game.dealInitialCards();

    //to iterate set and get current dealer cards
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

      if(count==0)
        playercard1 = new JLabel(pcard.getCardImage());
      else
        playercard2 = new JLabel(pcard.getCardImage());

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


    /*************************************************************
     HitButton
     every time the player wants another card
     until hand value is over 21.
     @param e Hit button pressed
     *************************************************************/
    class HitButton implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        hit();
      }
    }//end hitButton

  /**
   *
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

    /*************************************************************
     stickButton
     dealer must hit on 16 or lower. determines the winner,
     player wins if under 21 and above dealer.
     Tie goes to dealer.
     @param e stick button pressed
     *************************************************************/
    class StickButton implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        stick();
      }
    }

  /**
   *
   */
  public void stick() {
    game.player1.getHands().get(0).setSticking(true);
    dealersTurn();
  }

  /**
   *
   */
  class DoubleDownButton implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      doubleDown();
    }
  }

  /**
   *
   */
  public void doubleDown() {
    doubleDownButton.setEnabled(false);
    game.player1.doubleDown(game.player1.getHands().get(0));
    updateBankLabel();
    hit();
    stick();
  }

  /**
   *
   */
  public void placeBet() {

    String userInput = JOptionPane.showInputDialog("How much do you want to bet?");

    if (userInput.equalsIgnoreCase("M") || userInput.isEmpty() ) {
      game.player1.getHands().get(0).setBet(Blackjack.TABLE_MINIMUM);
      game.player1.setPlayerBank(game.player1.getPlayerBank() - Blackjack.TABLE_MINIMUM);
      updateBankLabel();
      //isValidChoice = true;
    }
    else {
      game.player1.getHands().get(0).setBet(Double.parseDouble(userInput));
      game.player1.setPlayerBank(game.player1.getPlayerBank() - Double.valueOf(userInput));
      updateBankLabel();
    }
  }

  /**
   *
   */
  private void updateBankLabel() {
    playerBankLabel.setText("  Bank:  " + game.player1.getPlayerBank());
  }

    /*************************************************************
     PlayAgainButton
     resets screen
     @param e Play Again button pressed
     *************************************************************/
    class playAgainButton implements ActionListener {
      public void actionPerformed(ActionEvent e) {

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
    }



    public void dealersTurn() {
      
      dealerCardPanel.remove(dealercard0);
      dealerCardPanel.add(dealercard1);
  
      //dealer = game.dealerPlays();
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
  
}
