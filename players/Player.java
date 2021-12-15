package players;


import java.util.List;
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
		if ((this.score+val)>=0) {//on empêche d'aller dans des valeurs négatives
			this.score+=val;
		}
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
		this.strategie = new HumanPlayer();//Voir comment choisir la strategie
	}
	
	public void setStrategieRandom() {
		Random rand = new Random();
		switch (rand.nextInt(2)) { //Switch pour choisir une stratï¿½gie alï¿½atoirement
        	case 0: this.strategie = new WitchStrategy(); break;
        	case 1: this.strategie = new Accuser(); break;
		}
	}
	
	
	
	public String toString () { 	//Retourne un string sous forme "nom du joueur, role, status, nombre de carde dans la main 
		String content = this.name;
		content+=",\t\trole : " + this.role.getRole();
		content+=",\t\test r\u00e9v\u00e9l\u00e9 : " + String.valueOf(this.role.getIsRevealed());
		content+=",\t\tnombre de carte : " + String.valueOf(this.hand.getNumberCard());
		content+=",\t\tstrat\u00e9gie : "+this.getStrategieAsString(); 
		return content;
	}
	
	public NextPlayer play(boolean isAccused) {

		boolean result = this.strategie.chooseAction(isAccused,hand.getPlayableCard(this, isAccused));
		if(isAccused) {//etre accusé
			if (result) {//et vouloir se réveler
				return this.revealRole();
			}
			else {//et vouloir jouer une carte
				return this.playCard(this.strategie.chooseCard(isAccused,hand.getPlayableCard(this, isAccused)), isAccused);
			}
		}
		else {//ne pas etre accusé
			if (result) {//et vouloir accuser
				List<Player> accusablePlayer = PlayerGroup.getInstance(0).getTarget("accusation", this);
				Player targetedPlayer = this.strategie.chooseTarget(accusablePlayer);
				return accuse(this, targetedPlayer);
			}
			else {//et jouer une carte
				Card cardToBePlayed = this.strategie.chooseCard(false,hand.getPlayableCard(this, isAccused));
				return this.playCard(cardToBePlayed, false);
				
			}
		}
	}
	
	public NextPlayer revealRole(){
		//rï¿½vï¿½ler son role permet de connaitre le prochain joueur 
		//donc changement de signature
		//A IMPLEMENTER
		System.out.println(this.getName()+" s'est revele !");
		System.out.println("Il etait : "+role.getRole());
		this.role.reveal();
		if (this.role.getRole().equalsIgnoreCase("Villager")) {
			return new NextPlayer(this, false);
		}
		else {
			return new NextPlayer(PlayerGroup.getInstance(0).getPreviousPlayer(), false);//on doit rï¿½cupï¿½rer l'accusateur
			
		}

	}
	
	public NextPlayer playCard(Card card,boolean isAccused) {
		NextPlayer nextPlayer = null;
		nextPlayer=card.activate(this,isAccused);
		//On dï¿½place la carte aprï¿½s qu'elle a ï¿½tï¿½ jouï¿½e
		if (this.hand.toString().contains(card.getName())) {//si la carte n'a pas Ã©tÃ© dÃ©placÃ©e parun autre effet
			this.hand.giveCard(card, this.board);
		}
		

		return nextPlayer;
	}
	
	public NextPlayer accuse(Player accuser,Player accusedPlayer) {
			System.out.println(accuser.getName()+" accuse "+accusedPlayer.getName()+"\n");
		return new NextPlayer(accusedPlayer, true);
	}


	
	
}
