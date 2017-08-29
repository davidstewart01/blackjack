package game;

import card.Deck;
import player.BlackjackDealer;
import player.BlackjackPlayer;

import java.util.Scanner;

/**
 * Class that represents a game of blackjack.
 *
 * @author David Stewart
 */
public class Blackjack {

  BlackjackDealer dealer = new BlackjackDealer();
  BlackjackPlayer player1 = new BlackjackPlayer();
  Deck deck = new Deck();

  public Blackjack() {
    deck.shuffleDeck(deck.getCards());

    player1.getHand().add(deck.getCards().removeLast());
    dealer.getHand().add(deck.getCards().removeLast());
    player1.getHand().add(deck.getCards().removeLast());
    dealer.getHand().add(deck.getCards().removeLast());
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

      if (!dealersTurn) {
        if (!player1.isSticking() && !player1.isBust()) {
          System.out.println("");
          System.out.println("========");
          System.out.println("Dealer's card: " + dealer.getHand().get(0));
          System.out.println("========");
          System.out.println("");

          showPlayerHand(player1);
          String userChoice = captureUserChoice();

          if (userChoice.equalsIgnoreCase("H")) {
            player1.hit(deck.getCards().removeLast());
          }
          else if (userChoice.equalsIgnoreCase("S")) {
            player1.setSticking(true);
          }
          else {
            System.exit(1);
          }
        }
        if (!dealer.isSticking() || !dealer.isBust()) {
          dealersTurn = true;
        }
      }
      else {
        if (!dealer.isSticking() && !dealer.isBust()) {
          if (dealer.getCardTotal() < 17) {
            dealer.hit(deck.getCards().removeLast());
          }
          else {
            dealer.setSticking(true);
          }
        }

        if (!player1.isSticking() || !player1.isBust()) {
          dealersTurn = false;
        }

        if ((dealer.isSticking() || dealer.isBust())
            && (player1.isSticking() || player1.isBust())) {

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

    if (dealer.isBust() && !player1.isBust()) {
      System.out.println("PLAYER1 WINS!");
    }
    else if (player1.isBust() && !dealer.isBust()) {
      System.out.println("DEALER WINS!");
    }
    else if (dealer.isBust() && player1.isBust()) {
      System.out.println("BOTH PLAYERS BUST!");
    }
    else {
      if (dealer.getCardTotal() > player1.getCardTotal()) {
        System.out.println("DEALER WINS!");
      }
      else if (player1.getCardTotal() > dealer.getCardTotal()) {
        System.out.println("PLAYER1 WINS!");
      }
      else {
        if (dealer.getNumberOfCardsInHand() > player1.getNumberOfCardsInHand()) {
          System.out.println("DEALER WINS!");
        }
        else if (player1.getNumberOfCardsInHand() > dealer.getNumberOfCardsInHand()) {
          System.out.println("PLAYER1 WINS!");
        }
        else {
          System.out.println("ITS A DRAW!");
        }
      }
    }
  }

  /**
   * Display the given player 's cards.
   *
   * @param pPlayer
   *   The player whose cards will be shown.
   */
  public void showPlayerHand(BlackjackPlayer pPlayer) {
     pPlayer.getHand().forEach(System.out::println);
  }

  /**
   * @return
   *   The user's choice to hit, stick or quit.
   */
  public String captureUserChoice() {
    boolean isValidChoice = false;
    String userInput = "";

    while (!isValidChoice) {
      System.out.println("(H)it, (S)tick, or (Q)uit: ");

      Scanner scannerInput = new Scanner(System.in);
      userInput = scannerInput.nextLine();

      if (userInput.equalsIgnoreCase("H")
          || userInput.equalsIgnoreCase("S")
          || userInput.equalsIgnoreCase("Q")) {

        isValidChoice = true;
      }
      else{
        System.out.println("Please enter a valid action.");
      }
    }

    return userInput;
  }

}
