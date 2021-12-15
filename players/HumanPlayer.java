package players;

import java.util.List;
import java.util.Scanner;

import cards.Card;
import game.CardContainer;
import game.UserInterface;


public class HumanPlayer implements Strategie{
	private Scanner inScanner = new Scanner(System.in);
	private String userInput = "";
	public HumanPlayer() {
	}
	
//	@Override
//	public NextPlayer play(boolean isAccused) {
//		//interface terminal
//		NextPlayer nextPlayer=null;
//		do {
//			if (isAccused) {//ETRE ACCUSE
//				System.out.println("Vous avez ete accuse !");
//				do { //on boucle tant que le choix n'est qu'un affichage
//					//this.userInput=UI.chooseBetween("Voulez-vous reveler votre role (reveal) ou jouer une carte (play). (show hand/show player)", "reveal,play,show hand,show player", false);
//					if (chooseToReveal()) {//SE REVELER //old code : userInput.equalsIgnoreCase("reveal")
//						nextPlayer = this.assignedPlayer.revealRole();
//					}
//					else if (userInput.equalsIgnoreCase("play")) {//JOUER UNE CARTE
//						Card cardToBePlayed=this.chooseCard(isAccused);
//						if (cardToBePlayed!=null) {//peut être null si on fait exit
//							nextPlayer = this.assignedPlayer.playCard(cardToBePlayed,true);
//						}
//					}
//					else if (userInput.equalsIgnoreCase("show hand")) {//REGARDER SA MAIN
//						System.out.println("Voici votre main");
//						this.assignedPlayer.getHand().showCardWithEffect();
//					}
//					else if (userInput.equalsIgnoreCase("show player")) {//REGARDER LES JOUEURS
//						System.out.println("Voici les joueurs");
//						playerGroup.showPlayerKnownInfo(assignedPlayer);
//					}
//				}while(userInput.equalsIgnoreCase("show hand")|| userInput.equalsIgnoreCase("show player"));
//			}
//			else {//NE PAS ETRE ACCUSE
//				do {
//					this.userInput=UI.chooseBetween("Voulez-vous jouer une carte (play) ou accuser (accuse). (show hand/show player)", "play,accuse,show hand,show player", false);
//					if (this.userInput.equalsIgnoreCase("accuse")) {//ACCUSER
//						
//						List<Player> accusablePlayers=playerGroup.getTarget("accusation",this.assignedPlayer);
//						Player accusedPlayer=this.chooseTarget(accusablePlayers,true);
//						if (accusedPlayer!=null) {
//							nextPlayer=assignedPlayer.accuse(assignedPlayer, accusedPlayer, true);
//						}
//						
//					}
//					else if (userInput.equalsIgnoreCase("play")) {//JOUER UNE CARTE
//						Card cardToBePlayed=this.chooseCard(isAccused);
//						if (cardToBePlayed!=null) {//peut être null si on fait exit
//							nextPlayer = this.assignedPlayer.playCard(cardToBePlayed,false);
//						}
//					}
//					else if (userInput.equalsIgnoreCase("show hand")) {//REGARDER SA MAIN
//						System.out.println("Voici votre main");
//						this.assignedPlayer.getHand().showCardWithEffect();
//					}
//					else if (userInput.equalsIgnoreCase("show player")) {//REGARDER LES JOUEURS
//						System.out.println("Voici les joueurs");
//						playerGroup.showPlayerKnownInfo(assignedPlayer);
//					}
//				}while(userInput.equalsIgnoreCase("show hand")|| userInput.equalsIgnoreCase("show player"));
//			}
//		}while(nextPlayer==null);
//		return nextPlayer;
//	}

