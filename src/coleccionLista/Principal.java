package coleccionLista;

import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Triangulo> trians = new ArrayList<Triangulo>();
		
    	// Añadimos tres elementos a la lista
		trians.add(new Triangulo(1, 2, 3));
		trians.add(new Triangulo(1, 1, 2));
		trians.add(new Triangulo(1, 1, 1));
		
		// trians.size() nos devuelve el número total de elementos guardados.
		System.out.println("Nº de elementos guardados: " + trians.size());
		System.out.println("Accediendo a la posición 1");
		    	// trians.get(1) nos devuelve el elemento que ocupa la posición 1, 
		//es decir, el segundo elemento, ya que el primer elemento es el 0.
		System.out.println(trians.get(1).verTipo());
		
		System.out.println("Recorriendo todos los elementos");
		for (int i=0; i<trians.size(); i++) {
			System.out.println(trians.get(i).verTipo()); 
		}

	}

}
