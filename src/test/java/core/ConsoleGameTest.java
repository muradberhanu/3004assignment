package core;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

public class ConsoleGameTest extends TestCase{
	
	
	public void testInitialDisplayCards() {
		ConsoleGame game = new ConsoleGame();
		DealerHand hand = game.dealer;
		PlayerHand hand2 = game.player;
		hand.cards.add("H2");
		hand.cards.add("SA");
		hand2.cards.add("D9");
		hand2.cards.add("C7");
		assertEquals("player cards: D9,C7\ndealer cards: H2\n", game.initialDisplayCards());
	}
	
	public void testDisplayPlayerCards() { //at end of turn
		ConsoleGame game = new ConsoleGame();
		PlayerHand hand = game.player;
		hand.cards.add("D9");
		hand.cards.add("C7");
		assertEquals("player cards: D9,C7", game.displayPlayerCards()); 
		hand.cards.add("SK");
		assertEquals("player cards: D9,C7,SK", game.displayPlayerCards()); 
	}
	
	public void testDisplayDealerCards() { //at end of turn
		ConsoleGame game = new ConsoleGame();
		DealerHand hand = game.dealer;
		hand.cards.add("H2");
		hand.cards.add("SA");
		assertEquals("dealer cards: H2,SA", game.displayDealerCards()); 
		hand.cards.add("H9");
		assertEquals("dealer cards: H2,SA,H9", game.displayDealerCards());
	}
	
	public void testCardValues() {
		ConsoleGame game = new ConsoleGame();
		PlayerHand hand = game.player;
		
		hand.cards.add("H2");
		hand.cards.add("S5");
		game.cardValues();
		assertEquals(7, hand.value); 
		game.player.cards = new LinkedList<String>();
		
		hand.cards.add("CK");
		hand.cards.add("SQ");
		game.cardValues();
		assertEquals(20, hand.value); 
		game.player.cards = new LinkedList<String>();
		
		hand.cards.add("H2");
		hand.cards.add("SK");
		game.cardValues();
		assertEquals(12, hand.value); 
		game.player.cards = new LinkedList<String>();
		
		hand.cards.add("H2");
		hand.cards.add("SK");
		hand.cards.add("C6");
		game.cardValues();
		assertEquals(18, hand.value); 
		game.player.cards = new LinkedList<String>();
		
		hand.cards.add("HQ");
		hand.cards.add("SK");
		hand.cards.add("SA");
		game.cardValues();
		assertEquals(21, hand.value); 
		game.player.cards = new LinkedList<String>();
		
		hand.cards.add("HA");
		hand.cards.add("SK");
		game.cardValues();
		assertEquals(21, hand.value); 
		game.player.cards = new LinkedList<String>();
		
	}
	
	public void testBust() {
		DealerHand hand = new DealerHand();
		PlayerHand hand2 = new PlayerHand();
		
		/////////////////
		/////////////////
		/////////////////
		
		assertTrue(hand.value > 21);
		assertTrue(hand2.value > 21);
	}
	
	public void testWinner() {
		DealerHand hand = new DealerHand();
		PlayerHand hand2 = new PlayerHand();
		//case where one wins right away - has 2 cards that equal 21
		//case where both win right away - has 2 cards that equal 21
		//case where one wins with 3 cards
	}
	
	public void testPlayerTurn() {
		
	}
	
	public void testDealerTurn() {
		
	}
}
