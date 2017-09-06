package game;

import card.Shoe;
import player.BlackjackDealer;
import player.BlackjackPlayer;

import java.util.Scanner;

/**
 * Class that represents a game of blackjack.
 *
 * @author David Stewart
 */
public class Blackjack {

  //---------------------------------------------------------------------
  // CONSTANTS
  //---------------------------------------------------------------------

  public static final double TABLE_MINIMUM = 10;

  BlackjackDealer dealer = new BlackjackDealer();
  BlackjackPlayer player1 = new BlackjackPlayer();
  Shoe shoe = new Shoe(1, true);

  public Blackjack() {
    //TODO fix this up to handle multiple hands more cleanly
    player1.getHands().get(0).add(shoe.removeLast());
    dealer.getHands().get(0).add(shoe.removeLast());
    player1.getHands().get(0).add(shoe.removeLast());
    dealer.getHands().get(0).add(shoe.removeLast());
  }

  /**
   * Main method.
   *
   * @param pArgs
   */
  public static void main(String[] pArgs) {
    Blackjack blackjack = new Blackjack();
    blackjack.start();
  }

  /**
   * Start the game.
   */
  public void start() {
    System.out.println("Starting game...");
    boolean isGameFinished = false;
    boolean dealersTurn = false;

    while (!isGameFinished) {

      //TODO - tidy up for the hand being played, not just the first one
      if (!dealersTurn) {
        if (player1.getHands().get(0).getBet() == 0.0){
          placeBets();
        }
        if (!player1.isSticking() && !player1.getHands().get(0).isBust()) {
          System.out.println("");
          System.out.println("========");
          System.out.println("Dealer's card: " + dealer.getHands().get(0));
          System.out.println("Dealer's total: " + dealer.getCardTotal());
          System.out.println("========");
          System.out.println("");

          showPlayerHand(player1);
          String userChoice = captureUserChoice();

          if (userChoice.equalsIgnoreCase("H")) {
            player1.hit(shoe.removeLast());
          }
          else if (userChoice.equalsIgnoreCase("S")) {
            player1.setSticking(true);
          }
          else if (userChoice.equalsIgnoreCase("D")) {
            player1.doubleDown(player1.getHands().get(0));
            player1.hit(shoe.removeLast());
            player1.setSticking(true);
          }
          else {
            System.exit(1);
          }
        }
        //TODO - remove this when dealing multiple hands, TEST ONLY
        if (player1.getHands().get(0).isBust()){
          isGameFinished = true;
        }
        if ((!dealer.isSticking() || !dealer.getHands().get(0).isBust()) && player1.isSticking()) {
          dealersTurn = true;
        }
      }
      // Dealers turns
      else {
        if (!dealer.isSticking() && !dealer.getHands().get(0).isBust()) {
          if (dealer.getCardTotal() < 17) {
            dealer.hit(shoe.removeLast());
          }
          else {
            dealer.setSticking(true);
          }
        }

        if ((dealer.isSticking() || dealer.getHands().get(0).isBust())
            && (player1.isSticking() || player1.getHands().get(0).isBust())) {

          isGameFinished = true;
        }

      }
    }

    determineGameOutcome();
  }

  /**
   *
   */
  public void determineGameOutcome() {
    System.out.println("\n");
    System.out.println("Dealer:");

    if (dealer.getCardTotal() > 21) {
      System.out.println("BUST!");
    }

    System.out.println(dealer.getCardTotal());
    showPlayerHand(dealer);
    System.out.println("");

    System.out.println("Player1:");

    if (player1.getCardTotal() > 21) {
      System.out.println("BUST!");
    }

    System.out.println(player1.getCardTotal());
    showPlayerHand(player1);
    System.out.println("");

    if (dealer.getHands().get(0).isBust() && !player1.getHands().get(0).isBust()) {
      System.out.println("PLAYER1 WINS!");
      //add bet plus win back to the player bank at 1:1
      // TODO - determine value for BlackJack at 3:2
      player1.setPlayerBank(player1.getPlayerBank() + (player1.getHands().get(0).getBet() * 2));
    }
    else if (player1.getHands().get(0).isBust() && !dealer.getHands().get(0).isBust()) {
      System.out.println("DEALER WINS!");
    }
    else if (dealer.getHands().get(0).isBust() && player1.getHands().get(0).isBust()) {
      System.out.println("BOTH PLAYERS BUST!");
    }
    else {
      if (dealer.getCardTotal() > player1.getCardTotal()) {
        System.out.println("DEALER WINS!");
      }
      else if (player1.getCardTotal() > dealer.getCardTotal()) {
        System.out.println("PLAYER1 WINS!");
        //add bet plus win back to the player bank at 1:1
        // TODO - determine value for BlackJack at 3:2
        player1.setPlayerBank(player1.getPlayerBank() + (player1.getHands().get(0).getBet() * 2));
      }
      else {
        if (dealer.getHands().get(0).getNumberOfCardsInHand() > player1.getHands().get(0).getNumberOfCardsInHand()) {
          System.out.println("DEALER WINS!");
        }
        else if (player1.getHands().get(0).getNumberOfCardsInHand() > dealer.getHands().get(0).getNumberOfCardsInHand()) {
          System.out.println("PLAYER1 WINS!");
        }
        else {
          System.out.println("ITS A DRAW!");
        }
      }
    }
    System.out.println("PLAYER1 BANK: " + player1.getPlayerBank());
  }

  /**
   * Display the given player 's cards.
   *
   * @param pPlayer
   *   The player whose cards will be shown.
   */
  public void showPlayerHand(BlackjackPlayer pPlayer) {
     pPlayer.getHands().get(0).forEach(System.out::println);
     System.out.println("Player's hand total: " + pPlayer.getCardTotal());
  }

  /**
   * @return
   *   The user's choice to hit, stick, double down or quit.
   */
  public String captureUserChoice() {
    boolean isValidChoice = false;
    String userInput = "";

    while (!isValidChoice) {
      System.out.println("(H)it, (S)tick, (D)ouble Down or (Q)uit: ");

      Scanner scannerInput = new Scanner(System.in);
      userInput = scannerInput.nextLine();

      if (userInput.equalsIgnoreCase("H")
          || userInput.equalsIgnoreCase("S")
          || userInput.equalsIgnoreCase("Q")
          || userInput.equalsIgnoreCase("D")) {

        isValidChoice = true;
      }
      else{
        System.out.println("Please enter a valid action.");
      }
    }

    return userInput;
  }

  /**
   * @return
   *   The bet value wagered on the hand.
   */
  public void placeBets() {
    boolean isValidChoice = false;
    String userInput = "";

    while (!isValidChoice) {
      System.out.println("Place your bets! Table Minimum is: " + TABLE_MINIMUM + "\n" +
              "(M)inimum or numeric value: ");

      Scanner scannerInput = new Scanner(System.in);
      userInput = scannerInput.nextLine();

      if (userInput.equalsIgnoreCase("M") || userInput.isEmpty() ) {
        player1.getHands().get(0).setBet(TABLE_MINIMUM);
        player1.setPlayerBank(player1.getPlayerBank() - TABLE_MINIMUM);
        System.out.println("PLAYER BANK IS: " + player1.getPlayerBank());
        isValidChoice = true;
      }
      else {
        player1.getHands().get(0).setBet(Double.parseDouble(userInput));
        player1.setPlayerBank(player1.getPlayerBank() - Double.valueOf(userInput));
        System.out.println("PLAYER BANK IS: " + player1.getPlayerBank());
      }
    }
  }

}
