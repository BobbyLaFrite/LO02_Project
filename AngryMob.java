package cards;

import projetLO02.*;

public class AngryMob extends Card{
	
	
	public AngryMob() {
		super("Angry mob");
	}
	
	public NextPlayer activate(){ //##incomplet, juste un test pour une carte et passer à un autre joueur
		Player target;
		target = super.chooseTarget();
		return new NextPlayer(target,false); //##aucun choix de cible car temporaire##
	}
	
	
}
