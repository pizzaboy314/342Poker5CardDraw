/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Game class, containing the user interface and 
 * methods for shuffling Card Piles.
 */
package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class Game {

	public static void main(String[] args) {
		CardPile drawPile = new CardPile(true);
		CardPile discardPile = new CardPile(false);

		UserPlayer player = new UserPlayer("Human Player");
		List<OpponentPlayer> opponents = new ArrayList<OpponentPlayer>();

		Scanner reader = new Scanner(System.in);

		System.out.println("Welcome to 5 Card Draw! Please enter the number of opponents: ");
		int numOpponents = Integer.parseInt(reader.nextLine());

		for (int i = 0; i < numOpponents; i++) {
			OpponentPlayer p = new OpponentPlayer("Computer Player " + (i + 1));
			opponents.add(p);
		}

		System.out.println("The deck is being shuffled.");
		drawPile.shuffleDeck(3);

		System.out.println("The cards are being dealt.\n");
		for (int i = 0; i < 5; i++) {
			Random rand = new Random();
			for(OpponentPlayer p : opponents){
				Card c = drawPile.get(rand.nextInt(drawPile.size() - 1));
				drawPile.remove(c);
				p.addToHand(c);
			}
			Card c = drawPile.get(rand.nextInt(drawPile.size() - 1));
			drawPile.remove(c);
			player.addToHand(c);
		}

		System.out.println("Computer players are discarding unwanted cards.");
		for(OpponentPlayer p : opponents){
			Hand h = p.getHand();
			h.determineDiscards();
			List<Card> cards = h.getHand();
			List<Card> discards = new ArrayList<Card>();

			// System.out.println("Hand: " + h.handAsString(true));
			// System.out.println("Poker: " + h.getPokerString());
			String s = "";

			int i = 0;
			for (Card c : cards) {
				if (c.willKeep() == false) {
					discards.add(c);
					i++;
					s += c.getNameAsString() + " ";
				}
			}
			s = (s.equals("")) ? "None" : s;
			// System.out.println("Will discard: " + s);

			for (Card c : discards) {
				cards.remove(c);
				discardPile.add(c);
			}
			String ending = (i > 1) ? " cards." : " card.";
			System.out.println(p.getName() + " has discarded " + i + ending);
		}

		System.out.println("Your hand: " + player.getHand().handAsString(true));
		System.out.println("Current poker value: " + player.getHand().getPokerString());
		int discardNum = (player.getHand().get(0).getRank() == 14) ? 4 : 3;
		if (discardNum == 4) {
			System.out.println("Your hand contains an Ace, so you may discard 4 cards.");
		}
		System.out.println("Enter the numbers of the cards you would like to discard (separated by a space): ");
		boolean valid = true;
		while (valid) {
			String line = reader.nextLine();
			String[] nums = line.split(" ");
			if (nums.length > discardNum) {
				valid = false;
				System.out.println("You may only enter " + discardNum + "numbers. Try again: ");
			} else {
				valid = true;
				List<Card> discards = new ArrayList<Card>();

				for (String s : nums) {
					Card c = player.getHand().get(Integer.parseInt(s));
					discards.add(c);
				}
				for (Card c : discards) {
					player.removeFromHand(c);
					discardPile.add(c);
				}
				String ending = (nums.length > 1) ? " cards." : " card.";
				System.out.println("You have discarded " + nums.length + ending);
			}
		}

		List<Player> players = new ArrayList<Player>(opponents);
		players.add(player);

		boolean done = false;
		while (done == false) {
			Random rand = new Random();
			for (Player p : players) {
				if (p.getHand().getHand().size() < 5) {
					Card c = drawPile.get(rand.nextInt(drawPile.size() - 1));
					drawPile.remove(c);
					p.addToHand(c);
				}
			} // TODO find way to end loop
		}

	}

}
