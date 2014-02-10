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
			
		}

	}

}
