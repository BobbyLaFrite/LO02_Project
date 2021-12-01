package cards;
import java.util.List;

import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class EvilEye extends Card{
	
	
	public EvilEye() {
		super("Evil Eye","Choose next player.\nOn their turn they must accuse a player other than you, if possible.","Choose next player.\\nOn their turn they must accuse a player other than you, if possible.");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		return true;
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		System.out.println("Evil Eye - Witch :\nChoisissez une cible");
		NextPlayer nextPlayer = null;
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		
		if (PlayerGroup.getInstance(0).getTarget("accusable",target).size()>1) {//si il y a plus de 2 joueurs accusables , donc en plus de nous même
			
			List<Player> accusablePlayers = PlayerGroup.getInstance(0).getTarget("accusable", target,actuPlayer);
			System.out.println(target.getName()+" choisit une cible à accuser !");
			nextPlayer = new NextPlayer(target.getStrategie().chooseTarget(accusablePlayers),true);
			
		}
		
		return nextPlayer;
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		System.out.println("Evil Eye - Hunt :\nChoisissez une cible");
		NextPlayer nextPlayer = null;
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		
		if (PlayerGroup.getInstance(0).getTarget("accusable",target).size()>1) {//si il y a plus de 2 joueurs accusables , donc en plus de nous même
			
			List<Player> accusablePlayers = PlayerGroup.getInstance(0).getTarget("accusable", target,actuPlayer);
			System.out.println(target.getName()+" choisit une cible à accuser !");
			nextPlayer = new NextPlayer(target.getStrategie().chooseTarget(accusablePlayers),true);
			
		}
		return nextPlayer;
	}
}
