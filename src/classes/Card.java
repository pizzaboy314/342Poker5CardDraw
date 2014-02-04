/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Card class, containing attributes for every instance of type Card.
 */
package classes;

public class Card implements Comparable {
	public char[] id;

	public int rank;
	public int suit;

	public Card(String in) {
		id = new char[2];
		id[0] = in.toUpperCase().charAt(0);
		id[1] = in.toUpperCase().charAt(1);
		setRank();
		getSuitString();
	}

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

	public String getNameAsString() {
		String rank = getRankString();
		String suit = getSuitString();
		return rank + " of " + suit;
	}

	public char[] getId() {
		return id;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public void setRank() {
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

	@Override
	public int compareTo(Object o) {
		Card c = (Card)o;
		return c.rank - rank;
	}
}
