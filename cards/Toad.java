package cards;
import java.util.ListIterator;

import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class Toad extends Card{
	
	
	public Toad() {
		super("Toad","Take next turn","Reveal your identity.\nWitch: Player to your left takes next turn.\nVillager: Chose next player");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		return true; //Toujours jouable
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		return this.takeTurn(actuPlayer); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		Player target;
		actuPlayer.revealRole();
		if (actuPlayer.getRole().getRole().equalsIgnoreCase("Witch")) {
			System.out.println("Le joueur à sa gauche prend le tour.");
			ListIterator<Player> it = PlayerGroup.getInstance(0).getIterator();
			
			while (it.hasNext()) { //Boucle pour retrouver le joueur actuel dans la liste
				target=it.next();
				if (target==actuPlayer) {
					break;
				}
			}
			//On cherche ensuite le joueur suivant qui est en vie
			
			if (!it.hasNext()) {//Si c'est le dernier on recommence depuis le début
				it = PlayerGroup.getInstance(0).getIterator();
				
			}
			do {
				if (!it.hasNext()) {//Si c'est le dernier on recommence depuis le début
					it = PlayerGroup.getInstance(0).getIterator();
				}
				target=it.next();
			}while (!target.getIsAlive());//On recommence tant que le joueur n'est pas en vie


			
			
		}else {
			System.out.println(actuPlayer.getName()+" choisit le prochain joueur");
			target = super.chooseTarget("alive",actuPlayer);
		}
		
		
		return new NextPlayer(target,false); 
		
		
	}
	
	
}
