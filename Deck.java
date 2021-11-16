package projetLO02;

import java.util.ListIterator;

public class Deck extends CardContainer{
	
	public void init() { //##a changer quand les cartes seront faites## 
		for (int i = 0; i < 12; i++) { 
			 this.addCard(new Card("carte_"+i));
		 }
	}
	
	
	public void distribute(PlayerGroup group, int cardNumber) {
		ListIterator<Player> playerIterator = group.getIterator();
		while(playerIterator.hasNext()) {
			
			this.giveRandomCard(playerIterator.next().getHand(),cardNumber);
		 }
		
	}
	
	
	
	public static void main(String[] args) {
		Deck a = new Deck();
		Deck b = new Deck();
		a.addCard(new Card("carte_a_0"));
		a.addCard(new Card("carte_a_1"));
		a.addCard(new Card("carte_a_2"));
		b.addCard(new Card("carte_b_0"));
		b.addCard(new Card("carte_b_1"));
		b.addCard(new Card("carte_b_2"));
		
		System.out.println("deck  a : " + a);
		System.out.println("deck  b : " + b);
		a.giveCard(a.getCardByIndex(0), b);
		System.out.println("\naprès échange\n");
		System.out.println("deck  a : " + a);
		System.out.println("deck  b : " + b);
		
		System.out.println("\nMélange de b\n");
		
		b.shuffle();
		System.out.println("deck  b : " + b);
		
		
	}
	
	
	
}
