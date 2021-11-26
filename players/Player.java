package players;

import java.util.Scanner;

import cards.Card;

import java.util.ListIterator;
import java.util.Random;
import game.Board;
import game.Role;

public class Player {
	private Hand hand;
	private Board board;
	int playerIndex;
	private Role role;
	private String name;
	private Strategie strategie;

	static int nbrJoueur = 0; 					//## temporaire, pour nommer les joueurs##
	
	public Player() {
		this.name = "joueur_"+Player.nbrJoueur;	//## temporaire, pour nommer les joueurs##
		Player.nbrJoueur+=1;					//## temporaire, pour nommer les joueurs##
		this.hand=new Hand();
		this.board=new Board();
		this.role = new Role();
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
	
	public Role getRole() {
		return this.role;
	}
	
	public Strategie getStrategie() {
		return this.strategie;
	}
	
	
	public String toString () { 	//Retourne un string sous forme "nom du joueur, role, status, nombre de carde dans la main 
		String content = this.name + " , role : " + this.role.getRole() + " , est révélé : " + String.valueOf(this.role.getIsRevealed()) + " , nombre de carte : " + String.valueOf(this.hand.getNumberCard()); 		
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
		//A IMPLEMENTER
		//la signature a été modifiée pour ajouter une cible
		return null;
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
