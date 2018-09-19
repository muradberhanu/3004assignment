package core;

import junit.framework.TestCase;

public class DealerHandTest extends TestCase{

	public void testNumCards() {
		Deck deck = new Deck();
		DealerHand hand = new DealerHand();
		hand.cards.add(deck.deal());
		hand.cards.add(deck.deal());
		assertEquals(2, hand.numCards());
	}

	public void testDisplayCards() {
		DealerHand hand = new DealerHand();
		hand.cards.add("H2");
		hand.cards.add("SA");
		assertEquals("dealer cards: H2", hand.displayCards());
		hand.cards.add("D9");
		assertEquals("dealer cards: H2,SA,D9", hand.displayCards());//making sure multiple cards can show
	}
	
	public void testHit() {
		DealerHand hand = new DealerHand();
		Deck deck = new Deck();
		hand.hit(deck.deal());
		assertEquals(1, hand.cards.size());
	}
	
	public void testStand() {
		DealerHand hand = new DealerHand();
		Deck deck = new Deck();
		hand.hit(deck.deal());
		hand.hit(deck.deal());
		hand.stand();
		assertEquals(2, hand.cards.size());
	}
	
}
