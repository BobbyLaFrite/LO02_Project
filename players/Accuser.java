package players;

import java.util.List;
import java.util.Random;

import cards.Card;

public class Accuser implements Strategie {
	Player assignedPlayer;
	PlayerGroup playerGroup = PlayerGroup.getInstance(0);
	Random randomNumber = new Random();
	public Accuser(Player assignedPlayer) {
		//on a besoin de savoir quel joueur est associé à la stratégie
		this.assignedPlayer=assignedPlayer;
	}

	@Override
	public NextPlayer play(boolean isAccused) {
		// TODO Auto-generated method stub
		//il va accuser tant qu'il peut un joueur au pif
		if (isAccused) {
			return assignedPlayer.revealRole();
		}
		else {//ACCUSER
			List<Player> accusablePlayers=playerGroup.getTarget("accusation",assignedPlayer);
			int taille = accusablePlayers.size();
			
			Player accusedPlayer = accusablePlayers.get(randomNumber.nextInt(taille));
			return assignedPlayer.accuse(assignedPlayer, accusedPlayer, true);
		}
	}
	@Override
	public void chooseRole() {
		// TODO Auto-generated method stub
		assignedPlayer.getRole().setRole("Villager");
	}

	@Override
	public Card chooseCard(boolean isAccused) {
		// TODO Auto-generated method stub
		//joue toujours la première
		return assignedPlayer.getHand().getPlayableCard(this.assignedPlayer,isAccused).getCardByIndex(0);
	}
	
	public Card chooseCardToDiscard() {
		return assignedPlayer.getHand().getCardByIndex(0);
	}

	@Override
	public Player chooseTarget(List<Player> targets) {
		// TODO Auto-generated method stub
		return targets.get(0);
	}
	@Override
	public boolean chooseToReveal() {//non utile 
		return true;
	}

}
