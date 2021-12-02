package cards;
import game.UserInterface;
import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class TheInquisition extends Card{
	
	UserInterface userInterface = UserInterface.getInstance();
	private String userInput = "";
	public TheInquisition() {
		super("The Inquisition","Discard a card from your hand.\nTake next turn","[Only playable if you have been revealed as villager]\nChoose next player.\nBefore their turn, secretly look at their identity.");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		if (isAccused || (actuPlayer.getRole().getIsRevealed() && actuPlayer.getRole().getRole()=="Villager") && PlayerGroup.getInstance(0).getTarget("accusation",actuPlayer).size()!=0) { //Soit on joue la partie Whtch et alors pas de pb, soit la partie hunt et il faut être un villageois révélé et avoir une cible valide
			return true;
		}else {
			return false;
		}
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		if (!actuPlayer.getHand().isEmpty() && actuPlayer.getHand().getNumberCard()!=1) {

			userInput=userInterface.chooseBetween("The Inquisition - Witch :Choisissez une carte \u00e0 d\u00e9fausser :", actuPlayer.getHand().toString(), false);
	 		Card choosenCard=actuPlayer.getHand().getCardByName(userInput);
			actuPlayer.getBoard().addCard(choosenCard);
			actuPlayer.getHand().removeCard(choosenCard);
 			
		}
		else {
			System.out.println("The Inquisition - Witch :\nVous n'avez pas de carte \u00e0 d\u00e9fausser !");
		}
		System.out.println("The Inquisition - Witch : Vous prenez le prochain tour");
		return new NextPlayer(actuPlayer,false); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		System.out.println("The Inquisition - Hunt :\nChoisissez le prochain joueur");
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		System.out.println("L'identit\u00e9e du joueur est "+target.getRole().getRole());
		return new NextPlayer(target, false);
	}
	
	
}
