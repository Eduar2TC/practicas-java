import java.awt.Color;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlot extends JFrame {
	
		  private static final long serialVersionUID = 6294689542092367723L;

		  public ScatterPlot(String title) {
		    super(title);

		    // Create dataset
		    XYDataset dataset = createDataset();

		    // Create chart
		    JFreeChart chart = ChartFactory.createScatterPlot(
		        "Ejemplo de pronostico Regresion Lineal", 
		        "X-Axis", "Y-Axis", dataset);

		    
		    //Changes background color
		    XYPlot plot = (XYPlot)chart.getPlot();
		    plot.setBackgroundPaint(new Color(255,228,196));
		    
		   
		    // Create Panel
		    ChartPanel panel = new ChartPanel(chart);
		    setContentPane(panel);
		  }

		  private XYDataset createDataset() {
			  
			Regresion r = new Regresion(8);
			float []arrX = r.getArregloX();
			float []arrY = r.getArregloY();
			
			  
			  
		    XYSeriesCollection dataset = new XYSeriesCollection();

		    //Boys (Age,weight) series
		    XYSeries series1 = new XYSeries("x, y");
		    series1.add(arrX[0], arrY[0]);
		    series1.add(arrX[1], arrY[1]);
		    series1.add(arrX[2], arrY[2]);
		    series1.add(arrX[3], arrY[3]);
		    series1.add(arrX[4], arrY[4]);
		    series1.add(arrX[5], arrY[5]);
		    series1.add(arrX[6], arrY[6]);
		    series1.add(arrX[7], arrY[7]);

		    dataset.addSeries(series1);
		    

		    return dataset;
	}
		  
		  
}