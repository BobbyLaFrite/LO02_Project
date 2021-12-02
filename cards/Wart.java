package cards;
import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class Wart extends Card{
	
	
	public Wart() {
		super("Wart","Take next turn.","Choose next player.");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		return true;
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		System.out.println("Wart - Witch :\n Vous prenez le tour !");
		return new NextPlayer(actuPlayer,false); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		System.out.println("Wart - Hunt Choisissez le prochain joueur");
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		return new NextPlayer(target, false);
		
	}
	
	
}
