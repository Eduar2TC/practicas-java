package Lista;

import java.util.Scanner;

public class Lista {
	private Nodo cabeza;
	private int tamanio;
	
	public Lista() {
		this.cabeza = null;
		this.tamanio = 0;
	}
	public void esVacia() {
		if(cabeza == null ) {
			System.out.println("La lista esta vacia");
		}
		else {
			System.out.println("Hay datos");
		}
	}
	public void agregarDatoInicio(Object dato) {
		if(cabeza == null) {
			cabeza = new Nodo(dato);
		}
		else {
			Nodo temp = cabeza;
			Nodo nuevo = new Nodo(dato);
			nuevo.siguiente = temp;
			cabeza = nuevo;			
		}
		tamanio++;
	}
	public void eliminarAlInicio() {
		if (cabeza == null) {
			System.out.println("Lista vacia");
		}
		else {
			cabeza = cabeza.siguiente;
		}
		tamanio--;		
	}
	public void eliminarAlfinal() {
		if(cabeza == null) {
			System.out.println("Lista vacia");
		}
		else {
			Nodo temp = null;
			Nodo aux = cabeza;
			while(aux.siguiente != null) {
				temp = aux;
				aux = aux.siguiente;
			}
			temp.siguiente = null;
		}
		tamanio--;
	}
	public void eliminaEspecifico(Object dato) {
		Nodo actual = cabeza;
		Nodo ant =null;
		while(actual != null) {
			if(actual.valor == dato) {
				if(actual == cabeza) {
					cabeza = cabeza.siguiente;
				}
				else {
					ant.siguiente = actual.siguiente;
				}
			}
			ant =actual;
			actual = actual.siguiente;
		}
		tamanio--;
	}
	public void eliminarLista() {
		if(cabeza == null) {
			System.out.println("No se puede eliminar, lista vacia");
		}
		Nodo temp;
		Nodo iterador = cabeza;
		while(iterador != null) {
			temp = iterador.siguiente;
			iterador.siguiente = null;
			iterador = temp;
		}
		cabeza = null;
		tamanio =0;
	}
	public void agregarAlFinal(Object dato) {
		if(cabeza == null ) {
			cabeza = new Nodo(dato);
		}
		else {
			Nodo actual = cabeza;
			while(actual.siguiente != null) {
				actual = actual.siguiente;
			}
			Nodo nuevo = new Nodo(dato);
				actual.siguiente = nuevo;	
		}
		tamanio++;
	}
	public int tamanioLista() {
		return tamanio;
	}
	public void mostrarLista() {
		if(cabeza == null ) {
			System.out.println("# elementos : "+ tamanio + " la lista esta vacia");
		}
		else {
			Nodo actual = cabeza;
			System.out.println("Datos.\n # elementos " + tamanio);
			while(actual != null){
				System.out.println(" [ " + actual.valor + " ] ");
				actual = actual.siguiente;
			}
		}
	}
	public int opcionEntrada() {
		Scanner teclea = new Scanner(System.in);
		System.out.print("Opcion : ");
		int entrada = teclea.nextInt();
		return entrada;
	}
	public int menu() {
		System.out.println("1. Insertar al inicio \n"+
						   "2. Insertar al final \n"+
						   "3. Borrar al inicio \n"+
						   "4. Borrar al final \n"+
						   "5. Borrar un dato especifico \n"+
						   "6. Borrar lista\n"+
						   "7. Mostrar lista");
		int opcion = opcionEntrada();
		return opcion;
	}
	public Object ingresaObjeto() {
		Object dato;
		Scanner entra = new Scanner(System.in);
		System.out.println("1. Entero\n2. Flotante\n3 Cadena : ");
		int eligeTipo = entra.nextInt();
		
		if(eligeTipo == 1) {
			System.out.print("Dato : ");
			int eleccion = entra.nextInt();
			dato = eleccion;
		}
		else if(eligeTipo == 2) {
			System.out.print("Dato : ");
			float eleccion = entra.nextFloat();
			dato = eleccion;
		}
		else {
			System.out.print("Dato : ");
			String eleccion = entra.next();
			dato = eleccion;
		}
		return dato;
	}
	public void procesadorQueProcesa(int opcionSeleccionada) {

		switch(opcionSeleccionada) {
			case 1:{
				this.agregarDatoInicio(this.ingresaObjeto());
				break;
			}
			case 2:{
				this.agregarAlFinal(this.ingresaObjeto());
				break;	
			}
			case 3:{
				this.eliminarAlInicio();
				break;	
			}
			case 4:{
				this.eliminarAlfinal();
				break;	
			}
			case 5:{
				this.eliminaEspecifico(this.ingresaObjeto());
				break;	
			}
			case 6:{
				this.eliminarLista();
				break;	
			}
			case 7:{
				this.mostrarLista();
				break;	
			}
			default:{
				break;
			}
			
		}
	}
		
	public static void main(String[] args) {
		
		Lista nuevaLista = new Lista();
		boolean Salir = false;
		Scanner Respuesta;
		do {
			int Opcion = nuevaLista.menu();
			if(Salir == false && (Opcion <= 7 && Opcion >=1 )) {
				nuevaLista.procesadorQueProcesa(Opcion);
				System.out.println();
				System.out.print("Continuar(y or n) : ");
				Respuesta = new Scanner(System.in);
				char Resp = Respuesta.next().charAt(0);  ///capturar cadena
				
				if(Resp == 'y') {
					Salir = false;
				}
				else {
					if(Resp == 'n') {
						System.out.println("Saliendo...");
						Salir = true;						
					}
				}
			}
			else {
			 System.out.println("	Opcion invalida!!");
			}
		}while(Salir == false);
	}

}