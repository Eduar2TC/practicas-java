import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Archivo{
	
	private MatrizColores matrizActual;
	private int tam, minimo, nivel;
	private ArrayList <MatrizColores> combinacionesMatrices;  // contendr? las combinaciones que se generar?n en cada nivel
	private ArrayList <CeldasSeleccionadas> celdasFinales;    // contendr? las celdas que ya han sido seleccionadas, una por cada nivel de combinaciones para que en cada siguiente nueva combinaci?n no las cambie de nuevo.
	private File finales;
	private PrintWriter p;
	
	public Archivo()  throws FileNotFoundException{
		combinacionesMatrices = new ArrayList <MatrizColores>();
		celdasFinales = new ArrayList <CeldasSeleccionadas>();
		tam = 0;
		nivel = 0;
		finales = new File("Hill Climbing.txt");
		p = new PrintWriter(finales);
	}
	
	public void leerArchivo(String ruta) throws FileNotFoundException{

		  Scanner sc = new Scanner(new BufferedReader(new FileReader(ruta)));
		  int cont = 0;
	      
	      while(sc.hasNextLine()) {
	         sc.nextLine();  // Solo lee fila por fila hasta que ya no haya, se obtiene el tama?o de la matriz
	         cont++;
	      }
		  tam = cont;
		  matrizActual = new MatrizColores(cont);
		  
		  // Ahora se prosigue a llenarlo
		  sc.close();
		  sc = new Scanner(new BufferedReader(new FileReader(ruta)));  // scanner lo vuelvo a iniciar
		  while(sc.hasNextLine()) {
	         for (int i=0; i<cont; i++) {
	            String[] linea = sc.nextLine().trim().split(",");
	            for (int j=0; j<cont; j++) {
	               matrizActual.setCeldaMatriz(i,j,linea[j]);
	            }
	         }
	      }
		  sc.close();
		  matrizActual.calcularAdyacentes();
		  minimo = matrizActual.getAdyacentes();
	}		  
	
	public void crearArchivo(int tam) throws FileNotFoundException{
		matrizActual = new MatrizColores(tam);
		File archivo = new File("Archivo.txt");
		PrintWriter p = new PrintWriter(archivo); 		
		String alfabeto = "RGB";
		Random r = new Random();
		this.tam = tam;
		
		for(int i=0; i<tam; i++){
			for(int j=0; j<tam; j++){
				char color = alfabeto.charAt(r.nextInt(alfabeto.length()));
				String c = String.valueOf(color);
				matrizActual.setCeldaMatriz(i,j,c);
				if( j == tam-1 )
					p.print(c);
				else
					p.print(c+",");
			}
			p.println("");
		}
		p.close();
		matrizActual.calcularAdyacentes();
		minimo = matrizActual.getAdyacentes();
	}
	
	public boolean seleccionarEntrada(String[] parametros) throws FileNotFoundException{
		
		int opcion = Integer.parseInt(parametros[0]);
		
		switch(opcion) {
			case 0:{
				//Leer Matriz (COLORES PREDEFINIDOS)
				leerArchivo(parametros[1]); // se le pasa ruta -  nombre del archivo
				break;
			}
			case 1:{
				//Crea Matriz (COLORES ALEATORIOS)
				crearArchivo(Integer.parseInt(parametros[1])); // se le pasa tama?o matriz a crear
				break;
			}
			default:{
				// No se ley? un 0 ni 1  ---> retorna false (hubo error al escribir parametros)
				return false;
			}
		}
		return true;
	}
	
	public void crearCombinaciones(){
		boolean red,green,blue,band;
		
		if( minimo == 0 ){
			p.close();
			System.exit(0);
		}
		
		
		for(int i=0; i<tam; i++){
			for(int j=0; j<tam; j++){
				
				red = false;
				green = false;
				blue = false;
				band = false;
				
				for(int z=0; z<celdasFinales.size(); z++){  // si hay una celda en todo el ArrayList que ya se hab?a seleccionado antes, ya no se le hacen combinaciones
					if(  ( i == celdasFinales.get(z).getFila()  ) && (j == celdasFinales.get(z).getColumna() ) ){
						band = true;
					}
				}
				
				if ( band == false ){ // inician combinaicones
				
					if( matrizActual.getCeldaMatriz(i,j).equals("R") ){
						red = true;
					}
					if( matrizActual.getCeldaMatriz(i,j).equals("G") ){
						green = true;
					}
					if( matrizActual.getCeldaMatriz(i,j).equals("B") ){
						blue = true;
					}
				
					for(int w=1; w<=2; w++){ // dos veces ya que por cada celda siempre solo habr? otras dos combinaciones posibles de los 3 colores (ya hay un color de los 2 restantes)
						MatrizColores combinacion = new MatrizColores(tam);
						combinacion.CeldaCambiada(i,j);
						for( int a=0; a<tam; a++){
							for(int b=0; b<tam; b++){
								combinacion.setCeldaMatriz(a,b,matrizActual.getCeldaMatriz(a,b));
							}
						}

						if( red == false ){
							combinacion.setCeldaMatriz(i,j,"R");
							combinacionesMatrices.add(combinacion);
							red = true;
							continue;
						}
						if( green == false ){
							combinacion.setCeldaMatriz(i,j,"G");
							combinacionesMatrices.add(combinacion);
							green = true;
							continue;
						}
						if( blue == false ){
							combinacion.setCeldaMatriz(i,j,"B");
							combinacionesMatrices.add(combinacion);
							blue = true;
							continue;
						}
					}
				}
			}
		}
		
		for(int i=0; i<combinacionesMatrices.size(); i++){
			combinacionesMatrices.get(i).calcularAdyacentes();
		}
	
		minimo = combinacionesMatrices.get(0).getAdyacentes(); // El n?mero de dyacentes de la primera combincaci?n (matriz) del Array se le asigna el n?mero m?s peque?o
		int posicionMatrizMinima = 0;
		for(int i=1; i<combinacionesMatrices.size(); i++){
			if( combinacionesMatrices.get(i).getAdyacentes() < minimo ){ // si encuentra otra con un valor m?s peque?o, ser? la nueva matriz con valor m?nimo
				minimo = combinacionesMatrices.get(i).getAdyacentes();
				posicionMatrizMinima = i;
			}
		}
		
		int filaAcambiar = combinacionesMatrices.get(posicionMatrizMinima).getFilaCambiada();
		int columnaAcambiar = combinacionesMatrices.get(posicionMatrizMinima).getColumnaCambiada();
		String colorAcambiar =  combinacionesMatrices.get(posicionMatrizMinima).getCeldaMatriz(filaAcambiar,columnaAcambiar);
		
		CeldasSeleccionadas celda = new CeldasSeleccionadas( filaAcambiar , columnaAcambiar );
		celdasFinales.add(celda);
		
		matrizActual.setCeldaMatriz(filaAcambiar,columnaAcambiar,colorAcambiar);
		imprimirCambio(filaAcambiar,columnaAcambiar);
		
		//imprimirCombinaciones();
		combinacionesMatrices.clear(); // se eliminan todas las cobinaciones una vez sido seleccionada una matriz
		
		
	}
	
	public void imprimirCambio(int fila, int columna){
		
		if( nivel == 0 )
			p.println("*** Matriz Inicial ***");
		else
			p.println("*** Nivel "+nivel+": Posicion cambiada: ["+fila+"]["+columna+"] ***");
		for (int i=0; i<tam; i++){
			for(int j=0; j<tam; j++){
				if( j == tam-1 )
					p.print(matrizActual.getCeldaMatriz(i,j));
				else
					p.print(matrizActual.getCeldaMatriz(i,j)+",");
			}
			p.println("");
		}
		p.println("");
		nivel++;
		
	}
		
	public void imprimirCombinaciones(){
		
		System.out.print("\n\n\n...Combinaciones...\n \n \n \n");
		for(int i=0; i<combinacionesMatrices.size(); i++){
			for(int a=0; a<tam; a++){
				for(int b=0; b<tam; b++){
					System.out.print(  combinacionesMatrices.get(i).getCeldaMatriz(a,b)+" "  );
				}
				System.out.print("\n");
			}
			System.out.print("\nADYACENTES = "+combinacionesMatrices.get(i).getAdyacentes());
			System.out.print("\n\n");
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Archivo archivo = new Archivo();
		// Verifica si los parametros sean correctos si no (false) manda mensaje alerta
		if( args.length == 0 || args.length > 2 || archivo.seleccionarEntrada(args) == false ){
			System.out.println("\n\n\nError al ingresar parametros. Solo puede ingresarlos de las dos siguientes formas...\n\nPara leer colores de archivo:  java  Archivo  0  NombreArchivoPorLeer.txt \nPara crear colores a archivo:  java  Archivo  1  tamanoMatriz");
		}
		else {
			// Una vez creada o le?da la matriz inicial...
			archivo.imprimirCambio(0,0);  // ...imprime en archivo HillClimbing.txt por defecto la primer matriz (la que ley? o creo seg?n sea el caso)
			while(true){
				archivo.crearCombinaciones();  // inicia el proceso de crear combinaciones de cada nivel, la condici?n de paro se encuentra en el mismo m?todo
			}	
		}
			
	}
	
}
