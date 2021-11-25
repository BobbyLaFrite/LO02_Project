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
	
	
	

	
	
	
}
