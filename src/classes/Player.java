package classes;

public class Player implements Comparable {

	private Hand hand;
	private int handRank;
	private int handSum;
	private boolean useSum;
	private String name;

	public Player(String s) {
		hand = new Hand();
		name = s;
	}

	public void refresh() {
		setPokerRank();
		setHandSum();
	}

	public void addToHand(Card c) {
		hand.add(c);
	}

	public void removeFromHand(Card c) {
		hand.remove(c);
	}

	public int getPokerRank() {
		return handRank;
	}

	public void setPokerRank() {
		handRank = hand.getPokerRank();
	}

	public int getHandSum() {
		return handSum;
	}

	public void setHandSum() {
		handSum = hand.getHandSum();
	}

	public boolean isUseSum() {
		return useSum;
	}

	public void setUseSum(boolean useSum) {
		this.useSum = useSum;
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	@Override
	public int compareTo(Object o) {
		Player p = (Player) o;
		int val;
		if (!useSum) {
			val = p.handRank - handRank;
		} else {
			val = p.handSum - handSum;
		}
		return val;
	}
}
