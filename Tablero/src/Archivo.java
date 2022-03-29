import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Archivo{
	
	private static String[][] matrizInicial; 
	
	
	public void leerArchivo(String ruta) throws FileNotFoundException{

		  Scanner sc = new Scanner(new BufferedReader(new FileReader(ruta)));
		  int cont = 0;
	      
	      while(sc.hasNextLine()) {
	         sc.nextLine();  // Solo lee fila por fila hasta que ya no haya, se obtiene el tamaño de la matriz
	         cont++;
	      }
		  matrizInicial = new String[cont][cont];
		  
		  
		  // Ahora se prosigue a llenarlo
		  sc.close();
		  sc = new Scanner(new BufferedReader(new FileReader(ruta)));  // scanner lo vuelvo a iniciar
		  while(sc.hasNextLine()) {
	         for (int i=0; i < cont; i++) {
	            String[] linea = sc.nextLine().trim().split(",");
	            for (int j=0; j < cont; j++) {
	               matrizInicial[i][j] = linea[j];
				   System.out.print(matrizInicial[i][j]+" ");
	            }
				System.out.print("\n");
	         }
	      }
		  sc.close();
	}		  
	
	public void crearArchivo(int tam) throws FileNotFoundException{
		
		matrizInicial = new String[tam][tam];
		File archivo = new File("src/archivo.txt");
		PrintWriter p = new PrintWriter(archivo); 		
		String alfabeto = "RGB";
		Random r = new Random();
		
		for(int i=0; i<tam; i++){
			for(int j=0; j<tam; j++){
				char color = alfabeto.charAt(r.nextInt(alfabeto.length()));
				String c = String.valueOf(color);
				matrizInicial[i][j] = c;
				if( j == tam-1 )
					p.print(c);
				else
					p.print(c+",");
			}
			p.print("\n");
		}
		p.close();
	}
	
	public boolean seleccionarCaso(String[] parametro) throws FileNotFoundException{
		
		int opcion = Integer.parseInt(parametro[0]);
		
		if(opcion == 0) {
			//Leer Matriz (COLORES PREDEFINIDOS)
			leerArchivo(parametro[1]); // se le pasa ruta -  nombre del archivo
			return true;
		}
		else if(opcion == 1) {
			//Crea Matriz (COLORES ALEATORIOS)
			crearArchivo(Integer.parseInt(parametro[1])); // se le pasa tamaño matriz a crear
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void imprimeMatriz(String [][] matriz){
		System.out.print("\n");
		for (int i = 0; i< matriz.length; i++) {
			for(int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j]+ " ");
			}
			System.out.print("\n");
		}
		
	}
	
	public String [][] procedaMatriz(String [][] matriz){
		/*El recorrido es en modo diagonal busca y compara con los valores adyacentes*/
		
		int contadorDeAdyecencias =0;
		for(int i =0; i <= matriz.length; i++) {
			for(int j =0; j <= matriz[0].length; j++ ) {
				
				if(i == 0 && j == 0) {  //sup izq
					
					if( matriz[i][j].equals( matriz[i][j+1]) ) {
							contadorDeAdyecencias++;
					}
					if( matriz[i][j].equals(matriz[i+1][j]) ) {
						contadorDeAdyecencias++;						
					}
					
				}

				else if(i ==0 && j == 2) {  //superior der
					
					if( matriz[i][j].equals( matriz[i][j-1]) ) {
							contadorDeAdyecencias++;
					}
					else if( matriz[i][j].equals(matriz[i+1][j]) ) {
						contadorDeAdyecencias++;						
					}
				}
				
				else if( i==2 && j == 0 ) { //inferior izq
					if( matriz[i][j].equals( matriz[i-1][j]) ) {
						contadorDeAdyecencias++;
					}
					if( matriz[i][j].equals( matriz[i][j+1]) ) {
						contadorDeAdyecencias++;	
					}
				}
				
				else if( i==2 && j == 2 ) { //inferior derecha
					if(matriz[i][j].equals( matriz[i-1][j]) ) {
						contadorDeAdyecencias++;
					}
					if(matriz[i][j].equals( matriz[i][j-1])) {
						contadorDeAdyecencias++;					
					}
				}
				
				else if(i == 0 && j <= 2 ) { //renglon sup
					if( matriz[i][j].equals( matriz[i][j+1]) ) {
						contadorDeAdyecencias++;
					}					
					if( matriz[i][j].equals( matriz[i][j-1]) ) {
						contadorDeAdyecencias++;
					}					
				}
				else if(i == 2 && j <= 2 ) { //renglon inf
					if( matriz[i][j].equals( matriz[i][j+1]) ) {
						contadorDeAdyecencias++;
					}					
					if( matriz[i][j].equals( matriz[i][j-1]) ) {
						contadorDeAdyecencias++;
					}					
				}
				else if(j == 0 && i <= 2 ) { //columna izq
					if( matriz[i][j].equals( matriz[i+1][j]) ) {
						contadorDeAdyecencias++;
					}
					if( matriz[i][j].equals( matriz[i-1][j]) ) {
						contadorDeAdyecencias++;
					}	
					
				}

				else if(j == 2 && i <= 2 ) { //columna der
					if( matriz[i][j].equals( matriz[i-1][j]) ) {
						contadorDeAdyecencias++;
					}					
					
				}
				
				else if((i > 0 && i <2) && (j > 0 && j <2) ) {
					if(matriz[i][j].equals( matriz[i-1][j])) {
						contadorDeAdyecencias++;
					}
					if(matriz[i][j].equals( matriz[i+1][j]) ) {
						contadorDeAdyecencias++;
					}
					if ( matriz[i][j].equals( matriz[i][j-1]) ) {
						contadorDeAdyecencias++;
					}
					if ( matriz[i][j].equals( matriz[i][j+1]) ) {
						contadorDeAdyecencias++;
					}
				}
				
			}
		}
		System.out.print("Adyacencias: " + contadorDeAdyecencias);
		return null;
		
	}
	
	
	
	public void menuPrincipal() {
		System.out.println("1) Lectura de datos : 'tamanioMatriz' 'nombreaArchivo.txt'\n2) CrearMatriz de colores: 'numeroOpcion' 'tamanioMatriz'\n Entrada : ");
	}
	
	public static void main(String[] args) throws IOException{
		
		Archivo archivo = new Archivo();
		// Verifica si los parametros sean correctos si no (false) manda mensaje alerta
		if( args.length == 0 || args.length > 2 || archivo.seleccionarCaso(args) == false ){
			
			System.out.println("\n\n\nError al ingresar parametros. Solo puede ingresarlos de las dos siguientes formas...\n\nPara leer colores de archivo:  java  Archivo  0  NombreArchivoPorLeer.txt \nPara crear colores a archivo:  java  Archivo  1  tamanoMatriz");
		}
		else {
			archivo.imprimeMatriz(matrizInicial);
			archivo.procedaMatriz(matrizInicial);
		}
		
	}
}
