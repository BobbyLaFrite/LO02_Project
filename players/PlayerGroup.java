package players;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Iterator;

import java.util.List;
import java.util.ListIterator;

import cards.Card;
import game.CardContainer;

public class PlayerGroup {
	private LinkedHashSet<Player> playerList;
	private int numberPlayer;
	private static PlayerGroup instance;
	
	private PlayerGroup(int numberPlayer) { //##Pour l'instant on ne donne pas de type au joueurs, a voir plus tard##
		this.numberPlayer = numberPlayer;
		this.playerList =  new LinkedHashSet<Player>() ;
		 
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
	
	public String getAllName() {
		String content = "";
		Iterator<Player> playerIT = this.getIterator();
		while (playerIT.hasNext()) {
			content+=playerIT.next().getName();
			if (playerIT.hasNext()){
				content+="\n";
			}
		}
		return content;
	}
	
	public Player getRandomPlayer() { //retourne un joueur aléatoire
		 
		int index = (int)(Math.random() * this.getNumberPlayer());
		return this.getPlayerByIndex(index);
		 
	}
	
	public int getNumberPlayer() {
		return this.numberPlayer;
	}
	
	public Iterator<Player> getIterator(){
		return this.playerList.iterator();
	}
	

	public void initAllPlayer() {
		Iterator<Player> it = this.getIterator();
		Player actuPlayer;
		while (it.hasNext()) {
			actuPlayer=it.next();
			actuPlayer.getHand().init();
			actuPlayer.getBoard().init();
		}
	}
	

	public Player getPlayerByIndex(int index) {//parcourt le linkedhashset pour renvoyer le nième élément
		Iterator<Player> it =  this.playerList.iterator();
		Player currentPlayer=null;
		for (int i = 0;i<index+1;i++) {
			if (it.hasNext()) {
				currentPlayer=it.next();
			}
		}
		return currentPlayer;
	}
	
	public Player getPlayerByName(String name) {
		Iterator<Player> it =  this.playerList.iterator();
		Player currentPlayer=null;
		Boolean playerFound = false;
		while (it.hasNext() && !playerFound)
			currentPlayer=it.next();
			if (currentPlayer.getName()==name) {
				playerFound=true;
		}
		if (!playerFound) {
			currentPlayer=null;
		}
		return currentPlayer;
	}

}
