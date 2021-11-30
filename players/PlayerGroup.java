package players;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class PlayerGroup {
	private List<Player> playerList;
	private int numberPlayer;
	private static PlayerGroup instance;
	//private List<Player> exceptionsAccusationList; ##Pas utile je pense car on va pas actualiser la liste � chaque fois donc pas d'attributs##
	private List<Player> exceptionCardTarget;

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
	
	public String getAllName() {
		String content = "";
		ListIterator<Player> playerIT = this.getIterator();
		while (playerIT.hasNext()) {
			content+=playerIT.next().getName();
			if (playerIT.hasNext()){
				content+="\n";
			}
		}
		return content;
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
			actuPlayer.getHand().init();
			actuPlayer.getBoard().init();
		}
	}
	
	public Player getPlayer(int index) {
		return this.playerList.get(index);	
	}
	
	public Player getPlayerByName(String name) {
		Iterator<Player> it = this.playerList.iterator();
		Player currentPlayer=null;
		while (it.hasNext()) {//On continue tant que le nom ne correspond pas
			currentPlayer=it.next();
			if (currentPlayer.getName().equalsIgnoreCase(name)) {
				break;
			}
		}
		return currentPlayer;
	}
	
	public List<Player> getRevealedPlayer(){
		Player actuPlayer;
		List<Player> RevealedPlayer = new ArrayList<Player>();
		ListIterator<Player> playerIT = this.getIterator();
		while (playerIT.hasNext()) {
			
			actuPlayer=playerIT.next();
			if (actuPlayer.getRole().getIsRevealed()){//Si le role est r�v�l�
				RevealedPlayer.add(actuPlayer);
			}
		}
		
		
		return RevealedPlayer;
	}
	
	private List<Player> getDeadPlayer() {
		Player actuPlayer;
		List<Player> deadPlayer = new ArrayList<Player>();
		ListIterator<Player> playerIT = this.getIterator();
		while (playerIT.hasNext()) {
			
			actuPlayer=playerIT.next();
			if (!actuPlayer.getIsAlive()){
				deadPlayer.add(actuPlayer);
			}
		}
		
		
		return deadPlayer;
	}

	
	//public int getUnrevealedPlayerNumber() { Pas utile je pense car on va pas actualiser la liste � chaque fois donc pas d'attributs##
	//	return exceptionsAccusationList.size();
	//}

	public List<Player> getTarget(String typeOfTarget,Player exception) {
		List<Player> targets = playerList;
		targets.remove(exception);
		if (typeOfTarget.contains("accusation")) { //##Modif car il peut y avoir plusieurs types d'exeption en m�me temps
			targets.removeAll(this.getRevealedPlayer());
		}
		if (typeOfTarget.contains("alive")) { //##Modif car il peut y avoir plusieurs types d'exeption en m�me temps
			targets.removeAll(this.getDeadPlayer());
		}
		if (typeOfTarget == "card") { //## a modifier je pense on verra
			targets.removeAll(exceptionCardTarget);
		} 
		return targets;
	}
	
	public String playerListToString(List<Player> playerList) {
		String content = "";
		Iterator<Player> playerIT = playerList.iterator();
		while (playerIT.hasNext()) {
			
			content+=playerIT.next().getName();
			if (playerIT.hasNext()){
				content+=",";
			}
		}
		return content;
	}

	
	public String toString () { 
		String content = "PlayerGroup : nombre de joueurs = "+String.valueOf(this.getNumberPlayer())+"\n";
		ListIterator<Player> playerIT = this.getIterator();
		while (playerIT.hasNext()) {
			
			content+=playerIT.next().toString();
			if (playerIT.hasNext()){
				content+="\n";
			}
		}
		return content;
	}
}
