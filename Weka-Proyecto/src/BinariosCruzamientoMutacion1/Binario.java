package BinariosCruzamientoMutacion1;

import java.util.ArrayList;

public class Binario {
	
	private ArrayList<int[]> arreglodDeArreglosBinariosTemp;
	public int generaAleatorio(int tamanioArreglo) {

		//genera los valores aleatorios
		int numeroAleatorio = 1 + (int)(Math.random() * (((tamanioArreglo -1)- 1) + 1));
		
		return numeroAleatorio ;
	}
	
	public int[] unirArreglos(int arregloBinario1[], int arregloBinario2[]) {
		
		int tamanio = arregloBinario1.length + arregloBinario2.length;
		int resultadoArreglo[] = new int[tamanio];
		System.arraycopy(arregloBinario2, 0, resultadoArreglo, 0, arregloBinario2.length);
		
		System.arraycopy(arregloBinario1, 0, resultadoArreglo, arregloBinario2.length, arregloBinario1.length);
		
		
		return  resultadoArreglo;
	}
	
	public int[] tipoCruzamiento(int arregloBinario1[], int arregloBinario2[], int metodo) {
		int resultadoCruze [] = null;
		int tamanioDeLosArreglos = arregloBinario1.length;  //Obtenemos el tamanio de los arreglos
		int valorAleatorio = this.generaAleatorio(tamanioDeLosArreglos);
		int arregloIzquierda1[] = new int[valorAleatorio];
		int arregloDerecha1[] = new int[tamanioDeLosArreglos - valorAleatorio ];
		
		//Intercambios
		switch(metodo) {
			case 1:{
				//Obtiene lado izquierdo del arreglo Binario 1
				for(int i =0; i< valorAleatorio; i++) {
					arregloIzquierda1[i] = arregloBinario1[i];
				}
				//Obtiene lado derecho del arreglo Binario 2
				int j =0;
				for(int i = valorAleatorio ; i < arregloBinario2.length; i++) {
					arregloDerecha1[j] = arregloBinario2[i];
					j++;
				}
				resultadoCruze = this.unirArreglos(arregloIzquierda1, arregloDerecha1);
				break;
				
			}
			case 2:{
				//Obtiene lado izquierdo del arreglo Binario 2
				
				for(int i =0; i< arregloIzquierda1.length; i++) {
					arregloIzquierda1[i] = arregloBinario2[i];
				}
				
				//Obtiene lado derecho del arreglo Binario 1
				int j =0;
				for(int i = valorAleatorio ; i < arregloBinario1.length; i++) {
					arregloDerecha1[j] = arregloBinario1[i];
					j++;
				}
				
				resultadoCruze = this.unirArreglos(arregloIzquierda1, arregloDerecha1);
				break;

			}
			case 3:{
				
				//Obtiene lado derecho del arreglo Binario 2
				int j =0;
				for(int i = valorAleatorio ; i < arregloBinario2.length; i++) {
					arregloDerecha1[j] = arregloBinario2[i];
					j++;
				}
				
				//Obtiene lado izquierdo del arreglo Binario 1
				for(int i =0; i< arregloIzquierda1.length; i++) {
					arregloIzquierda1[i] = arregloBinario1[i];
				}
				
				resultadoCruze = this.unirArreglos(arregloIzquierda1, arregloDerecha1);
				break;				
			}
			case 4:{
				//Obtiene lado izquierdo del arreglo Binario 2
				for(int i =0; i< arregloIzquierda1.length; i++) {
					arregloIzquierda1[i] = arregloBinario2[i];
				}
				//Obtiene lado derecho del arreglo Binario 1
				int j =0;
				for(int i = valorAleatorio ; i < arregloBinario1.length; i++) {
					arregloDerecha1[j] = arregloBinario1[i];
					j++;
				}
				resultadoCruze = this.unirArreglos(arregloDerecha1, arregloIzquierda1);
				break;
			}
		}
		return resultadoCruze;
	}
	
