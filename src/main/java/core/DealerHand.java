package core;

import java.util.LinkedList;
import java.util.List;

public class DealerHand {
	List<String> cards = new LinkedList<String>();
	int value = 0;
	
	public int numCards() {
		return cards.size();
	}
	
	
	public String displayCards() {
		String cardsString;
		if (numCards() == 2) {
			cardsString = cards.get(0);;
			cardsString = "dealer cards: " + cardsString;
			System.out.printf(cardsString);
			System.out.println("\n");
		}
		else { //dealer had 3 cards
			cardsString = String.join(",", cards);
			cardsString = "dealer cards: " + cardsString;
			System.out.printf(cardsString);
			System.out.println("\n");
		}
		return cardsString;
	}
	
	public void hit(String card) {
		cards.add(card);
	}
	
	public void stand() {
		System.out.println("Dealer Stands \n");
	}
}
