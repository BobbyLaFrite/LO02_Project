package cards;
import game.DiscardPile;
import game.UserInterface;
import players.NextPlayer;
import players.Player;



public class BlackHat extends Card{
	String userInput="";
	UserInterface userInterface = UserInterface.getInstance();
	DiscardPile discardPile = DiscardPile.getInstance();
	
	public BlackHat() {
		super("Black Hat","Take next turn","Add one discarded card to your hand, and then discard this card.\nTake next turn");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		return true; //Toujours jouable
	}
	
	public NextPlayer activateWitch(Player actuPlayer){ 
		System.out.println("Black Hat - Witch :\n Vous prenez le tour !");
		return new NextPlayer(actuPlayer,false); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		if (discardPile.isEmpty()) {
			System.out.println("La pile de défausse est vide !");
		}
		else {
			userInput = userInterface.chooseBetween("Black Hat - Hunt : Choisissez une carte parmis les suivantes : ", discardPile.toString(), false);
			discardPile.giveCard(discardPile.getCardByName(userInput), actuPlayer.getHand());//prend une carte de la défausse
			//actuPlayer.getHand().giveCard(this, discardPile);//met cette carte dans la défausse ##se fait d�ja normalement?
			
		}
		System.out.println("Black Hat - Hunt :\n Vous prenez le tour !");
		return new NextPlayer(actuPlayer,false); 
	}
	
	
}
