package cards;
import java.util.List;
import java.util.ListIterator;

import game.DiscardPile;
import game.UserInterface;
import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class PetNewt extends Card{
	String userInput="";
	UserInterface userInterface = UserInterface.getInstance();
	
	
	public PetNewt() {
		super("Pet Newt","Take next turn","Take a revealed Rumour card from any other player into your hand.\nChoose next player");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		return true; //Toujours jouable
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		System.out.println("Pet Newt - Witch :\n Vous prenez le tour !");
		return new NextPlayer(actuPlayer,false); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		
		String contentBoards = "";
		Player cardBelongsToPlayer = null;
		List<Player> players = PlayerGroup.getInstance(0).getTarget("", actuPlayer);
		for (Player player : players) {
			contentBoards+=player.getBoard().toString();
		}
		userInput = userInterface.chooseBetween("Pet Newt - Hunt :\nChoisissez la carte que vous voulez récupérer : ", contentBoards, false);
		
		for (Player player : players) {//on reparcourt tous les joueurs pour savoir auquel appartient la carte rummeur
			contentBoards=player.getBoard().toString();
			if (contentBoards.contains(userInput)) {
				cardBelongsToPlayer = player;
				break;
			}
		}
		
		cardBelongsToPlayer.getBoard().giveCard(cardBelongsToPlayer.getBoard().getCardByName(userInput), actuPlayer.getHand());
		
		System.out.println("Pet Newt - Hunt :\n Choisissez le prochain joueur !");
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		return new NextPlayer(target, false);
	}
	
	
}
