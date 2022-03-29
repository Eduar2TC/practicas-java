package BinariosCruzamientoMutacion;

import java.util.ArrayList;

public class binario {
	
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
		System.out.println("Aleatorio : " + valorAleatorio);
		int arregloIzquierda1[] = new int[valorAleatorio];
		int arregloDerecha1[] = new int[tamanioDeLosArreglos - valorAleatorio ];
		
		//Intercambios
		switch(metodo) {
			case 1:{
				System.out.println("izquierda : ");
				for(int i =0; i< arregloIzquierda1.length; i++) {
					arregloIzquierda1[i] = arregloBinario1[i];
					System.out.print(arregloIzquierda1[i]+" ");
				}
				System.out.println();
				//Obtiene lado derecho del arreglo binario 1
				int j =0;
				System.out.println("derecha : ");
				for(int i = valorAleatorio ; i < arregloBinario2.length; i++) {
					arregloDerecha1[j] = arregloBinario2[i];
					System.out.print(arregloDerecha1[j]+" ");
					j++;
				}
				System.out.println(); System.out.println();
				resultadoCruze = this.unirArreglos(arregloIzquierda1, arregloDerecha1);
				break;
				
			}
			case 2:{
				//Obtiene lado izquierdo del arreglo binario 1
				System.out.println("izquierda arreglo 2: ");
				
				for(int i =0; i< arregloIzquierda1.length; i++) {
					arregloIzquierda1[i] = arregloBinario2[i];
					System.out.print(arregloIzquierda1[i]+" ");
				}
				System.out.println();
				//Obtiene lado derecho del arreglo binario 2
				int j =0;
				System.out.println("derecha arreglo 1: ");
				for(int i = valorAleatorio ; i < arregloBinario1.length; i++) {
					arregloDerecha1[j] = arregloBinario1[i];
					System.out.print(arregloDerecha1[j]+" ");
					j++;
				}
				System.out.println(); System.out.println();
				
				resultadoCruze = this.unirArreglos(arregloIzquierda1, arregloDerecha1);
				break;

			}
			case 3:{
				
				//Obtiene lado derecho del arreglo binario 2
				int j =0;
				System.out.println("derecha arreglo 2: ");
				for(int i = valorAleatorio ; i < arregloBinario2.length; i++) {
					arregloDerecha1[j] = arregloBinario2[i];
					System.out.print(arregloDerecha1[j]+" ");
					j++;
				}
				System.out.println(); System.out.println();
				
				//Obtiene lado izquierdo del arreglo binario 1
				System.out.println("izquierda arreglo 1: ");
				for(int i =0; i< arregloIzquierda1.length; i++) {
					arregloIzquierda1[i] = arregloBinario1[i];
					System.out.print(arregloIzquierda1[i]+" ");
				}
				System.out.println();
				
				resultadoCruze = this.unirArreglos(arregloIzquierda1, arregloDerecha1);
				break;				
			}
			case 4:{
				System.out.println("izquierda arreglo 2: ");
				for(int i =0; i< arregloIzquierda1.length; i++) {
					arregloIzquierda1[i] = arregloBinario2[i];
					System.out.print(arregloIzquierda1[i]+" ");
				}
				System.out.println();
				//Obtiene lado derecho del arreglo binario 1
				int j =0;
				System.out.println("derecha arreglo 1: ");
				for(int i = valorAleatorio ; i < arregloBinario1.length; i++) {
					arregloDerecha1[j] = arregloBinario1[i];
					System.out.print(arregloDerecha1[j]+" ");
					j++;
				}
				resultadoCruze = this.unirArreglos(arregloDerecha1, arregloIzquierda1);
				break;
			}
		}
		return resultadoCruze;
	}
	
	
	public int [] mutaArregloBites(int arregoDeBites[]) {
		
		int valorAleatorio = this.generaAleatorio(arregoDeBites.length);  //Genera posicion a mutar
		//System.out.println("Aleatorio: " +(valorAleatorio+1));
		for(int i =0; i < arregoDeBites.length; i++) {
			if(valorAleatorio == i && arregoDeBites[i] == 0) {
				arregoDeBites[i] = 1;
			}
			else if(valorAleatorio == i && arregoDeBites[i] == 1) {
				arregoDeBites[i] = 0;
			}
		}
		return arregoDeBites;	
	}
	
	public int generaAleatorio(int tamanioArreglo) {

		
		//genera los valores aleatorios
		int numeroAleatorio = 1 + (int)(Math.random() * (((tamanioArreglo -1)- 1) + 1));
		
		return numeroAleatorio ;
	}
	
	/*public int[] mutaBinarios() {
		
	}*/
	
	public void cruzaYmuta(int arregloDeBites1[], int arregloDeBites2[]) {
		/* 1) Para la cruza Genero un valor aleatorio para cruzar por cada arreglo.
		 * 2) Para la mutación para cada arreglo se vuelve a generar un valor aleatorio 
		 * en el rango [1, tamArreglo] para cambiar el bit en la posición generada
		 * 
		 * */
		ArrayList<int[]> arregloDeArreglosBinarios = new ArrayList<int[]>();
		
		int arregloBinarioMutadoMetodo1[] = this.mutaArregloBites(this.tipoCruzamiento(arregloDeBites1, arregloDeBites2, 1));
		int arregloBinarioMutadoMetodo2[] = this.mutaArregloBites(this.tipoCruzamiento(arregloDeBites1, arregloDeBites2, 2));
		int arregloBinarioMutadoMetodo3[] = this.mutaArregloBites(this.tipoCruzamiento(arregloDeBites1, arregloDeBites2, 3));
		int arregloBinarioMutadoMetodo4[] = this.mutaArregloBites(this.tipoCruzamiento(arregloDeBites1, arregloDeBites2, 4));		

		arregloDeArreglosBinarios.add(arregloBinarioMutadoMetodo1);
		arregloDeArreglosBinarios.add(arregloBinarioMutadoMetodo2);
		arregloDeArreglosBinarios.add(arregloBinarioMutadoMetodo3);
		arregloDeArreglosBinarios.add(arregloBinarioMutadoMetodo4);
		
		for (int[] arreglo : arregloDeArreglosBinarios) {
			
			for (int elemento : arreglo) {
				System.out.print(elemento + " ");
				}
			System.out.println("\n");}
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
						//[x1			,        x2]
		int arreglo1[] = {0, 0, 0, 0, 0, 1, 1, 1, 1 , 1};
						//[y1			,        y2]
		int arreglo2[] = {0, 0, 0, 0, 1, 1, 1, 1, 0 , 0};
		
		binario Bin = new binario();
		int arreglo[] =  Bin.tipoCruzamiento(arreglo1, arreglo2, 4);
		
		System.out.println();
		System.out.println("Resultado");
		for(int j =0; j< arreglo.length; j++) {
			System.out.print( arreglo[j]+ " ");
		}
		
		int arregloMutado[] = Bin.mutaArregloBites(arreglo);
		System.out.println();
		System.out.println("Resultado mutacion");
		for(int j =0; j< arreglo.length; j++) {
			System.out.print( arregloMutado[j]+ " ");
		}
		

	}

}
