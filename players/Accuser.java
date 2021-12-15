package players;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import cards.Card;
import game.CardContainer;

public class Accuser implements Strategie {
	PlayerGroup playerGroup = PlayerGroup.getInstance(0);
	Random randomNumber = new Random();
	public Accuser() {
	}

//	@Override
//	public NextPlayer play(boolean isAccused) {
//		//il va accuser tant qu'il peut un joueur au pif
//		if (isAccused) {
//			return assignedPlayer.revealRole();
//		}
//		else {//ACCUSER
//			List<Player> accusablePlayers=playerGroup.getTarget("accusation",assignedPlayer);
//			int taille = accusablePlayers.size();
//			
//			Player accusedPlayer = accusablePlayers.get(randomNumber.nextInt(taille));
//			return assignedPlayer.accuse(assignedPlayer, accusedPlayer, true);
//		}
//	}
	
	public boolean chooseAction(boolean isAccused,CardContainer playableCards) {
		return true;
	}
	
	@Override
	public String chooseRole() {
		return "Villager";
	}

	@Override
	public Card chooseCard(boolean isAccused,CardContainer playableCards) {
		//joue toujours la première
		return playableCards.getCardByIndex(0);
	}
	
	public Card chooseCardToDiscard(Hand hand,Card exception) {
		LinkedHashSet<Card> discardableCards = new LinkedHashSet<Card>(hand.getCardList());
		discardableCards.remove(exception);
		Iterator<Card> iterator = discardableCards.iterator();
		return iterator.next();
	}

	@Override
	public Player chooseTarget(List<Player> targets) {
		return targets.get(0);
	}
	@Override
	public boolean chooseToReveal(CardContainer playableCards) {//non utile 
		return true;
	}

}
