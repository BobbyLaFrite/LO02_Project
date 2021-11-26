package players;

import java.util.List;
import java.util.Random;

import cards.Card;

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
				Card cardToBePlayed = chooseCard();
				NextPlayer nextPlayer = assignedPlayer.playCard(cardToBePlayed, true);
				return nextPlayer;
			}
			else {
				return assignedPlayer.revealRole();
			}
		}
		else {
			if ( !assignedPlayer.getHand().isEmpty() ) {
				//play card si la main ets pas vide
				Card cardToBePlayed = chooseCard();
				NextPlayer nextPlayer = assignedPlayer.playCard(cardToBePlayed, false);
				return nextPlayer;
			}
			else {//sinon accuse un joueur aléatoirement
				List<Player> accusablePlayers=playerGroup.getTargets("accusation",assignedPlayer);
				int taille = accusablePlayers.size();
				
				Player accusedPlayer = accusablePlayers.get(randomNumber.nextInt(taille));
				return new NextPlayer(accusedPlayer, true);
			}
		}
	}
	
	@Override
	public void chooseRole() {
		// TODO Auto-generated method stub
		assignedPlayer.getRole().setRole("Witch");

	}

	@Override
	public Card chooseCard() {
		// TODO Auto-generated method stub
		//play card randomly
		int handSize = assignedPlayer.getHand().getNumberCard();
		return assignedPlayer.getBoard().getCardByIndex(randomNumber.nextInt(handSize));
	}

	@Override
	public Player chooseTarget(List<Player> targets) {
		// TODO Auto-generated method stub
		//inutile je crois sauf pour la start humain
		return null;
	}

}
