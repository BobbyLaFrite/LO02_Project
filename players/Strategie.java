package players;
import java.util.List;
import cards.Card;
import game.CardContainer;
import game.UserInterface;

public interface Strategie {
	public PlayerGroup playerGroup = PlayerGroup.getInstance(0);
	public UserInterface UI=UserInterface.getInstance();
	public boolean chooseAction(boolean isAccused,CardContainer playableCards);
	public String chooseRole();
	public Card chooseCard(boolean isAccused,CardContainer playableCards);
	public Card chooseCardToDiscard(Hand hand,Card exception);
	public Player chooseTarget(List<Player> targets);
	public boolean chooseToReveal(CardContainer playableCards);
	
}
