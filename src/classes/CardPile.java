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

	public CardPile(boolean newDeck) {
		pile = new ArrayList<Card>();
		if (newDeck) {
			initializeDeck();
			shuffleDeck(3);
		}
	}

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

	public String pileAsString() {
		String pileString = "";
		for (Card c : pile) {
			pileString += c.getNameAsString() + "\n";
		}
		return pileString;
	}

	public void add(Card c) {
		pile.add(c);
	}

	public void remove(Card c) {
		pile.remove(c);
	}

}
