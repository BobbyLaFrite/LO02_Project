package game;

import java.util.ListIterator;
import players.PlayerGroup;
import players.NextPlayer;
import players.Player;
public class Game {

	private static Game instance;
	private Deck deck;
	private PlayerGroup playerGroup;
	private DiscardPile discardPile;
	private Player actuPlayer = null;
	private Player prevPlayer = null;

	private Game() {
		int userInput;
		
		this.deck = Deck.getInstance();
		
		UserInterface userInterface=UserInterface.getInstance();
		userInput=userInterface.chooseInt("Nombre de joueurs total?",3,6,false);
		this.playerGroup = PlayerGroup.getInstance(userInput); 	//##temporaire##
		userInput=userInterface.chooseInt("Nombre de joueurs humain?",0,userInput,false);		
		ListIterator<Player> it = this.playerGroup.getIterator();
		
		Player actuPlayer;
		for (int i=0; i<this.playerGroup.getNumberPlayer() ; i++) {
			actuPlayer=it.next();
			if (i<userInput) {
				actuPlayer.setName("joueur"+String.valueOf(i+1));
				actuPlayer.setStrategieHuman(); //Voir comment choisir la strategie
			}
			else {
				actuPlayer.setStrategieRandom(); //Voir comment choisir la strategie
				actuPlayer.setName("IA-"+String.valueOf(i+1));
			}
		}
		
		
		
		this.discardPile = DiscardPile.getInstance();
		
		
	}
	
	public static Game getInstance() {

		if (instance==null) {
			instance= new Game();
		}
		return instance;
	}
	
	private boolean shouldRoundEnd() {
		int numberHiddenPlayer=0;
		ListIterator<Player> it = this.playerGroup.getIterator();
		while (it.hasNext()) {
			if (!it.next().getRole().getIsRevealed()) {//si le role n'est pas révélé
				numberHiddenPlayer+=1;
			}
		}
		if (numberHiddenPlayer>1) {
			return false;
		}else {
			return true;
		}
	}
	
	public void playRound() {
		NextPlayer nextPlayer = null;
		this.deck.init();
		this.discardPile.init();
		this.playerGroup.initAllPlayer();
		
		this.deck.distribute(this.playerGroup, (int) 12/this.playerGroup.getNumberPlayer());
		while (this.deck.isEmpty()==false) {
			this.deck.giveCard(0,this.discardPile);
		}
		
		ListIterator<Player> playerIt = this.playerGroup.getIterator();
		
		while (playerIt.hasNext()) {
			playerIt.next().getStrategie().chooseRole();
		}
		
		
		
		
		playerIt = this.playerGroup.getIterator(); //On fait jouer le premier joueur (##mettre random plus tard##)
		nextPlayer=new NextPlayer(playerIt.next(),false);
		while (!this.shouldRoundEnd()) {
			System.out.println(this.playerGroup);
			this.actuPlayer=nextPlayer.getTarget();
			System.out.println("\nC'est à "+actuPlayer.getName()+" de jouer !");
			nextPlayer=this.actuPlayer.getStrategie().play(nextPlayer.getIsAccuded());
			this.actuPlayer=prevPlayer; 	//Utile notamment pour hoocked nose qui a besoin de la personne qui accuse
			
			
		}
				
		
	}
	
	public Player getActuPlayer() {
		return this.actuPlayer;
	}
	
	public Player getPrevPlayer() {
		return this.prevPlayer;
	}
	
	public static void main(String[] args) {	
		Game game=  Game.getInstance();
		boolean keepPlaying = true;
		String userInput;
		while (keepPlaying) {
			
			game.playRound();

			userInput=UserInterface.getInstance().chooseBetween("Play another round? Y/N", "Y,N", false);

			if (userInput.equalsIgnoreCase("N")) {
			keepPlaying=false;
			}
			
		}
		 
		 
	}
	
	
	
}
