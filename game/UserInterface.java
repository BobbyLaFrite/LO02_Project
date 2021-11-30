package game;

import java.util.Scanner;


public class UserInterface { //UserInterface est un singelton
	private static UserInterface instance;
	private Scanner scanner;
	
	
	private UserInterface() { //UserInterface est un singelton
		this.scanner=new Scanner(System.in);
	}
	
																						//canExit permet au joueur de sortir sans choisir (revenir en arri�re)
	public int chooseInt(String firstMessage,int minVal, int maxVal, boolean canExit) { //Fonction qui fait choisir un int.
		String userInput;
		
		System.out.println(firstMessage+" (min "+minVal+" max "+maxVal+")");			//Si on ne souhaite pas retourner de valeur, on met soit exit soit -1		
		
		int output=-1; 				//on initialise sinon eclipse est pas content
		boolean validChoice=false;
		do{		//Boucle do while tant que l'utilisateur n'a pas fait de choix valide
			do {
				userInput = scanner.nextLine(); 
			}while (userInput==null);
			
			if (this.isInteger(userInput)) {
				output=Integer.parseInt(userInput);
				if ((output>=minVal && output<=maxVal) || (output==-1 && canExit)) { 	//La valeur doit se trouver dans l'intervale ou �tre -1 avec un exit autoris�
					validChoice=true;
				} else {
					System.out.println("La valeur choisie doit �tre comprise entre "+minVal+" et "+maxVal+" inclus.\n");
				}
			
			}else if (canExit && userInput.equalsIgnoreCase("exit")) {	//pour utiliser "exit", la sortie doit �tre autoris�e
				output=-1;
				validChoice=true;
			}else {
				System.out.println("\""+userInput+"\" n'est pas une entr�e valide\n");
			}
			
	
		}while(!validChoice);	//tant que le choix n'est pas valide on continue
		
		
		return output;
	}
																		//canExit permet au joueur de sortir sans choisir (revenir en arri�re)
	public String chooseBetween(String firstMessage,String allChoice, boolean canExit) {//choisir parmis plusieurs possibilit�es donn�e dans allChoice s�par�es par ","
		String userInput;
		boolean validChoice=false;
		
		System.out.println(firstMessage);
		
		
		if (canExit) {
			allChoice+=",exit";//on rajoute la possibilit� d'�crire exit
		}
		System.out.println("Choix possibles : "+allChoice+"\n");
		allChoice=","+allChoice+","; //Ainsi tout les choix sont entre "," et on peut v�rifier tr�s facilement si la proposition est compl�te
		
			
		
		
		do{		//Boucle do while tant que l'utilisateur n'a pas fait de choix valide
			
			do {
				userInput = scanner.nextLine(); 
			}while (userInput==null);
			
			if (allChoice.contains(","+userInput+",")) {	//pour utiliser "exit", la sortie doit �tre autoris�e
				validChoice=true;
			}else {
				System.out.println("\""+userInput+"\" n'est pas une entr�e valide\n");
			}
			
	
		} while(!validChoice);	//tant que le choix n'est pas valide on continue
		return userInput;
	}
	
	

	public static UserInterface getInstance() { //UserInterface est un singelton
	
		if (instance==null) {
			instance= new UserInterface();
		}
		return instance;
	}
	
	
	
	private boolean isInteger(String input) {
		Boolean flag=true;
		for(int a=0;a<input.length();a++)
		{
			if(a==0 && input.charAt(a) == '-') {
				continue;
			}
			if( !Character.isDigit(input.charAt(a))) {
				flag=false;
			}
		}
		return flag;
	}
	
	
	
}


