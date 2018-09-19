package core;

import java.util.Arrays;

import junit.framework.TestCase;

public class DeckTest extends TestCase{
	
	public void test52cards() {
		Deck deck = new Deck();
		assertEquals(52, deck.numCards());
	}
	
	public void testShuffle() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		deck1 = deck1.shuffle(); //only shuffle the first deck
		String str1 = String.join(",", deck1.cards);
		String str2 = String.join(",", deck2.cards);
		assertNotSame(str1, str2);
	}
	
	public void testDeal() {
		Deck deck = new Deck();
		String card = deck.deal();
		assertFalse(Arrays.asList(deck.cards).contains(card)); //checks if card is removed from deck
		
	}
}
