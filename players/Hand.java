package players;

import java.util.Iterator;

import cards.Card;
import game.CardContainer;

public class Hand extends CardContainer{
	
	
	public void init() {
		this.cardList.clear();
	}
	
	public CardContainer getPlayableCard(Player actuPlayer,boolean isAccused){//Trois cartes ne peuvent être jouées que dans des conditions spécifiques
		CardContainer playableCard = new CardContainer();
		Card actuCard;
		Iterator<Card> it = this.cardList.iterator();
		 while(it.hasNext()) { 
			 actuCard= it.next();
			 if (actuCard.isPlayable(actuPlayer,isAccused)) {
				 playableCard.addCard(actuCard);
			 }
		 }
		
		return playableCard;
		
	}
	
}
