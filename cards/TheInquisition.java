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
		if (isAccused || (actuPlayer.getRole().getIsRevealed() && actuPlayer.getRole().getRole()=="Villager") && PlayerGroup.getInstance(0).getTarget("accusation",actuPlayer).size()!=0) { //Soit on joue la partie Whtch et alors pas de pb, soit la partie hunt et il faut ?tre un villageois r?v?l? et avoir une cible valide
			return true;
		}else {
			return false;
		}
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		if (!actuPlayer.getHand().isEmpty() && actuPlayer.getHand().getNumberCard()!=1) {

			System.out.println("The Inquisition - Witch :Choisissez une carte a defausser :");
	 		Card choosenCard=actuPlayer.getStrategie().chooseCardToDiscard(actuPlayer.getHand(),this);
			actuPlayer.getBoard().addCard(choosenCard);
			actuPlayer.getHand().removeCard(choosenCard);
 			
		}
		else {
			System.out.println("The Inquisition - Witch :\nVous n'avez pas de carte de a defausser !");
		}
		System.out.println("The Inquisition - Witch : Vous prenez le prochain tour");
		return new NextPlayer(actuPlayer,false); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		System.out.println("The Inquisition - Hunt :\nChoisissez le prochain joueur");
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		if (actuPlayer.getStrategieAsString().equalsIgnoreCase("HumanPlayer")) {
			System.out.println("L'identite du joueur est "+target.getRole().getRole());
		}
		return new NextPlayer(target, false);
	}
	
	
}
