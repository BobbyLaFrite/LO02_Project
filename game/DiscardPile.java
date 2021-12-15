package game;

public class DiscardPile extends CardContainer{
	private static DiscardPile instance;
	
	private DiscardPile() { 
		 
		 super();
		 
	}
	
	public static DiscardPile getInstance() {
		if (instance==null) {
			instance= new DiscardPile();
		}
		return instance;
	}
	
	public void init() {
		this.cardList.clear();
	}
	
}
