package players;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import cards.Card;
import game.CardContainer;

public class WitchStrategy implements Strategie {
	Random randomNumber = new Random();
	
	public WitchStrategy() {
	}

//	@Override
//	public NextPlayer play(boolean isAccused) {
//		if (isAccused) {
//			if ( !assignedPlayer.getHand().isEmpty()) {
//				Card cardToBePlayed = chooseCard(isAccused);
//				NextPlayer nextPlayer = assignedPlayer.playCard(cardToBePlayed, true);
//				return nextPlayer;
//			}
//			else {
//				return assignedPlayer.revealRole();
//			}
//		}
//		else {
//			if ( !assignedPlayer.getHand().getPlayableCard(assignedPlayer, isAccused).isEmpty() ) {
//				//play card si la main est pas vide
//				Card cardToBePlayed = chooseCard(isAccused);
//				NextPlayer nextPlayer = assignedPlayer.playCard(cardToBePlayed, false);
//				return nextPlayer;
//			}
//			else {//sinon accuse un joueur aléatoirement
//				List<Player> accusablePlayers=playerGroup.getTarget("accusation",assignedPlayer);
//				int taille = accusablePlayers.size();
//				
//				Player accusedPlayer = accusablePlayers.get(randomNumber.nextInt(taille));
//				return assignedPlayer.accuse(assignedPlayer, accusedPlayer, true);
//			}
//		}
//	}
	
	public boolean chooseAction(boolean isAccused,CardContainer playableCards) {
		if (isAccused) {
				if(playableCards.isEmpty()) {
					return true;
				}
				else {
					return false;
				}
			}
		else {
			if(playableCards.isEmpty()) {
				return true;
			}
			else {
				return false;
			}
			
		}	
	}
	
	@Override
	public String chooseRole() {
		return "Witch";

	}

	@Override
	public Card chooseCard(boolean isAccused,CardContainer playableCards) {
		//play card randomly
		int handSize = playableCards.getNumberCard();
		if (handSize>1) {
			return playableCards.getCardByIndex(randomNumber.nextInt(handSize));
		}else {
			return playableCards.getCardByIndex(0);
		}
		
	}
	
	public Card chooseCardToDiscard(Hand hand, Card exception) {
		LinkedHashSet<Card> discardableCards = new LinkedHashSet<Card>(hand.getCardList());
		discardableCards.remove(exception);
		Iterator<Card> iterator = discardableCards.iterator();
		return iterator.next();
	}

	@Override
	public Player chooseTarget(List<Player> targets) {
		//inutile je crois sauf pour la start humain
		return targets.get(0);
	}
	
	public boolean chooseToReveal(CardContainer playableCards) {
		if ((playableCards.isEmpty())) {
			return true;
		}
		else {
			return false;
		}
	}

}
