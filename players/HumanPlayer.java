package players;

import java.util.List;
import java.util.Scanner;

import cards.Card;
import game.UserInterface;


public class HumanPlayer implements Strategie{
	private Scanner inScanner = new Scanner(System.in);
	private String userInput = "";
	private Player assignedPlayer;
	public HumanPlayer(Player assignedPlayer) {
		//on a besoin de savoir quel joueur est associé à la stratégie
		this.assignedPlayer=assignedPlayer;
	}
	
	@Override
	public NextPlayer play(boolean isAccused) {
		//interface terminal
		NextPlayer nextPlayer=null;
		do {
			if (isAccused) {//ETRE ACCUSE
				System.out.println("Vous avez ete accuse !");
				do { //on boucle tant que le choix n'est qu'un affichage
					//this.userInput=UserInterface.getInstance().chooseBetween("Voulez-vous reveler votre role (reveal) ou jouer une carte (play). (show hand/show player)", "reveal,play,show hand,show player", false);
					if (chooseToReveal()) {//SE REVELER //old code : userInput.equalsIgnoreCase("reveal")
						nextPlayer = this.assignedPlayer.revealRole();
					}
					else if (userInput.equalsIgnoreCase("play")) {//JOUER UNE CARTE
						Card cardToBePlayed=this.chooseCard(isAccused);
						if (cardToBePlayed!=null) {//peut être null si on fait exit
							nextPlayer = this.assignedPlayer.playCard(cardToBePlayed,true);
						}
					}
					else if (userInput.equalsIgnoreCase("show hand")) {//REGARDER SA MAIN
						System.out.println("Voici votre main");
						this.assignedPlayer.getHand().showCardWithEffect();
					}
					else if (userInput.equalsIgnoreCase("show player")) {//REGARDER LES JOUEURS
						System.out.println("Voici les joueurs");
						PlayerGroup.getInstance(0).showPlayerKnownInfo(assignedPlayer);
					}
				}while(userInput.equalsIgnoreCase("show hand")|| userInput.equalsIgnoreCase("show player"));
			}
			else {//NE PAS ETRE ACCUSE
				do {
					this.userInput=UserInterface.getInstance().chooseBetween("Voulez-vous jouer une carte (play) ou accuser (accuse). (show hand/show player)", "play,accuse,show hand,show player", false);
					if (this.userInput.equalsIgnoreCase("accuse")) {//ACCUSER
						
						List<Player> accusablePlayers=playerGroup.getTarget("accusation",this.assignedPlayer);
						Player accusedPlayer=this.chooseTarget(accusablePlayers,true);
						if (accusedPlayer!=null) {
							nextPlayer=assignedPlayer.accuse(assignedPlayer, accusedPlayer, true);
						}
						
					}
					else if (userInput.equalsIgnoreCase("play")) {//JOUER UNE CARTE
						Card cardToBePlayed=this.chooseCard(isAccused);
						if (cardToBePlayed!=null) {//peut être null si on fait exit
							nextPlayer = this.assignedPlayer.playCard(cardToBePlayed,false);
						}
					}
					else if (userInput.equalsIgnoreCase("show hand")) {//REGARDER SA MAIN
						System.out.println("Voici votre main");
						this.assignedPlayer.getHand().showCardWithEffect();
					}
					else if (userInput.equalsIgnoreCase("show player")) {//REGARDER LES JOUEURS
						System.out.println("Voici les joueurs");
						PlayerGroup.getInstance(0).showPlayerKnownInfo(assignedPlayer);
					}
				}while(userInput.equalsIgnoreCase("show hand")|| userInput.equalsIgnoreCase("show player"));
			}
		}while(nextPlayer==null);
		return nextPlayer;
	}

	@Override
	public void chooseRole() {
		userInput=UserInterface.getInstance().chooseBetween("Choisissez un rôle entre Witch/Villager", "Witch,Villager", false);
		this.assignedPlayer.getRole().setRole(userInput);
	}


	@Override
	public Card chooseCard(boolean isAccused) {
		Card choosenCard=null;
		Hand cards = this.assignedPlayer.getHand();
		userInput=UserInterface.getInstance().chooseBetween("\"Choisissez une carte jouable dans votre main", cards.getPlayableCard(this.assignedPlayer,isAccused).toString(), true);
		
		if (!userInput.equalsIgnoreCase("exit")) {
			choosenCard=cards.getCardByName(userInput);
		}
		
		return choosenCard;
	}
	
	public Card chooseCardToDiscard() {
		Card choosenCard=null;
		Hand cards = this.assignedPlayer.getHand();
		userInput=UserInterface.getInstance().chooseBetween("\"Choisissez une carte à defausser", cards.toString(), false);
		choosenCard=cards.getCardByName(userInput);
		return choosenCard;
	}

	@Override
	public Player chooseTarget(List<Player> targets) {//Surcharge si on ne précise pas si on peut sortir du choix, on met alors faux.
		return this.chooseTarget(targets,false);
	}
	
	public Player chooseTarget(List<Player> targets, boolean canExit) {
		//Nouvelle méthode qui fait partie de stratégie et qui permet de choisir une cible pour les accusations ou le ciblages avec les cartes
		String userInput=UserInterface.getInstance().chooseBetween("Donnez le nom de la cible : ", PlayerGroup.getInstance(0).playerListToString(targets), canExit);
		if (!userInput.equalsIgnoreCase("exit")) {
			return PlayerGroup.getInstance(0).getPlayerByName(userInput);
		}
		return null;
	}
	
	public boolean chooseToReveal() {
		String userInput = UserInterface.getInstance().chooseBetween(assignedPlayer.getName()+" : Voulez-vous reveler votre role (reveal) ou jouer une carte (play). (show hand/show player)", "reveal,play,show hand,show player", false);
		if (userInput.contains("reveal")) {
			return true;
		}
		else return false;
	}
	
}
