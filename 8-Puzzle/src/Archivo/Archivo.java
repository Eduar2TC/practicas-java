package Archivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Archivo {
	
	private String ruta;
	
	public Archivo(String ruta){
		this.ruta = ruta;
	}
	
	public void escribeArchivo() {
		
	}
	
	public String[][] leerArchivo() throws FileNotFoundException{

		String[][] matrizTemp;
		
		  Scanner sc = new Scanner(new BufferedReader(new FileReader(ruta)));
		  int cont = 0;
	      
	      while(sc.hasNextLine()) {
	         sc.nextLine();  // Solo lee fila por fila hasta que ya no haya, se obtiene el tamaño de la matriz
	         cont++;
	      }
	      
		  matrizTemp = new String[cont][cont];
		  
		  // Ahora se prosigue a llenarlo
		  sc.close();
		  sc = new Scanner(new BufferedReader(new FileReader(ruta)));  // scanner lo vuelvo a iniciar
		  while(sc.hasNextLine()) {
			  
	         for (int i=0; i < cont; i++) {
	        	 
	            String[] linea = sc.nextLine().trim().split(",");
	            
	            for (int j=0; j < cont; j++) {	            	
	               matrizTemp[i][j] = linea[j];
	            }
	            
	         }
	      }
		  sc.close();
		  return matrizTemp;
	}
	
}
