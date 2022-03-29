package Archivo6;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafica {

	private String nombreArchivo = null;
	
	public Grafica(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public Grafica() {
		
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
		
		Grafica miGrafica = new Grafica(this.nombreArchivo);
		ArrayList<ArrayList<Float>> arregloDeDatosTemp = new ArrayList<>(miGrafica.LeeArchivo());
		float[][] arregloDeDatos = new float[arregloDeDatosTemp.size()][6];
		

		for(int i =0; i < arregloDeDatosTemp.size(); i++) {
			for(int j =0; j < arregloDeDatosTemp.get(i).size(); j++ ) {
				arregloDeDatos[i][j] = arregloDeDatosTemp.get(i).get(j);
			}
		}
		
		
		return arregloDeDatos;
	}
	
	public float[] ordenaDatos(){
		float[][] arregloDeDatos = this.retornaArregloDeDatos();
		float arregloUnidimensional[] = new float[100];
		int k =0;
	    float temp =0;
		for(int i =0; i< arregloDeDatos.length; i++) {
			for(int j =0; j < arregloDeDatos[i].length; j++) {
				
				if(j == 5 && k < 100) {
					arregloUnidimensional[k] = arregloDeDatos[i][j];
					k++;
				}
			}
		}
		
	      for(int i =0; i < arregloUnidimensional.length-1; i++) {
	    	  for(int j =0; j < arregloUnidimensional.length-i - 1; j++) {
	    		  
	  			if(arregloUnidimensional[j+1] < arregloUnidimensional[j]) {
					
					temp = arregloUnidimensional[j+1];
					arregloUnidimensional[j+1] = arregloUnidimensional[j];
					arregloUnidimensional[j] = temp;
					
				}
	    		  
	    	  }
	      }
		
		return arregloUnidimensional;
		
	}
	
	public void graficaDatos() {
		
		//Grafica miGrafica1 = new Grafica("6.txt");
		/*Grafica miGrafica2 = new Grafica("2.txt");
		Grafica miGrafica3 = new Grafica("3.txt");
		Grafica miGrafica4 = new Grafica("4.txt");
		Grafica miGrafica5 = new Grafica("5.txt");*/
		Grafica miGrafica6 = new Grafica("mejora5.txt");
		
		//float datos1[] = miGrafica1.ordenaDatos();
		/*float datos2[][] = miGrafica2.retornaArregloDeDatos();
		float datos3[][] = miGrafica3.retornaArregloDeDatos();
		float datos4[][] = miGrafica4.retornaArregloDeDatos();
		float datos5[][] = miGrafica5.retornaArregloDeDatos();*/
		float datos6[] = miGrafica6.ordenaDatos();
		
		//XYSeries series1 = new XYSeries("Fis archivo6.txt ");
		/*XYSeries series2 = new XYSeries("Porcentajes Fis2 ");
		XYSeries series3 = new XYSeries("Porcentajes Fis3 ");
		XYSeries series4 = new XYSeries("Porcentajes Fis4 ");
		XYSeries series5 = new XYSeries("Porcentajes Fis5 ");*/
		XYSeries series6 = new XYSeries("Fis mejora5.txt ");
		

			for(int i =0; i < datos6.length; i++) {
				
					
					//series1.add(i, datos1[i]);
					/*series2.add(i, datos2[i][j]);
					series3.add(i, datos3[i][j]);
					series4.add(i, datos4[i][j]);
					series5.add(i, datos5[i][j]);*/
					series6.add(i, datos6[i]);
					
					//System.out.println("indice : "+ i + " "+datos1[i][j] )				}
		}
		

        XYSeriesCollection dataset = new XYSeriesCollection();
	        //dataset.addSeries(series1);
	        /*dataset.addSeries(series2);
	        dataset.addSeries(series3);
	        dataset.addSeries(series4);
	        dataset.addSeries(series5);*/
	        dataset.addSeries(series6);

        //Creacion de la grafica de los datos
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Grafica de progreso", // Tï¿½tulo
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
        
        // Mostramos la grafica en pantalla
        ChartFrame frame = new ChartFrame("Progreso", chart);
        frame.pack();
        frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
            //Ingresar el nombre del archivo a graficar
			Grafica miGrafica = new Grafica();
			miGrafica.graficaDatos();
	}

}
