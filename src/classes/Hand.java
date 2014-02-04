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
		pokerRank = 0;
		sortHand();
	}

	public void determinePoker() {

	}

	public String handAsString(boolean numbering) {
		String handString = "";
		int i = 1;
		for (Card c : hand) {
			if (numbering) {
				handString += i + ". ";
			}
			handString += c.getNameAsString() + " ";
			i++;
		}
		return handString;
	}

	public int getRankOccurences(int r) {
		int count = 0;
		for (Card c : hand) {
			if (c.getRank() == r) {
				count++;
			}
		}
		return count - 1;
	}

	public int getSuitOccurences(int s) {
		int count = 0;
		for (Card c : hand) {
			if (c.getSuit() == s) {
				count++;
			}
		}
		return count - 1;
	}

	public void sortHand() {
		Collections.sort(hand);
	}

	public String getPokerString() {
		String pokerType = "";
		switch (pokerRank) {
		case 1:
			pokerType = "One Pair";
			break;
		case 2:
			pokerType = "Two Pair";
			break;
		case 3:
			pokerType = "Three of a Kind";
			break;
		case 4:
			pokerType = "Straight";
			break;
		case 5:
			pokerType = "Flush";
			break;
		case 6:
			pokerType = "Full House";
			break;
		case 7:
			pokerType = "Four of a Kind";
			break;
		case 8:
			pokerType = "Straight Flush";
			break;
		default:
			pokerType = "High Card";
		}
		return pokerType;
	}

	public int getPokerRank() {
		return pokerRank;
	}

	public void setPokerRank(int pokerRank) {
		this.pokerRank = pokerRank;
	}

}
