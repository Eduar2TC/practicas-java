package Estados;

import Matriz.Matriz;


//  Del estado inicial buscar la casilla vacia y crear y almacenar las matrices de los estados adyacentes

/*1.- Partimos del punto inicial (Matriz Inicial), lo metemos a la lista de Open
 * (a Metemos en la lista open el estado inicial
 * (b De la lista Open seleccionamos la casilla vacia del estado inicial y encontramos los adyacentes
 * c) Generamos los Estados(Matrices) adyacentes y los guardamos en una LISTA TEMPORAL
 * 2. -Buscamos el menor F
 * a) Metemos el estado Inicial a la lista close
 * b) Metemos los estados adyacentes (lista de Temporal) a la Lista Open
 *
 * 
 * 
 * 
 * 
 * */

public class Estado {  // Puede ser Padre o hijo
	
	Matriz estado = null;
	int idEstado;
	int idPadre; /*Almacena id del padre ejemplo
	
				[1][2][3]    		[1][2][3]
	idEstado =1	[4][5][6]	hijo	[4][5][0] =>idPadre = 1
				[7][8][0]			[7][8][6]
	*/
	
	int costoG;	//Costo total desde el inicio
	int heuristicaH;
	
	int funcionF = costoG + heuristicaH;
	
	public Estado(Matriz matrizInicial) {			//Inicializacion del estado inicial
		this.estado = matrizInicial;
		this.costoG = 0;
		this.heuristicaH = 0;
		
	}
	
	public int calculaG(){
		
		
		return costoG;
	}
	
	public int calculaH() {
		return costoG;
	}
	
	public int calculaF() {
		int funcionF = this.costoG + this.heuristicaH;
		return funcionF;
		
	}
	
	
	
}
