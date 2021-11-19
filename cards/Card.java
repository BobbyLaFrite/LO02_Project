package cards;

import java.util.ListIterator;
import java.util.Scanner;
import players.Player;
import players.PlayerGroup;

public class Card {
	private String name;
	
	public Card(String name) {
		this.name=name;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	
	
	protected Player chooseTarget() { //##classe ~test (comportera bcp de modifs) notamment on peut choisir tout les joueurs et seulement par nom
		Scanner scanner = new Scanner(System.in);
		String userInput;
		Player target = null;
		Player actuPlayer;
		while (target == null) {
			
			System.out.println("Donnez le nom de la cible");
			System.out.println(PlayerGroup.getInstance(0).getAllName());
			userInput = scanner.nextLine(); 
			
			ListIterator<Player> playerIt = PlayerGroup.getInstance(0).getIterator();// ## pas très propre, on est obligé de mettre un int en arg##
			while (playerIt.hasNext() && target == null) {
				actuPlayer = playerIt.next();
				if (actuPlayer.getName()==userInput) {
					target=actuPlayer;
				}
			}
			if (target == null) {
				System.out.println("Le nom "+userInput+" n'est pas valide");
			}
			
		}
		return target;
		
	}
}
