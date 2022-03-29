package Matriz;

import java.io.FileNotFoundException;
import Archivo.Archivo;

public class Matriz {
	
	String[][] matrizInicial;
	
	
	public Matriz() throws FileNotFoundException {
		Archivo archivo = new Archivo("src/Archivo/archivo.txt");
		this.matrizInicial = archivo.leerArchivo();
	}
	
	public void imprimeMatriz() {
        for (int i=0; i < matrizInicial[0].length; i++) {
        	
            for (int j=0; j < matrizInicial[i].length; j++) {
				   System.out.print(matrizInicial[i][j]+" ");
            }
            
			System.out.print("\n");
         }
	}
	

}
