package game;

import java.util.ListIterator;
import cards.*;
import players.Player;
import players.PlayerGroup;

public class Deck extends CardContainer{
	
	static Deck instance;
	
	private Deck() {
		
		
	}
	
	public static Deck getInstance() {
		if (instance==null) {
			instance= new Deck();
		}
		return instance;
	}
	
	public void init() { //##a changer quand les cartes seront faites## 
		for (int i = 0; i < 12; i++) { 
			 this.addCard(new AngryMob()); //##on remplit le deck d'une meme carte pour l'instant
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
		//on a plus besoin de mélanger le deck, on donnera une carte de manière aléatoire ce qui reviens au même
		
		
	}
	
	
	
}
