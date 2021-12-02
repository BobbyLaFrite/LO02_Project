package cards;

import java.util.List;

import players.NextPlayer;
import players.Player;
import players.PlayerGroup;

public abstract class Card {
	private String name;
	private String witchEffect;
	private String huntEffect;
	
	public Card(String name,String witchEffect,String huntEffect) {
		this.name=name;
		this.witchEffect=witchEffect;
		this.huntEffect=huntEffect;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	protected Player chooseTarget(String typeOfTarget,Player actuPlayer) { //##classe ~test (comportera bcp de modifs) notamment on peut choisir tout les joueurs et seulement par nom
		Player target = null;
		List<Player> validTarget = PlayerGroup.getInstance(0).getTarget(typeOfTarget, actuPlayer);
		target=actuPlayer.getStrategie().chooseTarget(validTarget);
		System.out.println("Entre "+PlayerGroup.getInstance(0).playerListToString(validTarget)+", "+actuPlayer.getName()+" a choisi "+target.getName());
		return target;
		
	}
	//Chaque carte se s�pare en deux, la fonction activateWitch et activateHunt. Dans tout les cas en la jouant on utilise activate qui va choisir quelle partie activer.
	public NextPlayer activate(Player actuPlayer,boolean isWitchEffect) {
		NextPlayer nextPlayer;
		this.writeCard(isWitchEffect);
		if (isWitchEffect) {
			nextPlayer=this.activateWitch(actuPlayer);
		}else {
			nextPlayer=this.activateHunt(actuPlayer);
		}
		
		return nextPlayer;
	}
	
	public abstract NextPlayer activateWitch(Player actuPlayer);
	
	public abstract NextPlayer activateHunt(Player actuPlayer);
	
	//La fonction isPlayable sert � voir si une carte est jouable. Par exemple elle pourrait ne pas avoir de cibles valides ou n�c�siter des condtions.
	public abstract boolean isPlayable(Player actuPlayer,boolean isAccused);
	
	//Le joueur actuel devient le nextPlayer
	public NextPlayer takeTurn(Player actuPlayer) {
		System.out.println(actuPlayer.getName()+" prenez le tour !");
		return new NextPlayer(actuPlayer,false);
	}
	
	public String getWitchEffect() {
		return this.witchEffect;
	}
	
	public String getHuntEffect() {
		return this.huntEffect;
	}
	
	//Affiche le nom de la carte ainsi que la partie de l'effet choisi.
	public void writeCard(boolean isWitchEffect) {
		
		System.out.println("\n"+this.getName()+"\n");
		if (isWitchEffect) {
			System.out.println(this.witchEffect);
		}else {
			System.out.println(this.huntEffect);
		}

	}
	
}
