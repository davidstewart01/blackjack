package game;

import player.BlackjackPlayer;

import java.util.Scanner;

/**
 * Class that allows the game to be played in the console.
 */
public class BlackjackConsole {

  //---------------------------------------------------------------------
  // MEMBERS
  //---------------------------------------------------------------------

  Blackjack game = null;

  /**
   * Main method.
   *
   * @param pArgs
   */
  public static void main(String[] pArgs) {
    BlackjackConsole blackjackConsole = new BlackjackConsole();
    blackjackConsole.start();
  }

  /**
   * Start the game.
   */
  public void start() {
    game = new Blackjack();
    game.getDealer().dealNewGame(game.getPlayers(), game.getShoe());

    System.out.println("Starting game...");

    boolean isGameFinished = false;
    boolean dealersTurn = false;

    while (!isGameFinished) {

      //TODO - tidy up for the hand being played, not just the first one
      if (!dealersTurn) {
        if (game.getPlayers().get(0).getHands().get(0).getBet() == 0.0){
          placeBets();
        }
        if (!game.getPlayers().get(0).getHands().get(0).isSticking() && !game.getPlayers().get(0).getHands().get(0).isBust()) {
          System.out.println("");
          System.out.println("========");
          System.out.println("Dealer's card: " + game.getDealer().getHands().get(0));
          System.out.println("Dealer's total: " + game.getDealer().getCardTotal());
          System.out.println("========");
          System.out.println("");

          showPlayerHand(game.getPlayers().get(0));
          String userChoice = captureUserChoice();

          if (userChoice.equalsIgnoreCase("H")) {
            game.getPlayers().get(0).hit(game.getShoe().removeLast());
          }
          else if (userChoice.equalsIgnoreCase("S")) {
            game.getPlayers().get(0).getHands().get(0).setSticking(true);
          }
          else if (userChoice.equalsIgnoreCase("D")) {
            game.getPlayers().get(0).doubleDown(game.getPlayers().get(0).getHands().get(0));
            game.getPlayers().get(0).hit(game.getShoe().removeLast());
            game.getPlayers().get(0).getHands().get(0).setSticking(true);
          }
          else {
            System.exit(1);
          }
        }

        //TODO - remove this when dealing multiple hands, TEST ONLY
        if (game.getPlayers().get(0).getHands().get(0).isBust()){
          isGameFinished = true;
        }

        if ((!game.getDealer().getHands().get(0).isSticking()
          || !game.getDealer().getHands().get(0).isBust()) && game.getPlayers().get(0).getHands().get(0).isSticking()) {

          dealersTurn = true;
        }
      }
      // Dealers turns
      else {
        if (!game.getDealer().getHands().get(0).isSticking() && !game.getDealer().getHands().get(0).isBust()) {
          if (game.getDealer().getCardTotal() < 17) {
            game.getDealer().hit(game.getShoe().removeLast());
          }
          else {
            game.getDealer().getHands().get(0).setSticking(true);
          }
        }

        if ((game.getDealer().getHands().get(0).isSticking() || game.getDealer().getHands().get(0).isBust())
          && (game.getPlayers().get(0).getHands().get(0).isSticking() || game.getPlayers().get(0).getHands().get(0).isBust())) {

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

    if (game.getDealer().getCardTotal() > 21) {
      System.out.println("BUST!");
    }

    System.out.println(game.getDealer().getCardTotal());
    showPlayerHand(game.getDealer());
    System.out.println("");

    System.out.println("Player:");

    if (game.getPlayers().get(0).getCardTotal() > 21) {
      System.out.println("BUST!");
    }

    System.out.println(game.getPlayers().get(0).getCardTotal());
    showPlayerHand(game.getPlayers().get(0));
    System.out.println("");

    System.out.println(game.determineOutcome());

    System.out.println("Player BANK: " + game.getPlayers().get(0).getPlayerBank());
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
      System.out.println("Place your bets! Table Minimum is: " + Blackjack.TABLE_MINIMUM + "\n" +
        "(M)inimum or numeric value: ");

      Scanner scannerInput = new Scanner(System.in);
      userInput = scannerInput.nextLine();

      if (userInput.equalsIgnoreCase("M") || userInput.isEmpty() ) {
        game.getPlayers().get(0).getHands().get(0).setBet(Blackjack.TABLE_MINIMUM);
        game.getPlayers().get(0).setPlayerBank(game.getPlayers().get(0).getPlayerBank() - Blackjack.TABLE_MINIMUM);
        System.out.println("PLAYER BANK IS: " + game.getPlayers().get(0).getPlayerBank());
        isValidChoice = true;
      }
      else {
        game.getPlayers().get(0).getHands().get(0).setBet(Double.parseDouble(userInput));
        game.getPlayers().get(0).setPlayerBank(game.getPlayers().get(0).getPlayerBank() - Double.valueOf(userInput));
        System.out.println("PLAYER BANK IS: " + game.getPlayers().get(0).getPlayerBank());
      }
    }
  }

}
