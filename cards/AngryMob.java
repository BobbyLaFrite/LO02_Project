package cards;
import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class AngryMob extends Card{
	
	
	public AngryMob() {
		super("Angry mob","Take next turn.","[Only playable if you have been revealed as villager]\nReveal another player's identity.\nWitch : You gain 2pts. You take next turn.\nVillager:You lose 2pts. They take next turn.");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		if (isAccused || (actuPlayer.getRole().getIsRevealed() && actuPlayer.getRole().getRole()=="Villager") && PlayerGroup.getInstance(0).getTarget("accusation",actuPlayer).size()!=0) { //Soit on joue la partie Whtch et alors pas de pb, soit la partie hunt et il faut être un villageois révélé et avoir une cible valide
			return true;
		}else {
			return false;
		}
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		System.out.println("Angry Mob - Witch :\n Vous prenez le tour !");
		return new NextPlayer(actuPlayer,false); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		Player target;
		target = super.chooseTarget("accusation",actuPlayer);
		target.revealRole();
		if (target.getRole().getRole()=="Witch") {
			actuPlayer.addScore(2);
			return new NextPlayer(actuPlayer,false); 
		}else {
			actuPlayer.addScore(-2);
			return new NextPlayer(target,false); 
		}
		
	}
	
	
}
