/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Hand class, containing a user or opponent's 5 cards and 
 * methods for evaluating the hand.
 */
package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
	private int pokerRank;
	private List<Card> hand;

	public Hand(List<Card> in) {
		hand = new ArrayList<Card>(in);
		System.out.println(handAsString(true));
		sortHand();
		System.out.println(handAsString(true));
	}

	public String handAsString(boolean numbering) {
		String handString = "";
		int i = 1;
		for (Card c : hand) {
			if(numbering){
				handString += i + ". ";
			}
			handString += c.getNameAsString() + " ";
			i++;
		}
		return handString;
	}

	public void sortHand() {
		Collections.sort(hand);
	}

	public int getPokerRank() {
		return pokerRank;
	}

	public void setPokerRank(int pokerRank) {
		this.pokerRank = pokerRank;
	}

}
