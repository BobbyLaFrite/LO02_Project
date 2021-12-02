package game;

import java.util.LinkedHashSet;
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
	
	public void init() { 
		this.cardList =  new LinkedHashSet<Card>();
		this.addCard(new AngryMob()); 
		this.addCard(new Toad());
		this.addCard(new HoockedNose());
		this.addCard(new BlackHat());
		this.addCard(new Broomstick());
		this.addCard(new Cauldron());
		this.addCard(new PetNewt());
		this.addCard(new TheInquisition());
			 
		this.addCard(new Toad());
		this.addCard(new Toad());
		this.addCard(new Toad());
		this.addCard(new Toad());
		
	}
	
	
	public void distribute(PlayerGroup group, int cardNumber) {
		ListIterator<Player> playerIterator = group.getIterator();
		while(playerIterator.hasNext()) {
			
			this.giveRandomCard(playerIterator.next().getHand(),cardNumber);
		 }
		
	}
	
	
	
	
	
	
	
}
