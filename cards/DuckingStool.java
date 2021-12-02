package cards;
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
		System.out.println("Duckig stool - Witch :\n Choisissez le prochain joueur !");
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		return new NextPlayer(target, false);
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		System.out.println("Duckig stool - Witch :\n Choisissez le joueur cible !");
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		//userInput = userInterface.chooseBetween(target.getName()+"V", userInput, false)
		return null;//A FINIR
		
	}
	
	
}
