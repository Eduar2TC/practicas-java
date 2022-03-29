import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Regresion {
	
	public static float []x = null;
	public static float []y =null;
	
	public Regresion(int n){
		 x = new float[n];
		 y = new float[n];
		 calculaPuntos(n);
	}
	
	public static void calculaPuntos(int n) {
		x = new float[n];
		y = new float[n];
		
		
		x[0] = 1;
		y[0] = 2;
		
		x[1] = 2;
		y[1] = 3;
		
		x[2] = 2;
		y[2] = 4;
		
		x[3] = 3;
		y[3] = 4;
		
		x[4] =4;
		y[4] = 4; 
		
		x[5] = 4;
		y[5] = 6;
		
		x[6] =5;
		y[6] = 5;
		
		x[7] = 6;
		y[7] = 7;
		
		float sumatoriaX=0; float sumatoriaY=0; float sumatoriaXY =0; float sumatoriaX2 =0;
		
		for(int i =0; i< x.length; i++) {
			sumatoriaX = sumatoriaX + x[i];
			sumatoriaY = sumatoriaY + y[i];
			sumatoriaXY = sumatoriaXY + y[i]*x[i];
			sumatoriaX2 = (float) (sumatoriaX2 + Math.pow(x[i],2));
		}
		
		float b = (float) ((n*sumatoriaXY - sumatoriaX*sumatoriaY) / (n*sumatoriaX2 - Math.pow(sumatoriaX,2))); //inclinacion de la recta o pendiente
		float mediaY = sumatoriaY / n;
		float mediaX = sumatoriaX /n;
		
		float a = mediaY - b*mediaX;   // a : Es la secante
		float X = x[n-1]; //x = 6		VALOR PUEDE CRECER PARA PREDICIR LOS SIGUIENTES PRONOSTICOS
		float Y = b*X  + a;  //X : variable independiente Y: variable dependiente
		
		System.out.println(Y);
	}
	
	public float[] getArregloX() {
		return x;
	}
	
	public float[] getArregloY() {
		return y;
	}

	public static void main(String[] args) {
		
		Regresion.calculaPuntos(8);
		
	    SwingUtilities.invokeLater(() -> {
	        ScatterPlot example = new ScatterPlot("Regresión Lineal | ejemplo");
	        example.setSize(800, 400);
	        example.setLocationRelativeTo(null);
	        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        example.setVisible(true);
	    });

	}

}
