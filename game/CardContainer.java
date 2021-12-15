package game;
import java.util.Iterator;
import java.util.LinkedHashSet;
import cards.Card;



public class CardContainer {
	
	protected LinkedHashSet<Card> cardList;
	
	
	
	public CardContainer() {
		 this.cardList =  new LinkedHashSet<Card>();
	}
	
	public LinkedHashSet<Card> getCardList() {
		return this.cardList;
	}
	
	public boolean isEmpty() {
		return this.cardList.isEmpty();
	}
	
	public int getNumberCard() {
		return this.cardList.size();
	}
	
	public void clear() {
		
		this.cardList.clear();
	}
		
	public Card getCardByIndex(int index) {//parcourt le linkedhashset pour renvoyer le nième élément
		Iterator<Card> it = cardList.iterator();
		Card currentCard=null;
		for (int i = 0;i<index+1;i++) {
			if (it.hasNext()) {
				currentCard=it.next();
			}
		}
		return currentCard;
	}
	
	public Card getCardByName(String name) {
		Iterator<Card> it = cardList.iterator();
		Card currentCard=null;
		while (it.hasNext()) {//On continue tant que le nom ne correspond pas
			currentCard=it.next();
			if (currentCard.getName().equalsIgnoreCase(name)) {
				break;
			}
		}
		return currentCard;
	}
	
	public void addCard(Card cardToAdd) {
		this.cardList.add(cardToAdd);
	}
	
	public void removeCard(Card cardToRemove) {
		this.cardList.remove(cardToRemove);
	}
	
	public void removeCard(int indexCardToRemove) {//surcharge avec seulement l'index comme r�f�rence
		this.removeCard(this.getCardByIndex(indexCardToRemove));
	}
	
	
	public void giveCard(Card cardToGive ,CardContainer containerToGive) { //fonction qui supprime une carte pour l'ajouter � un autre container. 
			//##Aucune s�curit�, si carte existe pas##
		this.removeCard(cardToGive);
		containerToGive.addCard(cardToGive);
	}
	
	public void giveCard(int indexCardToGive ,CardContainer containerToGive) { //Surcharge avec unindex plutot que la carte directement en parametre
		
		this.giveCard(this.getCardByIndex(indexCardToGive) , containerToGive);
	}
	
	public void giveRandomCard(CardContainer containerToGive,int cardNumber) { // donner des cartes al�atoirement, utile pour la distribution notament
		 for (int i = 0; i < cardNumber; i++) { 
			 int index = (int)(Math.random() * this.cardList.size());
			 this.giveCard(index, containerToGive);
		 	} 
	}
	
	public void giveRandomCard(CardContainer containerToGive) { //surcharge de la m�thode si on veut donner qu'une carte
		this.giveRandomCard(containerToGive,1);
	}
	
	public void showCardWithEffect() {
		Card actuCard;
		Iterator<Card> it = this.cardList.iterator(); //on cree un iterateur pour parcourir toute les cartes
		 while(it.hasNext()) { 
			 actuCard= it.next();
			 System.out.println("\u007c"+"=".repeat(20));
			 System.out.println("\u007c"+"\t"+actuCard.getName());
			 System.out.println("\u007c"+"~".repeat(20));
			 System.out.println("\u007c"+"\tWitch");
			 System.out.println("\u007c"+actuCard.getWitchEffect().replace("\n", "\n\u007c"));
			 System.out.println("\u007c"+"~".repeat(20));
			 System.out.println("\u007c"+"\tHunt");
			 System.out.println("\u007c"+actuCard.getHuntEffect().replace("\n", "\n\u007c"));
			 System.out.println("\u007c"+"=".repeat(20));
			 System.out.println("\n");
		 }
	}
	
	public String toString () { 	//Retourne un string sous forme nomde carte 1, nom de carte 2 ... 
		String content = ""; 		//content correspond au string qu'on retourne � la fin. On lui ajoute tout les nomsde carte
		Iterator<Card> it = this.cardList.iterator(); //on cr�e un iterateur pour parcourir toute les cartes
		 while(it.hasNext()) { 
			 content+= it.next().getName() ;
			  if (it.hasNext()) {  
				  content+=",";
			  }
		 }
		 return content;
	}
	
}
