package players;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import cards.Card;

public class HumanPlayer implements Strategie{
	Scanner inScanner = new Scanner(System.in);
	String userInput = "";
	Player assignedPlayer;
	public HumanPlayer(Player assignedPlayer) {
		//on a besoin de savoir quel joueur est associé à la stratégie
		this.assignedPlayer=assignedPlayer;
	}
	
	@Override
	public void play(boolean isAccused) {
		//interface terminal
		if (isAccused) {
			System.out.print("Voulez-vous réveler votre role ou jouer une carte ?\nY pour le role N pour la carte");
			userInput = inScanner.nextLine();
			if (userInput=="Y") {
				assignedPlayer.revealRole();
			}
			else if (userInput=="N") {
				Card cardToBePlayed=chooseCard();
				assignedPlayer.playCard(cardToBePlayed,isAccused);
			}
		}
		else {
			System.out.print("Voulez-vous accuser ou jouer une carte ?\nY pour l'accusation N pour la carte");
			userInput = inScanner.nextLine();
			if (userInput=="Y") {
				
				//List<Player> accusablePlayers=playerGroup.getTargets("accusation",assignedPlayer);
				assignedPlayer.accuse();
				
			}
			else if (userInput=="N") {
				Card cardToBePlayed=chooseCard();
				//List<Player> accusablePlayers=playerGroup.getTargets("card",assignedPlayer);
				assignedPlayer.playCard(cardToBePlayed,isAccused);
			}
		}
	}

	@Override
	public void chooseRole() {
		System.out.println("Chose your role Witch/Villager");
		userInput = inScanner.nextLine(); 
		assignedPlayer.getRole().setRole(userInput);
	}


	@Override
	public Card chooseCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player chooseTarget(List<Player> targets) {
		// TODO Auto-generated method stub
		//Nouvelle méthode qui fait partie de stratégie et qui permet de choisir une cible pour les accusations ou le ciblages avec les cartes
		System.out.println("Choisissez une cible parmis les suivantes : "+targets.toString());
		System.out.println("Chaque cible est numéroté, en commençant par 0. (Exemple choisissez 1 pour la deuxième cible)");
		userInput=inScanner.nextLine();
		return targets.get(Integer.parseInt(userInput));
	}
	
}
