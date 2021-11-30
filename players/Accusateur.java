package players;

import java.util.List;

import cards.Card;

public class Accusateur implements Strategie {
	Player assignedPlayer;
	PlayerGroup playerGroup = PlayerGroup.getInstance(0);
	public Accusateur(Player assignedPlayer) {
		//on a besoin de savoir quel joueur est associé à la stratégie
		this.assignedPlayer=assignedPlayer;
	}

	@Override
	public NextPlayer play(boolean isAccused) {
		// TODO Auto-generated method stub
		//il va accuser tant qu'il peut
		if (isAccused) {
			assignedPlayer.revealRole();
		}
		else {
			assignedPlayer.accuse();
		}
		
		//return nextPlayer
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
		return assignedPlayer.getHand().getCardByIndex(0);
	}

	@Override
	public Player chooseTarget(List<Player> targets) {
		// TODO Auto-generated method stub
		return targets.get(0);
	}

}
