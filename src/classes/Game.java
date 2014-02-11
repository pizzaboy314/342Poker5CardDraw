/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Game class, containing the user interface and 
 * methods for shuffling Card Piles.
 */
package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class Game {

	public static void main(String[] args) {
		CardPile drawPile = new CardPile(true);
		CardPile discardPile = new CardPile(false);

		// create human player
		UserPlayer player = new UserPlayer("Human Player");
		List<OpponentPlayer> opponents = new ArrayList<OpponentPlayer>();

		Scanner reader = new Scanner(System.in);

		System.out.println("Welcome to 5 Card Draw! Please enter the number of opponents: ");
		int numOpponents = 0;

		// read in number of opponents, must be 9 or less
		boolean valid = false;
		while (valid == false) {
			numOpponents = Integer.parseInt(reader.nextLine());
			if (numOpponents > 9) {
				valid = false;
				System.out.println("Please enter a number less than 10: ");
			} else {
				valid = true;
			}
		}

		// create opponents
		for (int i = 0; i < numOpponents; i++) {
			OpponentPlayer p = new OpponentPlayer("Computer Player " + (i + 1));
			opponents.add(p);
		}

		// shuffle the deck
		System.out.println("The deck is being shuffled.");
		drawPile.shuffleDeck(3);

		// deal 5 cards to everyone, one player at a time
		System.out.println("The cards are being dealt.\n");
		for (int i = 0; i < 5; i++) {
			Random rand = new Random();
			for(OpponentPlayer p : opponents){
				Card c = drawPile.get(rand.nextInt(drawPile.size()));
				drawPile.remove(c);
				p.addToHand(c);
			}
			Card c = drawPile.get(rand.nextInt(drawPile.size()));
			drawPile.remove(c);
			player.addToHand(c);
		}

		// perform predetermined computer player discards
		System.out.println("Computer players are discarding unwanted cards...");
		for(OpponentPlayer p : opponents){
			Hand h = p.getHand();
			h.determineDiscards();
			List<Card> cards = h.getHand();
			List<Card> discards = new ArrayList<Card>();

			
			// for testing purposes to see hands BEFORE discard
			//
			// System.out.println("Hand: " + h.handAsString(true)); 
			// System.out.println("Poker: " + h.getPokerString());
			// String s = "";

			int i = 0;
			for (Card c : cards) {
				if (c.willKeep() == false) {
					discards.add(c);
					i++;
					// s += c.getNameAsString() + " ";
				}
			}
			// s = (s.equals("")) ? "None" : s;
			// System.out.println("Will discard: " + s);

			for (Card c : discards) {
				cards.remove(c);
				discardPile.add(c);
			}
			String ending = (i > 1) ? " cards." : " card.";
			System.out.println(p.getName() + " has discarded " + i + ending);
		}
		System.out.println();

		// show human player's hand
		System.out.println("Your hand: " + player.getHand().handAsString(true));
		System.out.println("Current poker value: " + player.getHand().getPokerString());
		int discardNum = (player.getHand().get(0).getRank() == 14) ? 4 : 3;
		if (discardNum == 4) {
			System.out.println("Your hand contains an Ace, so you may discard 4 cards.");
		}
		System.out.println("Enter the numbers of the cards you would like to discard (separated by a space): ");

		// make sure only 3 numbers are entered, or 4 if hand contains an ace
		// then perform indicated discards
		valid = false;
		while (valid == false) {
			String line = reader.nextLine();
			String[] nums = line.split(" ");
			if (nums.length > discardNum) {
				valid = false;
				System.out.println("You may only enter " + discardNum + "numbers. Try again: ");
			} else {
				valid = true;
				List<Card> discards = new ArrayList<Card>();

				for (String s : nums) {
					int x = Integer.parseInt(s) - 1;
					Card c = player.getHand().get(x);
					discards.add(c);
				}
				for (Card c : discards) {
					player.removeFromHand(c);
					discardPile.add(c);
				}
				String ending = (nums.length > 1) ? " cards.\n" : " card.\n";
				System.out.println("You have discarded " + nums.length + ending);
			}
		}
		reader.close();

		// create list of all players, 1 human + multiple computer
		List<Player> players = new ArrayList<Player>(opponents);
		players.add(player);

		// loop through all players and refill their depleted hands with cards
		// from draw pile
		// if draw pile gets emptied, switch to drawing from discard pile
		boolean done = false;
		while (done == false) {
			Random rand = new Random();
			int sum = 0;
			for (Player p : players) {
				if (p.getHand().getHand().size() < 5) {
					Card c;
					if (drawPile.size() > 0) {
						c = drawPile.get(rand.nextInt(drawPile.size()));
					} else {
						c = discardPile.get(rand.nextInt(discardPile.size()));

					}
					drawPile.remove(c);
					p.addToHand(c);
				} else {
					p.getHand().sortHand();
					p.getHand().determinePoker();
					p.getHand().setHandSum();
					p.refresh();
				}
				sum += p.getHand().getHand().size();
			}
			if (sum >= 5 * players.size()) {
				done = true;
			}
		}

		// sort list of players by poker rank descending
		Collections.sort(players);

		// if there are multiple players with same rank, find player with
		// highest rank AND highest numerical card sum
		Player winner = players.get(0);
		for (Player p : players) {
			if (p.getPokerRank() == winner.getPokerRank() && p.getHandSum() > winner.getHandSum()) {
				winner = p;
			}
			System.out.println("Name: " + p.getName());
			System.out.println("Hand: " + p.getHand().handAsString(true));
			System.out.println("Poker: " + p.getHand().getPokerString());
			System.out.println("Hand Sum: " + p.getHandSum());
			System.out.println();
		}

		// display winner and exit messages
		System.out.println("The winner is " + winner.getName().toUpperCase() + " with poker: "
				+ winner.getHand().getPokerString().toUpperCase() + ".");
		System.out.println("Thanks for playing!");
	}

}
