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
		if (areSequential()) {
			if (getSuitOccurences(hand.get(0).getSuit()) == 5) {
				pokerRank = 8;
			} else {
				pokerRank = 4;
			}
		} else if (getSuitOccurences(hand.get(0).getSuit()) == 4) {
			pokerRank = 5;
		} else if(getRankOccurences(hand.get(0).getRank()) == 3){
			pokerRank = 7;
		} else if (getRankOccurences(hand.get(0).getRank()) == 2) {
			// pokerRank = 3;


		}
	}

	public boolean areSequential() {
		int firstRank = hand.get(0).getRank();
		int[] set = new int[] { firstRank, firstRank - 1, firstRank - 2,
				firstRank - 3, firstRank - 4 };
		boolean sequentialAceHigh = true;
		boolean sequentialAceLow = true;
		int i=0;

		for (Card c : hand) {
			if (c.getRank() != set[i]) {
				sequentialAceHigh = false;
			}
			i++;
		}
		if (firstRank == 14) {
			List<Card> handAlt = new ArrayList<Card>(hand);
			Card card = hand.get(0);
			handAlt.remove(card);
			card.setRank(1);
			handAlt.add(card);

			firstRank = handAlt.get(0).getRank();
			set = new int[] { firstRank, firstRank - 1, firstRank - 2,
					firstRank - 3, firstRank - 4 };
			i = 0;
			for (Card c : hand) {
				if (c.getRank() != set[i]) {
					sequentialAceLow = false;
				}
				i++;
			}
		}
		if (sequentialAceLow || sequentialAceHigh) {
			return true;
		} else {
			return false;
		}
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

	public int getRankOccurences(Card c) {
		int index = hand.indexOf(c);
		int len = hand.size();
		int r = c.getRank();
		int count = 0;

		for (int i = index; i < len; i++) {
			if (hand.get(i).getRank() == r) {
				count++;
			}
		}
		return count - 1;
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

	public void add(Card c) {
		hand.add(c);
	}

	public void remove(Card c) {
		hand.remove(c);
	}

	public Card get(int x) {
		return hand.get(x);
	}

	public int getPokerRank() {
		return pokerRank;
	}

	public void setPokerRank(int pokerRank) {
		this.pokerRank = pokerRank;
	}

}
