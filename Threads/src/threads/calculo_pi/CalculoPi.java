package threads.calculo_pi;

import java.util.concurrent.ThreadLocalRandom;
/*import java.util.concurrent.ThreadLocalRandom;  coordx =   ThreadLocalRandom.current().nextDouble(0, 1);*/

public class CalculoPi {
	
	public float CalculaPiPesado(int npuntosAleat) {
		int puntosEnCirculo=0;
		float coordx=0;
		float coordy=0;
		
		for(int j=0; j < npuntosAleat; j++) {
			coordx = (float) ThreadLocalRandom.current().nextDouble(0, 1);
			coordy = (float) ThreadLocalRandom.current().nextDouble(0, 1);
			
			if( Math.pow(coordx, 2) + Math.pow(coordy, 2) < 1.0) {
				puntosEnCirculo = puntosEnCirculo + 1;				
			}
		}
		
		float pi = 4 * puntosEnCirculo/(float)npuntosAleat;
		return pi;
	}
	
	public static void main(String[] args) {
	CalculoPi Calc = new CalculoPi();
		long tiempoInicial = System.currentTimeMillis();
		/*El código, función o método a ejecutar va aquí */
		System.out.println("Pi:" + Calc.CalculaPiPesado(10000));
		/*----------------------------------------------------------*/
		long tiempoFinal = System.currentTimeMillis();
		long tiempoTranscurrido = tiempoFinal - tiempoInicial;
		System.out.println( "Tiempo: " + tiempoTranscurrido );

	}

}