	public ArrayList<int[]> mutaArregloBites(ArrayList<int[]> arregloDeArreglosBinarios) {  //Recine un ArrayList con los arreglos a Mutar
		int tamanioArreglos = arregloDeArreglosBinarios.get(0).length;
		System.out.println("Tmanio arreglo " +tamanioArreglos+ "\n");
		int aleatorio = this.generaAleatorio(tamanioArreglos);
		System.out.println("Valor aleatorio: "+ aleatorio +"\n");
		
		ArrayList<int[]> arregloDeArreglosTemp = new ArrayList<int[]>(arregloDeArreglosBinarios);
		/*for(int i =0; i < arregloDeArreglosBinarios.size(); i++) {
			for(int j =0; j < arregloDeArreglosBinarios.get(j).length; j++) {
				int[] arragloTemp = arregloDeArreglosBinarios.get(j);
				
				if(j == aleatorio && arragloTemp[j] == 0) {
					arragloTemp[j] = 1;
					System.out.println("mutado posicion: "+i +"\n");
				}
				else if(j == aleatorio && arragloTemp[j] == 1) {
					arragloTemp[j] = 0;
				}
				
			}
		}*/
		int arregloTemp[][] = new int[arregloDeArreglosBinarios.size()][];
		
		for(int i = 0; i < arregloDeArreglosBinarios.size(); i++) {
			int []arreglo = arregloDeArreglosBinarios.get(i);
			
			for(int j = 0; j <= arreglo.length; j++) {
				
				arregloTemp[i] = new int []{arreglo};
			}
		}
		System.out.println("Arregos mutados\n");	
		for(int i = 0; i < arregloTemp.length; i++) {
			for(int j = 0; j <arregloTemp[i].length; j++) {
				System.out.print(arregloTemp[i][j] + " ");
			}
			System.out.println();
		}
		
		/*System.out.println("Arregos mutados\n");		
		for (int[] arreglo : arregloDeArreglosTemp ) {
			
			for (int elemento : arreglo) {
				System.out.print(elemento + " ");
				}
			System.out.println("\n");
			}*/
		
		return arregloDeArreglosBinarios;
	}
	
	public void cruzaYmuta(int arregloDeBites1[], int arregloDeBites2[]) {
		/* 1) Para la cruza Genero un valor aleatorio para cruzar *por cada arreglo.
		 * 2) Para la mutación *para cada arreglo se vuelve a generar un valor aleatorio 
		 * en el rango [1, tamArreglo] para cambiar el bit en la posición generada
		 * */
		ArrayList<int[]> arregloDeArreglosBinarios = new ArrayList<int[]>();
		ArrayList<int[]> arregloDeArreglosBinariosMutados = new ArrayList<int[]>();
		int arregloBinarioMutadoMetodo1[] = this.tipoCruzamiento(arregloDeBites1, arregloDeBites2, 1);  //
		int arregloBinarioMutadoMetodo2[] = this.tipoCruzamiento(arregloDeBites1, arregloDeBites2, 2);
		int arregloBinarioMutadoMetodo3[] = this.tipoCruzamiento(arregloDeBites1, arregloDeBites2, 3);
		int arregloBinarioMutadoMetodo4[] = this.tipoCruzamiento(arregloDeBites1, arregloDeBites2, 4);	
		
		arregloDeArreglosBinarios.add(arregloBinarioMutadoMetodo1);
		arregloDeArreglosBinarios.add(arregloBinarioMutadoMetodo2);
		arregloDeArreglosBinarios.add(arregloBinarioMutadoMetodo3);
		arregloDeArreglosBinarios.add(arregloBinarioMutadoMetodo4);
		
		arregloDeArreglosBinariosMutados =this.mutaArregloBites(arregloDeArreglosBinarios);
		System.out.println("Arreglos originales\n");
		for (int[] arreglo : arregloDeArreglosBinarios ) {
			
			for (int elemento : arreglo) {
				System.out.print(elemento + " ");
				}
			System.out.println("\n");
			}
		
/*		System.out.println("Arregos mutados\n");		
		for (int[] arreglo : arregloDeArreglosBinariosMutados ) {
			
			for (int elemento : arreglo) {
				System.out.print(elemento + " ");
				}
			System.out.println("\n");
			}*/
		
		
		//return arregloDeArreglosBinarios;
	}
	public static void main(String[] args) {
		// Ejemplo
						//[x1			,        x2]
		int arreglo1[] = {0, 0, 0, 0, 0, 1, 1, 1, 1 , 1};
						//[y1			,        y2]
		int arreglo2[] = {0, 0, 0, 0, 1, 1, 1, 1, 0 , 0};
		
		Binario Bin = new Binario();
		/*
		int arregloTemp1[] =  Bin.tipoCruzamiento(arreglo1, arreglo2, 4);
		
		System.out.println("Ejemplo \nCruzamiento metodo 4:");
		for(int j =0; j< arregloTemp1.length; j++) {
			System.out.print( arregloTemp1[j]+ " ");
		}
		System.out.println();
		System.out.println("Mutación aleatoria: ");
		int arregloTemp2[] = Bin.mutaArregloBites(Bin.tipoCruzamiento(arreglo1, arreglo2, 4));
		for(int j =0; j< arregloTemp2.length; j++) {
			System.out.print( arregloTemp2[j]+ " ");
		}
		*/
		//arraylist
		System.out.println();
		System.out.println("ArrayLists :");
		Bin.cruzaYmuta(arreglo1, arreglo2);
	}

}

