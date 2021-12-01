package cards;
import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class Broomstick extends Card{
	
	
	public Broomstick() {
		super("Broomstick","Take next turn.","Choose next player.");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		return true;
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		System.out.println("Broomstick - Witch :\n Vous prenez le tour !");
		return new NextPlayer(actuPlayer,false); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		System.out.println("Broomstick - Hunt Choisissez le prochain joueur");
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		return new NextPlayer(target, false);
		
	}
	
	
}
