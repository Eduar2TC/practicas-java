package threads.calculo_pi;
import java.util.concurrent.ThreadLocalRandom;


public class CalculoPiHilos implements Runnable{
	
	int totalPuntos;

	public int CalculaPuntos(int npuntosAleat) {	

		int numTareas=4;
		int puntosEnCirculo=0;
		float coordx=0;
		float coordy=0;
		int m = npuntosAleat/numTareas;
		
		for(int j=0; j < m; j++) {
			coordx = (float) ThreadLocalRandom.current().nextDouble(0, 1);
			coordy = (float) ThreadLocalRandom.current().nextDouble(0, 1);
			
			if( Math.pow(coordx, 2) + Math.pow(coordy, 2) < 1.0) {
				puntosEnCirculo = puntosEnCirculo + 1;				
			}
		}
		return puntosEnCirculo;
	}

	@Override
	public void run() {
		
		CalculoPiHilos h1 = new CalculoPiHilos();
		CalculoPiHilos h2 = new CalculoPiHilos();
		CalculoPiHilos h3 = new CalculoPiHilos();
		CalculoPiHilos h4 = new CalculoPiHilos();
		Thread tareaH1 = new Thread(h1);
		Thread tareaH2 = new Thread(h2);
		Thread tareaH3 = new Thread(h3);
		Thread tareaH4 = new Thread(h4);
		tareaH1.start();
		tareaH2.start();
		tareaH3.start();
		tareaH4.start();		
		totalPuntos= h1.CalculaPuntos(1000) + h2.CalculaPuntos(1000) +  h3.CalculaPuntos(1000) +  h4.CalculaPuntos(1000);
		
	}
	public int getTotalPuntos(){
		return totalPuntos;
	}
	
	public static void main( String[] args ) throws InterruptedException{
		long tiempoInicial = System.currentTimeMillis();
		CalculoPiHilos Padre = new CalculoPiHilos();
		Thread tareaPadre = new Thread(Padre);	
		
		tareaPadre.start();
		try{
			tareaPadre.join();
			}catch( InterruptedException e ){};
			
		float pi = 4 * Padre.getTotalPuntos()/(float)1000;
		System.out.println( "Pi " + pi );	
		
		long tiempoFinal = System.currentTimeMillis();
		long tiempoTranscurrido = tiempoFinal - tiempoInicial;
		System.out.println( "Tiempo : " + tiempoTranscurrido );
		
	}
}	
