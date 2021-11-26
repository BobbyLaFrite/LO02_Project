package players;
import java.util.List;

import cards.Card;

public interface Strategie {
	public PlayerGroup playerGroup = PlayerGroup.getInstance(0);
	public NextPlayer play(boolean isAccused);
	public void chooseRole();
	public Card chooseCard();
	public Player chooseTarget(List<Player> targets);
	
}
