package Intento;

public class IntentoUno implements Lock {

	volatile boolean abrePuerta = true;
/*	 	int N;
	    boolean[] eligiendo; // inside doorway
	    int[] numero;
	    public 	IntentoUno() {
	        N = 2;
	        eligiendo = new boolean[N];
	        numero = new int[N];
	        for (int j = 0; j < N; j++) {
	            eligiendo[j] = false;
	            numero[j] = 0;
	        }
	    }*/
	    public void requestCR(int i) {
	    	while( !abrePuerta ); // espera activa
	    	abrePuerta = false;
	    	
	    }
	    
	    public void releaseCR(int i) { // salida
	    	abrePuerta = true;
	    }
	    
	    public boolean getPuerta() {
	    	return abrePuerta;
	    }

}
