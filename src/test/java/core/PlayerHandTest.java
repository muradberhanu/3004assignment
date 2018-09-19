package core;

import junit.framework.TestCase;

public class PlayerHandTest extends TestCase{

	public void testNumCards() {
		Deck deck = new Deck();
		PlayerHand hand = new PlayerHand();
		hand.cards.add(deck.deal());
		hand.cards.add(deck.deal());
		assertEquals(2, hand.numCards());
	}

	public void testDisplayCards() {
		PlayerHand hand = new PlayerHand();
		hand.cards.add("H2");
		hand.cards.add("SA");
		assertEquals("player cards: H2,SA", hand.displayCards());
	}
	
	public void testHit() {
		PlayerHand hand = new PlayerHand();
		Deck deck = new Deck();
		hand.hit(deck.deal());
		assertEquals(1, hand.cards.size());
	}
	
	public void testStand() {
		PlayerHand hand = new PlayerHand();
		Deck deck = new Deck();
		hand.hit(deck.deal());
		hand.hit(deck.deal());
		hand.stand();
		assertEquals(2, hand.cards.size());
	}
	
	
}
