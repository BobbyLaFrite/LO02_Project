package projetLO02;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class test {


  public static void main(String[] args) {
	  List<String> liste =  new ArrayList<String>() ;

	  // ajout d'�l�ments � cette liste
	 liste.add("un") ;
	 liste.add("deux") ;
	 liste.add("trois") ;

	  // ajout d'un �l�ment � un index
	 liste.add(1,  "avant deux") ;

	  // positionnement d'un �l�ment donn�
	 liste.set(3,  "TROIS") ;
	 
	 ListIterator<String> it = liste.listIterator() ;
	 while(it.hasNext()) {
		 String element = it.next() ;
		 System.out.println(element);
	 }
	 System.out.println(liste);
  }
}