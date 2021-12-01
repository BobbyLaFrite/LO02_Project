package players;

import java.util.List;
import java.util.Random;

import cards.Card;
import game.CardContainer;

public class WitchStrategy implements Strategie {
	Player assignedPlayer;
	Random randomNumber = new Random();
	
	public WitchStrategy(Player assignedPlayer) {
		//on a besoin de savoir quel joueur est associé à la stratégie
		this.assignedPlayer=assignedPlayer;
	}

	@Override
	public NextPlayer play(boolean isAccused) {
		// TODO Auto-generated method stub
		if (isAccused) {
			if ( !assignedPlayer.getHand().isEmpty()) {
				Card cardToBePlayed = chooseCard(isAccused);
				NextPlayer nextPlayer = assignedPlayer.playCard(cardToBePlayed, true);
				return nextPlayer;
			}
			else {
				return assignedPlayer.revealRole();
			}
		}
		else {
			if ( !assignedPlayer.getHand().isEmpty() ) {
				//play card si la main est pas vide
				Card cardToBePlayed = chooseCard(isAccused);
				NextPlayer nextPlayer = assignedPlayer.playCard(cardToBePlayed, false);
				return nextPlayer;
			}
			else {//sinon accuse un joueur aléatoirement
				List<Player> accusablePlayers=playerGroup.getTarget("accusation",assignedPlayer);
				int taille = accusablePlayers.size();
				
				Player accusedPlayer = accusablePlayers.get(randomNumber.nextInt(taille));
				return assignedPlayer.accuse(assignedPlayer, accusedPlayer, true);
			}
		}
	}
	
	@Override
	public void chooseRole() {
		// TODO Auto-generated method stub
		assignedPlayer.getRole().setRole("Witch");

	}

	@Override
	public Card chooseCard(boolean isAccused) {
		// TODO Auto-generated method stub
		//play card randomly
		CardContainer playableCard=assignedPlayer.getHand().getPlayableCard(assignedPlayer, isAccused);
		int handSize = playableCard.getNumberCard();
		return playableCard.getCardByIndex(randomNumber.nextInt(handSize));
	}

	@Override
	public Player chooseTarget(List<Player> targets) {
		// TODO Auto-generated method stub
		//inutile je crois sauf pour la start humain
		return null;
	}

}
