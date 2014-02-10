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
		if (areSequential() && getSuitOccurences(5) == 5) { // straight flush
			pokerRank = 8;
		} else if (getRankOccurences(4) == 7) { // four of a kind
			pokerRank = 7;
		} else if (getRankOccurences(2) == 8) { // full house
			pokerRank = 6;
		} else if (getSuitOccurences(5) == 5) { // flush
			pokerRank = 5;
		} else if (areSequential()) { // straight
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

	public void determineDiscards() {
		switch (pokerRank) {
		case 0: // high card

		case 1: // one pair
			if (hand.get(0).getRank() == hand.get(1).getRank()) {
				setAllInPoker(0, 1, true);
			}
			if (hand.get(1).getRank() == hand.get(2).getRank()) {
				setAllInPoker(1, 2, true);
			}
			if (hand.get(2).getRank() == hand.get(3).getRank()) {
				setAllInPoker(2, 3, true);
			}
			if (hand.get(3).getRank() == hand.get(4).getRank()) {
				setAllInPoker(3, 4, true);
			}
		case 2: // two pair
			if (hand.get(0).getRank() == hand.get(1).getRank()) {
				setAllInPoker(0, 1, true);
				if (hand.get(2).getRank() == hand.get(3).getRank()) {
					setAllInPoker(2, 3, true);
				} else {
					setAllInPoker(3, 4, true);
				}
			} else {
				setAllInPoker(1, 4, true);
			}
		case 3: // three of a kind
			if (hand.get(0).getRank() == hand.get(2).getRank()) {
				setAllInPoker(0, 2, true);
			} else {
				setAllInPoker(2, 4, true);
			}
		case 4: // straight
			setAllInPoker(true);
		case 5: // flush
			setAllInPoker(true);
		case 6: // full house
			setAllInPoker(true);
		case 7: // four of a kind
			if (hand.get(0).getRank() == hand.get(1).getRank()) {
				setAllInPoker(0, 3, true);
			}
		case 8: // straight flush
			setAllInPoker(true);
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

	// returns the # of suit matches to the first card's suit
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

	// determines if the hand is sequential; IE: 8,7,6,5,4 etc.
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
		// if hand contains an ace, recheck it with ace as low card
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
			System.out.println(handAsString(true));
			System.out.println(getPokerString());

			String s = "";
			for (Card card : hand) {
				if (card.willKeep() == false) {
					s += card.getNameAsString() + " ";
				}
			}
			System.out.println("Will discard: " + s + "\n");
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
	public void setIsInPoker(int x, boolean val) {
		Card c = hand.get(x);
		c.setKeep(val);
	}

	// sets whether or not all the cards will be kept
	public void setAllInPoker(boolean val) {
		for (Card c : hand) {
			c.setKeep(val);
		}
	}

	// sets whether or not all the cards in the selected range will be kept
	public void setAllInPoker(int from, int to, boolean val) {
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
