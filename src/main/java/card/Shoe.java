package card;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Class that represents a shoe of cards. This can be 1 - n decks.
 *
 * @author David Stewart
 */
public class Shoe extends LinkedList<Card> {
  
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
  public Shoe(List<Deck> pDecks, boolean pShuffle) {
    addDecksToShoe(pDecks, pShuffle);
  }
  
  /**
   * This constructor will populate the shoe with the given number of decks.
   *
   * @param pNumberOfDecks
   *   The number of decks to create and populate the shoe with.
   */
  public Shoe(int pNumberOfDecks, boolean pShuffle) {
    populateShoe(pNumberOfDecks, pShuffle);
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

  //-----------------------------------
  // property: reshuffleMarkerLocation
  //-----------------------------------
  private int mReshuffleMarkerLocation= 0;

  /**
   * @param pReshuffleMarkerLocation
   *   The location of the shoe's reshuffle marker.
   */
  public void setReshuffleMarkerLocation(int pReshuffleMarkerLocation) {
    mReshuffleMarkerLocation = pReshuffleMarkerLocation;
  }

  /**
   * @return
   *   The location of the shoe's reshuffle marker.
   */
  public int getReshuffleMarkerLocation() {
    return mReshuffleMarkerLocation;
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
  public void addDecksToShoe(List<Deck> pDecks, boolean pShuffle) {
    pDecks.forEach(this::addAll);
    setNumberOfDecksInShoe(pDecks.size());

    if (pShuffle) {
      shuffle();
    }
  }
  
  /**
   * Populate the shoe with the given number of decks.
   *
   * @param pNumDecks
   *   The number of decks that will be created and added to the shoe.
   */
  public void populateShoe(int pNumDecks, boolean pShuffle) {
    List<Deck> decks = new ArrayList<>(pNumDecks);

    IntStream.rangeClosed(1, pNumDecks)
      .forEach(number -> decks.add(new Deck()));
    
    addDecksToShoe(decks, pShuffle);
  }
  
  /**
   * Shuffle the shoe.
   */
  public void shuffle() {
    Collections.shuffle(this);
  }

  /**
   * Calculates and sets the reshuffle marker location for the shoe.
   *
   * @param pLowerBoundPercentile
   *   The lower bound percentile for the marker.
   * @param pUpperBoundPercentile
   *   The upper bound percentile for the marker.
   */
  public void insertReshuffleMarker(int pLowerBoundPercentile, int pUpperBoundPercentile){
    int lowerBoundLocation = size() * pLowerBoundPercentile / 100;
    int upperBoundLocation = size() * pUpperBoundPercentile / 100;

    Random random = new Random();
    int reshuffleMarkerLocation = random.nextInt(upperBoundLocation - lowerBoundLocation) + lowerBoundLocation;

    setReshuffleMarkerLocation(reshuffleMarkerLocation);
  }
}
