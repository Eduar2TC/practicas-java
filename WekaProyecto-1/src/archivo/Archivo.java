package archivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
/*
 * Datos del archivo :
 * #capas #numeroDeNeuronasPorCapa #epocas # momentum #learningRate
 * */
public class Archivo {
	
	double numeroDeCapas;
	double numeroDeNeuronasPorCapa;
	double epocas;
	double learningRate;
	
	
	
	public ArrayList<ArrayList<Float>> LeeArchivo() {
		String archivo = "src/archivo.txt";
		ArrayList<Float> variables = null;
		ArrayList<ArrayList<Float>> arregloDeFis = new ArrayList<>();
		try {
		      FileInputStream fis = new FileInputStream(archivo);
		      InputStreamReader isr = new InputStreamReader(fis,"utf8");
		      BufferedReader br = new BufferedReader(isr);
		 
		      String linea =null;  //Lee linea por linea
		      int numeroDeColumnas =1; //Columnas por bloque de Fis
		      
		      while((linea = br.readLine()) != null) { 
		    	  
		    	   String[] cadenaLinea = linea.split("\\s+");
		    	   Float[] floats = Arrays.stream(cadenaLinea).map(Float::valueOf).toArray(Float[]::new);
		    	   
		    	   for(int i =0; i <numeroDeColumnas; i++) {
		    		   variables = new ArrayList<>();
			    	   for(int j =0; j < floats.length; j++) {
			    		   variables.add(floats[j]);
			    	   }
			    	   arregloDeFis.add(variables);
		    	   }

		      }
		      fis.close(); //Cerrando archivo
		    }
		    catch(Exception e) {
		      System.out.println("Excepcion leyendo fichero "+ archivo + ": " + e);
		    }
		return arregloDeFis;
	}
	
	public static void main(String args[]) {
		Archivo miArchivo = new Archivo();
		// Ejemplo de ArrayList Bidimensional con multiples Fis en el archivo de texto con 5 parametros
		ArrayList<ArrayList<Float>> ejemploFis = miArchivo.LeeArchivo();
		System.out.println(ejemploFis);
		
	}

}
