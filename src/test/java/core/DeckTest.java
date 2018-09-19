package core;

import java.util.Arrays;

import junit.framework.TestCase;

public class DeckTest extends TestCase{
	
	public void testNumCards() {
		Deck deck = new Deck();
		assertEquals(52, deck.numCards());
	}
	
	public void testShuffle() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		deck1.shuffle(); //only shuffle the first deck
		String str1 = String.join(",", deck1.cards);
		String str2 = String.join(",", deck2.cards);
		System.out.printf(str1);
		System.out.println("\n");;
		System.out.printf(str2);
		assertNotSame(str1, str2);
	}
	
	public void testDeal() {
		Deck deck = new Deck();
		String card = deck.deal();
		int numCards = deck.numCards();
		assertFalse(deck.cards.contains(card)); //checks if card is removed from deck
		assertEquals(51, numCards); //checks number of cards left
		
	}
}
