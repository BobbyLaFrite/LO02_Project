package game;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import players.PlayerGroup;
import players.NextPlayer;
import players.Player;

public class Game {

	private static Game instance;
	private Deck deck;
	private PlayerGroup playerGroup;
	private DiscardPile discardPile;
	public Game() {
		Scanner scanner = new Scanner(System.in);
		int userInput;
		
		this.deck = Deck.getInstance();
		
		System.out.println("Nombre de joueurs total?");  		//##temporaire##
		userInput = scanner.nextInt();   						//##temporaire##
		this.playerGroup = PlayerGroup.getInstance(userInput); 	//##temporaire##
		System.out.println("Nombre de joueurs controllable?"); 	//##temporaire##
		userInput = scanner.nextInt();  						//##temporaire##
		Iterator<Player> it = this.playerGroup.getIterator();
		
		for (int i=0; i<userInput ; i++) {
			it.next().setControlable(true);
			

		}
		
		this.discardPile = DiscardPile.getInstance();
	}
	
	public static Game getInstance() {

		if (instance==null) {
			instance= new Game();
		}
		return instance;
	}
	
	public void playRound() {
		
		Scanner scanner = new Scanner(System.in);
		String userInput;
		boolean keepPlaying = true;
		NextPlayer cible;
		
		this.deck.init();
		this.discardPile.init();

		this.playerGroup.initAllPlayer();
		this.deck.distribute(this.playerGroup, (int) 12/this.playerGroup.getNumberPlayer());
		while (this.deck.isEmpty()==false) {
			this.deck.giveCard(0,this.discardPile);
		}
		
		Iterator<Player> playerIt = this.playerGroup.getIterator();
		
		while (playerIt.hasNext()) {
			playerIt.next().chooseRole();
		}
		
		Player actuPlayer = this.playerGroup.getRandomPlayer();
		playerIt = this.playerGroup.getIterator(); 	//juste un affichage
		while (playerIt.hasNext()) {				//juste un affichage
			System.out.println(playerIt.next());	//juste un affichage
		}
		while (keepPlaying) {
			cible=actuPlayer.play(); 			//On fait jouer le joueur et on récupère le nextplayer ##rajouter en paramètre si il est accusé
			actuPlayer=cible.getTarget();
			
			System.out.println("Stopper? Y/N"); 	//Simple test pour sortir du jeu car pour l'instant ça continue à l'infini (car pas d'accusation)

			userInput = scanner.nextLine(); 
			
			if(userInput.equalsIgnoreCase("Y")) {
				keepPlaying=false;
			}
		}
		

		
						
		
		
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String userInput;
		Game game=  Game.getInstance();
		boolean keepPlaying = true;
		while (keepPlaying) {
			
			game.playRound();

			
			System.out.println("Play another round? Y/N");
			
			do {
				userInput = scanner.nextLine(); 
			} while (!(userInput.equalsIgnoreCase("Y") ||  userInput.equalsIgnoreCase("N")));
			
			if (userInput.equalsIgnoreCase("N")) {
			keepPlaying=false;
			}
			
		}
		 
		 
	}
	
	
	
}
