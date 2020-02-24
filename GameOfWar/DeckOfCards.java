import java.util.ArrayList;
import java.util.List;

/**
 *	Deck of cards
 *
 *	@author	Meet Vora
 *	@since	April 12th, 2019
 */
public class DeckOfCards
{
	private SinglyLinkedList<Card> deck;	// the deck of cards
	
	/** constructor */
	public DeckOfCards() {
		deck = new SinglyLinkedList<Card>();
	}
	
	/**	Add the Card to the bottom of the deck
	 *	@param c		the card to add
	 */
	public void add(Card c) {
		deck.add(c);
	}
	
	/**	Removes the card from the top of the deck
	 *	@return		the card removed from the top of the deck; null if deck is empty
	 */
	public Card draw() { 
		if(deck.size() == 0)
			return null;
		return deck.remove(0);
	}
	
	/**	Clear the deck of cards */
	public void clear()	{
		deck.clear();
	}
	
	/**	Clear deck and fills with standard 52 card deck */
	public void fill() {
		clear();
		// add the four suits of each rank card 
		for(int rank = 0; rank < 13; rank++)
			for(int suit = 0; suit < 4; suit++)
				// loops through indices of enums
				add(new Card(Rank.values()[rank], Suit.values()[suit]));
		// deck = new SinglyLinkedList(SinglyLinkedList<Card>());
	}
	
	/**	@return		true if deck is empty; false otherwise */
	public boolean isEmpty() { return deck.isEmpty(); }
	
	/**	Randomizes the order of the cards in the deck
	 *	This method must be SinglyLinkedList-based (do not use arrays or ArrayList)
	 */
	public void shuffle() {
		SinglyLinkedList<Card> temp = new SinglyLinkedList<Card>();
		// transfer randomly selected cards from deck to temporary list
		for(int a = deck.size() - 1; a >= 0; a--) {
			temp.add(deck.remove((int)(Math.random()*(a+1))));
		}

		// clear the deck from its original card order
		deck.clear();
		int size = temp.size();
		
		// add the now randomized order of cards back to the original deck list
		for(int a = 0; a < size; a++)
			deck.add(temp.get(a).getValue());
	}
	
	/**	@return		the size of the deck */
	public int size() { return deck.size(); }
	
	/**	print the deck */
	public void print()
	{
		for (int a = 0; a < deck.size(); a++)
			System.out.print(deck.get(a) + "; ");	
		// for (int a = 0; a < deck.size(); a++) {
		// 	System.out.print(deck.get(a).getValue().getRank() + " ");
		// 	System.out.print(deck.get(a).getValue().getSuit() + "; ");
		// }
		System.out.println();
	}
	
	/***************************************************************************/
	/************************** Testing methods ********************************/
	/***************************************************************************/
	public static void main(String[] args)
	{
		DeckOfCards doc = new DeckOfCards();
		doc.fill();
		doc.add(new Card(Rank.ACE, Suit.SPADES));
		System.out.println("Deck size = " + doc.size());
		doc.print();

		System.out.println();
		doc.shuffle();
		System.out.println("Deck size = " + doc.size());
		doc.print();
		System.out.println();
	}
}