package game;

public class Role {
	private String chosenRole;
	private boolean isRevealed;
	
	public void init() {
		this.chosenRole=null;
		this.isRevealed = false;
	}
	
	
	public void setRole(String chosenRole) {
		this.chosenRole=chosenRole;
	}
	
	public String getRole() {
		return this.chosenRole;
	}
	
	public boolean getIsRevealed() {
		return this.isRevealed;
	}
	public void reveal() {
		this.isRevealed=true;
	}
	
}
