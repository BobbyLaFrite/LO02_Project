package cards;
import java.util.List;

import game.DiscardPile;
import game.UserInterface;
import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class DuckingStool extends Card{
	
	String userInput="";
	UserInterface userInterface = UserInterface.getInstance();
	public DuckingStool() {
		super("Ducking stool","Choose next player.","Choose a player. They must reveal their identity or discard a card from their hand.\nWitch : You gain 1pts. You take next turn.\nVillager:You lose 1pts. They take next turn.\nIf they discard : they take turn");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		return true;
				
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		System.out.println("Ducking stool - Witch :\n Choisissez le prochain joueur !");
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		return new NextPlayer(target, false);
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		System.out.println("Ducking stool - Hunt :\n Choisissez le joueur cible !");
		//on ne peut pas cibler un joueur avec Wart de révélé
		List<Player> players = PlayerGroup.getInstance(0).getTarget("", actuPlayer);
		Player untargetablePlayer = null;
		for (Player player : players) {
		if (player.getBoard().toString().contains("Wart")) {
				untargetablePlayer = player;
				break;
			}
		}	
		Player target;
		if (untargetablePlayer!=null) {
			target = super.chooseTarget("accusation",actuPlayer,untargetablePlayer);
		}
		else {
			target = super.chooseTarget("accusation",actuPlayer);
		}
		//userInput = userInterface.chooseBetween(target.getName()+"V", userInput, false)
		System.out.println("Ducking stool - Witch :\n"+this.getName()+" doit choisir entre se reveler et defausser une carte");
		if (target.getStrategie().chooseToReveal(actuPlayer.getHand())) {
			if (target.getRole().getRole().equalsIgnoreCase("Wicth")) {
				actuPlayer.addScore(1);
				return new NextPlayer(actuPlayer, false);
			}
			else {
				actuPlayer.addScore(-1);
				return new NextPlayer(target, false);
			}
			
		}
		else {
			Card cardToDisCard = target.getStrategie().chooseCardToDiscard(actuPlayer.getHand(),this);
			target.getHand().giveCard(cardToDisCard, DiscardPile.getInstance());
			return new NextPlayer(target, false);
		}
		
	}
	
	
}
