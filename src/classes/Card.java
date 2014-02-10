/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Card class, containing attributes for every instance of type Card.
 */
package classes;

public class Card implements Comparable {

	private char[] id;
	private int rank;
	private int suit;
	private boolean keep;

	// in = 2-char ID in string form
	public Card(String in) {
		id = new char[2];
		id[0] = in.toUpperCase().charAt(0);
		id[1] = in.toUpperCase().charAt(1);
		setRanks();
		getSuitString();
		keep = false;
	}

	// returns the string representation of the rank
	public String getRankString() {
		char c = id[0];
		String rank;
		
		switch(c){
		case '2':
			rank = "Two";
			break;
		case '3':
			rank = "Three";
			break;
		case '4':
			rank = "Four";
			break;
		case '5':
			rank = "Five";
			break;
		case '6':
			rank = "Six";
			break;
		case '7':
			rank = "Seven";
			break;
		case '8':
			rank = "Eight";
			break;
		case '9':
			rank = "Nine";
			break;
		case 'T':
			rank = "Ten";
			break;
		case 'J':
			rank = "Jack";
			break;
		case 'Q':
			rank = "Queen";
			break;
		case 'K':
			rank = "King";
			break;
		case 'A':
			rank = "Ace";
			break;
		default:
			rank = "";
		}
		
		return rank;
	}

	// returns the string representation of the suit
	public String getSuitString() {
		char c = id[1];
		String suit;

		switch (c) {
		case 'C':
			suit = "Clubs";
			setSuit(1);
			break;
		case 'D':
			suit = "Diamonds";
			setSuit(2);
			break;
		case 'H':
			suit = "Hearts";
			setSuit(3);
			break;
		case 'S':
			suit = "Spades";
			setSuit(4);
			break;
		default:
			suit = "";
		}

		return suit;
	}

	// returns the string representation of the card
	public String getNameAsString() {
		String rank = getRankString();
		String suit = getSuitString();
		return rank + " of " + suit;
	}

	// returns the card's 2-char ID
	public char[] getId() {
		return id;
	}

	// returns the string representation of the card's 2-char ID
	public String getIdString() {
		return "" + id[0] + id[1];
	}

	// returns the value of the card's suit
	public int getSuit() {
		return suit;
	}

	// sets the value of the card's suit
	public void setSuit(int suit) {
		this.suit = suit;
	}

	// returns the value of the card's rank
	public int getRank() {
		return rank;
	}

	// sets the value of the card's rank
	public void setRank(int rank) {
		this.rank = rank;
	}

	// determines the rank of the card based on its ID
	public void setRanks() {
		char c = id[0];

		switch (c) {
		case '2':
			rank = 2;
			break;
		case '3':
			rank = 3;
			break;
		case '4':
			rank = 4;
			break;
		case '5':
			rank = 5;
			break;
		case '6':
			rank = 6;
			break;
		case '7':
			rank = 7;
			break;
		case '8':
			rank = 8;
			break;
		case '9':
			rank = 9;
			break;
		case 'T':
			rank = 10;
			break;
		case 'J':
			rank = 11;
			break;
		case 'Q':
			rank = 12;
			break;
		case 'K':
			rank = 13;
			break;
		case 'A':
			rank = 14;
			break;
		default:
			rank = 0;
		}
	}

	// returns whether or not this card will be kept
	public boolean willKeep() {
		return keep;
	}

	// sets whether or not this card will be kept
	public void setKeep(boolean keep) {
		this.keep = keep;
	}

	// Comparator object implementation to make Card sortable by rank
	@Override
	public int compareTo(Object o) {
		Card c = (Card) o;
		return c.rank - rank;
	}

}
