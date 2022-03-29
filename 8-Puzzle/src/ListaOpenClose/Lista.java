package ListaOpenClose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Matriz.Matriz;
public class Lista {
	
	private static List<Matriz> listaOpen; //almacena matrices en el arreglo listaOpen(Nodo)
	private static List<Matriz> listaClose; //almacena matrices en el arreglo listaClose(Nodo) Trayecto
	
	
	public static void main(String[] args) throws IOException{
	
	Matriz matrix1 = new Matriz();
	Matriz matrix2 = new Matriz();
	
	listaOpen = new ArrayList<Matriz>();
	
	listaOpen.add(matrix1);
	listaOpen.add(matrix2);
	
	  for (Matriz n : listaOpen) {
	        n.imprimeMatriz();
	    }
	
	
	
	}

}
