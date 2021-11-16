package projetLO02;

import java.util.Scanner;
import java.util.Random;

public class Player {
	Hand hand;
	Board board;
	int playerIndex;
	Role role;
	boolean controlable; //##temporaire##
	
	public Player() {
		this.hand=new Hand();
		this.board=new Board();
		this.role = new Role();
		this.controlable=false; //## temporaire, sert a  differencier les joueurs des ia##
	}
	
	
	
	public void chooseRole() {
		
		if (this.controlable) {
			Scanner scanner = new Scanner(System.in);
			String userInput;
			
			System.out.println("Chose your role Witch/Villager");
			userInput = scanner.nextLine(); 
			this.role.choseRole(userInput);
		} else {
			this.role.choseRole((String) (new Random().nextBoolean() ? "Witch" : "Villager"));
			
		}
		
	}
	
	
}
