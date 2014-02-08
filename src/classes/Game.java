/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Game class, containing the user interface and 
 * methods for shuffling Card Piles.
 */
package classes;

import java.util.ArrayList;
import java.util.List;


public class Game {

	public static void main(String[] args) {
		String[] tmp = new String[] { "AD", "AS", "4C", "4H", "3S)" };
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			Card c = new Card(tmp[i]);
			cards.add(c);
		}

		Hand temp = new Hand(cards);
		System.out.println(temp.getPokerString());

		// CardPile test = new CardPile(true);
	}

}
