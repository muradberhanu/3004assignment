package core;

public class ConsoleGame {
	
	PlayerHand player = new PlayerHand();
	DealerHand dealer = new DealerHand();
	Deck deck = new Deck();
	
	
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
			if(Character.isDigit(cardNum)) {
				player.value += Character.getNumericValue(cardNum);
			}
			else if(cardNum == 'K' || cardNum == 'Q' ||cardNum == 'J') {
				player.value += 10;
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
			return true;
		}
		else {
			return false;
		}
	}
	public boolean dealerBust() {
		cardValues();
		if(dealer.value >21) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean winner() {
		cardValues();
		if((player.value == 21) && (dealer.value == 21)){
			System.out.println("Both player and dealer have blackjack - Dealer wins");
			return true;
		}
		else if(dealer.value == 21){
			System.out.println("Dealer has blackjack - Dealer wins");
			return true;
		}
		else if(player.value == 21){
			System.out.println("Player has blackjack - Player wins");
			return true;
		}
		else {
			return false;
		}
	}
	
	public void playerTurn() {
		
	}
	
	public void dealerTurn() {
		
	}
	
	public void main() {
		dealCards();
		initialDisplayCards();
		winner();
//		displayPlayerCards();
//		displayDealerCards();
	}

}