	public boolean chooseAction(boolean isAccused,CardContainer playableCards) {
		boolean result=false;
		boolean isResultChoosen=false;
		do {
			if (isAccused) {//ETRE ACCUSE
				System.out.println("Vous avez ete accuse !");
				do {
					userInput=UI.chooseBetween("Voulez-vous reveler votre role (reveal) ou jouer une carte (play). (show hand/show player)", "reveal,play,show hand,show player", false);
					if(userInput.equalsIgnoreCase("reveal")) {//SE REVELER
						result = true;
						isResultChoosen=true;
					}
					else if(userInput.equalsIgnoreCase("play")&&!playableCards.isEmpty()){//JOUER UNE CARTE
							result=false;
							isResultChoosen=true;
						
					}
					else if (userInput.equalsIgnoreCase("play")&&playableCards.isEmpty()) {
						System.out.println("Vous ne pouvez pas jouer de carte ! Vous n'en avez plus !");
					}
					else if (userInput.equalsIgnoreCase("show hand")) {
						System.out.println("Voici vos carte jouables de votre main");//TODO changer cartes jouables en main ?
						playableCards.showCardWithEffect();
					}
					else if(userInput.equalsIgnoreCase("show player")){
						System.out.println("Voici les joueurs");
						playerGroup.showPlayerKnownInfo(null);// TODO changer ça
					}
					
				}while (userInput.equalsIgnoreCase("show hand")||userInput.equalsIgnoreCase("show player"));
			}
			else {
				do {
					this.userInput=UI.chooseBetween("Voulez-vous jouer une carte (play) ou accuser (accuse). (show hand/show player)", "play,accuse,show hand,show player", false);
					if (this.userInput.equalsIgnoreCase("accuse")) {//ACCUSER
						
						result = true;
						isResultChoosen=true;
						
					}
					else if (userInput.equalsIgnoreCase("play")) {//JOUER UNE CARTE
						result = false;
						isResultChoosen = true; 
					}
					else if (userInput.equalsIgnoreCase("show hand")) {//REGARDER SA MAIN
						System.out.println("Voici vos carte jouables de votre main");//TODO changer cartes jouables en main ?
						playableCards.showCardWithEffect();
					}
					else if (userInput.equalsIgnoreCase("show player")) {//REGARDER LES JOUEURS
						System.out.println("Voici les joueurs");
						playerGroup.showPlayerKnownInfo(null);// TODO changer ça
					}
				}while(userInput.equalsIgnoreCase("show hand")|| userInput.equalsIgnoreCase("show player"));	
			}
			
		}while (!isResultChoosen);
		
		
		return result;
	}

	@Override
	public String chooseRole() {
		userInput=UI.chooseBetween("Choisissez un rôle entre Witch/Villager", "Witch,Villager", false);
		return userInput;
	}


	@Override
	public Card chooseCard(boolean isAccused,CardContainer playableCards) {
		Card choosenCard=null;
		userInput=UI.chooseBetween("\"Choisissez une carte jouable dans votre main", playableCards.toString(), true);
		
		if (!userInput.equalsIgnoreCase("exit")) {
			choosenCard=playableCards.getCardByName(userInput);
		}
		
		return choosenCard;
	}
	
	public Card chooseCardToDiscard(Hand hand,Card exception) {
		Card choosenCard=null;
		userInput=UI.chooseBetween("\"Choisissez une carte à defausser", hand.toString(), false);
		choosenCard=hand.getCardByName(userInput);
		return choosenCard;
	}

	@Override
	public Player chooseTarget(List<Player> targets) {//Surcharge si on ne précise pas si on peut sortir du choix, on met alors faux.
		return this.chooseTarget(targets,false);
	}
	
	public Player chooseTarget(List<Player> targets, boolean canExit) {
		//Nouvelle méthode qui fait partie de stratégie et qui permet de choisir une cible pour les accusations ou le ciblages avec les cartes
		String userInput=UI.chooseBetween("Donnez le nom de la cible : ", playerGroup.playerListToString(targets), canExit);
		if (!userInput.equalsIgnoreCase("exit")) {
			return playerGroup.getPlayerByName(userInput);
		}
		return null;
	}
	
	public boolean chooseToReveal(CardContainer playableCards) {
		String userInput = UI.chooseBetween("Voulez-vous reveler votre role (reveal) ou jouer une carte (play). (show hand/show player)", "reveal,play,show hand,show player", false);
		if (userInput.contains("reveal")) {
			return true;
		}
		else return false;
	}
	
}
