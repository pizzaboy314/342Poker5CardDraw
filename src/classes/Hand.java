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
		System.out.println(handAsString(false));
		determinePoker(); // hand is self-aware
	}

	public void determinePoker() {
		if (areSequential() && getSuitOccurences(5) == 5) {
			pokerRank = 8; // straight flush
		} else if (getRankOccurences(4) == 7) {
			pokerRank = 7; // four of a kind
		} else if (getRankOccurences(2) == 8) {
			pokerRank = 6; // full house
		} else if (getSuitOccurences(5) == 5) {
			pokerRank = 5; // flush
		} else if (areSequential()) {
			pokerRank = 4; // straight
		} else if (getRankOccurences(3) == 5) {
			pokerRank = 3; // three of a kind
		} else if (getRankOccurences(2) == 6) {
			pokerRank = 2; // two pair
		} else if (getRankOccurences(2) == 2) {
			pokerRank = 1; // one pair
		} else {
			pokerRank = 0; // high card
		}

	}

	public int getRankOccurences(int n) { // n = matches expected
		int count = 0;
		for (int i = 0; i <= (5 - n); i++) {
			List<Card> handAlt = hand.subList(i, hand.size());
			int r = handAlt.get(0).getRank();
			for (Card c : handAlt) {
				if (c.getRank() == r) {
					count++;
				}
			}
			if (n == 4 && count == 1) {
				count += 2;
			}
		}
		return count;
	}

	public int getSuitOccurences(int n) { // n = matches expected
		int count = 0;
		for (int i = 0; i <= (5 - n); i++) {
			List<Card> handAlt = hand.subList(i, hand.size());
			int s = handAlt.get(0).getSuit();
			for (Card c : handAlt) {
				if (c.getSuit() == s) {
					count++;
				}
			}
		}
		return count;
	}
	public boolean areSequential() {
		int firstRank = hand.get(0).getRank();
		int[] set = new int[] { firstRank, firstRank - 1, firstRank - 2,
				firstRank - 3, firstRank - 4 };
		boolean sequentialAceHigh = true;
		boolean sequentialAceLow = false;
		int i=0;

		for (Card c : hand) {
			if (c.getRank() != set[i]) {
				sequentialAceHigh = false;
			}
			i++;
		}
		if (firstRank == 14) {
			sequentialAceLow = true;
			List<Card> handAlt = new ArrayList<Card>(hand);
			Card card = hand.get(0);
			handAlt.remove(card);
			card = new Card(hand.get(0).getIdString());
			card.setRank(1);
			handAlt.add(card);

			firstRank = handAlt.get(0).getRank();
			set = new int[] { firstRank, firstRank - 1, firstRank - 2,
					firstRank - 3, firstRank - 4 };
			i = 0;
			for (Card c : handAlt) {
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



	public void sortHand() {
		Collections.sort(hand);
	}

	public String getPokerString() {
		String pokerType = "";
		switch (pokerRank) {
		case 0:
			pokerType = "High Card";
			break;
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
			pokerType = "Error";
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

	public List<Card> getHand() {
		return hand;
	}

}
