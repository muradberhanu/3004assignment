package core;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
	
	List<String> cards = new LinkedList<String>(Arrays.asList("S2", "H2", "C2", "D2", 
	                  "S3", "H3", "C3", "D3", 
	                  "S4", "H4", "C4", "D4", 
	                  "S5", "H5", "C5", "D5", 
	                  "S6", "H6", "C6", "D6", 
	                  "S7", "H7", "C7", "D7", 
	                  "S8", "H8", "C8", "D8", 
	                  "S9", "H9", "C9", "D9", 
	                  "S10", "H10", "C10", "D10", 
	                  "SJ", "HJ", "CJ", "DJ", 
	                  "SQ", "HQ", "CQ", "DQ", 
	                  "SK", "HK", "CK", "DK", 
	                  "SA", "HA", "CA", "DA"));
	
	public int numCards() {
		return cards.size();
	}
	public void shuffle() {
		 Collections.shuffle(cards);
	}
	
	public String deal() {
		String card;
		card = cards.get(0); //deals the first card in the deck
		cards.remove(0);
		return card;
	}
}
