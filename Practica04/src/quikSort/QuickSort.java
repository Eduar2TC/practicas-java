package quikSort;

public class QuickSort {
	private int arreglo[];
    private int tamanio;
    
    public void inicializa(int[] arregloEntrada) {
    	
    	if (arregloEntrada == null || arregloEntrada.length == 0) {
    		return;
        }
    	this.arreglo = arregloEntrada;
    	tamanio = arregloEntrada.length;
    	ordena( 0, tamanio-1 );
    }
 
    private void ordena(int primerIndice, int ultimoIndice) {
    	
    	int i = primerIndice;
        int j = ultimoIndice;
        // calculo del pivote
        int pivote = arreglo[primerIndice+(ultimoIndice-primerIndice)/2];
        // dividir en dos el arreglo
        while (i <= j) {
        	
            while (arreglo[i] < pivote) {
                i++;
            }
            while (arreglo[j] > pivote) {
                j--;
            }
            if (i <= j) {
                intercambioNumeros(i, j);
                i++;
                j--;
            }
        }
        // llamada recursiva
        if (primerIndice < j) {
        	ordena(primerIndice, j);	
        }
        if (i < ultimoIndice) {
        	ordena(i, ultimoIndice);
        }
            
    }
 
    private void intercambioNumeros(int i, int j) {
    	int temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
  public void mostrarArreglo(int [] arreglo) {
	  System.out.print("[ ");
	  for(int i =0; i < arreglo.length; i++) {
		  System.out.print("|"+arreglo[i]);
		}
	  System.out.print(" ]");
	}   
    public static void main(String a[]){
    	QuickSort Orden = new QuickSort();
        int[] arreglo = {24,2,45,20,56,75,2,56,99,53,12};
        int[] arregloDos = {5,2,1,8,3,9,7};
        
        System.out.println("Arreglo uno inicial");
        Orden.mostrarArreglo(arreglo);
        Orden.inicializa(arreglo);
        System.out.println();
        System.out.println("Arreglo ordenado");
        Orden.mostrarArreglo(arreglo);
        System.out.println();
        System.out.println("Arreglo dos inicial");
        Orden.mostrarArreglo(arregloDos);
        Orden.inicializa(arregloDos);
        System.out.println();
        System.out.println("Arreglo ordenado");
        Orden.mostrarArreglo(arregloDos);
    }
}
