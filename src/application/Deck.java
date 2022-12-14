//not implemented :(
package application;

import java.util.ArrayList;

/**
 * object to track decks composed of cards.
 * @author bleu
 *
 */
public class Deck {
	private String deckName = "";
	private ArrayList<Card> deckList = new ArrayList<Card>();
	
	
	
	public Deck(String deckName, ArrayList<Card> deckList) {
		this.deckName = deckName;
		this.deckList = deckList;
	}
	
	public Deck(String deckName) {
		this.deckName = deckName;
		this.deckList = new ArrayList<Card>();
	}
	
	public Deck(Deck deckToCopy) {
		deckName = deckToCopy.getDeckName();
		deckList = deckToCopy.getDeckList();
	}

	public String getDeckName() {
		return deckName;
	}
	
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}
	
	public ArrayList<Card> getDeckList() {
		return deckList;
	}
	
	public void setDeckList(ArrayList<Card> deckList) {
		this.deckList = deckList;
	}
	
	public void addCard(Card card) {
		deckList.add(card);
	}

}
