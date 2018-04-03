package LDE;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		ListaDuplamenteEncadeada<String> lista  = new ListaDuplamenteEncadeada<>();
		
		lista.adiciona("Queijo");
		lista.adiciona("Presunto");
		//System.out.println(lista.obter(1));
		//lista.remover("Presunto");
		//System.out.println(lista.obter(0));
		
		for(String s : lista){
			System.out.println(s);
		}
		
		
		Iterator x = lista.iterator();
		while(x.hasNext()){
			System.out.println(x.next());
		}
		/*System.out.println("espaço");
		
		
		for (String string : lista) {
			System.out.println(string);
		}
		
		Iterator x = lista.iteratorReverso();
		while(x.hasNext()){
			System.out.println(x.next());
		}*/
		
	}

}
