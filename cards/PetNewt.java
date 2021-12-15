package cards;
import java.util.List;

import game.CardContainer;
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
		
		CardContainer boardsContent = new CardContainer();
		Card choosenCard;
		Player cardBelongsToPlayer = null;
		List<Player> players = PlayerGroup.getInstance(0).getTarget("", actuPlayer);
		for (Player player : players) {
			for (Card card : player.getBoard().getCardList()) {
				boardsContent.addCard(card);;
			}
		}
		if (!boardsContent.isEmpty()) {
			System.out.println("Pet Newt - Hunt :\nChoisissez la carte que vous voulez recuperer :");
			choosenCard = actuPlayer.getStrategie().chooseCard(false, boardsContent);
			for (Player player : players) {//on reparcourt tous les joueurs pour savoir auquel appartient la carte rummeur
				boardsContent=player.getBoard();
				if (boardsContent.getCardByName(choosenCard.getName())!=null) {
					cardBelongsToPlayer = player;
				}
			cardBelongsToPlayer.getBoard().giveCard(choosenCard, actuPlayer.getHand());
			}
		}
		else {
			System.out.println("Vous ne pouvez pas recuperer de carte !");
		}
		
		
		
		System.out.println("Pet Newt - Hunt :\n Choisissez le prochain joueur !");
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		return new NextPlayer(target, false);
	}
	
	
}
