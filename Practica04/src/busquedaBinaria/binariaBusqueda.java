package busquedaBinaria;

import java.util.Arrays;

public class binariaBusqueda {
	private int arreglo[];
	
	binariaBusqueda(int arreglo[]){
		this.arreglo = arreglo;
	}
	
	public void mostrarArreglo() {
		System.out.print("[ ");
		for(int i =0; i < this.arreglo.length; i++) {
			System.out.print("|"+arreglo[i]);
			}
		System.out.print(" ]");
	}
	
	public int busquedaNumero(int numero) {
		return Arrays.binarySearch(this.arreglo, numero);
	}

	public static void main(String[] args) {
		int arreglo[] = {1,6,8,19,70,100,40,4,7,9,1200};
		binariaBusqueda binB = new binariaBusqueda(arreglo);
		int resultado = binB.busquedaNumero(1200);
		System.out.println("Arreglo es : ");
		binB.mostrarArreglo();
		System.out.println("\nEl primer numero a buscar 1200 se encuentra en la posicion del indice : " + resultado);
		System.out.println("\n(nota: el indice empieza en :0)");
	}

}
