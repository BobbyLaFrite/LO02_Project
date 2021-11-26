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
	public NextPlayer play(boolean isAccused) {
		//interface terminal
		if (isAccused) {//ETRE ACCUSE
			System.out.print("Voulez-vous réveler votre role ou jouer une carte ?\nY pour le role N pour la carte");
			userInput = inScanner.nextLine();
			if (userInput=="Y") {//SE REVELER
				return assignedPlayer.revealRole();
			}
			else if (userInput=="N") {//JOUER UNE CARTE
				Card cardToBePlayed=chooseCard();
				NextPlayer nextPlayer = assignedPlayer.playCard(cardToBePlayed,true);
				return nextPlayer;
			}
		}
		else {//NE PAS ETRE ACCUSE
			System.out.print("Voulez-vous accuser ou jouer une carte ?\nY pour l'accusation N pour la carte");
			userInput = inScanner.nextLine();
			if (userInput=="Y") {//ACCUSER
				
				List<Player> accusablePlayers=playerGroup.getTargets("accusation",assignedPlayer);
				Player accusedPlayer=chooseTarget(accusablePlayers);
				return new NextPlayer(accusedPlayer, true);
				
			}
			else if (userInput=="N") {//JOUER UNE CARTE
				Card cardToBePlayed=chooseCard();
				//List<Player> accusablePlayers=playerGroup.getTargets("card",assignedPlayer);
				
				NextPlayer nextPlayer = assignedPlayer.playCard(cardToBePlayed,false);
				return nextPlayer;
			}
		}
		return null;
	}

	@Override
	public void chooseRole() {
		System.out.println("Chose your role Witch/Villager");
		userInput = inScanner.nextLine(); 
		assignedPlayer.getRole().setRole(userInput);
	}


	@Override
	public Card chooseCard() {
		Hand cards = assignedPlayer.getHand();
		System.out.println("Choisissez une carte parmis les suivantes : "+cards.toString());
		//on récupère le nom de la carte
		
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
