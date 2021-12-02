package game;

import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import players.PlayerGroup;
import players.NextPlayer;
import players.Player;
public class Game {

	private static Game instance;
	private Deck deck;
	private PlayerGroup playerGroup;
	private DiscardPile discardPile;
	private Player playingPlayer=null;
//	private Player actuPlayer = null; JE pense qu'il faut passer le prochain joueur et l'actuel dans group player ça fait plus de sens
//	private Player prevPlayer = null;

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
			
			//System.out.println(this.playerGroup);
			
			this.playingPlayer=nextPlayer.getTarget();
			this.playerGroup.setCurrentPlayer(playingPlayer);
			
			
			System.out.println("\n-------------------------------------\nC'est \u00e0 "+playingPlayer.getName()+" de jouer !");
			if (!playingPlayer.getStrategieAsString().equalsIgnoreCase("HumanPlayer")) {//Si le joueur est une IA, on attend un peu
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

				}
			}
			nextPlayer=this.playingPlayer.play(nextPlayer.getIsAccuded());
			this.playerGroup.setPreviousPlayer(playingPlayer);
			
		}
		Player roundWinner=null;
		ListIterator<Player> it = this.playerGroup.getIterator();
		int scoreWon=0;
		while (it.hasNext()) {
			roundWinner=it.next();
			if (!roundWinner.getRole().getIsRevealed()) {//si le role n'est pas révélé
				if (roundWinner.getRole().getRole().equalsIgnoreCase("Witch")) {
					scoreWon=2;
				}else {
					scoreWon=1;
				}
				roundWinner.addScore(scoreWon);
				
				break;
			}
		}
		System.out.println("\n==================================\n"+roundWinner.getName()+" gagne la manche ! Il était "+roundWinner.getRole().getRole()+" et gagne donc "+scoreWon+" points.");
		
		
	}
	
	public static void main(String[] args) {	
		Game game=Game.getInstance();
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
