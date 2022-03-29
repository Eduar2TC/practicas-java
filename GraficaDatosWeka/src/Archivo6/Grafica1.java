package Archivo6;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafica1 {

	private String nombreArchivo = null;
	
	public Grafica1(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	
	public ArrayList<ArrayList<Float>> LeeArchivo() {
		String archivo = "src/Archivo6/" + nombreArchivo;
		ArrayList<Float> variables = null;
		ArrayList<ArrayList<Float>> arregloDeFis = new ArrayList<>();
		try {
		      FileInputStream fis = new FileInputStream(archivo);
		      InputStreamReader isr = new InputStreamReader(fis,"utf8");
		      BufferedReader br = new BufferedReader(isr);
		 
		      String linea =null;  //Lee linea por linea
		      int numeroDeColumnas =1; //Columnas por bloque de Fis
		      
		      while((linea = br.readLine()) != null) { 
		    	  
		    	   String[] cadenaLinea = linea.split("\\s+");
		    	   Float[] floats = Arrays.stream(cadenaLinea).map(Float::valueOf).toArray(Float[]::new);
		    	   
		    	   for(int i =0; i <numeroDeColumnas; i++) {
		    		   variables = new ArrayList<>();
			    	   for(int j =0; j < floats.length; j++) {
			    		   variables.add(floats[j]);
			    	   }
			    	   arregloDeFis.add(variables);
		    	   }

		      }
		      fis.close(); //Cerrando archivo
		      
		    }
		    catch(Exception e) {
		      System.out.println("Excepcion leyendo fichero "+ archivo + ": " + e);
		    }
		return arregloDeFis;
	}
	
	public float[][] retornaArregloDeDatos() {
		
		Grafica1 miGrafica1 = new Grafica1(this.nombreArchivo);
		ArrayList<ArrayList<Float>> arregloDeDatosTemp = new ArrayList<>(miGrafica1.LeeArchivo());
		float[][] arregloDeDatos = new float[arregloDeDatosTemp.size()][6];
		

		for(int i =0; i < arregloDeDatosTemp.size(); i++) {
			for(int j =0; j < arregloDeDatosTemp.get(i).size(); j++ ) {
				arregloDeDatos[i][j] = arregloDeDatosTemp.get(i).get(j);
			}
		}
		return arregloDeDatos;
	}
	
	public void Grafica1Datos() {
		
		Grafica1 miGrafica1 = new Grafica1(this.nombreArchivo);
		float datos[][] = miGrafica1.retornaArregloDeDatos();
		
		XYSeries series = new XYSeries("Prorcentajes Fis mejoras5.txt");
		
		for(int i =0; i< datos.length; i++) {

			for(int j =0; j < datos[i].length; j++) {
				
				if( j == 5 ) {
					series.add(i, datos[i][j]);
					
					System.out.println("indice : "+ i + " "+datos[i][j] );
				}
			}
		}

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        //Creacion de la Grafica1 de los datos
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Gráfica de progreso", // Título
                "Fis", // Etiqueta Coordenada X
                "Porcentaje de clasificación de Instancias", // Etiqueta Coordenada Y
                dataset, // Datos
                PlotOrientation.VERTICAL,
                true, // Muestra la leyenda de los productos (Producto A)
                false,
                false
                );
        
		        XYPlot plot = chart.getXYPlot();
		        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		        plot.setBackgroundPaint(Color.DARK_GRAY);
		        renderer.setSeriesPaint(0, Color.YELLOW);
		        /*renderer.setSeriesPaint(1, Color.RED);*/
		        
		        plot.setRenderer(renderer);
        
        // Mostramos la Grafica1 en pantalla
        ChartFrame frame = new ChartFrame("Progreso", chart);
        frame.pack();
        frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Grafica1 miGrafica1 = new Grafica1("mejora5.txt");
		miGrafica1.Grafica1Datos();
	
	}

}
