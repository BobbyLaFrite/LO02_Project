package projetLO02;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Collections;
public class CardContainer {
	
	private List<Card> cardList;
	
	public CardContainer() {
		 this.cardList =  new ArrayList<Card>() ;
	}
	
	public boolean isEmpty() {
		return this.cardList.isEmpty();
	}
	
	public void clear() {
		
		this.cardList.clear();
	}
	
	public void init() {
		this.cardList.clear();
	}
	
	public Card getCardByIndex(int index) {
		return this.cardList.get(index);
	}
	
	public void addCard(Card cardToAdd) {
		this.cardList.add(cardToAdd);
	}
	
	public void removeCard(Card cardToRemove) {
		this.cardList.remove(cardToRemove);
	}
	
	public void removeCard(int indexCardToRemove) {//surcharge avec seulement l'index comme référence
		this.removeCard(this.getCardByIndex(indexCardToRemove));
	}
	
	public void shuffle() {
		Collections.shuffle(this.cardList);
	}
	
	public void giveCard(Card cardToGive ,CardContainer containerToGive) { //fonction qui supprime une carte pour l'ajouter à un autre container. 
			//##Aucune sécurité, si carte existe pas##
		this.removeCard(cardToGive);
		containerToGive.addCard(cardToGive);
	}
	
	public void giveCard(int indexCardToGive ,CardContainer containerToGive) { //Surcharge avec unindex plutot que la carte directement en parametre
		
		this.giveCard(this.getCardByIndex(indexCardToGive) , containerToGive);
	}
	
	public void giveRandomCard(CardContainer containerToGive,int cardNumber) { // donner une carte aléatoire, utile pour la distribution notament
		 for (int i = 0; i < cardNumber; i++) { 
			 int index = (int)(Math.random() * this.cardList.size());
			 this.giveCard(index, containerToGive);
		 	} 
	}
	
	public void giveRandomCard(CardContainer containerToGive) { //surcharge de la méthode si on veut donner qu'une carte
		this.giveRandomCard(containerToGive,1);
	}
	
	public String toString () { 	//Retourne un string sous forme [nomde carte 1, nom de carte 2 ... ]
		String content = "["; 		//content correspond au string qu'on retourne à la fin. On lui ajoute tout les nomsde carte
		ListIterator<Card> it = this.cardList.listIterator() ; //on crée un iterateur pour parcourir toute les cartes
		 while(it.hasNext()) {
			  content+= it.next().name ;
			  if (it.hasNext()) {
				  content+=" , ";
			  }
		 }
		 content+="]";
		 return content;
	}
	
}
