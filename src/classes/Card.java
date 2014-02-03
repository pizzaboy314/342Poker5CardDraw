/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Card class, containing attributes for every instance of type Card.
 */
package classes;

public class Card {
	private static char[] id;

	private static int rank;

	public Card(String in) {
		id = new char[2];
		id[0] = in.toUpperCase().charAt(0);
		id[1] = in.toUpperCase().charAt(1);
		setRank();
	}

	public static String getRankString() {
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

	public static String getSuitString() {
		char c = id[1];
		String suit;

		switch (c) {
		case 'C':
			suit = "Clubs";
			break;
		case 'D':
			suit = "Diamonds";
			break;
		case 'H':
			suit = "Hearts";
			break;
		case 'S':
			suit = "Spades";
			break;
		default:
			suit = "";
		}

		return suit;
	}

	public static String getNameAsString() {
		String rank = getRankString();
		String suit = getSuitString();
		return rank + " of " + suit;
	}

	public static char[] getId() {
		return id;
	}

	public static int getRank() {
		return rank;
	}

	public static void setRank() {
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
}
