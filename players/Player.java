package players;

import java.util.Scanner;
import java.util.ListIterator;
import java.util.Random;
import game.Board;
import game.Role;

public class Player {
	private Hand hand;
	private Board board;
	protected PlayerGroup playerGroup;
	private Role role;
	private String name;
	boolean controlable; //##temporaire##

	static int nbrJoueur = 0; 					//## temporaire, pour nommer les joueurs##
	
	public Player() {
		this.name = "joueur_"+Player.nbrJoueur;	//## temporaire, pour nommer les joueurs##
		Player.nbrJoueur+=1;					//## temporaire, pour nommer les joueurs##
		this.hand=new Hand();
		this.board=new Board();
		this.role = new Role();
		this.controlable=false; 				//## temporaire, sert a  differencier les joueurs des ia##
	}
	
	public void setControlable(boolean controlable) {
		this.controlable = controlable;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Hand getHand() {
		return this.hand;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public void chooseRole() {
		
		if (this.controlable) {
			Scanner scanner = new Scanner(System.in);
			String userInput;
			
			System.out.println("Chose your role Witch/Villager");
			userInput = scanner.nextLine(); 
			this.role.setRole(userInput);
		} else {
			this.role.setRole((String) (new Random().nextBoolean() ? "Witch" : "Villager"));
			
		}
		
	}
	
	public String toString () { 	//Retourne un string sous forme "nom du joueur, role, status, nombre de carde dans la main 
		String content = this.name + " , role : " + this.role.getRole() + " , est révélé : " + String.valueOf(this.role.getIsRevealed()) + " , nombre de carte : " + String.valueOf(this.hand.getNumberCard()); 		
		return content;
	}
	
	public NextPlayer play() { //Temporaire aussi, à inclure dans les comportements après. Ici on fait juste choisir et jouer une carte
		
		Scanner scanner = new Scanner(System.in);
		String userInput=null;
		int intValue;
		boolean validEntry=false;
		NextPlayer cible=null;
		
		System.out.println("C'est à toi de jouer "+this.name +"\n Voici les cartes dans ta main :\n"+this.hand+"\n Choisir une carte (nom/index) ou lancez une accusation (-1 / \"accuse\")+nom du joueur");

		do {
			if (userInput !=null) { //s'active seulement la deuxième fois qu'on va dans la boucle et donc que l'entrée est erronée
				System.out.println("Carte ou joueur non trouvée");

			}
			userInput = scanner.nextLine();
			try {  									//Très moche, si le prof voit ça il nous tue mais c'est une solution tempraire pour séparer les réponses int des strings
			    intValue = Integer.parseInt(userInput);
			    if (intValue < this.hand.getNumberCard()) {
			    	cible=this.hand.getCardByIndex(intValue).activate();
			    	validEntry=true;
			    }
			} catch (NumberFormatException e) { //Et du coup la partie String ici
				userInput.replace("accuse ", "");
				cible =new NextPlayer(this.playerGroup.getPlayerByName(userInput),false);
				
				
			}
		} while (validEntry==false);
		
		
		
		return cible;
	}




	
	
}
