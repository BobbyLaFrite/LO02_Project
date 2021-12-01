package game;

import java.util.Scanner;


public class UserInterface { //UserInterface est un singelton
	private static UserInterface instance;
	private Scanner scanner;
	
	
	private UserInterface() { //UserInterface est un singelton
		this.scanner=new Scanner(System.in);
	}
	
																						//canExit permet au joueur de sortir sans choisir (revenir en arrière)
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
				if ((output>=minVal && output<=maxVal) || (output==-1 && canExit)) { 	//La valeur doit se trouver dans l'intervale ou être -1 avec un exit autorisé
					validChoice=true;
				} else {
					System.out.println("La valeur choisie doit être comprise entre "+minVal+" et "+maxVal+" inclus.\n");
				}
			
			}else if (canExit && userInput.equalsIgnoreCase("exit")) {	//pour utiliser "exit", la sortie doit être autorisée
				output=-1;
				validChoice=true;
			}else {
				System.out.println("\""+userInput+"\" n'est pas une entrée valide\n");
			}
			
	
		}while(!validChoice);	//tant que le choix n'est pas valide on continue
		
		
		return output;
	}
																		//canExit permet au joueur de sortir sans choisir (revenir en arrière)
	public String chooseBetween(String firstMessage,String allChoice, boolean canExit) {//choisir parmis plusieurs possibilitées donnée dans allChoice séparées par ","
		String userInput;
		boolean validChoice=false;
		
		System.out.println(firstMessage);
		
		
		if (canExit) {
			allChoice+=",exit";//on rajoute la possibilité d'écrire exit
		}
		System.out.println("Choix possibles : "+allChoice+"\n");
		allChoice=","+allChoice+","; //Ainsi tout les choix sont entre "," et on peut vérifier très facilement si la proposition est complète
		
			
		
		
		do{		//Boucle do while tant que l'utilisateur n'a pas fait de choix valide
			
			do {
				userInput = scanner.nextLine(); 
			}while (userInput==null);
			
			if (allChoice.contains(","+userInput+",")) {	//pour utiliser "exit", la sortie doit être autorisée
				validChoice=true;
			}else {
				System.out.println("\""+userInput+"\" n'est pas une entrée valide\n");
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


