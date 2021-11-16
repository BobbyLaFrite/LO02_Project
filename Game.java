package projetLO02;

import java.util.ListIterator;
import java.util.Scanner;

public class Game {

	private static Game instance;
	private Deck deck;
	private PlayerGroup playerGroup;
	private DiscardPile discardPile;
	public Game() {
		Scanner scanner = new Scanner(System.in);
		int userInput;
		
		this.deck = new Deck();
		
		System.out.println("Nombre de joueurs total?");  		//##temporaire##
		userInput = scanner.nextInt();   						//##temporaire##
		this.playerGroup = PlayerGroup.getInstance(userInput); 	//##temporaire##
		System.out.println("Nombre de joueurs controllable?"); 	//##temporaire##
		userInput = scanner.nextInt();  						//##temporaire##
		
		ListIterator<Player> it = this.playerGroup.getIterator();
		
		for (int i=0; i<userInput ; i++) {
			it.next().controlable=true;
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
		this.deck.init();
		this.discardPile.init();
		this.playerGroup.initAllPlayer();
		this.deck.distribute(this.playerGroup, (int) 12/this.playerGroup.getNumberPlayer());
		while (this.deck.isEmpty()==false) {
			this.deck.giveCard(0,this.discardPile);
		}
		
		ListIterator<Player> playerIt = this.playerGroup.getIterator();
		
		while (playerIt.hasNext()) {
			playerIt.next().chooseRole();
		}
		
		
		playerIt = this.playerGroup.getIterator();
		while (playerIt.hasNext()) {
			System.out.println(playerIt.next());
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
