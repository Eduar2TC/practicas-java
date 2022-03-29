package Despachador;

public class Despachador {
	
	static int a = 0;
	static int b = 0;
	public static int opc1(){ 
		a = 1; 
		return b; 
	}
	public static int opc2(){ 
		b = 2; 
		return 3*a; 
	}
	
}
class Pruebas extends Despachador implements Runnable{
	private int centinela;
	
	public int llamaFuncion() {
		if(this.centinela == 1) {
			return this.opc1();
		}
		else if(this.centinela == 2){
			return this.opc2();
		}
		return -1;
	}
	public void run() {
		String nombre = null;
		try {
			if(this.centinela == 1) {
				nombre = "Funcion opcUno";
			}
			if(this.centinela == 2) {
				nombre = "Funcion opcDos";
			}
			
			int opc =this.llamaFuncion();
			System.out.println(nombre + ": " + opc );
		} catch(Exception e) {};

	}
	
	public static void main(String[] args) {
		for(int i=0; i< 10; i++) {
			Pruebas p1 = new Pruebas();
			Pruebas p2 = new Pruebas();
			p1.centinela = 1;
			Thread uno = new Thread(p1);
			p2.centinela = 2;
			Thread dos = new Thread(p2);
			uno.start();
			dos.start();
		}
	}
	
}