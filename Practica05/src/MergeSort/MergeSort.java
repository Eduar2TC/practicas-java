package MergeSort;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MergeSort implements Runnable{
    private int[] arreglo;
    private int[] tempArreglo;
    private int[] arregloInicial;
    private int tamanio;
    
	public int [] generaArreglo(int n) {
    	int[] tempArreglo = new int[n];
    	
    	for (int i = 0; i < n; i++) {
    		
    		tempArreglo [i] = ThreadLocalRandom.current().nextInt(-n*2, n*2);
    	}
    	return tempArreglo;
    }

public void mostrarArregloFinal( ) {
	System.out.print("[ ");
	for(int i =0; i < this.arreglo.length; i++) {
		System.out.print("|"+ this.arreglo[i]);
		}
	System.out.print("| ]");
}
public void mostrarArregloTemp(int [] arregloT ) {
	System.out.print("[ ");
	for(int i =0; i < arregloT.length; i++) {
		System.out.print("|"+ arregloT[i]);
		}
	System.out.print("| ]");
}
    public void inicializaMescla(int n) {  //Aqui ingresar n tamanio
        this.arregloInicial = this.arreglo = generaArreglo(n);   /* Inicializar con generaArreglo*/
        this.tamanio = arreglo.length;
        this.tempArreglo = new int[tamanio];
    }
 public int[] getArregloInicial() {
	 return this.arregloInicial;
 }
    private void haciendoParticiones(int indiceInfer, int indiceSuper) throws InterruptedException {
    	Thread hilo = new Thread();
    	hilo.start();
    	try {
        if (indiceInfer < indiceSuper) {
        		int mitad = indiceInfer + (indiceSuper - indiceInfer) / 2;
                // lado izquierdo arreglo
                this.haciendoParticiones(indiceInfer, mitad);
                //lado derecho arreglo
                this.haciendoParticiones(mitad + 1, indiceSuper);
                
                // Now merge both sides
                this.ordenaParticiones(indiceInfer, mitad, indiceSuper);
                System.out.print("Nombre : "+ hilo.getName()+" id : "+ hilo.getId()+ "\t");
        	}
    	}catch(Exception e) {};
            
    }
 
    private void ordenaParticiones(int indiceInfer, int mitad, int indiceSuper) {
        for (int i = indiceInfer; i <= indiceSuper; i++) {
            tempArreglo[i] = arreglo[i];
        }

        int i = indiceInfer;
        int j = mitad + 1;
        int k = indiceInfer;
        while (i <= mitad && j <= indiceSuper) {
            if (tempArreglo[i] <= tempArreglo[j]) {
                arreglo[k] = tempArreglo[i];
                i++;
            } else {
                arreglo[k] = tempArreglo[j];
                j++;
            }
            k++;
        }
        while (i <= mitad) {
            arreglo[k] = tempArreglo[i];
            k++;
            i++;
        }
        this.mostrarArregloTemp(arreglo);System.out.println();
    }
	
	public static void main(String[] args) throws InterruptedException {
		
			Scanner teclea = new Scanner(System.in);
			System.out.print("Ingresa tamanio : ");
			int entrada = teclea.nextInt();
		
	        MergeSort arregloIni = new MergeSort();
	        arregloIni.inicializaMescla(entrada);
	        System.out.print("Arreglo inicial : ");
	        arregloIni.mostrarArregloTemp(arregloIni.arreglo); System.out.println();
	        System.out.print("Procesando... ");
	        //arregloIni.haciendoParticiones(0, arregloIni.arreglo.length - 1);
	        	        
	        Thread primerHilo = new Thread(arregloIni);
	     	primerHilo.start();
			try{	
				primerHilo.join();
		        System.out.print("\nArreglo ordenado: ");
		        arregloIni.mostrarArregloFinal();
				System.out.print("\nNombre principal : "+ primerHilo.getName()+" id principal : "+ primerHilo.getId()+ " ");
				arregloIni.mostrarArregloTemp(arregloIni.arregloInicial); //Error
				}catch( InterruptedException e ){};
				
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.haciendoParticiones(0, this.arreglo.length - 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
