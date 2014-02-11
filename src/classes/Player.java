/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Player class, containing an generic player's hand and 
 * abstracted methods for interacting with the hand attributes.
 */

package classes;

public class Player implements Comparable<Player> {

	private Hand hand;
	private int handRank;
	private String name;

	public Player(String s) { // s = name of player
		hand = new Hand();
		name = s;
	}

	// updates the hand rank of this player
	public void refresh() {
		setPokerRank();
	}

	// adds a card to this players hand
	public void addToHand(Card c) {
		hand.add(c);
	}

	// removes a card from this players hand
	public void removeFromHand(Card c) {
		hand.remove(c);
	}

	// returns this player's hand rank
	public int getPokerRank() {
		return hand.getPokerRank();
	}

	// updates this player's hand rank from their hand
	public void setPokerRank() {
		handRank = hand.getPokerRank();
	}

	// returns the numerical sum of all the cards in this player's hand
	public int getHandSum() {
		return hand.getHandSum();
	}

	// returns the name of this player
	public String getName() {
		return name;
	}

	// returns this player's hand
	public Hand getHand() {
		return hand;
	}

	// Comparator object implementation to make Player sortable by rank
	@Override
	public int compareTo(Player p) {
		return p.handRank - handRank;
	}
}
