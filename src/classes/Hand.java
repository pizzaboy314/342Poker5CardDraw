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
	private int handSum;
	private List<Card> hand;

	public Hand() {
		hand = new ArrayList<Card>();
		pokerRank = 0;
		handSum = 0;
	}

	// determines the poker value of the hand
	public void determinePoker() {
		if (areSequential(hand) && getSuitOccurences(5) == 5) { // straight
																// flush
			pokerRank = 8;
		} else if (getRankOccurences(4) == 7) { // four of a kind
			pokerRank = 7;
		} else if (getRankOccurences(2) == 8) { // full house
			pokerRank = 6;
		} else if (getSuitOccurences(5) == 5) { // flush
			pokerRank = 5;
		} else if (areSequential(hand)) { // straight
			pokerRank = 4;
		} else if (getRankOccurences(3) == 6) { // three of a kind
			pokerRank = 3;
		} else if (getRankOccurences(2) == 6) { // two pair
			pokerRank = 2;
		} else if (getRankOccurences(2) == 5) { // one pair
			pokerRank = 1;
		} else {
			pokerRank = 0; // high card
		}

	}

	// determines which cards will be discarded (for computer players)
	public void determineDiscards() {
		switch (pokerRank) {
		case 0: // high card
			if (getSuitOccurences(4) == 5) {
				setAllKeep(1, 4, true);
			} else if (getSuitOccurences(4) == 7) {
				setAllKeep(0, 3, true);
			} else if (areSequential(hand.subList(0, 4))) {
				setAllKeep(0, 3, true);
			} else if (areSequential(hand.subList(1, 5))) {
				setAllKeep(1, 4, true);
			} else if (hand.get(0).getRank() == 14) {
				setKeep(0, true);
			} else {
				setAllKeep(0, 1, true);
			}
			break;
		case 1: // one pair
			if (hand.get(0).getRank() == hand.get(1).getRank()) {
				setAllKeep(0, 1, true);
			}
			if (hand.get(1).getRank() == hand.get(2).getRank()) {
				setAllKeep(1, 2, true);
			}
			if (hand.get(2).getRank() == hand.get(3).getRank()) {
				setAllKeep(2, 3, true);
			}
			if (hand.get(3).getRank() == hand.get(4).getRank()) {
				setAllKeep(3, 4, true);
			}
			break;
		case 2: // two pair
			if (hand.get(0).getRank() == hand.get(1).getRank()) {
				setAllKeep(0, 1, true);
				if (hand.get(2).getRank() == hand.get(3).getRank()) {
					setAllKeep(2, 3, true);
				} else {
					setAllKeep(3, 4, true);
				}
			} else {
				setAllKeep(1, 4, true);
			}
			break;
		case 3: // three of a kind
			if (hand.get(0).getRank() == hand.get(2).getRank()) {
				setAllKeep(0, 2, true);
			} else {
				setAllKeep(2, 4, true);
			}
			break;
		case 4: // straight
			setAllKeep(true);
			break;
		case 5: // flush
			setAllKeep(true);
			break;
		case 6: // full house
			setAllKeep(true);
			break;
		case 7: // four of a kind
			if (hand.get(0).getRank() == hand.get(1).getRank()) {
				setAllKeep(0, 3, true);
			}
			break;
		case 8: // straight flush
			setAllKeep(true);
			break;
		}
	}

	// compares the rank of card 1 of increasingly small sublists of hand
	// until sublist size = n. returns # of rank matches in each sublist
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
			if (n == 4 && count == 1) { // to make three of a kind not trigger
										// four of a kind
				count += 2;
			}
		}
		return count;
	}

	// compares the suit of card 1 of increasingly small sublists of hand
	// until sublist size = n. returns # of suit matches in each sublist
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

	// determines if the given list is sequential; IE: 8,7,6,5,4 etc.
	public boolean areSequential(List<Card> cards) {
		int firstRank = cards.get(0).getRank();
		int[] set = new int[cards.size()];
		boolean sequentialAceHigh = true;
		boolean sequentialAceLow = false;

		for (int i = 0; i < cards.size(); i++) {
			set[i] = firstRank - i;
		}

		int j = 0;
		for (Card c : cards) {
			if (c.getRank() != set[j]) {
				sequentialAceHigh = false;
			}
			j++;
		}
		// if hand contains an ace, recheck it with ace as low card
		if (firstRank == 14) {
			sequentialAceLow = true;
			List<Card> handAlt = new ArrayList<Card>(cards);
			Card card = cards.get(0);
			handAlt.remove(card);
			card = new Card(cards.get(0).getIdString());
			card.setRank(1);
			handAlt.add(card);

			firstRank = handAlt.get(0).getRank();
			set = new int[handAlt.size()];

			for (int i = 0; i < handAlt.size(); i++) {
				set[i] = firstRank - i;
			}

			j = 0;
			for (Card c : handAlt) {
				if (c.getRank() != set[j]) {
					sequentialAceLow = false;
				}
				j++;
			}
		}
		// if it is sequential with ace high OR low, return true
		if (sequentialAceLow || sequentialAceHigh) {
			return true;
		} else {
			return false;
		}
	}

	// sets the numerical value of all ranks in the hand
	public void setHandSum() {
		for (Card c : hand) {
			handSum += c.getRank();
		}
	}

	// returns the string representation of the hand
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

	// sorts the hand rank high to low
	public void sortHand() {
		Collections.sort(hand);
	}

	// returns the string representation of the poker value
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

	// adds a card to the hand
	public void add(Card c) {
		hand.add(c);

		// wait until the hand is complete, then sort and determine poker
		if (hand.size() == 5) {
			sortHand();
			determinePoker(); // hand is self-aware
			setHandSum();
		}
	}

	// removes a card from the hand
	public void remove(Card c) {
		hand.remove(c);
	}

	// returns the card in the hand at position x
	public Card get(int x) {
		return hand.get(x);
	}

	// sets whether or not the card at position x will be kept
	public void setKeep(int x, boolean val) {
		Card c = hand.get(x);
		c.setKeep(val);
	}

	// sets whether or not all the cards will be kept
	public void setAllKeep(boolean val) {
		for (Card c : hand) {
			c.setKeep(val);
		}
	}

	// sets whether or not all the cards in the selected range will be kept
	public void setAllKeep(int from, int to, boolean val) {
		List<Card> handAlt = hand.subList(from, to + 1);
		for (Card c : handAlt) {
			c.setKeep(val);
		}
	}

	// returns the hand's poker value
	public int getPokerRank() {
		return pokerRank;
	}

	// sets the hand's poker value
	public void setPokerRank(int pokerRank) {
		this.pokerRank = pokerRank;
	}

	// returns the hand
	public List<Card> getHand() {
		return hand;
	}
	
	// returns the hand sum
	public int getHandSum() {
		return handSum;
	}

}
