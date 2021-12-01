package cards;
import java.util.Iterator;
import java.util.ListIterator;

import game.CardContainer;
import game.DiscardPile;
import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class Cauldron extends Card{
	
	
	public Cauldron() {
		super("Cauldron","The player who accused you discards a random card from their hand.\nTake next Turn.","Reveal your identity.\nWitch: Player to your left takes next turn.\nVillager: Chose next player");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		return true;
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		Player prePlayer = PlayerGroup.getInstance(0).getPreviousPlayer();
		if (!prePlayer.getHand().isEmpty()) {
			System.out.println("Le joueur qui vous a accusé n'a pas de carte en main !");
		}
		else {
			prePlayer.getHand().giveRandomCard(DiscardPile.getInstance());
		}
		System.out.println("Cauldron - Witch :\n Vous prenez le tour !");
		
		return new NextPlayer(actuPlayer,false); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		actuPlayer.revealRole();
		if (actuPlayer.getRole().getRole().equalsIgnoreCase("Witch")) {
			System.out.println("Cauldron - Hunt :\n Le joueur à votre gauche prend le tour !");
			int nextPlayerIndex = PlayerGroup.getInstance(0).getIndex(actuPlayer)-1;
			if (nextPlayerIndex<=0) {
				return new NextPlayer(PlayerGroup.getInstance(0).getPlayer(nextPlayerIndex), false);
			}
			else {
				return new NextPlayer(PlayerGroup.getInstance(0).getPlayer(PlayerGroup.getInstance(0).getNumberPlayer()-nextPlayerIndex), false);
			}
		}
		else {
			System.out.println("Cauldron - Hunt :\n Choisissez le prochain joueur !");
			Player target;
			target = super.chooseTarget("alive",actuPlayer);
			return new NextPlayer(target, false);
		}
		
	}
	
	
}
