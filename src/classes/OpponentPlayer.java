/**
 * Bryan Spahr and Khurratul-Ain Naseer
 * 
 * Opponent Player class, containing an opponent's hand and 
 * methods for interacting with the AI algorithms.
 */
package classes;

public class OpponentPlayer extends Player {

	private int discarded;

	public OpponentPlayer(String s) {
		super(s);
		discarded = 0;
	}

	public int getDiscarded() {
		return discarded;
	}
}
