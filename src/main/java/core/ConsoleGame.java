package core;

import java.util.Scanner;

public class ConsoleGame {
	
	PlayerHand player = new PlayerHand();
	DealerHand dealer = new DealerHand();
	Deck deck = new Deck();
	boolean gameHasWinner = false;//used in main loop to see if the game has a winner
	
	
	public void dealCards() {
		deck.shuffle();
		player.hit(deck.deal());
		player.hit(deck.deal());
		dealer.hit(deck.deal());
		dealer.hit(deck.deal());
	}
	
	public String initialDisplayCards() {
		String display = "";
		display += player.displayCards();
		display += "\n";
		display += dealer.displayCards();
		display += "\n";
		return display;
	}
	
	public String displayPlayerCards() { //at end of turn
		String display = "";
		display += player.displayCards();
		return display;
	}
	
	public String displayDealerCards() { //at end of turn
		String cardsString;
		cardsString = String.join(",", dealer.cards);
		cardsString = "dealer cards: " + cardsString;
		System.out.printf(cardsString);
		System.out.println("\n");
		return cardsString;
	}
	
	public void cardValues() {
		player.value = 0;
		dealer.value = 0;
		for(int i=0; i<player.numCards(); i++) {
			String currentCard = player.cards.get(i);
			char cardNum = currentCard.charAt(1);
			if(cardNum == '1' || cardNum == 'K' || cardNum == 'Q' ||cardNum == 'J') {
				//includes '1' since cards with a value of 10 would be the only cards with a '1'
				player.value += 10;
			}
			else if(Character.isDigit(cardNum)) {
				player.value += Character.getNumericValue(cardNum);
			}
			else if(cardNum == 'A') {
				if((player.value + 11) > 21) {
					player.value += 1;
				}
				else {
					player.value += 11;
				}
			}
		}
		for(int i=0; i<dealer.numCards(); i++) {
			String currentCard = dealer.cards.get(i);
			char cardNum = currentCard.charAt(1);
			if(Character.isDigit(cardNum)) {
				dealer.value += Character.getNumericValue(cardNum);
			}
			else if(cardNum == 'K' || cardNum == 'Q' ||cardNum == 'J') {
				dealer.value += 10;
			}
			else if(cardNum == 'A') {
				if((dealer.value + 11) > 21) {
					dealer.value += 1;
				}
				else {
					dealer.value += 11;
				}
			}
		}
	}
	
	public boolean playerBust() {
		cardValues();
		if(player.value >21) {
			System.out.println("Player busts - Dealer wins");
			gameHasWinner = true;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean dealerBust() {
		cardValues();
		if(dealer.value >21) {
			System.out.println("Dealer busts - Player wins");
			gameHasWinner = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean winner() {
		cardValues();
		if((player.value == 21) && (dealer.value == 21)){
			gameHasWinner = true;
			System.out.println("Both player and dealer have blackjack - Dealer wins");
			return true;
		}
		else if(dealer.value == 21){
			gameHasWinner = true;
			System.out.println("Dealer has blackjack - Dealer wins");
			return true;
		}
		else if(player.value == 21){
			gameHasWinner = true;
			System.out.println("Player has blackjack - Player wins");
			return true;
		}
		else {
			return false;
		}
	}
	
	public void playerTurn() {
		Scanner scan = new Scanner(System.in);
		while(gameHasWinner == false) {
			System.out.println("Please select either hit (h) or stand (s) input: ");
			String s = scan.next();
			System.out.printf("You chose: %s \n", s);
			if(s.equals("h")) {
				System.out.println("Player Hits \n");
				player.hit(deck.deal());
				player.displayCards();
				cardValues();
				System.out.println("Value: ");
				System.out.println(player.value);
				System.out.println("\n");
				playerBust();
				winner();
			}
			else if(s.equals("s")) {
				player.stand();
				player.displayCards();
				return;
			}
		}
	}
	
	public void dealerTurn() {
		displayDealerCards();
		cardValues();
		while(gameHasWinner == false) {
			if(dealer.value<=16) {
				System.out.println("Dealer Hits \n");
				dealer.hit(deck.deal());
				dealer.displayCards();
				cardValues();
				System.out.println("Value: ");
				System.out.println(dealer.value);
				System.out.println("\n");
				dealerBust();
				winner();
			}
			else if(dealer.value==17) {	
				if(dealer.cards.contains("SA") || dealer.cards.contains("CA") ||dealer.cards.contains("DA") ||dealer.cards.contains("HA")) {
					dealer.hit(deck.deal());
					dealer.displayCards();
					cardValues();
					System.out.println("Value: ");
					System.out.println(dealer.value);
					System.out.println("\n");
					dealerBust();
					winner();
				}
				else {
					dealer.stand();
					displayDealerCards();
					return;
				}	
			}
			else {
				dealer.stand();
				displayDealerCards();
				return;
			}
		}
		
	}
	
	public String finalWinner() {
		String winnerName = "";
		cardValues();
		if(player.value > dealer.value) {
			System.out.println("Player Wins");
			System.out.printf("Player value: %d, Dealer value: %d", player.value, dealer.value);
			winnerName = "Player";
		}
		else if(dealer.value >= player.value) {
			System.out.println("Dealer Wins");
			System.out.printf("Player value: %d, Dealer value: %d", player.value, dealer.value);
			winnerName = "Dealer";
		}
		return winnerName;
	}
	
	public void main() {
		dealCards();
		initialDisplayCards();
		winner();
		if(gameHasWinner == false) {
			System.out.println("Player's Turn \n");
			playerTurn();
		}
		if(gameHasWinner == false) {
			System.out.println("Dealer's Turn \n");
			dealerTurn();
		}
		if(gameHasWinner == false) {
			finalWinner();
		}
	}

}
