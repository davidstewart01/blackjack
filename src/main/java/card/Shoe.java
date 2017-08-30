package card;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents a shoe of cards. This can be 1 - n decks.
 *
 * @author David Stewart
 */
public class Shoe extends LinkedList<Card> {
  
  //---------------------------------------------------------------------
  // CONSTANTS
  //---------------------------------------------------------------------
  
  
  //---------------------------------------------------------------------
  // CONSTRUCTORS
  //---------------------------------------------------------------------
  
  /**
   * Default constructor.
   */
  public Shoe() {
  }
  
  /**
   * This constructor sets up the shoe with a predefined list of decks.
   *
   * @param pDecks
   *   The decks to add to the shoe.
   */
  public Shoe(List<Deck> pDecks) {
    addDecksToShoe(pDecks);
  }
  
  //---------------------------------------------------------------------
  // PROPERTIES
  //---------------------------------------------------------------------
  
  //-----------------------------------
  // property: numberOfDecksInShoe
  //-----------------------------------
  private int mNumberOfDecksInShoe = 0;
  
  /**
   * @param pNumberOfDecksInShoe
   *   The number of decks contained in the shoe.
   */
  public void setNumberOfDecksInShoe(int pNumberOfDecksInShoe) {
    mNumberOfDecksInShoe = pNumberOfDecksInShoe;
  }
  
  /**
   * @return
   *   The number of decks contained in the shoe.
   */
  public int getNumberOfDecksInShoe() {
    return mNumberOfDecksInShoe;
  }
  
  //---------------------------------------------------------------------
  // METHODS
  //---------------------------------------------------------------------
  
  /**
   * Adds the given list of decks of cards to the shoe.
   *
   * Once the shoe has been populated, it will be shuffled.
   *
   * @param pDecks
   *   The list of decks to be added to the shoe.
   */
  public void addDecksToShoe(List<Deck> pDecks) {
    for (Deck deck : pDecks) {
      for (Card card : deck) {
        add(card);
      }
    }
    
    setNumberOfDecksInShoe(pDecks.size());
    Collections.shuffle(this);
  }
  
}
