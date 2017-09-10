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
  
  /** Text box that displays the outcome of the game. */
  private JTextPane statusTextBox = new JTextPane();
  
  /** Button that performs a hit for the player. */
  private JButton hitButton = new JButton();
  
  /** Button that deals the cards at the start of the game. */
  private JButton dealButton = new JButton();
  
  /** Button that performs a stick for the player. */
  private JButton stickButton = new JButton();
  
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
    dealButton.addActionListener(new dealButton());

    hitButton.setText("  Hit");
    hitButton.addActionListener(new hitButton());
    hitButton.setEnabled(false);

    stickButton.setText("  Stick");
    stickButton.addActionListener(new stickButton());
    stickButton.setEnabled(false);

    playAgainButton.setText("  Play Again");
    //playAgainButton.addActionListener(new playAgainButton());
    playAgainButton.setEnabled(false);

    dealerLabel.setText("  Dealer:  ");
    playerLabel.setText("  Player:  ");

    topPanel.add(statusTextBox);
    topPanel.add(dealButton);
    topPanel.add(hitButton);
    topPanel.add(stickButton);
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
    class dealButton implements ActionListener {
      public void actionPerformed(ActionEvent e) {

        dealerCardPanel.add(dealerLabel);
        playerCardPanel.add(playerLabel);


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

        if (game.player1.getCardTotal() == 21){
          hitButton.setEnabled(false);
          stickButton.setEnabled(false);
          dealButton.setEnabled(false);
          playAgainButton.setEnabled(true);

          dealersTurn();
        }

        add(dealerCardPanel,BorderLayout.CENTER);
        add(playerCardPanel,BorderLayout.SOUTH);
      }
    }//end dealButton

    /*************************************************************
     HitButton
     every time the player wants another card
     until hand value is over 21.
     @param e Hit button pressed
     *************************************************************/
    class hitButton implements ActionListener {
      public void actionPerformed(ActionEvent e) {

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
    }//end hitButton

    /*************************************************************
     stickButton
     dealer must hit on 16 or lower. determines the winner,
     player wins if under 21 and above dealer.
     Tie goes to dealer.
     @param e stick button pressed
     *************************************************************/
    class stickButton implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        game.player1.getHands().get(0).setSticking(true);
        dealersTurn();
      }
    }

    /*************************************************************
     PlayAgainButton
     resets screen
     @param e Play Again button pressed
     *************************************************************/
    /*class playAgainButton implements ActionListener {
      public void actionPerformed(ActionEvent e) {

        dealerLabel.setText("Dealer: ");
        playerLabel.setText("Player: ");
        statusTextBox.setText("");
        dealer = new Hand();
        player = new Hand();
        game=new Blackjack(dealer, player);

        dealerCardPanel.removeAll();
        playerCardPanel.removeAll();

        hitButton.setEnabled(false);
        stickButton.setEnabled(false);
        playAgainButton.setEnabled(false);
        dealButton.setEnabled(true);

      }
    }//end playAgainButton */



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
    statusTextBox.setVisible(true);

    if ((game.dealer.getHands().get(0).getNumberOfCardsInHand() == 2 && game.dealer.getCardTotal() == 21)
        && game.player1.getHands().get(0).getNumberOfCardsInHand() == 2 && game.player1.getCardTotal() == 21) {

      statusTextBox.setText("Player and Dealer have Blackjack!");
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
    }
    else if (game.dealer.getCardTotal() > 21 && game.player1.getCardTotal() <= 21) {
      statusTextBox.setText("Dealer Bust, Player1 wins");
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
      }
      else {
        statusTextBox.setText("ITS A DRAW!");
      }
    }
  }
  
}
