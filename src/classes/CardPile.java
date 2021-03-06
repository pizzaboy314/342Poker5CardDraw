/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Card Pile class, containing a Collection of type Card, and methods for 
 * initializing, sorting, and shuffling the Pile.
 */
package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardPile {

	private List<Card> pile;

	// if newDeck is true, create new deck and assign to pile
	// otherwise, create empty pile to send cards to later
	public CardPile(boolean newDeck) {
		pile = new ArrayList<Card>();
		if (newDeck) {
			initializeDeck();
		}
	}

	// creates a new 52-card deck in suit-rank order
	public void initializeDeck() {
		char[] suits = new char[] { 'C', 'D', 'H', 'S' };
		char[] ranks = new char[] { '2', '3', '4', '5', '6', '7', '8', '9',
				'T', 'J', 'Q', 'K', 'A' };
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				String s = "" + ranks[j] + suits[i];
				ids.add(s);
			}
		}
		for (String s : ids) {
			Card c = new Card(s);
			add(c);
		}
	}

	// shuffles the deck user-specified # of times; default once
	public void shuffleDeck(int numTimes) {
		if (numTimes > 0) {
			for (int i = 0; i < numTimes; i++) {
				Collections.shuffle(pile,
						new Random(System.currentTimeMillis()));
			}
		} else {
			Collections.shuffle(pile, new Random(System.currentTimeMillis()));
		}
	}

	// returns the string representation of the pile
	public String pileAsString() {
		String pileString = "";
		for (Card c : pile) {
			pileString += c.getNameAsString() + "\n";
		}
		return pileString;
	}

	// adds a card to the pile
	public void add(Card c) {
		pile.add(c);
	}

	// removes a card from the pile
	public void remove(Card c) {
		pile.remove(c);
	}

	// returns the card at position x
	public Card get(int x) {
		return pile.get(x);
	}

	// returns the size of this pile
	public int size() {
		return pile.size();
	}

}
