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
				System.out.print("Voulez-vous réveler votre role ou jouer une carte ?\nY pour le role N pour la carte");
				userInput = this.inScanner.nextLine();
				if (userInput=="Y") {//SE REVELER
					return assignedPlayer.revealRole();
				}
				else if (userInput=="N") {//JOUER UNE CARTE
					Card cardToBePlayed=this.chooseCard(isAccused);
					if (cardToBePlayed!=null) {//peut être null si on fait exit
						nextPlayer = this.assignedPlayer.playCard(cardToBePlayed,true);
					}
				}
			}
			else {//NE PAS ETRE ACCUSE
				System.out.print("Voulez-vous accuser ou jouer une carte ?\nY pour l'accusation N pour la carte");
				userInput = this.inScanner.nextLine();
				if (userInput.equalsIgnoreCase("Y")) {//ACCUSER
					
					List<Player> accusablePlayers=playerGroup.getTarget("accusation",this.assignedPlayer);
					Player accusedPlayer=this.chooseTarget(accusablePlayers,true);
					if (accusedPlayer!=null) {
						nextPlayer=new NextPlayer(accusedPlayer, true);
					}
					
				}
				else if (userInput.equalsIgnoreCase("N")) {//JOUER UNE CARTE
					Card cardToBePlayed=this.chooseCard(isAccused);
					if (cardToBePlayed!=null) {//peut être null si on fait exit
						nextPlayer = this.assignedPlayer.playCard(cardToBePlayed,false);
					}
				}
			}
		}while(nextPlayer==null);
		return nextPlayer;
	}

	@Override
	public void chooseRole() {
		userInput=UserInterface.getInstance().chooseBetween("Chose your role Witch/Villager", "Witch,Villager", false);
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
	
}
