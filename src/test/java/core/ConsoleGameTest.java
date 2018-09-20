package core;

import java.util.LinkedList;
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
		
		hand.cards.add("H10"); //testing card with value 10 since it has 2 digits instead of 1
		hand.cards.add("S5");
		game.cardValues();
		assertEquals(15, hand.value); 
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
		
		hand.cards.add("HA");  //test case for 2 aces in one hand - first is 11 second is 1
		game.cardValues();
		assertEquals(11, hand.value); 
		hand.cards.add("SA");
		game.cardValues();
		assertEquals(12, hand.value); 		
		game.player.cards = new LinkedList<String>();

		hand.cards.add("H9");
		hand.cards.add("SK");
		hand.cards.add("SA");
		hand.cards.add("DA"); //two aces - both count as 1
		game.cardValues();
		assertEquals(21, hand.value); 
	}
	
	public void testPlayerBust() {
		ConsoleGame game = new ConsoleGame();
		PlayerHand hand = game.player;
		hand.cards.add("HQ");
		hand.cards.add("SK");
		hand.cards.add("D7");
		assertEquals(true, game.playerBust());
	}
	public void testDealerBust() {
		ConsoleGame game = new ConsoleGame();
		DealerHand hand = game.dealer;
		hand.cards.add("HQ");
		hand.cards.add("SK");
		hand.cards.add("D7");
		assertEquals(true, game.dealerBust());
	}
	
	public void testWinner() {
		ConsoleGame game = new ConsoleGame();
		DealerHand dealer = game.dealer;
		PlayerHand player = game.player;
		
		//case where both have blackjack right away - dealer wins
		dealer.cards.add("HQ");
		dealer.cards.add("DA");
		player.cards.add("SK");
		player.cards.add("SA");
		assertEquals(true, game.winner());
		
		game.player.cards = new LinkedList<String>();
		game.dealer.cards = new LinkedList<String>();
		
		//case where dealer wins with blackjack
		dealer.cards.add("HQ");
		dealer.cards.add("DA");
		player.cards.add("SK");
		player.cards.add("S2");
		assertEquals(true, game.winner());
		
		game.player.cards = new LinkedList<String>();
		game.dealer.cards = new LinkedList<String>();
		
		//case where player wins with blackjack
		dealer.cards.add("HQ");
		dealer.cards.add("D5");
		player.cards.add("SK");
		player.cards.add("SA");
		assertEquals(true, game.winner());
		
		game.player.cards = new LinkedList<String>();
		game.dealer.cards = new LinkedList<String>();
		
		//case where dealer wins with 3 cards
		dealer.cards.add("HQ");
		dealer.cards.add("HK");
		dealer.cards.add("DA");
		player.cards.add("SK");
		player.cards.add("S2");
		assertEquals(true, game.winner());
		
		game.player.cards = new LinkedList<String>();
		game.dealer.cards = new LinkedList<String>();
	}
	
	
	public void testDealerTurn() {
		ConsoleGame game = new ConsoleGame();
		DealerHand dealer = game.dealer;
		
		dealer.cards.add("HQ");
		dealer.cards.add("HK"); //total value == 20
		game.dealerTurn();
		assertEquals(20, game.dealer.value); // making sure the value of their hand doesn't change (stand)
		game.dealer.cards = new LinkedList<String>();
		
		dealer.cards.add("HA");
		dealer.cards.add("H8"); //total value == soft 17
		game.dealerTurn();
		assertTrue(game.dealer.numCards()>2); // making sure the computer hits with a soft 17
		game.dealer.cards = new LinkedList<String>();
		
		dealer.cards.add("HK");
		dealer.cards.add("H7"); //total value == hard 17
		game.dealerTurn();
		assertEquals(17, game.dealer.value); // making sure the value of their hand doesn't change (stand)
		game.dealer.cards = new LinkedList<String>();
		
		dealer.cards.add("HK");
		dealer.cards.add("C6"); //total value == 16
		game.dealerTurn();
		assertTrue(game.dealer.numCards()>2);; // making sure the dealer hits
		game.dealer.cards = new LinkedList<String>();
		
		dealer.cards.add("H5");
		dealer.cards.add("C6"); //total value < 16
		game.dealerTurn();
		assertTrue(game.dealer.numCards()>2);; // making sure the dealer hits
		game.dealer.cards = new LinkedList<String>();
	}
	
	public void testFinalWinner() {
		ConsoleGame game = new ConsoleGame();
		DealerHand dealer = game.dealer;
		PlayerHand player = game.player;
		player.cards.add("C7");
		player.cards.add("D3");
		dealer.cards.add("S7");
		dealer.cards.add("CK");
		assertEquals("Dealer", game.finalWinner());
		
		game.player.cards = new LinkedList<String>();
		game.dealer.cards = new LinkedList<String>();
		
		dealer.cards.add("C7");
		dealer.cards.add("D3");
		player.cards.add("S7");
		player.cards.add("CK");
		assertEquals("Player", game.finalWinner());
	}
}
