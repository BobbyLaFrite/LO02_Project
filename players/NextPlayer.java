package players;

public class NextPlayer {
	private Player target;
	private boolean isAccused;
	public NextPlayer(Player target, boolean isAccused) {
		this.target=target;
		this.isAccused=isAccused;
	}
	
	
	public Player getTarget() {
		return this.target;
	}
	public boolean getIsAccused() {
		return this.isAccused;
	}
}
