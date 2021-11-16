package projetLO02;

public class Role {
	private String chosenRole;
	private boolean isRevealed;
	
	public void init() {
		this.chosenRole=null;
		this.isRevealed = false;
	}
	
	
	public void choseRole(String chosenRole) {
		this.chosenRole=chosenRole;
	}
	
}
