package core;

import java.util.LinkedList;
import java.util.List;

public class PlayerHand {
	List<String> cards = new LinkedList<String>();
	
	public int numCards() {
		return cards.size();
	}
	
	
	public String displayCards() {
		String cardsString;
		cardsString = String.join(",", cards);
		cardsString = "player cards: " + cardsString;
		System.out.printf(cardsString);
		System.out.println("\n");
		return cardsString;
	}
	
	public void hit(String card) {
		cards.add(card);
	}
	
	public void stand() {
		System.out.println("Player Stands \n");
	}
}
