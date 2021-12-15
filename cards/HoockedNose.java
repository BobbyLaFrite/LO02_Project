package cards;
import java.util.Random;

import players.NextPlayer;
import players.Player;
import players.PlayerGroup;



public class HoockedNose extends Card{
	
	
	public HoockedNose() {
		super("Hoocked Nose","Take one card from the hand of the player who accused you.\nTake next turn.","Choose next player.\n Before their turn, take a random card from their hand and add it to your hand.");
	}
	
	public boolean isPlayable(Player actuPlayer,boolean isAccused) {
		return true; //Cette carte est jouable sous toutes les conditions
	}
	
	public NextPlayer activateWitch(Player actuPlayer){
		Player prevPlayer = PlayerGroup.getInstance(0).getPreviousPlayer();
		Card stealedCard;
		if (prevPlayer.getHand().isEmpty()) {
			System.out.println("Hoocked Nose - Witch :\n Le joueur qui vous a accuse n'as plus de carte mais vous prenez le tour !");
		}else {
			
			Random randomNumber = new Random();
			stealedCard=prevPlayer.getHand().getCardByIndex(randomNumber.nextInt(prevPlayer.getHand().getNumberCard()));
			prevPlayer.getHand().giveCard(stealedCard, actuPlayer.getHand());
			System.out.println("Hoocked Nose - Witch :\n Vous avez vole la carte "+stealedCard.getName()+" et vous prenez le tour !");

		}
		return new NextPlayer(actuPlayer,false); 
	}
	
	public NextPlayer activateHunt(Player actuPlayer){ 
		Player target;
		target = super.chooseTarget("alive",actuPlayer);
		
		Card stealedCard;
		if (target.getHand().isEmpty()) {
			System.out.println("Hoocked Nose - Witch :\n Le joueur "+target.getName()+" n'as plus de carte et il prend le tour !");
		}else {
			
			Random randomNumber = new Random();
			stealedCard=target.getHand().getCardByIndex(randomNumber.nextInt(target.getHand().getNumberCard()));
			target.getHand().giveCard(stealedCard, actuPlayer.getHand());
			System.out.println("Hoocked Nose - Witch :\n Vous avez vole la carte "+stealedCard.getName()+" et le tour passe a"+target.getName()+"!");

		}
		return new NextPlayer(target,false); 
		
		
		
		
	}
	
	
}
