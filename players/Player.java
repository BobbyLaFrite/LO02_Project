package players;


import java.util.Random;

import cards.Card;
import game.Board;
import game.Role;

public class Player {
	private Hand hand;
	private Board board;
	int playerIndex;
	private Role role;
	private String name;
	private Strategie strategie;
	private int score;

	static int nbrJoueur = 0; 					//## temporaire, pour nommer les joueurs##
	
	public Player() {
		this.name = "joueur_"+Player.nbrJoueur;	//## temporaire, pour nommer les joueurs##
		Player.nbrJoueur+=1;					//## temporaire, pour nommer les joueurs##
		this.hand=new Hand();
		this.board=new Board();
		this.role = new Role();
		this.score=0;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void addScore(int val) {
		this.score+=val;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public Hand getHand() {
		return this.hand;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public Strategie getStrategie() {
		return this.strategie;
	}
	
	public boolean getIsAlive() {
		if (!this.getRole().getIsRevealed()||this.getRole().getRole().equalsIgnoreCase("Villager")){
			return true;
		}else {
			return false;
		}
	}
	
	public String getStrategieAsString() {
		return this.strategie.getClass().getSimpleName();
	}
	
	public void setStrategieHuman() {
		this.strategie = new HumanPlayer(this);//Voir comment choisir la strategie
	}
	
	public void setStrategieRandom() {
		Random rand = new Random();
		switch (rand.nextInt(2)) { //Switch pour choisir une stratégie aléatoirement
        	case 0: this.strategie = new WitchStrategy(this); break;
        	case 1: this.strategie = new Accuser(this); break;
		}
	}
	
	
	
	public String toString () { 	//Retourne un string sous forme "nom du joueur, role, status, nombre de carde dans la main 
		String content = this.name;
		content+=",\t\trole : " + this.role.getRole();
		content+=",\t\test révélé : " + String.valueOf(this.role.getIsRevealed());
		content+=",\t\tnombre de carte : " + String.valueOf(this.hand.getNumberCard());
		content+=",\t\tstratégie : "+this.getStrategieAsString(); 
		return content;
	}
	
	public NextPlayer Play(boolean isAccused) {
		//A IMPLEMENTER
		return null;
	}
	
	public NextPlayer revealRole(){
		//révéler son role permet de connaitre le prochain joueur 
		//donc changement de signature
		//A IMPLEMENTER
		return null;
	}
	
	public NextPlayer playCard(Card card,boolean isAccused) {
		NextPlayer nextPlayer = null;
		nextPlayer=card.activate(this,isAccused);

		return nextPlayer;
	}
	
	public Player accuse() {
		//A IMPLEMENTER
		//cette méthode à changé de classe, elle est passée de stratégie Player
		//dans la stratégie le joueur (réel ou IA) va choisir d'accuser et qui accuser, c'est la meme chose pour tous les joueurs
		//et cela ne dépends pas de leurs type
		//je pense que cette méthode n'est pas utile finalement
		return null;
	}


	
	
}
