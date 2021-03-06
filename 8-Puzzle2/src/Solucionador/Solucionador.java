package Solucionador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Archivo.Archivo;
import Tablero.Tablero;

public class Solucionador {
	
	public String [][] estadoInicial = null;
	public String [][] estadoMeta = null;
	
	public String [][] estadoActual = null;  //Almacena el valor actual
	
	int value_FuncionF;   // Almacena el valor de la suma de las heuristicas
	int value_heuristica_H;	// numero de casillas fuera de lugar
	int value_heuristica_G; //Cuato ha costado llegar a este nodo
	
	public ArrayList<String[][]> listaOpen = null; //Guarda los hijos generados
	public ArrayList<String[][]> listaClose = null; // Guarda los hijos procesados
	
	public Solucionador() {
		
		listaOpen = new ArrayList<String[][]>();
		listaClose = new ArrayList<String[][]>();
		
		this.estadoInicial = inicializaEstadoInicial();
		this.estadoMeta = inicializaEstadoMeta();
		this.estadoActual = null;
		
		this.value_heuristica_G = 0;
		this.value_heuristica_G = 0;
		this.value_FuncionF = this.value_heuristica_G + this.value_heuristica_G;
	}
	
	
	String [][] inicializaEstadoInicial(){
		Archivo nuevoArchivo = new Archivo("src/Archivo/estadoInicial.txt"); //$NON-NLS-1$
		String matriz[][] = null;
		try {
			matriz = nuevoArchivo.leerArchivo();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matriz;
	}
	String [][] inicializaEstadoMeta(){
		Archivo nuevoArchivo = new Archivo("src/Archivo/estadoMeta.txt"); //$NON-NLS-1$
		String matriz[][] = null;
		try {
			matriz = nuevoArchivo.leerArchivo();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matriz;
	}
	
	//seters y getters
	public int getValorF() {
		
		return this.value_FuncionF;
	}
	
	public void setValorF(int nuevoValorF) {
		this.value_FuncionF = nuevoValorF;
	}
	
	public int getValorG() {
		return this.value_heuristica_G;
	}
	
	public void setValorG(int nuevoValorG) {
		this.value_heuristica_G = nuevoValorG;
	}
	
	public void setEstadoActual(String [][] nuevoEstado) {
		this.estadoActual = nuevoEstado;
	}
	
	public String[][] getEstadoActual(){
		return this.estadoActual;
	} 
	
	
	public String [][] obtieneMinimoEstadoHijos( ArrayList<String[][]> listaEstadosHijos ){
		int arregloDeEmpatados[] = null;
		int valorMinimo = 0;

		String[][] estadoActual = new String[2][2];
		Tablero tableroTemp = new Tablero();

		for(int i = 0; i < listaEstadosHijos.size(); i++) { // listaEstadosHijos.size() numero de elementos guardados

			estadoActual = listaEstadosHijos.get(i);
			int valorHeuristicaTemp = tableroTemp.heuristicaManhatan(estadoActual, this.estadoMeta) + tableroTemp.heuristicaCasillasFueraDeLugar(estadoActual, this.estadoMeta);

			if(valorMinimo == valorHeuristicaTemp) {
				arregloDeEmpatados = new int[]{i};
			}

			if(valorMinimo < valorHeuristicaTemp && i == 4) {  // retorna el arreglo que tiene el minimo

				valorMinimo = valorHeuristicaTemp;
				return estadoActual;

			}
			else if (i == 4 && arregloDeEmpatados.length >= 1) {  // Si encuentra un empate guarda el indice de la matriz actual

				int aleatorio = (int)(Math.random() * ((arregloDeEmpatados.length - 0) + 1)) + 0;
				return listaEstadosHijos.get(aleatorio);
			}

		}
		return estadoActual;   //Posibilidades de fallar
	}

	boolean esSolucion(String [][] estado) {
		if (Arrays.deepEquals(estado, this.estadoMeta)) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean elEstadoEstaEnListaClose(String estado[][]) {
		if( this.listaClose.contains(estado) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void insertaEnOpen(String[][] estado) {  //Lista para insertar cada estado Generado
		
		if(elEstadoEstaEnListaClose(estado) == false) {
			this.listaOpen.add(estado);
		}
	}
	
	public void insertaHijosEnOpen(ArrayList<String[][]> nuevaLista) { 	
		
		Solucionador tempSol = new Solucionador();
		for(int i = 0; i < nuevaLista.size(); i++) {
			tempSol.insertaEnOpen(nuevaLista.get(i));
		}
		
	}
	
	public void eliminaDeOpen(String [][] estado) {
		this.listaOpen.remove(estado);
	}
	public void insertaEnClose(String[][] estado) {  //Lista para meter los que se han procesado
		this.listaClose.add(estado);
	}
	public void aEstrella() {
		
		Solucionador solucion = new Solucionador();
		Tablero tablero = new Tablero();
		ArrayList<String[][]> listaHijosTemporal = null;
		
		
		solucion.setEstadoActual(solucion.estadoInicial);
		solucion.insertaEnOpen(solucion.estadoActual);
		solucion.setEstadoActual(solucion.obtieneMinimoEstadoHijos(solucion.listaOpen));
		solucion.insertaEnClose(this.estadoActual);
		
		if(solucion.esSolucion(solucion.estadoActual) == true) {
			System.out.println("Solucion alcanzada");
			tablero.imprimeTablero(solucion.estadoActual);
		}
		
		if(solucion.estadoActual == null) {
			System.out.println("Matriz vacia");
		}
		//listaHijosTemporal = new ArrayList<String[][]>(tablero.generaHijos(solucion.getEstadoActual()));

		while(!solucion.esSolucion(getEstadoActual()) && solucion.getEstadoActual() != null) {
			listaHijosTemporal = new ArrayList<String[][]>();
			listaHijosTemporal = tablero.generaHijos(solucion.getEstadoActual());
			solucion.insertaHijosEnOpen(listaHijosTemporal);
			
			solucion.setEstadoActual(solucion.obtieneMinimoEstadoHijos(solucion.listaOpen));
			solucion.insertaEnOpen(solucion.getEstadoActual());
			
			if(solucion.listaOpen.contains(solucion.getEstadoActual())){
				solucion.eliminaDeOpen(solucion.getEstadoActual());
			}
			solucion.listaClose.add(solucion.estadoActual);
			

			//solucion.setEstadoActual(solucion.obtieneMinimoEstadoHijos(listaHijosTemporal));
			
		}
		
	}
	public static void main(String[] args) throws IOException {

			Solucionador objeto = new Solucionador();
			
			objeto.aEstrella();
		

        }

}
