package projetLO02;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PlayerGroup {
	private List<Player> playerList;
	private int numberPlayer;
	private static PlayerGroup instance;
	
	private PlayerGroup(int numberPlayer) { //##Pour l'instant on ne donne pas de type au joueurs, a voir plus tard##
		this.numberPlayer = numberPlayer;
		this.playerList =  new ArrayList<Player>() ;
		 
		 for (int i = 0; i < numberPlayer; i++) { //Boucle qui ajoute le nombre de joueurs voulu
			 this.playerList.add(new Player());
		 	}
	}
	
	public static PlayerGroup getInstance(int numberPlayer) {
		if (instance==null) {
			instance= new PlayerGroup(numberPlayer);
		}
		return instance;
	}
	
	public int getNumberPlayer() {
		return this.numberPlayer;
	}
	
	public ListIterator<Player> getIterator(){
		return this.playerList.listIterator();
	}
	

	public void initAllPlayer() {
		ListIterator<Player> it = this.getIterator();
		Player actuPlayer;
		while (it.hasNext()) {
			actuPlayer=it.next();
			actuPlayer.hand.init();
			actuPlayer.board.init();
		}
	}
	
	public Player getPlayer(int index) {
		return this.playerList.get(index);	
	}

}
