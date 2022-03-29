package Intento;

public interface Lock {

	public void requestCR( int pid ); //puede bloquear
	public void releaseCR( int pid ); //libera la CR
	
	public boolean getPuerta(); //Obtiene estado de puerta

	
}
